package com.tss.model;

public class Employee implements Comparable<Employee>{
	private int emp_id;
	private String name;
	private int salary;
	
	public Employee() {
		super();
	}
	public Employee(int emp_id, String name, int salary) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.salary = salary;
	}
	
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	
	
	@Override
	public String toString() {
		return "EmployeeTest [emp_id=" + emp_id + ", name=" + name + ", salary=" + salary + "]";
	}
	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		if(emp_id > o.emp_id) {
			return 1;
		}
		if(emp_id < o.emp_id) {
			return -1;
		}
		return 0;
	}
}
