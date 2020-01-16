package com.gl.loggly.glbook.controller;

import com.gl.loggly.glbook.dao.BookDao;
import com.gl.loggly.glbook.model.Author;
import com.gl.loggly.glbook.model.Book;
import com.gl.loggly.glbook.model.BookInfo;
import com.gl.loggly.glbook.model.BookItem;
import com.gl.loggly.glbook.service.NextSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;



import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookDao bookDao;

    @Autowired
    NextSequenceService nextSequenceService;

    @Autowired
    WebClient.Builder webClientBuilder;     //for communicating with another microservice

    @GetMapping(path = "/{bookName}")
    public ResponseEntity<Book> getBookByName(@PathVariable(required = true)String bookName){
        Long bookId = bookDao.getBookIdByBookName(bookName);

        Author author =  webClientBuilder.build()
                .get()
                .uri("http://localhost:8020/author/" + bookId)
                .retrieve()
                .bodyToMono(Author.class)
                .block();

        BookInfo bookInfo =  webClientBuilder.build()
                .get()
                .uri("http://localhost:8030/book_info/" +bookId)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
        return  ResponseEntity.ok(new Book(bookName,bookInfo.getBookInfo(),author.getAuthorName()));
    }



    @PostMapping(path = "/")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        BookItem bookItem = new BookItem();
        bookItem.setBookId(nextSequenceService.getNextSequence("bookCustomSequences"));
        bookItem.setBookName(book.getName());
        bookItem = bookDao.saveBookItem(bookItem);


        Author author = new Author();
        author.setAuthorName(book.getAuthor());
        author.setBookId(bookItem.getBookId());

         author =  webClientBuilder.build()
                .post()
                .uri("http://localhost:8020/author/")
                .bodyValue( author)
                .retrieve()
                .bodyToMono(Author.class)
                .block();


         BookInfo bookInfo = new BookInfo();
         bookInfo.setBookInfo(book.getBookInfo());
         bookInfo.setBookId(bookItem.getBookId());

         bookInfo =  webClientBuilder.build()
                .post()
                .uri("http://localhost:8030/book_info/")
                .bodyValue(bookInfo)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
        return  ResponseEntity.ok(new Book(book.getName(),bookInfo.getBookInfo(),author.getAuthorName()));
    }


    @PutMapping(path = "/")
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        Long bookId = bookDao.getBookIdByBookName(book.getName());


        BookItem bookItem = new BookItem();
        bookItem.setBookId(bookId);
        bookItem.setBookName(book.getName());
        bookItem = bookDao.updateBookItem(bookItem);


        Author author = new Author();
        author.setAuthorName(book.getAuthor());
        author.setBookId(bookItem.getBookId());

        author =  webClientBuilder.build()
                .put()
                .uri("http://localhost:8020/author/")
                .bodyValue( author)
                .retrieve()
                .bodyToMono(Author.class)
                .block();


        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookInfo(book.getBookInfo());
        bookInfo.setBookId(bookItem.getBookId());

        bookInfo =  webClientBuilder.build()
                .put()
                .uri("http://localhost:8030/book_info/")
                .bodyValue(bookInfo)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
        return  ResponseEntity.ok(new Book(book.getName(),bookInfo.getBookInfo(),author.getAuthorName()));
    }

}
