package com.tss.exception;

public class NoSuchMovieFoundException extends RuntimeException{
	public NoSuchMovieFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Movies with this id does not exist";
    }
}
