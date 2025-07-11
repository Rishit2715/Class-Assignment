package com.tss.model;

import com.tss.exception.MinimumBalanceException;
import com.tss.exception.NegativeAmountException;

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
        	throw new MinimumBalanceException(amount);
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
        System.out.println("Minimum Balance Required: " + minBalance);
    }
}