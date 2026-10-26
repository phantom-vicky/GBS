package com.isl.gbm.rbibond.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Component
public class FileSizeValidationService {

    // Define a default maximum size of 1MB if not provided in the configuration
    @Value("${file.maxSize:1MB}")
    private String maxSizeConfig;

    private static final Logger log = LoggerFactory.getLogger(FileSizeValidationService.class);

    public boolean validateFileSizeMultiPart(MultipartFile[] files) {
        // Convert the configured max size to bytes (default to 1MB if not configured)
        long maxSize = convertSizeToBytes(maxSizeConfig);

        for (MultipartFile file : files) {
            // Check if the file is empty
            if (file.isEmpty()) {
                logEmptyFileError(file);
                return true;
            }

            // Validate the file size
            if (file.getSize() > maxSize) {
                logEmptyFileError(file);
                return true;
            }
        }
        return false;
    }

    public boolean validateFileSize(File[] files) {
        // Convert the configured max size to bytes (default to 1MB if not configured)
        long maxSize = convertSizeToBytes(maxSizeConfig);

        for (File file : files) {
            // Check if the file exists and is not a directory
            if (!file.exists() || file.isDirectory()) {
                logEmptyFileError(file);
                return true;
            }

            // Validate the file size
            if (file.length() > maxSize) {
                logEmptyFileError(file);
                return true;
            }
        }
        return false;
    }

    private long convertSizeToBytes(String sizeConfig) {
        // Default to 1MB if the sizeConfig is not specified
        if (sizeConfig == null) {
            sizeConfig = "1MB";
        }

        // Parse the sizeConfig value (e.g., 1MB or 500KB)
        if (sizeConfig.endsWith("MB")) {
            return Long.parseLong(sizeConfig.replace("MB", "")) * 1024 * 1024; // Convert MB to bytes
        } else if (sizeConfig.endsWith("KB")) {
            return Long.parseLong(sizeConfig.replace("KB", "")) * 1024; // Convert KB to bytes
        } else {
            // Default to 1MB if no valid unit is found
            return 1 * 1024 * 1024;
        }
    }

    public static void logEmptyFileError(File file) {
        if (file == null) {
            log.error("The file is empty. File name: {}", file.getName());
        }
    }

    public static void logEmptyFileError(MultipartFile file) {
        if (file == null || file.getSize() == 0) {
            log.error("File size exceeds the maximum allowed size of 1MB: {}", file.getName());
        }
    }
}

