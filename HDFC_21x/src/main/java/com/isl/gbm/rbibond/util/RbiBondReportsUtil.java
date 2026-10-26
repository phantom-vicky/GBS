package com.isl.gbm.rbibond.util;

import be.quodlibet.boxable.BaseTable;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dao.RbiBondCommonServiceDAO;
import com.isl.gbm.rbibond.dto.RbiBondMetaDataRequestDto;
import com.isl.gbm.rbibond.dto.reports.*;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Component
public class RbiBondReportsUtil {


    @Value("${gbm.bankName}")
    private String bankName;
    @Autowired
    RbiBondCommonServiceDAO rbiBondCommonServiceDAO;


    public byte[] excelFileGeneratorForInterestRunReport(BondInterestRunCreditDTO bondInterestRunCreditDTO) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("sheet1");

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row, 0, "Bond Actual Interest Run Credited Report – centralized ", style);

        row = sheet.createRow(1);
        createCell(row, 0, bankName);


        row = sheet.createRow(2);
        createCell(row, 0, UserAccountUtils.getCurrentSolId());

        row = sheet.createRow(3);
        createCell(row, 0, bondInterestRunCreditDTO.getBranchName());


        row = sheet.createRow(4);
        createCell(row, 0, this.getSchemeName(bondInterestRunCreditDTO.getSchemeCode()));

        row = sheet.createRow(5);
        createCell(row, 0, "Bond Actual Interest Run Credited Report – centralized ", style);


        row = sheet.createRow(6);
        createCell(row, 0, "for Period from:" + bondInterestRunCreditDTO.getInterestPayFrom() + "\t" + "to: " + bondInterestRunCreditDTO.getInterestPayTo(), style);


        row = sheet.createRow(7);
        createCell(row, 0, "S.No", style);
        createCell(row, 1, "Branch Id", style);
        createCell(row, 2, "BOND Ledger Account Number", style);
        createCell(row, 3, "Customer Name", style);
        createCell(row, 4, "BOND Subscription ID", style);
        createCell(row, 5, "Subscription Date", style);
        createCell(row, 6, "Subscription Amount", style);
        createCell(row, 7, "Interest From Period", style);
        createCell(row, 8, "Interest To Period", style);
        createCell(row, 9, "Interest payment mode", style);
        createCell(row, 10, "Credit Account Number", style);
        createCell(row, 11, "Total Interest Rs.", style);
        createCell(row, 12, "TDS Amount Rs.", style);
        createCell(row, 13, "Net Interest Amount Rs.", style);
        createCell(row, 14, "CBS Tran ID", style);
        createCell(row, 15, "GBM Tran ID", style);


        int n = 7;
        int index = 1;

        double totIntAmt = 0;
        double totTds = 0;
