package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.dto.CommonGenericTableDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BranchIfscDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.BranchIfscDetailsFacade;
import com.isl.gbm.cbs.integration.parser.BranchIfscDetailsFileParser;
import com.isl.gbm.cbs.integration.service.BranchIfscDetailsService;
import com.isl.gbm.cbs.integration.validator.BranchIfscDetailsValidator;
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
public class BranchIfscDetailsFacadeImpl implements BranchIfscDetailsFacade {


    /**
     * The validator.
     */
    @Autowired
    private BranchIfscDetailsValidator validator;

    /**
     * The parser.
     */
    @Autowired
    private BranchIfscDetailsFileParser parser;

    /**
     * The service.
     */
    @Autowired
    private BranchIfscDetailsService service;


    /**
     * Creates the.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ADD")
    public ResponseEntity<ResponseDataDTO> create(final BranchIfscDetailsDto dto) {
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.service.create(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_SAVED_MESSAGE),
                HttpStatus.OK);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ADD")
    public ResponseEntity<ResponseDataDTO> add(final BranchIfscDetailsDto dto) {
        return new ResponseEntity<>(this.service.add(dto),HttpStatus.OK);
    }


    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:MODIFY")
    public ResponseEntity<ResponseDataDTO> modify(final BranchIfscDetailsDto dto) {
        return new ResponseEntity<>(this.service.modify(dto),HttpStatus.OK);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:MODIFY")
    public ResponseEntity<ResponseDataDTO> modifySubmit(final CommonGenericTableDto commonGenericTableDto) {
        ResponseDataDTO dto = this.service.modifySubmit(commonGenericTableDto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ENQUIRY")
    public ResponseEntity<ResponseDataDTO> inquiry(final BranchIfscDetailsDto dto) {
        return new ResponseEntity<>(this.service.inquiry(dto),HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:DELETE")
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(final BranchIfscDetailsDto dto) {
        return new ResponseEntity<>(this.service.verifyDeleteSelect(dto),HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifySubmit(final CommonGenericTableDto commonGenericTableDto) {
        return new ResponseEntity<>(this.service.verifySubmit(commonGenericTableDto),HttpStatus.OK);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:VERIFY")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(final CommonGenericTableDto commonGenericTableDto) {
        return new ResponseEntity<>(this.service.deleteSubmit(commonGenericTableDto),HttpStatus.OK);
    }
    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final BranchIfscDetailsDto dto) {
        this.service.delete(dto);
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
        ResponseDataDTO dataDto =this.service.delete(fileName,remarks);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ENQUIRY")
    public ResponseEntity<List<BranchIfscDetailsDtoAuditLogWrapper>> getAllRecords() {
        return new ResponseEntity<>(this.service.list(), HttpStatus.OK);
    }

    /**
     * Gets the branch ifsc details.
     *
     * @param branchDetailsId the branch details id
     * @return the branch ifsc details
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ENQUIRY")
    public ResponseEntity<BranchIfscDetailsDtoAuditLogWrapper> getBranchIfscDetails(final Integer branchDetailsId) {
        return new ResponseEntity<>(this.service.getBranchIfscDetails(branchDetailsId), HttpStatus.OK);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getUnverifiedFiles() {
        final List<String> fileNames = this.service.getUnverifiedFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getInquiryFiles() {
        final List<String> fileNames = this.service.getInquiryFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    /**
     * Gets the unverified instruments.
     *
     * @param fileName the file name
     * @return the unverified instruments
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<BranchIfscDetailsDto>> getUnverifiedInstruments(final String fileName) {
        final List<BranchIfscDetailsDto> result = this.service.getUnverifiedBranchIfscDetails(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ENQUIRY")
    public ResponseEntity<List<BranchIfscDetailsDtoAuditLogWrapper>> getUnverifiedRecords() {
        return new ResponseEntity<>(this.service.getUnverifiedRecords(), HttpStatus.OK);
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:ENQUIRY")
    public ResponseEntity<List<BranchIfscDetailsDto>> getVerifiedRecords() {
        return new ResponseEntity<>(this.service.getVerifiedRecords(), HttpStatus.OK);
    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final BranchIfscDetailsDto dto) {
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.service.update(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_UPDATED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Upload.
     *
     * @param file the file
     * @return the response entity
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:UPLOAD")
    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file) {

        final Map<String, String> errorMessages = new HashMap<>();
        final List<BranchIfscDetailsDto> ifscList;
        try {
            if (BooleanUtils.isFalse(this.validator.isValidFile(file, errorMessages))) {
                return APIResponseBuilder.buildErrorResponse("Validation failed", errorMessages,
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
            ifscList = this.parser.parse(file);
        } catch (final DuplicateRecordException | InvalidDataException | IOException e) {
            return APIResponseBuilder.buildErrorResponse(e.getMessage(), errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (BooleanUtils.isFalse(this.validator.isValidRecords(ifscList, errorMessages))) {
            return APIResponseBuilder.buildErrorResponse("Validation failed", errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        ResponseDataDTO dataDto =this.service.upload(ifscList);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);

    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCH_IFSC_CODES:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final BranchIfscDetailsDto dto) {
        this.service.verify(dto);
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
        ResponseDataDTO dataDto= this.service.verify(fileName);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }
}
