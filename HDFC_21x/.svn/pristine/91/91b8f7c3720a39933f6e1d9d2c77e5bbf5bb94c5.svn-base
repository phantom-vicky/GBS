package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TransactionCodesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.TransactionCodesFacade;
import com.isl.gbm.cbs.integration.parser.TransactionCodesParser;
import com.isl.gbm.cbs.integration.service.TransactionCodesService;
import com.isl.gbm.cbs.integration.validator.TransactionCodesValidator;
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
public class TransactionCodesFacadeImpl implements TransactionCodesFacade {

    /**
     * The validator.
     */
    @Autowired
    private TransactionCodesValidator validator;

    /**
     * The parser.
     */
    @Autowired
    private TransactionCodesParser parser;

    /**
     * The service.
     */
    @Autowired
    private TransactionCodesService service;



    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ADD")
    public ResponseEntity<ResponseDataDTO> add(final TransactionCodesDto dto) {
        ResponseDataDTO dto1 =this.service.add(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:MODIFY")
    public ResponseEntity<ResponseDataDTO> modifySelect(final TransactionCodesDto dto) {
        return new ResponseEntity<>(this.service.modifySelect(dto),HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:MODIFY")
    public ResponseEntity<ResponseDataDTO> modifySubmit(final TransactionCodesDto dto) {
        return new ResponseEntity<>(this.service.modifySubmit(dto),HttpStatus.OK);
    }

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ENQUIRY")
    public ResponseEntity<ResponseDataDTO> inquiry(final TransactionCodesDto dto) {
        return new ResponseEntity<>(this.service.inquiry(dto),HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(final TransactionCodesDto dto) {
        return new ResponseEntity<>(this.service.verifyDeleteSelect(dto),HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifySubmit(final TransactionCodesDto dto) {
        return new ResponseEntity<>(this.service.verifySubmit(dto),HttpStatus.OK);
    }

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:DELETE")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(final TransactionCodesDto dto) {
        return new ResponseEntity<>(this.service.deleteSubmit(dto),HttpStatus.OK);
    }

    /**
     * Creates the.
     *
     * @param transactionCodeDto the transaction code dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ADD")
    public ResponseEntity<ResponseDataDTO> create(final TransactionCodesDto transactionCodeDto) {
        final Map<String, String> errorMessages = new HashMap<>();
        if (BooleanUtils.isFalse(this.validator.isValid(transactionCodeDto))) {
            return APIResponseBuilder.buildErrorResponse("invalid input", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (BooleanUtils.isFalse(this.validator.isValidRecord(transactionCodeDto, errorMessages))) {
            return APIResponseBuilder.buildErrorResponse("Data already exits", errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        this.service.create(transactionCodeDto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_SAVED_MESSAGE),
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
        ResponseDataDTO dataDto = this.service.delete(fileName,remarks);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param transactionCodesdto the transaction codesdto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(
            final TransactionCodesDto transactionCodesdto) {
        this.service.deleteTransactionCodes(transactionCodesdto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_DELETED_MESSAGE),
                HttpStatus.OK);
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ENQUIRY")
    public ResponseEntity<List<TransactionCodesDtoAuditLogWrapper>> getAllRecords() {
        return new ResponseEntity<>(this.service.getAllRecords(), HttpStatus.OK);
    }

    /**
     * Gets the transaction details.
     *
     * @param id the id
     * @return the transaction details
     */

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ENQUIRY")
    public ResponseEntity<TransactionCodesDtoAuditLogWrapper> getTransactionDetails(final Integer id) {
        return new ResponseEntity<>(this.service.getTransactionDetails(id), HttpStatus.OK);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getUnverifiedFiles() {
        final List<String> fileNames = this.service.getUnverifiedFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getInquiryFiles() {
        final List<String> fileNames = this.service.getInquiryFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ENQUIRY")
    public ResponseEntity<List<TransactionCodesDtoAuditLogWrapper>> getUnverifiedRecords() {
        return new ResponseEntity<>(this.service.getUnverifiedRecords(), HttpStatus.OK);
    }

    /**
     * Gets the unverified transactions.
     *
     * @param fileName the file name
     * @return the unverified transactions
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<TransactionCodesDto>> getUnverifiedTransactions(
            final String fileName) {
        final List<TransactionCodesDto> result = this.service.getUnverifiedTransactions(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:ENQUIRY")
    public ResponseEntity<List<TransactionCodesDto>> getVerifiedRecords() {
        return new ResponseEntity<>(this.service.getVerifiedRecords(), HttpStatus.OK);
    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final TransactionCodesDto dto) {
        final Map<String, String> errorMessages = new HashMap<>();
        if (BooleanUtils.isFalse(this.validator.isValid(dto))) {
            return APIResponseBuilder.buildErrorResponse("Invalid input", errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
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
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:UPLOAD")
    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file) {
        final Map<String, String> errorMessages = new HashMap<>();
        final List<TransactionCodesDto> transactionList;
        try {
            if (BooleanUtils.isFalse(this.validator.isValidFile(file, errorMessages))) {
                return APIResponseBuilder.buildErrorResponse("Validation failed", errorMessages,
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
            transactionList = this.parser.parse(file);
        } catch (final DuplicateRecordException | InvalidDataException | IOException e) {
            return APIResponseBuilder.buildErrorResponse(e.getMessage(), errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (BooleanUtils.isFalse(this.validator.isValidRecords(transactionList, errorMessages))) {
            return APIResponseBuilder.buildErrorResponse("Validation failed", errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

       ResponseDataDTO dataDto= this.service.upload(transactionList);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);

    }

    /**
     * Verify.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final String fileName) {
        ResponseDataDTO  dataDto = this.service.verify(fileName);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);

    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:LIST_OF_TRANSACTION_CODES:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final TransactionCodesDto dto) {
        this.service.verify(dto);
        return new ResponseEntity<>(APIResponseBuilder.buildResponse(CommonConstants.DATA_VERIFIED_MESSAGE),
                HttpStatus.OK);
    }
}
