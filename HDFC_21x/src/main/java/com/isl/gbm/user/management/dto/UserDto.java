package com.isl.gbm.user.management.dto;

import com.isl.gbm.user.management.security.shiro.config.ValidPassword;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;


/**
 * The Class UserDto.
 */
public class UserDto {

    /**
     * The id.
     */
    private Integer id;

    /**
     * The first name.
     */
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    /**
     * The last name.
     */
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    /**
     * The application name.
     */
    @NotBlank(message = "Application name cannot be empty")
    private String applicationName;

    /**
     * The sol id.
     */
    @NotBlank(message = "Sol ID cannot be empty")
    private String solId;

    /**
     * The created by user.
     */
    private String createdByUser;

    /**
     * The created date.
     */
    private Date createdDate;

    /**
     * The modified by user.
     */
    private String modifiedByUser;

    /**
     * The modified date.
     */
    private Date modifiedDate;

    /**
     * The verified by user.
     */
    private String verifiedByUser;

    /**
     * The verified date.
     */
    private Date verifiedDate;

    /**
     * The employee code.
     */
    @NotBlank(message = "Employee code cannot be empty")
    private String employeeCode;

    /**
     * The location code.
     */
    @NotBlank(message = "Location code cannot be empty")
    private String locationCode;

    /**
     * The department code.
     */
   // @NotBlank(message = "Department code cannot be empty")
    private String departmentCode;

    /**
     * The supervisor code.
     */
    //@NotBlank(message = "Supervisor code cannot be empty")
    private String supervisorCode;

    /**
     * The username.
     */
    @NotBlank(message = "Username cannot be empty")
    private String username;

    /**
     * The password.
     */
   @ValidPassword
    private String password;

    /**
     * The deleted.
     */
    private Integer deleted;

    /**
     * The activation time.
     */
    private Date activationTime;

    /**
     * The status.
     */
    private String status;

    /**
     * The user type.
     */
    @NotBlank(message = "User Type cannot be empty")
    private String userType;

    /**
     * The password modified date.
     */
    private Date passwordModifiedDate;

    /**
     * The password expiry date.
     */
    private Date passwordExpiryDate;

    /**
     * The user expiry date.
     */
    private Date userExpiryDate;

    /**
     * The user disabled till.
     */
    private Date userDisabledTill;

    /**
     * The consecutive invalid login attempts.
     */
    private Integer invalidLoginCount;

    /**
     * The last login attempt.
     */
    private Date lastLoginAttempt;

    /**
     * The user domain.
     */
    @NotBlank(message = "User Domain cannot be empty")
    private String userDomain;

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
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the application name.
     *
     * @return the application name
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Sets the application name.
     *
     * @param applicationName the new application name
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
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
     * Gets the created by user.
     *
     * @return the created by user
     */
    public String getCreatedByUser() {
        return createdByUser;
    }

    /**
     * Sets the created by user.
     *
     * @param createdByUser the new created by user
     */
    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the modified by user.
     *
     * @return the modified by user
     */
    public String getModifiedByUser() {
        return modifiedByUser;
    }

    /**
     * Sets the modified by user.
     *
     * @param modifiedByUser the new modified by user
     */
    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the new modified date
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the verified by user.
     *
     * @return the verified by user
     */
    public String getVerifiedByUser() {
        return verifiedByUser;
    }

    /**
     * Sets the verified by user.
     *
     * @param verifiedByUser the new verified by user
     */
    public void setVerifiedByUser(String verifiedByUser) {
        this.verifiedByUser = verifiedByUser;
    }

    /**
     * Gets the verified date.
     *
     * @return the verified date
     */
    public Date getVerifiedDate() {
        return verifiedDate;
    }

