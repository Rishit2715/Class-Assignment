package com.tss.test;

import com.tss.model.Invoice;
import java.util.Scanner;

public class InvoiceTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Invoice ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  

        System.out.print("Enter Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Price per item: ");
        double price = scanner.nextDouble();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        Invoice invoice = new Invoice(id, description, price, quantity);

        invoice.print();

        scanner.close();
    }
}
