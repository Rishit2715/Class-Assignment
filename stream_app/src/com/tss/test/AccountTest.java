package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.tss.model.Account;

public class AccountTest {
	public static void main(String[] args) {
		List<Account> accounts = Arrays.asList(
				new Account(1, "rishit", 50000),
				new Account(2, "nishit", 75000),
				new Account(3, "krish", 60000), 
				new Account(4, "jay", 85000),
				new Account(5, "kirtan", 45000)
				);

		System.out.println("Account with minimum balance:");
		System.out.println(accounts.stream()
									.min(Comparator.comparing(Account::getSalary)));

		System.out.println("\nAccount with maximum balance:");
		System.out.println(accounts.stream()
									.max(Comparator.comparing(Account::getSalary)));

		System.out.println("\nNames with more than 6 characters:");
		accounts.stream().map(Account::getName)
						 .filter(name -> name.length() > 6)
						 .forEach(System.out::println);

		System.out.println("\nTotal balance of all accounts:");
		double total = accounts.stream()
							    .mapToDouble(Account::getSalary)
							    .sum();

		System.out.println(total);
	}
}
