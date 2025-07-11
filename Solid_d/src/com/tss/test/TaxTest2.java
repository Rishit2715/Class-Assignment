package com.tss.test;

import com.tss.model.DbLogger2;
import com.tss.model.ILogger;
import com.tss.model.TaxCalculator2;

public class TaxTest2 {
public static void main(String []args)
{
	 ILogger logger = new DbLogger2(); 
     TaxCalculator2 calculator = new TaxCalculator2(logger);
     int tax = calculator.calculateTax(1000, 2);
     System.out.println("tax: "+tax);
}
}