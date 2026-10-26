/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.InstrumentDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.service.InstrumentDetailsService;
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
 * The Class InstrumentDetailsValidator.
 */
@Component
public class InstrumentDetailsValidator {

    /**
     * The Constant BLANK_CHARACTER.
     */
    private static final Character BLANK_CHARACTER = ' ';

    /**
     * The instrument details service.
     */
    @Autowired
    private InstrumentDetailsService instrumentDetailsService;

    /**
     * Checks if is data exists.
     *
     * @param dto the dto
     * @return true, if is data exists
     */
    public boolean isDataExists(final InstrumentDetailsDto dto) {

        final List<InstrumentDetailsDto> savedDtoList = this.instrumentDetailsService
                .getInstrumentDetails(dto.getInstrumentType(), dto.getSchemeCode());

        for (final InstrumentDetailsDto savedDto : savedDtoList) {
            if ((null == dto.getInstrumentId())
                    || BooleanUtils.isFalse(dto.getInstrumentId().equals(savedDto.getInstrumentId()))) {
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
    public boolean isValid(final InstrumentDetailsDto dto) {
        if (dto.getDrCrIndicator() == BLANK_CHARACTER) {
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
     */
    public boolean isValidFile(final MultipartFile file, final Map<String, String> errorMessages) throws IOException {
        final String fileName = file.getOriginalFilename();
        if (file.isEmpty() || this.instrumentDetailsService.fileNameExists(fileName)) {
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

        return Boolean.TRUE;
    }

    /**
     * Checks if is valid records.
     *
     * @param instrumentDtoList the instrument dto list
     * @param errorMessages     the error messages
     * @return true, if is valid records
     */
    public boolean isValidRecords(final List<InstrumentDetailsDto> instrumentDtoList,
                                  final Map<String, String> errorMessages) {

        if (CollectionUtils.isEmpty(instrumentDtoList)) {
            errorMessages.put("File_Empty", "File is empty");
            return Boolean.FALSE;
        }

        final List<InstrumentDetailsDtoAuditLogWrapper> wrapperList = this.instrumentDetailsService.list();

        if (CollectionUtils.isEmpty(wrapperList)) {
            return Boolean.TRUE;
        }

        for (final InstrumentDetailsDto dto : instrumentDtoList) {
            for (final InstrumentDetailsDtoAuditLogWrapper wrapper : wrapperList) {
                if (StringUtils.equals(dto.getInstrumentType(), wrapper.getDto().getInstrumentType())
                        && StringUtils.equals(dto.getSchemeCode(), wrapper.getDto().getSchemeCode())) {
                    errorMessages.put("Duplicate_Data", "Duplicate data available");
                    return Boolean.FALSE;
                }
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Sets the instrument details service.
     *
     * @param instrumentDetailsService the new instrument details service
     */
    public void setInstrumentDetailsService(final InstrumentDetailsService instrumentDetailsService) {
        this.instrumentDetailsService = instrumentDetailsService;
    }

}
