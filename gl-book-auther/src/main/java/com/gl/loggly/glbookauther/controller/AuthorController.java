package com.gl.loggly.glbookauther.controller;

import com.gl.loggly.glbookauther.dao.AuthorDao;
import com.gl.loggly.glbookauther.model.Author;
import com.gl.loggly.glbookauther.service.NextSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorDao authorDao;

    @Autowired
    NextSequenceService nextSequenceService;

    @GetMapping(path = "/{bookId}")
    public Author getAuthorByBookId(@PathVariable(required = true)long bookId){
        return authorDao.getAuthorByBookId(bookId);
    }

    @PostMapping(path = "/")
    public Author addAuthor(@RequestBody Author author){
        Author newAuthor = new Author();
        newAuthor.setAuthorId(nextSequenceService.getNextSequence("authorCustomSequences"));
        newAuthor.setAuthorName(author.getAuthorName());
        newAuthor.setBookId(author.getBookId());
        return authorDao.saveAuthor(newAuthor);

    }


    @PostMapping(path = "/")
    public Author updateAuthor(@RequestBody Author author){
        Author newAuthor = new Author();
        newAuthor.setAuthorId();
        newAuthor.setAuthorName(author.getAuthorName());
        newAuthor.setBookId(author.getBookId());
        return authorDao.saveAuthor(newAuthor);

    }
}
