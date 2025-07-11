package com.tss.abstractfactory.test;

import java.util.Scanner;

import com.tss.abstractfactory.model.CarType;
import com.tss.abstractfactory.model.ICarFactory;
import com.tss.abstractfactory.model.ICars;
import com.tss.abstractfactory.model.MahindraFactory;
import com.tss.abstractfactory.model.MarutiFactory;
import com.tss.abstractfactory.model.TataFactory;



public class CarTest {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter Car Type : ");
		System.out.print("1. MarutiCar \n2. TataCar \n3. MahindraCar => ");

		int car = scanner.nextInt();

		if (car == 1) {
			ICarFactory marutiFactory = new MarutiFactory();
			ICars maruti = marutiFactory.makeCar();
			maruti.start();
			maruti.stop();
			System.out.println();
		}

		if (car == 2) {
			ICarFactory tataFactory = new TataFactory();
			ICars tata = tataFactory.makeCar();
			tata.start();
			tata.stop();
			System.out.println();
		}

		if (car == 3) {
			ICarFactory mahindraFactory = new MahindraFactory();
			ICars mahindra = mahindraFactory.makeCar();
			mahindra.start();
			mahindra.stop();
		}

		if (car != 1 && car != 2 && car != 3) {
			System.out.println("Enter valid car");
		}

	}

}
