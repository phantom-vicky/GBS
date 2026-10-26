package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.SchemeCodesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.SchemeCodesFacade;
import com.isl.gbm.cbs.integration.parser.SchemeCodesParser;
import com.isl.gbm.cbs.integration.service.SchemeCodesService;
import com.isl.gbm.cbs.integration.validator.SchemeCodesValidator;
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
public class SchemeCodesFacadeImpl implements SchemeCodesFacade {

    /**
     * The scheme codes validator.
     */
    @Autowired
    private SchemeCodesValidator schemeCodesValidator;

    /**
     * The scheme codes parser.
     */
    @Autowired
    private SchemeCodesParser schemeCodesParser;

    /**
     * The scheme codes service.
     */
    @Autowired
    private SchemeCodesService schemeCodesService;

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
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final SchemeCodesDto dto) {
        this.schemeCodesService.delete(dto);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_DELETED_MESSAGE);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Delete scheme codes.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:DELETE")
    public ResponseEntity<ResponseDataDTO> deleteSchemeCodes(final String fileName,final String remarks) {
        ResponseDataDTO dataDto = this.schemeCodesService.deleteSchemeCodes(fileName,remarks);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ENQUIRY")
    public ResponseEntity<List<SchemeCodesDtoAuditLogWrapper>> getAllRecords() {
        return new ResponseEntity<>(this.schemeCodesService.getAllList(), HttpStatus.OK);
    }

    /**
     * Gets the scheme code by id.
     *
     * @param id the id
     * @return the scheme code by id
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ENQUIRY")
    public ResponseEntity<SchemeCodesDtoAuditLogWrapper> getSchemeCodeById(final Integer id) {
        return new ResponseEntity<>(this.schemeCodesService.getSchemeCodes(id), HttpStatus.OK);
    }

    /**
     * Gets the unverified bank details.
     *
     * @param fileName the file name
     * @return the unverified bank details
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<SchemeCodesDto>> getUnverifiedBankDetails(final String fileName) {
        final List<SchemeCodesDto> result = this.schemeCodesService.getUnverifiedSchemeCodesDetails(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getUnverifiedFiles() {
        final List<String> fileNames = this.schemeCodesService.getUnverifiedFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getInquiryFiles() {
        final List<String> fileNames = this.schemeCodesService.getInquiryFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ENQUIRY")
    public ResponseEntity<List<SchemeCodesDtoAuditLogWrapper>> getUnverifiedRecords() {
        return new ResponseEntity<>(this.schemeCodesService.getUnverifiedRecords(), HttpStatus.OK);
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ENQUIRY")
    public ResponseEntity<List<SchemeCodesDto>> getVerifiedRecords() {
        return new ResponseEntity<>(this.schemeCodesService.getVerifiedRecords(), HttpStatus.OK);
    }

    /**
     * Save details.
     *
     * @param file the file
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:UPLOAD")
    public ResponseEntity<ResponseDataDTO> saveDetails(final MultipartFile file) {
        final Map<String, String> errorMessages = new HashMap<>();
        final List<SchemeCodesDto> schemeCodesDtoList;
        try {
            if (BooleanUtils.isFalse(this.schemeCodesValidator.isValidFile(file, errorMessages))) {
                return this.buildErrorResponse("Validation failed", errorMessages);
            }
            schemeCodesDtoList = this.schemeCodesParser.parse(file);
        } catch (final DuplicateRecordException | InvalidDataException | IOException e) {

            return this.buildErrorResponse(e.getMessage(), errorMessages);
        }

        if (BooleanUtils.isFalse(this.schemeCodesValidator.isValidRecords(schemeCodesDtoList, errorMessages))) {
            return this.buildErrorResponse("Validation failed", errorMessages);
        }
        ResponseDataDTO responseDto=this.schemeCodesService.buildDetails(schemeCodesDtoList);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Save details scheme codes.
     *
     * @param dto the dto
     * @return the response entity
     */
   /* @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ADD")
    public ResponseEntity<ResponseDataDTO> saveDetailsSchemeCodes(final SchemeCodesDto dto) {
        if (BooleanUtils.isTrue(this.schemeCodesValidator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.schemeCodesService.saveDetailsSchemeCodes(dto);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_SAVED_MESSAGE);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }*/

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final SchemeCodesDto dto) {
        if (BooleanUtils.isFalse(this.schemeCodesValidator.isValid(dto))) {
            return APIResponseBuilder.buildErrorResponse("Invalid input", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.schemeCodesService.update(dto);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_UPDATED_MESSAGE);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final SchemeCodesDto dto) {
        this.schemeCodesService.verify(dto);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_VERIFIED_MESSAGE);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Verify scheme codes.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifySchemeCodes(final String fileName) {
       ResponseDataDTO dataDto= this.schemeCodesService.verifySchemeCodes(fileName);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ADD")
    public ResponseDataDTO saveDetailsSchemeCodes(SchemeCodesDto dto) {
        return schemeCodesService.saveDetailsSchemeCodes(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:ENQUIRY")
    public ResponseDataDTO inquiry(SchemeCodesDto dto) {
        return schemeCodesService.inquiry(dto);
    }
    @Override
    public ResponseDataDTO verDelDetails(SchemeCodesDto dto) {
        return schemeCodesService.verDelDetails(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:MODIFY")
    public ResponseDataDTO modifyDetails(SchemeCodesDto dto) {
        return schemeCodesService.modifyDetails(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:MODIFY")
    public ResponseDataDTO modify(SchemeCodesDto dto) {
        return schemeCodesService.modify(dto);
    }

    @Override
    public ResponseDataDTO verifySubmit(SchemeCodesDto dto) {
        return schemeCodesService.verifySubmit(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:SCHEME_CODES:DELETE")
    public ResponseDataDTO deleteSubmit(SchemeCodesDto dto) {
        return schemeCodesService.deleteSubmit(dto);
    }

}
