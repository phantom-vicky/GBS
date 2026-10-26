package com.isl.gbm.user.management.controller;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.APIResponseBuilder;
import com.isl.gbm.user.management.controller.facad.AuditLogReportFacade;
import com.isl.gbm.user.management.dto.AuditReportDTO;
import com.isl.gbm.user.management.dto.ReportOnBranchWiseDeptWiseDTO;
import com.isl.gbm.user.management.dto.ReportsForDCParmaterizationDTO;
import com.isl.gbm.user.management.dto.UserCommonRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gbmultra/users/reports")
public class AuditLogReportOneController {

    @Autowired
    AuditLogReportFacade auditLogReportFacade;

    @PostMapping(path = "/getReportsForDCParamertization")
    public ResponseEntity<ResponseDataDTO> getReportsForDCParamertization(@RequestBody ReportsForDCParmaterizationDTO reportsForDCParmaterizationDTO) {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getReportsForDCParamertization(reportsForDCParmaterizationDTO));
    }

    @PostMapping(path = "/getLinkName")
    public ResponseEntity<ResponseDataDTO> getLinkName(@RequestBody UserCommonRequestDTO userCommonRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getLinkName(userCommonRequestDTO.getModuleId()));
    }

    @PostMapping(path = "/getReportsAtDCLevelForAnySol")
    public ResponseEntity<ResponseDataDTO> getReportsAtDCLevelForAnySol(@RequestBody AuditReportDTO auditReportDTO) {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getReportsAtDCLevelForAnySol(auditReportDTO));
    }

    @PostMapping(path = "audit/getModuleId")
    public ResponseEntity<ResponseDataDTO> getModuleId() {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getModuleId());
    }

    @PostMapping(path = "/getOperations")
    public ResponseEntity<ResponseDataDTO> getOperations(@RequestBody UserCommonRequestDTO userCommonRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getOperations(userCommonRequestDTO.getModuleId()));
    }

    @PostMapping(path = "/audit/getRecordStatus")
    public ResponseEntity<ResponseDataDTO> getRecordStatus() {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getRecordStatus());
    }

    @PostMapping(path = "audit/getBranchId")
    public ResponseEntity<ResponseDataDTO> getBranchId(@RequestBody UserCommonRequestDTO userCommonRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getBranchId(userCommonRequestDTO.getModuleId(), userCommonRequestDTO.getScreenType(), userCommonRequestDTO.getTreasuryCode(), userCommonRequestDTO.getStateCode()));
    }

    @PostMapping(path = "audit/getStateTaxCode")
    public ResponseEntity<ResponseDataDTO> getStateTaxCode(@RequestBody UserCommonRequestDTO userCommonRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getStateTaxCode(userCommonRequestDTO.getModuleId()));
    }

    @PostMapping(path = "audit/getTreasuryCode")
    public ResponseEntity<ResponseDataDTO> getTreasuryCode(@RequestBody UserCommonRequestDTO userCommonRequestDTO) {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getTreasuryCode(userCommonRequestDTO.getModuleId()));
    }

    @PostMapping(path = "/getReportOnBranchWiseDeptWiseUserDetails")
    public ResponseEntity<ResponseDataDTO> getReportOnBranchWiseDeptWiseUserDetails(@RequestBody ReportOnBranchWiseDeptWiseDTO reportOnBranchWiseDeptWiseDTO) {
        return APIResponseBuilder.buildResponseFromDto(auditLogReportFacade.getReportOnBranchWiseDeptWiseUserDetails(reportOnBranchWiseDeptWiseDTO));
    }

}
