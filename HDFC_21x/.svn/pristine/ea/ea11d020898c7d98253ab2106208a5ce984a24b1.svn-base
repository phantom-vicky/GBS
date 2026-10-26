package com.isl.gbm.rbibond.externalservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper(); // You can customize the ObjectMapper here if needed
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
