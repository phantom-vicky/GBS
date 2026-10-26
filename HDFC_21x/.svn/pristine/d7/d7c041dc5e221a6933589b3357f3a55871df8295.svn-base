/**
 *
 */
package com.isl.gbm.cbs.integration.parser;

import com.isl.gbm.cbs.integration.common.CalendarTypes;
import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.parser.model.CalendarEntriesExcelRow;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Class CalendarEntriesParser.
 *
 * @author harishbabu.t
 */
@Component
public class CalendarEntriesParser {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(CalendarEntriesParser.class);

    /** The Constant TRUE_STRING. */
    private static final String TRUE_STRING = "Y";

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
     * Builds the calendar details dto.
     *
     * @param row
     *            the row
     * @param originalFilename
     *            the original filename
     * @return the calendar entries dto
     */
    private CalendarEntriesDto buildCalendarDetailsDto(final CalendarEntriesExcelRow row,
                                                       final String originalFilename){
        final CalendarEntriesDto calendarEntriesDto = new CalendarEntriesDto();
        long monthYearLength=0;
        long validDayCount = 0;
        long validRemarksCount = 0;
        if (row != null) {
            if (row.getMonthYear() != null && row.getMonthYear().length() == 6) {
                int month = Integer.parseInt(row.getMonthYear().substring(0, 2));
                int year = Integer.parseInt(row.getMonthYear().substring(2));
                monthYearLength = YearMonth.of(year, month).lengthOfMonth(); // days in month
            }
            validDayCount = row.getIsHoliday() != null && !row.getIsHoliday().trim().isEmpty()
                    ? row.getIsHoliday().trim().chars().filter(ch -> ch == 'N' || ch == 'Y').count()
                    : 0;
            validRemarksCount = row.getHolidayRemarks() != null && !row.getHolidayRemarks().trim().isEmpty()
                    ? row.getHolidayRemarks().trim().chars().filter(ch -> ch == '#').count()
                    : 0;
        }
        StringBuilder holiBuilder = new StringBuilder();
        StringBuilder remarksBuilder = new StringBuilder();
        if (monthYearLength == validDayCount && validDayCount == validRemarksCount) {
            if (row.getIsHoliday() != null && row.getHolidayRemarks() != null) {
                String[] remarkArray = row.getHolidayRemarks().split("#", -1);  // Keep empty values
                int length = Math.min(row.getIsHoliday().length(), remarkArray.length);
                for (int i = 0; i < length; i++) {
                    char holChar = row.getIsHoliday().charAt(i);
                    String remark = remarkArray[i];
                    if (holChar == 'Y' && (remark == null || remark.trim().isEmpty())) {
                        throw new IllegalArgumentException("Holiday at day " + (i + 1) + " is marked 'Y' but remark is missing.");
                    }
                    holiBuilder.append(Character.toUpperCase(holChar) == 'N' ? " " : "Y");
                    if (holChar == 'N' && (remark == null || remark.trim().isEmpty())) {
                        remarksBuilder.append("#");
                    } else {
                        remarksBuilder.append(remark);
                        if (i < length - 1) {
                            remarksBuilder.append("#");
                        }
                    }
                }
            }
        calendarEntriesDto.setHolidayRemarks(remarksBuilder.toString());
        calendarEntriesDto.setCalendarCode(row.getCalendarCode());
        String calendarType = row.getCalendarType();
        if (!"Branch".equalsIgnoreCase(calendarType) && !"DC".equalsIgnoreCase(calendarType)) {
            throw new IllegalArgumentException("Invalid Calendar Type: " + calendarType + ". Only 'Branch' or 'DC' are allowed.");
        }else {
            calendarEntriesDto.setCalendarType(calendarType);
        }
        calendarEntriesDto.setCreatedDate(new Date());
        calendarEntriesDto.setFileName(originalFilename);
        calendarEntriesDto.setHolidayString(holiBuilder.toString());
        calendarEntriesDto.setMonthYear(row.getMonthYear());
        char statusChar = row.getStatus().charAt(0);
        if (statusChar != '0' && statusChar != '1') {
            throw new IllegalArgumentException("Invalid status value: " + statusChar + ". Only '0' or '1' are allowed.");
        }else{
        calendarEntriesDto.setStatus(statusChar);
        }
        calendarEntriesDto.setVerifiedFlag(Boolean.FALSE);
        calendarEntriesDto.setDeleteFlag(StringUtils.equalsIgnoreCase(TRUE_STRING, row.getIsDeleted()) ? Boolean.TRUE : Boolean.FALSE);
        } else {
            throw new IllegalArgumentException("Validation failed: " + "Month has " + monthYearLength + " days, " + "holiday has " + validDayCount + " N/Y characters, " + "remarks has " + validRemarksCount + " '#' characters.");
        }
        return calendarEntriesDto;
    }


