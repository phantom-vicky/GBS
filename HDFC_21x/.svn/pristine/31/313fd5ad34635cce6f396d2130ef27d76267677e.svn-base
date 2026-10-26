/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser.model;

import com.isl.gbm.cbs.integration.common.ErrorMessages;
import org.hibernate.validator.constraints.NotBlank;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Size;
import java.util.Date;

/**
 * The Class UserWorkClassExcelRow.
 */
@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class UserWorkClassExcelRow {

    /**
     * The user id.
     */
    @ExcelField(position = 1)
    @NotBlank(message = "User Id should not be blank")
    @Size(max = 15, message = "User Id should not have morethan 15 characters")
    private String userId;

    /**
     * The delete flag.
     */
    @ExcelField(position = 2)
    private String deleteFlag;

    /**
     * The user work class.
     */
    @ExcelField(position = 3)
    @NotBlank(message = "User work class should not be blank")
    @Size(max = 3, message = "User work class should not have morethan 3 characters")
    private String userWorkClass;

    /**
     * The sol id.
     */
    @ExcelField(position = 4)
    @NotBlank(message = "Sol Id should not be blank")
    @Size(max = 8, message = "Sol Id should not have morethan 8 characters")
    private String solId;

    /**
     * The user sol tenor.
     */
    @ExcelField(position = 5)
    @NotBlank(message = "User sol tenor should not be blank")
    @Size(max = 1, message = "User sol tenor should not have morethan 1 character")
    private String userSolTenor;

    /**
     * The user max inactive time.
     */
    @ExcelField(position = 6)
    private Integer userMaxInactiveTime;

    /**
     * The user account expiry date.
     */
    @ExcelField(position = 7)
    private Date userAccountExpiryDate;

    /**
     * The user password expiry date.
     */
    @ExcelField(position = 8)
    private Date userPasswordExpiryDate;

    /**
     * The user application name.
     */
    @ExcelField(position = 9)
    @NotBlank(message = "User application name should not be blank")
    @Size(max = 2, message = "User application name should not have morethan 2 characters")
    private String userApplicationName;

    /**
     * The new user flag.
     */
    @ExcelField(position = 10)
    private String newUserFlag;

    /**
     * The default term id.
     */
    @ExcelField(position = 11)
    private String defaultTermId;

    /**
     * The virtual flag.
     */
    @ExcelField(position = 12)
    private String virtualFlag;

    /**
     * The additional text 1.
     */
    @ExcelField(position = 13)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT1_MAX_LENGTH_MSG)
    private String additionalText1;

    /**
     * The additional text 2.
     */
    @ExcelField(position = 14)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT2_MAX_LENGTH_MSG)
    private String additionalText2;

    /**
     * The additional text 3.
     */
    @ExcelField(position = 15)
    @Size(max = 40, message = ErrorMessages.ADDL_TEXT3_MAX_LENGTH_MSG)
    private String additionalText3;

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final UserWorkClassExcelRow other = (UserWorkClassExcelRow) obj;

        if (this.userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!this.userId.equals(other.userId)) {
            return false;
        }

        return true;
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
     * Gets the default term id.
     *
     * @return the default term id
     */
    public String getDefaultTermId() {
        return this.defaultTermId;
    }

    /**
     * Gets the delete flag.
     *
     * @return the delete flag
     */
    public String getDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * Gets the new user flag.
     *
     * @return the new user flag
     */
    public String getNewUserFlag() {
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
    public String getUserSolTenor() {
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
     * Gets the virtual flag.
     *
     * @return the virtual flag
     */
    public String getVirtualFlag() {
        return this.virtualFlag;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = (PRIME * result) + ((this.userId == null) ? 0 : this.userId.hashCode());
        return result;
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
     * Sets the default term id.
     *
     * @param defaultTermId the new default term id
     */
    public void setDefaultTermId(final String defaultTermId) {
        this.defaultTermId = defaultTermId;
    }

    /**
     * Sets the delete flag.
     *
     * @param deleteFlag the new delete flag
     */
    public void setDeleteFlag(final String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * Sets the new user flag.
     *
     * @param newUserFlag the new new user flag
     */
    public void setNewUserFlag(final String newUserFlag) {
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
    public void setUserSolTenor(final String userSolTenor) {
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
     * Sets the virtual flag.
     *
     * @param virtualFlag the new virtual flag
     */
    public void setVirtualFlag(final String virtualFlag) {
        this.virtualFlag = virtualFlag;
    }

}
