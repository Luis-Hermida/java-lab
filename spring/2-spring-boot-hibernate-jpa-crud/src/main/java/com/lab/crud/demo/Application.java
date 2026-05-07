package com.lab.crud.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lab.crud.demo.dao.StudentDAO;
import com.lab.crud.demo.entity.Student;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return args -> {
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			findStudentById(studentDAO, 7);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");

		// Create the student object
		Student tempStudent = new Student("Paul", "Wall", "paul.wall@example.com");

		// Save the student object
		studentDAO.save(tempStudent);

		// Display the saved student object
		System.out.println("Saved student: " + tempStudent + " with id: " + tempStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 new student objects...");

		// Create the student objects
		Student tempStudent1 = new Student("John", "Doe", "john.doe@example.com");
		Student tempStudent2 = new Student("Jane", "Smith", "jane.smith@example.com");
		Student tempStudent3 = new Student("Bob", "Johnson", "bob.johnson@example.com");

		// Save the student objects
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		// Display the saved student objects
		System.out.println("Saved student: " + tempStudent1 + " with id: " + tempStudent1.getId());
		System.out.println("Saved student: " + tempStudent2 + " with id: " + tempStudent2.getId());
		System.out.println("Saved student: " + tempStudent3 + " with id: " + tempStudent3.getId());
	}

	private void findStudentById(StudentDAO studentDAO, Integer id) {
		Student tempStudent = studentDAO.findById(id);

		if (tempStudent == null) {
			System.out.println("No student found with id: " + id);
		} else {
			System.out.println("Found student: " + tempStudent);
		}
	}
}