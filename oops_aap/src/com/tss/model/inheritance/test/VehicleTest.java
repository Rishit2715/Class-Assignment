package com.tss.model.inheritance.test;
import com.tss.model.inheritance.model.ElectricCar;

public class VehicleTest {

	public static void main(String[] args) {
		ElectricCar car = new ElectricCar("hello", 2004, 150000, 4, 70);
		car.startEngine();
		car.stopEngine();
		car.displayInfo();
		
		car.lockDoors();
		car.unlockDoors();
		
		car.chargeBattery();
		car.displayBAtteryStatus();

	}

}
