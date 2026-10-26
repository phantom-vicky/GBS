package com.isl.gbm.rbibond.externalservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestTemplateConfig {

    @Bean(name = "webClient")
    public WebClient webClient() {
        return WebClient.builder().build();
    }

}
