package com.isl.gbm.user.management.service;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.dto.AuditReportDTO;
import com.isl.gbm.user.management.dto.ReportOnBranchWiseDeptWiseDTO;
import com.isl.gbm.user.management.dto.ReportsForDCParmaterizationDTO;

public interface AuditLogReportService {

    public ResponseDataDTO getReportsForDCParamertization(ReportsForDCParmaterizationDTO reportsForDCParmaterizationDTO);

    public ResponseDataDTO getLinkName(String moduleId);

    public ResponseDataDTO getReportsAtDCLevelForAnySol(AuditReportDTO auditReportDTO);

    ResponseDataDTO getModuleId();

    ResponseDataDTO getOperations(String moduleId);

    ResponseDataDTO getRecordStatus();

    ResponseDataDTO getBranchId(String moduleId, String screenType, String treasuryCode, String stateCode);

    ResponseDataDTO getStateTaxCode(String moduleId);

    ResponseDataDTO getTreasuryCode(String moduleId);

    public ResponseDataDTO getReportOnBranchWiseDeptWiseUserDetails(ReportOnBranchWiseDeptWiseDTO reportOnBranchWiseDeptWiseDTO);
}
