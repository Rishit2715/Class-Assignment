package com.tss.model;

public class ElectricityBill {

	private int apartmentNumber;
	private static double costPerUnit;
	private double unitsConsumed;
	
	public ElectricityBill() {
		apartmentNumber = 0;
		unitsConsumed = 0;
	}
	
	public ElectricityBill(int apartmentNumber, double unitsConsumed) {
		this.apartmentNumber = apartmentNumber;
		this.unitsConsumed = unitsConsumed;
	}
	
	@Override
	public String toString() {
		return "ElectricityBill [apartmentNumber=" + apartmentNumber + ", unitsConsumed=" + unitsConsumed + "]";
	}

	public int getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(int apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public static double getCostPerUnit() {
		return costPerUnit;
	}
	public static void setCostPerUnit(double costPerUnit) {
		ElectricityBill.costPerUnit = costPerUnit;
	}
	public double getUnitsConsumed() {
		return unitsConsumed;
	}
	public void setUnitsConsumed(double unitsConsumed) {
		this.unitsConsumed = unitsConsumed;
	}
	
	public static double generatebill(double unitsConsumed) {
		double totalBill = costPerUnit * unitsConsumed;
		return totalBill;
	}
	

}
