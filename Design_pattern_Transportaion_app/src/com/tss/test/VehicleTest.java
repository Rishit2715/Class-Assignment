package com.tss.test;

import java.util.Scanner;

import com.tss.model.IVehicle;
import com.tss.model.VehicleFactory;
import com.tss.model.VehicleType;

public class VehicleTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleFactory factory = new VehicleFactory();
        IVehicle vehicle = null;

        System.out.println("Enter vehicle type:");
        System.out.println("1. Two Wheeler");
        System.out.println("2. Four Wheeler");
        System.out.println("3. Heavy vehicle");
        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                vehicle = factory.getVehicle(VehicleType.TwoWheeler);
                break;
            case 2:
                vehicle = factory.getVehicle(VehicleType.FourWheeler);
                break;
            case 3:
                vehicle = factory.getVehicle(VehicleType.HeavyWheeler);
                break;
            default:
                System.out.println("Invalid choice.");
                scanner.close();
                return;
        }

        String license = vehicle.generateLiscenceNumber();
        System.out.println("Generated License Number: " + license);

        scanner.close();
    }
}
