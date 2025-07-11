package com.tss.model;

public class Customer {
	private String name;
	private int age, income, credit_score;

	public Customer(String name, int age, int income, int credit_score) {
		super();
		this.name = name;
		this.age = age;
		this.income = income;
		this.credit_score = credit_score;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getIncome() {
		return income;
	}

	public int getCredit_score() {
		return credit_score;
	}

}
