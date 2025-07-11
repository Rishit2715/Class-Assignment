package com.tss.model.inheritance.model;

public class NewRectangle implements IShape {

	int length;
	int breadth;
	public NewRectangle(int length, int breadth) {
		this.length = length;
		this.breadth = breadth;
		area();
	}
	@Override
	public void area() {
		// TODO Auto-generated method stub
		int area = 2*length*breadth;
		System.out.println("area: "+area);
	}

}
