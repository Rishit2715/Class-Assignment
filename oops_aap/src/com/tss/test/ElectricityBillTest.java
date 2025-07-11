package com.tss.test;

import java.util.Random;
import java.util.Scanner;

import com.tss.model.Account;
import com.tss.model.AccountType;
import com.tss.model.ElectricityBill;


public class ElectricityBillTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random random = new Random();

		System.out.println("Enter number of apartments you want:");
		int size = scanner.nextInt();
		ElectricityBill[] apartment = new ElectricityBill[size];

		int i = 0, index = 0, apartmentId = 0;
		int generateAccId = 1;
		while (i <= size) {
			System.out.println("\n1. Enter apartment details");
			System.out.println("2. Generate Bill");
			System.out.println("3. Display bill");
			System.out.println("4. Change Cost per unit");
			System.out.println("5. Exit");			
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				if (i == size) {
					System.out.println("sortry you cannot have more apartments");
					break;
				}
				int apartmentNumber = generateAccId++;
				System.out.println("enter units consumed");
				double  unitsConsumed = scanner.nextInt(); 
				


				apartment[i] = new ElectricityBill(apartmentNumber, unitsConsumed);
				System.out.println("Apartment details added successfully.");
				i++;
				break;

			case 2:
				System.out.print("Enter Apartment number to generate bill: ");
				apartmentId = scanner.nextInt();
				index = findIndexFromAccountId(apartmentId, apartment, size);
				if (isValidApartmentIndex(index, apartment)) {
					System.out.println("Toyal Bill: " + ElectricityBill.generatebill(apartment[index].getUnitsConsumed()));
				} else {
					System.out.println("Invalid account index.");
				}
				break;

			case 3:
				System.out.print("Enter Apartment number to display bill: ");
				apartmentId = scanner.nextInt();
				index = findIndexFromAccountId(apartmentId, apartment, size);
				if (isValidApartmentIndex(index, apartment)) {
					System.out.println("Toyal Bill: " + apartment[index]);
				} else {
					System.out.println("Invalid account index.");
				}
				break;

			case 4:
				System.out.print("Enter new cost per unit: ");
				double costPerUnit = scanner.nextDouble();
				ElectricityBill.setCostPerUnit(costPerUnit);
				break;
				
			case 5:
				System.out.println("Exiting. Thank you!");
				System.exit(0);

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

	}

	public static int findIndexFromAccountId(int apartmemtId, ElectricityBill[] apartment, int size) {
		for (int i = 0; i < size; i++) {
			if (apartment[i].getApartmentNumber() == apartmemtId) {
				return i;
			}
		}
		return -1;
	}

	public static boolean isValidApartmentIndex(int index, ElectricityBill[] apartment) {
		return index >= 0 && index < apartment.length && apartment[index] != null;
	}



}
