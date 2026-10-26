package com.isl.gbm.user.management.model;

import com.isl.gbm.common.util.CommonConstants;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * The Class User.
 */
@Entity
@Table(name = "GBM_USER_MGMT_USERS")
@DynamicUpdate
@SelectBeforeUpdate
public class User implements Serializable {

    /**
     * The id.
     */
    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "usersSequence", sequenceName = "SEQ_USER_MGMT_USERS", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersSequence")
    private Integer id;

    /**
     * The first name.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The application name.
     */
    @Column(name = "application_name")
    private String applicationName;

    /**
     * The sol id.
     */
    @Column(name = "sol_id")
    private String solId;

    /**
     * The created by user.
     */
    @Column(name = "created_by")
    private String createdByUser;

    /**
     * The created date.
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * The modified by user.
     */
    @Column(name = "modified_by")
    private String modifiedByUser;

    /**
     * The modified date.
     */
    @Column(name = "modified_date")
    private Date modifiedDate;

    /**
     * The verified by user.
     */
    @Column(name = "verified_by")
    private String verifiedByUser;

    /**
     * The verified date.
     */
    @Column(name = "verified_date")
    private Date verifiedDate;

    /**
     * The employee code.
     */
    @Column(name = "employee_code")
    private String employeeCode;

    /**
     * The location code.
     */
    @Column(name = "location_code")
    private String locationCode;

    /**
     * The department code.
     */
    @Column(name = "department_code")
    private String departmentCode;

    /**
     * The supervisor code.
     */
    @Column(name = "supervisor_code")
    private String supervisorCode;

    /**
     * The username.
     */
    @Column(name = "username")
    private String username;

    /**
     * The password.
     */
    @Column(name = "password")
    private String password;

    /**
     * The deleted.
     */
    @Column(name = "deleted")
    private Integer deleted;

    /**
     * The activation time.
     */
    @Column(name = "activation_time")
    private Date activationTime;

    /**
     * The status.
     */
    @Column(name = "status")
    private String status;

    /**
     * The user type.
     */
    @Column(name = "user_type")
    private String userType;

    /**
     * The password modified date.
     */
    @Column(name = "password_modified_date")
    private Date passwordModifiedDate;

    /**
     * The password expiry date.
     */
    @Column(name = "password_expiry_date")
    private Date passwordExpiryDate;

    /**
     * The user expiry date.
     */
    @Column(name = "user_expiry_date")
    private Date userExpiryDate;

    /**
     * The user disabled till.
     */
    @Column(name = "user_disabled_till")
    private Date userDisabledTill;

    /**
     * The permissions.
     */
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "GBM_USER_MGMT_USER_ROLE", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    /**
     * The consecutive invalid login attempts.
     */
    @Column(name = "invalid_login_count")
    private Integer invalidLoginCount;

    /**
     * The last login attempt.
     */
    @Column(name = "last_login_attempt")
    private Date lastLoginAttempt;

    /**
     * The user domain.
     */
    @Column(name = "user_domain")
    private String userDomain;

    /**
     * The last success login.
     */
    @Column(name = "last_success_login")
    private Date lastSuccessLogin;

    /**
     *  Indicates whether user logged in at given time
     */
    @Column(name = "logged_in_flg", nullable = false)
    private Character loggedInFlag = CommonConstants.NO;

    @Column(name = "VERIFIED_STATUS", nullable = false)
    private Character verifiedStatus;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Character isActive ;

    @Column(name = "AUDIT_CRE_FLG", nullable = false)
    private Character aditCreFlg ;

    public Character getVerifiedStatus() {
        return verifiedStatus;
    }

    public void setVerifiedStatus(Character verifiedStatus) {
        this.verifiedStatus = verifiedStatus;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    public Character getAditCreFlg() {
        return aditCreFlg;
    }

    public void setAditCreFlg(Character aditCreFlg) {
        this.aditCreFlg = aditCreFlg;
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
     * Gets the roles.
     *
     * @return the roles
     */
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles.
     *
     * @param roles the new roles
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    /**
     * Gets the last success login.
     *
     * @return the last success login
     */
    public Date getLastSuccessLogin() {
        return lastSuccessLogin;
    }

    /**
     * Sets the last success login.
     *
     * @param lastSuccessLogin the new last success login
     */
    public void setLastSuccessLogin(Date lastSuccessLogin) {
        this.lastSuccessLogin = lastSuccessLogin;
    }


    public Character getLoggedInFlag() {
        return loggedInFlag;
    }

    public void setLoggedInFlag(Character loggedInFlag) {
        this.loggedInFlag = loggedInFlag;
    }
}
