/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.logger;

import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.model.ModifiedDataDetails;
import com.isl.gbm.user.management.constants.FeatureNames;
import com.isl.gbm.user.management.constants.ModuleDisplayNames;

import java.util.Date;
import java.util.List;

/**
 * The Interface AuditLogger.
 */
public interface AuditLogger {

    /**
     * Creates the audit log entries.
     *
     * @param entity            the entity
     * @param currentDate       the current date
     * @param user              the user
     * @param columnDisplayName the column display name
     * @param moduleDisplayName the module display name
     * @param featureName       the feature name
     */
    public void createAuditLogEntries(final Object entity, final Date currentDate, final String user,
                                      final String columnDisplayName, final ModuleDisplayNames moduleDisplayName, FeatureNames featureName);

    /**
     * Delete audit log records.
     *
     * @param entity      the entity
     * @param user        the user
     * @param currentDate the current date
     */
    public void deleteAuditLogRecords(final Object entity, final String user, final Date currentDate);

    /**
     * Delete gen mod records.
     *
     * @param entity      the entity
     * @param user        the user
     * @param currentDate the current date
     */
    public void deleteGenModRecords(final Object entity, final String user, final Date currentDate);

    /**
     * Gets the active audit log details.
     *
     * @param entity the entity
     * @return the active audit log details
     */
    public AuditLogDto getActiveAuditLogDetails(final Object entity);

    /**
     * Gets the modified data details.
     *
     * @param oldDataObject     the old data object
     * @param newDataObject     the new data object
     * @param columnDisplayName the column display name
     * @return the modified data details
     */
    public ModifiedDataDetails getModifiedDataDetails(final Object oldDataObject, final Object newDataObject,
                                                      final String columnDisplayName);

    /**
     * Revert audit log records.
     *
     * @param entity       the entity
     * @param user         the user
     * @param currentDate  the current date
     * @param verifiedFlag the verified flag
     */
    public void revertAuditLogRecords(final Object entity, final String user, final Date currentDate,
                                      final Boolean verifiedFlag);

    /**
     * Revert gen mod records.
     *
     * @param entity       the entity
     * @param user         the user
     * @param currentDate  the current date
     * @param verifiedFlag the verified flag
     */
    public void revertGenModRecords(final Object entity, final String user, final Date currentDate,
                                    final Boolean verifiedFlag);

    /**
     * Save or update audit log.
     *
     * @param auditLoggerParams the audit logger params
     */
    public void saveOrUpdateAuditLog(final AuditLoggerParams auditLoggerParams);

    /**
     * Save or update gen mod records.
     *
     * @param auditLoggerParams the audit logger params
     */
    public void saveOrUpdateGenModRecords(final AuditLoggerParams auditLoggerParams);

    /**
     * Verify audit log.
     *
     * @param user        the user
     * @param currentDate the current date
     * @param entityList  the entity list
     * @param flag        the flag
     * @return the int
     */
    public int verifyAuditLog(final String user, final Date currentDate, final List<? extends Object> entityList,
                              final Boolean flag);

    /**
     * Verify audit log.
     *
     * @param user        the user
     * @param currentDate the current date
     * @param entity      the entity
     * @return the int
     */
    public int verifyAuditLog(final String user, final Date currentDate, final Object entity);

    /**
     * Verify gen mod records.
     *
     * @param user        the user
     * @param currentDate the current date
     * @param entityList  the entity list
     * @param flag        the flag
     */
    public void verifyGenModRecords(final String user, final Date currentDate, final List<? extends Object> entityList,
                                    final Boolean flag);

    /**
     * Verify gen mod records.
     *
     * @param user        the user
     * @param currentDate the current date
     * @param entity      the entity
     */
    public void verifyGenModRecords(final String user, final Date currentDate, final Object entity);

}
