package com.isl.gbm.rbibond.bo.impl;

import be.quodlibet.boxable.BaseTable;
import com.isl.gbm.common.dto.BodEodStatusInqDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondReportsBO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.dto.reports.BrokerageClaimMonthlyDTO;
import com.isl.gbm.rbibond.dto.reports.TDSRemittanceFinconFiledDTO;
import com.isl.gbm.rbibond.dao.RbiBondCommonServiceDAO;
import com.isl.gbm.rbibond.dao.RbiBondReportsDAO;
import com.isl.gbm.rbibond.dto.reports.*;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class RbiBondReportsBOImpl implements RbiBondReportsBO {

    private static final Logger log = LoggerFactory.getLogger(RbiBondReportsBOImpl.class);

    @Autowired
    RbiBondReportsDAO rbiBondReportsDAO;

    @Value("${gbm.bankName}")
    private String bankName;

    private static final Map<String, String> genderMap = new HashMap<>();
    static {
        genderMap.put("M", "Male");
        genderMap.put("F", "Female");
        genderMap.put("O", "Others");
    }
    private static final Map<String, String> holderTypeMap = new HashMap<>();

    static {
        holderTypeMap.put("HT001", "Power of Attorney");
        holderTypeMap.put("HT002", "Karta of HUF");
        holderTypeMap.put("HT003", "Individual");
    }

    public static String getApplicantStatus(String applStatus) {
        if (applStatus == null) {
            return "";
        }
        return holderTypeMap.getOrDefault(applStatus, applStatus);
    }
    @Autowired
    private RbiBondCommonServiceDAO rbiBondCommonServiceDAO;


    @Override
    public ResponseDataDTO getBondAckNominiReport(BondAckNominationDTO bondAckNominationDTO) {
        return rbiBondReportsDAO.getBondAckNominiReport(bondAckNominationDTO);
    }

    @Override
    public ResponseDataDTO getminorToMajorIntimationRepValues(RbiBondMinorToMajorIntimateRepDTO rbiBondMinorToMajorIntimateRepDTO) {
        return rbiBondReportsDAO.getminorToMajorIntimationRepValues(rbiBondMinorToMajorIntimateRepDTO);
    }


    @Override
    public ResponseDataDTO getBondInterestPayMisReportValues(RbiBondInterestPaymentMisReportDto rbiBondInterestPaymentMisReportDto) {
        return rbiBondReportsDAO.getBondInterestPayMisReportValues(rbiBondInterestPaymentMisReportDto);
    }

    @Override
    public ResponseDataDTO getBondNominiRegsReport(BondNominiRegsReportDTO bondNominiRegsReportDTO) {
        return rbiBondReportsDAO.getBondNominiRegsReport(bondNominiRegsReportDTO);
    }

    @Override
    public ResponseDataDTO getBondNominationDownload(BondNominiRegsReportDTO bondNominiRegsReportDTO) {

        if (bondNominiRegsReportDTO.getDownloadType().equalsIgnoreCase("csv") || bondNominiRegsReportDTO.getDownloadType().equalsIgnoreCase("pdf")) {


            return rbiBondReportsDAO.getBondNominiRegsReport(bondNominiRegsReportDTO);

        } else {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getBondNominiRegsReport(bondNominiRegsReportDTO), bondNominiRegsReportDTO)));
                fileResponseDto.setFileName("BOND_NOMINATION_REGISTER_REPORT.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in BondNominiRegsReportDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;

        }
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, BondNominiRegsReportDTO bondNominiRegsReportDTO) {
        List<BondNominiRegsReportDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BOND NOMINATION REGISTER REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
//            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));

            // === Primary header row (Row 2) ===
            SXSSFRow primaryHeaderRow = sheet.createRow(2);
            primaryHeaderRow.createCell(0).setCellValue(""); // "S.No." not merged

            primaryHeaderRow.createCell(1).setCellValue("PARTICULARS OF INVESTMENT");
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3)); // Merge for Date of Issue, Amount, Date of Repayment

            primaryHeaderRow.createCell(4).setCellValue("PARTICULARS OF NOMINEE");
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 9)); // Merge for Nominee Name to Bank Account

// === Secondary header row (Row 3) ===
            SXSSFRow headerRow = sheet.createRow(3); // Use row 3 instead of 2

            String[] headers = {
                    "S.No.",
                    "Date of Issue",
                    "Amount (Rs.)",
                    "Date of Repayment",
                    "Nominee Name",
                    "Nominee Address",
                    "Date of Birth",
                    "Relationship to holder",
                    "STATUS Resident/NRI",
                    "Particulars of Bank Account"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BondNominiRegsReportDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 4;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondNominiRegsReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getDateOfIssue() != null ? data.getDateOfIssue() : "",
                            data.getAmount() != null ? data.getAmount() : "",
                            data.getDateOfRepayment() != null ? data.getDateOfRepayment() : "",
                            data.getNomineeName() != null ? data.getNomineeName() : "",
                            data.getNomineeAddress() != null ? data.getNomineeAddress() : "",
                            data.getDob() != null ? data.getDob() : "",
                            data.getRelationshipHolder() != null ? data.getRelationshipHolder() : "",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getParticularsOfBankAccount() != null ? data.getParticularsOfBankAccount() : ""
                    };


                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
//        List<BondNominiRegsReportDTO> list = Collections.emptyList();
//        HashMap<String,Object> finalData;
//        String loginSolid = UserAccountUtils.getCurrentSolId();
//       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
//        ByteArrayOutputStream byteArrayOutputStream = null;
//        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
//            SXSSFSheet sheet = workbook.createSheet("sheet1");
//            sheet.setRandomAccessWindowSize(100);
//
//            byteArrayOutputStream = new ByteArrayOutputStream();
//
//            // Create title row
//            SXSSFRow titleRow = sheet.createRow(0);
//            SXSSFCell titleCell = titleRow.createCell(0);
//            titleCell.setCellValue("BOND NOMINATION REGISTER REPORT");
//
//            // Create and set title cell style
//            CellStyle titleCellStyle = workbook.createCellStyle();
//            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
//            titleCell.setCellStyle(titleCellStyle);
//
//            // Merge cells for title
//            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));
//
//            // Create bank information row
//            SXSSFRow bankInfoRow = sheet.createRow(1);
//            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
//            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);
//
//            // Create and set bank info cell style
//            CellStyle bankInfoCellStyle = workbook.createCellStyle();
//            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
//            bankInfoCell.setCellStyle(bankInfoCellStyle);
//
//            // Merge cells for bank information
//            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));
//
//            // === Primary header row (Row 2) ===
//            SXSSFRow primaryHeaderRow = sheet.createRow(2);
//            primaryHeaderRow.createCell(0).setCellValue(""); // "S.No." not merged
//
//            primaryHeaderRow.createCell(1).setCellValue("PARTICULARS OF INVESTMENT");
//            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3)); // Merge for Date of Issue, Amount, Date of Repayment
//
//            primaryHeaderRow.createCell(4).setCellValue("PARTICULARS OF NOMINEE");
//            sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 9)); // Merge for Nominee Name to Status
//
//            // === Secondary header row (Row 3) ===
//            SXSSFRow headerRow = sheet.createRow(3);
//            String[] headers = {
//                    "S.No.",
//                    "Date of Issue",
//                    "Amount (Rs.)",
//                    "Date of Repayment",
//                    "Nominee Name",
//                    "Nominee Address",
//                    "Date of Birth",
//                    "Relationship to holder",
//                    "STATUS Resident/NRI",
//                    "Particulars of Bank Account"
//            };
//
//            CellStyle headerCellStyle = workbook.createCellStyle();
//            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
//            font.setBold(true);
//            headerCellStyle.setFont(font);
//
//            // Track maximum column widths
//            int[] maxColumnWidths = new int[headers.length];
//            for (int i = 0; i < headers.length; i++) {
//                SXSSFCell cell = headerRow.createCell(i);
//                cell.setCellValue(headers[i]);
//                cell.setCellStyle(headerCellStyle);
//                maxColumnWidths[i] = headers[i].length();
//            }
//
//            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
//            list = (List<BondNominiRegsReportDTO>) finalData.get("list");
//
//            // Populate data rows
//            int rowNumber = 4; // Data now starts from row 4
//            int sNo = 1;
//            if (!list.isEmpty()) {
//                for (BondNominiRegsReportDTO data : list) {
//                    SXSSFRow row = sheet.createRow(rowNumber++);
//                    int cellNumber = 0;
//
//                    String[] cellValues = {
//                            String.valueOf(sNo++),
//                            data.getDateOfIssue() != null ? data.getDateOfIssue() : "",
//                            data.getAmount() != null ? data.getAmount() : "",
//                            data.getDateOfRepayment() != null ? data.getDateOfRepayment() : "",
//                            data.getNomineeName() != null ? data.getNomineeName() : "",
//                            data.getNomineeAddress() != null ? data.getNomineeAddress() : "",
//                            data.getDob() != null ? data.getDob() : "",
//                            data.getRelationshipHolder() != null ? data.getRelationshipHolder() : "",
//                            data.getStatus() != null ? data.getStatus() : "",
//                            data.getParticularsOfBankAccount() != null ? data.getParticularsOfBankAccount() : ""
//                    };
//
//                    log.info("cellValues.length: " + cellValues.length);
//                    for (int i = 0; i < cellValues.length; i++) {
//                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
//                        //log.info(cellValues[i]);
//                        if (cellValues[i].length() > maxColumnWidths[i]) {
//                            maxColumnWidths[i] = cellValues[i].length() + 5;
//                        }
//                    }
//                }
//            } else {
//                log.info("REPORT OBJECT HAVING EMPTY DATA ");
//            }
//            log.info("REPORT DATA GENERATION COMPLETED...");
//            log.info("Processed Data rows: " + rowNumber);
//            // Set column widths
//            for (int i = 0; i < maxColumnWidths.length; i++) {
//                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
//            }
//
//            workbook.write(byteArrayOutputStream);
//            byteArrayOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error("Exception while writing the Excel file: " + e.getMessage());
//        }
//        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getNoticeCancelReport(BondNoticeCancelReportDTO bondNoticeCancelReportDTO) {
        return rbiBondReportsDAO.getNoticeCancelReport(bondNoticeCancelReportDTO);
    }

    @Override
    public ResponseDataDTO getAckCancelNomineReport(BondCancelNomineeReportDTO bondCancelNomineeReportDTO) {
        return rbiBondReportsDAO.getAckCancelNomineReport(bondCancelNomineeReportDTO);
    }

    @Override
    public ResponseDataDTO getCertificateInvtReport(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsDAO.getCertificateInvtReport(bondCertificateInvtReportDTO);
    }

    @Override
    public ResponseDataDTO getPanValidationBondSchemeReport(PANValidationReportDTO panValidationReportDTO) {
        return rbiBondReportsDAO.getPanValidationBondSchemeReport(panValidationReportDTO);
    }



    @Override
    public ResponseDataDTO getPreMaturityMarkingReport(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        return rbiBondReportsDAO.getPreMaturityMarkingReport(preMaturityMarkingReportDTO);
    }

    @Override
    public ResponseDataDTO getPanNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsDAO.getPanNo(bondCertificateInvtReportDTO);
    }

    @Override
    public ResponseDataDTO getBlaAcNo(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsDAO.getBlaAcNo(bondCertificateInvtReportDTO);
    }

    @Override
    public ResponseDataDTO getSubscripID(BondCertificateInvtReportDTO bondCertificateInvtReportDTO) {
        return rbiBondReportsDAO.getSubscripID(bondCertificateInvtReportDTO);
    }

    @Override
    public ResponseDataDTO getCertificateOfHolding(BondCertificateInvtHoldReportDTO bondCertificateInvtHoldReportDTO) {
        return rbiBondReportsDAO.getCertificateOfHolding(bondCertificateInvtHoldReportDTO);
    }

    @Override
    public ResponseDataDTO getTranSameBankReport(BondTransSameBankReportDTO bondTransSameBankReportDTO) {
        return rbiBondReportsDAO.getTranSameBankReport(bondTransSameBankReportDTO);
    }

    @Override
    public ResponseDataDTO getListOfBondReport(ListOfBondInvestmentsDTO listOfBondInvestmentsDTO) {
        return rbiBondReportsDAO.getListOfBondReport(listOfBondInvestmentsDTO);
    }

    @Override
    public ResponseDataDTO getListOfBondReportDownload(ListOfBondInvestmentsDTO listOfBondInvestmentsDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getListOfBondReport(listOfBondInvestmentsDTO), listOfBondInvestmentsDTO)));
            fileResponseDto.setFileName("BOND_INVESTMENTS_PENDING_RECEIPT_OF_CAS_ADVICE_NO._REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in ListOfBondInvestmentsDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, ListOfBondInvestmentsDTO listOfBondInvestmentsDTO) {
        List<ListOfBondInvestmentsDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();

            // Title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("LIST OF BOND INVESTMENTS PENDING RECEIPT OF CAS ADVICE NO. REPORT");

            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Bank info row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Header row
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "BLA NO.",
                    "BOND SCHEME",
                    "SUBSCRIPTION DATE",
                    "SUBSCRIPTION AMOUNT"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<ListOfBondInvestmentsDTO>) finalData.get("listOfBondInvestmentsDTOList");

            int rowNumber = 3;
            int sNo = 1;
            double totalSubscriptionAmount = 0;

            if (!list.isEmpty()) {
                for (ListOfBondInvestmentsDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getBlaNumber() != null ? data.getBlaNumber() : "",
                            data.getSchemeName() != null ? data.getSchemeName() : "",
                            data.getSubsDate() != null ? data.getSubsDate() : "",
                            data.getSubsAmount() != null ? data.getSubsAmount() : ""
                    };

                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber).setCellValue(cellValues[i]);
                        if (i < maxColumnWidths.length && cellValues[i] != null) {
                            if (cellValues[i].length() > maxColumnWidths[i]) {
                                maxColumnWidths[i] = cellValues[i].length() + 5;
                            }
                        }
                        cellNumber++;
                    }

                    // Accumulate subscription amount
                    try {
                        if (data.getSubsAmount() != null && !data.getSubsAmount().isEmpty()) {
                            totalSubscriptionAmount += Double.parseDouble(data.getSubsAmount());
                        }
                    } catch (NumberFormatException e) {
                        log.warn("Invalid subscription amount: " + data.getSubsAmount());
                    }
                }

                // Add "Total" label below "SUBSCRIPTION DATE"
                SXSSFRow totalRow = sheet.createRow(rowNumber);
                totalRow.createCell(3).setCellValue("Total");

                // Add sum below "SUBSCRIPTION AMOUNT"
                totalRow.createCell(4).setCellValue(String.format("%.2f", totalSubscriptionAmount));

            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA");
            }

            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }

        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getBlaStatementReport(BlaStatementForBondHoldersDTO blaStatementForBondHoldersDTO) {

          if(blaStatementForBondHoldersDTO.getDetailsFlag() != null&&blaStatementForBondHoldersDTO.getDetailsFlag().equalsIgnoreCase("D")) {
              return rbiBondReportsDAO.getBlaStatementReport(blaStatementForBondHoldersDTO);
          }

        else if(blaStatementForBondHoldersDTO.getDownloadType()!=null&&blaStatementForBondHoldersDTO.getDownloadType().equalsIgnoreCase("csv")) {
            return rbiBondReportsDAO.getBlaStatementReport(blaStatementForBondHoldersDTO);
        }



        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateBlaStatementReportExcel(rbiBondReportsDAO.getBlaStatementReport(blaStatementForBondHoldersDTO), blaStatementForBondHoldersDTO)));
            fileResponseDto.setFileName("BLA_STATEMENTF_FOR_BOND_HOLDERS_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in BlaStatementForBondHoldersDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateBlaStatementReportExcel(ResponseDataDTO responseDataDTO, BlaStatementForBondHoldersDTO blaStatementForBondHoldersDTO) {
        List<BlaStatementForBondHoldersDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();

            // Title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BLA Statement For BondHolders Report");

            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Bank info row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name :  HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));



            // Bank info row
            SXSSFRow nameOfBandSchmeRow = sheet.createRow(2);
            SXSSFCell nameOfBandSchmecell = nameOfBandSchmeRow.createCell(0);
            nameOfBandSchmecell.setCellValue("Name of the Bond Scheme: "+blaStatementForBondHoldersDTO.getSchmeName());
            CellStyle nameOfBondSChmeStyle = workbook.createCellStyle();
            nameOfBondSChmeStyle.setAlignment(HorizontalAlignment.CENTER);
            nameOfBandSchmecell.setCellStyle(nameOfBondSChmeStyle);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));

            // Bank info row
            SXSSFRow fromToDateRow = sheet.createRow(3);
            SXSSFCell fromToDateCell = fromToDateRow.createCell(0);
            fromToDateCell.setCellValue("From Date: " + blaStatementForBondHoldersDTO.getFromDate() +      "        ToDate: " +blaStatementForBondHoldersDTO.getToDate() );
            CellStyle fromDateToDateStyle = workbook.createCellStyle();
            fromDateToDateStyle.setAlignment(HorizontalAlignment.CENTER);
            fromToDateCell.setCellStyle(fromDateToDateStyle);
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 7));

            // Header row


            SXSSFRow headerRow = sheet.createRow(4);
            String[] headers = {


                    "S.No",
                    "Customer Name",
                    "Address",
                    "Pan No",
                    "Bla No",
                    "Date of Subscription",
                    "Subscription ID",
                    "Bond Type",
                    "Maturity Date",
                    "Subscription Amount Rs.",
                    "Subscription Status"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<BlaStatementForBondHoldersDTO>) finalData.get("list");

            int rowNumber = 5;
            int sNo = 1;
            double totalSubscriptionAmount = 0;

            if (!list.isEmpty()) {
                for (BlaStatementForBondHoldersDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getBondholdername() != null ? data.getBondholdername() : "",
                            data.getAddress() != null ? data.getAddress() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getBlaNumber() != null ? data.getBlaNumber() : "",
                            data.getDateOfSubscription() != null ? data.getDateOfSubscription() : "",
                            data.getSubcriptionId() != null ? data.getSubcriptionId() : "",
                            data.getBondtype() != null ? data.getBondtype() : "",
                            data.getMaturitydate() != null ? data.getMaturitydate() : "",
                            data.getSubcriptionamount() != null ? data.getSubcriptionamount() : "",
                            data.getSubsStatus() != null ? data.getSubsStatus() : ""
                         //   tableData = vm.downloadFilteredRecords.map((record, index) => [index + 1, record.dateOfSubscription, record.subscriptionId, record.bondtype, record.maturitydate, record.subcriptionamount, record.subsStatus]);
                    };

                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber).setCellValue(cellValues[i]);
                        if (i < maxColumnWidths.length && cellValues[i] != null) {
                            if (cellValues[i].length() > maxColumnWidths[i]) {
                                maxColumnWidths[i] = cellValues[i].length() + 5;
                            }
                        }
                        cellNumber++;
                    }

                    // Accumulate subscription amount
                    try {
                        if (data.getSubcriptionamount() != null && !data.getSubcriptionamount().isEmpty()) {
                            totalSubscriptionAmount += Double.parseDouble(data.getSubcriptionamount());
                        }
                    } catch (NumberFormatException e) {
                        log.warn("Invalid subscription amount: " + data.getSubscriptionAmount());
                    }
                }

                // Add "Total" label below "SUBSCRIPTION DATE"
                SXSSFRow totalRow = sheet.createRow(rowNumber);
                totalRow.createCell(8).setCellValue("Total Sub Amount");

                // Add sum below "SUBSCRIPTION AMOUNT"
                totalRow.createCell(9).setCellValue(String.format("%.2f", totalSubscriptionAmount));

            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA");
            }

            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }

        return byteArrayOutputStream.toByteArray();






    }

    @Override
    public ResponseDataDTO bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO bondSubscriptionwithoutnominationDTO) {
        return rbiBondReportsDAO.bondSubscriptionwithoutnomination(bondSubscriptionwithoutnominationDTO);
    }

    @Override
    public ResponseDataDTO getTdsDeductReport(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) {
        return rbiBondReportsDAO.getTdsDeductReport(bondTdsDeductionReportDTO);
    }

    @Override
    public ResponseDataDTO getTdsDeductionReportCb(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) {
        return rbiBondReportsDAO.getTdsDeductionReportCb(bondTdsCollDeductionReportDTO);
    }

    @Override
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

    @Override
    public byte[] pdfFileGeneratorForTdsCb(List<Map<String, Object>> results, BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Header Title
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
        contentStream.newLineAtOffset(page.getMediaBox().getWidth() / 2 - 200, page.getMediaBox().getHeight() - 60);
        contentStream.showText("TDS Deduction – Report");
        contentStream.endText();
        contentStream.close();

        float margin = 40;
        float yStart = page.getMediaBox().getHeight() - 120;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawLines = true;
        boolean drawContent = true;


        // ---------- TABLE 1 (Bank / Branch / Sol ID Info) ----------
        BaseTable infoTable = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, drawLines, drawContent);

        be.quodlibet.boxable.Row<PDPage> row = infoTable.createRow(20);
        row.createCell(50, "Bank Name");
        row.createCell(50, bankName);

        be.quodlibet.boxable.Row<PDPage> row2 = infoTable.createRow(20);
        row2.createCell(50, "Branch Name");
        row2.createCell(50, bondTdsCollDeductionReportDTO.getBranchName());

        be.quodlibet.boxable.Row<PDPage> row3 = infoTable.createRow(20);
        row3.createCell(50, "Branch Id");
        row3.createCell(50, UserAccountUtils.getCurrentSolId());

        infoTable.draw();
        yStart = infoTable.draw() - 25;


        // ---------- TABLE 2 (Main Data Table) ----------
        BaseTable table = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, drawLines, drawContent);

        be.quodlibet.boxable.Row<PDPage> headerRow = table.createRow(22);
        String[] columns = {
                "S.No", "Branch Id", "Bond BLA No", "Bond Holder Name", "PAN",
                "Subscription ID", "Subscription Amount Rs", "Date of Deduction of TDS",
                "Interest Amount Rs", "TDS Amount Rs", "Net Amount Paid Rs"
        };

        for (String col : columns) {
            headerRow.createCell(100 / columns.length, col).setFont(PDType1Font.HELVETICA_BOLD);
        }
        table.addHeaderRow(headerRow);


        double totIntAmt = 0, totTds = 0, totNet = 0;
        int index = 1;

        for (Map<String, Object> item : bondTdsCollDeductionReportDTO.getData()) {

            double interest = parseDouble(item.get("INTEREST_AMT"));
            double tds = parseDouble(item.get("CURRENT_TDS_AMOUNT"));
            double net = parseDouble(item.get("NET_INTEREST_AMOUNT"));

            totIntAmt += interest;
            totTds += tds;
            totNet += net;

            be.quodlibet.boxable.Row<PDPage> dataRow = table.createRow(20);
            dataRow.createCell(5, String.valueOf(index++));
            dataRow.createCell(8, (String) item.get("BRANCH_ID"));
            dataRow.createCell(10, (String) item.get("BOND_LEDGER_ACCOUNT_NUMBER"));
            dataRow.createCell(12, (String) item.get("Holder_Name"));
            dataRow.createCell(8, (String) item.get("PAN_NO"));
            dataRow.createCell(8, (String) item.get("BOND_SUBSCRIPTION_ID"));
            dataRow.createCell(10, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            dataRow.createCell(12, (String) item.get("Date_of_Deduction_of_TDS"));
            dataRow.createCell(8, String.valueOf(interest));
            dataRow.createCell(8, String.valueOf(tds));
            dataRow.createCell(8, String.valueOf(net));
        }

        table.draw();


        // ---------- TOTAL ROW ----------
        yStart = table.draw() - 25;

        BaseTable totalTable = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, drawLines, drawContent);
        be.quodlibet.boxable.Row<PDPage> totalRow = totalTable.createRow(25);

        totalRow.createCell(60, "Total").setFont(PDType1Font.HELVETICA_BOLD);
        totalRow.createCell(10, "");
        totalRow.createCell(10, "");
        totalRow.createCell(10, String.valueOf(totIntAmt));
        totalRow.createCell(10, String.valueOf(totTds));
        totalRow.createCell(10, String.valueOf(totNet));

        totalTable.draw();

        document.save(baos);
        document.close();
        return baos.toByteArray();
    }

    @Override
    public ResponseDataDTO getTdsDeductDetails(BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException {
        return rbiBondReportsDAO.getTdsDeductDetails(bondTdsDeductionReportDTO);
    }


    @Override
    public ResponseDataDTO getUnverifiedReportRbi(UnverifiedReportDto unverifiedReportDto) {
        return rbiBondReportsDAO.getUnverifiedReportRbi(unverifiedReportDto);
    }


    @Override
    public ResponseDataDTO financialTransactionInquiry(FinancialTransactionInquiryDTO financialTransactionInquiryDTO) {
        if (financialTransactionInquiryDTO.getDownloadType().equalsIgnoreCase("excel")){


            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.financialTransactionInquiry(financialTransactionInquiryDTO), financialTransactionInquiryDTO)));
                fileResponseDto.setFileName("Financial Transaction Inquiry.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in FinancialTransactionInquiryDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;


    }

        else{
            return rbiBondReportsDAO.financialTransactionInquiry(financialTransactionInquiryDTO);
        }
    }


    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO,FinancialTransactionInquiryDTO financialTransactionInquiryDTO) {
        List<FinancialTransactionInquiryDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Financial Transaction Inquiry REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "BLA No",
                    "Bond Subscription ID",
                    "Broker ID",
                    "Tran Date",
                    "Tran Mode",
                    "Account Number",
                    "Tran ID (GBM)",
                    "Amount",
                    "Tran Status",
                    "Deleted",
                    "Tran Type",
                    "Tran ID",
                    "Amount (CBS)",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<FinancialTransactionInquiryDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (FinancialTransactionInquiryDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getBla() != null ? data.getBla() : "",
                            data.getSubscriptionID() != null ? data.getSubscriptionID() : "",
                            data.getBrokerId() != null ? data.getBrokerId() : "",
                            data.getTranDate() != null ? data.getTranDate() : "",
                            data.getTranMode() != null ? data.getTranMode() : "",
                            data.getAccountNumber() != null ? data.getAccountNumber() : "",
                            data.getGbmTranID() != null ? data.getGbmTranID() : "",
                            data.getGbmAmount() != null ? data.getGbmAmount() : "",
                            data.getTranStatus() != null ? data.getTranStatus() : "",
                            data.getDeleted() != null ? data.getDeleted() : "",
                            data.getTranType() != null ? data.getTranType() : "",
                            data.getCbsTranID() != null ? data.getCbsTranID() : "",
                            data.getCbsAmount() != null ? data.getCbsAmount() : "",
                    };


                    //log.info("cellValues.length: "+cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (i < maxColumnWidths.length && cellValues[i] != null) {
                            if (cellValues[i].length() > maxColumnWidths[i]) {
                                maxColumnWidths[i] = cellValues[i].length() + 5;
                            }
                        }
                    }
                }
            } else {
                log.info("Financial Transaction Inquiry");
            }
            log.info("Financial Transaction Inquiry...");
            log.info("Processed Data rows: " + rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getBlaStatusReport(BlaStatusReportDTO blaStatusReportDTO) {
        return rbiBondReportsDAO.getBlaStatusReport(blaStatusReportDTO);
    }


    @Override
    public ResponseDataDTO getBranchDetails() {
        return rbiBondReportsDAO.getBranchDetails();
    }

    @Override
    public ResponseDataDTO getInterestRunCreditReport(BondInterestRunCreditDTO bondInterestRunCreditDTO) {
        return rbiBondReportsDAO.getInterestRunCreditReport(bondInterestRunCreditDTO);
    }

    @Override
    public ResponseDataDTO getBondLedgerAccDet(BondLedgerAccDto bondLedgerAccDto) {
//        if (bondLedgerAccDto.getSchemeCode().isEmpty()) {
//            return rbiBondReportsDAO.getBondSchemeBondLedgerAccDet(bondLedgerAccDto);
//        } else {
        return rbiBondReportsDAO.getBondLedgerAccDet(bondLedgerAccDto);
//        }
    }

    @Override
    public ResponseDataDTO getRegBondLedgeAccDownload(BondLedgerAccDto bondLedgerAccDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getBondLedgerAccDet(bondLedgerAccDto), bondLedgerAccDto)));
            fileResponseDto.setFileName("REGISTER_OF_BOND_LEDGER_ACCOUNTS_INDIVIDUAL_ACCOUNTS_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in BondLedgerAccDto, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, BondLedgerAccDto bondLedgerAccDto) {
        List<BondLedgerAccDto> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("REGISTER OF BOND LEDGER ACCOUNTS - INDIVIDUAL ACCOUNT REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "Date Of Transaction",
                    "Amount of Subscription Investment Value",
                    "Amount of Subscription Maturity Value",
                    "CAS Advice No. & Date",
                    "Initials",
                    "Transfer",
                    "Transfer Sale or Purchase @",
                    "Transfer Investment Amount",
                    "Transfer Maturity Value",
                    "Due Date of Repayment",
                    "Repayment Investment Amount",
                    "Repayment Maturity Value",
                    "Balance (Investment Amt)",
                    "Remarks",
                    "Initials",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BondLedgerAccDto>) finalData.get("bondLedgerAccDtoList");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondLedgerAccDto data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getDateOfTransaction() != null ? data.getDateOfTransaction() : "",
                            data.getAmtOfSubs() != null ? data.getAmtOfSubs() : "",
                            data.getMaturityVal() != null ? data.getMaturityVal() : "",
                            data.getCasAdviceNoDate() != null ? data.getCasAdviceNoDate() : "",
                            data.getSubsId() != null ? data.getSubsId() : "",
                            data.getTransferSaleBurchase() != null ? data.getTransferSaleBurchase() : "",
                            data.getTransferNominalValue() != null ? data.getTransferNominalValue() : "",
                            data.getTransferNominalAmt() != null ? data.getTransferNominalAmt() : "",
                            data.getDueDateOfRepayment() != null ? data.getDueDateOfRepayment() : "",
                            data.getRepaymntNominalAmt() != null ? data.getRepaymntNominalAmt() : "",
                            data.getRepaymntMaturityValue() != null ? data.getRepaymntMaturityValue() : "",
                            data.getRemarks() != null ? data.getRemarks() : "",
                            data.getInitials() != null ? data.getInitials() : "",
                    };


                    //log.info("cellValues.length: "+cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (i < maxColumnWidths.length && cellValues[i] != null) {
                            if (cellValues[i].length() > maxColumnWidths[i]) {
                                maxColumnWidths[i] = cellValues[i].length() + 5;
                            }
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public byte[] csvFileGeneratorForInterestRunPendingBrokerageReport(BondLedgerAccDto bondLedgerAccDto) throws IOException {
        return new byte[0];
    }


    @Override
    public byte[] pipeSeparatedFileGeneratorForInterestRunPendingBrokerageReport(BondLedgerAccDto bondLedgerAccDto) throws IOException {
        return new byte[0];
    }

    @Override
    public byte[] excelFileGeneratorForInterestRunPendingBrokerageReport(BondLedgerAccDto bondLedgerAccDto) throws IOException {
        return new byte[0];
    }

    @Override
    public byte[] excelFileGeneratorForBondLedgerAccountReport(BondLedgerAccDto bondLedgerAccDto) throws IOException {
        return null;
    }


    @Override
    public byte[] pdfFileGeneratorForInterestRunPendingBrokerageReport(BondLedgerAccDto bondLedgerAccDto) throws IOException {
        return new byte[0];
    }
