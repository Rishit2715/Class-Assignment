package com.tss.test;

import java.util.function.Predicate;

public class OddEven {
	public static void main(String []args) {
		Predicate<Integer> checkEvenOdd = (number1) -> {
			if (number1 % 2 == 0) {
				return true;
			}
			return false;
		};
		
		if(checkEvenOdd.test(5)) {
			System.out.println("number is even");
		}
		else {
			System.out.println("number is odd");

		}
	}
}
