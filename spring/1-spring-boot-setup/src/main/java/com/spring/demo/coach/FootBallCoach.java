package com.spring.demo.coach;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Primary
public class FootBallCoach implements Coach {

    public FootBallCoach() {
        System.out.println("Constructor info: " + getClass().getSimpleName());
    }

    // define initialization method
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("Inside method: doMyStartupStuff " + getClass().getSimpleName());
    }

    // define destroy method
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("Inside method: doMyCleanupStuff " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice football for 2 mins";
    }
}
