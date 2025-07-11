package com.tss.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private int id;
	private String name;
	private List<Order> orders;

	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
		this.orders = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void addOrder(Order order) {
		orders.add(order);
	}
}