//    excelFileGeneratorForInterestRunReport

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

    @Override
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

    @Override
    public byte[] pdfFileGeneratorForInterestRunReport(BondInterestRunCreditDTO bondInterestRunCreditDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Header Title
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18);
        contentStream.newLineAtOffset(page.getMediaBox().getWidth() / 2 - 250, page.getMediaBox().getHeight() - 60);
        contentStream.showText("Bond Actual Interest Run Credited Report – Centralized");
        contentStream.endText();
        contentStream.close();

        float margin = 40;
        float yStart = page.getMediaBox().getHeight() - 120;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        boolean drawLines = true;
        boolean drawContent = true;

        // ---------- TABLE 1 (Bank / Branch / Scheme Info) ----------
        BaseTable infoTable = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, drawLines, drawContent);

        be.quodlibet.boxable.Row<PDPage> row = infoTable.createRow(20);
        row.createCell(50, bankName);
        row.createCell(50, UserAccountUtils.getCurrentSolId());

        be.quodlibet.boxable.Row<PDPage> row2 = infoTable.createRow(20);
        row2.createCell(50, bondInterestRunCreditDTO.getBranchName());
        row2.createCell(50, getSchemeName(bondInterestRunCreditDTO.getSchemeCode()));

        be.quodlibet.boxable.Row<PDPage> row3 = infoTable.createRow(20);
        row3.createCell(100, "For Period: " + bondInterestRunCreditDTO.getInterestPayFrom() + " to " + bondInterestRunCreditDTO.getInterestPayTo());

        infoTable.draw();

        yStart = infoTable.draw() - 25;


        // ---------- TABLE 2 (Data Table) ----------
        BaseTable table = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, drawLines, drawContent);

        // Header Row
        be.quodlibet.boxable.Row<PDPage> headerRow = table.createRow(22);
        String[] columns = {
                "S.No", "Branch Id", "BOND Ledger Account Number", "Customer Name",
                "BOND Subscription ID", "Subscription Date", "Subscription Amount",
                "Interest From Period", "Interest To Period", "Interest payment mode",
                "Credit Account Number", "Total Interest Rs.", "TDS Amount Rs.",
                "Net Interest Amount Rs.", "CBS Tran ID", "GBM Tran ID"
        };

        for (String col : columns) {
            headerRow.createCell(100 / columns.length, col).setFont(PDType1Font.HELVETICA_BOLD);
        }
        table.addHeaderRow(headerRow);

        double totIntAmt = 0;
        double totTds = 0;
        double totNet = 0;
        int index = 1;

        for (Map<String, Object> item : bondInterestRunCreditDTO.getData()) {

            double grossInt = parseDouble(item.get("Gross_Interest_Amount"));
            double tds = parseDouble(item.get("TDS_AMT"));
            double netAmt = parseDouble(item.get("NET_INTEREST_AMOUNT"));

            totIntAmt += grossInt;
            totTds += tds;
            totNet += netAmt;

            be.quodlibet.boxable.Row<PDPage> dataRow = table.createRow(20);
            dataRow.createCell(4, String.valueOf(index++));
            dataRow.createCell(6, (String) item.get("BRANCH_ID"));
            dataRow.createCell(8, (String) item.get("BLA_NO"));
            dataRow.createCell(10, (String) item.get("CUSTOMER_NAME"));
            dataRow.createCell(8, (String) item.get("BOND_SUBSCRIPTION_ID"));
            dataRow.createCell(6, (String) item.get("SUBSCRIPTION_DATE"));
            dataRow.createCell(6, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            dataRow.createCell(6, (String) item.get("INTEREST_PERIOD_FROM"));
            dataRow.createCell(6, (String) item.get("INTERST_PERIOD_TO"));
            dataRow.createCell(6, (String) item.get("MODE_OF_INTEREST_PAYMENT"));
            dataRow.createCell(6, (String) item.get("CREDIT_ACCOUNT_NUMBER"));
            dataRow.createCell(6, String.valueOf(grossInt));
            dataRow.createCell(6, String.valueOf(tds));
            dataRow.createCell(6, String.valueOf(netAmt));
            dataRow.createCell(6, (String) item.get("CBS_Tran_Id"));
            dataRow.createCell(6, (String) item.get("GBM_TRAN_ID"));
        }

        table.draw();


        // ---------- TOTAL ROW ----------
        yStart = table.draw() - 20;
        BaseTable totalTable = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, drawLines, drawContent);

        be.quodlibet.boxable.Row<PDPage> totalRow = totalTable.createRow(20);
        totalRow.createCell(40, "Total");
        totalRow.createCell(40, "");
        totalRow.createCell(40, String.valueOf(totIntAmt));
        totalRow.createCell(40, String.valueOf(totTds));
        totalRow.createCell(40, String.valueOf(totNet));

        totalTable.draw();

        document.save(baos);
        document.close();
        return baos.toByteArray();
    }


    // Helper to handle null and number parsing
    private double parseDouble(Object val) {
        try {
            return val == null ? 0 : Double.parseDouble(String.valueOf(val));
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public ResponseDataDTO getInterestTrailRunReportPayment(InterestTrailRunPaymentDTO interestTrailRunPaymentDTO) {
        return rbiBondReportsDAO.getInterestTrailRunReportPayment(interestTrailRunPaymentDTO);
    }

    @Override
    public ResponseDataDTO getInterRunCrdtCollBranReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO) {
        return rbiBondReportsDAO.getInterRunCrdtCollBranReport(bondInterRunCrdtCollBranDTO);
    }

    @Override
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


//            createCell(row, 13, item.getArrearTdsAmount());
//            createCell(row, 14, item.getNetInterestAmount());


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

    @Override
    public byte[] pdfFileGeneratorForInterRunCollBranchReport(BondInterRunCrdtCollBranDTO bondInterRunCrdtCollBranDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        // ---- Title ----
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
        contentStream.newLineAtOffset((page.getMediaBox().getWidth() / 2) - 360, page.getMediaBox().getHeight() - 60);
        contentStream.showText("Bond Actual Interest Run Credited Report – Collecting Branch");
        contentStream.endText();
        contentStream.close();

        float margin = 40;
        float yStart = page.getMediaBox().getHeight() - 100;
        float bottomMargin = 40;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yPos = yStart;

        // ------------------- Header Info Table (2 columns) -------------------
        BaseTable infoTable = new BaseTable(yPos, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> r1 = infoTable.createRow(22);
        r1.createCell(50, "Bank Name");
        r1.createCell(50, bankName);

        be.quodlibet.boxable.Row<PDPage> r2 = infoTable.createRow(22);
        r2.createCell(50, "Branch Name");
        r2.createCell(50, bondInterRunCrdtCollBranDTO.getBranchName());

        be.quodlibet.boxable.Row<PDPage> r3 = infoTable.createRow(22);
        r3.createCell(50, "Branch Id");
        r3.createCell(50, UserAccountUtils.getCurrentSolId());

        infoTable.draw();

        yPos = infoTable.draw() - 25;

        // ------------------- Main Data Table (15 Columns) -------------------
        BaseTable table = new BaseTable(yPos, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> headerRow = table.createRow(25);
        headerRow.setHeaderRow(true);

        headerRow.createCell(4, "S.No");
        headerRow.createCell(8, "BOND Ledger Account Number");
        headerRow.createCell(10, "Customer Name");
        headerRow.createCell(7, "BOND Subscription ID");
        headerRow.createCell(8, "Subscription Date");
        headerRow.createCell(8, "Subscription Amount");
        headerRow.createCell(8, "Interest From Period");
        headerRow.createCell(8, "Interest To Period");
        headerRow.createCell(8, "Interest payment mode");
        headerRow.createCell(10, "Credit Account Number");
        headerRow.createCell(8, "Total Interest Rs.");
        headerRow.createCell(8, "TDS Amount Rs.");
        headerRow.createCell(8, "Net Interest Amount Rs.");
        headerRow.createCell(6, "CBS Tran ID");
        headerRow.createCell(6, "GBM Tran ID");

        table.addHeaderRow(headerRow);

        double totIntAmt = 0d;
        double totTds = 0d;
        double totNet = 0d;

        int index = 1;
        for (Map<String, Object> item : bondInterRunCrdtCollBranDTO.getData()) {

            double interest = Double.parseDouble(String.valueOf(item.get("Gross_Interest_Amount")));
            double tds = Double.parseDouble(String.valueOf(item.get("TDS_AMT")));
            double net = Double.parseDouble(String.valueOf(item.get("NET_INTEREST_AMOUNT")));

            totIntAmt += interest;
            totTds += tds;
            totNet += net;

            be.quodlibet.boxable.Row<PDPage> row = table.createRow(20);
            row.createCell(4, String.valueOf(index++));
            row.createCell(8, String.valueOf(item.get("BLA_NO")));
            row.createCell(10, String.valueOf(item.get("CUSTOMER_NAME")));
            row.createCell(7, String.valueOf(item.get("BOND_SUBSCRIPTION_ID")));
            row.createCell(8, String.valueOf(item.get("SUBSCRIPTION_DATE")));
            row.createCell(8, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            row.createCell(8, String.valueOf(item.get("INTEREST_PERIOD_FROM")));
            row.createCell(8, String.valueOf(item.get("INTERST_PERIOD_TO")));
            row.createCell(8, String.valueOf(item.get("MODE_OF_INTEREST_PAYMENT")));
            row.createCell(10, String.valueOf(item.get("CREDIT_ACCOUNT_NUMBER")));
            row.createCell(8, String.valueOf(interest));
            row.createCell(8, String.valueOf(tds));
            row.createCell(8, String.valueOf(net));
            row.createCell(6, String.valueOf(item.get("CBS_Tran_Id")));
            row.createCell(6, String.valueOf(item.get("GBM_TRAN_ID")));
        }

        // Draw data table
        table.draw();

        // ------------------- Totals Row -------------------
        BaseTable totalTable = new BaseTable(table.draw() - 20, yStart, bottomMargin, tableWidth, margin, document, page, true, true);
        be.quodlibet.boxable.Row<PDPage> totalRow = totalTable.createRow(22);

        totalRow.createCell(70, ""); // blank space
        totalRow.createCell(10, "Total");
        totalRow.createCell(6, String.valueOf(totIntAmt));
        totalRow.createCell(6, String.valueOf(totTds));
        totalRow.createCell(6, String.valueOf(totNet));

        totalRow.createCell(2, "");
        totalRow.createCell(2, "");

        totalTable.draw();

        document.save(baos);
        document.close();
        return baos.toByteArray();
    }

    @Override
    public ResponseDataDTO getInterPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO) {
        return rbiBondReportsDAO.getInterPayPendingReport(bondInterestPayPendingDTO);
    }

    @Override
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

    @Override
    public byte[] pdfFileGeneratorForInterestPayPendingReport(BondInterestPayPendingDTO bondInterestPayPendingDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        // ---- Title ----
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
        contentStream.newLineAtOffset((page.getMediaBox().getWidth() / 2) - 330, page.getMediaBox().getHeight() - 60);
        contentStream.showText("Bond Interest Payment Pending Report – Centralised");
        contentStream.endText();
        contentStream.close();

        float margin = 40;
        float yStart = page.getMediaBox().getHeight() - 100;
        float bottomMargin = 30;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yPosition = yStart;

        // ------------------- First Information Table (2 columns) -------------------
        BaseTable infoTable = new BaseTable(yPosition, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> r1 = infoTable.createRow(22);
        r1.createCell(50, "Bond Interest Payment Pending Report – Centralised");
        r1.createCell(50, "For " + bondInterestPayPendingDTO.getInterestPaymentFor() + "   " + bondInterestPayPendingDTO.getFinancialYear());

        be.quodlibet.boxable.Row<PDPage> r2 = infoTable.createRow(22);
        r2.createCell(50, this.getSchemeName(bondInterestPayPendingDTO.getSchemeCode()));
        r2.createCell(50, "Branch Id");

        be.quodlibet.boxable.Row<PDPage> r3 = infoTable.createRow(22);
        r3.createCell(50, UserAccountUtils.getCurrentSolId());
        r3.createCell(50, "Branch Name");

        be.quodlibet.boxable.Row<PDPage> r4 = infoTable.createRow(22);
        r4.createCell(50, bondInterestPayPendingDTO.getBranchName());
        r4.createCell(50, "");

        infoTable.draw();

        // Move table cursor for next table
        yPosition = infoTable.draw() - 25;

        // ------------------- Main Data Table (10 Columns) -------------------
        BaseTable dataTable = new BaseTable(yPosition, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(25);
        headerRow.setHeaderRow(true);

        headerRow.createCell(5, "S.No");
        headerRow.createCell(8, "Branch Id");
        headerRow.createCell(10, "BLA No.");
        headerRow.createCell(15, "Holder Name");
        headerRow.createCell(12, "Subscription Date");
        headerRow.createCell(12, "Subscription Account No.");
        headerRow.createCell(12, "Subscription Amount");
        headerRow.createCell(10, "Interest Payment mode");
        headerRow.createCell(10, "Gross Interest Amount (Rs)");
        headerRow.createCell(16, "Reasons");

        dataTable.addHeaderRow(headerRow);

        int index = 1;
        for (Map<String, Object> item : bondInterestPayPendingDTO.getData()) {

            be.quodlibet.boxable.Row<PDPage> row = dataTable.createRow(20);
            row.createCell(5, String.valueOf(index++));
            row.createCell(8, String.valueOf(item.get("BRANCH_ID")));
            row.createCell(10, String.valueOf(item.get("bond_ledger_account_number")));
            row.createCell(15, String.valueOf(item.get("CUSTOMER_NAME")));
            row.createCell(12, String.valueOf(item.get("SUBCRIPTION_DATE")));
            row.createCell(12, String.valueOf(item.get("BOND_SUBSCRIPTION_ID")));
            row.createCell(12, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            row.createCell(10, String.valueOf(item.get("INTEREST_PAYMENT_MODE")));
            row.createCell(10, String.valueOf(item.get("GROSS_INTEREST_AMOUNT")));
            row.createCell(16, String.valueOf(item.get("REASON")));
        }

        dataTable.draw();

        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

    @Override
    public ResponseDataDTO getBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO) {
        return rbiBondReportsDAO.getBondInterestCalculatedReport(bondInterestCalculatedDTO);
    }

    @Override
    public byte[] pdfFileGeneratorForBondInterestCalculatedReport(BondInterestCalculatedDTO bondInterestCalculatedDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // ---- Header ----
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 22);
        contentStream.newLineAtOffset(page.getMediaBox().getWidth() / 2 - 250, page.getMediaBox().getHeight() - 60);
        contentStream.showText("Bond Interest Not Calculated Report");
        contentStream.endText();
        contentStream.close();

        float margin = 40;
        float yStart = page.getMediaBox().getHeight() - 100;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yPosition = yStart;
        float bottomMargin = 30;

        // ---------- First Info Table (2 Columns) ----------
        BaseTable infoTable = new BaseTable(yPosition, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        // Row 1
        be.quodlibet.boxable.Row<PDPage> row1 = infoTable.createRow(20);
        row1.createCell(50, bankName);
        row1.createCell(50, UserAccountUtils.getCurrentSolId());

        // Row 2
        be.quodlibet.boxable.Row<PDPage> row2 = infoTable.createRow(20);
        row2.createCell(50, bondInterestCalculatedDTO.getBranchname());
        row2.createCell(50, this.getSchemeName(bondInterestCalculatedDTO.getSchemeCode()));

        // Row 3
        be.quodlibet.boxable.Row<PDPage> row3 = infoTable.createRow(20);
        row3.createCell(50, "Bond Interest Not Calculated Report - Centralized");
        row3.createCell(50, "for " + bondInterestCalculatedDTO.getInterestPaid() + "   " + bondInterestCalculatedDTO.getFinancialYear());

        // Row 4
        be.quodlibet.boxable.Row<PDPage> row4 = infoTable.createRow(20);
        row4.createCell(50, "Branch Id");
        row4.createCell(50, UserAccountUtils.getCurrentSolId());

        // Row 5
        be.quodlibet.boxable.Row<PDPage> row5 = infoTable.createRow(20);
        row5.createCell(50, "Branch Name");
        row5.createCell(50, bondInterestCalculatedDTO.getBranchname());

        infoTable.draw();

        // Move table Y start below previous table
        yPosition = infoTable.draw() - 20;

        // ---------- Main Data Table (10 Columns) ----------
        BaseTable dataTable = new BaseTable(yPosition, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(25);
        headerRow.setHeaderRow(true);

        headerRow.createCell(5, "S.No");
        headerRow.createCell(8, "Branch ID");
        headerRow.createCell(10, "BLA No");
        headerRow.createCell(15, "Holder Name");
        headerRow.createCell(12, "Subscription Date");
        headerRow.createCell(12, "Subscription Account No");
        headerRow.createCell(12, "Subscription Amount");
        headerRow.createCell(10, "Interest Payment mode");
        headerRow.createCell(10, "Gross Interest Amount");
        headerRow.createCell(16, "Reasons");

        dataTable.addHeaderRow(headerRow);

        int index = 1;
        for (Map<String, Object> item : bondInterestCalculatedDTO.getData()) {

            be.quodlibet.boxable.Row<PDPage> row = dataTable.createRow(20);
            row.createCell(5, String.valueOf(index++));
            row.createCell(8, String.valueOf(item.get("BRANCH_ID")));
            row.createCell(10, String.valueOf(item.get("BLANO")));
            row.createCell(15, String.valueOf(item.get("CUSTOMER_NAME")));
            row.createCell(12, String.valueOf(item.get("SUBCRIPTION_DATE")));
            row.createCell(12, String.valueOf(item.get("BOND_SUBSCRIPTION_ID")));
            row.createCell(12, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            row.createCell(10, String.valueOf(item.get("INTEREST_PAYMENT_MODE")));
            row.createCell(10, String.valueOf(item.get("GROSS_INTEREST_AMOUNT")));
            row.createCell(16, String.valueOf(item.get("REASON")));
        }

        dataTable.draw();

        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

    @Override
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

    @Override
    public ResponseDataDTO getBondInterestCalculatedReportDetails(BondInterestCalculatedDTO bondInterestCalculatedDTO) {
        return rbiBondReportsDAO.getBondInterestCalculatedReportDetails(bondInterestCalculatedDTO);
    }

    @Override
    public ResponseDataDTO getAccHolderInquiryReport(BondAccHolderInquiryReportDTO bondAccHolderInquiryReportDTO) {
        return rbiBondReportsDAO.getAccHolderInquiryReport(bondAccHolderInquiryReportDTO);
    }

    @Override
    public ResponseDataDTO getBlaNameSearchReport(BondBlaNameSearchReportDTO bondBlaNameSearchReportDTO) {
        return rbiBondReportsDAO.getBlaNameSearchReport(bondBlaNameSearchReportDTO);
    }

    @Override
    public ResponseDataDTO getBondCollectingdReportDetails(BondInteresDTO bondInteresDTO) {


      if( bondInteresDTO.getDetailsFlag() != null&&bondInteresDTO.getDetailsFlag().equalsIgnoreCase("D")){

          return rbiBondReportsDAO.getBondCollectingdReportDetails(bondInteresDTO);
      }

      else if(bondInteresDTO.getDownloadType() != null&&bondInteresDTO.getDownloadType().equalsIgnoreCase("csv")) {
          return rbiBondReportsDAO.getBondCollectingdReportDetails(bondInteresDTO);
      }


        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(bondInterestNotCalculatedReportExcel(rbiBondReportsDAO.getBondCollectingdReportDetails(bondInteresDTO), bondInteresDTO)));
            fileResponseDto.setFileName("Bond_Interest_Not_Calculated_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in BondInteresDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] bondInterestNotCalculatedReportExcel(ResponseDataDTO responseDataDTO, BondInteresDTO BondInteresDTO1) {
        List<BondInteresDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();

            // Title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Bond Interest Not Calculated Report");

            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));

            // Bank info row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name :     HDFC BANK         Branch Name : " + solDesc + "       Branch Id : " + loginSolid);

            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 8));

            SXSSFRow finalYearRow = sheet.createRow(2);
            SXSSFCell finalYearCell = finalYearRow.createCell(0);
            finalYearCell.setCellValue("Interest payment for  : " + BondInteresDTO1.getMonth() + "        FinancialYear   : " + BondInteresDTO1.getFinYear());

            CellStyle finalYearStyle = workbook.createCellStyle();
            finalYearStyle.setAlignment(HorizontalAlignment.CENTER);
            finalYearCell.setCellStyle(finalYearStyle);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 8));

            SXSSFRow bondSchmeRow = sheet.createRow(3);
            SXSSFCell bondSchmeCell = bondSchmeRow.createCell(0);
            bondSchmeCell.setCellValue("Name of the Bond Scheme    : "+BondInteresDTO1.getSchemeName());

            CellStyle bondSchmeCellStyle = workbook.createCellStyle();
            bondSchmeCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bondSchmeCell.setCellStyle(bondSchmeCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 8));




            // Headers
            SXSSFRow headerRow = sheet.createRow(4);
            String[] headers = {
                    "S.No.",
                    "PAN No",
                    "BLA No",
                    "Holder Name",
                    "Subscription Date",
                    "Subscription Account No",
                    "Subscription Amount (Rs)",
                    "Interest Payment mode",
                    "Gross Interest Amount",
                    "Reasons",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<BondInteresDTO>) finalData.get("list");

            // Data rows
            int rowNumber = 5;
            int sNo = 1;
            double totalSubscriptionAmount = 0;
            double totalGrossInterest = 0;
            double totalTdsAmount = 0;

            if (!list.isEmpty()) {
                for (BondInteresDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getSubscriptionDate() != null ? data.getSubscriptionDate() : "",
                            data.getSubscriptionAccountNo() != null ? data.getSubscriptionAccountNo() : "",
                            data.getSubscriptionAmount() != null ? data.getSubscriptionAmount() : "",
                            data.getInterestPaymentmode() != null ? data.getInterestPaymentmode() : "",
                            data.getGrossInterestAmount() != null ? data.getGrossInterestAmount() : "",
                            data.getReasons() != null ? data.getReasons() : "",
                    };

                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }

                    // Totals
                    try {
                        totalSubscriptionAmount += Double.parseDouble(data.getSubscriptionAmount());
                    } catch (Exception ignored) {}
                    try {
                        totalGrossInterest += Double.parseDouble(data.getGrossInterestAmount());
                    } catch (Exception ignored) {}

                }

                // Total Row
                SXSSFRow totalRow = sheet.createRow(rowNumber);
                totalRow.createCell(5).setCellValue("Total"); // Below Subscription Date
                totalRow.createCell(6).setCellValue(String.format("%.2f", totalSubscriptionAmount)); // Subscription Amount
                totalRow.createCell(8).setCellValue(String.format("%.2f", totalGrossInterest)); // Gross Interest

            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }

            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            log.error("Exception while writing the Excel file: " + e.getMessage(), e);
        }

        return byteArrayOutputStream != null ? byteArrayOutputStream.toByteArray() : null;
    }

    @Override
    public ResponseDataDTO getPreClosureRepaymentReport(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO) {
        return rbiBondReportsDAO.getPreClosureRepaymentReport(bondPrecloserRepayReportDTO);
    }

    @Override
    public ResponseDataDTO getPreClosureRepaymentDownload(BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getPreClosureRepaymentReport(bondPrecloserRepayReportDTO), bondPrecloserRepayReportDTO)));
            fileResponseDto.setFileName("BOND_PRECLOSURE_REPAYMENT_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in BondPrecloserRepayReportDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, BondPrecloserRepayReportDTO bondPrecloserRepayReportDTO) {
        List<BondPrecloserRepayReportDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BOND PRECLOSURE REPAYMENT REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK   Branch Name : " + solDesc + " Branch Id : " + loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "PAN No",
                    "Scheme Name",
                    "Bla No",
                    "Holder Name",
                    "Subscription ID",
                    "Subscription Date",
                    "Subscription Amount",
                    "Due date of Repayment",
                    "Date of Pre-Closed Repayment",
                    "Gross Amount",
                    "TDS Amount",
                    "TDS Percentage",
                    "Amount of Repayment",
                    "Credited to Bank Account No",
                   "Maker Id",
                   "Maker Date",
                   "Checker Id",
                   "Checker Date",
                "CAS Date",
                  "Old Bond Subscription Id",
                   "Old Bla No",
                  "Investment Holding Year"

            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BondPrecloserRepayReportDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondPrecloserRepayReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getSchemeName() != null ? data.getSchemeName() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getSubscriptionId() != null ? data.getSubscriptionId() : "",
                            data.getSubscriptionDate() != null ? data.getSubscriptionDate() : "",
                            data.getSubscriptionAmt() != null ? data.getSubscriptionAmt() : "",
                            data.getDueDateRepay() != null ? data.getDueDateRepay() : "",
                            data.getDatePreclosRepay() != null ? data.getDatePreclosRepay() : "",
                            data.getGrossAmt() != null ? data.getGrossAmt() : "",
                            data.getTdsAmt() != null ? data.getTdsAmt() : "",

                            data.getTdsPercentage() != null ? data.getTdsPercentage() + " %" : "",

                            data.getRepayAmt() != null ? data.getRepayAmt() : "",
                            data.getCreditAccNo() != null ? data.getCreditAccNo() : "",
                            data.getMakerId() != null ? data.getMakerId() : "",
                            data.getMakerDate() != null ? data.getMakerDate() : "",
                            data.getCheckerId() != null ? data.getCheckerId() : "",
                            data.getCheckerDate() != null ? data.getCheckerDate() : "",
                            data.getCasDate() != null ? data.getCasDate() : "",
                            data.getOldBondSubscriptionId() != null ? data.getOldBondSubscriptionId() : "",
                            data.getOldBlaNo() != null ? data.getOldBlaNo() : "",
                            data.getInvestmentHoldingYear() != null ? data.getInvestmentHoldingYear() : "",
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
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getStatementBrokerageDetails(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO) {
        return rbiBondReportsDAO.getStatementBrokerageDetails(statementOfBrokeragePaidDTO);
    }

    @Override
    public ResponseDataDTO getStatementBrokerageDownload(StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            if(statementOfBrokeragePaidDTO.getReportType().equalsIgnoreCase("IW")) {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcelIW(rbiBondReportsDAO.getStatementBrokerageDetails(statementOfBrokeragePaidDTO), statementOfBrokeragePaidDTO)));
            } else if(statementOfBrokeragePaidDTO.getReportType().equalsIgnoreCase("BW")) {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcelBW(rbiBondReportsDAO.getStatementBrokerageDetails(statementOfBrokeragePaidDTO), statementOfBrokeragePaidDTO)));
            }
            fileResponseDto.setFileName("STATEMENT_OF_BROKERAGE_PAIN_UNPAID_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in StatementOfBrokeragePaidDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcelIW(ResponseDataDTO responseDataDTO, StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO) {
        List<StatementOfBrokeragePaidDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();

            // Title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("STATEMENT OF BROKERAGE PAID & UNPAID REPORT");

            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 20));

            // Bank Info
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 20));

            // Merged Row: Subscription Amount Header
            SXSSFRow groupHeaderRow = sheet.createRow(2);
            groupHeaderRow.createCell(4).setCellValue("Subscription Amount (Rs.)");
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 4, 5));

            // Actual Header Row
            SXSSFRow headerRow = sheet.createRow(3);
            String[] headers = {
                    "S.No.",
                    "BLA NO.",
                    "CUSTOMER NAME",
                    "SUBSCRIPTION ID",
                    "CUMULATIVE",
                    "NON-CUMULATIVE",
                    "TOTAL", // Inserted
                    "STATUS",
                    "INVOICE NO.",
                    "INVOICE DATE",
                    "BROKER ID",
                    "NAME OF THE BROKER",
                    "BROKER PAN",
                    "BROKER EMAIL ID",
                    "GROSS BROKERAGE",
                    "GST",
                    "NET BROKERAGE PAID",
                    "BROKERAGE PAID DATE",
                    "BROKERAGE TRANSACTION INITIATED DATE FOR UNPAID",
                    "REASON FOR UNPAID"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            // No bold font setting here

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<StatementOfBrokeragePaidDTO>) finalData.get("list");

            double totalCumulative = 0;
            double totalNonCumulative = 0;
            double totalNetBrokeragePaid = 0;

            int rowNumber = 4;
            int sNo = 1;

            if (!list.isEmpty()) {
                for (StatementOfBrokeragePaidDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String cumulativeStr = data.getCumulative() != null ? data.getCumulative() : "0";
                    String nonCumulativeStr = data.getNonCumulative() != null ? data.getNonCumulative() : "0";
                    String netBrokerageStr = data.getNetBrokeragePaid() != null ? data.getNetBrokeragePaid() : "0";

                    double cumulative = parseDoubleSafe(cumulativeStr);
                    double nonCumulative = parseDoubleSafe(nonCumulativeStr);
                    double total = cumulative + nonCumulative;
                    double netBrokeragePaid = parseDoubleSafe(netBrokerageStr);

                    totalCumulative += cumulative;
                    totalNonCumulative += nonCumulative;
                    totalNetBrokeragePaid += netBrokeragePaid;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            safe(data.getBlaNumber()),
                            safe(data.getCustomerName()),
                            safe(data.getSubscriptionID()),
                            cumulativeStr,
                            nonCumulativeStr,
                            String.valueOf(total),
                            safe(data.getStatus()),
                            safe(data.getInvoiceNumber()),
                            safe(data.getInvoiceDate()),
                            safe(data.getBrokerID()),
                            safe(data.getBrokerName()),
                            safe(data.getBrokerPAN()),
                            safe(data.getBrokerEmailId()),
                            safe(data.getGrossBrokerage()),
                            safe(data.getGST()),
                            netBrokerageStr,
                            safe(data.getBrokeragePaidDate()),
                            safe(data.getBrokerageTransactionInitiatedDate()),
                            safe(data.getReasonForUnpaid())
                    };

                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }

                // Footer Totals Row
                SXSSFRow totalRow = sheet.createRow(rowNumber);
                totalRow.createCell(3).setCellValue("TOTAL");
                totalRow.createCell(4).setCellValue(totalCumulative); // CUMULATIVE SUM
                totalRow.createCell(5).setCellValue(totalNonCumulative); // NON-CUMULATIVE SUM
                totalRow.createCell(6).setCellValue(totalCumulative + totalNonCumulative); // TOTAL
                totalRow.createCell(16).setCellValue(totalNetBrokeragePaid); // NET BROKERAGE PAID TOTAL
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA");
            }

            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }

        return byteArrayOutputStream.toByteArray();
    }

    public byte[] generateZoneWiseBranchWiseReportExcelBW(ResponseDataDTO responseDataDTO, StatementOfBrokeragePaidDTO statementOfBrokeragePaidDTO) {
        List<StatementOfBrokeragePaidDTO> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("STATEMENT OF BROKERAGE PAID & UNPAID REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "INVOICE NO.",
                    "INVOICE DATE",
                    "BROKER NAME",
                    "BROKER CODE",
                    "BROKER PAN",
                    "BROKER EMAIL ID",
                    "GROSS BROKERAGE",
                    "GST%",
                    "GST AMOUNT",
                    "NET BROKERAGE",
                    "BROKERAGE PAID DATE",
                    "STATUS",
                    "REMARKS",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<StatementOfBrokeragePaidDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (StatementOfBrokeragePaidDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getInvoiceNumber() != null ? data.getInvoiceNumber() : "",
                            data.getInvoiceDate() != null ? data.getInvoiceDate() : "",
                            data.getBrokerName() != null ? data.getBrokerName() : "",
                            data.getBrokerCode() != null ? data.getBrokerCode() : "",
                            data.getBrokerPAN() != null ? data.getBrokerPAN() : "",
                            data.getBrokerEmailId() != null ? data.getBrokerEmailId() : "",
                            data.getGrossBrokerage() != null ? data.getGrossBrokerage() : "",
                            data.getGstPercent() != null ? data.getGstPercent() : "",
                            data.getGstAmount() != null ? data.getGstAmount() : "",
                            data.getNetBrokeragePaid() != null ? data.getNetBrokeragePaid() : "",
                            data.getBrokeragePaidDate() != null ? data.getBrokeragePaidDate() : "",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getRemarks() != null ? data.getRemarks() : "",
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    private double parseDoubleSafe(String val) {
        try {
            return Double.parseDouble(val.trim().replace(",", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    private String safe(String val) {
        return val != null ? val : "";
    }


    @Override
    public ResponseDataDTO getBondRepaymentDetails(BondRepaymentReportDto bondRepaymentReportDto) throws IOException {
        return rbiBondReportsDAO.getBondRepaymentDetails(bondRepaymentReportDto);
    }

    @Override
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

    @Override
    public byte[] pdfFileGeneratorForBondRepay(List<Map<String, Object>> results,
                                               BondRepaymentReportDto dto) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        PDRectangle pageSize = page.getMediaBox();
        float margin = 40;
        float yStart = pageSize.getHeight() - margin;
        float bottomMargin = 40;
        float tableWidth = pageSize.getWidth() - (2 * margin);

        // ===== HEADER =====
        try (PDPageContentStream cs = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 20);
            cs.newLineAtOffset(pageSize.getWidth() / 3, yStart);
            cs.showText("Bond Repayment – Report");
            cs.endText();
        }

        yStart -= 50;

        // ===== INFO TABLE (2 Columns) =====
        BaseTable infoTable = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);
        be.quodlibet.boxable.Row<PDPage> row;

        row = infoTable.createRow(18);
        row.createCell(50, "Bank Name");
        row.createCell(50, bankName);

        row = infoTable.createRow(18);
        row.createCell(50, "Branch Name");
        row.createCell(50, dto.getBranchCode());

        row = infoTable.createRow(18);
        row.createCell(50, "Branch Id");
        row.createCell(50, UserAccountUtils.getCurrentSolId());

        // Draw info table and update position
        yStart = infoTable.draw() - 30;

        // ===== MAIN DATA TABLE (9 Columns) =====
        BaseTable table = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> headerRow = table.createRow(20);
        headerRow.createCell(5, "S.No");
        headerRow.createCell(12, "BLA No");
        headerRow.createCell(15, "Holder Name");
        headerRow.createCell(12, "Subscription ID");
        headerRow.createCell(12, "Date of Subscription");
        headerRow.createCell(12, "Subscription Amount (Rs)");
        headerRow.createCell(12, "Amount Repaid (Rs)");
        headerRow.createCell(10, "Maturity Date");
        headerRow.createCell(10, "Date of Repayment");

        // ===== DATA ROWS =====
        double totSubsAmt = 0;
        double totAmtRepaid = 0;
        int index = 1;

        for (Map<String, Object> item : dto.getData()) {

            String subsAmtStr = String.valueOf(item.get("subscription_amount"));
            String repaidAmtStr = String.valueOf(item.get("Amount_of_Repayment"));

            double subsAmt = subsAmtStr == null ? 0 : Double.parseDouble(subsAmtStr);
            double amtRepaid = repaidAmtStr == null ? 0 : Double.parseDouble(repaidAmtStr);

            totSubsAmt += subsAmt;
            totAmtRepaid += amtRepaid;

            be.quodlibet.boxable.Row<PDPage> dataRow = table.createRow(16);
            dataRow.createCell(5, String.valueOf(index++));
            dataRow.createCell(12, (String) item.get("BLA_NO"));
            dataRow.createCell(15, (String) item.get("bond_holder_name"));
            dataRow.createCell(12, (String) item.get("BOND_SUBSCRIPTION_ID"));
            dataRow.createCell(12, (String) item.get("subscription_date"));
            dataRow.createCell(12, subsAmtStr);
            dataRow.createCell(12, repaidAmtStr);
            dataRow.createCell(10, String.valueOf(item.get("MATURITY_DATE")));
            dataRow.createCell(10, String.valueOf(item.get("DATE_OF_REPAYMENT")));
        }

        table.draw();

        // ===== TOTAL ROW =====
        yStart = table.draw() - 25;
        BaseTable totalTable = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> totalRow = totalTable.createRow(18);
        totalRow.createCell(66, "TOTAL");
        totalRow.createCell(12, String.valueOf(totSubsAmt));
        totalRow.createCell(12, String.valueOf(totAmtRepaid));
        totalRow.createCell(10, "");

        totalTable.draw();

        // ===== SAVE & RETURN =====
        document.save(baos);
        document.close();

        System.out.println("PDF (Bond Repayment) Created Successfully ✅");
        return baos.toByteArray();
    }

    @Override
    public ResponseDataDTO getBodEodStatusInquiryDetails(BodEodStatusInqDTO bodEodStatusInqDTO) {
        return rbiBondReportsDAO.getBodEodStatusInquiryDetails(bodEodStatusInqDTO);
    }

    @Override
    public ResponseDataDTO getModuleName() {
        return rbiBondReportsDAO.getModuleName();
    }

    @Override
    public ResponseDataDTO getInterPayPendingCollReport(BondInterestPayPendingCollDTO bondInterestPayPendingCollDTO) {
        return rbiBondReportsDAO.getInterPayPendingCollReport(bondInterestPayPendingCollDTO);
    }

    @Override
    public byte[] pdfFileGeneratorForBondInterestPendingCollReport(BondInterestPayPendingCollDTO dto) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        PDRectangle pageSize = page.getMediaBox();
        float margin = 40;
        float yStart = pageSize.getHeight() - margin;
        float bottomMargin = 40;
        float tableWidth = pageSize.getWidth() - (2 * margin);

        // ===== HEADER TEXT =====
        try (PDPageContentStream cs = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
            cs.beginText();
            cs.setFont(PDType1Font.HELVETICA_BOLD, 20);
            cs.newLineAtOffset(pageSize.getWidth() / 3, yStart);
            cs.showText("Bond Interest Payment Pending Report – Collecting Branch");
            cs.endText();
        }

        yStart -= 50;

        // ===== INFO TABLE (2 Columns) =====
        BaseTable infoTable = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);
        be.quodlibet.boxable.Row<PDPage> row;

        row = infoTable.createRow(18);
        row.createCell(50, "Bond Interest Payment Pending Report – Collecting Branch");
        row.createCell(50, "for " + dto.getInterestPaymentFor() + " " + dto.getFinancialYear());

        row = infoTable.createRow(18);
        row.createCell(50, this.getSchemeName(dto.getSchemeCode()));
        row.createCell(50, "");

        row = infoTable.createRow(18);
        row.createCell(50, "Branch Id");
        row.createCell(50, UserAccountUtils.getCurrentSolId());

        row = infoTable.createRow(18);
        row.createCell(50, "Branch Name");
        row.createCell(50, dto.getBranchName());

        // Draw info table and get new y-position
        yStart = infoTable.draw() - 30;

        // ===== MAIN DATA TABLE (10 Columns) =====
        BaseTable table = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> header = table.createRow(18);
        header.createCell(5, "S.No");
        header.createCell(10, "Branch Id");
        header.createCell(10, "BLA No.");
        header.createCell(13, "Holder Name");
        header.createCell(10, "Subscription Date");
        header.createCell(12, "Subscription Account No.");
        header.createCell(10, "Subscription Amount");
        header.createCell(10, "Interest Payment Mode");
        header.createCell(10, "Gross Interest Amount (Rs)");
        header.createCell(10, "Reasons");

        // ===== TABLE DATA ROWS =====
        int index = 1;
        for (Map<String, Object> item : dto.getData()) {

            be.quodlibet.boxable.Row<PDPage> dataRow = table.createRow(16);
            dataRow.createCell(5, String.valueOf(index++));
            dataRow.createCell(10, (String) item.get("BRANCH_ID"));
            dataRow.createCell(10, (String) item.get("bond_ledger_account_number"));
            dataRow.createCell(13, (String) item.get("CUSTOMER_NAME"));
            dataRow.createCell(10, (String) item.get("SUBCRIPTION_DATE"));
            dataRow.createCell(12, (String) item.get("BOND_SUBSCRIPTION_ID"));
            dataRow.createCell(10, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            dataRow.createCell(10, (String) item.get("INTEREST_PAYMENT_MODE"));
            dataRow.createCell(10, String.valueOf(item.get("GROSS_INTEREST_AMOUNT")));
            dataRow.createCell(10, (String) item.get("REASON"));
        }

        table.draw();

        document.save(baos);
        document.close();

        System.out.println("PDF Created Successfully (Boxable)");
        return baos.toByteArray();
    }

    @Override
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

    @Override
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
//            totIntAmt = totIntAmt + bondTdsDeductionReportDTO.getInterestAmt() == null ? 0 : Integer.parseInt(bondTdsDeductionReportDTO.getInterestAmt());
//            totTds = totTds + bondTdsDeductionReportDTO.getTdsAmt() == null ? 0 : Integer.parseInt(bondTdsDeductionReportDTO.getTdsAmt());
//            totNet = totNet + bondTdsDeductionReportDTO.getNetAmt() == null ? 0 : Integer.parseInt(bondTdsDeductionReportDTO.getNetAmt());


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

    @Override
    public byte[] pdfFileGeneratorForTDSReport(List<Map<String, Object>> results,
                                               BondTdsDeductionReportDTO bondTdsDeductionReportDTO) throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PDDocument document = new PDDocument();

        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        float margin = 20;
        float yStart = page.getMediaBox().getHeight() - margin;
        float bottomMargin = 20;
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Header Title
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 20);
        contentStream.newLineAtOffset((page.getMediaBox().getWidth() / 2) - 200, yStart);
        contentStream.showText("TDS Deduction – Report");
        contentStream.endText();
        contentStream.close();

        yStart -= 40;

        // ---------- First Basic Details Table ----------
        BaseTable infoTable = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> infoRow1 = infoTable.createRow(20);
        infoRow1.createCell(40, "Bank Name");
        infoRow1.createCell(60, bankName);

        be.quodlibet.boxable.Row<PDPage> infoRow2 = infoTable.createRow(20);
        infoRow2.createCell(40, "Branch Name");
        infoRow2.createCell(60, bondTdsDeductionReportDTO.getBranchName());

        be.quodlibet.boxable.Row<PDPage> infoRow3 = infoTable.createRow(20);
        infoRow3.createCell(40, "Branch Id");
        infoRow3.createCell(60, UserAccountUtils.getCurrentSolId());

        infoTable.draw();

        yStart = infoTable.draw() - 30f;

        // ---------- Main Table ----------
        BaseTable table = new BaseTable(yStart, yStart, bottomMargin, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> headerRow = table.createRow(25);
        headerRow.setHeaderRow(true);

        String[] headers = {
                "S.No", "Branch Id", "Bond BLA No", "Bond Holder Name", "PAN", "Subscription ID",
                "Subscription Amount Rs", "Date of Deduction of TDS", "Interest Amount Rs",
                "TDS Amount Rs", "Net Amount Paid Rs"
        };

        for (String h : headers) {
            be.quodlibet.boxable.Cell<PDPage> cell = headerRow.createCell(100f / headers.length, h);
            cell.setFont(PDType1Font.HELVETICA_BOLD);
            cell.setAlign(be.quodlibet.boxable.HorizontalAlignment.CENTER);
        }

        double totIntAmt = 0, totTds = 0, totNet = 0;
        int index = 1;

        for (Map<String, Object> item : bondTdsDeductionReportDTO.getData()) {

            double currInt = item.get("INTEREST_AMT") == null ? 0 : Double.parseDouble(item.get("INTEREST_AMT").toString());
            double currTds = item.get("CURRENT_TDS_AMOUNT") == null ? 0 : Double.parseDouble(item.get("CURRENT_TDS_AMOUNT").toString());
            double currNet = item.get("NET_INTEREST_AMOUNT") == null ? 0 : Double.parseDouble(item.get("NET_INTEREST_AMOUNT").toString());

            totIntAmt += currInt;
            totTds += currTds;
            totNet += currNet;

            be.quodlibet.boxable.Row<PDPage> row = table.createRow(20);
            row.createCell(100f / headers.length, String.valueOf(index++));
            row.createCell(100f / headers.length, String.valueOf(item.get("BRANCH_ID")));
            row.createCell(100f / headers.length, String.valueOf(item.get("BOND_LEDGER_ACCOUNT_NUMBER")));
            row.createCell(100f / headers.length, String.valueOf(item.get("Holder_Name")));
            row.createCell(100f / headers.length, String.valueOf(item.get("PAN_NO")));
            row.createCell(100f / headers.length, String.valueOf(item.get("BOND_SUBSCRIPTION_ID")));
            row.createCell(100f / headers.length, String.valueOf(item.get("SUBSCRIPTION_AMOUNT")));
            row.createCell(100f / headers.length, String.valueOf(item.get("Date_of_Deduction_of_TDS")));
            row.createCell(100f / headers.length, String.valueOf(currInt));
            row.createCell(100f / headers.length, String.valueOf(currTds));
            row.createCell(100f / headers.length, String.valueOf(currNet));
        }

        // ---------- Total Row ----------
        be.quodlibet.boxable.Row<PDPage> totalRow = table.createRow(22);
        for (int i = 0; i < 7; i++) totalRow.createCell(100f / headers.length, "");

        totalRow.createCell(100f / headers.length, "Total").setFont(PDType1Font.HELVETICA_BOLD);
        totalRow.createCell(100f / headers.length, String.valueOf(totIntAmt));
        totalRow.createCell(100f / headers.length, String.valueOf(totTds));
        totalRow.createCell(100f / headers.length, String.valueOf(totNet));

        table.draw();

        document.save(baos);
        document.close();

        return baos.toByteArray();
    }

    @Override
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

//        sheet.addMergedRegion(new CellRangeAddress(n, n, 0, 1));


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


    @Override
    public byte[] pdfFileGeneratorForInterestPayMisReport(HashMap<String, Object> resultMap) throws IOException {

        List<RbiBondInterestPaymentMisReportDto> list =
                (List<RbiBondInterestPaymentMisReportDto>) resultMap.get("dataList");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // Create PDF Document
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A1.getHeight(), PDRectangle.A1.getWidth()));
        document.addPage(page);

        PDPageContentStream content = new PDPageContentStream(document, page);
        float margin = 40;
        float yStart = PDRectangle.A4.getWidth() - 50;
        float tableWidth = PDRectangle.A4.getHeight() - (2 * margin);

        // ================= HEADER TITLE =================
        content.beginText();
        content.setFont(PDType1Font.HELVETICA_BOLD, 16);
        content.newLineAtOffset((tableWidth / 2) - 150, yStart + 10);
        content.showText("BOND INTEREST PAYMENT MIS REPORT - SOL WISE");
        content.endText();
        content.close();

        // ================= BANK INFO TABLE =================
        BaseTable infoTable = new BaseTable(yStart, yStart, 20, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> row = infoTable.createRow(20);
        row.createCell(30, "Bank Name");
        row.createCell(70, bankName);

        row = infoTable.createRow(20);
        row.createCell(30, "Branch Name");
        row.createCell(70, resultMap.get("solDesc").toString());

        row = infoTable.createRow(20);
        row.createCell(30, "Branch Id");
        row.createCell(70, UserAccountUtils.getCurrentSolId());

        // ✅ Draw and capture new y-position
        float yPos = infoTable.draw() - 30;

        // ================= MAIN DATA TABLE =================
        BaseTable dataTable = new BaseTable(yPos, yPos, 20, tableWidth, margin, document, page, true, true);

        // Header
        be.quodlibet.boxable.Row<PDPage> headerRow = dataTable.createRow(25);
        headerRow.createCell(10, "S.No");
        headerRow.createCell(20, "Branch Id");
        headerRow.createCell(23, "Gross Interest Amount\nRs.");
        headerRow.createCell(23, "TDS Amount\nRs.");
        headerRow.createCell(24, "Net Interest Amount\nRs.");

        // Data Rows
        for (RbiBondInterestPaymentMisReportDto item : list) {
            be.quodlibet.boxable.Row<PDPage> dataRow = dataTable.createRow(20);
            dataRow.createCell(10, item.getsNo());
            dataRow.createCell(20, item.getBranchId());
            dataRow.createCell(23, item.getGrossIntAmt());
            dataRow.createCell(23, item.getTdsAmt());
            dataRow.createCell(24, item.getNetIntAmt());
        }

        yPos = dataTable.draw() - 30;

        // ================= TOTAL TABLE =================
        BaseTable totalTable = new BaseTable(yPos, yPos, 20, tableWidth, margin, document, page, true, true);

        be.quodlibet.boxable.Row<PDPage> totalRow = totalTable.createRow(25);
        totalRow.createCell(10, "");
        totalRow.createCell(20, "Total");
        totalRow.createCell(23, resultMap.get("totGrossIntAmt").toString());
        totalRow.createCell(23, resultMap.get("totTdsAmt").toString());
        totalRow.createCell(24, resultMap.get("totNetIntAmt").toString());

        totalTable.draw();

        // ================= CLOSE DOCUMENT =================
        document.save(baos);
        document.close();

        System.out.println("PDF created successfully!");
        return baos.toByteArray();
    }



    @Override
    public ResponseDataDTO getBondBalanceReport(BondBalanceReportDTO bondBalanceReportDTO) {
        return rbiBondReportsDAO.getBondBalanceReport(bondBalanceReportDTO);
    }

    @Override
    public ResponseDataDTO getBondBalanceReportDownload(BondBalanceReportDTO bondBalanceReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getBondBalanceReport(bondBalanceReportDTO), bondBalanceReportDTO)));
            fileResponseDto.setFileName("BOND_BALANCE_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in BondBalanceReportDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getPanValidationReportDownload(PANValidationReportDTO panValidationReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generatePANValidationReportExcel(rbiBondReportsDAO.getPanValidationBondSchemeReport( panValidationReportDTO), panValidationReportDTO)));
            fileResponseDto.setFileName("PAN_VALIDATION_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in PANValidationReportDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }



    @Override
    public ResponseDataDTO getTaxWaiverDocumentsReport(TaxWaiverDocumentsReportDTO taxWaiverDocumentsReportDTO) {
        return rbiBondReportsDAO.getTaxWaiverDocumentsReport(taxWaiverDocumentsReportDTO);
    }
    @Override
    public ResponseDataDTO getPreMaturityMarkingReportDownload(PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generatePreMaturityMarkingReportExcel(rbiBondReportsDAO.getPreMaturityMarkingReport( preMaturityMarkingReportDTO), preMaturityMarkingReportDTO)));
            fileResponseDto.setFileName("PreMaturity_Marking_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in PreMaturityMarkingReportDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generatePreMaturityMarkingReportExcel(ResponseDataDTO responseDataDTO,PreMaturityMarkingReportDTO preMaturityMarkingReportDTO) {
        List<PreMaturityMarkingReportDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        BigDecimal totalCurrentBalance = BigDecimal.ZERO;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("PRE MATURITY MARKING REPORT");


            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);


            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);

            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "Pan_No",
                    "Holder_Name",
                    "Age",
                    "BLA_No",
                    "Mode_Of_Holding",
                    "Subscription_Id",
                    "Pre_Maturity_Date",
                    "Account_Open_Date",
                    "Maturity_Date",
                    "Subscription_Date",
                    "Status",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<PreMaturityMarkingReportDTO>) finalData.get("preMaturityMarkingReportDTOList");

            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (PreMaturityMarkingReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getAge() != null ? data.getAge() : "",
                           // data.getDOB() != null ? formatDate(data.getDOB() ): "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                            data.getSubscriptionId() != null ? data.getSubscriptionId(): "",
                            data.getPreMaturityMarkedDate() != null ? formatDate(data.getPreMaturityMarkedDate()): "",
                            data.getAccOpenDate() != null ? formatDate(data.getAccOpenDate()): "",
                            data.getMaturityDate() != null ?formatDate( data.getMaturityDate()) : "",
                            data.getSubscriptionAmt() != null ? data.getSubscriptionAmt() : "",
                            data.getStatus() != null ? data.getStatus() : "",
                    };


                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            SXSSFRow totalRow = sheet.createRow(rowNumber++);
