package com.isl.gbm.user.management.dto;

import java.util.Date;
import java.util.List;

/**
 * The Class ReportSearchCriteriaDto.
 */
public class ReportSearchCriteriaDto {

    /**
     * The sol id.
     */
    private String solId;

    /**
     * The user id.
     */
    private Integer userId;

    /**
     * The from date.
     */
    private Date fromDate;

    /**
     * The to date.
     */
    private Date toDate;

    public List<ReportSearchCriteriaDto> getUserList() {
        return userList;
    }

    public void setUserList(List<ReportSearchCriteriaDto> userList) {
        this.userList = userList;
    }

    /**
     * The username.
     */
    private String username;
    private String branchFlag;
    private String branchID;
    private String userName;
    private String firstName;
    private String locationCode;
    private String applicationName;

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    private String userDomain;
    private List<ReportSearchCriteriaDto> userList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEntityRole() {
        return entityRole;
    }

    public void setEntityRole(String entityRole) {
        this.entityRole = entityRole;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getAuthorizedDateTime() {
        return authorizedDateTime;
    }

    public void setAuthorizedDateTime(String authorizedDateTime) {
        this.authorizedDateTime = authorizedDateTime;
    }

    public String getLastLoginAttempt() {
        return lastLoginAttempt;
    }

    public void setLastLoginAttempt(String lastLoginAttempt) {
        this.lastLoginAttempt = lastLoginAttempt;
    }

    private String lastName;
    private String userType;
    private String entityRole;
    private String expiryDate;
    private String status;
    private String created;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String verifiedBy;
    private String authorizedDateTime;
    private String lastLoginAttempt;

    public String getBranchFlag() {
        return branchFlag;
    }

    public void setBranchFlag(String branchFlag) {
        this.branchFlag = branchFlag;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    /**
     * Gets the sol id.
     *
     * @return the sol id
     */
    public String getSolId() {
        return solId;
    }

    /**
     * Sets the sol id.
     *
     * @param solId the new sol id
     */
    public void setSolId(String solId) {
        this.solId = solId;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the from date.
     *
     * @return the from date
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * Sets the from date.
     *
     * @param fromDate the new from date
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * Gets the to date.
     *
     * @return the to date
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     * Sets the to date.
     *
     * @param toDate the new to date
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ReportSearchCriteriaDto [solId=" + solId + ", userId=" + userId + ", fromDate=" + fromDate + ", toDate="
                + toDate + ", username=" + username + "]";
    }

}
