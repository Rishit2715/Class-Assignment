package com.tss.basics3.iterativemethods;

import java.util.Scanner;

public class Palindrome {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter a number to reverse it");
		int number = sc.nextInt();
		
		checkPalindrome(number);
	}
	
	public static void checkPalindrome(int number) {
		int temp = 0, sum = 0, temp2=number;
		while(number>0) {
			temp = number % 10;
			sum = (sum * 10) + temp;
			number=number/10;
		}
		if(sum==temp2) {
		System.out.println("Yes it is palindrome");
		}
		else {
			System.out.println("No it is palindrome");
		}
	}
}
