package com.tss.service;

import com.tss.dao.AccountDAO;
import com.tss.model.Account;

import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();

    public String requestAccount(int custId, String accountType) {
        try {
            // Rule 1: Max 2 accounts
            int count = accountDAO.getAccountCount(custId);
            if (count >= 2) {
                return "You cannot have more than 2 accounts.";
            }

            // Rule 2: No duplicate type
            if (accountDAO.hasAccountType(custId, accountType)) {
                return "You already have a " + accountType + " account.";
            }

            // Create request (status = pending)
            boolean success = accountDAO.createAccountRequest(custId, accountType);
            if (success) {
                return "success:Account request submitted. Awaiting admin approval.";
            } else {
                return "Failed to submit request. Try again.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Database error. Please try later.";
        }
    }

    // For admin
    public List<Account> getPendingAccounts() throws SQLException {
        return accountDAO.getPendingAccounts();
    }
}