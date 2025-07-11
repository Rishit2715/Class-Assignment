package com.tss.basics3;

import java.util.Scanner;

public class SimpleCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number 1: ");
		int number1= scanner.nextInt();
		
		System.out.println("Enter number 2: ");
		int number2= scanner.nextInt();
		
		addition(number1,number2);
		difference(number1,number2);
		multiplication(number1,number2);
		division(number1,number2);
	}
	
	public static void addition(int number1, int number2) {
		int sum = number1+number2;
		System.out.println("Sum ="+sum);
	}
	
	public static void difference(int number1, int number2) {
		int difference = number1-number2;
		System.out.println("Difference ="+difference);
	}
	
	public static void multiplication(int number1, int number2) {
		int multiplication = number1*number2;
		System.out.println("Multiplication ="+multiplication);
	}
	
	public static void division(int number1, int number2) {
		int division = number1/number2;
		System.out.println("Division ="+division);
	}

}
