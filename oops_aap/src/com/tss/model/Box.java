package com.tss.model;

public class Box {

	public int width;
	public int depth;
	public int height;
	
	public void initialize() {
		width = 10;
		depth = 20;
		height = 30;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	public int getWidth() {
		return width;
	}
	public int getDepth() {
		return depth;
	}
	public int getHeight() {
		return height;
	}
	
	public void display() {
		System.out.println("Width = "+width);
		System.out.println("Depth = "+depth);
		System.out.println("Height = "+height);

	}

}
