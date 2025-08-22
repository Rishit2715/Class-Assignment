package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.model.Beneficiary;

public class BeneficiaryDAO {

 
	// ✅ Check if account belongs to customer
    public boolean isOwnAccount(int custId, String accNo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE cust_id = ? AND acc_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custId);
            ps.setString(2, accNo);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    // ✅ Check if account exists and is approved
    public boolean isApprovedAccount(String accNo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM accounts WHERE acc_no = ? AND status = 'approved'";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, accNo);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    // ✅ Check if already added as beneficiary
    public boolean exists(String customerAccNo, String beneficiaryAccNo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM beneficiaries WHERE customer_acc_no = ? AND beneficiary_acc_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customerAccNo);
            ps.setString(2, beneficiaryAccNo);
            ResultSet rs = ps.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        }
    }

    // ✅ Get customer's own approved account
    public String getCustomerAccount(int custId) throws SQLException {
        String sql = "SELECT acc_no FROM accounts WHERE cust_id = ? AND status = 'approved' LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("acc_no");
            }
        }
        return null;
    }

    // ✅ Add beneficiary by params
    public boolean addBeneficiary(String customerAccNo, String beneficiaryAccNo, String nickname) throws SQLException {
        String sql = "INSERT INTO beneficiaries (customer_acc_no, beneficiary_acc_no, nickname) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customerAccNo);
            ps.setString(2, beneficiaryAccNo);
            ps.setString(3, nickname);
            return ps.executeUpdate() > 0;
        }
    }

    // ✅ Add beneficiary using Beneficiary object
    public boolean addBeneficiary(Beneficiary b) throws SQLException {
        String sql = "INSERT INTO beneficiaries (customer_acc_no, beneficiary_acc_no, nickname) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, b.getCustomerAccNo());
            ps.setString(2, b.getBeneficiaryAccNo());
            ps.setString(3, b.getNickname());
            return ps.executeUpdate() > 0;
        }
    }

    // ✅ Fetch beneficiaries by customer account number
    public List<Beneficiary> getBeneficiaries(String customerAccNo) throws SQLException {
        List<Beneficiary> list = new ArrayList<>();
        String sql = "SELECT id, beneficiary_acc_no, nickname, added_date FROM beneficiaries WHERE customer_acc_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, customerAccNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Beneficiary b = new Beneficiary();
                b.setId(rs.getInt("id"));
                b.setBeneficiaryAccNo(rs.getString("beneficiary_acc_no"));
                b.setNickname(rs.getString("nickname"));
                b.setAddedDate(rs.getString("added_date"));
                b.setCustomerAccNo(customerAccNo);
                list.add(b);
            }
        }
        return list;
    }

    // ✅ Fetch beneficiaries by customer ID
    public List<Beneficiary> getBeneficiariesByCustomerId(int custId) throws SQLException {
        List<Beneficiary> list = new ArrayList<>();
        String sql = """
            SELECT b.id, b.beneficiary_acc_no, b.nickname, b.added_date, b.customer_acc_no
            FROM beneficiaries b
            JOIN accounts a ON b.customer_acc_no = a.acc_no
            WHERE a.cust_id = ? AND a.status = 'approved'
        """;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, custId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Beneficiary b = new Beneficiary();
                b.setId(rs.getInt("id"));
                b.setBeneficiaryAccNo(rs.getString("beneficiary_acc_no"));
                b.setNickname(rs.getString("nickname"));
                b.setAddedDate(rs.getString("added_date"));
                b.setCustomerAccNo(rs.getString("customer_acc_no"));
                list.add(b);
            }
        }
        return list;
    }

    // ✅ Remove beneficiary
    public boolean removeBeneficiary(int id, String customerAccNo) throws SQLException {
        String sql = "DELETE FROM beneficiaries WHERE id = ? AND customer_acc_no = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, customerAccNo);
            return ps.executeUpdate() > 0;
        }
    }
}
