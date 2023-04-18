package com.employee.controller.department;

import com.employee.entity.department.Department;
import com.employee.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity addingDepartment(Department department) {
        departmentService.addDepartment(department);
        return new ResponseEntity(department, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity viewAddedDepartments() {
        List<Department> departmentList = departmentService.viewAllDepartment();
        return new ResponseEntity(departmentList, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity updateDepartment(int id) {
        Department department = departmentService.updateDepartment(id);
        return new ResponseEntity(department, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity deleteDepartment(int id) {
        departmentService.deleteDepartment(id);
        return new ResponseEntity("Entry with id" + "\t" + id + "\t" + "is deleted", HttpStatus.OK);
    }
}
