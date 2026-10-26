package com.isl.gbm.common.dto;

import java.math.BigDecimal;
import java.util.Date;

public class GbmSchemeParameterMasterDto {

    private BigDecimal id;

    /**
     * The param name.
     */
    private String paramName;

    /**
     * The param value.
     */
    private String paramValue;

    /**
     * The scheme code.
     */
    private String schmCode;

    /**
     * The is zeo balance.
     */
    private String isZeroBalance = "N";

    /**
     * The eff from date.
     */
    private Date effFromDate;

    /**
     * The eff to date.
     */
    private Date effToDate;

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

    /**
     * The is verified.
     */
    private Character verified;

    /**
     * The is active.
     */
    private Character isActive;

    /**
     * The account title.
     */
    private String accountTitle;

    /**
     * @return the accountTitle
     */
    public String getAccountTitle() {
        return this.accountTitle;
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
     * @return the effFromDate
     */
    public Date getEffFromDate() {
        return this.effFromDate;
    }

    /**
     * @return the effToDate
     */
    public Date getEffToDate() {
        return this.effToDate;
    }

    /**
     * @return the id
     */
    public BigDecimal getId() {
        return this.id;
    }

    /**
     * @return the isActive
     */
    public Character getIsActive() {
        return this.isActive;
    }

    /**
     * @return the isZeroBalance
     */
    public String getIsZeroBalance() {
        return this.isZeroBalance;
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
     * @return the paramName
     */
    public String getParamName() {
        return this.paramName;
    }

    /**
     * @return the paramValue
     */
    public String getParamValue() {
        return this.paramValue;
    }

    /**
     * @return the schmCode
     */
    public String getSchmCode() {
        return this.schmCode;
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
     * @param accountTitle the accountTitle to set
     */
    public void setAccountTitle(final String accountTitle) {
        this.accountTitle = accountTitle;
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
     * @param effFromDate the effFromDate to set
     */
    public void setEffFromDate(final Date effFromDate) {
        this.effFromDate = effFromDate;
    }

    /**
     * @param effToDate the effToDate to set
     */
    public void setEffToDate(final Date effToDate) {
        this.effToDate = effToDate;
    }

    /**
     * @param id the id to set
     */
    public void setId(final BigDecimal id) {
        this.id = id;
    }

    /**
     * @param isActive the isActive to set
     */
    public void setIsActive(final Character isActive) {
        this.isActive = isActive;
    }

    /**
     * @param isZeroBalance the isZeroBalance to set
     */
    public void setIsZeroBalance(final String isZeroBalance) {
        this.isZeroBalance = isZeroBalance;
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
     * @param paramName the paramName to set
     */
    public void setParamName(final String paramName) {
        this.paramName = paramName;
    }

    /**
     * @param paramValue the paramValue to set
     */
    public void setParamValue(final String paramValue) {
        this.paramValue = paramValue;
    }

    /**
     * @param schmCode the schmCode to set
     */
    public void setSchmCode(final String schmCode) {
        this.schmCode = schmCode;
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

}
