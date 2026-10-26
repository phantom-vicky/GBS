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
 * The Class OtherBankBranchesMaster.
 */
@Entity
@Table(name = "gbm_other_bank_branches")

public class OtherBankBranchesMaster implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OTH_BANK_BRANCHES_ID_GEN")
    @SequenceGenerator(name = "SEQ_OTH_BANK_BRANCHES_ID_GEN", sequenceName = "SEQ_OTH_BANK_BRANCHES_ID_GEN", allocationSize = 1)
    private Integer id;

    /**
     * The branch code.
     */
    @Column(name = "BR_CODE")
    private String branchCode;

    /**
     * The bank code.
     */
    @Column(name = "BANK_CODE")
    private String bankCode;

    /**
     * The micr centre code.
     */
    @Column(name = "MICR_CENTRE_CODE")
    private String micrCentreCode;

    /**
     * The micr bank code.
     */
    @Column(name = "MICR_BANK_CODE")
    private String micrBankCode;

    /**
     * The micr branch code.
     */
    @Column(name = "MICR_BRANCH_CODE")
    private String micrBranchCode;

    /**
     * The branch name.
     */
    @Column(name = "BR_NAME")
    private String branchName;

    /**
     * The branch addr 1.
     */
    @Column(name = "BR_ADDR_1")
    private String branchAddr1;

    /**
     * The branch addr 2.
     */
    @Column(name = "BR_ADDR_2")
    private String branchAddr2;

    /**
     * The branch addr 3.
     */
    @Column(name = "BR_ADDR_3")
    private String branchAddr3;

    /**
     * The branch city code.
     */
    @Column(name = "BR_CITY_CODE")
    private String branchCityCode;

    /**
     * The branch state code.
     */
    @Column(name = "BR_STATE_CODE")
    private String branchStateCode;

    /**
     * The branch pin code.
     */
    @Column(name = "BR_PIN_CODE")
    private String branchPinCode;

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
     *
     *
     /**
     * The ifscCode.
     */
    @Column(name = "BANK_IFSC_CODE")
    private String bankIfscCode;

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
     * Gets the auditable.
     *
     * @return the auditable
     */
    public Boolean getAuditable() {
        return this.auditable;
    }

    public String getBankIfscCode() {
        return bankIfscCode;
    }

    public void setBankIfscCode(String bankIfscCode) {
        this.bankIfscCode = bankIfscCode;
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
}
