package com.tss.dip;

public class UPIPayment implements IPayment {
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using UPI.");
    }
}