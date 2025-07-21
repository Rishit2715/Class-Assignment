package com.tss.model;

import java.util.*;

public class Employee {
	

	String name;
    String department;
    double salary;

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', department='" + department + "', salary=" + salary + "}";
    }
}