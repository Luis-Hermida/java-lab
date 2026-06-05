package com.lab.crud.demo.dao;

import java.util.List;

import com.lab.crud.demo.entity.Student;

// Persistence contract — implementation uses EntityManager; could later sit behind a REST controller.
public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    void delete(Integer id);

    int deleteAll();
}