package com.tss.model;

public class Account {
    private String accNo;        // Changed from long to String
    private int custId;
    private String accountType;
    private double balance;
    private String status;

    // Constructors
    public Account() {}

    public Account(int custId, String accountType, double balance, String status) {
        this.custId = custId;
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
    }

    // Getters and Setters
    public String getAccNo() { return accNo; }
    public void setAccNo(String accNo) { this.accNo = accNo; }

    public int getCustId() { return custId; }
    public void setCustId(int custId) { this.custId = custId; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}