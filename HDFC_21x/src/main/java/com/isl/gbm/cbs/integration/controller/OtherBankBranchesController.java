/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankBranchesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.OtherBankBranchesFacade;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * The Class OtherBankBranchesController.
 */
@RestController
@Validated
@RequestMapping("/gbmultra/cbs/integration")
public class OtherBankBranchesController {

    /**
     * The Constant ERROR_MESSAGE.
     */
    private static final String ERROR_MESSAGE = "file name should have atleast one character";

    @Autowired
    private OtherBankBranchesFacade otherBankBranchesFacade;


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
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/otherbankbranches/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> create(@Validated @RequestBody final OtherBankBranchesDto dto) {

            return otherBankBranchesFacade.create(dto);

    }
    @RequestMapping(value = "/master/otherbankbranches/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> add(@Validated @RequestBody final OtherBankBranchesDto dto) {

        return otherBankBranchesFacade.add(dto);

    }

    @RequestMapping(value = "/master/otherbankbranches/modifySubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifySubmit(@Validated @RequestBody final OtherBankBranchesDto dto) {

        return otherBankBranchesFacade.modifySubmit(dto);

    }
    @RequestMapping(value = "/master/otherbankbranches/modifySelect", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifySelect(@Validated @RequestBody final OtherBankBranchesDto dto) {

        return otherBankBranchesFacade.modifySelect(dto);

    }

    @RequestMapping(value = "/master/otherbankbranches/inquiry", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> inquiry(@Validated @RequestBody final OtherBankBranchesDto dto) {

        return otherBankBranchesFacade.inquiry(dto);

    }
    @RequestMapping(value = "/master/otherbankbranches/verifyDeleteSelect", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(@Validated @RequestBody final OtherBankBranchesDto dto) {

        return otherBankBranchesFacade.verifyDeleteSelect(dto);

    }
    @RequestMapping(value = "/master/otherbankbranches/verifySubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifySubmit(@Validated @RequestBody final OtherBankBranchesDto dto) {

        return otherBankBranchesFacade.verifySubmit(dto);

    }

    @RequestMapping(value = "/master/otherbankbranches/deleteSubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@Validated @RequestBody final OtherBankBranchesDto dto) {

        return otherBankBranchesFacade.deleteSubmit(dto);

    }


    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/otherbankbranches/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(@Validated @RequestBody final OtherBankBranchesDto dto) {

            return otherBankBranchesFacade.delete(dto);

    }

    /**
     * Delete.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/otherbankbranches", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return otherBankBranchesFacade.delete(cbsCommonDTo.getFileName(), cbsCommonDTo.getRemarks());

    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequestMapping(value = "/master/otherbankbranches/list", method = RequestMethod.POST)
    public ResponseEntity<List<OtherBankBranchesDtoAuditLogWrapper>> getAllRecords() {

            return otherBankBranchesFacade.getAllRecords();

    }


    @RequestMapping(value = "/master/otherbankbranches", method = RequestMethod.POST)
    public ResponseEntity<OtherBankBranchesDtoAuditLogWrapper> getOtherBankBranchesDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return otherBankBranchesFacade.getOtherBankBranchesDetails(cbsCommonDTo.getId());

    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/otherbankbranches/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

            return otherBankBranchesFacade.getUnverifiedFiles();

    }


    @RequestMapping(value = "/bulkupload/otherbankbranches/inquiryfiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getInquiryFiles() {

            return otherBankBranchesFacade.getInquiryFiles();

    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequestMapping(value = "/master/otherbankbranches/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<OtherBankBranchesDtoAuditLogWrapper>> getUnverifiedRecords() {

            return otherBankBranchesFacade.getUnverifiedRecords();

    }

    /**
     * Gets the unverified records.
     *
     * @body  fileName the file name
     * @return the unverified records
     */
    @RequestMapping(value = "/bulkupload/otherbankbranches/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<OtherBankBranchesDto>> getUnverifiedRecords(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return otherBankBranchesFacade.getUnverifiedRecords(cbsCommonDTo.getFileName());

    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequestMapping(value = "/master/otherbankbranches/getVerifiedRecords", method = RequestMethod.POST)
    public ResponseEntity<List<OtherBankBranchesDto>> getVerifiedRecords() {

            return otherBankBranchesFacade.getVerifiedRecords();

    }


    @RequestMapping(value = "/master/other-bank-branches-by-bank-branch-code", method = RequestMethod.GET)
    public ResponseEntity<List<OtherBankBranchesDto>> getVerifiedRecordsByBankCode(@Validated @Size(min = 1, message = "Bank Code Should not be empty") @RequestParam(value = "bankCode") final String bankCode, @RequestParam(value = "bankCode", required = false) final String branchCode) {

            return otherBankBranchesFacade.getVerifiedBankBranchesList(bankCode, branchCode);

    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/otherbankbranchesupdate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@Validated @RequestBody final OtherBankBranchesDto dto) {

            return otherBankBranchesFacade.update(dto);

    }

    /**
     * Upload.
     *
     * @param file the file
     * @return the response entity
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @RequestMapping(value = "/bulkupload/otherbankbranches/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> upload(@RequestPart(value = "file") final MultipartFile file)
            throws IOException {

            return otherBankBranchesFacade.upload(file);

    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/otherbankbranches/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@Validated @RequestBody final OtherBankBranchesDto dto) {

            return otherBankBranchesFacade.verify(dto);

    }

    /**
     * Verify.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/otherbankbranches/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return otherBankBranchesFacade.verify(cbsCommonDTo.getFileName());

    }
}
