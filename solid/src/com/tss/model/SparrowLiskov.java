package com.tss.model;

public class SparrowLiskov implements IFlyable {
String color;
	
	
	public SparrowLiskov(String color) {
		super();
		this.color = color;
	}

	public void getColour() {
		System.out.println("color of sparrow is "+color);
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("sparrow can fly");
	}

}
