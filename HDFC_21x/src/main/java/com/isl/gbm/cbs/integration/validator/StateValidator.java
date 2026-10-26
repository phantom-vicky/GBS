package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.common.util.FileValidationUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@Component
public class StateValidator {

    private static final String HEADERS = "STATE,CITY,PIN_CODE,POST_OFFICE_NAME";


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


        //return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 21);
        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 4);
    }

    public Boolean isValidRecords(final List<StateMasterDTO> stateMasterDTOS,
                                  final Map<String, String> errorMessages) {
        if (CollectionUtils.isEmpty(stateMasterDTOS)) {
            errorMessages.put("Invalid_File", "File is empty");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
