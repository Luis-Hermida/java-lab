package com.springboot.employee.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.employee.crud.entity.Employee;

// Spring Data JPA repository — backs the same /api/employees controller as module 3.3.
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