//            totalRow.createCell(5).setCellValue("Total");
//            totalRow.createCell(6).setCellValue(totalCurrentBalance.setScale(2, RoundingMode.HALF_UP).toString());
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] generatePANValidationReportExcel(ResponseDataDTO responseDataDTO,PANValidationReportDTO panValidationReportDTO) {
        List<PANValidationReportDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        BigDecimal totalCurrentBalance = BigDecimal.ZERO;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("PAN VALIDATION REPORT");


            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);


            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);

            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "PAN Triggered From",
                    "Triggered  Date",
                    "Triggered ID",
                    "Pan_No",
                    "New_Pan_No",
                    "Name",
                    "New_Name",
                    "DOB",
                    "New_DOB",
                    "Aadhar_Seeding",
                    "Is Send NSDL",
                    "SendNSDL_Date",
                    "Status",
                    "DOB_Status",
                    "Pan_No_Status",
                    "Name_Status",
                    "Aadhar_Status",
                    "File_Type",
                    "Remark",
                    "UploadBy",
                    "Upload_Date",
                    "Modify_By",
                    "Modify_Date",
                    "Re Send NSDL",
                    "ReSendNSDL_Date",
                    "NetBanking_Date",
                    "Audit_Date",
                    "Upload_FileName",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<PANValidationReportDTO>) finalData.get("panValidationReportDTOList");

            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (PANValidationReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getPanTriggeredFrom() != null ? data.getPanTriggeredFrom() : "",
                            data.getTriggedDate() != null ? formatDate(data.getTriggedDate()) : "",
                            data.getTriggedCheckerID() != null ? data.getTriggedCheckerID() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getNewPANNO() != null ? data.getNewPANNO() : "",
                            data.getName() != null ? data.getName() : "",
                            data.getNewName() != null ? data.getNewName() : "",
                            data.getDOB() != null ? formatDate(data.getDOB() ): "",
                            data.getNewDOB() != null ?formatDate( data.getNewDOB()) : "",
                            data.getAadharSeeding() != null ? data.getAadharSeeding() : "",
                            data.getIsSendNSDL() != null ? data.getIsSendNSDL() : "",
                            data.getSendNSDLDate() != null ? formatDate(data.getSendNSDLDate()) : "",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getDOBStatus() != null ? data.getDOBStatus() : "",
                            data.getPanNoStatus() != null ? data.getPanNoStatus() : "",
                            data.getNameStatus() != null ? data.getNameStatus() : "",
                            data.getAadharStatus() != null ? data.getAadharStatus() : "",
                            data.getFileType() != null ? data.getFileType() : "",
                            data.getRemark() != null ? data.getRemark() : "",
                            data.getUploadBy() != null ? data.getUploadBy() : "",
                            data.getUploadDate() != null ? formatDate(data.getUploadDate()) : "",
                            data.getModifyBy() != null ? data.getModifyBy() : "",
                            data.getModifyDate() != null ? formatDate(data.getModifyDate()) : "",
                            data.getReSendNSDL() != null ? data.getReSendNSDL() : "",
                            data.getReSendNSDLDate() != null ? formatDate(data.getReSendNSDLDate()): "",
                            data.getNetBankingDate() != null ?formatDate( data.getNetBankingDate()) : "",
                            data.getAuditDate() != null ? data.getAuditDate() : "",
                            data.getUploadFileName() != null ? data.getUploadFileName() : "",
                    };


                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            SXSSFRow totalRow = sheet.createRow(rowNumber++);
            totalRow.createCell(5).setCellValue("Total");
            totalRow.createCell(6).setCellValue(totalCurrentBalance.setScale(2, RoundingMode.HALF_UP).toString());
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    private String formatDate(String dateStr) {
        try {
            if (dateStr == null || dateStr.trim().isEmpty()) return "";

            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
            return new SimpleDateFormat("dd/MM/yyyy").format(date);

        } catch (Exception e) {
            return "";
        }
    }


    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, BondBalanceReportDTO bondBalanceReportDTO) {
        List<BondBalanceReportDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        BigDecimal totalCurrentBalance = BigDecimal.ZERO;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BOND BALANCE REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "BLA No.",
                    "Subscription ID",
                    "Bond Type",
                    "Subscription Date",
                    "Subscription Maturity Date",
                    "Current Balance (Rs)",
                    "Subscription Status",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BondBalanceReportDTO>) finalData.get("bondBalanceReportDTOList");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondBalanceReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getSubscriptionId() != null ? data.getSubscriptionId() : "",
                            data.getBondType() != null ? data.getBondType() : "",
                            data.getSubscriptionDate() != null ? data.getSubscriptionDate() : "",
                            data.getSubscriptionMaturityDate() != null ? data.getSubscriptionMaturityDate() : "",
                            data.getCurrentBalance() != null ? roundBalance(data.getCurrentBalance(), 2) : "",
                            data.getSubStatus() != null ? data.getSubStatus() : "",
                    };

                    if (data.getCurrentBalance() != null && !data.getCurrentBalance().isEmpty()) {
                        BigDecimal currentBalance = new BigDecimal(data.getCurrentBalance());
                        totalCurrentBalance = totalCurrentBalance.add(currentBalance.setScale(2, RoundingMode.HALF_UP));
                    }
                    //log.info("cellValues.length: "+cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            SXSSFRow totalRow = sheet.createRow(rowNumber++);
            totalRow.createCell(5).setCellValue("Total");
            totalRow.createCell(6).setCellValue(totalCurrentBalance.setScale(2, RoundingMode.HALF_UP).toString());
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getReportOnTransactionDetails(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        return rbiBondReportsDAO.getReportOnTransactionDetails(reportTransactionDetailsDTO);
    }

    @Override
    public ResponseDataDTO getReportOnTransactionDetailsDownload(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getReportOnTransactionDetails(reportTransactionDetailsDTO),reportTransactionDetailsDTO)));
            fileResponseDto.setFileName("REPORT_ON_TRANSACTION_DETAILS_FOR_BOND.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in ReportTransactionDetailsDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        List<ReportTransactionDetailsDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("REPORT ON TRANSACTION DETAILS FOR BOND");

            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Bank info row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);

            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Header row
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "PAN NO.",
                    "BOND BLA NO.",
                    "HOLDER NAME",
                    "BOND SUBSCRIPTION ID",
                    "BROKER CODE",
                    "BROKER NAME",
                    "BROKER GROUP",
                    "MODE OF TRANSACTION",
                    "SUBSCRIPTION AMOUNT RS.",
                    "TRANSACTION DATE"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<ReportTransactionDetailsDTO>) finalData.get("list");

            int rowNumber = 3;
            int sNo = 1;
            double totalSubscriptionAmount = 0.0;

            if (!list.isEmpty()) {
                for (ReportTransactionDetailsDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getPanNumber() != null ? data.getPanNumber() : "",
                            data.getBlaNumber() != null ? data.getBlaNumber() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getSubscriptionID() != null ? data.getSubscriptionID() : "",
                            data.getBrokerCode() != null ? data.getBrokerCode() : "",
                            data.getBrokerName() != null ? data.getBrokerName() : "",
                            data.getBrokerType() != null ? data.getBrokerType() : "",
                            data.getModeOfTransaction() != null ? data.getModeOfTransaction() : "",
                            data.getSubscriptionAmount() != null ? data.getSubscriptionAmount() : "",
                            data.getTransactionDate() != null ? data.getTransactionDate() : "",
                    };

                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }

                    // Sum up the subscription amount
                    if (data.getSubscriptionAmount() != null && !data.getSubscriptionAmount().isEmpty()) {
                        try {
                            totalSubscriptionAmount += Double.parseDouble(data.getSubscriptionAmount());
                        } catch (NumberFormatException e) {
                            log.warn("Invalid subscription amount: " + data.getSubscriptionAmount());
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }

            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            // Total amount row
            SXSSFRow totalRow = sheet.createRow(rowNumber);
            totalRow.createCell(8).setCellValue("Total Amount Rs.");
            totalRow.createCell(9).setCellValue(String.format("%.2f", totalSubscriptionAmount));

            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }

        return byteArrayOutputStream.toByteArray();
    }


    @Override
    public ResponseDataDTO getBondInvestmentDueNoticeReport(BondInvestmentDueDateNoticeDTO bondInvestmentDueDateNoticeDTO) {
        return rbiBondReportsDAO.getBondInvestmentDueNoticeReport(bondInvestmentDueDateNoticeDTO);
    }

    @Override
    public ResponseDataDTO getReportTransactionSubcriptionDeatails(ReportTransactionDetailsDTO reportTransactionDetailsDTO) {
        return rbiBondReportsDAO.getReportTransactionSubcriptionDeatails(reportTransactionDetailsDTO);
    }


//    @Override
//    public ResponseDataDTO getBondLedgerAccDet(BondLedgerAccDto bondLedgerAccDto) {
//        return rbiBondReportsDAO.getBondLedgerAccDet(bondLedgerAccDto);
//    }

    @Override
    public ResponseDataDTO getReportTorchBla(BondTdsCollDeductionReportDTO bondTdsCollDeductionReportDTO) {
        return rbiBondReportsDAO.getReportTorchBla(bondTdsCollDeductionReportDTO);
    }

    @Override
    public ResponseDataDTO getDailyRemittanceLcReport(BondDailyRemLcDTO bondDailyRemLcDTO) {
        return rbiBondReportsDAO.getDailyRemittanceLcReport(bondDailyRemLcDTO);
    }

    @Override
    public ResponseDataDTO getSubsAccDetails(SubscriptionAccountDTO subscriptionAccountDTO) {
        return rbiBondReportsDAO.getSubsAccDetails(subscriptionAccountDTO);
    }

    @Override
    public ResponseDataDTO getSubsAccDetailsDownload(SubscriptionAccountDTO subscriptionAccountDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getSubsAccDetails(subscriptionAccountDTO), subscriptionAccountDTO)));
            fileResponseDto.setFileName("SUBSCRIPTION_ACCOUNT_OPENED_DETAILS.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in SubscriptionAccountDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, SubscriptionAccountDTO subscriptionAccountDTO) {
        List<SubscriptionAccountDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("SUBSCRIPTION ACCOUNT OPENED DETAILS REPORT");

            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));

            // Bank info row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);

            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 10));

            // Header row
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "Mode of Investment",
                    "BLA No.",
                    "Holder Name",
                    "Holder PAN No",
                    "Subscription ID",
                    "Date of Subscription",
                    "Maturity Date",
                    "Mode of Holding",
                    "Subscription Amount Rs",
                    "Subscription Status"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<SubscriptionAccountDTO>) finalData.get("list");

            // Data population
            int rowNumber = 3;
            int sNo = 1;
            double totalSubscriptionAmount = 0;

            if (!list.isEmpty()) {
                for (SubscriptionAccountDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfInvestment() != null ? data.getModeOfInvestment() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getHolderPanNo() != null ? data.getHolderPanNo() : "",
                            data.getSubsId() != null ? data.getSubsId() : "",
                            data.getDateOfSubs() != null ? data.getDateOfSubs() : "",
                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                            data.getSubsAmount() != null ? data.getSubsAmount() : "",
                            data.getSubsStatus() != null ? data.getSubsStatus() : ""
                    };

                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }

                    // Sum subscription amount
                    try {
                        if (data.getSubsAmount() != null && !data.getSubsAmount().isEmpty()) {
                            totalSubscriptionAmount += Double.parseDouble(data.getSubsAmount());
                        }
                    } catch (NumberFormatException e) {
                        log.warn("Invalid subscription amount: " + data.getSubsAmount());
                    }
                }

                // Total row
                SXSSFRow totalRow = sheet.createRow(rowNumber);
                totalRow.createCell(8).setCellValue("Total");
                totalRow.createCell(9).setCellValue(String.format("%.2f", totalSubscriptionAmount));
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }

            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }

        return byteArrayOutputStream != null ? byteArrayOutputStream.toByteArray() : null;
    }

    @Override
    public ResponseDataDTO getSubsAmtDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsDAO.getSubsAmtDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getAccClosedDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsDAO.getAccClosedDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getBlaAccNumDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsDAO.getBlaAccNumDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getHolderTypeDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsDAO.getHolderTypeDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getMaturedAccDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsDAO.getMaturedAccDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getMinorAccDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsDAO.getMinorAccDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getNriAccDetails(BondInquiryDto bondInquiryDto) {
        return rbiBondReportsDAO.getNriAccDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getTransferInOutDetails(TransferInOutDTO bondInquiryDto) {
        return rbiBondReportsDAO.getTransferInOutDetails(bondInquiryDto);
    }

    @Override
    public ResponseDataDTO getTransferInOutDownload(TransferInOutDTO transferInOutDTO) {


        if (transferInOutDTO.getDownloadType().equalsIgnoreCase("csv")) {

            return rbiBondReportsDAO.getTransferInOutDetails(transferInOutDTO);
        } else {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getTransferInOutDetails(transferInOutDTO), transferInOutDTO)));
                fileResponseDto.setFileName("TRANSFER_IN_TRANSFER_OUT_REPORT.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in TransferInOutDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;

        }
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, TransferInOutDTO transferInOutDTO) {
        List<TransferInOutDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        BigDecimal subscriptionAmountt = BigDecimal.ZERO;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("TRANSFER IN TRANSFER OUT REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));

// === Secondary header row (Row 3) ===
            SXSSFRow headerRow = sheet.createRow(2); // Use row 3 instead of 2

            String[] headers = {
                    "S.No.",
                    "BLA No",
                    "Holder Name",
                    "Subscription ID",
                    "Subscription Amount Rs.",
                    "Transfer In Bank",
                    "Transfer In Bank Branch",
                    "Transferred  in Date",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<TransferInOutDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (TransferInOutDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getBlaNumber() != null ? data.getBlaNumber() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getSubscriptionID() != null ? data.getSubscriptionID() : "",
                            data.getSubscriptionAmount() != null ? data.getSubscriptionAmount() : "",
                            data.getTransferInBankName() != null ? data.getTransferInBankName() : "",
                            data.getTransferInBranchName() != null ? data.getTransferInBranchName() : "",
                            data.getTransferDate() != null ? data.getTransferDate() : "",




                    };


                    if (data.getSubscriptionAmount() != null && !data.getSubscriptionAmount().isEmpty()) {
                        BigDecimal subscriptionAmount = new BigDecimal(data.getSubscriptionAmount());
                        subscriptionAmountt = subscriptionAmountt.add(subscriptionAmount.setScale(2, RoundingMode.HALF_UP));
                    }


                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            log.info("Processed Data rows: "+ rowNumber);

            SXSSFRow totalRow = sheet.createRow(rowNumber++);
            totalRow.createCell(3).setCellValue("Total");
            totalRow.createCell(4).setCellValue(subscriptionAmountt.setScale(2, RoundingMode.HALF_UP).toString());
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getBrokerageInvoiceBondScheme(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return rbiBondReportsDAO.getBrokerageInvoiceBondScheme(brokerageInvoiceDownloadDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageAgencyCode(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return rbiBondReportsDAO.getBrokerageAgencyCode(brokerageInvoiceDownloadDTO);
    }


    @Override
    public ResponseDataDTO generateBrokerRes(BrokerageInvoiceDownloadDTO brokerageInvoiceDownloadDTO) {
        return rbiBondReportsDAO.generateBrokerRes(brokerageInvoiceDownloadDTO);
    }


    @Override
    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return rbiBondReportsDAO.unlockBla(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO unlockBlaSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return rbiBondReportsDAO.unlockBlaSubmit(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getPendingBrokeragePaymentClaimDetails(PendingBrokeragePaymentClaimStatusDTO pendingBrokeragePaymentClaimStatusDTO) {
        return rbiBondReportsDAO.getPendingBrokeragePaymentClaimDetails(pendingBrokeragePaymentClaimStatusDTO);
    }

    @Override
    public ResponseDataDTO getBondActualInterestRunCreditedDetails(BondActualInterestRunCreditedDTO bondActualInterestRunCreditedDTO) {

        if(bondActualInterestRunCreditedDTO.getDownloadType().equalsIgnoreCase("N")) {
            return rbiBondReportsDAO.getBondActualInterestRunCreditedDetails(bondActualInterestRunCreditedDTO);
        }
        else if(bondActualInterestRunCreditedDTO.getDownloadType().equalsIgnoreCase("M")) {


            // return rbiBondReportsDAO.getBondActualInterestRunCreditedDetails(bondActualInterestRunCreditedDTO);

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();


            ResponseDataDTO fileResponseDto1 =     rbiBondReportsDAO.getBondActualInterestRunCreditedDetails(bondActualInterestRunCreditedDTO);


            if(fileResponseDto1.getMessage().equalsIgnoreCase("SUCCESS")) {

                try {

                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(getBondActualInterestMergedRunCreditedDetailsExcel(fileResponseDto1, bondActualInterestRunCreditedDTO)));
                    fileResponseDto.setFileName("BOND _ACTUAL_INTEREST_RUN_CREDITED_MERGED_DETAILS.xlsx");
                    fileResponseDto1.setResult(fileResponseDto);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("Error in BondActualInterestRunCreditedDTO, ReportsBOImpl: " + e.getMessage());
                    resultMsg = CommonConstants.NDF;
                    fileResponseDto1.setMessage(resultMsg);
                }


                fileResponseDto1.setMessage(resultMsg);
                return responseDataDTO;

            }

            else{

                return fileResponseDto1;
            }



        }
        else{
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();



            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(getBondActualInterestRunCreditedDetailsExcel(rbiBondReportsDAO.getBondActualInterestRunCreditedDetails(bondActualInterestRunCreditedDTO), bondActualInterestRunCreditedDTO)));
                fileResponseDto.setFileName("BOND _ACTUAL_INTEREST_RUN_CREDITED_UN_MERGED_DETAILS.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in BondActualInterestRunCreditedDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        }
    }


    public byte[] getBondActualInterestRunCreditedDetailsExcel(ResponseDataDTO responseDataDTO, BondActualInterestRunCreditedDTO bondActualInterestRunCrediteedDTO) {
        List<BondActualInterestRunCreditedDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        BigDecimal subscriptionAmounttt = BigDecimal.ZERO;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Bond Actual Interest Run Credited Report");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK"+     "            Branch Name : " + solDesc + "                      Branch Id : " + loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));






            SXSSFRow bankSchmeNameRow = sheet.createRow(2);
            SXSSFCell bankSchmeNameRowCell = bankSchmeNameRow.createCell(0);
            bankSchmeNameRowCell.setCellValue("Name of the Bond Scheme:: " + bondActualInterestRunCrediteedDTO.getSchemeName());

            // Create and set branch info cell style
            CellStyle bankInfoCellStylee = workbook.createCellStyle();
            bankInfoCellStylee.setAlignment(HorizontalAlignment.CENTER);
            bankSchmeNameRowCell.setCellStyle(bankInfoCellStylee);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 9));









            SXSSFRow fromDateTODateRow = sheet.createRow(3);
            SXSSFCell fromDateTODateCell = fromDateTODateRow.createCell(0);
            fromDateTODateCell.setCellValue("Interest Payment From Date : " + bondActualInterestRunCrediteedDTO.getFromDate() + "                           Interest Payment To Date : " + bondActualInterestRunCrediteedDTO.getToDate());

            // Create and set branch info cell style
            CellStyle fromDateToDateStylee = workbook.createCellStyle();
            fromDateToDateStylee.setAlignment(HorizontalAlignment.CENTER);
            fromDateTODateCell.setCellStyle(fromDateToDateStylee);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 9));



            // === Secondary header row (Row 3) ===
            SXSSFRow headerRoww = sheet.createRow(4);


            String[] headerss = {
                    "",
                    "Basic Details From BLA cum Subscription",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "OTHER SCHEMES INTEREST RELATED INFO",
                    "",
                    "",
                    "",
                    "",
                    "PAN STATUS",
                    "",
                    "TDS EXEMPTION DETAILS",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "FRSB INTEREST PAYOUT DETAILS",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    ""


            };

            CellStyle headerCellStylee = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            headerCellStylee.setFont(font);

            // Track maximum column widths
            int[] maxColumnWidthss = new int[headerss.length];
            for (int i = 0; i < headerss.length; i++) {
                SXSSFCell celll = headerRoww.createCell(i);
                celll.setCellValue(headerss[i]);
                celll.setCellStyle(headerCellStylee);
                maxColumnWidthss[i] = headerss[i].length();
            }












            /// Use row 3 instead of 2
            SXSSFRow headerRow = sheet.createRow(5);

            String[] headers = {
                    "S.No",
                    "Mode of Payment",
                    "Branch Ref No",
                    "BLA No",
                    "Ref No",
                    "App Name 1",
                    "App Name 2",
                    "App Name 3",
                    "Address 1",
                    "Address 2",
                    "Rec Name",
                    "Rec Parent Name",
                    "Pin Code",
                    "Phone No",
                    "Application Status",
                    "Investment Amount",
                    "CFD",
                    "Maturity Date",
                    "Account No",
                    "Subscription Count",
                    "Bank Sort Code",
                    "Bank Name",
                    "Pan No Name",
                    "8 % Interest Payout",
                    "Non Cumulative Int",
                    "Cumulative Int",
                    "7.75% interest payout",
                    "Non Cumulative Int 775",
                    "Cumulative Int 775",
                    "Total",
                    "In-Operative PAN",
                    "Non-Filers",
                    "Age",
                    "Taxable Limit",
                    "Waiver Document",
                    "Waiver Document Ref No",
                    "RRB-TDS rate",
                    "Threshold Exceed Amount",
                    "Threshold Exceed TDS Amount",
                    "Exemption Rate %",
                    "Exemption Amount",
                    "Exemption TDS Amount",
                    "Surcharge %",
                    "Edu Cess %",
                    "Base Rate Amount",
                    "Surcharge Amount",
                    "Edu cess Amount",
                    "TDS Amount",
                    "TDS Recovery Amount",
                    "TDS Refund Amount",
                    "Int Rate",
                    "Last Int Paid",
                    "Pending Interest",
                    "Current Half-Yearly Interest",
                    "Future Interest",
                    "Gross Interest Amount",
                    "Int Paid (Net Interest Amount)",
                    "Payment Date",
                    "Return Reason",
                    "Return Amount",
                    "ReInitiated Amount",
                    "ReInitiated Date",
                    "Payment Date",
                    "ReInitiate Payment Mode"


            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font fontt = workbook.createFont();
            fontt.setBold(true);
            headerCellStyle.setFont(fontt);

            // Track maximum column widths
            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<BondActualInterestRunCreditedDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 6;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondActualInterestRunCreditedDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
                            data.getBranchRefNo() != null ? data.getBranchRefNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getRefNo() != null ? data.getRefNo() : "",
                            data.getAppName1() != null ? data.getAppName1() : "",
                            data.getAppName2() != null ? data.getAppName2() : "",
                            data.getAppName3() != null ? data.getAppName3() : "",
                            data.getAddress1() != null ? data.getAddress1() : "",
                            data.getAddress2() != null ? data.getAddress2() : "",
                            data.getRecName() != null ? data.getRecName() : "",
                            data.getRecParentName() != null ? data.getRecParentName() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                            data.getPhoneNo() != null ? data.getPhoneNo() : "",
                            data.getAppStatus() != null ? data.getAppStatus() : "",
                            data.getInvestmentAmount() != null ? data.getInvestmentAmount() : "",
                            data.getCfd() != null ? data.getCfd() : "",
                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getSubscriptionCount() != null ? data.getSubscriptionCount() : "",
                            data.getBankSortCode() != null ? data.getBankSortCode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getPanNoName() != null ? data.getPanNoName() : "",
                            data.getInterestPayOut8Per() != null ? data.getInterestPayOut8Per() : "",
                            data.getNonCumulativeInt() != null ? data.getNonCumulativeInt() : "",
                            data.getCumulativeInt() != null ? data.getCumulativeInt() : "",
                            data.getInterestPayOut7Per() != null ? data.getInterestPayOut7Per() : "",
                            data.getNonCumulativeIntFor7() != null ? data.getNonCumulativeIntFor7() : "",
                            data.getCumulativeIntFor7() != null ? data.getCumulativeIntFor7() : "",
                            data.getTotal() != null ? data.getTotal() : "",
                            data.getInOperativePAN() != null ? data.getInOperativePAN() : "",
                            data.getNonFilers() != null ? data.getNonFilers() : "",
                            data.getAge() != null ? data.getAge() : "",
                            data.getTaxableLimit() != null ? data.getTaxableLimit() : "",
                            data.getWaiverDocument() != null ? data.getWaiverDocument() : "",
                            data.getWaiverDocumentRefNo() != null ? data.getWaiverDocumentRefNo() : "",
                            data.getRrbTDSRate() != null ? data.getRrbTDSRate() : "",
                            data.getThresholdExceedAmount() != null ? data.getThresholdExceedAmount() : "",
                            data.getThresholdExceedTDSAmount() != null ? data.getThresholdExceedTDSAmount() : "",
                            data.getExemptionRate() != null ? data.getExemptionRate() : "",
                            data.getExemptionAmount() != null ? data.getExemptionAmount() : "",
                            data.getExemptionTDSAmount() != null ? data.getExemptionTDSAmount() : "",
                            data.getSurcharge() != null ? data.getSurcharge() : "",
                            data.getEduCess() != null ? data.getEduCess() : "",
                            data.getBaseRateAmount() != null ? data.getBaseRateAmount() : "",
                            data.getSurchargeAmount() != null ? data.getSurchargeAmount() : "",
                            data.getEduCessAmount() != null ? data.getEduCessAmount() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getTdsRecoveryAmount() != null ? data.getTdsRecoveryAmount() : "",
                            data.getTdsRefundAmount() != null ? data.getTdsRefundAmount() : "",
                            data.getIntRate() != null ? data.getIntRate() : "",
                            data.getLastIntPaid() != null ? data.getLastIntPaid() : "",
                            data.getPendingInterest() != null ? data.getPendingInterest() : "",
                            data.getCurrentHalfYearlyInterest() != null ? data.getCurrentHalfYearlyInterest() : "",
                            data.getFutureInterest() != null ? data.getFutureInterest() : "",
                            data.getGrossInterestAmount() != null ? data.getGrossInterestAmount() : "",
                            data.getIntPaid() != null ? data.getIntPaid() : "",
                            data.getPaymentDate() != null ? data.getPaymentDate() : "",
                            data.getReturnReason() != null ? data.getReturnReason() : "",
                            data.getReturnAmt() != null ? data.getReturnAmt() : "",
                            data.getReInitiatedAmt() != null ? data.getReInitiatedAmt() : "",
                            data.getReInitiatedDate() != null ? data.getReInitiatedDate() : "",
                            data.getPaymentDateOfReturn() != null ? data.getPaymentDateOfReturn() : "",
                            data.getReInitiatedPaymentMode() != null ? data.getReInitiatedPaymentMode() : "",
                    };


                    if (data.getInvestmentAmount() != null && !data.getInvestmentAmount().isEmpty()) {
                        BigDecimal subscriptionAmountt = new BigDecimal(data.getInvestmentAmount());
                        subscriptionAmounttt = subscriptionAmounttt.add(subscriptionAmountt.setScale(2, RoundingMode.HALF_UP));
                    }
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);


            SXSSFRow totalRow = sheet.createRow(rowNumber++);
            totalRow.createCell(14).setCellValue("Total");
            totalRow.createCell(15).setCellValue(subscriptionAmounttt.setScale(2, RoundingMode.HALF_UP).toString());


            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }




    public byte[] getBondActualInterestMergedRunCreditedDetailsExcel(ResponseDataDTO responseDataDTO, BondActualInterestRunCreditedDTO bondActualInterestRunCrediteedDTO) {




        List<BondActualInterestRunCreditedDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        BigDecimal subscriptionAmounttt = BigDecimal.ZERO;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Bond Actual Interest Run Credited Report");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK"+     "            Branch Name : " + solDesc + "                      Branch Id : " + loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));






            SXSSFRow bankSchmeNameRow = sheet.createRow(2);
            SXSSFCell bankSchmeNameRowCell = bankSchmeNameRow.createCell(0);
            bankSchmeNameRowCell.setCellValue("Name of the Bond Scheme:: " + bondActualInterestRunCrediteedDTO.getSchemeName());

            // Create and set branch info cell style
            CellStyle bankInfoCellStylee = workbook.createCellStyle();
            bankInfoCellStylee.setAlignment(HorizontalAlignment.CENTER);
            bankSchmeNameRowCell.setCellStyle(bankInfoCellStylee);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 9));









            SXSSFRow fromDateTODateRow = sheet.createRow(3);
            SXSSFCell fromDateTODateCell = fromDateTODateRow.createCell(0);
            fromDateTODateCell.setCellValue("Interest Payment From Date : " + bondActualInterestRunCrediteedDTO.getFromDate() + "                           Interest Payment To Date : " + bondActualInterestRunCrediteedDTO.getToDate());

            // Create and set branch info cell style
            CellStyle fromDateToDateStylee = workbook.createCellStyle();
            fromDateToDateStylee.setAlignment(HorizontalAlignment.CENTER);
            fromDateTODateCell.setCellStyle(fromDateToDateStylee);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 9));



            // === Secondary header row (Row 3) ===
