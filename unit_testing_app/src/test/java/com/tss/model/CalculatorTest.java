package com.tss.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {
	private Calculator calculator;

	@BeforeEach
	void init() {
		calculator = new Calculator();
	}

	@AfterEach
	 void  display() {
		System.out.println("testing done");
	}

//	@Test
//	void testAddition() {
//		assertEquals(5, calculator.addition(3, 2));
//	}
//
//	void testSubtraction() {
//		assertEquals(1, calculator.subtraction(3, 2));
//	}
//
//	void testMultiplication() {
//		assertEquals(6, calculator.multiplication(3, 2));
//	}
	@Test
	void tesdtDivision() {
		int actualResult = calculator.division(10,0);
		assertEquals(0, actualResult);
        assertThrows(ArithmeticException.class, () -> calculator.division(3, 0),"");
	}

	
}
