package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

@RestController
public class CoachSetterController {

    // define a private field for the dependency
    private Coach controlledCoach;

    // define a setter method for dependency injection
    // we can use any name for the method, but it is a good practice to setCoach()
    // setCoach() is fine, but setCoachLaLaLaLa() is more fun :D
    @Autowired
    public void setCoachLaLaLaLa(@Qualifier("basketBallCoach") Coach coach) {
        this.controlledCoach = coach;
    }

    @GetMapping("/coachSetter")
    public String getDailyWorkout() {
        return controlledCoach.getDailyWorkout();
    }
}
