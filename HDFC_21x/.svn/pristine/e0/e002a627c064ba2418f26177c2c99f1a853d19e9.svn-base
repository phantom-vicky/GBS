/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.cbs.integration.common.ErrorMessages;
import com.isl.gbm.common.dto.ColumnValueDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Class OtherBankBranchesDto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OtherBankBranchesDto {

    /**
     * The branch id.
     */
    private Integer branchId;


    @Valid
    List<ColumnValueDTO> columnValues;

    public String id;
    public String bankName;

    private Map<String, Object> cbsBranchesOfOtherBankDetailsOldNewValue;

    /**
     * The branch code.
     */
    @Size(max = 6, message = "Branch Code should not have more than 6 characters")
    //@NotBlank(message = "Branch code cannot be blank")
    private String branchCode;

    /**
     * The bank code.
     */
    @Size(max = 5, message = "Bank Code should not have more than 5 characters")
    //@NotBlank(message = "Bank code cannot be blank")
    private String bankCode;

    /**
     * The micr centre code.
     */
    @Size(max = 3, message = "Micr centre code should not have morethan 3 characters")
    private String micrCentreCode;

    /**
     * The micr bank code.
     */
    @Size(max = 3, message = "Micr bank code should not have morethan 3 characters")
    private String micrBankCode;

    /**
     * The micr branch code.
     */
    @Size(max = 3, message = "Micr branch code should not have morethan 3 characters")
    private String micrBranchCode;

    /**
     * The branch name.
     */
    @Size(max = 20, message = "Branch name should not have morethan 20 characters")
    private String branchName;

    /**
     * The branch addr 1.
     */
    @Size(max = 45, message = "Branch address1 should not have morethan 45 characters")
    private String branchAddr1;

    /**
     * The branch addr 2.
     */
    @Size(max = 45, message = "Branch address2 should not have morethan 45 characters")
    private String branchAddr2;

    /**
     * The branch addr 3.
     */
    @Size(max = 45, message = "Branch address3 should not have morethan 45 characters")
    private String branchAddr3;

    /**
     * The branch city code.
     */
    @Size(max = 5, message = "Branch city code should not have morethan 5 characters")
    private String branchCityCode;

    /**
     * The branch state code.
     */
    @Size(max = 5, message = "Branch state code should not have morethan 5 characters")
    private String branchStateCode;

    /**
     * The branch pin code.
     */
    @Size(max = 10, message = "Branch pin code should not have morethan 10 characters")
    private String branchPinCode;

    /**
     * The del flg.
     */
    private boolean delFlg;

    /**
     * The created by.
     */
    private String createdBy;

    /**
     * The created date.
     */
    private Date createdDate;

    /**
     * The last modified by.
     */
    private String lastModifiedBy;

    /**
     * The last modified date.
     */
    private Date lastModifiedDate;

    /**
     * The verified by.
     */
    private String verifiedBy;

    /**
     * The verified date.
     */
    private Date verifiedDate;

    /**
     * The is verified.
     */
    private boolean isVerified;

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
     * The filename.
     */
    @Size(max = 100, message = "File name should not have morethan 100 characters")
    private String filename;

    private String creatDate;

    private String modDate;

    private String verDate;

    private String previousOperationType;

    private String verifiedFlag;

    private String rejectionRemarks;

    public String getRejectionRemarks() {
        return rejectionRemarks;
    }

    public void setRejectionRemarks(String rejectionRemarks) {
        this.rejectionRemarks = rejectionRemarks;
    }

    public List<ColumnValueDTO> getColumnValues() {
        return columnValues;
    }

    public void setColumnValues(List<ColumnValueDTO> columnValues) {
        this.columnValues = columnValues;
    }

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
     * Gets the branch addr 1.
     *
     * @return the branch addr 1
     */
    public String getBranchAddr1() {
        return this.branchAddr1;
    }

    /**
     * Gets the branch addr 2.
     *
     * @return the branch addr 2
     */
    public String getBranchAddr2() {
        return this.branchAddr2;
    }

    /**
     * Gets the branch addr 3.
     *
     * @return the branch addr 3
     */
    public String getBranchAddr3() {
        return this.branchAddr3;
    }

    /**
     * Gets the branch city code.
     *
     * @return the branch city code
     */
    public String getBranchCityCode() {
        return this.branchCityCode;
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
     * Gets the branch id.
     *
     * @return the branch id
     */
    public Integer getBranchId() {
        return this.branchId;
    }

    /**
     * Gets the branch name.
     *
     * @return the branch name
     */
    public String getBranchName() {
        return this.branchName;
    }

    /**
     * Gets the branch pin code.
     *
     * @return the branch pin code
     */
    public String getBranchPinCode() {
        return this.branchPinCode;
    }

    /**
     * Gets the branch state code.
     *
     * @return the branch state code
     */
    public String getBranchStateCode() {
        return this.branchStateCode;
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
     * Gets the del flg.
     *
     * @return the del flg
     */
    public boolean getDelFlg() {
        return this.delFlg;
    }

    /**
     * Gets the filename.
     *
     * @return the filename
     */
    public String getFilename() {
        return this.filename;
    }

    /**
     * Gets the last modified by.
     *
     * @return the last modified by
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Gets the last modified date.
     *
     * @return the last modified date
     */
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Gets the micr bank code.
     *
     * @return the micr bank code
     */
    public String getMicrBankCode() {
        return this.micrBankCode;
    }

    /**
     * Gets the micr branch code.
     *
     * @return the micr branch code
     */
    public String getMicrBranchCode() {
        return this.micrBranchCode;
    }

    /**
     * Gets the micr centre code.
     *
     * @return the micr centre code
     */
    public String getMicrCentreCode() {
        return this.micrCentreCode;
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
        return this.isVerified;
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
     * Sets the branch addr 1.
     *
     * @param branchAddr1 the new branch addr 1
     */
    public void setBranchAddr1(final String branchAddr1) {
        this.branchAddr1 = branchAddr1;
    }

    /**
     * Sets the branch addr 2.
     *
     * @param branchAddr2 the new branch addr 2
     */
    public void setBranchAddr2(final String branchAddr2) {
        this.branchAddr2 = branchAddr2;
    }

    /**
     * Sets the branch addr 3.
     *
     * @param branchAddr3 the new branch addr 3
     */
    public void setBranchAddr3(final String branchAddr3) {
        this.branchAddr3 = branchAddr3;
    }

    /**
     * Sets the branch city code.
     *
     * @param branchCityCode the new branch city code
     */
    public void setBranchCityCode(final String branchCityCode) {
        this.branchCityCode = branchCityCode;
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
     * Sets the branch id.
     *
     * @param branchId the new branch id
     */
    public void setBranchId(final Integer branchId) {
        this.branchId = branchId;
    }

    /**
     * Sets the branch name.
     *
     * @param branchName the new branch name
     */
    public void setBranchName(final String branchName) {
        this.branchName = branchName;
    }

    /**
     * Sets the branch pin code.
     *
     * @param branchPinCode the new branch pin code
     */
    public void setBranchPinCode(final String branchPinCode) {
        this.branchPinCode = branchPinCode;
    }

    /**
     * Sets the branch state code.
     *
     * @param branchStateCode the new branch state code
     */
    public void setBranchStateCode(final String branchStateCode) {
        this.branchStateCode = branchStateCode;
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
     * Sets the del flg.
     *
     * @param delFlg the new del flg
     */
    public void setDelFlg(final boolean delFlg) {
        this.delFlg = delFlg;
    }

    /**
     * Sets the filename.
     *
     * @param filename the new filename
     */
    public void setFilename(final String filename) {
        this.filename = filename;
    }

    /**
     * Sets the last modified by.
     *
     * @param lastModifiedBy the new last modified by
     */
    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Sets the last modified date.
     *
     * @param lastModifiedDate the new last modified date
     */
    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Sets the micr bank code.
     *
     * @param micrBankCode the new micr bank code
     */
    public void setMicrBankCode(final String micrBankCode) {
        this.micrBankCode = micrBankCode;
    }

    /**
     * Sets the micr branch code.
     *
     * @param micrBranchCode the new micr branch code
     */
    public void setMicrBranchCode(final String micrBranchCode) {
        this.micrBranchCode = micrBranchCode;
    }

    /**
     * Sets the micr centre code.
     *
     * @param micrCentreCode the new micr centre code
     */
    public void setMicrCentreCode(final String micrCentreCode) {
        this.micrCentreCode = micrCentreCode;
    }

    /**
     * Sets the verified.
     *
     * @param isVerified the new verified
     */
    public void setVerified(final boolean isVerified) {
        this.isVerified = isVerified;
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

    public Map<String, Object> getCbsBranchesOfOtherBankDetailsOldNewValue() {
        return cbsBranchesOfOtherBankDetailsOldNewValue;
    }

    public void setCbsBranchesOfOtherBankDetailsOldNewValue(Map<String, Object> cbsBranchesOfOtherBankDetailsOldNewValue) {
        this.cbsBranchesOfOtherBankDetailsOldNewValue = cbsBranchesOfOtherBankDetailsOldNewValue;
    }


    private String bankIfscCode;

    public String getBankIfscCode() {
        return bankIfscCode;
    }

    public void setBankIfscCode(String bankIfscCode) {
        this.bankIfscCode = bankIfscCode;
    }

    public boolean isDelFlg() {
        return delFlg;
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

    public String getPreviousOperationType() {
        return previousOperationType;
    }

    public void setPreviousOperationType(String previousOperationType) {
        this.previousOperationType = previousOperationType;
    }

    public String getVerifiedFlag() {
        return verifiedFlag;
    }

    public void setVerifiedFlag(String verifiedFlag) {
        this.verifiedFlag = verifiedFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
