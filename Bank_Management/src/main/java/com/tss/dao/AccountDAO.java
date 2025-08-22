package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tss.database.DBConnection;
import com.tss.model.Account;

public class AccountDAO {
	public Connection getConnection() throws SQLException {
	    return DBConnection.getConnection();
	}

    // Generate a unique 16-digit account number
    private String generateUniqueAccountNumber(Connection conn) throws SQLException {
        Random rand = new Random();
        String accNo;
        int attempts = 0;
        do {
            // Generate random 16-digit number (10^15 to 10^16 - 1)
            long number = rand.nextLong(1_000_000_000_0L, 10_000_000_000_0L);
            accNo = String.valueOf(number);

            attempts++;
            if (attempts > 100) {
                throw new SQLException("Unable to generate unique account number after 100 attempts");
            }
        } while (isAccountNumberExists(accNo, conn));

        return accNo;
    }

    // Check if account number already exists
    private boolean isAccountNumberExists(String accNo, Connection conn) throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE acc_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Count total accounts for a customer
    public int getAccountCount(int custId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE cust_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return 0;
    }

    // Check if account type already exists for customer
    public boolean hasAccountType(int custId, String accountType) throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE cust_id = ? AND account_type = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custId);
            ps.setString(2, accountType);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Create account request with 16-digit acc_no
    public boolean createAccountRequest(int custId, String accountType) {
        String sql = "INSERT INTO accounts (acc_no, cust_id, account_type, balance, status) VALUES (?, ?, ?, 0.00, 'pending')";
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Start transaction

            // Generate 16-digit account number
            String accNo = generateUniqueAccountNumber(conn);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, accNo);
                ps.setInt(2, custId);
                ps.setString(3, accountType);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    try {
                        logTransaction(accNo, 0.0, 0.0, "Account creation request", conn);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("WARNING: Failed to log transaction for acc_no " + accNo);
                    }
                }
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Log transaction (acc_no is String)
    private void logTransaction(String accNo, double amount, double balanceAfter, String desc, Connection conn) throws SQLException {
        String sql = "INSERT INTO transactions (acc_no, type, amount, balance_after, description) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accNo);
            ps.setString(2, "account_request");
            ps.setDouble(3, amount);
            ps.setDouble(4, balanceAfter);
            ps.setString(5, desc);
            ps.executeUpdate();
        }
    }

    // Get all pending accounts (for admin)
    public List<Account> getPendingAccounts() throws SQLException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT acc_no, cust_id, account_type, balance, status FROM accounts WHERE status = 'pending'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Account acc = new Account();
                acc.setAccNo(rs.getString("acc_no"));
                acc.setCustId(rs.getInt("cust_id"));
                acc.setAccountType(rs.getString("account_type"));
                acc.setBalance(rs.getDouble("balance"));
                acc.setStatus(rs.getString("status"));
                list.add(acc);
            }
        }
        return list;
    }
    
    
 // Get balance
    public double getBalance(String accNo, Connection conn) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE acc_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
            throw new SQLException("Account not found: " + accNo);
        }
    }

    // Update balance
    public void updateBalance(String accNo, double newBalance, Connection conn) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE acc_no = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setString(2, accNo);
            int rows = ps.executeUpdate();
            if (rows == 0) throw new SQLException("Failed to update balance for " + accNo);
        }
    }
    
 // File: src/com/tss/dao/AccountDAO.java
    public List<Account> getApprovedAccounts(int custId) throws SQLException {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT acc_no, cust_id, account_type, balance, status FROM accounts WHERE cust_id = ? AND status = 'approved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccNo(rs.getString("acc_no"));
                acc.setCustId(rs.getInt("cust_id"));
                acc.setAccountType(rs.getString("account_type"));
                acc.setBalance(rs.getDouble("balance"));
                acc.setStatus(rs.getString("status"));
                list.add(acc);
            }
        }
        return list;
    }
    
    /**
     * Validates if the account belongs to the customer and is approved
     */
    public boolean isAccountOfCustomer(String accNo, int custId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE acc_no = ? AND cust_id = ? AND status = 'approved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accNo);
            ps.setInt(2, custId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}