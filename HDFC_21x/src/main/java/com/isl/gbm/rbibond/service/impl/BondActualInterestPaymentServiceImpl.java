package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.BondActualInterestPaymentBO;
import com.isl.gbm.rbibond.bo.impl.SubscriptionAccClosingOnMaturityBOImpl;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.service.BondActualInterestPaymentService;
import com.isl.gbm.rbibond.util.Sftp;
import com.isl.gbm.rbibond.util.SftpUploader;
import com.isl.gbm.user.management.util.UserAccountUtils;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class BondActualInterestPaymentServiceImpl implements BondActualInterestPaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BondActualInterestPaymentServiceImpl.class);

    private final BondActualInterestPaymentBO paymentBO;

    private final SftpUploader uploadUtils;

    private Sftp sftp;

    @Value("${bond.payments.file.sftp.path}")
    private String remoteDir;

    private final SubscriptionAccClosingOnMaturityBOImpl achFileUtils;

    public BondActualInterestPaymentServiceImpl(final BondActualInterestPaymentBO paymentBO,
                                                final Sftp sftp,
                                                final SubscriptionAccClosingOnMaturityBOImpl achFileUtils,
                                                final SftpUploader uploadUtils
                                                ) {
        this.paymentBO = paymentBO;
        this.sftp = sftp;
        this.achFileUtils=achFileUtils;
        this.uploadUtils=uploadUtils;
    }

    private static ResponseDataDTO parseUploadedTextFile(final MultipartFile file) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        BondInterestPaymentFileDTO paymentFileDTO = new BondInterestPaymentFileDTO();
        List<BondInterestPaymentFileDetailsDTO> detailsDTOList = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<String> listOfRows = bufferedReader.lines().collect(Collectors.toList());
            List<String> rows = listOfRows.stream().filter(row -> row.length() == 306)
                    .collect(Collectors.toList());
            if ((!listOfRows.isEmpty() && !rows.isEmpty()) && (listOfRows.size() == rows.size())) {
                paymentFileDTO.setFileHeaderDTO(headerRowParser(listOfRows.get(0)));
                listOfRows.stream().skip(1).forEach(
                        item -> detailsDTOList.add(detailsRowParser(item))
                );
                paymentFileDTO.setFileDetailsDTO(detailsDTOList);
                responseDataDTO.setResult(paymentFileDTO);
                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                return responseDataDTO;
            } else {
                responseDataDTO.setMessage("Upload Valid File with Length of the Rows as 306 Character");
                return responseDataDTO;
            }
        } catch (IOException e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
        }
        return responseDataDTO;
    }

    private static BondInterestPaymentFileHeaderDTO headerRowParser(String line) {
        BondInterestPaymentFileHeaderDTO headerDTO = new BondInterestPaymentFileHeaderDTO();
        headerDTO.setAchTranCode(line.substring(0, 2));
        headerDTO.setControl1(line.substring(2, 9));
        headerDTO.setUserName(line.substring(9, 49));
        headerDTO.setControl2(line.substring(49, 63));
        headerDTO.setAchFileNo(line.substring(63, 72));
        headerDTO.setControl3(line.substring(72, 81));
        headerDTO.setControl4(line.substring(81, 96));
        headerDTO.setLedgerFolioNo(line.substring(96, 99));
        headerDTO.setUserDefLimit(line.substring(99, 112));
        headerDTO.setTotalAmt(line.substring(112, 125));
        headerDTO.setSettlementDate(line.substring(125, 133));
        headerDTO.setItemSeqNo(line.substring(133, 143));
        headerDTO.setCheckSum(line.substring(143, 153));
        headerDTO.setFiller1(line.substring(153, 156));
        headerDTO.setUserNo(line.substring(156, 174));
        headerDTO.setUserRef(line.substring(174, 192));
        headerDTO.setSponsorBankIFSCMICR(line.substring(192, 203));
        headerDTO.setUserBankAccNo(line.substring(203, 238));
        headerDTO.setTotalItems(line.substring(238, 247));
        headerDTO.setSettlementCycle(line.substring(247, 249));
        headerDTO.setFiller2(line.substring(249, 305));
        return headerDTO;
    }

    private static BondInterestPaymentFileDetailsDTO detailsRowParser(String line) {
        BondInterestPaymentFileDetailsDTO detailsDTO = new BondInterestPaymentFileDetailsDTO();
        detailsDTO.setAchTranCode(line.substring(0, 2));
        detailsDTO.setControl1(line.substring(2, 11));
        detailsDTO.setDestAccType(line.substring(11, 13));
        detailsDTO.setLedgerFolioNo(line.substring(13, 16));
        detailsDTO.setControl2(line.substring(16, 31));
        detailsDTO.setBeneficiaryAccName(line.substring(31, 71));
        detailsDTO.setControl3(line.substring(71, 80));
        detailsDTO.setControl4(line.substring(80, 87));
        detailsDTO.setUserName(line.substring(87, 107));
        detailsDTO.setControl5(line.substring(107, 120));
        detailsDTO.setAmount(line.substring(120, 133));
        detailsDTO.setAchItemSeqNo(line.substring(133, 143));
        detailsDTO.setChecksum(line.substring(143, 153));
        detailsDTO.setFlagForSuccess(line.substring(153, 154));
        detailsDTO.setReasonCode(line.substring(154, 156));
        detailsDTO.setDestBankIFSCMICR(line.substring(156, 167));
        detailsDTO.setBeneficiaryBankAccNo(line.substring(167, 202));
        detailsDTO.setSponsorBankIFSCMICR(line.substring(202, 213));
        detailsDTO.setUserNo(line.substring(213, 231));
        detailsDTO.setTransRef(line.substring(231, 261).trim());
        detailsDTO.setProductType(line.substring(261, 264));
        detailsDTO.setBeneficiaryAadharNo(line.substring(264, 278));
        detailsDTO.setuMRN(line.substring(278, 298));
        detailsDTO.setFiller(line.substring(298, 305));
        return detailsDTO;
    }

    public FileDownloadWrapperDTO downloadFile(String remoteFilePath, String fileName) {
        ChannelSftp channelSftp = null;
        FileDownloadWrapperDTO fileDownloadWrapperDTO = new FileDownloadWrapperDTO();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            channelSftp = this.sftp.createSftp();
            // Download the file into a ByteArrayOutputStream
            InputStream inputStream = channelSftp.get(remoteFilePath + "/" + fileName + ".txt");
            IOUtils.copy(inputStream, outputStream);
            fileDownloadWrapperDTO.setFileContent(outputStream);
            fileDownloadWrapperDTO.setMessage(CommonConstants.SUCCESS_CONST);
            return fileDownloadWrapperDTO;
        } catch (Exception e) {
            LOGGER.info("Exception will throw: "+e.getMessage());
            fileDownloadWrapperDTO.setMessage(e.getMessage());
        } finally {
            if (channelSftp != null && channelSftp.isConnected())
                channelSftp.disconnect();
        }
        return fileDownloadWrapperDTO;
    }
    @Override
    public ResponseDataDTO mergeUnMergeDownloadFile(BondActualInterestPaymentRequestDTO paymentRequestDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ResponseDataDTO responseDataDTO1 = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            if(null!=paymentRequestDTO.getFlag()&&paymentRequestDTO.getFlag().equalsIgnoreCase("UNMERGED")) {
                responseDataDTO1=paymentBO.downloadMergedUnMerged(paymentRequestDTO);
                if(null!=responseDataDTO1&&null!=responseDataDTO1.getMessage()&&responseDataDTO1.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(actualInterestPaymentExcelUnmerged(responseDataDTO1, paymentRequestDTO)));
                    fileResponseDto.setFileName("ACTUAL_INTEREST_PAYMENT_UNMERGED.xlsx");
                }else {
                    return responseDataDTO1;
                }
            }else if(null!=paymentRequestDTO.getFlag()&&paymentRequestDTO.getFlag().equalsIgnoreCase("MERGED")){
                responseDataDTO1=paymentBO.downloadMergedUnMerged(paymentRequestDTO);
                if(null!=responseDataDTO1&&null!=responseDataDTO1.getMessage()&&responseDataDTO1.getMessage().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(actualInterestPayment(responseDataDTO1, paymentRequestDTO)));
                    fileResponseDto.setFileName("ACTUAL_INTEREST_PAYMENT_MERGED.xlsx");
                }else {
                    return responseDataDTO1;
                }
            }
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Error in BondActualInterestPaymentRequestDTO, BondActualInterestPaymentServiceImpl: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    public byte[] actualInterestPaymentExcelUnmerged(ResponseDataDTO responseDataDTO,BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        List<BondActualInterestPaymentRequestDTO> list = Collections.emptyList();
        String loginSolid = UserAccountUtils.getCurrentSolId();
        String solDesc = Optional.ofNullable(UserAccountUtils.getCurrentUsername()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            workbook.setCompressTempFiles(true);
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);

            byteArrayOutputStream = new ByteArrayOutputStream();

            // Create title row
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("ACTUAL INTEREST PAYMENT UNMERGED");

            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);

            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));

            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);

            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
            // Create bank information row
            SXSSFRow bankInfoRow1 = sheet.createRow(2);
            SXSSFCell bankInfoCell1 = bankInfoRow1.createCell(0);
            bankInfoCell1.setCellValue("Interest for the Half Year : " + paymentRequestDTO.getHalfYear() + " Year : " + paymentRequestDTO.getYear());
