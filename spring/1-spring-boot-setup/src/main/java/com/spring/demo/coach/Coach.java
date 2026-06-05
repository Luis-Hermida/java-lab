package com.spring.demo.coach;

// Shared contract — several implementations exist as Spring beans, so injection sites need @Qualifier or @Primary.
public interface Coach {

    String getDailyWorkout();
}
