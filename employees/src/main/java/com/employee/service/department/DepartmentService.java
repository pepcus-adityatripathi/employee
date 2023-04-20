package com.employee.service.department;

import com.employee.entity.department.Department;

import java.util.List;

public interface DepartmentService {

    public void addDepartment(Department department);

    public List<Department> viewAllDepartment();

    public Department updateDepartment(int id);

    public void deleteDepartment(int id);
}
