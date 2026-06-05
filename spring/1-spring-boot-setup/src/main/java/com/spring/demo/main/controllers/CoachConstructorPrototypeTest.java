package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

// Prototype scope demo — GolfCoach creates a new object for each injection point in this constructor.
@RestController
public class CoachConstructorPrototypeTest {

    private Coach controlledCoach;
    private Coach anotherCoach;

    // Same @Qualifier twice, but prototype scope: two separate instances (== false). Compare with CoachConstructorSingletonTest.
    @Autowired
    public CoachConstructorPrototypeTest(
            @Qualifier("golfCoach") Coach coach,
            @Qualifier("golfCoach") Coach anotherCoach) {
        this.controlledCoach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/testPrototype")
    public String check() {
        return "Are the two coaches the same? " + (controlledCoach == anotherCoach);
    }
}
