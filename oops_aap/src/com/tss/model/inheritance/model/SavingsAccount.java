package com.tss.model.inheritance.model;

import com.tss.model.inheritance.model.Account;

public class SavingsAccount extends Account {
    private double minBalance;

    public SavingsAccount(int accNo, String name, double balance, double minBalance) {
        super(accNo, name, balance);
        this.minBalance = minBalance;
    }

    public void debit(double amount) {
        if (balance - amount >= minBalance) {
            balance -= amount;
            System.out.println("Amount debited new balance: " + balance);
        } else {
            System.out.println("Debit failed.");
        }
    }
	public void credit(double amount) {
		balance += amount;
		System.out.println("Amount credited new balance: " + balance);
	}


    public void displayDetails() {
        super.displayDetails();
        System.out.println("Minimum Balance Required: " + minBalance);
    }
}