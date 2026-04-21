package com.spring.demo.coach;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FootBallCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice football for 2 mins";
    }
}
