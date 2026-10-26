package com.isl.gbm.user.management.service.impl;

import com.isl.gbm.user.management.dto.AdminActivityReportDto;
import com.isl.gbm.user.management.dto.LoginReportDto;
import com.isl.gbm.user.management.dto.ReportSearchCriteriaDto;
import com.isl.gbm.user.management.manager.ReportExportBoxableManager;
import com.isl.gbm.user.management.service.ReportExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


/**
 * The Class ReportExportServiceBoxableImpl.
 */
@Service
@Transactional
public class ReportExportServiceBoxableImpl implements ReportExportService {

    /**
     * The report export manager.
     */
    @Autowired
    private ReportExportBoxableManager reportExportManager;


    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.ReportExportService#exportUnsuccessfulLoginAtemptsReport(java.util.List, java.util.Date, java.util.Date, jakarta.servlet.http.HttpServletResponse)
     */
    @Override
    public void exportUnsuccessfulLoginAttemptsReport(List<LoginReportDto> loginReports, Date fromDate, Date toDate, HttpServletResponse response) {
        this.reportExportManager.exportUnsuccessfulLoginAtemptsReport(loginReports, fromDate, toDate, response);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.ReportExportService#generateLoginReport(java.util.List, com.isl.gbm.user.management.dto.ReportSearchCriteriaDto, jakarta.servlet.http.HttpServletResponse)
     */
    @Override
    public void exportLoginReport(List<LoginReportDto> loginReports, ReportSearchCriteriaDto reportSearchCriteriaDto, HttpServletResponse response) {
        this.reportExportManager.exportLoginReport(loginReports, reportSearchCriteriaDto, response);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.ReportExportService#generateMultipleDeviceLogin(java.util.List, java.lang.String, java.util.Date, jakarta.servlet.http.HttpServletResponse)
     */
    @Override
    public void exportMultipleDeviceLogin(List<LoginReportDto> loginReportDtos, String solId, Date date, HttpServletResponse response) {
        this.reportExportManager.exportMultipleDeviceLogin(loginReportDtos, solId, date, response);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.ReportExportService#generateDormantUsersReport(java.util.List, java.util.Date, java.util.Date, jakarta.servlet.http.HttpServletResponse)
     */
    @Override
    public void exportDormantUsersReport(List<LoginReportDto> loginReports, Date fromDate, Date toDate, HttpServletResponse response) {
        this.reportExportManager.exportDormantUsersReport(loginReports, fromDate, toDate, response);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.user.management.service.ReportExportService#generateAdminActivitiesReport(java.util.List, java.util.Date, jakarta.servlet.http.HttpServletResponse)
     */
    @Override
    public void exportAdminActivitiesReport(List<AdminActivityReportDto> adminActivityReportDtos, Date date, HttpServletResponse response) {
        this.reportExportManager.exportAdminActivitiesReport(adminActivityReportDtos, date, response);
    }

}
