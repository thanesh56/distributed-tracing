package com.gl.loggly.glbookinfo.dao.impl;

import com.gl.loggly.glbookinfo.dao.BookInfoDao;
import com.gl.loggly.glbookinfo.model.BookInfo;
import com.gl.loggly.glbookinfo.repository.BookInfoRepository;
import com.gl.loggly.glbookinfo.service.NextSequenceService;
import org.springframework.stereotype.Service;

@Service
public class BookInfoDaoImpl implements BookInfoDao {

    private final BookInfoRepository bookInfoRepository;
    private final NextSequenceService nextSequenceService;

    public BookInfoDaoImpl(BookInfoRepository bookInfoRepository, NextSequenceService nextSequenceService) {
        this.bookInfoRepository = bookInfoRepository;
        this.nextSequenceService = nextSequenceService;
    }

    @Override
    public BookInfo getBookInfoByBookId(long id) {
        return bookInfoRepository.findBookInfoByBookId(id);
    }

    @Override
    public BookInfo saveBookInfo(BookInfo bookInfo) {
        bookInfo.setBookInfoId(nextSequenceService.getNextSequence("bookInfoCustomSequences"));
        return bookInfoRepository.save(bookInfo);
    }


    @Override
    public BookInfo updateBookInfo(BookInfo bookInfo) {
        bookInfo.setBookInfoId(bookInfoRepository.findBookInfoByBookId(bookInfo.getBookId()).getBookInfoId());
        return bookInfoRepository.save(bookInfo);
    }


    @Override
    public void deleteBookInfoByBookId(long id) {
        bookInfoRepository.deleteByBookId(id);
    }

    @Override
    public void deleteAllBookInfo() {
        bookInfoRepository.deleteAll();

    }


}
