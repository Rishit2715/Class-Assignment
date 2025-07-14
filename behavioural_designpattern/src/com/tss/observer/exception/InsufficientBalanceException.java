package com.tss.observer.exception;

public class InsufficientBalanceException extends RuntimeException {
	private double balance;

	public InsufficientBalanceException(double balance) {
		this.balance = balance;
	}

	@Override
	public String getMessage() {
		return "Insufficient balance! You only have: " + balance;
	}
}
