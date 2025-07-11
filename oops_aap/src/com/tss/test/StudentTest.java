package com.tss.test;

import java.util.Scanner;

import com.tss.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner = new Scanner(System.in);
		Student student1 = new Student();
		
		System.out.println("enter details for student1");
		System.out.println("Enter Roll Number");
		student1.setRollNumber(scanner.nextInt());
		
		System.out.println("Enter Age");
		student1.setAge(scanner.nextInt());
		
		System.out.println("Enter Name");
		student1.setName(scanner.next());
		
		System.out.println("Enter marks of subject1 subject2 and subject3");
		student1.setMarks(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
		
		System.out.println("Average of marks: ");
		System.out.println(student1.marksAverage());
		
		Student student2 = new Student();

		System.out.println("enter details for student2");
		System.out.println("Enter Roll Number");
		student2.setRollNumber(scanner.nextInt());
		
		System.out.println("Enter Age");
		student2.setAge(scanner.nextInt());
		
		System.out.println("Enter Name");
		student2.setName(scanner.next());
		
		System.out.println("Enter marks of subject1 subject2 and subject3");
		student2.setMarks(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
		
		System.out.println("Average of marks: ");
		System.out.println(student1.marksAverage());
		

	}

}
