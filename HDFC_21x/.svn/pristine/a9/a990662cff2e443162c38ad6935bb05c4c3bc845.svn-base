package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GbmUserDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.UserDetailsFacade;
import com.isl.gbm.cbs.integration.parser.UserDetailsFileParser;
import com.isl.gbm.cbs.integration.service.UserDetailsService;
import com.isl.gbm.cbs.integration.validator.UserDetailsValidator;
import com.isl.gbm.common.dto.ResponseDataDTO;
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
public class UserDetailsFacadeImpl implements UserDetailsFacade {


    /**
     * The validator.
     */
    @Autowired
    private UserDetailsValidator validator;

    /**
     * The parser.
     */
    @Autowired
    private UserDetailsFileParser parser;

    /**
     * The service.
     */
    @Autowired
    private UserDetailsService service;

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ADD")
    public ResponseEntity<ResponseDataDTO> add(final GbmUserDetailsDto dto) {
        ResponseDataDTO dto1 = this.service.add(dto);
        return com.isl.gbm.common.util.APIResponseBuilder.buildResponseFromDto(dto1);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:MODIFY")
    public ResponseEntity<ResponseDataDTO> modifySelect(final GbmUserDetailsDto dto) {
        return new ResponseEntity<>(this.service.modifySelect(dto),HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:MODIFY")
    public ResponseEntity<ResponseDataDTO> modifySubmit(final GbmUserDetailsDto dto) {
        return new ResponseEntity<>(this.service.modifySubmit(dto),HttpStatus.OK);
    }

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ENQUIRY")
    public ResponseEntity<ResponseDataDTO> inquiry(final GbmUserDetailsDto dto) {
        return new ResponseEntity<>(this.service.inquiry(dto),HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifyDeleteSelect(final GbmUserDetailsDto dto) {
        return new ResponseEntity<>(this.service.verifyDeleteSelect(dto),HttpStatus.OK);
    }
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:VERIFY")
    public ResponseEntity<ResponseDataDTO> verifySubmit(final GbmUserDetailsDto dto) {
        return new ResponseEntity<>(this.service.verifySubmit(dto),HttpStatus.OK);
    }

    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:DELETE")
    public ResponseEntity<ResponseDataDTO> deleteSubmit(final GbmUserDetailsDto dto) {
        return new ResponseEntity<>(this.service.deleteSubmit(dto),HttpStatus.OK);
    }
    /**
     * Creates the.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ADD")
    public ResponseEntity<ResponseDataDTO> create(final GbmUserDetailsDto dto) {
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("User Id already exists", null,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (BooleanUtils.isFalse(this.validator.isValid(dto))) {
            return APIResponseBuilder.buildErrorResponse("Invalid input", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.service.create(dto);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse("Data saved successfully");
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final GbmUserDetailsDto dto) {
        this.service.delete(dto);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse("Data deleted successfully");
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     * @return the response entity
     */

    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks) {
        ResponseDataDTO dataDto= this.service.delete(fileName,remarks);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
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
     * Gets the unverified instruments.
     *
     * @param fileName the file name
     * @return the unverified instruments
     */
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:ENQUIRY")
    public ResponseEntity<List<GbmUserDetailsDto>> getUnverifiedInstruments(final String fileName) {
        final List<GbmUserDetailsDto> result = this.service.getUnverifiedUserDetails(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ENQUIRY")
    public ResponseEntity<List<GbmUserDetailsDtoAuditLogWrapper>> getUnverifiedRecords() {
        return new ResponseEntity<>(this.service.getUnverifiedRecords(), HttpStatus.OK);
    }

    /**
     * Gets the user work details.
     *
     * @param gbmUserDetailsId the gbm user details id
     * @return the user work details
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ENQUIRY")
    public ResponseEntity<GbmUserDetailsDto> getUserWorkDetails(final Integer gbmUserDetailsId) {
        return new ResponseEntity<>(this.service.getUserWorkDetails(gbmUserDetailsId), HttpStatus.OK);
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ENQUIRY")
    public ResponseEntity<List<GbmUserDetailsDtoAuditLogWrapper>> getVerifiedRecords() {
        return new ResponseEntity<>(this.service.getVerifiedRecords(), HttpStatus.OK);
    }

    /**
     * List.
     *
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:ENQUIRY")
    public ResponseEntity<List<GbmUserDetailsDto>> list() {
        return new ResponseEntity<>(this.service.getAllRecords(), HttpStatus.OK);
    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final GbmUserDetailsDto dto) {
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("User Id already exists", null,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        this.service.update(dto);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse("Data updated successfully");
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
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
        final List<GbmUserDetailsDto> userList;
        try {
            if (BooleanUtils.isFalse(this.validator.isValidFile(file, errorMessages))) {
                return APIResponseBuilder.buildErrorResponse("Validation failed", errorMessages,
                        HttpStatus.UNPROCESSABLE_ENTITY);
            }
            userList = this.parser.parse(file);
        } catch (final DuplicateRecordException | InvalidDataException | IOException e) {
            return APIResponseBuilder.buildErrorResponse(e.getMessage(), errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (BooleanUtils.isFalse(this.validator.isValidRecords(userList, errorMessages))) {
            return APIResponseBuilder.buildErrorResponse("Validation failed", errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        ResponseDataDTO dataDto =this.service.upload(userList);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);

    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:USER_WORK_CLASS_INFORMATION:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final GbmUserDetailsDto dto) {
        this.service.verify(dto);
        final ResponseDataDTO dataDto = APIResponseBuilder.buildResponse("Data verified successfully");
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
        ResponseDataDTO dataDto =  this.service.verify(fileName);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

}
