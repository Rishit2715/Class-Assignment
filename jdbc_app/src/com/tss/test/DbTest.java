package com.tss.test;

import java.util.Scanner;
import com.tss.database.Database;
import com.tss.model.Student;

public class DbTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Database db = new Database();

		System.out.print("Enter Student ID: ");
		int studentId = sc.nextInt();

		sc.nextLine(); 

		System.out.print("Enter Student Name: ");
		String studentName = sc.nextLine();

		System.out.print("Enter Roll Number: ");
		int rollNo = sc.nextInt();

		System.out.print("Enter Age: ");
		int age = sc.nextInt();

		System.out.print("Enter Percentage: ");
		double percentage = sc.nextDouble();

		Student student = new Student(studentId, studentName, rollNo, age, percentage);

		db.addStudent(student);
		db.readAllStudents();

		sc.close(); 
	}
}
