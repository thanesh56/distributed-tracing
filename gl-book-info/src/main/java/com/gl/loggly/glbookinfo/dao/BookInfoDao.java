package com.gl.loggly.glbookinfo.dao;

import com.gl.loggly.glbookinfo.model.BookInfo;

public interface BookInfoDao {
    BookInfo getBookInfoByBookId(long id);

    BookInfo saveBookInfo(BookInfo bookInfo);

    void deleteBookInfoByBookId(long id);

    void deleteAllBookInfo();

    BookInfo updateBookInfo(BookInfo bookInfo);
}
