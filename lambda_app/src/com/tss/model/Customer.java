package com.tss.model;

public class Customer {
	private String itemName;
	private double price, tax, shippingCharge;
	private double amount;
	
	public Customer(String itemName, double price, double tax) {
		super();
		this.itemName = itemName;
		this.price = price;
		this.tax = tax;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getShippingCharge() {
		return shippingCharge;
	}

	public void setShippingCharge(double shippingCharge) {
		this.shippingCharge = shippingCharge;
	}

	@Override
	public String toString() {
		return "Customer [itemName=" + itemName + ", price=" + price + ", tax=" + tax + ", shippingCharge="
				+ shippingCharge + ", amount=" + amount + "]";
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
