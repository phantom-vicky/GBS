/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GbmUserDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.UserDetailsFacade;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.List;


/**
 * The Class UserDetailsController.
 */
@RestController
@Validated
@RequestMapping("/gbmultra/cbs/integration")
public class UserDetailsController {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsController.class);

    /**
     * The Constant ERROR_MESSAGE.
     */
    private static final String ERROR_MESSAGE = "file name should have atleast one character";

    @Autowired
    private UserDetailsFacade userDetailsFacade;




    @RequestMapping(value = "/master/userdetails/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> add(@Validated @RequestBody final GbmUserDetailsDto dto) {

        return userDetailsFacade.add(dto);

    }

    @RequestMapping(value = "/master/userdetails/modifySubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifySubmit(@Validated @RequestBody final GbmUserDetailsDto dto) {

        return userDetailsFacade.modifySubmit(dto);

    }
    @RequestMapping(value = "/master/userdetails/modifySelect", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifySelect(@Validated @RequestBody final GbmUserDetailsDto dto) {

        return userDetailsFacade.modifySelect(dto);

    }

    @RequestMapping(value = "/master/userdetails/inquiry", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> inquiry(@Validated @RequestBody final GbmUserDetailsDto dto) {

        return userDetailsFacade.inquiry(dto);

    }
    @RequestMapping(value = "/master/userdetails/verifyDeleteSelect", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(@Validated @RequestBody final GbmUserDetailsDto dto) {

        return userDetailsFacade.verifyDeleteSelect(dto);

    }
    @RequestMapping(value = "/master/userdetails/verifySubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifySubmit(@Validated @RequestBody final GbmUserDetailsDto dto) {

        return userDetailsFacade.verifySubmit(dto);

    }

    @RequestMapping(value = "/master/userdetails/deleteSubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@Validated @RequestBody final GbmUserDetailsDto dto) {

        return userDetailsFacade.deleteSubmit(dto);

    }

    /**
     * Creates the.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/userdetails/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> create(@Validated @RequestBody final GbmUserDetailsDto dto) {

            return userDetailsFacade.create(dto);

    }

    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/userdetails/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(@Validated @RequestBody final GbmUserDetailsDto dto) {

        return userDetailsFacade.delete(dto);

    }

    /**
     * Delete.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/userdetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return userDetailsFacade.delete(cbsCommonDTo.getFileName(), cbsCommonDTo.getRemarks());

    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/userdetails/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

            return userDetailsFacade.getUnverifiedFiles();

    }

    @RequestMapping(value = "/bulkupload/userdetails/inquiryfiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getInquiryFiles() {

            return userDetailsFacade.getInquiryFiles();

    }

    /**
     * Gets the unverified instruments.
     *
     * @body  fileName the file name
     * @return the unverified instruments
     */
    @RequestMapping(value = "/bulkupload/userdetails/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<GbmUserDetailsDto>> getUnverifiedInstruments(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return userDetailsFacade.getUnverifiedInstruments(cbsCommonDTo.getFileName());

    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequestMapping(value = "/master/userdetails/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<GbmUserDetailsDtoAuditLogWrapper>> getUnverifiedRecords() {

            return userDetailsFacade.getUnverifiedRecords();

    }

    @RequestMapping(value = "/master/userdetails", method = RequestMethod.POST)
    public ResponseEntity<GbmUserDetailsDto> getUserWorkDetails(@RequestBody CbsCommonDTo cbsCommonDTo) {

            return userDetailsFacade.getUserWorkDetails(cbsCommonDTo.getId());

    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequestMapping(value = "/master/userdetails/getVerfiedRecords", method = RequestMethod.POST)
    public ResponseEntity<List<GbmUserDetailsDtoAuditLogWrapper>> getVerifiedRecords() {

            return userDetailsFacade.getVerifiedRecords();

    }

    /**
     * List.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/master/userdetails/list", method = RequestMethod.POST)
    public ResponseEntity<List<GbmUserDetailsDto>> list() {

            return userDetailsFacade.list();

    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/userdetailsupdate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@Validated @RequestBody final GbmUserDetailsDto dto) {

            return userDetailsFacade.update(dto);

    }

    /**
     * Upload.
     *
     * @param file the file
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/userdetails/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> upload(@RequestPart("file") final MultipartFile file) {

            return userDetailsFacade.upload(file);

    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/userdetails/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@Validated @RequestBody final GbmUserDetailsDto dto) {

            return userDetailsFacade.verify(dto);

    }

    /**
     * Verify.
     *
     * @body fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/userdetails/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return userDetailsFacade.verify(cbsCommonDTo.getFileName());

    }

}
