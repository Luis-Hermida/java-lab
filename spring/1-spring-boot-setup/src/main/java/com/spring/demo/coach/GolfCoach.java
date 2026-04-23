package com.spring.demo.coach;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Lazy
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GolfCoach implements Coach {

    public GolfCoach() {
        System.out.println("Constructor info: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice golf for 2 hours";
    }
}
