/*
 *
 */
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.service.OtherBankBranchesService;
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
 * The Class OtherBankBranchesValidator.
 */
@Component
public class OtherBankBranchesValidator {

    /**
     * The Constant BLANK_CHARACTER.
     */
    private static final String BLANK_CHARACTER = " ";

    /**
     * The Constant HEADERS.
     */
    private static final String HEADERS = "BR_CODE,BANK_CODE,MICR_CENTRE_CODE,MICR_BANK_CODE,MICR_BRANCH_CODE,BR_NAME,ADDR_1,ADDR_2,ADDR_3,CITY_CODE,STATE_CODE,PIN_CODE,additionalText1,additionalText2,additionalText3,BANK_IFSC_CODE";

    /**
     * The other bank branches service.
     */
    @Autowired
    private OtherBankBranchesService otherBankBranchesService;

    /**
     * Checks if is data exists.
     *
     * @param dto the dto
     * @return true, if is data exists
     */
    public boolean isDataExists(final OtherBankBranchesDto dto) {

        final List<OtherBankBranchesDto> savedDtoList = otherBankBranchesService
                .getOtherBankBranchesDetails(dto.getBranchCode(), dto.getBankCode());

        for (final OtherBankBranchesDto savedDto : savedDtoList) {
            if (null == dto.getBranchId() || BooleanUtils.isFalse(dto.getBranchCode().equals(savedDto.getBranchCode())
                    && dto.getBankCode().equals(savedDto.getBankCode()))) {
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
    public boolean isValid(final OtherBankBranchesDto dto) {
        if (dto.getBranchCode() == BLANK_CHARACTER) {
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
        if (file.isEmpty() || otherBankBranchesService.fileNameExists(fileName)) {
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

        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 16);
    }

    /**
     * Checks if is valid records.
     *
     * @param dtoList       the dto list
     * @param errorMessages the error messages
     * @return true, if is valid records
     */
    public boolean isValidRecords(final List<OtherBankBranchesDto> dtoList, final Map<String, String> errorMessages) {

        if (CollectionUtils.isEmpty(dtoList)) {
            errorMessages.put("File_Empty", "File is empty");
            return Boolean.FALSE;
        }

        final List<OtherBankBranchesDto> recordListDtls = otherBankBranchesService.recordList();

        if (CollectionUtils.isEmpty(recordListDtls)) {
            return Boolean.TRUE;
        }

        for (final OtherBankBranchesDto dto : dtoList) {
            for (final OtherBankBranchesDto recordDto : recordListDtls) {
                if (StringUtils.equals(dto.getBankIfscCode(), recordDto.getBankIfscCode())) {
                    errorMessages.put("Duplicate_Data", "IFSC Code is already exits : "+recordDto.getBankIfscCode());
                    return Boolean.FALSE;
                }
            }
        }

        return Boolean.TRUE;
    }

    /**
     * Sets the other bank branches service.
     *
     * @param otherBankBranchesService the new other bank branches service
     */
    public void setOtherBankBranchesService(OtherBankBranchesService otherBankBranchesService) {
        this.otherBankBranchesService = otherBankBranchesService;
    }

}
