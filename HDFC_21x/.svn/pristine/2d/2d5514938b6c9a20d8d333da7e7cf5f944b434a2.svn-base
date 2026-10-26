package com.isl.gbm.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, @Qualifier("customCsrfTokenRepository") CsrfTokenRepository csrfTokenRepository) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/**").denyAll()
                        .requestMatchers(HttpMethod.DELETE, "/**").denyAll()
                        .requestMatchers(HttpMethod.OPTIONS, "/**").denyAll()
                        .requestMatchers(HttpMethod.TRACE, "/**").denyAll()
                        .anyRequest().denyAll()
                )
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionFixation().migrateSession()
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .csrf(csrf -> csrf.disable())
                /*.csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )*/
                .headers(headers -> headers
                       .frameOptions(frame -> frame.deny())
                        .httpStrictTransportSecurity(hsts -> hsts
                                .includeSubDomains(true)
                                .preload(true)
                                .maxAgeInSeconds(31536000)
                        )
                        .addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy", "default-src * 'unsafe-inline' 'unsafe-eval' data: blob:;"))
                        .addHeaderWriter(new StaticHeadersWriter("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains ; preload"))
                        .addHeaderWriter(new StaticHeadersWriter("X-Content-Type-Options", "nosniff"))
                        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Methods", "POST, GET"))
                        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Max-Age", "3600"))
                        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Credentials", "true"))
                        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Allow-Headers", "X-CSRF-TOKEN, X-Frame-Options"))
                        .addHeaderWriter(new StaticHeadersWriter("Access-Control-Expose-Headers", "X-CSRF-TOKEN, X-Frame-Options"))
                        .addHeaderWriter(new StaticHeadersWriter("X-Frame-Options", "DENY"))
                        .addHeaderWriter(new StaticHeadersWriter("Referrer-Policy", "strict-origin-when-cross-origin"))
                        .addHeaderWriter(new StaticHeadersWriter("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0"))
                        .addHeaderWriter(new StaticHeadersWriter("Pragma", "no-cache"))
                        .addHeaderWriter(new StaticHeadersWriter("Server", "SecureBankApp"))
                        .addHeaderWriter(new StaticHeadersWriter("X-XSS-Protection", "1; mode=block"))
                );

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web
                .ignoring()
                .requestMatchers("/api/login", "/api/logout", "/gbmultra/isac/api/**", "/gbmultra/user/login");
    }
}

