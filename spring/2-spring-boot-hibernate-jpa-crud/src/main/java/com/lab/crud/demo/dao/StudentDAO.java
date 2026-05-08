package com.lab.crud.demo.dao;

import java.util.List;

import com.lab.crud.demo.entity.Student;

public interface StudentDAO {
    void save(Student student);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);
}