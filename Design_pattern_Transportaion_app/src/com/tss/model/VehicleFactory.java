package com.tss.model;



public class VehicleFactory {
	public static IVehicle getVehicle(VehicleType vehicle) {
		if(vehicle == VehicleType.TwoWheeler ) {
			return new TwoWheeler();
		}
		if(vehicle == VehicleType.FourWheeler ) {
			return new FourWheeler();
		}
		if(vehicle == VehicleType.HeavyWheeler ) {
			return new HeavyWheeler();
		}
		return null;
		
	}}
