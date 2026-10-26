/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TransactionCodesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.TransactionCodesFacade;
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
 * The Class TransactionCodesController.
 */
@RestController
@Validated
@RequestMapping("/gbmultra/cbs/integration")
public class TransactionCodesController {

    private static final Logger log = LoggerFactory.getLogger(TransactionCodesController.class);

    /**
     * The Constant ERROR_MESSAGE.
     */
    public static final String ERROR_MESSAGE = "account number should have atleast 3 characters";

    @Autowired
    private TransactionCodesFacade transactionCodesFacade;


    @RequestMapping(value = "/master/transactioncodes/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> add(@Validated @RequestBody final TransactionCodesDto dto) {

        return transactionCodesFacade.add(dto);

    }

    @RequestMapping(value = "/master/transactioncodes/modifySubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifySubmit(@Validated @RequestBody final TransactionCodesDto dto) {

        return transactionCodesFacade.modifySubmit(dto);

    }
    @RequestMapping(value = "/master/transactioncodes/modifySelect", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifySelect(@Validated @RequestBody final TransactionCodesDto dto) {

        return transactionCodesFacade.modifySelect(dto);

    }

    @RequestMapping(value = "/master/transactioncodes/inquiry", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> inquiry(@Validated @RequestBody final TransactionCodesDto dto) {

        return transactionCodesFacade.inquiry(dto);

    }
    @RequestMapping(value = "/master/transactioncodes/verifyDeleteSelect", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(@Validated @RequestBody final TransactionCodesDto dto) {

        return transactionCodesFacade.verifyDeleteSelect(dto);

    }
    @RequestMapping(value = "/master/transactioncodes/verifySubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifySubmit(@Validated @RequestBody final TransactionCodesDto dto) {

        return transactionCodesFacade.verifySubmit(dto);

    }

    @RequestMapping(value = "/master/transactioncodes/deleteSubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@Validated @RequestBody final TransactionCodesDto dto) {

        return transactionCodesFacade.deleteSubmit(dto);

    }

    /**
     * Creates the.
     *
     * @param transactionCodeDto the transaction code dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/transactioncodes/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> create(
            @Validated @RequestBody final TransactionCodesDto transactionCodeDto) {

            return transactionCodesFacade.create(transactionCodeDto);

    }

    /**
     * Delete.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/transactioncodes", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(
            @Validated @Size(min = 3, message = ERROR_MESSAGE) @RequestBody CbsCommonDTo cbsCommonDTo) {

            return transactionCodesFacade.delete(cbsCommonDTo.getFileName(),cbsCommonDTo.getRemarks());

    }

    /**
     * Delete.
     *
     * @param transactionCodesdto the transaction codesdto
     * @return the response entity
     */
    @RequestMapping(value = "/master/transactioncodes/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(
            @Validated @RequestBody final TransactionCodesDto transactionCodesdto) {

            return transactionCodesFacade.delete(transactionCodesdto);

    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequestMapping(value = "/master/transactioncodes/list", method = RequestMethod.POST)
    public ResponseEntity<List<TransactionCodesDtoAuditLogWrapper>> getAllRecords() {

            return transactionCodesFacade.getAllRecords();

    }

    @RequestMapping(value = "/master/transactioncodes", method = RequestMethod.POST)
    public ResponseEntity<TransactionCodesDtoAuditLogWrapper> getTransactionDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return transactionCodesFacade.getTransactionDetails(cbsCommonDTo.getId());

    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/transactioncodes/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

            return transactionCodesFacade.getUnverifiedFiles();

    }


    @RequestMapping(value = "/bulkupload/transactioncodes/inquiryfiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getInquiryFiles() {

            return transactionCodesFacade.getInquiryFiles();

    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequestMapping(value = "/master/transactioncodes/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<TransactionCodesDtoAuditLogWrapper>> getUnverifiedRecords() {

            return transactionCodesFacade.getUnverifiedRecords();

    }

    /**
     * Gets the unverified transactions.
     *
     * @body  fileName the file name
     * @return the unverified transactions
     */
    @RequestMapping(value = "/bulkupload/transactioncodes/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<TransactionCodesDto>> getUnverifiedTransactions(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return transactionCodesFacade.getUnverifiedTransactions(cbsCommonDTo.getFileName());

    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequestMapping(value = "/master/transactioncodes/getVerifiedRecords", method = RequestMethod.POST)
    public ResponseEntity<List<TransactionCodesDto>> getVerifiedRecords() {

            return transactionCodesFacade.getVerifiedRecords();

    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/transactioncodesupdate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@Validated @RequestBody final TransactionCodesDto dto) {

            return transactionCodesFacade.update(dto);

    }

    /**
     * Upload.
     *
     * @param file the file
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/transactioncodes/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> upload(@RequestPart("file") final MultipartFile file) {

            return transactionCodesFacade.upload(file);


    }

    /**
     * Verify.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/transactioncodes/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody CbsCommonDTo cbsCommonDTo) {

            return transactionCodesFacade.verify(cbsCommonDTo.getFileName());


    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/transactioncodes/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@Validated @RequestBody final TransactionCodesDto dto) {

            return transactionCodesFacade.verify(dto);

    }

}
