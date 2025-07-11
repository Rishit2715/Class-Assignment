package com.tss.model.inheritance.test;

import java.util.Scanner;

import com.tss.model.inheritance.model.Account;
import com.tss.model.inheritance.model.CurrentAccount;
import com.tss.model.inheritance.model.SavingsAccount;

public class AccountTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Account account = null;

		while (true) {
			System.out.println("1. Create Current Account");
			System.out.println("2. Create Savings Account");
			System.out.println("3. Credit Amount");
			System.out.println("4. Debit Amount");
			System.out.println("5. Display");
			System.out.println("6. Exit");
			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.print("Enter Account Number: ");
				int cAccNo = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Name: ");
				String cName = scanner.nextLine();
				System.out.print("Enter Balance: ");
				double cBalance = scanner.nextDouble();
				System.out.print("Enter Overdraft Limit: ");
				double limit = scanner.nextDouble();
				account = new CurrentAccount(cAccNo, cName, cBalance, limit);
				System.out.println("Current account created.");
				break;

			case 2:
				System.out.print("Enter Acc No: ");
				int sAccNo = scanner.nextInt();
				scanner.nextLine();
				System.out.print("Enter Name: ");
				String sName = scanner.nextLine();
				System.out.print("Enter Balance: ");
				double sBalance = scanner.nextDouble();
				System.out.print("Enter Minimum Balance: ");
				double minBal = scanner.nextDouble();
				account = new SavingsAccount(sAccNo, sName, sBalance, minBal);
				System.out.println("Savings account created.");
				break;

			case 3:
				if (account != null) {
					System.out.print("Enter amount to credit: ");
					double amt = scanner.nextDouble();
					account.credit(amt);
				} else {
					System.out.println("Create an account first.");
				}
				break;

			case 4:
				if (account != null) {
					System.out.print("Enter amount to debit: ");
					double amt = scanner.nextDouble();
					if (account instanceof CurrentAccount) {
						((CurrentAccount) account).debit(amt);
					} else if (account instanceof SavingsAccount) {
						((SavingsAccount) account).debit(amt);
					}
				} else {
					System.out.println("Create an account first.");
				}
				break;

			case 5:
				if (account != null) {
					account.displayDetails();
				} else {
					System.out.println("No account to display.");
				}
				break;

			case 6:
				System.out.println("Thank you for using the bank app.");
				scanner.close();
				System.exit(0);

			default:
				System.out.println("Invalid choice!");
			}
		}
	}
}
