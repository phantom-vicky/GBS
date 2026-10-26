/*
 *
 */
package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dto.FileResponseDto;
import com.isl.gbm.user.management.dao.UserDao;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.manager.ReportsManager;
import com.isl.gbm.user.management.service.ReportsService;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
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
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import static org.apache.poi.ss.util.CellUtil.createCell;

/**
 * The Class ReportsServiceImpl.
 */
@Service
@Transactional
public class ReportsServiceImpl implements ReportsService {

    /**
     * The reports manager.
     */
    @Autowired
    private ReportsManager reportsManager;
    @Autowired
    private UserDao userDao;

    @Value("${gbm.bankName}")
    private String bankName;

    /**
     * Sets the reports manager.
     *
     * @paramreportsManager the new reports manager
     */

    private static final Logger log = LoggerFactory.getLogger(ReportsServiceImpl.class);


    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.ReportsService#getUnsuccessfulLoginAtempts(java.util.Date, java.util.Date)
     */
    @Override
    public List<LoginReportDto> getUnsuccessfulLoginAtempts(Date fromDate, Date toDate) {
        return this.reportsManager.getUnsuccessfulLoginAtempts(fromDate, toDate);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.user.management.service.ReportsService#getAdminActivities(java.
     * lang.Long)
     */
    @Override
    public List<AdminActivityReportDto> getAdminActivities(Date date) {
        return this.reportsManager.getAdminActivities(date);
    }


    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.ReportsService#getLoginReports(com.isl.gbm.user.management.dto.ReportSearchCriteriaDto)
     */
    @Override
    public List<LoginReportDto> getLoginReports(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        return this.reportsManager.getLoginReports(reportSearchCriteriaDto);
    }


    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.ReportsService#getMultipleDeviceLogin(java.util.Date)
     */
    @Override
    public List<LoginReportDto> getMultipleDeviceLogin(String solId, Date date) {
        return this.reportsManager.getMultipleDeviceLogin(solId, date);
    }


    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.ReportsService#getDormantUsersReport(java.util.Date, java.util.Date)
     */
    @Override
    public List<LoginReportDto> getDormantUsersReport(Date from, Date to) {
        return this.reportsManager.getDormantUsersReport(from, to);
    }


