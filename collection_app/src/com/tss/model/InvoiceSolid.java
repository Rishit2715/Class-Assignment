package com.tss.model;

public class InvoiceSolid {
    private int id;
    private String description;
    private double cost;
    private double discountPercent;

    public static final double TAX_PERCENT = 10.0;

    public InvoiceSolid(int id, String description, double cost, double discountPercent) {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.discountPercent = discountPercent;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }
}
