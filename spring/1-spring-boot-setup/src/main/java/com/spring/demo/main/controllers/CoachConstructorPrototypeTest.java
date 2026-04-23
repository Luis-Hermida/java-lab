package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

@RestController
public class CoachConstructorPrototypeTest {

    // define a private field for the dependency
    private Coach controlledCoach;
    private Coach anotherCoach;

    /*
     * Define a constructor for dependency injection
     * using the same bean for both parameters, which is GolfCoach because of
     * 
     * @Qualifier
     * this will test if the same instance is injected for both parameters, which is
     * the case for prototype beans
     */
    @Autowired
    public CoachConstructorPrototypeTest(
            @Qualifier("golfCoach") Coach coach,
            @Qualifier("golfCoach") Coach anotherCoach) {
        this.controlledCoach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/testPrototype")
    public String check() {
        return "Are the two coaches the same? " + (controlledCoach == anotherCoach); // return false for prototype beans
    }
}
