package com.tss.basics3.iterativemethods;

import java.util.Scanner;

public class PrimeNumberPattern {
	public static void main(String[] args) {
		int k=2;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter how manyt rows you want");
		int rows = sc.nextInt();
		
		for(int i=1; i<=rows; i++) {
			for(int j=1; j<=i; j++) {
				if(isprime(k)) {
					System.out.print(k+" ");
					k++;
				}
				else {
					j--;
					k++;
					continue;
				}
			}
			System.out.println();
		}
	}
	
	public static boolean isprime(int number) {
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
			return true;
		} else {
			return false;
		}
	}
}
