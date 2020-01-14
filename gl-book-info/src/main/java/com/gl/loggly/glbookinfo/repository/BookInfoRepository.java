package com.gl.loggly.glbookinfo.repository;

import com.gl.loggly.glbookinfo.model.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo,Long> {

    BookInfo findBookInfoByBookId(long id);
}
