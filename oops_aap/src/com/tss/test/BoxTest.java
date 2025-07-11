package com.tss.test;

import com.tss.model.*;

public class BoxTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Box box1 = new Box();
		
		box1.display();
		
		box1.setWidth(30);
		box1.display();

		Box box2 = new Box();
		box2.display();
		box2.setWidth(50);
		box2.setHeight(60);
		box2.display();

	}

}
