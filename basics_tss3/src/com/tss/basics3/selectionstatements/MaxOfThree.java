package com.tss.basics3.selectionstatements;

import java.util.Scanner;

public class MaxOfThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter first number");
		int first = sc.nextInt();
		
		System.out.println("Enter second number");
		int second = sc.nextInt();
		
		System.out.println("Enter third number");
		int third = sc.nextInt();
		
		maxOfThree(first, second, third);
	}

	public static void maxOfThree(int first, int second, int third) {
		if(first>second && first>third) {
			System.out.println("max of three numbers is: "+first);
		}
		else if(second>third){
			System.out.println("max of three numbers is: "+second);
		}
		else {
			System.out.println("max of three numbers is: "+third);
		}
	}
}
