package com.gl.loggly.glbookauther.repository;

import com.gl.loggly.glbookauther.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends MongoRepository<Author,Long> {

    Author findAuthorByBookId(long id);
}
