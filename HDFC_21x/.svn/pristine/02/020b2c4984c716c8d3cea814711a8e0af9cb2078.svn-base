package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OfficeAccountPlaceHolderDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.OfficeAccountPlaceHolderFacade;
import com.isl.gbm.cbs.integration.parser.OfficeAccountParser;
import com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService;
import com.isl.gbm.cbs.integration.validator.OfficeAccountPlaceHolderValidator;
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
public class OfficeAccountPlaceHolderFacadeImpl implements OfficeAccountPlaceHolderFacade {


    /**
     * The office account place holder service.
     */
    @Autowired
    private OfficeAccountPlaceHolderService officeAccountPlaceHolderService;

    /**
     * The parser.
     */
    @Autowired
    private OfficeAccountParser parser;

    /**
     * The validator.
     */
    @Autowired
    private OfficeAccountPlaceHolderValidator validator;


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
/*    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ADD")
    public ResponseEntity<ResponseDataDTO> create(final OfficeAccountPlaceHolderDto dto) {
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.officeAccountPlaceHolderService.create(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_SAVED_MESSAGE),
                HttpStatus.OK);
    }*/

    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final OfficeAccountPlaceHolderDto dto) {
        this.officeAccountPlaceHolderService.delete(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_DELETED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks) {
        ResponseDataDTO dataDto =   this.officeAccountPlaceHolderService.delete(fileName,remarks);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ENQUIRY")
    public ResponseEntity<List<OfficeAccountPlaceHolderDtoAuditLogWrapper>> getAllRecords() {
        return new ResponseEntity<>(this.officeAccountPlaceHolderService.list(), HttpStatus.OK);
    }

    /**
     * Gets the office account place holder details.
     *
     * @param officeId the office id
     * @return the office account place holder details
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ENQUIRY")
    public ResponseEntity<OfficeAccountPlaceHolderDtoAuditLogWrapper> getOfficeAccountPlaceHolderDetails(final Integer officeId) {
        return new ResponseEntity<>(this.officeAccountPlaceHolderService.getOfficeAccountPlaceHolderDetails(officeId),
                HttpStatus.OK);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */

    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getUnverifiedFiles() {
        final List<String> fileNames = this.officeAccountPlaceHolderService.getUnverifiedFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getInquiryFiles() {
        final List<String> fileNames = this.officeAccountPlaceHolderService.getInquiryFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ENQUIRY")
    public ResponseEntity<List<OfficeAccountPlaceHolderDtoAuditLogWrapper>> getUnverifiedRecords() {
        return new ResponseEntity<>(this.officeAccountPlaceHolderService.getUnverifiedRecords(), HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @param fileName the file name
     * @return the unverified records
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<OfficeAccountPlaceHolderDto>> getUnverifiedRecords(final String fileName) {
        final List<OfficeAccountPlaceHolderDto> result = this.officeAccountPlaceHolderService
                .getUnverifiedPlaceHolders(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ENQUIRY")
    public ResponseEntity<List<OfficeAccountPlaceHolderDto>> getVerifiedRecords() {
        return new ResponseEntity<>(this.officeAccountPlaceHolderService.getVerifiedRecords(), HttpStatus.OK);
    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final OfficeAccountPlaceHolderDto dto) {
        if (BooleanUtils.isFalse(this.validator.isValid(dto))) {
            return APIResponseBuilder.buildErrorResponse("Invalid input", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.officeAccountPlaceHolderService.update(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_UPDATED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Upload.
     *
     * @param file the file
     * @return the response entity
     * @throws IOException Signals that an I/O exception has occurred.
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:UPLOAD")
    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file)
            throws IOException {
        final Map<String, String> errorMessages = new HashMap<>();

        if (BooleanUtils.isFalse(this.validator.isValidFile(file, errorMessages))) {
            return this.buildErrorResponse("Validation failed", errorMessages);
        }
        final List<OfficeAccountPlaceHolderDto> dtoList;
        try {
            dtoList = this.parser.parseFile(file);
        } catch (final DuplicateRecordException | InvalidDataException e) {
            return this.buildErrorResponse(e.getMessage(), errorMessages);
        }

        if (BooleanUtils.isFalse(this.validator.isValidRecords(dtoList, errorMessages))) {
            return this.buildErrorResponse("Validation failed", errorMessages);
        }

        ResponseDataDTO responseDataDTO= this.officeAccountPlaceHolderService.officeAccountPlaceHolder(dtoList);
        final ResponseDataDTO dataDto = this.buildResponse("Data uploaded successfully");
        return new ResponseEntity<>(responseDataDTO, HttpStatus.OK);
    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final OfficeAccountPlaceHolderDto dto) {
        this.officeAccountPlaceHolderService.verify(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_VERIFIED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final String fileName) {
        ResponseDataDTO dataDto = this.officeAccountPlaceHolderService.verify(fileName);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ADD")
    public ResponseDataDTO create(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderService.create(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:ENQUIRY")
    public ResponseDataDTO inquiry(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderService.inquiry(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:VERIFY")
    public ResponseDataDTO verDelDetails(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderService.verDelDetails(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:MODIFY")
    public ResponseDataDTO modifyDetails(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderService.modifyDetails(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:MODIFY")
    public ResponseDataDTO modify(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderService.modify(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:VERIFY")
    public ResponseDataDTO verifySubmit(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderService.verifySubmit(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:OFFICE_ACCOUNT_PLACE_HOLDER:DELETE")
    public ResponseDataDTO deleteSubmit(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderService.deleteSubmit(dto);
    }
}
