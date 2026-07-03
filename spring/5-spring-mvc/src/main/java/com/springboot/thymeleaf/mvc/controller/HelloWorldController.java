package com.springboot.thymeleaf.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
    // Controller method to show initial form
    @RequestMapping("/helloForm")
    public String showForm() {
        return "helloworld-form";
    }

    // Controller method to process HTML form
    @RequestMapping("/processForm")
    public String processForm() {
        return "helloworld-result";
    }
}
