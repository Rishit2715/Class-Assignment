package com.tss.model;

public class TaxCalculator {
	private DBLogger dbLogger;

    public TaxCalculator() {
        dbLogger = new DBLogger();
    }

    public int calculateTax(int amount, int rate) {
        int tax = 0;
        try {
            tax = amount / rate;
        } catch (Exception e) {
            new DBLogger().log("Divide by zero");
        }
        return tax;
    }
}