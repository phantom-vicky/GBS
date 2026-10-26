package com.isl.gbm.rbibond.externalservice.utility;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PanInputValidations {

    private static final Logger logger = LoggerFactory.getLogger(PanInputValidations.class);


    // Method to validate PAN, Name, and Date of Birth
    public static boolean validatePanValidationRequest(PanDetailsDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request object cannot be null.");
        }

        // Validate PAN
        if (!isValidPan(request.getPan())) {
            logger.info("Invalid PAN format.");
            return false;
        }

        // Validate Name
        if (isNullOrEmpty(request.getName())) {
            logger.info("Name cannot be null or empty.");
            return false;
        }

        // Validate Date of Birth (dob)
        if (!isValidDob(request.getDob())) {
            logger.info("Invalid Date of Birth format.");
            return false;
        }

        // If all validations pass, return true
        return true;
    }

    // Validate PAN format (example: AAAAA1234A)
    private static boolean isValidPan(String pan) {
        if (pan == null || pan.length() != 10) {
            return false;
        }

        String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pan);
        return matcher.matches();
    }

    // Validate Date of Birth (DOB) format (example: dd/MM/yyyy)
    private static boolean isValidDob(String dob) {
        if (dob == null || dob.length() != 10) {
            return false;
        }

        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(17|18|19|20|21)\\d\\d$";  // dd/MM/yyyy
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dob);
        return matcher.matches();
    }

    // Helper method to check if a string is null or empty
    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}