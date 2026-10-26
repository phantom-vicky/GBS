/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.validator;

import com.isl.gbm.cbs.integration.common.FileValidatorUtil;
import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.service.BankBranchService;
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
 * The Class BankBranchFileValidator.
 *
 * @author harishbabu.t
 */
@Component
public class BankBranchFileValidator {

    /**
     * The Constant HEADERS.
     */
    //private static final String HEADERS = "SOL_ID,SOL_DESC,BANK_CODE,BR_CODE,MICR_CENTRE_CODE,MICR_BANK_CODE,MICR_BRANCH_CODE,ADDR_1,ADDR_2,CITY_CODE,STATE_CODE,PIN_CODE,PAN_GIR_NO,TAX_DEDN_DETAILS,TDS_CIRCLE_NAME,DIVISION_NAME,CIRCLE_OFFICE_NAME,UNIFORM_BR_CODE,Text 1,Text 2,Text 3";
	  private static final String HEADERS = "SOL_ID/BR_CODE,SOL_DESC,BANK_CODE,MICR_CENTRE_CODE,MICR_BANK_CODE,MICR_BRANCH_CODE,ADDR_1,ADDR_2,CITY_NAME,STATE_NAME,PIN_CODE,PAN_GIR_NO,TAX_DEDN_DETAILS,TDS_CIRCLE_NAME,DIVISION_NAME,CIRCLE_OFFICE_NAME,UNIFORM_BR_CODE,BRANCH_LOCATION,ZONE_CODE,ZONE_NAME,PDO_CODE,SHORT_NAME,BR_MAIL_ID,BR_STATUS,BRANCH_IFSC_CODE";

    /**
     * The branch bank service.
     */
    @Autowired
    private BankBranchService bankBranchService;

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

        assert fileName != null;
        if (
                !StringUtils.equals(extension, "xls") ||
                        !StringUtils.equals("XLS", FileValidationUtils.getFileTypeByUsingMagicNumbers(FileValidationUtils.convertBytesToHexaDecimal(file.getBytes()))) ||
                        !StringUtils.equals(contentType, "application/vnd.ms-excel") ||
                        FileValidationUtils.checkDoubleExtension(fileName)
        ) {
            errorMessages.put("message", "Please upload a valid Excel file (.xls)");
            return Boolean.FALSE;
        }

        if(this.bankBranchService.unVerifiedDetails()){
           errorMessages.put("message", "Unverified Record Exists");
           return Boolean.FALSE;
       }

        if (file.isEmpty() || this.bankBranchService.fileNameExists(fileName)) {
            errorMessages.put("message", "File Already Exists");
            return Boolean.FALSE;
        }
        return FileValidatorUtil.isValidHeader(file, errorMessages, HEADERS, 25);
    }

    /**
     * Checks if is valid record.
     *
     * @param bankBranchDto the bank branch dto
     * @param errorMessages the error messages
     * @return the boolean
     */
    public Boolean isValidRecord(final BankBranchDto bankBranchDto, final Map<String, String> errorMessages) {
        if (this.bankBranchService.isSolIdExists(bankBranchDto.getSolId())) {
            errorMessages.put("Duplicate_Data","SolId Already Exists");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * Checks if is valid records.
     *
     * @param branchBankDtoList the branch bank dto list
     * @param errorMessages     the error messages
     * @return the boolean
     */
    public Boolean isValidRecords(final List<BankBranchDto> branchBankDtoList,
                                  final Map<String, String> errorMessages) {
        if (CollectionUtils.isEmpty(branchBankDtoList)) {
            errorMessages.put("message", "File is empty");
            return Boolean.FALSE;
        }
        final List<BankBranchDto> branchBankListDto = this.bankBranchService.getBranchBankList();
        if (CollectionUtils.isEmpty(branchBankListDto)) {
            return Boolean.TRUE;
        }
        for (final BankBranchDto branchBankDto : branchBankDtoList) {
            for (final BankBranchDto existingdto : branchBankListDto) {
                if (StringUtils.equals(branchBankDto.getSolId(), existingdto.getSolId())) {
                    errorMessages.put("message", "Duplicate data available");
                    return Boolean.FALSE;
                }
            }
        }
        return Boolean.TRUE;
    }

    /**
     * Sets the bank branch service.
     *
     * @param bankBranchService the bankBranchService to set
     */
    public void setBankBranchService(final BankBranchService bankBranchService) {
        this.bankBranchService = bankBranchService;
    }

}
