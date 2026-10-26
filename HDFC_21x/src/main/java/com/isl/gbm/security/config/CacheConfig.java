package com.isl.gbm.security.config;


import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    @Override
    public CacheManager cacheManager() {
        return new GuavaCacheManager();
    }


    private static class GuavaCacheManager implements CacheManager {
        @Override
        public Cache getCache(String s) {
            return (Cache) CacheBuilder.newBuilder()
                    .expireAfterWrite(1, TimeUnit.MINUTES) // Adjust as needed
                    .build();
        }

        @Override
        public Collection<String> getCacheNames() {
            return null;
        }

    }



}
