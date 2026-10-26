package com.isl.gbm.common.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PropertiesValue {


    //@Value("${server.ssl.key-store-password}")
    private String keyStorePassword;

    //  @Value("${server.ssl.key-store}")
    private String keyStore;

    public String getKeyStorePassword() {
        return keyStorePassword;
    }

    public void setKeyStorePassword(String keyStorePassword) {
        this.keyStorePassword = keyStorePassword;
    }

    public String getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(String keyStore) {
        this.keyStore = keyStore;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;

    public String getAccountValidationServiceURL() {
        return accountValidationServiceURL;
    }

    public void setAccountValidationServiceURL(String accountValidationServiceURL) {
        this.accountValidationServiceURL = accountValidationServiceURL;
    }

    @Value("account.validations.service.url")
    private String accountValidationServiceURL;
}





