package com.tss.dip.test;

import com.tss.dip.CreditCardD;
import com.tss.dip.IPayment;
import com.tss.dip.ShoppingCartDIP;

public class ShoppingDIPTest {
    public static void main(String[] args) {
        IPayment paymentMethod = new CreditCardD(); 
        ShoppingCartDIP cart = new ShoppingCartDIP(paymentMethod);
        cart.checkout(400);
    }
}
