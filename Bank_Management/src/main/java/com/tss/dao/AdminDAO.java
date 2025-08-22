package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.Customer;
import com.tss.model.CustomerWithAccountStatus;

public class AdminDAO {

    public int getTotalCustomers() throws SQLException {
        String sql = "SELECT COUNT(*) FROM customers";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }

    public int getPendingAccountsCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE status = 'pending'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }

    public int getApprovedAccountsCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE status = 'approved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }

    public double getTotalDeposits() throws SQLException {
        String sql = "SELECT COALESCE(SUM(balance), 0) FROM accounts WHERE status = 'approved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getDouble(1);
            return 0.0;
        }
    }

    public int getActiveFDsCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM fixed_deposits WHERE status = 'active'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getInt(1);
            return 0;
        }
    }
    
 // In AdminDAO.java

 // Get all customers with pending accounts (not approved yet)
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> list = new java.util.ArrayList<>();
        String sql = """
            SELECT id, username, name, email, phone, address, role
            FROM customers
            ORDER BY id
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setUsername(rs.getString("username"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                c.setRole(rs.getString("role"));
                list.add(c);
            }
        }
        return list;
    }
 
//Approve all pending accounts of customer
public void approveCustomerAccounts(int customerId) throws SQLException {
  String sql = "UPDATE accounts SET status = 'approved' WHERE cust_id = ? AND status = 'pending'";
  try (Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, customerId);
      ps.executeUpdate();
  }
}

//Reject all pending accounts of customer
public void rejectCustomerAccounts(int customerId) throws SQLException {
  String sql = "UPDATE accounts SET status = 'rejected' WHERE cust_id = ? AND status = 'pending'";
  try (Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(1, customerId);
      ps.executeUpdate();
  }
}
public List<CustomerWithAccountStatus> getAllCustomersWithStatus() throws SQLException {
    List<CustomerWithAccountStatus> list = new java.util.ArrayList<>();
    
    // Query: One row per account, exclude admin
    String sql = """
        SELECT 
            c.id, c.username, c.name, c.email, c.phone, c.address, c.role,
            a.acc_no, a.account_type, a.status as account_status, a.balance
        FROM customers c
        INNER JOIN accounts a ON c.id = a.cust_id
        WHERE c.role = 'customer'  -- Exclude admin
        ORDER BY c.id, a.acc_no
        """;

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            CustomerWithAccountStatus c = new CustomerWithAccountStatus();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setUsername(rs.getString("username"));
            c.setEmail(rs.getString("email"));
            c.setPhone(rs.getString("phone"));
            c.setAddress(rs.getString("address"));
            c.setRole(rs.getString("role"));

            // Account-specific fields
            c.setAccountNumber(rs.getString("acc_no"));
            c.setAccountType(rs.getString("account_type"));
            c.setBalance(rs.getDouble("balance"));

            // Status is the account status
            String accountStatus = rs.getString("account_status");
            c.setStatus(accountStatus);  // "approved", "pending", "rejected"

            list.add(c);
        }
    }
    return list;
}


public void approveAccount(String accNo) throws SQLException {
    String sql = "UPDATE accounts SET status = 'approved' WHERE acc_no = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, accNo);
        ps.executeUpdate();
    }
}


//In AdminDAO.java
public void rejectAccount(String accNo) throws SQLException {
 String sql = "UPDATE accounts SET status = 'rejected' WHERE acc_no = ?";
 try (Connection conn = DBConnection.getConnection();
      PreparedStatement ps = conn.prepareStatement(sql)) {
     ps.setString(1, accNo);
     ps.executeUpdate();
 }
}



//Approve all pending accounts of a customer
public void approveAllAccountsOfCustomer(int customerId) throws SQLException {
 String sql = "UPDATE accounts SET status = 'approved' WHERE cust_id = ? AND status = 'pending'";
 try (Connection conn = DBConnection.getConnection();
      PreparedStatement ps = conn.prepareStatement(sql)) {
     ps.setInt(1, customerId);
     ps.executeUpdate();
 }
}

//Delete customer and all related data
public void deleteCustomerAndAllData(int customerId) throws SQLException {
 Connection conn = null;
 try {
     conn = DBConnection.getConnection();
     conn.setAutoCommit(false);

     // Delete transactions
     try (PreparedStatement ps1 = conn.prepareStatement("DELETE FROM transactions WHERE acc_no IN (SELECT acc_no FROM accounts WHERE cust_id = ?)")) {
         ps1.setInt(1, customerId);
         ps1.executeUpdate();
     }

     // Delete accounts
     try (PreparedStatement ps2 = conn.prepareStatement("DELETE FROM accounts WHERE cust_id = ?")) {
         ps2.setInt(1, customerId);
         ps2.executeUpdate();
     }

     // Delete customer
     try (PreparedStatement ps3 = conn.prepareStatement("DELETE FROM customers WHERE id = ?")) {
         ps3.setInt(1, customerId);
         ps3.executeUpdate();
     }

     conn.commit();
 } catch (SQLException e) {
     if (conn != null) {
         conn.rollback();
     }
     throw e;
 } finally {
     if (conn != null) {
         conn.setAutoCommit(true);
         conn.close();
     }
 }
}


//File: com.tss.dao.AdminDAO.java

/**
* Activates a customer by setting their status to 'active'
* Called when admin approves a customer registration
* 
* @param customerId the ID of the customer to activate
* @throws SQLException if database operation fails
*/
public void activateCustomer(int customerId) throws SQLException {
 String sql = "UPDATE customers SET status = 'active' WHERE id = ?";
 
 try (Connection conn = DBConnection.getConnection();
      PreparedStatement ps = conn.prepareStatement(sql)) {
     
     // Set parameters
     ps.setInt(1, customerId);
     
     // Execute update
     int rows = ps.executeUpdate();
     
     if (rows == 0) {
         throw new SQLException("No customer found with ID: " + customerId);
     }
     
     System.out.println("✅ Customer ID " + customerId + " activated (status = 'active')");
 }
}
}