/**
 *
 */
package com.isl.gbm.cbs.integration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Data
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendarEntriesDto {
    /** The Id. */
    private int id;

    /** The calendar type. */
    private String calendarType;

    /** The calendar code. */
    private String calendarCode;

    private List<CalenderEntriesResponseDto> calenderEntriesResponseDto;

    private String rejectionRemarks;

    private String remarks;

    /** The month year. */
    private String monthYear;

    private String month;

    private String year;

    private String existingFlag;

    /** The holiday string. */
    private String holidayString;

    /** The status. */
    private Character status;

    /** The delete flag. */
    private boolean deleteFlag;

    /** The verified flag. */
    private boolean verifiedFlag;

    /** The file name. */
    @Size(max = 100, message = "File name should not exceed 100 characters")
    private String fileName;

    /** The created by. */
    private String createdBy;

    /** The created date. */
    private Date createdDate;

    /** The last modified by. */
    private String lastModifiedBy;

    /** The last modified date. */
    private Date lastModifiedDate;

    /** The verified by. */
    private String verifiedBy;

    /** The verified date. */
    private Date verifiedDate;

    /** The text 1. */
    private String additionalText1;

    /** The text 2. */
    private String additionalText2;

    /** The additional text 3. */
    private String additionalText3;

    private String holidayRemarks;


}
