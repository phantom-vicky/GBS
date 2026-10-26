/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.dao.SolDateDao;
import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.model.GbmSolDate;
import com.isl.gbm.common.audit.log.logger.AuditLogger;
import com.isl.gbm.common.audit.log.logger.AuditLoggerParams;
import com.isl.gbm.common.audit.log.model.ModifiedDataDetails;
import com.isl.gbm.user.management.constants.FeatureNames;
import com.isl.gbm.user.management.constants.ModuleDisplayNames;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class SolDateManager.
 */
@Component
public class SolDateManager {

    /**
     * The sol date dao.
     */
    @Autowired
    private SolDateDao solDateDao;

    /**
     * The audit logger.
     */
    @Autowired
    private AuditLogger auditLogger;

    /**
     * Builds the save sol date.
     *
     * @param solDateDto the sol date dto
     * @return the gbm sol date
     */
    private GbmSolDate buildSaveSolDate(final SolDateDto solDateDto) {
        final GbmSolDate date = new GbmSolDate();
        date.setId(solDateDto.getId());
        date.setSolDate(solDateDto.getSolDate());
        date.setModifiedDate(new Date());
        date.setAddlText1(solDateDto.getAddlText1());
        date.setAddlText2(solDateDto.getAddlText2());
        date.setAddlText3(solDateDto.getAddlText3());
        return date;
    }

    /**
     * Builds the sol date dto.
     *
     * @param date the date
     * @return the sol date dto
     */
    private SolDateDto buildSolDateDto(final GbmSolDate date) {
        final SolDateDto dto = new SolDateDto();
        dto.setId(date.getId());
        dto.setSolDate(date.getSolDate());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date.getSolDate().getTime();
        dto.setModifiedDate(date.getModifiedDate());
        dto.setAddlText1(date.getAddlText1());
        dto.setAddlText2(date.getAddlText2());
        dto.setAddlText3(date.getAddlText3());
        return dto;
    }

    /**
     * Gets the audit logger params.
     *
     * @param oldEntity   the old entity
     * @param newEntity   the new entity
     * @param currentUser the current user
     * @param currentDate the current date
     * @return the audit logger params
     */
    private AuditLoggerParams getAuditLoggerParams(final GbmSolDate oldEntity, final GbmSolDate newEntity,
                                                   final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setDeleted(Boolean.TRUE);
        loggerParams.setFeatureName(FeatureNames.POPULATION_OF_SOL_DATE);
        return loggerParams;
    }

    /**
     * Gets the sol date record.
     *
     * @return the sol date record
     */
  public SolDateDto getSolDateRecord() {
        return this.buildSolDateDto(this.solDateDao.getSolDateRecord());
    }

    /**
     * Save details sol date.
     *
     * @param dto the dto
     *//*
    public void saveDetailsSolDate(final SolDateDto dto) {
        final GbmSolDate gbmSolDate = this.buildSaveSolDate(dto);
        final GbmSolDate savedData = this.solDateDao.getSolDateRecord();
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final AuditLoggerParams helperParam = this.getAuditLoggerParams(savedData, gbmSolDate, currentUser,
                currentDate);
        final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, gbmSolDate,
                null);
        if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
            this.auditLogger.saveOrUpdateAuditLog(helperParam);
        }
        this.solDateDao.saveOrUpdate(gbmSolDate);
    }*/


}
