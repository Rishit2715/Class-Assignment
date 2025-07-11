package com.tss.test;

import java.util.Scanner;
import com.tss.exception.InsufficientWaterException;
import com.tss.exception.OverFillException;
import com.tss.model.WaterDispenser;

public class WaterDispenserTest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		WaterDispenser dispenser = new WaterDispenser(0);

		while (true) {
			System.out.println("\nChoose an option:");
			System.out.println("1. Fill Water");
			System.out.println("2. Dispense Water");
			System.out.println("3. Exit");
			System.out.print("Enter choice: ");
			int choice = scanner.nextInt();

			switch (choice) {
				case 1:
					try {
						System.out.print("Enter liters to fill: ");
						int fillAmount = scanner.nextInt();
						
						if (fillAmount <= 0) {
							System.out.println("Error: Enter a positive number.");
							break;
						}

						dispenser.fillWater(fillAmount);
					} catch (OverFillException e) {
						System.out.println("Error: " + e.getMessage());
					} finally {
						System.out.println("Current Water Level: " + dispenser.getCurrentLiters() + "L");
					}
					break;

				case 2:
					try {
						System.out.print("Enter liters to dispense: ");
						int dispenseAmount = scanner.nextInt();

						if (dispenseAmount <= 0) {
							System.out.println("Error: enter a positive number.");
							break;
						}

						dispenser.dispenseWater(dispenseAmount);
					} catch (InsufficientWaterException e) {
						System.out.println("Error: " + e.getMessage());
					} finally {
						System.out.println("Current Water Level: " + dispenser.getCurrentLiters() + "L");
					}
					break;

				case 3:
					System.out.println("Exiting system");
					scanner.close(); 
					return;

				default:
					System.out.println("Invalid choice. Please try again.");
			}
		}
	}
}
