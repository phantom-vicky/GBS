/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.parser.model.OtherBankBranchesExcelRows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
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
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class OtherBankBranchesFileParser.
 */
@Component
public class OtherBankBranchesFileParser {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OtherBankBranchesFileParser.class);

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
    private List<OtherBankBranchesDto> buildDtoList(final MultipartFile file,
                                                    final List<OtherBankBranchesExcelRows> excelRows) {
        final HashSet<OtherBankBranchesExcelRows> rowSet = new HashSet<>(excelRows.size());
        final List<OtherBankBranchesDto> dtoList = new ArrayList<>(excelRows.size());
        for (final OtherBankBranchesExcelRows row : excelRows) {
           /* if (row.getBankCode() == null) {
                throw new InvalidDataException("Empty cell found in the excel file");
            }*/
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
     * @return the other bank branches dto
     */
    private OtherBankBranchesDto dtoList(final OtherBankBranchesExcelRows row, final String fileName) {
        final OtherBankBranchesDto dto = new OtherBankBranchesDto();

        dto.setAdditionalText1(row.getAddtionalText1());
        dto.setAdditionalText2(row.getAddtionalText2());
        dto.setAdditionalText3(row.getAddtionalText3());
        dto.setCreatedDate(new Date());
        dto.setFilename(fileName);
        dto.setBranchCode(row.getBranchCode());
        dto.setBankCode(row.getBankCode());
        dto.setMicrBankCode(row.getMicrBankCode());
        dto.setMicrBranchCode(row.getMicrBranchCode());
        dto.setMicrCentreCode(row.getMicrCentreCode());
        dto.setBranchAddr1(row.getBranchAddr1());
        dto.setBranchAddr2(row.getBranchAddr2());
        dto.setBranchAddr3(row.getBranchAddr3());
        dto.setBranchName(row.getBranchName());
        dto.setBranchCityCode(row.getBranchCityCode());
        dto.setBranchPinCode(row.getBranchPinCode());
        dto.setBranchStateCode(row.getBranchStateCode());
        dto.setVerified(Boolean.FALSE);
        dto.setBankIfscCode(row.getBankIfscCode());

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
    public List<OtherBankBranchesDto> parse(final MultipartFile file) throws DuplicateRecordException {
        final SheetParser sheetParser = new SheetParser();
        final List<OtherBankBranchesExcelRows> excelRows = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream(); HSSFWorkbook workbook = new HSSFWorkbook(inputStream);) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                final List<OtherBankBranchesExcelRows> entityList = sheetParser.createEntity(sheet,
                        OtherBankBranchesExcelRows.class, OtherBankBranchesFileParser::handleParseError);
                if (CollectionUtils.isNotEmpty(entityList)) {
                    excelRows.addAll(entityList);
                }
            }
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }

        return this.buildDtoList(file, excelRows);
    }

    public List<OtherBankBranchesDto> parse1(final MultipartFile file) throws DuplicateRecordException {
        final List<OtherBankBranchesExcelRows> excelRows = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream(); HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // assuming first row is header
                    final Row row = sheet.getRow(rowIndex);
                    if (row == null) continue;

                    OtherBankBranchesExcelRows excelRow = new OtherBankBranchesExcelRows();

                    // 👉 Map Excel columns to fields (adjust indices and names based on your Excel structure)
                    excelRow.setBranchCode(getStringCellValue(row.getCell(0)));
                    excelRow.setBankCode(getStringCellValue(row.getCell(1)));
                    excelRow.setMicrCentreCode(getStringCellValue(row.getCell(2)));
                    excelRow.setMicrBankCode(getStringCellValue(row.getCell(3)));
                    excelRow.setMicrBranchCode(getStringCellValue(row.getCell(4)));
                    excelRow.setBranchName(getStringCellValue(row.getCell(5)));
                    excelRow.setBranchAddr1(getStringCellValue(row.getCell(6)));
                    excelRow.setBranchAddr2(getStringCellValue(row.getCell(7)));
                    excelRow.setBranchAddr3(getStringCellValue(row.getCell(8)));
                    excelRow.setBranchCityCode(getStringCellValue(row.getCell(9)));
                    excelRow.setBranchStateCode(getStringCellValue(row.getCell(10)));
                    excelRow.setBranchPinCode(getStringCellValue(row.getCell(11)));
                    excelRow.setAddtionalText1(getStringCellValue(row.getCell(12)));
                    excelRow.setAddtionalText2(getStringCellValue(row.getCell(13)));
                    excelRow.setAddtionalText3(getStringCellValue(row.getCell(14)));
                    excelRow.setBankIfscCode(getStringCellValue(row.getCell(15)));

                    // Only add non-empty rows (optional)
                    if (!isRowEmpty(excelRow)) {
                        excelRows.add(excelRow);
                    }
                }
            }
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }

        return this.buildDtoList(file, excelRows);
    }

    /**
     * Sets the validator.
     *
     * @param validator the new validator
     */
    public void setValidator(final Validator validator) {
        this.validator = validator;
    }

    private boolean isRowEmpty(OtherBankBranchesExcelRows row) {
        if (row == null) {
            return true;
        }

        for (Field field : row.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(row);
                if (value != null && !value.toString().trim().isEmpty()) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING); // Safe conversion
        return cell.getStringCellValue().trim();
    }

}
