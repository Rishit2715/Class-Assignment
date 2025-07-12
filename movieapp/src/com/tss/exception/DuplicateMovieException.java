package com.tss.exception;

public class DuplicateMovieException extends RuntimeException {
    public DuplicateMovieException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "A movie with this ID already exists.";
    }
}
