package com.gl.loggly.glbook.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private long authorId;
    private String authorName;
    private long bookId;

}
