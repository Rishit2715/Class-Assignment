package com.tss.basics3.commandline;

public class AreaOfCircle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
			double rad= Double.parseDouble(args[0]);
			
			
			areaOfCircle(rad);
	}
		public static void areaOfCircle(double rad) {
			double area = 3.14 * rad * rad;
			System.out.println("area of circle ="+area);
		}

}
