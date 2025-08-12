package com.tss.controller;

import java.util.List;
import java.util.Scanner;

import com.tss.model.Student;
import com.tss.service.StudentService;

public class StudentController {

	private StudentService studentService;
	private Scanner scanner = new Scanner(System.in);

	public StudentController() {
		super();
		this.studentService = new StudentService();
	}

	public void readAllRecords() {
		List<Student> students = studentService.readAllStudent();

		for (Student student : students) {
			System.out.println("Student ID: " + student.getStudentId() + " | " + "Name: " + student.getStudentName()
					+ " | " + "Age: " + student.getAge() + " | " + "Percentage: " + student.getPercentage() + " | "
					+ "Roll No: " + student.getRollNo());
		}
	}

	public void addNewStudent() {

		System.out.print("Enter Student ID: ");
		int studentId = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter Name: ");
		String name = scanner.nextLine();

		System.out.print("Enter Age: ");
		int age = scanner.nextInt();

		System.out.print("Enter Percentage: ");
		double percentage = scanner.nextDouble();

		System.out.print("Enter Roll No: ");
		int rollNo = scanner.nextInt();

		Student student = new Student(studentId, name, age, percentage, rollNo);

		studentService.addStudent(student);

	}

	public void readStudentById() {
		System.out.println("enter student id to get details");
		int studentId = scanner.nextInt();
		Student student = studentService.readStudentById(studentId);

		if (student == null)
			return;
	System.out.println("Student ID: "+student.getStudentId()+" | "+"Name: "+student.getStudentName()+" | "+"Age: "+student.getAge()+" | "+"Percentage: "+student.getPercentage()+" | "+"Roll No: "+student.getRollNo());

	}

	public void updatePercentageById() {
		System.out.println("Enter student ID to update details:");
		int studentId = scanner.nextInt();

		System.out.println("Enter new percentage:");
		double newPercentage = scanner.nextDouble();

		Student student = studentService.updatePercentageById(studentId, newPercentage);

		if (student != null) {
			System.out.println("Student ID: " + student.getStudentId() + " | " + "Name: " + student.getStudentName()
					+ " | " + "Age: " + student.getAge() + " | " + "Percentage: " + student.getPercentage() + " | "
					+ "Roll No: " + student.getRollNo());
		} else {
			System.out.println("Student not found or update failed.");
		}
	}

	public void deleteStudentById() {
		System.out.println("Enter student ID to delete:");
		int studentId = scanner.nextInt();

		boolean deleted = studentService.deleteStudentById(studentId);

		if (deleted) {
			System.out.println("Student deleted successfully.");
		} else {
			System.out.println("Student not found or deletion failed.");
		}
	}

}