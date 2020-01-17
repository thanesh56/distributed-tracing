package com.gl.loggly.glbook.service;

import com.gl.loggly.glbook.constant.BookConstant;
import com.gl.loggly.glbook.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AuthorService {

    @Autowired
    private WebClient.Builder webClientBuilder;//for communicating with another microservice

    public AuthorService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Author getAuthorByBookId(long bookId){
     return    webClientBuilder.build()
                .get()
                .uri(BookConstant.BOOK_AUTHOR_URL + bookId)
                .retrieve()
                .bodyToMono(Author.class)
                .block();
    }

    public Author addAuthor(Author author){
       return webClientBuilder.build()
                .post()
                .uri(BookConstant.BOOK_AUTHOR_URL)
                .bodyValue( author)
                .retrieve()
                .bodyToMono(Author.class)
                .block();
    }

    public Author updateAuthor(Author author){
        return webClientBuilder.build()
                .put()
                .uri(BookConstant.BOOK_AUTHOR_URL)
                .bodyValue( author)
                .retrieve()
                .bodyToMono(Author.class)
                .block();
    }


    public void deleteAuthorByBookId(Long bookId) {
        webClientBuilder.build()
                .delete()
                .uri(BookConstant.BOOK_AUTHOR_URL + bookId)
                .retrieve()
                .bodyToMono(Author.class)
                .block();
    }

    public void deleteAllAuthor() {
        webClientBuilder.build()
                .delete()
                .uri(BookConstant.BOOK_AUTHOR_URL )
                .retrieve()
                .bodyToMono(Author.class)
                .block();
    }
}
