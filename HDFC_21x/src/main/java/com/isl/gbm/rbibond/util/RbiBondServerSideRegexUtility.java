package com.isl.gbm.rbibond.util;

import com.isl.gbm.common.util.CommonConstants;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.regex.Pattern;

@Component
public class RbiBondServerSideRegexUtility {


    public static void validatePAN(String pan) {
        if (pan == null || !pan.matches(CommonConstants.PAN_PATTERN)) {
            throw new IllegalArgumentException(CommonConstants.INVALID_PAN_NO + pan);
        }
    }

    public static void validateMobileNumber(String mobileNo) {
        if (null!=mobileNo && !Pattern.matches(CommonConstants.MOB_PATTERN, mobileNo)) {
            throw new IllegalArgumentException(CommonConstants.INVALID_MOBILE_NO + mobileNo);
        }
    }

    public static void validateIFSC(String ifsc) {
        if (ifsc == null || ifsc.trim().isEmpty()) {
            return;
        }
        String normalizedIfsc = ifsc.trim().toUpperCase();
        if (!Pattern.matches(CommonConstants.IFSC_PATTERN, normalizedIfsc)) {
            throw new IllegalArgumentException(CommonConstants.INVALID_IFSC_CODE + ifsc);
        }
    }

    public static void validateEmail(String email) {
        if (null!=email && !Pattern.matches(CommonConstants.EMAIL_PATTERN, email)) {
            throw new IllegalArgumentException(CommonConstants.INVALID_EMAIL + email);
        }
    }

    public static void validateAccountNumber(String accNo) {
        if (null!=accNo && !Pattern.matches(CommonConstants.ACC_PATTERN,accNo)) {
            throw new IllegalArgumentException(CommonConstants.INVALID_ACC_NO + accNo);
        }
    }

    public static void validateAmount(String amount) {
        if (null!=amount && !amount.matches("^\\d+(\\.\\d+)?$")) {
            throw new IllegalArgumentException(CommonConstants.INVALID_AMOUNT + amount);
        }
    }
    public static void validatePercentage(String percentage) {
        // Ensure the percentage is not null, and matches the number format with up to 2 decimal places
        if (null != percentage && !percentage.matches("^\\d+(\\.\\d{1,2})?$")) {
            throw new IllegalArgumentException(CommonConstants.INVALID_PERCENTAGE + percentage);
        }
        // Check if the value is between 0 and 100 (inclusive)
        try {
            BigDecimal decimalPercentage = new BigDecimal(percentage);
            // Check if the percentage is within the valid range (0 to 100)
            if (decimalPercentage.compareTo(BigDecimal.ZERO) < 0 || decimalPercentage.compareTo(new BigDecimal("100")) > 0) {
                throw new IllegalArgumentException(CommonConstants.INVALID_PERCENTAGE + "Percentage must be between 0 and 100: " + percentage);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(CommonConstants.INVALID_PERCENTAGE + "Invalid numeric value: " + percentage);
        }
    }



    public static void validateName(String name) {
        if (null!=name && !Pattern.matches(CommonConstants.NAME_PATTERN, name)) {
            throw new IllegalArgumentException(CommonConstants.NAME_VAL + name);
        }
    }

    public static void validateMakerReason(String makerReason) {
        if (null != makerReason && !Pattern.matches(CommonConstants.MAKER_REASON_PATTERN, makerReason)) {
            throw new IllegalArgumentException(CommonConstants.INVALID_MAKER_REASON + makerReason);
        }
    }


}

