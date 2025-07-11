package com.tss.basics3.iterativemethods;

import java.util.Scanner;

public class PerfectNumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter a number to check perfect or not");
		int number = sc.nextInt();
		
		perfect(number);
	}
	
	public static void perfect(int number) {
		int i = 1, sum = 0;
		while(i<=number/2) {
			if(number%i==0) {
				sum=sum+i;
			}
			i++;
		}
		if(sum==number) {
		System.out.println("number is perfect");
		}
		else {
			System.out.println("number is not perfect");
		}
	}
}
