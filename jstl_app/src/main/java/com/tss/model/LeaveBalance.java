package com.tss.model;

public class LeaveBalance {
    private int userId;
    private int annualLeft;
    private int sickLeft;

    // getters/setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getAnnualLeft() { return annualLeft; }
    public void setAnnualLeft(int annualLeft) { this.annualLeft = annualLeft; }

    public int getSickLeft() { return sickLeft; }
    public void setSickLeft(int sickLeft) { this.sickLeft = sickLeft; }
}
