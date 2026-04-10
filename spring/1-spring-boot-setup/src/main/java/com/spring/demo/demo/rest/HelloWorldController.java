package com.spring.demo.demo.rest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldController {

    // expose '/' that return "Hello World"
    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

}
