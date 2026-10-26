package com.isl.gbm.rbibond.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CsvValidationException extends RuntimeException {
    public CsvValidationException(String message) {
        super(message);
    }
}