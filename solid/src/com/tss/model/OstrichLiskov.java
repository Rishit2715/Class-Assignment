package com.tss.model;

public class OstrichLiskov implements INonFlyable {

	String color;
	
	

	public OstrichLiskov(String color2) {
		super();
		this.color = color2;
	}

	public void getColour() {
		System.out.println("color of ostrich is "+color);
	}

	@Override
	public void walk() {
		// TODO Auto-generated method stub
		System.out.println("ostrich can walk");
	}

	

}
