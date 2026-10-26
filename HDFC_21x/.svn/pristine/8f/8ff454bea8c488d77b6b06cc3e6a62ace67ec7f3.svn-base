/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService;
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
 * The Class OfficeAccountPlaceHolderValidator.
 */
@Component
public class OfficeAccountPlaceHolderValidator {

    /**
     * The Constant BLANK_CHARACTER.
     */
    private static final String BLANK_CHARACTER = " ";

    /**
     * The Constant HEADERS.
     */
    private static final String HEADERS = "BACID,BACID_DESC,SCHM_TYPE,ADDITIONAL1,ADDITIONAL2,ADDITIONAL3";

    /**
     * The office account place holder service.
     */
    @Autowired
    private OfficeAccountPlaceHolderService officeAccountPlaceHolderService;

    /**
     * Checks if is data exists.
     *
     * @param dto the dto
     * @return true, if is data exists
     */
    public boolean isDataExists(final OfficeAccountPlaceHolderDto dto) {

        final List<OfficeAccountPlaceHolderDto> savedDtoList = this.officeAccountPlaceHolderService
                .getOfficeAccountPlaceHolderDetails(dto.getBacid());

        for (final OfficeAccountPlaceHolderDto savedDto : savedDtoList) {
            if ((null == dto.getOfficeId()) || BooleanUtils.isFalse(dto.getOfficeId().equals(savedDto.getOfficeId()))) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
    }

    /**
     * Checks if is valid.
     *
     * @param dto the dto
     * @return true, if is valid
     */
    public boolean isValid(final OfficeAccountPlaceHolderDto dto) {
        if (dto.getBacid() == BLANK_CHARACTER) {
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
        if (file.isEmpty() || this.officeAccountPlaceHolderService.fileNameExists(fileName)) {
            errorMessages.put("File_Exists", "File already exists");
            return Boolean.FALSE;
        }

        final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        final String contentType = file.getContentType();

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

        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 6);
    }

    /**
     * Checks if is valid records.
     *
     * @param dtoList       the dto list
     * @param errorMessages the error messages
     * @return true, if is valid records
     */
    public boolean isValidRecords(final List<OfficeAccountPlaceHolderDto> dtoList,
                                  final Map<String, String> errorMessages) {

        if (CollectionUtils.isEmpty(dtoList)) {
            errorMessages.put("File_Empty", "File is empty");
            return Boolean.FALSE;
        }

        final List<OfficeAccountPlaceHolderDto> recordListDtls = this.officeAccountPlaceHolderService.recordList();

        if (CollectionUtils.isEmpty(recordListDtls)) {
            return Boolean.TRUE;
        }

        for (final OfficeAccountPlaceHolderDto dto : dtoList) {
            for (final OfficeAccountPlaceHolderDto recordDto : recordListDtls) {
                if (StringUtils.equals(dto.getBacid(), recordDto.getBacid())
                        && StringUtils.equals(dto.getSchmType(), recordDto.getSchmType())) {
                    errorMessages.put("Duplicate_Data", "Duplicate data available");
                    return Boolean.FALSE;
                }
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Sets the office account place holder service.
     *
     * @param officeAccountPlaceHolderService the new office account place holder service
     */
    public void setOfficeAccountPlaceHolderService(
            final OfficeAccountPlaceHolderService officeAccountPlaceHolderService) {
        this.officeAccountPlaceHolderService = officeAccountPlaceHolderService;
    }

}
