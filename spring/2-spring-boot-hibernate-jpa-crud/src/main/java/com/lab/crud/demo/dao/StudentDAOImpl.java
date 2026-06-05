package com.lab.crud.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lab.crud.demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

// Custom DAO on EntityManager — alternative to Spring Data JpaRepository for learning JPA internals.
@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager entityManager;

    // Spring Boot auto-configures EntityManager for JPA — injected via constructor.
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional // Write ops need a transaction; persist flushes at commit.
    public void save(Student student) {
        entityManager.persist(student); // INSERT for a new entity; id is still 0 until flush.
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id); // null if no row — callers should null-check.
    }

    @Override
    public List<Student> findAll() {
        // JPQL entity name is Student (class), not student (table).
        TypedQuery<Student> query = entityManager.createQuery("FROM Student order by lastName asc", Student.class);

        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData",
                Student.class);

        query.setParameter("theData", lastName); // Named params — safer than string concatenation.

        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student); // Syncs changes to the database (UPDATE for existing rows).
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = entityManager.find(Student.class, id);

        // remove() requires a managed entity — load first, then delete.
        if (student != null) {
            entityManager.remove(student);
        } else {
            System.out.println("Entity with ID: " + id + " don't exist.");
        }
    }

    @Override
    @Transactional
    public int deleteAll() {
        // Bulk JPQL DELETE — bypasses per-entity remove(); returns affected row count.
        int rowsDeleted = entityManager.createQuery("DELETE from Student").executeUpdate();
        return rowsDeleted;
    }
}
