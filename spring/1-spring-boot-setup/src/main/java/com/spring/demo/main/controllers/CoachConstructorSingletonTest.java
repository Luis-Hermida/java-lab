package com.spring.demo.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.demo.coach.Coach;

@RestController
public class CoachConstructorSingletonTest {

    // define a private field for the dependency
    private Coach controlledCoach;
    private Coach anotherCoach;

    /*
     * Define a constructor for dependency injection
     * using the same bean for both parameters, which is CricketCoach because of
     * 
     * @Qualifier
     * this will test if the same instance is injected for both parameters, which is
     * the case for singleton beans
     */
    @Autowired
    public CoachConstructorSingletonTest(
            @Qualifier("cricketCoach") Coach coach,
            @Qualifier("cricketCoach") Coach anotherCoach) {
        this.controlledCoach = coach;
        this.anotherCoach = anotherCoach;
    }

    @GetMapping("/testSingleton")
    public String check() {
        return "Are the two coaches the same? " + (controlledCoach == anotherCoach); // return true for singleton beans
    }
}
