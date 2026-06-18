package com.springboot.employee.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.employee.crud.dao.EmployeeDAO;
import com.springboot.employee.crud.entity.Employee;

import jakarta.transaction.Transactional;

// Service layer — delegates to manual EmployeeDAO (EntityManager), not JpaRepository.
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int Id) {
        return employeeDAO.findById(Id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee); // DAO uses merge — INSERT or UPDATE depending on id.
    }

    @Transactional
    @Override
    public void deleteById(int Id) {
        employeeDAO.deleteById(Id);
    }

}
