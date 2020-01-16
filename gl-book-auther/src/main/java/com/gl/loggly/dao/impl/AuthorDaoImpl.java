package com.gl.loggly.glbookauther.dao.impl;

import com.gl.loggly.glbookauther.dao.AuthorDao;
import com.gl.loggly.glbookauther.model.Author;
import com.gl.loggly.glbookauther.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorDaoImpl implements AuthorDao {
    @Autowired
    AuthorRepository authorRepository;


    @Override
    public Author getAuthorByBookId(long id) {
        return authorRepository.findAuthorByBookId(id);
    }

    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
}
