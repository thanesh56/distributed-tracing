package com.gl.loggly.glbook.dao.impl;

import com.gl.loggly.glbook.dao.BookDao;
import com.gl.loggly.glbook.model.Author;
import com.gl.loggly.glbook.model.Book;
import com.gl.loggly.glbook.model.BookInfo;
import com.gl.loggly.glbook.model.BookItem;
import com.gl.loggly.glbook.repository.BookRepository;
import com.gl.loggly.glbook.service.AuthorService;
import com.gl.loggly.glbook.service.BookInfoService;
import com.gl.loggly.glbook.service.NextSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookDaoImpl implements BookDao {

    private final AuthorService authorService;
    private final BookInfoService bookInfoService;
    private final NextSequenceService nextSequenceService;
    private final BookRepository bookRepository;

    public BookDaoImpl(AuthorService authorService, BookInfoService bookInfoService, NextSequenceService nextSequenceService, BookRepository bookRepository) {
        this.authorService = authorService;
        this.bookInfoService = bookInfoService;
        this.nextSequenceService = nextSequenceService;
        this.bookRepository = bookRepository;
    }


    @Override
    public Book getBookByBookName(String bookName) {

        long bookId =  bookRepository.findByBookName(bookName).getBookId();
        Author author = authorService.getAuthorByBookId(bookId);
        BookInfo bookInfo = bookInfoService.getBookInfoByBookId(bookId);
        return new Book(bookName,bookInfo.getBookInfo(),author.getAuthorName());

    }

    @Override
    public List<Book> getAllBook() {

        List<BookItem> bookItems = bookRepository.findAll();
        return bookItems.stream().map(bookItem -> {
            Author author = authorService.getAuthorByBookId(bookItem.getBookId());
            BookInfo bookInfo = bookInfoService.getBookInfoByBookId(bookItem.getBookId());
            return  new Book(bookItem.getBookName(),bookInfo.getBookInfo(),author.getAuthorName());
        }).collect(Collectors.toList());

    }

    @Override
    public Book saveBook(Book book) {
        BookItem bookItem = new BookItem();
        bookItem.setBookId(nextSequenceService.getNextSequence("bookCustomSequences"));
        bookItem.setBookName(book.getName());
        bookItem = bookRepository.save(bookItem);

        Author author = new Author();
        author.setAuthorName(book.getAuthor());
        author.setBookId(bookItem.getBookId());
        author = authorService.addAuthor(author);

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookInfo(book.getBookInfo());
        bookInfo.setBookId(bookItem.getBookId());
        bookInfo =  bookInfoService.addBookInfo(bookInfo);

        return new Book(book.getName(),bookInfo.getBookInfo(),author.getAuthorName());
    }

    @Override
    public Book updateBook(Book book) {
        BookItem bookItem = new BookItem();
        bookItem.setBookId(bookRepository.findByBookName(book.getName()).getBookId());
        bookItem.setBookName(book.getName());
        bookItem = bookRepository.save(bookItem);

        Author author = new Author();
        author.setAuthorName(book.getAuthor());
        author.setBookId(bookItem.getBookId());
        author = authorService.updateAuthor(author);

        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookInfo(book.getBookInfo());
        bookInfo.setBookId(bookItem.getBookId());
        bookInfo = bookInfoService.updateBookInfo(bookInfo);

        return new Book(book.getName(),bookInfo.getBookInfo(),author.getAuthorName());
    }

    @Override
    public String deleteBookByBookName(String bookName) {

        long bookId =  bookRepository.findByBookName(bookName).getBookId();
        bookRepository.deleteById(bookId);
        authorService.deleteAuthorByBookId(bookId);
        bookInfoService.deleteBookInfoByBookId(bookId);

        return  " "+ bookName+" deleted successfully";


    }

    @Override
    public String deleteAllBook() {
        bookRepository.deleteAll();
        authorService.deleteAllAuthor();
        bookInfoService.deleteAllBookInfo();
        return "All Book deleted successfully";
    }
}
