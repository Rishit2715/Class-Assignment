package com.tss.model;

public class FixedDepositSolid {
    private int accountNumber;
    private String name;
    private double principal;
    private int duration;
    private IFestival interest;

    public FixedDepositSolid(int accountNumber, String name, double principal, int duration, IFestival interest) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.principal = principal;
        this.duration = duration;
        this.interest = interest;
    }

    public double getPrincipal() { return principal; }
    public int getDuration() { return duration; }
    public IFestival getInterest() { return interest; }

    public String getName() { return name; }
}