// Create and set bank info cell style
            CellStyle bankInfoCellStyle1 = workbook.createCellStyle();
            bankInfoCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell1.setCellStyle(bankInfoCellStyle1);
            // Merge cells for bank information
            SXSSFRow headerRow1 = sheet.createRow(3);
            String[] mainHeaders = {
                    "","Basic Details From BLA cum Subscription", "", "", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "",
                    "OTHER SCHEMES INTEREST RELATED INFO", "", "", "", "", "", "", "",
                    "PAN STATUS", "", "","","","",
                    "TDS EXEMPTION DETAILS", "", "", "", "", "", "",
                    "FRSB INTEREST PAYOUT DETAILS", ""
            };
            CellStyle headerCellStyle1 = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font1 = workbook.createFont();
            font1.setBold(true);
            headerCellStyle1.setFont(font1);
            // Track maximum column widths
            int[] maxColumnWidths1 = new int[mainHeaders.length];
            for (int i = 0; i < mainHeaders.length; i++) {
                SXSSFCell cell = headerRow1.createCell(i);
                cell.setCellValue(mainHeaders[i]);
                cell.setCellStyle(headerCellStyle1);
                maxColumnWidths1[i] = mainHeaders[i].length();
            }
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(4);
            String[] headers = {
                    "Sl.No", "ModeOfPayment", "BranchRefNo", "BlaNo", "RefNo",
                    "ApplName1", "ApplName2", "ApplName3", "Addr1", "Addr2",
                    "RecName", "RecParentName", "PinCode", "PhoneNo", "ApplStatus",
                    "InvestmentAmount", "CFD", "MatDate", "AccountNo", "SubscriptionCount",
                    "BankSortCode", "BankName", "PanNo", "InterestPayout8Percent",
                    "NonCumulativeInt8", "CumulativeInt8", "InterestPayout7.75Percent",
                    "NonCumulativeInt7.75", "CumulativeInt7.75", "InterestPayout8.05Percent",
                    "NonCumulativeInt8.05", "CumulativeInt8.05", "TotalInterestPayout",
                    "InOperativePan", "NonFilers", "Age", "TaxableLimit",
                    "WaiverDocument", "WaiverDocumentRefNo", "RrbTdsRate",
                    "ThresholdExceedAmount", "ThresholdExceedTdsAmount", "ExemptionRate",
                    "ExemptionAmount", "ExemptionTdsAmount", "SurchargePercent", "EduCessPercent",
                    "BaseRateAmt", "SurchargeAmt", "EduCessAmt", "TdsAmount",
                    "TdsRecoveryAmount", "TdsRefundAmount", "IntRate", "LastIntPaid",
                    "PendingInterest", "CurrentHalfYearlyInterest", "FutureInterest",
                    "GrossInterestAmount", "IntPaidNet"
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
            list = (List<BondActualInterestPaymentRequestDTO>) responseDataDTO.getResult();
            // Populate data rows
            int rowNumber = 5;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondActualInterestPaymentRequestDTO data : list) {
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
                            data.getInterestPayOut85Per() != null ? data.getInterestPayOut85Per() : "",
                            data.getNonCumulativeIntFor85() != null ? data.getNonCumulativeIntFor85() : "",
                            data.getCumulativeIntFor85() != null ? data.getCumulativeIntFor85() : "",
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
                            data.getIntPaid() != null ? data.getIntPaid() : ""
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
                LOGGER.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            LOGGER.info("ACTUAL PAYMENT DATA GENERATION COMPLETED...");
            LOGGER.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
    public byte[] actualInterestPayment(ResponseDataDTO responseDataDTO,BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        List<BondActualInterestPaymentRequestDTO> list = Collections.emptyList();
        String loginSolid = UserAccountUtils.getCurrentSolId();
        String solDesc = Optional.ofNullable(UserAccountUtils.getCurrentUsername()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            workbook.setCompressTempFiles(true);
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Create title row
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("ACTUAL INTEREST PAYMENT MERGED");
            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));
            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK Branch Name : "+solDesc+" Branch Id : "+loginSolid);
            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 7));
            // Create bank information row
            SXSSFRow bankInfoRow1 = sheet.createRow(2);
            SXSSFCell bankInfoCell1 = bankInfoRow1.createCell(0);
            bankInfoCell1.setCellValue("Interest for the Half Year : " + paymentRequestDTO.getHalfYear() + " Year : " + paymentRequestDTO.getYear());
