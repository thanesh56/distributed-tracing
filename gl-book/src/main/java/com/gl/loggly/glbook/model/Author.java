package com.gl.loggly.glbook.model;

import lombok.*;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private long authorId;
    private String authorName;
    private long bookId;

}
