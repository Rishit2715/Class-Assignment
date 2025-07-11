package com.tss.model.inheritance.test;

import com.tss.model.inheritance.model.BikeRental;
import com.tss.model.inheritance.model.CarRental;
import com.tss.model.inheritance.model.TruckRental;
import com.tss.model.inheritance.model.VehicleRent;


public class VehicleRentTest {
    public static void main(String[] args) {
        VehicleRent car = new CarRental(11111, 1000, 4, "Petrol");
        car.calculateRent();
        car.displayDetails();

        System.out.println("\n");

        VehicleRent bike = new BikeRental(22222, 500, 10, "petrol");
        bike.calculateRent();
        bike.displayDetails();

        System.out.println("\n");

        VehicleRent truck = new TruckRental(33333, 1500, 7, "Diesel");
        truck.calculateRent();
        truck.displayDetails();
    }
}