    @Override
    public ResponseDataDTO getUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        return userDao.getUserReports(reportSearchCriteriaDto);
    }

    @Override
    public ResponseDataDTO activateUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        return userDao.activateUserReport(reportSearchCriteriaDto);
    }

    @Override
    public ResponseDataDTO getAccessControlMatrixreport(AccessControlMatrixReportDTO accessControlMatrixReportDTO) {
        return userDao.getAccessControlMatrixreport(accessControlMatrixReportDTO);
    }

    public ResponseDataDTO getPopulationData(PopulationReportDTO populationReportDTO) {
        return userDao.getPopulationData(populationReportDTO);
    }

  @Override
    public ResponseDataDTO getRoleRightsReport(RoleRightsReportDTO roleRightsReportDTO) {
        return userDao.getRoleRightsReport(roleRightsReportDTO);
    }
  @Override
    public ResponseDataDTO getRoleRightsReportDownload(RoleRightsReportDTO roleRightsReportDTO) {
      ResponseDataDTO responseDataDTO = new ResponseDataDTO();
      String resultMsg = "SUCCESS";
      FileResponseDto fileResponseDto = new FileResponseDto();
      try {
          fileResponseDto.setFileData(Base64.getEncoder().encodeToString(roleRightsReportExcel(userDao.getRoleRightsReport(roleRightsReportDTO), roleRightsReportDTO)));
          fileResponseDto.setFileName("Role Rights Report.xlsx");
          responseDataDTO.setResult(fileResponseDto);
      } catch (Exception e) {
          e.printStackTrace();
          log.error("Error in RoleRights: " + e.getMessage());
          resultMsg = CommonConstants.NDF;
          responseDataDTO.setMessage(resultMsg);
      }
      responseDataDTO.setMessage(resultMsg);
      return responseDataDTO;
    }
    public byte[] roleRightsReportExcel(ResponseDataDTO responseDataDTO, RoleRightsReportDTO roleRightsReportDTO) {
        List<RoleRightsReportDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
        String solDesc = Optional.ofNullable(UserAccountUtils.getCurrentUsername()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Role Rights Report");
            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setWrapText(true);
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK  Branch Name : " + solDesc + "  Branch Id : " + loginSolid);
            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setWrapText(true);
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No",
                    "Role/Profile/Template Name",
                    "Role/Profile/Template Description",
                    "Rights Assigned to Role",
                    "Rights Description(User friendly language)",
                    "Rights Assigned Y/N",
                    "Status of the Role/Profile/Template Name",
                    "Profile Assigned To(Department/Business Unit)",
                    "Type of Access"
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
            list = (List<RoleRightsReportDTO>) finalData.get("roleRightsReportDTOList");
            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (RoleRightsReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getRoleName() != null ? data.getRoleName() : "",
                            data.getRoleDesc() != null ? data.getRoleDesc() : "",
                            data.getRightsAssigned() != null ? data.getRightsAssigned() : "",
                            data.getRoleDescription() != null ? data.getRoleDescription() : "",
                            data.getRightsAssignedFlg() != null ? data.getRightsAssignedFlg() : "",
                            data.getRoleStatus() != null ? data.getRoleStatus() : "",
                            data.getDepartmentCode() != null ? data.getDepartmentCode() : "",
                            data.getTypeAccess() != null ? data.getTypeAccess() : ""
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
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, getSafeColumnWidth(maxColumnWidths[i]));
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
    private int getSafeColumnWidth(int charLength) {
        int MAX_EXCEL_COLUMN_WIDTH = 255 * 256;
        return Math.min(charLength * 256, MAX_EXCEL_COLUMN_WIDTH);
    }
    @Override
    public ResponseDataDTO getUserActivityDetails(UserActivityReportDTO userActivityReportDTO) {
        return userDao.getUserActivityDetails(userActivityReportDTO);
    }

    @Override
    public ResponseDataDTO getModuleName(UserActivityReportDTO userActivityReportDTO) {
        return userDao.getModuleName(userActivityReportDTO);
    }

    @Override
    public byte[] excelFileGeneratorForUserReport(List<Map<String, Object>> results, UserActivityReportDTO userActivityReportDTO) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        String sheet1="sheet1";

        XSSFSheet sheet = workbook.createSheet(sheet1);

        CellStyle style = workbook.createCellStyle();
        XSSFFont headerFont = workbook.createFont();

        headerFont.setBold(true);
        headerFont.setFontHeight(14);
        style.setFont(headerFont);

        Row row = sheet.createRow(0);
        createCell(row,0, "User Activity Report", style);

        row = sheet.createRow(1);
        createCell(row, 0, "Bank Name :",style);

        createCell(row,1, bankName);

        row = sheet.createRow(2);
        createCell(row, 0, "Branch Name :",style);
        createCell(row,1, userActivityReportDTO.getBranchName());

        row = sheet.createRow(3);
        createCell(row, 0, "Branch Id :",style);
        createCell(row,1, UserAccountUtils.getCurrentSolId());


        row=sheet.createRow(4);
        createCell(row, 0, "Module Id",style);
        createCell(row, 1, "Link Name",style);
        createCell(row, 2, "Audit Ref Num",style);
        createCell(row, 3, "Modified Fields Data",style);
        createCell(row, 4, "Remarks",style);
        createCell(row, 5, "Created By",style);
        createCell(row, 6, "Created On",style);
        createCell(row, 7, "Modified By",style);
        createCell(row, 8, "Modified On",style);
        createCell(row, 9, "Authorized By",style);
        createCell(row, 10, "Authorized On",style);
        createCell(row, 11, "Deleted By",style);
        createCell(row, 12, "Deleted On",style);
        createCell(row, 13, "Module Account Number",style);
        createCell(row, 14, "Name of the Treasury",style);



        int n = 4;

        double totIntAmt = 0;
        double totTds = 0;
        double totNet = 0;

        for(Map<String, Object> item : userActivityReportDTO.getData())
        {
            String totIntAmtS = (String)  item.get("INTEREST_AMT");
            String totTdsS = (String)  item.get("CURRENT_TDS_AMOUNT");
            String totNetS = (String)  item.get("NET_INTEREST_AMOUNT");

            totIntAmt =   totIntAmtS == null ? 0 : Double.parseDouble(totIntAmtS) + totIntAmt;
            totTds =  totTdsS == null ? 0 : Double.parseDouble(totTdsS) + totTds;
            totNet =  totNetS == null ? 0 : Double.parseDouble(totNetS) + totNet;
//            totIntAmt = totIntAmt + bondTdsDeductionReportDTO.getInterestAmt() == null ? 0 : Integer.parseInt(bondTdsDeductionReportDTO.getInterestAmt());
//            totTds = totTds + bondTdsDeductionReportDTO.getTdsAmt() == null ? 0 : Integer.parseInt(bondTdsDeductionReportDTO.getTdsAmt());
//            totNet = totNet + bondTdsDeductionReportDTO.getNetAmt() == null ? 0 : Integer.parseInt(bondTdsDeductionReportDTO.getNetAmt());


            row=sheet.createRow(++n);
            createCell(row, 1, (String) item.get("module_id"));
            createCell(row, 2, (String) item.get("link_name"));
            createCell(row, 3, (String) item.get("audit_ref_num"));
            createCell(row, 4, (String) item.get("OLD_VAL"));
            createCell(row, 5, (String) item.get("NEW_VAL"));
            createCell(row, 6, String.valueOf(item.get("remarks")));
            createCell(row, 7, (String) item.get("created_by"));
            createCell(row, 8,String.valueOf(item.get("created_on")));
            createCell(row, 9,String.valueOf(item.get("modified_by")));
            createCell(row, 10,String.valueOf(item.get("modified_on")));
            createCell(row, 11,String.valueOf(item.get("authorized_by")));
            createCell(row, 12,String.valueOf(item.get("authorized_on")));
            createCell(row, 13,String.valueOf(item.get("deleted_by")));
            createCell(row, 14,String.valueOf(item.get("entity_account_no")));
            createCell(row, 15,String.valueOf(item.get("entity_account_no")));

        }

        row = sheet.createRow(++n);
        createCell(row, 7, "Total ",style);
        createCell(row,8, String.valueOf(totIntAmt));
        createCell(row,9, String.valueOf(totTds));
        createCell(row,10, String.valueOf(totNet));

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
    public byte[] pdfFileGeneratorForUserReport(List<Map<String, Object>> results, UserActivityReportDTO userActivityReportDTO) throws IOException {
        return new byte[0];
    }

    @Override
    public ResponseDataDTO adminActivityReport(NewAdminActivityReportDTO newAdminActivityReportDTO) {
        return userDao.adminActivityReport(newAdminActivityReportDTO);
    }
    @Override
    public ResponseDataDTO adminActivityReportDownload(NewAdminActivityReportDTO newAdminActivityReportDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(adminActivityReportExcel(userDao.adminActivityReport(newAdminActivityReportDTO), newAdminActivityReportDTO)));
            fileResponseDto.setFileName("Admin Activity Report.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in RoleRights: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    public byte[] adminActivityReportExcel(ResponseDataDTO responseDataDTO, NewAdminActivityReportDTO newAdminActivityReportDTO) {
        List<NewAdminActivityReportDTO> list = Collections.emptyList();
        String loginSolid = UserAccountUtils.getCurrentSolId();
        String solDesc = Optional.ofNullable(UserAccountUtils.getCurrentUsername()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Admin Activity Report");
            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setWrapText(true);
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK  Branch Name : " + solDesc + "  Branch Id : " + loginSolid);
            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setWrapText(true);
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No",
                    "User ID",
                    "UserName",
                    "Branch Code",
                    "Branch Name",
                    "Profile Name",
                    "Activity",
                    "Maker Id and Maker Name",
                    "Checker Id and Checker Name",
                    "Maker Date",
                    "Maker Time",
                    "Checker Date",
                    "Checker Time",
                    "Old Employee Name",
                    "New Employee Name",
                    "Old Profile Name",
                    "New Profile Name",
                    "Old Branch Name",
                    "New Branch Name"
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
            list =(List<NewAdminActivityReportDTO>)responseDataDTO.getResult();
            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (NewAdminActivityReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            String.valueOf(sNo++),
                            data.getId() != null ? data.getId() : "",
                            data.getUsername() != null ? data.getUsername() : "",
                            data.getBranchCode() != null ? data.getBranchCode() : "",
                            data.getBranchName() != null ? data.getBranchName() : "",
                            data.getProfileName() != null ? data.getProfileName() : "",
                            data.getCreationFlag() != null ? data.getCreationFlag() : "",
                            data.getMakerId() != null ? data.getMakerId() : "",
                            data.getLastCheckerId() != null ? data.getLastCheckerId() : "",
                            (data.getMakerDateTime() != null && data.getMakerDateTime().split(" ").length == 2)
                                    ? data.getMakerDateTime().split(" ")[0] : "",
                            (data.getMakerDateTime() != null && data.getMakerDateTime().split(" ").length == 2)
                                    ? data.getMakerDateTime().split(" ")[1] : "",
                            (data.getLastCheckerDateTime() != null && data.getLastCheckerDateTime().split(" ").length == 2)
                                    ? data.getLastCheckerDateTime().split(" ")[0] : "",
                            (data.getLastCheckerDateTime() != null && data.getLastCheckerDateTime().split(" ").length == 2)
                                    ? data.getLastCheckerDateTime().split(" ")[1] : "",
                            data.getOldEmployeeName() != null ? data.getOldEmployeeName() : "",
                            data.getNewEmployeeName() != null ? data.getNewEmployeeName() : "",
                            data.getOldProfileName() != null ? data.getOldProfileName() : "",
                            data.getNewProfileName() != null ? data.getNewProfileName() : "",
                            data.getOldBranchName() != null ? data.getOldBranchName() : "",
                            data.getNewBranchName() != null ? data.getNewBranchName() : ""
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
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, getSafeColumnWidth(maxColumnWidths[i]));
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
    public ResponseDataDTO loginLogoutReport(LoginLogoutReportDTO loginLogoutReportDTO) {
        return userDao.loginLogoutReport(loginLogoutReportDTO);


    }



    public ResponseDataDTO getPopulationDataDownload(PopulationReportDTO populationReportDTO){


        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        String resultMsg = "SUCCESS";
        FileResponseDto fileResponseDto = new FileResponseDto();
        try {
            fileResponseDto.setFileData(Base64.getEncoder().encodeToString(populationReportExcel(userDao.getPopulationData(populationReportDTO), populationReportDTO)));
            fileResponseDto.setFileName("Population Report.xlsx");
            responseDataDTO.setResult(fileResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error in Population: " + e.getMessage());
            resultMsg = CommonConstants.NDF;
            responseDataDTO.setMessage(resultMsg);
        }
        responseDataDTO.setMessage(resultMsg);
        return responseDataDTO;
    }
    public byte[] populationReportExcel(ResponseDataDTO responseDataDTO, PopulationReportDTO populationReportDTO) {
        List<PopulationReportDTO> list = Collections.emptyList();
        HashMap<String, Object> finalData;
        String loginSolid = UserAccountUtils.getCurrentSolId();
        String solDesc = Optional.ofNullable(UserAccountUtils.getCurrentUsername()).orElse("");
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Create title row
            SXSSFRow titleRow = sheet.createRow(0);
            SXSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("Population Report");
            // Create and set title cell style
            CellStyle titleCellStyle = workbook.createCellStyle();
            titleCellStyle.setWrapText(true);
            titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
            titleCell.setCellStyle(titleCellStyle);
            // Merge cells for title
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
            // Create bank information row
            SXSSFRow bankInfoRow = sheet.createRow(1);
            SXSSFCell bankInfoCell = bankInfoRow.createCell(0);
            bankInfoCell.setCellValue("Bank Name : HDFC BANK  Branch Name : " + solDesc + "  Branch Id : " + loginSolid);
            // Create and set bank info cell style
            CellStyle bankInfoCellStyle = workbook.createCellStyle();
            bankInfoCellStyle.setWrapText(true);
            bankInfoCellStyle.setAlignment(HorizontalAlignment.CENTER);
            bankInfoCell.setCellStyle(bankInfoCellStyle);
            // Merge cells for bank information
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(2);
            String[] headers = {
                    "S.No",
                    "User ID",
                    "User Name",
                    "Employee Code",
                    "Branch Code",
                    "Branch Name",
                    "Department Code",
                    "Department Name",
                    "Profile",
                    "Status",
                    "Creation Date & Time",
                    "Last Password Changed Date & Time",
                    "Last Login Date & Time",
                    "Last Modified Maker ID & Name",
                    "Maker Date & Time",
                    "Last Modified Checker ID & Name",
                    "Checker Date & Time",
                    "Disabled Date & Time",
                    "Profile End/Deletion date & time"
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
            list = (List<PopulationReportDTO>) finalData.get("populationDTOList");
            // Populate data rows
            int rowNumber = 3;
            int sNo = 1;
            if (!list.isEmpty()) {
                for (PopulationReportDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            String.valueOf(sNo++),

//                            data.getId() != null ? data.getId() : "",
                            data.getUserId() != null ? data.getUserId() : "",
                            data.getUserName() != null ? data.getUserName() : "",
                            data.getEmployeeCode() != null ? data.getEmployeeCode() : "",
                            data.getBranchCode() != null ? data.getBranchCode() : "",
                            data.getBranchName() != null ? data.getBranchName() : "",
                            data.getDepartmentCode() != null ? data.getDepartmentCode() : "",
                            data.getDepartmentName() != null ? data.getDepartmentName() : "",
                            data.getRoleName() != null ? data.getRoleName() : "",
                            data.getStatus() != null ? data.getStatus() : "",
                            data.getCreationDateTime() != null ? data.getCreationDateTime() : "",
                            data.getLastPwdChangedDateTime() != null ? data.getLastPwdChangedDateTime() : "",
                            data.getLastLoginDateTime() != null ? data.getLastLoginDateTime() : "",
                            data.getLastModifiedIdName() != null ? data.getLastModifiedIdName() : "",
                            data.getLastModifiedDateTime() != null ? data.getLastModifiedDateTime() : "",
                            data.getLastCheckerId() != null ? data.getLastCheckerId() : "",
                            data.getLastCheckerDateTime() != null ? data.getLastCheckerDateTime() : "",
                            data.getDisabledDateTime() != null ? data.getDisabledDateTime() : "",
                            data.getDeletionDateTime() != null ? data.getDeletionDateTime() : "",
//                            data.getCheckerDateTime() != null ? data.getCheckerDateTime() : "",
//                            data.getDisabledDateTime() != null ? data.getDisabledDateTime() : "",
//                            data.getDeletionDateTime() != null ? data.getDeletionDateTime() : "",

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
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, getSafeColumnWidth(maxColumnWidths[i]));
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: " + e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }



    }


