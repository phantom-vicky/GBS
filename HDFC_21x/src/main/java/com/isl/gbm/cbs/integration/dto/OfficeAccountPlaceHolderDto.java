/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dto;

import com.isl.gbm.cbs.integration.common.ErrorMessages;
import org.hibernate.validator.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class OfficeAccountPlaceHolderDto.
 */
public class OfficeAccountPlaceHolderDto {

    /**
     * The office id.
     */
    private Integer officeId;

    /**
     * The bacid.
     */
    @NotBlank(message = "Place holder cannot be blank")
    @Size(max = 16, message = "Place holder length cannot exceed 16 characters")
    private String bacid;

    /**
     * The bacid desc.
     */
    @NotBlank(message = "Place holder description cannot be blank")
    @Size(max = 40, message = "Place holder length cannot exceed 40 characters")
    private String bacidDesc;

    /**
     * The schm type.
     */
    @NotBlank(message = "Scheme type cannot be blank")
    @Size(max = 5, message = "Scheme type cannot exceed 5 characters")
    private String schmType;

    public List<HashMap<String, String>> getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(List<HashMap<String, String>> schemeName) {
        this.schemeName = schemeName;
    }

    /**
     * The del flg.
     */

    private List<HashMap<String, String>> schemeName;
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

    private List<Map<String, Object>> oldNewValueList;

    public boolean isDelFlg() {
        return delFlg;
    }

    public List<Map<String, Object>> getOldNewValueList() {
        return oldNewValueList;
    }

    public void setOldNewValueList(List<Map<String, Object>> oldNewValueList) {
        this.oldNewValueList = oldNewValueList;
    }

    public String getId1() {
        return id1;
    }

    public void setId1(String id1) {
        this.id1 = id1;
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

    public String getPkColVal() {
        return pkColVal;
    }

    public void setPkColVal(String pkColVal) {
        this.pkColVal = pkColVal;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

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

    private String id1;
    private  String createdDate1;
    private  String verifiedDate1;
    private  String modifiedDate1;
    private  String pkColVal;

    private Date modifiedDate;

    private String verifiedStatus;
    private String prevOperationType;

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
    @Size(max = 100, message = "File name should not exceed 100 characters")
    private String filename;

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
     * Gets the bacid.
     *
     * @return the bacid
     */
    public String getBacid() {
        return this.bacid;
    }

    /**
     * Gets the bacid desc.
     *
     * @return the bacid desc
     */
    public String getBacidDesc() {
        return this.bacidDesc;
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
     * Gets the office id.
     *
     * @return the office id
     */
    public Integer getOfficeId() {
        return this.officeId;
    }

    /**
     * Gets the schm type.
     *
     * @return the schm type
     */
    public String getSchmType() {
        return this.schmType;
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
     * Sets the bacid.
     *
     * @param bacid the new bacid
     */
    public void setBacid(final String bacid) {
        this.bacid = bacid;
    }

    /**
     * Sets the bacid desc.
     *
     * @param bacidDesc the new bacid desc
     */
    public void setBacidDesc(final String bacidDesc) {
        this.bacidDesc = bacidDesc;
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
     * Sets the office id.
     *
     * @param officeId the new office id
     */
    public void setOfficeId(final Integer officeId) {
        this.officeId = officeId;
    }

    /**
     * Sets the schm type.
     *
     * @param schmType the new schm type
     */
    public void setSchmType(final String schmType) {
        this.schmType = schmType;
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
}
