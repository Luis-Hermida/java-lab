package com.spring.demo.coach;

import org.springframework.stereotype.Component;

// Standard singleton @Component — injected via setter in CoachSetterController (@Qualifier("basketBallCoach")).
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
