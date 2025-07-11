package com.tss.exception;

public class NegativeAmountException extends RuntimeException{
	private double amount;
	
	public NegativeAmountException(double amount) {
		this.amount = amount;
		getMessage();
	}
	
	public String getMessage()
	{
		return "amount cant be negative: You have: "+amount;
	}
}
