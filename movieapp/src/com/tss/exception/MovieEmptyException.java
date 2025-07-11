package com.tss.exception;

public class MovieEmptyException extends RuntimeException {
    public MovieEmptyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "No movies in list.";
    }
}
