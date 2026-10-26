/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.service.TransactionCodesService;
import com.isl.gbm.common.util.FileValidationUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The Class TransactionCodesValidator.
 */
@Component
public class TransactionCodesValidator {

    /**
     * The Constant BLANK_CHARACTER.
     */
    private static final String BLANK_CHARACTER = " ";

    /**
     * The Constant HEADERS.
     */
    private static final String HEADERS = "clgTranCode,clgTranCodeDesc,instrumentType,schemeCode,addText1,addText2,addText3";

    /**
     * The transaction codes service.
     */
    @Autowired
    private TransactionCodesService transactionCodesService;

    /**
     * Checks if is data exists.
     *
     * @param transactionCodeDto the transaction code dto
     * @return the boolean
     */
    public Boolean isDataExists(final TransactionCodesDto transactionCodeDto) {

        final List<TransactionCodesDto> savedDtoList = this.transactionCodesService
                .getTransactionDetails(transactionCodeDto.getClgTranCode(), transactionCodeDto.getInstrumentType());

        for (final TransactionCodesDto savedDto : savedDtoList) {
            if (BooleanUtils.isFalse(transactionCodeDto.getId().equals(savedDto.getId()))) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    /**
     * Checks if is valid.
     *
     * @param dto the dto
     * @return the boolean
     */
    public Boolean isValid(final TransactionCodesDto dto) {
        if (dto.getClgTranCode() == BLANK_CHARACTER) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Checks if is valid file.
     *
     * @param file          the file
     * @param errorMessages the error messages
     * @return true, if is valid file
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public boolean isValidFile(final MultipartFile file, final Map<String, String> errorMessages) throws IOException {
        final String fileName = file.getOriginalFilename();
        final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        final String contentType = file.getContentType();
        if (file.isEmpty() || this.transactionCodesService.fileNameExists(fileName)) {
            errorMessages.put("File_Exists", "File already exists");
            return Boolean.FALSE;
        }

        assert fileName != null;
        if (BooleanUtils.isFalse(StringUtils.equals(extension, "xls") || BooleanUtils.isFalse(StringUtils.equals("xls".toUpperCase(), FileValidationUtils.getFileTypeByUsingMagicNumbers(FileValidationUtils.convertBytesToHexaDecimal(file.getBytes())))))
                || BooleanUtils.isFalse(StringUtils.equals(contentType, "application/vnd.ms-excel")) || BooleanUtils.isTrue(FileValidationUtils.checkDoubleExtension(fileName))) {
            errorMessages.put("Invalid_File", "Please upload an valid excel file");
            return Boolean.FALSE;
        }

        if(BooleanUtils.isTrue(FileValidationUtils.checkFileSize(file.getSize()))){
            errorMessages.put("Invalid_File", "File size should be less than or equal to 2MB");
            return Boolean.FALSE;
        }

        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 7);
    }

    /**
     * Checks if is valid record.
     *
     * @param transactionCodeDto the transaction code dto
     * @param errorMessages      the error messages
     * @return the boolean
     */
    public Boolean isValidRecord(final TransactionCodesDto transactionCodeDto,
                                 final Map<String, String> errorMessages) {
        if (this.transactionCodesService.isTranCode(transactionCodeDto.getClgTranCode())) {
            errorMessages.put("Duplicate_Data", "This TranCode already exists in DataBase");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Checks if is valid records.
     *
     * @param transactionCodesDtoList the transaction codes dto list
     * @param errorMessages           the error messages
     * @return true, if is valid records
     */
    public boolean isValidRecords(final List<TransactionCodesDto> transactionCodesDtoList,
                                  final Map<String, String> errorMessages) {

        if (CollectionUtils.isEmpty(transactionCodesDtoList)) {
            errorMessages.put("File_Empty", "File is empty");
            return Boolean.FALSE;
        }

        final List<TransactionCodesDto> savedDtoList = this.transactionCodesService.list();

        if (CollectionUtils.isEmpty(savedDtoList)) {
            return Boolean.TRUE;
        }

        for (final TransactionCodesDto dto : transactionCodesDtoList) {
            for (final TransactionCodesDto savedDto : savedDtoList) {
                if (StringUtils.equals(dto.getClgTranCode(), savedDto.getClgTranCode())
                        && StringUtils.equals(dto.getInstrumentType(), savedDto.getInstrumentType())) {
                    errorMessages.put("Duplicate_Data", "Duplicate data available");
                    return Boolean.FALSE;
                }
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Sets the transaction codes service.
     *
     * @param transactionCodesService the new transaction codes service
     */
    public void setTransactionCodesService(final TransactionCodesService transactionCodesService) {
        this.transactionCodesService = transactionCodesService;
    }
}