//            SXSSFRow headerRoww = sheet.createRow(4);
//
//
//            String[] headerss = {
//                    "",
//                    "Basic Details From BLA cum Subscription",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "OTHER SCHEMES INTEREST RELATED INFO",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "PAN STATUS",
//                    "",
//                    "TDS EXEMPTION DETAILS",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "FRSB INTEREST PAYOUT DETAILS",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    ""
//
//
//            };
//
//            CellStyle headerCellStylee = workbook.createCellStyle();
//            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
//            font.setBold(true);
//            headerCellStylee.setFont(font);
//
//            // Track maximum column widths
//            int[] maxColumnWidthss = new int[headerss.length];
//            for (int i = 0; i < headerss.length; i++) {
//                SXSSFCell celll = headerRoww.createCell(i);
//                celll.setCellValue(headerss[i]);
//                celll.setCellStyle(headerCellStylee);
//                maxColumnWidthss[i] = headerss[i].length();
//            }












            /// Use row 3 instead of 2
            SXSSFRow headerRow = sheet.createRow(4);

            String[] headers = {
                    "S.No",
                    "Mode of Payment",
//                    "Branch Ref No",
                    "BLA No",
//                    "Ref No",
                    "App Name 1",
                    "App Name 2",
                    "App Name 3",
                    "Address 1",
                    "Address 2",
                    "Rec Name",
                    "Rec Parent Name",
                    "Pin Code",
                    "Phone No",
                    "Application Status",
                    "Investment Amount",
//                    "CFD",
//                    "Maturity Date",
                    "Account No",
                    "Subscription Count",
                    "Bank Sort Code",
                    "Bank Name",
                    "Pan No Name",
                    "8 % Interest Payout",
                    "Non Cumulative Int",
                    "Cumulative Int",
                    "7.75% interest payout",
                    "Non Cumulative Int 775",
                    "Cumulative Int 775",
                    "Total",
                    "In-Operative PAN",
                    "Non-Filers",
                    "Age",
                    "Taxable Limit",
                    "Waiver Document",
                    "Waiver Document Ref No",
                    "RRB-TDS rate",
                    "Threshold Exceed Amount",
                    "Threshold Exceed TDS Amount",
                    "Exemption Rate %",
                    "Exemption Amount",
                    "Exemption TDS Amount",
                    "Surcharge %",
                    "Edu Cess %",
                    "Base Rate Amount",
                    "Surcharge Amount",
                    "Edu cess Amount",
                    "TDS Amount",
                    "TDS Recovery Amount",
                    "TDS Refund Amount",
                    "Int Rate",
                    "Last Int Paid",
                    "Pending Interest",
                    "Current Half-Yearly Interest",
                    "Future Interest",
                    "Gross Interest Amount",
                    "Int Paid (Net Interest Amount)",
                    "Payment Date",
                    "Return Reason",
                    "Return Amount",
                    "ReInitiated Amount",
                    "ReInitiated Date",
                    "Payment Date",
                    "ReInitiate Payment Mode"


            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font fontt = workbook.createFont();
            fontt.setBold(true);
            headerCellStyle.setFont(fontt);

            // Track maximum column widths
            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<BondActualInterestRunCreditedDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 6;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondActualInterestRunCreditedDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
//                            data.getBranchRefNo() != null ? data.getBranchRefNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
//                            data.getRefNo() != null ? data.getRefNo() : "",
                            data.getAppName1() != null ? data.getAppName1() : "",
                            data.getAppName2() != null ? data.getAppName2() : "",
                            data.getAppName3() != null ? data.getAppName3() : "",
                            data.getAddress1() != null ? data.getAddress1() : "",
                            data.getAddress2() != null ? data.getAddress2() : "",
                            data.getRecName() != null ? data.getRecName() : "",
                            data.getRecParentName() != null ? data.getRecParentName() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                            data.getPhoneNo() != null ? data.getPhoneNo() : "",
                            data.getAppStatus() != null ? data.getAppStatus() : "",
                            data.getInvestmentAmount() != null ? data.getInvestmentAmount() : "",
//                            data.getCfd() != null ? data.getCfd() : "",
//                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getSubscriptionCount() != null ? data.getSubscriptionCount() : "",
                            data.getBankSortCode() != null ? data.getBankSortCode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getPanNoName() != null ? data.getPanNoName() : "",
                            data.getInterestPayOut8Per() != null ? data.getInterestPayOut8Per() : "",
                            data.getNonCumulativeInt() != null ? data.getNonCumulativeInt() : "",
                            data.getCumulativeInt() != null ? data.getCumulativeInt() : "",
                            data.getInterestPayOut7Per() != null ? data.getInterestPayOut7Per() : "",
                            data.getNonCumulativeIntFor7() != null ? data.getNonCumulativeIntFor7() : "",
                            data.getCumulativeIntFor7() != null ? data.getCumulativeIntFor7() : "",
                            data.getTotal() != null ? data.getTotal() : "",
                            data.getInOperativePAN() != null ? data.getInOperativePAN() : "",
                            data.getNonFilers() != null ? data.getNonFilers() : "",
                            data.getAge() != null ? data.getAge() : "",
                            data.getTaxableLimit() != null ? data.getTaxableLimit() : "",
                            data.getWaiverDocument() != null ? data.getWaiverDocument() : "",
                            data.getWaiverDocumentRefNo() != null ? data.getWaiverDocumentRefNo() : "",
                            data.getRrbTDSRate() != null ? data.getRrbTDSRate() : "",
                            data.getThresholdExceedAmount() != null ? data.getThresholdExceedAmount() : "",
                            data.getThresholdExceedTDSAmount() != null ? data.getThresholdExceedTDSAmount() : "",
                            data.getExemptionRate() != null ? data.getExemptionRate() : "",
                            data.getExemptionAmount() != null ? data.getExemptionAmount() : "",
                            data.getExemptionTDSAmount() != null ? data.getExemptionTDSAmount() : "",
                            data.getSurcharge() != null ? data.getSurcharge() : "",
                            data.getEduCess() != null ? data.getEduCess() : "",
                            data.getBaseRateAmount() != null ? data.getBaseRateAmount() : "",
                            data.getSurchargeAmount() != null ? data.getSurchargeAmount() : "",
                            data.getEduCessAmount() != null ? data.getEduCessAmount() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getTdsRecoveryAmount() != null ? data.getTdsRecoveryAmount() : "",
                            data.getTdsRefundAmount() != null ? data.getTdsRefundAmount() : "",
                            data.getIntRate() != null ? data.getIntRate() : "",
                            data.getLastIntPaid() != null ? data.getLastIntPaid() : "",
                            data.getPendingInterest() != null ? data.getPendingInterest() : "",
                            data.getCurrentHalfYearlyInterest() != null ? data.getCurrentHalfYearlyInterest() : "",
                            data.getFutureInterest() != null ? data.getFutureInterest() : "",
                            data.getGrossInterestAmount() != null ? data.getGrossInterestAmount() : "",
                            data.getIntPaid() != null ? data.getIntPaid() : "",
                            data.getPaymentDate() != null ? data.getPaymentDate() : "",
                            data.getReturnReason() != null ? data.getReturnReason() : "",
                            data.getReturnAmt() != null ? data.getReturnAmt() : "",
                            data.getReInitiatedAmt() != null ? data.getReInitiatedAmt() : "",
                            data.getReInitiatedDate() != null ? data.getReInitiatedDate() : "",
                            data.getPaymentDateOfReturn() != null ? data.getPaymentDateOfReturn() : "",
                            data.getReInitiatedPaymentMode() != null ? data.getReInitiatedPaymentMode() : "",
                    };


                    if (data.getInvestmentAmount() != null && !data.getInvestmentAmount().isEmpty()) {
                        BigDecimal subscriptionAmountt = new BigDecimal(data.getInvestmentAmount());
                        subscriptionAmounttt = subscriptionAmounttt.add(subscriptionAmountt.setScale(2, RoundingMode.HALF_UP));
                    }

                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);


            SXSSFRow totalRow = sheet.createRow(rowNumber++);
            totalRow.createCell(12).setCellValue("Total");
            totalRow.createCell(13).setCellValue(subscriptionAmounttt.setScale(2, RoundingMode.HALF_UP).toString());


            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }


    //    @Override
    public ResponseDataDTO getTdsRemittanceFinconFileDetails(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO) {
//        if (tdsRemittanceFinconFileDTO.getSchemeCode().isEmpty()) {
//            return rbiBondReportsDAO.getSchemeTdsRemittanceReport(tdsRemittanceFinconFileDTO);
//        } else {
        return rbiBondReportsDAO.getTdsRemittanceFinconFileDetails(tdsRemittanceFinconFileDTO);
//        }
    }

    @Override
    public ResponseDataDTO getTdsRemittanceFinconFileDetailsDownload(TDSRemittanceFinconFiledDTO tdsRemittanceFinconFileDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getTdsRemittanceFinconFileDetails(tdsRemittanceFinconFileDTO), tdsRemittanceFinconFileDTO)));
            String fromDateStr = tdsRemittanceFinconFileDTO.getFromDate();

            SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            Date fromDate = inputFormat.parse(fromDateStr);

            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM_yyyy", Locale.ENGLISH);
            String formattedDate = outputFormat.format(fromDate).toUpperCase();

            fileResponseDto.setFileName("TDS_REMITTANCE_FINCON_FILE_" + formattedDate + "_REPORT.xlsx");

           // fileResponseDto.setFileName("TDS_REMITTANCE_FINCON_FILE_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in TDSRemittanceFinconFiledDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, TDSRemittanceFinconFiledDTO tdsRemittanceFinconFiledDTO) {
        List<TDSRemittanceFinconFiledDTO> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);

            String fromDateStr = tdsRemittanceFinconFiledDTO.getFromDate();

            String formattedDate = "";

            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                Date fromDate = inputFormat.parse(fromDateStr);

                SimpleDateFormat outputFormat = new SimpleDateFormat("MMM yyyy", Locale.ENGLISH);
                formattedDate = outputFormat.format(fromDate);

            } catch (ParseException e) {
                e.printStackTrace();
                formattedDate = "";
            }


            titleCell.setCellValue("TDS Remittance Fincon File Report For " + formattedDate);

          //  titleCell.setCellValue("TDS REMITTANCE FINCON FILE REPORT");


            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "CO CODE",
                    "REF. NO.",
                    "BLA NO.",
                    "VENDOR NAME",
                    "ADD1",
                    "ADD2",
                    "CITY",
                    "PIN",
                    "STATE",
                    "COUNTRY",
                    "SECTION",
                    "PAN NO.",
                    "GROSS AMOUNT",
                    "TAX RATE",
                    "TOTAL TAX AMOUNT",
                    "PAYMENT DATE",
                    "BASIC TAX",
                    "SURCHARGE AMOUNT",
                    "CESS AMOUNT",
                    "TAX DEP DATE",
                    "LOWER DEDUCTION STATUS",
                    "CATEGORY",
                    "INTEREST PAYOUT FOR THE YEAR",
                    "WAIVER DOCUMENT",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<TDSRemittanceFinconFiledDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (TDSRemittanceFinconFiledDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getCoCode() != null ? data.getCoCode() : "",
                            data.getRefNo() != null ? data.getRefNo() : "",
                            data.getBla() != null ? data.getBla() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getAddress1() != null ? data.getAddress1() : "",
                            data.getAddress2() != null ? data.getAddress2() : "",
                            data.getCity() != null ? data.getCity() : "",
                            data.getPin() != null ? data.getPin() : "",
                            data.getState() != null ? data.getState() : "",
                            data.getCountry() != null ? data.getCountry() : "",
                            data.getSection() != null ? data.getSection() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getGrossAmount() != null ? data.getGrossAmount() : "",
                            data.getTaxRate() != null ? data.getTaxRate() : "",
                            data.getTotalTaxAmount() != null ? data.getTotalTaxAmount() : "",
                            data.getPaymentDate() != null ? data.getPaymentDate() : "",
                            data.getBasicTax() != null ? data.getBasicTax() : "",
                            data.getSurchargeAmount() != null ? data.getSurchargeAmount() : "",
                            data.getCessAmount() != null ? data.getCessAmount() : "",
                            data.getTaxDepDate() != null ? data.getTaxDepDate() : "",
                            data.getLowerDeductionStatus() != null ? data.getLowerDeductionStatus() : "",
                            data.getCategory() != null ? data.getCategory() : "",
                            data.getInterestPayoutfortheYear() != null ? data.getInterestPayoutfortheYear() : "",
                            data.getWaiverDocument() != null ? data.getWaiverDocument() : "",
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

//    @Override
//    public ResponseDataDTO getBondType(BondLedgerAccDto bondLedgerAccDto) {
//        return rbiBondReportsDAO.getBondType(bondLedgerAccDto);
//    }

    @Override
    public ResponseDataDTO getBrokerageInvoiceEmailBodyLetterDetails(BrokerageInvoiceEmailBodyDTO brokerageInvoiceEmailBodyDTO) {
        return rbiBondReportsDAO.getBrokerageInvoiceEmailBodyLetterDetails(brokerageInvoiceEmailBodyDTO);
    }

    @Override
    public ResponseDataDTO getCallBackReportDetails(CallBackReportDTO callBackReportDTO) {
        return rbiBondReportsDAO.getCallBackReportDetails(callBackReportDTO);
    }

    @Override
    public ResponseDataDTO getCallBackReportDownload(CallBackReportDTO callBackReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getCallBackReportDetails(callBackReportDTO), callBackReportDTO)));
            fileResponseDto.setFileName("CALL_BACK_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in CallBackReportDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, CallBackReportDTO callBackReportDTO) {
        List<CallBackReportDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("CALL BACK REPORT");

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
                    "S.No",
                    "Mode of Investment",
                    "Scheme",
                    "BranchName",
                    "Application No",
                    "Investment_Date",
                    "CAS Date",
                    "InvestAmt",
                    "BranchRefNo (Subscription Id)",
                    "BlaNo",
                    "ApplName1",
                    "Gender",
                    "First Holder DOB",
                    "PanNo",
                    "ApplName2",
                    "Second Holder DOB",
                    "PanNo2",
                    "ApplName3",
                    "Third Holder DOB",
                    "PanNo3",
                    "Addr1",
                    "Addr2",
                    "City",
                    "State",
                    "PinCode",
                    "TelePhoneNo",
                    "MobileNo",
                    "EmailId",
                    "ApplicantStatus",
                    "ModeHolding",
                    "GuardianName",
                    "ModeOfPayment",
                    "ChequeNumber",
                    "AccountNo",
                    "IFSCCODE",
                    "MICRCODE",
                    "BankName",
                    "Nominee1",
                    "Nominee DOB1",
                    "Nominee Relationship",
                    "Nominee Sharing %",
                    "If Nominee1 Minor",
                    "Guardian Name 1",
                    "Guardian Relationship 1",
                    "Nomination 1 Ref.No",
                    "Nominee 1 Status",
                    "Nominee2",
                    "Nominee DOB2",
                    "If Nominee 2 Minor",
                    "Guardian Name 2",
                    "Guardian Relationship 2",
                    "Nomination 2 Ref.No",
                    "Nominee 2 Status",
                    "Nominee3",
                    "Nominee DOB3",
                    "If Nominee 3 Minor",
                    "Guardian Name 3",
                    "Guardian Relationship 3",
                    "Nomination 3 Ref.No",
                    "Nominee 3 Status",
                    "Nominee4",
                    "Nominee DOB4",
                    "If Nominee 4 Minor",
                    "Guardian Name 4",
                    "Guardian Relationship 4",
                    "Nomination 4 Ref.No",
                    "Nominee 4 Status",
                    "Status",
                    "MaturityDate",
                    "Actual Paid date (Maturity)",
                    "MaturityAmt",
                    "Half Yearly Interest Amount",
                    "LastIntPaidDate (Broken Interest)",
                    "Net Paid (Gross - TDS) Acutal paid",
                    "TDS @ 10%",
                    "TDSAmt",
                    "ExemptDoc Status Yes / No",
                    "ExemptDoc Type",
                    "Limit",
                    "FY",
                    "Age",
                    "Whether assessed to tax",
                    "Date on which Declaration is received",
                    "Estimated income for which this declaration is made",
                    "Estimated total income of the P.Y",
                    "Total No. of Forms",
                    "Aggregate amount of income",
                    "Certificate No Low Tax / No Tax",
                    "Exem Doc Maker Id",
                    "Exem Doc Checker Id",
                    "LastUpdateDate & Time",
                    "CurrentStatus",
                    "CIF",
                    "AccountTypeCode",
                    "Application/Data Entry Maker Id",
                    "Application/Data Entry Checker ID",
                    "applStatus",
                    "DataEntry By HO",
                    "Certificate No",
                    "Certificate Type ( COH / COI )",
                    "Section Code",
                    "Reason for Non-deduction / Lower Deduction",
                    "Interest Payout of the Year",
                    "Deductee Code (02)",
                    "Pan Status Active / Inactive",
                  /*  "Filer / Non Filer",*/
                    "Transfer Cases Old Branch Ref Number",
                    "Transfer Cases Old Blas",
                    "Transfer Cases Old CustomerName",
                    "Transfer Cases Old PanNumber",
                    "Bond Certificate Dispath Date",
                    "Bond Certificate Email Trigger Date",
                    "Bond Certificate Status",
                    "Half Yearly Interest Advice Email Trigger Date",
                    "Half Yearly Interest Advice Status",
                    "Coparceners Details 1",
                    "Coparceners Details 2",
                    "Coparceners Details 3",
                    "Coparceners 1 DOB",
                    "Coparceners 1 Relationship",
                    "Coparceners 2 DOB",
                    "Coparceners 2 Relationship",
                    "Coparceners 3 DOB",
                    "Coparceners 3 Relationship",
                    "BrokerCode",
                    "BrokerName",
                    "BrokerAddress 1",
                    "BrokerAddress 2",
                    "BrokerLocation",
                    "BrokerState",
                    "BrokerPinCode",
                    "BrokerMobileNumber",
                    "BrokerPanNumber",
                    "GSTNumber",
                    "BrokerAccountNumber",
                    "Gross Brokerage",
                    "NET Brokerage",
                    "GST @ 18%",
                    "BrokerCode Status ( Active / Inactive )",
                    "Brokerage Letter - Email Triggered Status",
                    "Brokerage Payment Status",
                    "POA",
                    "Scheme Type"
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
            list = (List<CallBackReportDTO>) finalData.get("callbackreportDtoList");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (CallBackReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfInvestment() != null ? data.getModeOfInvestment() : "",
                            data.getSchemeName() != null ? data.getSchemeName() : "",
                            data.getBranchName() != null ? data.getBranchName() : "",
                            data.getApplicationNo() != null ? data.getApplicationNo() : "",
                            data.getInvestmentDate() != null ? data.getInvestmentDate() : "",
                            data.getCASDate() != null ? data.getCASDate() : "",
                            data.getInvestmentAmount() != null ? data.getInvestmentAmount() : "",
                            data.getBranchRefNoSubsId() != null ? data.getBranchRefNoSubsId() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getApplicantName1() != null ? data.getApplicantName1() : "",
                            data.getGender() != null ? data.getGender() : "",
                            data.getDOB() != null ? data.getDOB() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getApplicantName2() != null ? data.getApplicantName2() : "",
                            data.getDOB2() != null ? data.getDOB2() : "",
                            data.getPanNo2() != null ? data.getPanNo2() : "",
                            data.getApplicantName3() != null ? data.getApplicantName3() : "",
                            data.getDOB3() != null ? data.getDOB3() : "",
                            data.getPanNo3() != null ? data.getPanNo3() : "",
                            data.getAddress1() != null ? data.getAddress1() : "",
                            data.getAddress2() != null ? data.getAddress2() : "",
                            data.getCity() != null ? data.getCity() : "",
                            data.getState1() != null ? data.getState1() : "",
                            data.getPincode() != null ? data.getPincode() : "",
                            data.getTelephoneNo() != null ? data.getTelephoneNo() : "",
                            data.getMobile_Number() != null ? data.getMobile_Number() : "",
                            data.getEmailId() != null ? data.getEmailId() : "",
                            data.getApplicantStatus() != null ? data.getApplicantStatus() : "",
                            data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                            data.getGuardianName() != null ? data.getGuardianName() : "",
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
                            data.getChequeNumber() != null ? data.getChequeNumber() : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getIfscCode() != null ? data.getIfscCode() : "",
                            data.getMicrCode() != null ? data.getMicrCode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getNominee1() != null ? data.getNominee1() : "",
                            data.getNominee_DOB1() != null ? data.getNominee_DOB1() : "",
                            data.getNominee_Relationship() != null ? data.getNominee_Relationship() : "",
                            data.getNominee_Sharing_Percent1() != null ? data.getNominee_Sharing_Percent1() : "",
                            data.getIf_Nominee_minor_Guardian_details()!= null ? data.getIf_Nominee_minor_Guardian_details():"",
                            data.getGuardianName1() != null ? data.getGuardianName1() : "",
                            data.getGuardianRelationship1() != null ? data.getGuardianRelationship1() : "",
                            data.getNominationRefNo()!=null?data.getNominationRefNo():"",
                            data.getNominationStatus1()!=null?data.getNominationStatus1():"",
                            data.getNominee2() != null ? data.getNominee2() : "",
                            data.getNominee_DOB2() != null ? data.getNominee_DOB2() : "",
                            data.getIf_Nominee_minor_Guardian_details2()!= null ? data.getIf_Nominee_minor_Guardian_details2():"",
                            data.getGuardianName2() != null ? data.getGuardianName2() : "",
                            data.getGuardianRelationship2() != null ? data.getGuardianRelationship2() : "",
                            data.getNominationRefNo2()!=null?data.getNominationRefNo2():"",
                            data.getNominationStatus2()!=null?data.getNominationStatus2():"",
                            data.getNominee3() != null ? data.getNominee3() : "",
                            data.getNominee_DOB3() != null ? data.getNominee_DOB3() : "",
                            data.getIf_Nominee_minor_Guardian_details3()!= null ? data.getIf_Nominee_minor_Guardian_details3():"",
                            data.getGuardianName3() != null ? data.getGuardianName3() : "",
                            data.getGuardianRelationship3() != null ? data.getGuardianRelationship3() : "",
                            data.getNominationRefNo3()!=null?data.getNominationRefNo3():"",
                            data.getNominationStatus3()!=null?data.getNominationStatus3():"",
                            data.getNominee4() != null ? data.getNominee4() : "",
                            data.getNominee_DOB4() != null ? data.getNominee_DOB4() : "",
                            data.getIf_Nominee_minor_Guardian_details4()!= null ? data.getIf_Nominee_minor_Guardian_details4():"",
                            data.getGuardianName4() != null ? data.getGuardianName4() : "",
                            data.getGuardianRelationship4() != null ? data.getGuardianRelationship4() : "",
                            data.getNominationRefNo4() != null ? data.getNominationRefNo4() : "",
                            data.getNominationStatus4()!=null?data.getNominationStatus4():"",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getActualPaidDate() != null ? data.getActualPaidDate() : "",
                            data.getMaturityAmount() != null ? data.getMaturityAmount() : "",
                            data.getHalfYearlyInterestAmount() != null ? data.getHalfYearlyInterestAmount() : "",
                            data.getLastIntrPaidDateBrokenInterest() != null ? data.getLastIntrPaidDateBrokenInterest() : "",
                            data.getNetPaidGrossTDSAcutalPaid() != null ? data.getNetPaidGrossTDSAcutalPaid() : "",
                            data.getTdsAt10Percent() != null ? data.getTdsAt10Percent() : "",
                            data.getTDS_amt() != null ? data.getTDS_amt() : "",
                            data.getExempt_Doc_Status_Yes_No() != null ? data.getExempt_Doc_Status_Yes_No() : "",
                            data.getExempt_Doc_Type() != null ? data.getExempt_Doc_Type() : "",
                            data.getLimit() != null ? data.getLimit() : "",
                            data.getFY() != null ? data.getFY() : "",
                            data.getAge() != null ? data.getAge() : "",
                            data.getWhether_assessed_to_tax() != null ? data.getWhether_assessed_to_tax() : "",
                            data.getDate_on_which_Declaration_is_received() != null ? data.getDate_on_which_Declaration_is_received() : "",
                            data.getEstimated_income_for_which_this_declaration_is_ma() != null ? data.getEstimated_income_for_which_this_declaration_is_ma() : "",
                            data.getEstimated_total_income_of_the_P_Y() != null ? data.getEstimated_total_income_of_the_P_Y() : "",
                            data.getTotal_No__of_Forms() != null ? data.getTotal_No__of_Forms() : "",
                            data.getAggregate_amount_of_income() != null ? data.getAggregate_amount_of_income() : "",
                            data.getCertificateNoLowTaxNoTax()!=null?data.getCertificateNoLowTaxNoTax():"",
                            data.getExem_Doc_Maker_Id() != null ? data.getExem_Doc_Maker_Id() : "",
                            data.getExem_Doc_Checker_Id() != null ? data.getExem_Doc_Checker_Id() : "",
                            data.getLast_Update_Date_And_Time() != null ? data.getLast_Update_Date_And_Time() : "",
                            data.getCurrent_Status() != null ? data.getCurrent_Status() : "",
                            data.getCIF() != null ? data.getCIF() : "",
                            data.getAccount_Type_Code() != null ? data.getAccount_Type_Code() : "",
                            data.getApplication_Data_Entry_Maker_id() != null ? data.getApplication_Data_Entry_Maker_id() : "",
                            data.getApplication_Data_Entry_Checker_ID() != null ? data.getApplication_Data_Entry_Checker_ID() : "",
                            data.getAppl_status() != null ? data.getAppl_status() : "",
                            data.getDataEntry_By_HO() != null ? data.getDataEntry_By_HO() : "",
                            data.getCertificateNo() != null ? data.getCertificateNo() : "",
                            data.getCertificate_Type_COH__COI() != null ? data.getCertificate_Type_COH__COI() : "",
                            data.getSection_Code_193() != null ? data.getSection_Code_193() : "",
                            data.getReason_for_Non_deduction_Lower_Deduction() != null ? data.getReason_for_Non_deduction_Lower_Deduction() : "",
                            data.getInteret_Payout_of_the_Year() != null ? data.getInteret_Payout_of_the_Year() : "",
                            data.getDeductee_Code_02() != null ? data.getDeductee_Code_02() : "",
                            data.getPan_Status_Active_Inactive() != null ? data.getPan_Status_Active_Inactive() : "",
/*
                            data.getFiler_Non_Filer() != null ? data.getFiler_Non_Filer() : "",
*/
                            data.getTransfer_cases_Old_Branch_Ref_Number() != null ? data.getTransfer_cases_Old_Branch_Ref_Number() : "",
                            data.getTransfer_cases_Old_Blas() != null ? data.getTransfer_cases_Old_Blas() : "",
                            data.getTransfer_cases_Old_Customer_Name() != null ? data.getTransfer_cases_Old_Customer_Name() : "",
                            data.getTransfer_cases_Old_Pan_Number() != null ? data.getTransfer_cases_Old_Pan_Number() : "",
                            data.getBond_Certificate_Dispatch_Date() != null ? data.getBond_Certificate_Dispatch_Date() : "",
                            data.getBond_Certificate_Email_trigger_date() != null ? data.getBond_Certificate_Email_trigger_date() : "",
                            data.getBond_Certificate_Status() != null ? data.getBond_Certificate_Status() : "",
                            data.getHalf_Yearly_Interest_Advice_Email_trigger_date() != null ? data.getHalf_Yearly_Interest_Advice_Email_trigger_date() : "",
                            data.getHalf_Yearly_Interest_Advice_Status() != null ? data.getHalf_Yearly_Interest_Advice_Status() : "",
                            data.getCo_parceners_details_1() != null ? data.getCo_parceners_details_1() : "",
                            data.getCo_parceners_details_2() != null ? data.getCo_parceners_details_2() : "",
                            data.getCo_parceners_details_3() != null ? data.getCo_parceners_details_3() : "",
                            data.getCo_parceners_1_DOB() != null ? data.getCo_parceners_1_DOB() : "",
                            data.getCo_parceners_1_Relationship() != null ? data.getCo_parceners_1_Relationship() : "",
                            data.getCo_parceners_2_DOB() != null ? data.getCo_parceners_2_DOB() : "",
                            data.getCo_parceners_2_Relationship() != null ? data.getCo_parceners_2_Relationship() : "",
                            data.getCo_parceners_3_DOB() != null ? data.getCo_parceners_3_DOB() : "",
                            data.getCo_parceners_3_Relationship() != null ? data.getCo_parceners_3_Relationship() : "",
                            data.getBroker_code() != null ? data.getBroker_code() : "",
                            data.getBroker_Name() != null ? data.getBroker_Name() : "",
                            data.getAddress_1() != null ? data.getAddress_1() : "",
                            data.getAddress_2() != null ? data.getAddress_2() : "",
                            data.getLocation() != null ? data.getLocation() : "",
                            data.getState2() != null ? data.getState2() : "",
                            data.getPin_Code() != null ? data.getPin_Code() : "",
                            data.getMobileNo() != null ? data.getMobileNo() : "",
                            data.getPan_Number() != null ? data.getPan_Number() : "",
                            data.getGST_Number() != null ? data.getGST_Number() : "",
                            data.getAccount_Number() != null ? data.getAccount_Number() : "",
                            data.getGross_Brokerage() != null ? data.getGross_Brokerage() : "",
                            data.getNET_Brokerage() != null ? data.getNET_Brokerage() : "",
                            data.getGST_At_18Percent() != null ? data.getGST_At_18Percent() : "",
                            data.getBroker_Code_Status_Active_Inactive() != null ? data.getBroker_Code_Status_Active_Inactive() : "",
                            data.getBrokerage_letter_Email_triggered_status()!=null?data.getBrokerage_letter_Email_triggered_status():"",
                            data.getBrokerage_Payment_status()!=null?data.getBrokerage_Payment_status(): "",
                            data.getPOA() != null ? data.getPOA() : "",
                            data.getSchemeType() != null ? data.getSchemeType() : ""
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
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, getSafeColumnWidth(maxColumnWidths[i]));
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    private int getSafeColumnWidth(int charLength) {
        int MAX_EXCEL_COLUMN_WIDTH = 255 * 256;
        return Math.min(charLength * 256, MAX_EXCEL_COLUMN_WIDTH);
    }

    @Override
    public ResponseDataDTO getTDSExemptionSubmitNonSubmitReport(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        return rbiBondReportsDAO.getTDSExemptionSubmitNonSubmitReport(tdsExemptionSubmitNonSubmitReportDTO);
    }

    @Override
    public ResponseDataDTO getTDSExemptionSubmitNonSubmitDownload(TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getTDSExemptionSubmitNonSubmitReport(tdsExemptionSubmitNonSubmitReportDTO), tdsExemptionSubmitNonSubmitReportDTO)));
            fileResponseDto.setFileName("TDS_EXEMPTION_SUBMITTED/NON-SUBMITTED_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in TDSExemptionSubmitNonSubmitReportDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, TDSExemptionSubmitNonSubmitReportDTO tdsExemptionSubmitNonSubmitReportDTO) {
        List<TDSExemptionSubmitNonSubmitReportDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("TDS EXEMPTION SUBMITTED/NON-SUBMITTED REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "Customer Name",
                    "PAN No",
                    "Type of Exemption",
                    "Certificate No / UIN No",
                    "TDS Limit",
                    "Low Tax Percentage",
                    "Valid From Date",
                    "Valid to Date",
                    "Financial Year",
                    "Certificate Submission Status",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<TDSExemptionSubmitNonSubmitReportDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (TDSExemptionSubmitNonSubmitReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getCustomerName() != null ? data.getCustomerName() : "",
                            data.getPanNumber() != null ? data.getPanNumber() : "",
                            data.getTypeOfExemption() != null ? data.getTypeOfExemption() : "",
                            data.getCertificateNo() != null ? data.getCertificateNo() : "",
                            data.getTdsLimit() != null ? data.getTdsLimit() : "",
                            data.getLowTaxPercentage() != null ? data.getLowTaxPercentage() : "",
                            data.getValidFromDate() != null ? data.getValidFromDate() : "",
                            data.getValidToDate() != null ? data.getValidToDate() : "",
                            data.getFinancialYear() != null ? data.getFinancialYear() : "",
                            data.getCertificateSubStatus() != null ? data.getCertificateSubStatus() : "",
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
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getSuccessFailureDetails(SuccessFailureReportDto successFailureReportDto) {
        if (successFailureReportDto.getSchemeCode().isEmpty()) {
            return rbiBondReportsDAO.getSchemeSuccessFailureDetails(successFailureReportDto);
        } else {
            return rbiBondReportsDAO.getSuccessFailureDetails(successFailureReportDto);
        }
    }

    @Override
    public ResponseDataDTO getBondInterestTrialRunEligibleAccounts(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto) {
        return rbiBondReportsDAO.getBondInterestTrialRunEligibleAccounts(bondInterestTrialRunEligibleAccountsDto);
    }
    @Override
    public ResponseDataDTO getBondInterestTrialRunEligibleAccountsDownload(BondInterestTrialRunEligibleAccountsDto bondInterestTrialRunEligibleAccountsDto) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        ResponseDataDTO responseDataDTO1=new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try{
            responseDataDTO1=rbiBondReportsDAO.getBondInterestTrialRunEligibleAccounts(bondInterestTrialRunEligibleAccountsDto);
            if(null!=responseDataDTO1&&responseDataDTO1.getMessage().equals("SUCCESS")) {
                if (null != bondInterestTrialRunEligibleAccountsDto && null != bondInterestTrialRunEligibleAccountsDto.getFlag() && bondInterestTrialRunEligibleAccountsDto.getFlag().equals("T")) {
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateEligiblereportUnmergedExcel(responseDataDTO1)));
                    fileResponseDto.setFileName("Bond_Interest_Trial_Run-Eligible_Accounts_Report_UnMerged.xlsx");
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(resultMsg);
                } else {
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateEligiblereportMergedExcel(responseDataDTO1)));
                    fileResponseDto.setFileName("Bond_Interest_Trial_Run-Eligible_Accounts_Report_Merged.xlsx");
                    responseDataDTO.setResult(fileResponseDto);
                    responseDataDTO.setMessage(resultMsg);
                }
            }else{
                responseDataDTO.setMessage(responseDataDTO1.getMessage());
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("Error in EligibleReportDto, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO getBlaModificationReport(BlaModificationReportDto blaModificationReportDto) {
        return rbiBondReportsDAO.getBlaModificationReport(blaModificationReportDto);
    }

    @Override
    public ResponseDataDTO getBlaModificationReportDownload(BlaModificationReportDto blaModificationReportDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getBlaModificationReport(blaModificationReportDto),blaModificationReportDto)));
            fileResponseDto.setFileName("BLA_MODIFICATION_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in BlaModificationReportDto, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    public byte[] generateEligiblereportMergedExcel(ResponseDataDTO responseDataDTO) {
        List<BondInterestTrialRunEligibleAccountsDto> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Bond Interest Trial Run-Eligible Accounts Report");
            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);
            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No",
                    "Mode of Payment",
                    "Bla No",
                    "Appl Name 1",
                    "Appl Name 2",
                    "Appl Name 3",
                    "Address 1",
                    "Address 2",
                    "Recipient Name",
                    "Recipient Parent Name",
                    "Pin Code",
                    "Phone No",
                    "Application Status",
                    "Investment Amount",
                    "Account No",
                    "Subscription Count",
                    "Bank Sort Code",
                    "Bank Name",
                    "PAN No",
                    "8% Interest Payout",
                    "Non Cumulative Interest",
                    "Cumulative Interest",
                    "7.75% Interest Payout",
                    "Non Cumulative Interest 775",
                    "Cumulative Interest 775",
                    "Total",
                    "In-operative PAN",
                    "Non-filers",
                    "Age",
                    "Taxable Limit",
                    "Waiver Document",
                    "Waiver Document Ref No",
                    "RRB-TDS Rate",
                    "Threshold Exceed Amount",
                    "Threshold Exceed TDS Amount",
                    "Exemption Rate %",
                    "Exemption Amount",
                    "Exemption TDS Amount",
                    "Surcharge %",
                    "Edu Cess %",
                    "Base Rate Amount",
                    "Surcharge Amount",
                    "Edu Cess Amount",
                    "TDS Amount",
                    "TDS Recovery Amount",
                    "TDS Refund Amount",
                    "Interest Rate",
                    "Last Interest Paid",
                    "Pending Interest",
                    "Current Half-Yearly Interest",
                    "Future Interest",
                    "Gross Interest Amount",
                    "Net Interest Paid Amount"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BondInterestTrialRunEligibleAccountsDto>) finalData.get("list");
            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondInterestTrialRunEligibleAccountsDto data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getApplName1() != null ? data.getApplName1() : "",
                            data.getApplName2() != null ? data.getApplName2() : "",
                            data.getApplName3() != null ? data.getApplName3() : "",
                            data.getAddr1() != null ? data.getAddr1() : "",
                            data.getAddr2() != null ? data.getAddr2() : "",
                            data.getRecName() != null ? data.getRecName() : "",
                            data.getRecParentName() != null ? data.getRecParentName() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                            data.getPhoneNo() != null ? data.getPhoneNo() : "",
                            data.getApplStatus() != null ? data.getApplStatus() : "",
                            data.getInvestmentAmt() != null ? data.getInvestmentAmt() : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getSubscriptionCount() != null ? data.getSubscriptionCount() : "",
                            data.getBankSortCode() != null ? data.getBankSortCode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getInterestPayout8() != null ? data.getInterestPayout8() : "",
                            data.getNonCumulativeInt8() != null ? data.getNonCumulativeInt8() : "",
                            data.getCumulatinveInt8() != null ? data.getCumulatinveInt8() : "",
                            data.getInterestPayout7() != null ? data.getInterestPayout7() : "",
                            data.getNonCumulativeInt7() != null ? data.getNonCumulativeInt7() : "",
                            data.getCumulativeInt7() != null ? data.getCumulativeInt7() : "",
                            data.getTotal() != null ? data.getTotal() : "",
                            data.getInOperativePan() != null ? data.getInOperativePan() : "",
                            data.getNonFilers() != null ? data.getNonFilers() : "",
                            data.getAge() != null ? data.getAge() : "",
                            data.getTaxableLimit() != null ? data.getTaxableLimit() : "",
                            data.getWaiverDocument() != null ? data.getWaiverDocument() : "",
                            data.getWaiverDocumentRefNo() != null ? data.getWaiverDocumentRefNo() : "",
                            data.getRrbTdsRate() != null ? data.getRrbTdsRate() : "",
                            data.getThresholdExceedAmount() != null ? data.getThresholdExceedAmount() : "",
                            data.getThresholdExceedTdsAmount() != null ? data.getThresholdExceedTdsAmount() : "",
                            data.getExemptionRatePercent() != null ? data.getExemptionRatePercent() : "",
                            data.getExemptionAmount() != null ? data.getExemptionAmount() : "",
                            data.getExemptionTdsAmount() != null ? data.getExemptionTdsAmount() : "",
                            data.getSurchargePercent() != null ? data.getSurchargePercent() : "",
                            data.getEduCessPercent() != null ? data.getEduCessPercent() : "",
                            data.getBaseRateAmt() != null ? data.getBaseRateAmt() : "",
                            data.getSurchargeAmt() != null ? data.getSurchargeAmt() : "",
                            data.getEduCessAmt() != null ? data.getEduCessAmt() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getTdsRecoveryAmount() != null ? data.getTdsRecoveryAmount() : "",
                            data.getTdsRefundAmount() != null ? data.getTdsRefundAmount() : "",
                            data.getIntRate() != null ? data.getIntRate() : "",
                            data.getLastIntPaid() != null ? data.getLastIntPaid() : "",
                            data.getPendingInterest() != null ? data.getPendingInterest() : "",
                            data.getCurrentHalfYearlyInterest() != null ? data.getCurrentHalfYearlyInterest() : "",
                            data.getFutureInterest() != null ? data.getFutureInterest() : "",
                            data.getGrossInterestAmount() != null ? data.getGrossInterestAmount() : "",
                            data.getIntPaidAmt() != null ? data.getIntPaidAmt() : ""
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            workbook.write(byteArrayOutputStream);
            // dispose temp files (clears disk storage used by SXSSF)
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
    public byte[] generateEligiblereportUnmergedExcel(ResponseDataDTO responseDataDTO) {
        List<BondInterestTrialRunEligibleAccountsDto> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Bond Interest Trial Run-Eligible Accounts Report");
            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);
            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            //sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
