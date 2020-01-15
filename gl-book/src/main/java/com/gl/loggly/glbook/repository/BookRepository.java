package com.gl.loggly.glbook.repository;

import com.gl.loggly.glbook.model.BookItem;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<BookItem,Long> {

    BookItem findByBookName(String bookName);


}
