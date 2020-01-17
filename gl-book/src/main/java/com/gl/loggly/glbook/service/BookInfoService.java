package com.gl.loggly.glbook.service;

import com.gl.loggly.glbook.constant.BookConstant;
import com.gl.loggly.glbook.model.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class BookInfoService {

    @Autowired
    private WebClient.Builder webClientBuilder;     //for communicating with another microservice

    public BookInfoService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public BookInfo getBookInfoByBookId(long bookId){
      return webClientBuilder.build()
                .get()
                .uri(BookConstant.BOOK_INFO_URL +bookId)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
    }

    public BookInfo addBookInfo(BookInfo bookInfo){
        return webClientBuilder.build()
                .post()
                .uri(BookConstant.BOOK_INFO_URL)
                .bodyValue(bookInfo)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
    }

    public BookInfo updateBookInfo(BookInfo bookInfo) {
        return  webClientBuilder.build()
                .put()
                .uri(BookConstant.BOOK_INFO_URL)
                .bodyValue(bookInfo)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
    }

    public void deleteBookInfoByBookId(Long bookId) {
        webClientBuilder.build()
                .delete()
                .uri(BookConstant.BOOK_INFO_URL +bookId)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
    }

    public void deleteAllBookInfo() {
        webClientBuilder.build()
                .delete()
                .uri(BookConstant.BOOK_INFO_URL)
                .retrieve()
                .bodyToMono(BookInfo.class)
                .block();
    }
}
