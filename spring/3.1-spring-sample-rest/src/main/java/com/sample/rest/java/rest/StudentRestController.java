package com.sample.rest.java.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.rest.java.entity.Student;

import jakarta.annotation.PostConstruct;

// In-memory student API — no database; data lives in a List seeded at startup.
@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    // Runs after dependency injection — fills theStudents before any HTTP request.
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Luiss", "Hormiga"));
        theStudents.add(new Student("Caro", "Lina"));
        theStudents.add(new Student("Nami", "Kezumi"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        // studentId is a list index (0, 1, 2), not a database primary key — see api_specification.md.
        if ((studentId >= theStudents.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    // Per-controller @ExceptionHandler alternative — commented out; global handler in StudentRestExceptionHandler.
    // public ResponseEntity<StudentErrorResponse>
    // handleException(StudentNotFoundException exc) {
    // StudentErrorResponse error = new StudentErrorResponse();
    // error.setStatus(HttpStatus.NOT_FOUND.value());
    // error.setMessage(exc.getMessage());
    // error.setTimeStamp(System.currentTimeMillis());

    // return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    // }

    // @ExceptionHandler
    // public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
    // StudentErrorResponse error = new StudentErrorResponse();
    // error.setStatus(HttpStatus.BAD_REQUEST.value());
    // error.setMessage(exc.getMessage());
    // error.setTimeStamp(System.currentTimeMillis());

    // return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    // }
}
