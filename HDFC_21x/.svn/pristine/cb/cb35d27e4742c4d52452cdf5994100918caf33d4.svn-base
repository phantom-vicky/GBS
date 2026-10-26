package com.isl.gbm.common.model;


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
import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class GbmSchemeParameterMaster.
 */
@Entity
@Table(name = "gbm_scheme_parameter_table")
@DynamicUpdate
@SelectBeforeUpdate
public class GbmSchemeParameterMaster implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GBM_SCHEME_PARAMETER_MASTER")
    @SequenceGenerator(name = "SEQ_GBM_SCHEME_PARAMETER_MASTER", sequenceName = "SEQ_GBM_SCHEME_PARAMETER_MASTER", allocationSize = 1)
    private BigDecimal id;

    /**
     * The scheme code.
     */
    @Column(name = "sch_code")
    private String schemeCode;

    /**
     * The parameter name.
     */
    @Column(name = "param_name")
    private String parameterName;

    /**
     * The paramer value.
     */
    @Column(name = "param_value")
    private String parameterValue;

    /**
     * The zero balance.
     */
    @Column(name = "is_zero_bal")
    private String zeroBalance;

    /**
     * The effective from date.
     */
    @Column(name = "eff_from_date")
    private Date effectiveFromDate;

    /**
     * The effective to date.
     */
    @Column(name = "eff_to_date")
    private Date effectiveToDate;

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
     * The last modified by.
     */
    @Column(name = "last_mod_by", insertable = false)
    private String lastModifiedBy;

    /**
     * The last modified date.
     */
    @Column(name = "last_mod_date", insertable = false)
    private Date lastModifiedDate;

    /**
     * The active.
     */
    @Column(name = "is_active")
    private Character active;

    /**
     * The verified.
     */
    @Column(name = "is_verified")
    private Character verified;

    /**
     * The verified by.
     */
    @Column(name = "verified_by", insertable = false)
    private String verifiedBy;

    /**
     * The verified date.
     */
    @Column(name = "verified_date", insertable = false)
    private Date verifiedDate;

    @Column(name = "AUDITABLE")
    private Boolean auditable;

    public Boolean getAuditable() {
        return auditable;
    }

    public void setAuditable(Boolean auditable) {
        this.auditable = auditable;
    }

    /**
     * @return the active
     */
    public Character getActive() {
        return this.active;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * @return the effectiveFromDate
     */
    public Date getEffectiveFromDate() {
        return this.effectiveFromDate;
    }

    /**
     * @return the effectiveToDate
     */
    public Date getEffectiveToDate() {
        return this.effectiveToDate;
    }

    /**
     * @return the id
     */
    public BigDecimal getId() {
        return this.id;
    }

    /**
     * @return the lastModifiedBy
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * @return the lastModifiedDate
     */
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * @return the parameterName
     */
    public String getParameterName() {
        return this.parameterName;
    }

    /**
     * @return the parameterValue
     */
    public String getParameterValue() {
        return this.parameterValue;
    }

    /**
     * @return the schemeCode
     */
    public String getSchemeCode() {
        return this.schemeCode;
    }

    /**
     * @return the verified
     */
    public Character getVerified() {
        return this.verified;
    }

    /**
     * @return the verifiedBy
     */
    public String getVerifiedBy() {
        return this.verifiedBy;
    }

    /**
     * @return the verifiedDate
     */
    public Date getVerifiedDate() {
        return this.verifiedDate;
    }

    /**
     * @return the zeroBalance
     */
    public String getZeroBalance() {
        return this.zeroBalance;
    }

    /**
     * @param active the active to set
     */
    public void setActive(final Character active) {
        this.active = active;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @param effectiveFromDate the effectiveFromDate to set
     */
    public void setEffectiveFromDate(final Date effectiveFromDate) {
        this.effectiveFromDate = effectiveFromDate;
    }

    /**
     * @param effectiveToDate the effectiveToDate to set
     */
    public void setEffectiveToDate(final Date effectiveToDate) {
        this.effectiveToDate = effectiveToDate;
    }

    /**
     * @param id the id to set
     */
    public void setId(final BigDecimal id) {
        this.id = id;
    }

    /**
     * @param lastModifiedBy the lastModifiedBy to set
     */
    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @param lastModifiedDate the lastModifiedDate to set
     */
    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * @param parameterName the parameterName to set
     */
    public void setParameterName(final String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * @param parameterValue the parameterValue to set
     */
    public void setParameterValue(final String parameterValue) {
        this.parameterValue = parameterValue;
    }

    /**
     * @param schemeCode the schemeCode to set
     */
    public void setSchemeCode(final String schemeCode) {
        this.schemeCode = schemeCode;
    }

    /**
     * @param verified the verified to set
     */
    public void setVerified(final Character verified) {
        this.verified = verified;
    }

    /**
     * @param verifiedBy the verifiedBy to set
     */
    public void setVerifiedBy(final String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    /**
     * @param verifiedDate the verifiedDate to set
     */
    public void setVerifiedDate(final Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    /**
     * @param zeroBalance the zeroBalance to set
     */
    public void setZeroBalance(final String zeroBalance) {
        this.zeroBalance = zeroBalance;
    }

}
