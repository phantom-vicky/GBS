package com.isl.gbm.rbibond.externalservice.connections;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesValues {
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.maximum-pool-size}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private int maximumTimedOut;


    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimedOut;


    public int getIdleTimedOut() {
        return idleTimedOut;
    }

    public void setIdleTimedOut(int idleTimedOut) {
        this.idleTimedOut = idleTimedOut;
    }

    public int getMaximumTimedOut() {
        return maximumTimedOut;
    }

    public void setMaximumTimedOut(int maximumTimedOut) {
        this.maximumTimedOut = maximumTimedOut;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }


    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
