package com.tss.model;

import com.tss.exception.NegativeAmountException;
import com.tss.exception.OverDraftLimitReachedException;

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
			throw new OverDraftLimitReachedException(balance);
		}
	}
	
	public void credit(double amount) {
		if(amount>0)
		{
			balance = balance+amount;
			System.out.println("credited Successfully.  New Balance: "+balance);
			return;
		}
		throw new NegativeAmountException(amount);
	}

	public void displayDetails() {
		super.displayDetails();
		System.out.println("Overdraft Limit: " + overdraftLimit);
	}

	


}
