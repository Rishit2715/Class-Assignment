package com.tss.strategy.model;

public class Employee {
    private int id;
    private String name;
    private IRole role;

    public Employee(int id, String name, IRole role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public void promote(IRole newRole) {
        this.role = newRole;
    }

    public String getDescription() {
        return role.getDescription();
    }

    public String getResponsibility() {
        return role.getResponsibility();
    }

    public void printDetails() {
        System.out.println("Employee: " + name);
        System.out.println("Role: " + getDescription());
        System.out.println("Responsibility: " + getResponsibility());
    }
}

