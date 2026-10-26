/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * The Class GbmUserDetailsDto.
 */
@Entity
@Table(name = "gbm_user_details")
public class GbmUserDetails implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_WORK_DETAILS")
    @SequenceGenerator(name = "SEQ_USER_WORK_DETAILS", sequenceName = "SEQ_USER_WORK_DETAILS", allocationSize = 1)
    private Integer id;

    /**
     * The user id.
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * The user password.
     */
    @Column(name = "user_pw")
    private String userPassword;

    /**
     * The user work class.
     */
    @Column(name = "user_work_class")
    private String userWorkClass;

    /**
     * The sol id.
     */
    @Column(name = "sol_id")
    private String solId;

    /**
     * The user sol tenor.
     */
    @Column(name = "user_sol_tenor")
    private Character userSolTenor;

    /**
     * The user max inactive time.
     */
    @Column(name = "user_max_inactive_time")
    private Integer userMaxInactiveTime;

    /**
     * The user account expiry date.
     */
    @Column(name = "user_acct_expy_date")
    private Date userAccountExpiryDate;

    /**
     * The user password expiry date.
     */
    @Column(name = "user_pw_expy_date")
    private Date userPasswordExpiryDate;

    /**
     * The user application name.
     */
    @Column(name = "user_appl_name")
    private String userApplicationName;

    /**
     * The new user flag.
     */
    @Column(name = "new_user_flg")
    private Boolean newUserFlag;

    /**
     * The default term id.
     */
    @Column(name = "default_term_id")
    private String defaultTermId;

    /**
     * The virtual flag.
     */
    @Column(name = "virtual_flg")
    private Boolean virtualFlag;

    /**
     * The deleted.
     */
    @Column(name = "del_flg")
    private Boolean deleted;

    /**
     * The created by.
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * The created date.
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * The modified by.
     */
    @Column(name = "modified_by")
    private String modifiedBy;

    /**
     * The modified date.
     */
    @Column(name = "modified_date")
    private Date modifiedDate;

    /**
     * The verified.
     */
    @Column(name = "is_verified")
    private Boolean verified;

    /**
     * The verified by.
     */
    @Column(name = "verified_by")
    private String verifiedBy;

    /**
     * The verified date.
     */
    @Column(name = "verified_date")
    private Date verifiedDate;

    /**
     * The file name.
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * The additional text 1.
     */
    @Column(name = "addl_text1")
    private String additionalText1;

    /**
     * The additional text 2.
     */
    @Column(name = "addl_text2")
    private String additionalText2;

    /**
     * The additional text 3.
     */
    @Column(name = "addl_text3")
    private String additionalText3;


    @Column(name = "auditable")
    private Boolean auditable = Boolean.FALSE;

    public Boolean isAuditable() {
        return auditable;
    }

    public void setAuditable(Boolean auditable) {
        this.auditable = auditable;
    }

    /**
     * Gets the additional text 1.
     *
     * @return the additional text 1
     */
    public String getAdditionalText1() {
        return this.additionalText1;
    }

    /**
     * Gets the additional text 2.
     *
     * @return the additional text 2
     */
    public String getAdditionalText2() {
        return this.additionalText2;
    }

    /**
     * Gets the additional text 3.
     *
     * @return the additional text 3
     */
    public String getAdditionalText3() {
        return this.additionalText3;
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
     * Gets the default term id.
     *
     * @return the default term id
     */
    public String getDefaultTermId() {
        return this.defaultTermId;
    }

    /**
     * Gets the deleted.
     *
     * @return the deleted
     */
    public Boolean getDeleted() {
        return this.deleted;
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Integer getId() {
        return this.id;
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
     * Gets the new user flag.
     *
     * @return the new user flag
     */
    public Boolean getNewUserFlag() {
        return this.newUserFlag;
    }

    /**
     * Gets the sol id.
     *
     * @return the sol id
     */
    public String getSolId() {
        return this.solId;
    }

    /**
     * Gets the user account expiry date.
     *
     * @return the user account expiry date
     */
    public Date getUserAccountExpiryDate() {
        return this.userAccountExpiryDate;
    }

    /**
     * Gets the user application name.
     *
     * @return the user application name
     */
    public String getUserApplicationName() {
        return this.userApplicationName;
    }

    /**
     * Gets the user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return this.userId;
    }

    /**
     * Gets the user max inactive time.
     *
     * @return the user max inactive time
     */
    public Integer getUserMaxInactiveTime() {
        return this.userMaxInactiveTime;
    }

    /**
     * Gets the user password.
     *
     * @return the user password
     */
    public String getUserPassword() {
        return this.userPassword;
    }

    /**
     * Gets the user password expiry date.
     *
     * @return the user password expiry date
     */
    public Date getUserPasswordExpiryDate() {
        return this.userPasswordExpiryDate;
    }

    /**
     * Gets the user sol tenor.
     *
     * @return the user sol tenor
     */
    public Character getUserSolTenor() {
        return this.userSolTenor;
    }

    /**
     * Gets the user work class.
     *
     * @return the user work class
     */
    public String getUserWorkClass() {
        return this.userWorkClass;
    }

    /**
     * Gets the verified.
     *
     * @return the verified
     */
    public Boolean getVerified() {
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
     * Gets the virtual flag.
     *
     * @return the virtual flag
     */
    public Boolean getVirtualFlag() {
        return this.virtualFlag;
    }

    /**
     * Sets the additional text 1.
     *
     * @param additionalText1 the new additional text 1
     */
    public void setAdditionalText1(final String additionalText1) {
        this.additionalText1 = additionalText1;
    }

    /**
     * Sets the additional text 2.
     *
     * @param additionalText2 the new additional text 2
     */
    public void setAdditionalText2(final String additionalText2) {
        this.additionalText2 = additionalText2;
    }

    /**
     * Sets the additional text 3.
     *
     * @param additionalText3 the new additional text 3
     */
    public void setAdditionalText3(final String additionalText3) {
        this.additionalText3 = additionalText3;
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
     * Sets the default term id.
     *
     * @param defaultTermId the new default term id
     */
    public void setDefaultTermId(final String defaultTermId) {
        this.defaultTermId = defaultTermId;
    }

    /**
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Sets the file name.
     *
     * @param fileName the new file name
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Integer id) {
        this.id = id;
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
     * Sets the new user flag.
     *
     * @param newUserFlag the new new user flag
     */
    public void setNewUserFlag(final Boolean newUserFlag) {
        this.newUserFlag = newUserFlag;
    }

    /**
     * Sets the sol id.
     *
     * @param solId the new sol id
     */
    public void setSolId(final String solId) {
        this.solId = solId;
    }

    /**
     * Sets the user account expiry date.
     *
     * @param userAccountExpiryDate the new user account expiry date
     */
    public void setUserAccountExpiryDate(final Date userAccountExpiryDate) {
        this.userAccountExpiryDate = userAccountExpiryDate;
    }

    /**
     * Sets the user application name.
     *
     * @param userApplicationName the new user application name
     */
    public void setUserApplicationName(final String userApplicationName) {
        this.userApplicationName = userApplicationName;
    }

    /**
     * Sets the user id.
     *
     * @param userId the new user id
     */
    public void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * Sets the user max inactive time.
     *
     * @param userMaxInactiveTime the new user max inactive time
     */
    public void setUserMaxInactiveTime(final Integer userMaxInactiveTime) {
        this.userMaxInactiveTime = userMaxInactiveTime;
    }

    /**
     * Sets the user password.
     *
     * @param userPassword the new user password
     */
    public void setUserPassword(final String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Sets the user password expiry date.
     *
     * @param userPasswordExpiryDate the new user password expiry date
     */
    public void setUserPasswordExpiryDate(final Date userPasswordExpiryDate) {
        this.userPasswordExpiryDate = userPasswordExpiryDate;
    }

    /**
     * Sets the user sol tenor.
     *
     * @param userSolTenor the new user sol tenor
     */
    public void setUserSolTenor(final Character userSolTenor) {
        this.userSolTenor = userSolTenor;
    }

    /**
     * Sets the user work class.
     *
     * @param userWorkClass the new user work class
     */
    public void setUserWorkClass(final String userWorkClass) {
        this.userWorkClass = userWorkClass;
    }

    /**
     * Sets the verified.
     *
     * @param verified the new verified
     */
    public void setVerified(final Boolean verified) {
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

    /**
     * Sets the virtual flag.
     *
     * @param virtualFlag the new virtual flag
     */
    public void setVirtualFlag(final Boolean virtualFlag) {
        this.virtualFlag = virtualFlag;
    }

}
