/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.facade;

import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;
import com.isl.gbm.common.audit.log.common.AuditLogRecordType;
import com.isl.gbm.common.audit.log.common.OperationModes;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.dto.AuditLogReportDto;
import com.isl.gbm.common.audit.log.dto.AuditLogReportParams;
import com.isl.gbm.common.audit.log.service.AuditLogReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Class AuditLogReportFacade.
 */
@Component
public class AuditLogReportFacade {

    /**
     * The audit log report service.
     */
    @Autowired
    private AuditLogReportService auditLogReportService;

    /**
     * Gets the audit log record types.
     *
     * @return the audit log record types
     */
    public List<AuditLogRecordType> getAuditLogRecordTypes() {
        return this.auditLogReportService.getAuditLogRecordTypes();
    }

    /**
     * Gets the branch level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the branch level audit report details
     */
    public List<AuditLogReportDto> getBranchLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        return this.auditLogReportService.getBranchLevelAuditReportDetails(auditLogReportParams);
    }

    /**
     * Gets the dc parameterization details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the dc parameterization details
     */
    public List<AuditLogDto> getDcParameterizationDetails(final AuditLogReportParams auditLogReportParams) {
        return this.auditLogReportService.getDcParameterizationDetails(auditLogReportParams);
    }

    /**
     * Gets the gu level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the gu level audit report details
     */
    public List<AuditLogDto> getGuLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        return this.auditLogReportService.getGuLevelAuditReportDetails(auditLogReportParams);
    }

    /**
     * Gets the higher branch pop sp sol ids.
     *
     * @param moduleDisplayName the module display name
     * @return the higher branch pop sp sol ids
     */
    public List<SolDetailsIntegrationDto> getHigherBranchPopSpSolIds(final String moduleDisplayName) {
        return this.auditLogReportService.getHigherBranchPopSpSolIds(moduleDisplayName);
    }

    /**
     * Gets the higher gu level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the higher gu level audit report details
     */
    public List<AuditLogReportDto> getHigherGuLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        return this.auditLogReportService.getHigherGuLevelAuditReportDetails(auditLogReportParams);
    }

    /**
     * Gets the operation modes.
     *
     * @return the operation modes
     */
    public List<OperationModes> getOperationModes() {
        return this.auditLogReportService.getOperationModes();
    }

    /**
     * Gets the pop sp sol ids.
     *
     * @param moduleDisplayName the module display name
     * @return the pop sp sol ids
     */
    public List<SolDetailsIntegrationDto> getPopSpSolIds(final String moduleDisplayName) {
        return this.auditLogReportService.getPopSpSolIds(moduleDisplayName);
    }

    /**
     * Sets the audit log report service.
     *
     * @param auditLogReportService the new audit log report service
     */
    public void setAuditLogReportService(final AuditLogReportService auditLogReportService) {
        this.auditLogReportService = auditLogReportService;
    }

}