    /**
     * Builds the calendar entries list.
     *
     * @param file
     *            the file
     * @param excelRows
     *            the excel rows
     * @return the list
     * @throws DuplicateRecordException
     *             the duplicate record exception
     * @throws InvalidDataException
     *             the invalid data exception
     */
    private List<CalendarEntriesDto> buildCalendarEntriesList(final MultipartFile file,
                                                              final List<CalendarEntriesExcelRow> excelRows) throws DuplicateRecordException, InvalidDataException {
        final HashSet<CalendarEntriesExcelRow> rowset = new HashSet<>(excelRows.size());
        final List<CalendarEntriesDto> dtoList = new ArrayList<>(excelRows.size());
        for (final CalendarEntriesExcelRow row : excelRows) {
            if (null == CalendarTypes.findByType(row.getCalendarType())) {
                throw new InvalidDataException("Invalid Calendar type found in the excel file");
            }
            if (BooleanUtils.isFalse(rowset.add(row))) {
                throw new DuplicateRecordException("Duplicate records found in the excel file");
            }
            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);

            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList()).toString());
            }
            dtoList.add(this.buildCalendarDetailsDto(row, file.getOriginalFilename()));
        }
        return dtoList;
    }

    /**
     * Parses the.
     *
     * @param file
     *            the file
     * @return the list
     * @throws DuplicateRecordException
     *             the duplicate record exception
     * @throws InvalidDataException
     *             the invalid data exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public List<CalendarEntriesDto> parse(final MultipartFile file)
            throws DuplicateRecordException, InvalidDataException, IOException {
        final List<CalendarEntriesExcelRow> excelRows = this.readFile1(file);
        return this.buildCalendarEntriesList(file, excelRows);
    }

    /**
     * Read file.
     *
     * @param file
     *            the file
     * @return the list
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private List<CalendarEntriesExcelRow> readFile(final MultipartFile file) throws IOException {
        final SheetParser sheetParser = new SheetParser();
        final List<CalendarEntriesExcelRow> excelRows = new ArrayList<>(1000);
        final InputStream inputStream = file.getInputStream();
        final HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            final List<CalendarEntriesExcelRow> entityList = sheetParser.createEntity(sheet,
                    CalendarEntriesExcelRow.class, CalendarEntriesParser::handleParseError);
            if (CollectionUtils.isNotEmpty(entityList)) {
                excelRows.addAll(entityList);
            }
        }
        workbook.close();
        return excelRows;
    }

    private List<CalendarEntriesExcelRow> readFile1(final MultipartFile file) throws IOException {
        final List<CalendarEntriesExcelRow> excelRows = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream(); HSSFWorkbook workbook = new HSSFWorkbook(inputStream)) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // assuming first row is header
                    final HSSFRow row = sheet.getRow(rowIndex);
                    if (row == null) continue;

                    CalendarEntriesExcelRow excelRow = new CalendarEntriesExcelRow();
                    excelRow.setCalendarType(getStringCellValue(row.getCell(0)));
                    excelRow.setCalendarCode(getStringCellValue(row.getCell(1)));
                    excelRow.setMonthYear(getMonthYearFromCell(getStringCellValue(row.getCell(2))));
                    excelRow.setIsDeleted(getStringCellValue(row.getCell(3)));
                    excelRow.setIsHoliday(getStringCellValue(row.getCell(4)));
                    excelRow.setStatus(getStringCellValue(row.getCell(5)));
                    excelRow.setHolidayRemarks(getStringCellValue(row.getCell(6)));

                    excelRows.add(excelRow);
                }
            }
        }
        return excelRows;
    }
    private String getMonthYearFromCell(String cellValue) {
        if (cellValue == null || cellValue.isEmpty()) {
            return null;
        }
        // If it's 5 digits (e.g., "52025"), pad with a leading zero
        if (cellValue.matches("^\\d{5}$")) {
            cellValue = "0" + cellValue;
        }
        // Validate that the final result is 6 digits (MMYYYY)
        if (!cellValue.matches("^\\d{6}$")) {
            throw new IllegalArgumentException("Invalid month-year format: " + cellValue);
        }
        // Optional: Validate that MM is between 01 and 12
        int month = Integer.parseInt(cellValue.substring(0, 2));
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month in MMYYYY: " + cellValue);
        }
        return cellValue;
    }

    private String getStringCellValue(Cell cell) {
        if (cell == null) return null;
        cell.setCellType(CellType.STRING); // converts numeric/date/etc. to string
        return cell.getStringCellValue().trim();
    }


}
