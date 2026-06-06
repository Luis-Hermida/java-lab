package com.sample.rest.java.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.rest.java.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for "/students" - return a list of students

    @GetMapping("/students")
    public List<Student> getStudents() {

        List<Student> theStudents = new ArrayList<>();

        theStudents.add(new Student("Luiss", "Hormiga"));
        theStudents.add(new Student("Caro", "Lina"));
        theStudents.add(new Student("Nami", "Kezumi"));

        return theStudents;
    }

}
