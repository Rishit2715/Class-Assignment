package com.tss.exception;

public class CapacityFullException extends RuntimeException {
    public CapacityFullException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "No space for storing movies. You can store a maximum of 5 movies.";
    }
}
