package com.gl.loggly.glbookinfo.controller;

import com.gl.loggly.glbookinfo.constant.BookInfoConstant;
import com.gl.loggly.glbookinfo.dao.BookInfoDao;
import com.gl.loggly.glbookinfo.model.BookInfo;
import com.gl.loggly.glbookinfo.service.NextSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BookInfoConstant.BOOK_INFO_URL)
public class BookInfoController {

    @Autowired
    private BookInfoDao bookInfoDao;

    @Autowired
    private NextSequenceService nextSequenceService;

    public BookInfoController(BookInfoDao bookInfoDao, NextSequenceService nextSequenceService) {
        this.bookInfoDao = bookInfoDao;
        this.nextSequenceService = nextSequenceService;
    }

    @GetMapping(path = BookInfoConstant.GET_BOOK_INFO_BY_BOOK_ID_URL)
    public BookInfo getBookInfoByBookId(@PathVariable(required = true)long bookId){
        return bookInfoDao.getBookInfoByBookId(bookId);
    }

    @PostMapping(path = BookInfoConstant.SAVE_BOOK_INFO_URL)
    public BookInfo addBookInfo(@RequestBody BookInfo bookInfo){
        BookInfo newBookInfo = new BookInfo();
        newBookInfo.setBookInfoId(nextSequenceService.getNextSequence("bookInfoCustomSequences"));
        newBookInfo.setBookInfo(bookInfo.getBookInfo());
        newBookInfo.setBookId(bookInfo.getBookId());
        return bookInfoDao.saveBookInfo(newBookInfo);

    }

    @PutMapping(path = BookInfoConstant.UPDATE_BOOK_INFO_URL)
    public BookInfo updateBookInfo(@RequestBody BookInfo bookInfo){
        BookInfo newBookInfo = new BookInfo();
        newBookInfo.setBookInfoId(bookInfoDao.getBookInfoByBookId(bookInfo.getBookId()).getBookInfoId());
        newBookInfo.setBookInfo(bookInfo.getBookInfo());
        newBookInfo.setBookId(bookInfo.getBookId());
        return bookInfoDao.saveBookInfo(newBookInfo);

    }

    @DeleteMapping(path = BookInfoConstant.DELETE_BOOK_INFO)
    public void deleteBookInfoByBookId(@PathVariable(required = true)long bookId){
         bookInfoDao.getBookInfoByBookId(bookId);
    }

    @DeleteMapping(path = BookInfoConstant.DELETE_ALL_BOOK_INFO_URL)
    public void deleteAllBookInfo(){
        bookInfoDao.deleteAllBookInfo();
    }
}
