package com.tss.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private int id;
	private Date date;
	private List<LineItem> items;

	public Order(int id) {
		this.id = id;
		this.date = new Date();
		this.items = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public List<LineItem> getItems() {
		return items;
	}

	public void addItem(LineItem item) {
		items.add(item);
	}

	public double calculateOrderPrice() {
		double total = 0;
		for (LineItem item : items) {
			total += item.calculateLineItemCost();
		}
		return total;
	}
}

