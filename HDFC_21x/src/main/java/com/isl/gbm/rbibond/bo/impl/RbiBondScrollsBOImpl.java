package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondScrollsBO;
import com.isl.gbm.rbibond.dao.RbiBondScrollsDAO;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import com.isl.gbm.rbibond.dto.reports.CallBackReportDTO;
import com.isl.gbm.rbibond.dto.scrolls.*;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Component
public class RbiBondScrollsBOImpl implements RbiBondScrollsBO {

    @Autowired
    RbiBondScrollsDAO rbiBondScrollsDAO;

    @Override
    public ResponseDataDTO getBondPrincipalRepay(BondPrincipalRepayDTO bondPrincipalRepayDTO) {
        return rbiBondScrollsDAO.getBondPrincipalRepay(bondPrincipalRepayDTO);
    }

    @Override
    public ResponseDataDTO getBondInterestRepay(BondInterestRepayDTO bondInterestRepayDTO) {
        return rbiBondScrollsDAO.getBondInterestRepay(bondInterestRepayDTO);
    }

    @Override
    public ResponseDataDTO getBondRegisterLedgerAcc(BondRegisterLedgerAccDTO bondRegisterLedgerAccDTO) {
        return rbiBondScrollsDAO.getBondRegisterLedgerAcc(bondRegisterLedgerAccDTO);
    }

    @Override
    public ResponseDataDTO getBondRegLedgerLinkCell(BondRegisterLedgerLinkDTO bondRegisterLedgerLinkDTO) {
        return rbiBondScrollsDAO.getBondRegLedgerLinkCell(bondRegisterLedgerLinkDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageHandlingReport(BondBrokerageHandleDTO bondBrokerageHandleDTO) {
        return rbiBondScrollsDAO.getBrokerageHandlingReport(bondBrokerageHandleDTO);
    }

    @Override
    public ResponseDataDTO statementOfBondsDetails(StatementofBondsIssuedDTO statementofBondsIssuedDTO) {
        return rbiBondScrollsDAO.statementOfBondsDetails(statementofBondsIssuedDTO);
    }

    @Override
    public ResponseDataDTO statementOfChargesServicingDetails(SatatementOfChargesBlaDTO satatementOfChargesBlaDTO) {
        return rbiBondScrollsDAO.statementOfChargesServicingDetails(satatementOfChargesBlaDTO);
    }

    @Override
    public ResponseDataDTO statementOfChargesTurnoverCommissionDetails(RbiBondTurnoverCommissionDTO rbiBondTurnoverCommissionDTO) {
        return rbiBondScrollsDAO.statementOfChargesTurnoverCommissionDetails(rbiBondTurnoverCommissionDTO);
    }


    @Override
    public ResponseDataDTO principleScrollreportAppendixXII(PrincipleScrollreportAppendixXIIDto scrollreportAppendixXIIDto) {
        return rbiBondScrollsDAO.principleScrollreportAppendixXII(scrollreportAppendixXIIDto);
    }


    @Override
    public ResponseDataDTO estimateOfExpenses(EstimateOfExpensesDto estimateOfExpensesDto) {
        return rbiBondScrollsDAO.estimateOfExpenses(estimateOfExpensesDto);
    }

    @Override
    public ResponseDataDTO getSubsTextFileDetails(SusbscriptionTextFileDto susbscriptionTextFileDto) {
        return rbiBondScrollsDAO.getSubsTextFileDetails(susbscriptionTextFileDto);
    }

    @Override
    public ResponseDataDTO getRedemptionTextFileDetails(RedemptionTextFileDto redemptionTextFileDto) {
        return rbiBondScrollsDAO.getRedemptionTextFileDetails(redemptionTextFileDto);
    }

    @Override
    public ResponseDataDTO getMonthBrokerageDetails(MonthlyBrokerageClaimDto monthlyBrokerageClaimDto) {
        return rbiBondScrollsDAO.getMonthBrokerageDetails(monthlyBrokerageClaimDto);
    }

    @Override
    public ResponseDataDTO getInterestScrollReport5106Details(InterestScrollReportDTO interestScrollReportDTO) {
        return rbiBondScrollsDAO.getInterestScrollReport5106Details(interestScrollReportDTO);
    }

    @Override
    public ResponseDataDTO getAppendixDetails(AppendixFourMarchDTO appendixFourMarchDTO) {
        return rbiBondScrollsDAO.getAppendixDetails(appendixFourMarchDTO);
    }

    @Override
    public ResponseDataDTO getEfillingDetails(EfillingDTO efillingDTO) {
        return rbiBondScrollsDAO.getEfillingDetails(efillingDTO);
    }



    @Override
    public ResponseDataDTO getEfillingDetailsDownload(EfillingDTO efillingDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondScrollsDAO.getEfillingDetails(efillingDTO), efillingDTO)));
            fileResponseDto.setFileName("E-Filling.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, EfillingDTO efillingDTO ) {
        List<EfillingDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
        String solDesc= Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("E-Filling");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setWrapText(true);
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setWrapText(true);
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "Serial.No",
                    "Name of the declarant",
                    "Permanent Account Number",
                    "Unique Identification No.",
                    "Date Of Birth/Incorporation",
                    "Country/Region",
                    "Please Specify",
                    "Flat/Door/Building",
                    "Road/Street/Block/Sector",
                    "PIN Code / ZIP Code",
                    "Post Office",
                    "Area/ Locality",
                    "District",
                    "State",
                    "Email id",
                    "Contact number",
                    "Estimated income for which this declaration is made",
                    "Estimated total income of the tax year of the declarant",
                    "Aggregate amount of income for which declaration is made during the tax year",
                    "Date on which declaration is received"

            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setWrapText(true);
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            // Track maximum column widths
            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<EfillingDTO  >) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (EfillingDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getAssesseeName()!=null?data.getAssesseeName():"",
                            data.getAssesseePan()!=null?data.getAssesseePan():"",
                            data.getUniqueIdNum()!=null?data.getUniqueIdNum():"",
                            data.getDOB()!=null?data.getDOB():"",
                            data.getCountry()!=null?data.getCountry():"",
                            data.getPleaseSpecify()!=null?data.getPleaseSpecify():"",
                            data.getFlatDoorBlockNum()!=null?data.getFlatDoorBlockNum():"",
                            data.getRoadStreetLane()!=null?data.getRoadStreetLane():"",
                            data.getPin()!=null?data.getPin():"",
                            data.getPostOffice()!=null?data.getPostOffice():"",
                            data.getAreaLocality()!=null?data.getAreaLocality():"",
                            data.getTownCityDistrict()!=null?data.getTownCityDistrict():"",
                            data.getState()!=null?data.getState():"",
                            data.getEmail()!=null?data.getEmail():"",
                            data.getMobileNum()!=null?data.getMobileNum():"",
                            data.getEstimatedIncomeForWhichDeclarationIsMade()!=null?data.getEstimatedIncomeForWhichDeclarationIsMade():"",
                            data.getEstimatedTotalIncomeOfThePreviousYear()!=null?data.getEstimatedTotalIncomeOfThePreviousYear():"",
                            data.getAggregateAmountOfIncomeForm15GFilled()!=null?data.getAggregateAmountOfIncomeForm15GFilled():"",
                            data.getDeclarationReceivedDate()!=null?data.getDeclarationReceivedDate():"",

                    };


