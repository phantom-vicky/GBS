package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondReportBOLatest;
import com.isl.gbm.rbibond.dao.RbiBondReportDAOLatest;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import com.isl.gbm.rbibond.dto.reports.InetPortfolioRequestDTO;
import com.isl.gbm.rbibond.dto.reports.InetPortfolioResponseDTO;
import com.isl.gbm.rbibond.dto.reports.PortfolioDTO;
import com.isl.gbm.rbibond.dto.reports.TransferReportDTO;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class RbiBondReportBOImplLatest implements RbiBondReportBOLatest {


    @Autowired
    RbiBondReportDAOLatest rbiBondReportDAOLatest;

    @Override
    public ResponseDataDTO getPortfolio(PortfolioDTO portfoliodto) {
        if (portfoliodto.getSchemeCode() == null) {
            return rbiBondReportDAOLatest.getPortfolio(portfoliodto);
        }

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(generateZoneWiseBranchWiseReportExcel(rbiBondReportDAOLatest.getPortfolio(portfoliodto), portfoliodto)));
            fileResponseDto.setFileName("PORTFOLIO_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }

    public ResponseDataDTO getInetPortfolioDetails(InetPortfolioRequestDTO inetPortfolioRequestDTO) {
        if (inetPortfolioRequestDTO != null
                && inetPortfolioRequestDTO.getDownloadType() != null
                && inetPortfolioRequestDTO.getDownloadType().equalsIgnoreCase("excel")) {
            return getInetPortfolioDownload(inetPortfolioRequestDTO);
        }
        return rbiBondReportDAOLatest.getInetPortfolioDetails(inetPortfolioRequestDTO);
    }



    private ResponseDataDTO getInetPortfolioDownload(InetPortfolioRequestDTO inetPortfolioRequestDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            ResponseDataDTO dataResponse = rbiBondReportDAOLatest.getInetPortfolioDetails(inetPortfolioRequestDTO);
            if (dataResponse.getMessage() != null
                    && (CommonConstants.NDF.equalsIgnoreCase(dataResponse.getMessage())
                    || "No Data Found".equalsIgnoreCase(dataResponse.getMessage()))) {
                responseDataDTO.setMessage(CommonConstants.NDF);
                return responseDataDTO;
            }
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(
                    generateInetPortfolioReportExcel(dataResponse, inetPortfolioRequestDTO)));
            fileResponseDto.setFileName("INET_PORTFOLIO_REPORT.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }


    public byte[] generateZoneWiseBranchWiseReportExcel(ResponseDataDTO responseDataDTO,PortfolioDTO  portfoliodto) {
        List<PortfolioDTO> list = Collections.emptyList();
        HashMap<String,Object> finalData;
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
            titleCell.setCellValue("PORTFOLIO REPORT");

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
                    "S. NO.",
                    "SCHEME ID",
                    "SCHEME NAME",
                    "BRANCH REF NO",
                    "BLA NO",
                    "APPLICANT NAME 1",
                    "APPLICANT NAME 2",
                    "APPLICANT NAME 3",
                    "PAN NUMBER",
                    "INVESTMENT AMOUNT",
                    "MATURITY AMOUNT",
                    "SCHEME",
                    "CFD",
                    "CAS DATE",
                    "MATURITY DATE",
                    "ACCOUNT NO",
                    "MICR CODE",
                    "IFSC CODE",
                    "BANK NAME",
                    "BROKER CODE",
                    "STATUS",
                    "EMAIL",
                    "MOBILE NO",
                    "PHONE NO",
                    "ADDRESS 1",
                    "ADDRESS 2",
                    "CITY",
                    "STATE",
                    "PINCODE",
                    "Payment Mode",
                    "GSP_BLA_NO"
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
            list = (List<PortfolioDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (PortfolioDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getSchemeCode() != null ? data.getSchemeCode() : "",
                            data.getSchemeName() != null ? data.getSchemeName() : "",
                            data.getBranchRefNo() != null ? data.getBranchRefNo() : "",
                            data.getBlaNo() != null ? data.getBlaNo() : "",
                            data.getApplName1() != null ? data.getApplName1() : "",
                            data.getApplName2() != null ? data.getApplName2() : "",
                            data.getApplName3() != null ? data.getApplName3() : "",
                            data.getPanNumber() != null ? data.getPanNumber() : "",
                            data.getInvstAmount() != null ? data.getInvstAmount() : "",
                            data.getMaturityAmount() != null ? data.getMaturityAmount() : "",
                            data.getScheme() != null ? data.getScheme() : "",
                            data.getCfd() != null ? data.getCfd() : "",
                            data.getCasDate() != null ? data.getCasDate() : "",
                            data.getMaturityDate() != null ? data.getMaturityDate() : "",
                            data.getAccountNo() != null ? data.getAccountNo() : "",
                            data.getMicrCode() != null ? data.getMicrCode() : "",
                            data.getIfscCode() != null ? data.getIfscCode() : "",
                            data.getBankName() != null ? data.getBankName() : "",
                            data.getBrokerCode() != null ? data.getBrokerCode() : "",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getEmail() != null ? data.getEmail() : "",
                            data.getMobileNo() != null ? data.getMobileNo() : "",
                            data.getPhoneNo() != null ? data.getPhoneNo() : "",
                            data.getAddress1() != null ? data.getAddress1() : "",
                            data.getAddress2() != null ? data.getAddress2() : "",
                            data.getCity() != null ? data.getCity() : "",
                            data.getState() != null ? data.getState() : "",
                            data.getPinCode() != null ? data.getPinCode() : "",
                             data.getPaymentMode()!=null?data.getPaymentMode():"",
                            data.getGsp_BLA_NO()!=null?data.getGsp_BLA_NO():""

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
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }


    public byte[] generateInetPortfolioReportExcel(ResponseDataDTO responseDataDTO, InetPortfolioRequestDTO  inetPortfolioDto) {

        List<InetPortfolioResponseDTO> list = Collections.emptyList();
        HashMap<String,Object> finalData;
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
            titleCell.setCellValue("INET PORTFOLIO REPORT");

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
                    "S. NO.",
                    "Request ID",
                    "Pan Number",
                    "Request DateTime",
                    "Response DateTime",
                    "Response Status"
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
            list = (List<InetPortfolioResponseDTO>) finalData.get("list");

            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (InetPortfolioResponseDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;

                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getRequestId() != null ? data.getRequestId() : "",
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getReqDateTime() != null ? data.getReqDateTime() : "",
                            data.getResDateTime() != null ? data.getResDateTime() : "",
                            data.getResStatus() != null ? data.getResStatus() : "",
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
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }

            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public ResponseDataDTO getTransferReport(TransferReportDTO transferReportDTO) {
        return rbiBondReportDAOLatest.getTransferReport(transferReportDTO);

    }
}
