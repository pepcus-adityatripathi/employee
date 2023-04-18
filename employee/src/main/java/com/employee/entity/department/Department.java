package com.employee.entity.department;

import jakarta.persistence.*;

@Entity
@Table(name = "department", uniqueConstraints = {@UniqueConstraint(columnNames = {"department_name"})})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "department_name")
    private String departmentName;

    public Department(int id, String departmentName) {
        this.id = id;
        this.departmentName = departmentName;
    }

    public Department() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
