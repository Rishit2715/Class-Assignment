package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.Circle;
import com.tss.model.inheritance.model.Rectangle;
import com.tss.model.inheritance.model.Shape;

public class ShapeTest {
	public static void main(String[] args) {
		Shape circle = new Circle("Red", 5.0);
		Shape rectangle = new Rectangle("Blue", 4.0, 6.0);

		circle.area();
		rectangle.area();
	}
}
