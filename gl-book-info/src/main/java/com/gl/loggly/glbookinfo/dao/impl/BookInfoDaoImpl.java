package com.gl.loggly.glbookinfo.dao.impl;

import com.gl.loggly.glbookinfo.dao.BookInfoDao;
import com.gl.loggly.glbookinfo.model.BookInfo;
import com.gl.loggly.glbookinfo.repository.BookInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookInfoDaoImpl implements BookInfoDao {
    @Autowired
    BookInfoRepository bookInfoRepository;
    @Override
    public BookInfo getBookInfoByBookId(long id) {
        return bookInfoRepository.findBookInfoByBookId(id);
    }

    @Override
    public BookInfo saveBookInfo(BookInfo bookInfo) {
        return bookInfoRepository.save(bookInfo);
    }
}
