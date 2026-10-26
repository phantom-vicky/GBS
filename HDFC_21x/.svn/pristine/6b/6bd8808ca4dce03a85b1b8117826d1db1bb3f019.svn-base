/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser.model;

import com.isl.gbm.cbs.integration.common.ErrorMessages;
import org.hibernate.validator.constraints.NotBlank;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * The Class OtherBankBranchesExcelRows.
 */
@ExcelObject(parseType = ParseType.ROW, start = 2)
public class OtherBankBranchesExcelRows {

    /**
     * The branch code.
     */
    @ExcelField(position = 1)
//    @NotBlank(message = ErrorMessages.BRANCH_CODE_EMPTY_MSG)
    @Size(max = 6, message = ErrorMessages.BRANCH_CODE_MAX_LENGTH)
    private String branchCode;

    /**
     * The bank code.
     */
    @ExcelField(position = 2)
    @NotBlank(message = ErrorMessages.BANK_CODE_EMPTY_MSG)
    @Size(max = 5, message = ErrorMessages.BANK_CODE_MAX_LENGTH)
    private String bankCode;

    /**
     * The micr centre code.
     */
    @ExcelField(position = 3)
//    @NotBlank(message = ErrorMessages.MICR_CENTRE_CODE_EMPTY_MSG)
    @Size(max = 3, message = ErrorMessages.MICR_CENTRE_CODE_MAX_LENGTH)
    private String micrCentreCode;

    /**
     * The micr bank code.
     */
    @ExcelField(position = 4)
//    @NotBlank(message = ErrorMessages.MICR_BANK_CODE_EMPTY_MSG)
    @Size(max = 3, message = ErrorMessages.MICR_BANK_CODE_MAX_LENGTH)
    private String micrBankCode;

    /**
     * The micr branch code.
     */
    @ExcelField(position = 5)
//    @NotBlank(message = ErrorMessages.MICR_BRANCH_CODE_EMPTY_MSG)
    @Size(max = 3, message = ErrorMessages.MICR_BRANCH_CODE_MAX_LENGTH)
    private String micrBranchCode;

    /**
     * The branch name.
     */
    @ExcelField(position = 6)
//    @NotBlank(message = ErrorMessages.BRANCH_NAME_EMPTY_MSG)
    @Size(max = 20, message = ErrorMessages.BRANCH_NAME_MAX_LENGTH)
    private String branchName;

    /**
     * The branch addr 1.
     */
    @ExcelField(position = 7)
//    @NotBlank(message = ErrorMessages.ADDR1_EMPTY_MSG)
    @Size(max = 45, message = ErrorMessages.ADDR1_MAX_LENGTH)
    private String branchAddr1;

    /**
     * The branch addr 2.
     */
    @ExcelField(position = 8)
//    @NotBlank(message = ErrorMessages.ADDR2_EMPTY_MSG)
    @Size(max = 45, message = ErrorMessages.ADDR2_MAX_LENGTH)
    private String branchAddr2;

    /**
     * The branch addr 3.
     */
    @ExcelField(position = 9)
//    @NotBlank(message = ErrorMessages.ADDR3_EMPTY_MSG)
    @Size(max = 45, message = ErrorMessages.ADDR3_MAX_LENGTH)
    private String branchAddr3;

    /**
     * The branch city code.
     */
    @ExcelField(position = 10)
//    @NotBlank(message = ErrorMessages.CITY_CODE_EMPTY_MSG)
    @Size(max = 5, message = ErrorMessages.CITY_CODE_MAX_LENGTH)
    private String branchCityCode;

    /**
     * The branch state code.
     */
    @ExcelField(position = 11)
//    @NotBlank(message = ErrorMessages.STATE_CODE_EMPTY_MSG)
    @Size(max = 5, message = ErrorMessages.STATE_CODE_MAX_LENGTH)
    private String branchStateCode;

    /**
     * The branch pin code.
     */
    @ExcelField(position = 12)
    @Size(max = 6, message = "PinCode should not have more than 6 characters")
    @Pattern(regexp = "^[0-9]{1,6}$", message = "PinCode should contain only digits")
    private String branchPinCode;

