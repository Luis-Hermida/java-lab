package com.springboot.employee.crud.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.crud.entity.Employee;
import com.springboot.employee.crud.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeControler {

    private EmployeeService employeeService;

    public EmployeeControler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

}
