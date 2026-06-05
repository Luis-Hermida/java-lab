package com.spring.demo.coach;

// No @Component — Spring only knows this class because CoachConfig declares a @Bean swimCoach().
public class SwimCoach implements Coach {

    public SwimCoach() {
        System.out.println("Constructor info: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice swimming for 212 hours";
    }
}
