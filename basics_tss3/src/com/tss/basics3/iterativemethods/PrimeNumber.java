package com.tss.basics3.iterativemethods;

import java.util.Scanner;

public class PrimeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number: ");
		int number = sc.nextInt();
		
		primeNumber(number);

	}
	
	public static void primeNumber(int number) {
		int i = 2;
		Boolean isprime = true;
		while (i <= number / 2) {
			if (number % i == 0) {
				isprime = false;
				break;
			}
			i++;
		}
		if (isprime) {
			System.out.println("prime number");
		} else {
			System.out.println("not an prime number");
		}
	}

}
