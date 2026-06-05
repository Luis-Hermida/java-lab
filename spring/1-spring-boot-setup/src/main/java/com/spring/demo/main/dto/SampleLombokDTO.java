package com.spring.demo.main.dto;

import lombok.Data;

// @Data generates getters, setters, equals, hashCode, and toString at compile time — see GET / in HelloWorldController.
@Data
public class SampleLombokDTO {
    private String message;
}
