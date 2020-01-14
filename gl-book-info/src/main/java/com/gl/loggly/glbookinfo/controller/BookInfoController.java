package com.gl.loggly.glbookinfo.controller;

import com.gl.loggly.glbookinfo.dao.BookInfoDao;
import com.gl.loggly.glbookinfo.model.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book_info")
public class BookInfoController {

    @Autowired
    BookInfoDao bookInfoDao;

    @GetMapping(path = "/{bookId}")
    public BookInfo getBookInfoByBookId(@PathVariable(required = true)long bookId){
        return bookInfoDao.getBookInfoByBookId(bookId);
    }
}
