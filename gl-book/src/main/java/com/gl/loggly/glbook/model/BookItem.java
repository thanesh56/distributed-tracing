package com.gl.loggly.glbook.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class BookItem {
    @Id
    private long bookId;
    private String bookName;

}
