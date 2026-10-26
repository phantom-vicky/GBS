package com.isl.gbm.user.management.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * The Class UserParamDto.
 */
public class UserParamDto {

    /**
     * The id.
     */
    private Integer id;
    private Integer noOfRetries;
    private Integer maxAccPeriod;

    public Integer getNoOfRetries() {
        return noOfRetries;
    }

    public void setNoOfRetries(Integer noOfRetries) {
        this.noOfRetries = noOfRetries;
    }

    public Integer getMaxAccPeriod() {
        return maxAccPeriod;
    }

    public void setMaxAccPeriod(Integer maxAccPeriod) {
        this.maxAccPeriod = maxAccPeriod;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getVerifiedDate() {
        return verifiedDate;
    }

    public void setVerifiedDate(String verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    public String getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(String verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    private String createdBy;
    private String createdDate;
    private String verifiedBy;
    private String verifiedDate;
    private String verifiedStatus;
    private String remarks;
    private int dormancyPeriod1;
    private int activationPeriod1;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getDormancyPeriod1() {
        return dormancyPeriod1;
    }

    public void setDormancyPeriod1(int dormancyPeriod1) {
        this.dormancyPeriod1 = dormancyPeriod1;
    }

    public int getActivationPeriod1() {
        return activationPeriod1;
    }

    public void setActivationPeriod1(int activationPeriod1) {
        this.activationPeriod1 = activationPeriod1;
    }

    public int getNoOfRetries1() {
        return noOfRetries1;
    }

    public void setNoOfRetries1(int noOfRetries1) {
        this.noOfRetries1 = noOfRetries1;
    }

    public int getMaxAccPeriod1() {
        return maxAccPeriod1;
    }

    public void setMaxAccPeriod1(int maxAccPeriod1) {
        this.maxAccPeriod1 = maxAccPeriod1;
    }

    public String getPerviousOperation() {
        return perviousOperation;
    }

    public void setPerviousOperation(String perviousOperation) {
        this.perviousOperation = perviousOperation;
    }

    private int noOfRetries1;
    private int maxAccPeriod1;
    private String perviousOperation;

    /**
     * The password age.
     */
    @NotNull(message = "Password age cannot be blank")
    private Integer passwordAge;

    /**
     * The dormancy period.
     */
    @NotNull(message = "Dormancy period cannot be blank")
    private Integer dormancyPeriod;

    /**
     * The activation period.
     */
    @NotNull(message = "Activation period cannot be blank")
    private Integer activationPeriod;

    /**
     * The old password exclusion count.
     */
    private Integer oldPasswordExclusionCount;

    /**
     * The allow old password.
     */
    private Boolean allowOldPassword;

    /**
     * The last modified date.
     */
    private String lastModifiedDate;

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * The last modified by.
     */
    private String lastModifiedBy;

    @AssertTrue(message = "Please specify the old password exclusion count")
    private boolean isValid() {
        return allowOldPassword ? true : (oldPasswordExclusionCount > 0);
    }

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
    public Boolean getAllowOldPassword() {
        return allowOldPassword;
    }

    /**
     * Sets the allow old password.
     *
     * @param allowOldPassword the new allow old password
     */
    public void setAllowOldPassword(Boolean allowOldPassword) {
        this.allowOldPassword = allowOldPassword;
    }

    /**
     * Gets the last modified date.
     *
     * @return the last modified date
     */


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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserParamDto [id=" + id + ", passwordAge=" + passwordAge + ", dormancyPeriod=" + dormancyPeriod
                + ", activationPeriod=" + activationPeriod + ", oldPasswordExclusionCount=" + oldPasswordExclusionCount
                + ", allowOldPassword=" + allowOldPassword + ", lastModifiedDate=" + lastModifiedDate
                + ", lastModifiedBy=" + lastModifiedBy + "]";
    }

}
