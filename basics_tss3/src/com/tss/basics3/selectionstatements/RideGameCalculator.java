package com.tss.basics3.selectionstatements;

import java.util.Scanner;

public class RideGameCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int cost = 0;

		System.out.println("Enter height: ");
		double height = sc.nextDouble();
		if (height > 120) {
			System.out.println("Yes You can ride.");

			System.out.println("Enter age: ");
			int age = sc.nextInt();

			if (age < 12) {
				cost = 5;
			} else if (age < 18) {
				cost = 7;
			} else if (age < 45) {
				cost = 12;
			} else if (age < 55) {
				cost = 0;
			} else
				cost = 12;

			System.out.println("enter yes or no do you want photos or not?");
			String photo = sc.next();
			if (photo.equalsIgnoreCase("yes")) {
				cost = cost + 3;
			}
			System.out.println("Total Bill is $" + cost);
		} else {
			System.out.println("Can't Ride.");
		}
	}

}
