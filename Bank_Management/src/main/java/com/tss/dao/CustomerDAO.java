package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tss.database.DBConnection;
import com.tss.model.Customer;
//File: com.tss.dao.CustomerDAO
public class CustomerDAO {

 // Check if username or email exists
 public boolean isUsernameOrEmailExists(String username, String email) {
     String sql = "SELECT id FROM customers WHERE username = ? OR email = ?";
     try (Connection conn = DBConnection.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, username);
         ps.setString(2, email);
         ResultSet rs = ps.executeQuery();
         return rs.next();
     } catch (SQLException e) {
         e.printStackTrace();
         return false;
     }
 }

 // Register customer
//In CustomerDAO.java
public boolean registerCustomer(Customer customer) {
  String sql = "INSERT INTO customers (username, password_hash, name, email, phone, address, role, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
  try (Connection conn = DBConnection.getConnection();
       PreparedStatement ps = conn.prepareStatement(sql)) {
      
      ps.setString(1, customer.getUsername());
      ps.setString(2, customer.getPasswordHash());
      ps.setString(3, customer.getName());
      ps.setString(4, customer.getEmail());
      ps.setString(5, customer.getPhone());
      ps.setString(6, customer.getAddress());
      ps.setString(7, customer.getRole());
      ps.setString(8, customer.getStatus());  // ← Critical!

      return ps.executeUpdate() > 0;
  } catch (SQLException e) {
      e.printStackTrace();
      return false;
  }
}

 // Find by username (for login)
 public Customer findByUsername(String username) {
	 String sql = "SELECT id, username, password_hash, name, email, phone, address, role, status FROM customers WHERE username = ?";
     try (Connection conn = DBConnection.getConnection();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, username);
         ResultSet rs = ps.executeQuery();
         if (rs.next()) {
             Customer customer = new Customer();
             customer.setId(rs.getInt("id"));
             customer.setUsername(rs.getString("username"));
             customer.setPasswordHash(rs.getString("password_hash"));
             customer.setName(rs.getString("name"));
             customer.setEmail(rs.getString("email"));
             customer.setRole(rs.getString("role"));
             customer.setStatus(rs.getString("status"));
             return customer;
         }
     } catch (SQLException e) {
         e.printStackTrace();
     }
     return null;
 }
}