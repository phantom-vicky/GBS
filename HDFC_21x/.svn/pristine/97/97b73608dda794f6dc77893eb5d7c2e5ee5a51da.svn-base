package com.isl.gbm.rbibond.security;

import io.github.bucket4j.Bucket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.annotation.PostConstruct;
import java.io.IOException;

//@Component
public class RateLimitingFilterBucket implements Filter {

    private final Bucket bucket;

    private static final Logger logger = LoggerFactory.getLogger(RateLimitingFilterBucket.class);

    @Value("${rateLimitEnabled}")
    private boolean rateLimitEnabled;


    @Autowired
    public RateLimitingFilterBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    @PostConstruct
    public void logRateLimitConfig() {
        logger.info("Rate limiting is {}.", rateLimitEnabled ? "ENABLED" : "DISABLED");
    }

    /**
     * Intercepts incoming requests and applies rate limiting.
     * Adds security headers and logs rate-limiting events.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;

        // Set security headers BEFORE processing the request
        httpResp.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");
        httpResp.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
        httpResp.setHeader("X-Content-Type-Options", "nosniff");
        httpResp.setHeader("X-Frame-Options", "DENY");
        httpResp.setHeader("X-XSS-Protection", "1; mode=block");
        String requestURI = httpReq.getRequestURI();
        try {
            if (rateLimitEnabled) {
                if (bucket.tryConsume(1)) {
                    logger.debug("Request allowed: [{} {}]", httpReq.getMethod(), requestURI);
                    chain.doFilter(request, response);
                } else {
                    httpResp.setStatus(429); // Too Many Requests
                    logger.warn("Rate limit exceeded for request: [{} {}]", httpReq.getMethod(), requestURI);
                }
            }
        } catch (Exception e) {
            logger.error("Exception during rate limiting: {}", e.getMessage(), e);
            httpResp.sendError(jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("RateLimitingFilterBucket initialized");
    }

    @Override
    public void destroy() {
        logger.info("RateLimitingFilterBucket destroyed");
    }

}
