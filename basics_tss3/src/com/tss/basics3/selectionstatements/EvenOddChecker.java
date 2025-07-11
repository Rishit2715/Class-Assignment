package com.tss.basics3.selectionstatements;

import java.util.Scanner;

public class EvenOddChecker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Enter a number to check wheter odd or even");

		Scanner sc = new Scanner(System.in);

		int number = sc.nextInt();
		
		if(number%2==0) {
			System.out.println("number is even");
		}
		else {
			System.out.println("number is odd");
		}
	}

}
