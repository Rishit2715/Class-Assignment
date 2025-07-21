package com.tss.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tss.model.Employee;

public class EmployeeTest {

	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(new Employee("Rishit", "Finance", 75000),
				new Employee("jay", "Engineering", 80000), new Employee("krish", "Engineering", 75000),
				new Employee("harsh", "Marketing", 75000), new Employee("kirtan", "Finance", 85000));

		employees.sort(Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getName));

//		employees.forEach(System.out::println);

		Map<String, Employee> highestPaidByDept = employees.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.collectingAndThen(
						Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)), Optional::get)));

		highestPaidByDept.forEach((dept, emp) -> System.out.println(dept + " => " + emp));
	}

}