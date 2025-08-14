package com.tss.service;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.tss.dao.LeaveBalanceDao;
import com.tss.dao.LeaveDao;
import com.tss.exception.AppException;
import com.tss.model.LeaveBalance;
import com.tss.model.LeaveRequest;

public class LeaveService {
	private final LeaveDao leaveDao = new LeaveDao();
	private final LeaveBalanceDao balanceDao = new LeaveBalanceDao();

	public LeaveBalance getBalance(int userId) throws AppException {
		LeaveBalance lb = balanceDao.getBalance(userId);
		if (lb == null) {
			throw new AppException("Leave balance not found for user: " + userId);
		}
		return lb;
	}

	public void applyLeave(LeaveRequest req) throws AppException {
		validateLeaveRequest(req, true);
		leaveDao.createRequest(req);
	}

	public void updateLeave(LeaveRequest leave) throws AppException {
		LeaveRequest existing = leaveDao.getById(leave.getRequestId());
		if (existing == null) {
			throw new AppException("Leave request not found");
		}
		if (!"PENDING".equalsIgnoreCase(existing.getStatus())) {
			throw new AppException("Only pending requests can be updated");
		}
		if (existing.getFromDate().before(new java.util.Date())) {
			throw new AppException("Cannot edit past leave requests");
		}
		leave.setUserId(existing.getUserId());
		leave.setRequestId(existing.getRequestId());
		validateLeaveRequest(leave, false);
		leaveDao.updateLeave(leave);
	}

	private void validateLeaveRequest(LeaveRequest req, boolean isNew) throws AppException {
		if (req == null)
			throw new AppException("Invalid request");
		if (req.getFromDate() == null || req.getToDate() == null) {
			throw new AppException("From date and To date are required");
		}
		if (req.getFromDate().after(req.getToDate())) {
			throw new AppException("'From' date cannot be after 'To' date");
		}

		int computedDays = computeInclusiveDays(req.getFromDate().getTime(), req.getToDate().getTime());
		if (computedDays <= 0) {
			throw new AppException("Invalid number of days");
		}
		req.setDays(computedDays);

		LeaveBalance lb = getBalance(req.getUserId());
		if ("ANNUAL".equalsIgnoreCase(req.getLeaveType())) {
			if (lb.getAnnualLeft() < computedDays) {
				throw new AppException("Insufficient annual leave balance");
			}
		} else if ("SICK".equalsIgnoreCase(req.getLeaveType())) {
			if (lb.getSickLeft() < computedDays) {
				throw new AppException("Insufficient sick leave balance");
			}
		} else {
			throw new AppException("Invalid leave type");
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(req.getFromDate());
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		int approvedDaysThisMonth = leaveDao.getApprovedLeaveDaysForMonth(req.getUserId(), month, year);

		if (isNew) {
			if (approvedDaysThisMonth + computedDays > 3) {
				throw new AppException("Monthly cap exceeded: already approved " + approvedDaysThisMonth + " day(s) in "
						+ month + "/" + year + ". Max 3 days per month.");
			}
		} else {
			LeaveRequest existing = leaveDao.getById(req.getRequestId());
			int existingDays = existing != null ? existing.getDays() : 0;
			if (approvedDaysThisMonth  + computedDays > 3) {
				throw new AppException("Monthly cap exceeded after update: already approved " + approvedDaysThisMonth
						+ " day(s) in " + month + "/" + year + ". Max 3 days per month.");
			}
		}
	}

	public List<LeaveRequest> getUserRequests(int userId) throws AppException {
		return leaveDao.getRequestsForUser(userId);
	}

	public List<LeaveRequest> getPendingRequests() throws AppException {
		return leaveDao.getAllPendingRequests();
	}

	public LeaveRequest getRequest(int id) throws AppException {
		return leaveDao.getById(id);
	}

	public void processRequest(int requestId, int adminId, boolean approve, String comment) throws AppException {
		LeaveRequest req = leaveDao.getById(requestId);
		if (req == null)
			throw new AppException("Request not found");
		if (!"PENDING".equalsIgnoreCase(req.getStatus()))
			throw new AppException("Request already processed");

		if (!approve) {
			leaveDao.updateRequestStatus(requestId, "REJECTED", adminId, comment);
			return;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(req.getFromDate());
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);

		int approvedDaysThisMonth = leaveDao.getApprovedLeaveDaysForMonth(req.getUserId(), month, year);
		if (approvedDaysThisMonth + req.getDays() > 3) {
			throw new AppException("Cannot approve: employee already has " + approvedDaysThisMonth
					+ " approved day(s) in " + month + "/" + year + ". Max 3 per month.");
		}

		LeaveBalance lb = getBalance(req.getUserId());
		if ("ANNUAL".equalsIgnoreCase(req.getLeaveType())) {
			if (lb.getAnnualLeft() < req.getDays()) {
				throw new AppException("Insufficient annual leave balance at approval time");
			}
			lb.setAnnualLeft(lb.getAnnualLeft() - req.getDays());
		} else if ("SICK".equalsIgnoreCase(req.getLeaveType())) {
			if (lb.getSickLeft() < req.getDays()) {
				throw new AppException("Insufficient sick leave balance at approval time");
			}
			lb.setSickLeft(lb.getSickLeft() - req.getDays());
		} else {
			throw new AppException("Invalid leave type");
		}
		balanceDao.updateBalance(lb.getUserId(), lb.getAnnualLeft(), lb.getSickLeft());

		leaveDao.updateRequestStatus(requestId, "APPROVED", adminId, comment);
	}

	private int computeInclusiveDays(long fromMillis, long toMillis) {
		long diff = toMillis - fromMillis;
		return (int) TimeUnit.MILLISECONDS.toDays(diff) + 1;
	}

	public LeaveBalance getLeaveBalance(int userId) throws AppException {
		return leaveDao.fetchLeaveBalance(userId);
	}

	public List<LeaveRequest> getRequestsByUser(int userId) throws AppException {
		return leaveDao.fetchRequestsByUser(userId);
	}

	public List<LeaveRequest> getAllRequests() throws AppException {
		return leaveDao.getAllRequests();
	}

	public LeaveRequest getLeaveById(int id) throws AppException {
		return leaveDao.getLeaveById(id);
	}

	public List<LeaveRequest> getPastRequests() throws AppException {
		return leaveDao.getApprovedAndRejectedRequests();
	}

}
