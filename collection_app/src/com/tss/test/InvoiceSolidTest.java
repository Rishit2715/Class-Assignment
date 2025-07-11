package com.tss.test;

import java.util.Scanner;

import com.tss.model.Invoice;
import com.tss.model.InvoiceCalculator;
import com.tss.model.InvoicePrinter;

public class InvoiceSolidTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Invoice ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter Description: ");
		String description = scanner.nextLine();

		System.out.print("Enter Cost: ");
		double cost = scanner.nextDouble();

		System.out.print("Enter Discount Percent: ");
		double discountPercent = scanner.nextDouble();

		Invoice invoice = new Invoice(id, description, cost, discountPercent);

		InvoiceCalculator calculator = new InvoiceCalculator();
		InvoicePrinter printer = new InvoicePrinter(calculator);

		printer.print(invoice);

		scanner.close();
	}
}
