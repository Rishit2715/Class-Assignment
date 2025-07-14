package com.tss.observer.model;

import java.util.ArrayList;
import java.util.List;

import com.tss.observer.exception.InsufficientBalanceException;

public class Account {
	private int accNo;
	private String name;
	private double balance;
	private double minBalance;

	private List<INotifier> notifiers;

	public Account(int accNo, String name, double balance, double minBalance) {
		this.accNo = accNo;
		this.name = name;
		this.balance = balance;
		this.minBalance = minBalance;
		this.notifiers = new ArrayList<>();
	}

	public boolean removeNotifier(String notifierTypeName) {
	    for (int i = 0; i < notifiers.size(); i++) {
	        INotifier notifier = notifiers.get(i);
	        String className = notifier.getClass().getSimpleName().toLowerCase();

	        if (className.contains(notifierTypeName.toLowerCase())) {
	            notifiers.remove(i);
	            return true; 
	        }
	    }
	    return false;
	}



	public void registerNotifier(INotifier notifier) {
		this.notifiers.add(notifier);
	}

	private void notifyAllObservers() {
		for (INotifier notifier : notifiers) {
			notifier.sendNotification(this);
		}
	}

	public void credit(double amount) {
		balance += amount;
		System.out.println("Credited " + amount);
		notifyAllObservers();
	}

	public void debit(double amount) {
		if ((balance - amount) < minBalance) {
			throw new InsufficientBalanceException(balance);
		}
		balance -= amount;
		System.out.println("Debited " + amount);
		notifyAllObservers();
	}

	public void displayDetails() {
		System.out.println("Account No : " + accNo);
		System.out.println("Name       : " + name);
		System.out.println("Balance    : " + balance);
		System.out.println("Min Balance: " + minBalance);
	}

	public int getAccNo() {
		return accNo;
	}

	public String getName() {
		return name;
	}

	public double getBalance() {
		return balance;
	}
}
