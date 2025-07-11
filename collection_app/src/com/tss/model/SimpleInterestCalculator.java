package com.tss.model;

public class SimpleInterestCalculator {

    public double calculate(FixedDepositSolid fd) {
        double p = fd.getPrincipal();
        int n = fd.getDuration();
        double r = fd.getInterest().getInterestRate();
        return (p * n * r) / 100;
    }
}
