package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

        @Bean
        public InMemoryUserDetailsManager userDetailsManager() {
                UserDetails james = User.builder()
                                .username("james")
                                .password("{noop}test123")
                                .roles("EMPLOYEE")
                                .build();
                UserDetails mary = User.builder()
                                .username("mary")
                                .password("{noop}test123")
                                .roles("EMPLOYEE", "MANAGER")
                                .build();
                UserDetails maria = User.builder()
                                .username("maria")
                                .password("{noop}test123")
                                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                                .build();

                return new InMemoryUserDetailsManager(james, mary, maria);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

                httpSecurity.authorizeHttpRequests(configurer -> configurer
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));

                // Use HTTP basic configuration
                httpSecurity.httpBasic(Customizer.withDefaults());

                // Disable Cross Site Request Forgery (CSRF)
                // Not required for stateless REST API's
                // -- Used for HTML applications
                httpSecurity.csrf(csrf -> csrf.disable());

                // Don't use sessions, each request makes their own
                // httpSecurity.sessionManagement(
                // session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                return httpSecurity.build();
        }
}
