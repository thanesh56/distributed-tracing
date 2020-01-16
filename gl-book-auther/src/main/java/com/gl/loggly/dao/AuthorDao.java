package com.gl.loggly.glbookauther.dao;

import com.gl.loggly.glbookauther.model.Author;


public interface AuthorDao {
    Author getAuthorByBookId(long id);
    Author saveAuthor(Author author);
    void deleteAuthorByBookId(long id);
    void deleteAll();
}
