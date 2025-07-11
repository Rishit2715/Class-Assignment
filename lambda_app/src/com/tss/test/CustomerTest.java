package com.tss.test;

import java.util.function.Function;
import java.util.function.Supplier;

import com.tss.model.Customer;

public class CustomerTest {

	public static void main(String[] args) {
		Customer customer = new Customer("Wafer", 100, 18);

		Supplier<Double> shippingSupplier = () -> {
			return 50.0;
		};

		double shippingCharge = shippingSupplier.get();
		customer.setShippingCharge(shippingCharge);

		Function<Customer, Double> billCalculator = (cust) -> {
			double taxAmount = cust.getPrice() * cust.getTax() / 100;
			return cust.getPrice() + taxAmount + cust.getShippingCharge();
		};

		double finalAmount = billCalculator.apply(customer);
		customer.setAmount(finalAmount);

		System.out.println(customer);
	}
}
