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
import jakarta.persistence.Table;
import java.util.Date;

/**
 * The Class GeneralSetupDetails.
 */
@Entity
@Table(name = "GBM_CBS_GENERAL_SETUP")
@DynamicUpdate
@SelectBeforeUpdate
public class GeneralSetupDetails {

    /**
     * The id.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The parameter name.
     */
    @Column(name = "PARAM_NAME")
    private String parameterName;

    /**
     * The parameter value.
     */
    @Column(name = "PARAM_VALUE")
    private String parameterValue;

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
    @Column(name = "LAST_MOD_BY")
    private String modifiedBy;

    /**
     * The modified date.
     */
    @Column(name = "LAST_MOD_DATE")
    private Date modifiedDate;

    /**
     * The is verified.
     */
    @Column(name = "IS_VERIFIED")
    private Boolean isVerified;

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
     * The auditable.
     */
    @Column(name = "auditable")
    private Boolean auditable;

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
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the checks if is verified.
     *
     * @return the checks if is verified
     */
    public Boolean getIsVerified() {
        return this.isVerified;
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
     * Gets the parameter name.
     *
     * @return the parameter name
     */
    public String getParameterName() {
        return this.parameterName;
    }

    /**
     * Gets the parameter value.
     *
     * @return the parameter value
     */
    public String getParameterValue() {
        return this.parameterValue;
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
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Sets the checks if is verified.
     *
     * @param isVerified the new checks if is verified
     */
    public void setIsVerified(final Boolean isVerified) {
        this.isVerified = isVerified;
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
     * Sets the parameter name.
     *
     * @param parameterName the new parameter name
     */
    public void setParameterName(final String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * Sets the parameter value.
     *
     * @param parameterValue the new parameter value
     */
    public void setParameterValue(final String parameterValue) {
        this.parameterValue = parameterValue;
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
