package com.tss.exception;

public class InsufficientWaterException extends RuntimeException {

	public InsufficientWaterException() {
		getMessage();
	}

	public String getMessage() {
		return "Currently you are not having taht much water Stored.";
	}
}