package com.gl.loggly.glbook.controller;

import com.gl.loggly.glbook.constant.BookConstant;
import com.gl.loggly.glbook.dao.BookDao;
import com.gl.loggly.glbook.model.Author;
import com.gl.loggly.glbook.model.Book;
import com.gl.loggly.glbook.model.BookInfo;
import com.gl.loggly.glbook.model.BookItem;
import com.gl.loggly.glbook.service.NextSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;



import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BookConstant.BOOK_URL)
public class BookController {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private NextSequenceService nextSequenceService;

    @Autowired
    private WebClient.Builder webClientBuilder;     //for communicating with another microservice

    public BookController(BookDao bookDao, NextSequenceService nextSequenceService, WebClient.Builder webClientBuilder) {
        this.bookDao = bookDao;
        this.nextSequenceService = nextSequenceService;
        this.webClientBuilder = webClientBuilder;
    }



    @GetMapping(path = BookConstant.GET_BOOK_BY_NAME_URL)
    public ResponseEntity<Book> getBookByName(@PathVariable(required = true)String bookName){
        Long bookId = bookDao.getBookIdByBookName(bookName);

        Author author =  webClientBuilder.build()
                .get()
                .uri(BookConstant.BOOK_AUTHOR_URL + bookId)
                .retrieve()
                .bodyToMono(Author.class)
                .block();

        BookInfo bookInfo =  webClientBuilder.build()
                .get()
                .uri(BookConstant.BOOK_INFO_URL +bookId)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
        return  ResponseEntity.ok(new Book(bookName,bookInfo.getBookInfo(),author.getAuthorName()));
    }



    @GetMapping(path = BookConstant.GET_ALL_BOOK_URL)
    public ResponseEntity<List<Book>> getAllBook(){
        List<BookItem> bookItems = bookDao.getAllBook();
        List<Book> books = bookItems.stream().map(bookItem -> {
            Author author = webClientBuilder.build()
                    .get()
                    .uri(BookConstant.BOOK_AUTHOR_URL + bookItem.getBookId())
                    .retrieve()
                    .bodyToMono(Author.class)
                    .block();
            BookInfo bookInfo =  webClientBuilder.build()
                    .get()
                    .uri(BookConstant.BOOK_INFO_URL +bookItem.getBookId())
                    .retrieve()
                    .bodyToMono(BookInfo.class)
                    .block();
            return  new Book(bookItem.getBookName(),bookInfo.getBookInfo(),author.getAuthorName());

        })
        .collect(Collectors.toList());

        return ResponseEntity.ok(books);
    }



    @PostMapping(path = BookConstant.SAVE_BOOK_URL)
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        BookItem bookItem = new BookItem();
        bookItem.setBookId(nextSequenceService.getNextSequence("bookCustomSequences"));
        bookItem.setBookName(book.getName());
        bookItem = bookDao.saveUpdateBookItem(bookItem);


        Author author = new Author();
        author.setAuthorName(book.getAuthor());
        author.setBookId(bookItem.getBookId());

         author =  webClientBuilder.build()
                .post()
                .uri(BookConstant.BOOK_AUTHOR_URL)
                .bodyValue( author)
                .retrieve()
                .bodyToMono(Author.class)
                .block();


         BookInfo bookInfo = new BookInfo();
         bookInfo.setBookInfo(book.getBookInfo());
         bookInfo.setBookId(bookItem.getBookId());

         bookInfo =  webClientBuilder.build()
                .post()
                .uri(BookConstant.BOOK_INFO_URL)
                .bodyValue(bookInfo)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
        return  ResponseEntity.ok(new Book(book.getName(),bookInfo.getBookInfo(),author.getAuthorName()));
    }


    @PutMapping(path = BookConstant.UPDATE_BOOK_URL)
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Long bookId = bookDao.getBookIdByBookName(book.getName());


        BookItem bookItem = new BookItem();
        bookItem.setBookId(bookId);
        bookItem.setBookName(book.getName());
        bookItem = bookDao.saveUpdateBookItem(bookItem);


        Author author = new Author();
        author.setAuthorName(book.getAuthor());
        author.setBookId(bookItem.getBookId());

        author =  webClientBuilder.build()
                .put()
                .uri(BookConstant.BOOK_AUTHOR_URL)
                .bodyValue( author)
                .retrieve()
                .bodyToMono(Author.class)
                .block();


        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookInfo(book.getBookInfo());
        bookInfo.setBookId(bookItem.getBookId());

        bookInfo =  webClientBuilder.build()
                .put()
                .uri(BookConstant.BOOK_INFO_URL)
                .bodyValue(bookInfo)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
        return  ResponseEntity.ok(new Book(book.getName(),bookInfo.getBookInfo(),author.getAuthorName()));
    }


    @DeleteMapping(path = BookConstant.DELETE_BOOK)
    public ResponseEntity<String> deleteBook(@PathVariable(required = true)String bookName){
        Long bookId = bookDao.getBookIdByBookName(bookName);
        bookDao.deleteBookItemById(bookId);


            webClientBuilder.build()
                .delete()
                .uri(BookConstant.BOOK_AUTHOR_URL + bookId)
                .retrieve()
                .bodyToMono(Author.class)
                .block();

            webClientBuilder.build()
                .delete()
                .uri(BookConstant.BOOK_INFO_URL +bookId)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
        return  ResponseEntity.ok(" "+ bookName+" deleted successfully");
    }

    @DeleteMapping(path = BookConstant.DELETE_ALL_BOOK_URL)
    public ResponseEntity<String> deleteAllBook(){
        bookDao.deleteAllBookItem();


            webClientBuilder.build()
                .delete()
                .uri(BookConstant.BOOK_AUTHOR_URL )
                .retrieve()
                .bodyToMono(Author.class)
                .block();

            webClientBuilder.build()
                .delete()
                .uri(BookConstant.BOOK_INFO_URL)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
        return  ResponseEntity.ok("All Book deleted successfully");
    }

}
