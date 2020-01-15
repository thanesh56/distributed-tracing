package com.gl.loggly.glbookauther.controller;

import com.gl.loggly.glbookauther.dao.AuthorDao;
import com.gl.loggly.glbookauther.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorDao authorDao;

    @GetMapping(path = "/{bookId}")
    public Author getAuthorByBookId(@PathVariable(required = true)long bookId){
        return authorDao.getAuthorByBookId(bookId);
    }

    @PostMapping(path = "/")
    public Author addAuthor(@RequestBody Author author){
        return authorDao.saveAuthor(author);
    }
}
