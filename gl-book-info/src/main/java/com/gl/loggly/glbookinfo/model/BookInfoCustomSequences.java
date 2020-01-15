package com.gl.loggly.glbookinfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bookInfoCustomSequences")
public class BookInfoCustomSequences {
    @Id
    private String id;
    private int seq;


}