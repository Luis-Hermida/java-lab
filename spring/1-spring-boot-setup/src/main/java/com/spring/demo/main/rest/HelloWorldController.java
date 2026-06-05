package com.spring.demo.main.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.main.dto.SampleLombokDTO;

// Basic REST + externalized config — endpoints and property keys are documented in api_specification.md.
@RestController
public class HelloWorldController {

    // Values come from application.properties — change dog.name / cat.name and restart to see GET /custom update.
    @Value("${dog.name}")
    private String dogName;

    @Value("${cat.name}")
    private String catName;

    // Demonstrates Lombok @Data on SampleLombokDTO rather than hand-written getters/setters.
    @GetMapping("/")
    public String hello() {
        SampleLombokDTO lombok = new SampleLombokDTO();
        lombok.setMessage("Lombok is working!!!!");
        return lombok.getMessage();
    }

    @GetMapping("/custom")
    public String custom() {
        return "Dog name: " + dogName + " Cat name: " + catName;
    }

}