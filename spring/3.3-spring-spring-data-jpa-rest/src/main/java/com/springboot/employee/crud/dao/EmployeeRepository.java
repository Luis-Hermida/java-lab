package com.springboot.employee.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.employee.crud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
