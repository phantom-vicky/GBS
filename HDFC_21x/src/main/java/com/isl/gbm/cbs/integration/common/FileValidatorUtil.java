/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The Class FileValidatorUtil.
 */
public final class FileValidatorUtil {

    /**
     * The Constant INVALID_FILE_MESSAGE.
     */
    private static final String INVALID_FILE_MESSAGE = "Please upload a valid excel file";

    /**
     * The Constant INVALID_FILE_KEY.
     */
    private static final String INVALID_FILE_KEY = "Invalid_File";

    /**
     * The Constant COMMA.
     */
    private static final String COMMA = ",";

    /**
     * Checks if the file is having valid header.
     *
     * @param file               the excel file
     * @param errorMessages      the error messages
     * @param expectedHeaders    the expected header string in comma separated values
     * @param totalColumnNumbers the total column numbers
     * @return true, if is valid header
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static boolean isValidHeader1(final MultipartFile file, final Map<String, String> errorMessages,
                                        final String expectedHeaders, final int totalColumnNumbers) throws IOException {
        final InputStream inputStream = file.getInputStream();
        final XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        try {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                final XSSFRow headerRow = sheet.getRow(0);
                if (headerRow.getPhysicalNumberOfCells() != totalColumnNumbers) {
                    errorMessages.put("message", INVALID_FILE_MESSAGE);
                    return Boolean.FALSE;
                }
                final Iterator<Cell> iterator = headerRow.cellIterator();
                final List<String> headerColumns = new ArrayList<>(totalColumnNumbers);
                while (iterator.hasNext()) {
                    headerColumns.add(iterator.next().getRichStringCellValue().toString().trim());
                }
                final String headerColumnString = headerColumns.stream().collect(Collectors.joining(COMMA));
                if (!StringUtils.equals(expectedHeaders, headerColumnString)) {
                    errorMessages.put("message", INVALID_FILE_MESSAGE);
                    return Boolean.FALSE;
                }
            }
        } finally {
            workbook.close();
        }

        return Boolean.TRUE;
    }

    public static boolean isValidHeader(final MultipartFile file,
                                        final Map<String, String> errorMessages,
                                        final String expectedHeaders,
                                        final int totalColumnNumbers) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) { // Auto-detects XLS or XLSX

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = workbook.getSheetAt(sheetIndex);
                Row headerRow = sheet.getRow(0);
                if (headerRow == null || headerRow.getPhysicalNumberOfCells() != totalColumnNumbers) {
                    errorMessages.put("message", INVALID_FILE_MESSAGE);
                    return false;
                }

                List<String> headerColumns = new ArrayList<>(totalColumnNumbers);
                for (Cell cell : headerRow) {
                    headerColumns.add(cell.getStringCellValue().trim());
                }

                String headerColumnString = String.join(COMMA, headerColumns);
                if (!StringUtils.equals(expectedHeaders, headerColumnString)) {
                    errorMessages.put("message", INVALID_FILE_MESSAGE);
                    return false;
                }
            }
        } catch (Exception e) {
            errorMessages.put("message", "Invalid file format. Only Excel files are allowed.");
            return false;
        }
        return true;
    }
    /**
     * Instantiates a new file validator util.
     */
    private FileValidatorUtil() {
        // private constructor
    }

}
