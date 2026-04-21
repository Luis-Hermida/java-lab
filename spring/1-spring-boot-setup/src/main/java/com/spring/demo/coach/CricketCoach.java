package com.spring.demo.coach;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice cricket for 2 hours";
    }
}
