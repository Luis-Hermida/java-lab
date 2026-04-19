package com.spring.demo.demo.rest.coach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoachController {

    // define a private field for the dependency
    private Coach controlledCoach;

    // define a constructor for dependency injection
    @Autowired
    public CoachController(Coach coach) {
        this.controlledCoach = coach;
    }

    @GetMapping("/coach")
    public String getDailyWorkout() {
        return controlledCoach.getDailyWorkout();
    }
}
