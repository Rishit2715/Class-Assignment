package com.tss.model;

import com.tss.model.Invoice;

public class InvoicePrinter {

    private InvoiceCalculator calculator;

    public InvoicePrinter(InvoiceCalculator calculator) {
        this.calculator = calculator;
    }

    public void print(Invoice invoice) {
        System.out.printf("%-5s %-20s %-10s %-10s %-20s %-15s%n", 
            "ID", "Description", "Amount", "Tax(%)", "Discount(%)", "Final Amount");

        double finalCost = calculator.calculateFinalCost(invoice);

        System.out.printf("%-5d %-20s %-10.2f %-10.2f %-20.2f %-15.2f%n", 
            invoice.getId(), 
            invoice.getDescription(), 
            invoice.getCost(), 
            Invoice.TAX_PERCENT, 
            invoice.getDiscountPercent(), 
            finalCost);
    }
}
