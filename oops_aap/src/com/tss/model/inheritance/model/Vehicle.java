package com.tss.model.inheritance.model;

public class Vehicle {
 private String model;
 private int year;
 private int price;

 public Vehicle(String model, int year, int price) {
     this.model = model;
     this.year = year;
     this.price = price;
 }

 public void startEngine() {
     System.out.println("engine is starting.");
 }
 public void stopEngine() {
     System.out.println("engine is stopped.");
 } 
 public void displayInfo() {
     System.out.println("model: "+model);
     System.out.println("year: "+year);
     System.out.println("price: "+price);

 }


}
