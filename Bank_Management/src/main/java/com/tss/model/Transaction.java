// File: com/tss/model/Transaction.java
package com.tss.model;

import java.sql.Timestamp;

public class Transaction {
    private int transId;           // Matches DB: trans_id
    private String accNo;          // acc_no
    private String type;           // deposit, withdraw, transfer_out, etc.
    private double amount;         // amount
    private double balanceAfter;   // balance_after
    private String description;    // description
    private Timestamp transactionDate; // transaction_date
    private String relatedAccNo;   // related_acc_no (for transfers)
    private String customerName;   // Not in DB — for display in reports

    // === Getters and Setters ===

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public void setBalanceAfter(double balanceAfter) {
        this.balanceAfter = balanceAfter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getRelatedAccNo() {
        return relatedAccNo;
    }

    public void setRelatedAccNo(String relatedAccNo) {
        this.relatedAccNo = relatedAccNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // === Helper Methods for UI ===

    /**
     * Returns user-friendly label for transaction type
     */
    public String getTypeLabel() {
        return switch (type) {
            case "deposit" -> "Deposit";
            case "withdraw" -> "Withdrawal";
            case "transfer_out" -> "Transfer Out";
            case "transfer_in" -> "Transfer In";
            case "fd_deposit" -> "FD Deposit";
            case "account_request" -> "Account Request";
            default -> Character.toUpperCase(type.charAt(0)) + type.substring(1).toLowerCase();
        };
    }

    /**
     * Returns Bootstrap badge class based on type
     */
    public String getTypeBadgeClass() {
        return switch (type) {
            case "deposit", "transfer_in" -> "bg-success";
            case "withdraw", "transfer_out" -> "bg-primary";
            case "fd_deposit" -> "bg-info";
            case "account_request" -> "bg-warning text-dark";
            default -> "bg-secondary";
        };
    }

    /**
     * Format amount with ₹ symbol
     */
    public String getFormattedAmount() {
        return "₹" + String.format("%.2f", amount);
    }

    /**
     * Format balance with ₹ symbol
     */
    public String getFormattedBalanceAfter() {
        return "₹" + String.format("%.2f", balanceAfter);
    }
}