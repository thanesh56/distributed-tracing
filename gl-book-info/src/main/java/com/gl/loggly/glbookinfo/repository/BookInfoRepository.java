package com.gl.loggly.glbookinfo.repository;

import com.gl.loggly.glbookinfo.model.BookInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoRepository extends MongoRepository<BookInfo,Long> {

    BookInfo findBookInfoByBookId(long id);
    void deleteByBookId(long id);
}
