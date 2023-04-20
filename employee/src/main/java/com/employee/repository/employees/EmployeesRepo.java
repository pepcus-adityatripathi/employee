package com.employee.repository.employees;

import com.employee.entity.employees.Employees;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepo extends CrudRepository<Employees, Integer> {
    @Query(value = "SELECT * FROM employees e WHERE e.department_id = :deptId", nativeQuery = true)
    List<Employees> findByDeptId(@Param("deptId") int deptId);

    @Query(value = "SELECT e.*  FROM employees e, department d where d.id=e.DEPARTMENT_ID and d.DEPARTMENT_NAME=:deptName", nativeQuery = true)
    List<Employees> findByDeptName(@Param("deptName") String deptName);
}
