package com.tss.exception;

public class OverFillException extends RuntimeException {

	public OverFillException() {
		getMessage();
	}

	public String getMessage() {
		return "enter water liters under capacity. Currently it exceeds water storage capaqcity";
	}
}