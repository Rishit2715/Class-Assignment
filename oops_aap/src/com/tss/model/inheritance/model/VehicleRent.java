package com.tss.model.inheritance.model;

public abstract class VehicleRent {
	protected int rentPerDay;
	protected int numberOfDays;
	protected String fuelType;
	protected  double total_rent;
	protected int vehicleNumber;
	
	public VehicleRent(int vehicleNumber, int rentPerDay, int numberOfDays, String fuelType) {
		this.vehicleNumber = vehicleNumber;
		this.rentPerDay = rentPerDay;
		this.numberOfDays = numberOfDays;
		this.fuelType = fuelType;
	}


	
	public abstract void calculateRent();
	
	public void displayDetails() {
		System.out.println("vehicle number: "+vehicleNumber);
		System.out.println("rentperday: "+rentPerDay);
		System.out.println("numberOfDays: "+numberOfDays);
		System.out.println("fuelType: "+fuelType);
		System.out.println("total_rent: "+total_rent);

	}
}
