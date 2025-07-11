package com.tss.test;

import java.util.Scanner;

import com.tss.model.*;

public class FixedDepositSolidTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter Account Number: ");
		int accNo = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter Name: ");
		String name = sc.nextLine();

		System.out.print("Enter Principal Amount: ");
		double principal = sc.nextDouble();

		System.out.print("Enter Duration (in years): ");
		int duration = sc.nextInt();

		System.out.println("Choose Festival Type:");
		System.out.println("1. New Year");
		System.out.println("2. Diwali");
		System.out.println("3. Holi");
		System.out.println("4. Others");
		System.out.print("Enter your choice (1-4): ");
		int choice = sc.nextInt();

		IFestival interest = null;

		switch (choice) {
		case 1:
			interest = new NewYearInterest();
			break;
		case 2:
			interest = new DiwaliInterest();
			break;
		case 3:
			interest = new HoliInterest();
			break;
		case 4:
			interest = new OtherInterest();
			break;
		default:
			System.out.println("Invalid choice. others will be applicable");
			interest = new OtherInterest();
		}

		FixedDepositSolid fd = new FixedDepositSolid(accNo, name, principal, duration, interest);
		SimpleInterestCalculator calc = new SimpleInterestCalculator();

		System.out.println("\n" + fd.getName() + "'s Interest: " + calc.calculate(fd));

		sc.close();
	}
}
