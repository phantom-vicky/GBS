/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.service.impl;

import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.manager.SolDetailsIntegrationManager;
import com.isl.gbm.common.audit.log.common.AuditLogRecordType;
import com.isl.gbm.common.audit.log.common.OperationModes;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.dto.AuditLogReportDto;
import com.isl.gbm.common.audit.log.dto.AuditLogReportParams;
import com.isl.gbm.common.audit.log.manager.AuditLogReportManager;
import com.isl.gbm.common.audit.log.service.AuditLogReportService;
import com.isl.gbm.user.management.constants.ModuleDisplayNames;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import org.apache.commons.collections.CollectionUtils;

/**
 * The Class AuditLogReportServiceImpl.
 */
@Service
public class AuditLogReportServiceImpl implements AuditLogReportService {

    /**
     * The audit log report manager.
     */
    @Autowired
    private AuditLogReportManager auditLogReportManager;

    /**
     * The sol details integration manager.
     */
    @Autowired
    private SolDetailsIntegrationManager solDetailsIntegrationManager;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.audit.log.service.AuditLogReportService#getAuditLogRecordTypes()
     */
    @Override
    public List<AuditLogRecordType> getAuditLogRecordTypes() {
        return this.auditLogReportManager.getAuditLogRecordTypes();
    }

    /**
     * Gets the audit log report dto.
     *
     * @param auditLogReportParams  the audit log report params
     * @param auditLogReportDtoList the audit log report dto list
     * @return the audit log report dto
     */
    private void getAuditLogReportDto(final AuditLogReportParams auditLogReportParams,
                                      final List<AuditLogReportDto> auditLogReportDtoList) {
        final AuditLogReportDto auditLogReportDto = new AuditLogReportDto();
        final List<SolDetailsIntegrationDto> solList = this.solDetailsIntegrationManager
                .getSolDetails(auditLogReportParams.getSolId());
        if (CollectionUtils.isEmpty(solList)) {
            // invalid sol details. so no need to add
            return;
        }
        auditLogReportDto.setSolDetailsIntegrationDto(solList.get(0));
        final List<AuditLogDto> auditLogDtoList = this.auditLogReportManager
                .getBranchLevelAuditReportDetails(auditLogReportParams);
        auditLogReportDto.setAuditLogDtoList(auditLogDtoList);
        auditLogReportDtoList.add(auditLogReportDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.service.AuditLogReportService#
     * getBranchLevelAuditReportDetails(com.isl.gbm.audit.log.dto.
     * AuditLogReportParams)
     */
    @Override
    public List<AuditLogReportDto> getBranchLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        final List<AuditLogReportDto> reportDtoList = new ArrayList<>();
        if (StringUtils.isNotBlank(auditLogReportParams.getSolId())) {
            this.getAuditLogReportDto(auditLogReportParams, reportDtoList);
        } else {
            final List<SolDetailsIntegrationDto> hierarchyList = this.auditLogReportManager.getSolDetailsFromMaster();
            for (final SolDetailsIntegrationDto dto : hierarchyList) {
                auditLogReportParams.setSolId(dto.getSolId());
                this.getAuditLogReportDto(auditLogReportParams, reportDtoList);
            }
        }
        return reportDtoList;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.service.AuditLogReportService#
     * getDcParameterizationDetails(com.isl.gbm.audit.log.dto.AuditLogReportParams)
     */
    @Override
    public List<AuditLogDto> getDcParameterizationDetails(final AuditLogReportParams auditLogReportParams) {
        return this.auditLogReportManager.getDcParameterizationDetails(auditLogReportParams);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.service.AuditLogReportService#
     * getGuLevelAuditReportDetails(com.isl.gbm.audit.log.dto.AuditLogReportParams)
     */
    @Override
    public List<AuditLogDto> getGuLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        return this.auditLogReportManager.getGuLevelAuditReportDetails(auditLogReportParams);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.service.AuditLogReportService#
     * getHigherBranchPopSpSolIds(java.lang.String)
     */
    @Override
    public List<SolDetailsIntegrationDto> getHigherBranchPopSpSolIds(final String moduleDisplayName) {
        final ModuleDisplayNames module = ModuleDisplayNames.valueOf(moduleDisplayName);
        switch (module) {
            default:
                return this.auditLogReportManager.getSolDetailsFromMaster();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.service.AuditLogReportService#
     * getHigherGuLevelAuditReportDetails(com.isl.gbm.audit.log.dto.
     * AuditLogReportParams)
     */
    @Override
    public List<AuditLogReportDto> getHigherGuLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        final List<AuditLogReportDto> reportDtoList = new ArrayList<>();
        if (StringUtils.isNotBlank(auditLogReportParams.getSolId())) {
            this.getAuditLogReportDto(auditLogReportParams, reportDtoList);
        } else {
            final List<SolDetailsIntegrationDto> hierarchyList = this.auditLogReportManager
                    .getSolDetailsFromMaster();
            for (final SolDetailsIntegrationDto dto : hierarchyList) {
                auditLogReportParams.setSolId(dto.getSolId());
                this.getAuditLogReportDto(auditLogReportParams, reportDtoList);
            }
        }
        return reportDtoList;
    }


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.service.AuditLogReportService#getOperationModes()
     */
    @Override
    public List<OperationModes> getOperationModes() {
        return this.auditLogReportManager.getOperationModes();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.audit.log.service.AuditLogReportService#getSolIds(java.lang.
     * String)
     */
    @Override
    public List<SolDetailsIntegrationDto> getPopSpSolIds(final String moduleDisplayName) {
        final ModuleDisplayNames module = ModuleDisplayNames.valueOf(moduleDisplayName);
        switch (module) {
            default:
                return this.auditLogReportManager.getSolDetailsFromMasterGU();
        }
    }


}
