package com.tss.test;

import com.tss.model.Account;
import com.tss.model.AccountType;
import java.util.Random;
import java.util.Scanner;

public class AccountTest1 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		System.out.println("Enter number of accounts you want:");
		int size = scanner.nextInt();
		Account[] accounts = new Account[size];

		int i = 0, index = 0, accId = 0;
		int generateAccId = 1;
		while (i <= size) {
			System.out.println("\n1. Create Account");
			System.out.println("2. Display Balance");
			System.out.println("3. Deposit");
			System.out.println("4. Withdraw");
			System.out.println("5. Transfer");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				if (i == size) {
					System.out.println("sortry you cannot create more accounts");
					break;
				}
				int accountId = generateAccId++;
				String accountNumber = "AXIS1000" + (100000 + random.nextInt(900000));
				while (!isUniqueAccountNumber(accountNumber, accounts, i)) {
					accountNumber = "AXIS" + (100000 + random.nextInt(900000));
				}
				System.out.print("Enter Name: ");
				String name = scanner.next();

				System.out.print("Enter Initial Balance: ");
				int balance = scanner.nextInt();

				System.out.print("Enter Account Type 1.SAVINGS 2.CURRENT 3.FD: ");
				int temp = scanner.nextInt();

				AccountType accountType;
				if (temp == 1) {
					accountType = AccountType.SAVINGS;
				} else if (temp == 2) {
					accountType = AccountType.CURRENT;
				} else if (temp == 3) {
					accountType = AccountType.FD;
				} else {
					System.out.println("Invalid account type. Defaulting to FD.");
					accountType = AccountType.FD;
				}

				accounts[i] = new Account(accountId, accountNumber, name, balance, accountType);
				System.out.println("Account created successfully.");
				i++;
				break;

			case 2:
				System.out.print("Enter accountId to view balance: ");
				accId = scanner.nextInt();
				index = findIndexFromAccountId(accId, accounts, size);
				System.out.println("index" + index);
				if (isValidAccountIndex(index, accounts)) {
					System.out.println("Current Balance: " + accounts[index].getBalance());
					System.out.println(accounts[index]);
				} else {
					System.out.println("Invalid account index.");
				}
				break;

			case 3:
				System.out.print("Enter accountId to deposit: ");
				accId = scanner.nextInt();
				index = findIndexFromAccountId(accId, accounts, size);
				if (isValidAccountIndex(index, accounts)) {
					System.out.print("Enter deposit amount: ");
					int depositAmount = scanner.nextInt();
					accounts[index].deposit(depositAmount);
				} else {
					System.out.println("Invalid account index.");
				}
				break;

			case 4:
				System.out.print("Enter accountId to withdraw: ");
				accId = scanner.nextInt();
				index = findIndexFromAccountId(accId, accounts, size);
				if (isValidAccountIndex(index, accounts)) {
					System.out.print("Enter withdrawal amount: ");
					int withdrawAmount = scanner.nextInt();
					int possible = accounts[index].withdraw(withdrawAmount);
					if (possible <= 500) {
						System.out.println("withdraw not possible");
					}
				} else {
					System.out.println("Invalid account index.");
				}
				break;

			case 5:
				System.out.print("Enter accountId to transfer from: ");
				accId = scanner.nextInt();
				index = findIndexFromAccountId(accId, accounts, size);
				System.out.print("Enter accountId to transfer to: ");
				accId = scanner.nextInt();
				int index1 = findIndexFromAccountId(accId, accounts, size);
				if (isValidAccountIndex(index, accounts) && isValidAccountIndex(index1, accounts)) {
					System.out.print("Enter amount to transfer: ");
					int transferAmount = scanner.nextInt();
					int transferPossible = accounts[index].withdraw(transferAmount);
					if (transferPossible >= 500) {
						accounts[index].deposit(transferAmount);
					} else {
						System.out.println("Transfer not possible");
					}
				} else {
					System.out.println("Invalid accountId.");
				}
				break;

			case 6:
				System.out.println("Exiting. Thank you!");
				System.exit(0);

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

	}

	public static int findIndexFromAccountId(int accId, Account[] accounts, int size) {
		for (int i = 0; i < size; i++) {
			if (accounts[i].getAccountId() == accId) {
				return i;
			}
		}
		return -1;
	}

	public static boolean isValidAccountIndex(int index, Account[] accounts) {
		return index >= 0 && index < accounts.length && accounts[index] != null;
	}

	public static boolean isUniqueAccountNumber(String accountNumber, Account[] accounts, int count) {
		for (int i = 0; i < count; i++) {
			if (accounts[i] != null && accounts[i].getAccountNumber().equals(accountNumber)) {
				return false;
			}
		}
		return true;
	}
}
