/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.dao;

import com.isl.gbm.common.audit.log.model.AuditGenMod;

import java.math.BigDecimal;

/**
 * The Interface AuditGenModDao.
 */
public interface AuditGenModDao {

    /**
     * Delete.
     *
     * @param auditGenMod the audit gen mod
     */
    public void delete(AuditGenMod auditGenMod);

    /**
     * Execute.
     *
     * @param auditGenMod the audit gen mod
     */
    public void execute(AuditGenMod auditGenMod);

    /**
     * Gets the audit gen mod details.
     *
     * @param tablePropertyName         the table property name
     * @param tablePkPropertyColumnName the table pk property column name
     * @param tablePkColumnValue        the table pk column value
     * @return the audit gen mod details
     */
    public AuditGenMod getAuditGenModDetails(String tablePropertyName, String tablePkPropertyColumnName,
                                             BigDecimal tablePkColumnValue);

    /**
     * Gets the audit gen mod details by id.
     *
     * @param id the id
     * @return the audit gen mod details by id
     */
    public AuditGenMod getAuditGenModDetailsById(BigDecimal id);

    /**
     * Revert.
     *
     * @param auditGenMod the audit gen mod
     */
    public void revert(AuditGenMod auditGenMod);

    /**
     * Save.
     *
     * @param auditGenMod the audit gen mod
     */
    public void save(AuditGenMod auditGenMod);
}
