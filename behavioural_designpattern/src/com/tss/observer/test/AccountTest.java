package com.tss.observer.test;

import java.util.Scanner;

import com.tss.observer.exception.InsufficientBalanceException;
import com.tss.observer.model.Account;
import com.tss.observer.model.EmailNotifier;
import com.tss.observer.model.SMSNotifier;
import com.tss.observer.model.WhatsappNotifier;

public class AccountTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Account account = null;
		boolean loop = true;

		while (loop) {
			try {
				System.out.println("\nBank Menu");
				System.out.println("1. Create Account");
				System.out.println("2. Credit Amount");
				System.out.println("3. Debit Amount");
				System.out.println("4. Display Account Details");
				System.out.println("5. Remove Notifier");
				System.out.println("6. Exit");
				System.out.print("Enter choice: ");
				int choice = sc.nextInt();

				switch (choice) {
				case 1:
					System.out.print("Enter Acc No: ");
					int accNo = sc.nextInt();
					sc.nextLine();
					System.out.print("Enter Name: ");
					String name = sc.nextLine();
					System.out.print("Enter Balance: ");
					double balance = sc.nextDouble();
					System.out.print("Enter Minimum Balance: ");
					double minBalance = sc.nextDouble();

					account = new Account(accNo, name, balance, minBalance);
					System.out.println("Account created.");
					registerNotifiers(sc, account);
					break;

				case 2:
					if (account != null) {
						System.out.print("Enter amount to credit: ");
						double creditAmt = sc.nextDouble();
						account.credit(creditAmt);
					} else {
						System.out.println("Create an account first.");
					}
					break;

				case 3:
					if (account != null) {
						System.out.print("Enter amount to debit: ");
						double debitAmt = sc.nextDouble();
						account.debit(debitAmt);
					} else {
						System.out.println("Create an account first.");
					}
					break;

				case 4:
					if (account != null) {
						account.displayDetails();
					} else {
						System.out.println("No account to display.");
					}
					break;

				case 5:
					if (account != null) {
						System.out.println("Select notifier(s) to remove (comma separated):");
						System.out.println("1. SMS\n2. Email\n3. WhatsApp");
						sc.nextLine(); // clear buffer
						String removeInput = sc.nextLine();
						String[] removeChoices = removeInput.split(",");
						boolean anyRemoved = false;

						for (String choice1 : removeChoices) {
							switch (choice1.trim()) {
							case "1":
								anyRemoved |= account.removeNotifier("sms");
								break;
							case "2":
								anyRemoved |= account.removeNotifier("email");
								break;
							case "3":
								anyRemoved |= account.removeNotifier("whatsapp");
								break;
							default:
								System.out.println("Invalid notifier option: " + choice1.trim());
							}
						}

						if (anyRemoved) {
							System.out.println("Selected notifiers removed successfully.");
						} else {
							System.out.println("No matching notifiers found to remove.");
						}
					} else {
						System.out.println("Create an account first.");
					}
					break;

				case 6:
					System.out.println("Thank you for using the bank app.");
					loop = false;
					break;

				default:
					System.out.println("Invalid choice!");
				}

			} catch (InsufficientBalanceException ex) {
				System.out.println(ex.getMessage());
			} catch (Exception ex) {
				System.out.println("Error: " + ex.getMessage());
				sc.nextLine();
			}
		}

		sc.close();
	}

	private static void registerNotifiers(Scanner sc, Account account) {
		System.out.println("Select notification types (comma separated):");
		System.out.println("1. SMS\n2. Email\n3. WhatsApp");
		sc.nextLine();
		String input = sc.nextLine();
		String[] tokens = input.split(",");
		for (String t : tokens) {
			switch (t.trim()) {
			case "1":
				account.registerNotifier(new SMSNotifier());
				break;
			case "2":
				account.registerNotifier(new EmailNotifier());
				break;
			case "3":
				account.registerNotifier(new WhatsappNotifier());
				break;
			default:
				System.out.println("Invalid notifier option: " + t);
			}
		}
	}
}
