package com.tss.exception;

public class OverDraftLimitReachedException extends RuntimeException{
	private double balance;
	
	public OverDraftLimitReachedException(double balance) {
		this.balance = balance;
		getMessage();
	}
	
	public String getMessage()
	{
		return "over draft limit reachred: You have: "+balance;
	}
}
