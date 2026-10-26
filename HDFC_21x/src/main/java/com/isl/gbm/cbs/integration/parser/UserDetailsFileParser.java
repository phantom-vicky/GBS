/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.parser.model.UserWorkClassExcelRow;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.javafunk.excelparser.SheetParser;
import org.javafunk.excelparser.exception.ExcelParsingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class UserDetailsFileParser.
 */
@Component
public class UserDetailsFileParser {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsFileParser.class);

    /**
     * The Constant TRUE_STRING.
     */
    private static final String TRUE_STRING = "Y";

    /**
     * Handle parse error.
     *
     * @param exception the excel parsing exception
     */
    private static void handleParseError(final ExcelParsingException exception) {
        LOGGER.error(exception.getLocalizedMessage(), exception);
    }

    /**
     * The validator.
     */
    @Autowired
    private Validator validator;

    /**
     * Builds the user details dto.
     *
     * @param row      the row
     * @param fileName the file name
     * @return the gbm user details dto
     */
    private GbmUserDetailsDto buildUserDetailsDto(final UserWorkClassExcelRow row, final String fileName)
            throws InvalidDataException {

        if (StringUtils.isBlank(row.getUserId())) {
            throw new InvalidDataException("User Id cannot be blank");
        }

        final GbmUserDetailsDto dto = new GbmUserDetailsDto();
        dto.setUserId(row.getUserId());
        dto.setDeleted(StringUtils.equalsIgnoreCase(TRUE_STRING, row.getDeleteFlag()) ? Boolean.TRUE : Boolean.FALSE);
        dto.setUserWorkClass(row.getUserWorkClass());
        dto.setSolId(row.getSolId());
        dto.setUserSolTenor(StringUtils.isNotBlank(row.getUserSolTenor()) ? row.getUserSolTenor().charAt(0) : null);
        dto.setUserMaxInactiveTime(row.getUserMaxInactiveTime());
        dto.setUserAccountExpiryDate(row.getUserAccountExpiryDate());
        dto.setUserPasswordExpiryDate(row.getUserPasswordExpiryDate());
        dto.setUserApplicationName(row.getUserApplicationName());
        dto.setNewUserFlag(
                StringUtils.equalsIgnoreCase(TRUE_STRING, row.getNewUserFlag()) ? Boolean.TRUE : Boolean.FALSE);
        dto.setDefaultTermId(row.getDefaultTermId());
        dto.setVirtualFlag(
                StringUtils.equalsIgnoreCase(TRUE_STRING, row.getVirtualFlag()) ? Boolean.TRUE : Boolean.FALSE);
        dto.setAdditionalText1(row.getAdditionalText1());
        dto.setAdditionalText2(row.getAdditionalText2());
        dto.setAdditionalText3(row.getAdditionalText3());
        dto.setFileName(fileName);
        dto.setCreatedDate(new Date());
        dto.setVerified(Boolean.FALSE);
        return dto;
    }

    /**
     * Builds the user details list.
     *
     * @param file      the file
     * @param excelRows the excel rows
     * @return the list
     * @throws DuplicateRecordException the duplicate record exception
     * @throws InvalidDataException     the invalid data exception
     */
    private List<GbmUserDetailsDto> buildUserDetailsList(final MultipartFile file,
                                                         final List<UserWorkClassExcelRow> excelRows) throws DuplicateRecordException, InvalidDataException {
        final HashSet<UserWorkClassExcelRow> rowSet = new HashSet<>(excelRows.size());
        final List<GbmUserDetailsDto> dtoList = new ArrayList<>(excelRows.size());
        for (final UserWorkClassExcelRow row : excelRows) {
            if (BooleanUtils.isFalse(rowSet.add(row))) {
                throw new DuplicateRecordException("Duplicate records found in the excel file");
            }

            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);

            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
            }

            dtoList.add(this.buildUserDetailsDto(row, file.getOriginalFilename()));
        }
        return dtoList;
    }

    /**
     * Parses the.
     *
     * @param file the file
     * @return the list
     * @throws DuplicateRecordException the duplicate record exception
     * @throws InvalidDataException     the invalid data exception
     * @throws IOException              Signals that an I/O exception has occurred.
     */
    public List<GbmUserDetailsDto> parse(final MultipartFile file)
            throws DuplicateRecordException, InvalidDataException, IOException {
        final List<UserWorkClassExcelRow> excelRows = this.readFile(file);
        return this.buildUserDetailsList(file, excelRows);
    }

    /**
     * Read file.
     *
     * @param file the file
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private List<UserWorkClassExcelRow> readFile(final MultipartFile file) throws IOException {
        final SheetParser sheetParser = new SheetParser();
        final List<UserWorkClassExcelRow> excelRows = new ArrayList<>(1000);
        final InputStream inputStream = file.getInputStream();
        final HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            final List<UserWorkClassExcelRow> entityList = sheetParser.createEntity(sheet, UserWorkClassExcelRow.class,
                    UserDetailsFileParser::handleParseError);
            if (CollectionUtils.isNotEmpty(entityList)) {
                excelRows.addAll(entityList);
            }
        }
        workbook.close();
        return excelRows;
    }

    /**
     * Sets the validator.
     *
     * @param validator the new validator
     */
    public void setValidator(final Validator validator) {
        this.validator = validator;
    }

}
