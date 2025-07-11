package com.tss.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Consumer;

import com.tss.model.Customer;
	
public class CustomerTest {
	public static void main(String[] args) {

		Supplier<List<Customer>> customerSupplier = () -> {
			List<Customer> customers = new ArrayList<>();
			customers.add(new Customer("Rishit", 30, 50000, 720));
			customers.add(new Customer("Krish", 25, 60000, 680));
			customers.add(new Customer("Kirtan", 40, 7500, 800));

			return customers;
		};

		List<Customer> customers = customerSupplier.get();

		Predicate<Customer> customerEligibility = (c) -> {
			if (c.getAge() >= 21 && c.getIncome() >= 25000 && c.getCredit_score() >= 650) {
				return true;
			}
			return false;
		};

		Function<Customer, Double> calculateLoan = (c) -> {
			return c.getIncome() * (c.getCredit_score() / 1000.0);
		};

		Consumer<Customer> approvalMessage = (c) -> {
			double loanAmount = calculateLoan.apply(c);
			System.out.println("Loan Approved for " + c.getName());
			System.out.println("Eligible Amount: " + loanAmount);
		};

		for (Customer c : customers) {	
			if (customerEligibility.test(c)) {
				approvalMessage.accept(c);
			}
		}
	}
}
