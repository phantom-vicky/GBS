package com.isl.gbm.rbibond.bo.impl;

import be.quodlibet.boxable.BaseTable;
import com.isl.gbm.rbibond.bo.RbiBondCentralizedInterestTrailRunBo;
import com.isl.gbm.rbibond.dto.RbiBondCentralizedInterestTrailRunDto;
import com.isl.gbm.rbibond.dto.RbiBondTrailRunReportDto;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Component
public class RbiBondCentralizedInterestTrailRunBoImpl implements RbiBondCentralizedInterestTrailRunBo {

    @Value("${gbm.bankName}")
    private String bankName;

    @Override
    public byte[] excelFileGeneratorTrailRunRep(List<RbiBondTrailRunReportDto> rbiBondCentralizedInterestTrailRunDtoList, RbiBondCentralizedInterestTrailRunDto bondCentralizedInterestTrailRunDto) throws IOException {


        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("sheet1");

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row,0, "BOND Interest Trial Run – Eligible Accounts Report", style);

        row = sheet.createRow(1);
        createCell(row, 0, "Bank Name :",style);
        createCell(row,1, bankName);

        row = sheet.createRow(2);
        createCell(row, 0, "Branch Name :",style);
        createCell(row,1, bondCentralizedInterestTrailRunDto.getBranchName());

        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :",style);
        createCell(row,1, UserAccountUtils.getCurrentSolId());


        row=sheet.createRow(4);
        createCell(row, 0, "S.No",style);
        createCell(row, 1, "Branch Id",style);
        createCell(row, 2, "Bond Ledger Account Number",style);
        createCell(row, 3, "Customer Name",style);
        createCell(row, 4, "Bond Subscription ID",style);
        createCell(row, 5, "Subscription Amount",style);
        createCell(row, 6, "Subscription Date",style);
        createCell(row, 7, "Interest From Period",style);
        createCell(row, 8, "Interest To Period",style);
        createCell(row, 9, "Interest payment mode",style);
        createCell(row, 10, "Credit Account Number",style);
        createCell(row, 11, "Total Interest Rs",style);
        createCell(row, 12, "TDS Amount Rs",style);
        createCell(row, 13, "Arrear TDS Amount Rs",style);
        createCell(row, 14, "Net Interest Amount Rs",style);
        
        int n = 4;
        int index = 1;

        Double totIntAmt = 0d;
        Double totTds = 0d;
        Double totArr = 0d;
        Double totNet = 0d;

        for(RbiBondTrailRunReportDto rbiBondCentralizedInterestTrailRunDto : rbiBondCentralizedInterestTrailRunDtoList)
        {

            totIntAmt += rbiBondCentralizedInterestTrailRunDto.getInterestAmount() == null ? 0 : Double.parseDouble(rbiBondCentralizedInterestTrailRunDto.getInterestAmount());
            totTds += rbiBondCentralizedInterestTrailRunDto.getCurrentTdsAmount() == null ? 0 : Double.parseDouble(rbiBondCentralizedInterestTrailRunDto.getCurrentTdsAmount());
            totArr += rbiBondCentralizedInterestTrailRunDto.getArrearTdsAmount() == null ? 0 : Double.parseDouble(rbiBondCentralizedInterestTrailRunDto.getArrearTdsAmount());
            totNet += rbiBondCentralizedInterestTrailRunDto.getNetInterestAmount() == null ? 0 : Double.parseDouble(rbiBondCentralizedInterestTrailRunDto.getNetInterestAmount());

            row=sheet.createRow(++n);

            createCell(row, 0, String.valueOf(index++));
            createCell(row, 1, rbiBondCentralizedInterestTrailRunDto.getBranchId());
            createCell(row, 2, rbiBondCentralizedInterestTrailRunDto.getBondLedgerAccountNumber());
            createCell(row, 3, rbiBondCentralizedInterestTrailRunDto.getBondHolderName());
            createCell(row, 4, rbiBondCentralizedInterestTrailRunDto.getBondSubscriptionId());
            createCell(row, 5, rbiBondCentralizedInterestTrailRunDto.getSubscriptionAmount());
            createCell(row, 6, rbiBondCentralizedInterestTrailRunDto.getAccountOpenDate());
            createCell(row, 7, rbiBondCentralizedInterestTrailRunDto.getInterestPeriodFrom());
            createCell(row, 8, rbiBondCentralizedInterestTrailRunDto.getInterestPeriodTo());
            createCell(row, 9, rbiBondCentralizedInterestTrailRunDto.getModeOfInterestPayment());
            createCell(row, 10, rbiBondCentralizedInterestTrailRunDto.getCreditAccountNumber());
            createCell(row, 11, rbiBondCentralizedInterestTrailRunDto.getInterestAmount());
            createCell(row, 12, rbiBondCentralizedInterestTrailRunDto.getCurrentTdsAmount());
            createCell(row, 13, rbiBondCentralizedInterestTrailRunDto.getArrearTdsAmount());
            createCell(row, 14, rbiBondCentralizedInterestTrailRunDto.getNetInterestAmount());
            

        }

        row = sheet.createRow(++n);
        createCell(row, 0, "Total ",style);
        createCell(row,11, String.valueOf(totIntAmt));
        createCell(row,12, String.valueOf(totTds));
        createCell(row,13, String.valueOf(totArr));
        createCell(row,14, String.valueOf(totNet));

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

