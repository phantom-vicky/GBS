package com.isl.gbm.user.management.controller.facad.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.controller.facad.AuditLogReportFacade;
import com.isl.gbm.user.management.dto.AuditReportDTO;
import com.isl.gbm.user.management.dto.ReportOnBranchWiseDeptWiseDTO;
import com.isl.gbm.user.management.dto.ReportsForDCParmaterizationDTO;
import com.isl.gbm.user.management.service.AuditLogReportService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuditLogReportFacadeImpl implements AuditLogReportFacade {

    @Autowired
    AuditLogReportService auditLogReportService;

    @Override
    public ResponseDataDTO getReportsForDCParamertization(ReportsForDCParmaterizationDTO reportsForDCParmaterizationDTO) {
        return auditLogReportService.getReportsForDCParamertization(reportsForDCParmaterizationDTO);

    }

    @Override
    public ResponseDataDTO getLinkName(String moduleId) {
        return auditLogReportService.getLinkName(moduleId);
    }


    @Override
    @RequiresPermissions(logical = Logical.OR,value = {"AUDIT_LOG_REPORT:AUDIT_REPORT_AT_DC_LEVEL_FOR_ANY_SOL:ENQUIRY","AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_HIGHER_LEVEL_BRANCHES:ENQUIRY","AUDIT_LOG_REPORT:AUDIT_REPORT_AT_GU_FOR_BRANCH_LEVEL:ENQUIRY","AUDIT_LOG_REPORT:AUDIT_REPORT_DC_PARAMETERIZATIONS:ENQUIRY"})
    public ResponseDataDTO getReportsAtDCLevelForAnySol(AuditReportDTO auditReportDTO) {
        return this.auditLogReportService.getReportsAtDCLevelForAnySol(auditReportDTO);
    }

    @Override
    public ResponseDataDTO getModuleId() {
        return auditLogReportService.getModuleId();
    }

    @Override
    public ResponseDataDTO getOperations(String moduleId) {
        return auditLogReportService.getOperations(moduleId);
    }

    @Override
    public ResponseDataDTO getRecordStatus() {
        return auditLogReportService.getRecordStatus();
    }

    @Override
    public ResponseDataDTO getBranchId(String moduleId, String screenType, String treasuryCode, String stateCode) {
        return auditLogReportService.getBranchId(moduleId, screenType, treasuryCode, stateCode);
    }

    @Override
    public ResponseDataDTO getStateTaxCode(String moduleId) {
        return auditLogReportService.getStateTaxCode(moduleId);
    }

    @Override
    public ResponseDataDTO getTreasuryCode(String moduleId) {
        return auditLogReportService.getTreasuryCode(moduleId);
    }

    @Override
    @RequiresPermissions("USER_MGMT:REPORTS:REPORT")
    public ResponseDataDTO getReportOnBranchWiseDeptWiseUserDetails(ReportOnBranchWiseDeptWiseDTO reportOnBranchWiseDeptWiseDTO) {
        return auditLogReportService.getReportOnBranchWiseDeptWiseUserDetails(reportOnBranchWiseDeptWiseDTO);
    }
}
