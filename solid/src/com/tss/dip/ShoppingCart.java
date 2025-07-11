package com.tss.dip;

public class ShoppingCart {
    CreditCardPayment payment = new CreditCardPayment();

    public void checkOut() {
        payment.payment(300);
    }
}
