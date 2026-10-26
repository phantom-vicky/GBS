package com.isl.gbm.user.management.security.shiro.config;

import org.apache.shiro.authc.AccountException;

public class AccountInActiveException extends AccountException {


    public AccountInActiveException() {
        super();
    }

    public AccountInActiveException(String message) {
        super(message);
    }


}
