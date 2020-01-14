package com.gl.loggly.glbook.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo {
    private long bookInfoId;
    private String bookInfo;
    private long bookId;
}