/*            SXSSFRow headersRow = sheet.createRow(2);
            String[] mainHeaders = {
                    "", "Basic Details From BLA cum Subscription", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "", "OTHER SCHEMES INTEREST RELATED INFO",
                    "", "", "", "", "", "", "", "PAN STATUS", "", "", "TDS EXEMPTION DETAILS", "", "",
                    "", "", "", "", "FRSB INTEREST PAYOUT DETAILS", ""
            };

            CellStyle headerCellStyle1 = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font1 = workbook.createFont();
            font1.setBold(true);
            headerCellStyle1.setFont(font1);

            // Track maximum column widths
            int[] maxColumnWidths1 = new int[mainHeaders.length];
            for (int i = 0; i < mainHeaders.length; i++) {
                SXSSFCell cell = headersRow.createCell(i);
                cell.setCellValue(mainHeaders[i]);
                cell.setCellStyle(headerCellStyle1);
                maxColumnWidths1[i] = mainHeaders[i].length();
            }*/
            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No",
                    "Mode of Payment",
                    "Branch Ref No",
                    "Bla No",
                    "Ref No",
                    "Appl Name 1",
                    "Appl Name 2",
                    "Appl Name 3",
                    "Address 1",
                    "Address 2",
                    "Recipient Name",
                    "Recipient Parent Name",
                    "Pin Code",
                    "Phone No",
                    "Application Status",
                    "Investment Amount",
                    "CFD",
                    "Mat Date",
                    "Account No",
                    "Subscription Count",
                    "Bank Sort Code",
                    "Bank Name",
                    "PAN No",
                    "8% Interest Payout",
                    "Non Cumulative Interest",
                    "Cumulative Interest",
                    "7.75% Interest Payout",
                    "Non Cumulative Interest 775",
                    "Cumulative Interest 775",
                    "Total",
                    "In-operative PAN",
                    "Non-filers",
                    "Age",
                    "Taxable Limit",
                    "Waiver Document",
                    "Waiver Document Ref No",
                    "RRB-TDS Rate",
                    "Threshold Exceed Amount",
                    "Threshold Exceed TDS Amount",
                    "Exemption Rate %",
                    "Exemption Amount",
                    "Exemption TDS Amount",
                    "Surcharge %",
                    "Edu Cess %",
                    "Base Rate Amount",
                    "Surcharge Amount",
                    "Edu Cess Amount",
                    "TDS Amount",
                    "TDS Recovery Amount",
                    "TDS Refund Amount",
                    "Interest Rate",
                    "Last Interest Paid",
                    "Pending Interest",
                    "Current Half-Yearly Interest",
                    "Future Interest",
                    "Gross Interest Amount",
                    "Net Interest Paid Amount"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BondInterestTrialRunEligibleAccountsDto>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            /*BigDecimal totalInvestmentAmt = BigDecimal.ZERO;
            BigDecimal totalThresholdExceedAmount = BigDecimal.ZERO;
            BigDecimal totalThresholdExceedTdsAmount = BigDecimal.ZERO;
            BigDecimal totalExemptionAmount = BigDecimal.ZERO;
            BigDecimal totalExemptionTdsAmount = BigDecimal.ZERO;
            BigDecimal totalBaseRateAmt = BigDecimal.ZERO;
            BigDecimal totalSurchargeAmt = BigDecimal.ZERO;
            BigDecimal totalEduCessAmt = BigDecimal.ZERO;
            BigDecimal totalTdsAmount = BigDecimal.ZERO;
            BigDecimal totalTdsRecoveryAmount = BigDecimal.ZERO;
            BigDecimal totalTdsRefundAmount = BigDecimal.ZERO;
            BigDecimal totalGrossInterestAmount = BigDecimal.ZERO;
            BigDecimal totalIntPaidAmt = BigDecimal.ZERO;*/
            if (!list.isEmpty()) {
                for (BondInterestTrialRunEligibleAccountsDto data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
                            data.getBranchRefNo() != null ? data.getBranchRefNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getRefNo() != null ? data.getRefNo() : "",
                            data.getApplName1() != null ? data.getApplName1() : "",
                            data.getApplName2() != null ? data.getApplName2() : "",
                            data.getApplName3() != null ? data.getApplName3() : "",
                            data.getAddr1() != null ? data.getAddr1() : "",
                            data.getAddr2() != null ? data.getAddr2() : "",
                            data.getRecName() != null ? data.getRecName() : "",
                            data.getRecParentName() != null ? data.getRecParentName() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                            data.getPhoneNo() != null ? data.getPhoneNo() : "",
                            data.getApplStatus() != null ? data.getApplStatus() : "",
                            data.getInvestmentAmt() != null ? data.getInvestmentAmt() : "",
                            data.getCfd() != null ? data.getCfd() : "",
                            data.getMatDate() != null ? data.getMatDate() : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getSubscriptionCount() != null ? data.getSubscriptionCount() : "",
                            data.getBankSortCode() != null ? data.getBankSortCode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getInterestPayout8() != null ? data.getInterestPayout8() : "",
                            data.getNonCumulativeInt8() != null ? data.getNonCumulativeInt8() : "",
                            data.getCumulatinveInt8() != null ? data.getCumulatinveInt8() : "",
                            data.getInterestPayout7() != null ? data.getInterestPayout7() : "",
                            data.getNonCumulativeInt7() != null ? data.getNonCumulativeInt7() : "",
                            data.getCumulativeInt7() != null ? data.getCumulativeInt7() : "",
                            data.getTotal() != null ? data.getTotal() : "",
                            data.getInOperativePan() != null ? data.getInOperativePan() : "",
                            data.getNonFilers() != null ? data.getNonFilers() : "",
                            data.getAge() != null ? data.getAge() : "",
                            data.getTaxableLimit() != null ? data.getTaxableLimit() : "",
                            data.getWaiverDocument() != null ? data.getWaiverDocument() : "",
                            data.getWaiverDocumentRefNo() != null ? data.getWaiverDocumentRefNo() : "",
                            data.getRrbTdsRate() != null ? data.getRrbTdsRate() : "",
                            data.getThresholdExceedAmount() != null ? data.getThresholdExceedAmount() : "",
                            data.getThresholdExceedTdsAmount() != null ? data.getThresholdExceedTdsAmount() : "",
                            data.getExemptionRatePercent() != null ? data.getExemptionRatePercent() : "",
                            data.getExemptionAmount() != null ? data.getExemptionAmount() : "",
                            data.getExemptionTdsAmount() != null ? data.getExemptionTdsAmount() : "",
                            data.getSurchargePercent() != null ? data.getSurchargePercent() : "",
                            data.getEduCessPercent() != null ? data.getEduCessPercent() : "",
                            data.getBaseRateAmt() != null ? data.getBaseRateAmt() : "",
                            data.getSurchargeAmt() != null ? data.getSurchargeAmt() : "",
                            data.getEduCessAmt() != null ? data.getEduCessAmt() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getTdsRecoveryAmount() != null ? data.getTdsRecoveryAmount() : "",
                            data.getTdsRefundAmount() != null ? data.getTdsRefundAmount() : "",
                            data.getIntRate() != null ? data.getIntRate() : "",
                            data.getLastIntPaid() != null ? data.getLastIntPaid() : "",
                            data.getPendingInterest() != null ? data.getPendingInterest() : "",
                            data.getCurrentHalfYearlyInterest() != null ? data.getCurrentHalfYearlyInterest() : "",
                            data.getFutureInterest() != null ? data.getFutureInterest() : "",
                            data.getGrossInterestAmount() != null ? data.getGrossInterestAmount() : "",
                            data.getIntPaidAmt() != null ? data.getIntPaidAmt() : ""
                    };
                    //log.info("cellValues.length: "+cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                    /*totalInvestmentAmt = totalInvestmentAmt.add(safeBigDecimal(data.getInvestmentAmt()));
                    totalThresholdExceedAmount = totalThresholdExceedAmount.add(safeBigDecimal(data.getThresholdExceedAmount()));
                    totalThresholdExceedTdsAmount = totalThresholdExceedTdsAmount.add(safeBigDecimal(data.getThresholdExceedTdsAmount()));
                    totalExemptionAmount = totalExemptionAmount.add(safeBigDecimal(data.getExemptionAmount()));
                    totalExemptionTdsAmount = totalExemptionTdsAmount.add(safeBigDecimal(data.getExemptionTdsAmount()));
                    totalBaseRateAmt = totalBaseRateAmt.add(safeBigDecimal(data.getBaseRateAmt()));
                    totalSurchargeAmt = totalSurchargeAmt.add(safeBigDecimal(data.getSurchargeAmt()));
                    totalEduCessAmt = totalEduCessAmt.add(safeBigDecimal(data.getEduCessAmt()));
                    totalTdsAmount = totalTdsAmount.add(safeBigDecimal(data.getTdsAmount()));
                    totalTdsRecoveryAmount = totalTdsRecoveryAmount.add(safeBigDecimal(data.getTdsRecoveryAmount()));
                    totalTdsRefundAmount = totalTdsRefundAmount.add(safeBigDecimal(data.getTdsRefundAmount()));
                    totalGrossInterestAmount = totalGrossInterestAmount.add(safeBigDecimal(data.getGrossInterestAmount()));
                    totalIntPaidAmt = totalIntPaidAmt.add(safeBigDecimal(data.getIntPaidAmt()));*/
                }
                /*sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
                SXSSFRow footerRow = sheet.createRow(rowNumber++);
                footerRow.createCell(15).setCellValue(String.valueOf(totalInvestmentAmt));
                footerRow.createCell(37).setCellValue(String.valueOf(totalThresholdExceedAmount));
                footerRow.createCell(38).setCellValue(String.valueOf(totalThresholdExceedTdsAmount));
                footerRow.createCell(40).setCellValue(String.valueOf(totalExemptionAmount));
                footerRow.createCell(41).setCellValue(String.valueOf(totalExemptionTdsAmount));
                footerRow.createCell(44).setCellValue(String.valueOf(totalBaseRateAmt));
                footerRow.createCell(45).setCellValue(String.valueOf(totalSurchargeAmt));
                footerRow.createCell(46).setCellValue(String.valueOf(totalEduCessAmt));
                footerRow.createCell(47).setCellValue(String.valueOf(totalTdsAmount));
                footerRow.createCell(48).setCellValue(String.valueOf(totalTdsRecoveryAmount));
                footerRow.createCell(49).setCellValue(String.valueOf(totalTdsRefundAmount));
                footerRow.createCell(54).setCellValue(String.valueOf(totalGrossInterestAmount));
                footerRow.createCell(55).setCellValue(String.valueOf(totalIntPaidAmt));

// Autosize columns
                for (int i = 0; i < maxColumnWidths.length; i++) {
                    sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
                }*/
            }
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            workbook.write(byteArrayOutputStream);
            // dispose temp files (clears disk storage used by SXSSF)
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, BlaModificationReportDto blaModificationReportDto) {
        List<BlaModificationReportDto> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BLA MODIFICATION REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "SCHEME ID",
                    "SCHEME NAME",
                    "INWARD REFERENCE NO.",
                    "INWARD REFERENCE DATE",
                    "BLA NO.",
                    "APPLICANT Name",
                    "Old APPLICANT Name",
                    "FIRST APPLICANT",
                    "OLD FIRST APPLICANT",
                    "SECOND APPLICANT",
                    "OLD SECOND APPLICANT",
                    "THIRD APPLICANT",
                    "OLD THIRD APPLICANT",
                    "ADDRESS",
                    "OLD ADDRESS",
                    "FIRST APPLICANT ADDRESS",
                    "FIRST APPLICANT OlD ADDRESS",
                    "SECOND APPLICANT ADDRESS",
                    "SECOND APPLICANT OLD ADDRESS",
                    "THIRD APPLICANT ADDRESS",
                    "THIRD APPLICANT OLD ADDRESS",
                    "MODE OF HOLDING",
                    "OLD MODE OF HOLDING",
                    "DATE OF BIRTH",
                    "OLD DOB",
                    "FIRST APPLICANT DOB",
                    "FIRST APPLICANT OlD DOB",
                    "SECOND APPLICANT DOB",
                    "SECOND APPLICANT OLD DOB",
                    "THIRD APPLICANT DOB",
                    "THIRD APPLICANT OLD DOB",
                    "MODE OF PAYMENT",
                    "OLD MODE OF PAYMENT",
                    "ACCOUNT TYPE",
                    "OLD ACCOUNT TYPE",
                    "ACCOUNT NO.",
                    "OLD ACCOUNT NO.",
                    "MICR",
                    "OLD MICR",
                    "EMAIL ID",
                    "OLD EMAIL ID",
                    "FIRST APPLICANT EMAIL ID",
                    "FIRST APPLICANT OlD EMAIL ID",
                    "SECOND APPLICANT EMAIL ID",
                    "SECOND APPLICANT OLD EMAIL ID",
                    "THIRD APPLICANT EMAIL ID",
                    "THIRD APPLICANT OLD EMAIL ID",
                     "MOBILE NO",
                    "OLD MOBILE NO",
                    "FIRST APPLICANT MOBILE NO",
                    "FIRST APPLICANT OlD MOBILE NO",
                    "SECOND APPLICANT MOBILE NO",
                    "SECOND APPLICANT OLD MOBILE NO",
                    "THIRD APPLICANT MOBILE NO",
                    "THIRD APPLICANT OLD MOBILE NO",
                    "NOMINEE 1",
                    "NOMINEE 1 OLD",
                    "NOMINEE 2",
                    "NOMINEE 2 OLD",
                    "NOMINEE 3",
                    "NOMINEE 3 OLD",
                    "NOMINEE 4",
                    "NOMINEE 4 OLD",
                    "APPLICATION STATUS",
                    "OLD APPLICATION STATUS",
                    "PAN 1",
                    "PAN 1 OLD",
                    "PAN 2",
                    "PAN 2 OLD",
                    "PAN 3",
                    "PAN 3 OLD",
                    "PAN NO.",
                    "OLD PAN NO.",
                    "MAKER ID",
                    "MAKER DATE",
                    "CHECKER ID",
                    "CHECKER DATE",
                    "AUTHORIZED STATUS",
                    "BRANCH CODE",
                    "OLD BRANCH CODE",
                    "REMARKS",
            };


            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BlaModificationReportDto>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BlaModificationReportDto data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getSchemeCode() != null ? data.getSchemeCode() : "",
                            data.getSchemeName() != null ? data.getSchemeName() : "",
                            data.getInwardRefNo() != null ? data.getInwardRefNo() : "",
                            data.getInwardRefDate() != null ? data.getInwardRefDate() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getApplicant() != null ? data.getApplicant() : "",
                            data.getOldApplicant() != null ? data.getOldApplicant() : "",
                            data.getFirstApplicant() != null ? data.getFirstApplicant() : "",
                            data.getOldFirstApplicant() != null ? data.getOldFirstApplicant() : "",
                            data.getSecondApplicant() != null ? data.getSecondApplicant() : "",
                            data.getOldSecondApplicant() != null ? data.getOldSecondApplicant() : "",
                            data.getThirdApplicant() != null ? data.getThirdApplicant() : "",
                            data.getOldThirdApplicant() != null ? data.getOldThirdApplicant() : "",
                            data.getAddress() != null ? data.getAddress() : "",
                            data.getOldAddress() != null ? data.getOldAddress() : "",
                            data.getAddressOne() != null ? data.getAddressOne() : "",
                            data.getOldAddressOne() != null ? data.getOldAddressOne() : "",
                            data.getAddressTwo() != null ? data.getAddressTwo() : "",
                            data.getOldAddressTwo() != null ? data.getOldAddressTwo() : "",
                            data.getAddressThree() != null ? data.getAddressThree() : "",
                            data.getOldAddressThree() != null ? data.getOldAddressThree() : "",
                            data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                            data.getOldModeOfHolding() != null ? data.getOldModeOfHolding() : "",
                            data.getDob() != null ? data.getDob() : "",
                            data.getOldDob() != null ? data.getOldDob() : "",
                            data.getDobOne() != null ? data.getDobOne() : "",
                            data.getOldDobOne() != null ? data.getOldDobOne() : "",
                            data.getDobTwo() != null ? data.getDobTwo() : "",
                            data.getOldDobTwo() != null ? data.getOldDobTwo() : "",
                            data.getDobThree() != null ? data.getDobThree() : "",
                            data.getOldDobThree() != null ? data.getOldDobThree() : "",
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
                            data.getOldModeOfPayment() != null ? data.getOldModeOfPayment() : "",
                            data.getAccountType() != null ? data.getAccountType() : "",
                            (data.getOldAccountType() != null)
                                    ? (data.getOldAccountType().equalsIgnoreCase("SB002") ? "SB"
                                    : (data.getOldAccountType().equalsIgnoreCase("SB003") ? "CR"
                                    : (data.getOldAccountType().equalsIgnoreCase("SB004") ? "CR"
                                    : (data.getOldAccountType().equalsIgnoreCase("SB005") ? "CAS"
                                    : ""))))
                                    : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getOldAccountNo() != null ? data.getOldAccountNo() : "",
                            data.getMicr() != null ? data.getMicr() : "",
                            data.getOldMicr() != null ? data.getOldMicr() : "",
                            data.getEmail() != null ? data.getEmail() : "",
                            data.getOldEmail() != null ? data.getOldEmail() : "",
                            data.getEmailOne() != null ? data.getEmailOne() : "",
                            data.getOldEmailOne() != null ? data.getOldEmailOne() : "",
                            data.getEmailTwo() != null ? data.getEmailTwo() : "",
                            data.getOldEmailTwo() != null ? data.getOldEmailTwo() : "",
                            data.getEmailThree() != null ? data.getEmailThree() : "",
                            data.getOldEmailThree() != null ? data.getOldEmailThree() : "",
                            data.getMobileNo() != null ? data.getMobileNo() : "",
                            data.getOldMobileNo() != null ? data.getOldMobileNo() : "",
                            data.getMobileNoOne() != null ? data.getMobileNoOne() : "",
                            data.getOldMobileNoOne() != null ? data.getOldMobileNoOne() : "",
                            data.getMobileNoTwo() != null ? data.getMobileNoTwo() : "",
                            data.getOldMobileNoTwo() != null ? data.getOldMobileNoTwo() : "",
                            data.getMobileNoThree() != null ? data.getMobileNoThree() : "",
                            data.getOldMobileNoThree() != null ? data.getOldMobileNoThree() : "",
                            data.getNomineeOne() != null ? data.getNomineeOne() : "",
                            data.getNomineeOneOld() != null ? data.getNomineeOneOld() : "",
                            data.getNomineeTwo() != null ? data.getNomineeTwo() : "",
                            data.getNomineeTwoOld() != null ? data.getNomineeTwoOld() : "",
                            data.getNomineeThree() != null ? data.getNomineeThree() : "",
                            data.getNomineeThreeOld() != null ? data.getNomineeThreeOld() : "",
                            data.getNomineeFour() != null ? data.getNomineeFour() : "",
                            data.getNomineeFourOld() != null ? data.getNomineeFourOld() : "",
                            data.getApplStatus() != null ? data.getApplStatus() : "",
                            data.getOldApplStatus() != null ? data.getOldApplStatus() : "",

                              data.getPanOne() != null ? data.getPanOne() : "",
                            data.getPanOneOld() != null ? data.getPanOneOld() : "",
                             data.getPanTwo() != null ? data.getPanTwo() : "",
                            data.getPanTwoOld() != null ? data.getPanTwoOld() : "",
                               data.getPanThree() != null ? data.getPanThree() : "",
                            data.getPanThreeOld() != null ? data.getPanThreeOld() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getOldPanNo() != null ? data.getOldPanNo() : "",
                            data.getMakerId() != null ? data.getMakerId() : "",
                            data.getMakerDate() != null ? data.getMakerDate() : "",
                            data.getCheckerId() != null ? data.getCheckerId() : "",
                            data.getCheckerDate() != null ? data.getCheckerDate() : "",
                            data.getAuthorizedStatus() != null ? data.getAuthorizedStatus() : "",
                            data.getHdfcBranchCode() != null ? data.getHdfcBranchCode() : "",
                            data.getOldHdfcBranchCode() != null ? data.getOldHdfcBranchCode() : "",
                            data.getRemarks() != null ? data.getRemarks() : "",
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getInterestPaymentReport(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO) {
        return rbiBondReportsDAO.getInterestPaymentReport(interestPaymentToOtherBankDTO);
    }

    @Override
    public ResponseDataDTO getinterestPaymentDetailsDownload(InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getInterestPaymentReport(interestPaymentToOtherBankDTO), interestPaymentToOtherBankDTO)));
            fileResponseDto.setFileName("INTEREST_PAYMENT_TO_OTHER_BANK_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in InterestPaymentToOtherBankDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, InterestPaymentToOtherBankDTO interestPaymentToOtherBankDTO) {
        List<InterestPaymentToOtherBankDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();

            // Title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("INTEREST PAYMENT TO OTHER BANK(NON-HDFC) REPORT");

            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 22));

            // Bank info row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);

            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 22));

            // Headers
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "Customer Name",
                    "PAN No",
                    "Bond Ledger Account Number",
                    "Bond Subscription Id",
                    "Subscription Date",
                    "Subscription Amount",
                    "Interest Paid Date",
                    "Interest From Date",
                    "Interest To Date",
                    "Interest Payment Mode",
                    "Bank Name",
                    "IFSC Code",
                    "MICR Code",
                    "Credit Account Number",
                    "TDS Certificate",
                    "TDS Exemption Limit",
                    "Low Tax Percentage",
                    "PAN Status",
                    "ITR Status",
                    "Gross Interest",
                    "TDS Amount",
                    "Net Amount"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<InterestPaymentToOtherBankDTO>) finalData.get("list");

            // Data rows
            int rowNumber = 3;
            int sNo = 1;
            double totalSubscriptionAmount = 0;
            double totalGrossInterest = 0;
            double totalTdsAmount = 0;

            if (!list.isEmpty()) {
                for (InterestPaymentToOtherBankDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getCustomerName() != null ? data.getCustomerName() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getBondLedgerAccountNumber() != null ? data.getBondLedgerAccountNumber() : "",
                            data.getBondSubscriptionId() != null ? data.getBondSubscriptionId() : "",
                            data.getSubscriptionDate() != null ? data.getSubscriptionDate() : "",
                            data.getSubscriptionAmount() != null ? data.getSubscriptionAmount() : "",
                            data.getInterestPaidDate() != null ? data.getInterestPaidDate() : "",
                            data.getInterestFromDate() != null ? data.getInterestFromDate() : "",
                            data.getInterestToDate() != null ? data.getInterestToDate() : "",
                            data.getInterestPaymentMode() != null ? data.getInterestPaymentMode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getIfcCode() != null ? data.getIfcCode() : "",
                            data.getMicrCode() != null ? data.getMicrCode() : "",
                            data.getCreditAccountNumber() != null ? data.getCreditAccountNumber() : "",
                            data.getTdsCertificate() != null ? data.getTdsCertificate() : "",
                            data.getTdsExemptionLimit() != null ? data.getTdsExemptionLimit() : "",
                            data.getLowTaxPercentage() != null ? data.getLowTaxPercentage() : "",
                            data.getPanStatus() != null ? data.getPanStatus() : "",
                            data.getItrStatus() != null ? data.getItrStatus() : "",
                            data.getGrossInterest() != null ? data.getGrossInterest() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getNetAmount() != null ? data.getNetAmount() : "",
                    };

                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }

                    // Totals
                    try {
                        totalSubscriptionAmount += Double.parseDouble(data.getSubscriptionAmount());
                    } catch (Exception ignored) {}
                    try {
                        totalGrossInterest += Double.parseDouble(data.getGrossInterest());
                    } catch (Exception ignored) {}
                    try {
                        totalTdsAmount += Double.parseDouble(data.getTdsAmount());
                    } catch (Exception ignored) {}
                }

                // Total Row
                SXSSFRow totalRow = sheet.createRow(rowNumber);
                totalRow.createCell(5).setCellValue("Total"); // Below Subscription Date
                totalRow.createCell(6).setCellValue(String.format("%.2f", totalSubscriptionAmount)); // Subscription Amount
                totalRow.createCell(20).setCellValue(String.format("%.2f", totalGrossInterest)); // Gross Interest
                totalRow.createCell(21).setCellValue(String.format("%.2f", totalTdsAmount)); // TDS Amount
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }

            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            log.error("Exception while writing the Excel file: " + e.getMessage(), e);
        }

        return byteArrayOutputStream != null ? byteArrayOutputStream.toByteArray() : null;
    }


    @Override
    public ResponseDataDTO getTdsSummaryForTaxAudit(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        return rbiBondReportsDAO.getTdsSummaryForTaxAudit(tdsSummaryForTaxAuditDTO);
    }

    @Override
    public ResponseDataDTO getReportDetailsDownload(TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getTdsSummaryForTaxAudit(tdsSummaryForTaxAuditDTO), tdsSummaryForTaxAuditDTO)));
            fileResponseDto.setFileName("TDS_SUMMARY_FOR_TAX_AUDIT_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in TdsSummaryForTaxAuditDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, TdsSummaryForTaxAuditDTO tdsSummaryForTaxAuditDTO) {
        List<TdsSummaryForTaxAuditDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        BigDecimal totalAmountPaidBalance = BigDecimal.ZERO;
        BigDecimal totalTaxDeductedBalance = BigDecimal.ZERO;
        BigDecimal taxDepositedBalance = BigDecimal.ZERO;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("TDS SUMMARY FOR TAX AUDIT REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "Challan Serial No",
                    "For The Month Of",
                    "Quater",
                    "Date Of Payment",
                    "Nature Of Payment",
                    "Quaterly Form",
                    "Total Amount Paid",
                    "Total Tax Deducted",
                    "Tax Deposited (Total Challan Amount)",
                    "Team",
                    "Difference [CTT] [J-I]",
                    "Teams Revert For Difference"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<TdsSummaryForTaxAuditDTO>) finalData.get("tdsSummaryForTaxAuditDTOList");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (TdsSummaryForTaxAuditDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getChallanSerialNo() != null ? data.getChallanSerialNo() : "",
                            data.getForTheMonthOf() != null ? data.getForTheMonthOf() : "",
                            data.getQuater() != null ? data.getQuater() : "",
                            data.getDateOfPayment() != null ? data.getDateOfPayment() : "",
                            data.getNatureOfPayment() != null ? data.getNatureOfPayment() : "",
                            data.getQuaterlyForm() != null ? data.getQuaterlyForm() : "",
                            data.getTotalAmountPaid() != null ? roundBalance(data.getTotalAmountPaid(), 2) : "",
                            data.getTotalTaxDeducted() != null ? roundBalance(data.getTotalTaxDeducted(), 2) : "",
                            data.getTaxDeposited() != null ? roundBalance(data.getTaxDeposited(), 2) : "",
                            data.getTeam() != null ? data.getTeam() : "",
                            data.getDifference() != null ? data.getDifference() : "",
                            data.getTeamsRevertForDifference() != null ? data.getTeamsRevertForDifference() : "",
                    };

                    if (data.getTotalAmountPaid() != null && !data.getTotalAmountPaid().isEmpty()) {
                        BigDecimal currentBalance = new BigDecimal(data.getTotalAmountPaid());
                        totalAmountPaidBalance = totalAmountPaidBalance.add(currentBalance.setScale(2, RoundingMode.HALF_UP));
                    }
                    if (data.getTotalTaxDeducted() != null && !data.getTotalTaxDeducted().isEmpty()) {
                        BigDecimal currentBalance = new BigDecimal(data.getTotalTaxDeducted());
                        totalTaxDeductedBalance = totalTaxDeductedBalance.add(currentBalance.setScale(2, RoundingMode.HALF_UP));
                    }
                    if (data.getTaxDeposited() != null && !data.getTaxDeposited().isEmpty()) {
                        BigDecimal currentBalance = new BigDecimal(data.getTaxDeposited());
                        taxDepositedBalance = taxDepositedBalance.add(currentBalance.setScale(2, RoundingMode.HALF_UP));
                    }
                    //log.info("cellValues.length: "+cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            SXSSFRow totalRow = sheet.createRow(rowNumber++);
            totalRow.createCell(6).setCellValue("Total");
            totalRow.createCell(7).setCellValue(totalAmountPaidBalance.setScale(2, RoundingMode.HALF_UP).toString());
            totalRow.createCell(8).setCellValue(totalTaxDeductedBalance.setScale(2, RoundingMode.HALF_UP).toString());
            totalRow.createCell(9).setCellValue(taxDepositedBalance.setScale(2, RoundingMode.HALF_UP).toString());
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getReportCancellationSubsCatCbt(ReportCancellationSubsCatCbtDTO reportCancellationSubsCatCbtDTO) {
        return rbiBondReportsDAO.getReportCancellationSubsCatCbt(reportCancellationSubsCatCbtDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageClaimMonthly(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        return rbiBondReportsDAO.getBrokerageClaimMonthly(brokerageClaimMonthlyDTO);
    }

    @Override
    public ResponseDataDTO getBrokerageClaimMonthlyDownload(BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getBrokerageClaimMonthly(brokerageClaimMonthlyDTO),brokerageClaimMonthlyDTO)));
            fileResponseDto.setFileName("BROKERAGE_CLAIM_MONTHLY_BROKER_WISE_INCOME_EXPENSE_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in BrokerageClaimMonthlyDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, BrokerageClaimMonthlyDTO brokerageClaimMonthlyDTO) {
        List<BrokerageClaimMonthlyDTO> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BROKERAGE CLAIM MONTHLY BROKER WISE INCOME & EXPENSE REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "BLA_NO",
                    "INVEST_AMT",
                    "CAS_TRANSFER_DATE",
                    "MATURITY_DATE",
                    "SCHEME",
                    "CFD",
                    "BRANCH_REF_NO",
                    "PAN NO.",
                    "BANK NAME",
                    "ADVICE NO.",
                    "APPLICANT NAME",
                    "ADDRESS1",
                    "ADDRESS2",
                    "CITY",
                    "STATE",
                    "PINCODE",
                    "BRANCH NAME",
                    "RCPT_DATE",
                    "GROUP_CODE",
                    "HDFC_BROKER_CODE",
                    "BROKER_NAME",
                    "ADDR1",
                    "ADDR2",
                    "CITY",
                    "STATE",
                    "PIN_CODE",
                    "PHONE",
                    "PAYMENT_MODE",
                    "ACCOUNT_TYPE_CODE",
                    "BANK_ACCOUNT",
                    "MICR_CODE",
                    "APPL_NO",
                    "STATUS",
                    "DE_STATUS",
                    "BROKER AMOUNT",
                    "GROSS BROKERAGE",
                    "NET BROKERAGE",
                    "GST",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BrokerageClaimMonthlyDTO>) finalData.get("list1");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BrokerageClaimMonthlyDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getInvestAmt() != null ? data.getInvestAmt() : "",
                            data.getCasTransferDate() != null ? data.getCasTransferDate() : "",
                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getScheme() != null ? data.getScheme() : "",
                            data.getCfd() != null ? data.getCfd() : "",
                            data.getBranchrefno() != null ? data.getBranchrefno() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getAdviceNo() != null ? data.getAdviceNo() : "",
                            data.getAppName() != null ? data.getAppName() : "",
                            data.getAddrs1() != null ? data.getAddrs1() : "",
                            data.getAddrs2() != null ? data.getAddrs2() : "",
                            data.getCity() != null ? data.getState() : "",
                            data.getPincode() != null ? data.getPincode() : "",
                            data.getBranchName() != null ? data.getBranchName() : "",
                            data.getRcptDate() != null ? data.getRcptDate() : "",
                            data.getGroupDate() != null ? data.getGroupDate() : "",
                            data.getHdfcBrokerCode() != null ? data.getHdfcBrokerCode() : "",
                            data.getBrokerName() != null ? data.getBrokerName() : "",
                            data.getAddr1() != null ? data.getAddr1() : "",
                            data.getAddr2() != null ? data.getAddr2() : "",
                            data.getCity1() != null ? data.getCity1() : "",
                            data.getState1() != null ? data.getState1() : "",
                            data.getPincode1() != null ? data.getPincode1() : "",
                            data.getPhone() != null ? data.getPhone() : "",
                            data.getPaymentMode() != null ? data.getPaymentMode() : "",
                            data.getAccTypeCode() != null ? data.getAccTypeCode() : "",
                            data.getBankAcc() != null ? data.getBankAcc() : "",
                            data.getMicrCode() != null ? data.getMicrCode() : "",
                            data.getAppNo() != null ? data.getAppNo() : "",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getDeStatus() != null ? data.getDeStatus() : "",
                            data.getBrokerAmt() != null ? data.getBrokerAmt() : "",
                            data.getGrossBrok() != null ? data.getGrossBrok() : "",
                            data.getNetBrok() != null ? data.getNetBrok() : "",
                            data.getGst1() != null ? data.getGst1() : "",
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getBondInterestNotCredited(BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        return rbiBondReportsDAO.getBondInterestNotCredited(bondInterestNotCreditedDTO);
    }

    @Override
    public ResponseDataDTO getBondInterestNotCreditedDownload(BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        if (bondInterestNotCreditedDTO.getFlag().equalsIgnoreCase("M")) {


            //return rbiBondReportsDAO.getBondActualInterestRunCreditedDetails(bondActualInterestRunCreditedDTO);

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();


            ResponseDataDTO fileResponseDto2 = rbiBondReportsDAO.getBondInterestNotCredited(bondInterestNotCreditedDTO);


            if (fileResponseDto2.getMessage().equalsIgnoreCase("SUCCESS")) {

                try {

                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(getBondActualInterestMergedRunNotCreditedDetailsExcel(fileResponseDto2, bondInterestNotCreditedDTO)));
                    fileResponseDto.setFileName("BOND_INTEREST_NOT_CREDITED_MERGED_REPORT.xlsx");
                    fileResponseDto2.setResult(fileResponseDto);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("Error in BondInterestNotCreditedDTO, ReportsBOImpl: " + e.getMessage());
                    resultMsg = CommonConstants.NDF;
                    fileResponseDto2.setMessage(resultMsg);
                }


                responseDataDTO.setMessage(resultMsg);
                return fileResponseDto2;

            } else {

                return fileResponseDto2;
            }


        } else {


            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {

                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getBondInterestNotCredited(bondInterestNotCreditedDTO), bondInterestNotCreditedDTO)));
                fileResponseDto.setFileName("BOND_INTEREST_NOT_CREDITED_UN_MERGED_REPORT.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in BondInterestNotCreditedDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        }
    }

    @Override
    public ResponseDataDTO getBondRepaymentReport(BondRepaymentReportDto bondRepaymentReportDto) {
        return rbiBondReportsDAO.getBondRepaymentReport(bondRepaymentReportDto);
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {
        List<BondInterestNotCreditedDTO> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BOND INTEREST NOT CREDITED REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "PAN NO.",
                    "BLA NO.",
                    "CUSTOMER NAME",
                    "BOND SUBSCRIPTION ID",
                    "SUBSCRIPTION DATE",
                    "SUBSCRIPTION AMOUNT",
                    "INTEREST FROM PERIOD",
                    "INTEREST TO PERIOD",
                    "INTEREST PAYMENT MODE",
                    "CREDIT ACCOUNT NUMBER",
                    "TYPE OF CERTIFICATE",
                    "TAX EXEMPTION LIMIT",
                    "LOW TAX PERCENTAGE",
                    "PAN STATUS",
                    "TOTAL INTEREST RS",
                    "TDS AMOUNT RS",
                    "NET INTEREST AMOUNT RS",
                    "REASON",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BondInterestNotCreditedDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondInterestNotCreditedDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getPanNumber() != null ? data.getPanNumber() : "",
                            data.getBlaNumber() != null ? data.getBlaNumber() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getSubscriptionID() != null ? data.getSubscriptionID() : "",
                            data.getSubscriptionDate() != null ? data.getSubscriptionDate() : "",
                            data.getSubscriptionAmount() != null ? data.getSubscriptionAmount() : "",
                            data.getInterestFromPeriod() != null ? data.getInterestFromPeriod() : "",
                            data.getInterestToPeriod() != null ? data.getInterestToPeriod() : "",
                            data.getInterestPaymentMode() != null ? data.getInterestPaymentMode() : "",
                            data.getCreditAccountNumber() != null ? data.getCreditAccountNumber() : "",
                            data.getTypeOfCertificate() != null ? data.getTypeOfCertificate() : "",
                            data.getTaxExemptionLimit() != null ? data.getTaxExemptionLimit() : "",
                            data.getLowTaxPercentage() != null ? data.getLowTaxPercentage() : "",
                            data.getPanStatus() != null ? data.getPanStatus() : "",
                            data.getTotalInterest() != null ? data.getTotalInterest() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getNetInterestAmount() != null ? data.getNetInterestAmount() : "",
                            data.getReason() != null ? data.getReason() : "",
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }



    public byte[] getBondActualInterestMergedRunNotCreditedDetailsExcel(ResponseDataDTO responseDataDTO, BondInterestNotCreditedDTO bondInterestNotCreditedDTO) {




        List<BondInterestNotCreditedDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        BigDecimal subscriptionAmounttt = BigDecimal.ZERO;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Bond Actual Interest Merged Not Run Credited Report");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK"+     "            Branch Name : " + solDesc + "                      Branch Id : " + loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));






            SXSSFRow bankSchmeNameRow = sheet.createRow(2);
            SXSSFCell bankSchmeNameRowCell = bankSchmeNameRow.createCell(0);
            bankSchmeNameRowCell.setCellValue("Name of the Bond Scheme:: " + bondInterestNotCreditedDTO.getSchemeName());

            // Create and set branch info cell style
            CellStyle bankInfoCellStylee = workbook.createCellStyle();
            bankInfoCellStylee.setAlignment(HorizontalAlignment.CENTER);
            bankSchmeNameRowCell.setCellStyle(bankInfoCellStylee);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 9));









            SXSSFRow fromDateTODateRow = sheet.createRow(3);
            SXSSFCell fromDateTODateCell = fromDateTODateRow.createCell(0);
            fromDateTODateCell.setCellValue("Interest Payment From Date : " + bondInterestNotCreditedDTO.getFromDate() + "                           Interest Payment To Date : " + bondInterestNotCreditedDTO.getToDate());

            // Create and set branch info cell style
            CellStyle fromDateToDateStylee = workbook.createCellStyle();
            fromDateToDateStylee.setAlignment(HorizontalAlignment.CENTER);
            fromDateTODateCell.setCellStyle(fromDateToDateStylee);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 9));



            // === Secondary header row (Row 3) ===
