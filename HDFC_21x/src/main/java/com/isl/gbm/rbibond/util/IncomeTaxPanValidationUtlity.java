package com.isl.gbm.rbibond.util;

import com.isl.gbm.rbibond.dto.IncomeTaxPanStatusDTO;
import com.isl.gbm.rbibond.dto.NsdlPanStatusDTO;
import com.isl.gbm.rbibond.dto.PanStatusUploadForTdsFileUploadDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class IncomeTaxPanValidationUtlity {

    private static final String REMARKS_PATTERN = "^[A-Za-z0-9!@#$%^&*()_+={}\\[\\]:;\"'<>,.?/|\\\\`~ \\-]*$";
    private static final String NAME_PATTERN = "^[A-Za-z]+( +[A-Za-z]+)*$";
    private static final String PAN_PATTERN = "^[A-Za-z]{5}[0-9]{4}[A-Za-z]$";
    private static final Set<String> VALID_PAN_STATUSES = Set.of(
            "E", "F", "X", "D", "N",
            "EA", "EC", "ED", "EI", "EL",
            "EM", "EP", "ES", "EU"
    );
    private static final Set<String> VALID_AADHAR_STATUSES = Set.of(
            "Y", "R", "NA"
    );

    private boolean isValidPan(String pan) {
        if (pan == null || pan.length() != 10) {
            return false; // PAN must be exactly 10 characters
        }
        boolean isValid = Pattern.matches(PAN_PATTERN, pan);
        if (isValid) {
            char fourthCharacter = pan.charAt(3); // The 4th character is at index 3
            if (fourthCharacter != 'P' && fourthCharacter != 'H') {
                return false; // The 4th character must be 'P' or 'H'
            }
        }
        return isValid;
    }

    // Validate itrFlagStatus field
    private boolean isValidItrFlagStatus(String itrFlagStatus) {
        if(null==itrFlagStatus){
            return false;
        }
        return "F".equals(itrFlagStatus) || "NF".equals(itrFlagStatus);
    }

    // Check if the row is empty
    private boolean isRowEmpty(IncomeTaxPanStatusDTO row) {
        return row == null || row.getPanNo() == null || row.getItrFlagStatus() == null;
    } // Check if the row is empty
    private boolean isRowEmptyTds(PanStatusUploadForTdsFileUploadDTO row) {
        return row == null || row.getOldPan() == null || row.getNewPanStatus() == null;
    }
    // Check if the row is empty
    private boolean isRowEmptyNsdl(NsdlPanStatusDTO row) {
        return row == null || row.getPan() == null || row.getPanStatus() == null
                || row.getPanAsPerNsdl() == null|| row.getDob() == null|| row.getDobAsPerNsdl() == null|| row.getDobStatus() == null
                || row.getDob() == null|| row.getDobStatus() == null|| row.getDobAsPerNsdl() == null|| row.getRemarks() == null;
    }
    // Field-wise validation for each row
    private String validateNsdlRow(NsdlPanStatusDTO row) {
        boolean isPanValid = isValidPan(row.getPan());
        boolean isValidStatus = isStatusValid(row.getPanStatus(),row.getNameStatus(),row.getDobStatus());
        boolean isPanValidNsdl = isValidPan(row.getPanAsPerNsdl());
        boolean isValidRemarks = isValidRemarks(row.getRemarks());
        boolean isValidName = isValidName(row.getName());
        if (!isPanValid) {
            return "Invalid PAN number";
        }
        if (!isPanValidNsdl) {
            return "Invalid PAN number";
        }
        if (!isValidStatus) {
            return "Invalid Status";
        }
        if (!isValidRemarks) {
            return "Invalid Character Found Remarks Field";
        }
        if (!isValidName) {
            return "Invalid Name Field";
        }
        return "NR";
    }
    // Field-wise validation for each row
    private String validateRow(IncomeTaxPanStatusDTO row) {
        boolean isPanValid = isValidPan(row.getPanNo());
        boolean isItrFlagValid = isValidItrFlagStatus(row.getItrFlagStatus());
        if (!isPanValid) {
            return "Invalid PAN number";
        }
        if (!isItrFlagValid) {
            return "Invalid ITR flag status";
        }
        return "NR";
    }
    private String validateRowTds(PanStatusUploadForTdsFileUploadDTO row) {
        boolean isPanValid = isValidPan(row.getOldPan());
        boolean isPanStatus = isPanStatusValidate(row.getNewPanStatus());
        boolean isAadharStatus = isAadharStatusValidate(row.getNewAadharSeedingStatus());
        if (!isPanValid) {
            return "Invalid PAN number";
        }
        if (!isPanStatus) {
            return "Invalid Pan status";
        }
        if (!isAadharStatus) {
            return "Invalid AADHAR Seeding status";
        }
        return "NR";
    }

    private boolean isPanStatusValidate(String panStatus) {
        if (panStatus == null || panStatus.trim().isEmpty()) {
            return false;
        }
        return VALID_PAN_STATUSES.contains(panStatus.trim().toUpperCase());
    }
    private boolean isAadharStatusValidate(String aadharStatus) {
        if (aadharStatus == null || aadharStatus.trim().isEmpty()) {
            return true;
        }
        return VALID_AADHAR_STATUSES.contains(aadharStatus.trim().toUpperCase());
    }
    public String processDataNsdl(List<NsdlPanStatusDTO> entityList) {
        String message ="";
        // Loop through the rows and validate them one by one
        for (NsdlPanStatusDTO row : entityList) {
            if (isRowEmptyNsdl(row)) {
                throw new IllegalArgumentException("Row is empty. Cannot process empty rows.");
            }
            message =validateNsdlRow(row);
            if(!message.equals("NR")){
                break;
            }
        }
        System.out.println("All rows are valid. Proceeding with further processing." +message);
        return message;
    }
    public String processData(List<IncomeTaxPanStatusDTO> entityList) {
        String message ="";
        // Loop through the rows and validate them one by one
        for (IncomeTaxPanStatusDTO row : entityList) {
            if (isRowEmpty(row)) {
                throw new IllegalArgumentException("Row is empty. Cannot process empty rows.");
            }
            message =validateRow(row);
            if(!message.equals("NR")){
                break;
            }
        }
        return message;
    }
    public String processDataTds(List<PanStatusUploadForTdsFileUploadDTO> entityList) {
        String message ="";
        // Loop through the rows and validate them one by one
        for (PanStatusUploadForTdsFileUploadDTO row : entityList) {
            if (isRowEmptyTds(row)) {
                throw new IllegalArgumentException("Row is empty. Cannot process empty rows.");
            }
            message =validateRowTds(row);
            if(!message.equals("NR")){
                break;
            }
        }
        return message;
    }
    private boolean isStatusValid(String pan, String name, String dob) {
        if((null==dob&&dob.trim().isEmpty()) || (null==name&&name.trim().isEmpty()) || (null==pan&&pan.trim().isEmpty())){
            return false;
        }
        return ("N".equals(dob) || "Y".equals(dob)) &&
                ("N".equals(name) || "Y".equals(name)) &&
                ("N".equals(pan) || "Y".equals(pan));
    }
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false; // If the name is null or empty, it's invalid.
        }
        // Validate the name using the regular expression
        return name.matches(NAME_PATTERN);
    }
    public static boolean isValidRemarks(String remarks) {
        if (remarks == null) {
            return false; // If null, it's not valid
        }
        // Check if the length of the remarks is within the allowed limit (200 characters max)
        if (remarks.length() > 200) {
            return false; // Remarks exceed max length
        }
        // Check if the remarks match the allowed pattern using regex
        return Pattern.matches(REMARKS_PATTERN, remarks);
    }
    private boolean isItrFlagStatusValid(String itrFlagStatus) {
        return "N".equals(itrFlagStatus) || "NF".equals(itrFlagStatus);
    }

}

