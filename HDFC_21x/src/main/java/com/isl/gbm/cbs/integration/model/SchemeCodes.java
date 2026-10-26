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
 * The Class SchemeCodes.
 */
@Entity
@Table(name = "GBM_SCHEME_CODES")
@DynamicUpdate
@SelectBeforeUpdate
public class SchemeCodes implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SCHEME_CODES")
    @SequenceGenerator(name = "SEQ_SCHEME_CODES", sequenceName = "SEQ_SCHEME_CODES", allocationSize = 1)
    private Integer id;

    /**
     * The scheme code.
     */
    @Column(name = "SCHEME_CODE")
    private String schemeCode;

    /**
     * The description.
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * The gl sub head code.
     */
    @Column(name = "GL_SUB_HEAD_CODE")
    private String glSubHeadCode;

    /**
     * The scheme type.
     */
    @Column(name = "SCHEME_TYPE")
    private String schemeType;

    /**
     * The delete flag.
     */
    @Column(name = "DELETE_FLAG")
    private boolean deleteFlag;

    /**
     * The created by.
     */
    @Column(name = "CREATED_BY")
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
     * Gets the auditable.
     *
     * @return the auditable
     */
    public Boolean getAuditable() {
        return this.auditable;
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
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return this.description;
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
     * Gets the gl sub head code.
     *
     * @return the gl sub head code
     */
    public String getGlSubHeadCode() {
        return this.glSubHeadCode;
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
     * Gets the scheme code.
     *
     * @return the scheme code
     */
    public String getSchemeCode() {
        return this.schemeCode;
    }

    /**
     * Gets the scheme type.
     *
     * @return the scheme type
     */
    public String getSchemeType() {
        return this.schemeType;
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
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(final String description) {
        this.description = description;
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
     * Sets the gl sub head code.
     *
     * @param glSubHeadCode the new gl sub head code
     */
    public void setGlSubHeadCode(final String glSubHeadCode) {
        this.glSubHeadCode = glSubHeadCode;
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
     * Sets the scheme code.
     *
     * @param schemeCode the new scheme code
     */
    public void setSchemeCode(final String schemeCode) {
        this.schemeCode = schemeCode;
    }

    /**
     * Sets the scheme type.
     *
     * @param schemeType the new scheme type
     */
    public void setSchemeType(final String schemeType) {
        this.schemeType = schemeType;
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
