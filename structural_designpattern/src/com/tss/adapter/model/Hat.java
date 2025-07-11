package com.tss.adapter.model;

public class Hat {
	private String shortName, longName;
	double discount, price;
	public String get;
	public Hat(String shortName, String longName, double discount, double price) {
		super();
		this.shortName = shortName;
		this.longName = longName;
		this.discount = discount;
		this.price = price;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

}
