package com.spring.demo.coach;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
@Primary // Default Coach when a constructor asks for Coach without @Qualifier — see CoachContructorController.
public class FootBallCoach implements Coach {

    public FootBallCoach() {
        System.out.println("Constructor info: " + getClass().getSimpleName());
    }

    // Runs after dependency injection completes — good for one-time setup (not a replacement for a real init API).
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("Inside method: doMyStartupStuff " + getClass().getSimpleName());
    }

    // Runs before the bean is removed from the context on shutdown.
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("Inside method: doMyCleanupStuff " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice football for 2 mins";
    }
}
