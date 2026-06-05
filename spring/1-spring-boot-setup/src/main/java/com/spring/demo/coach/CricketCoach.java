package com.spring.demo.coach;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy // Bean is not created until first needed — with spring.main.lazy-initialization=true, watch when the constructor logs.
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("Constructor info: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice cricket for 3 >:( hours";
    }
}
