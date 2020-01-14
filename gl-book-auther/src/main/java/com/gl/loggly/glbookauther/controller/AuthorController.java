package com.gl.loggly.glbookauther.controller;

import com.gl.loggly.glbookauther.dao.AuthorDao;
import com.gl.loggly.glbookauther.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorDao authorDao;

    @GetMapping(path = "/{bookId}")
    public Author getAuthorByBookId(@PathVariable(required = true)long bookId){
        return authorDao.getAuthorByBookId(bookId);
    }
}
