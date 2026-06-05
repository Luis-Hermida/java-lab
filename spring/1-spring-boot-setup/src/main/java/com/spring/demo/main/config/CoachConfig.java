package com.spring.demo.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.demo.coach.Coach;
import com.spring.demo.coach.SwimCoach;

// Java-based bean registration — use when you need factory logic or a class you cannot annotate with @Component.
@Configuration
public class CoachConfig {

    // Method name becomes the bean id: @Qualifier("swimCoach") matches this @Bean method.
    @Bean
    public Coach swimCoach() {
        return new SwimCoach();
    }
}