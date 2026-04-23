package com.spring.demo.coach;

import org.springframework.stereotype.Component;

@Component
public class BasketBallCoach implements Coach {

    public BasketBallCoach() {
        System.out.println("Constructor info: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice basketball for 123232312131!!!! hours";
    }
}
