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
        BookItem bookItem = bookRepository.findByBookName(bookName);
        return bookItem.getBookId();
    }

    @Override
    public BookItem saveBookItem(BookItem bookItem) {
        return bookRepository.save(bookItem);
    }

    @Override
    public BookItem updateBookItem(BookItem bookItem) {
        return bookRepository.save(bookItem);
    }

    @Override
    public void deleteBookItemById(long bookId) {
        bookRepository.deleteById(bookId);

    }

    @Override
    public void deleteAllBookItem() {
        bookRepository.deleteAll();

    }
}
