package com.isl.gbm.rbibond.externalservice.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.UUID;

public class RequestUtils {

    public static String generateReqNo(String nsdlUserName, String consumerName, int uniqueNumber) {
        // Get the current Julian date
        LocalDate currentDate = LocalDate.now();
        int julianDate = currentDate.get(ChronoField.DAY_OF_YEAR);

        // Format: <NSDL_User_Name>:<Consumer_Name>_<Julian Date>_<Unique 6-digit number>
        return String.format("%s:%s_%03d_%06d", nsdlUserName, consumerName, julianDate, uniqueNumber);
    }

    public static String getRequestDateTime() {
        // Get the current timestamp
        LocalDateTime now = LocalDateTime.now();
        // Format as per the required pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return now.format(formatter);
    }

    public static String escapeSpecialCharacters(String input) {
        if (input == null) {
            return null;
        }
        return input.replace("&", "&amp;").replace("<", "&lt;");
    }

    public static String generateUniqueReference() {
        String uuid = UUID.randomUUID().toString();
        String uniqueRef = uuid.replace("-", "");
        if (uniqueRef.length() > 40) {
            uniqueRef = uniqueRef.substring(0, 40);
        }
        return uniqueRef;
    }


    public static String generateTransactionReference() {
        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        // Define a date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS");
        // Generate a unique identifier (UUID)
        String uniqueId = UUID.randomUUID().toString().replace("-", "").substring(0, 8); // Shortened for brevity
        // Combine date and unique ID
        return now.format(formatter);
    }

}
