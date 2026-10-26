/**
 *
 */
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.model.CalendarEntries;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface CalendarEntriesDao.
 *
 * @author harishbabu.t
 */
public interface CalendarEntriesDao {

    /**
     * Creates the.
     *
     * @param calendarEntries
     *            the calendar entries
     */
    void create(CalendarEntries calendarEntries);

    /**
     * Delete.
     *
     * @param id
     *            the id
     */
    void delete(int id);

    /**
     * Delete.
     *
     * @param fileName
     *            the file name
     */
    void delete(String fileName);

    public void deleteId(Integer id);

    /**
     * File name exists.
     *
     * @param //fileName
     *            the file name
     * @return true, if successful
     */
    boolean fileNameExists(String calendarFileName);

    /**
     * Gets the all entries.
     *
     * @return the all entries
     */
    List<CalendarEntries> getAllEntries();

    /**
     * Gets the calendar entries.
     *
     * @return the calendar entries
     */
    List<CalendarEntries> getCalendarEntries();

    /**
     * Gets the calendar entry.
     *
     * @param id
     *            the id
     * @return the calendar entry
     */
    CalendarEntries getCalendarEntry(Integer id);

    /**
     * Gets the unverified entries.
     *
     * @return the unverified entries
     */
    List<CalendarEntries> getUnverifiedEntries();

    /**
     * Gets the unverified entries.
     *
     * @param fileName
     *            the file name
     * @return the unverified entries
     */
    List<CalendarEntries> getUnverifiedEntries(String fileName);

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
     * @param calendarCode
     *            the calendar code
     * @param monthYear
     *            the month year
     * @return true, if is calendar entry exists
     */
    boolean iscalendarEntryExists(String calendarCode, String monthYear);

    /**
     * Save entries.
     *
     * @param calendarEntriesList
     *            the calendar entries list
     */
    void saveEntries(List<CalendarEntries> calendarEntriesList);

    /**
     * Verify.
     *
     * @param id
     *            the id
     */
    void verify(int id);

    /**
     * Verify.
     *
     * @param fileName
     *            the file name
     */
    void verify(String fileName);


    void update(CalendarEntries calendarEntries);

    public ResponseDataDTO addDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO inquiry(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO verifyDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO deleteDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO getVerifyDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO getModifyDetails(CalendarEntriesDto calendarEntriesDto);

    public ResponseDataDTO modifyDetails(CalendarEntriesDto calendarEntriesDto);
}
