package com.tss.task;

public class MyTask2 implements Runnable {

	@Override
	public void run() {

		System.out.println(Thread.currentThread().getName() + "i am running 2");

	}

}