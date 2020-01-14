package com.gl.loggly.glbook.dao.impl;

import com.gl.loggly.glbook.dao.BookDao;
import com.gl.loggly.glbook.model.BookItem;
import com.gl.loggly.glbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookDaoImpl implements BookDao {

    @Autowired
    BookRepository bookRepository;

    @Override
    public Long getBookIdByBookName(String bookName) {
        return bookRepository.findBookIdByBookName(bookName);
    }
}
