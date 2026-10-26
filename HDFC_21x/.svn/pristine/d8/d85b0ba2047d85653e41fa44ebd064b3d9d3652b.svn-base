/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.parser.model.OtherBankDetailsExcelRows;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
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

@Component
public class OtherBankDetailsParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(OtherBankDetailsParser.class);

    private static void handleParseError(final ExcelParsingException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
    }

    @Autowired
    private Validator validator;

    private List<OtherBankDetailsDto> buildResultList(final MultipartFile file,
                                                      final List<OtherBankDetailsExcelRows> otherBankDetailsExcelRows) throws DuplicateRecordException {

        final HashSet<OtherBankDetailsExcelRows> rowSet = new HashSet<>(otherBankDetailsExcelRows.size());
        final List<OtherBankDetailsDto> dtoList = new ArrayList<>(otherBankDetailsExcelRows.size());
        for (final OtherBankDetailsExcelRows row : otherBankDetailsExcelRows) {
            if (BooleanUtils.isFalse(rowSet.add(row))) {
                throw new DuplicateRecordException("Duplicate records found in the excel file");
            }

            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);

            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
            }
            dtoList.add(this.mapRowsToDto(row, file.getOriginalFilename()));
        }
        return dtoList;

    }

    private OtherBankDetailsDto mapRowsToDto(final OtherBankDetailsExcelRows otherBankDetailsExcelRows,
                                             final String fileName) {
        final OtherBankDetailsDto otherBankDetailsDto = new OtherBankDetailsDto();

        otherBankDetailsDto.setBankCode(otherBankDetailsExcelRows.getBankCode());
        otherBankDetailsDto.setBankName(otherBankDetailsExcelRows.getBankName());
        otherBankDetailsDto.setBankShortName(otherBankDetailsExcelRows.getBankShortName());
        otherBankDetailsDto.setAddOne(otherBankDetailsExcelRows.getAddOne());
        otherBankDetailsDto.setAddTwo(otherBankDetailsExcelRows.getAddTwo());
        otherBankDetailsDto.setCity(otherBankDetailsExcelRows.getCity());
        otherBankDetailsDto.setPinCode(otherBankDetailsExcelRows.getPinCode());
        otherBankDetailsDto.setCreatedDate(new Date());
        otherBankDetailsDto.setVerified(Boolean.FALSE);
        otherBankDetailsDto.setFileName(fileName);

        return otherBankDetailsDto;
    }

    public List<OtherBankDetailsDto> parseFile(final MultipartFile file) throws DuplicateRecordException {
        final SheetParser sheetParser = new SheetParser();
        final List<OtherBankDetailsExcelRows> excelRows = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream(); HSSFWorkbook workbook = new HSSFWorkbook(inputStream);) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                final List<OtherBankDetailsExcelRows> entityList = sheetParser.createEntity(sheet,
                        OtherBankDetailsExcelRows.class, OtherBankDetailsParser::handleParseError);
                if (CollectionUtils.isNotEmpty(entityList)) {
                    excelRows.addAll(entityList);
                }
            }
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }

        return this.buildResultList(file, excelRows);
    }
    public List<OtherBankDetailsDto> parseFile1(final MultipartFile file) throws DuplicateRecordException {
        final List<OtherBankDetailsExcelRows> excelRows = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream(); HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);

                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // assuming first row is header
                    final Row row = sheet.getRow(rowIndex);
                    if (row == null) continue;

                    OtherBankDetailsExcelRows excelRow = new OtherBankDetailsExcelRows();

                    // Map cells to your POJO fields (update indices based on actual Excel structure)
                    excelRow.setBankCode(getStringCellValue(row.getCell(0)));
                    excelRow.setBankName(getStringCellValue(row.getCell(1)));
                    excelRow.setBankShortName(getStringCellValue(row.getCell(2)));
                    excelRow.setAddOne(getStringCellValue(row.getCell(3)));
                    excelRow.setAddTwo(getStringCellValue(row.getCell(4)));
                    excelRow.setCity(getStringCellValue(row.getCell(5)));
                    excelRow.setPinCode(getStringCellValue(row.getCell(6)));

                    if (!isRowEmpty(excelRow)) {
                        excelRows.add(excelRow);
                    }
                }
            }
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }

        return this.buildResultList(file, excelRows);
    }
    private boolean isRowEmpty(OtherBankDetailsExcelRows row) {
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
    public void setValidator(final Validator validator) {
        this.validator = validator;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING); // Safe conversion
        return cell.getStringCellValue().trim();
    }
}
