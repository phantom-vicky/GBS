/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.CommonGenericTableDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BranchIfscDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.BranchIfscDetailsFacade;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import java.util.List;


/**
 * The Class BranchIfscDetailsController.
 */
@RestController
@Validated
@RequestMapping("/gbmultra/cbs/integration")
public class BranchIfscDetailsController {

    private static final Logger log = LoggerFactory.getLogger(BranchIfscDetailsController.class);

    /**
     * The Constant ERROR_MESSAGE.
     */
    private static final String ERROR_MESSAGE = "file name should have atleast one character";


    @Autowired
    private BranchIfscDetailsFacade branchIfscDetailsFacade;


    /**
     * Creates the.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/old/master/branchifscdetails/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> create(@Validated @RequestBody final BranchIfscDetailsDto dto) {

            return branchIfscDetailsFacade.create(dto);

    }
    @RequestMapping(value = "/master/branchifscdetails/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> add(@Validated @RequestBody final BranchIfscDetailsDto dto) {

        return branchIfscDetailsFacade.add(dto);

    }

    @PostMapping(value = "/master/branchifscdetails/modifySelect")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody BranchIfscDetailsDto dto) {

        return branchIfscDetailsFacade.modify(dto);

    }
    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/old/master/branchifscdetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@Validated @RequestBody final BranchIfscDetailsDto dto) {

        return branchIfscDetailsFacade.update(dto);

    }
    @PutMapping(value = "/master/branchifscdetails")
    public ResponseEntity<ResponseDataDTO> modifySubmit(@RequestBody CommonGenericTableDto commonGenericTableDto) {

        return branchIfscDetailsFacade.modifySubmit(commonGenericTableDto);

    }
    @PostMapping(value = "/master/branchifscdetails/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody BranchIfscDetailsDto dto) {

        return branchIfscDetailsFacade.inquiry(dto);

    }
    @PostMapping(value = "/master/branchifscdetails/verifyDeleteSelect")
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(@RequestBody BranchIfscDetailsDto dto) {

        return branchIfscDetailsFacade.verifyDeleteSelect(dto);

    }
    @PostMapping(value = "/master/branchifscdetails/verifySubmit")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody CommonGenericTableDto commonGenericTableDto) {

        return branchIfscDetailsFacade.verifySubmit(commonGenericTableDto);

    }
    @PostMapping(value = "/master/branchifscdetails/deleteSubmit")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@RequestBody CommonGenericTableDto commonGenericTableDto) {

        return branchIfscDetailsFacade.deleteSubmit(commonGenericTableDto);

    }
    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/branchifscdetails/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(@Validated @RequestBody final BranchIfscDetailsDto dto) {

            return branchIfscDetailsFacade.delete(dto);

    }
    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequestMapping(value = "/master/branchifscdetails/list", method = RequestMethod.POST)
    public ResponseEntity<List<BranchIfscDetailsDtoAuditLogWrapper>> getAllRecords() {

        return branchIfscDetailsFacade.getAllRecords();

    }
    @RequestMapping(value = "/master/branchifscdetails", method = RequestMethod.POST)
    public ResponseEntity<BranchIfscDetailsDtoAuditLogWrapper> getBranchIfscDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

        return branchIfscDetailsFacade.getBranchIfscDetails(cbsCommonDTo.getId());

    }
    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequestMapping(value = "/master/branchifscdetails/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<BranchIfscDetailsDtoAuditLogWrapper>> getUnverifiedRecords() {

        return branchIfscDetailsFacade.getUnverifiedRecords();

    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequestMapping(value = "/master/branchifscdetails/getVerifiedRecords", method = RequestMethod.POST)
    public ResponseEntity<List<BranchIfscDetailsDto>> getVerifiedRecords() {

        return branchIfscDetailsFacade.getVerifiedRecords();

    }




    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/branchifscdetails/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@Validated @RequestBody final BranchIfscDetailsDto dto) {

        return branchIfscDetailsFacade.verify(dto);

    }
    /**
     * Delete.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/branchifscdetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return branchIfscDetailsFacade.delete(cbsCommonDTo.getFileName(), cbsCommonDTo.getRemarks());

    }



    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/branchifscdetails/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

            return branchIfscDetailsFacade.getUnverifiedFiles();

    }


    @RequestMapping(value = "/bulkupload/branchifscdetails/inquiryfiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getInquiryFiles() {

            return branchIfscDetailsFacade.getInquiryFiles();

    }

    /**
     * Gets the unverified instruments.
     *
     * @body  fileName the file name
     * @return the unverified instruments
     */
    @RequestMapping(value = "/bulkupload/branchifscdetails/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<BranchIfscDetailsDto>> getUnverifiedInstruments(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return branchIfscDetailsFacade.getUnverifiedInstruments(cbsCommonDTo.getFileName());

    }

    /**
     * Upload.
     *
     * @param file the file
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/branchifscdetails/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> upload(@RequestPart("file") final MultipartFile file) {

        return branchIfscDetailsFacade.upload(file);


    }

    /**
     * Verify.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/branchifscdetails/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(
            @Valid @RequestBody CbsCommonDTo cbsCommonDTo) {

            return branchIfscDetailsFacade.verify(cbsCommonDTo.getFileName());

    }

}
