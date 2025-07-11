package com.tss.basics3;

import java.util.Scanner;

public class SwapTwoNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number 1: ");
		int number1= scanner.nextInt();
		
		System.out.println("Enter number 2: ");
		int number2= scanner.nextInt();
		
		System.out.println("number1 before: "+number1);
		System.out.println("number2 before: "+number2);

		swapWith(number1,number2);
		swapWithout(number1,number2);
	}
	
	public static void swapWith(int number1, int number2) {
		int number3 = number1;
		number1=number2;
		number2=number3;
		System.out.println("number1 before: "+number1);
		System.out.println("number2 before: "+number2);
	}
	
	public static void swapWithout(int number1, int number2) {
		number1=number1+number2;
		number2=number1-number2;
		number1=number1-number2;
		System.out.println("number1 before: "+number1);
		System.out.println("number2 before: "+number2);
	}

}



