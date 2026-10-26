/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

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
 * The Class OtherBankDetailsMaster.
 */

@Entity
@Table(name = "gbm_other_bank_details")
@DynamicUpdate
@SelectBeforeUpdate
public class OtherBankDetailsMaster implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OTHER_BANK_DETAILS")
    @SequenceGenerator(name = "SEQ_OTHER_BANK_DETAILS", sequenceName = "SEQ_OTHER_BANK_DETAILS", allocationSize = 1)
    private int id;

    /**
     * The bank code.
     */
    @Column(name = "BANK_CODE")
    private String bankCode;

    /**
     * The bank name.
     */
    @Column(name = "BANK_NAME")
    private String bankName;

    /**
     * The bank short name.
     */
    @Column(name = "BANK_SHORT_NAME")
    private String bankShortName;

    /**
     * The delete flag.
     */
    @Column(name = "DELETE_FLAG")
    private boolean deleteFlag;

    /**
     * The created by.
     */
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;

    /**
     * The created date.
     */
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;

    /**
     * The modified by.
     */
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    /**
     * The modified date.
     */
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    /**
     * The verified.
     */
    @Column(name = "IS_VERIFIED")
    private boolean verified;

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
     * The file name.
     */
    @Column(name = "FILE_NAME")
    private String fileName;

    /**
     * The additional text 1.
     */
    @Column(name = "ADDL_TEXT1")
    private String additionalText1;

    /**
     * The additional text 2.
     */
    @Column(name = "ADDL_TEXT2")
    private String additionalText2;

    /**
     * The additional text 3.
     */
    @Column(name = "ADDL_TEXT3")
    private String additionalText3;

    /**
     * The auditable.
     */
    @Column(name = "auditable")
    private Boolean auditable = Boolean.FALSE;
    /**
     * The ADDR1.
     */
    @Column(name = "ADDR1")
    private String addOne ;
    /**
     * The ADDR1.
     */
    @Column(name = "ADDR2")
    private String addTwo ;
    /**
     * The CITY.
     */
    @Column(name = "CITY")
    private String city ;

    /**
     * The PIN_CODE.
     */
    @Column(name = "PIN_CODE")
    private String pinCode ;
    /**
     * Gets the additional text 1.
     *
     * @return the additional text 1
     */
    public String getAdditionalText1() {
        return this.additionalText1;
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

}
