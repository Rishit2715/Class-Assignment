package com.tss.test;

import com.tss.model.TaxCalculator;

public class TaxTest {
	public static void main(String []args)
	{
	TaxCalculator calculator = new TaxCalculator();
    int tax = calculator.calculateTax(1000, 0);
	}
}
