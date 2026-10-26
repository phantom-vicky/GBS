/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.wrapper.BankBranchDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.BankBranchFacade;
import com.isl.gbm.cbs.integration.generator.CbsBulkUploadGenerator;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;


/**
 * The Class BankBranchController.
 */
@RestController
@Validated
@RequestMapping("/gbmultra/cbs/integration")
public class BankBranchController {

    private static final Logger log = LoggerFactory.getLogger(BankBranchController.class);

    @Autowired
    private BankBranchFacade bankBranchFacade;


    /**
     * Creates the.
     *
     * @param bankBranchDto the bank branch dto
     * @return the response entity
     */
   /* @RequestMapping(value = "/master/bankbranch/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> create(@Validated @RequestBody final BankBranchDto bankBranchDto) {

            return bankBranchFacade.create(bankBranchDto);

    }*/

    /**
     * Delete.
     *
     * @param bankBranchDto the bank branch dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/bankbranch/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(@RequestBody final BankBranchDto bankBranchDto) {

            String user = UserAccountUtils.getCurrentUsername();
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();

            if (StringUtils.equalsIgnoreCase(user, bankBranchDto.getCreatedBy())) {
                responseDataDTO.setMessage("Created user Cannot Delete Records");
                return new ResponseEntity<>(responseDataDTO, HttpStatus.OK);
            } else {
                return bankBranchFacade.delete(bankBranchDto);
            }

    }

    /**
     * Delete banks.
     *
     * @body fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/bankbranch", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> deleteBanks(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return bankBranchFacade.deleteBanks(cbsCommonDTo.getFileName(), cbsCommonDTo.getRemarks());

    }

    /**
     * Gets the all bank details.
     *
     * @return the all bank details
     */
    @RequestMapping(value = "/master/bankbranch/getallrecords", method = RequestMethod.GET)
    public ResponseEntity<List<BankBranchDto>> getAllBankDetails() {

            return bankBranchFacade.getAllBankDetails();

    }

    /**
     * Gets the all bank details for inquiry.
     *
     * @return the all bank details for inquiry
     */
    @RequestMapping(value = "/master/bankbranch/getallrecordsinquiry", method = RequestMethod.POST)
    public ResponseEntity<List<BankBranchDtoAuditLogWrapper>> getAllBankDetailsForInquiry() {

            return bankBranchFacade.getAllBankDetailsForInquiry();

    }

    @RequestMapping(value = "/master/bankbranch", method = RequestMethod.POST)
    public ResponseEntity<BankBranchDtoAuditLogWrapper> getBankBranchDetails(@RequestBody CbsCommonDTo cbsCommonDTo) {

            return bankBranchFacade.getBankBranchDetails(cbsCommonDTo.getId());

    }

    /**
     * Gets the bank branch status.
     *
     * @return the bank branch status
     */
    @RequestMapping(value = "/master/bankbranch/status", method = RequestMethod.POST)
    public ResponseEntity<Map<Integer, String>> getBankBranchStatus() {

            return bankBranchFacade.getBankBranchStatus();

    }

    /**
     * Gets the sol idand description.
     *
     * @return the sol idand description
     */
    @RequestMapping(value = "/master/bankbranch/solIdandDesc", method = RequestMethod.GET)
    public ResponseEntity<List<BankBranchDto>> getSolIdandDescription() {

            return bankBranchFacade.getSolIdandDescription();

    }

    /**
     * Gets the unverified bank details.
     *
     * @body fileName the file name
     * @return the unverified bank details
     */
    @RequestMapping(value = "/bulkupload/bankbranch/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<BankBranchDto>> getUnverifiedBankDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return bankBranchFacade.getUnverifiedBankDetails(cbsCommonDTo.getFileName());

    }

    /**
     * Gets the functionality name.
     *
     * @return the excel files to download
     */
    @RequestMapping(value = "/bulkupload/fileExcelDownload", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> getUnverifiedBankDetailsDownload(@RequestBody CbsCommonDTo cbsCommonDTo) throws IOException, InvalidFormatException {
        return bankBranchFacade.getUnverifiedBankDetailsDownload(cbsCommonDTo);
    }


    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/bankbranch/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

            return bankBranchFacade.getUnverifiedFiles();

    }

    @RequestMapping(value = "/bulkupload/bankbranch/inquiryfiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getInquiryFiles() {

            return bankBranchFacade.getInquiryFiles();

    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequestMapping(value = "/master/bankbranch/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<BankBranchDtoAuditLogWrapper>> getUnverifiedRecords() {

            return bankBranchFacade.getUnverifiedRecords();

    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequestMapping(value = "/master/bankbranch/getVerifyRecords", method = RequestMethod.POST)
    public ResponseEntity<List<BankBranchDto>> getVerifiedRecords() {

            return bankBranchFacade.getVerifiedRecords();
    }

    /**
     * Save details.
     *
     * @param file the file
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/bankbranch/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> saveDetails(@RequestPart("file") final MultipartFile file) {

            return bankBranchFacade.saveDetails(file);
    }

    /**
     * Update.
     *
     * @param bankBranchDto the bank branch dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/bankbranchupdate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@Validated @RequestBody final BankBranchDto bankBranchDto) {

            return bankBranchFacade.update(bankBranchDto);
    }

    /**
     * Verify.
     *
     * @param bankBranchDto the bank branch dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/bankbranch/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@RequestBody @Validated final BankBranchDto bankBranchDto) {

            return bankBranchFacade.verify(bankBranchDto);

    }

    /**
     * Verify banks.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/bankbranch/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifyBanks(
            @RequestBody CbsCommonDTo cbsCommonDTo) {
            return bankBranchFacade.verifyBanks(cbsCommonDTo.getFileName());
    }


    @PostMapping(path = "/master/bankbranch/save")
    public ResponseEntity<ResponseDataDTO> addDetails(@RequestBody BankBranchDto dto) {
        return APIResponseBuilder.buildResponseFromDto(bankBranchFacade.addDetails(dto));
    }

    @PostMapping(path = "/master/bankbranch/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody BankBranchDto dto) {
        return APIResponseBuilder.buildResponseFromDto(bankBranchFacade.inquiry(dto));
    }
    @PostMapping(path = "/master/bankbranch/verDelDetails")
    public ResponseEntity<ResponseDataDTO> verDelDetails(@RequestBody BankBranchDto dto) {
        return APIResponseBuilder.buildResponseFromDto(bankBranchFacade.verDelDetails(dto));
    }
    @PostMapping(path = "/master/bankbranch/modifyDetails")
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody BankBranchDto dto) {
        return APIResponseBuilder.buildResponseFromDto(bankBranchFacade.modifyDetails(dto));
    }
    @PostMapping(path = "/master/bankbranch/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody BankBranchDto dto) {
        return APIResponseBuilder.buildResponseFromDto(bankBranchFacade.modify(dto));
    }
    @PostMapping(path = "/master/bankbranch/verifySubmit")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody BankBranchDto dto) {
        return APIResponseBuilder.buildResponseFromDto(bankBranchFacade.verifySubmit(dto));
    }
    @PostMapping(path = "/master/bankbranch/deleteSubmit")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@RequestBody BankBranchDto dto) {
        return APIResponseBuilder.buildResponseFromDto(bankBranchFacade.deleteSubmit(dto));
    }

    @PostMapping(path = "/master/bankbranch/getAllBranchId")
    public List<BankBranchMaster> getAllBranchId(@RequestBody BankBranchDto dto) {
        return bankBranchFacade.getAllBranchId(dto);
    }
}
