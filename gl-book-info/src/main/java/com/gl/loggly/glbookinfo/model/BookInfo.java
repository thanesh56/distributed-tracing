package com.gl.loggly.glbookinfo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookInfoId;
    private String bookInfo;
    private long bookId;
}
