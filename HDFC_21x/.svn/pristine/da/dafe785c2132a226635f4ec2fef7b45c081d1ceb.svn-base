package com.isl.gbm.user.management.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.controller.facad.ReportsFacade;
import com.isl.gbm.user.management.dto.*;
import com.isl.gbm.user.management.service.ReportExportService;
import com.isl.gbm.user.management.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * The Class ReportsController.
 */
@RestController
@RequestMapping("/gbmultra/user/report")
public class ReportsController {

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

    @Autowired
    private ReportsFacade reportsFacade;


    @PostMapping("/login/unsuccessful")
    public ResponseEntity<List<LoginReportDto>> getUnsuccessfulLoginAtemptsReport(@RequestBody UserCommonRequestDTO userCommonRequestDTO) {
		/*final List<LoginReportDto> loginReportDtos = this.reportsService.getUnsuccessfulLoginAtempts(new Date(fromDate), new Date(toDate));
		return new ResponseEntity<>(loginReportDtos, HttpStatus.OK);*/
        return reportsFacade.getUnsuccessfulLoginAtempts(userCommonRequestDTO.getFrom(), userCommonRequestDTO.getTo());
    }

    @PostMapping("/login/unsuccessful/export")
    public void exportUnsuccessfulLoginAtemptsReport(@RequestBody UserCommonRequestDTO userCommonRequestDTO, HttpServletResponse response) throws IOException {
        final List<LoginReportDto> loginReportDtos = reportsFacade.getUnsuccessfulLoginAtempts1(userCommonRequestDTO.getFrom(), userCommonRequestDTO.getTo());
        response.setContentType(CONTENT_TYPE_PDF);
        this.reportExportService.exportUnsuccessfulLoginAttemptsReport(loginReportDtos, new Date(userCommonRequestDTO.getFrom()), new Date(userCommonRequestDTO.getTo()), response);
    }

    /**
     * Gets the login report.
     *
     * @param reportSearchCriteriaDto the report search criteria dto
     * @return the login report
     */
    @PostMapping("/loginReport")
    public ResponseEntity<List<LoginReportDto>> getLoginReport(@RequestBody ReportSearchCriteriaDto reportSearchCriteriaDto) {
	/*	final List<LoginReportDto> loginReportDtos = this.reportsService.getLoginReports(reportSearchCriteriaDto);
		return new ResponseEntity<>(loginReportDtos, HttpStatus.OK);*/
        return reportsFacade.getLoginReports(reportSearchCriteriaDto);
    }

    /**
     * Export login report.
     *
     * @param reportSearchCriteriaDto the report search criteria dto
     * @param response                the response
     * @throws IOException       Signals that an I/O exception has occurred.
     */
    @PostMapping("/login/export")
    public void exportLoginReport(@RequestBody ReportSearchCriteriaDto reportSearchCriteriaDto, HttpServletResponse response) throws IOException {
        final List<LoginReportDto> loginReportDtos = this.reportsFacade.getLoginReport(reportSearchCriteriaDto);
        response.setContentType(CONTENT_TYPE_PDF);
        this.reportExportService.exportLoginReport(loginReportDtos, reportSearchCriteriaDto, response);
    }

    @PostMapping("/activities/admin")
    public ResponseEntity<List<AdminActivityReportDto>> getAdminActivitiesReport(@RequestBody UserCommonRequestDTO userCommonRequestDTO) {
        return reportsFacade.getAdminActivities(userCommonRequestDTO.getFrom());
    }

    @PostMapping("/activities/admin/export")
    public void exportAdminActivitiesReport(@RequestBody UserCommonRequestDTO userCommonRequestDTO, HttpServletResponse response) throws IOException {
        final List<AdminActivityReportDto> adminActivityReportDtos = reportsFacade.getAdminActivities(new Date(userCommonRequestDTO.getFrom()));
        response.setContentType(CONTENT_TYPE_PDF);
        this.reportExportService.exportAdminActivitiesReport(adminActivityReportDtos, new Date(userCommonRequestDTO.getFrom()), response);
    }

    @PostMapping("/login/multipledevices")
    public ResponseEntity<List<LoginReportDto>> getMultipleDeviceLogin(@RequestBody UserCommonRequestDTO userCommonRequestDTO) {
        return reportsFacade.getMultipleDeviceLogin(userCommonRequestDTO.getSolId(), new Date(userCommonRequestDTO.getFrom()));
    }

