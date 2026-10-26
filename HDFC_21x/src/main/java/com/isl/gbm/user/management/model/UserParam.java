package com.isl.gbm.user.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

/**
 * The Class UserParam.
 */
@Entity
@Table(name = "GBM_USER_MGMT_USER_PARAM")
public class UserParam {

    /**
     * The id.
     */
    @Id
    @Column(name = "id", updatable = false)
    private Integer id;

    /**
     * The password age.
     */
    @Column(name = "password_age")
    private Integer passwordAge;

    /**
     * The dormancy period.
     */
    @Column(name = "dormancy_period")
    private Integer dormancyPeriod;

    @Column(name = "max_accept_period")
    private Integer maxAcceptPeriod;

    /**
     * The activation period.
     */
    @Column(name = "activation_period")
    private Integer activationPeriod;

    /**
     * The old password exclusion count.
     */
    @Column(name = "old_pwd_exclsn_count")
    private Integer oldPasswordExclusionCount;

    /**
     * The allow old password.
     */
    @Column(name = "allow_old_password")
    private Integer allowOldPassword;

    /**
     * The last modified date.
     */
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    /**
     * The last modified by.
     */
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the password age.
     *
     * @return the password age
     */
    public Integer getPasswordAge() {
        return passwordAge;
    }

    /**
     * Sets the password age.
     *
     * @param passwordAge the new password age
     */
    public void setPasswordAge(Integer passwordAge) {
        this.passwordAge = passwordAge;
    }

    /**
     * Gets the dormancy period.
     *
     * @return the dormancy period
     */
    public Integer getDormancyPeriod() {
        return dormancyPeriod;
    }

    /**
     * Sets the dormancy period.
     *
     * @param dormancyPeriod the new dormancy period
     */
    public void setDormancyPeriod(Integer dormancyPeriod) {
        this.dormancyPeriod = dormancyPeriod;
    }

    /**
     * Gets the activation period.
     *
     * @return the activation period
     */
    public Integer getActivationPeriod() {
        return activationPeriod;
    }

    /**
     * Sets the activation period.
     *
     * @param activationPeriod the new activation period
     */
    public void setActivationPeriod(Integer activationPeriod) {
        this.activationPeriod = activationPeriod;
    }

    /**
     * Gets the old password exclusion count.
     *
     * @return the old password exclusion count
     */
    public Integer getOldPasswordExclusionCount() {
        return oldPasswordExclusionCount;
    }

    /**
     * Sets the old password exclusion count.
     *
     * @param oldPasswordExclusionCount the new old password exclusion count
     */
    public void setOldPasswordExclusionCount(Integer oldPasswordExclusionCount) {
        this.oldPasswordExclusionCount = oldPasswordExclusionCount;
    }

    /**
     * Gets the allow old password.
     *
     * @return the allow old password
     */
    public Integer getAllowOldPassword() {
        return allowOldPassword;
    }

    /**
     * Sets the allow old password.
     *
     * @param allowOldPassword the new allow old password
     */
    public void setAllowOldPassword(Integer allowOldPassword) {
        this.allowOldPassword = allowOldPassword;
    }

    /**
     * Gets the last modified date.
     *
     * @return the last modified date
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * Sets the last modified date.
     *
     * @param lastModifiedDate the new last modified date
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Gets the last modified by.
     *
     * @return the last modified by
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the last modified by.
     *
     * @param lastModifiedBy the new last modified by
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Integer getMaxAcceptPeriod() {
        return maxAcceptPeriod;
    }

    public void setMaxAcceptPeriod(Integer maxAcceptPeriod) {
        this.maxAcceptPeriod = maxAcceptPeriod;
    }
}