//            SXSSFRow headerRoww = sheet.createRow(4);
//
//
//            String[] headerss = {
//                    "",
//                    "Basic Details From BLA cum Subscription",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "OTHER SCHEMES INTEREST RELATED INFO",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "PAN STATUS",
//                    "",
//                    "TDS EXEMPTION DETAILS",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "FRSB INTEREST PAYOUT DETAILS",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    "",
//                    ""
//
//
//            };
//
//            CellStyle headerCellStylee = workbook.createCellStyle();
//            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
//            font.setBold(true);
//            headerCellStylee.setFont(font);
//
//            // Track maximum column widths
//            int[] maxColumnWidthss = new int[headerss.length];
//            for (int i = 0; i < headerss.length; i++) {
//                SXSSFCell celll = headerRoww.createCell(i);
//                celll.setCellValue(headerss[i]);
//                celll.setCellStyle(headerCellStylee);
//                maxColumnWidthss[i] = headerss[i].length();
//            }












            /// Use row 3 instead of 2
            SXSSFRow headerRow = sheet.createRow(4);

            String[] headers = {
                    "S.No",
                    "Mode of Payment",
//                    "Branch Ref No",
                    "BLA No",
//                    "Ref No",
                    "App Name 1",
                    "App Name 2",
                    "App Name 3",
                    "Address 1",
                    "Address 2",
                    "Rec Name",
                    "Rec Parent Name",
                    "Pin Code",
                    "Phone No",
                    "Application Status",
                    "Investment Amount",
//                    "CFD",
//                    "Maturity Date",
                    "Account No",
                    "Subscription Count",
                    "Bank Sort Code",
                    "Bank Name",
                    "Pan No Name",
                    "8 % Interest Payout",
                    "Non Cumulative Int",
                    "Cumulative Int",
                    "7.75% interest payout",
                    "Non Cumulative Int 775",
                    "Cumulative Int 775",
                    "Total",
                    "In-Operative PAN",
                    "Non-Filers",
                    "Age",
                    "Taxable Limit",
                    "Waiver Document",
                    "Waiver Document Ref No",
                    "RRB-TDS rate",
                    "Threshold Exceed Amount",
                    "Threshold Exceed TDS Amount",
                    "Exemption Rate %",
                    "Exemption Amount",
                    "Exemption TDS Amount",
                    "Surcharge %",
                    "Edu Cess %",
                    "Base Rate Amount",
                    "Surcharge Amount",
                    "Edu cess Amount",
                    "TDS Amount",
                    "TDS Recovery Amount",
                    "TDS Refund Amount",
                    "Int Rate",
                    "Last Int Paid",
                    "Pending Interest",
                    "Current Half-Yearly Interest",
                    "Future Interest",
                    "Gross Interest Amount",
                    "Int Paid (Net Interest Amount)",
                    "Payment Date",
                    "Return Reason",
                    "Return Amount",
                    "ReInitiated Amount",
                    "ReInitiated Date",
                    "Payment Date",
                    "ReInitiate Payment Mode"


            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font fontt = workbook.createFont();
            fontt.setBold(true);
            headerCellStyle.setFont(fontt);

            // Track maximum column widths
            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<BondInterestNotCreditedDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 6;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondInterestNotCreditedDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
//                            data.getBranchRefNo() != null ? data.getBranchRefNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
//                            data.getRefNo() != null ? data.getRefNo() : "",
                            data.getAppName1() != null ? data.getAppName1() : "",
                            data.getAppName2() != null ? data.getAppName2() : "",
                            data.getAppName3() != null ? data.getAppName3() : "",
                            data.getAddress1() != null ? data.getAddress1() : "",
                            data.getAddress2() != null ? data.getAddress2() : "",
                            data.getRecName() != null ? data.getRecName() : "",
                            data.getRecParentName() != null ? data.getRecParentName() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                            data.getPhoneNo() != null ? data.getPhoneNo() : "",
                            data.getAppStatus() != null ? data.getAppStatus() : "",
                            data.getInvestmentAmount() != null ? data.getInvestmentAmount() : "",
    //                            data.getCfd() != null ? data.getCfd() : "",
    //                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getSubscriptionCount() != null ? data.getSubscriptionCount() : "",
                            data.getBankSortCode() != null ? data.getBankSortCode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getPanNoName() != null ? data.getPanNoName() : "",
                            data.getInterestPayOut8Per() != null ? data.getInterestPayOut8Per() : "",
                            data.getNonCumulativeInt() != null ? data.getNonCumulativeInt() : "",
                            data.getCumulativeInt() != null ? data.getCumulativeInt() : "",
                            data.getInterestPayOut7Per() != null ? data.getInterestPayOut7Per() : "",
                            data.getNonCumulativeIntFor7() != null ? data.getNonCumulativeIntFor7() : "",
                            data.getCumulativeIntFor7() != null ? data.getCumulativeIntFor7() : "",
                            data.getTotal() != null ? data.getTotal() : "",
                            data.getInOperativePAN() != null ? data.getInOperativePAN() : "",
                            data.getNonFilers() != null ? data.getNonFilers() : "",
                            data.getAge() != null ? data.getAge() : "",
                            data.getTaxableLimit() != null ? data.getTaxableLimit() : "",
                            data.getWaiverDocument() != null ? data.getWaiverDocument() : "",
                            data.getWaiverDocumentRefNo() != null ? data.getWaiverDocumentRefNo() : "",
                            data.getRrbTDSRate() != null ? data.getRrbTDSRate() : "",
                            data.getThresholdExceedAmount() != null ? data.getThresholdExceedAmount() : "",
                            data.getThresholdExceedTDSAmount() != null ? data.getThresholdExceedTDSAmount() : "",
                            data.getExemptionRate() != null ? data.getExemptionRate() : "",
                            data.getExemptionAmount() != null ? data.getExemptionAmount() : "",
                            data.getExemptionTDSAmount() != null ? data.getExemptionTDSAmount() : "",
                            data.getSurcharge() != null ? data.getSurcharge() : "",
                            data.getEduCess() != null ? data.getEduCess() : "",
                            data.getBaseRateAmount() != null ? data.getBaseRateAmount() : "",
                            data.getSurchargeAmount() != null ? data.getSurchargeAmount() : "",
                            data.getEduCessAmount() != null ? data.getEduCessAmount() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getTdsRecoveryAmount() != null ? data.getTdsRecoveryAmount() : "",
                            data.getTdsRefundAmount() != null ? data.getTdsRefundAmount() : "",
                            data.getIntRate() != null ? data.getIntRate() : "",
                            data.getLastIntPaid() != null ? data.getLastIntPaid() : "",
                            data.getPendingInterest() != null ? data.getPendingInterest() : "",
                            data.getCurrentHalfYearlyInterest() != null ? data.getCurrentHalfYearlyInterest() : "",
                            data.getFutureInterest() != null ? data.getFutureInterest() : "",
                            data.getGrossInterestAmount() != null ? data.getGrossInterestAmount() : "",
                            data.getIntPaid() != null ? data.getIntPaid() : "",
                            data.getPaymentDate() != null ? data.getPaymentDate() : "",
                            data.getReturnReason() != null ? data.getReturnReason() : "",
                            data.getReturnAmt() != null ? data.getReturnAmt() : "",
                            data.getReInitiatedAmt() != null ? data.getReInitiatedAmt() : "",
                            data.getReInitiatedDate() != null ? data.getReInitiatedDate() : "",
                            data.getPaymentDateOfReturn() != null ? data.getPaymentDateOfReturn() : "",
                            data.getReInitiatedPaymentMode() != null ? data.getReInitiatedPaymentMode() : "",
                    };


                    if (data.getInvestmentAmount() != null && !data.getInvestmentAmount().isEmpty()) {
                        BigDecimal subscriptionAmountt = new BigDecimal(data.getInvestmentAmount());
                        subscriptionAmounttt = subscriptionAmounttt.add(subscriptionAmountt.setScale(2, RoundingMode.HALF_UP));
                    }

                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);


            SXSSFRow totalRow = sheet.createRow(rowNumber++);
            totalRow.createCell(12).setCellValue("Total");
            totalRow.createCell(13).setCellValue(subscriptionAmounttt.setScale(2, RoundingMode.HALF_UP).toString());


            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getBondRepaymentReportDownload(BondRepaymentReportDto bondRepaymentReportDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getBondRepaymentReport(bondRepaymentReportDto), bondRepaymentReportDto)));
            fileResponseDto.setFileName("BOND_REPAYMENT_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in BondRepaymentReportDto, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, BondRepaymentReportDto bondRepaymentReportDto) {
        List<BondRepaymentReportDto> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("BOND REPAYMENT REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "PAN NO.",
                    "BLA NO.",
                    "HOLDER NAME",
                    "SUBSCRIPTION ID",
                    "DATE OF SUBSCRIPTION",
                    "SUBSCRIPTION AMOUNT(RS)",
                    "GROSS AMOUNT",
                    "TDS PERCENTAGE",
                    "TDS AMOUNT",
                    "AMOUNT REPAID(RS)",
                    "MATURITY DATE",
                    "DATE OF REPAYMENT",
                    "MODE OF INTEREST PAYMENT/PRINCIPAL REPAYMENT",
                    "OPERATIVE ACCOUNT NUMBER",
                    "DD/PO ACCOUNT NUMBER",
                    "BANK CODE",
                    "BRANCH CODE",
                    "IFSC CODE OF THE CREDIT BANK",
                    "NAME OF THE PAYEE",
                    "CREDIT ACCOUNT NUMBER",
                    "IFSC CODE",
                    "MICR CODE",
                    "STATUS",
                    "RETURN DESCRIPTION"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BondRepaymentReportDto>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondRepaymentReportDto data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getSubsId() != null ? data.getSubsId() : "",
                            data.getDateOfSubscription() != null ? data.getDateOfSubscription() : "",
                            data.getSubsAmt() != null ? data.getSubsAmt() : "",
                            data.getGrossAmount() != null ? data.getGrossAmount() : "",
                            data.getTdsPercentage() != null ? data.getTdsPercentage() : "",
                            data.getTdsAmount() != null ? data.getTdsAmount() : "",
                            data.getAmtRepaid() != null ? data.getAmtRepaid() : "",
                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getDateOfRepayment() != null ? data.getDateOfRepayment() : "",
                            data.getPrincipalRepayment() != null ? data.getPrincipalRepayment() : "",
                            data.getOperativeAccountNumber() != null ? data.getOperativeAccountNumber() : "",
                            data.getDdOrPoAccountNumber() != null ? data.getDdOrPoAccountNumber() : "",
                            data.getBankCode() != null ? data.getBankCode() : "",
                            data.getBranchCode() != null ? data.getBranchCode() : "",
                            data.getIfscCodeOfCreditBank() != null ? data.getIfscCodeOfCreditBank() : "",
                            data.getNameOfPayee() != null ? data.getNameOfPayee() : "",
                            data.getCreditAccountNumber() != null ? data.getCreditAccountNumber() : "",
                            data.getIfscCodeSecondary() != null ? data.getIfscCodeSecondary() : "",
                            data.getMicrCode() != null ? data.getMicrCode() : "",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getReturnDescription() != null ? data.getReturnDescription() : ""
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getMinorAccountDetails(MinorAccountDetailsDTO minorAccountDetailsDTO) {
        return rbiBondReportsDAO.getMinorAccountDetails(minorAccountDetailsDTO);
    }

    @Override
    public ResponseDataDTO getBLASubscriptionNominationDetails(BLASubscriptionNominationDTO blaSubscriptionNominationDTO) {
        return rbiBondReportsDAO.getBLASubscriptionNominationDetails(blaSubscriptionNominationDTO);
    }

    @Override
    public ResponseDataDTO getAcknowledgement(AcknowledgementDTO acknowledgementDTO) {
        return rbiBondReportsDAO.getAcknowledgement(acknowledgementDTO);
    }

    @Override
    public ResponseDataDTO getAcknowledgementconfirmation(AcknowledgementconfirmationDTO acknowledgementconfirmationDTO) {
        return rbiBondReportsDAO.getAcknowledgementconfirmation(acknowledgementconfirmationDTO);
    }

    @Override
    public ResponseDataDTO getReportOnUnverifiedDetails(ReportOnUnverifiedDTO reportOnUnverifiedDTO) {
        return rbiBondReportsDAO.getReportOnUnverifiedDetails(reportOnUnverifiedDTO);
    }

    @Override
    public ResponseDataDTO getPanAadharDetails(PanAadhaarDTO panAadhaarDTO) {
        return rbiBondReportsDAO.getPanAadharDetails(panAadhaarDTO);
    }

    @Override
    public ResponseDataDTO getPanAadharDetailsDownload(PanAadhaarDTO panAadhaarDTO) {


        if (panAadhaarDTO.getDownloadType().equalsIgnoreCase("csv")) {

            return rbiBondReportsDAO.getPanAadharDetails(panAadhaarDTO);


        } else {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getPanAadharDetails(panAadhaarDTO), panAadhaarDTO)));
                fileResponseDto.setFileName("PAN_AADHAAR_NON_LINKAGE_INVALID_PAN_REPORT.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in PanAadhaarDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;

        }
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, PanAadhaarDTO panAadhaarDTO) {
        List<PanAadhaarDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("PAN-AADHAAR NON-LINKAGE/INVALID PAN REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));

            // === Secondary header row (Row 3) ===
            SXSSFRow headerRow = sheet.createRow(2); // Use row 3 instead of 2

            String[] headers = {
                    "S.No.",
                    "PAN No",
                    "Holder Name",
                    "PAN Status",
                    "Last Status Date",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<PanAadhaarDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (PanAadhaarDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getPanStatus() != null ? data.getPanStatus() : "",
                            data.getLastStatusDate() != null ? data.getLastStatusDate() : "",
                    };

                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getHalfYearlyInterestAdvice(HalfYearlyInterestAdviceDTO halfYearlyInterestAdviceDTO) {
        return rbiBondReportsDAO.getHalfYearlyInterestAdvice(halfYearlyInterestAdviceDTO);
    }

    @Override
    public ResponseDataDTO getModeOfHoldingChangeDetails(ModeOfHoldingReportDto modeOfHoldingReportDto) {
        return rbiBondReportsDAO.getModeOfHoldingChangeDetails(modeOfHoldingReportDto);
    }

    @Override
    public ResponseDataDTO getModeOfHoldingDownload(ModeOfHoldingReportDto modeOfHoldingReportDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getModeOfHoldingChangeDetails(modeOfHoldingReportDto), modeOfHoldingReportDto)));
            fileResponseDto.setFileName("MODE_OF_HOLDING_CHANGE_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in ModeOfHoldingReportDto, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, ModeOfHoldingReportDto modeOfHoldingReportDto) {
        List<ModeOfHoldingReportDto> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("MODE OF HOLDING CHANGE REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));

            // === Secondary header row (Row 3) ===
            SXSSFRow headerRow = sheet.createRow(2); // Use row 3 instead of 2

            String[] headers = {
                    "S.No.",
                    "Holder Name",
                    "PAN No",
                    "Mode of Holding",
                    "From BLA Account Number",
                    "Active Subscription Id",
                    "Active Subscription Count",
                    "To BLA Account Number",
                    "Date of Holding Change",
                    "Remarks",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<ModeOfHoldingReportDto>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (ModeOfHoldingReportDto data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                            data.getFromBlaAccountNumber() != null ? data.getFromBlaAccountNumber() : "",
                            data.getActiveSubscriptionId() != null ? data.getActiveSubscriptionId() : "",
                            data.getActiveSubscriptionCount() != null ? data.getActiveSubscriptionCount() : "",
                            data.getToBlaAccountNumber() != null ? data.getToBlaAccountNumber() : "",
                            data.getDateOfHoldingChange() != null ? data.getDateOfHoldingChange() : "",
                            data.getRemarks() != null ? data.getRemarks() : "",
                    };

                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getBrokerEnrolledDetails(BrokerEnrolledDTO brokerEnrolledDTO) {

       if( brokerEnrolledDTO.getDetailsFlag()!= null && brokerEnrolledDTO.getDetailsFlag().equalsIgnoreCase("D")) {
            return rbiBondReportsDAO.getBrokerEnrolledDetails(brokerEnrolledDTO);


       }
        else if (brokerEnrolledDTO.getDownloadType() !=null &&brokerEnrolledDTO.getDownloadType().equalsIgnoreCase("csv")) {

            return rbiBondReportsDAO.getBrokerEnrolledDetails(brokerEnrolledDTO);
        } else {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateBrokerAppointmentEnrolledReportExcel(rbiBondReportsDAO.getBrokerEnrolledDetails(brokerEnrolledDTO), brokerEnrolledDTO)));
                fileResponseDto.setFileName("Broker_appointment_enrolled_report_and_broker_appointment_letter_to_broker.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in BrokerEnrolledDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        }
    }

    public byte[] generateBrokerAppointmentEnrolledReportExcel(ResponseDataDTO responseDataDTO, BrokerEnrolledDTO brokerEnrolledDTO1) {
        List<BrokerEnrolledDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        BigDecimal totalInterest = BigDecimal.ZERO;
        BigDecimal totalTdsAmount = BigDecimal.ZERO;
        BigDecimal tdsDeductionAmount = BigDecimal.ZERO;
        BigDecimal netIntestAmount = BigDecimal.ZERO;
        BigDecimal netInvestAmountt = BigDecimal.ZERO;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Broker appointment /enrolled report and broker appointment letter to brokers");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK       Branch Name : " + solDesc + "             Branch Id  : " + loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
//            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // === Primary header row (Row 2) ===
//            SXSSFRow primaryHeaderRow = sheet.createRow(2);
//            primaryHeaderRow.createCell(0).setCellValue(""); // "S.No." not merged




//            SXSSFRow bankNameRow = sheet.createRow(1);
//            SXSSFCell bankNameCell = bankNameRow.createCell(0);
//            bankNameCell.setCellValue("Bank Name : HDFC BANK       Branch Name : " + solDesc + " Branch Id : " + loginSolid);
//
//            // Create and set bank info cell style
//            CellStyle bankNameCellStyle = workbook.createCellStyle();
//            bankNameCellStyle.setAlignment(HorizontalAlignment.CENTER);
//            bankNameCell.setCellStyle(bankNameCellStyle);



            SXSSFRow schmeNameRow = sheet.createRow(2);
            SXSSFCell schmeNameCell = schmeNameRow.createCell(0);
            schmeNameCell.setCellValue("Name of the Bond Scheme : "+ brokerEnrolledDTO1.getSchemeName()  );

            // Create and set bank info cell style
            CellStyle schmeNameStyle = workbook.createCellStyle();
            schmeNameStyle.setAlignment(HorizontalAlignment.CENTER);
            schmeNameCell.setCellStyle(schmeNameStyle);

            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));




            SXSSFRow fromToDateRow = sheet.createRow(3);
            SXSSFCell fromToDateCell = fromToDateRow.createCell(0);
            fromToDateCell.setCellValue("FromDate :"+ brokerEnrolledDTO1.getFromDate() +"           To Date :   " + brokerEnrolledDTO1.getToDate() );

            // Create and set bank info cell style
            CellStyle fromToDateStyle = workbook.createCellStyle();
            fromToDateStyle.setAlignment(HorizontalAlignment.CENTER);
            fromToDateCell.setCellStyle(fromToDateStyle);

            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 7));




// === Secondary header row (Row 3) ===
            SXSSFRow headerRow = sheet.createRow(4); // Use row 3 instead of 2

            String[] headers = {
                    "S. No.",
                    "BLA no",
                    "Invest Amount",
                    "CAS Transfer Date",
                    "Maturity Date",
                    "Scheme",
                    "CFD",
                    "Branch Ref No",
                    "Advice no",
                    "Applicant Name",
                    "Address 1",
                    "Address 2",
                    "City",
                    "State",
                    "Pin code",
                    "Branch Name",
                    "RCPT Date",
                    "Group Code",
                    "HDFC Broker Code",
                    "Broker Name",
                    "Broker PAN No",
                    "Address 1",
                    "Address 2",
                    "City",
                    "State",
                    "Pin code",
                    "Phone",
                    "Payment Mode",
                    "Account Type Code",
                    "Bank Account",
                    "MICR Code",
                    "Application No",
                    "Status",
                    "DE Status",
                    "Broker Amount",
                    "HDFC Branch Code"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<BrokerEnrolledDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 5;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BrokerEnrolledDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getBlaNumber() != null ? data.getBlaNumber() : "",
                            data.getInvestAmount() != null ? data.getInvestAmount() : "",
                            data.getCasTransferDate() != null ? data.getCasTransferDate() : "",
                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getScheme() != null ? data.getScheme() : "",
                            data.getCfd() != null ? data.getCfd() : "",
                            data.getBranchRefNo() != null ? data.getBranchRefNo() : "",
                            data.getAdviceNo() != null ? data.getAdviceNo() : "",
                            data.getApplicantName() != null ? data.getApplicantName() : "",
                            data.getAddress1() != null ? data.getAddress1() : "",
                            data.getAddress2() != null ? data.getAddress2() : "",
                            data.getCity() != null ? data.getCity() : "",
                            data.getState() != null ? data.getState() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                            data.getBranchName() != null ? data.getBranchName() : "",
                            data.getRcptDate() != null ? data.getRcptDate() : "",
                            data.getGroupCode() != null ? data.getGroupCode() : "",
                            data.getHdfcBrokerCode() != null ? data.getHdfcBrokerCode() : "",
                            data.getBrokerName() != null ? data.getBrokerName() : "",
                            data.getBrokerPanNumber() != null ? data.getBrokerPanNumber() : "",
                            data.getBrokerAddress1() != null ? data.getBrokerAddress1() : "",
                            data.getBrokerAddress2() != null ? data.getBrokerAddress2() : "",
                            data.getBrokerCity() != null ? data.getBrokerCity() : "",
                            data.getBrokerState() != null ? data.getBrokerState() : "",
                            data.getBrokerPinCode() != null ? data.getBrokerPinCode() : "",
                            data.getPhone() != null ? data.getPhone() : "",
                            data.getPaymentMode() != null ? data.getPaymentMode() : "",
                            data.getAccountTypeCode() != null ? data.getAccountTypeCode() : "",
                            data.getBankAccount() != null ? data.getBankAccount() : "",
                            data.getMicrCode() != null ? data.getMicrCode() : "",
                            data.getApplNo() != null ? data.getApplNo() : "",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getDeStatus() != null ? data.getDeStatus() : "",
                            data.getBrokerAmount() != null ? data.getBrokerAmount() : "",
                            data.getHdfcBranchCode() != null ? data.getHdfcBranchCode() : "",
                    };









                    if (data.getBrokerAmount() != null&&!data.getBrokerAmount().isEmpty()) {
                        BigDecimal netIntestAmountt = new BigDecimal(data.getBrokerAmount());
                        netIntestAmount = netIntestAmount.add(netIntestAmountt.setScale(2, RoundingMode.HALF_UP));
                    }


                    if (data.getInvestAmount() != null&&!data.getInvestAmount().isEmpty()) {
                        BigDecimal netInvestAmount = new BigDecimal(data.getInvestAmount());
                        netInvestAmountt = netInvestAmountt.add(netInvestAmount.setScale(2, RoundingMode.HALF_UP));
                    }




                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            //log.info("Processed Data rows: "+ rowNumber);






            SXSSFRow totalRow = sheet.createRow(rowNumber++);

            totalRow.createCell(33).setCellValue("Total Amount".toString());
            totalRow.createCell(34).setCellValue(netIntestAmount.setScale(2, RoundingMode.HALF_UP).toString());


            totalRow.createCell(1).setCellValue("Total Investment Amount".toString());
            totalRow.createCell(2).setCellValue(netInvestAmountt.setScale(2, RoundingMode.HALF_UP).toString());
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getBondTransferIn(BondTransferInDTO bondTransferInDTO) {
        return rbiBondReportsDAO.getBondTransferIn(bondTransferInDTO);
    }

    @Override
    public ResponseDataDTO getDisablingDeactivating(DisablingDeactivatingDTO disablingDeactivatingDTO) {
        return rbiBondReportsDAO.getDisablingDeactivating(disablingDeactivatingDTO);
    }
    @Override
    public ResponseDataDTO getDashBoardReportForInwardOutwardDownload(DashBoardReportForInwardOutwardDTO dashBoardReportForInwardOutwardDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateDashBoardInwardOutwardExcel(rbiBondReportsDAO.getDashBoardReportForInwardOutward(dashBoardReportForInwardOutwardDTO),dashBoardReportForInwardOutwardDTO)));
            fileResponseDto.setFileName("DASHBOARD_INWARD_OUTWARD_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in DashBoardReportForInwardOutwardDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateDashBoardInwardOutwardExcel(ResponseDataDTO responseDataDTO,
                                                      DashBoardReportForInwardOutwardDTO dashBoardReportForInwardOutwardDTO) {

        List<DashBoardReportForInwardOutwardDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {

            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();

            // ================= TITLE =================
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("DASHBOARD REPORT");

            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 13));

            // ================= BANK INFO =================
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);

            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 13));

            // ================= GET DATA =================

            Integer inwardCount = 0;
            Integer outwardCount = 0;
            Integer pendingCount = 0;
            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<DashBoardReportForInwardOutwardDTO>) finalData.get("dashBoardReportList");
             inwardCount = Integer.parseInt(finalData.get("inwardsCount").toString());
             outwardCount = Integer.parseInt(finalData.get("outwardsCount").toString());
             pendingCount = Integer.parseInt(finalData.get("pendingForOutwardsCount").toString());

            SXSSFRow countRow = sheet.createRow(2);

            countRow.createCell(0).setCellValue("Inwards Count: " + inwardCount);
            countRow.createCell(4).setCellValue("Outwards Count: " + outwardCount);
            countRow.createCell(8).setCellValue("Pending Count: " + pendingCount);

            // ================= HEADER ROW =================
            SXSSFRow headerRow = sheet.createRow(3);

            String[] headers = {
                    "S.No", "FTS Bar Code Number", "Scheme Name", "BLA number", "PAN No",
                    "Customer Name", "Request Code", "Request Description",
                    "Inward Reference Number", "Inward Date",
                    "Outward Reference Number", "Outward Date", "POD Number", "No. of Days",
                    "Outward Reason", "Status"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];

            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            // ================= DATA =================
            int rowNumber = 4;
            int sNo = 1;

            if (list != null && !list.isEmpty()) {

                for (DashBoardReportForInwardOutwardDTO data : list) {

                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int col = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getFts() != null ? data.getFts() : "",
                            data.getSchemeName() != null ? data.getSchemeName() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getUserName() != null ? data.getUserName() : "",
                            data.getRequestCode() != null ? data.getRequestCode() : "",
                            data.getReqDescription() != null ? data.getReqDescription() : "",
                            data.getInwardRefNo() != null ? data.getInwardRefNo() : "",
                            data.getInwardDate() != null ? data.getInwardDate() : "",
                            data.getOutwardRefNo() != null ? data.getOutwardRefNo() : "",
                            data.getOutwardDate() != null ? data.getOutwardDate() : "",
                            data.getPodNumber() != null ? data.getPodNumber() : "",
                            data.getNoOfDays() != null ? data.getNoOfDays() : "",
                            data.getOutwardReason() != null ? data.getOutwardReason() : "",
                            data.getStatus() != null ? data.getStatus() : ""
                    };

                    for (int i = 0; i < cellValues.length; i++) {
                        SXSSFCell cell = row.createCell(col++);
                        cell.setCellValue(cellValues[i]);

                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            }

            // ================= COLUMN WIDTH =================
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();

        } catch (IOException e) {
            log.error("Exception while writing Excel: " + e.getMessage(), e);
        }

        return byteArrayOutputStream != null ? byteArrayOutputStream.toByteArray() : new byte[0];
    }
    @Override
    public ResponseDataDTO getDisablingDeactivatingDownload(DisablingDeactivatingDTO disablingDeactivatingDTO) {

        if(disablingDeactivatingDTO!= null && disablingDeactivatingDTO.getDownloadType()!= null && "csv".equalsIgnoreCase(disablingDeactivatingDTO.getDownloadType())) {
           return rbiBondReportsDAO.getDisablingDeactivating(disablingDeactivatingDTO);
        }
        else{
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();

            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getDisablingDeactivating(disablingDeactivatingDTO), disablingDeactivatingDTO)));
                fileResponseDto.setFileName("DISABLING_DEACTIVATING_OF_BROKERS_AFTER_2_YEARS_OF_NON_SOURCING.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in DisablingDeactivatingDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        }
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, DisablingDeactivatingDTO disablingDeactivatingDTO) {
        List<DisablingDeactivatingDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("DISABLING/DEACTIVATING OF BROKERS AFTER 2 YEARS OF NON-SOURCING REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));

            // === Secondary header row (Row 3) ===
            SXSSFRow headerRow = sheet.createRow(2); // Use row 3 instead of 2

            String[] headers = {
                    "S.No.",
                    "HDFC Broker Code",
                    "Broker Sort Code",
                    "Broker Name",
                    "Enable Broker",
                    "Bank Account",
                    "PAN",
                    "GST Number",
                    "MICR/IFSC Code",
                    "Bank Name",
                    "Status",
                    "No Of Month",
                    "Address 1",
                    "Address 2",
                    "District",
                    "State",
                    "Pin Code",
                    "Phone",
                    "Email ID",
                    "Mobile",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<DisablingDeactivatingDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (DisablingDeactivatingDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getHdfcBrokercode() != null ? data.getHdfcBrokercode() : "",
                            data.getBrokerSortCode() != null ? data.getBrokerSortCode() : "",
                            data.getBrokerName() != null ? data.getBrokerName() : "",
                            data.getEnableBroker() != null ? data.getEnableBroker() : "",
                            data.getBankAccount() != null ? data.getBankAccount() : "",
                            data.getpAN() != null ? data.getpAN() : "",
                            data.getGstNo() != null ? data.getGstNo() : "",
                            data.getMicrCode() != null ? data.getMicrCode()
                                    : (data.getIfscCode() != null ? data.getIfscCode() : ""),
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getNoOfMonth() != null ? data.getNoOfMonth() : "",
                            data.getAddress() != null ? data.getAddress() : "",
                            data.getAddress2() != null ? data.getAddress2() : "",
                            data.getDistrict() != null ? data.getDistrict() : "",
                            data.getState() != null ? data.getState() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                            data.getPhone() != null ? data.getPhone() : "",
                            data.getEmailId() != null ? data.getEmailId() : "",
                            data.getMobileNo() != null ? data.getMobileNo() : "",

                    };


                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        String cellValue = cellValues[i] != null ? cellValues[i] : "";
                        row.createCell(cellNumber++).setCellValue(cellValue);
                        if (i < maxColumnWidths.length && cellValue.length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValue.length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getTdsDeductionTdsrate(TdsDeductionDTO tdsDeductionDTO) {
        return rbiBondReportsDAO.getTdsDeductionTdsrate(tdsDeductionDTO);
    }

    @Override
    public ResponseDataDTO getTdsDeductionTdsrateDownload(TdsDeductionDTO tdsDeductionDTO) {
        if (tdsDeductionDTO.getDownloadType().equalsIgnoreCase("csv")) {

            return rbiBondReportsDAO.getTdsDeductionTdsrate(tdsDeductionDTO);
        } else {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getTdsDeductionTdsrate(tdsDeductionDTO), tdsDeductionDTO)));
                fileResponseDto.setFileName("TDS_DEDUCTION_AT_OTHER_THAN_NORMAL_TDS_RATE_REPORT.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in TdsDeductionDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        }
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, TdsDeductionDTO tdsDeductionDTO) {
        List<TdsDeductionDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
        String solDesc = Optional.ofNullable(UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        BigDecimal totalInterest = BigDecimal.ZERO;
        BigDecimal totalTdsAmount = BigDecimal.ZERO;
        BigDecimal tdsDeductionAmount = BigDecimal.ZERO;
        BigDecimal netIntestAmount = BigDecimal.ZERO;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("TDS DEDUCTION AT OTHER THAN NORMAL TDS RATE REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
//            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));

            // === Primary header row (Row 2) ===
//            SXSSFRow primaryHeaderRow = sheet.createRow(2);
//            primaryHeaderRow.createCell(0).setCellValue(""); // "S.No." not merged

// === Secondary header row (Row 3) ===
            SXSSFRow headerRow = sheet.createRow(2); // Use row 3 instead of 2

            String[] headers = {
                    "S.No.",
                    "PAN No",
                    "BLA No",
                    "Customer Name",
                    "Bond Subscription Id",
                    "Financial year",
                    "Type of Certificate",
                    "Tax Excemption Limit",
                    "Low/No Tax Percentage",
                    "PAN Status",
                    "Interest Payment Date",
                    "Total Interest Rs",
                    "TDS Amount Rs",
                    "TDS Deducted Percentage",
                    "TDS Deducted Amount(Rs)",
                    "Ner Interest Amount Paid",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<TdsDeductionDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (TdsDeductionDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getCustomerName() != null ? data.getCustomerName() : "",
                            data.getSubsId() != null ? data.getSubsId() : "",
                            data.getFinancialYear() != null ? data.getFinancialYear() : "",
                            data.getTypeofCertificate() != null ? data.getTypeofCertificate() : "",
                            data.getTaxExcemlimit() != null ? data.getTaxExcemlimit() : "",
                            data.getLownotaxper() != null ? data.getLownotaxper() : "",
                            data.getPanStatus() != null ? data.getPanStatus() : "",
                            data.getInterestPaymentDate() != null ? data.getInterestPaymentDate() : "",
                            data.getTotalInterest() != null ? data.getTotalInterest() : "",
                            data.getTdsAmt() != null ? data.getTdsAmt() : "",
                            data.getTdsDeductionper() != null ? data.getTdsDeductionper() : "",
                            data.getTdsDeductionAmt() != null ? data.getTdsDeductionAmt() : "",
                            data.getNetInterestAmt() != null ? data.getNetInterestAmt() : "",
                    };




                    if (data.getTotalInterest() != null&&!data.getTotalInterest().isEmpty()) {
                        BigDecimal totalInterestt = new BigDecimal(data.getTotalInterest());
                        totalInterest = totalInterest.add(totalInterestt.setScale(2, RoundingMode.HALF_UP));
                    }

                    if (data.getTdsAmt() != null&&!data.getTdsAmt().isEmpty()) {
                        BigDecimal totalTdsAmountt = new BigDecimal(data.getTdsAmt());
                        totalTdsAmount = totalTdsAmount.add(totalTdsAmountt.setScale(2, RoundingMode.HALF_UP));
                    }


                    if (data.getTdsDeductionAmt() != null&&!data.getTdsDeductionAmt().isEmpty()) {
                        BigDecimal tdsDeductionAmountt = new BigDecimal(data.getTdsDeductionAmt());
                        tdsDeductionAmount = tdsDeductionAmount.add(tdsDeductionAmountt.setScale(2, RoundingMode.HALF_UP));
                    }


                    if (data.getNetInterestAmt() != null&&!data.getNetInterestAmt().isEmpty()) {
                        BigDecimal netIntestAmountt = new BigDecimal(data.getNetInterestAmt());
                        netIntestAmount = netIntestAmount.add(netIntestAmountt.setScale(2, RoundingMode.HALF_UP));
                    }


                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);
            //log.info("Processed Data rows: "+ rowNumber);






            SXSSFRow totalRow = sheet.createRow(rowNumber++);
            totalRow.createCell(11).setCellValue(totalInterest.setScale(2, RoundingMode.HALF_UP).toString());
            totalRow.createCell(12).setCellValue(totalTdsAmount.setScale(2, RoundingMode.HALF_UP).toString());

            totalRow.createCell(14).setCellValue(tdsDeductionAmount.setScale(2, RoundingMode.HALF_UP).toString());
            totalRow.createCell(15).setCellValue(netIntestAmount.setScale(2, RoundingMode.HALF_UP).toString());
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getTdsEligibility(TdsEligibilityDTO tdsEligibilityDTO) {
        return rbiBondReportsDAO.getTdsEligibility(tdsEligibilityDTO);
    }

    @Override
    public ResponseDataDTO getTdsEligibilityDownload(TdsEligibilityDTO tdsEligibilityDTO) {


        if (tdsEligibilityDTO.getDownloadType().equalsIgnoreCase("csv")) {
            return rbiBondReportsDAO.getTdsEligibility(tdsEligibilityDTO);
        } else {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getTdsEligibility(tdsEligibilityDTO), tdsEligibilityDTO)));
                fileResponseDto.setFileName("TDS_ELIGIBILITY_REPORT.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in TdsDeductionDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        }
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, TdsEligibilityDTO tdsEligibilityDTO) {
        List<TdsEligibilityDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        BigDecimal subAmtTotal = BigDecimal.ZERO;
        BigDecimal grosstotalInterestAmtTotal = BigDecimal.ZERO;
        BigDecimal tdsAmountTotal = BigDecimal.ZERO;
        BigDecimal interestAmtTotal = BigDecimal.ZERO;
        BigDecimal tdsDeductionPercentageTotal = BigDecimal.ZERO;


        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("TDS ELIGIBILITY REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
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
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 9));

// === Secondary header row (Row 3) ===
            SXSSFRow headerRow = sheet.createRow(2); // Use row 3 instead of 2

            String[] headers = {
                    "S.No.",
                    "Customer Name",
                    "PAN No",
                    "Bond Ledger Account Number",
                    "Bond Subscription ID",
                    "Subscription on Date",
                    "Subscription on Amount",
                    "Interest Rate",
                    "TDS Deduction From Period",
                    "TDS Deduction To period",
                    "Interest Paid Date",
                    "Interest Payment Mode",
                    "Credit Account Number",
                    "Type of Certificate",
                    "TDS Exemption Limit",
                    "Low Tax Percentage",
                    "PAN Status",
                    "Gross Total Interest Amount Rs",
                    "TDS Amount Rs",
                    "TDS Deducted Percentage",
                    "New Interest Amount Rs",
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<TdsEligibilityDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (TdsEligibilityDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getSubsId() != null ? data.getSubsId() : "",
                            data.getSubsDate() != null ? data.getSubsDate() : "",
                            data.getSubsAmt() != null ? data.getSubsAmt() : "",
                            data.getInterestRate() != null ? data.getInterestRate() : "",
                            data.getTdsDeductionFrom() != null ? data.getTdsDeductionFrom() : "",
                            data.getTdsDeductionTo() != null ? data.getTdsDeductionTo() : "",
                            data.getInterestPaidDate() != null ? data.getInterestPaidDate() : "",
                            data.getInterestPaidMode() != null ? data.getInterestPaidMode() : "",
                            data.getCreditAccNo() != null ? data.getCreditAccNo() : "",
                            data.getTypeOfCertificate() != null ? data.getTypeOfCertificate() : "",
                            data.getTdsExemptionLimit() != null ? data.getTdsExemptionLimit() : "",
                            data.getLowTAxPercentage() != null ? data.getLowTAxPercentage() : "",
                            data.getPanStatus() != null ? data.getPanStatus() : "",
                            data.getGrosstotalInterestAmt() != null ? data.getGrosstotalInterestAmt() : "",
                            data.getTdsAmt() != null ? data.getTdsAmt() : "",
                            data.getTdsDeductionPercentage() != null ? data.getTdsDeductionPercentage() : "",
                            data.getNewInterestAmt() != null ? data.getNewInterestAmt() : "",
                    };


                    if (data.getSubsAmt() != null&&!data.getSubsAmt().isEmpty()) {
                        BigDecimal subAmt = new BigDecimal(data.getSubsAmt());
                        subAmtTotal = subAmtTotal.add(subAmt.setScale(2, RoundingMode.HALF_UP));
                    }




                    if (data.getGrosstotalInterestAmt() != null&&!data.getGrosstotalInterestAmt().isEmpty()) {
                        BigDecimal grosstotalInterestAmt = new BigDecimal(data.getGrosstotalInterestAmt());
                        grosstotalInterestAmtTotal = grosstotalInterestAmtTotal.add(grosstotalInterestAmt.setScale(2, RoundingMode.HALF_UP));
                    }




                    if (data.getTdsAmt() != null&&!data.getTdsAmt().isEmpty()) {
                        BigDecimal tdsAmount = new BigDecimal(data.getTdsAmt());
                        tdsAmountTotal = tdsAmountTotal.add(tdsAmount.setScale(2, RoundingMode.HALF_UP));
                    }



                    if (data.getNewInterestAmt() != null&&!data.getNewInterestAmt().isEmpty()) {
                        BigDecimal interestAmt = new BigDecimal(data.getNewInterestAmt());
                        interestAmtTotal = interestAmtTotal.add(interestAmt.setScale(2, RoundingMode.HALF_UP));
                    }


                    if (data.getTdsDeductionPercentage() != null&&!data.getTdsDeductionPercentage().isEmpty()) {
                        BigDecimal tdsDeductionPercentage = new BigDecimal(data.getTdsDeductionPercentage());
                        tdsDeductionPercentageTotal = tdsDeductionPercentageTotal.add(tdsDeductionPercentage.setScale(2, RoundingMode.HALF_UP));
                    }



                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);






            SXSSFRow totalRow = sheet.createRow(rowNumber++);
           // totalRow.createCell(6).setCellValue("Total");
            totalRow.createCell(6).setCellValue(subAmtTotal.setScale(2, RoundingMode.HALF_UP).toString());

            totalRow.createCell(17).setCellValue(grosstotalInterestAmtTotal.setScale(2, RoundingMode.HALF_UP).toString());
            totalRow.createCell(18).setCellValue(tdsAmountTotal.setScale(2, RoundingMode.HALF_UP).toString());
            totalRow.createCell(19).setCellValue(tdsDeductionPercentageTotal.setScale(2, RoundingMode.HALF_UP).toString());
            totalRow.createCell(20).setCellValue(interestAmtTotal.setScale(2, RoundingMode.HALF_UP).toString());



            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getPreIntimationAdviceNoticeDetails(PreIntimationAdviceNoticeDTO preIntimationAdviceNoticeDTO) {
        return rbiBondReportsDAO.getPreIntimationAdviceNoticeDetails(preIntimationAdviceNoticeDTO);
    }

    @Override
    public ResponseDataDTO getPostIntimationAdviceNoticeDetails(PostIntimationAdviceNoticeDTO postIntimationAdviceNoticeDTO) {
        return rbiBondReportsDAO.getPostIntimationAdviceNoticeDetails(postIntimationAdviceNoticeDTO);
    }


    @Override
    public ResponseDataDTO getFreezeUnfreezeDetails(FreezeUnFreezeDTO freezeUnFreezeDTO) {




        if (freezeUnFreezeDTO.getDetailsFlag()!= null&&freezeUnFreezeDTO.getDetailsFlag().equalsIgnoreCase("D")) {
            return rbiBondReportsDAO.getFreezeUnfreezeDetails(freezeUnFreezeDTO);

        }

        else if (freezeUnFreezeDTO.getDownloadType().equalsIgnoreCase("csv")) {
            return rbiBondReportsDAO.getFreezeUnfreezeDetails(freezeUnFreezeDTO);
        } else {

            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(freezUnfreezWiseReportExcel(rbiBondReportsDAO.getFreezeUnfreezeDetails(freezeUnFreezeDTO), freezeUnFreezeDTO)));
                fileResponseDto.setFileName("FreezeUnFreezeDTO.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in FreezeUnFreezeDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        }
    }

    public byte[] freezUnfreezWiseReportExcel(ResponseDataDTO responseDataDTO, FreezeUnFreezeDTO freezeUnFreezeDTO1) {
        List<FreezeUnFreezeDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");

        ByteArrayOutputStream byteArrayOutputStream = null;




        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Freeze / Un-Freeze Report");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK  Branch Name : " + solDesc + "           Branch Id    : " + loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));




            SXSSFRow nameOfTheSchmenamerow = sheet.createRow(2);
            SXSSFCell nameOfTheSchmenameCell = nameOfTheSchmenamerow.createCell(0);
            nameOfTheSchmenameCell.setCellValue("Name of the Bond Scheme   :       " +freezeUnFreezeDTO1.getSchemeName());

            // Create and set bank info cell style
            CellStyle nameOfTheSchmenameStyle = workbook.createCellStyle();
            nameOfTheSchmenameStyle.setAlignment(HorizontalAlignment.CENTER);
            nameOfTheSchmenameCell.setCellStyle(nameOfTheSchmenameStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));




            SXSSFRow fromToDaterow = sheet.createRow(3);
            SXSSFCell fromToDateCell = fromToDaterow.createCell(0);
            fromToDateCell.setCellValue("From Date    " + freezeUnFreezeDTO1.getFromDate()+     "        To Date     " +freezeUnFreezeDTO1.getToDate());

            // Create and set bank info cell style
            CellStyle fromToDateStyle = workbook.createCellStyle();
            fromToDateStyle.setAlignment(HorizontalAlignment.CENTER);
            fromToDateCell.setCellStyle(fromToDateStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 7));

// === Secondary header row (Row 3) ===
            SXSSFRow headerRow = sheet.createRow(4); // Use row 3 instead of 2

            String[] headers = {
                    "S. No.",
                    "Holder Name",
                    "PAN No",
                    "BLA No",
                    "Freeze",
                    "Freeze Date",
                    "Un Freeze",
                    "Un Freeze Date",
                    "Mode of Holding",
                    "Does the principal amount need to be frozen?",
                    "Does the Interest Payment need to be frozen?",
                    "Is transferring out from another bank allowed?",
                    "Reason from Maker",
                    "Reason from Checker",
                    "Subscription_id",
                    "Investment_amount",
                    "Investment_date",
                    "Maker_id",
                    "Maker_date",
                    "Checker_id",
                    "Checker_date",
                    "Freeze_authority"


            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<FreezeUnFreezeDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 5;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (FreezeUnFreezeDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getHolderName() != null ? data.getHolderName() : "",
                            data.getPanNumber() != null ? data.getPanNumber() : "",
                            data.getBlaNumber() != null ? data.getBlaNumber() : "",
                            data.getFreeze() != null ? data.getFreeze() : "",
                            data.getFreezeDate() != null ? data.getFreezeDate() : "",
                            data.getUnFreeze() != null ? data.getUnFreeze() : "",
                            data.getUnFreezeDate() != null ? data.getUnFreezeDate() : "",
                         //   data.getModeOfHolding() != null ? data.getModeOfHolding() : "",

                            data.getModeOfHolding() != null
                                    ? (data.getModeOfHolding().equals("MH001") ? "SINGLE"
                                    : data.getModeOfHolding().equals("MH002") ? "JOINT - JOINTLY"
                                    : data.getModeOfHolding().equals("MH003") ? "JOINT - ANYONE OR SURVIVOR"
                                    : data.getModeOfHolding().equals("MH004") ? "HUF"
                                    : data.getModeOfHolding().equals("MH005") ? "LEGAL GUARDIAN"
                                    : data.getModeOfHolding())
                                    : "",

                            data.getPrincipalAmountFrozen() != null ? data.getPrincipalAmountFrozen() : "",
                            data.getInterestPaymentFrozen() != null ? data.getInterestPaymentFrozen() : "",
                            data.getIsTransferringOutAllowed() != null ? data.getIsTransferringOutAllowed() : "",
                            data.getReasonFromMaker() != null ? data.getReasonFromMaker().toUpperCase()  : "",
                            data.getReasonFromChecker() != null ? data.getReasonFromChecker().toUpperCase() : "",

                            data.getSubscriptionId() != null ? data.getSubscriptionId() : "",
                            data.getInvestmentAmount() != null ? data.getInvestmentAmount() : "",
                            data.getInvestmentDate() != null ? data.getInvestmentDate() : "",
                            data.getMakerId() != null ? data.getMakerId() : "",
                            data.getMakerDate() != null ? data.getMakerDate() : "",
                            data.getCheckerId() != null ? data.getCheckerId() : "",
                            data.getCheckerDate() != null ? data.getCheckerDate() : "",
                            data.getFreezeAuth() != null ? data.getFreezeAuth() : "",











                    };

                    log.info("cellValues.length: " + cellValues.length);
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        //log.info(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

























    @Override
    public ResponseDataDTO getEtdsDetails(EtdsDTO etdsDTO) {
        return rbiBondReportsDAO.getEtdsDetails(etdsDTO);
    }

    @Override
    public ResponseDataDTO getEtdsDetailsDownload(EtdsDTO etdsDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getEtdsDetails(etdsDTO), etdsDTO)));
            fileResponseDto.setFileName("E-TDS_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in EtdsDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, EtdsDTO etdsDTO) {
        List<EtdsDTO> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("E-TDS REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "Challan no",
                    "Deductee Sr. no.",
                    "PAN",
                    "Name of the Deductee",
                    "Section Code",
                    "Date of Payment or Credit (DD/MM/YYYY)",
                    "Amount Paid/Credited",
                    "Amount of cash withdrawal which is in excess of Rs. 1 crore referred to in section 393(3) (in case of person other than co-operative society)",
                    "Amount of cash withdrawal which is in excess of Rs. 3 crore for cases covered by section 393(3) [Table: Sl No. 5] (in case of Co-operative Societies)",
                    "Total Tax Deducted",
                    "Total Tax Deposited",
                    "Rate at Which Tax Deducted",
                    "Date of Deduction",
                    "Reason for non-deduction/lower deduction/higher deduction/threshold/Transporter etc.",
                    "Certificate number of certificate issued under section 395 for non-deduction/lower deduction",
                    "Unique Identification Number of Form No.121 Submitted by deductee",
                    "Whether the total amount of tax deducted as mentioned in Column (j) has been deposited (Yes/No)",
                   "Cust ID/ Reference No",
                  /*  "PAN",
                    "Section",*/
                    "Customer Name",
                   "Address Line 1",
                  "Address Line 2",
                    "Address Line 3",
                   "City",
                    "State",
                   "Country",
                   "Pincode",
                   "Email ID",
                      };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<EtdsDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (EtdsDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            data.getChallanNo() != null ? data.getChallanNo() : "",
//                            data.getDeducteeSrNo() != null ? data.getDeducteeSrNo() : "",
                            String.valueOf(sNo++),
                            data.getPan() != null ? data.getPan() : "",
                            data.getNameOfTheDeductee() != null ? data.getNameOfTheDeductee() : "",
                            data.getSectionCode() != null ? data.getSectionCode() : "",
                            data.getDateOfPaymentOrCredit() != null ? data.getDateOfPaymentOrCredit() : "",
                            data.getAmountPaidCredited() != null
                                    ? new DecimalFormat("0.00").format(Double.parseDouble(data.getAmountPaidCredited()))
                                    : "",
                            data.getAmountOfCashWithdrawalExceedingRs1Crore() != null ? data.getAmountOfCashWithdrawalExceedingRs1Crore() : "",
                            data.getAmountOfCashWithdrawalExceedingRs3Crore() != null ? data.getAmountOfCashWithdrawalExceedingRs3Crore() : "",
                            data.getTotalTaxDeducted() != null ? data.getTotalTaxDeducted() : "",
                            data.getTotalTaxDeposited() != null ? data.getTotalTaxDeposited() : "",
                            data.getRateAtWhichTaxDeducted() != null ? data.getRateAtWhichTaxDeducted()  + "%" : "",
                            data.getDateOfDeduction() != null ? data.getDateOfDeduction() : "",
                            data.getReasonForNonDeductionLowerDeductionHigherDeductionThresholdTransporter() != null
                                    ? data.getReasonForNonDeductionLowerDeductionHigherDeductionThresholdTransporter()
                                    : "",
                            data.getCertificateNo() != null
                                    ? data.getCertificateNo()
                                    : "",
                            data.getUniqueIdentificationNumberOfForm121() != null
                                    ? data.getUniqueIdentificationNumberOfForm121()
                                    : "",
                            data.getTaxDeductedDeposited() != null ? data.getTaxDeductedDeposited() : "",

                            data.getReferenceNo()!= null ? data.getReferenceNo():"",
                            /*data.getPan()!= null ? data.getPan():"",
                            data.getSectionCode()!= null ? data.getSectionCode():"",*/
                            data.getNameOfTheDeductee() != null ? data.getNameOfTheDeductee() : "",
                            data.getAddressLine1()!= null ? data.getAddressLine1():"",
                            data.getAddressLine2()!= null ? data.getAddressLine2():"",
                            data.getAddressLine3()!= null ? data.getAddressLine3():"",
                            data.getCity()!= null ? data.getCity():"",
                            data.getState()!= null ? data.getState():"",
                            data.getCountry()!= null ? data.getCountry():"",
                            data.getPinCode()!= null ? data.getPinCode():"",
                            data.getEmailId()!= null ? data.getEmailId():"",
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getDetailsOfPdf(NetBankingTransectionPdfDTO netBankingTransectionPdfDTO) {
        return rbiBondReportsDAO.getDetailsOfPdf(netBankingTransectionPdfDTO);
    }

    @Override
    public ResponseDataDTO getFutureTdsDetails(FutureTdsDto futureTdsDto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ResponseDataDTO responseDataDTO1 = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            responseDataDTO1=rbiBondReportsDAO.getFutureTdsDetails(futureTdsDto);
            if(null!=futureTdsDto&&futureTdsDto.getSchemeCode()==null){
                responseDataDTO.setResult(responseDataDTO1.getResult());
                responseDataDTO.setMessage(resultMsg);
                return responseDataDTO;
            }
            if(null!=responseDataDTO1&&null!=responseDataDTO1.getMessage()&&responseDataDTO1.getMessage().equals("SUCCESS")) {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(FutureTdsReportExcelDownload(responseDataDTO1)));
                if (null!=futureTdsDto.getMenu()&&futureTdsDto.getMenu().equalsIgnoreCase("I")) {
                    fileResponseDto.setFileName("Future_TdsCalculation_Interest_Calculation_Report.xlsx");
                } else if (null!=futureTdsDto.getMenu()&&futureTdsDto.getMenu().equalsIgnoreCase("OM001")) {
                    fileResponseDto.setFileName("Future_TdsCalculation_Redemption_OnMaturity_Report.xlsx");
                } else if (null!=futureTdsDto.getMenu()&&futureTdsDto.getMenu().equalsIgnoreCase("BM001")) {
                    fileResponseDto.setFileName("Future_TdsCalculation_Redemption_PreMaturity_Report.xlsx");
                } else if (null!=futureTdsDto.getMenu()&&futureTdsDto.getMenu().equalsIgnoreCase("OM002")) {
                    fileResponseDto.setFileName("Future_TdsCalculation_Redemption_OnMaturity_Death_Report.xlsx");
                }
                responseDataDTO.setResult(fileResponseDto);
                responseDataDTO.setMessage(resultMsg);
            }else{
                responseDataDTO.setMessage(responseDataDTO1.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in getFutureTdsDetails, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        return responseDataDTO;
    }

    public byte[] FutureTdsReportExcelDownload(ResponseDataDTO responseDataDTO) {
        List<FutureTdsDto> list = Collections.emptyList();
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Future TdsCalculation Report");
            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);
            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No",
                    "Scheme ID",
                    "Branch Ref No",
                    "BLA No",
                    "Applicant Name with HUF",
                    "Applicant Name 1",
                    "Applicant Name 2",
                    "Applicant Name 3",
                    "CAS Transfer Date",
                    "Applicant Status",
                    "Receipt Date",
                    "Investment Amount",
                    "Scheme Name",
                    "CFD",
                    "Maturity Date",
                    "Interest Rate",
                    "Last Interest Paid Amount",
                    "PAN Number",
                    "Future Interest Status",
                    "New 1",
                    "New 2",
                    "New 3",
                    "New 4",
                    "New 5"
            };
            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<FutureTdsDto>) responseDataDTO.getResult();
            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (FutureTdsDto data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getSchemeId() != null ? data.getSchemeId() : "",
                            data.getBranchRefNo() != null ? data.getBranchRefNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getApplNameWithHuf() != null ? data.getApplNameWithHuf() : "",
                            data.getApplName1() != null ? data.getApplName1() : "",
                            data.getApplName2() != null ? data.getApplName2() : "",
                            data.getApplName3() != null ? data.getApplName3() : "",
                            data.getCasTransferDate() != null ? data.getCasTransferDate() : "",
                            data.getApplStatus() != null ? getApplicantStatus(data.getApplStatus()) : "",
                            data.getRcptDate() != null ? data.getRcptDate() : "",
                            data.getInvestAmt() != null ? data.getInvestAmt() : "",
                            data.getScheme() != null ? data.getScheme() : "",
                            data.getCfd() != null ? data.getCfd() : "",
                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getIntRate() != null ? data.getIntRate() : "",
                            data.getLastIntPaidAmt() != null ? data.getLastIntPaidAmt() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getFutureInterestStatus() != null ? data.getFutureInterestStatus() : "",
                            data.getNew1() != null ? data.getNew1() : "",
                            data.getNew2() != null ? data.getNew2() : "",
                            data.getNew3() != null ? data.getNew3() : "",
                            data.getNew4() != null ? data.getNew4() : "",
                            data.getNew5() != null ? data.getNew5() : ""
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getSubsAmtBLAMaturedAccClosed(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        return rbiBondReportsDAO.getSubsAmtBLAMaturedAccClosed(subsAmtBLAMaturedAccClosedDTO);
    }

    @Override
    public ResponseDataDTO getSubsAmtBLAMaturedAccClosedDownload(SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();

        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportsDAO.getSubsAmtBLAMaturedAccClosed(subsAmtBLAMaturedAccClosedDTO), subsAmtBLAMaturedAccClosedDTO)));

            if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("B")) {
                fileResponseDto.setFileName("BOND_INQUIRY_BLA_ACCOUNT_DETAILS_REPORT.xlsx");
            } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("S")) {
                fileResponseDto.setFileName("BOND_INQUIRY_SUBSCRIPTION_AMOUNT_DETAILS_REPORT.xlsx");
            } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("M")) {
                fileResponseDto.setFileName("BOND_INQUIRY_MATURED_ACCOUNT_DETAILS_REPORT.xlsx");
            } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("A")) {
                fileResponseDto.setFileName("BOND_INQUIRY_ACCOUNT_CLOSED_DETAILS_REPORT.xlsx");
            }

            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in SubsAmtBLAMaturedAccClosedDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, SubsAmtBLAMaturedAccClosedDTO subsAmtBLAMaturedAccClosedDTO) {
        List<SubsAmtBLAMaturedAccClosedDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;

        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();

            // Title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("B")) {
                titleCell.setCellValue("BLA ACCOUNT NUMBER DETAILS REPORT");
            } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("S")) {
                titleCell.setCellValue("SUBSCRIPTION AMOUNT DETAILS REPORT");
            } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("M")) {
                titleCell.setCellValue("MATURED ACCOUNT DETAILS REPORT");
            } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("A")) {
                titleCell.setCellValue("ACCOUNT CLOSED DETAILS REPORT REPORT");
            }

            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Bank Info Row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : " + solDesc + " Branch Id : " + loginSolid);
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Header Row
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = null;
            if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("B")) {
                headers = new String[]{"S.No.", "Bla_No", "Holder Name", "Address", "Is NRI", "Is Minor", "Mode of Holding", "Holder Type"};
            } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("S")) {
                headers = new String[]{"S.No.", "Bla_No", "PAN No.", "Holder Name", "Subscription ID", "Date of Subscription", "Maturity Date", "Mode of Holding", "Subscription Amount Rs."};
            } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("M")) {
                headers = new String[]{"S.No.", "Bla_No", "Holder Name", "Subscription ID", "Date of Subscription", "Maturity Date", "Mode of Holding", "Subscription Amount Rs."};
            } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("A")) {
                headers = new String[]{"S.No.", "Bla_No", "Holder Name", "Subscription ID", "Date of Subscription", "Maturity Date", "Mode of Holding", "Subscription Amount Rs.", "Account Closed Date"};
            }

            CellStyle headerCellStyle = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);

            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }

            finalData = (HashMap<String, Object>) responseDataDTO.getResult();
            list = (List<SubsAmtBLAMaturedAccClosedDTO>) finalData.get("list");

            // Data Rows
            int rowNumber = 3;
            int sNo = 1;
            double totalAmount = 0.0;

            if (!list.isEmpty()) {
                for (SubsAmtBLAMaturedAccClosedDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = null;

                    if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("B")) {
                        cellValues = new String[]{
                                String.valueOf(sNo++),
                                data.getBlaNumber() != null ? data.getBlaNumber() : "",
                                data.getHolderName() != null ? data.getHolderName() : "",
                                data.getAddress() != null ? data.getAddress() : "",
                                data.getIsNRI() != null ? data.getIsNRI() : "",
                                data.getIsMinor() != null ? data.getIsMinor() : "",
                                data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                                data.getHolderType() != null ? data.getHolderType() : ""
                        };
                    } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("S")) {
                        cellValues = new String[]{
                                String.valueOf(sNo++),
                                data.getBlaNumber() != null ? data.getBlaNumber() : "",
                                data.getPanNumber() != null ? data.getPanNumber() : "",
                                data.getHolderName() != null ? data.getHolderName() : "",
                                data.getSubscriptionID() != null ? data.getSubscriptionID() : "",
                                data.getDateOfSubscription() != null ? data.getDateOfSubscription() : "",
                                data.getMaturityDate() != null ? data.getMaturityDate() : "",
                                data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                                data.getSubscriptionAmount() != null ? data.getSubscriptionAmount() : ""
                        };
                    } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("M")) {
                        cellValues = new String[]{
                                String.valueOf(sNo++),
                                data.getBlaNumber() != null ? data.getBlaNumber() : "",
                                data.getHolderName() != null ? data.getHolderName() : "",
                                data.getSubscriptionID() != null ? data.getSubscriptionID() : "",
                                data.getDateOfSubscription() != null ? data.getDateOfSubscription() : "",
                                data.getMaturityDate() != null ? data.getMaturityDate() : "",
                                data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                                data.getSubscriptionAmount() != null ? data.getSubscriptionAmount() : ""
                        };
                    } else if (subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("A")) {
                        cellValues = new String[]{
                                String.valueOf(sNo++),
                                data.getBlaNumber() != null ? data.getBlaNumber() : "",
                                data.getHolderName() != null ? data.getHolderName() : "",
                                data.getSubscriptionID() != null ? data.getSubscriptionID() : "",
                                data.getDateOfSubscription() != null ? data.getDateOfSubscription() : "",
                                data.getMaturityDate() != null ? data.getMaturityDate() : "",
                                data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                                data.getSubscriptionAmount() != null ? data.getSubscriptionAmount() : "",
                                data.getAccountClosedDate() != null ? data.getAccountClosedDate() : ""
                        };
                    }

                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(i).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }

                    // Sum subscription amount if applicable
                    if ((subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("S") ||
                            subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("M") ||
                            subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("A")) &&
                            data.getSubscriptionAmount() != null &&
                            !data.getSubscriptionAmount().isEmpty()) {
                        try {
                            totalAmount += Double.parseDouble(data.getSubscriptionAmount().replaceAll(",", ""));
                        } catch (NumberFormatException e) {
                            log.warn("Unable to parse subscription amount: " + data.getSubscriptionAmount());
                        }
                    }
                }

                // Add Total Row if applicable
                if (subsAmtBLAMaturedAccClosedDTO.getFlag().matches("(?i)[SMA]")) {
                    SXSSFRow totalRow = sheet.createRow(rowNumber++);
                    int labelColIndex = 7;
                    int valueColIndex = 8;
                    if(subsAmtBLAMaturedAccClosedDTO.getFlag().equalsIgnoreCase("A")){
                        labelColIndex = 6;
                        valueColIndex = 7;
                    }
                    SXSSFCell labelCell = totalRow.createCell(labelColIndex);
                    labelCell.setCellValue("Total");

                    SXSSFCell valueCell = totalRow.createCell(valueColIndex);
                    valueCell.setCellValue(totalAmount);

                    CellStyle boldStyle = workbook.createCellStyle();
                    org.apache.poi.ss.usermodel.Font boldFont = workbook.createFont();
                    boldFont.setBold(true);
                    boldStyle.setFont(boldFont);
                    labelCell.setCellStyle(boldStyle);
                    valueCell.setCellStyle(boldStyle);
                }
            } else {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: " + rowNumber);

            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getNomineeReport(NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        return rbiBondReportsDAO.getNomineeReport(nomineeContinuationReportDTO);
    }

    @Override
        public ResponseDataDTO getNomineeReportDownload(NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ResponseDataDTO responseDataDTO1 = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            responseDataDTO1=rbiBondReportsDAO.getNomineeReport(nomineeContinuationReportDTO);
            if(null!=responseDataDTO1 && "SUCCESS".equalsIgnoreCase(responseDataDTO1.getMessage())) {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(responseDataDTO1, nomineeContinuationReportDTO)));
                fileResponseDto.setFileName("NOMINEE_CONTINUATION_REPORT.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            }else{
                return responseDataDTO1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in NomineeContinuationReportDTO, ReportsBOImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO, NomineeContinuationReportDTO nomineeContinuationReportDTO) {
        List<NomineeContinuationReportDTO> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("NOMINEE_CONTINUATION_REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "Scheme Name",
                    "Death Marked Holder PAN",
                    "Death Holder Name",
                    "Death Marked Holder BLA No",
                    "Death Marked Holder Subscription ID",
                    "Date of Death of Bond Holder",
                    "Date of Receipt of Death Certificate",
                    "Death Marking Date",
                    "Main Subscription Amount",

                    "Nominee 1 ID",
                    "Nominee 1 Name",
                    "Nominee 1 PAN No",
                    "Nominee 1 DOB",
                    "Nominee 1 Share Percentage",
                    "Nominee 1 BLA No",
                    "Nominee 1 Subscription ID",
                    "Nominee 1 Subscription Amount",
                    "Nominee 1 Mode Of Interest Payment",

                    "Nominee 2 ID",
                    "Nominee 2 Name",
                    "Nominee 2 PAN No",
                    "Nominee 2 DOB",
                    "Nominee 2 Share Percentage",
                    "Nominee 2 BLA No",
                    "Nominee 2 Subscription ID",
                    "Nominee 2 Subscription Amount",
                    "Nominee 2 Mode Of Interest Payment",

                    "Nominee 3 ID",
                    "Nominee 3 Name",
                    "Nominee 3 PAN No",
                    "Nominee 3 DOB",
                    "Nominee 3 Share Percentage",
                    "Nominee 3 BLA No",
                    "Nominee 3 Subscription ID",
                    "Nominee 3 Subscription Amount",
                    "Nominee 3 Mode Of Interest Payment",

                    "Nominee 4 ID",
                    "Nominee 4 Name",
                    "Nominee 4 PAN No",
                    "Nominee 4 DOB",
                    "Nominee 4 Share Percentage",
                    "Nominee 4 BLA No",
                    "Nominee 4 Subscription ID",
                    "Nominee 4 Subscription Amount",
                    "Nominee 4 Mode Of Interest Payment",

                    "Bond Type",
                    "Account Open Date",
                    "Subscription Transferred To Nominee",
                    "Period Of Deposits",
                    "Maturity Date"
                    //  "Nominee Sharable Percentage"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<NomineeContinuationReportDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (NomineeContinuationReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getSchemeName() != null ? data.getSchemeName() : "",
                            data.getDeathMarkedHolderPan() != null ? data.getDeathMarkedHolderPan() : "",
                            data.getDeathHolderName() != null ? data.getDeathHolderName() : "",
                            data.getDeathMarkedHolderBlaNo() != null ? data.getDeathMarkedHolderBlaNo() : "",
                            data.getDeathMarkedHolderSubscriptionId() != null ? data.getDeathMarkedHolderSubscriptionId() : "",
                            data.getDateOfDeathOfBondHolder() != null ? data.getDateOfDeathOfBondHolder() : "",
                            data.getDateOfReceiptOfDeathCertificate() != null ? data.getDateOfReceiptOfDeathCertificate() : "",
                            data.getDeathMarkingDate() != null ? data.getDeathMarkingDate() : "",
                            data.getMainSubscriptionAmount() != null ? data.getMainSubscriptionAmount() : "",

                            data.getNominee1Id() != null ? data.getNominee1Id() : "",
                            data.getNominee1Name() != null ? data.getNominee1Name() : "",
                            data.getNominee1PanNo() != null ? data.getNominee1PanNo() : "",
                            data.getNominee1Dob() != null ? data.getNominee1Dob() : "",
                            data.getNominee1SharePercentage() != null ? data.getNominee1SharePercentage() : "",
                            data.getNominee1BlaNo() != null ? data.getNominee1BlaNo() : "",
                            data.getNominee1SubscriptionId() != null ? data.getNominee1SubscriptionId() : "",
                            data.getNominee1SubscriptionAmount() != null ? data.getNominee1SubscriptionAmount() : "",
                            data.getNominee1ModeOfInterestPayment() != null ? data.getNominee1ModeOfInterestPayment() : "",

                            data.getNominee2Id() != null ? data.getNominee2Id() : "",
                            data.getNominee2Name() != null ? data.getNominee2Name() : "",
                            data.getNominee2PanNo() != null ? data.getNominee2PanNo() : "",
                            data.getNominee2Dob() != null ? data.getNominee2Dob() : "",
                            data.getNominee2SharePercentage() != null ? data.getNominee2SharePercentage() : "",
                            data.getNominee2BlaNo() != null ? data.getNominee2BlaNo() : "",
                            data.getNominee2SubscriptionId() != null ? data.getNominee2SubscriptionId() : "",
                            data.getNominee2SubscriptionAmount() != null ? data.getNominee2SubscriptionAmount() : "",
                            data.getNominee2ModeOfInterestPayment() != null ? data.getNominee2ModeOfInterestPayment() : "",

                            data.getNominee3Id() != null ? data.getNominee3Id() : "",
                            data.getNominee3Name() != null ? data.getNominee3Name() : "",
                            data.getNominee3PanNo() != null ? data.getNominee3PanNo() : "",
                            data.getNominee3Dob() != null ? data.getNominee3Dob() : "",
                            data.getNominee3SharePercentage() != null ? data.getNominee3SharePercentage() : "",
                            data.getNominee3BlaNo() != null ? data.getNominee3BlaNo() : "",
                            data.getNominee3SubscriptionId() != null ? data.getNominee3SubscriptionId() : "",
                            data.getNominee3SubscriptionAmount() != null ? data.getNominee3SubscriptionAmount() : "",
                            data.getNominee3ModeOfInterestPayment() != null ? data.getNominee3ModeOfInterestPayment() : "",

                            data.getNominee4Id() != null ? data.getNominee4Id() : "",
                            data.getNominee4Name() != null ? data.getNominee4Name() : "",
                            data.getNominee4PanNo() != null ? data.getNominee4PanNo() : "",
                            data.getNominee4Dob() != null ? data.getNominee4Dob() : "",
                            data.getNominee4SharePercentage() != null ? data.getNominee4SharePercentage() : "",
                            data.getNominee4BlaNo() != null ? data.getNominee4BlaNo() : "",
                            data.getNominee4SubscriptionId() != null ? data.getNominee4SubscriptionId() : "",
                            data.getNominee4SubscriptionAmount() != null ? data.getNominee4SubscriptionAmount() : "",
                            data.getNominee4ModeOfInterestPayment() != null ? data.getNominee4ModeOfInterestPayment() : "",

                            data.getBondType() != null ? data.getBondType() : "",
                            data.getAccountOpenDate() != null ? data.getAccountOpenDate() : "",
                            data.getSubscriptionTransferredToNominee() != null ? data.getSubscriptionTransferredToNominee() : "",
                            data.getPeriodOfDeposits() != null ? data.getPeriodOfDeposits() : "",
                            data.getMaturityDate() != null ? data.getMaturityDate() : ""
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    private String roundBalance(Object input, int decimalPlaces) {
        try {
            BigDecimal bd;
            if (input instanceof String) {
                bd = new BigDecimal((String) input);
            } else if (input instanceof Number) {
                bd = new BigDecimal(((Number) input).doubleValue());
            } else {
                return "";
            }
            bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
            return bd.toPlainString();
        } catch (NumberFormatException e) {
            return ""; // fallback if string isn't a number
        }
    }


    @Override
    public ResponseDataDTO getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO DashBoardReportForInwardOutwardDTO) {
        return rbiBondReportsDAO.getDashBoardReportForInwardOutward(DashBoardReportForInwardOutwardDTO);
    }
    public ResponseDataDTO getJointTypeDetails(JointTypeThreeToTwoReportDTO jointTypeThreeToTwoReportDTO) {


        if (jointTypeThreeToTwoReportDTO.getDownloadType().equalsIgnoreCase("D")) {
            return rbiBondReportsDAO.getJointTypeDetails(jointTypeThreeToTwoReportDTO);

        }

        if (jointTypeThreeToTwoReportDTO.getDownloadType().equalsIgnoreCase("excel")) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            String resultMsg = "SUCCESS";
            FileResponseDto fileResponseDto = new FileResponseDto();
            try {
                fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateJointTypeChangeReportReportExcel(rbiBondReportsDAO.getJointTypeDetails(jointTypeThreeToTwoReportDTO), jointTypeThreeToTwoReportDTO)));
                fileResponseDto.setFileName("JOINT-TYPE-CHANGE-REPORT.xlsx");
                responseDataDTO.setResult(fileResponseDto);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error in EtdsDTO, ReportsBOImpl: " + e.getMessage());
                resultMsg = CommonConstants.NDF;
                responseDataDTO.setMessage(resultMsg);
            }
            responseDataDTO.setMessage(resultMsg);
            return responseDataDTO;
        }
        else{
            return null;
        }
    }




    public byte[] generateJointTypeChangeReportReportExcel(ResponseDataDTO responseDataDTO, JointTypeThreeToTwoReportDTO jointTypeThreeToTwoReportDTO) {
        List<JointTypeThreeToTwoReportDTO> list = Collections.emptyList();
        HashMap<String,Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
       String solDesc=Optional.ofNullable( UserAccountUtils.getSolDesc()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("JOINT_TYPE_CHANGE_REPORT");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);

            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No.",
                    "First Holder Name",
                    "First Holder PAN No",
                    "Second Holder Name",
                    "Second Holder PAN No",
                    "Third Holder Name",
                    "Third Holder PAN No",
                    "Mode of Holding",
                    "From BLA Account Number",
                    "Active Subscription Count",
                    "Active Subscription ID",
                    "To BLA Account Number",
                    "First Holder Name",
                    "First Holder PAN No",
                    "Second Holder Name",
                    "Second Holder PAN No",
                    "Date of Change in Joint Type",
                    "Remarks"
                    //  "Nominee Sharable Percentage"
            };

            CellStyle headerCellStyle = workbook.createCellStyle();
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
            list = (List<JointTypeThreeToTwoReportDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (JointTypeThreeToTwoReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
//                            data.getSchemeName() != null ? data.getSchemeName() : "",
                            data.getFirstHolderName() != null ? data.getFirstHolderName() : "",
                            data.getFirstHolderPan() != null ? data.getFirstHolderPan() : "",
                            data.getSecondHolderName() != null ? data.getSecondHolderName() : "",
                            data.getSecondHolderPan() != null ? data.getSecondHolderPan() : "",
                            data.getThirdHolderName() != null ? data.getThirdHolderName() : "",
                            data.getThirdHolderPan() != null ? data.getThirdHolderPan() : "",
                            data.getModeOfHolding() != null ? data.getModeOfHolding() : "",
                            data.getFromBlaAccountNumber() != null ? data.getFromBlaAccountNumber() : "",
                            data.getActiveSubscriptionCount() != null ? data.getActiveSubscriptionCount() : "",
                            data.getActiveSubscriptionId() != null ? data.getActiveSubscriptionId() : "",
                            data.getToBlaAccountNumber() != null ? data.getToBlaAccountNumber() : "",
                            data.getToFirstHolderName() != null ? data.getToFirstHolderName() : "",
                            data.getToFirstHolderPan() != null ? data.getToFirstHolderPan() : "",
                            data.getToSecondHolderName() != null ? data.getToSecondHolderName() : "",
                            data.getToSecondHolderPan() != null ? data.getToSecondHolderPan() : "",
                            data.getDateOfChangeInJointType() != null ? data.getDateOfChangeInJointType() : "",
                            data.getRemarks() != null ? data.getRemarks() : ""
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
            else
            {
                log.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            log.info("REPORT DATA GENERATION COMPLETED...");
            log.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getCpTotalDetails(CertificatePrintingDTO certificatePrintingDTO){
        return rbiBondReportsDAO.getCpTotalDetails(certificatePrintingDTO);
    }

    @Override
    public ResponseDataDTO getHufDeathMarkingDetails(HUFDeathMarkingReportDTO hufDeathMarkingReportDTO) {
        return rbiBondReportsDAO.getHufDeathMarkingDetails(hufDeathMarkingReportDTO);
    }

//    private static final String ROW_DELIMITER = "|";
//    @Override
//    public ResponseDataDTO uploadDataPatch(MultipartFile file, DataPatchUploadRequestDTO requestDTO) {
//        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
//
//        if (file == null || file.isEmpty()) {
//            responseDataDTO.setMessage("File data is empty");
//            responseDataDTO.setStatusCode("0");
//            return responseDataDTO;
//        }
//
//        if (requestDTO == null || StringUtils.isBlank(requestDTO.getFromDate())
//                || StringUtils.isBlank(requestDTO.getToDate())) {
//            responseDataDTO.setMessage("From Date and To Date are mandatory");
//            responseDataDTO.setStatusCode("0");
//            return responseDataDTO;
//        }
//
//        try {
//            ResponseDataDTO parseResponse = parseExcelFile(file);
//            if ("0".equals(parseResponse.getStatusCode())) {
//                return parseResponse;
//            }
//
//            @SuppressWarnings("unchecked")
//            List<DataPatchUploadRowDTO> uploadList = (List<DataPatchUploadRowDTO>) parseResponse.getResult();
//            if (uploadList == null || uploadList.isEmpty()) {
//                responseDataDTO.setMessage("No data found in uploaded file");
//                responseDataDTO.setStatusCode("0");
//                return responseDataDTO;
//            }
//
//            requestDTO.setFileName(file.getOriginalFilename());
//            requestDTO.setFile(file.getBytes());
//            requestDTO.setUploadList(uploadList);
////            return rbiBondReportsDAO.uploadDataPatch(file, requestDTO);
//            return null;
//        } catch (Exception e) {
//            log.error("Data patch upload failed", e);
//            responseDataDTO.setMessage("File validation error");
//            responseDataDTO.setStatusCode("0");
//            return responseDataDTO;
//        }
//    }

//    private ResponseDataDTO parseExcelFile(MultipartFile file) {
//        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
//        List<DataPatchUploadRowDTO> uploadRows = new ArrayList<>();
//
//        try (InputStream inputStream = file.getInputStream(); Workbook workbook = WorkbookFactory.create(inputStream)) {
//            DataFormatter formatter = new DataFormatter();
//            int serialNo = 1;
//
//            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
//                Sheet sheet = workbook.getSheetAt(sheetIndex);
//                if (sheet == null) {
//                    continue;
//                }
//
//                int startRow = sheet.getFirstRowNum();
//                for (int rowIndex = startRow + 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//                    Row row = sheet.getRow(rowIndex);
//                    if (row == null || isRowEmpty(row, formatter)) {
//                        continue;
//                    }
//
//                    String rowData = buildRowData(row, formatter);
//                    if (StringUtils.isBlank(rowData)) {
//                        continue;
//                    }
//
//                    DataPatchUploadRowDTO rowDTO = new DataPatchUploadRowDTO();
//                    rowDTO.setSerialNo(String.valueOf(serialNo++));
//                    rowDTO.setRowData(rowData);
//                    uploadRows.add(rowDTO);
//                }
//            }
//
//            responseDataDTO.setResult(uploadRows);
//            return responseDataDTO;
//        } catch (Exception e) {
//            log.error("Unable to parse data patch upload file", e);
//            responseDataDTO.setMessage("Invalid Excel file format");
//            responseDataDTO.setStatusCode("0");
//            return responseDataDTO;
//        }
//    }

//    private String buildRowData(Row row, DataFormatter formatter) {
//        int lastCellNum = row.getLastCellNum();
//        if (lastCellNum < 0) {
//            return null;
//        }
//
//        List<String> values = new ArrayList<>();
//        for (int cellIndex = 0; cellIndex < lastCellNum; cellIndex++) {
//            String cellValue = formatter.formatCellValue(row.getCell(cellIndex));
//            values.add(StringUtils.trimToEmpty(cellValue));
//        }
//
//        return values.stream()
//                .filter(StringUtils::isNotBlank)
//                .collect(Collectors.joining(ROW_DELIMITER));
//    }

//    private boolean isRowEmpty(Row row, DataFormatter formatter) {
//        int lastCellNum = row.getLastCellNum();
//        if (lastCellNum <= 0) {
//            return true;
//        }
//
//        for (int cellIndex = 0; cellIndex < lastCellNum; cellIndex++) {
//            if (StringUtils.isNotBlank(formatter.formatCellValue(row.getCell(cellIndex)))) {
//                return false;
//            }
//        }
//        return true;
//    }
}