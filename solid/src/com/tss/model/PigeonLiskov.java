package com.tss.model;

public class PigeonLiskov implements IFlyable {
String color;
	
	
	public PigeonLiskov(String color) {
		super();
		this.color = color;
	}

	public void getColour() {
		System.out.println("color of pigeon is "+color);
	}

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("pigeon can fly");
	}

}
