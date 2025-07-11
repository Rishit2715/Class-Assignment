package com.tss.model;

import com.tss.model.Invoice;

public class InvoiceCalculator {

    public double calculateTax(Invoice invoice) {
        return invoice.getCost() * Invoice.TAX_PERCENT / 100;
    }

    public double calculateDiscount(Invoice invoice) {
        return invoice.getCost() * invoice.getDiscountPercent() / 100;
    }

    public double calculateFinalCost(Invoice invoice) {
        return invoice.getCost() + calculateTax(invoice) - calculateDiscount(invoice);
    }
}

