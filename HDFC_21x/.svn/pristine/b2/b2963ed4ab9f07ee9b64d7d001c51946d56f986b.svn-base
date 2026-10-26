/**
 *
 */
package com.isl.gbm.cbs.integration.parser.model;

import org.hibernate.validator.constraints.NotBlank;
import org.javafunk.excelparser.annotations.ExcelField;
import org.javafunk.excelparser.annotations.ExcelObject;
import org.javafunk.excelparser.annotations.ParseType;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * The Class CalendarEntriesRowMapper.
 *
 * @author harishbabu.t
 */
@ExcelObject(parseType = ParseType.ROW, start = 2)
@Validated
public class CalendarEntriesExcelRow {

    /** The Calendar type. */
    @ExcelField(position = 1)
    @NotBlank(message = "Calendar Type should not be blank")
    @Pattern(regexp = "(BRANCH|DC)?", message = "Calendar Type must be BRANCH or DC")
    private String calendarType;

    /** The Calendar code. */
    @ExcelField(position = 2)
    @NotBlank(message = "CalendarCode should not be blank")
    @Size(max = 12, message = "CalendarCode should not have more than 12 characters")
    private String calendarCode;

    /** The month year. */
    @ExcelField(position = 3)
    @NotBlank(message = "MonthYear should not be blank")
    @Size(max = 6, message = "MonthYear should not have more than 6 characters")
    // @NumberFormat(style = Style.NUMBER)
    private String monthYear;

    /** The is deleted. */
    @ExcelField(position = 4)
    private String isDeleted;

    /** The is holiday. */
    @ExcelField(position = 5)
    private String isHoliday;

    /** The status. */
    @ExcelField(position = 6)
    private String status;

    /** The holiday remarks. */
    @ExcelField(position = 7)
    private String holidayRemarks;


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
        final CalendarEntriesExcelRow other = (CalendarEntriesExcelRow) obj;
        if (this.calendarCode == null) {
            if (other.calendarCode != null) {
                return false;
            }
        } else if (!this.calendarCode.equals(other.calendarCode)) {
            return false;
        }
        if (this.monthYear == null) {
            if (other.monthYear != null) {
                return false;
            }
        } else if (!this.monthYear.equals(other.monthYear)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the holidayrRemarks.
     *
     * @return the holidayRemarks
     */
    public String getHolidayRemarks() {
        return this.holidayRemarks;
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
     * Gets the checks if is deleted.
     *
     * @return the isDeleted
     */
    public String getIsDeleted() {
        return this.isDeleted;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */

    /**
     * Gets the checks if is holiday.
     *
     * @return the isHoliday
     */
    public String getIsHoliday() {
        return this.isHoliday;
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
    public String getStatus() {
        return this.status;
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
        result = (PRIME * result) + (this.calendarCode == null ? 0 : this.calendarCode.hashCode());
        result = (PRIME * result) + (this.monthYear == null ? 0 : this.monthYear.hashCode());
        return result;
    }

    /**
     * Sets the holiday remarks.
     *
     * @param holidayRemarks
     *            the holidayRemarks to set
     */
    public void setHolidayRemarks(final String holidayRemarks) {
        this.holidayRemarks = holidayRemarks;
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
     * Sets the checks if is deleted.
     *
     * @param isDeleted
     *            the isDeleted to set
     */
    public void setIsDeleted(final String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * Sets the checks if is holiday.
     *
     * @param isHoliday
     *            the isHoliday to set
     */
    public void setIsHoliday(final String isHoliday) {
        this.isHoliday = isHoliday;
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
    public void setStatus(final String status) {
        this.status = status;
    }
}
