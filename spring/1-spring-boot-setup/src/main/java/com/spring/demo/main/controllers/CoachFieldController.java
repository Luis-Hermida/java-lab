package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

// Field injection demo — Spring sets the field directly. Harder to test and easy to hide required dependencies.
@RestController
public class CoachFieldController {

    // @Qualifier picks cricketCoach when many Coach beans exist — field @Autowired is discouraged in production code.
    @Autowired
    @Qualifier("cricketCoach")
    private Coach controlledCoach;

    @GetMapping("/coachField")
    public String getDailyWorkout() {
        return controlledCoach.getDailyWorkout();
    }
}
