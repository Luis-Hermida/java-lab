package com.spring.demo.main.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.main.dto.SampleLombokDTO;

@RestController
public class HelloWorldController {
    @Value("${dog.name}")
    private String dogName;

    @Value("${cat.name}")
    private String catName;

    // expose '/' that return "Hello World"
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