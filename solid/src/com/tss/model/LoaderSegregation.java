package com.tss.model;

public class LoaderSegregation implements IWorkerSegregation {

	@Override
	public void startWork() {
		// TODO Auto-generated method stub
		System.out.println("labour started working");
	}

	@Override
	public void stopWork() {
		// TODO Auto-generated method stub
		System.out.println("labour stopped working");

	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("labour started eating");

	}

	@Override
	public void rest() {
		// TODO Auto-generated method stub
		System.out.println("labour started resting");

	}

}
