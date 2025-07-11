package com.tss.basics3.iterativemethods;
import java.math.*;
import java.util.Scanner;

public class Armstrong {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("enter a number to ckeck whether a number is armstrong or not");
		int number = sc.nextInt();
		
		checkArmstrong(number);
	}
	
	public static void checkArmstrong(int number) {
		int temp = 0, sum = 0, temp2=number;
	    int size = (int) Math.log10(number) + 1;
	    System.out.println("size"+size);
		while(number>0) {
			temp = number % 10;
			sum  += (Math.pow(temp, size));
			number=number/10;
		}
		
		if(sum==temp2) {
		System.out.println("number is armstrong");
		}
		else {
			System.out.println("number is not an armstrong");
		}
	}
}
