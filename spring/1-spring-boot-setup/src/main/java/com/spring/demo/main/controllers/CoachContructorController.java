package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

// Constructor injection (recommended) — dependencies are required, explicit, and the fields can stay final in real code.
@RestController
public class CoachContructorController {

    private Coach controlledCoach;
    private Coach anotherCoach;

    // First Coach param resolves to @Primary FootBallCoach; swimCoach comes from CoachConfig @Bean.
    @Autowired
    public CoachContructorController(Coach coach,
            @Qualifier("swimCoach") Coach anotherCoach) {
        this.controlledCoach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/coachConstructor")
    public String getDailyWorkout() {
        return controlledCoach.getDailyWorkout();
    }

    @GetMapping("/swimCoach")
    public String getSwimWorkout() {
        return anotherCoach.getDailyWorkout();
    }
}
