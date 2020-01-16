package com.gl.loggly.glbook.dao;


import com.gl.loggly.glbook.model.BookItem;

public interface BookDao {
    Long getBookIdByBookName(String bookName);
    BookItem saveBookItem(BookItem bookItem);
    BookItem updateBookItem(BookItem bookItem);
    void deleteBookItemById(long bookId);
    void deleteAllBookItem();
}
