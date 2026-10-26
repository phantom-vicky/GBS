/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.model;

import com.isl.gbm.common.audit.log.common.AuditLogConstants;
import com.isl.gbm.common.audit.log.model.converter.FeatureDisplayNameConverter;
import com.isl.gbm.common.audit.log.model.converter.ModifiedFieldsDataConverter;
import com.isl.gbm.user.management.constants.FeatureNames;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The Class AuditGenMod.
 */
@Entity
@Table(name = "GBM_GEN_MOD")
@DynamicUpdate
@SelectBeforeUpdate
public class AuditGenMod implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The id.
     */
    @Column(name = "id", updatable = false)
    @Id
    @SequenceGenerator(name = "auditGenModSequence", sequenceName = "SEQ_GBM_GEN_MOD", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auditGenModSequence")
    private BigDecimal id;

    /**
     * The table name.
     */
    @Column(name = "table_name")
    private String tableName;

    /**
     * The table property name.
     */
    @Column(name = "table_prop_name")
    private String tablePropertyName;

    /**
     * The table pk column name.
     */
    @Column(name = "table_pk_col_name")
    private String tablePkColumnName;

    /**
     * The table pk property name.
     */
    @Column(name = "table_pk_prop")
    private String tablePkPropertyName;

    /**
     * The table pk column value.
     */
    @Column(name = "table_pk_col_value")
    private BigDecimal tablePkColumnValue;

    /**
     * The module name.
     */
    @Column(name = "module_name")
    private String moduleName;

    /**
     * The last modified by.
     */
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    /**
     * The last modified date.
     */
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    /**
     * The auth id.
     */
    @Column(name = "auth_id")
    private String authId = AuditLogConstants.DEFAULT_AUTH_ID;

    /**
     * The auth date.
     */
    @Column(name = "auth_date")
    private Date authDate;

    /**
     * The remarks.
     */
    @Column(name = "remarks")
    private String remarks;

    /**
     * The modified fields data.
     */
    @Column(name = "modified_fields_data")
    @Lob
    @Convert(converter = ModifiedFieldsDataConverter.class)
    private List<ColumnDetails> modifiedFieldsData;

    /**
     * The operation mode.
     */
    @Column(name = "operation_mode")
    private String operationMode;

    /**
     * The deleted.
     */
    @Column(name = "deleted")
    private Boolean deleted = Boolean.FALSE;

    /**
     * The deleted by.
     */
    @Column(name = "deleted_by")
    private String deletedBy;

    /**
     * The deleted date.
     */
    @Column(name = "deleted_date")
    private Date deletedDate;

    /**
     * The reverted by.
     */
    @Column(name = "reverted_by")
    private String revertedBy;

    /**
     * The reverted date.
     */
    @Column(name = "reverted_date")
    private Date revertedDate;

    /**
     * The record updated date.
     */
    @Column(name = "record_updated_date")
    private Date recordUpdatedDate;

    /**
     * The feature display name.
     */
    @Column(name = "feature_display_name")
    @Convert(converter = FeatureDisplayNameConverter.class)
    private FeatureNames featureDisplayName;

    /**
     * The modifier sol id.
     */
    @Column(name = "modifier_sol_id")
    private String modifierSolId;

    /**
     * The verifier sol id.
     */
    @Column(name = "verifier_sol_id")
    private String verifierSolId;

    /**
     * The deleter sol id.
     */
    @Column(name = "deleter_sol_id")
    private String deleterSolId;

    /**
     * The reverter sol id.
     */
    @Column(name = "reverter_sol_id")
    private String reverterSolId;

    /**
     * The record updated sol id.
     */
    @Column(name = "record_updated_sol_id")
    private String recordUpdatedSolId;

    /**
     * Gets the auth date.
     *
     * @return the auth date
     */
    public Date getAuthDate() {
        return this.authDate;
    }

    /**
     * Gets the auth id.
     *
     * @return the auth id
     */
    public String getAuthId() {
        return this.authId;
    }

    /**
     * Gets the deleted.
     *
     * @return the deleted
     */
    public Boolean getDeleted() {
        return this.deleted;
    }

    /**
     * Gets the deleted by.
     *
     * @return the deleted by
     */
    public String getDeletedBy() {
        return this.deletedBy;
    }

    /**
     * Gets the deleted date.
     *
     * @return the deleted date
     */
    public Date getDeletedDate() {
        return this.deletedDate;
    }

    /**
     * Gets the deleter sol id.
     *
     * @return the deleter sol id
     */
    public String getDeleterSolId() {
        return this.deleterSolId;
    }

    /**
     * Gets the feature display name.
     *
     * @return the feature display name
     */
    public FeatureNames getFeatureDisplayName() {
        return this.featureDisplayName;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public BigDecimal getId() {
        return this.id;
    }

    /**
     * Gets the last modified by.
     *
     * @return the last modified by
     */
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Gets the last modified date.
     *
     * @return the last modified date
     */
    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    /**
     * Gets the modified fields data.
     *
     * @return the modified fields data
     */
    public List<ColumnDetails> getModifiedFieldsData() {
        return this.modifiedFieldsData;
    }

    /**
     * Gets the modifier sol id.
     *
     * @return the modifier sol id
     */
    public String getModifierSolId() {
        return this.modifierSolId;
    }

    /**
     * Gets the module name.
     *
     * @return the module name
     */
    public String getModuleName() {
        return this.moduleName;
    }

    /**
     * Gets the operation mode.
     *
     * @return the operation mode
     */
    public String getOperationMode() {
        return this.operationMode;
    }

    /**
     * Gets the record updated date.
     *
     * @return the record updated date
     */
    public Date getRecordUpdatedDate() {
        return this.recordUpdatedDate;
    }

    /**
     * Gets the record updated sol id.
     *
     * @return the record updated sol id
     */
    public String getRecordUpdatedSolId() {
        return this.recordUpdatedSolId;
    }

    /**
     * Gets the remarks.
     *
     * @return the remarks
     */
    public String getRemarks() {
        return this.remarks;
    }

    /**
     * Gets the reverted by.
     *
     * @return the reverted by
     */
    public String getRevertedBy() {
        return this.revertedBy;
    }

    /**
     * Gets the reverted date.
     *
     * @return the reverted date
     */
    public Date getRevertedDate() {
        return this.revertedDate;
    }

    /**
     * Gets the reverter sol id.
     *
     * @return the reverter sol id
     */
    public String getReverterSolId() {
        return this.reverterSolId;
    }

    /**
     * Gets the table name.
     *
     * @return the table name
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * Gets the table pk column name.
     *
     * @return the table pk column name
     */
    public String getTablePkColumnName() {
        return this.tablePkColumnName;
    }

    /**
     * Gets the table pk column value.
     *
     * @return the table pk column value
     */
    public BigDecimal getTablePkColumnValue() {
        return this.tablePkColumnValue;
    }

    /**
     * Gets the table pk property name.
     *
     * @return the table pk property name
     */
    public String getTablePkPropertyName() {
        return this.tablePkPropertyName;
    }

    /**
     * Gets the table property name.
     *
     * @return the table property name
     */
    public String getTablePropertyName() {
        return this.tablePropertyName;
    }

    /**
     * Gets the verifier sol id.
     *
     * @return the verifier sol id
     */
    public String getVerifierSolId() {
        return this.verifierSolId;
    }

    /**
     * Sets the auth date.
     *
     * @param authDate the new auth date
     */
    public void setAuthDate(final Date authDate) {
        this.authDate = authDate;
    }

    /**
     * Sets the auth id.
     *
     * @param authId the new auth id
     */
    public void setAuthId(final String authId) {
        this.authId = authId;
    }

    /**
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(final Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * Sets the deleted by.
     *
     * @param deletedBy the new deleted by
     */
    public void setDeletedBy(final String deletedBy) {
        this.deletedBy = deletedBy;
    }

    /**
     * Sets the deleted date.
     *
     * @param deletedDate the new deleted date
     */
    public void setDeletedDate(final Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    /**
     * Sets the deleter sol id.
     *
     * @param deleterSolId the new deleter sol id
     */
    public void setDeleterSolId(final String deleterSolId) {
        this.deleterSolId = deleterSolId;
    }

    /**
     * Sets the feature display name.
     *
     * @param featureDisplayName the new feature display name
     */
    public void setFeatureDisplayName(final FeatureNames featureDisplayName) {
        this.featureDisplayName = featureDisplayName;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final BigDecimal id) {
        this.id = id;
    }

    /**
     * Sets the last modified by.
     *
     * @param lastModifiedBy the new last modified by
     */
    public void setLastModifiedBy(final String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * Sets the last modified date.
     *
     * @param lastModifiedDate the new last modified date
     */
    public void setLastModifiedDate(final Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Sets the modified fields data.
     *
     * @param modifiedFieldsData the new modified fields data
     */
    public void setModifiedFieldsData(final List<ColumnDetails> modifiedFieldsData) {
        this.modifiedFieldsData = modifiedFieldsData;
    }

    /**
     * Sets the modifier sol id.
     *
     * @param modifierSolId the new modifier sol id
     */
    public void setModifierSolId(final String modifierSolId) {
        this.modifierSolId = modifierSolId;
    }

    /**
     * Sets the module name.
     *
     * @param moduleName the new module name
     */
    public void setModuleName(final String moduleName) {
        this.moduleName = moduleName;
    }

    /**
     * Sets the operation mode.
     *
     * @param operationMode the new operation mode
     */
    public void setOperationMode(final String operationMode) {
        this.operationMode = operationMode;
    }

    /**
     * Sets the record updated date.
     *
     * @param recordUpdatedDate the new record updated date
     */
    public void setRecordUpdatedDate(final Date recordUpdatedDate) {
        this.recordUpdatedDate = recordUpdatedDate;
    }

    /**
     * Sets the record updated sol id.
     *
     * @param recordUpdatedSolId the new record updated sol id
     */
    public void setRecordUpdatedSolId(final String recordUpdatedSolId) {
        this.recordUpdatedSolId = recordUpdatedSolId;
    }

    /**
     * Sets the remarks.
     *
     * @param remarks the new remarks
     */
    public void setRemarks(final String remarks) {
        this.remarks = remarks;
    }

    /**
     * Sets the reverted by.
     *
     * @param revertedBy the new reverted by
     */
    public void setRevertedBy(final String revertedBy) {
        this.revertedBy = revertedBy;
    }

    /**
     * Sets the reverted date.
     *
     * @param revertedDate the new reverted date
     */
    public void setRevertedDate(final Date revertedDate) {
        this.revertedDate = revertedDate;
    }

    /**
     * Sets the reverter sol id.
     *
     * @param reverterSolId the new reverter sol id
     */
    public void setReverterSolId(final String reverterSolId) {
        this.reverterSolId = reverterSolId;
    }

    /**
     * Sets the table name.
     *
     * @param tableName the new table name
     */
    public void setTableName(final String tableName) {
        this.tableName = tableName;
    }

    /**
     * Sets the table pk column name.
     *
     * @param tablePkColumnName the new table pk column name
     */
    public void setTablePkColumnName(final String tablePkColumnName) {
        this.tablePkColumnName = tablePkColumnName;
    }

    /**
     * Sets the table pk column value.
     *
     * @param tablePkColumnValue the new table pk column value
     */
    public void setTablePkColumnValue(final BigDecimal tablePkColumnValue) {
        this.tablePkColumnValue = tablePkColumnValue;
    }

    /**
     * Sets the table pk property name.
     *
     * @param tablePkPropertyName the new table pk property name
     */
    public void setTablePkPropertyName(final String tablePkPropertyName) {
        this.tablePkPropertyName = tablePkPropertyName;
    }

    /**
     * Sets the table property name.
     *
     * @param tablePropertyName the new table property name
     */
    public void setTablePropertyName(final String tablePropertyName) {
        this.tablePropertyName = tablePropertyName;
    }

    /**
     * Sets the verifier sol id.
     *
     * @param verifierSolId the new verifier sol id
     */
    public void setVerifierSolId(final String verifierSolId) {
        this.verifierSolId = verifierSolId;
    }

}