                    //log.info("cellValues.length: "+cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            }

            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, getSafeColumnWidth(maxColumnWidths[i]));
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }




    private int getSafeColumnWidth(int charLength) {
        int MAX_EXCEL_COLUMN_WIDTH = 255 * 256;
        return Math.min(charLength * 256, MAX_EXCEL_COLUMN_WIDTH);
    }


    @Override
    public ResponseDataDTO getSft(SftScrollDTO sftScrollDTO) {
        return rbiBondScrollsDAO.getSft(sftScrollDTO);
    }

    @Override
    public ResponseDataDTO getAIRDetails(AirDTO airDTO) {
        return rbiBondScrollsDAO.getAIRDetails(airDTO);
    }

    @Override
    public ResponseDataDTO getAppendixIV805Details(AppendixIV805DTO appendixIV805DTO) {
        return rbiBondScrollsDAO.getAppendixIV805Details(appendixIV805DTO);
    }

    @Override
    public ResponseDataDTO getAnnualReport(AnnualReportDto annualReportDto) {
        return rbiBondScrollsDAO.getAnnualReport(annualReportDto);
    }

    @Override
    public ResponseDataDTO getAppendixIV8Details(AppendixIV8DTO appendixIV8DTO) {
        return rbiBondScrollsDAO.getAppendixIV8Details(appendixIV8DTO);
    }

    @Override
    public ResponseDataDTO getAppendixIV85Details(AppendixIV85DTO appendixIV85DTO) {
        return rbiBondScrollsDAO.getAppendixIV85Details(appendixIV85DTO);
    }

    @Override
    public ResponseDataDTO getAppendix65Details(AppendixFourMar65DTO apendixFourMar65DTO){

        return rbiBondScrollsDAO.getAppendix65Details(apendixFourMar65DTO);
    }

    @Override
    public ResponseDataDTO getsubScriptionMandateDetails(SubscriptionMandateDTO subscriptionMandateDTO){

        return rbiBondScrollsDAO.getsubScriptionMandateDetails(subscriptionMandateDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageClaimHandlingCommissionDetails(BrokerageClaimAndHandlingCommissionDTO brokerageClaimDTO){
        return rbiBondScrollsDAO.getBrokerageClaimHandlingCommissionDetails(brokerageClaimDTO);
    }

    @Override
    public ResponseDataDTO getSchemeWiseOutstandingBalanceDetails(SchemeWiseOutstandingBalanceDTO schemeWiseOutstandingBalanceDTO){
        return rbiBondScrollsDAO.getSchemeWiseOutstandingBalanceDetails(schemeWiseOutstandingBalanceDTO);
    }

    @Override
    public ResponseDataDTO getHalfYearlyInterestScrollDetails(HalfYearlyInterestScrollDTO halfYearlyInterestScrollDTO) {
        return rbiBondScrollsDAO.getHalfYearlyInterestScrollDetails(halfYearlyInterestScrollDTO);
    }
}
