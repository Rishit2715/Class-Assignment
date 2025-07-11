package com.tss.model;

public class Labour implements IWorker {

	@Override
	public void startWork() {
		// TODO Auto-generated method stub
		System.out.println("labour started the work");
		
	}

	@Override
	public void stopWork() {
		// TODO Auto-generated method stub
		System.out.println("labour stopped the work");

	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("labour started the eating");

	}

	@Override
	public void drink() {
		// TODO Auto-generated method stub
		System.out.println("labour started the drinking");

	}

}
