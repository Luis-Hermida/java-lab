package com.spring.demo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication = @Configuration + @EnableAutoConfiguration + @ComponentScan.
// scanBasePackages limits where Spring looks for @Component, @RestController, etc.
// Coaches live under com.spring.demo.coach; web layer under com.spring.demo.main.
@SpringBootApplication(scanBasePackages = {
		"com.spring.demo.coach",
		"com.spring.demo.main"
})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
