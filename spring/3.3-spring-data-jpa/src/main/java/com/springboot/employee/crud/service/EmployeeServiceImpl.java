package com.springboot.employee.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springboot.employee.crud.dao.EmployeeRepository;
import com.springboot.employee.crud.entity.Employee;

import jakarta.transaction.Transactional;

// Service layer — delegates to Spring Data JpaRepository instead of manual DAO (contrast module 3.2).
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int Id) {
        Optional<Employee> result = employeeRepository.findById(Id);

        Employee employee = null;

        if (result.isPresent()) {
            employee = result.get();
        } else {
            throw new RuntimeException("Did not find employee id - " + Id);
        }

        return employee;
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee); // Spring Data implements persist/merge for you.
    }

    @Transactional
    @Override
    public void deleteById(int Id) {
        employeeRepository.deleteById(Id);
    }

}
