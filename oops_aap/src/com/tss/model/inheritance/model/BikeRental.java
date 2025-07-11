package com.tss.model.inheritance.model;

public class BikeRental extends VehicleRent {

	public BikeRental(int vehicleNumber, int rentPerDay, int numberOfDays, String fuelType) {
		super(vehicleNumber, rentPerDay, numberOfDays ,fuelType );
	}

	public void calculateRent() {
		if (numberOfDays > 5) {
			double temp = (numberOfDays * rentPerDay );
			total_rent = temp-(temp/10);
			System.out.println("total_rent: " + total_rent);
		}else {
			total_rent = numberOfDays * rentPerDay;
			System.out.println("total_rent: " + total_rent);
		}
	}
}
