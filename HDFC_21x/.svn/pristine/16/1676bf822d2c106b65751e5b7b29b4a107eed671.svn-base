package com.isl.gbm.rbibond.dto;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NsdlPanValidationBulkUploadGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(NsdlPanValidationBulkUploadGenerator.class);

    public static byte[] incomeTaxPanToExcelFile() throws IOException, InvalidFormatException {
        String headers = "PAN_NO,ITR_FLAG_STATUS,INCOME_TAX_DATE";
        return generateExcelSheet(headers);
    }

    public static byte[] nsdlPanToExcelFile() throws IOException, InvalidFormatException {
        String headers = "PAN,PAN_STATUS,PAN_AS_PER_NSDL,NAME,NAME_STATUS,NAME_AS_PER_NSDL,DOB,DOB_STATUS,DOB_AS_PER_NSDL,REMARKS";
        return generateExcelSheet(headers);
    }

    public static byte[] generateExcelSheet(String headers) throws InvalidFormatException, IOException {
        String[] headersList = headers.split(",");
        Workbook wb = new XSSFWorkbook();
        try {
            Sheet sheet = wb.createSheet();
            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = wb.createCellStyle();
            CellStyle headerCellStyle1 = wb.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerCellStyle1.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating header
            for (int i = 0; i < headersList.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(headersList[i]);
                cell.setCellStyle(headerCellStyle);
                if (cell.getStringCellValue().equalsIgnoreCase("Text 1") || cell.getStringCellValue().equalsIgnoreCase("Text 2") || cell.getStringCellValue().equalsIgnoreCase("Text 3")) {
                    cell.setCellStyle(headerCellStyle1);
                }
                sheet.autoSizeColumn(i);
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            wb.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static byte[] generateExcelSheet1(String headers) {
        LOGGER.info("Headers->{}",headers);
        String[] headersList = headers.split(",");
        try (
            Workbook wb = new XSSFWorkbook();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            Sheet sheet = wb.createSheet("Sheet1");
            Row headerRow = sheet.createRow(0);

            // Define cell styles
            CellStyle aquaStyle = wb.createCellStyle();
            aquaStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            aquaStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            CellStyle whiteStyle = wb.createCellStyle();
            whiteStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            whiteStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Set<String> whiteHeaders = new HashSet<>(Arrays.asList("text 1", "text 2", "text 3"));

            // Creating header cells
            LOGGER.info("Headers Size1->{} ",headersList.length);
            for (int i = 0; i < headersList.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headersList[i]);

                if (whiteHeaders.contains(headersList[i].trim().toLowerCase())) {
                    cell.setCellStyle(whiteStyle);
                } else {
                    cell.setCellStyle(aquaStyle);
                }
            }

            // Ensure all cells are created before resizing
            LOGGER.info("Headers Size2-> {}",headersList.length);
            for (int i = 0; i < headersList.length; i++) {
                sheet.autoSizeColumn(i);
            }

            wb.write(outputStream);
            return outputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
