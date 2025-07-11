package com.tss.test;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

//import com.tss.model.IFactorial;

public class FactorialTest {
	public static void main(String[] args) {
		Function<Integer, Integer> factorial = (number1) -> {
			int temp = 1;
			while (number1 > 1) {
				temp = temp * (number1--);
			}

			return temp;
		};
		int fact = factorial.apply(3);
		System.out.println("factorial of 3: " + fact);
		
		
		Supplier<Integer> rad = ()->{
			Random Random = new Random();
			return Random.nextInt();
		};
		
		int a = rad.get();
		System.out.println(a);
		
	}

}
