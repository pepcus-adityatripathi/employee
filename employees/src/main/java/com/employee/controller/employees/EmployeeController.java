package com.employee.controller.employees;

import com.employee.entity.employees.Employees;
import com.employee.service.employees.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {


    Logger logs = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeBusiness;


    @PostMapping("/")
    public ResponseEntity addEmployee(Employees employee) {
        logs.info("Called POST METHOD");
        employeeBusiness.addEmployee(employee);
        logs.info("Employee Added");
        logs.info("List of employees are" + "\n" + employeeBusiness.findAll());
        return new ResponseEntity<Employees>(employee, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity viewAllEmployee() {
        List<Employees> employee = employeeBusiness.findAll();
        return new ResponseEntity(employee, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity updateEmployee(@RequestParam int id) {
        Employees employees = employeeBusiness.updateEmployeeDetails(id);
        return new ResponseEntity(employees, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity deleteEmployee(@RequestParam int id) {
        employeeBusiness.deleteEmployees(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/filter/{deptId}")
    public ResponseEntity filterByDepartmentId(@PathVariable int deptId) {
        List<Employees> employees = employeeBusiness.filterByDeptId(deptId);
        return new ResponseEntity(employees, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity filterByDepartmentName(String departmentName) {
        List<Employees> employees = employeeBusiness.filterByDeptName(departmentName);
        return new ResponseEntity(employees, HttpStatus.OK);
    }
}