// Create and set bank info cell style
            CellStyle bankInfoCellStyle1 = workbook.createCellStyle();
            bankInfoCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell1.setCellStyle(bankInfoCellStyle1);
            // Merge cells for bank information
            SXSSFRow headerRow1 = sheet.createRow(3);
            String[] mainHeaders = {
                    "","Basic Details From BLA cum Subscription", "", "", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "", "",
                    "OTHER SCHEMES INTEREST RELATED INFO", "", "", "", "", "", "", "","","","",
                    "PAN STATUS", "", "","","","",
                    "TDS EXEMPTION DETAILS", "", "", "", "", "", "",
                    "FRSB INTEREST PAYOUT DETAILS", ""
            };
            CellStyle headerCellStyle1 = workbook.createCellStyle();
            org.apache.poi.ss.usermodel.Font font1 = workbook.createFont();
            font1.setBold(true);
            headerCellStyle1.setFont(font1);
            // Track maximum column widths
            int[] maxColumnWidths1 = new int[mainHeaders.length];
            for (int i = 0; i < mainHeaders.length; i++) {
                SXSSFCell cell = headerRow1.createCell(i);
                cell.setCellValue(mainHeaders[i]);
                cell.setCellStyle(headerCellStyle1);
                maxColumnWidths1[i] = mainHeaders[i].length();
            }
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(4);
            String[] headers = {
                    "Sl.No", "ModeOfPayment", "BlaNo",
                    "ApplName1", "ApplName2", "ApplName3", "Addr1", "Addr2",
                    "RecName", "RecParentName", "PinCode", "PhoneNo", "ApplStatus",
                    "InvestmentAmount", "AccountNo", "SubscriptionCount",
                    "BankSortCode", "BankName", "PanNo", "InterestPayout8Percent",
                    "NonCumulativeInt8", "CumulativeInt8", "InterestPayout7.75Percent",
                    "NonCumulativeInt7.75", "CumulativeInt7.75",
                    "InterestPayout8.05Percent","NonCumulativeInt8.05", "CumulativeInt8.05",
                    "TotalInterestPayout","InOperativePan", "NonFilers", "Age", "TaxableLimit",
                    "WaiverDocument", "WaiverDocumentRefNo", "RrbTdsRate",
                    "ThresholdExceedAmount", "ThresholdExceedTdsAmount", "ExemptionRate",
                    "ExemptionAmount", "ExemptionTdsAmount", "SurchargePercent", "EduCessPercent",
                    "BaseRateAmt", "SurchargeAmt", "EduCessAmt", "TdsAmount",
                    "TdsRecoveryAmount", "TdsRefundAmount", "IntRate", "LastIntPaid",
                    "PendingInterest", "CurrentHalfYearlyInterest", "FutureInterest",
                    "GrossInterestAmount", "IntPaidNet"
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
            list = (List<BondActualInterestPaymentRequestDTO>) responseDataDTO.getResult();
            // Populate data rows
            int rowNumber = 5;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (BondActualInterestPaymentRequestDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getModeOfPayment() != null ? data.getModeOfPayment() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
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
                            data.getInterestPayOut85Per() != null ? data.getInterestPayOut85Per() : "",
                            data.getNonCumulativeIntFor85() != null ? data.getNonCumulativeIntFor85() : "",
                            data.getCumulativeIntFor85() != null ? data.getCumulativeIntFor85() : "",
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
                            data.getIntPaid() != null ? data.getIntPaid() : ""
                    };
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            }
            else
            {
                LOGGER.info("REPORT OBJECT HAVING EMPTY DATA ");
            }
            LOGGER.info("ACTUAL PAYMENT MERGED DATA GENERATION COMPLETED...");
            LOGGER.info("Processed Data rows: "+ rowNumber);
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Exception while writing the Excel file: "+e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO){
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            ResponseDataDTO boResponse = this.paymentBO.downloadFileFromSFTP(paymentRequestDTO);
            if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(boResponse.getMessage())
                    && boResponse.getResult() instanceof String) {
                String fileName = (String) boResponse.getResult();
                FileDownloadWrapperDTO fileDownloadWrapperDTO = downloadFile(remoteDir, fileName);
                LOGGER.info("FileName: {}",fileName);
                if (fileDownloadWrapperDTO != null && fileDownloadWrapperDTO.getFileContent() != null) {
                    FileResponseDto fileResponseDto = new FileResponseDto();
                    fileResponseDto.setFileName(fileName + ".txt");
                    fileResponseDto.setFileData(Base64.getEncoder().encodeToString(fileDownloadWrapperDTO.getFileContent().toByteArray()));
                    responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                    responseDataDTO.setResult(fileResponseDto);
                }else if(fileDownloadWrapperDTO != null && fileDownloadWrapperDTO.getMessage() !=null&&fileDownloadWrapperDTO.getMessage().equalsIgnoreCase("No such file")) {
                    ResponseDataDTO bOResponse = this.paymentBO.downloadFileManual(paymentRequestDTO);
                    responseDataDTO.setMessage(bOResponse.getMessage());
                    responseDataDTO.setResult(bOResponse.getResult());
                } else {
                    ResponseDataDTO bOResponse = this.paymentBO.downloadFileManual(paymentRequestDTO);
                    responseDataDTO.setMessage(bOResponse.getMessage());
                    responseDataDTO.setResult(bOResponse.getResult());
                }
            } else {
                responseDataDTO.setMessage(boResponse.getMessage());
            }
        }catch (Exception ex) {
            LOGGER.info("Unexpected error occurred: "+ex.getMessage());
            ResponseDataDTO bOResponse = this.paymentBO.downloadFileManual(paymentRequestDTO);
            responseDataDTO.setMessage(bOResponse.getMessage());
            responseDataDTO.setResult(bOResponse.getResult());
            return responseDataDTO;
        }
        return responseDataDTO;
    }


    @Override
    public ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String halfYear, String year) {
        ResponseDataDTO responseDataDTO =new ResponseDataDTO();
        if (textFile.getOriginalFilename() == null || !textFile.getOriginalFilename().toLowerCase().endsWith(".txt")) {
            responseDataDTO.setMessage("Invalid file type. Only .txt files are allowed");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        responseDataDTO = parseUploadedTextFile(textFile);
        if (!ObjectUtils.isEmpty(responseDataDTO) && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())
                && responseDataDTO.getResult() instanceof BondInterestPaymentFileDTO){
            return this.paymentBO.uploadFile((BondInterestPaymentFileDTO) responseDataDTO.getResult(), schemeCode, halfYear, year);
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO addDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentBO.addDetails(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentBO.inquiry(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO verifyDetails(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentBO.verifyDetails(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO reject(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return this.paymentBO.reject(paymentRequestDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO = this.paymentBO.verifySubmit(paymentRequestDTO);
        if (responseDataDTO != null && CommonConstants.SUCCESS_CONST.equalsIgnoreCase(responseDataDTO.getMessage())) {
            BondACHFileWrapperDTO fileWrapperDTO = (BondACHFileWrapperDTO) responseDataDTO.getResult();
            BondACHFileHeaderDTO headerDTO = fileWrapperDTO.getAchFileHeaderDTO();
            List<BondACHFileDetailsDTO> detailsList = fileWrapperDTO.getAchFileDetailsList();
            String fileName = fileWrapperDTO.getFileName();
            List<String> listOfData = new LinkedList<>();
            listOfData.add(achFileUtils.headerStringCreationForACHFile(headerDTO));
            Consumer<BondACHFileDetailsDTO> consumeFn = element ->
                    listOfData.add(achFileUtils.detailsStringCreationForACHFile(element));
            detailsList.forEach(consumeFn);
            uploadUtils.uploadFileToSFTP(remoteDir, fileName, listOfData);
            LOGGER.info(" File written successfully!!!! with file name as {}", fileName);
            return responseDataDTO;
        } else
            return responseDataDTO;
    }
}
