package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.StateMasterDTO;
import com.isl.gbm.cbs.integration.parser.model.StateExcelRow;
import com.isl.gbm.common.util.CommonConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StateParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateParser.class);

    /**
     * Handle parse error.
     *
     * @param ex
     *            the ex
     */
    private static void handleParseError(final ExcelParsingException ex) {
        LOGGER.error(ex.getLocalizedMessage(), ex);
    }

    /** The validator. */
    @Autowired
    private Validator validator;

    /**
     * Builds the result list.
     *
     * @param file
     *            the file
     * @param stateExcelRows
     *            the branch banks excel rows
     * @return the list
     * @throws DuplicateRecordException
     *             the duplicate record exception
     */
    private List<StateMasterDTO> buildResultList(final MultipartFile file,
                                                 final List<StateExcelRow> stateExcelRows) throws DuplicateRecordException {

        //final HashSet<StateExcelRow> rowSet = new HashSet<>(stateExcelRows.size());
        final List<StateMasterDTO> dtoList = new ArrayList<>(stateExcelRows.size());
        for (final StateExcelRow row : stateExcelRows) {
//            if (BooleanUtils.isFalse(rowSet.add(row))) {
//                throw new DuplicateRecordException("Duplicate records found in the excel file");
//            }
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

    /**
     * Map rows to dto.
     *
     * @param banksExcelRows
     *            the banks excel rows
     * @param fileName
     *            the file name
     * @return the branch bank dto
     */
    private StateMasterDTO mapRowsToDto(final StateExcelRow banksExcelRows, final String fileName) {
        final StateMasterDTO stateMasterDTO = new StateMasterDTO();
        stateMasterDTO.setState(banksExcelRows.getState());
        stateMasterDTO.setCity(banksExcelRows.getCity());
        stateMasterDTO.setPinCode(banksExcelRows.getPinCode());
        stateMasterDTO.setPostOfficeName(banksExcelRows.getPostOfficeName());
        return stateMasterDTO;
    }

    /**
     * Parses the file.
     *
     * @param file
     *            the file
     * @return the list
     * @throws DuplicateRecordException
     *             the duplicate record exception
     */
    public List<StateMasterDTO> parseFile(final MultipartFile file) throws DuplicateRecordException {
        final SheetParser sheetParser = new SheetParser();
        final List<StateExcelRow> stateExcelRows = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream(); XSSFWorkbook workbook = new XSSFWorkbook(inputStream);) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                final List<StateExcelRow> entityList = sheetParser.createEntity(sheet, StateExcelRow.class,
                        StateParser::handleParseError);
                if (CollectionUtils.isNotEmpty(entityList)) {
                    stateExcelRows.addAll(entityList);
                }
            }
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }

        return this.buildResultList(file, stateExcelRows);
    }
    private boolean validateHeaders(Sheet sheet, String[] expectedHeaders) {
        Row headerRow = sheet.getRow(0); // Assuming the first row contains headers
        if (headerRow == null) {
            return false; // No header row found
        }
        for (int i = 0; i < expectedHeaders.length; i++) {
            Cell cell = headerRow.getCell(i);
            String headerValue = cell != null ? cell.getStringCellValue().trim() : "";
            if (!headerValue.equalsIgnoreCase(expectedHeaders[i])) {
                return false; // Header does not match
            }
        }
        return true; // All headers are valid
    }
    public List<StateMasterDTO> parseFile1(final MultipartFile file) throws DuplicateRecordException {
        final List<StateMasterDTO> stateMasterRows = new ArrayList<>();
        String[] expectedHeaders = {"STATE", "CITY", "PIN_CODE", "POST_OFFICE_NAME"};

        try (InputStream inputStream = file.getInputStream();Workbook workbook = WorkbookFactory.create(inputStream)) {
            /*Workbook workbook;

            if (file.getOriginalFilename().toLowerCase().endsWith(".xls")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().toLowerCase().endsWith(".xlsx")) {
                workbook = new HSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("File must be either .xls or .xlsx format");
            }
*/
            try {
                for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                    Sheet sheet = workbook.getSheetAt(sheetIndex);

                    if (!validateHeaders(sheet, expectedHeaders)) {
                        LOGGER.error("Header validation failed for sheet: {}", sheet.getSheetName());
                        throw new RuntimeException(CommonConstants.HEADER_VALIDATION_FAILURE);
                    }

                    for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                        Row row = sheet.getRow(rowIndex);
                        if (row == null) continue;

                        StateExcelRow excelRow = new StateExcelRow();
                        StateMasterDTO dto = new StateMasterDTO();

                        excelRow.setState(getStringCellValue(row.getCell(0)));
                        excelRow.setCity(getStringCellValue(row.getCell(1)));
                        excelRow.setPinCode(getStringCellValue(row.getCell(2)));
                        excelRow.setPostOfficeName(getStringCellValue(row.getCell(3)));

                        if (isRowEmpty(excelRow)) continue;

                        dto.setState(excelRow.getState());
                        dto.setCity(excelRow.getCity());
                        dto.setPinCode(excelRow.getPinCode());
                        dto.setPostOfficeName(excelRow.getPostOfficeName());

                        stateMasterRows.add(dto);
                    }
                }
            } finally {
                workbook.close();
            }
        } catch (final IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            return Collections.emptyList();
        }

        return stateMasterRows;
    }
    /**
     * Sets the validator.
     *
     * @param validator
     *            the validator to set
     */
    public void setValidator(final Validator validator) {
        this.validator = validator;
    }
    private boolean isRowEmpty(StateExcelRow row) {
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
