/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.service.UserDetailsService;
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
 * The Class UserDetailsValidator.
 */
@Component
public class UserDetailsValidator {

    /**
     * The Constant HEADERS.
     */
    private static final String HEADERS = "USER_ID,DEL_FLG,USER_WORK_CLASS,SOL_ID,USER_SOL_TENOR,USER_MAX_INACTIVE_TIME,USER_ACCT_EXPY_DATE,"
            + "USER_PW_EXPY_DATE,USER_APPL_NAME,NEW_USER_FLG,DEFAULT_TERM_ID,VIRTUAL_FLG,Text 1,Text 2,Text 3";

    private static final String BLANK_CHARACTER = "";

    /**
     * The user details service.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Checks if is data exists.
     *
     * @param dto the dto
     * @return true, if is data exists
     */
    public boolean isDataExists(final GbmUserDetailsDto dto) {

        final List<GbmUserDetailsDto> savedDtoList = this.userDetailsService.getUser(dto.getUserId());

        for (final GbmUserDetailsDto savedDto : savedDtoList) {
            if (StringUtils.equalsIgnoreCase(dto.getUserId(), savedDto.getUserId())
                    && (!savedDto.getGbmUserDetailsId().equals(dto.getGbmUserDetailsId()))) {
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
    public boolean isValid(final GbmUserDetailsDto dto) {

        if (dto.getUserId() == BLANK_CHARACTER) {

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
        if (file.isEmpty() || this.userDetailsService.fileNameExists(fileName)) {
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

        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 15);
    }

    /**
     * Checks if is valid records.
     *
     * @param dtoList       the dto list
     * @param errorMessages the error messages
     * @return true, if is valid records
     */
    public boolean isValidRecords(final List<GbmUserDetailsDto> dtoList, final Map<String, String> errorMessages) {

        if (CollectionUtils.isEmpty(dtoList)) {
            errorMessages.put("File_Empty", "File is empty");
            return Boolean.FALSE;
        }

        final List<GbmUserDetailsDto> savedDtoList = this.userDetailsService.list();

        if (CollectionUtils.isEmpty(savedDtoList)) {
            return Boolean.TRUE;
        }

        for (final GbmUserDetailsDto dto : dtoList) {
            for (final GbmUserDetailsDto savedDto : savedDtoList) {
                if (StringUtils.equals(dto.getUserId(), savedDto.getUserId())) {
                    errorMessages.put("Duplicate_Data", "Duplicate data available");
                    return Boolean.FALSE;
                }
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Sets the user details service.
     *
     * @param userDetailsService the new user details service
     */
    public void setUserDetailsService(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
