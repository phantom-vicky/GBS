/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.OtherBankDetailsFacade;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 * The Class OtherBankDetailsController.
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
public class OtherBankDetailsController {

    private static final Logger log = LoggerFactory.getLogger(OtherBankDetailsController.class);

    @Autowired
    private OtherBankDetailsFacade otherBankDetailsFacade;



    @RequestMapping(value = "/master/otherbankdetails/add", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> add(@Validated @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.add(dto);

    }

    @RequestMapping(value = "/master/otherbankdetails/modifySubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifySubmit(@Validated @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.modifySubmit(dto);

    }
    @RequestMapping(value = "/master/otherbankdetails/modifySelect", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> modifySelect(@Validated @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.modifySelect(dto);

    }

    @RequestMapping(value = "/master/otherbankdetails/inquiry", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> inquiry(@Validated @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.inquiry(dto);

    }
    @RequestMapping(value = "/master/otherbankdetails/verifyDeleteSelect", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect( @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.verifyDeleteSelect(dto);

    }
    @RequestMapping(value = "/master/otherbankdetails/verifySubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifySubmit(@Validated @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.verifySubmit(dto);

    }

    @RequestMapping(value = "/master/otherbankdetails/deleteSubmit", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@Validated @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.deleteSubmit(dto);

    }


    /**
     * Delete banks.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/otherbankdetails", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> deleteBanks(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

        return otherBankDetailsFacade.deleteBanks(cbsCommonDTo.getFileName(), cbsCommonDTo.getRemarks());


    }

    /**
     * Delete other bank records.
     *
     * @param otherBankDetailsDto the other bank details dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/otherbankdetails/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> deleteOtherBankRecords(
            @RequestBody final OtherBankDetailsDto otherBankDetailsDto) {

        String user = UserAccountUtils.getCurrentUsername();
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        if (StringUtils.equalsIgnoreCase(user, otherBankDetailsDto.getCreatedBy())) {
            responseDataDTO.setMessage("Created user Cannot Delete Records");
            return new ResponseEntity<>(responseDataDTO, HttpStatus.OK);
        } else {
            return otherBankDetailsFacade.deleteOtherBankRecords(otherBankDetailsDto);
        }
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequestMapping(value = "/master/otherbankdetails/list", method = RequestMethod.POST)
    public ResponseEntity<List<OtherBankDetailsDtoAuditLogWrapper>> getAllRecords() {
         return new ResponseEntity(otherBankDetailsFacade.getAllRecords(), HttpStatus.OK);

    }



    @RequestMapping(value = "/master/otherbankdetails", method = RequestMethod.POST)
    public ResponseEntity<OtherBankDetailsDtoAuditLogWrapper> getOtherBankDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

        return otherBankDetailsFacade.getOtherBankDetails(cbsCommonDTo.getId());
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/otherbankdetails/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

        return otherBankDetailsFacade.getUnverifiedFiles();
    }


    @RequestMapping(value = "/bulkupload/otherbankdetails/inquiryfiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getInquiryFiles() {

        return otherBankDetailsFacade.getInquiryFiles();
    }

    /**
     * Gets the unverified other bank details.
     *
     * @body fileName the file name
     * @return the unverified other bank details
     */
    @RequestMapping(value = "/bulkupload/otherbankdetails/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<OtherBankDetailsDto>> getUnverifiedOtherBankDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

        return otherBankDetailsFacade.getUnverifiedOtherBankDetails(cbsCommonDTo.getFileName());
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequestMapping(value = "/master/otherbankdetails/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<OtherBankDetailsDtoAuditLogWrapper>> getUnverifiedRecords() {

        return otherBankDetailsFacade.getUnverifiedRecords();
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequestMapping(value = "/master/otherbankdetails/getVerifiedRecords", method = RequestMethod.POST)
    public ResponseEntity<List<OtherBankDetailsDto>> getVerifiedRecords() {

        return otherBankDetailsFacade.getVerifiedRecords();
    }

    /**
     * Save details.
     *
     * @param file the file
     * @return the response entity
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @RequestMapping(value = "/bulkupload/otherbankdetails/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> saveDetails(@RequestPart("file") final MultipartFile file) throws IOException {

        return otherBankDetailsFacade.saveDetails(file);
    }

    /**
     * Save entry record.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/otherbankdetails/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> saveEntryRecord(@Validated @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.saveEntryRecord(dto);
    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/otherbankdetailsupdate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@Validated @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.update(dto);
    }

    @RequestMapping(value = "/bulkupload/otherbankdetails/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyOtherBankDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {
        return otherBankDetailsFacade.verifyOtherBankDetails(cbsCommonDTo.getFileName());
    }

    /**
     * Verify other bank entry record.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "master/otherbankdetails/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyOtherBankEntryRecord(
            @Validated @RequestBody final OtherBankDetailsDto dto) {

        return otherBankDetailsFacade.verifyOtherBankEntryRecord(dto);
    }

}
