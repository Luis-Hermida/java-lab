package com.spring.demo.coach;

public class SwimCoach implements Coach {

    public SwimCoach() {
        System.out.println("Constructor info: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice swimming for 212 hours";
    }
}
