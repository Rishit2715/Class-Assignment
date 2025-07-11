package com.tss.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.tss.model.Employee;
import com.tss.model.EmployeeNameComparator;
import com.tss.model.EmployeeSalaryComparator;

public class EmployeeTest {

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		List<Employee> employee = new ArrayList<>();

		System.out.println("enter number of employee you want to enter");
		int number = scanner.nextInt();
		for (int i = 0; i < number; i++) {
			System.out.println("enter details for employee " + (i + 1) + ": ");
			readEmployees(employee, scanner);
		}
		Collections.sort(employee);

		System.out.println("after comparable");
		printEmployees(employee, scanner);

		System.out.println("after id comparator");
		Collections.sort(employee, new EmployeeNameComparator());
		printEmployees(employee, scanner);

		
		System.out.println("after name comparable");
		Collections.sort(employee, new EmployeeSalaryComparator());
		printEmployees(employee, scanner);



	}

	private static void readEmployees(List<Employee> employee, Scanner scanner) {
		System.out.println("enter emp_id");
		int emp_id = scanner.nextInt();
		System.out.println("enter name");
		String name = scanner.next();
		System.out.println("enter salary");
		int salary = scanner.nextInt();
		employee.add(new Employee(emp_id, name, salary));
	}

	private static void printEmployees(List<Employee> employee, Scanner scanner) {
		System.out.println(employee);
	}
}
