/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.cbs.integration.common.ErrorMessages;
import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Class BranchIfscDetailsDto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BranchIfscDetailsDto {

    /**
     * The branch details id.
     */
    private Integer branchDetailsId;

    /**
     * The bank code.
     */
    @NotBlank
    @Size(max = 15, message = "Max length of bank code should be 15")
    private String bankCode;

    /**
     * The branch code.
     */
    @NotBlank
    @Size(max = 15, message = "Max length of branch code should be 15")
    private String branchCode;

    /**
     * The bank identifier.
     */
    @NotBlank
    @Size(max = 15, message = "Max length of bank identifier should be 15")
    private String bankIdentifier;

    /**
     * The paysys id.
     */
    @Size(max = 15, message = "Max length of paysys id should be 15")
    private String paysysId;

    /**
     * The deleted.
     */
    private boolean deleted;

    /**
     * The created by.
     */
    private String createdBy;

    /**
     * The created date.
     */
    private Date createdDate;


    private String creatDate;

    private String modDate;

    private  String verDate;

    private String delDate;

    private String isVerified;

    private String previousOperationType;

    private List<Map<String, Object>> branchIfscDetailsOldNewValues;

    private Map<String, Object> branchIfscDetailsOldNewValue;

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

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public String getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(String isVerified) {
        this.isVerified = isVerified;
    }

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public List<Map<String, Object>> getBranchIfscDetailsOldNewValues() {
        return branchIfscDetailsOldNewValues;
    }

    public void setBranchIfscDetailsOldNewValues(List<Map<String, Object>> branchIfscDetailsOldNewValues) {
        this.branchIfscDetailsOldNewValues = branchIfscDetailsOldNewValues;
    }

    public Map<String, Object> getBranchIfscDetailsOldNewValue() {
        return branchIfscDetailsOldNewValue;
    }

    public void setBranchIfscDetailsOldNewValue(Map<String, Object> branchIfscDetailsOldNewValue) {
        this.branchIfscDetailsOldNewValue = branchIfscDetailsOldNewValue;
    }

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
     * Gets the bank identifier.
     *
     * @return the bank identifier
     */
    public String getBankIdentifier() {
        return this.bankIdentifier;
    }

    /**
     * Gets the branch code.
     *
     * @return the branch code
     */
    public String getBranchCode() {
        return this.branchCode;
    }

    /**
     * Gets the branch details id.
     *
     * @return the branch details id
     */
    public Integer getBranchDetailsId() {
        return this.branchDetailsId;
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
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
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
     * Gets the paysys id.
     *
     * @return the paysys id
     */
    public String getPaysysId() {
        return this.paysysId;
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
     * Checks if is deleted.
     *
     * @return true, if is deleted
     */
    public boolean isDeleted() {
        return this.deleted;
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
     * Sets the bank identifier.
     *
     * @param bankIdentifier the new bank identifier
     */
    public void setBankIdentifier(final String bankIdentifier) {
        this.bankIdentifier = bankIdentifier;
    }

    /**
     * Sets the branch code.
     *
     * @param branchCode the new branch code
     */
    public void setBranchCode(final String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * Sets the branch details id.
     *
     * @param branchDetailsId the new branch details id
     */
    public void setBranchDetailsId(final Integer branchDetailsId) {
        this.branchDetailsId = branchDetailsId;
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
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(final boolean deleted) {
        this.deleted = deleted;
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
     * Sets the paysys id.
     *
     * @param paysysId the new paysys id
     */
    public void setPaysysId(final String paysysId) {
        this.paysysId = paysysId;
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "BranchIfscDetailsDto [branchDetailsId=" + this.branchDetailsId + ", bankCode=" + this.bankCode
                + ", branchCode=" + this.branchCode + ", bankIdentifier=" + this.bankIdentifier + ", paysysId="
                + this.paysysId + ", deleted=" + this.deleted + ", createdBy=" + this.createdBy + ", createdDate="
                + this.createdDate + ", modifiedBy=" + this.modifiedBy + ", modifiedDate=" + this.modifiedDate
                + ", verified=" + this.verified + ", verifiedBy=" + this.verifiedBy + ", verifiedDate="
                + this.verifiedDate + ", fileName=" + this.fileName + ", additionalText1=" + this.additionalText1
                + ", additionalText2=" + this.additionalText2 + ", additionalText3=" + this.additionalText3 + "]";
    }

}
