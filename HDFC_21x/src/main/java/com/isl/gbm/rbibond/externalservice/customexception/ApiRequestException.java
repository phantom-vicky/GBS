package com.isl.gbm.rbibond.externalservice.customexception;

public class ApiRequestException  extends RuntimeException{

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
