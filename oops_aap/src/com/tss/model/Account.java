package com.tss.model;

public class Account {

	private int accountId;
	private String accountNumber;
	private String name;
	private int balance;
	private AccountType accountType;

	public Account() {
		accountId = 0;
		accountNumber = "";
		name = "";
		balance = 0;
		accountType = AccountType.SAVINGS;
	}


	public Account(int accountId, String accountNumber, String name, int balance, AccountType accountType) {
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", name=" + name + ", balance="
				+ balance + ", accountType=" + accountType + "]";
	}


	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public int deposit(int deposit) {
	    balance += deposit;
	    return balance;
	}

	public int withdraw(int withdraw) {
	    if (balance - withdraw >= 500) {
	        balance -= withdraw;
	    } 
	    return balance;
	}
	
	public void display() {
	    System.out.println("account Id: " + accountId);
	    System.out.println("account Number: " + accountNumber);
	    System.out.println("Name: " + name);
	    System.out.println("Balance: " + balance);
	    System.out.println("Account Type: " + accountType);
	}


}
