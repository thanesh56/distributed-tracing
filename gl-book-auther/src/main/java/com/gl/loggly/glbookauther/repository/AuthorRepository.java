package com.gl.loggly.glbookauther.repository;

import com.gl.loggly.glbookauther.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author findAuthorByBookId(long id);
}
