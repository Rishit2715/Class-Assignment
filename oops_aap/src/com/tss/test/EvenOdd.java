package com.tss.test;

import java.util.Scanner;

public class EvenOdd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		System.out.println("enter number to check odd or even");
		int check = scanner.nextInt();
		
		if(check%2==0) {
			System.out.println("number is even");
			return ;
		}
		System.out.println("number is odd");

	}

}
