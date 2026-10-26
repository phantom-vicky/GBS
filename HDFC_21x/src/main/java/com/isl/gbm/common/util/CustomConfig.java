package com.isl.gbm.common.util;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

;

@Configuration
public class CustomConfig {

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private Integer databaseConnectionPoolCount;
    @Value("${encryption.key}")
    private String encryptionKey;

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster(@Qualifier("applicationTaskExecutor") TaskExecutor taskExecutor) {
        SimpleApplicationEventMulticaster eventMulticaster
                = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(taskExecutor);
        return eventMulticaster;
    }

    @Bean(name = "applicationTaskExecutor")
    public SimpleAsyncTaskExecutor appTaskExecutor() {
        SimpleAsyncTaskExecutor customExecutor = new SimpleAsyncTaskExecutor();

        //Set Concurrency Limit of AsyncExecutor.It expects atleast 3 connections
        if (databaseConnectionPoolCount < 3)
            throw new RuntimeException("INSUFFICIENT DATABASE CONNECTION POOL SIZE FOR ASYNC EXECUTOR");
        customExecutor.setConcurrencyLimit(databaseConnectionPoolCount - 1);

        return customExecutor;
    }


    @Bean(name = "islEncryptor")
    public PooledPBEStringEncryptor customEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(encryptionKey);
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
       // System.out.println(encryptor.decrypt("WwgxQ3kX7SSQLKF/2IySj81J9HSQpkD5"));
        //System.out.println("USERNAME ENCRYPTED USERNAME:"+encryptor.encrypt("RRBUSER"));
        //System.out.println("USERNAME ENCRYPTED PASSWORD:"+encryptor.encrypt("hdfcbank123$"));
        return encryptor;
    }
}
