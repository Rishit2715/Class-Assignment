package com.tss.service;

import com.tss.dao.EmployeeDao;
import com.tss.model.Employee;
import java.util.List;

public class EmployeeService {

    private EmployeeDao dao = new EmployeeDao();

    public Employee findEmployee(int empno) {
        return dao.getEmployeeById(empno);
    }

    public List<Employee> findAllEmployees() {
        return dao.getAllEmployees();
    }
}
