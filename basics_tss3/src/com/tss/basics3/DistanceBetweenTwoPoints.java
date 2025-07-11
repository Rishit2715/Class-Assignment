package com.tss.basics3;

import java.util.Scanner;
import java.math.*;

public class DistanceBetweenTwoPoints {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter value of x1 point : ");
		double x1= scanner.nextDouble();
		
		System.out.println("Enter value of x2 point: ");
		double x2= scanner.nextDouble();
		
		System.out.println("Enter value of y1 point : ");
		double y1= scanner.nextDouble();
		
		System.out.println("Enter value of y2 point: ");
		double y2= scanner.nextDouble();
		
		distanceBetweenTwoPoints(x1, x2, y1, y2);
	}
	
	public static void distanceBetweenTwoPoints(double x1, double x2, double y1, double y2) {
		double distance= Math.sqrt(((x2-x1)*(x2-x1))+((y2-y1)*(y2-y1)));
		System.out.println("distance = "+distance);
	}
}
