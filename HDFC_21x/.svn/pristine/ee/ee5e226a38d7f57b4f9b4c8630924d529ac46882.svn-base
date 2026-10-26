/**
 *
 */
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.CalendarEntriesDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface CalendarEntriesService.
 *
 * @author harishbabu.t
 */
public interface CalendarEntriesService {

    /**
     * Creates the.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     */
    void create(CalendarEntriesDto calendarEntriesDto);

    /**
     * Delete.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     */
    void delete(CalendarEntriesDto calendarEntriesDto);

    /**
     * Delete.
     *
     * @param fileName
     *            the file name
     */
    ResponseDataDTO delete(String fileName,String remarks);

    /**
     * File name exists.
     *
     * @param fileName
     *            the file name
     * @return true, if successful
     */
    boolean fileNameExists(String fileName);

    /**
     * Gets the all entries.
     *
     * @return the all entries
     */
    List<CalendarEntriesDtoAuditLogWrapper> getAllEntries();

    /**
     * Gets the calendar entries.
     *
     * @return the calendar entries
     */
    List<CalendarEntriesDto> getCalendarEntries();

    /**
     * Gets the calendar entry.
     *
     * @param calendarId
     *            the calendar id
     * @return the calendar entry
     */
    CalendarEntriesDtoAuditLogWrapper getCalendarEntry(Integer calendarId);

    /**
     * Gets the unverified entries.
     *
     * @return the unverified entries
     */
    List<CalendarEntriesDtoAuditLogWrapper> getUnverifiedEntries();

    /**
     * Gets the unverified entries.
     *
     * @param fileName
     *            the file name
     * @return the unverified entries
     */
    List<CalendarEntriesDto> getUnverifiedEntries(String fileName);

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    List<String> getUnverifiedFiles();

    List<String> getInquiryFiles();

    /**
     * Checks if is calendar entry exists.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     * @return true, if is calendar entry exists
     */
    boolean iscalendarEntryExists(CalendarEntriesDto calendarEntriesDto);

    /**
     * Save entries.
     *
     * @param calendarEntriesDtoList
     *            the calendar entries dto list
     */
    ResponseDataDTO saveEntries(List<CalendarEntriesDto> calendarEntriesDtoList);

    /**
     * Update.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     */
    void update(CalendarEntriesDto calendarEntriesDto);

    /**
     * Verify.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     */
    void verify(CalendarEntriesDto calendarEntriesDto);

    /**
     * Verify.
     *
     * @param fileName
     *            the file name
     */
    ResponseDataDTO verify(String fileName);

    public ResponseDataDTO addDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO inquiry(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO verifyDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO deleteDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO getVerifyDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO getModifyDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO modifyDetails(CalendarEntriesDto calendarEntriesDto);
}
