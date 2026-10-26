package com.isl.gbm.rbibond.jobs;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.util.Sftp;
import com.isl.gbm.user.management.dto.NewAdminActivityReportDTO;
import com.isl.gbm.user.management.dto.PopulationReportDTO;
import com.isl.gbm.user.management.service.impl.ReportsServiceImpl;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class is responsible for generating reports related to Admin activities and population data.
 * It utilizes scheduled tasks to fetch data, export it to Excel, and upload the files to an SFTP server.
 * The class supports exporting two types of reports:
 * 1. Population report
 * 2. Admin activity report

 * The reports are fetched at scheduled intervals and are uploaded to a designated remote directory via SFTP.
 * Additionally, the reports are saved locally on the file system with a timestamped name for easy identification.

 * The class handles SFTP connections, file creation, and file uploads, including error handling and logging.

 * Author: Nethaji
 */
@Component
public class AdminReports {

    private static final Logger logger = LoggerFactory.getLogger(AdminReports.class);

    private final ReportsServiceImpl service;

    @Autowired
    public AdminReports(ReportsServiceImpl service) {
        this.service = service;
    }

    @Autowired
    @Qualifier(value  = "GstSftp")
    Sftp sftpConnection;

  //  @Value("${sftp.base.directory.reports}")
    private String baseDirectory;

   // @Value("${sftp.scheduled.job.cron.report}")
    private String cronExpression;

    public static boolean SFTP_FLAG = true;
    public static String  SUCCESS = "SUCCESS";
    public static String  FILE_NAME_p = "population_report_";
    public static String  FILE_NAME_A = "admin_activity_report_";

    /**
     * Scheduled method that fetches data from the reports service at regular intervals.
     * It exports the data to Excel and uploads it to the SFTP server.

     * The method fetches:
     * - Population report data
     * - Admin activity report data

     * These reports are exported to Excel and uploaded to an SFTP server, as well as saved locally on the system.

     * This method is executed based on the cron expression specified in the application properties.
     *
     */
    //@Scheduled(cron = "${sftp.scheduled.job.cron.report}")
    public void fetchData() {
        ChannelSftp sftp = null;
        try {
            sftp = sftpConnection.createSftp();
            PopulationReportDTO dto = new PopulationReportDTO();
            dto.setDate(getFormattedDate());
            dto.setSftpFlag(SFTP_FLAG);
            dto.setPageNo("0");
            NewAdminActivityReportDTO dto1 = new NewAdminActivityReportDTO();
            dto1.setFromDate(getFormattedDateTMinusOne());
            dto1.setToDate(getFormattedDate());
            dto1.setSftpFlag(SFTP_FLAG);
            dto1.setPageNo("0");
            ResponseDataDTO datafile1 = service.getPopulationData(dto);
            if (SUCCESS.equalsIgnoreCase(datafile1.getMessage())) {
                List<PopulationReportDTO> dataList = (List<PopulationReportDTO>) datafile1.getResult();
                byte[] fileData = exportDataToExcel(dataList);
                //saveExcelFileLocally(fileData, FILE_NAME_p);
                uploadFileToSFTP(sftp, fileData, baseDirectory, FILE_NAME_p);
            }else {
                logger.error("Failed to fetch population data: {}", datafile1.getMessage());
                throw new RuntimeException("Failed to fetch population data, reason: " + datafile1.getMessage());
            }

            ResponseDataDTO datafile2 = service.adminActivityReport(dto1);
            if (SUCCESS.equalsIgnoreCase(datafile2.getMessage())) {
                List<NewAdminActivityReportDTO> dataList = (List<NewAdminActivityReportDTO>) datafile2.getResult();
                byte[] fileData = exportDataToExcelActivityReport(dataList);
                //saveExcelFileLocally(fileData, FILE_NAME_A);
                uploadFileToSFTP(sftp, fileData, baseDirectory, FILE_NAME_A);
            }else {
                logger.error("Failed to fetch population data: {}", datafile1.getMessage());
                throw new RuntimeException("Failed to fetch population data, reason: " + datafile1.getMessage());
            }
        } catch (Exception e) {
            logger.error("Error in fetchData method: {}", e.getMessage(), e);
        } finally {
            if (sftp != null) {
                sftp.disconnect();
            }
        }
    }

