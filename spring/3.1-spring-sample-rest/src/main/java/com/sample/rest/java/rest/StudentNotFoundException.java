package com.sample.rest.java.rest;

// Thrown when studentId is out of range — mapped to HTTP 404 by @ControllerAdvice.
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
