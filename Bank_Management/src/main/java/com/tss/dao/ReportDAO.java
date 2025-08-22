// File: src/com/tss/dao/ReportDAO.java
package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.tss.database.DBConnection;

public class ReportDAO {

    // 1. Total Customers
    public int getTotalCustomers() throws SQLException {
        String sql = "SELECT COUNT(*) FROM customers WHERE role = 'customer'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }

    // 2. Total Deposits (sum of approved account balances)
    public double getTotalDeposits() throws SQLException {
        String sql = "SELECT COALESCE(SUM(balance), 0) FROM accounts WHERE status = 'approved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
            return 0.0;
        }
    }

    // 3. Approved Accounts Count
    public int getApprovedAccountsCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE status = 'approved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }

    // 4. Pending Approvals
    public int getPendingAccountsCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE status = 'pending'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }

    // 5. Monthly Deposit/Withdrawal Sum
    public Map<String, Double> getMonthlyTransactionSum(String type) throws SQLException {
        String sql = """
            SELECT 
                DATE_FORMAT(transaction_date, '%b') as month_name,
                SUM(amount) as total
            FROM transactions t
            JOIN accounts a ON t.acc_no = a.acc_no
            WHERE t.type = ? AND a.status = 'approved'
            GROUP BY month_name, MONTH(transaction_date)
            ORDER BY MONTH(transaction_date)
            """;

        Map<String, Double> data = new LinkedHashMap<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                data.put(rs.getString("month_name"), rs.getDouble("total"));
            }
        }
        return data;
    }

    // 6. Account Type Distribution (Count)
    public Map<String, Integer> getAccountTypeDistribution() throws SQLException {
        String sql = "SELECT account_type, COUNT(*) as count FROM accounts WHERE status = 'approved' GROUP BY account_type";
        Map<String, Integer> data = new HashMap<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                data.put(rs.getString("account_type"), rs.getInt("count"));
            }
        }
        return data;
    }

    // 7. Monthly Customer Growth
    public Map<String, Integer> getMonthlyCustomerGrowth() throws SQLException {
        String sql = """
            SELECT 
                DATE_FORMAT(created_date, '%b') as month_name,
                COUNT(*) as count
            FROM customers 
            WHERE role = 'customer'
            GROUP BY month_name, MONTH(created_date)
            ORDER BY MONTH(created_date)
            """;

        Map<String, Integer> data = new LinkedHashMap<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                data.put(rs.getString("month_name"), rs.getInt("count"));
            }
        }
        return data;
    }

    // 8. Balance by Account Type
    public Map<String, Double> getBalanceByAccountType() throws SQLException {
        String sql = "SELECT account_type, SUM(balance) as total FROM accounts WHERE status = 'approved' GROUP BY account_type";
        Map<String, Double> data = new HashMap<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                data.put(rs.getString("account_type"), rs.getDouble("total"));
            }
        }
        return data;
    }
}