package com.tss.exception;

public class FeedbackException extends Exception {
    public FeedbackException(String message) {
        super(message);
    }

    public FeedbackException(String message, Throwable cause) {
        super(message, cause);
    }
}

