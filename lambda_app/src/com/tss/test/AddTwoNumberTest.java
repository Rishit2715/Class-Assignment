package com.tss.test;

import java.util.function.BiFunction;

//import com.tss.model.IAddTwoNumbers;

public class AddTwoNumberTest {

	public static void main(String[] args) {
		BiFunction<Integer, Integer, Integer> addTwoNumbers = (number1, number2) -> {
			 return (number1 + number2);
		};
		
		int add = addTwoNumbers.apply(3, 5);
		System.out.println("addition of 3 and 5: "+add);

	}

}
