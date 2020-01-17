package com.gl.loggly.glbook.controller;

import com.gl.loggly.glbook.constant.BookConstant;
import com.gl.loggly.glbook.dao.BookDao;
import com.gl.loggly.glbook.model.Author;
import com.gl.loggly.glbook.model.Book;
import com.gl.loggly.glbook.model.BookInfo;
import com.gl.loggly.glbook.model.BookItem;
import com.gl.loggly.glbook.service.AuthorService;
import com.gl.loggly.glbook.service.BookInfoService;
import com.gl.loggly.glbook.service.NextSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BookConstant.BOOK_URL)
public class BookController {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookInfoService bookInfoService;


    @Autowired
    private NextSequenceService nextSequenceService;

    public BookController(BookDao bookDao, AuthorService authorService, BookInfoService bookInfoService, NextSequenceService nextSequenceService) {
        this.bookDao = bookDao;
        this.authorService = authorService;
        this.bookInfoService = bookInfoService;
        this.nextSequenceService = nextSequenceService;
    }



    @GetMapping(path = BookConstant.GET_BOOK_BY_NAME_URL)
    public ResponseEntity<Book> getBookByName(@PathVariable(required = true)String bookName){
        Long bookId = bookDao.getBookIdByBookName(bookName);
        Author author = authorService.getAuthorByBookId(bookId);
        BookInfo bookInfo = bookInfoService.getBookInfoByBookId(bookId);
        return  ResponseEntity.ok(new Book(bookName,bookInfo.getBookInfo(),author.getAuthorName()));
    }



    @GetMapping(path = BookConstant.GET_ALL_BOOK_URL)
    public ResponseEntity<List<Book>> getAllBook(){
        List<BookItem> bookItems = bookDao.getAllBook();
        List<Book> books = bookItems.stream().map(bookItem -> {
            Author author = authorService.getAuthorByBookId(bookItem.getBookId());
            BookInfo bookInfo = bookInfoService.getBookInfoByBookId(bookItem.getBookId());
            return  new Book(bookItem.getBookName(),bookInfo.getBookInfo(),author.getAuthorName());
        }).collect(Collectors.toList());
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
        author = authorService.addAuthor(author);

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookInfo(book.getBookInfo());
        bookInfo.setBookId(bookItem.getBookId());
        bookInfo =  bookInfoService.addBookInfo(bookInfo);

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
        author = authorService.updateAuthor(author);

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookInfo(book.getBookInfo());
        bookInfo.setBookId(bookItem.getBookId());
        bookInfo = bookInfoService.updateBookInfo(bookInfo);

        return  ResponseEntity.ok(new Book(book.getName(),bookInfo.getBookInfo(),author.getAuthorName()));
    }


    @DeleteMapping(path = BookConstant.DELETE_BOOK)
    public ResponseEntity<String> deleteBook(@PathVariable(required = true)String bookName){
        Long bookId = bookDao.getBookIdByBookName(bookName);
        bookDao.deleteBookItemById(bookId);
        authorService.deleteAuthorByBookId(bookId);
        bookInfoService.deleteBookInfoByBookId(bookId);
        return  ResponseEntity.ok(" "+ bookName+" deleted successfully");
    }

    @DeleteMapping(path = BookConstant.DELETE_ALL_BOOK_URL)
    public ResponseEntity<String> deleteAllBook(){
        bookDao.deleteAllBookItem();
        authorService.deleteAllAuthor();
        bookInfoService.deleteAllBookInfo();
        return  ResponseEntity.ok("All Book deleted successfully");
    }

}
