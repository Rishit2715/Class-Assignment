package com.tss.abstractfactory.model;

public class MarutiFactory implements ICarFactory{

	@Override
	public ICars makeCar() {
		// TODO Auto-generated method stub
		return new Maruti();
	}

}
