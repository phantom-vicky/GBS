package com.isl.gbm.user.management.security.shiro.config;

import org.apache.shiro.authc.AccountException;

public class FinacleNotIntegratedException extends AccountException {
    public FinacleNotIntegratedException() {
        super();
    }

    public FinacleNotIntegratedException(String message) {
        super(message);
    }
}
