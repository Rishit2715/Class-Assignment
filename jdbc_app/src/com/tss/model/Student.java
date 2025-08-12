package com.tss.model;

public class Student {
	private int studentId;
	private String studentName;
	private int age;
	private double percentage;
	private int roll_no;

	public Student() {
	}

	public Student(int studentId, String studentName, int age, double percentage, int rollno) {
		this.studentId = studentId;
		this.studentName = studentName;
		this.age = age;
		this.percentage = percentage;
		this.roll_no = rollno;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public int getRollNo() {
		return roll_no;
	}

	public void setRollNo(int rollno) {
		this.roll_no = rollno;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", age=" + age + ", percentage="
				+ percentage + ", rollno=" + roll_no + "]";
	}
}