package com.tss.model.inheritance.model;
public class Circle extends Shape {
    double radius;

    public Circle(String color, double radius) {
        super();
        this.radius = radius;
    }

    @Override
    public void area() {
        System.out.println(Math.PI * radius * radius);
    }

	
}
