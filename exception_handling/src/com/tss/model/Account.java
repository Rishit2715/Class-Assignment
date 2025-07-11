package com.tss.model;

public abstract class Account {
	protected int accNo;
	protected String name;
	protected double balance;

	public Account(int accNo, String name, double balance) {
		this.accNo = accNo;
		this.name = name;
		this.balance = balance;
	}
	
	public abstract void debit(double amount);
	public abstract void credit(double amount);


	public void displayDetails() {
		System.out.println("Account No: " + accNo);
		System.out.println("Name: " + name);
		System.out.println("Balance: " + balance);
	}
}
