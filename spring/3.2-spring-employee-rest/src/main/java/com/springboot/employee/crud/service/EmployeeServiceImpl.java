package com.springboot.employee.crud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.employee.crud.dao.EmployeeDAO;
import com.springboot.employee.crud.entity.Employee;

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

}
