package com.gl.loggly.glbookauther.controller;

import com.gl.loggly.glbookauther.constant.AuthorConstant;
import com.gl.loggly.glbookauther.dao.AuthorDao;
import com.gl.loggly.glbookauther.model.Author;
import com.gl.loggly.glbookauther.service.NextSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(AuthorConstant.AUTHOR_URL)
public class AuthorController {
    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }


    @GetMapping(path = AuthorConstant.GET_AUTHOR_BY_BOOK_ID_URL )
    public Author getAuthorByBookId(@PathVariable(required = true)long bookId){
        return authorDao.getAuthorByBookId(bookId);
    }


    @PostMapping(path = AuthorConstant.SAVE_AUTHOR_URL)
    public Author saveAuthor(@RequestBody Author author){
        return authorDao.saveAuthor(author);
    }


    @PutMapping(path = AuthorConstant.UPDATE_AUTHOR_URL)
    public Author updateAuthor(@RequestBody Author author){
        return authorDao.updateAuthor(author);
    }

    @DeleteMapping(path = AuthorConstant.DELETE_AUTHOR)
    public void deleteAuthorByBookId(@PathVariable(required = true)long bookId){
         authorDao.deleteAuthorByBookId(bookId);
    }

    @DeleteMapping(path = AuthorConstant.DELETE_ALL_AUTHOR_URL)
    public void deleteAllAuthorByBookId(){
        authorDao.deleteAll();
    }
}
