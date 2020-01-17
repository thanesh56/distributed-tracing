package com.gl.loggly.glbook.dao;


import com.gl.loggly.glbook.model.Book;


import java.util.List;

public interface BookDao {
    Book getBookByBookName(String bookName);
    List<Book> getAllBook();
    Book saveBook(Book book);
    Book updateBook(Book book);
    String deleteBookByBookName(String bookName);
    String deleteAllBook();
}
