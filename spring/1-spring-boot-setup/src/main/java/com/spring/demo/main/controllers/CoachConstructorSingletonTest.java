package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

// Singleton scope demo — default for @Component beans unless @Scope says otherwise.
@RestController
public class CoachConstructorSingletonTest {

    private Coach controlledCoach;
    private Coach anotherCoach;

    // Same @Qualifier twice: Spring reuses the one cricketCoach instance (controlledCoach == anotherCoach → true).
    @Autowired
    public CoachConstructorSingletonTest(
            @Qualifier("cricketCoach") Coach coach,
            @Qualifier("cricketCoach") Coach anotherCoach) {
        this.controlledCoach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/testSingleton")
    public String check() {
        return "Are the two coaches the same? " + (controlledCoach == anotherCoach);
    }
}
