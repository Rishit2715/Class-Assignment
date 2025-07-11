package com.tss.dip;

public class ShoppingCartDIP {
    private IPayment payment;

    public ShoppingCartDIP(IPayment payment) {
        this.payment = payment;
    }

    public void checkout(int amount) {
        payment.pay(amount);
    }
}