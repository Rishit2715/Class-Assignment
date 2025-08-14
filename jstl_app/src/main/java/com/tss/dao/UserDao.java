package com.tss.dao;

import com.tss.database.DBConnection;
import com.tss.exception.AppException;
import com.tss.model.User;

import java.sql.*;

public class UserDao {

    public User findByUsernameAndPassword(String username, String password) throws AppException {
        String sql = "SELECT user_id, username, full_name, role FROM users WHERE username=? AND password=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password); // In production: store hashed password
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setFullName(rs.getString("full_name"));
                u.setRole(rs.getString("role"));
                return u;
            }
            return null;
        } catch (Exception e) {
            throw new AppException("Error authenticating user", e);
        }
    }

    public User findById(int userId) throws AppException {
        String sql = "SELECT user_id, username, full_name, role FROM users WHERE user_id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setFullName(rs.getString("full_name"));
                u.setRole(rs.getString("role"));
                return u;
            }
            return null;
        } catch (Exception e) {
            throw new AppException("Error finding user", e);
        }
    }

    // ✅ Check if username already exists
    public boolean usernameExists(String username) throws AppException {
        String sql = "SELECT user_id FROM users WHERE username=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            throw new AppException("Error checking username existence", e);
        }
    }

    public int registerEmployee(User user) throws AppException {
        int userId = -1;
        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            String sqlUser = "INSERT INTO users (username, password, full_name, role) VALUES (?,?,?,'EMPLOYEE')";
            try (PreparedStatement ps = con.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword()); // TODO: hash in production
                ps.setString(3, user.getFullName());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        userId = rs.getInt(1);
                    }
                }
            }


            String sqlBalance = "INSERT INTO leave_balance (user_id, annual_left, sick_left) VALUES (?, 12, 10)";
            try (PreparedStatement ps = con.prepareStatement(sqlBalance)) {
                ps.setInt(1, userId);
                ps.executeUpdate();
            }

            con.commit();
        } catch (Exception e) {
            throw new AppException("Error registering employee", e);
        }
        return userId;
    }
}
