package com.gl.loggly.glbook.repository;

import com.gl.loggly.glbook.model.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookItem,Long> {

    @Query("SELECT bookId FROM BookItem WHERE bookName = ?1")
    Long findBookIdByBookName(String bookName);
}
