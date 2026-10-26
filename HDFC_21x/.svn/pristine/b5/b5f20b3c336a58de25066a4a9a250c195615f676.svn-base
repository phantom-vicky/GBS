/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.common.audit.log.manager;

import com.isl.gbm.cbs.integration.dao.impl.BankBranchDaoImpl;
import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.ObjectMapperUtils;
import com.isl.gbm.common.audit.log.common.AuditLogRecordType;
import com.isl.gbm.common.audit.log.common.OperationModes;
import com.isl.gbm.common.audit.log.dao.AuditLogDao;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.dto.AuditLogReportParams;
import com.isl.gbm.common.audit.log.model.AuditLog;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//import org.apache.commons.collections.CollectionUtils;

/**
 * The Class AuditLogReportManager.
 */
@Component
public class AuditLogReportManager {

    /**
     * The audit log dao.
     */
    @Autowired
    private AuditLogDao auditLogDao;

    @Autowired
    private BankBranchDaoImpl bankBranchDao;

    /**
     * Builds the audit log dto list.
     *
     * @param auditLogList the audit log list
     * @return the list
     */
    private List<AuditLogDto> buildAuditLogDtoList(final List<AuditLog> auditLogList) {
        List<AuditLogDto> dtoList = Collections.emptyList();
        if (CollectionUtils.isNotEmpty(auditLogList)) {
            dtoList = ObjectMapperUtils.mapAll(auditLogList, AuditLogDto.class);
        }
        return dtoList;
    }

    /**
     * Gets the audit log record types.
     *
     * @return the audit log record types
     */
    public List<AuditLogRecordType> getAuditLogRecordTypes() {
        return Arrays.asList(AuditLogRecordType.values());
    }

    /**
     * Gets the branch level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the branch level audit report details
     */
    public List<AuditLogDto> getBranchLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        final List<AuditLog> auditLogList = this.auditLogDao.getBranchLevelAuditReportDetails(auditLogReportParams);
        return this.buildAuditLogDtoList(auditLogList);
    }

    /**
     * Gets the dc parameterization details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the dc parameterization details
     */
    public List<AuditLogDto> getDcParameterizationDetails(final AuditLogReportParams auditLogReportParams) {
        final List<AuditLog> auditLogList = this.auditLogDao.getDcParameterizationDetails(auditLogReportParams);
        return this.buildAuditLogDtoList(auditLogList);
    }

    /**
     * Gets the gu level audit report details.
     *
     * @param auditLogReportParams the audit log report params
     * @return the gu level audit report details
     */
    public List<AuditLogDto> getGuLevelAuditReportDetails(final AuditLogReportParams auditLogReportParams) {
        auditLogReportParams.setSolId(UserAccountUtils.getCurrentSolId());
        final List<AuditLog> auditLogList = this.auditLogDao.getGuLevelAuditReportDetails(auditLogReportParams);
        return this.buildAuditLogDtoList(auditLogList);
    }

    /**
     * Gets the operation modes.
     *
     * @return the operation modes
     */
    public List<OperationModes> getOperationModes() {
        final List<OperationModes> operationModes = new ArrayList<>(3);
        operationModes.add(OperationModes.ADD);
        operationModes.add(OperationModes.MODIFY);
        operationModes.add(OperationModes.VERIFY);
        return operationModes;
    }

    public List<SolDetailsIntegrationDto> getSolDetailsFromMaster() {
        final List<BankBranchMaster> solMasterDetails = this.bankBranchDao.getSolDetails(UserAccountUtils.getCurrentSolId());
        final List<SolDetailsIntegrationDto> dtoList = new ArrayList<>();
        for (final BankBranchMaster master : solMasterDetails) {
            final SolDetailsIntegrationDto dto = new SolDetailsIntegrationDto();
            dto.setAddress1(master.getAddr1());
            dto.setAddress2(master.getAddr2());
            dto.setBankCode(master.getBankCode());
            dto.setBranchCode(master.getBranchCode());
            dto.setCirclOfficeName(master.getCirclOfficeName());
            dto.setCityCode(master.getCityCode());
            dto.setDivisionName(master.getDivisionName());
            dto.setMicrBankCode(master.getMicrBankCode());
            dto.setMicrBranchCode(master.getMicrBranchCode());
            dto.setMicrCertreCode(master.getMicrCertreCode());
            dto.setPanNumber(master.getPanGirNumber());
            dto.setPinCode(master.getPinCode());
            dto.setSolDescription(master.getSolDesc());
            dto.setSolId(master.getSolId());
            dto.setStateCode(master.getStateCode());
            dto.setTanNumber(master.getTaxDednDetails());
            dto.setTdsCircleName(master.getTdsCircleName());
            dto.setUniformBrCode(master.getUniformBrCode());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<SolDetailsIntegrationDto> getSolDetailsFromMasterGU() {
        final List<BankBranchMaster> solMasterDetails = this.bankBranchDao.getSolDetails(UserAccountUtils.getCurrentSolId());
        final List<SolDetailsIntegrationDto> dtoList = new ArrayList<>();
        for (final BankBranchMaster master : solMasterDetails) {
            final SolDetailsIntegrationDto dto = new SolDetailsIntegrationDto();
            dto.setAddress1(master.getAddr1());
            dto.setAddress2(master.getAddr2());
            dto.setBankCode(master.getBankCode());
            dto.setBranchCode(master.getBranchCode());
            dto.setCirclOfficeName(master.getCirclOfficeName());
            dto.setCityCode(master.getCityCode());
            dto.setDivisionName(master.getDivisionName());
            dto.setMicrBankCode(master.getMicrBankCode());
            dto.setMicrBranchCode(master.getMicrBranchCode());
            dto.setMicrCertreCode(master.getMicrCertreCode());
            dto.setPanNumber(master.getPanGirNumber());
            dto.setPinCode(master.getPinCode());
            dto.setSolDescription(master.getSolDesc());
            dto.setSolId(master.getSolId());
            dto.setStateCode(master.getStateCode());
            dto.setTanNumber(master.getTaxDednDetails());
            dto.setTdsCircleName(master.getTdsCircleName());
            dto.setUniformBrCode(master.getUniformBrCode());
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Sets the audit log dao.
     *
     * @param auditLogDao the new audit log dao
     */
    public void setAuditLogDao(final AuditLogDao auditLogDao) {
        this.auditLogDao = auditLogDao;
    }
}
