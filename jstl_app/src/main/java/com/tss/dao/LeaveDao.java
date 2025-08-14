package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBConnection;
import com.tss.exception.AppException;
import com.tss.model.LeaveBalance;
import com.tss.model.LeaveRequest;

public class LeaveDao {

	public void createRequest(LeaveRequest req) throws AppException {
		String sql = "INSERT INTO leave_request(user_id, leave_type, from_date, to_date, days, reason) "
				+ "VALUES (?,?,?,?,?,?)";
		try (Connection c = DBConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, req.getUserId());
			ps.setString(2, req.getLeaveType());
			ps.setDate(3, new java.sql.Date(req.getFromDate().getTime()));
			ps.setDate(4, new java.sql.Date(req.getToDate().getTime()));
			ps.setInt(5, req.getDays());
			ps.setString(6, req.getReason());
			ps.executeUpdate();

			try (ResultSet keys = ps.getGeneratedKeys()) {
				if (keys.next())
					req.setRequestId(keys.getInt(1));
			}
		} catch (Exception e) {
			throw new AppException("Error creating leave request", e);
		}
	}

	public List<LeaveRequest> getRequestsForUser(int userId) throws AppException {
		String sql = "SELECT * FROM leave_request WHERE user_id = ? ORDER BY applied_on DESC";
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				List<LeaveRequest> list = new ArrayList<>();
				while (rs.next()) {
					list.add(map(rs));
				}
				return list;
			}
		} catch (Exception e) {
			throw new AppException("Error getting user requests", e);
		}
	}

	public List<LeaveRequest> getAllPendingRequests() throws AppException {
		String sql = "SELECT lr.*, u.full_name " + "FROM leave_request lr " + "JOIN users u ON lr.user_id = u.user_id "
				+ "WHERE lr.status='PENDING' " + "ORDER BY lr.applied_on";
		try (Connection c = DBConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			List<LeaveRequest> list = new ArrayList<>();
			while (rs.next()) {
				LeaveRequest lr = map(rs);
				lr.setApplicantName(rs.getString("full_name"));
				list.add(lr);
			}
			return list;
		} catch (Exception e) {
			throw new AppException("Error getting pending requests", e);
		}
	}

	public LeaveRequest getById(int requestId) throws AppException {
		String sql = "SELECT lr.*, u.full_name " + "FROM leave_request lr " + "JOIN users u ON lr.user_id = u.user_id "
				+ "WHERE lr.request_id=?";
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, requestId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					LeaveRequest lr = map(rs);
					lr.setApplicantName(rs.getString("full_name"));
					return lr;
				}
				return null;
			}
		} catch (Exception e) {
			throw new AppException("Error reading request", e);
		}
	}

	public void updateRequestStatus(int requestId, String newStatus, Integer processedBy, String adminComment)
			throws AppException {
		String sql = "UPDATE leave_request " + "SET status=?, processed_by=?, processed_on=NOW(), admin_comment=? "
				+ "WHERE request_id=?";
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setString(1, newStatus);
			if (processedBy != null) {
				ps.setInt(2, processedBy);
			} else {
				ps.setNull(2, Types.INTEGER);
			}
			ps.setString(3, adminComment);
			ps.setInt(4, requestId);
			int updated = ps.executeUpdate();
			if (updated == 0)
				throw new AppException("Request not found for update");
		} catch (Exception e) {
			throw new AppException("Error updating request", e);
		}
	}

	/**
	 * Sum of approved days for a user within a specific month and year (based on
	 * from_date).
	 */
	public int getApprovedLeaveDaysForMonth(int userId, int month, int year) throws AppException {
		String sql = "SELECT COALESCE(SUM(days),0) AS total_days " + "FROM leave_request "
				+ "WHERE user_id=? AND status='APPROVED' " + "AND MONTH(from_date)=? AND YEAR(from_date)=?";
		try (Connection c = DBConnection.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, userId);
			ps.setInt(2, month);
			ps.setInt(3, year);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next())
					return rs.getInt("total_days");
				return 0;
			}
		} catch (Exception e) {
			throw new AppException("Error checking monthly approved leaves", e);
		}
	}

	private LeaveRequest map(ResultSet rs) throws SQLException {
		LeaveRequest lr = new LeaveRequest();
		lr.setRequestId(rs.getInt("request_id"));
		lr.setUserId(rs.getInt("user_id"));
		lr.setLeaveType(rs.getString("leave_type"));
		lr.setFromDate(rs.getDate("from_date"));
		lr.setToDate(rs.getDate("to_date"));
		lr.setDays(rs.getInt("days"));
		lr.setReason(rs.getString("reason"));
		lr.setStatus(rs.getString("status"));
		lr.setAdminComment(rs.getString("admin_comment"));
		return lr;
	}

	public LeaveBalance fetchLeaveBalance(int userId) throws AppException {
		String sql = "SELECT * FROM leave_balance WHERE user_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					LeaveBalance balance = new LeaveBalance();
					balance.setUserId(rs.getInt("user_id"));
					balance.setAnnualLeft(rs.getInt("annual_left"));
					balance.setSickLeft(rs.getInt("sick_left"));
					return balance;
				}
			}
		} catch (SQLException e) {
			throw new AppException("Error fetching leave balance: " + e.getMessage());
		}
		return new LeaveBalance(); // default empty balance if none found
	}

	public List<LeaveRequest> fetchRequestsByUser(int userId) throws AppException {
		List<LeaveRequest> requests = new ArrayList<>();
		String sql = "SELECT * FROM leave_request WHERE user_id = ? ORDER BY from_date DESC";

		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					LeaveRequest lr = new LeaveRequest();
					lr.setRequestId(rs.getInt("request_id")); // ✅ Correct column
					lr.setUserId(rs.getInt("user_id"));
					lr.setLeaveType(rs.getString("leave_type"));
					lr.setFromDate(rs.getDate("from_date"));
					lr.setToDate(rs.getDate("to_date"));
					lr.setDays(rs.getInt("days"));
					lr.setReason(rs.getString("reason"));
					lr.setStatus(rs.getString("status"));
					lr.setAdminComment(rs.getString("admin_comment")); // if exists
					requests.add(lr);
				}
			}
		} catch (SQLException e) {
			throw new AppException("Error fetching leave requests: " + e.getMessage());
		}

		return requests;
	}

	// Fetch all requests for admin (past + present)
	public List<LeaveRequest> getAllRequests() throws AppException {
		String sql = "SELECT lr.*, u.full_name " + "FROM leave_request lr " + "JOIN users u ON lr.user_id = u.user_id "
				+ "ORDER BY lr.applied_on DESC";
		try (Connection c = DBConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			List<LeaveRequest> list = new ArrayList<>();
			while (rs.next()) {
				LeaveRequest lr = map(rs);
				lr.setApplicantName(rs.getString("full_name"));
				list.add(lr);
			}
			return list;
		} catch (Exception e) {
			throw new AppException("Error getting all requests", e);
		}
	}

	// Update a leave request (only for pending future requests)
	public boolean updateLeave(LeaveRequest leave) throws AppException {
		String sql = "UPDATE leave_request SET leave_type=?, from_date=?, to_date=?, days=?, reason=? "
				+ "WHERE request_id=? AND status='PENDING'";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, leave.getLeaveType());
			ps.setDate(2, new java.sql.Date(leave.getFromDate().getTime()));
			ps.setDate(3, new java.sql.Date(leave.getToDate().getTime()));
			ps.setInt(4, leave.getDays());
			ps.setString(5, leave.getReason());
			ps.setInt(6, leave.getRequestId());

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0; // true if update happened
		} catch (SQLException e) {
			throw new AppException("Error updating leave request: " + e.getMessage());
		}
	}

	public LeaveRequest findById(int id) {
		LeaveRequest leave = null;
		try (Connection conn = DBConnection.getConnection()) {
			String sql = "SELECT * FROM leave_requests WHERE request_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				leave = new LeaveRequest();
				leave.setRequestId(rs.getInt("request_id"));
				leave.setUserId(rs.getInt("user_id"));
				leave.setLeaveType(rs.getString("leave_type"));
				leave.setFromDate(rs.getDate("from_date"));
				leave.setToDate(rs.getDate("to_date"));
				leave.setDays(rs.getInt("days"));
				leave.setReason(rs.getString("reason"));
				leave.setStatus(rs.getString("status"));
				leave.setAppliedOn(rs.getTimestamp("applied_on"));
				leave.setProcessedBy(rs.getInt("processed_by"));
				leave.setProcessedOn(rs.getTimestamp("processed_on"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return leave;
	}

	public LeaveRequest getLeaveById(int id) throws AppException {
		String sql = "SELECT * FROM leave_request WHERE request_id = ?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					LeaveRequest leave = new LeaveRequest();
					leave.setRequestId(rs.getInt("request_id"));
					leave.setUserId(rs.getInt("user_id"));
					leave.setLeaveType(rs.getString("leave_type"));
					leave.setFromDate(rs.getDate("from_date")); // java.sql.Date
					leave.setToDate(rs.getDate("to_date")); // java.sql.Date
					leave.setDays(rs.getInt("days"));
					leave.setReason(rs.getString("reason"));
					leave.setStatus(rs.getString("status"));
					leave.setAppliedOn(rs.getTimestamp("applied_on"));
					leave.setProcessedBy(rs.getObject("processed_by") != null ? rs.getInt("processed_by") : null);
					leave.setProcessedOn(rs.getTimestamp("processed_on"));
					return leave;
				}
			}
		} catch (SQLException e) {
			throw new AppException("Error fetching leave by ID: " + e.getMessage(), e);
		}
		return null;
	}

	public List<LeaveRequest> getApprovedAndRejectedRequests() throws AppException {
	    String sql = "SELECT lr.*, u.username AS applicant_name " +
	                 "FROM leave_request lr " +
	                 "JOIN users u ON lr.user_id = u.user_id " +
	                 "WHERE lr.status IN ('APPROVED', 'REJECTED') " +
	                 "ORDER BY lr.applied_on DESC";
	    
	    List<LeaveRequest> list = new ArrayList<>();
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            LeaveRequest lr = new LeaveRequest();
	            lr.setRequestId(rs.getInt("request_id"));
	            lr.setUserId(rs.getInt("user_id"));
	            lr.setLeaveType(rs.getString("leave_type"));
	            lr.setFromDate(rs.getDate("from_date"));
	            lr.setToDate(rs.getDate("to_date"));
	            lr.setDays(rs.getInt("days"));
	            lr.setReason(rs.getString("reason"));
	            lr.setStatus(rs.getString("status"));
	            lr.setAppliedOn(rs.getTimestamp("applied_on"));
	            lr.setProcessedBy(rs.getObject("processed_by") != null ? rs.getInt("processed_by") : null);
	            lr.setProcessedOn(rs.getTimestamp("processed_on"));
	            lr.setAdminComment(rs.getString("admin_comment"));
	            lr.setApplicantName(rs.getString("applicant_name"));
	            list.add(lr);
	        }
	    } catch (SQLException e) {
	        throw new AppException("Error fetching approved/rejected leave requests", e);
	    }
	    return list;
	}


}
