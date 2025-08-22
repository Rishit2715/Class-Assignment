// File: com.tss.model.CustomerWithAccountStatus.java
package com.tss.model;

public class CustomerWithAccountStatus extends Customer {
    private String status;           // account status: pending, approved, etc.
    private String accountNumber;    // acc_no
    private String accountType;      // Savings, Current
    private double balance;          // account balance

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}