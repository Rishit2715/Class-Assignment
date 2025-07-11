package com.tss.model;

public class Product {

	private int id;
	private String name;
	private double price;
	private double discountPercent;

	public Product(int id, String name, double price, double discountPercent) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.discountPercent = discountPercent;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public double getDiscountPercent() {
		return discountPercent;
	}

	public double calculateDiscountedPrice() {
		return price - (price * discountPercent / 100);
	}

	@Override
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", Price: " + price + ", Discount: " + discountPercent + "%";
	}
}
