package com.isl.gbm.rbibond.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Configuration
public class RateLimitFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimitFilter.class);

    private final Map<String, RateLimitTracker> requestsPerIp = new ConcurrentHashMap<>();

    @Value("${rateLimit.enabled:true}")
    private boolean rateLimitEnabled;

    @Value("${rateLimit.timeWindow:PT10M}") // ISO-8601 duration (e.g. PT10M = 10 minutes)
    private Duration timeWindow;

    @Value("${rateLimit.maxRequestsPerWindow:2000}") // default 200 requests
    private int maxRequestsPerWindow;

    @PostConstruct
    public void logRateLimitConfig() {
        LOGGER.info("Rate limiting is {}.", rateLimitEnabled ? "ENABLED" : "DISABLED");
        LOGGER.info("Rate limiting time window: {} ms", timeWindow);
        LOGGER.info("Max requests per window: {}", maxRequestsPerWindow);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if (rateLimitEnabled) {
                String ip = getClientIP(request);

                RateLimitTracker tracker = requestsPerIp.computeIfAbsent(ip,
                        k -> new RateLimitTracker((int) timeWindow.toMillis(), maxRequestsPerWindow));

                if (tracker.isRateLimited()) {
                    LOGGER.warn("IP {} exceeded rate limit: {} requests in {} ms", ip, maxRequestsPerWindow, timeWindow);
                    response.sendError(429, "Too many requests");
                    return;
                }
            }
            // Set security headers BEFORE processing the request
            //response.setHeader("Content-Security-Policy", "default-src 'self'; script-src 'self'; frame-src 'self'; style-src 'self'; object-src 'none'");
            response.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload");
            response.setHeader("X-Content-Type-Options", "nosniff");
            response.setHeader("X-Frame-Options", "DENY");
            response.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            LOGGER.error("Exception during rate limiting: {}", e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    /**
     * Extracts client IP, considering common proxy headers.
     */
    private String getClientIP(HttpServletRequest request) {
        String header = request.getHeader("X-Forwarded-For");
        if (header != null && !header.isEmpty() && !"unknown".equalsIgnoreCase(header)) {
            return header.split(",")[0].trim(); // First IP is original
        }
        return request.getRemoteAddr();
    }

    /**
     * Simple timestamp-based rate limit tracker.
     */
    private static class RateLimitTracker {
        private final int timeWindowMillis;
        private final int maxRequests;
        private final Queue<Long> timestamps = new ConcurrentLinkedQueue<>();

        RateLimitTracker(int timeWindowMillis, int maxRequests) {
            this.timeWindowMillis = timeWindowMillis;
            this.maxRequests = maxRequests;
        }

        synchronized boolean isRateLimited() {
            long now = System.currentTimeMillis();
            cleanup(now);
            if (timestamps.size() >= maxRequests) {
                return true;
            }
            timestamps.offer(now);
            return false;
        }

        private void cleanup(long now) {
            while (!timestamps.isEmpty() && now - timestamps.peek() > timeWindowMillis) {
                timestamps.poll();
            }
        }
    }
}
