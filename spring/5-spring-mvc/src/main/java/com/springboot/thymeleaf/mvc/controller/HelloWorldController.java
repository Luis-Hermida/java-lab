package com.springboot.thymeleaf.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

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

    // Controller method to process HTML form
    @RequestMapping("/processFormAllCaps")
    public String processFormAllCaps(HttpServletRequest request, Model model) {
        // read te request parameter
        String name = request.getParameter("studentName");

        // convert data
        name = name.toUpperCase();

        // create message
        String message = "YOOO!!! " + name;

        // add message to model
        model.addAttribute("message", message);

        return "helloworld-result";
    }
}
