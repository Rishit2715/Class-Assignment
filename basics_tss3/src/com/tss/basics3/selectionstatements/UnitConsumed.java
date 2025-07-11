package com.tss.basics3.selectionstatements;

import java.util.Scanner;

public class UnitConsumed {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter units consumed");
		double unitsconsumed = sc.nextDouble();
		
		findWaterBill(unitsconsumed);
	}
	
	public static void findWaterBill(double unitsconsumed) {
		double charge = 0, total_water_bill = 0,  meter_charge=75;
		double charge1=500, charge2=1500;
		

		if(unitsconsumed>=250) {
			charge = charge1 + charge2 + ((unitsconsumed-250)*20); 
		}
		else {
			if(unitsconsumed>=100) {
				charge = charge1 + ((unitsconsumed-100)*20); 
			}
			else {
				charge = unitsconsumed*5;
			}
		}
		total_water_bill = charge + meter_charge;
		System.out.println("Total Water Bill : "+total_water_bill);
	}

}
