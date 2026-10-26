/*
 *
 */
package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.parser.model.TransactionCodesExcelRow;
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class TransactionCodesParser.
 */
@Component
public class TransactionCodesParser {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionCodesParser.class);

    /**
     * Handle parse error.
     *
     * @param ex the ex
     */
    private static void handleParseError(final ExcelParsingException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
    }

    @Autowired
    private Validator validator;

    /**
     * Save transaction codes dto.
     *
     * @param row              the row
     * @param originalFilename the original filename
     * @return the transaction codes dto
     */
    private TransactionCodesDto buildTransactionCodesDto(final TransactionCodesExcelRow row,
                                                         final String originalFilename) {
        final TransactionCodesDto dto = new TransactionCodesDto();
        dto.setFileName(originalFilename);
        dto.setClgTranCode(row.getClgTranCode());
        dto.setClgTranCodeDesc(row.getClgTranCodeDesc());
        dto.setInstrumentType(row.getInstrumentType());
        dto.setSchemeCode(row.getSchemeCode());
        dto.setAddText1(row.getAddText1());
        dto.setAddText2(row.getAddText2());
        dto.setAddText3(row.getAddText3());
        dto.setCreatedDate(new Date());

        return dto;
    }

    /**
     * Save transaction list.
     *
     * @param file      the file
     * @param excelRows the excel rows
     * @return the list
     * @throws DuplicateRecordException the duplicate record exception
     * @throws InvalidDataException     the invalid data exception
     */
    private List<TransactionCodesDto> buildTransactionList(final MultipartFile file,
                                                           final List<TransactionCodesExcelRow> excelRows) throws DuplicateRecordException, InvalidDataException {
        final HashSet<TransactionCodesExcelRow> rowSet = new HashSet<>(excelRows.size());
        final List<TransactionCodesDto> dtoList = new ArrayList<>(excelRows.size());
        for (final TransactionCodesExcelRow row : excelRows) {

            if (BooleanUtils.isFalse(rowSet.add(row))) {
                throw new DuplicateRecordException("Duplicate records found in the excel file");
            }
            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);

            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
            }
            dtoList.add(this.buildTransactionCodesDto(row, file.getOriginalFilename()));
        }
        return dtoList;
    }

    /**
     * Read file.
     *
     * @param file the file
     * @return the list
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public List<TransactionCodesDto> parse(final MultipartFile file)
            throws DuplicateRecordException, InvalidDataException, IOException {
        final List<TransactionCodesExcelRow> excelRows = this.readFile(file);
        return this.buildTransactionList(file, excelRows);
    }

    private List<TransactionCodesExcelRow> readFile(final MultipartFile file) throws IOException {
        final SheetParser sheetParser = new SheetParser();
        final List<TransactionCodesExcelRow> excelRows = new ArrayList<>(1000);
        final InputStream inputStream = file.getInputStream();
        final HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            final List<TransactionCodesExcelRow> entityList = sheetParser.createEntity(sheet,
                    TransactionCodesExcelRow.class, TransactionCodesParser::handleParseError);
            if (CollectionUtils.isNotEmpty(entityList)) {
                excelRows.addAll(entityList);
            }
        }
        workbook.close();
        return excelRows;
    }

    /**
     * Parses the.
     *
     * @param validator the new validator
     * @return the list
     * @throws DuplicateRecordException the duplicate record exception
     * @throws InvalidDataException     the invalid data exception
     */

    public void setValidator(final Validator validator) {
        this.validator = validator;
    }

}
