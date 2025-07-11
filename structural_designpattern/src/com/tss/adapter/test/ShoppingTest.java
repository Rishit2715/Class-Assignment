package com.tss.adapter.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tss.adapter.model.*;

public class ShoppingTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("1. Add Items to Cart");
            System.out.println("2. Display Cart");
            System.out.println("3. Get Cart Items");
            System.out.println("4. Get Total Price");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    List<IItem> itemList = new ArrayList<>();
                    
                    System.out.println("\nChoose items to add:");
                    System.out.println("a. Biscuit");
                    System.out.println("b. Chocolate");
                    System.out.println("c. Hat");
                    System.out.print("Enter your choice(s): ");

                    String input = scanner.nextLine().toLowerCase();

                    if (input.contains("a")) {
                        itemList.add(new Biscuit("Biscuit", 10));
                    }
                    if (input.contains("b")) {
                        itemList.add(new Chocolate("Chocolate", 20));
                    }
                    if (input.contains("c")) {
                        itemList.add(new HatAdapter());
                    }

                    if (!itemList.isEmpty()) {
                        cart.addItemsToCart(itemList);
                        System.out.println("Items added to cart.");
                    } else {
                        System.out.println("No valid items selected.");
                    }
                    break;

                case 2:
                    cart.displayCart();
                    break;

                case 3:
                    List<IItem> items = cart.getCartItems();
                    if (items.isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        System.out.println("Items in cart:");
                        for (IItem item : items) {
                            System.out.println("- " + item.getName());
                        }
                        System.out.println("Total items: " + items.size());
                    }
                    break;

                case 4:
                    System.out.println("Total Cart Price: " + cart.getCartPrice());
                    break;

                case 5:
                    System.out.println("Thank you for shopping!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
