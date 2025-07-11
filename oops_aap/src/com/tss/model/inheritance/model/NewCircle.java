package com.tss.model.inheritance.model;

public class NewCircle {


		double radius;
		public NewCircle(double radius) {
			this.radius = radius;
			area();
		}
		public void area() {
			// TODO Auto-generated method stub
			double area = 3.14*radius*radius;
			System.out.println("area: "+area);
		}


}
