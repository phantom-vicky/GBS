/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.model;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class NpsSystemParameter.
 */
@Entity
@Table(name = "GBM_SYSTEM_PARAMETER")
@DynamicUpdate
public class NpsSystemParameter implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal id;

    /**
     * The parameter id.
     */
    @Column(name = "PARAMETER_ID")
    private String parameterId;

    /**
     * The parameter desc.
     */
    @Column(name = "PARAMETER_DESC")
    private String parameterDesc;

    /**
     * The parameter value.
     */
    @Column(name = "PARAMETER_VALUE")
    private String parameterValue;

    /**
     * The parameter type.
     */
    @Column(name = "PARAMETER_TYPE")
    private Character parameterType;

    /**
     * The verified.
     */
    @Column(name = "IS_VERIFIED")
    private Character verified;

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
     * The last modified by.
     */
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;

    /**
     * The last modified date.
     */
    @Column(name = "LAST_MODIFIED_DATE")
    private Date lastModifiedDate;

    /**
     * The auditable.
     */
    @Column(name = "AUDITABLE")
    private Boolean auditable;

    @Column(name = "SCH_CODE")
    private String schCode;

    public String getSchCode() {
        return schCode;
    }

    public void setSchCode(String schCode) {
        this.schCode = schCode;
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
     * Gets the id.
     *
     * @return the id
     */
    public BigDecimal getId() {
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
     * Gets the parameter desc.
     *
     * @return the parameter desc
     */
    public String getParameterDesc() {
        return this.parameterDesc;
    }

    /**
     * Gets the parameter id.
     *
     * @return the parameter id
     */
    public String getParameterId() {
        return this.parameterId;
    }

    /**
     * Gets the parameter type.
     *
     * @return the parameter type
     */
    public Character getParameterType() {
        return this.parameterType;
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
     * Gets the verified.
     *
     * @return the verified
     */
    public Character getVerified() {
        return this.verified;
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
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final BigDecimal id) {
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
     * Sets the parameter desc.
     *
     * @param parameterDesc the new parameter desc
     */
    public void setParameterDesc(final String parameterDesc) {
        this.parameterDesc = parameterDesc;
    }

    /**
     * Sets the parameter id.
     *
     * @param parameterId the new parameter id
     */
    public void setParameterId(final String parameterId) {
        this.parameterId = parameterId;
    }

    /**
     * Sets the parameter type.
     *
     * @param parameterType the new parameter type
     */
    public void setParameterType(final Character parameterType) {
        this.parameterType = parameterType;
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
     * Sets the verified.
     *
     * @param verified the new verified
     */
    public void setVerified(final Character verified) {
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
