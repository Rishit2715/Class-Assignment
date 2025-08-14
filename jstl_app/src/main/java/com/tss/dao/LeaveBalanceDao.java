package com.tss.dao;

import com.tss.database.DBConnection;
import com.tss.exception.AppException;
import com.tss.model.LeaveBalance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LeaveBalanceDao {

    public LeaveBalance getBalance(int userId) throws AppException {
        String sql = "SELECT user_id, annual_left, sick_left FROM leave_balance WHERE user_id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    LeaveBalance lb = new LeaveBalance();
                    lb.setUserId(rs.getInt("user_id"));
                    lb.setAnnualLeft(rs.getInt("annual_left"));
                    lb.setSickLeft(rs.getInt("sick_left"));
                    return lb;
                }
                return null;
            }
        } catch (Exception e) {
            throw new AppException("Error reading leave balance", e);
        }
    }

    public void updateBalance(int userId, int newAnnual, int newSick) throws AppException {
        String sql = "UPDATE leave_balance SET annual_left=?, sick_left=? WHERE user_id=?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, newAnnual);
            ps.setInt(2, newSick);
            ps.setInt(3, userId);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new AppException("Leave balance row not found for user: " + userId);
            }
        } catch (Exception e) {
            throw new AppException("Error updating leave balance", e);
        }
    }
}
