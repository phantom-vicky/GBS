package com.isl.gbm.rbibond.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

// @Configuration - DISABLED: Using Shiro for authentication instead of Spring Security
public class SecurityConfig {

    // @Bean - DISABLED: Using Shiro for authentication instead of Spring Security
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 🔒 Authorization rules
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/**").denyAll()
                .requestMatchers(HttpMethod.DELETE, "/**").denyAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").denyAll()
                .anyRequest().denyAll()
            )
            // 🔐 Basic Authentication
            .httpBasic(Customizer.withDefaults())
            // 📦 Session Management
            .sessionManagement(session -> session
                .sessionFixation().migrateSession()
            )
            // 🛡️ CSRF Protection
            .csrf(csrf -> csrf
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            )
            // 🧱 Security Headers
            .headers(headers -> headers
                // .xssProtection(xss -> xss.block(true)) // REMOVED: not supported in Spring Security 6
                .frameOptions(frame -> frame.deny())
                .httpStrictTransportSecurity(hsts -> hsts
                    .includeSubDomains(true)
                    .preload(true)
                    .maxAgeInSeconds(31536000)
                )
                .addHeaderWriter(new StaticHeadersWriter("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains"))
                .addHeaderWriter(new StaticHeadersWriter("X-Content-Type-Options", "nosniff"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST, GET"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
                .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "X-CSRF-TOKEN, X-Frame-Options"))
                .addHeaderWriter(new StaticHeadersWriter("X-Frame-Options", "DENY"))
                .addHeaderWriter(new StaticHeadersWriter("Referrer-Policy", "strict-origin-when-cross-origin"))
                .addHeaderWriter(new StaticHeadersWriter("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0"))
                .addHeaderWriter(new StaticHeadersWriter("Pragma", "no-cache"))
                .addHeaderWriter(new StaticHeadersWriter("Server", "SecureBankApp"))
                .addHeaderWriter(new StaticHeadersWriter("X-XSS-Protection", "1; mode=block"))
            );

        return http.build();
    }

    // @Bean - DISABLED: Using Shiro for authentication instead of Spring Security
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
            .ignoring()
            .requestMatchers("/api/login", "/api/logout", "/gbmultra/isac/api/**");
    }
}
