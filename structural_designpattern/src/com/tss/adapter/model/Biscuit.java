package com.tss.adapter.model;

public class Biscuit implements IItem {
	
	private String name;
	private double price;

	public Biscuit(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public double getPrice() {
		// TODO Auto-generated method stub
		return price;
	}

}
