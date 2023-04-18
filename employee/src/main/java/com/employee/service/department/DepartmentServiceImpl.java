package com.employee.service.department;

import com.employee.entity.department.Department;
import com.employee.exception.DepartmentIdNotFound;
import com.employee.exception.EmptyInputException;
import com.employee.repository.department.DepartmentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    Logger logs = LoggerFactory.getLogger(DepartmentServiceImpl.class);


    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private DepartmentIdNotFound departmentIdNotFound;

    @Override
    public void addDepartment(Department department) {
        if (department == null) {
            throw new EmptyInputException("400", "No values are passed", "Please provide department details");
        }
        if (department.getDepartmentName() == null || department.getDepartmentName().isEmpty()) {
            String missingField = "departmentName";
            throw new EmptyInputException("400", "Field value is empty", missingField);
        }
        departmentRepo.save(department);
    }

    @Override
    public List<Department> viewAllDepartment() {
        List<Department> departmentList = (List<Department>) departmentRepo.findAll();
        return departmentList;
    }

    @Override
    public Department updateDepartment(int id) {
        if (!departmentRepo.existsById(id)) {
            throw new DepartmentIdNotFound(HttpStatus.BAD_REQUEST, "Deparrtment id does not exist. Please check");
        }
        Department department = departmentRepo.findById(id).orElse(null);
        logs.info("Enter the new department");
        Scanner scanner = new Scanner(System.in);
        String newDepartment = scanner.next();
        department.setDepartmentName(newDepartment);
        departmentRepo.save(department);
        scanner.close();
        logs.info("Department details updated" + "\t" + department);
        return department;

    }

    @Override
    public void deleteDepartment(int id) {

        if (!departmentRepo.existsById(id)) {
            throw new DepartmentIdNotFound(HttpStatus.BAD_REQUEST, "Deparrtment id does not exist. Please check");
        }
        departmentRepo.deleteById(id);

    }
}
