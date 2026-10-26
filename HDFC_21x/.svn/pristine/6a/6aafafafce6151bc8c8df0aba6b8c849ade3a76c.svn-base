/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OfficeAccountPlaceHolderDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.OfficeAccountPlaceHolderFacade;
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


/**
 * The Class OfficeAccountPlaceHolderController.
 */
@RestController
@Validated
@RequestMapping("/gbmultra/cbs/integration")
public class OfficeAccountPlaceHolderController {

    private static final Logger log = LoggerFactory.getLogger(OfficeAccountPlaceHolderController.class);

    /**
     * The Constant ERROR_MESSAGE.
     */
    private static final String ERROR_MESSAGE = "file name should have atleast one character";


    @Autowired
    private OfficeAccountPlaceHolderFacade officeAccountPlaceHolderFacade;


    /**
     * Creates the.
     *
     * @param dto the dto
     * @return the response entity
     */
/*    @RequestMapping(value = "/master/officeaccountplaceholder/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> create(@Validated @RequestBody final OfficeAccountPlaceHolderDto dto) {

            return officeAccountPlaceHolderFacade.create(dto);

    }*/

    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/officeaccountplaceholder/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(@Validated @RequestBody final OfficeAccountPlaceHolderDto dto) {

            return officeAccountPlaceHolderFacade.delete(dto);

    }

    /**
     * Delete.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/officeaccountplaceholder", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return officeAccountPlaceHolderFacade.delete(cbsCommonDTo.getFileName(), cbsCommonDTo.getRemarks());

    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequestMapping(value = "/master/officeaccountplaceholder/list", method = RequestMethod.POST)
    public ResponseEntity<List<OfficeAccountPlaceHolderDtoAuditLogWrapper>> getAllRecords() {

            return officeAccountPlaceHolderFacade.getAllRecords();

    }


    @RequestMapping(value = "/master/officeaccountplaceholder", method = RequestMethod.POST)
    public ResponseEntity<OfficeAccountPlaceHolderDtoAuditLogWrapper> getOfficeAccountPlaceHolderDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return officeAccountPlaceHolderFacade.getOfficeAccountPlaceHolderDetails(cbsCommonDTo.getId());

    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/officeaccountplaceholder/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

            return officeAccountPlaceHolderFacade.getUnverifiedFiles();

    }

    @RequestMapping(value = "/bulkupload/officeaccountplaceholder/inquiryfiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getInquiryFiles() {

            return officeAccountPlaceHolderFacade.getInquiryFiles();

    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequestMapping(value = "/master/officeaccountplaceholder/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<OfficeAccountPlaceHolderDtoAuditLogWrapper>> getUnverifiedRecords() {

            return officeAccountPlaceHolderFacade.getUnverifiedRecords();


    }

    /**
     * Gets the unverified records.
     *
     * @body  fileName the file name
     * @return the unverified records
     */
    @RequestMapping(value = "/bulkupload/officeaccountplaceholder/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<OfficeAccountPlaceHolderDto>> getUnverifiedRecords(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return officeAccountPlaceHolderFacade.getUnverifiedRecords(cbsCommonDTo.getFileName());

    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequestMapping(value = "/master/officeaccountplaceholder/getVerifiedRecords", method = RequestMethod.POST)
    public ResponseEntity<List<OfficeAccountPlaceHolderDto>> getVerifiedRecords() {

            return officeAccountPlaceHolderFacade.getVerifiedRecords();

    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/officeaccountplaceholderupdate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@Validated @RequestBody final OfficeAccountPlaceHolderDto dto) {

            return officeAccountPlaceHolderFacade.update(dto);

    }

    /**
     * Upload.
     *
     * @param file the file
     * @return the response entity
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @RequestMapping(value = "/bulkupload/officeaccountplaceholder/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> upload(@RequestPart(value = "file") final MultipartFile file)
            throws IOException {

            return officeAccountPlaceHolderFacade.upload(file);

    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/officeaccountplaceholder/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@Validated @RequestBody final OfficeAccountPlaceHolderDto dto) {

            return officeAccountPlaceHolderFacade.verify(dto);

    }

    /**
     * Verify.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/officeaccountplaceholder/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return officeAccountPlaceHolderFacade.verify(cbsCommonDTo.getFileName());

    }

    @PostMapping(path = "/master/officeaccountplaceholder/save")
    public ResponseEntity<ResponseDataDTO> create(@RequestBody OfficeAccountPlaceHolderDto dto) {
        return APIResponseBuilder.buildResponseFromDto(officeAccountPlaceHolderFacade.create(dto));
    }

    @PostMapping(path = "/master/officeaccountplaceholder/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody OfficeAccountPlaceHolderDto dto) {
        return APIResponseBuilder.buildResponseFromDto(officeAccountPlaceHolderFacade.inquiry(dto));
    }
    @PostMapping(path = "/master/officeaccountplaceholder/verDelDetails")
    public ResponseEntity<ResponseDataDTO> verDelDetails(@RequestBody OfficeAccountPlaceHolderDto dto) {
        return APIResponseBuilder.buildResponseFromDto(officeAccountPlaceHolderFacade.verDelDetails(dto));
    }
    @PostMapping(path = "/master/officeaccountplaceholder/modifyDetails")
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody OfficeAccountPlaceHolderDto dto) {
        return APIResponseBuilder.buildResponseFromDto(officeAccountPlaceHolderFacade.modifyDetails(dto));
    }
    @PostMapping(path = "/master/officeaccountplaceholder/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody OfficeAccountPlaceHolderDto dto) {
        return APIResponseBuilder.buildResponseFromDto(officeAccountPlaceHolderFacade.modify(dto));
    }
    @PostMapping(path = "/master/officeaccountplaceholder/verifySubmit")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody OfficeAccountPlaceHolderDto dto) {
        return APIResponseBuilder.buildResponseFromDto(officeAccountPlaceHolderFacade.verifySubmit(dto));
    }
    @PostMapping(path = "/master/officeaccountplaceholder/deleteSubmit")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@RequestBody OfficeAccountPlaceHolderDto dto) {
        return APIResponseBuilder.buildResponseFromDto(officeAccountPlaceHolderFacade.deleteSubmit(dto));
    }

}