    /**
     * The addtional text 1.
     */
    @ExcelField(position = 13)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT1_MAX_LENGTH_MSG)
    private String addtionalText1;

    /**
     * The addtional text 2.
     */
    @ExcelField(position = 14)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT2_MAX_LENGTH_MSG)
    private String addtionalText2;

    /**
     * The addtional text 3.
     */
    @ExcelField(position = 15)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT3_MAX_LENGTH_MSG)
    private String addtionalText3;


    /**
     * The addtional text 3.
     */
    @ExcelField(position = 16)
    @Size(min = 11, max = 11, message = "IFSC Code must be exactly 11 characters")
    @NotBlank(message = "IFSC Code should not be blank")
    @Pattern(
            regexp = "^[a-zA-Z]{4}[0-9]{1}[a-zA-Z0-9]{6}$",
            message = "Invalid IFSC Code format"
    )    private String bankIfscCode;

    public String getBankIfscCode() {
        return bankIfscCode;
    }

    public void setBankIfscCode(String bankIfscCode) {
        this.bankIfscCode = bankIfscCode;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final OtherBankBranchesExcelRows other = (OtherBankBranchesExcelRows) obj;

        if (branchCode == null) {
            if (other.branchCode != null) {
                return false;
            }
        } else if (!branchCode.equals(other.branchCode)) {
            return false;
        }
        if (bankCode == null) {
            if (other.bankCode != null) {
                return false;
            }
        } else if (!bankCode.equals(other.bankCode)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the addtional text 1.
     *
     * @return the addtional text 1
     */
    public String getAddtionalText1() {
        return addtionalText1;
    }

    /**
     * Gets the addtional text 2.
     *
     * @return the addtional text 2
     */
    public String getAddtionalText2() {
        return addtionalText2;
    }

    /**
     * Gets the addtional text 3.
     *
     * @return the addtional text 3
     */
    public String getAddtionalText3() {
        return addtionalText3;
    }

    /**
     * Gets the bank code.
     *
     * @return the bank code
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Gets the branch addr 1.
     *
     * @return the branch addr 1
     */
    public String getBranchAddr1() {
        return branchAddr1;
    }

    /**
     * Gets the branch addr 2.
     *
     * @return the branch addr 2
     */
    public String getBranchAddr2() {
        return branchAddr2;
    }

    /**
     * Gets the branch addr 3.
     *
     * @return the branch addr 3
     */
    public String getBranchAddr3() {
        return branchAddr3;
    }

    /**
     * Gets the branch city code.
     *
     * @return the branch city code
     */
    public String getBranchCityCode() {
        return branchCityCode;
    }

    /**
     * Gets the branch code.
     *
     * @return the branch code
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * Gets the branch name.
     *
     * @return the branch name
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Gets the branch pin code.
     *
     * @return the branch pin code
     */
    public String getBranchPinCode() {
        return branchPinCode;
    }

    /**
     * Gets the branch state code.
     *
     * @return the branch state code
     */
    public String getBranchStateCode() {
        return branchStateCode;
    }

    /**
     * Gets the micr bank code.
     *
     * @return the micr bank code
     */
    public String getMicrBankCode() {
        return micrBankCode;
    }

    /**
     * Gets the micr branch code.
     *
     * @return the micr branch code
     */
    public String getMicrBranchCode() {
        return micrBranchCode;
    }

    /**
     * Gets the micr centre code.
     *
     * @return the micr centre code
     */
    public String getMicrCentreCode() {
        return micrCentreCode;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + (addtionalText1 == null ? 0 : addtionalText1.hashCode());
        result = PRIME * result + (addtionalText2 == null ? 0 : addtionalText2.hashCode());
        result = PRIME * result + (addtionalText3 == null ? 0 : addtionalText3.hashCode());
        result = PRIME * result + (branchCode == null ? 0 : branchCode.hashCode());
        result = PRIME * result + (bankCode == null ? 0 : bankCode.hashCode());
        result = PRIME * result + (micrCentreCode == null ? 0 : micrCentreCode.hashCode());
        result = PRIME * result + (micrBankCode == null ? 0 : micrBankCode.hashCode());
        result = PRIME * result + (micrBranchCode == null ? 0 : micrBranchCode.hashCode());
        result = PRIME * result + (branchName == null ? 0 : branchName.hashCode());
        result = PRIME * result + (branchAddr1 == null ? 0 : branchAddr1.hashCode());
        result = PRIME * result + (branchAddr2 == null ? 0 : branchAddr2.hashCode());
        result = PRIME * result + (branchAddr3 == null ? 0 : branchAddr3.hashCode());
        result = PRIME * result + (branchCityCode == null ? 0 : branchCityCode.hashCode());
        result = PRIME * result + (branchStateCode == null ? 0 : branchStateCode.hashCode());
        result = PRIME * result + (branchPinCode == null ? 0 : branchPinCode.hashCode());
        result = PRIME * result + (bankIfscCode == null ? 0 : bankIfscCode.hashCode());
        return result;
    }

    /**
     * Sets the addtional text 1.
     *
     * @param addtionalText1 the new addtional text 1
     */
    public void setAddtionalText1(final String addtionalText1) {
        this.addtionalText1 = addtionalText1;
    }

    /**
     * Sets the addtional text 2.
     *
     * @param addtionalText2 the new addtional text 2
     */
    public void setAddtionalText2(final String addtionalText2) {
        this.addtionalText2 = addtionalText2;
    }

    /**
     * Sets the addtional text 3.
     *
     * @param addtionalText3 the new addtional text 3
     */
    public void setAddtionalText3(final String addtionalText3) {
        this.addtionalText3 = addtionalText3;
    }

    /**
     * Sets the bank code.
     *
     * @param bankCode the new bank code
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Sets the branch addr 1.
     *
     * @param branchAddr1 the new branch addr 1
     */
    public void setBranchAddr1(String branchAddr1) {
        this.branchAddr1 = branchAddr1;
    }

    /**
     * Sets the branch addr 2.
     *
     * @param branchAddr2 the new branch addr 2
     */
    public void setBranchAddr2(String branchAddr2) {
        this.branchAddr2 = branchAddr2;
    }

    /**
     * Sets the branch addr 3.
     *
     * @param branchAddr3 the new branch addr 3
     */
    public void setBranchAddr3(String branchAddr3) {
        this.branchAddr3 = branchAddr3;
    }

    /**
     * Sets the branch city code.
     *
     * @param branchCityCode the new branch city code
     */
    public void setBranchCityCode(String branchCityCode) {
        this.branchCityCode = branchCityCode;
    }

    /**
     * Sets the branch code.
     *
     * @param branchCode the new branch code
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * Sets the branch name.
     *
     * @param branchName the new branch name
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * Sets the branch pin code.
     *
     * @param branchPinCode the new branch pin code
     */
    public void setBranchPinCode(String branchPinCode) {
        this.branchPinCode = branchPinCode;
    }

    /**
     * Sets the branch state code.
     *
     * @param branchStateCode the new branch state code
     */
    public void setBranchStateCode(String branchStateCode) {
        this.branchStateCode = branchStateCode;
    }

    /**
     * Sets the micr bank code.
     *
     * @param micrBankCode the new micr bank code
     */
    public void setMicrBankCode(String micrBankCode) {
        this.micrBankCode = micrBankCode;
    }

    /**
     * Sets the micr branch code.
     *
     * @param micrBranchCode the new micr branch code
     */
    public void setMicrBranchCode(String micrBranchCode) {
        this.micrBranchCode = micrBranchCode;
    }

    /**
     * Sets the micr centre code.
     *
     * @param micrCentreCode the new micr centre code
     */
    public void setMicrCentreCode(String micrCentreCode) {
        this.micrCentreCode = micrCentreCode;
    }

}
