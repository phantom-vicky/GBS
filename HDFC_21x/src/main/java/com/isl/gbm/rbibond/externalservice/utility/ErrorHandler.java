package com.isl.gbm.rbibond.externalservice.utility;

import java.util.HashMap;
import java.util.Map;

public class ErrorHandler {
    // Static map to store error codes and messages
    private static final Map<Integer, String> ERROR_CODES = new HashMap<>();

    static {
        ERROR_CODES.put(1, "Success");
        ERROR_CODES.put(2, "System Error");
        ERROR_CODES.put(3, "Authentication Failure");
        ERROR_CODES.put(4, "User not authorized");
        ERROR_CODES.put(5, "No PANs Entered or Number of PANs exceeds the limit (5)");
        ERROR_CODES.put(6, "User validity has expired");
        ERROR_CODES.put(8, "Not enough balance");
        ERROR_CODES.put(9, "Not an HTTPs request");
        ERROR_CODES.put(10, "POST method not used");
        ERROR_CODES.put(12, "Invalid version number entered");
        ERROR_CODES.put(15, "Valid User ID not sent in Input request and only PAN sent");
        ERROR_CODES.put(16, "Certificate Revocation List issued by the Certifying Authorities is expired");
        ERROR_CODES.put(17, "User id Deactivated");
        ERROR_CODES.put(18, "User ID does not present in database or Wrong certificate used or User ID is greater than 12 character or Contains special characters");
        ERROR_CODES.put(19, "Signature sent in input request is blank");
        ERROR_CODES.put(20, "Body of the Request is blank or Input data is not received");
        ERROR_CODES.put(21, "User ID is blank in Header Input request");
        ERROR_CODES.put(22, "PAN Number is more than 10 characters or Value is Null or Contains special characters");
        ERROR_CODES.put(23, "System Failure or common error message for request");
        ERROR_CODES.put(24, "Duplicate Transaction ID entered");
        ERROR_CODES.put(25, "Parse Exception in JSON");
        ERROR_CODES.put(26, "Records Count Passed from the header value is not matched with the Records Count present in the JSON Input Array");
        ERROR_CODES.put(27, "Name of Pan holder/Name on card is greater than 85 character or Value is null or contains ~ ^ special characters");
        ERROR_CODES.put(28, "Father is greater than 75 character or Value is Null or contains ~ ^ special characters");
        ERROR_CODES.put(29, "Date of Birth format is incorrect it should be separated with slash (/) and in format of (DD/MM/YYYY) or Value is Null");
        ERROR_CODES.put(30, "Request Time is greater than 30 characters or Value is Null or Invalid format");
        ERROR_CODES.put(31, "Transaction ID is greater than 50 characters or Value is Null");
        ERROR_CODES.put(32, "Record count is blank or Record count contains alphabets or special characters");
        ERROR_CODES.put(33, "Request Time is could not be future date/time and not within the last half an hour");
    }

    // Method to get the error message by code
    public static String getMessageByCode(int code) {
        return ERROR_CODES.getOrDefault(code, "Unknown error code");
    }

}

