package com.tss.exception;

public class MinimumBalanceException extends RuntimeException {
	private double balance;
	
	public MinimumBalanceException(double balance) {
		this.balance = balance;
		getMessage();
	}
	
	public String getMessage()
	{
		return "balace cant be less than limit: You have: "+balance;
	}

}
