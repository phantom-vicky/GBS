/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.InstrumentDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.InstrumentDetailsFacade;
import com.isl.gbm.common.ErrorMessages;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.common.util.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * The Class InstrumentDetailsController.
 */
@RestController
@Validated
@RequestMapping("/gbmultra/cbs/integration")
public class InstrumentDetailsController {

    private static final Logger log = LoggerFactory.getLogger(InstrumentDetailsController.class);

    /**
     * The Constant ERROR_MESSAGE.
     */
    private static final String ERROR_MESSAGE = "file name should have atleast one character";


    @Autowired
    private InstrumentDetailsFacade instrumentDetailsFacade;


    /**
     * Creates the.
     *
     * @param dto the dto
     * @return the response entity
     */
   /* @RequestMapping(value = "/master/instruments/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> create(@Validated @RequestBody final InstrumentDetailsDto dto) {

        return instrumentDetailsFacade.create(dto);
    }*/

    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/instruments/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(@Validated @RequestBody final InstrumentDetailsDto dto) {

        return instrumentDetailsFacade.delete(dto);
    }

    /**
     * Delete.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/instruments", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

        return instrumentDetailsFacade.delete(cbsCommonDTo.getFileName(), cbsCommonDTo.getRemarks());
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequestMapping(value = "/master/instruments/list", method = RequestMethod.POST)
    public ResponseEntity<List<InstrumentDetailsDtoAuditLogWrapper>> getAllRecords() {

        return instrumentDetailsFacade.getAllRecords();
    }


    @RequestMapping(value = "/master/instruments", method = RequestMethod.POST)
    public ResponseEntity<InstrumentDetailsDtoAuditLogWrapper> getInstrumentDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

        return instrumentDetailsFacade.getInstrumentDetails(cbsCommonDTo.getId());
    }

    /**
     * Gets the instrument types.
     *
     * @return the instrument types
     */
    @RequestMapping(value = "/master/instruments/instrumenttypes", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> getInstrumentTypes() {

        return instrumentDetailsFacade.getInstrumentTypes();
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/instruments/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

        return instrumentDetailsFacade.getUnverifiedFiles();
    }


    @RequestMapping(value = "/bulkupload/instruments/inquiryfiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getInquiryFiles() {

        return instrumentDetailsFacade.getInquiryFiles();
    }

    /**
     * Gets the unverified instruments.
     *
     * @body  fileName the file name
     * @return the unverified instruments
     */
    @RequestMapping(value = "/bulkupload/instruments/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<InstrumentDetailsDto>> getUnverifiedInstruments(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

        return instrumentDetailsFacade.getUnverifiedInstruments(cbsCommonDTo.getFileName());
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequestMapping(value = "/master/instruments/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<InstrumentDetailsDtoAuditLogWrapper>> getUnverifiedRecords() {

        return instrumentDetailsFacade.getUnverifiedRecords();
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequestMapping(value = "/master/instruments/getVerifiedRecords", method = RequestMethod.POST)
    public ResponseEntity<List<InstrumentDetailsDto>> getVerifiedRecords() {

        return instrumentDetailsFacade.getVerifiedRecords();
    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/instrumentsupdate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@Validated @RequestBody final InstrumentDetailsDto dto) {

        return instrumentDetailsFacade.update(dto);
    }

    /**
     * Upload.
     *
     * @param file the file
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/instruments/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> upload(@RequestPart("file") final MultipartFile file) throws IOException {

        return instrumentDetailsFacade.upload(file);

    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/instruments/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@Validated @RequestBody final InstrumentDetailsDto dto) {

        return instrumentDetailsFacade.verify(dto);
    }

    /**
     * Verify.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/instruments/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return instrumentDetailsFacade.verify(cbsCommonDTo.getFileName());
    }

    @PostMapping(path = "/master/instruments/save")
    public ResponseEntity<ResponseDataDTO> addDetails(@RequestBody InstrumentDetailsDto dto) {
        return APIResponseBuilder.buildResponseFromDto(instrumentDetailsFacade.addDetails(dto));
    }

    @PostMapping(path = "/master/instruments/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody InstrumentDetailsDto dto) {
        return APIResponseBuilder.buildResponseFromDto(instrumentDetailsFacade.inquiry(dto));
    }
    @PostMapping(path = "/master/instruments/verDelDetails")
    public ResponseEntity<ResponseDataDTO> verDelDetails(@RequestBody InstrumentDetailsDto dto) {
        return APIResponseBuilder.buildResponseFromDto(instrumentDetailsFacade.verDelDetails(dto));
    }
    @PostMapping(path = "/master/instruments/modifyDetails")
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody InstrumentDetailsDto dto) {
        return APIResponseBuilder.buildResponseFromDto(instrumentDetailsFacade.modifyDetails(dto));
    }
    @PostMapping(path = "/master/instruments/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody InstrumentDetailsDto dto) {
        return APIResponseBuilder.buildResponseFromDto(instrumentDetailsFacade.modify(dto));
    }
    @PostMapping(path = "/master/instruments/verifySubmit")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody InstrumentDetailsDto dto) {
        return APIResponseBuilder.buildResponseFromDto(instrumentDetailsFacade.verifySubmit(dto));
    }
    @PostMapping(path = "/master/instruments/deleteSubmit")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@RequestBody InstrumentDetailsDto dto) {
        return APIResponseBuilder.buildResponseFromDto(instrumentDetailsFacade.deleteSubmit(dto));
    }

}
