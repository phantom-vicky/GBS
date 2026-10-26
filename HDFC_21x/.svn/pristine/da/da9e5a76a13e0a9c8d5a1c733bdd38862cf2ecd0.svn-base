package com.isl.gbm.cbs.integration.generator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class CbsBulkUploadGenerator {

    public static byte[] bankBranchToExcelFile() throws IOException, InvalidFormatException {
        String headers = "SOL_ID/BR_CODE,SOL_DESC,BANK_CODE,MICR_CENTRE_CODE,MICR_BANK_CODE,MICR_BRANCH_CODE,ADDR_1,ADDR_2,CITY_NAME,STATE_NAME,PIN_CODE,PAN_GIR_NO,TAX_DEDN_DETAILS,TDS_CIRCLE_NAME,DIVISION_NAME,CIRCLE_OFFICE_NAME,UNIFORM_BR_CODE,BRANCH_LOCATION,ZONE_CODE,ZONE_NAME,PDO_CODE,SHORT_NAME,BR_MAIL_ID,BR_STATUS,BRANCH_IFSC_CODE";
        return generateExcelSheet(headers);
    }

    public static byte[] officeAccPlaceHolder() throws IOException, InvalidFormatException {
        String headers = "BACID,BACID_DESC,SCHM_TYPE,ADDITIONAL1,ADDITIONAL2,ADDITIONAL3";
        return generateExcelSheet(headers);
    }

    public static byte[] intrumentDetails() throws IOException, InvalidFormatException {
        String headers = "DR_CR_IND,INSTRMNT_TYPE,SCHM_CODE,Text 1,Text 2,Text 3";
        return generateExcelSheet(headers);
    }

    public static byte[] otherBankDetails() throws IOException, InvalidFormatException {
        String headers = "BANK_CODE,BANK_NAME,BANK_SHORT_NAME,ADDR1,ADDR2,CITY,PIN_CODE";
        return generateExcelSheet(headers);
    }

    public static byte[] branchsOfTheOtherBank() throws IOException, InvalidFormatException {
        String headers = "BR_CODE,BANK_CODE,MICR_CENTRE_CODE,MICR_BANK_CODE,MICR_BRANCH_CODE,BR_NAME,ADDR_1,ADDR_2,ADDR_3,CITY_CODE,STATE_CODE,PIN_CODE,additionalText1,additionalText2,additionalText3,BANK_IFSC_CODE";
        return generateExcelSheet(headers);
    }

    public static byte[] maintenanceOfCalenderEntreies() throws IOException, InvalidFormatException {
        String headers = "CAL_B2K_TYPE,CAL_B2K_ID,MMYYYY,DEL_FLG,HLDY_STR,STATUS,HLDY_REMARKS";
        return generateExcelSheet(headers);
    }

    public static byte[] schemeCodeExcel() throws IOException, InvalidFormatException {
        String headers = "schemeCode,description,glSubHeadCode,schemeType,addText1,addText2,addText3";
        return generateExcelSheet(headers);
    }

    public static byte[] userWorkClassInformation() throws IOException, InvalidFormatException {
        String headers = "USER_ID,DEL_FLG,USER_WORK_CLASS,SOL_ID,USER_SOL_TENOR,USER_MAX_INACTIVE_TIME,USER_ACCT_EXPY_DATE,USER_PW_EXPY_DATE,USER_APPL_NAME,NEW_USER_FLG,DEFAULT_TERM_ID,VIRTUAL_FLG,Text 1,Text 2,Text 3";
        return generateExcelSheet(headers);
    }

    public static byte[] branchIfscCodes() throws IOException, InvalidFormatException {
        String headers = "BANK_CODE,BRANCH_CODE,BANK_IDENTIFIER,PAYSYS_ID,DEL_FLG,Text 1,Text 2,Text 3";
        return generateExcelSheet(headers);
    }

    public static byte[] listOfTransactionDetailCodes() throws InvalidFormatException, IOException {
        String headers = "clgTranCode,clgTranCodeDesc,instrumentType,schemeCode,addText1,addText2,addText3";
        return generateExcelSheet(headers);
    }

    public static byte[] generateExcelSheet(String headers) throws InvalidFormatException, IOException {
        String[] headersList = headers.split(",");

//        XSSFWorkbook wb = new XSSFWorkbook();

        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            Sheet sheet = wb.createSheet();

            Row row = sheet.createRow(0);
            CellStyle headerCellStyle = wb.createCellStyle();
            CellStyle headerCellStyle1 = wb.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            headerCellStyle1.setFillForegroundColor(IndexedColors.WHITE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            // Creating header

            for(int i = 0; i < headersList.length; i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(headersList[i]);
                cell.setCellStyle(headerCellStyle);
                if(cell.getStringCellValue().equalsIgnoreCase("Text 1") || cell.getStringCellValue().equalsIgnoreCase("Text 2") || cell.getStringCellValue().equalsIgnoreCase("Text 3")){
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


}
