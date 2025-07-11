package com.tss.model.inheritance.model;

public class Rectangle extends Shape {
    double length;
    double width;

    public Rectangle(String color, double length, double width) {
        super();
        this.length = length;
        this.width = width;
    }
    @Override
    public void area() {
        System.out.println(length * width);
    }

}
