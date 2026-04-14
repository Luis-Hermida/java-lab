package com.spring.demo.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.demo.playground.LombokData;

@RestController
public class HelloWorldController {

    // expose '/' that return "Hello World"
    @GetMapping("/")
    public String hello() {
        LombokData lombok = new LombokData();
        lombok.setMessage("Lombok is working!!!!");
        return lombok.getMessage();
    }

}