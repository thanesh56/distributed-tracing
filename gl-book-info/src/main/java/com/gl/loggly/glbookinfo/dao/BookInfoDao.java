package com.gl.loggly.glbookinfo.dao;

import com.gl.loggly.glbookinfo.model.BookInfo;

public interface BookInfoDao {
    BookInfo getBookInfoByBookId(long id);
}
