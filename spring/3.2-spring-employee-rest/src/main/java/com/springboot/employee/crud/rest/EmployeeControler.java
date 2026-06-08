package com.springboot.employee.crud.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.crud.dao.EmployeeDAO;
import com.springboot.employee.crud.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeControler {

    private EmployeeDAO employeeDAO;

    public EmployeeControler(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("employees")
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

}
