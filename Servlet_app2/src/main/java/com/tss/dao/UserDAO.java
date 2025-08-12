package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.tss.database.DBConnection;

public class UserDAO {

    public boolean checkUser(String username, String password, String role) {
        boolean isValid = false;

        String sql = "SELECT * FROM user WHERE userName = ? AND password = ? AND role = ?";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    isValid = true;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error while checking user: " + e.getMessage());
            e.printStackTrace();
        }

        return isValid;
    }
}
