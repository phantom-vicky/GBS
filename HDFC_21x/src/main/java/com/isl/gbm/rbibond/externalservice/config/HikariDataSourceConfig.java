package com.isl.gbm.rbibond.externalservice.config;

import com.isl.gbm.rbibond.externalservice.connections.PropertiesValues;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

@Configuration
public class HikariDataSourceConfig {

    private final PropertiesValues propertiesValue;

    @Autowired
    public HikariDataSourceConfig(PropertiesValues propertiesValue) {
        this.propertiesValue = propertiesValue;
    }

    @Bean
    public HikariDataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(propertiesValue.getUrl());
        dataSource.setUsername(propertiesValue.getUserName());
        dataSource.setPassword(propertiesValue.getPassword());
        dataSource.setDriverClassName(propertiesValue.getDriverClass());
        dataSource.setMaximumPoolSize(propertiesValue.getMaximumPoolSize());
        dataSource.setConnectionTimeout(propertiesValue.getMaximumTimedOut());


        // Pool configuration
        dataSource.setMinimumIdle(10); // Usually between 5-10
        dataSource.setIdleTimeout(propertiesValue.getIdleTimedOut()); // 10 minutes in ms
        dataSource.setMaxLifetime(1800000); // 30 minutes

        // Optional for validation
        dataSource.setValidationTimeout(5000); // 5 seconds
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // Optional: Default configurations
        jdbcTemplate.setQueryTimeout(1200); // 20 mins default query timeout
        jdbcTemplate.setExceptionTranslator(new SQLErrorCodeSQLExceptionTranslator());
        return jdbcTemplate;
    }

}
