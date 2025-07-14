package com.tss.strategy.test;

import com.tss.strategy.model.Consultant;
import com.tss.strategy.model.Employee;
import com.tss.strategy.model.IRole;
import com.tss.strategy.model.SeniorConsultant;
import com.tss.strategy.model.TechLead;

public class EmployeeTest {
    public static void main(String[] args) {
        IRole consultant = new Consultant();
        Employee employee = new Employee(1, "Rishit", consultant);

        employee.printDetails();

        employee.promote(new SeniorConsultant());
        employee.printDetails();

        employee.promote(new TechLead());
        employee.printDetails();
    }
}
