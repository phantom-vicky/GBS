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
 * The Class BranchIfscDetails.
 */
@Entity
@Table(name = "gbm_branch_ifsc_details")
public class BranchIfscDetails implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BRANCH_IFSC_CODES")
    @SequenceGenerator(name = "SEQ_BRANCH_IFSC_CODES", sequenceName = "SEQ_BRANCH_IFSC_CODES", allocationSize = 1)
    private Integer id;

    /**
     * The bank code.
     */
    @Column(name = "bank_code")
    private String bankCode;

    /**
     * The branch code.
     */
    @Column(name = "branch_code")
    private String branchCode;

    /**
     * The bank identifier.
     */
    @Column(name = "bank_identifier")
    private String bankIdentifier;

    /**
     * The paysys id.
     */
    @Column(name = "paysys_id")
    private String paysysId;

    /**
     * The deleted.
     */
    @Column(name = "del_flg")
    private Boolean deleted;

    /**
     * The created by.
     */
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    /**
     * The created date.
     */
    @Column(name = "created_date", updatable = false)
    private Date createdDate;

    /**
     * The modified by.
     */
    @Column(name = "modified_by")
    private String modifiedBy;

    /**
     * The modified date.
     */
    @Column(name = "modified_date")
    private Date modifiedDate;

    /**
     * The verified.
     */
    @Column(name = "is_verified")
    private Boolean verified;

    /**
     * The verified by.
     */
    @Column(name = "verified_by")
    private String verifiedBy;

    /**
     * The verified date.
     */
    @Column(name = "verified_date")
    private Date verifiedDate;

    /**
     * The file name.
     */
    @Column(name = "file_name", updatable = false)
    private String fileName;

    /**
     * The additional text 1.
     */
    @Column(name = "addl_text1")
    private String additionalText1;

    /**
     * The additional text 2.
     */
    @Column(name = "addl_text2")
    private String additionalText2;

    /**
     * The additional text 3.
     */
    @Column(name = "addl_text3")
    private String additionalText3;

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
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
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
     * Checks if is auditable.
     *
     * @return the boolean
     */
    public Boolean isAuditable() {
        return this.auditable;
    }

    /**
     * Checks if is deleted.
     *
     * @return the boolean
     */
    public Boolean isDeleted() {
        return this.deleted;
    }

    /**
     * Checks if is verified.
     *
     * @return the boolean
     */
    public Boolean isVerified() {
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
     * Sets the auditable.
     *
     * @param auditable the new auditable
     */
    public void setAuditable(final Boolean auditable) {
        this.auditable = auditable;
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
    public void setDeleted(final Boolean deleted) {
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
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Integer id) {
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
    public void setVerified(final Boolean verified) {
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

}
