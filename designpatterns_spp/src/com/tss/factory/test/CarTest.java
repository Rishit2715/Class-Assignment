package com.tss.factory.test;

import com.tss.factory.model.CarFactory;
import com.tss.factory.model.CarType;
import com.tss.factory.model.ICars;

public class CarTest {
	public static void main(String[] args) {
		CarFactory factory = new CarFactory();
		ICars maruti = factory.makeCars(CarType.Maruti);

		maruti.start();
		maruti.stop();

		ICars tata = factory.makeCars(CarType.Tata);

		tata.start();
		tata.stop();
		
		ICars mahindra = factory.makeCars(CarType.Mahindra);

		mahindra.start();
		mahindra.stop();
	}

}
