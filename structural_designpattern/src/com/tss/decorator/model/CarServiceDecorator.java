package com.tss.decorator.model;

public abstract class CarServiceDecorator implements ICarService {
	protected ICarService carObj;

	public CarServiceDecorator(ICarService obj) {
		this.carObj = obj;
	}

	@Override
	public double getCost() {
		return carObj.getCost();
	}
}
