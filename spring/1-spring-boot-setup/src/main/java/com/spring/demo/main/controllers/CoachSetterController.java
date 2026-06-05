package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

// Setter injection — Spring calls an @Autowired method after the object is constructed.
@RestController
public class CoachSetterController {

    private Coach controlledCoach;

    // Any single-argument method can be the injection point; basketBallCoach matches @Component default bean name.
    @Autowired
    public void setCoachLaLaLaLa(@Qualifier("basketBallCoach") Coach coach) {
        this.controlledCoach = coach;
    }

    @GetMapping("/coachSetter")
    public String getDailyWorkout() {
        return controlledCoach.getDailyWorkout();
    }
}
