package com.tss.basics3.commandline;

public class SwapTwoNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		int number1= Integer.parseInt(args[0]);
		
		int number2= Integer.parseInt(args[1]);
		
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



