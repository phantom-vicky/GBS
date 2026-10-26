package com.isl.gbm.rbibond.bo.impl;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.HorizontalAlignment;
import be.quodlibet.boxable.VerticalAlignment;
import com.isl.gbm.rbibond.bo.RbiBondTrialRunForRedemptionBo;
import com.isl.gbm.rbibond.dto.RbiBondTrailRunForRedemptionDto;
import com.isl.gbm.rbibond.dto.RbiBondTrailRunReportDto;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
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
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Component
public class RbiBondTrialRunForRedemptionBoImpl implements RbiBondTrialRunForRedemptionBo {

    @Value("${gbm.bankName}")
    private String bankName;

    @Override
    public byte[] excelFileGeneratorTrailRunRep(List<RbiBondTrailRunReportDto> rbiBondCentralizedInterestTrailRunDtoList, RbiBondTrailRunForRedemptionDto bondCentralizedInterestTrailRunDto) throws IOException {


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
    public byte[] pdfFileGeneratorTrailRunRep(List<RbiBondTrailRunReportDto> dataList,
                                              RbiBondTrailRunForRedemptionDto headerDto) throws IOException {

        Double totIntAmt = 0d;
        Double totTds = 0d;
        Double totArr = 0d;
        Double totNet = 0d;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();

        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Header Title
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;
        PDFont fontNormal = PDType1Font.HELVETICA;

        contentStream.beginText();
        contentStream.setFont(fontBold, 18);
        contentStream.newLineAtOffset(800, 1600);
        contentStream.showText("BOND Interest Trial Run – Eligible Accounts Report");
        contentStream.endText();
        contentStream.close();

        float margin = 30;
        float yStart = PDRectangle.A1.getHeight() - 100;
        float tableWidth = PDRectangle.A1.getWidth() - (2 * margin);
        boolean drawLines = true;
        boolean drawContent = true;

        // ========== SMALL HEADER TABLE (Bank/Branch Info) ==========
        BaseTable infoTable = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, drawLines, drawContent);

        be.quodlibet.boxable.Row<PDPage> row = infoTable.createRow(25);
        row.createCell(30, "Bank Name");
        row.createCell(70, bankName);

        row = infoTable.createRow(25);
        row.createCell(30, "Branch Name");
        row.createCell(70, headerDto.getBranchName());

        row = infoTable.createRow(25);
        row.createCell(30, "Branch Id");
        row.createCell(70, UserAccountUtils.getCurrentSolId());

        infoTable.draw();

        // ========== LARGE REPORT TABLE ==========
        yStart = infoTable.draw() - 20;

        BaseTable table = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, drawLines, drawContent);

        be.quodlibet.boxable.Row<PDPage> header = table.createRow(28);
        String[] colNames = {
                "S.No", "Branch Id", "Bond Ledger Account Number", "Customer Name", "Bond Subscription ID",
                "Subscription Amount", "Subscription Date", "Interest From Period", "Interest To Period",
                "Interest payment mode", "Credit Account Number", "Total Interest Rs", "TDS Amount Rs",
                "Arrear TDS Amount Rs", "Net Interest Amount Rs"
        };

        for (String col : colNames) {
            Cell<PDPage> cell = header.createCell(100f / colNames.length, col);
            cell.setFont(fontBold);
            cell.setAlign(HorizontalAlignment.CENTER);
            cell.setValign(VerticalAlignment.MIDDLE);
        }

        int index = 1;
        for (RbiBondTrailRunReportDto dto : dataList) {

            totIntAmt += dto.getInterestAmount() == null ? 0 : Double.parseDouble(dto.getInterestAmount());
            totTds += dto.getCurrentTdsAmount() == null ? 0 : Double.parseDouble(dto.getCurrentTdsAmount());
            totArr += dto.getArrearTdsAmount() == null ? 0 : Double.parseDouble(dto.getArrearTdsAmount());
            totNet += dto.getNetInterestAmount() == null ? 0 : Double.parseDouble(dto.getNetInterestAmount());

            be.quodlibet.boxable.Row<PDPage> r = table.createRow(22);
            r.createCell(100f / colNames.length, String.valueOf(index++));
            r.createCell(100f / colNames.length, dto.getBranchId());
            r.createCell(100f / colNames.length, dto.getBondLedgerAccountNumber());
            r.createCell(100f / colNames.length, dto.getBondHolderName());
            r.createCell(100f / colNames.length, dto.getBondSubscriptionId());
            r.createCell(100f / colNames.length, dto.getSubscriptionAmount());
            r.createCell(100f / colNames.length, dto.getAccountOpenDate());
            r.createCell(100f / colNames.length, dto.getInterestPeriodFrom());
            r.createCell(100f / colNames.length, dto.getInterestPeriodTo());
            r.createCell(100f / colNames.length, dto.getModeOfInterestPayment());
            r.createCell(100f / colNames.length, dto.getCreditAccountNumber());
            r.createCell(100f / colNames.length, dto.getInterestAmount());
            r.createCell(100f / colNames.length, dto.getCurrentTdsAmount());
            r.createCell(100f / colNames.length, dto.getArrearTdsAmount());
            r.createCell(100f / colNames.length, dto.getNetInterestAmount());
        }

        // TOTAL ROW
        be.quodlibet.boxable.Row<PDPage> total = table.createRow(28);
        total.createCell(30, "Total");
        total.createCell(70, "");
        total.createCell(70, "");
        total.createCell(70, "");
        total.createCell(70, "");
        total.createCell(70, "");
        total.createCell(70, "");
        total.createCell(70, "");
        total.createCell(70, "");
        total.createCell(70, "");
        total.createCell(70, "");
        total.createCell(70, String.valueOf(totIntAmt));
        total.createCell(70, String.valueOf(totTds));
        total.createCell(70, String.valueOf(totArr));
        total.createCell(70, String.valueOf(totNet));

        table.draw();

        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

}
