package com.tss.basics3.debuging;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Random random = new Random();
        int number = random.nextInt(5) + 1; 
        
        calculatefactorial(number);
	}

	private static void calculatefactorial(int number) {
		// TODO Auto-generated method stub
		int fact = 1;
		while(number>0) {
			fact = fact *number;
			number--;
		}
		System.out.println("factorial "+fact);
		
	}

}
