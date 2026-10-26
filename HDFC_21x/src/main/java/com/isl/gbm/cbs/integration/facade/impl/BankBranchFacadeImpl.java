package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.BankBranchStatus;
import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BankBranchDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.BankBranchFacade;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.cbs.integration.parser.BankBranchFileParser;
import com.isl.gbm.cbs.integration.service.BankBranchService;
import com.isl.gbm.cbs.integration.validator.BankBranchFileValidator;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.utils.APIResponseBuilder;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
public class BankBranchFacadeImpl implements BankBranchFacade {

    /**
     * The bank branch validator.
     */
    @Autowired
    private BankBranchFileValidator bankBranchValidator;

    /**
     * The bank branch parser.
     */
    @Autowired
    private BankBranchFileParser bankBranchParser;

    /**
     * The bank branch service.
     */
    @Autowired
    private BankBranchService bankBranchService;


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
        errorDto.setMessage(errorMessages.get("message"));
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
     * @param bankBranchDto the bank branch dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ADD")
    public ResponseEntity<ResponseDataDTO> create(final BankBranchDto bankBranchDto) {
        final Map<String, String> errorMessages = new HashMap<>();
        if (BooleanUtils.isFalse(this.bankBranchValidator.isValidRecord(bankBranchDto, errorMessages))) {
            return this.buildErrorResponse("SolId Exists in DataBase", errorMessages);
        }
        this.bankBranchService.create(bankBranchDto);
        final ResponseDataDTO responseDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_SAVED_MESSAGE);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param bankBranchDto the bank branch dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final BankBranchDto bankBranchDto) {
        this.bankBranchService.delete(bankBranchDto);
        final ResponseDataDTO responseDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_DELETED_MESSAGE);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Delete banks.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:VERIFY")
    public ResponseEntity<ResponseDataDTO> deleteBanks(final String fileName, final String remarks) {
       ResponseDataDTO responseDataDTO= this.bankBranchService.deleteBanks(fileName, remarks);
        return new ResponseEntity<>(responseDataDTO, HttpStatus.OK);
    }

    /**
     * Gets the all bank details.
     *
     * @return the all bank details
     */
    @Override
    public ResponseEntity<List<BankBranchDto>> getAllBankDetails() {
        final List<BankBranchDto> result = this.bankBranchService.getAllBankDetails();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the all bank details for inquiry.
     *
     * @return the all bank details for inquiry
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ENQUIRY")
    public ResponseEntity<List<BankBranchDtoAuditLogWrapper>> getAllBankDetailsForInquiry() {
        final List<BankBranchDtoAuditLogWrapper> result = this.bankBranchService.getAllBankDetailsForInquiry();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the bank branch details.
     *
     * @param id the id
     * @return the bank branch details
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ENQUIRY")
    public ResponseEntity<BankBranchDtoAuditLogWrapper> getBankBranchDetails(final int id) {
        final BankBranchDtoAuditLogWrapper wrapper = this.bankBranchService.getBankBranchDetails(id);
        return new ResponseEntity<>(wrapper, HttpStatus.OK);
    }

    /**
     * Gets the bank branch status.
     *
     * @return the bank branch status
     */
    @Override
    public ResponseEntity<Map<Integer, String>> getBankBranchStatus() {
        final Map<Integer, String> statusMap = new HashMap<>();
        for (final BankBranchStatus status : BankBranchStatus.values()) {
            statusMap.put(status.getStatus(), status.getDescription());
        }
        return new ResponseEntity<>(statusMap, HttpStatus.OK);
    }

    /**
     * Gets the sol idand description.
     *
     * @return the sol idand description
     */
    @Override
    public ResponseEntity<List<BankBranchDto>> getSolIdandDescription() {
        final List<BankBranchDto> result = this.bankBranchService.getSolIdandDescription();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the unverified bank details.
     *
     * @param fileName the file name bulk upload inquiry
     * @return the unverified bank details
     */

    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<BankBranchDto>> getUnverifiedBankDetails(final String fileName) {
        final List<BankBranchDto> result = this.bankBranchService.getUnverifiedBankDetails(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<BankBranchDto>> getInquiryBankDetails(final String fileName) {
        final List<BankBranchDto> result = this.bankBranchService.getInquiryBankDetails(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files Bulk Upload
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getUnverifiedFiles() {
        final List<String> fileNames = this.bankBranchService.getUnverifiedFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<String>> getInquiryFiles() {
        final List<String> fileNames = this.bankBranchService.getInquiryFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ENQUIRY")
    public ResponseEntity<List<BankBranchDtoAuditLogWrapper>> getUnverifiedRecords() {
        final List<BankBranchDtoAuditLogWrapper> result = this.bankBranchService.getUnverifiedRecords();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:VERIFY")
    public ResponseEntity<List<BankBranchDto>> getVerifiedRecords() {
        final List<BankBranchDto> bankBranchDtoList = this.bankBranchService.getVerifiedRecords();
        return new ResponseEntity<>(bankBranchDtoList, HttpStatus.OK);
    }

    /**
     * Save details.
     *
     * @param file the file
     * @return the response entity
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:UPLOAD")
    public ResponseEntity<ResponseDataDTO> saveDetails(final MultipartFile file) {
/**/        final Map<String, String> errorMessages = new HashMap<>();
        List<BankBranchDto> branchBankDtoList;
        try {
            if (BooleanUtils.isFalse(this.bankBranchValidator.isValidFile(file, errorMessages))) {
                return this.buildErrorResponse("Validation failed", errorMessages);
            }
            branchBankDtoList = this.bankBranchParser.parseFile1(file);
        } catch (final DuplicateRecordException | InvalidDataException | IOException e) {
            errorMessages.put("message",e.getMessage());
            return this.buildErrorResponse(e.getMessage(), errorMessages);
        }

        if (BooleanUtils.isFalse(this.bankBranchValidator.isValidRecords(branchBankDtoList, errorMessages))) {
            return this.buildErrorResponse("Validation failed", errorMessages);
        }
        ResponseDataDTO  responseDataDTO=this.bankBranchService.saveDetails(branchBankDtoList);
       if(responseDataDTO.getMessage().equalsIgnoreCase("SUCCESS")) {
           return new ResponseEntity<>(responseDataDTO, HttpStatus.OK);
       }
       else{
           return new ResponseEntity<>(responseDataDTO, HttpStatus.UNPROCESSABLE_ENTITY);
       }
    }

    /**
     * Update.
     *
     * @param bankBranchDto the bank branch dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final BankBranchDto bankBranchDto) {
        this.bankBranchService.update(bankBranchDto);
        final ResponseDataDTO responseDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_UPDATED_MESSAGE);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Verify.
     *
     * @param bankBranchDto the bank branch dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final BankBranchDto bankBranchDto) {
        this.bankBranchService.verify(bankBranchDto);
        final ResponseDataDTO responseDto = APIResponseBuilder.buildResponse(CommonConstants.DATA_VERIFIED_MESSAGE);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Verify banks.
     *
     * @param fileName the file name
     * @return the response entity
     */
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifyBanks(final String fileName) {
         ResponseDataDTO responseDataDTO = this.bankBranchService.verifyBanks(fileName);
        return new ResponseEntity<>(responseDataDTO, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ADD")
    public ResponseDataDTO addDetails(BankBranchDto dto) {
        return bankBranchService.addDetails(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:ENQUIRY")
    public ResponseDataDTO inquiry(BankBranchDto dto) {
        return bankBranchService.inquiry(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:VERIFY")
    public ResponseDataDTO verDelDetails(BankBranchDto dto) {
        return bankBranchService.verDelDetails(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:MODIFY")
    public ResponseDataDTO modifyDetails(BankBranchDto dto) {
        return bankBranchService.modifyDetails(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:MODIFY")
    public ResponseDataDTO modify(BankBranchDto dto) {
        return bankBranchService.modify(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:VERIFY")
    public ResponseDataDTO verifySubmit(BankBranchDto dto) {
        return bankBranchService.verifySubmit(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:BRANCHES_OF_THE_BANK:DELETE")
    public ResponseDataDTO deleteSubmit(BankBranchDto dto) {
        return bankBranchService.deleteSubmit(dto);
    }

    @Override
    public List<BankBranchMaster> getAllBranchId(BankBranchDto dto) {
        return bankBranchService.getAllBranchId(dto);
    }



    @Override
    public ResponseEntity<ResponseDataDTO> getUnverifiedBankDetailsDownload(CbsCommonDTo dto) throws IOException, InvalidFormatException {
        return bankBranchService.getUnverifiedBankDetailsDownload(dto);
    }


}
