/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BranchIfscDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.service.BranchIfscDetailsService;
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
 * The Class BranchIfscDetailsValidator.
 */
@Component
public class BranchIfscDetailsValidator {

    /**
     * The Constant INVALID_FILE_MESSAGE.
     */
    private static final String INVALID_FILE_MESSAGE = "Please upload a valid excel file";

    /**
     * The Constant INVALID_FILE_KEY.
     */
    private static final String INVALID_FILE_KEY = "Invalid_File";

    /**
     * The Constant HEADERS.
     */
    private static final String HEADERS = "BANK_CODE,BRANCH_CODE,BANK_IDENTIFIER,PAYSYS_ID,DEL_FLG,Text 1,Text 2,Text 3";

    /**
     * The branch ifsc details service.
     */
    @Autowired
    private BranchIfscDetailsService branchIfscDetailsService;

    /**
     * Checks if is data exists.
     *
     * @param dto the dto
     * @return the boolean
     */
    public Boolean isDataExists(final BranchIfscDetailsDto dto) {
        final List<BranchIfscDetailsDto> savedDtoList = this.branchIfscDetailsService
                .getBranchIfscDetails(dto.getBankCode(), dto.getBranchCode());

        for (final BranchIfscDetailsDto savedDto : savedDtoList) {
            if ((null == dto.getBranchDetailsId())
                    || BooleanUtils.isFalse(dto.getBranchDetailsId().equals(savedDto.getBranchDetailsId()))) {
                return Boolean.TRUE;
            }
        }

        return Boolean.FALSE;
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
        if (file.isEmpty() || this.branchIfscDetailsService.fileNameExists(fileName)) {
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

        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 8);
    }

    /**
     * Checks if is valid records.
     *
     * @param dtoList       the dto list
     * @param errorMessages the error messages
     * @return true, if is valid records
     */
    public boolean isValidRecords(final List<BranchIfscDetailsDto> dtoList, final Map<String, String> errorMessages) {

        if (CollectionUtils.isEmpty(dtoList)) {
            errorMessages.put("File_Empty", "File is empty");
            return Boolean.FALSE;
        }

        final List<BranchIfscDetailsDtoAuditLogWrapper> wrapperList = this.branchIfscDetailsService.list();

        if (CollectionUtils.isEmpty(wrapperList)) {
            return Boolean.TRUE;
        }

        for (final BranchIfscDetailsDto dto : dtoList) {
            for (final BranchIfscDetailsDtoAuditLogWrapper wrapper : wrapperList) {
                if (StringUtils.equals(dto.getBranchCode(), wrapper.getDto().getBranchCode())
                        && StringUtils.equals(dto.getBankCode(), wrapper.getDto().getBankCode())) {
                    errorMessages.put("Duplicate_Data", "Duplicate data available");
                    return Boolean.FALSE;
                }
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Sets the branch ifsc details service.
     *
     * @param branchIfscDetailsService the new branch ifsc details service
     */
    public void setBranchIfscDetailsService(final BranchIfscDetailsService branchIfscDetailsService) {
        this.branchIfscDetailsService = branchIfscDetailsService;
    }

}
