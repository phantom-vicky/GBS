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
 * The Class TransactionCodes.
 */
@Entity
@Table(name = "GBM_TRANSACTION_CODES")
public class TransactionCodes implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LIST_OF_TRAN_CODES")
    @SequenceGenerator(name = "SEQ_LIST_OF_TRAN_CODES", sequenceName = "SEQ_LIST_OF_TRAN_CODES", allocationSize = 1)
    private Integer id;

    /**
     * The clg tran code.
     */
    @Column(name = "CLG_TRAN_CODE")
    private String clgTranCode;

    /**
     * The clg tran code desc.
     */
    @Column(name = "CLG_TRAN_CODE_DESC")
    private String clgTranCodeDesc;

    /**
     * The instrument type.
     */
    @Column(name = "INSTRUMENT_TYPE")
    private String instrumentType;

    /**
     * The delete flag.
     */
    @Column(name = "DELETE_FLAG")
    private boolean deleteFlag;

    /**
     * The scheme code.
     */
    @Column(name = "SCHEME_CODE")
    private String schemeCode;

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
     * The is verified.
     */
    @Column(name = "IS_VERIFIED")
    private boolean isVerified;

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
     * The add text 1.
     */
    @Column(name = "ADD_TEXT1")
    private String addText1;

    /**
     * The add text 2.
     */
    @Column(name = "ADD_TEXT2")
    private String addText2;

    /**
     * The add text 3.
     */
    @Column(name = "ADD_TEXT3")
    private String addText3;

    /**
     * The auditable.
     */
    @Column(name = "auditable")
    private Boolean auditable = Boolean.FALSE;

    /**
     * Gets the adds the text 1.
     *
     * @return the adds the text 1
     */
    public String getAddText1() {
        return this.addText1;
    }

    /**
     * Gets the adds the text 2.
     *
     * @return the adds the text 2
     */
    public String getAddText2() {
        return this.addText2;
    }

    /**
     * Gets the adds the text 3.
     *
     * @return the adds the text 3
     */
    public String getAddText3() {
        return this.addText3;
    }

    /**
     * Gets the clg tran code.
     *
     * @return the clg tran code
     */
    public String getClgTranCode() {
        return this.clgTranCode;
    }

    /**
     * Gets the clg tran code desc.
     *
     * @return the clg tran code desc
     */
    public String getClgTranCodeDesc() {
        return this.clgTranCodeDesc;
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
     * Checks if is auditable.
     *
     * @return the boolean
     */
    public Boolean isAuditable() {
        return this.auditable;
    }

    /**
     * Checks if is delete flag.
     *
     * @return true, if is delete flag
     */
    public boolean isDeleteFlag() {
        return this.deleteFlag;
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
     * Sets the adds the text 1.
     *
     * @param addText1 the new adds the text 1
     */
    public void setAddText1(final String addText1) {
        this.addText1 = addText1;
    }

    /**
     * Sets the adds the text 2.
     *
     * @param addText2 the new adds the text 2
     */
    public void setAddText2(final String addText2) {
        this.addText2 = addText2;
    }

    /**
     * Sets the adds the text 3.
     *
     * @param addText3 the new adds the text 3
     */
    public void setAddText3(final String addText3) {
        this.addText3 = addText3;
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
     * Sets the clg tran code.
     *
     * @param clgTranCode the new clg tran code
     */
    public void setClgTranCode(final String clgTranCode) {
        this.clgTranCode = clgTranCode;
    }

    /**
     * Sets the clg tran code desc.
     *
     * @param clgTranCodeDesc the new clg tran code desc
     */
    public void setClgTranCodeDesc(final String clgTranCodeDesc) {
        this.clgTranCodeDesc = clgTranCodeDesc;
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
    public void setId(final Integer id) {
        this.id = id;
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
