package com.springboot.employee.crud.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.springboot.employee.crud.entity.Employee;

// No @RestController — Spring Data REST exposes this repository at /api/members. See api_specification.md.
@RepositoryRestResource(path = "members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
