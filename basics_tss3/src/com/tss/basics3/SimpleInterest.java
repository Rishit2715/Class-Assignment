package com.tss.basics3;

import java.util.Scanner;

public class SimpleInterest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter principal amount : ");
		double principalamount= scanner.nextInt();
		
		System.out.println("Enter rate of interest ");
		double rateofinterest= scanner.nextFloat();
		
		System.out.println("Enter duration: ");
		double duration= scanner.nextInt();

		
		simpleInterest(principalamount,rateofinterest,duration);
	}
	
	public static void simpleInterest(double principalamount, double rateofinterest, double duration) {
		double simpleinterest= (principalamount * rateofinterest * duration)/100;
		System.out.println("simple interest = "+simpleinterest);
	}

}
