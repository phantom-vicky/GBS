/**
 *
 */
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.wrapper.CalendarEntriesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.CalendarEntriesFacade;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * The Class CalendarEntriesController.
 *
 * @author harishbabu.t
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class CalendarEntriesController {

    private static final Logger log = LoggerFactory.getLogger(CalendarEntriesController.class);

    @Autowired
    private CalendarEntriesFacade calendarEntriesFacade;

    /**
     * Builds the error response.
     *
     * @param message
     *            the message
     * @param errorMessages
     *            the error messages
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
     * @param message
     *            the message
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
     * @param calendarEntriesDto
     *            the calendar entries dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/calendarentries/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> create(@RequestBody final CalendarEntriesDto calendarEntriesDto) {

            return calendarEntriesFacade.create(calendarEntriesDto);
    }

    /**
     * Delete.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/calendarentries/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody final CalendarEntriesDto calendarEntriesDto) {

            String user = UserAccountUtils.getCurrentUsername();
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            if (StringUtils.equalsIgnoreCase(user, calendarEntriesDto.getCreatedBy())) {
                responseDataDTO.setMessage("Created user Cannot Delete Records");
                return new ResponseEntity<>(responseDataDTO, HttpStatus.OK);
            } else {
                return calendarEntriesFacade.delete(calendarEntriesDto);
            }
    }

    @RequestMapping(value = "/bulkupload/calendarentries", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(
            @RequestBody CbsCommonDTo cbsCommonDTo) {
            return calendarEntriesFacade.delete(cbsCommonDTo.getFileName(), cbsCommonDTo.getRemarks());
    }

    /**
     * Gets the all entries.
     *
     * @return the all entries
     */
    @RequestMapping(value = "/master/calendarentries/getallrecords", method = RequestMethod.POST)
    public ResponseEntity<List<CalendarEntriesDtoAuditLogWrapper>> getAllEntries() {

            return calendarEntriesFacade.getAllEntries();

    }

    @RequestMapping(value = "/master/calendarentries", method = RequestMethod.POST)
    public ResponseEntity<CalendarEntriesDtoAuditLogWrapper> getCalendarEntry(@RequestBody CbsCommonDTo cbsCommonDTo) {

            return calendarEntriesFacade.getCalendarEntry(cbsCommonDTo.getId());

    }

    /**
     * Gets the unverified entries.
     *
     * @return the unverified entries
     */
    @RequestMapping(value = "/master/calendarentries/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<CalendarEntriesDtoAuditLogWrapper>> getUnverifiedEntries() {

            return calendarEntriesFacade.getUnverifiedEntries();

    }

    /**
     * Gets the unverified entries.
     *
     * @body  fileName
     *            the file name
     * @return the unverified entries
     */
    @RequestMapping(value = "/bulkupload/calendarentries/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<CalendarEntriesDto>> getUnverifiedEntries(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return calendarEntriesFacade.getUnverifiedEntries(cbsCommonDTo.getFileName());

    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/calendarentries/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

            return calendarEntriesFacade.getUnverifiedFiles();

    }


    @RequestMapping(value = "/bulkupload/calendarentries/inquiryfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getInquiryFiles() {

            return calendarEntriesFacade.getInquiryFiles();

    }

    /**
     * Save entries.
     *
     * @param file
     *            the file
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/calendarentries/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> saveEntries(@RequestPart("file") final MultipartFile file) {

            return calendarEntriesFacade.saveEntries(file);

    }

    /**
     * Update.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/calendarentriesupdate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@RequestBody final CalendarEntriesDto calendarEntriesDto) {

            return calendarEntriesFacade.update(calendarEntriesDto);

    }

    /**
     * Verify.
     *
     * @param calendarEntriesDto
     *            the calendar entries dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/calendarentries/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody final CalendarEntriesDto calendarEntriesDto) {

            return calendarEntriesFacade.verify(calendarEntriesDto);

    }

    /**
     * Verify.
     *
     * @body fileName
     *            the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/calendarentries/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return calendarEntriesFacade.verify(cbsCommonDTo.getFileName());

    }


    @PostMapping(path = "/master/addDetails")
    public ResponseEntity<ResponseDataDTO> addDetails(@RequestBody CalendarEntriesDto calendarEntriesDto) {
        return APIResponseBuilder.buildResponseFromDto(calendarEntriesFacade.addDetails(calendarEntriesDto));
    }

    @PostMapping(path = "/master/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody CalendarEntriesDto calendarEntriesDto) {
        return APIResponseBuilder.buildResponseFromDto(calendarEntriesFacade.inquiry(calendarEntriesDto));
    }

    @PostMapping(path = "/master/verifyDetails")
    public ResponseEntity<ResponseDataDTO> verifyDetails(@RequestBody CalendarEntriesDto calendarEntriesDto) {
        return APIResponseBuilder.buildResponseFromDto(calendarEntriesFacade.verifyDetails(calendarEntriesDto));
    }

    @PostMapping(path = "/master/deleteDetails")
    public ResponseEntity<ResponseDataDTO> deleteDetails(@RequestBody CalendarEntriesDto calendarEntriesDto) {
        return APIResponseBuilder.buildResponseFromDto(calendarEntriesFacade.deleteDetails(calendarEntriesDto));
    }

    @PostMapping(path = "/master/getVerifyDetails")
    public ResponseEntity<ResponseDataDTO> getVerifyDetails(@RequestBody CalendarEntriesDto calendarEntriesDto) {
        return APIResponseBuilder.buildResponseFromDto(calendarEntriesFacade.getVerifyDetails(calendarEntriesDto));
    }

    @PostMapping(path = "/master/getModifyDetails")
    public ResponseEntity<ResponseDataDTO> getModifyDetails(@RequestBody CalendarEntriesDto calendarEntriesDto) {
        return APIResponseBuilder.buildResponseFromDto(calendarEntriesFacade.getModifyDetails(calendarEntriesDto));
    }

    @PostMapping(path = "/master/modify")
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody CalendarEntriesDto calendarEntriesDto) {
        return APIResponseBuilder.buildResponseFromDto(calendarEntriesFacade.modifyDetails(calendarEntriesDto));
    }


}
