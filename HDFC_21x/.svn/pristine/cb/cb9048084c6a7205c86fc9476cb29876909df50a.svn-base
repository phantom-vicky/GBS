package com.isl.gbm.user.management.service;

import com.isl.gbm.user.management.dto.AdminActivityReportDto;
import com.isl.gbm.user.management.dto.LoginReportDto;
import com.isl.gbm.user.management.dto.ReportSearchCriteriaDto;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * The Interface ReportExportService.
 */
public interface ReportExportService {

    /**
     * Export unsuccessful login attempts report.
     *
     * @param loginReports the login reports
     * @param fromDate     the from date
     * @param toDate       the to date
     * @param response     the response
     */
    void exportUnsuccessfulLoginAttemptsReport(List<LoginReportDto> loginReports, Date fromDate, Date toDate, HttpServletResponse response);

    /**
     * Generate login report.
     *
     * @param loginReports            the login reports
     * @param reportSearchCriteriaDto the report search criteria dto
     * @param response                the response
     */
    void exportLoginReport(List<LoginReportDto> loginReports, ReportSearchCriteriaDto reportSearchCriteriaDto, HttpServletResponse response);

    /**
     * Generate multiple device login.
     *
     * @param loginReportDtos the login report dtos
     * @param solId           the sol id
     * @param date            the date
     * @param response        the response
     */
    void exportMultipleDeviceLogin(List<LoginReportDto> loginReportDtos, String solId, Date date, HttpServletResponse response);

    /**
     * Generate dormant users report.
     *
     * @param loginReports the login reports
     * @param fromDate     the from date
     * @param toDate       the to date
     * @param response     the response
     */
    void exportDormantUsersReport(List<LoginReportDto> loginReports, Date fromDate, Date toDate, HttpServletResponse response);

    /**
     * Generate admin activities report.
     *
     * @param adminActivityReportDtos the admin activity report dtos
     * @param date                    the date
     * @param response                the response
     */
    void exportAdminActivitiesReport(List<AdminActivityReportDto> adminActivityReportDtos, Date date, HttpServletResponse response);

}
