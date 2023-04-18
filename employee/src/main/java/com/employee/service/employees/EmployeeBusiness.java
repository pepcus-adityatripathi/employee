package com.employee.service.employees;

import com.employee.entity.employees.Employees;

import java.util.List;

public interface EmployeeBusiness {
    public void addEmployee(Employees employee);

    public Employees updateEmployeeDetails(int id);

    public List<Employees> findAll();

    public void deleteEmployees(int id);

    public List<Employees> filterByDeptId(int deptId);

    public List<Employees> filterByDeptName(String deptName);
}
