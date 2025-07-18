package com.tss.basics3.iterativemethods;

import java.util.Scanner;

public class Reverse {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter a number to reverse it");
		int number = sc.nextInt();
		
		sum(number);
	}
	
	public static void sum(int number) {
		int temp = 0, sum = 0;
		while(number>0) {
			temp = number % 10;
			sum = (sum * 10) + temp;
			number=number/10;
		}
		System.out.println("Reverse: "+sum);
	}
}
