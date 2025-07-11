package com.tss.model.inheritance.model;

public class TruckRental extends VehicleRent {
	
	public TruckRental(int vehicleNumber, int rentPerDay, int numberOfDays, String fuelType) {
		super(vehicleNumber, rentPerDay, numberOfDays ,fuelType );
	}
	
	public void calculateRent() {
		if (numberOfDays > 5) {
			total_rent = (numberOfDays * 500 )+(numberOfDays*rentPerDay);
			System.out.println("total_rent: " + total_rent);
		}else {
			total_rent = numberOfDays * rentPerDay;
			System.out.println("total_rent: " + total_rent);
		}
	}
}
