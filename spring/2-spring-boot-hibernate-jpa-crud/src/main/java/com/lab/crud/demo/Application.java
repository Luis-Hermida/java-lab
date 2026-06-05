package com.lab.crud.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lab.crud.demo.dao.StudentDAO;
import com.lab.crud.demo.entity.Student;

// JPA CRUD demos — no REST layer; persistence runs via CommandLineRunner after the context starts.
// See api_specification.md for each DAO operation; uncomment one demo at a time below.
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// Runs once at startup — StudentDAO is injected like any other Spring bean.
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return args -> {
			// createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			// findStudentById(studentDAO, 7);
			// queryForAllStudents(studentDAO);
			// queryFindStudentsByLastName(studentDAO, "Doe");
			// updateStudent(studentDAO, 1, "Pepe");
			// deleteStudent(studentDAO, 1);
			// deleteAllStudents(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object...");

		Student tempStudent = new Student("Paul", "Wall", "paul.wall@example.com");

		studentDAO.save(tempStudent);

		// After persist + flush, Hibernate fills id from the database identity column.
		System.out.println("Saved student: " + tempStudent + " with id: " + tempStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 new student objects...");

		Student tempStudent1 = new Student("John", "Doe", "john.doe@example.com");
		Student tempStudent2 = new Student("Jane", "Smith", "jane.smith@example.com");
		Student tempStudent3 = new Student("Bob", "Johnson", "bob.johnson@example.com");

		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

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

	private void queryForAllStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();

		for (Student tempStudent : students) {
			System.out.println(tempStudent);
		}
	}

	private void queryFindStudentsByLastName(StudentDAO studentDAO, String lastName) {
		List<Student> students = studentDAO.findByLastName(lastName);

		for (Student tempStudent : students) {
			System.out.println(tempStudent);
		}
	}

	private void updateStudent(StudentDAO studentDAO, Integer id, String updatedFirstName) {
		Student tempStudent = studentDAO.findById(id);

		tempStudent.setFirstName(updatedFirstName);

		// Typical update flow: load managed/detached entity, mutate, then merge back to the DB.
		studentDAO.update(tempStudent);

		System.out.println(tempStudent);
	}

	private void deleteStudent(StudentDAO studentDAO, Integer id) {
		studentDAO.delete(id);
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int rowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted " + rowsDeleted + " rows from student table");
	}
}
