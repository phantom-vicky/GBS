/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.dao;

import com.isl.gbm.common.audit.log.dto.AuditLogReportParams;
import com.isl.gbm.common.audit.log.model.AuditLog;

import java.math.BigDecimal;
import java.util.List;

/**
 * The Interface AuditLogDao.
 */
public interface AuditLogDao {

    /**
     * Delete.
     *
     * @param auditLog the audit log
     */
    public void delete(AuditLog auditLog);

    /**
     * Execute.
     *
     * @param log the log
     */
    public void execute(AuditLog log);

    /**
     * Gets the active audit log details.
     *
     * @param tablePropertyName         the table property name
     * @param tablePkPropertyColumnName the table pk property column name
     * @param tablePkColumnValue        the table pk column value
     * @return the active audit log details
     */
    public AuditLog getActiveAuditLogDetails(String tablePropertyName, String tablePkPropertyColumnName,
                                             BigDecimal tablePkColumnValue);

    /**
     * Gets the audit log details.
     *
     * @param tablePropertyName         the table property name
     * @param tablePkPropertyColumnName the table pk property column name
     * @param tablePkColumnValue        the table pk column value
     * @return the audit log details
     */
    public AuditLog getAuditLogDetails(String tablePropertyName, String tablePkPropertyColumnName,
                                       BigDecimal tablePkColumnValue);

    /**
     * Gets the audit log details by id.
     *
     * @param auditId the audit id
     * @return the audit log details by id
     */
    public AuditLog getAuditLogDetailsById(BigDecimal auditId);

    /**
     * Gets the branch level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the branch level audit report details
     */
    public List<AuditLog> getBranchLevelAuditReportDetails(AuditLogReportParams auditLogReportParams);

    /**
     * Gets the dc parameterization details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the dc parameterization details
     */
    public List<AuditLog> getDcParameterizationDetails(final AuditLogReportParams auditLogReportParams);

    /**
     * Gets the gu level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the gu level audit report details
     */
    public List<AuditLog> getGuLevelAuditReportDetails(AuditLogReportParams auditLogReportParams);

    /**
     * Gets the higher gu level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the higher gu level audit report details
     */
    public List<AuditLog> getHigherGuLevelAuditReportDetails(AuditLogReportParams auditLogReportParams);

    /**
     * Revert.
     *
     * @param auditLog the audit log
     */
    public void revert(AuditLog auditLog);

    /**
     * Save.
     *
     * @param auditLog the audit log
     */
    public void save(AuditLog auditLog);


}
