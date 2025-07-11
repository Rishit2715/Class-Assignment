package com.tss.model;

public class DbLogger2 implements ILogger {

	@Override
	public void log(String err) {
		System.out.println("Logged to database: " + err);
	}

} 