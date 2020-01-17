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
    private final BookInfoDao bookInfoDao;

    public BookInfoController(BookInfoDao bookInfoDao) {
        this.bookInfoDao = bookInfoDao;

    }

    @GetMapping(path = BookInfoConstant.GET_BOOK_INFO_BY_BOOK_ID_URL)
    public BookInfo getBookInfoByBookId(@PathVariable long bookId){
        return bookInfoDao.getBookInfoByBookId(bookId);
    }

    @PostMapping(path = BookInfoConstant.SAVE_BOOK_INFO_URL)
    public BookInfo addBookInfo(@RequestBody BookInfo bookInfo){
        return bookInfoDao.saveBookInfo(bookInfo);

    }

    @PutMapping(path = BookInfoConstant.UPDATE_BOOK_INFO_URL)
    public BookInfo updateBookInfo(@RequestBody BookInfo bookInfo){
        return bookInfoDao.updateBookInfo(bookInfo);

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
