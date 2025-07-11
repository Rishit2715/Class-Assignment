package com.tss.model.inheritance.model;

import com.tss.model.inheritance.model.Account;

public class CurrentAccount extends Account {

	private double overdraftLimit;

	public CurrentAccount(int accNo, String name, double balance, double overdraftLimit) {
		super(accNo, name, balance);
		this.overdraftLimit = overdraftLimit;
	}

	public void debit(double amount) {
		if (balance - amount >= -overdraftLimit) {
			balance -= amount;
			System.out.println("Amount debited new balance: " + balance);
			
		} else {
			System.out.println("Exceeds overdraft limit.");
		}
	}
	
	public void credit(double amount) {
		balance += amount;
		System.out.println("Amount credited new balance: " + balance);
	}

	public void displayDetails() {
		super.displayDetails();
		System.out.println("Overdraft Limit: " + overdraftLimit);
	}

	


}
