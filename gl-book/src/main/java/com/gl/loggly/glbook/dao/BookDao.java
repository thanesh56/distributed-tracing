package com.gl.loggly.glbook.dao;


import com.gl.loggly.glbook.model.BookItem;

import java.util.List;

public interface BookDao {
    Long getBookIdByBookName(String bookName);
    List<BookItem> getAllBook();
    BookItem saveUpdateBookItem(BookItem bookItem);
    void deleteBookItemById(long bookId);
    void deleteAllBookItem();
}
