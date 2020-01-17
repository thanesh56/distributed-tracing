package com.gl.loggly.glbook.controller;

import com.gl.loggly.glbook.constant.BookConstant;
import com.gl.loggly.glbook.dao.BookDao;
import com.gl.loggly.glbook.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(BookConstant.BOOK_URL)
public class BookController {

    private final BookDao bookDao;


    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }



    @GetMapping(path = BookConstant.GET_BOOK_BY_NAME_URL)
    public ResponseEntity<Book> getBookByName(@PathVariable(required = true)String bookName){
        return  ResponseEntity.ok(bookDao.getBookByBookName(bookName));
    }



    @GetMapping(path = BookConstant.GET_ALL_BOOK_URL)
    public ResponseEntity<List<Book>> getAllBook(){
        return ResponseEntity.ok(bookDao.getAllBook());
    }



    @PostMapping(path = BookConstant.SAVE_BOOK_URL)
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return  ResponseEntity.ok(bookDao.saveBook(book));
    }



    @PutMapping(path = BookConstant.UPDATE_BOOK_URL)
    public ResponseEntity<Book> updateBook(@RequestBody Book book){
        return  ResponseEntity.ok(bookDao.updateBook(book));
    }



    @DeleteMapping(path = BookConstant.DELETE_BOOK)
    public ResponseEntity<String> deleteBook(@PathVariable()String bookName){
        return  ResponseEntity.ok(bookDao.deleteBookByBookName(bookName));
    }



    @DeleteMapping(path = BookConstant.DELETE_ALL_BOOK_URL)
    public ResponseEntity<String> deleteAllBook(){
        return  ResponseEntity.ok(bookDao.deleteAllBook());
    }

}
