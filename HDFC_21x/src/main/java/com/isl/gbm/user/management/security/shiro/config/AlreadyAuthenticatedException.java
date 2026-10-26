package com.isl.gbm.user.management.security.shiro.config;

import org.apache.shiro.authc.AccountException;

public class AlreadyAuthenticatedException extends AccountException {
    public AlreadyAuthenticatedException() {
        super();
    }

    public AlreadyAuthenticatedException(String message) {
        super(message);
    }
}
