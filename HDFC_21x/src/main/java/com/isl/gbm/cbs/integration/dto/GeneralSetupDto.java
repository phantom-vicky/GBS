/**
 *
 */
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * The Class GeneralSetupDto.
 *
 * @author harishbabu.t
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralSetupDto {

    /** The id. */
    private int id;

    /** The parameter name. */
    private String parameterName;

    /** The parameter value. */
    private String parameterValue;

    /** The created by. */
    private String createdBy;

    /** The created date. */
    private Date createdDate;

    /** The modified by. */
    private String modifiedBy;

    /** The modified date. */
    private Date modifiedDate;

    /** The verified. */
    private Boolean isVerified;

    /** The verified by. */
    private String verifiedBy;

    /** The verified date. */
    private Date verifiedDate;

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
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    /**
     * @return the isVerified
     */
    public Boolean getIsVerified() {
        return this.isVerified;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return this.modifiedDate;
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
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @param isVerified
     *            the isVerified to set
     */
    public void setIsVerified(final Boolean isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * @param modifiedBy
     *            the modifiedBy to set
     */
    public void setModifiedBy(final String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @param modifiedDate
     *            the modifiedDate to set
     */
    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * @param parameterName
     *            the parameterName to set
     */
    public void setParameterName(final String parameterName) {
        this.parameterName = parameterName;
    }

    /**
     * @param parameterValue
     *            the parameterValue to set
     */
    public void setParameterValue(final String parameterValue) {
        this.parameterValue = parameterValue;
    }

    /**
     * @param verifiedBy
     *            the verifiedBy to set
     */
    public void setVerifiedBy(final String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    /**
     * @param verifiedDate
     *            the verifiedDate to set
     */
    public void setVerifiedDate(final Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

}
