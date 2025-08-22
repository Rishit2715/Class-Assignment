package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.Transaction;

public class TransactionDAO {
	/**
	 * Inserts a transaction record into the database
	 * For transfers:
	 * - Outgoing: related_acc_no = receiver
	 * - Incoming: related_acc_no = sender
	 */
	// In TransactionDAO.java
	public void insertTransaction(String accNo, String type, double amount,
	                              double balanceAfter, String description, 
	                              String relatedAccNo, Connection conn) throws SQLException {
	    
	    String sql = """
	        INSERT INTO transactions 
	        (acc_no, type, amount, balance_after, description, related_acc_no) 
	        VALUES (?, ?, ?, ?, ?, ?)
	        """;

	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
	        ps.setString(1, accNo);
	        ps.setString(2, type);
	        ps.setDouble(3, amount);
	        ps.setDouble(4, balanceAfter);
	        ps.setString(5, description);
	        ps.setString(6, relatedAccNo); // Can be null for non-transfer transactions

	        ps.executeUpdate();
	    }
	}
    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        
        // 🔴 Removed 't.id' because it doesn't exist
        String sql = """
            SELECT 
                t.acc_no, t.type, t.amount, t.balance_after, 
                t.description, t.transaction_date,
                c.name as customer_name
            FROM transactions t
            JOIN accounts a ON t.acc_no = a.acc_no
            JOIN customers c ON a.cust_id = c.id
            ORDER BY t.transaction_date DESC
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Transaction tx = new Transaction();
                // ❌ tx.setId(rs.getInt("id"));  // No 'id' column
                tx.setAccNo(rs.getString("acc_no"));
                tx.setType(rs.getString("type"));
                tx.setAmount(rs.getDouble("amount"));
                tx.setBalanceAfter(rs.getDouble("balance_after"));
                tx.setDescription(rs.getString("description"));
                tx.setTransactionDate(rs.getTimestamp("transaction_date"));
                tx.setCustomerName(rs.getString("customer_name"));
                list.add(tx);
            }
        }
        return list;
    }
    
    
 // In TransactionDAO.java
    public List<Transaction> getTransactionsByCustomer(int custId) throws SQLException {
        List<Transaction> list = new java.util.ArrayList<>();
        String sql = """
            SELECT t.*, a.acc_no
            FROM transactions t
            JOIN accounts a ON t.acc_no = a.acc_no
            WHERE a.cust_id = ?
            ORDER BY t.transaction_date DESC
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, custId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction tx = new Transaction();
                tx.setTransId(rs.getInt("trans_id"));
                tx.setAccNo(rs.getString("acc_no"));
                tx.setType(rs.getString("type"));
                tx.setAmount(rs.getDouble("amount"));
                tx.setBalanceAfter(rs.getDouble("balance_after"));
                tx.setDescription(rs.getString("description"));
                tx.setTransactionDate(rs.getTimestamp("transaction_date"));
                tx.setRelatedAccNo(rs.getString("related_acc_no"));
                list.add(tx);
            }
        }
        return list;
    }
}