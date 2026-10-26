/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.isl.gbm.cbs.integration.common.ErrorMessages;
import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Class InstrumentDetailsDto.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstrumentDetailsDto {

    /**
     * The instrument id.
     */
    private Integer instrumentId;

    /**
     * The dr cr indicator.
     */
    @NotNull(message = "dr cr indicator cannot be null")
    private Character drCrIndicator;

    public String getDrCrIndicator1() {
        return drCrIndicator1;
    }

    public void setDrCrIndicator1(String drCrIndicator1) {
        this.drCrIndicator1 = drCrIndicator1;
    }

    private String drCrIndicator1;

    /**
     * The instrument type.
     */
    @NotBlank(message = ErrorMessages.INSTRUMENT_TYPE_EMPTY_MSG)
    @Size(max = 5, message = ErrorMessages.INSTRUMENT_TYPE_MAX_LENGTH)
    private String instrumentType;

    /**
     * The scheme code.
     */
    @NotBlank(message = ErrorMessages.SCHEME_CODE_EMPTY_MSG)
    @Size(max = 5, message = ErrorMessages.SCHEME_CODE_MAX_LENGTH)
    private String schemeCode;

    /**
     * The created by.
     */
    private String createdBy;


    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public String getPrevOperationType() {
        return prevOperationType;
    }

    public void setPrevOperationType(String prevOperationType) {
        this.prevOperationType = prevOperationType;
    }

    private String verifiedStatus;
    private String prevOperationType;
    private  String createdDate1;
    private  String verifiedDate1;
    private  String modifiedDate1;
    private  String pkColVal;

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
     * Gets the dr cr indicator.
     *
     * @return the dr cr indicator
     */
    public Character getDrCrIndicator() {
        return this.drCrIndicator;
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
     * Gets the instrument id.
     *
     * @return the instrument id
     */
    public Integer getInstrumentId() {
        return this.instrumentId;
    }

    /**
     * Gets the instrument type.
     *
     * @return the instrument type
     */
    public String getInstrumentType() {
        return this.instrumentType;
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
     * Gets the scheme code.
     *
     * @return the scheme code
     */
    public String getSchemeCode() {
        return this.schemeCode;
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
     * Sets the dr cr indicator.
     *
     * @param drCrIndicator the new dr cr indicator
     */
    public void setDrCrIndicator(final Character drCrIndicator) {
        this.drCrIndicator = drCrIndicator;
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
     * Sets the instrument id.
     *
     * @param instrumentId the new instrument id
     */
    public void setInstrumentId(final Integer instrumentId) {
        this.instrumentId = instrumentId;
    }

    /**
     * Sets the instrument type.
     *
     * @param instrumentType the new instrument type
     */
    public void setInstrumentType(final String instrumentType) {
        this.instrumentType = instrumentType;
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
     * Sets the scheme code.
     *
     * @param schemeCode the new scheme code
     */
    public void setSchemeCode(final String schemeCode) {
        this.schemeCode = schemeCode;
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

    public List<Map<String, Object>> getOldNewValueList() {
        return oldNewValueList;
    }

    public void setOldNewValueList(List<Map<String, Object>> oldNewValueList) {
        this.oldNewValueList = oldNewValueList;
    }



    public String getPkColVal() {
        return pkColVal;
    }

    public void setPkColVal(String pkColVal) {
        this.pkColVal = pkColVal;
    }


    public String getCreatedDate1() {
        return createdDate1;
    }

    public void setCreatedDate1(String createdDate1) {
        this.createdDate1 = createdDate1;
    }

    public String getVerifiedDate1() {
        return verifiedDate1;
    }

    public void setVerifiedDate1(String verifiedDate1) {
        this.verifiedDate1 = verifiedDate1;
    }

    public String getModifiedDate1() {
        return modifiedDate1;
    }

    public void setModifiedDate1(String modifiedDate1) {
        this.modifiedDate1 = modifiedDate1;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private  String id;

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    private List<Map<String, Object>> oldNewValueList;
    @Override
    public String toString() {
        return "InstrumentDetailsDto [drCrIndicator=" + this.drCrIndicator + ", instrumentType=" + this.instrumentType
                + ", schemeCode=" + this.schemeCode + ", createdBy=" + this.createdBy + ", createdDate="
                + this.createdDate + ", modifiedBy=" + this.modifiedBy + ", modifiedDate=" + this.modifiedDate
                + ", verified=" + this.verified + ", verifiedBy=" + this.verifiedBy + ", verifiedDate="
                + this.verifiedDate + ", fileName=" + this.fileName + ", additionalText1=" + this.additionalText1
                + ", additionalText2=" + this.additionalText2 + ", additionalText3=" + this.additionalText3 + "]";
    }

}
