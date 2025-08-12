package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tss.model.Account;

public class AccountDao {

    public Account getAccountById(Connection conn, int id) throws SQLException {
        String query = "SELECT * FROM accounts WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Account(id, rs.getString("name"), rs.getDouble("balance"));
            }
        }
        return null;
    }

    public void updateBalance(Connection conn, int id, double newBalance) throws SQLException {
        String update = "UPDATE accounts SET balance = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(update)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }
}
