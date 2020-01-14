package com.gl.loggly.glbook.controller;

import com.gl.loggly.glbook.dao.BookDao;
import com.gl.loggly.glbook.model.Author;
import com.gl.loggly.glbook.model.Book;
import com.gl.loggly.glbook.model.BookInfo;
import com.gl.loggly.glbook.model.BookItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;



import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookDao bookDao;

    @Autowired
    WebClient.Builder webClientBuilder;     //for communicating with another microservice

    @GetMapping(path = "/{bookName}")
    public ResponseEntity<Book> getBookByName(@PathVariable(required = true)String bookName){
        Long bookId = bookDao.getBookIdByBookName(bookName);

        Author author =  webClientBuilder.build()
                .get()
                .uri("http://localhost:8020/author/" + bookId)
                .retrieve()
                .bodyToMono(Author.class)
                .block();

        BookInfo bookInfo =  webClientBuilder.build()
                .get()
                .uri("http://localhost:8030/book_info/" +bookId)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();






        return  ResponseEntity.ok(new Book("Java",bookInfo.getBookInfo(),author.getAuthorName()));
    }




}
