package com.tss.model;

import java.util.Date;

public class Leave {
    private int leaveId;
    private int empId;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status; // Pending, Approved, Rejected
    private Date appliedOn;

    public int getLeaveId() {
        return leaveId;
    }
    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public int getEmpId() {
        return empId;
    }
    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
}