    @Override
    public byte[] pdfFileGeneratorTrailRunRep(List<RbiBondTrailRunReportDto> list,
                                              RbiBondCentralizedInterestTrailRunDto dto) throws IOException {

        PDDocument document = new PDDocument();
        // Use landscape A1 (swap width/height)
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        float margin = 30;
        float yStart = page.getMediaBox().getHeight() - 50;
        float bottomMargin = 30;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        // -------- HEADER TITLE ----------
        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
            contentStream.beginText();
            contentStream.newLineAtOffset((page.getMediaBox().getWidth() / 2) - 200, yStart + 20);
            contentStream.showText("BOND Interest Trial Run – Eligible Accounts Report");
            contentStream.endText();
        }

        // -------- TOP INFO TABLE ----------
        BaseTable table = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> row = table.createRow(20f);
        row.createCell(20, "Bank Name");
        row.createCell(80, bankName);

        row = table.createRow(20f);
        row.createCell(20, "Branch Name");
        row.createCell(80, dto.getBranchName());

        row = table.createRow(20f);
        row.createCell(20, "Branch Id");
        row.createCell(80, UserAccountUtils.getCurrentSolId());

        // Correct: pass the Row<PDPage> object to addHeaderRow, not an int
        // here we mark the first row (index 0) as the header row to repeat if table spans pages
        be.quodlibet.boxable.Row<PDPage> headerRowForTop = table.getRows().get(0);
        table.addHeaderRow(headerRowForTop);

        // draw the header table
        table.draw();

        // get y position after draw (works across Boxable versions)
        float yPosition = getYAfterDraw(table, page, yStart) - 25f;

        // -------- MAIN DATA TABLE ----------
        BaseTable table1 = new BaseTable(yPosition, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        String[] headers = {
                "S.No", "Branch Id", "Bond Ledger Account Number", "Customer Name", "Bond Subscription ID",
                "Subscription Amount", "Subscription Date", "Interest From Period", "Interest To Period",
                "Interest payment mode", "Credit Account Number", "Total Interest Rs", "TDS Amount Rs",
                "Arrear TDS Amount Rs", "Net Interest Amount Rs"
        };

        be.quodlibet.boxable.Row<PDPage> headerRow = table1.createRow(22f);
        for (String h : headers) headerRow.createCell(100f / headers.length, h);

        // Correct: pass the headerRow object itself (not an index)
        table1.addHeaderRow(headerRow);

        Double totIntAmt = 0d, totTds = 0d, totArr = 0d, totNet = 0d;
        int index = 1;

        for (RbiBondTrailRunReportDto data : list) {

            be.quodlibet.boxable.Row<PDPage> dRow = table1.createRow(18f); // Row<PDPage>

            dRow.createCell(100f / headers.length, String.valueOf(index++));
            dRow.createCell(100f / headers.length, nullSafe(data.getBranchId()));
            dRow.createCell(100f / headers.length, nullSafe(data.getBondLedgerAccountNumber()));
            dRow.createCell(100f / headers.length, nullSafe(data.getBondHolderName()));
            dRow.createCell(100f / headers.length, nullSafe(data.getBondSubscriptionId()));
            dRow.createCell(100f / headers.length, nullSafe(data.getSubscriptionAmount()));
            dRow.createCell(100f / headers.length, nullSafe(data.getAccountOpenDate()));
            dRow.createCell(100f / headers.length, nullSafe(data.getInterestPeriodFrom()));
            dRow.createCell(100f / headers.length, nullSafe(data.getInterestPeriodTo()));
            dRow.createCell(100f / headers.length, nullSafe(data.getModeOfInterestPayment()));
            dRow.createCell(100f / headers.length, nullSafe(data.getCreditAccountNumber()));
            dRow.createCell(100f / headers.length, nullSafe(data.getInterestAmount()));
            dRow.createCell(100f / headers.length, nullSafe(data.getCurrentTdsAmount()));
            dRow.createCell(100f / headers.length, nullSafe(data.getArrearTdsAmount()));
            dRow.createCell(100f / headers.length, nullSafe(data.getNetInterestAmount()));

            totIntAmt += parseDoubleSafe(data.getInterestAmount());
            totTds += parseDoubleSafe(data.getCurrentTdsAmount());
            totArr += parseDoubleSafe(data.getArrearTdsAmount());
            totNet += parseDoubleSafe(data.getNetInterestAmount());
        }

        table1.draw();

        // Correctly get Y after drawing table1
        float yPositionBottom = getYAfterDraw(table1, page, yStart) - 25f;

        // -------- TOTAL ROW TABLE ----------
        BaseTable tot = new BaseTable(yPositionBottom, yStart, bottomMargin, tableWidth, margin, document, page, true, true);
        be.quodlibet.boxable.Row<PDPage> totalRow = tot.createRow(22f);
        totalRow.createCell(100f / headers.length, "Total");

        for (int i = 0; i < 10; i++) totalRow.createCell(100f / headers.length, "");

        totalRow.createCell(100f / headers.length, String.format("%.2f", totIntAmt));
        totalRow.createCell(100f / headers.length, String.format("%.2f", totTds));
        totalRow.createCell(100f / headers.length, String.format("%.2f", totArr));
        totalRow.createCell(100f / headers.length, String.format("%.2f", totNet));

        tot.draw();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

    // ----------------- helper utilities -----------------
    private static String nullSafe(String s) {
        return s == null ? "" : s;
    }

    private static double parseDoubleSafe(String s) {
        try {
            return s == null || s.isEmpty() ? 0d : Double.parseDouble(s);
        } catch (Exception e) {
            return 0d;
        }
    }

    /**
     * Helper to obtain the Y position after drawing a BaseTable in a Boxable-version-safe way.
     * It attempts multiple strategies:
     *  1) invoke draw() via reflection and capture return value (if any)
     *  2) call common accessor methods (getYPosition, getTableBottomY, getCursor)
     *  3) fallback to the provided fallbackY
     */
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

}
