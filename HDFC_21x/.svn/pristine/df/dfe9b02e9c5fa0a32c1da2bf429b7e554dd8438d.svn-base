/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.parser.model.OfficeAccountExcelRows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class OfficeAccountParser.
 */
@Component
public class OfficeAccountParser {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OfficeAccountParser.class);

    /**
     * Handle parse error.
     *
     * @param ex the ex
     */
    private static void handleParseError(final ExcelParsingException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
    }

    /**
     * The validator.
     */
    @Autowired
    private Validator validator;

    /**
     * Dto list.
     *
     * @param file      the file
     * @param excelRows the excel rows
     * @return the list
     * @throws DuplicateRecordException the duplicate record exception
     * @throws InvalidDataException     the invalid data exception
     */
    private List<OfficeAccountPlaceHolderDto> dtoList(final MultipartFile file,
                                                      final List<OfficeAccountExcelRows> excelRows) {
        final HashSet<OfficeAccountExcelRows> rowSet = new HashSet<>(excelRows.size());
        final List<OfficeAccountPlaceHolderDto> dtoList = new ArrayList<>(excelRows.size());
        for (final OfficeAccountExcelRows row : excelRows) {
            if ((null == row.getBacid()) || (null == row.getBacidDesc()) || (null == row.getSchmType())) {
                throw new InvalidDataException("Empty cell found in the excel file");
            }
            if (BooleanUtils.isFalse(rowSet.add(row))) {
                throw new DuplicateRecordException("Duplicate records found in the excel file");
            }
            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);

            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
            }
            dtoList.add(this.dtoList(row, file.getOriginalFilename()));
        }
        return dtoList;
    }

    /**
     * Dto list.
     *
     * @param row      the row
     * @param fileName the file name
     * @return the office account place holder dto
     */
    private OfficeAccountPlaceHolderDto dtoList(final OfficeAccountExcelRows row, final String fileName) {
        final OfficeAccountPlaceHolderDto dto = new OfficeAccountPlaceHolderDto();
        dto.setAdditionalText1(row.getAddtionalText1());
        dto.setAdditionalText2(row.getAddtionalText2());
        dto.setAdditionalText3(row.getAddtionalText3());
        dto.setCreatedDate(new Date());
        dto.setFilename(fileName);
        dto.setBacid(row.getBacid());
        dto.setBacidDesc(row.getBacidDesc());
        dto.setSchmType(row.getSchmType().trim());
        dto.setVerified(Boolean.FALSE);
        return dto;
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
    public List<OfficeAccountPlaceHolderDto> parseFile(final MultipartFile file) throws DuplicateRecordException {
        final SheetParser sheetParser = new SheetParser();
        final List<OfficeAccountExcelRows> excelRows = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream(); HSSFWorkbook workbook = new HSSFWorkbook(inputStream);) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                final List<OfficeAccountExcelRows> entityList = sheetParser.createEntity(sheet,
                        OfficeAccountExcelRows.class, OfficeAccountParser::handleParseError);
                if (CollectionUtils.isNotEmpty(entityList)) {
                    excelRows.addAll(entityList);
                }
            }
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }

        return this.dtoList(file, excelRows);
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
