/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * The Class OfficeAccountPlaceHolderMaster.
 */
@Entity
@Table(name = "gbm_office_account_placeholder")

public class OfficeAccountPlaceHolderMaster implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OFF_ACC_PLACEHOLDER_ID_GEN")
    @SequenceGenerator(name = "SEQ_OFF_ACC_PLACEHOLDER_ID_GEN", sequenceName = "SEQ_OFF_ACC_PLACEHOLDER_ID_GEN", allocationSize = 1)
    private Integer id;

    /**
     * The bacid.
     */
    @Column(name = "BACID")
    private String bacid;

    /**
     * The bacid desc.
     */
    @Column(name = "BACID_DESC")
    private String bacidDesc;

    /**
     * The schm type.
     */
    @Column(name = "SCHM_TYPE")
    private String schmType;

    /**
     * The del flg.
     */
    @Column(name = "DEL_FLG")
    private boolean delFlg;

    /**
     * The created by.
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * The created date.
     */
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    /**
     * The last modified by.
     */
    @Column(name = "LAST_MOD_BY")
    private String lastModifiedBy;

    /**
     * The last modified date.
     */
    @Column(name = "LAST_MOD_DATE")
    private Date lastModifiedDate;

    /**
     * The verified by.
     */
    @Column(name = "VERIFIED_BY")
    private String verifiedBy;

    /**
     * The verified date.
     */
    @Column(name = "VERIFIED_DATE")
    private Date verifiedDate;

    /**
     * The is verified.
     */
    @Column(name = "IS_VERIFIED")
    private boolean isVerified;

    /**
     * The additional text 1.
     */
    @Column(name = "ADDITIONAL_1")
    private String additionalText1;

    /**
     * The additional text 2.
     */
    @Column(name = "ADDITIONAL_2")
    private String additionalText2;

    /**
     * The additional text 3.
     */
    @Column(name = "ADDITIONAL_3")
    private String additionalText3;

    /**
     * The filename.
     */
    @Column(name = "FILENAME")
    private String filename;

    /**
     * The auditable.
     */
    @Column(name = "auditable")
    private Boolean auditable = Boolean.FALSE;

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
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return this.id;
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
     * Checks if is auditable.
     *
     * @return the boolean
     */
    public Boolean isAuditable() {
        return this.auditable;
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
     * Sets the auditable.
     *
     * @param auditable the new auditable
     */
    public void setAuditable(final Boolean auditable) {
        this.auditable = auditable;
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
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Integer id) {
        this.id = id;
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
