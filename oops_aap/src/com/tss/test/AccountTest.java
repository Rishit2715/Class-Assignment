package com.tss.test;

import com.tss.model.Account;
import com.tss.model.AccountType;

import java.util.Scanner;

public class AccountTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Account account = null;

		while (true) {
			System.out.println("1. Create Account");
			System.out.println("2. Display Balance");
			System.out.println("3. Deposit");
			System.out.println("4. Withdraw");
			System.out.println("5. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter Account ID: ");
				int accountId = scanner.nextInt();
				System.out.print("Enter Account Number: ");
				String accountNumber = scanner.next();
				scanner.nextLine();
				System.out.print("Enter Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter Initial Balance: ");
				int balance = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Account Type 1.SAVINGS 2.CURRENT 3.FD: ");
				int temp = scanner.nextInt();
				AccountType accountType = AccountType.FD;
				if(temp == 1) {
					 accountType = AccountType.CURRENT;
				}else if(temp == 2) {
					 accountType = AccountType.SAVINGS;
				}else if(temp == 1) {
					 accountType = AccountType.FD;
				}
				else {
					System.out.println("not valid type");
				}
				

				account = new Account(accountId, accountNumber, name, balance, accountType);
				System.out.println("Account created successfully.");
				break;

			case 2:
				if (account != null) {
					System.out.println("Current Balance: " + account.getBalance());
				} else {
					System.out.println("No account found. Please create one first.");
				}
				break;

			case 3:
				if (account != null) {
					System.out.print("Enter deposit amount: ");
					int depositAmount = scanner.nextInt();
					account.deposit(depositAmount);
				} else {
					System.out.println("No account found. Please create one first.");
				}
				break;

			case 4:
				if (account != null) {
					System.out.print("Enter withdrawal amount: ");
					int withdrawAmount = scanner.nextInt();
					account.withdraw(withdrawAmount);
				} else {
					System.out.println("No account found. Please create one first.");
				}
				break;

			case 5:
				System.out.println("Exiting. Thank you!");
				System.exit(0);

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
