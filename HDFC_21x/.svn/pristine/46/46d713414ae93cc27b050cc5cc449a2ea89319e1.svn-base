/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.logger;

import com.isl.gbm.common.ObjectMapperUtils;
import com.isl.gbm.common.audit.log.common.AuditLogConstants;
import com.isl.gbm.common.audit.log.common.OperationModes;
import com.isl.gbm.common.audit.log.dao.AuditGenModDao;
import com.isl.gbm.common.audit.log.dao.AuditLogDao;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.dto.ColumnDetailsDto;
import com.isl.gbm.common.audit.log.exception.AuditLogException;
import com.isl.gbm.common.audit.log.exception.AuditLogVerifyException;
import com.isl.gbm.common.audit.log.model.AuditGenMod;
import com.isl.gbm.common.audit.log.model.AuditLog;
import com.isl.gbm.common.audit.log.model.ColumnDefinitionDetails;
import com.isl.gbm.common.audit.log.model.ColumnDetails;
import com.isl.gbm.common.audit.log.model.ModifiedDataDetails;
import com.isl.gbm.common.audit.log.model.PrimaryKeyDetails;
import com.isl.gbm.common.audit.log.model.TableDefinitionDetails;
import com.isl.gbm.user.management.constants.FeatureNames;
import com.isl.gbm.user.management.constants.ModuleDisplayNames;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The Class AuditLoggerImpl.
 */