    @PostMapping("/login/multipledevices/export")
    public void exportMultipleDeviceLogin(@RequestBody UserCommonRequestDTO userCommonRequestDTO, HttpServletResponse response) throws IOException {
        final List<LoginReportDto> loginReportDtos = this.reportsFacade.getMultipleDeviceLogins(userCommonRequestDTO.getSolId(), new Date(userCommonRequestDTO.getFrom()));
        response.setContentType(CONTENT_TYPE_PDF);
        this.reportExportService.exportMultipleDeviceLogin(loginReportDtos, userCommonRequestDTO.getSolId(), new Date(userCommonRequestDTO.getFrom()), response);
    }

    @PostMapping("/dormant")

    public ResponseEntity<List<LoginReportDto>> getDormantUsersReport(@RequestBody UserCommonRequestDTO userCommonRequestDTO) {
        //final List<LoginReportDto> loginReportDtos = this.reportsService.getDormantUsersReport(new Date(fromDate), new Date(toDate));
        return reportsFacade.getDormantUsersReport(new Date(userCommonRequestDTO.getFrom()), new Date(userCommonRequestDTO.getTo()));
    }

    @PostMapping("/dormant/export")
    public void exportDormantUsersReport(@RequestBody UserCommonRequestDTO userCommonRequestDTO, HttpServletResponse response) throws IOException {
        final List<LoginReportDto> loginReportDtos = this.reportsFacade.getDormantUsersReports(new Date(userCommonRequestDTO.getFrom()), new Date(userCommonRequestDTO.getTo()));
        response.setContentType(CONTENT_TYPE_PDF);
        this.reportExportService.exportDormantUsersReport(loginReportDtos, new Date(userCommonRequestDTO.getFrom()), new Date(userCommonRequestDTO.getTo()), response);
    }


    @PostMapping(path = "/userReport")
    public ResponseEntity<ResponseDataDTO> getUserReport(@RequestBody final ReportSearchCriteriaDto reportSearchCriteriaDto) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.getUserReport(reportSearchCriteriaDto));
    }
    @PostMapping(path = "/getAccessControlMatrixreport")
    public ResponseEntity<ResponseDataDTO> getAccessControlMatrixreport(@RequestBody AccessControlMatrixReportDTO accessControlMatrixReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.getAccessControlMatrixreport(accessControlMatrixReportDTO));
    }

    @PostMapping(path = "/activateUserReport")
    public ResponseEntity<ResponseDataDTO> activateUserReport(@RequestBody final ReportSearchCriteriaDto reportSearchCriteriaDto) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.activateUserReport(reportSearchCriteriaDto));
    }
    @PostMapping(path = "/getUserActivityDetails")
    public ResponseEntity<ResponseDataDTO> getUserActivityDetails(@RequestBody UserActivityReportDTO userActivityReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.getUserActivityDetails(userActivityReportDTO));
    }

    @PostMapping(path = "/getModuleName")
    public ResponseEntity<ResponseDataDTO> getModuleName(@RequestBody UserActivityReportDTO userActivityReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.getModuleName(userActivityReportDTO));
    }

    @PostMapping(path = "/getPopulationData")
    public ResponseEntity<ResponseDataDTO> getPopulationData(@RequestBody PopulationReportDTO populationReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.getPopulationData(populationReportDTO));
    }


    @PostMapping(path = "/adminActivityReport")
    public ResponseEntity<ResponseDataDTO> adminActivityReport(@RequestBody NewAdminActivityReportDTO newAdminActivityReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.adminActivityReport(newAdminActivityReportDTO));
    }
    @PostMapping(path = "/adminActivityReportDownload")
    public ResponseEntity<ResponseDataDTO> adminActivityReportDownload(@RequestBody NewAdminActivityReportDTO newAdminActivityReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.adminActivityReportDownload(newAdminActivityReportDTO));
    }

    @PostMapping(path = "/loginLogoutReport")
    public ResponseEntity<ResponseDataDTO> loginLogoutReport(@RequestBody LoginLogoutReportDTO loginLogoutReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.loginLogoutReport(loginLogoutReportDTO));
    }


    @PostMapping(path = "/getRoleRightsReport")
    public ResponseEntity<ResponseDataDTO> getRoleRightsReport(@RequestBody RoleRightsReportDTO roleRightsReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.getRoleRightsReport(roleRightsReportDTO));
    }

    @PostMapping(path = "/getRoleRightsReportDownload")
    public ResponseEntity<ResponseDataDTO> getRoleRightsReportDownload(@RequestBody RoleRightsReportDTO roleRightsReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.getRoleRightsReportDownload(roleRightsReportDTO));
    }

    @PostMapping(path = "/getPopulationDataDownload")
    public ResponseEntity<ResponseDataDTO> getPopulationDataDownload(@RequestBody PopulationReportDTO populationReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(reportsFacade.getPopulationDataDownload(populationReportDTO));
    }
}
