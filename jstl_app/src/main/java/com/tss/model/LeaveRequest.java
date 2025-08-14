package com.tss.model;

import java.util.Date;

public class LeaveRequest {
    private int requestId;
    private int userId;
    private String leaveType; // ANNUAL or SICK
    private Date fromDate;
    private Date toDate;
    private int days;
    private String reason;
    private String status; // PENDING, APPROVED, REJECTED

    private Date appliedOn;       // Timestamp when leave applied
    private Integer processedBy;  // Admin user ID
    private Date processedOn;     // Timestamp when processed

    // Optional convenience field (not in DB, but for display)
    private String applicantName;
    private String adminComment;
    
    public int getLeaveId() {
        return requestId;
    }

    public void setLeaveId(int leaveId) {
        this.requestId = leaveId;
    }

    public String getAdminComment() {
        return adminComment;
    }

    public void setAdminComment(String adminComment) {
        this.adminComment = adminComment;
    }


    public int getRequestId() {
        return requestId;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLeaveType() {
        return leaveType;
    }
    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Date getFromDate() {
        return fromDate;
    }
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public int getDays() {
        return days;
    }
    public void setDays(int days) {
        this.days = days;
    }

    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAppliedOn() {
        return appliedOn;
    }
    public void setAppliedOn(Date appliedOn) {
        this.appliedOn = appliedOn;
    }

    public Integer getProcessedBy() {
        return processedBy;
    }
    public void setProcessedBy(Integer processedBy) {
        this.processedBy = processedBy;
    }

    public Date getProcessedOn() {
        return processedOn;
    }
    public void setProcessedOn(Date processedOn) {
        this.processedOn = processedOn;
    }

    public String getApplicantName() {
        return applicantName;
    }
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
}
