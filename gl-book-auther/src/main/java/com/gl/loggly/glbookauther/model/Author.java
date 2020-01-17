package com.gl.loggly.glbookauther.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Author {
    @Id
    private long authorId;
    private String authorName;
    private long bookId;

}
