package com.springboot.employee.crud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springboot.employee.crud.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

// Manual JPA DAO — same pattern as module 2 student DAO, applied to Employee.
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);

        List<Employee> employees = theQuery.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int Id) {
        return entityManager.find(Employee.class, Id);
    }

    @Override
    public Employee save(Employee employee) {
        return entityManager.merge(employee); // merge handles both new and existing rows.
    }

    @Override
    public void deleteById(int Id) {
        Employee employee = entityManager.find(Employee.class, Id);
        entityManager.remove(employee); // NPE if id missing — see api_specification.md caveat.
    }

}
