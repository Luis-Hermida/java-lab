package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

@RestController
public class CoachContructorController {

    // define a private field for the dependency
    private Coach controlledCoach;

    // define a constructor for dependency injection
    // using default Coach bean, which is FootBallCoach because of @Primary
    @Autowired
    public CoachContructorController(Coach coach) {
        this.controlledCoach = coach;
    }

    @GetMapping("/coachConstructor")
    public String getDailyWorkout() {
        return controlledCoach.getDailyWorkout();
    }
}
