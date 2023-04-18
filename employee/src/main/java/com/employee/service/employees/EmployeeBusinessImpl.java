package com.employee.service.employees;

import com.employee.entity.department.Department;
import com.employee.entity.employees.Employees;
import com.employee.exception.DepartmentIdNotFound;
import com.employee.exception.EmptyInputException;
import com.employee.repository.department.DepartmentRepo;
import com.employee.repository.employees.EmployeesRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class EmployeeBusinessImpl implements EmployeeBusiness {

    Logger logs = LoggerFactory.getLogger(EmployeeBusiness.class);
    @Autowired
    private EmptyInputException emptyInputException;
    @Autowired
    private EmployeesRepo employeeRepo;
    @Autowired
    private DepartmentRepo departmentRepo;

    @Override
    @Transactional
    public void addEmployee(Employees employee) {
        if (employee.getDesignation().isEmpty() || employee.getAddress().isEmpty() || employee.getContactNumber().isEmpty()) {
            String missingField = "";
            if (employee.getDesignation().isEmpty()) {
                missingField = "designation";
            } else if (employee.getAddress().isEmpty()) {
                missingField = "address";
            } else {
                missingField = "contact number";
            }
            throw new EmptyInputException("400", "Value of field is missing", missingField);
        }
        Department department = departmentRepo.findById(employee.getDepartment().getId()).orElse(null);
        if (department != null) {
            employee.setDepartment(department);
            employeeRepo.save(employee);
        }

    }

    @Override
    public Employees updateEmployeeDetails(int id) {
        Scanner scanner = new Scanner(System.in);
        Employees employee = employeeRepo.findById(id).orElse(null);
        if (employee != null) {

            logs.info("Enter the constraint that need to be updated");
            String choice = scanner.next();

            switch (choice) {
                case "name":
                    logs.info("Enter the new name");
                    String name = scanner.next();
                    employee.setName(name);
                    employeeRepo.save(employee);
                    break;
                case "address":
                    logs.info("Enter the new address");
                    String address = scanner.next();
                    employee.setAddress(address);
                    employeeRepo.save(employee);
                    break;
                case "contactNumber":
                    logs.info("Enter the new contact number");
                    String contactNumber = scanner.next();
                    employee.setContactNumber(contactNumber);
                    employeeRepo.save(employee);
                    break;
                case "designation":
                    logs.info("Enter the new designation");
                    String designation = scanner.next();
                    employee.setDesignation(designation);
                    employeeRepo.save(employee);
                    break;
                case "departmentId":
                    logs.info("Enter the new departmentId");
                    String departmentId = scanner.next();
                    Department department = departmentRepo.findById(employee.getDepartment().getId()).orElse(null);
                    if (department != null) {
                        employee.setDepartment(department);
                        employeeRepo.save(employee);
                    }
                    break;
                default:
                    logs.info("Wrong choice");
            }
            scanner.close();
            Employees employee1 = employeeRepo.findById(id).orElse(null);
            logs.info("Saved value for record are" + "\t" + employee1);
            return employee1;
        } else {
            throw new DepartmentIdNotFound(HttpStatus.NOT_FOUND, "Employee id not found");
        }
    }

    @Override
    public List<Employees> findAll() {
        List<Employees> employee = (List<Employees>) employeeRepo.findAll();
        logs.info("Details of employee are" + "\n" + employee);
        return employee;
    }

    @Override
    public void deleteEmployees(int id) {
        Employees employee = employeeRepo.findById(id).orElse(null);
        if (employee != null) {
            employeeRepo.deleteById(id);
            logs.info("Records after deletion");
            logs.info("List of employees are " + "\n" + employeeRepo.findAll());
        } else {
            logs.info("Record not found");
        }
    }

    @Override
    public List<Employees> filterByDeptId(int deptId) {
        List<Employees> employees = employeeRepo.findByDeptId(deptId);
        if (employees != null) {
            return employees;
        }
        return null;
    }

    @Override
    public List<Employees> filterByDeptName(String deptName) {
        List<Employees> employees = employeeRepo.findByDeptName(deptName);
        if (employees != null) {
            return employees;
        }
        return null;
    }
}
