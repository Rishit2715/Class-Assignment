package com.tss.model;

public class Student {
	private int studentId;
	private String studentName;
	private int rollNo;
	private int age;
	private double percentage;

	public Student(int studentId, String studentName, int rollNo, int age, double percentage) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.rollNo = rollNo;
		this.age = age;
		this.percentage = percentage;
	}

	public int getStudentId() {
		return studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public int getRollNo() {
		return rollNo;
	}

	public int getAge() {
		return age;
	}

	public double getPercentage() {
		return percentage;
	}
}
