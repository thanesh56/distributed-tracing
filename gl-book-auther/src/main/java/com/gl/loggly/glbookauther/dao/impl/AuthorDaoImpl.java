package com.gl.loggly.glbookauther.dao.impl;

import com.gl.loggly.glbookauther.dao.AuthorDao;
import com.gl.loggly.glbookauther.model.Author;
import com.gl.loggly.glbookauther.repository.AuthorRepository;
import com.gl.loggly.glbookauther.service.NextSequenceService;
import org.springframework.stereotype.Service;

@Service
public class AuthorDaoImpl implements AuthorDao {

    private final AuthorRepository authorRepository;
    private final NextSequenceService nextSequenceService;

    public AuthorDaoImpl(AuthorRepository authorRepository, NextSequenceService nextSequenceService) {
        this.authorRepository = authorRepository;
        this.nextSequenceService = nextSequenceService;
    }


    @Override
    public Author getAuthorByBookId(long id) {
        return authorRepository.findAuthorByBookId(id);
    }

    @Override
    public Author saveAuthor(Author author) {
        author.setAuthorId(nextSequenceService.getNextSequence("authorCustomSequences"));
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        author.setAuthorId(authorRepository.findAuthorByBookId(author.getBookId()).getAuthorId());
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorByBookId(long id) {
        authorRepository.deleteByBookId(id);
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();

    }

}
