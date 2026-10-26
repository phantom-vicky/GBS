/**
 *
 */
package com.isl.gbm.cbs.integration.model;


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
import java.util.Date;

/**
 * The Class CalendarEntries.
 *
 * @author harishbabu.t
 */
@Entity
@Table(name = "GBM_CALENDAR_ENTRIES")
@DynamicUpdate
@SelectBeforeUpdate
public class CalendarEntries implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The Id. */
    @Id
    @Column(name = "ID")
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OTHER_BANK_DETAILS")
    //@SequenceGenerator(name = "SEQ_OTHER_BANK_DETAILS", sequenceName = "SEQ_OTHER_BANK_DETAILS", allocationSize = 1)
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GBM_CALENDAR_ENTRIES")
    @SequenceGenerator(name = "SEQ_GBM_CALENDAR_ENTRIES", sequenceName = "SEQ_GBM_CALENDAR_ENTRIES", allocationSize = 1)
    private Integer id;

    /** The calendar type. */
    @Column(name = "CAL_B2K_TYPE")
    private String calendarType;

    /** The calendar code. */
    @Column(name = "CAL_B2K_ID")
    private String calendarCode;

    /** The month year. */
    @Column(name = "MMYYYY")
    private String monthYear;

    /** The holiday string. */
    @Column(name = "HLDY_STR")
    private String holidayString;

    /** The status. */
    @Column(name = "STATUS")
    private Character status;

    /** The delete flag. */
    @Column(name = "DEL_FLG")
    private boolean deleteFlag;

    /** The verified flag. */
    @Column(name = "VER_FLG")
    private boolean verifiedFlag;
    /** The file name. */
    @Column(name = "FILENAME")
    private String fileName;

    /** The created by. */
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;

    /** The created date. */
    @Column(name = "CREATED_DATE", updatable = false)
    private Date createdDate;

    /** The last modified by. */
    @Column(name = "LAST_MOD_BY")
    private String lastModifiedBy;

    /** The last modified date. */
    @Column(name = "LAST_MOD_DATE")
    private Date lastModifiedDate;

    /** The verified by. */
    @Column(name = "VERIFIED_BY")
    private String verifiedBy;

    /** The verified date. */
    @Column(name = "VERIFIED_DATE")
    private Date verifiedDate;

    /** The text 1. */
    @Column(name = "ADDL_TEXT1")
    private String additionalText1;

    /** The text 2. */
    @Column(name = "ADDL_TEXT2")
    private String additionalText2;

    /** The additional text 3. */
    @Column(name = "ADDL_TEXT3")
    private String additionalText3;

    @Column(name = "auditable")
    private Boolean auditable = Boolean.FALSE;

    /** The holiday Remarks. */
    @Column(name = "HLDY_REMARKS")
    private String holidayRemarks;

    public String getHolidayRemarks() {
        return holidayRemarks;
    }

    public void setHolidayRemarks(String holidayRemarks) {
        this.holidayRemarks = holidayRemarks;
    }

    public Boolean isAuditable() {
        return this.auditable;
    }

    public void setAuditable(Boolean auditable) {
        this.auditable = auditable;
    }

    /**
     * Gets the additional text 1.
     *
     * @return the additionalText1
     */
    public String getAdditionalText1() {
        return this.additionalText1;
    }

    /**
     * Gets the additional text 2.
     *
     * @return the additionalText2
     */
    public String getAdditionalText2() {
        return this.additionalText2;
    }

    /**
     * Gets the additional text 3.
     *
     * @return the additionalText3
     */
    public String getAdditionalText3() {
        return this.additionalText3;
    }

    /**
     * Gets the calendar code.
     *
     * @return the calendarCode
     */
    public String getCalendarCode() {
        return this.calendarCode;
    }

    /**
     * Gets the calendar type.
     *
     * @return the calendarType
     */
    public String getCalendarType() {
        return this.calendarType;
    }

    /**
     * Gets the created by.
     *
     * @return the createdBy
     */
    public String getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Gets the created date.
     *
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Gets the file name.
     *
     * @return the fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Gets the holiday string.
     *
     * @return the holidayString
     */
    public String getHolidayString() {
        return this.holidayString;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Gets the last modified by.
     *
     * @return the lastModifiedBy
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Gets the last modified date.
     *
     * @return the lastModifiedDate
     */
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Gets the month year.
     *
     * @return the monthYear
     */
    public String getMonthYear() {
        return this.monthYear;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public Character getStatus() {
        return this.status;
    }

    /**
     * Gets the verified by.
     *
     * @return the verifiedBy
     */
    public String getVerifiedBy() {
        return this.verifiedBy;
    }

    /**
     * Gets the verified date.
     *
     * @return the verifiedDate
     */
    public Date getVerifiedDate() {
        return this.verifiedDate;
    }

    /**
     * Checks if is delete flag.
     *
     * @return the deleteFlag
     */
    public boolean isDeleteFlag() {
        return this.deleteFlag;
    }

    /**
     * Checks if is verified flag.
     *
     * @return the verifiedFlag
     */
    public boolean isVerifiedFlag() {
        return this.verifiedFlag;
    }

    /**
     * Sets the additional text 1.
     *
     * @param additionalText1
     *            the additionalText1 to set
     */
    public void setAdditionalText1(final String additionalText1) {
        this.additionalText1 = additionalText1;
    }

    /**
     * Sets the additional text 2.
     *
     * @param additionalText2
     *            the additionalText2 to set
     */
    public void setAdditionalText2(final String additionalText2) {
        this.additionalText2 = additionalText2;
    }

    /**
     * Sets the additional text 3.
     *
     * @param additionalText3
     *            the additionalText3 to set
     */
    public void setAdditionalText3(final String additionalText3) {
        this.additionalText3 = additionalText3;
    }

    /**
     * Sets the calendar code.
     *
     * @param calendarCode
     *            the calendarCode to set
     */
    public void setCalendarCode(final String calendarCode) {
        this.calendarCode = calendarCode;
    }

    /**
     * Sets the calendar type.
     *
     * @param calendarType
     *            the calendarType to set
     */
    public void setCalendarType(final String calendarType) {
        this.calendarType = calendarType;
    }

    /**
     * Sets the created by.
     *
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Sets the delete flag.
     *
     * @param deleteFlag
     *            the deleteFlag to set
     */
    public void setDeleteFlag(final boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * Sets the file name.
     *
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the holiday string.
     *
     * @param holidayString
     *            the holidayString to set
     */
    public void setHolidayString(final String holidayString) {
        this.holidayString = holidayString;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Sets the last modified by.
     *
     * @param lastModifiedBy
     *            the lastModifiedBy to set
     */
    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Sets the last modified date.
     *
     * @param lastModifiedDate
     *            the lastModifiedDate to set
     */
    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Sets the month year.
     *
     * @param monthYear
     *            the monthYear to set
     */
    public void setMonthYear(final String monthYear) {
        this.monthYear = monthYear;
    }

    /**
     * Sets the status.
     *
     * @param status
     *            the status to set
     */
    public void setStatus(final Character status) {
        this.status = status;
    }

    /**
     * Sets the verified by.
     *
     * @param verifiedBy
     *            the verifiedBy to set
     */
    public void setVerifiedBy(final String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    /**
     * Sets the verified date.
     *
     * @param verifiedDate
     *            the verifiedDate to set
     */
    public void setVerifiedDate(final Date verifiedDate) {
        this.verifiedDate = verifiedDate;
    }

    /**
     * Sets the verified flag.
     *
     * @param verifiedFlag
     *            the verifiedFlag to set
     */
    public void setVerifiedFlag(final boolean verifiedFlag) {
        this.verifiedFlag = verifiedFlag;
    }


}
