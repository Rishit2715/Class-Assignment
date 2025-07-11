package com.tss.abstractfactory.model;

public class TataFactory implements ICarFactory {
	public ICars makeCar() {
		// TODO Auto-generated method stub
		return new Tata();
	}

}
