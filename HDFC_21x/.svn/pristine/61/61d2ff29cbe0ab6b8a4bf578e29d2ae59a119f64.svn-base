/**
 *
 */
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.CalenderEntriesResponseDto;
import com.isl.gbm.cbs.integration.dto.wrapper.CalendarEntriesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.CalendarEntriesManager;
import com.isl.gbm.cbs.integration.service.CalendarEntriesService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class CalendarEntriesServiceImpl.
 *
 * @author harishbabu.t
 */
@Service
@Transactional
public class CalendarEntriesServiceImpl implements CalendarEntriesService {

    /** The calendar entries manager. */
    @Autowired
    private CalendarEntriesManager calendarEntriesManager;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.CalendarEntriesService#create(com.isl
     * .gbm.cbs.integration.dto.CalendarEntriesDto)
     */
    @Override
    public void create(final CalendarEntriesDto calendarEntriesDto) {

        this.calendarEntriesManager.create(calendarEntriesDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.CalendarEntriesService#delete(com.isl
     * .gbm.cbs.integration.dto.CalendarEntriesDto)
     */
    @Override
    public void delete(final CalendarEntriesDto calendarEntriesDto) {

        this.calendarEntriesManager.delete(calendarEntriesDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.CalendarEntriesService#delete(java.
     * lang.String)
     */
    @Override
    public ResponseDataDTO delete(final String fileName,final String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
      return this.calendarEntriesManager.delete(fileName,remarks);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.CalendarEntriesService#fileNameExists
     * (java.lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {

        return this.calendarEntriesManager.fileNameExists(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.CalendarEntriesService#getAllEntries(
     * )
     */
    @Override
    public List<CalendarEntriesDtoAuditLogWrapper> getAllEntries() {

        return this.calendarEntriesManager.getAllEntries();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.CalendarEntriesService#
     * getCalendarEntries()
     */
    @Override
    public List<CalendarEntriesDto> getCalendarEntries() {

        return this.calendarEntriesManager.getCalendarEntries();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.CalendarEntriesService#
     * getCalendarEntry(java.lang.String)
     */
    @Override
    public CalendarEntriesDtoAuditLogWrapper getCalendarEntry(final Integer calendarId) {
        return this.calendarEntriesManager.getCalendarEntry(calendarId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.CalendarEntriesService#
     * getUnverifiedEntries()
     */
    @Override
    public List<CalendarEntriesDtoAuditLogWrapper> getUnverifiedEntries() {

        return this.calendarEntriesManager.getUnverifiedEntries();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.CalendarEntriesService#
     * getUnverifiedEntries(java.lang.String)
     */
    @Override
    public List<CalendarEntriesDto> getUnverifiedEntries(final String fileName) {

        return this.calendarEntriesManager.getUnverifiedEntries(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.CalendarEntriesService#
     * getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {

        return this.calendarEntriesManager.getUnverifiedFiles();
    }

    @Override
    public List<String> getInquiryFiles() {

        return this.calendarEntriesManager.getInquiryFiles();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.CalendarEntriesService#
     * iscalendarEntryExists(com.isl.gbm.cbs.integration.dto.CalendarEntriesDto)
     */
    @Override
    public boolean iscalendarEntryExists(final CalendarEntriesDto calendarEntriesDto) {

        return this.calendarEntriesManager.iscalendarEntryExists(calendarEntriesDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.CalendarEntriesService#saveEntries(
     * java.util.List)
     */
    @Override
    public ResponseDataDTO saveEntries(final List<CalendarEntriesDto> calendarEntriesDtoList) {

      return   this.calendarEntriesManager.saveEntries(calendarEntriesDtoList);
    }

    /**
     * Sets the calendar entries manager.
     *
     * @param calendarEntriesManager
     *            the calendarEntriesManager to set
     */
    public void setCalendarEntriesManager(final CalendarEntriesManager calendarEntriesManager) {
        this.calendarEntriesManager = calendarEntriesManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.CalendarEntriesService#update(com.isl
     * .gbm.cbs.integration.dto.CalendarEntriesDto)
     */
    @Override
    public void update(final CalendarEntriesDto calendarEntriesDto) {

        this.calendarEntriesManager.update(calendarEntriesDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.CalendarEntriesService#verify(com.isl
     * .gbm.cbs.integration.dto.CalendarEntriesDto)
     */
    @Override
    public void verify(final CalendarEntriesDto calendarEntriesDto) {

        this.calendarEntriesManager.verify(calendarEntriesDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.CalendarEntriesService#verify(java.
     * lang.String)
     */
    @Override
    public ResponseDataDTO verify(final String fileName) {

       return this.calendarEntriesManager.verify(fileName);
    }

    @Override
    public ResponseDataDTO addDetails(CalendarEntriesDto calendarEntriesDto) {
        String holi="";
        String remarks="";
        Integer count = 0;
        if(null!=calendarEntriesDto&&null!=calendarEntriesDto.getCalenderEntriesResponseDto()){
            for (CalenderEntriesResponseDto entry : calendarEntriesDto.getCalenderEntriesResponseDto()) {
                ++count;
                if (entry.getRemarks().isEmpty()&&entry.getHoliday().equalsIgnoreCase("N")) {
                    remarks+="#";
                    holi+=" ";
                }
                else{
                    if(calendarEntriesDto.getCalenderEntriesResponseDto().size() == count) {
                        remarks += entry.getRemarks();
                        holi += entry.getHoliday();
                    }
                    else{
                        remarks += entry.getRemarks() + "#";
                        holi += entry.getHoliday();
                    }
                }
            }
            calendarEntriesDto.setHolidayString(holi);
            calendarEntriesDto.setRemarks(remarks);
        }
        return calendarEntriesManager.addDetails(calendarEntriesDto);
    }

    @Override
    public ResponseDataDTO inquiry(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesManager.inquiry(calendarEntriesDto);
    }

    @Override
    public ResponseDataDTO verifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesManager.verifyDetails(calendarEntriesDto);
    }

    @Override
    public ResponseDataDTO deleteDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesManager.deleteDetails(calendarEntriesDto);
    }

    @Override
    public ResponseDataDTO getVerifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesManager.getVerifyDetails(calendarEntriesDto);
    }

    @Override
    public ResponseDataDTO getModifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesManager.getModifyDetails(calendarEntriesDto);
    }

    @Override
    public ResponseDataDTO modifyDetails(CalendarEntriesDto calendarEntriesDto) {
        String holi="";
        String remarks="";
        Integer count = 0;
        if(null!=calendarEntriesDto&&null!=calendarEntriesDto.getCalenderEntriesResponseDto()){
            for (CalenderEntriesResponseDto entry : calendarEntriesDto.getCalenderEntriesResponseDto()) {
                ++count;
                if (entry.getRemarks().isEmpty()&&entry.getHoliday().equalsIgnoreCase("N")) {
                    remarks+="#";
                    holi+=" ";
                }
                else{
                    if(calendarEntriesDto.getCalenderEntriesResponseDto().size() == count) {
                        remarks += entry.getRemarks().toUpperCase();
                        holi += entry.getHoliday();
                    }
                    else{
                        remarks += entry.getRemarks().toUpperCase() + "#";
                        holi += entry.getHoliday();
                    }
                }
            }
            calendarEntriesDto.setHolidayString(holi);
            calendarEntriesDto.setRemarks(remarks);
        }
        return calendarEntriesManager.modifyDetails(calendarEntriesDto);
    }


}
