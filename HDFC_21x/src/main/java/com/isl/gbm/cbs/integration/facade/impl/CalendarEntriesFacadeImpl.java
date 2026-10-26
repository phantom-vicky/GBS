package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.CalendarEntriesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.CalendarEntriesFacade;
import com.isl.gbm.cbs.integration.parser.CalendarEntriesParser;
import com.isl.gbm.cbs.integration.service.CalendarEntriesService;
import com.isl.gbm.cbs.integration.validator.CalendarEntriesValidator;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CalendarEntriesFacadeImpl implements CalendarEntriesFacade {

    /**
     * The calendar entries validator.
     */
    @Autowired
    private CalendarEntriesValidator calendarEntriesValidator;

    /**
     * The calendar entries parser.
     */
    @Autowired
    private CalendarEntriesParser calendarEntriesParser;

    /**
     * The calendar entries service.
     */
    @Autowired
    private CalendarEntriesService calendarEntriesService;

    /**
     * Builds the error response.
     *
     * @param message       the message
     * @param errorMessages the error messages
     * @return the response entity
     */
    private ResponseEntity<ResponseDataDTO> buildErrorResponse(final String message,
                                                               final Map<String, String> errorMessages) {
        final ResponseDataDTO errorDto = this.buildResponse(message);
        errorDto.setResult(errorMessages);
        return new ResponseEntity<>(errorDto, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Builds the response.
     *
     * @param message the message
     * @return the response data DTO
     */
    private ResponseDataDTO buildResponse(final String message) {
        final ResponseDataDTO dataDto = new ResponseDataDTO();
        dataDto.setMessage(message);
        return dataDto;
    }

    /**
     * Creates the.
     *
     * @param calendarEntriesDto the calendar entries dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:ADD")
    public ResponseEntity<ResponseDataDTO> create(final CalendarEntriesDto calendarEntriesDto) {
        final Map<String, String> errorMessages = new HashMap<>();
        if (BooleanUtils.isFalse(this.calendarEntriesValidator.isValidRecord(calendarEntriesDto, errorMessages))) {
            return this.buildErrorResponse("Data Already Exists", errorMessages);
        }
        this.calendarEntriesService.create(calendarEntriesDto);
        final ResponseDataDTO responseDto = this.buildResponse("Data Saved successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param calendarEntriesDto the calendar entries dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final CalendarEntriesDto calendarEntriesDto) {
        this.calendarEntriesService.delete(calendarEntriesDto);
        final ResponseDataDTO responseDto = this.buildResponse("Data Deleted successfully");
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks) {
         ResponseDataDTO dataDto =  this.calendarEntriesService.delete(fileName,remarks);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Gets the all entries.
     *
     * @return the all entries
     */
    public ResponseEntity<List<CalendarEntriesDtoAuditLogWrapper>> getAllEntries() {
        final List<CalendarEntriesDtoAuditLogWrapper> resultList = this.calendarEntriesService.getAllEntries();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * Gets the calendar entry.
     *
     * @param calendarId the calendar id
     * @return the calendar entry
     */
    public ResponseEntity<CalendarEntriesDtoAuditLogWrapper> getCalendarEntry(final Integer calendarId) {
        final CalendarEntriesDtoAuditLogWrapper resultList = this.calendarEntriesService.getCalendarEntry(calendarId);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * Gets the unverified entries.
     *
     * @return the unverified entries
     */
    public ResponseEntity<List<CalendarEntriesDtoAuditLogWrapper>> getUnverifiedEntries() {
        final List<CalendarEntriesDtoAuditLogWrapper> resultList = this.calendarEntriesService.getUnverifiedEntries();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * Gets the unverified entries.
     *
     * @param fileName the file name
     * @return the unverified entries
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<CalendarEntriesDto>> getUnverifiedEntries(final String fileName) {
        final List<CalendarEntriesDto> resultList = this.calendarEntriesService.getUnverifiedEntries(fileName);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getUnverifiedFiles() {
        final List<String> fileNames = this.calendarEntriesService.getUnverifiedFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getInquiryFiles() {
        final List<String> fileNames = this.calendarEntriesService.getInquiryFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    /**
     * Save entries.
     *
     * @param file the file
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:UPLOAD")
    public ResponseEntity<ResponseDataDTO> saveEntries(final MultipartFile file) {
        final Map<String, String> errorMessages = new HashMap<>();
        final List<CalendarEntriesDto> calendarEntriesDtoList;
        ResponseDataDTO dataDto=new ResponseDataDTO();
        try {
            if (BooleanUtils.isFalse(this.calendarEntriesValidator.isValidFile(file, errorMessages))) {
            if(null!=errorMessages.get("Invalid_File")) {
                dataDto.setMessage(errorMessages.get("Invalid_File"));
                return APIResponseBuilder.buildResponseFromDto(dataDto);
            }else if(null!=errorMessages.get("File_Exists")){
                dataDto.setMessage(errorMessages.get("File_Exists"));
                return APIResponseBuilder.buildResponseFromDto(dataDto);
            }
            }
            calendarEntriesDtoList = this.calendarEntriesParser.parse(file);
        } catch (Exception e) {
            dataDto.setMessage(e.getMessage());
            return APIResponseBuilder.buildResponseFromDto(dataDto);
        }
        if (BooleanUtils.isFalse(this.calendarEntriesValidator.isValidRecords(calendarEntriesDtoList, errorMessages))) {
            if(null!=errorMessages.get("File_Empty")) {
                dataDto.setMessage(errorMessages.get("File_Empty"));
                return APIResponseBuilder.buildResponseFromDto(dataDto);
            }else if(null!=errorMessages.get("Duplicate_Data")){
                dataDto.setMessage(errorMessages.get("Duplicate_Data"));
                return APIResponseBuilder.buildResponseFromDto(dataDto);
            }
        }
         dataDto=this.calendarEntriesService.saveEntries(calendarEntriesDtoList);
        return APIResponseBuilder.buildResponseFromDto(dataDto);
    }

    /**
     * Update.
     *
     * @param calendarEntriesDto the calendar entries dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final CalendarEntriesDto calendarEntriesDto) {
        this.calendarEntriesService.update(calendarEntriesDto);
        final ResponseDataDTO dataDto = this.buildResponse("Data updated Successfully");
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Verify.
     *
     * @param calendarEntriesDto the calendar entries dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final CalendarEntriesDto calendarEntriesDto) {
        this.calendarEntriesService.verify(calendarEntriesDto);
        final ResponseDataDTO dataDto = this.buildResponse("Data Verified Successfully");
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final String fileName) {
      ResponseDataDTO dataDto=  this.calendarEntriesService.verify(fileName);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:ADD")
    public ResponseDataDTO addDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesService.addDetails(calendarEntriesDto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:ENQUIRY")
    public ResponseDataDTO inquiry(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesService.inquiry(calendarEntriesDto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:VERIFY")
    public ResponseDataDTO verifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesService.verifyDetails(calendarEntriesDto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:VERIFY")
    public ResponseDataDTO deleteDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesService.deleteDetails(calendarEntriesDto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:VERIFY")
    public ResponseDataDTO getVerifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesService.getVerifyDetails(calendarEntriesDto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:MODIFY")
    public ResponseDataDTO getModifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesService.getModifyDetails(calendarEntriesDto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:MAINTAINENCE_OF_CALENDER_ENTRIES:MODIFY")
    public ResponseDataDTO modifyDetails(CalendarEntriesDto calendarEntriesDto) {
        return calendarEntriesService.modifyDetails(calendarEntriesDto);
    }


}
