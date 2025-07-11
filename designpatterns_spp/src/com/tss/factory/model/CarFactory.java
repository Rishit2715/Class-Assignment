package com.tss.factory.model;

public class CarFactory {
	public ICars makeCars(CarType car) {
		if(car == CarType.Maruti ) {
			return new Maruti();
		}
		if(car == CarType.Mahindra ) {
			return new Mahindra();
		}
		if(car == CarType.Tata ) {
			return new Tata();
		}
		return null;
		
	}

}
