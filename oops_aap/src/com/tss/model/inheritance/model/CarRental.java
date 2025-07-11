package com.tss.model.inheritance.model;


public class CarRental extends VehicleRent {

	public CarRental(int vehicleNumber, int rentPerDay, int numberOfDays, String fuelType) {
		super(vehicleNumber, rentPerDay, numberOfDays ,fuelType );
	}
	
	@Override
	public void calculateRent() {
		total_rent = numberOfDays * rentPerDay;
		System.out.println("total_rent: "+total_rent);
	}

}
