package com.isl.gbm.user.management.service;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * The Interface ReportsService.
 */
public interface ReportsService {


    /**
     * Gets the unsuccessful login atempts.
     *
     * @param fromDate the from date
     * @param toDate   the to date
     * @return the unsuccessful login atempts
     */
    List<LoginReportDto> getUnsuccessfulLoginAtempts(Date fromDate, Date toDate);


    /**
     * Gets the admin activities.
     *
     * @param date the date
     * @return the admin activities
     */
    List<AdminActivityReportDto> getAdminActivities(Date date);


    /**
     * Gets the successful login atempts.
     *
     * @param reportSearchCriteriaDto the report search criteria dto
     * @return the successful login atempts
     */
    List<LoginReportDto> getLoginReports(ReportSearchCriteriaDto reportSearchCriteriaDto);


    /**
     * Gets the multiple device login.
     *
     * @param solId the sol id
     * @param date  the date
     * @return the multiple device login
     */
    List<LoginReportDto> getMultipleDeviceLogin(String solId, Date date);


    /**
     * Get dormant users report.
     *
     * @param from the from
     * @param to   the to
     * @return the list
     */
    List<LoginReportDto> getDormantUsersReport(Date from, Date to);

    public ResponseDataDTO getUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto);

    public ResponseDataDTO activateUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto);


   public ResponseDataDTO getAccessControlMatrixreport(AccessControlMatrixReportDTO accessControlMatrixReportDTO);

    public ResponseDataDTO getPopulationData(PopulationReportDTO populationReportDTO);

    public ResponseDataDTO getRoleRightsReport(RoleRightsReportDTO roleRightsReportDTO);
    public ResponseDataDTO getRoleRightsReportDownload(RoleRightsReportDTO roleRightsReportDTO);


   public ResponseDataDTO getUserActivityDetails(UserActivityReportDTO userActivityReportDTO);

   public ResponseDataDTO getModuleName(UserActivityReportDTO userActivityReportDTO);

    byte[] excelFileGeneratorForUserReport(List<Map<String, Object>> results, UserActivityReportDTO userActivityReportDTO) throws IOException;

    byte[] pdfFileGeneratorForUserReport(List<Map<String, Object>> results, UserActivityReportDTO userActivityReportDTO) throws IOException;

    ResponseDataDTO adminActivityReport(NewAdminActivityReportDTO newAdminActivityReportDTO);
    ResponseDataDTO adminActivityReportDownload(NewAdminActivityReportDTO newAdminActivityReportDTO);

    ResponseDataDTO loginLogoutReport(LoginLogoutReportDTO loginLogoutReportDTO);

    public ResponseDataDTO getPopulationDataDownload(PopulationReportDTO populationReportDTO);
}
