package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

@RestController
public class CoachFieldController {

    // define a private field for the dependency
    // field injection is not recommended, but it is still possible
    @Autowired
    @Qualifier("cricketCoach")
    private Coach controlledCoach;

    @GetMapping("/coachField")
    public String getDailyWorkout() {
        return controlledCoach.getDailyWorkout();
    }
}
