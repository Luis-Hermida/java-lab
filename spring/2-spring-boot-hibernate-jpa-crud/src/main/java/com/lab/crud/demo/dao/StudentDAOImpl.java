package com.lab.crud.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lab.crud.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // Define field for entity manager
    private EntityManager entityManager;

    // Set up constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Implement save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

}
