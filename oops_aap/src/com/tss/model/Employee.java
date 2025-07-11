package com.tss.model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Employee {
    private int empid;
    private String name;
    private LocalDate joiningDate;
    private double salary;

    public int getEmpid() {
        return empid;
    }

    public void setEmpid(int empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getExperience() {
        return Period.between(joiningDate, LocalDate.now()).getYears();
    }

    public double calculateBonus() {
        return salary * 0.5;
    }

    public void display() {
        System.out.println("Employee ID   : " + empid);
        System.out.println("Name          : " + name);
        System.out.println("Joining Date  : " + joiningDate);
        System.out.println("Salary        : " + salary);
        System.out.println("Experience    : " + getExperience() + " years");
        System.out.println("Bonus         : " + calculateBonus());
    }
}
