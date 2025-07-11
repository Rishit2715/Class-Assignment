package com.tss.basics3;

import java.util.Scanner;

public class AreaOfCircle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Scanner scanner = new Scanner(System.in);
			
			System.out.println("Enter radius: ");
			double rad= scanner.nextDouble();
			
			
			areaOfCircle(rad);
	}
		public static void areaOfCircle(double rad) {
			double area = 3.14 * rad * rad;
			System.out.println("area ="+area);
		}

}
