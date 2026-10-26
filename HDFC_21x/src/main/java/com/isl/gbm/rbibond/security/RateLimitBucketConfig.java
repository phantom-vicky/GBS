package com.isl.gbm.rbibond.security;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitBucketConfig {


    @Value("${rateLimit.timeWindow:PT10M}") // ISO-8601 duration (e.g. PT10M = 10 minutes)
    private Duration timeWindow;

    @Value("${rateLimit.maxRequestsPerWindow}")
    private int maxRequestsPerWindow;
    /**
     * Configures a Bucket4j bucket with a capacity of 100 tokens that refills at a rate of 100 tokens per minute.
     * Each token represents the capacity to handle one request.
     */
    @Bean
    public Bucket bucket() {
        // Use the configured Duration directly
        Bandwidth limit = Bandwidth.classic(maxRequestsPerWindow, Refill.greedy(maxRequestsPerWindow, timeWindow));
        return Bucket4j.builder().addLimit(limit).build();
    }
}