//        Double totArr = 0d;
        double totNet = 0;

        for (Map<String, Object> item : bondInterestRunCreditDTO.getData()) {

            String totIntAmtS = (String) item.get("Gross_Interest_Amount");
            String totTdsS = (String) item.get("TDS_AMT");
            String totNetS = (String) item.get("NET_INTEREST_AMOUNT");

            totIntAmt = totIntAmtS == null ? 0 : Double.parseDouble(totIntAmtS) + totIntAmt;
            totTds = totTdsS == null ? 0 : Double.parseDouble(totTdsS) + totTds;
            totNet = totNetS == null ? 0 : Double.parseDouble(totNetS) + totNet;

//            totIntAmt += item.get("Gross_Interest_Amount") == null ? 0 : Double.parseDouble((String) item.get("Gross_Interest_Amount"));
//            totTds += bondInterestRunCreditDTO.getCurrentTds() == null ? 0 : Double.parseDouble(bondInterestRunCreditDTO.getCurrentTds());
////            totArr += rbiBondCentralizedInterestTrailRunDto.getArrearTdsAmount() == null ? 0 : Double.parseDouble(rbiBondCentralizedInterestTrailRunDto.getArrearTdsAmount());
//            totNet += bondInterestRunCreditDTO.getNetInterestAmt() == null ? 0 : Double.parseDouble(bondInterestRunCreditDTO.getNetInterestAmt());

            row = sheet.createRow(++n);

            createCell(row, 0, String.valueOf(index++));
            createCell(row, 1, (String) item.get("BRANCH_ID"));
            createCell(row, 2, (String) item.get("BLA_NO"));
            createCell(row, 3, (String) item.get("CUSTOMER_NAME"));
            createCell(row, 4, (String) item.get("BOND_SUBSCRIPTION_ID"));
            createCell(row, 5, (String) item.get("SUBSCRIPTION_DATE"));
            createCell(row, 6, (String) String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            createCell(row, 7, (String) item.get("INTEREST_PERIOD_FROM"));
            createCell(row, 8, (String) item.get("INTERST_PERIOD_TO"));
            createCell(row, 9, (String) item.get("MODE_OF_INTEREST_PAYMENT"));
            createCell(row, 10, (String) item.get("CREDIT_ACCOUNT_NUMBER"));
            createCell(row, 11, (String) String.valueOf(item.get("Gross_Interest_Amount")));
            createCell(row, 12, (String) String.valueOf(item.get("TDS_AMT")));
            createCell(row, 13, (String) String.valueOf(item.get("NET_INTEREST_AMOUNT")));
            createCell(row, 14, (String) item.get("CBS_Tran_Id"));
            createCell(row, 15, (String) item.get("GBM_TRAN_ID"));


//            createCell(row, 13, item.getArrearTdsAmount());
//            createCell(row, 14, item.getNetInterestAmount());


        }

        row = sheet.createRow(++n);
        createCell(row, 0, "Total ", style);
        createCell(row, 11, String.valueOf(totIntAmt));
        createCell(row, 12, String.valueOf(totTds));
//        createCell(row,13, String.valueOf(totArr));
        createCell(row, 13, String.valueOf(totNet));

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(9);
        sheet.autoSizeColumn(10);
        sheet.autoSizeColumn(11);
        sheet.autoSizeColumn(12);
        sheet.autoSizeColumn(13);
        sheet.autoSizeColumn(14);
        sheet.autoSizeColumn(15);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }

    public byte[] pdfFileGeneratorForBondInterestPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Create PDF document
        try (PDDocument document = new PDDocument()) {

            // Create Landscape A1 Page
            PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // ---- Header ----
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
            contentStream.newLineAtOffset(800, PDRectangle.A1.getWidth() - 80);
            contentStream.showText("Bond Interest Payment Pending Report – Collecting Branch");
            contentStream.endText();
            contentStream.close();

            float margin = 20;
            float yStart = PDRectangle.A1.getWidth() - 150;
            float tableWidth = PDRectangle.A1.getHeight() - (2 * margin);

            // ---- First Info Table (2 Columns) ----
            BaseTable table = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, true, true);

            be.quodlibet.boxable.Row<PDPage> row = table.createRow(18);
            row.createCell(50, "Bond Interest Payment Pending Report – Collecting Branch");
            row.createCell(50, "for " + bondInterestPayPendingCollDTO.getInterestPaymentFor() + "  " + bondInterestPayPendingCollDTO.getFinancialYear());

            row = table.createRow(18);
            row.createCell(50, this.getSchemeName(bondInterestPayPendingCollDTO.getSchemeCode()));
            row.createCell(50, "Branch Id");

            row = table.createRow(18);
            row.createCell(50, UserAccountUtils.getCurrentSolId());
            row.createCell(50, "Branch Name");

            row = table.createRow(18);
            row.createCell(50, bondInterestPayPendingCollDTO.getBranchName());
            row.createCell(50, "");

            table.draw();

            // ---- Move to next table ----
            float yPosition = getYAfterDraw(table, page, yStart) - 30;

            // ---- Data Table (10 Columns) ----
            BaseTable table1 = new BaseTable(yPosition, yStart, 20, tableWidth, margin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> headerRow = table1.createRow(18);

            String[] headerTitles = {
                    "S.No", "Branch Id", "BLA No.", "Holder Name", "Subscription Date",
                    "Subscription Account No.", "Subscription Amount", "Interest Payment mode",
                    "Gross Interest Amount (Rs)", "Reasons"
            };

            for (String headerTitle : headerTitles) {
                headerRow.createCell(10, headerTitle).setFont(PDType1Font.HELVETICA_BOLD);
            }

            int index = 1;
            for (Map<String, Object> item : bondInterestPayPendingCollDTO.getData()) {
                be.quodlibet.boxable.Row<PDPage> dataRow = table1.createRow(16);
                dataRow.createCell(10, String.valueOf(index++));
                dataRow.createCell(10, (String) item.get("BRANCH_ID"));
                dataRow.createCell(10, (String) item.get("bond_ledger_account_number"));
                dataRow.createCell(10, (String) item.get("CUSTOMER_NAME"));
                dataRow.createCell(10, (String) item.get("SUBCRIPTION_DATE"));
                dataRow.createCell(10, (String) item.get("BOND_SUBSCRIPTION_ID"));
                dataRow.createCell(10, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
                dataRow.createCell(10, (String) item.get("INTEREST_PAYMENT_MODE"));
                dataRow.createCell(10, String.valueOf(item.get("GROSS_INTEREST_AMOUNT")));
                dataRow.createCell(10, (String) item.get("REASON"));
            }

            table1.draw();

            document.save(baos);
        }

        System.out.println("PDF created successfully (PDFBox)");
        return baos.toByteArray();
    }
    private float getYAfterDraw(BaseTable table, PDPage page, float fallbackY) {
        // 1) Try invoking draw() reflectively and capture return value
        try {
            Method drawMethod = table.getClass().getMethod("draw");
            Object ret = drawMethod.invoke(table);
            if (ret instanceof Number) {
                return ((Number) ret).floatValue();
            }
        } catch (NoSuchMethodException nsme) {
            // draw() may be present but void, or method not found — ignore and continue
        } catch (Exception e) {
            // any reflection issue — continue to next strategy
        }

        // 2) Try calling common accessor methods
        String[] candidateGetters = {"getYPosition", "getTableBottomY", "getCursor", "getCurrentY", "getY"};
        for (String getter : candidateGetters) {
            try {
                Method m = table.getClass().getMethod(getter);
                Object val = m.invoke(table);
                if (val instanceof Number) {
                    return ((Number) val).floatValue();
                }
            } catch (NoSuchMethodException nsme) {
                // method not available — next
            } catch (Exception e) {
                // invocation failed — next
            }
        }

        // 3) Final fallback: try reflectively getting fields that may store y position (rare)
        try {
            // common field names (best-effort)
            String[] candidateFields = {"yStart", "yPosition", "cursor", "y"};
            for (String fld : candidateFields) {
                try {
                    java.lang.reflect.Field f = table.getClass().getDeclaredField(fld);
                    f.setAccessible(true);
                    Object val = f.get(table);
                    if (val instanceof Number) {
                        return ((Number) val).floatValue();
                    }
                } catch (NoSuchFieldException nsf) {
                    // ignore
                }
            }
        } catch (Exception e) {
            // ignore and fallback
        }

        // 4) As a last resort, return fallbackY (page top minus margin) — prevents crash
        return fallbackY;
    }

    public byte[] excelFileGeneratorForBondInterestPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("sheet1");

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row, 0, "Bond Interest Payment Pending Report – Collecting Branch", style);

        row = sheet.createRow(1);
        createCell(row, 0, "for " + bondInterestPayPendingCollDTO.getInterestPaymentFor() + "\t" + bondInterestPayPendingCollDTO.getFinancialYear(), style);

        row = sheet.createRow(2);
        createCell(row, 0, this.getSchemeName(bondInterestPayPendingCollDTO.getSchemeCode()));

        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(4);
        createCell(row, 0, "Branch Name :", style);
        createCell(row, 1, bondInterestPayPendingCollDTO.getBranchName());


        row = sheet.createRow(5);
        createCell(row, 0, "S.No", style);
        createCell(row, 1, "Branch ID", style);
        createCell(row, 2, "BLA No.", style);
        createCell(row, 3, "Holder Name", style);
        createCell(row, 4, "Subscription Date", style);
        createCell(row, 5, "Subscription Account No.", style);
        createCell(row, 6, "Subscription Amount", style);
        createCell(row, 7, "Interest Payment mode", style);
        createCell(row, 8, "Gross Interest Amount (Rs)", style);
        createCell(row, 9, "Reasons", style);


        int n = 5;
        int index = 1;


        for (Map<String, Object> item : bondInterestPayPendingCollDTO.getData()) {


            row = sheet.createRow(++n);

            createCell(row, 0, String.valueOf(index++));

            createCell(row, 1, (String) item.get("BRANCH_ID"));
            createCell(row, 2, (String) item.get("bond_ledger_account_number"));
            createCell(row, 3, (String) item.get("CUSTOMER_NAME"));
            createCell(row, 4, (String) item.get("SUBCRIPTION_DATE"));
            createCell(row, 5, (String) item.get("BOND_SUBSCRIPTION_ID"));
            createCell(row, 6, (String) String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            createCell(row, 7, (String) item.get("INTEREST_PAYMENT_MODE"));
            createCell(row, 8, (String) String.valueOf(item.get("GROSS_INTEREST_AMOUNT")));
            createCell(row, 9, (String) item.get("REASON"));


//            createCell(row, 13, item.getArrearTdsAmount());
//            createCell(row, 14, item.getNetInterestAmount());


        }

        row = sheet.createRow(++n);


        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(9);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }

    public byte[] pdfFileGeneratorForInterestRunReport(BondInterestRunCreditDTO bondInterestRunCreditDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PDDocument document = new PDDocument()) {

            // Create Landscape A1 Page
            PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
            document.addPage(page);

            // Header
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
            contentStream.newLineAtOffset(750, PDRectangle.A1.getWidth() - 80);
            contentStream.showText("Bond Actual Interest Run Credited Report – Centralized");
            contentStream.endText();
            contentStream.close();

            float margin = 20;
            float yStart = PDRectangle.A1.getWidth() - 150;
            float tableWidth = PDRectangle.A1.getHeight() - (2 * margin);

            // ===== First Info Table =====
            BaseTable table = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> row;

            row = table.createRow(18);
            row.createCell(50, bankName);
            row.createCell(50, UserAccountUtils.getCurrentSolId());

            row = table.createRow(18);
            row.createCell(50, bondInterestRunCreditDTO.getBranchName());
            row.createCell(50, this.getSchemeName(bondInterestRunCreditDTO.getSchemeCode()));

            row = table.createRow(18);
            row.createCell(50, "Bond Actual Interest Run Credited Report – Centralized");
            row.createCell(50, "For Period From: " + bondInterestRunCreditDTO.getInterestPayFrom()
                    + " To: " + bondInterestRunCreditDTO.getInterestPayTo());

            table.draw();

            float yPosition = getYAfterDraw(table, page, yStart) - 30;

            // ===== Main Data Table (16 columns) =====
            BaseTable table1 = new BaseTable(yPosition, yStart, 20, tableWidth, margin, document, page, true, true);
            be.quodlibet.boxable.Row<PDPage> headerRow = table1.createRow(18);

            String[] cols = {"S.No", "Branch Id", "BOND Ledger Account Number", "Customer Name",
                    "BOND Subscription ID", "Subscription Date", "Subscription Amount",
                    "Interest From Period", "Interest To Period", "Interest payment mode",
                    "Credit Account Number", "Total Interest Rs.", "TDS Amount Rs.",
                    "Net Interest Amount Rs.", "CBS Tran ID", "GBM Tran ID"};

            for (String c : cols) headerRow.createCell(6.25f, c).setFont(PDType1Font.HELVETICA_BOLD);

            double totIntAmt = 0, totTds = 0, totNet = 0;
            int index = 1;

            for (Map<String, Object> item : bondInterestRunCreditDTO.getData()) {

                String totIntAmtS = (String) item.get("Gross_Interest_Amount");
                String totTdsS = (String) item.get("TDS_AMT");
                String totNetS = (String) item.get("NET_INTEREST_AMOUNT");

                totIntAmt += totIntAmtS == null ? 0 : Double.parseDouble(totIntAmtS);
                totTds += totTdsS == null ? 0 : Double.parseDouble(totTdsS);
                totNet += totNetS == null ? 0 : Double.parseDouble(totNetS);

                be.quodlibet.boxable.Row<PDPage> dataRow = table1.createRow(16);
                dataRow.createCell(6.25f, String.valueOf(index++));
                dataRow.createCell(6.25f, (String) item.get("BRANCH_ID"));
                dataRow.createCell(6.25f, (String) item.get("BLA_NO"));
                dataRow.createCell(6.25f, (String) item.get("CUSTOMER_NAME"));
                dataRow.createCell(6.25f, (String) item.get("BOND_SUBSCRIPTION_ID"));
                dataRow.createCell(6.25f, (String) item.get("SUBSCRIPTION_DATE"));
                dataRow.createCell(6.25f, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
                dataRow.createCell(6.25f, (String) item.get("INTEREST_PERIOD_FROM"));
                dataRow.createCell(6.25f, (String) item.get("INTERST_PERIOD_TO"));
                dataRow.createCell(6.25f, (String) item.get("MODE_OF_INTEREST_PAYMENT"));
                dataRow.createCell(6.25f, (String) item.get("CREDIT_ACCOUNT_NUMBER"));
                dataRow.createCell(6.25f, String.valueOf(item.get("Gross_Interest_Amount")));
                dataRow.createCell(6.25f, String.valueOf(item.get("TDS_AMT")));
                dataRow.createCell(6.25f, String.valueOf(item.get("NET_INTEREST_AMOUNT")));
                dataRow.createCell(6.25f, (String) item.get("CBS_Tran_Id"));
                dataRow.createCell(6.25f, (String) item.get("GBM_TRAN_ID"));
            }

            table1.draw();

            // ===== Total Row =====
            float totalY = getYAfterDraw(table1, page, yStart);
            BaseTable tot = new BaseTable(totalY, yStart, 20, tableWidth, margin, document, page, true, true);

            be.quodlibet.boxable.Row<PDPage> totalRow = tot.createRow(18);
            totalRow.createCell(6.25f, "Total");
            for (int i = 0; i < 10; i++) totalRow.createCell(6.25f, "");
            totalRow.createCell(6.25f, String.valueOf(totIntAmt));
            totalRow.createCell(6.25f, String.valueOf(totTds));
            totalRow.createCell(6.25f, String.valueOf(totNet));
            totalRow.createCell(6.25f, "");
            totalRow.createCell(6.25f, "");

            tot.draw();

            document.save(baos);
        }

        System.out.println("PDF created successfully (PDFBox)");
        return baos.toByteArray();
    }


    public byte[] excelFileGeneratorForBondRepay(List<Map<String, Object>> results, BondRepaymentReportDto bondRepaymentReportDto) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheet1 = "sheet1";

        XSSFSheet sheet = workbook.createSheet(sheet1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row, 0, "Bond Repayment –  Report", style);

        row = sheet.createRow(1);
        createCell(row, 0, "Bank Name :", style);

        createCell(row, 1, bankName);

        row = sheet.createRow(2);
        createCell(row, 0, "Branch Name :", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(4);
        createCell(row, 0, "S.No", style);
        createCell(row, 1, "BLA No", style);
        createCell(row, 2, "Holder Name", style);
        createCell(row, 3, "Subscription ID", style);
        createCell(row, 4, "Date of Subscription", style);
        createCell(row, 5, "Subscription Amount", style);
        createCell(row, 6, "Amount Repaid", style);
        createCell(row, 7, "Maturity Date", style);
        createCell(row, 8, "Date of Repayment", style);


        int n = 4;

        Double totIntAmt = 0d;
        Double totTds = 0d;
        Double totNet = 0d;

        for (Map<String, Object> item : bondRepaymentReportDto.getData()) {

            totIntAmt += bondRepaymentReportDto.getSubsAmt() == null ? 0 : Double.parseDouble(bondRepaymentReportDto.getSubsAmt());
            totTds += bondRepaymentReportDto.getAmtRepaid() == null ? 0 : Double.parseDouble(bondRepaymentReportDto.getAmtRepaid());

            row = sheet.createRow(++n);
            createCell(row, 1, (String) item.get("BLA_NO"));
            createCell(row, 2, (String) item.get("bond_holder_name"));
            createCell(row, 3, (String) item.get("BOND_SUBSCRIPTION_ID"));
            createCell(row, 4, (String) item.get("subscription_date"));
            createCell(row, 5, String.valueOf(item.get("subscription_amount")));
            createCell(row, 6, (String) item.get("Amount_of_Repayment"));
            createCell(row, 7, String.valueOf(item.get("MATURITY_DATE")));
            createCell(row, 8, String.valueOf(item.get("DATE_OF_REPAYMENT")));

        }

        row = sheet.createRow(++n);
        createCell(row, 9, "Total ", style);
        createCell(row, 10, String.valueOf(totIntAmt));
        createCell(row, 11, String.valueOf(totTds));


        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);

        sheet.autoSizeColumn(9);
        sheet.autoSizeColumn(10);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }

    public byte[] pdfFileGeneratorForBondRepay(List<Map<String, Object>> results, BondRepaymentReportDto bondRepaymentReportDto) throws IOException {

        double totIntAmt = 0d;
        double totTds = 0d;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(org.apache.pdfbox.pdmodel.font.PDType1Font.HELVETICA_BOLD, 18);
        contentStream.newLineAtOffset(500, 1650);
        contentStream.showText("Bond Repayment – Report");
        contentStream.endText();
        contentStream.close();

        float margin = 30;
        float yStart = 1600;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yPosition = yStart;

        // --------------- HEADER TABLE ---------------
        BaseTable table = new BaseTable(yPosition, yStart, 20, tableWidth, margin, document, page, true, true);
        be.quodlibet.boxable.Row<PDPage> row = table.createRow(20);

        row.createCell(50, "Bank Name");
        row.createCell(50, bankName);

        row = table.createRow(20);
        row.createCell(50, "Branch Name");
        row.createCell(50, bondRepaymentReportDto.getBranchCode());

        row = table.createRow(20);
        row.createCell(50, "Branch ID");
        row.createCell(50, UserAccountUtils.getCurrentSolId());
        yPosition = table.draw();


        // --------------- DATA TABLE ---------------
        BaseTable table1 = new BaseTable(yPosition - 20, yStart, 20, tableWidth, margin, document, page, true, true);
        be.quodlibet.boxable.Row<PDPage> headerRow = table1.createRow(20);

        String[] headers = {"S.No", "BLA No", "Holder Name", "Subscription ID", "Date of Subscription",
                "Sub Amount Rs", "Amount Repaid Rs", "Maturity Date", "Repayment Date"};

        for (String col : headers) {
            headerRow.createCell(100 / 9f, col);
        }

        int index = 1;
        for (Map<String, Object> item : bondRepaymentReportDto.getData()) {

            totIntAmt += item.get("subscription_amount") == null ? 0 : Double.parseDouble(item.get("subscription_amount").toString());
            totTds += item.get("Amount_of_Repayment") == null ? 0 : Double.parseDouble(item.get("Amount_of_Repayment").toString());

            be.quodlibet.boxable.Row<PDPage> dataRow = table1.createRow(18);
            dataRow.createCell(100 / 9f, String.valueOf(index++));
            dataRow.createCell(100 / 9f, (String) item.get("BLA_NO"));
            dataRow.createCell(100 / 9f, (String) item.get("bond_holder_name"));
            dataRow.createCell(100 / 9f, (String) item.get("BOND_SUBSCRIPTION_ID"));
            dataRow.createCell(100 / 9f, String.valueOf(item.get("subscription_date")));
            dataRow.createCell(100 / 9f, String.valueOf(item.get("subscription_amount")));
            dataRow.createCell(100 / 9f, String.valueOf(item.get("Amount_of_Repayment")));
            dataRow.createCell(100 / 9f, String.valueOf(item.get("MATURITY_DATE")));
            dataRow.createCell(100 / 9f, String.valueOf(item.get("DATE_OF_REPAYMENT")));
        }

        float tableEndY = table1.draw();


        // --------------- TOTAL ROW ---------------
        BaseTable totalTable = new BaseTable(tableEndY - 20, yStart, 20, tableWidth, margin, document, page, true, true);
        be.quodlibet.boxable.Row<PDPage> totalRow = totalTable.createRow(20);

        // 7 blank cells + label + totals
        for (int i = 0; i < 7; i++) totalRow.createCell(100 / 9f, "");
        totalRow.createCell(100 / 9f, "Total");
        totalRow.createCell(100 / 9f, String.format("%.2f", totIntAmt));
        totalRow.createCell(100 / 9f, String.format("%.2f", totTds));

        totalTable.draw();

        document.save(baos);
        document.close();

        return baos.toByteArray();
    }


    public byte[] excelFileGeneratorForTdsCb(List<Map<String, Object>> results, BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheet1 = "sheet1";

        XSSFSheet sheet = workbook.createSheet(sheet1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row, 0, "TDS Deduction –  Report", style);

        row = sheet.createRow(1);
        createCell(row, 0, "Bank Name :", style);

        createCell(row, 1, bankName);

        row = sheet.createRow(2);
        createCell(row, 0, "Branch Name :", style);
        createCell(row, 1, bondTdsCollDeductionReportDTO.getBranchName());

        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(4);
        createCell(row, 0, "S.No", style);
        createCell(row, 1, "Branch Id", style);
        createCell(row, 2, "Bond BLA No", style);
        createCell(row, 3, "Bond Holder Name", style);
        createCell(row, 4, "PAN", style);
        createCell(row, 5, "Subscription ID", style);
        createCell(row, 6, "Subscription Amount Rs", style);
        createCell(row, 7, "Date of Deduction of TDS", style);
        createCell(row, 8, "Interest Amount Rs", style);
        createCell(row, 9, "TDS Amount Rs", style);
        createCell(row, 10, "Net Amount Paid Rs", style);

        int n = 4;

        Double totIntAmt = 0d;
        Double totTds = 0d;
        Double totNet = 0d;

        for (Map<String, Object> item : bondTdsCollDeductionReportDTO.getData()) {

            totIntAmt += bondTdsCollDeductionReportDTO.getInterestAmt() == null ? 0 : Double.parseDouble(bondTdsCollDeductionReportDTO.getInterestAmt());
            totTds += bondTdsCollDeductionReportDTO.getTdsAmt() == null ? 0 : Double.parseDouble(bondTdsCollDeductionReportDTO.getTdsAmt());
            totNet += bondTdsCollDeductionReportDTO.getNetAmt() == null ? 0 : Double.parseDouble(bondTdsCollDeductionReportDTO.getNetAmt());

            row = sheet.createRow(++n);
            createCell(row, 1, (String) item.get("BRANCH_ID"));
            createCell(row, 2, (String) item.get("BOND_LEDGER_ACCOUNT_NUMBER"));
            createCell(row, 3, (String) item.get("Holder_Name"));
            createCell(row, 4, (String) item.get("PAN_NO"));
            createCell(row, 5, (String) item.get("BOND_SUBSCRIPTION_ID"));
            createCell(row, 6, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            createCell(row, 7, (String) item.get("Date_of_Deduction_of_TDS"));
            createCell(row, 8, String.valueOf(item.get("INTEREST_AMT")));
            createCell(row, 9, String.valueOf(item.get("CURRENT_TDS_AMOUNT")));
            createCell(row, 10, String.valueOf(item.get("NET_INTEREST_AMOUNT")));


        }

        row = sheet.createRow(++n);
        createCell(row, 7, "Total ", style);
        createCell(row, 8, String.valueOf(totIntAmt));
        createCell(row, 9, String.valueOf(totTds));
        createCell(row, 10, String.valueOf(totNet));

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(9);
        sheet.autoSizeColumn(10);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();

    }

    public byte[] pdfFileGeneratorForTdsCb(List<Map<String, Object>> results,
                                           BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        double totIntAmt = 0d;
        double totTds = 0d;
        double totNet = 0d;

        // Use landscape A1 by swapping width & height
        PDRectangle landscapeA1 = new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth());

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage(landscapeA1);
            document.addPage(page);

            // Header text
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                contentStream.newLineAtOffset(landscapeA1.getWidth() / 2 - 140, landscapeA1.getHeight() - 60);
                contentStream.showText("TDS Deduction –  Report");
                contentStream.endText();
            }

            float margin = 30f;
            float yStart = landscapeA1.getHeight() - 100f;
            float tableWidth = landscapeA1.getWidth() - (2 * margin);
            float bottomMargin = 40f;
            float rowHeight = 20f;
            boolean drawLines = true;
            boolean drawContent = true;

            // ---------------- HEADER INFO TABLE ----------------
            BaseTable headerTable = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, drawLines, drawContent);

            be.quodlibet.boxable.Row<PDPage> row = headerTable.createRow(rowHeight);
            row.createCell(50, "Bank Name");
            row.createCell(50, bankName);

            row = headerTable.createRow(rowHeight);
            row.createCell(50, "Branch Name");
            row.createCell(50, bondTdsCollDeductionReportDTO.getBranchName());

            row = headerTable.createRow(rowHeight);
            row.createCell(50, "Branch Id");
            row.createCell(50, UserAccountUtils.getCurrentSolId());

            // draw header table and capture cursor (y position) returned by draw()
            float yAfterHeader = headerTable.draw();

            // ---------------- MAIN DATA TABLE ----------------
            // Start slightly below header
            BaseTable mainTable = new BaseTable(yAfterHeader - 20f, yStart, bottomMargin, tableWidth, margin, document, page, drawLines, drawContent);

            String[] headers = {
                    "S.No", "Branch Id", "Bond BLA No", "Bond Holder Name", "PAN",
                    "Subscription ID", "Subscription Amount Rs", "Date of Deduction of TDS",
                    "Interest Amount Rs", "TDS Amount Rs", "Net Amount Paid Rs"
            };

            be.quodlibet.boxable.Row<PDPage> headerRow = mainTable.createRow(rowHeight);
            for (String h : headers) {
                headerRow.createCell(100f / headers.length, h);
            }

            int index = 1;
            for (Map<String, Object> item : bondTdsCollDeductionReportDTO.getData()) {

                // Safely parse numeric strings if present in the item map
                String totIntAmtS = item.get("INTEREST_AMT") == null ? null : item.get("INTEREST_AMT").toString();
                String totTdsS = item.get("CURRENT_TDS_AMOUNT") == null ? null : item.get("CURRENT_TDS_AMOUNT").toString();
                String totNetS = item.get("NET_INTEREST_AMOUNT") == null ? null : item.get("NET_INTEREST_AMOUNT").toString();

                totIntAmt += (totIntAmtS == null || totIntAmtS.isEmpty()) ? 0 : Double.parseDouble(totIntAmtS);
                totTds += (totTdsS == null || totTdsS.isEmpty()) ? 0 : Double.parseDouble(totTdsS);
                totNet += (totNetS == null || totNetS.isEmpty()) ? 0 : Double.parseDouble(totNetS);

                be.quodlibet.boxable.Row<PDPage> dataRow = mainTable.createRow(rowHeight);
                dataRow.createCell(100f / headers.length, String.valueOf(index++));
                dataRow.createCell(100f / headers.length, item.get("BRANCH_ID") != null ? item.get("BRANCH_ID").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("BOND_LEDGER_ACCOUNT_NUMBER") != null ? item.get("BOND_LEDGER_ACCOUNT_NUMBER").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("Holder_Name") != null ? item.get("Holder_Name").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("PAN_NO") != null ? item.get("PAN_NO").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("BOND_SUBSCRIPTION_ID") != null ? item.get("BOND_SUBSCRIPTION_ID").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("SUBSCRIPTION_AMOUNT") != null ? item.get("SUBSCRIPTION_AMOUNT").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("Date_of_Deduction_of_TDS") != null ? item.get("Date_of_Deduction_of_TDS").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("INTEREST_AMT") != null ? item.get("INTEREST_AMT").toString() : "0");
                dataRow.createCell(100f / headers.length, item.get("CURRENT_TDS_AMOUNT") != null ? item.get("CURRENT_TDS_AMOUNT").toString() : "0");
                dataRow.createCell(100f / headers.length, item.get("NET_INTEREST_AMOUNT") != null ? item.get("NET_INTEREST_AMOUNT").toString() : "0");
            }

            // draw main table and capture y position after draw
            float yAfterData = mainTable.draw();

            // ---------------- TOTAL ROW ----------------
            BaseTable totalTable = new BaseTable(yAfterData - 20f, yStart, bottomMargin, tableWidth, margin, document, page, drawLines, drawContent);

            be.quodlibet.boxable.Row<PDPage> totalRow = totalTable.createRow(rowHeight);
            // create first 7 empty cells to position "Total" under the right column
            int emptyCellsBeforeTotal = 7;
            for (int i = 0; i < emptyCellsBeforeTotal; i++) {
                totalRow.createCell(100f / headers.length, "");
            }
            totalRow.createCell(100f / headers.length, "Total");
            totalRow.createCell(100f / headers.length, String.format("%.2f", totIntAmt));
            totalRow.createCell(100f / headers.length, String.format("%.2f", totTds));
            totalRow.createCell(100f / headers.length, String.format("%.2f", totNet));

            totalTable.draw();

            document.save(baos);
        }

        return baos.toByteArray();
    }

    public byte[] excelFileGeneratorForTDSReport(List<Map<String, Object>> results, BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheet1 = "sheet1";

        XSSFSheet sheet = workbook.createSheet(sheet1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row, 0, "TDS Deduction –  Report", style);

        row = sheet.createRow(1);
        createCell(row, 0, "Bank Name :", style);

        createCell(row, 1, bankName);

        row = sheet.createRow(2);
        createCell(row, 0, "Branch Name :", style);
        createCell(row, 1, bondTdsDeductionReportDTO.getBranchName());

        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(4);
        createCell(row, 0, "S.No", style);
        createCell(row, 1, "Branch Id", style);
        createCell(row, 2, "Bond BLA No", style);
        createCell(row, 3, "Bond Holder Name", style);
        createCell(row, 4, "PAN", style);
        createCell(row, 5, "Subscription ID", style);
        createCell(row, 6, "Subscription Amount Rs", style);
        createCell(row, 7, "Date of Deduction of TDS", style);
        createCell(row, 8, "Interest Amount Rs", style);
        createCell(row, 9, "TDS Amount Rs", style);
        createCell(row, 10, "Net Amount Paid Rs", style);

        int n = 4;

        double totIntAmt = 0;
        double totTds = 0;
        double totNet = 0;

        for (Map<String, Object> item : bondTdsDeductionReportDTO.getData()) {
            String totIntAmtS = (String) item.get("INTEREST_AMT");
            String totTdsS = (String) item.get("CURRENT_TDS_AMOUNT");
            String totNetS = (String) item.get("NET_INTEREST_AMOUNT");

            totIntAmt = totIntAmtS == null ? 0 : Double.parseDouble(totIntAmtS) + totIntAmt;
            totTds = totTdsS == null ? 0 : Double.parseDouble(totTdsS) + totTds;
            totNet = totNetS == null ? 0 : Double.parseDouble(totNetS) + totNet;

            row = sheet.createRow(++n);
            createCell(row, 1, (String) item.get("BRANCH_ID"));
            createCell(row, 2, (String) item.get("BOND_LEDGER_ACCOUNT_NUMBER"));
            createCell(row, 3, (String) item.get("Holder_Name"));
            createCell(row, 4, (String) item.get("PAN_NO"));
            createCell(row, 5, (String) item.get("BOND_SUBSCRIPTION_ID"));
            createCell(row, 6, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            createCell(row, 7, (String) item.get("Date_of_Deduction_of_TDS"));
            createCell(row, 8, String.valueOf(item.get("INTEREST_AMT")));
            createCell(row, 9, String.valueOf(item.get("CURRENT_TDS_AMOUNT")));
            createCell(row, 10, String.valueOf(item.get("NET_INTEREST_AMOUNT")));

        }

        row = sheet.createRow(++n);
        createCell(row, 7, "Total ", style);
        createCell(row, 8, String.valueOf(totIntAmt));
        createCell(row, 9, String.valueOf(totTds));
        createCell(row, 10, String.valueOf(totNet));

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(9);
        sheet.autoSizeColumn(10);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();

    }

    public byte[] pdfFileGeneratorForTDSReport(List<Map<String, Object>> results,
                                               BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException {

        double totIntAmt = 0;
        double totTds = 0;
        double totNet = 0;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Use landscape A1 by swapping width & height
        PDRectangle landscapeA1 = new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth());

        try (PDDocument document = new PDDocument()) {

            PDPage page = new PDPage(landscapeA1);
            document.addPage(page);

            // Header text
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                // approximate centered offset, adjust if you need exact centering
                contentStream.newLineAtOffset(landscapeA1.getWidth() / 2 - 140, landscapeA1.getHeight() - 60);
                contentStream.showText("TDS Deduction –  Report");
                contentStream.endText();
            }

            float margin = 30f;
            float yStart = landscapeA1.getHeight() - 100f;
            float tableWidth = landscapeA1.getWidth() - (2 * margin);
            float bottomMargin = 40f;
            float rowHeight = 20f;
            boolean drawLines = true;
            boolean drawContent = true;

            // ---------------- HEADER INFO TABLE ----------------
            BaseTable headerTable = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, drawLines, drawContent);

            be.quodlibet.boxable.Row<PDPage> row = headerTable.createRow(rowHeight);
            row.createCell(50, "Bank Name");
            row.createCell(50, bankName);

            row = headerTable.createRow(rowHeight);
            row.createCell(50, "Branch Name");
            row.createCell(50, bondTdsDeductionReportDTO.getBranchName());

            row = headerTable.createRow(rowHeight);
            row.createCell(50, "Branch Id");
            row.createCell(50, UserAccountUtils.getCurrentSolId());

            // draw header table and capture cursor (y position)
            float yAfterHeader = headerTable.draw();

            // ---------------- MAIN DATA TABLE ----------------
            // Start slightly below header
            BaseTable table = new BaseTable(yAfterHeader - 20f, yStart, bottomMargin, tableWidth, margin, document, page, drawLines, drawContent);

            String[] headers = {
                    "S.No", "Branch Id", "Bond BLA No", "Bond Holder Name", "PAN",
                    "Subscription ID", "Subscription Amount Rs", "Date of Deduction of TDS",
                    "Interest Amount Rs", "TDS Amount Rs", "Net Amount Paid Rs"
            };

            be.quodlibet.boxable.Row<PDPage> headerRow = table.createRow(rowHeight);
            for (String h : headers) {
                headerRow.createCell(100f / headers.length, h);
            }

            int index = 1;
            for (Map<String, Object> item : bondTdsDeductionReportDTO.getData()) {

                String totIntAmtS = item.get("INTEREST_AMT") == null ? null : item.get("INTEREST_AMT").toString();
                String totTdsS = item.get("CURRENT_TDS_AMOUNT") == null ? null : item.get("CURRENT_TDS_AMOUNT").toString();
                String totNetS = item.get("NET_INTEREST_AMOUNT") == null ? null : item.get("NET_INTEREST_AMOUNT").toString();

                totIntAmt += (totIntAmtS == null || totIntAmtS.isEmpty()) ? 0 : Double.parseDouble(totIntAmtS);
                totTds += (totTdsS == null || totTdsS.isEmpty()) ? 0 : Double.parseDouble(totTdsS);
                totNet += (totNetS == null || totNetS.isEmpty()) ? 0 : Double.parseDouble(totNetS);

                be.quodlibet.boxable.Row<PDPage> dataRow = table.createRow(rowHeight);
                dataRow.createCell(100f / headers.length, String.valueOf(index++));
                dataRow.createCell(100f / headers.length, item.get("BRANCH_ID") != null ? item.get("BRANCH_ID").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("BOND_LEDGER_ACCOUNT_NUMBER") != null ? item.get("BOND_LEDGER_ACCOUNT_NUMBER").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("Holder_Name") != null ? item.get("Holder_Name").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("PAN_NO") != null ? item.get("PAN_NO").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("BOND_SUBSCRIPTION_ID") != null ? item.get("BOND_SUBSCRIPTION_ID").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("SUBSCRIPTION_AMOUNT") != null ? item.get("SUBSCRIPTION_AMOUNT").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("Date_of_Deduction_of_TDS") != null ? item.get("Date_of_Deduction_of_TDS").toString() : "");
                dataRow.createCell(100f / headers.length, item.get("INTEREST_AMT") != null ? item.get("INTEREST_AMT").toString() : "0");
                dataRow.createCell(100f / headers.length, item.get("CURRENT_TDS_AMOUNT") != null ? item.get("CURRENT_TDS_AMOUNT").toString() : "0");
                dataRow.createCell(100f / headers.length, item.get("NET_INTEREST_AMOUNT") != null ? item.get("NET_INTEREST_AMOUNT").toString() : "0");
            }

            // draw main table and capture y position after draw
            float yAfterData = table.draw();

            // ---------------- TOTAL ROW ----------------
            // Create total table starting a bit below the data table
            BaseTable totalTable = new BaseTable(yAfterData - 20f, yStart, bottomMargin, tableWidth, margin, document, page, drawLines, drawContent);

            be.quodlibet.boxable.Row<PDPage> totalRow = totalTable.createRow(rowHeight);
            // create 7 empty cells (to align "Total" at position 8)
            for (int i = 0; i < 7; i++) {
                totalRow.createCell(100f / headers.length, "");
            }
            totalRow.createCell(100f / headers.length, "Total");
            totalRow.createCell(100f / headers.length, String.format("%.2f", totIntAmt));
            totalRow.createCell(100f / headers.length, String.format("%.2f", totTds));
            totalRow.createCell(100f / headers.length, String.format("%.2f", totNet));

            totalTable.draw();

            // Save document to stream
            document.save(baos);
        }

        return baos.toByteArray();
    }

    public String getSchemeName(String schemeCode) {
        RbiBondMetaDataRequestDto rbiBondMetaDataRequestDto = new RbiBondMetaDataRequestDto();
        rbiBondMetaDataRequestDto.setReferenceId("TAX");
        String des = null;
        ResponseDataDTO responseDataDTO = rbiBondCommonServiceDAO.getModuleDetails(rbiBondMetaDataRequestDto);
        if (responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
            List<HashMap<String, String>> list = (List<HashMap<String, String>>) responseDataDTO.getResult();
            for (HashMap<String, String> item : list) {
                if (item.get("subCode").equalsIgnoreCase(schemeCode)) {
                    des = item.get("subCodeDescription");
                    break;
                }
            }
        }
        return des;
    }

    public byte[] excelFileGeneratorForInterestPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("sheet1");

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row, 0, "Bond Interest Payment Pending Report – Centralised", style);

        row = sheet.createRow(1);
        createCell(row, 0, "for " + bondInterestPayPendingDTO.getInterestPaymentFor() + "\t" + bondInterestPayPendingDTO.getFinancialYear(), style);

        row = sheet.createRow(2);
        createCell(row, 0, this.getSchemeName(bondInterestPayPendingDTO.getSchemeCode()));

        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(4);
        createCell(row, 0, "Branch Name :", style);
        createCell(row, 1, bondInterestPayPendingDTO.getBranchName());


        row = sheet.createRow(5);
        createCell(row, 0, "S.No", style);
        createCell(row, 1, "Branch ID", style);
        createCell(row, 2, "BLA No.", style);
        createCell(row, 3, "Holder Name", style);
        createCell(row, 4, "Subscription Date", style);
        createCell(row, 5, "Subscription Account No.", style);
        createCell(row, 6, "Subscription Amount", style);
        createCell(row, 7, "Interest Payment mode", style);
        createCell(row, 8, "Gross Interest Amount (Rs)", style);
        createCell(row, 9, "Reasons", style);

        int n = 5;
        int index = 1;

        for (Map<String, Object> item : bondInterestPayPendingDTO.getData()) {

            row = sheet.createRow(++n);

            createCell(row, 0, String.valueOf(index++));

            createCell(row, 1, (String) item.get("BRANCH_ID"));
            createCell(row, 2, (String) item.get("bond_ledger_account_number"));
            createCell(row, 3, (String) item.get("CUSTOMER_NAME"));
            createCell(row, 4, (String) item.get("SUBCRIPTION_DATE"));
            createCell(row, 5, (String) item.get("BOND_SUBSCRIPTION_ID"));
            createCell(row, 6, (String) String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            createCell(row, 7, (String) item.get("INTEREST_PAYMENT_MODE"));
            createCell(row, 8, (String) String.valueOf(item.get("GROSS_INTEREST_AMOUNT")));
            createCell(row, 9, (String) item.get("REASON"));

        }

        row = sheet.createRow(++n);

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(9);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();

    }

    public byte[] pdfFileGeneratorForInterestPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Landscape A1 Page
        PDRectangle landscapeA1 = new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth());
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(landscapeA1);
        document.addPage(page);

        // --------- HEADER TITLE ---------
        try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
            cs.newLineAtOffset(landscapeA1.getWidth() / 2 - 260, landscapeA1.getHeight() - 60);
            cs.showText("Bond Interest Payment Pending Report – Centralised");
            cs.endText();
        }

        float margin = 40;
        float yStart = landscapeA1.getHeight() - 100;
        float tableWidth = landscapeA1.getWidth() - (2 * margin);
        float bottomMargin = 50;
        float rowHeight = 22;

        // ---------------- HEADER INFO TABLE ----------------
        BaseTable table = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> row;

        row = table.createRow(rowHeight);
        row.createCell(50, "Report");
        row.createCell(50, "Bond Interest Payment Pending Report – Centralised");

        row = table.createRow(rowHeight);
        row.createCell(50, "For");
        row.createCell(50, bondInterestPayPendingDTO.getInterestPaymentFor()
                + "   " + bondInterestPayPendingDTO.getFinancialYear());

        row = table.createRow(rowHeight);
        row.createCell(50, "Scheme");
        row.createCell(50, this.getSchemeName(bondInterestPayPendingDTO.getSchemeCode()));

        row = table.createRow(rowHeight);
        row.createCell(50, "Branch Id");
        row.createCell(50, UserAccountUtils.getCurrentSolId());

        row = table.createRow(rowHeight);
        row.createCell(50, "Branch Name");
        row.createCell(50, bondInterestPayPendingDTO.getBranchName());

        float yAfterHeader = table.draw();

        // ---------------- DATA TABLE ----------------
        BaseTable table1 = new BaseTable(yAfterHeader - 20, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        String[] headers = {
                "S.No", "Branch Id", "BLA No.", "Holder Name", "Subscription Date",
                "Subscription Account No.", "Subscription Amount", "Interest Payment Mode",
                "Gross Interest Amount (Rs)", "Reasons"
        };

        be.quodlibet.boxable.Row<PDPage> headerRow = table1.createRow(rowHeight);
        for (String col : headers) {
            headerRow.createCell(100 / 10f, col);
        }

        int index = 1;
        for (Map<String, Object> item : bondInterestPayPendingDTO.getData()) {

            be.quodlibet.boxable.Row<PDPage> dataRow = table1.createRow(rowHeight);
            dataRow.createCell(100 / 10f, String.valueOf(index++));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("BRANCH_ID")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("bond_ledger_account_number")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("CUSTOMER_NAME")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("SUBCRIPTION_DATE")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("BOND_SUBSCRIPTION_ID")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("INTEREST_PAYMENT_MODE")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("GROSS_INTEREST_AMOUNT")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("REASON")));
        }

        table1.draw();

        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

    public byte[] pdfFileGeneratorForBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Landscape A1
        PDRectangle landscapeA1 = new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth());
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(landscapeA1);
        document.addPage(page);

        // --------- HEADER TITLE ---------
        try (PDPageContentStream cs = new PDPageContentStream(document, page)) {
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 18);
            cs.newLineAtOffset(landscapeA1.getWidth() / 2 - 260, landscapeA1.getHeight() - 60);
            cs.showText("Bond Interest Not Calculated Report");
            cs.endText();
        }

        float margin = 40;
        float yStart = landscapeA1.getHeight() - 100;
        float tableWidth = landscapeA1.getWidth() - (2 * margin);
        float bottomMargin = 50;
        float rowHeight = 22;

        // ---------------- HEADER INFO TABLE ----------------
        BaseTable headerTable = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> row;

        row = headerTable.createRow(rowHeight);
        row.createCell(50, "Bank Name");
        row.createCell(50, bankName);

        row = headerTable.createRow(rowHeight);
        row.createCell(50, "Branch Name");
        row.createCell(50, bondInterestCalculatedDTO.getBranchname());

        row = headerTable.createRow(rowHeight);
        row.createCell(50, "Branch Id");
        row.createCell(50, UserAccountUtils.getCurrentSolId());

        row = headerTable.createRow(rowHeight);
        row.createCell(50, "Scheme Name");
        row.createCell(50, this.getSchemeName(bondInterestCalculatedDTO.getSchemeCode()));

        row = headerTable.createRow(rowHeight);
        row.createCell(50, "Interest Period");
        row.createCell(50, "For " + bondInterestCalculatedDTO.getInterestPaid() + "  " + bondInterestCalculatedDTO.getFinancialYear());

        float yAfterHeader = headerTable.draw();

        // ---------------- DATA TABLE ----------------
        BaseTable table1 = new BaseTable(yAfterHeader - 20, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        String[] headers = {
                "S.No", "Branch ID", "BLA No", "Holder Name", "Subscription Date",
                "Subscription Account No", "Subscription Amount", "Interest Payment mode",
                "Gross Interest Amount", "Reasons"
        };

        be.quodlibet.boxable.Row<PDPage> headerRow = table1.createRow(rowHeight);
        for (String col : headers) {
            headerRow.createCell(100 / 10f, col);
        }

        int index = 1;
        for (Map<String, Object> item : bondInterestCalculatedDTO.getData()) {

            be.quodlibet.boxable.Row<PDPage> dataRow = table1.createRow(rowHeight);
            dataRow.createCell(100 / 10f, String.valueOf(index++));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("BRANCH_ID")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("BLANO")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("CUSTOMER_NAME")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("SUBCRIPTION_DATE")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("BOND_SUBSCRIPTION_ID")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("INTEREST_PAYMENT_MODE")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("GROSS_INTEREST_AMOUNT")));
            dataRow.createCell(100 / 10f, String.valueOf(item.get("REASON")));
        }

        table1.draw();

        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

    public byte[] excelFileGeneratorForBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheet1 = "sheet1";

        XSSFSheet sheet = workbook.createSheet(sheet1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row, 0, "Bond Interest NOT Calculated Report - Centralized", style);

        row = sheet.createRow(1);
        createCell(row, 0, "Bank Name :", style);

        createCell(row, 1, bankName);

        row = sheet.createRow(2);
        createCell(row, 0, "Branch Name :", style);
        createCell(row, 1, bondInterestCalculatedDTO.getBranchname());

        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());

        row = sheet.createRow(4);
        createCell(row, 0, "Bond Actual Interest Run Credited Report – centralized ", style);


        row = sheet.createRow(5);
        createCell(row, 0, "for Period from:" + bondInterestCalculatedDTO.getInterestPaid() + "\t" + "to: " + bondInterestCalculatedDTO.getFinancialYear(), style);

        row = sheet.createRow(6);
        createCell(row, 0, "Branch Id", style);
        createCell(row, 1, bondInterestCalculatedDTO.getBranchname());

        row = sheet.createRow(7);
        createCell(row, 0, "Branch Name", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(8);
        createCell(row, 0, "S.No", style);
        createCell(row, 1, "Branch ID", style);
        createCell(row, 2, "BLA No", style);
        createCell(row, 3, "Holder Name", style);
        createCell(row, 4, "Subscription Date", style);
        createCell(row, 5, "Subscription Account No", style);
        createCell(row, 6, "Subscription Amount", style);
        createCell(row, 7, "Interest Payment mode", style);
        createCell(row, 8, "Gross Interest Amount", style);
        createCell(row, 9, "Reasons", style);

        int n = 8;
        int index = 1;


        for (Map<String, Object> item : bondInterestCalculatedDTO.getData()) {
            row = sheet.createRow(++n);

            createCell(row, 0, String.valueOf(index++));
            createCell(row, 1, (String) item.get("BRANCH_ID"));
            createCell(row, 2, (String) item.get("BLANO"));
            createCell(row, 3, (String) item.get("CUSTOMER_NAME"));
            createCell(row, 4, (String) item.get("SUBCRIPTION_DATE"));
            createCell(row, 5, (String) item.get("BOND_SUBSCRIPTION_ID"));
            createCell(row, 6, (String) String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            createCell(row, 7, (String) item.get("INTEREST_PAYMENT_MODE"));
            createCell(row, 8, (String) String.valueOf(item.get("GROSS_INTEREST_AMOUNT")));
            createCell(row, 9, (String) item.get("REASON"));

        }


        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(9);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }

    public byte[] excelFileGeneratorForInterestPayMisReport(HashMap<String, Object> resultMap) throws IOException {
        List<RbiBondInterestPaymentMisReportDto> list = new ArrayList<>();
        RbiBondInterestPaymentMisReportDto rbiBondInterestPaymentMisReportDto = new RbiBondInterestPaymentMisReportDto();

        list = (List<RbiBondInterestPaymentMisReportDto>) resultMap.get("dataList");


        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheet1 = "sheet1";

        XSSFSheet sheet = workbook.createSheet(sheet1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(12);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);


        CellStyle centerAlignedStyle = workbook.createCellStyle();
        centerAlignedStyle.setAlignment(HorizontalAlignment.CENTER);
        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        centerAlignedStyle.setFont(headerFont);


        createCell(row, 0, "BOND INTEREST PAYMENT MIS REPORT - SOL WISE", centerAlignedStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));


        row = sheet.createRow(1);
        createCell(row, 0, "Bank Name :", style);

        createCell(row, 1, bankName);

        row = sheet.createRow(2);
        createCell(row, 0, "Branch Name :", style);
        createCell(row, 1, resultMap.get("solDesc").toString());

        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(4);
        createCell(row, 0, "S.No", style);
        createCell(row, 1, "Branch Id", style);
        createCell(row, 2, "Gross Interest Amount\n " + "Rs.", style);
        createCell(row, 3, "TDS Amount\n" + "Rs.\n", style);
        createCell(row, 4, "Net Interest Amount\n" + "Rs.\n", style);


        int n = 4;

        double totIntAmt = 0;
        double totTds = 0;
        double totNet = 0;

        for (RbiBondInterestPaymentMisReportDto item : list) {

            row = sheet.createRow(++n);
            createCell(row, 0, (String) item.getsNo());
            createCell(row, 1, (String) item.getBranchId());
            createCell(row, 2, (String) item.getGrossIntAmt());
            createCell(row, 3, (String) item.getTdsAmt());
            createCell(row, 4, (String) item.getNetIntAmt());


        }

        row = sheet.createRow(++n);
        createCell(row, 1, "Total ", style);
        createCell(row, 2, resultMap.get("totGrossIntAmt").toString());
        createCell(row, 3, resultMap.get("totTdsAmt").toString());
        createCell(row, 4, resultMap.get("totNetIntAmt").toString());

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();

    }

    public byte[] pdfFileGeneratorForInterestPayMisReport(HashMap<String, Object> resultMap) throws IOException {
        List<RbiBondInterestPaymentMisReportDto> list =
                (List<RbiBondInterestPaymentMisReportDto>) resultMap.get("dataList");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // totals (if you also want to compute locally, you can)
        double totIntAmt = 0;
        double totTds = 0;
        double totNet = 0;

        // Use landscape A1
        PDRectangle landscapeA1 = new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth());

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(landscapeA1);
            document.addPage(page);

            // Header
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
                // approximate centering—adjust offset if needed
                contentStream.newLineAtOffset(landscapeA1.getWidth() / 2 - 200, landscapeA1.getHeight() - 60);
                contentStream.showText("BOND INTEREST PAYMENT MIS REPORT - SOL WISE");
                contentStream.endText();
            }

            float margin = 50;
            float yStart = landscapeA1.getHeight() - 100;
            float tableWidth = landscapeA1.getWidth() - (2 * margin);
            float bottomMargin = 50;
            float rowHeight = 20;
            boolean drawLines = true;
            boolean drawContent = true;

            // ---------------- HEADER INFO TABLE ----------------
            BaseTable headerTable = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, drawLines, drawContent);

            be.quodlibet.boxable.Row<PDPage> row = headerTable.createRow(rowHeight);
            row.createCell(50, "Bank Name");
            row.createCell(50, bankName);

            row = headerTable.createRow(rowHeight);
            row.createCell(50, "Branch Name");
            row.createCell(50, resultMap.get("solDesc") != null ? resultMap.get("solDesc").toString() : "");

            row = headerTable.createRow(rowHeight);
            row.createCell(50, "Branch Id");
            row.createCell(50, UserAccountUtils.getCurrentSolId());

            // draw header table and capture current Y position
            float yAfterHeader = headerTable.draw();

            // ---------------- DATA TABLE ----------------
            // start a bit below the header
            BaseTable table1 = new BaseTable(yAfterHeader - 20, yStart, bottomMargin, tableWidth, margin, document, page, drawLines, drawContent);

            be.quodlibet.boxable.Row<PDPage> headerRow = table1.createRow(rowHeight);
            headerRow.createCell(10, "S.No");
            headerRow.createCell(20, "Branch Id");
            headerRow.createCell(24, "Gross Interest Amount (Rs.)");
            headerRow.createCell(23, "TDS Amount (Rs.)");
            headerRow.createCell(23, "Net Interest Amount (Rs.)");

            int index = 1;
            for (RbiBondInterestPaymentMisReportDto item : list) {
                be.quodlibet.boxable.Row<PDPage> dataRow = table1.createRow(rowHeight);

                // add cells (use toString guards to avoid NPEs)
                dataRow.createCell(10, item.getsNo() != null ? item.getsNo().toString() : String.valueOf(index++));
                dataRow.createCell(20, item.getBranchId() != null ? item.getBranchId() : "");
                dataRow.createCell(24, item.getGrossIntAmt() != null ? item.getGrossIntAmt() : "0");
                dataRow.createCell(23, item.getTdsAmt() != null ? item.getTdsAmt() : "0");
                dataRow.createCell(23, item.getNetIntAmt() != null ? item.getNetIntAmt() : "0");
            }

            // draw data table and capture current Y position after drawing
            float yAfterData = table1.draw();

            // ---------------- TOTAL ROW ----------------
            BaseTable tot = new BaseTable(yAfterData - 20, yStart, bottomMargin, tableWidth, margin, document, page, drawLines, drawContent);
            be.quodlibet.boxable.Row<PDPage> totalRow = tot.createRow(rowHeight);

            totalRow.createCell(30, "");
            totalRow.createCell(20, "Total");
            totalRow.createCell(24, resultMap.get("totGrossIntAmt") != null ? resultMap.get("totGrossIntAmt").toString() : "0");
            totalRow.createCell(23, resultMap.get("totTdsAmt") != null ? resultMap.get("totTdsAmt").toString() : "0");
            totalRow.createCell(23, resultMap.get("totNetIntAmt") != null ? resultMap.get("totNetIntAmt").toString() : "0");

            tot.draw();

            document.save(baos);
        }

        return baos.toByteArray();
    }

    public byte[] excelFileGeneratorForInterRunCollBranchReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("sheet1");

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row, 0, "Bond Actual Interest Run Credited Report – Collecting Branch ", style);

        row = sheet.createRow(1);
        createCell(row, 0, "Bank Name :", style);
        createCell(row, 1, bankName);

        row = sheet.createRow(2);
        createCell(row, 0, "Branch Name :", style);
        createCell(row, 1, bondInterRunCrdtCollBranDTO.getBranchName());

        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :", style);
        createCell(row, 1, UserAccountUtils.getCurrentSolId());


        row = sheet.createRow(4);
        createCell(row, 0, "S.No", style);
        createCell(row, 1, "BOND Ledger Account Number", style);
        createCell(row, 2, "Customer Name", style);
        createCell(row, 3, "BOND Subscription ID", style);
        createCell(row, 4, "Subscription Date", style);
        createCell(row, 5, "Subscription Amount", style);
        createCell(row, 6, "Interest From Period", style);
        createCell(row, 7, "Interest To Period", style);
        createCell(row, 8, "Interest payment mode", style);
        createCell(row, 9, "Credit Account Number", style);
        createCell(row, 10, "Total Interest Rs.", style);
        createCell(row, 11, "TDS Amount Rs.", style);
        createCell(row, 12, "Net Interest Amount Rs.", style);
        createCell(row, 13, "CBS Tran ID", style);
        createCell(row, 14, "GBM Tran ID", style);


        int n = 4;
        int index = 1;

        Double totIntAmt = 0d;
        Double totTds = 0d;