    /**
     * Sets the verified date.
     *
     * @param verifiedDate the new verified date
     */
    public void setVerifiedDate(Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    /**
     * Gets the employee code.
     *
     * @return the employee code
     */
    public String getEmployeeCode() {
        return employeeCode;
    }

    /**
     * Sets the employee code.
     *
     * @param employeeCode the new employee code
     */
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * Gets the location code.
     *
     * @return the location code
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Sets the location code.
     *
     * @param locationCode the new location code
     */
    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    /**
     * Gets the department code.
     *
     * @return the department code
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * Sets the department code.
     *
     * @param departmentCode the new department code
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    /**
     * Gets the supervisor code.
     *
     * @return the supervisor code
     */
    public String getSupervisorCode() {
        return supervisorCode;
    }

    /**
     * Sets the supervisor code.
     *
     * @param supervisorCode the new supervisor code
     */
    public void setSupervisorCode(String supervisorCode) {
        this.supervisorCode = supervisorCode;
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

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the deleted.
     *
     * @return the deleted
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * Gets the activation time.
     *
     * @return the activation time
     */
    public Date getActivationTime() {
        return activationTime;
    }

    /**
     * Sets the activation time.
     *
     * @param activationTime the new activation time
     */
    public void setActivationTime(Date activationTime) {
        this.activationTime = activationTime;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the user type.
     *
     * @return the user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the user type.
     *
     * @param userType the new user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Gets the password modified date.
     *
     * @return the password modified date
     */
    public Date getPasswordModifiedDate() {
        return passwordModifiedDate;
    }

    /**
     * Sets the password modified date.
     *
     * @param passwordModifiedDate the new password modified date
     */
    public void setPasswordModifiedDate(Date passwordModifiedDate) {
        this.passwordModifiedDate = passwordModifiedDate;
    }

    /**
     * Gets the password expiry date.
     *
     * @return the password expiry date
     */
    public Date getPasswordExpiryDate() {
        return passwordExpiryDate;
    }

    /**
     * Sets the password expiry date.
     *
     * @param passwordExpiryDate the new password expiry date
     */
    public void setPasswordExpiryDate(Date passwordExpiryDate) {
        this.passwordExpiryDate = passwordExpiryDate;
    }

    /**
     * Gets the user expiry date.
     *
     * @return the user expiry date
     */
    public Date getUserExpiryDate() {
        return userExpiryDate;
    }

    /**
     * Sets the user expiry date.
     *
     * @param userExpiryDate the new user expiry date
     */
    public void setUserExpiryDate(Date userExpiryDate) {
        this.userExpiryDate = userExpiryDate;
    }

    /**
     * Gets the user disabled till.
     *
     * @return the user disabled till
     */
    public Date getUserDisabledTill() {
        return userDisabledTill;
    }

    /**
     * Sets the user disabled till.
     *
     * @param userDisabledTill the new user disabled till
     */
    public void setUserDisabledTill(Date userDisabledTill) {
        this.userDisabledTill = userDisabledTill;
    }

    /**
     * Gets the invalid login count.
     *
     * @return the invalid login count
     */
    public Integer getInvalidLoginCount() {
        return invalidLoginCount;
    }

    /**
     * Sets the invalid login count.
     *
     * @param invalidLoginCount the new invalid login count
     */
    public void setInvalidLoginCount(Integer invalidLoginCount) {
        this.invalidLoginCount = invalidLoginCount;
    }

    /**
     * Gets the last login attempt.
     *
     * @return the last login attempt
     */
    public Date getLastLoginAttempt() {
        return lastLoginAttempt;
    }

    /**
     * Sets the last login attempt.
     *
     * @param lastLoginAttempt the new last login attempt
     */
    public void setLastLoginAttempt(Date lastLoginAttempt) {
        this.lastLoginAttempt = lastLoginAttempt;
    }

    /**
     * Gets the user domain.
     *
     * @return the user domain
     */
    public String getUserDomain() {
        return userDomain;
    }

    /**
     * Sets the user domain.
     *
     * @param userDomain the new user domain
     */
    public void setUserDomain(String userDomain) {
        this.userDomain = userDomain;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", applicationName="
                + applicationName + ", solId=" + solId + ", createdByUser=" + createdByUser + ", createdDate="
                + createdDate + ", modifiedByUser=" + modifiedByUser + ", modifiedDate=" + modifiedDate
                + ", verifiedByUser=" + verifiedByUser + ", verifiedDate=" + verifiedDate + ", employeeCode="
                + employeeCode + ", locationCode=" + locationCode + ", departmentCode=" + departmentCode
                + ", supervisorCode=" + supervisorCode + ", username=" + username + ", password=" + password
                + ", deleted=" + deleted + ", activationTime=" + activationTime + ", status=" + status + ", userType="
                + userType + ", passwordModifiedDate=" + passwordModifiedDate + ", passwordExpiryDate="
                + passwordExpiryDate + ", userExpiryDate=" + userExpiryDate + ", userDisabledTill=" + userDisabledTill
                + ", invalidLoginCount=" + invalidLoginCount + ", lastLoginAttempt=" + lastLoginAttempt
                + ", userDomain=" + userDomain + "]";
    }

}