    /**
     * Exports a list of PopulationReportDTO objects to an Excel file.
     *
     * @param dataList the list of PopulationReportDTO objects to be exported.
     * @return a byte array representing the exported Excel file.
     * @throws Exception if an error occurs during the export process.
     */
    public byte[] exportDataToExcel(List<PopulationReportDTO> dataList) throws Exception {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Data");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Map<String, String> columnNameMap = getColumnNameMap();

            if (!dataList.isEmpty()) {
                Field[] fields = PopulationReportDTO.class.getDeclaredFields();
                Row headerRow = sheet.createRow(0);
                int cellIndex = 0;
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();

                    if ("date".equalsIgnoreCase(fieldName) || "sftpFlag".equalsIgnoreCase(fieldName)) {
                        continue;
                    }

                    String displayName = columnNameMap.getOrDefault(fieldName, fieldName);
                    Cell cell = headerRow.createCell(cellIndex++);
                    cell.setCellValue(displayName);
                    cell.setCellStyle(headerCellStyle);
                }

                int rowIndex = 1;
                for (PopulationReportDTO record : dataList) {
                    Row row = sheet.createRow(rowIndex++);
                    int columnIndex = 0;
                    for (Field field : fields) {
                        field.setAccessible(true);
                        String fieldName = field.getName();

                        if ("date".equalsIgnoreCase(fieldName) || "sftpFlag".equalsIgnoreCase(fieldName)) {
                            continue;
                        }

                        Object value = field.get(record);
                        Cell cell = row.createCell(columnIndex++);
                        cell.setCellValue(value == null ? "" : value.toString());
                    }
                }
            }

