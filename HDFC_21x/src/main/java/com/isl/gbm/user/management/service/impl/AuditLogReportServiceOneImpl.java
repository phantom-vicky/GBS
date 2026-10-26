package com.isl.gbm.user.management.service.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dao.AuditLogReportDAO;
import com.isl.gbm.user.management.dto.AuditReportDTO;
import com.isl.gbm.user.management.dto.ReportOnBranchWiseDeptWiseDTO;
import com.isl.gbm.user.management.dto.ReportsForDCParmaterizationDTO;
import com.isl.gbm.user.management.service.AuditLogReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuditLogReportServiceOneImpl implements AuditLogReportService {

    @Autowired
    AuditLogReportDAO auditLogReportDAO;

    @Override
    public ResponseDataDTO getReportsForDCParamertization(ReportsForDCParmaterizationDTO reportsForDCParmaterizationDTO) {
        return auditLogReportDAO.getReportsForDCParamertization(reportsForDCParmaterizationDTO);
    }

    @Override
    public ResponseDataDTO getLinkName(String moduleId) {
        return auditLogReportDAO.getLinkName(moduleId);
    }

    @Override
    public ResponseDataDTO getReportsAtDCLevelForAnySol(AuditReportDTO auditReportDTO) {
        return this.auditLogReportDAO.getReportsAtDCLevelForAnySol(auditReportDTO);
    }

    @Override
    public ResponseDataDTO getModuleId() {
        return auditLogReportDAO.getModuleId();
    }

    @Override
    public ResponseDataDTO getOperations(String moduleId) {
        return auditLogReportDAO.getOperations(moduleId);
    }

    @Override
    public ResponseDataDTO getRecordStatus() {
        return auditLogReportDAO.getRecordStatus();
    }

    @Override
    public ResponseDataDTO getBranchId(String moduleId, String screenType, String treasuryCode, String stateCode) {
        return auditLogReportDAO.getBranchId(moduleId, screenType, treasuryCode, stateCode);
    }

    @Override
    public ResponseDataDTO getStateTaxCode(String moduleId) {
        return auditLogReportDAO.getStateTaxCode(moduleId);
    }

    @Override
    public ResponseDataDTO getTreasuryCode(String moduleId) {
        return auditLogReportDAO.getTreasuryCode(moduleId);
    }

    @Override
    public ResponseDataDTO getReportOnBranchWiseDeptWiseUserDetails(ReportOnBranchWiseDeptWiseDTO reportOnBranchWiseDeptWiseDTO) {
        return auditLogReportDAO.getReportOnBranchWiseDeptWiseUserDetails(reportOnBranchWiseDeptWiseDTO);
    }
}
