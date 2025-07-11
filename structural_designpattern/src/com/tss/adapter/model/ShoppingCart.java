package com.tss.adapter.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<IItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItemsToCart(List<IItem> newItems) {
        this.items.addAll(newItems);
    }

    public List<IItem> getCartItems() {
        return items;
    }

    public double getCartPrice() {
        double total = 0.0;
        for (IItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("Cart is empty!");
            return;
        }

        System.out.printf("%-15s %s\n", "Item Name", "Price");
        for (IItem item : items) {
            System.out.printf("%-15s %.2f\n", item.getName(), item.getPrice());
        }

        System.out.println("\nTotal: " + getCartPrice());
    }
}
