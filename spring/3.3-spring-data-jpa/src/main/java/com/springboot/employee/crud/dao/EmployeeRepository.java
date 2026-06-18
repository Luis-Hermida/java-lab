package com.springboot.employee.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.employee.crud.entity.Employee;

// Empty interface — Spring Data generates CRUD implementation at runtime from method names.
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