            workbook.write(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            logger.error("Error exporting data to Excel: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Helper method to get the column name mapping for PopulationReportDTO fields.
     *
     * @return a map of field names to display names.
     */
    private static Map<String, String> getColumnNameMap() {
        Map<String, String> columnNameMap = new HashMap<>();
        columnNameMap.put("id", "ID");
        columnNameMap.put("userId", "User ID");
        columnNameMap.put("userName", "User Name");
        columnNameMap.put("employeeCode", "Employee Code");
        columnNameMap.put("branchCode", "Branch Code");
        columnNameMap.put("branchName", "Branch Name");
        columnNameMap.put("departmentCode", "Department Code");
        columnNameMap.put("departmentName", "Department Name");
        columnNameMap.put("roleName", "Role Name");
        columnNameMap.put("status", "Status");
        columnNameMap.put("creationDateTime", "Creation Date Time");
        columnNameMap.put("lastModifiedIdName", "Last Modified ID Name");
        columnNameMap.put("lastCheckerId", "Last Checker ID");
        columnNameMap.put("lastCheckerDateTime", "Last Checker Date Time");
        columnNameMap.put("disabledDateTime", "Disabled Date Time");
        columnNameMap.put("deletionDateTime", "Deletion Date Time");
        columnNameMap.put("lastPwdChangedDateTime", "Last Password Changed");
        columnNameMap.put("lastModifiedDateTime", "Last Modified");
        columnNameMap.put("lastLoginDateTime", "Last Login");
        return columnNameMap;
    }

    /**
     * Exports a list of NewAdminActivityReportDTO objects to an Excel file.
     *
     * @param dataList the list of NewAdminActivityReportDTO objects to be exported.
     * @return a byte array representing the exported Excel file.
     * @throws Exception if an error occurs during the export process.
     */
    public byte[] exportDataToExcelActivityReport(List<NewAdminActivityReportDTO> dataList) throws Exception {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Data");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);

            Map<String, String> columnNameMap = getNameMap();

            if (!dataList.isEmpty()) {
                Field[] fields = NewAdminActivityReportDTO.class.getDeclaredFields();
                Row headerRow = sheet.createRow(0);
                int cellIndex = 0;
                for (Field field : fields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();

                    if ("fromDate".equalsIgnoreCase(fieldName) || "toDate".equalsIgnoreCase(fieldName) || "sftpFlag".equalsIgnoreCase(fieldName)) {
                        continue;
                    }

                    String displayName = columnNameMap.getOrDefault(fieldName, fieldName);
                    Cell cell = headerRow.createCell(cellIndex++);
                    cell.setCellValue(displayName);
                    cell.setCellStyle(headerCellStyle);
                }

                int rowIndex = 1;
                for (NewAdminActivityReportDTO record : dataList) {
                    Row row = sheet.createRow(rowIndex++);
                    int columnIndex = 0;
                    for (Field field : fields) {
                        field.setAccessible(true);
                        String fieldName = field.getName();

                        if ("fromDate".equalsIgnoreCase(fieldName) || "toDate".equalsIgnoreCase(fieldName) || "sftpFlag".equalsIgnoreCase(fieldName)) {
                            continue;
                        }

                        Object value = field.get(record);
                        Cell cell = row.createCell(columnIndex++);
                        cell.setCellValue(value == null ? "" : value.toString());
                    }
                }
            }

            workbook.write(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            logger.error("Error exporting activity report data to Excel: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Helper method to get the column name mapping for NewAdminActivityReportDTO fields.
     *
     * @return a map of field names to display names.
     */
    private static Map<String, String> getNameMap() {
        Map<String, String> columnNameMap = new HashMap<>();
        columnNameMap.put("id", "ID");
        columnNameMap.put("username", "Username");
        columnNameMap.put("branchCode", "Branch Code");
        columnNameMap.put("branchName", "Branch Name");
        columnNameMap.put("profileName", "Profile Name");
        columnNameMap.put("activity", "Activity");
        columnNameMap.put("makerId", "Maker ID");
        columnNameMap.put("makerDateTime", "Maker Date Time");
        columnNameMap.put("lastCheckerId", "Last Checker ID");
        columnNameMap.put("lastCheckerDateTime", "Last Checker Date Time");
        columnNameMap.put("oldEmployeeName", "Old Employee Name");
        columnNameMap.put("newEmployeeName", "New Employee Name");
        columnNameMap.put("oldProfileName", "Old Profile Name");
        columnNameMap.put("newProfileName", "New Profile Name");
        columnNameMap.put("oldBranchName", "Old Branch Name");
        columnNameMap.put("newBranchName", "New Branch Name");
        columnNameMap.put("creationFlag", "Creation Flag");
        columnNameMap.put("enabledFlag", "Enabled Flag");
        columnNameMap.put("disabledFlag", "Disabled Flag");
        columnNameMap.put("deletionFlag", "Deletion Flag");
        return columnNameMap;
    }

    /**
     * Helper method to get the current date in the required format  (e.g., dd-MMM-yyyy).
     *
     * @return the current date as a string.
     */
    private static String getFormattedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        return sdf.format(new Date());
    }

    // Method to get the date one day before the current date (t-1) in the format dd-MMM-yyyy
    public static String getFormattedDateTMinusOne() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return LocalDate.now().minusDays(1).format(formatter);
    }

    public void saveExcelFileLocally(byte[] excelData, String nameFormat) {
        try {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter folderFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = now.format(folderFormatter);
            DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
            String formattedDateTime = now.format(fileFormatter);

            String baseDirectory = "D:/CLIENTS/HDFC/HDFC_DOCS/TEST/SFTP-FILE/";
            String folderPath = baseDirectory + formattedDate;
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileName = nameFormat + formattedDateTime + ".xlsx";
            String filePath = folderPath + "/" + fileName;

            File file = new File(filePath);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(excelData);
            }

            logger.info("File saved successfully at: {}", filePath);
        } catch (IOException e) {
            logger.error("Error saving Excel file locally: {}", e.getMessage(), e);
        }
    }

    public void uploadFileToSFTP(ChannelSftp sftp, byte[] excelData, String remoteDirectory, String remoteFileName) throws Exception {
        try(ByteArrayInputStream data = new ByteArrayInputStream(excelData)) {
            // Check if the connection is established
            if (sftp == null || !isSftpConnected(sftp)) {
                throw new SftpException(ChannelSftp.SSH_FX_NO_CONNECTION, "SFTP connection is not established or is closed.");
            }
            createRemoteDirectoryIfNotExists(sftp, remoteDirectory);

            // Format for file name (e.g., "population_report_2025-01-27_14-30.xlsx")
            DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
            LocalDateTime now = LocalDateTime.now();
            String formattedDateTime = now.format(fileFormatter);

            // Construct the full remote file path
            String remoteFilePath = Paths.get(remoteFileName + formattedDateTime + ".xlsx").toString();

            sftp.put(data, remoteDirectory + "/" + remoteFilePath);
            logger.info("File uploaded successfully to SFTP server: {}", remoteDirectory + "/" + remoteFileName);

        } catch (SftpException e) {
            logger.error("Error uploading file to SFTP: {}", e.getMessage(), e);
            throw new Exception("Error uploading file to SFTP.", e);
        }
    }

    /**
     * Helper method to check if the SFTP connection is still alive.
     * Performs a harmless operation (ls(".")) to check the connection status.
     *
     * @param sftp The SFTP channel object.
     * @return true if the connection is alive, false otherwise.
     */
    private boolean isSftpConnected(ChannelSftp sftp) {
        try {
            sftp.ls(".");
            return true;
        } catch (SftpException e) {
            logger.error("SFTP connection check failed: " + e.getMessage(), e);
            return false;
        }
    }


    private void createRemoteDirectoryIfNotExists(ChannelSftp sftp, String remoteDirectory) throws SftpException {
        try {
            sftp.cd(remoteDirectory);  // Try to change to the remote directory
        } catch (SftpException e) {
            logger.info("Directory {} does not exist, creating it.", remoteDirectory);
            String[] directories = remoteDirectory.split("/");
            StringBuilder path = new StringBuilder();
            for (String dir : directories) {
                if (!dir.isEmpty()) {
                    path.append("/").append(dir);
                    try {
                        sftp.cd(path.toString());  // Try to change into the directory
                    } catch (SftpException ex) {
                        sftp.mkdir(path.toString());  // Create the directory if not found
                        sftp.cd(path.toString());    // Change into the newly created directory
                    }
                }
            }
        }
    }
}
