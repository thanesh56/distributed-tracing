package com.gl.loggly.glbook.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String exception) {
        super(exception);
    }
}
