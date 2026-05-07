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
			createStudent(studentDAO);
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
}
