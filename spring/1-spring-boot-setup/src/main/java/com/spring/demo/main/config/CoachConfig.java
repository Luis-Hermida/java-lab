package com.spring.demo.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spring.demo.coach.Coach;
import com.spring.demo.coach.SwimCoach;

@Configuration
public class CoachConfig {

    @Bean
    public Coach swimCoach() {
        return new SwimCoach();
    }
}