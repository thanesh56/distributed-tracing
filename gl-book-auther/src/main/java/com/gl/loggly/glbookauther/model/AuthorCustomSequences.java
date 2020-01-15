package com.gl.loggly.glbookauther.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authorCustomSequences")
public class AuthorCustomSequences {
    @Id
    private String id;
    private int seq;


}