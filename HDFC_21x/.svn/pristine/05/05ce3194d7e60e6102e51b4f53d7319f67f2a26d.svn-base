/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.cbs.integration.common.ErrorMessages;
import com.isl.gbm.common.dto.ColumnValueDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtherBankDetailsDto {

    /**
     * The id.
     */
    private int id;


    @Valid
    List<ColumnValueDTO> columnValues;

    private Map<String, Object> cbsOtherBankDetailsOldNewValue;


    /**
     * The bank code.
     */
//    @NotBlank(message = "Bank code cannot be empty")
//    @Size(max = 8, message = "Bank code should not exceed 8 characters")
//    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Bank code should be alphanumeric without spaces")
    private String bankCode;

    /**
     * The bank name.
     */
    @Size(max = 50, message = "Bank name should not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Bank name should be alphanumeric with spaces allowed")
    private String bankName;

    /**
     * The bank short name.
     */
    @Size(max = 50, message = "Bank short name should not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Bank short name should be alphanumeric with spaces allowed")
    private String bankShortName;

    /**
     * The delete flag.
     */
    private boolean deleteFlag;

    /**
     * The created by.
     */
    private String createdBy;

    /**
     * The created date.
     */
    private Date createdDate = new Date();

    /**
     * The modified by.
     */
    private String modifiedBy;

    /**
     * The modified date.
     */
    private Date modifiedDate;

    /**
     * The verified.
     */
    private boolean verified;

    /**
     * The verified by.
     */
    private String verifiedBy;

    /**
     * The verified date.
     */
    private Date verifiedDate;

    private String creatDate;

    private String modDate;

    private String verDate;

    private String verifiedFlag;

    private String previousOperationType;
    @Size(max = 100, message = "Address line 1 should not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Address line 1 should be alphanumeric with spaces allowed")
    private String addOne;
    @Size(max = 100, message = "Address line 2 should not exceed 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Address line 2 should be alphanumeric with spaces allowed")
    private String addTwo;
    @Size(max = 50, message = "City should not exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "City should contain only letters")
    private String city;
    @Pattern(regexp = "^[0-9]{6}$", message = "Pin Code must be exactly 6 digits")
    private String pinCode;
    private String rejectionRemarks;

    public String getRejectionRemarks() {
        return rejectionRemarks;
    }

    public void setRejectionRemarks(String rejectionRemarks) {
        this.rejectionRemarks = rejectionRemarks;
    }

    public String getAddOne() {
        return addOne;
    }

    public void setAddOne(String addOne) {
        this.addOne = addOne;
    }

    public String getAddTwo() {
        return addTwo;
    }

    public void setAddTwo(String addTwo) {
        this.addTwo = addTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * The file name.
     */
    @Size(max = 100, message = "File name should not exceed 100 characters")
    private String fileName;

    /**
     * The additional text 1.
     */
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT1_MAX_LENGTH_MSG)
    private String additionalText1;

    /**
     * The additional text 2.
     */
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT2_MAX_LENGTH_MSG)
    private String additionalText2;

    /**
     * The additional text 3.
     */
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT3_MAX_LENGTH_MSG)
    private String additionalText3;

    /**
     * Gets the additional text 1.
     *
     * @return the additional text 1
     */
    public String getAdditionalText1() {
        return this.additionalText1;
    }

    /**
     * Gets the additional text 2.
     *
     * @return the additional text 2
     */
    public String getAdditionalText2() {
        return this.additionalText2;
    }

    /**
     * Gets the additional text 3.
     *
     * @return the additional text 3
     */
    public String getAdditionalText3() {
        return this.additionalText3;
    }

    /**
     * Gets the bank code.
     *
     * @return the bank code
     */
    public String getBankCode() {
        return this.bankCode;
    }

    /**
     * Gets the bank name.
     *
     * @return the bank name
     */
    public String getBankName() {
        return this.bankName;
    }

    /**
     * Gets the bank short name.
     *
     * @return the bank short name
     */
    public String getBankShortName() {
        return this.bankShortName;
    }

    /**
     * Gets the created by.
     *
     * @return the created by
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Gets the delete flag.
     *
     * @return the delete flag
     */
    public boolean getDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the modified by.
     *
     * @return the modified by
     */
    public String getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    /**
     * Gets the verified by.
     *
     * @return the verified by
     */
    public String getVerifiedBy() {
        return this.verifiedBy;
    }

    /**
     * Gets the verified date.
     *
     * @return the verified date
     */
    public Date getVerifiedDate() {
        return this.verifiedDate;
    }

    /**
     * Checks if is verified.
     *
     * @return true, if is verified
     */
    public boolean isVerified() {
        return this.verified;
    }

    /**
     * Sets the additional text 1.
     *
     * @param additionalText1 the new additional text 1
     */
    public void setAdditionalText1(final String additionalText1) {
        this.additionalText1 = additionalText1;
    }

    /**
     * Sets the additional text 2.
     *
     * @param additionalText2 the new additional text 2
     */
    public void setAdditionalText2(final String additionalText2) {
        this.additionalText2 = additionalText2;
    }

    /**
     * Sets the additional text 3.
     *
     * @param additionalText3 the new additional text 3
     */
    public void setAdditionalText3(final String additionalText3) {
        this.additionalText3 = additionalText3;
    }

    /**
     * Sets the bank code.
     *
     * @param bankCode the new bank code
     */
    public void setBankCode(final String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Sets the bank name.
     *
     * @param bankName the new bank name
     */
    public void setBankName(final String bankName) {
        this.bankName = bankName;
    }

    /**
     * Sets the bank short name.
     *
     * @param bankShortName the new bank short name
     */
    public void setBankShortName(final String bankShortName) {
        this.bankShortName = bankShortName;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy the new created by
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the delete flag.
     *
     * @param deleteFlag the new delete flag
     */
    public void setDeleteFlag(final boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * Sets the file name.
     *
     * @param fileName the new file name
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Sets the modified by.
     *
     * @param modifiedBy the new modified by
     */
    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the new modified date
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Sets the verified.
     *
     * @param verified the new verified
     */
    public void setVerified(final boolean verified) {
        this.verified = verified;
    }

    /**
     * Sets the verified by.
     *
     * @param verifiedBy the new verified by
     */
    public void setVerifiedBy(final String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    /**
     * Sets the verified date.
     *
     * @param verifiedDate the new verified date
     */
    public void setVerifiedDate(final Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }


    public List<ColumnValueDTO> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(List<ColumnValueDTO> columnValues) {
        this.columnValues = columnValues;
    }

    public Map<String, Object> getCbsOtherBankDetailsOldNewValue() {
        return cbsOtherBankDetailsOldNewValue;
    }

    public void setCbsOtherBankDetailsOldNewValue(Map<String, Object> cbsOtherBankDetailsOldNewValue) {
        this.cbsOtherBankDetailsOldNewValue = cbsOtherBankDetailsOldNewValue;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public String getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(String creatDate) {
        this.creatDate = creatDate;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public String getVerDate() {
        return verDate;
    }

    public void setVerDate(String verDate) {
        this.verDate = verDate;
    }

    public String getVerifiedFlag() {
        return verifiedFlag;
    }

    public void setVerifiedFlag(String verifiedFlag) {
        this.verifiedFlag = verifiedFlag;
    }


    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }
}
