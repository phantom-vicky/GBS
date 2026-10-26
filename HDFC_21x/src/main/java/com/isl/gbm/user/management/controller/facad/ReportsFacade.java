package com.isl.gbm.user.management.controller.facad;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

public interface ReportsFacade {
     ResponseEntity<List<LoginReportDto>> getUnsuccessfulLoginAtempts(Long fromDate, Long toDate);

     List<LoginReportDto> getUnsuccessfulLoginAtempts1(Long fromDate, Long toDate);

     ResponseEntity<List<LoginReportDto>> getLoginReports(ReportSearchCriteriaDto reportSearchCriteriaDto);

     List<LoginReportDto> getLoginReport(ReportSearchCriteriaDto reportSearchCriteriaDto);

    public ResponseEntity<List<AdminActivityReportDto>> getAdminActivities(Long date);

    public List<AdminActivityReportDto> getAdminActivities(Date date);

    ResponseEntity<List<LoginReportDto>> getMultipleDeviceLogin(String solId, Date date);

    public List<LoginReportDto> getMultipleDeviceLogins(String solId, Date date);

    public ResponseEntity<List<LoginReportDto>> getDormantUsersReport(Date from, Date to);

    public List<LoginReportDto> getDormantUsersReports(Date from, Date to);

    public ResponseDataDTO getUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto);

    public ResponseDataDTO activateUserReport(ReportSearchCriteriaDto reportSearchCriteriaDto);

   public ResponseDataDTO getAccessControlMatrixreport(AccessControlMatrixReportDTO accessControlMatrixReportDTO);

   public ResponseDataDTO getUserActivityDetails(UserActivityReportDTO userActivityReportDTO);

   public ResponseDataDTO getModuleName(UserActivityReportDTO userActivityReportDTO);

     ResponseDataDTO getPopulationData(PopulationReportDTO populationReportDTO);

    ResponseDataDTO adminActivityReport(NewAdminActivityReportDTO newAdminActivityReportDTO);
    ResponseDataDTO adminActivityReportDownload(NewAdminActivityReportDTO newAdminActivityReportDTO);

    ResponseDataDTO getRoleRightsReport(RoleRightsReportDTO roleRightsReportDTO);
     ResponseDataDTO getRoleRightsReportDownload(RoleRightsReportDTO roleRightsReportDTO);


    ResponseDataDTO loginLogoutReport(LoginLogoutReportDTO loginLogoutReportDTO);
    public ResponseDataDTO getPopulationDataDownload(PopulationReportDTO populationReportDTO);
}
