package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.OtherBankDetailsFacade;
import com.isl.gbm.cbs.integration.parser.OtherBankDetailsParser;
import com.isl.gbm.cbs.integration.service.OtherBankDetailsService;
import com.isl.gbm.cbs.integration.validator.OtherBankDetailsValidator;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.utils.APIResponseBuilder;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OtherBankDetailsFacadeImpl implements OtherBankDetailsFacade {

    @Autowired
    private OtherBankDetailsValidator otherBankDetailsValidator;

    /**
     * The other bank details parser.
     */
    @Autowired
    private OtherBankDetailsParser otherBankDetailsParser;

    /**
     * The other bank details service.
     */
    @Autowired
    private OtherBankDetailsService otherBankDetailsService;


    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ADD")
    public ResponseEntity<ResponseDataDTO> add(final OtherBankDetailsDto dto) {
        ResponseDataDTO dto1 = this.otherBankDetailsService.add(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:MODIFY")
    public ResponseEntity<ResponseDataDTO> modifySelect(final OtherBankDetailsDto dto) {
        return new ResponseEntity<>(this.otherBankDetailsService.modifySelect(dto),HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:MODIFY")
    public ResponseEntity<ResponseDataDTO> modifySubmit(final OtherBankDetailsDto dto) {
        ResponseDataDTO dto1 = this.otherBankDetailsService.modifySubmit(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ENQUIRY")
    public ResponseEntity<ResponseDataDTO> inquiry(final OtherBankDetailsDto dto) {
        return new ResponseEntity<>(this.otherBankDetailsService.inquiry(dto),HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(final OtherBankDetailsDto dto) {
        return new ResponseEntity<>(this.otherBankDetailsService.verifyDeleteSelect(dto),HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifySubmit(final OtherBankDetailsDto dto) {
        return new ResponseEntity<>(this.otherBankDetailsService.verifySubmit(dto),HttpStatus.OK);
    }

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:DELETE")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(final OtherBankDetailsDto dto) {
        return new ResponseEntity<>(this.otherBankDetailsService.deleteSubmit(dto),HttpStatus.OK);
    }

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
     * Delete banks.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:DELETE")
    public ResponseEntity<ResponseDataDTO> deleteBanks(final String fileName,final String remarks) {
       ResponseDataDTO dataDto= this.otherBankDetailsService.deleteOtherBankDetails(fileName,remarks);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Delete other bank records.
     *
     * @param otherBankDetailsDto the other bank details dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:DELETE")
    public ResponseEntity<ResponseDataDTO> deleteOtherBankRecords(final OtherBankDetailsDto otherBankDetailsDto) {
        this.otherBankDetailsService.deleteOtherBankRecords(otherBankDetailsDto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_DELETED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ENQUIRY")
    public ResponseEntity<List<OtherBankDetailsDtoAuditLogWrapper>> getAllRecords() {
        return new ResponseEntity<>(this.otherBankDetailsService.list(), HttpStatus.OK);
    }

    /**
     * Gets the other bank details.
     *
     * @param id the id
     * @return the other bank details
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ENQUIRY")
    public ResponseEntity<OtherBankDetailsDtoAuditLogWrapper> getOtherBankDetails(final Integer id) {
        return new ResponseEntity<>(this.otherBankDetailsService.getOtherBankDetails(id), HttpStatus.OK);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getUnverifiedFiles() {
        final List<String> fileNames = this.otherBankDetailsService.getUnverifiedFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getInquiryFiles() {
        final List<String> fileNames = this.otherBankDetailsService.getInquiryFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    /**
     * Gets the unverified other bank details.
     *
     * @param fileName the file name
     * @return the unverified other bank details
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<OtherBankDetailsDto>> getUnverifiedOtherBankDetails(final String fileName) {
        final List<OtherBankDetailsDto> result = this.otherBankDetailsService.getUnverifiedOtherBankDetails(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ENQUIRY")
    public ResponseEntity<List<OtherBankDetailsDtoAuditLogWrapper>> getUnverifiedRecords() {
        return new ResponseEntity<>(this.otherBankDetailsService.getUnverifiedRecords(), HttpStatus.OK);
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ENQUIRY")
    public ResponseEntity<List<OtherBankDetailsDto>> getVerifiedRecords() {
        return new ResponseEntity<>(this.otherBankDetailsService.getVerifiedRecords(), HttpStatus.OK);
    }

    /**
     * Save details.
     *
     * @param file the file
     * @return the response entity
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:UPLOAD")
    public ResponseEntity<ResponseDataDTO> saveDetails(final MultipartFile file)
            throws IOException {
        final Map<String, String> errorMessages = new HashMap<>();
        if (BooleanUtils.isFalse(this.otherBankDetailsValidator.isValidFile(file, errorMessages))) {
            return this.buildErrorResponse("Validation failed", errorMessages);
        }

        List<OtherBankDetailsDto> otherBankDetailsDtoList;
        try {
            otherBankDetailsDtoList = this.otherBankDetailsParser.parseFile1(file);
        } catch (final DuplicateRecordException | InvalidDataException e) {

            return this.buildErrorResponse(e.getMessage(), errorMessages);
        }

        if (BooleanUtils
                .isFalse(this.otherBankDetailsValidator.isValidRecords(otherBankDetailsDtoList, errorMessages))) {
            return this.buildErrorResponse("Validation failed", errorMessages);
        }
       ResponseDataDTO responseDto= this.otherBankDetailsService.saveDetails(otherBankDetailsDtoList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);


    }


    /**
     * Save entry record.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:ADD")
    public ResponseEntity<ResponseDataDTO> saveEntryRecord(final OtherBankDetailsDto dto) {
        if (BooleanUtils.isFalse(this.otherBankDetailsValidator.isValidRecord(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.otherBankDetailsService.saveEntryRecord(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_SAVED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final OtherBankDetailsDto dto) {
        if (BooleanUtils.isFalse(this.otherBankDetailsValidator.isValid(dto))) {
            return APIResponseBuilder.buildErrorResponse("Invalid input", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (BooleanUtils.isFalse(this.otherBankDetailsValidator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.otherBankDetailsService.update(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_UPDATED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Verify other bank details.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifyOtherBankDetails(final String fileName) {
        ResponseDataDTO dataDto =  this.otherBankDetailsService.verifyOtherBankDetails(fileName);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Verify other bank entry record.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OTHER_BANK_DETAILS:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifyOtherBankEntryRecord(final OtherBankDetailsDto dto) {
        this.otherBankDetailsService.verifyOtherBankEntryRecord(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_VERIFIED_MESSAGE),
                HttpStatus.OK);
    }
}
