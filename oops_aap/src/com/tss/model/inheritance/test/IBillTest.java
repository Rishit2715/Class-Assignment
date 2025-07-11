package com.tss.model.inheritance.test;

import java.util.Scanner;
import com.tss.model.inheritance.model.*;

public class IBillTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Choose a payment method:");
		System.out.println("1. Credit Card");
		System.out.println("2. Debit Card");
		System.out.println("3. UPI");
		System.out.println("4. PayPal");

		int choice = scanner.nextInt();
		scanner.nextLine(); 

		IBill paymentMethod = null;
		String name, cardNumber, bankName, upiId, email;
		int cardPin, amount;

		System.out.println("enter amount: ");
		amount = scanner.nextInt();
		scanner.nextLine();
		
		switch (choice) {
		case 1:
			while (true) {
				name = inputName(scanner);
				cardNumber = inputCardNumber(scanner);
				bankName = inputBankName(scanner);
				cardPin = inputPin(scanner);
				paymentMethod = new Credit(name, cardNumber, bankName, amount, cardPin);
				if (paymentMethod.validate()) {
					paymentMethod.payment();
					break;
				}
				System.out.println("Invalid details. Please try again.\n");
			}
			break;

		case 2: 
			while (true) {
				name = inputName(scanner);
				cardNumber = inputCardNumber(scanner);
				bankName = inputBankName(scanner);
				cardPin = inputPin(scanner);
				paymentMethod = new Debit(name, cardNumber, bankName, amount, cardPin);
				if (paymentMethod.validate()) {
					paymentMethod.payment();
					break;
				}
				System.out.println("Invalid details. Please try again.\n");
			}
			break;

		case 3: 
			while (true) {
				upiId = inputUpiId(scanner);
				int upiPin = inputPin(scanner);
				paymentMethod = new Upi(amount, upiId, upiPin);
				if (paymentMethod.validate()) {
					paymentMethod.payment();
					break;
				}
				System.out.println("Invalid UPI details. Please try again.\n");
			}
			break;

		case 4: 
			while (true) {
				email = inputEmail(scanner);
				int paypalPin = inputPin(scanner);
				paymentMethod = new PayPal(amount, email, paypalPin);
				if (paymentMethod.validate()) {
					paymentMethod.payment();
					break;
				}
				System.out.println("Invalid PayPal details. Please try again.\n");
			}
			break;

		default:
			System.out.println("Invalid choice! Please select a valid option.");
		}

		scanner.close();
	}

	private static String inputName(Scanner scanner) {
		System.out.print("Enter your name: ");
		return scanner.nextLine();
	}

	private static String inputCardNumber(Scanner scanner) {
		System.out.print("Enter card number: ");
		return scanner.nextLine();
	}

	private static String inputBankName(Scanner scanner) {
		System.out.print("Enter bank name: ");
		return scanner.nextLine();
	}

	private static String inputEmail(Scanner scanner) {
		System.out.print("Enter email: ");
		return scanner.nextLine();
	}

	private static String inputUpiId(Scanner scanner) {
		System.out.print("Enter UPI ID: ");
		return scanner.nextLine();
	}

	private static int inputPin(Scanner scanner) {
		System.out.print("Enter 4-digit PIN: ");
		int pin = scanner.nextInt();
		scanner.nextLine(); 
		return pin;
	}

}
