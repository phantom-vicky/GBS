/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.parser.model.BranchIfscDetailsExcelRow;
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
 * The Class BranchIfscDetailsFileParser.
 */
@Component
public class BranchIfscDetailsFileParser {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BranchIfscDetailsFileParser.class);

    /**
     * The Constant TRUE_STRING.
     */
    private static final String TRUE_STRING = "Y";

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
     * Builds the branch ifsc details dto.
     *
     * @param row      the row
     * @param fileName the file name
     * @return the branch ifsc details dto
     */
    private BranchIfscDetailsDto buildBranchIfscDetailsDto(final BranchIfscDetailsExcelRow row, final String fileName) {
        final BranchIfscDetailsDto dto = new BranchIfscDetailsDto();
        dto.setAdditionalText1(row.getAdditionalText1());
        dto.setAdditionalText2(row.getAdditionalText2());
        dto.setAdditionalText3(row.getAdditionalText3());
        dto.setBankCode(row.getBankCode());
        dto.setBankIdentifier(row.getBankIdentifier());
        dto.setBranchCode(row.getBranchCode());
        dto.setDeleted(StringUtils.equalsIgnoreCase(TRUE_STRING, row.getDeleteFlag()) ? Boolean.TRUE : Boolean.FALSE);
        dto.setPaysysId(row.getPaysysId());
        dto.setFileName(fileName);
        dto.setCreatedDate(new Date());
        dto.setVerified(Boolean.FALSE);
        return dto;
    }

    /**
     * Builds the branch ifsc details list.
     *
     * @param file      the file
     * @param excelRows the excel rows
     * @return the list
     * @throws DuplicateRecordException the duplicate record exception
     * @throws InvalidDataException     the invalid data exception
     */
    private List<BranchIfscDetailsDto> buildBranchIfscDetailsList(final MultipartFile file,
                                                                  final List<BranchIfscDetailsExcelRow> excelRows) throws DuplicateRecordException, InvalidDataException {
        final HashSet<BranchIfscDetailsExcelRow> rowSet = new HashSet<>(excelRows.size());
        final List<BranchIfscDetailsDto> dtoList = new ArrayList<>(excelRows.size());
        for (final BranchIfscDetailsExcelRow row : excelRows) {
            if (BooleanUtils.isFalse(rowSet.add(row))) {
                throw new DuplicateRecordException("Duplicate records found in the excel file");
            }
            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);

            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
            }

            dtoList.add(this.buildBranchIfscDetailsDto(row, file.getOriginalFilename()));
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
    public List<BranchIfscDetailsDto> parse(final MultipartFile file)
            throws DuplicateRecordException, InvalidDataException, IOException {
        final List<BranchIfscDetailsExcelRow> excelRows = this.readFile(file);
        return this.buildBranchIfscDetailsList(file, excelRows);
    }

    /**
     * Read file.
     *
     * @param file the file
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private List<BranchIfscDetailsExcelRow> readFile(final MultipartFile file) throws IOException {
        final SheetParser sheetParser = new SheetParser();
        final List<BranchIfscDetailsExcelRow> excelRows = new ArrayList<>(1000);
        final InputStream inputStream = file.getInputStream();
        final HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            final List<BranchIfscDetailsExcelRow> entityList = sheetParser.createEntity(sheet,
                    BranchIfscDetailsExcelRow.class, BranchIfscDetailsFileParser::handleParseError);
            if (CollectionUtils.isNotEmpty(entityList)) {
                excelRows.addAll(entityList);
            }
        }
        workbook.close();
        return excelRows;
    }


}
