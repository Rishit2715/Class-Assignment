package com.tss.model;

public class Robot implements IWorker {

	@Override
	public void startWork() {
		// TODO Auto-generated method stub
		System.out.println("robot started the work");
		
	}

	@Override
	public void stopWork() {
		// TODO Auto-generated method stub
		System.out.println("robot stopped the work");

	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("robot started the eating");

	}

	@Override
	public void drink() {
		// TODO Auto-generated method stub
		System.out.println("robot started the drinking");

	}

}
