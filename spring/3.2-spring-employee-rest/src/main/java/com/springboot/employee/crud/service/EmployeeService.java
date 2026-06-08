package com.springboot.employee.crud.service;

import java.util.List;

import com.springboot.employee.crud.entity.Employee;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int Id);

    Employee save(Employee employee);

    void deleteById(int Id);
}
