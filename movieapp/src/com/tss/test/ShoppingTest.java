package com.tss.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Customer;
import com.tss.model.LineItem;
import com.tss.model.Order;
import com.tss.model.Product;


public class ShoppingTest {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<Product> products = loadProducts();

        System.out.println("Welcome to the Shopping Application!");
        System.out.print("Enter Customer ID: ");
        int custId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Customer Name: ");
        String custName = scanner.nextLine();

        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Customer customer = new Customer(custId, custName);
        Order order = new Order(orderId);

        while (true) {
            System.out.println("\nAvailable Products:");
            for (Product p : products) {
                System.out.println(p);
            }

            System.out.print("Enter Product ID to add to order: ");
            int productId = scanner.nextInt();
            scanner.nextLine();

            Product selectedProduct = null;
            for (Product p : products) {
                if (p.getId() == productId) {
                    selectedProduct = p;
                    break;
                }
            }

            if (selectedProduct == null) {
                System.out.println("Invalid Product ID.");
                continue;
            }

            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            LineItem item = new LineItem(order.getItems().size() + 1, quantity, selectedProduct);
            order.addItem(item);

            System.out.print("Do you want to add another item? (y/n): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("n")) {
                break;
            }
        }

        customer.addOrder(order);
        printBill(customer, order);
    }

    private static List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Milk", 100, 10));
        products.add(new Product(2, "Laptop", 200, 20));
        products.add(new Product(3, "Book", 300, 15));
        return products;
    }

    private static void printBill(Customer customer, Order order) {
        System.out.println("\n*********** BILL ***********");
        System.out.println("Customer ID: " + customer.getId());
        System.out.println("Customer Name: " + customer.getName());
        System.out.println("Order ID: " + order.getId());
        System.out.println("Order Date: " + order.getDate());

        System.out.printf("%-10s %-12s %-15s %-5s %-10s %-10s %-12s %-10s\n", "LineItem", "Product ID", "Item", "Qty", "Unit Price", "Discount %", "Discounted Price", "Total Cost");

        int line = 1;
        for (LineItem item : order.getItems()) {
            Product p = item.getProduct();
            System.out.printf("%-10d %-12d %-15s %-5d %-10.0f %-10.0f %-12.0f %-10.0f\n",
                    line++, p.getId(), p.getName(), item.getQuantity(), p.getPrice(),
                    p.getDiscountPercent(), p.calculateDiscountedPrice(), item.calculateLineItemCost());
        }

        System.out.println("\nTotal Order Price: " + order.calculateOrderPrice());
        System.out.println("\n********* Thank You For Shopping with us! *********");
    }
}
