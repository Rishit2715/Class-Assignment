package com.tss.test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.tss.model.Student;

public class StudentTest {

	public static void main(String[] args) {
		List<Student> students = new ArrayList<>();

		students.add(new Student("Rishit", 1, 85));
		students.add(new Student("Jay", 2, 78));
		students.add(new Student("Krish", 3, 92));
		students.add(new Student("Kirtan", 4, 67));
		students.add(new Student("Harsh", 5, 73));
		students.add(new Student("Harshvardhan", 6, 88));
		students.add(new Student("Neel", 7, 81));
		students.add(new Student("Om", 8, 90));
		students.add(new Student("Umang", 9, 76));
		students.add(new Student("Abhay", 10, 69));

		Predicate<Student> above75 = (student) -> {
			return student.getMarks() > 75;
		};

		System.out.println("\nStudents with marks above 75:");

		Consumer<Student> congratulate = (student) -> {
			if (above75.test(student)) {
				System.out.println("Congratulations " + student.getName());
			}
		};

		for (Student s : students) {
			congratulate.accept(s);
		}
	}
}
