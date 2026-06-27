package com.luv2code.springboot.cruddemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

        @Bean
        public UserDetailsManager userDetailsManager(DataSource dataSource) {
                return new JdbcUserDetailsManager(dataSource);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

                httpSecurity.authorizeHttpRequests(configurer -> configurer
                                .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("MANAGER")
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
