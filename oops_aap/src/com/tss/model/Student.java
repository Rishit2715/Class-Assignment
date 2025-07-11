package com.tss.model;

public class Student {

	private int rollNumber;
	private String name;
	private int age;
	private int subject1;
	private int subject2;
	private int subject3;
	
	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setMarks(int subject1, int subject2, int subject3) {
		this.subject1 = subject1;
		this.subject2 = subject2;
		this.subject3 = subject3;
	}
	
	public int getRollNumber(int rollNumber) {
		return rollNumber;
	}
	
	public String getName(String name) {
		return name;
	}
	
	public int getAge(int age) {
		return age;
	}
	
	public int getSubject1Marks(int subject1) {
		return subject1;
	}
	public int getSubject2Marks(int subject2) {
		return subject2;
	}
	public int getSubject3Marks(int subject3) {
		return subject3;
	}
	
	
	public double marksAverage() {
		double average = (subject1 + subject2 + subject3)/3;
		return average;
	}

}
