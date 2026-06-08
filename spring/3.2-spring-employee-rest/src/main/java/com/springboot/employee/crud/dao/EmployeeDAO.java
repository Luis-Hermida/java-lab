package com.springboot.employee.crud.dao;

import java.util.List;

import com.springboot.employee.crud.entity.Employee;

public interface EmployeeDAO {
    List<Employee> findAll();
}
