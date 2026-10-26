/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.service;

import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;
import com.isl.gbm.common.audit.log.common.AuditLogRecordType;
import com.isl.gbm.common.audit.log.common.OperationModes;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.dto.AuditLogReportDto;
import com.isl.gbm.common.audit.log.dto.AuditLogReportParams;

import java.util.List;

/**
 * The Interface AuditLogReportService.
 */
public interface AuditLogReportService {

    /**
     * Gets the audit log record types.
     *
     * @return the audit log record types
     */
    public List<AuditLogRecordType> getAuditLogRecordTypes();

    /**
     * Gets the branch level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the branch level audit report details
     */
    public List<AuditLogReportDto> getBranchLevelAuditReportDetails(AuditLogReportParams auditLogReportParams);

    /**
     * Gets the dc parameterization details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the dc parameterization details
     */
    public List<AuditLogDto> getDcParameterizationDetails(final AuditLogReportParams auditLogReportParams);

    /**
     * Gets the gu level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the gu level audit report details
     */
    public List<AuditLogDto> getGuLevelAuditReportDetails(AuditLogReportParams auditLogReportParams);

    /**
     * Gets the higher branch pop sp sol ids.
     *
     * @param moduleDisplayName the module display name
     * @return the higher branch pop sp sol ids
     */
    public List<SolDetailsIntegrationDto> getHigherBranchPopSpSolIds(String moduleDisplayName);

    /**
     * Gets the higher gu level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the higher gu level audit report details
     */
    public List<AuditLogReportDto> getHigherGuLevelAuditReportDetails(AuditLogReportParams auditLogReportParams);

    /**
     * Gets the operation modes.
     *
     * @return the operation modes
     */
    public List<OperationModes> getOperationModes();

    /**
     * Gets the pop sp sol ids.
     *
     * @param moduleDisplayName the module display name
     * @return the pop sp sol ids
     */
    public List<SolDetailsIntegrationDto> getPopSpSolIds(String moduleDisplayName);

}
