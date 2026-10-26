package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.service.OtherBankDetailsService;
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

@Component
public class OtherBankDetailsValidator {
    @Autowired
    private OtherBankDetailsService otherBankDetailsService;

    private static final String BLANK_CHARACTER = "";

    private static final String HEADERS = "BANK_CODE,BANK_NAME,BANK_SHORT_NAME,ADDR1,ADDR2,CITY,PIN_CODE";

    /**
     * Checks if is valid file.
     *
     * @param file          the file
     * @param errorMessages the error messages
     * @return true, if is valid file
     */
    public boolean isValidFile(final MultipartFile file, final Map<String, String> errorMessages) throws IOException {
        final String fileName = file.getOriginalFilename();
        final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        final String contentType = file.getContentType();

        assert fileName != null;
        if (BooleanUtils.isFalse(StringUtils.equals(extension, "xls") || BooleanUtils.isFalse(StringUtils.equals("xls".toUpperCase(), FileValidationUtils.getFileTypeByUsingMagicNumbers(FileValidationUtils.convertBytesToHexaDecimal(file.getBytes())))))
                || BooleanUtils.isFalse(StringUtils.equals(contentType, "application/vnd.ms-excel")) || BooleanUtils.isTrue(FileValidationUtils.checkDoubleExtension(fileName))) {
            errorMessages.put("Invalid_File", "Please upload an valid excel file");
            return Boolean.FALSE;
        }

        if (file.isEmpty() || this.otherBankDetailsService.fileNameExists(fileName)) {
            errorMessages.put("File_Exists", "File already exists");
            return Boolean.FALSE;
        }

        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 7);
    }

    /**
     * Checks if is valid records.
     *
     * @param otherBankDetailsDtoList the other bank details dto list
     * @param errorMessages           the error messages
     * @return the boolean
     */
    public Boolean isValidRecords(final List<OtherBankDetailsDto> otherBankDetailsDtoList,
                                  final Map<String, String> errorMessages) {

        if (CollectionUtils.isEmpty(otherBankDetailsDtoList)) {
            errorMessages.put("File_Empty", "File is empty");
            return Boolean.FALSE;
        }
        final List<OtherBankDetailsDto> otherBankDetailsListDto = this.otherBankDetailsService.getOtherBankDetailsList();
        if (CollectionUtils.isEmpty(otherBankDetailsListDto)) {
            return Boolean.TRUE;
        }
        for (final OtherBankDetailsDto otherBankDetailsDto : otherBankDetailsDtoList) {
            for (final OtherBankDetailsDto existingdto : otherBankDetailsListDto) {
                if (StringUtils.equals(otherBankDetailsDto.getBankCode(), existingdto.getBankCode())) {
                    errorMessages.put("Duplicate_Data", "Duplicate data available");
                    return Boolean.FALSE;
                }
                if (StringUtils.isEmpty(otherBankDetailsDto.getBankCode())) {
                    errorMessages.put("null_Data", "null data available");
                    return Boolean.FALSE;
                }
                if (StringUtils.length(otherBankDetailsDto.getBankCode()) > 8) {
                    errorMessages.put("invalid-Data", "Invalid Bank Code available");
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }


    /**
     * Checks if is valid record.
     *
     * @param otherBankDetailsDto the other bank details dto
     * @return the boolean
     */
    public Boolean isValidRecord(final OtherBankDetailsDto otherBankDetailsDto) {
        final List<OtherBankDetailsDto> otherBankDetailsListDto = this.otherBankDetailsService.getOtherBankDetailsList();
        for (final OtherBankDetailsDto existingdto : otherBankDetailsListDto) {
            if (StringUtils.equals(otherBankDetailsDto.getBankCode(), existingdto.getBankCode())) {

                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;

    }

    /**
     * Checks if is valid.
     *
     * @param dto the dto
     * @return true, if is valid
     */
    public boolean isValid(final OtherBankDetailsDto dto) {
        if (dto.getBankCode() == BLANK_CHARACTER) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    /**
     * Checks if is data exists.
     *
     * @param dto the dto
     * @return true, if is data exists
     */
    public boolean isDataExists(final OtherBankDetailsDto dto) {

        final List<OtherBankDetailsDto> savedDtoList = this.otherBankDetailsService
                .getOtherBankDetails(dto.getBankCode(), dto.getBankName());

        for (final OtherBankDetailsDto savedDto : savedDtoList) {
            if (dto.getId() != savedDto.getId()) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }


    /**
     * Sets the other bank details service.
     *
     * @param otherBankDetailsService the new other bank details service
     */
    public void setOtherBankDetailsService(final OtherBankDetailsService otherBankDetailsService) {
        this.otherBankDetailsService = otherBankDetailsService;
    }


}
