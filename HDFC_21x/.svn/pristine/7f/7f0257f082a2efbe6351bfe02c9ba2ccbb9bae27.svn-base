/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.controller;

import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.SchemeCodesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.SchemeCodesFacade;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * The Class SchemeCodesController.
 */
@RestController
@RequestMapping("/gbmultra/cbs/integration")
@Validated
public class SchemeCodesController {

    @Autowired
    private SchemeCodesFacade schemeCodesFacade;


    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/schemecodes/delete", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> delete(@Validated @RequestBody final SchemeCodesDto dto) {

            return schemeCodesFacade.delete(dto);

    }

    /**
     * Delete scheme codes.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/schemecodes", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> deleteSchemeCodes(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return schemeCodesFacade.deleteSchemeCodes(cbsCommonDTo.getFileName(), cbsCommonDTo.getRemarks());

    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequestMapping(value = "/master/schemecodes/list", method = RequestMethod.POST)
    public ResponseEntity<List<SchemeCodesDtoAuditLogWrapper>> getAllRecords() {

            return schemeCodesFacade.getAllRecords();

    }

    @RequestMapping(value = "/master/schemecodes", method = RequestMethod.POST)
    public ResponseEntity<SchemeCodesDtoAuditLogWrapper> getSchemeCodeById(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return schemeCodesFacade.getSchemeCodeById(cbsCommonDTo.getId());

    }

    /**
     * Gets the unverified bank details.
     *
     * @body fileName the file name
     * @return the unverified bank details
     */
    @RequestMapping(value = "/bulkupload/schemecodes/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<SchemeCodesDto>> getUnverifiedBankDetails(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return schemeCodesFacade.getUnverifiedBankDetails(cbsCommonDTo.getFileName());

    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequestMapping(value = "/bulkupload/schemecodes/unverifiedfiles", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getUnverifiedFiles() {

            return schemeCodesFacade.getUnverifiedFiles();

    }


    @RequestMapping(value = "/bulkupload/schemecodes/inquiryfiles", method = RequestMethod.GET)
    public ResponseEntity<List<String>> getInquiryFiles() {

            return schemeCodesFacade.getInquiryFiles();

    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequestMapping(value = "/master/schemecodes/unverifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<SchemeCodesDtoAuditLogWrapper>> getUnverifiedRecords() {

            return schemeCodesFacade.getUnverifiedRecords();

    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequestMapping(value = "/master/schemecodes/verifiedrecords", method = RequestMethod.POST)
    public ResponseEntity<List<SchemeCodesDto>> getVerifiedRecords() {

            return schemeCodesFacade.getVerifiedRecords();

    }

    /**
     * Save details.
     *
     * @param file the file
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/schemecodes/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> saveDetails(@RequestPart("file") final MultipartFile file) {

            return schemeCodesFacade.saveDetails(file);

    }

    /**
     * Save details scheme codes.
     *
     * @param dto the dto
     * @return the response entity
     */
  /*  @RequestMapping(value = "/master/schemecodes/save", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> saveDetailsSchemeCodes(@Validated @RequestBody final SchemeCodesDto dto) {

            return schemeCodesFacade.saveDetailsSchemeCodes(dto);

    }*/

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/schemecodesupdate", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> update(@Validated @RequestBody final SchemeCodesDto dto) {

            return schemeCodesFacade.update(dto);

    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequestMapping(value = "/master/schemecodes/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verify(@Validated @RequestBody final SchemeCodesDto dto) {

            return schemeCodesFacade.verify(dto);

    }

    /**
     * Verify scheme codes.
     *
     * @body  fileName the file name
     * @return the response entity
     */
    @RequestMapping(value = "/bulkupload/schemecodes/verify", method = RequestMethod.POST)
    public ResponseEntity<ResponseDataDTO> verifySchemeCodes(
            @RequestBody CbsCommonDTo cbsCommonDTo) {

            return schemeCodesFacade.verifySchemeCodes(cbsCommonDTo.getFileName());

    }

    @PostMapping(path = "/master/schemecodes/save")
    public ResponseEntity<ResponseDataDTO> saveDetailsSchemeCodes(@RequestBody SchemeCodesDto dto) {
        return APIResponseBuilder.buildResponseFromDto(schemeCodesFacade.saveDetailsSchemeCodes(dto));
    }

    @PostMapping(path = "/master/schemecodes/inquiry")
    public ResponseEntity<ResponseDataDTO> inquiry(@RequestBody SchemeCodesDto dto) {
        return APIResponseBuilder.buildResponseFromDto(schemeCodesFacade.inquiry(dto));
    }
    @PostMapping(path = "/master/schemecodes/verDelDetails")
    public ResponseEntity<ResponseDataDTO> verDelDetails(@RequestBody SchemeCodesDto dto) {
        return APIResponseBuilder.buildResponseFromDto(schemeCodesFacade.verDelDetails(dto));
    }
    @PostMapping(path = "/master/schemecodes/modifyDetails")
    public ResponseEntity<ResponseDataDTO> modifyDetails(@RequestBody SchemeCodesDto dto) {
        return APIResponseBuilder.buildResponseFromDto(schemeCodesFacade.modifyDetails(dto));
    }
    @PostMapping(path = "/master/schemecodes/modify")
    public ResponseEntity<ResponseDataDTO> modify(@RequestBody SchemeCodesDto dto) {
        return APIResponseBuilder.buildResponseFromDto(schemeCodesFacade.modify(dto));
    }
    @PostMapping(path = "/master/schemecodes/verifySubmit")
    public ResponseEntity<ResponseDataDTO> verifySubmit(@RequestBody SchemeCodesDto dto) {
        return APIResponseBuilder.buildResponseFromDto(schemeCodesFacade.verifySubmit(dto));
    }
    @PostMapping(path = "/master/schemecodes/deleteSubmit")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(@RequestBody SchemeCodesDto dto) {
        return APIResponseBuilder.buildResponseFromDto(schemeCodesFacade.deleteSubmit(dto));
    }

}
