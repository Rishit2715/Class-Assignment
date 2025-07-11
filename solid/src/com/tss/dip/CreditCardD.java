package com.tss.dip;

public class CreditCardD implements IPayment {
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using Credit Card.");
    }
}