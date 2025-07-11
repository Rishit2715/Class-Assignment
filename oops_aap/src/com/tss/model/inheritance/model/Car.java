package com.tss.model.inheritance.model;

public class Car extends Vehicle {
 private int numberOfDoors;

 public Car(String model, int year, int price, int numberOfDoors) {
     super(model, year, price); 
     this.numberOfDoors = numberOfDoors; 
 }

 public void lockDoors() {
     System.out.println("Door is locking");
 }
 public void unlockDoors() {
     System.out.println("Door is unlocking");
 }
}
