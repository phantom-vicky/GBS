package com.isl.gbm.user.management.controller.facad.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.constants.PermissionConstants;
import com.isl.gbm.user.management.controller.facad.ReportsFacade;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.service.ReportExportService;
import com.isl.gbm.user.management.service.ReportsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ReportsFacadeImpl implements ReportsFacade {
    /**
     * The Constant CONTENT_TYPE.
     */
    private static final String CONTENT_TYPE_PDF = "application/pdf";

    /**
     * The reports service.
     */
    @Autowired
    private ReportsService reportsService;

    /**
     * The report export service.
     */
    @Autowired
    @Qualifier("ReportExportServiceImpl")
    private ReportExportService reportExportService;

    @Override
    @RequiresPermissions("USER_MGMT:REPORTS_ON_UNSUCESSFUL_LOGIN_ATTEMPTS:REPORT")
    public ResponseEntity<List<LoginReportDto>> getUnsuccessfulLoginAtempts(Long fromDate, Long toDate) {
        final List<LoginReportDto> loginReportDtos = this.reportsService.getUnsuccessfulLoginAtempts(new Date(fromDate), new Date(toDate));
        return new ResponseEntity<>(loginReportDtos, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_REPORTS_REPORT)
    public List<LoginReportDto> getUnsuccessfulLoginAtempts1(Long fromDate, Long toDate) {
        final List<LoginReportDto> loginReportDtos = this.reportsService.getUnsuccessfulLoginAtempts(new Date(fromDate), new Date(toDate));
        return loginReportDtos;
    }

    @Override
    @RequiresPermissions("USER_MGMT:REPORT_ON_USER_SIGN_ON_SIGN_OFF:REPORT")
    public ResponseEntity<List<LoginReportDto>> getLoginReports(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        final List<LoginReportDto> loginReportDtos = this.reportsService.getLoginReports(reportSearchCriteriaDto);
        return new ResponseEntity<>(loginReportDtos, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_REPORTS_REPORT)
    public List<LoginReportDto> getLoginReport(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        final List<LoginReportDto> loginReportDtos = this.reportsService.getLoginReports(reportSearchCriteriaDto);
        return loginReportDtos;
    }

    @Override
    @RequiresPermissions("USER_MGMT:REPORT_ON_LIST_OF_USER_RELATED_ACTIVITIES_CARRIED_OUT_ON_A_DAY:REPORT")
    public ResponseEntity<List<AdminActivityReportDto>> getAdminActivities(Long date) {
        final List<AdminActivityReportDto> adminActivityReportDtos = this.reportsService.getAdminActivities(new Date(date));
        return new ResponseEntity<>(adminActivityReportDtos, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_REPORTS_REPORT)
    public List<AdminActivityReportDto> getAdminActivities(Date date) {
        return this.reportsService.getAdminActivities(date);
    }

    @Override
    @RequiresPermissions("USER_MGMT:REPORTS_ON_USER_LOGGED_IN_MORE_THAN_ONE_IP_ADDRESS_DURING_THE_DAY:REPORT")
    public ResponseEntity<List<LoginReportDto>> getMultipleDeviceLogin(String solId, Date date) {
        final List<LoginReportDto> loginReportDtos = this.reportsService.getMultipleDeviceLogin(solId, date);
        return new ResponseEntity<>(loginReportDtos, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_REPORTS_REPORT)
    public List<LoginReportDto> getMultipleDeviceLogins(String solId, Date date) {
        return this.reportsService.getMultipleDeviceLogin(solId, date);
    }

    @Override
    @RequiresPermissions("USER_MGMT:REPORT_ON_DORMANT_USER_IDS:REPORT")
    public ResponseEntity<List<LoginReportDto>> getDormantUsersReport(Date from, Date to) {
        final List<LoginReportDto> loginReportDtos = this.reportsService.getDormantUsersReport(from, to);
        return new ResponseEntity<>(loginReportDtos, HttpStatus.OK);
    }

    @Override
    @RequiresPermissions(PermissionConstants.USER_MGMT_REPORTS_REPORT)
    public List<LoginReportDto> getDormantUsersReports(Date from, Date to) {
        return this.reportsService.getDormantUsersReport(from, to);
    }


    @Override
    @RequiresPermissions("USER_MGMT:REPORT_ON_USER:REPORT")
    public ResponseDataDTO getUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        return reportsService.getUserReport(reportSearchCriteriaDto);
    }

    @Override
    @RequiresPermissions("ADMIN:ACTIVATE_USER")
    public ResponseDataDTO activateUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto) {
        return reportsService.activateUserReport(reportSearchCriteriaDto);
    }

    @Override
    @RequiresPermissions("GBM_ADMIN_REPORTS:ACCESS_CONTROL_MATRIX_REPORT:REPORT")
    public ResponseDataDTO getAccessControlMatrixreport(AccessControlMatrixReportDTO accessControlMatrixReportDTO) {
        return reportsService.getAccessControlMatrixreport(accessControlMatrixReportDTO);
    }

    @Override
    @RequiresPermissions("USER_MGMT:REPORT_ON_POPULATION:REPORT")
    public ResponseDataDTO getPopulationData(PopulationReportDTO populationReportDTO) {
        return reportsService.getPopulationData(populationReportDTO);
    }

    @Override
    @RequiresPermissions("USER_MGMT:ADMIN_ACTIVITY:REPORT")
    public ResponseDataDTO adminActivityReport(NewAdminActivityReportDTO newAdminActivityReportDTO) {
        return reportsService.adminActivityReport(newAdminActivityReportDTO);
    }
    @Override
    @RequiresPermissions("USER_MGMT:ADMIN_ACTIVITY:REPORT")
    public ResponseDataDTO adminActivityReportDownload(NewAdminActivityReportDTO newAdminActivityReportDTO) {
        return reportsService.adminActivityReportDownload(newAdminActivityReportDTO);
    }

    @Override
    @RequiresPermissions("USER_MGMT:REPORT_ON_ROLE_RIGHTS:REPORT")
    public ResponseDataDTO getRoleRightsReport(RoleRightsReportDTO roleRightsReportDTO) {
        return reportsService.getRoleRightsReport(roleRightsReportDTO);
    }
    @Override
    @RequiresPermissions("USER_MGMT:REPORT_ON_ROLE_RIGHTS:REPORT")
    public ResponseDataDTO getRoleRightsReportDownload(RoleRightsReportDTO roleRightsReportDTO) {
        return reportsService.getRoleRightsReportDownload(roleRightsReportDTO);
    }
    @Override
    @RequiresPermissions("USER_MGMT:LOGIN_LOGOUT:REPORT")
    public ResponseDataDTO loginLogoutReport(LoginLogoutReportDTO loginLogoutReportDTO) {
        return reportsService.loginLogoutReport(loginLogoutReportDTO);
    }


    @Override
    public ResponseDataDTO getUserActivityDetails(UserActivityReportDTO userActivityReportDTO) {
        return reportsService.getUserActivityDetails(userActivityReportDTO);
    }




    @Override
    public ResponseDataDTO getModuleName(UserActivityReportDTO userActivityReportDTO) {
        return reportsService.getModuleName(userActivityReportDTO);
    }

    public ResponseDataDTO getPopulationDataDownload(PopulationReportDTO populationReportDTO){
        return reportsService.getPopulationDataDownload(populationReportDTO);
    }
}
