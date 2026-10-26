package com.isl.gbm.rbibond.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitingSecurityConfig {

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimiterFilter(RateLimitFilter filter) {
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/gbmultra/*", "/gbsultra/*","/rbiBond/*","/external-service/*");
        return registrationBean;
    }
}

