package com.tss.model.inheritance.model;

public class ElectricCar extends Car {
 private int batteryLevel;

 public ElectricCar(String model, int year, int price, int numberOfDoors, int batteryLevel) {
     super(model, year, price, numberOfDoors); 
     this.batteryLevel = batteryLevel; 
 }


 public void chargeBattery() {
     System.out.println("Battery is charging");
 }
 
 public void displayBAtteryStatus() {
     System.out.println("Battery level: "+batteryLevel);
 }
}
