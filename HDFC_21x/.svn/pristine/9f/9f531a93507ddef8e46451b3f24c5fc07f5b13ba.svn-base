/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dto.GeneralSetupDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GeneralSetupDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.logger.AuditLogger;
import com.isl.gbm.common.audit.log.logger.AuditLoggerParams;
import com.isl.gbm.common.audit.log.model.ModifiedDataDetails;
import com.isl.gbm.user.management.constants.FeatureNames;
import com.isl.gbm.user.management.constants.ModuleDisplayNames;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class GeneralSetupManager.
 */
@Component
public class GeneralSetupManager {

    /**
     * The general setup dao.
     */
    @Autowired
    private GeneralSetupDao generalSetupDao;

    /**
     * The audit logger.
     */
    @Autowired
    private AuditLogger auditLogger;

    /**
     * Builds the general setup.
     *
     * @param generalSetupDto the general setup dto
     * @return the general setup details
     */
    private GeneralSetupDetails buildGeneralSetup(final GeneralSetupDto generalSetupDto) {
        final GeneralSetupDetails generalSetupDetails = new GeneralSetupDetails();
        generalSetupDetails.setId(generalSetupDto.getId());
        generalSetupDetails.setCreatedBy(generalSetupDto.getCreatedBy());
        generalSetupDetails.setCreatedDate(generalSetupDto.getCreatedDate());
        generalSetupDetails.setIsVerified(generalSetupDto.getIsVerified());
        generalSetupDetails.setModifiedBy(generalSetupDto.getModifiedBy());
        generalSetupDetails.setModifiedDate(generalSetupDto.getModifiedDate());
        generalSetupDetails.setParameterName(generalSetupDto.getParameterName());
        generalSetupDetails.setParameterValue(generalSetupDto.getParameterValue());
        generalSetupDetails.setVerifiedBy(generalSetupDto.getVerifiedBy());
        generalSetupDetails.setVerifiedDate(generalSetupDto.getVerifiedDate());
        return generalSetupDetails;
    }

    /**
     * Builds the general setup dto.
     *
     * @param generalSetupDetails the general setup details
     * @return the general setup dto
     */
    private GeneralSetupDto buildGeneralSetupDto(final GeneralSetupDetails generalSetupDetails) {
        final GeneralSetupDto generalSetupDto = new GeneralSetupDto();
        generalSetupDto.setCreatedBy(generalSetupDetails.getCreatedBy());
        generalSetupDto.setCreatedDate(generalSetupDetails.getCreatedDate());
        generalSetupDto.setId(generalSetupDetails.getId());
        generalSetupDto.setIsVerified(generalSetupDetails.getIsVerified());
        generalSetupDto.setModifiedBy(generalSetupDetails.getModifiedBy());
        generalSetupDto.setModifiedDate(generalSetupDetails.getModifiedDate());
        generalSetupDto.setParameterName(generalSetupDetails.getParameterName());
        generalSetupDto.setParameterValue(generalSetupDetails.getParameterValue());
        generalSetupDto.setVerifiedBy(generalSetupDetails.getVerifiedBy());
        generalSetupDto.setVerifiedDate(generalSetupDetails.getVerifiedDate());
        return generalSetupDto;
    }

    /**
     * Creates the logger params.
     *
     * @param currentUser the current user
     * @param currentDate the current date
     * @param savedData   the saved data
     * @param model       the model
     * @return the audit logger params
     */
    private AuditLoggerParams createLoggerParams(final String currentUser, final Date currentDate,
                                                 final GeneralSetupDetails savedData, final GeneralSetupDetails model) {
        final AuditLoggerParams helperParam = new AuditLoggerParams();
        helperParam.setCurrentUser(currentUser);
        helperParam.setLastModifiedUser(savedData.getModifiedBy());
        helperParam.setCurrentDate(currentDate);
        helperParam.setOldEntity(savedData);
        helperParam.setNewEntity(model);
        helperParam.setColumnDisplayName(savedData.getParameterName());
        helperParam.setModuleDisplayName(ModuleDisplayNames.CBS);
        helperParam.setFeatureName(FeatureNames.CBS_INTEGRATION_GENERAL_SETUP);
        return helperParam;
    }