//        Double totArr = 0d;
        Double totNet = 0d;

        for (Map<String, Object> item : bondInterRunCrdtCollBranDTO.getData()) {


            String totIntAmtS = (String) item.get("Gross_Interest_Amount");
            String totTdsS = (String) item.get("TDS_AMT");
            String totNetS = (String) item.get("NET_INTEREST_AMOUNT");

            totIntAmt = totIntAmtS == null ? 0 : Double.parseDouble(totIntAmtS) + totIntAmt;
            totTds = totTdsS == null ? 0 : Double.parseDouble(totTdsS) + totTds;
            totNet = totNetS == null ? 0 : Double.parseDouble(totNetS) + totNet;

            row = sheet.createRow(++n);

            createCell(row, 0, String.valueOf(index++));

            createCell(row, 1, (String) item.get("BLA_NO"));
            createCell(row, 2, (String) item.get("CUSTOMER_NAME"));
            createCell(row, 3, (String) item.get("BOND_SUBSCRIPTION_ID"));
            createCell(row, 4, (String) item.get("SUBSCRIPTION_DATE"));
            createCell(row, 5, (String) String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            createCell(row, 6, (String) item.get("INTEREST_PERIOD_FROM"));
            createCell(row, 7, (String) item.get("INTERST_PERIOD_TO"));
            createCell(row, 8, (String) item.get("MODE_OF_INTEREST_PAYMENT"));
            createCell(row, 9, (String) item.get("CREDIT_ACCOUNT_NUMBER"));
            createCell(row, 10, (String) String.valueOf(item.get("Gross_Interest_Amount")));
            createCell(row, 11, (String) String.valueOf(item.get("TDS_AMT")));
            createCell(row, 12, (String) String.valueOf(item.get("NET_INTEREST_AMOUNT")));
            createCell(row, 13, (String) item.get("CBS_Tran_Id"));
            createCell(row, 14, (String) item.get("GBM_TRAN_ID"));

        }

        row = sheet.createRow(++n);
        createCell(row, 9, "Total ", style);
        createCell(row, 10, String.valueOf(totIntAmt));
        createCell(row, 11, String.valueOf(totTds));
//        createCell(row,13, String.valueOf(totArr));
        createCell(row, 12, String.valueOf(totNet));

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        sheet.autoSizeColumn(9);
        sheet.autoSizeColumn(10);
        sheet.autoSizeColumn(11);
        sheet.autoSizeColumn(12);
        sheet.autoSizeColumn(13);
        sheet.autoSizeColumn(14);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        workbook.write(byteArrayOutputStream);

        System.out.println(" excel file created successfully");
        workbook.close();

        return byteArrayOutputStream.toByteArray();
    }

    public byte[] pdfFileGeneratorForInterRunCollBranchReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO) throws IOException {

        double totIntAmt = 0d;
        double totTds = 0d;
        double totNet = 0d;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        // Header Text
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
        contentStream.newLineAtOffset(500, 1650);
        contentStream.showText("Bond Actual Interest Run Credited Report – Collecting Branch");
        contentStream.endText();
        contentStream.close();

        float margin = 30;
        float yStart = 1600;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        // ---------------- HEADER TABLE ----------------
        BaseTable table = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> row = table.createRow(20);
        row.createCell(50, "Bank Name");
        row.createCell(50, bankName);

        row = table.createRow(20);
        row.createCell(50, "Branch Name");
        row.createCell(50, bondInterRunCrdtCollBranDTO.getBranchName());

        row = table.createRow(20);
        row.createCell(50, "Branch Id");
        row.createCell(50, UserAccountUtils.getCurrentSolId());

        float yPosition = table.draw();

        // ---------------- DATA TABLE ----------------
        BaseTable table1 = new BaseTable(yPosition - 20, yStart, 20, tableWidth, margin, document, page, true, true);

        String[] headers = {
                "S.No", "BOND Ledger Account Number", "Customer Name", "BOND Subscription ID",
                "Subscription Date", "Subscription Amount", "Interest From Period",
                "Interest To Period", "Interest payment mode", "Credit Account Number",
                "Total Interest Rs.", "TDS Amount Rs.", "Net Interest Amount Rs.",
                "CBS Tran ID", "GBM Tran ID"
        };

        be.quodlibet.boxable.Row<PDPage> headerRow = table1.createRow(20);
        for (String col : headers) {
            headerRow.createCell(100 / 15f, col);
        }

        int index = 1;
        for (Map<String, Object> item : bondInterRunCrdtCollBranDTO.getData()) {

            double gross = item.get("Gross_Interest_Amount") == null ? 0 : Double.parseDouble(item.get("Gross_Interest_Amount").toString());
            double tds = item.get("TDS_AMT") == null ? 0 : Double.parseDouble(item.get("TDS_AMT").toString());
            double net = item.get("NET_INTEREST_AMOUNT") == null ? 0 : Double.parseDouble(item.get("NET_INTEREST_AMOUNT").toString());

            totIntAmt += gross;
            totTds += tds;
            totNet += net;

            be.quodlibet.boxable.Row<PDPage> dataRow = table1.createRow(18);
            dataRow.createCell(100 / 15f, String.valueOf(index++));
            dataRow.createCell(100 / 15f, (String) item.get("BLA_NO"));
            dataRow.createCell(100 / 15f, (String) item.get("CUSTOMER_NAME"));
            dataRow.createCell(100 / 15f, (String) item.get("BOND_SUBSCRIPTION_ID"));
            dataRow.createCell(100 / 15f, (String) item.get("SUBSCRIPTION_DATE"));
            dataRow.createCell(100 / 15f, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            dataRow.createCell(100 / 15f, (String) item.get("INTEREST_PERIOD_FROM"));
            dataRow.createCell(100 / 15f, (String) item.get("INTERST_PERIOD_TO"));
            dataRow.createCell(100 / 15f, (String) item.get("MODE_OF_INTEREST_PAYMENT"));
            dataRow.createCell(100 / 15f, (String) item.get("CREDIT_ACCOUNT_NUMBER"));
            dataRow.createCell(100 / 15f, String.valueOf(item.get("Gross_Interest_Amount")));
            dataRow.createCell(100 / 15f, String.valueOf(item.get("TDS_AMT")));
            dataRow.createCell(100 / 15f, String.valueOf(item.get("NET_INTEREST_AMOUNT")));
            dataRow.createCell(100 / 15f, (String) item.get("CBS_Tran_Id"));
            dataRow.createCell(100 / 15f, (String) item.get("GBM_TRAN_ID"));
        }

        float tableEndY = table1.draw();

        // ---------------- TOTAL ROW ----------------
        BaseTable totalTable = new BaseTable(tableEndY - 20, yStart, 20, tableWidth, margin, document, page, true, true);
        be.quodlibet.boxable.Row<PDPage> totalRow = totalTable.createRow(20);

        for (int i = 0; i < 9; i++) totalRow.createCell(100 / 15f, "");
        totalRow.createCell(100 / 15f, "Total");
        totalRow.createCell(100 / 15f, String.format("%.2f", totIntAmt));
        totalRow.createCell(100 / 15f, String.format("%.2f", totTds));
        totalRow.createCell(100 / 15f, String.format("%.2f", totNet));
        totalRow.createCell(100 / 15f, "");
        totalRow.createCell(100 / 15f, "");

        totalTable.draw();

        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

}
