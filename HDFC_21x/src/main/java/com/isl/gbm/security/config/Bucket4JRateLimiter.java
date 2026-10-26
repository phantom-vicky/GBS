package com.isl.gbm.security.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.github.bucket4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
public class Bucket4JRateLimiter {
    private static final Logger log = LoggerFactory.getLogger(Bucket4JRateLimiter.class);

    //    private final ConcurrentMap<String, Bucket> buckets;
    private final Cache<String, Bucket> buckets;

//
//    @Value("${gbm.rate.limiter.buckets}")
//    private String bucketDetails;


    public Bucket4JRateLimiter(@Value("${gbm.rate.limiter.buckets}") String bucketDetails) {
//        buckets = new ConcurrentHashMap<>();
        this.buckets = CacheBuilder.newBuilder()
                .expireAfterWrite(Long.MAX_VALUE, TimeUnit.DAYS) // Adjust the time window as needed
                .build();

        this.createBucketWithDetails(bucketDetails);
//
    }


    public boolean isAllowed(String bucketId) {
        log.info("Bucket request for BucketID: " + bucketId);
        Bucket buc = buckets.asMap().get(bucketId);
        ConsumptionProbe probe = buc.tryConsumeAndReturnRemaining(1);
        boolean result =  probe.isConsumed();
        log.info("Bucket response for BucketId: " + bucketId + " Result: " + (result ? "Bucket Quota allowed" : "Bucket Quota Limit Reached"));
        return result;
    }

    private Bucket createBucket(int totalPermits, int durationPerMinutes) {

        Bandwidth limit = Bandwidth.classic(totalPermits, Refill.intervally(totalPermits, Duration.ofMinutes(durationPerMinutes))).withInitialTokens(totalPermits);
        Bucket bucket = Bucket4j.builder().addLimit(limit).build();
        return bucket;
    }


    public void createBucketWithDetails(String bucketDetails) {
    try {
        String[] singleBucket = bucketDetails.split(",");
        for (String i : singleBucket) {
            String[] bbss = i.split(":");
            String bucketId = bbss[0];
            int permits = Integer.parseInt(bbss[1]);
            int perMinute = Integer.parseInt(bbss[2]);

            log.info("bucketId : " + bucketId);
            log.info("permits : " + permits);
            log.info("minute : " + perMinute);

            // Create the bucket with 3 requests per 1 minute
            Duration refillDuration = Duration.ofMinutes(perMinute);
            Bandwidth limit = Bandwidth.classic(permits, Refill.intervally(permits, refillDuration));

            // Store the bucket in the map
            buckets.put(bucketId, Bucket4j.builder().addLimit(limit).build());
        }
    } catch (Exception e) {
        throw new RuntimeException("gbm.rate.limiter.buckets is not correct value", e);
    }
}



}
