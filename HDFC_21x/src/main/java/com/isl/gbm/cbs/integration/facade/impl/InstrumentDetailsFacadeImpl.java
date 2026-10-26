package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.InstrumentDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.InstrumentDetailsFacade;
import com.isl.gbm.cbs.integration.parser.InstrumentDetailsParser;
import com.isl.gbm.cbs.integration.service.InstrumentDetailsService;
import com.isl.gbm.cbs.integration.validator.InstrumentDetailsValidator;
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
public class InstrumentDetailsFacadeImpl implements InstrumentDetailsFacade {


    /**
     * The validator.
     */
    @Autowired
    private InstrumentDetailsValidator validator;

    /**
     * The parser.
     */
    @Autowired
    private InstrumentDetailsParser parser;

    /**
     * The service.
     */
    @Autowired
    private InstrumentDetailsService service;


    /**
     * Creates the.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ADD")
    public ResponseEntity<ResponseDataDTO> create(final InstrumentDetailsDto dto) {
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
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
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final InstrumentDetailsDto dto) {
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
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:DELETE")
    public ResponseEntity<ResponseDataDTO> delete(final String fileName,final String remarks) {
        ResponseDataDTO dataDto = this.service.delete(fileName,remarks);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ENQUIRY")
    public ResponseEntity<List<InstrumentDetailsDtoAuditLogWrapper>> getAllRecords() {
        return new ResponseEntity<>(this.service.list(), HttpStatus.OK);
    }

    /**
     * Gets the instrument details.
     *
     * @param instrumentId the instrument id
     * @return the instrument details
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ENQUIRY")
    public ResponseEntity<InstrumentDetailsDtoAuditLogWrapper> getInstrumentDetails(final Integer instrumentId) {
        return new ResponseEntity<>(this.service.getInstrumentDetails(instrumentId), HttpStatus.OK);
    }

    /**
     * Gets the instrument types.
     *
     * @return the instrument types
     */
    @Override
    public ResponseEntity<Map<String, String>> getInstrumentTypes() {
        return new ResponseEntity<>(this.service.getInstrumentTypes(), HttpStatus.OK);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    @Override
    public ResponseEntity<List<String>> getUnverifiedFiles() {
        final List<String> fileNames = this.service.getUnverifiedFiles();
        return new ResponseEntity<>(fileNames, HttpStatus.OK);
    }

    @Override
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
    public ResponseEntity<List<InstrumentDetailsDto>> getUnverifiedInstruments(final String fileName) {
        final List<InstrumentDetailsDto> result = this.service.getUnverifiedInstruments(fileName);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ENQUIRY")
    public ResponseEntity<List<InstrumentDetailsDtoAuditLogWrapper>> getUnverifiedRecords() {
        return new ResponseEntity<>(this.service.getUnverifiedRecords(), HttpStatus.OK);
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ENQUIRY")
    public ResponseEntity<List<InstrumentDetailsDto>> getVerifiedRecords() {
        return new ResponseEntity<>(this.service.getVerifiedRecords(), HttpStatus.OK);
    }

    /**
     * Update.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:MODIFY")
    public ResponseEntity<ResponseDataDTO> update(final InstrumentDetailsDto dto) {
        if (BooleanUtils.isFalse(this.validator.isValid(dto))) {
            return APIResponseBuilder.buildErrorResponse("Invalid input", null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if (BooleanUtils.isTrue(this.validator.isDataExists(dto))) {
            return APIResponseBuilder.buildErrorResponse("Data already exists", null, HttpStatus.UNPROCESSABLE_ENTITY);
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
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:UPLOAD")
    public ResponseEntity<ResponseDataDTO> upload(final MultipartFile file) throws IOException {

        final Map<String, String> errorMessages = new HashMap<>();

        if (BooleanUtils.isFalse(this.validator.isValidFile(file, errorMessages))) {
            return APIResponseBuilder.buildErrorResponse("Validation failed", errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        final List<InstrumentDetailsDto> instrumentDtoList;
        try {
            instrumentDtoList = this.parser.parse(file);
        } catch (final DuplicateRecordException | InvalidDataException | IOException e) {
            return APIResponseBuilder.buildErrorResponse(e.getMessage(), errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

        if (BooleanUtils.isFalse(this.validator.isValidRecords(instrumentDtoList, errorMessages))) {
            return APIResponseBuilder.buildErrorResponse("Validation failed", errorMessages,
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }

       ResponseDataDTO dataDto= this.service.upload(instrumentDtoList);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);

    }

    /**
     * Verify.
     *
     * @param dto the dto
     * @return the response entity
     */
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final InstrumentDetailsDto dto) {
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
    @Override
    @RequiresPermissions("CBS:CBS_BULK_UPLOAD:VERIFY")
    public ResponseEntity<ResponseDataDTO> verify(final String fileName) {
         ResponseDataDTO dataDto = this.service.verify(fileName);
        return new ResponseEntity<>(dataDto, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ADD")
    public ResponseDataDTO addDetails(InstrumentDetailsDto dto) {
        return service.addDetails(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:ENQUIRY")
    public ResponseDataDTO inquiry(InstrumentDetailsDto dto) {
        return service.inquiry(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:VERIFY")
    public ResponseDataDTO verDelDetails(InstrumentDetailsDto dto) {
        return service.verDelDetails(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:MODIFY")
    public ResponseDataDTO modifyDetails(InstrumentDetailsDto dto) {
        return service.modifyDetails(dto);
    }
    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:MODIFY")
    public ResponseDataDTO modify(InstrumentDetailsDto dto) {
        return service.modify(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:VERIFY")
    public ResponseDataDTO verifySubmit(InstrumentDetailsDto dto) {
        return service.verifySubmit(dto);
    }

    @Override
    @RequiresPermissions("GBM_CBS_MASTER_UPDATION:INSTRUMENT_DETAILS:DELETE")
    public ResponseDataDTO deleteSubmit(InstrumentDetailsDto dto) {
        return service.deleteSubmit(dto);
    }




}
