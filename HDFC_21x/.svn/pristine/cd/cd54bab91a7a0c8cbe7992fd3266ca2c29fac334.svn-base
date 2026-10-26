package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankBranchesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.OtherBankBranchesFacade;
import com.isl.gbm.cbs.integration.parser.OtherBankBranchesFileParser;
import com.isl.gbm.cbs.integration.service.OtherBankBranchesService;
import com.isl.gbm.cbs.integration.validator.OtherBankBranchesValidator;
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
public class OtherBankBranchesFacadeImpl implements OtherBankBranchesFacade {


    /**
     * The other bank branches service.
     */
    @Autowired
    private OtherBankBranchesService otherBankBranchesService;

    /**
     * The parser.
     */
    @Autowired
    private OtherBankBranchesFileParser parser;

    /**
     * The validator.
     */
    @Autowired
    private OtherBankBranchesValidator validator;


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
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ADD")
    public ResponseEntity<ResponseDataDTO> create(final OtherBankBranchesDto dto) {
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.otherBankBranchesService.create(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_SAVED_MESSAGE),
                HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ADD")
    public ResponseEntity<ResponseDataDTO> add(final OtherBankBranchesDto dto) {
        ResponseDataDTO dto1 = this.otherBankBranchesService.add(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:MODIFY")
    public ResponseEntity<ResponseDataDTO> modifySelect(final OtherBankBranchesDto dto) {
        ResponseDataDTO dto1 = this.otherBankBranchesService.modifySelect(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:MODIFY")
    public ResponseEntity<ResponseDataDTO> modifySubmit(final OtherBankBranchesDto dto) {
        ResponseDataDTO dto1 = this.otherBankBranchesService.modifySubmit(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ENQUIRY")
    public ResponseEntity<ResponseDataDTO> inquiry(final OtherBankBranchesDto dto) {
        ResponseDataDTO dto1 = this.otherBankBranchesService.inquiry(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(final OtherBankBranchesDto dto) {
        ResponseDataDTO dto1 = this.otherBankBranchesService.verifyDeleteSelect(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifySubmit(final OtherBankBranchesDto dto) {
        ResponseDataDTO dto1 = this.otherBankBranchesService.verifySubmit(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:DELETE")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(final OtherBankBranchesDto dto) {
        ResponseDataDTO dto1 = this.otherBankBranchesService.deleteSubmit(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }


    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final OtherBankBranchesDto dto) {
        this.otherBankBranchesService.delete(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_DELETED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks) {
       ResponseDataDTO dataDto= this.otherBankBranchesService.delete(fileName,remarks);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dataDto);
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ENQUIRY")
    public ResponseEntity<List<OtherBankBranchesDtoAuditLogWrapper>> getAllRecords() {
        return new ResponseEntity<>(this.otherBankBranchesService.list(), HttpStatus.OK);
    }

    /**
     * Gets the other bank branches details.
     *
     * @param branchId the branch id
     * @return the other bank branches details
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ENQUIRY")
    public ResponseEntity<OtherBankBranchesDtoAuditLogWrapper> getOtherBankBranchesDetails(final Integer branchId) {
        return new ResponseEntity<>(this.otherBankBranchesService.getOtherBankBranchesDetails(branchId), HttpStatus.OK);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getUnverifiedFiles() {
        final List<String> fileNames = this.otherBankBranchesService.getUnverifiedFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getInquiryFiles() {
        final List<String> fileNames = this.otherBankBranchesService.getInquiryFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ENQUIRY")
    public ResponseEntity<List<OtherBankBranchesDtoAuditLogWrapper>> getUnverifiedRecords() {
        return new ResponseEntity<>(this.otherBankBranchesService.getUnverifiedRecords(), HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @param fileName the file name
     * @return the unverified records
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<OtherBankBranchesDto>> getUnverifiedRecords(final String fileName) {
        final List<OtherBankBranchesDto> result = this.otherBankBranchesService.getUnverifiedRecords(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:ENQUIRY")
    public ResponseEntity<List<OtherBankBranchesDto>> getVerifiedRecords() {
        return new ResponseEntity<>(this.otherBankBranchesService.getVerifiedRecords(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<OtherBankBranchesDto>> getVerifiedBankBranchesList(final String bankCode, final String branchCode) {
        return new ResponseEntity<>(this.otherBankBranchesService.getVerifiedBankBranchesList(bankCode, branchCode), HttpStatus.OK);
    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final OtherBankBranchesDto dto) {
        if (BooleanUtils.isFalse(this.validator.isValid(dto))) {
            return APIResponseBuilder.buildErrorResponse("Invalid input", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.otherBankBranchesService.update(dto);
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
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:UPLOAD")
    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file)
            throws IOException {
        final Map<String, String> errorMessages = new HashMap<>();

        if (BooleanUtils.isFalse(this.validator.isValidFile(file, errorMessages))) {
            return this.buildErrorResponse("Validation failed", errorMessages);
        }
        final List<OtherBankBranchesDto> dtoList;
        try {
            dtoList = this.parser.parse1(file);
        } catch (final DuplicateRecordException | InvalidDataException e) {
            return this.buildErrorResponse(e.getMessage(), errorMessages);
        }

        if (BooleanUtils.isFalse(this.validator.isValidRecords(dtoList, errorMessages))) {
            return this.buildErrorResponse(errorMessages.isEmpty()?"Validation failed": errorMessages.values().stream().findFirst().get(), errorMessages);
        }

        ResponseDataDTO dataDto = this.otherBankBranchesService.otherBankBranches(dtoList);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_OTHER_BANK:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final OtherBankBranchesDto dto) {
        this.otherBankBranchesService.verify(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_VERIFIED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final String fileName) {
       ResponseDataDTO dataDto= this.otherBankBranchesService.verify(fileName);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }
}
