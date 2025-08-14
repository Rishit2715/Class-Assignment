package com.tss.dao;

import com.tss.database.DBConnection;
import com.tss.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public Employee getEmployeeById(int empno) {
        Employee emp = null;
        String sql = "SELECT * FROM emp WHERE empno = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, empno);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emp = mapRow(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM emp";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    private Employee mapRow(ResultSet rs) throws Exception {
        Employee emp = new Employee();
        emp.setEmpno(rs.getInt("empno"));
        emp.setEname(rs.getString("ename"));
        emp.setJob(rs.getString("job"));
        emp.setMgr(rs.getInt("mgr"));
        emp.setHiredate(rs.getDate("hiredate"));
        emp.setSal(rs.getDouble("sal"));
        emp.setComm(rs.getObject("comm") != null ? rs.getDouble("comm") : null);
        emp.setDeptno(rs.getInt("deptno"));
        return emp;
    }
}
