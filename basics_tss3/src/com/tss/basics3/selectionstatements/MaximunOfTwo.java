package com.tss.basics3.selectionstatements;

import java.util.Scanner;

public class MaximunOfTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter first number");
		int first = sc.nextInt();
		
		System.out.println("Enter second number");
		int second = sc.nextInt();
		
		maxOfTwo(first, second);
	}
	
	public static void maxOfTwo(int first, int second) {
		if(first>second) {
			System.out.println("max of two numbers is: "+first);
		}
		else {
			System.out.println("max of two numbers is: "+second);
		}
	}

}
