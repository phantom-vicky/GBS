package com.isl.gbm.security.config;


import com.isl.gbm.rbibond.security.CustomFilterEnc;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {



    //@Bean
    public FilterRegistrationBean<CustomFilterEnc> loggingFilter(){
        FilterRegistrationBean<CustomFilterEnc> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CustomFilterEnc());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }




}
