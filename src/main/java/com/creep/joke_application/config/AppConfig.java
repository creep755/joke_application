package com.creep.joke_application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public java.util.Random random() {
        return new java.util.Random();
    }
}