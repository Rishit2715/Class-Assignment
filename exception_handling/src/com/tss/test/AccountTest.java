package com.tss.test;

import java.util.Scanner;

import com.tss.model.Account;
import com.tss.model.CurrentAccount;
import com.tss.model.SavingsAccount;

public class AccountTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		Account account = null;
		boolean loop = true;
		while (loop) {
			try {

				System.out.println();
				System.out.println("Bank Menu");
				System.out.println("1. Create Current Account");
				System.out.println("2. Create Savings Account");
				System.out.println("3. Credit Amount");
				System.out.println("4. Debit Amount");
				System.out.println("5. Display Account Details");
				System.out.println("6. Exit");
				System.out.print("Enter choice: ");
				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					System.out.print("Enter Acc No: ");
					int currentAccNo = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter Name: ");
					String currentAccountName = sc.nextLine();
					System.out.print("Enter Balance: ");
					double currentAccountBalance = sc.nextDouble();
					System.out.print("Enter Overdraft Limit: ");
					double limit = sc.nextDouble();
					account = new CurrentAccount(currentAccNo, currentAccountName, currentAccountBalance, limit);
					System.out.println("Current account created.");
					break;

				case 2:
					System.out.print("Enter Acc No: ");
					int savingsAccountNo = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter Name: ");
					String savingsAccountName = sc.nextLine();
					System.out.print("Enter Balance: ");
					double savingsAccountBalance = sc.nextDouble();
					System.out.print("Enter Minimum Balance: ");
					double minimumBal = sc.nextDouble();
					account = new SavingsAccount(savingsAccountNo, savingsAccountName, savingsAccountBalance,
							minimumBal);
					System.out.println("Savings account created.");
					break;

				case 3:
					if (account != null) {
						System.out.print("Enter amount to credit: ");
						double amt = sc.nextDouble();
						account.credit(amt);
						break;
					}
					System.out.println("Create an account first.");
					break;

				case 4:
					if (account != null) {
						System.out.print("Enter amount to debit: ");
						double amt = sc.nextDouble();
						if (account instanceof CurrentAccount) {
							((CurrentAccount) account).debit(amt);
							break;
						} else if (account instanceof SavingsAccount) {
							((SavingsAccount) account).debit(amt);
							break;
						}
					}
					System.out.println("Create an account first.");
					break;

				case 5:
					if (account != null) {
						account.displayDetails();
						break;
					}
					System.out.println("No account to display.");
					break;

				case 6:
					System.out.println("Thank you for using the bank app.");
					loop = false;
					break;

				default:
					System.out.println("Invalid choice!");
				}

			} catch (Exception exception) {
				System.out.println(exception.getMessage());
			}
		}

	}

}
