/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.SchemeCodesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.service.SchemeCodesService;
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
 * The Class SchemeCodesValidator.
 */
@Component
public class SchemeCodesValidator {

    /**
     * The Constant BLANK_CHARACTER.
     */
    private static final String BLANK_CHARACTER = " ";

    /**
     * The Constant HEADERS.
     */
    private static final String HEADERS = "schemeCode,description,glSubHeadCode,schemeType,addText1,addText2,addText3";

    /**
     * The scheme codes service.
     */
    @Autowired
    private SchemeCodesService schemeCodesService;

    /**
     * Checks if is data exists.
     *
     * @param dto the dto
     * @return true, if is data exists
     */
    public boolean isDataExists(final SchemeCodesDto dto) {

        final List<SchemeCodesDtoAuditLogWrapper> wrapperList = this.schemeCodesService.getAllList();
        for (final SchemeCodesDtoAuditLogWrapper wrapper : wrapperList) {
            if (BooleanUtils.isTrue(dto.getSchemeCode().equals(wrapper.getDto().getSchemeCode()))) {
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
    public boolean isValid(final SchemeCodesDto dto) {
        if (dto.getSchemeCode() == BLANK_CHARACTER) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Checks if is valid file.
     *
     * @param file          the file
     * @param errorMessages the error messages
     * @return the boolean
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public Boolean isValidFile(final MultipartFile file, final Map<String, String> errorMessages) throws IOException {
        final String fileName = file.getOriginalFilename();
        final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        final String contentType = file.getContentType();

        assert fileName != null;
        if (BooleanUtils.isFalse(StringUtils.equals(extension, "xls") || BooleanUtils.isFalse(StringUtils.equals("xls".toUpperCase(), FileValidationUtils.getFileTypeByUsingMagicNumbers(FileValidationUtils.convertBytesToHexaDecimal(file.getBytes())))))
                || BooleanUtils.isFalse(StringUtils.equals(contentType, "application/vnd.ms-excel")) || BooleanUtils.isTrue(FileValidationUtils.checkDoubleExtension(fileName))) {
            errorMessages.put("Invalid_File", "Please upload an valid excel file");
            return Boolean.FALSE;
        }

        if (file.isEmpty() || this.schemeCodesService.fileNameExists(fileName)) {
            errorMessages.put("File_Exists", "File already exists");
            return Boolean.FALSE;
        }

        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 7);
    }

    /**
     * Checks if is valid records.
     *
     * @param schemeCodesDtoList the scheme codes dto list
     * @param errorMessages      the error messages
     * @return the boolean
     */
    public Boolean isValidRecords(final List<SchemeCodesDto> schemeCodesDtoList,
                                  final Map<String, String> errorMessages) {
        if (CollectionUtils.isEmpty(schemeCodesDtoList)) {
            errorMessages.put("File_Empty", "File is empty");
            return Boolean.FALSE;
        }
        final List<SchemeCodesDto> schemeCodesListDto = this.schemeCodesService.getSchemeCodesList();
        if (CollectionUtils.isEmpty(schemeCodesListDto)) {
            return Boolean.TRUE;
        }
        for (final SchemeCodesDto schemeCodesDto : schemeCodesDtoList) {
            for (final SchemeCodesDto existingdto : schemeCodesListDto) {
                if (StringUtils.equals(schemeCodesDto.getSchemeCode(), existingdto.getSchemeCode())) {
                    errorMessages.put("Duplicate_Data", "duplicate scheme code available");
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Sets the scheme codes service.
     *
     * @param schemeCodesService the new scheme codes service
     */
    public void setSchemeCodesService(final SchemeCodesService schemeCodesService) {
        this.schemeCodesService = schemeCodesService;
    }

}
