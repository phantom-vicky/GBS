package com.isl.gbm.rbibond.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for file-related operations.
 */
@ControllerAdvice
public class BondExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BondExceptionHandler.class);

    @ExceptionHandler(FileDownloadException.class)
    public ResponseEntity<String> handleFileDownloadException(FileDownloadException ex) {
        LOGGER.error("File download failed: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error downloading file: " + ex.getMessage());
    }

}