    /**
     * Gets the general setup details.
     *
     * @return the general setup details
     */
    public List<GeneralSetupDtoAuditLogWrapper> getGeneralSetupDetails() {
        final List<GeneralSetupDetails> generalSetupDetailsList = this.generalSetupDao.getGeneralSetupDetails();
        final List<GeneralSetupDtoAuditLogWrapper> wrapperList = new ArrayList<>(31);
        for (final GeneralSetupDetails generalSetupDetails : generalSetupDetailsList) {
            final GeneralSetupDtoAuditLogWrapper wrapper = new GeneralSetupDtoAuditLogWrapper();
            final GeneralSetupDto generalSetupDto = this.buildGeneralSetupDto(generalSetupDetails);
            wrapper.setDto(generalSetupDto);
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(generalSetupDetails);
            wrapper.setAuditLogDto(auditLogDto);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    /**
     * Gets the general setup details.
     *
     * @param paramName the param name
     * @return the general setup details
     */
    public GeneralSetupDto getGeneralSetupDetails(final String paramName) {
        final GeneralSetupParameterKeys keys = GeneralSetupParameterKeys.findByIntTPID(paramName);
        final GeneralSetupDetails generalSetupModel = this.generalSetupDao
                .getGeneralSetupDetails(keys.getIntegrationTpId());
        return this.buildGeneralSetupDto(generalSetupModel);
    }

    /**
     * Map user details.
     *
     * @param savedData the saved data
     * @param model     the model
     */
    private void mapUserDetails(final GeneralSetupDetails savedData, final GeneralSetupDetails model) {
        model.setIsVerified(savedData.getIsVerified());
        model.setAuditable(savedData.getAuditable());
        model.setModifiedBy(savedData.getModifiedBy());
        model.setModifiedDate(savedData.getModifiedDate());
        model.setVerifiedBy(savedData.getVerifiedBy());
        model.setVerifiedDate(savedData.getVerifiedDate());
        model.setId(savedData.getId());
    }

    /**
     * Sets the audit logger.
     *
     * @param auditLogger the new audit logger
     */
    public void setAuditLogger(final AuditLogger auditLogger) {
        this.auditLogger = auditLogger;
    }

    /**
     * Sets the general setup dao.
     *
     * @param generalSetupDao the new general setup dao
     */
    public void setGeneralSetupDao(final GeneralSetupDao generalSetupDao) {
        this.generalSetupDao = generalSetupDao;
    }

    /**
     * Update.
     *
     * @param wrapperList the wrapper list
     */
    public void update(final List<GeneralSetupDtoAuditLogWrapper> wrapperList) {

        final List<GeneralSetupDetails> generalSetupDetailsList = new ArrayList<>(31);
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();

        for (final GeneralSetupDtoAuditLogWrapper wrapper : wrapperList) {
            final GeneralSetupDto dto = wrapper.getDto();
            final GeneralSetupDetails savedData = this.generalSetupDao.findById(dto.getId());
            final GeneralSetupDetails model = this.buildGeneralSetup(dto);

            this.mapUserDetails(savedData, model);

            final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, model,
                    model.getParameterName());

            if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
                model.setModifiedBy(currentUser);
                model.setModifiedDate(currentDate);
                final AuditLoggerParams helperParam = this.createLoggerParams(currentUser, currentDate, savedData,
                        model);
                if (BooleanUtils.isNotTrue(savedData.getIsVerified())) {
                    // newly added record
                    helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                    this.auditLogger.saveOrUpdateAuditLog(helperParam);
                    generalSetupDetailsList.add(model);
                } else {
                    // already verified record
                    helperParam.setAlreadyVerifiedRecord(Boolean.TRUE);
                    this.auditLogger.saveOrUpdateAuditLog(helperParam);
                    this.auditLogger.saveOrUpdateGenModRecords(helperParam);
                }
            } else {
                this.auditLogger.revertAuditLogRecords(model, currentUser, currentDate, savedData.getIsVerified());
                this.auditLogger.revertGenModRecords(model, currentUser, currentDate, savedData.getIsVerified());
            }
        }

        if (CollectionUtils.isNotEmpty(generalSetupDetailsList)) {
            this.generalSetupDao.update(generalSetupDetailsList);
        }
    }

    /**
     * Verify.
     *
     * @param wrapperList the wrapper list
     */
    public void verify(final List<GeneralSetupDtoAuditLogWrapper> wrapperList) {
        final List<GeneralSetupDetails> generalSetupDetailsList = new ArrayList<>(31);
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (final GeneralSetupDtoAuditLogWrapper wrapper : wrapperList) {
            final GeneralSetupDto dto = wrapper.getDto();
            final GeneralSetupDetails savedData = this.generalSetupDao.findById(dto.getId());
            final GeneralSetupDetails model = this.buildGeneralSetup(dto);
            this.mapUserDetails(savedData, model);
            model.setIsVerified(Boolean.TRUE);
            model.setVerifiedBy(currentUser);
            model.setVerifiedDate(currentDate);
            model.setAuditable(Boolean.TRUE);
            generalSetupDetailsList.add(model);
        }
        this.auditLogger.verifyAuditLog(currentUser, currentDate, generalSetupDetailsList, Boolean.TRUE);
        this.auditLogger.verifyGenModRecords(currentUser, currentDate, generalSetupDetailsList, Boolean.TRUE);
        this.generalSetupDao.verify(generalSetupDetailsList);
    }

}