@Component
public class AuditLoggerImpl implements AuditLogger {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditLoggerImpl.class);

    /**
     * The audit log dao.
     */
    @Autowired
    private AuditLogDao auditLogDao;

    /**
     * The audit gen mod dao.
     */
    @Autowired
    private AuditGenModDao auditGenModDao;

    /**
     * Adds the modified fields data.
     *
     * @param dataDetails the data details
     * @param oValue      the o value
     * @param nValue      the n value
     * @param details     the details
     */
    private void addModifiedFieldsData(final ModifiedDataDetails dataDetails, final Object oValue, final Object nValue,
                                       final ColumnDetails details) {
        if ((oValue instanceof String) || (nValue instanceof String)) {
            if (BooleanUtils.isFalse(StringUtils.equalsIgnoreCase((String) oValue, (String) nValue))) {
                dataDetails.getModifiedColumnDetailList().add(details);
            }
        } else {
            if (ObjectUtils.notEqual(oValue, nValue)) {
                dataDetails.getModifiedColumnDetailList().add(details);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.logger.AuditLogger#createAuditLogEntries(java.lang.
     * Object, java.util.Date, java.lang.String, java.lang.String,
     * com.isl.gbm.user.management.constants.ModuleDisplayNames,
     * com.isl.gbm.user.management.constants.FeatureNames)
     */
    @Override
    public void createAuditLogEntries(final Object entity, final Date currentDate, final String user,
                                      final String columnDisplayName, final ModuleDisplayNames moduleDisplayName,
                                      final FeatureNames featureName) {
        final TableDefinitionDetails definitionDetails = this.getTableDefinitionDetails(entity);
        final PrimaryKeyDetails dto = this.getPrimaryKeyDetails(entity);
        final AuditLog log = new AuditLog();
        final String currentSolId = UserAccountUtils.getCurrentSolId();
        log.setEntererDate(currentDate);
        log.setEntererId(user);
        log.setEntererSolId(currentSolId);
        log.setRecordUpdatedSolId(currentSolId);

        final List<ColumnDetails> detailList = new ArrayList<>();
        final ColumnDetails details = this.getDefaultColumnDetails(definitionDetails, AuditLogConstants.RECORD_ADDED,
                columnDisplayName);
        detailList.add(details);

        log.setModifiedFieldsData(detailList);
        log.setModuleName(moduleDisplayName.name());
        log.setOperationMode(OperationModes.ADD.getOperationMode());
        log.setRemarks(AuditLogConstants.RECORD_ADDED);
        log.setDeleted(Boolean.FALSE);
        log.setFeatureDisplayName(featureName);
        if (null != definitionDetails) {
            log.setTableName(definitionDetails.getTableName());
            log.setTablePropertyName(definitionDetails.getTablePropertyName());
        }
        if (null != dto) {
            log.setTablePkPropertyName(dto.getPkPropertyName());
            log.setTablePkColumnName(dto.getPkColumnName());
            log.setTablePkColumnValue(dto.getValue());
        }

        this.auditLogDao.save(log);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.logger.AuditLogger#deleteAuditLogRecords(java.lang.
     * Object, java.lang.String, java.util.Date)
     */
    @Override
    public void deleteAuditLogRecords(final Object entity, final String user, final Date currentDate) {
        final TableDefinitionDetails tableDefinitionDetails = this.getTableDefinitionDetails(entity);
        final PrimaryKeyDetails pkDetails = this.getPrimaryKeyDetails(entity);
        if ((null == tableDefinitionDetails) || (null == pkDetails)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(
                        "isl-AUDIT-LOG-cf9ffaef-7dfb-4c8a-b687-06b0cdd9ab4b not able to extract table definition details");
            }
            return;
        }

        final AuditLog log = this.auditLogDao.getActiveAuditLogDetails(tableDefinitionDetails.getTablePropertyName(),
                pkDetails.getPkPropertyName(), pkDetails.getValue());

        // already existing records
        if (null != log) {
            this.validateAuditLogDeleteUser(user, log);
            final String currentSolId = UserAccountUtils.getCurrentSolId();
            log.setDeleterSolId(currentSolId);
            log.setRecordUpdatedSolId(currentSolId);
            log.setDeletedBy(user);
            log.setDeletedDate(currentDate);
            this.auditLogDao.delete(log);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.logger.AuditLogger#deleteGenModRecords(java.lang.
     * Object, java.lang.String, java.util.Date)
     */
    @Override
    public void deleteGenModRecords(final Object entity, final String user, final Date currentDate) {
        // insert/update data in audit table and gen mod table
        final TableDefinitionDetails tableDefinitionDetails = this.getTableDefinitionDetails(entity);
        final PrimaryKeyDetails pkDetails = this.getPrimaryKeyDetails(entity);
        if ((null == tableDefinitionDetails) || (null == pkDetails)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(
                        "isl-AUDIT-LOG-007e8f65-b1ca-4c45-a97d-b8f0fabe62ef not able to extract table definition details or pk details");
            }
            return;
        }

        final AuditGenMod genMod = this.auditGenModDao.getAuditGenModDetails(
                tableDefinitionDetails.getTablePropertyName(), pkDetails.getPkPropertyName(), pkDetails.getValue());

        if (null != genMod) {
            this.validateGenModDeleteUser(user, genMod.getLastModifiedBy());
            genMod.setDeletedBy(user);
            genMod.setDeletedDate(currentDate);
            final String currentSolId = UserAccountUtils.getCurrentSolId();
            genMod.setDeleterSolId(currentSolId);
            genMod.setRecordUpdatedSolId(currentSolId);
            // updating gen_mod table
            this.auditGenModDao.delete(genMod);
        }
    }

    /**
     * Extract primary key details.
     *
     * @param column the column
     * @param entity the entity
     * @return the primary key details
     * @throws IllegalArgumentException the illegal argument exception
     * @throws IllegalAccessException   the illegal access exception
     */
    private PrimaryKeyDetails extractPrimaryKeyDetails(final Field column, final Object entity)
            throws IllegalArgumentException, IllegalAccessException {
        final PrimaryKeyDetails dto = new PrimaryKeyDetails();
        if (null != column) {
            final Object value = column.get(entity);
            dto.setPkPropertyName(column.getName());
            dto.setPkColumnName(column.getAnnotation(Column.class).name());

            if (value instanceof Integer) {
                dto.setValue(new BigDecimal((Integer) value));
            } else {
                dto.setValue((BigDecimal) value);
            }
        }
        return dto;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.logger.AuditLogger#getActiveAuditLogDetails(java.lang.
     * Object)
     */
    @Override
    public AuditLogDto getActiveAuditLogDetails(final Object entity) {

        final TableDefinitionDetails definitionDetails = this.getTableDefinitionDetails(entity);
        final PrimaryKeyDetails pkDetails = this.getPrimaryKeyDetails(entity);

        AuditLogDto dto = null;

        if ((null == definitionDetails) || (null == pkDetails)) {
            return null;
        }

        final AuditLog log = this.auditLogDao.getActiveAuditLogDetails(definitionDetails.getTablePropertyName(),
                pkDetails.getPkPropertyName(), pkDetails.getValue());

        if (null != log) {
            dto = this.mapModelToDto(log);
            for (final ColumnDetails columDetails : log.getModifiedFieldsData()) {
                if (this.isInvalidColumnDetails(columDetails)) {
                    dto = null;
                    break;
                }
            }
        }

        return dto;

    }

    /**
     * Gets the column definition details.
     *
     * @param column the column
     * @return the column definition details
     */
    private ColumnDefinitionDetails getColumnDefinitionDetails(final Field column) {
        final ColumnDefinitionDetails details = new ColumnDefinitionDetails();
        details.setColumnName(column.getAnnotation(Column.class).name());
        details.setColumnProperty(column.getName());
        return details;
    }

    /**
     * Gets the default column details.
     *
     * @param definitionDetails the definition details
     * @param value             the value
     * @param columnDisplayName the column display name
     * @return the default column details
     */
    private ColumnDetails getDefaultColumnDetails(final TableDefinitionDetails definitionDetails, final String value,
                                                  final String columnDisplayName) {
        final ColumnDetails details = new ColumnDetails();
        if (null != definitionDetails) {
            details.setColumnName(definitionDetails.getTableName());
            details.setPropertyName(definitionDetails.getTablePropertyName());
        }
        details.setOldValue(value);
        details.setNewValue(value);
        details.setColumnDisplayName(columnDisplayName);
        return details;
    }

    /**
     * Gets the display name.
     *
     * @param columnDisplayName the column display name
     * @param colDefinition     the col definition
     * @return the display name
     */
    private String getDisplayName(final String columnDisplayName, final ColumnDefinitionDetails colDefinition) {
        return StringUtils.isBlank(columnDisplayName) ? colDefinition.getColumnProperty() : columnDisplayName;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.logger.AuditLogger#getModifiedDataDetails(java.lang.
     * Object, java.lang.Object, java.lang.String)
     */
    @Override
    public ModifiedDataDetails getModifiedDataDetails(final Object oldDataObject, final Object newDataObject,
                                                      final String columnDisplayName) {
        // validate the input objects
        this.validateObjects(oldDataObject, newDataObject);

        final ModifiedDataDetails dataDetails = new ModifiedDataDetails();

        for (final Field field : oldDataObject.getClass().getDeclaredFields()) {
            try {
                if (null != field.getAnnotation(Column.class)) {
                    field.setAccessible(true);
                    final String fieldName = field.getName();
                    Object oValue = field.get(oldDataObject);
                    final Field newField = newDataObject.getClass().getDeclaredField(fieldName);
                    newField.setAccessible(true);
                    Object nValue = newField.get(newDataObject);
                    final ColumnDefinitionDetails colDefinition = this.getColumnDefinitionDetails(field);
                    final ColumnDetails details = new ColumnDetails();
                    details.setColumnName(colDefinition.getColumnName());
                    details.setPropertyName(colDefinition.getColumnProperty());
                    this.setDataType(oValue, nValue, details);
                    // removing the milli seconds from the comparison since the get method of
                    // hibernate session returns with millisecond. Here the old value appends 0 in
                    // millisecond and the new value doesn't have the millisecond field which was
                    // giving the notEquals method returns true.
                    if (oValue instanceof Date) {
                        oValue = DateUtils.truncate((Date) oValue, Calendar.MILLISECOND).getTime();
                        details.setDataType(Date.class.getName());
                    }
                    if (nValue instanceof Date) {
                        nValue = DateUtils.truncate((Date) nValue, Calendar.MILLISECOND).getTime();
                        details.setDataType(Date.class.getName());
                    }
                    final String displayName = this.getDisplayName(columnDisplayName, colDefinition);
                    details.setColumnDisplayName(displayName);
                    details.setOldValue(oValue);
                    details.setNewValue(nValue);
                    dataDetails.getColumnDetailsList().add(details);
                    this.addModifiedFieldsData(dataDetails, oValue, nValue, details);
                }
            } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                LOGGER.error("isl-AUDIT-LOG-ERROR-4f00612f-0e1e-4f9a-bf11-5a091e5a3ddb " + e.getMessage());
            }
        }

        return dataDetails;

    }

    /**
     * Gets the primary key details.
     *
     * @param entity the entity
     * @return the primary key details
     */
    private PrimaryKeyDetails getPrimaryKeyDetails(final Object entity) {
        Field idField = null;
        PrimaryKeyDetails dto = null;
        for (final Field field : entity.getClass().getDeclaredFields()) {
            if (null != field.getAnnotation(Id.class)) {
                idField = field;
                break;
            }
        }

        if (null != idField) {
            idField.setAccessible(true);
            try {
                dto = this.extractPrimaryKeyDetails(idField, entity);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                LOGGER.error("Primary key details extraction failed", e);
            }
        }

        return dto;
    }

    /**
     * Gets the table definition details.
     *
     * @param entity the entity
     * @return the table definition details
     */
    private TableDefinitionDetails getTableDefinitionDetails(final Object entity) {
        TableDefinitionDetails definitionDetails = null;
        for (final Annotation a : entity.getClass().getAnnotations()) {
            if (a instanceof Table) {
                definitionDetails = new TableDefinitionDetails();
                definitionDetails.setTableName(((Table) a).name());
                definitionDetails.setTablePropertyName(entity.getClass().getSimpleName());
                break;
            }
        }
        return definitionDetails;
    }

    /**
     * Checks if is invalid column details.
     *
     * @param details the details
     * @return true, if is invalid column details
     */
    private boolean isInvalidColumnDetails(final ColumnDetails details) {
        return StringUtils.equals(AuditLogConstants.RECORD_ADDED, details.getOldValue().toString())
                || StringUtils.equals(AuditLogConstants.RECORD_MODIFIED, details.getOldValue().toString())
                || StringUtils.equals(AuditLogConstants.RECORD_DELETED, details.getOldValue().toString())
                || StringUtils.equals(AuditLogConstants.RECORD_VERIFIED, details.getOldValue().toString());
    }

    /**
     * Map model to dto.
     *
     * @param log the log
     * @return the audit log dto
     */
    private AuditLogDto mapModelToDto(final AuditLog log) {

        final AuditLogDto dto = ObjectMapperUtils.map(log, AuditLogDto.class);

        final List<ColumnDetailsDto> columnDetailsDtos = ObjectMapperUtils.mapAll(log.getModifiedFieldsData(),
                ColumnDetailsDto.class);

        dto.setModifiedFieldsData(columnDetailsDtos);

        return dto;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.logger.AuditLogger#revertAuditLogRecords(java.lang.
     * Object, java.lang.String, java.util.Date, java.lang.Boolean)
     */
    @Override
    public void revertAuditLogRecords(final Object entity, final String user, final Date currentDate,
                                      final Boolean verifiedFlag) { // NOSONAR
        if (BooleanUtils.isNotTrue(verifiedFlag)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(
                        "isl-AUDIT-LOG-cf9ffaef-7dfb-4c8a-b687-06b0cdd9ab4b no need to revert the audit log values");
            }
            return;
        }
        final TableDefinitionDetails tableDefinitionDetails = this.getTableDefinitionDetails(entity);
        final PrimaryKeyDetails pkDetails = this.getPrimaryKeyDetails(entity);
        if ((null == tableDefinitionDetails) || (null == pkDetails)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(
                        "isl-AUDIT-LOG-cf9ffaef-7dfb-4c8a-b687-06b0cdd9ab4b not able to extract table definition details");
            }
            return;
        }

        final AuditLog log = this.auditLogDao.getActiveAuditLogDetails(tableDefinitionDetails.getTablePropertyName(),
                pkDetails.getPkPropertyName(), pkDetails.getValue());

        // already existing records
        if (null != log) {
            final String currentSolId = UserAccountUtils.getCurrentSolId();
            log.setReverterSolId(currentSolId);
            log.setRecordUpdatedSolId(currentSolId);
            log.setRevertedBy(user);
            log.setRevertedDate(currentDate);
            this.auditLogDao.revert(log);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.logger.AuditLogger#revertGenModRecords(java.lang.
     * Object, java.lang.String, java.util.Date, java.lang.Boolean)
     */
    @Override
    public void revertGenModRecords(final Object entity, final String user, final Date currentDate,
                                    final Boolean verifiedFlag) { // NOSONAR

        if (BooleanUtils.isNotTrue(verifiedFlag)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("isl-AUDIT-LOG-007e8f65-b1ca-4c45-a97d-b8f0fabe62ef no need to revert the gen mod values");
            }
            return;
        }
        // insert/update data in audit table and gen mod table
        final TableDefinitionDetails tableDefinitionDetails = this.getTableDefinitionDetails(entity);
        final PrimaryKeyDetails pkDetails = this.getPrimaryKeyDetails(entity);
        if ((null == tableDefinitionDetails) || (null == pkDetails)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(
                        "isl-AUDIT-LOG-007e8f65-b1ca-4c45-a97d-b8f0fabe62ef not able to extract table definition details or pk details");
            }
            return;
        }

        final AuditGenMod genMod = this.auditGenModDao.getAuditGenModDetails(
                tableDefinitionDetails.getTablePropertyName(), pkDetails.getPkPropertyName(), pkDetails.getValue());

        if (null != genMod) {
            genMod.setRevertedBy(user);
            genMod.setRevertedDate(currentDate);
            final String currentSolId = UserAccountUtils.getCurrentSolId();
            genMod.setReverterSolId(currentSolId);
            genMod.setRecordUpdatedSolId(currentSolId);
            // updating gen_mod table
            this.auditGenModDao.revert(genMod);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.logger.AuditLogger#saveOrUpdateAuditLog(com.isl.gbm.
     * audit.log.logger.AuditLoggerParams)
     */
    @Override
    public void saveOrUpdateAuditLog(final AuditLoggerParams auditLoggerParams) {
        final TableDefinitionDetails tableDefinitionDetails = this
                .getTableDefinitionDetails(auditLoggerParams.getOldEntity());
        final PrimaryKeyDetails pkDetails = this.getPrimaryKeyDetails(auditLoggerParams.getOldEntity());
        if ((null == tableDefinitionDetails) || (null == pkDetails)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(
                        "isl-AUDIT-LOG-16807d0c-8b78-4e49-be7f-830d88bb9bcb not able to extract table definition details or pk details");
            }
            return;
        }
        AuditLog log = this.auditLogDao.getActiveAuditLogDetails(tableDefinitionDetails.getTablePropertyName(),
                pkDetails.getPkPropertyName(), pkDetails.getValue());
        final String currentSolId = UserAccountUtils.getCurrentSolId();
        // already existing records
        if (null != log) {
            final String user = StringUtils.isBlank(log.getLastModifiedBy()) ? log.getEntererId()
                    : log.getLastModifiedBy();
            // validating the privilege of the user
            this.validateModifyUser(auditLoggerParams.getCurrentUser(), user, AuditLogConstants.MODIFY_ERROR_MESSAGE);

            log.setLastModifiedBy(auditLoggerParams.getCurrentUser());
            log.setLastModifiedDate(auditLoggerParams.getCurrentDate());
            log.setModifierSolId(currentSolId);
            log.setRecordUpdatedSolId(currentSolId);
            this.setModifiedDetailsForExistingRecords(auditLoggerParams, tableDefinitionDetails, log);
            log.setOperationMode(OperationModes.MODIFY.getOperationMode());
            log.setRemarks(AuditLogConstants.RECORD_MODIFIED);
            log.setFeatureDisplayName(auditLoggerParams.getFeatureName());
        } else {
            // newly added records
            log = new AuditLog();

            if (auditLoggerParams.isAlreadyVerifiedRecord()) {
                log.setLastModifiedBy(auditLoggerParams.getCurrentUser());
                log.setLastModifiedDate(auditLoggerParams.getCurrentDate());
                log.setModifierSolId(currentSolId);

            } else {
                log.setEntererId(auditLoggerParams.getCurrentUser());
                log.setEntererDate(auditLoggerParams.getCurrentDate());
                log.setEntererSolId(currentSolId);
            }
            log.setRecordUpdatedSolId(currentSolId);
            log.setDeleted(auditLoggerParams.getDeleted());
            this.setModifiedFieldsForNewRecords(auditLoggerParams, tableDefinitionDetails, log);
            log.setModuleName(auditLoggerParams.getModuleDisplayName().name());
            log.setOperationMode(OperationModes.MODIFY.getOperationMode());
            log.setRemarks(AuditLogConstants.RECORD_MODIFIED);
            log.setTableName(tableDefinitionDetails.getTableName());
            log.setTablePropertyName(tableDefinitionDetails.getTablePropertyName());
            log.setTablePkPropertyName(pkDetails.getPkPropertyName());
            log.setTablePkColumnName(pkDetails.getPkColumnName());
            log.setTablePkColumnValue(pkDetails.getValue());
            log.setFeatureDisplayName(auditLoggerParams.getFeatureName());
        }
        this.auditLogDao.save(log);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.logger.AuditLogger#saveOrUpdateGenModRecords(com.isl.
     * gbm.audit.log.logger.AuditLoggerParams)
     */
    @Override
    public void saveOrUpdateGenModRecords(final AuditLoggerParams auditLoggerParams) {
        // insert/update data in audit table and gen mod table
        final TableDefinitionDetails tableDefinitionDetails = this
                .getTableDefinitionDetails(auditLoggerParams.getOldEntity());
        final PrimaryKeyDetails pkDetails = this.getPrimaryKeyDetails(auditLoggerParams.getOldEntity());
        if ((null == tableDefinitionDetails) || (null == pkDetails)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(
                        "isl-AUDIT-LOG-55e5cbdf-7a63-4fe3-a118-79b3df4dc49e not able to extract table definition details or pk details");
            }
            return;
        }

        AuditGenMod genMod = this.auditGenModDao.getAuditGenModDetails(tableDefinitionDetails.getTablePropertyName(),
                pkDetails.getPkPropertyName(), pkDetails.getValue());
        final String currentSolId = UserAccountUtils.getCurrentSolId();
        if (null != genMod) {
            // validating the privilege of the user
            this.validateModifyUser(auditLoggerParams.getCurrentUser(), genMod.getLastModifiedBy(),
                    AuditLogConstants.MODIFY_ERROR_MESSAGE);
            genMod.setLastModifiedBy(auditLoggerParams.getCurrentUser());
            genMod.setLastModifiedDate(auditLoggerParams.getCurrentDate());
            genMod.setModifierSolId(currentSolId);
            genMod.setRecordUpdatedSolId(currentSolId);
            if (auditLoggerParams.isAlreadyVerifiedRecord()) {
                final ModifiedDataDetails dataDetails = this.getModifiedDataDetails(auditLoggerParams.getOldEntity(),
                        auditLoggerParams.getNewEntity(), auditLoggerParams.getColumnDisplayName());
                genMod.setModifiedFieldsData(dataDetails.getModifiedColumnDetailList());
                if (CollectionUtils.isEmpty(genMod.getModifiedFieldsData())) {
                    genMod.setDeleted(Boolean.TRUE);
                    genMod.setDeleterSolId(currentSolId);
                }
            } else {
                final List<ColumnDetails> detailList = genMod.getModifiedFieldsData();
                final ColumnDetails details = this.getDefaultColumnDetails(tableDefinitionDetails,
                        AuditLogConstants.RECORD_MODIFIED, auditLoggerParams.getColumnDisplayName());
                detailList.add(details);
                genMod.setModifiedFieldsData(detailList);
            }
            genMod.setOperationMode(OperationModes.MODIFY.getOperationMode());
            genMod.setRemarks(AuditLogConstants.RECORD_MODIFIED);
            genMod.setFeatureDisplayName(auditLoggerParams.getFeatureName());
        } else {
            genMod = new AuditGenMod();

            final ModifiedDataDetails dataDetails = this.getModifiedDataDetails(auditLoggerParams.getOldEntity(),
                    auditLoggerParams.getNewEntity(), auditLoggerParams.getColumnDisplayName());
            genMod.setModifiedFieldsData(dataDetails.getModifiedColumnDetailList());
            if (CollectionUtils.isEmpty(genMod.getModifiedFieldsData())) {
                genMod.setDeleted(Boolean.TRUE);
                genMod.setDeleterSolId(currentSolId);
            }
            genMod.setModifierSolId(currentSolId);
            genMod.setRecordUpdatedSolId(currentSolId);
            genMod.setModuleName(auditLoggerParams.getModuleDisplayName().name());
            genMod.setLastModifiedBy(auditLoggerParams.getCurrentUser());
            genMod.setLastModifiedDate(auditLoggerParams.getCurrentDate());
            genMod.setOperationMode(OperationModes.MODIFY.getOperationMode());
            genMod.setRemarks(AuditLogConstants.RECORD_MODIFIED);
            genMod.setTableName(tableDefinitionDetails.getTableName());
            genMod.setTablePropertyName(tableDefinitionDetails.getTablePropertyName());
            genMod.setTablePkPropertyName(pkDetails.getPkPropertyName());
            genMod.setTablePkColumnName(pkDetails.getPkColumnName());
            genMod.setTablePkColumnValue(pkDetails.getValue());
            genMod.setFeatureDisplayName(auditLoggerParams.getFeatureName());
        }
        this.auditGenModDao.save(genMod);
    }

    /**
     * Sets the audit gen mod dao.
     *
     * @param auditGenModDao the new audit gen mod dao
     */
    public void setAuditGenModDao(final AuditGenModDao auditGenModDao) {
        this.auditGenModDao = auditGenModDao;
    }

    /**
     * Sets the audit log dao.
     *
     * @param auditLogDao the new audit log dao
     */
    public void setAuditLogDao(final AuditLogDao auditLogDao) {
        this.auditLogDao = auditLogDao;
    }

    /**
     * Sets the data type.
     *
     * @param oValue  the o value
     * @param nValue  the n value
     * @param details the details
     */
    private void setDataType(final Object oValue, final Object nValue, final ColumnDetails details) {
        if (null != oValue) {
            details.setDataType(oValue.getClass().getName());
        }

        if (null != nValue) {
            details.setDataType(nValue.getClass().getName());
        }
    }

    /**
     * Sets the modified details for existing records.
     *
     * @param auditLoggerParams      the audit logger params
     * @param tableDefinitionDetails the table definition details
     * @param log                    the log
     */
    private void setModifiedDetailsForExistingRecords(final AuditLoggerParams auditLoggerParams,
                                                      final TableDefinitionDetails tableDefinitionDetails, final AuditLog log) {
        if (auditLoggerParams.isAlreadyVerifiedRecord()) {
            final ModifiedDataDetails dataDetails = this.getModifiedDataDetails(auditLoggerParams.getOldEntity(),
                    auditLoggerParams.getNewEntity(), auditLoggerParams.getColumnDisplayName());
            log.setModifiedFieldsData(dataDetails.getModifiedColumnDetailList());
            if (CollectionUtils.isEmpty(log.getModifiedFieldsData())) {
                log.setDeleted(Boolean.TRUE);
                log.setDeleterSolId(UserAccountUtils.getCurrentSolId());
            }
        } else {
            final List<ColumnDetails> detailList = log.getModifiedFieldsData();
            final ColumnDetails details = this.getDefaultColumnDetails(tableDefinitionDetails,
                    AuditLogConstants.RECORD_MODIFIED, auditLoggerParams.getColumnDisplayName());
            detailList.add(details);
            log.setModifiedFieldsData(detailList);
            log.setModifierSolId(UserAccountUtils.getCurrentSolId());
        }

    }

    /**
     * Sets the modified fields for new records.
     *
     * @param auditLoggerParams      the audit logger params
     * @param tableDefinitionDetails the table definition details
     * @param log                    the log
     */
    private void setModifiedFieldsForNewRecords(final AuditLoggerParams auditLoggerParams,
                                                final TableDefinitionDetails tableDefinitionDetails, final AuditLog log) {
        if (auditLoggerParams.isAlreadyVerifiedRecord()) {
            final ModifiedDataDetails dataDetails = this.getModifiedDataDetails(auditLoggerParams.getOldEntity(),
                    auditLoggerParams.getNewEntity(), auditLoggerParams.getColumnDisplayName());
            log.setModifiedFieldsData(dataDetails.getModifiedColumnDetailList());
            if (CollectionUtils.isEmpty(log.getModifiedFieldsData())) {
                log.setDeleted(Boolean.TRUE);
                log.setDeleterSolId(UserAccountUtils.getCurrentSolId());
            }
        } else {
            final List<ColumnDetails> detailList = new ArrayList<>();
            final ColumnDetails details = this.getDefaultColumnDetails(tableDefinitionDetails,
                    AuditLogConstants.RECORD_MODIFIED, auditLoggerParams.getColumnDisplayName());
            detailList.add(details);
            log.setModifiedFieldsData(detailList);
            log.setModifierSolId(UserAccountUtils.getCurrentSolId());
        }
    }

    /**
     * Validate audit log delete user.
     *
     * @param currentUser the current user
     * @param log         the log
     */
    private void validateAuditLogDeleteUser(final String currentUser, final AuditLog log) {
        if (StringUtils.isBlank(log.getLastModifiedBy())) {
            if ((StringUtils.isNotBlank(log.getEntererId())
                    && StringUtils.equalsIgnoreCase(log.getEntererId(), currentUser))) {
                throw new AuditLogException(AuditLogConstants.DELETE_CREATE_ERROR_MESSAGE);
            }
        } else {
            if (StringUtils.equalsIgnoreCase(log.getLastModifiedBy(), currentUser)) {
                throw new AuditLogException(AuditLogConstants.DELETE_MODIFY_ERROR_MESSAGE);
            }
        }
    }

    /**
     * Validate audit log verify user.
     *
     * @param currentUser the current user
     * @param log         the log
     */
    private void validateAuditLogVerifyUser(final String currentUser, final AuditLog log) {
        if (StringUtils.isBlank(log.getLastModifiedBy())) {
            if ((StringUtils.isNotBlank(log.getEntererId())
                    && StringUtils.equalsIgnoreCase(log.getEntererId(), currentUser))) {
                throw new AuditLogException(AuditLogConstants.VERIFY_CREATE_ERROR_MESSAGE);
            }
        } else {
            if (StringUtils.equalsIgnoreCase(log.getLastModifiedBy(), currentUser)) {
                throw new AuditLogException(AuditLogConstants.VERIFY_MODIFY_ERROR_MESSAGE);
            }
        }
    }

    /**
     * Validate gen mod delete user.
     *
     * @param user           the user
     * @param lastModifiedBy the last modified by
     */
    private void validateGenModDeleteUser(final String user, final String lastModifiedBy) {
        if (StringUtils.isNotBlank(lastModifiedBy) && StringUtils.equalsIgnoreCase(lastModifiedBy, user)) {
            throw new AuditLogException(AuditLogConstants.DELETE_MODIFY_ERROR_MESSAGE);
        }

    }

    /**
     * Validate gen mod verify user.
     *
     * @param user           the user
     * @param lastModifiedBy the last modified by
     */
    private void validateGenModVerifyUser(final String user, final String lastModifiedBy) {
        if (StringUtils.isNotBlank(lastModifiedBy) && StringUtils.equalsIgnoreCase(lastModifiedBy, user)) {
            throw new AuditLogException(AuditLogConstants.VERIFY_MODIFY_ERROR_MESSAGE);
        }
    }

    /**
     * Validate modify user.
     *
     * @param currentUser      the current user
     * @param lastModifiedUser the last modified user
     * @param errorMessage     the error message
     */
    private void validateModifyUser(final String currentUser, final String lastModifiedUser,
                                    final String errorMessage) {
        if ((StringUtils.isNotBlank(lastModifiedUser)
                && !StringUtils.equalsIgnoreCase(lastModifiedUser, currentUser))) {
            throw new AuditLogException(errorMessage);
        }
    }

    /**
     * Validate objects.
     *
     * @param oldDataObject the old data object
     * @param newDataObject the new data object
     */
    private void validateObjects(final Object oldDataObject, final Object newDataObject) {
        if ((null == oldDataObject) || (null == newDataObject)
                || !oldDataObject.getClass().equals(newDataObject.getClass())) {
            throw new AuditLogException(
                    "Old value might be null, new value might be null or both are of different type of objects");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.logger.AuditLogger#verifyAuditLog(java.lang.String,
     * java.util.Date, java.util.List, java.lang.Boolean)
     */
    @Override
    public int verifyAuditLog(final String user, final Date currentDate, final List<? extends Object> entityList,
                              final Boolean flag) { // NOSONAR

        int totalCount = 0;

        for (final Object entity : entityList) {
            try {
                totalCount += this.verifyAuditLog(user, currentDate, entity);
            } catch (final AuditLogVerifyException e) {
                LOGGER.error("isl-audit-log-34d90823-e0a9-49bc-9deb-657491cdb926 skipping the exception");
            }
        }

        if (totalCount == 0) {
            throw new AuditLogVerifyException(AuditLogConstants.ALREADY_VERIFIED_RECORD_MSG);
        }

        return totalCount;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.logger.AuditLogger#verifyAuditLog(java.lang.String,
     * java.util.Date, java.lang.Object)
     */
    @Override
    public int verifyAuditLog(final String user, final Date currentDate, final Object entity) {
        final TableDefinitionDetails tableDefinitionDetails = this.getTableDefinitionDetails(entity);
        final PrimaryKeyDetails pkDetails = this.getPrimaryKeyDetails(entity);
        int count = 0;
        if ((null == tableDefinitionDetails) || (null == pkDetails)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(
                        "isl-AUDIT-LOG-ec404c4e-4421-47a7-ac75-344299126e05 not able to extract table definition details");
            }
            return count;
        }

        final AuditLog log = this.auditLogDao.getActiveAuditLogDetails(tableDefinitionDetails.getTablePropertyName(),
                pkDetails.getPkPropertyName(), pkDetails.getValue());
        final String currentSolId = UserAccountUtils.getCurrentSolId();
        // already existing records
        if (null != log) {
            count = count + 1;
            this.validateAuditLogVerifyUser(user, log);
            log.setAuthId(user);
            log.setAuthDate(currentDate);
            log.setOperationMode(OperationModes.VERIFY.getOperationMode());
            log.setRemarks(AuditLogConstants.RECORD_VERIFIED);
            log.setDeleted(Boolean.TRUE);
            log.setVerifierSolId(currentSolId);
            log.setRecordUpdatedSolId(currentSolId);
            // updating the audit table
            this.auditLogDao.save(log);

            boolean validRecord = false;
            for (final ColumnDetails details : log.getModifiedFieldsData()) {
                if (BooleanUtils.isFalse(this.isInvalidColumnDetails(details))) {
                    validRecord = true;
                }
            }
            // update the main tables
            if (validRecord) {
                this.auditLogDao.execute(log);
            }
        }

        if (count == 0) {
            throw new AuditLogVerifyException(AuditLogConstants.ALREADY_VERIFIED_RECORD_MSG);
        }

        return count;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.logger.AuditLogger#verifyGenModRecords(java.lang.
     * String, java.util.Date, java.util.List, java.lang.Boolean)
     */
    @Override
    public void verifyGenModRecords(final String user, final Date currentDate, final List<? extends Object> entityList,
                                    final Boolean flag) { // NOSONAR
        for (final Object entity : entityList) {
            this.verifyGenModRecords(user, currentDate, entity);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.logger.AuditLogger#verifyGenModRecords(java.lang.
     * String, java.util.Date, java.lang.Object)
     */
    @Override
    public void verifyGenModRecords(final String user, final Date currentDate, final Object entity) {
        // insert/update data in audit table and gen mod table
        final TableDefinitionDetails tableDefinitionDetails = this.getTableDefinitionDetails(entity);
        final PrimaryKeyDetails pkDetails = this.getPrimaryKeyDetails(entity);
        if ((null == tableDefinitionDetails) || (null == pkDetails)) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info(
                        "isl-AUDIT-LOG-9ce6683a-aee6-4ba4-ba80-3437912691d7 not able to extract table definition details or pk details");
            }
            return;
        }

        final AuditGenMod genMod = this.auditGenModDao.getAuditGenModDetails(
                tableDefinitionDetails.getTablePropertyName(), pkDetails.getPkPropertyName(), pkDetails.getValue());
        final String currentSolId = UserAccountUtils.getCurrentSolId();
        if (null != genMod) {
            this.validateGenModVerifyUser(user, genMod.getLastModifiedBy());
            genMod.setAuthId(user);
            genMod.setAuthDate(currentDate);
            genMod.setOperationMode(OperationModes.VERIFY.getOperationMode());
            genMod.setRemarks(AuditLogConstants.RECORD_VERIFIED);
            genMod.setDeleted(Boolean.TRUE);
            genMod.setVerifierSolId(currentSolId);
            genMod.setRecordUpdatedSolId(currentSolId);
            // updating gen_mod table
            this.auditGenModDao.save(genMod);
            boolean validRecord = false;
            // update the main tables
            for (final ColumnDetails details : genMod.getModifiedFieldsData()) {
                if (BooleanUtils.isFalse(this.isInvalidColumnDetails(details))) {
                    validRecord = true;
                }
            }
            if (validRecord) {
                this.auditGenModDao.execute(genMod);
            }
        }
    }
}
