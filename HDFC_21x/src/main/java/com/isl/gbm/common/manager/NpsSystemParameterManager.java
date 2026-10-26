/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/

package com.isl.gbm.common.manager;

import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.logger.AuditLogger;
import com.isl.gbm.common.audit.log.logger.AuditLoggerParams;
import com.isl.gbm.common.audit.log.model.ModifiedDataDetails;
import com.isl.gbm.common.dao.NpsSystemParameterDao;
import com.isl.gbm.common.dto.NpsSystemParameterDetailsDto;
import com.isl.gbm.common.dto.NpsSystemParameterDto;
import com.isl.gbm.common.dto.wrapper.NpsSystemParameterAuditLogWrapper;
import com.isl.gbm.common.model.NpsSystemParameter;
import com.isl.gbm.common.model.NpsSystemParameterDetails;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.management.constants.FeatureNames;
import com.isl.gbm.user.management.constants.ModuleDisplayNames;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class NpsSystemParameterManager.
 */
@Component
public class NpsSystemParameterManager {

    /**
     * The nps system parameter dao.
     */
    @Autowired
    private NpsSystemParameterDao npsSystemParameterDao;

    /**
     * The audit logger.
     */
    @Autowired
    private AuditLogger auditLogger;

    /**
     * Creates the audit logger params.
     *
     * @param currentUser the current user
     * @param currentDate the current date
     * @param savedData   the saved data
     * @param model       the model
     * @return the audit logger params
     */
    private AuditLoggerParams createAuditLoggerParams(final String currentUser, final Date currentDate,
                                                      final NpsSystemParameter savedData, final NpsSystemParameter model) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(savedData.getLastModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(savedData);
        loggerParams.setNewEntity(model);
        loggerParams.setColumnDisplayName(savedData.getParameterId());
        loggerParams.setModuleDisplayName(ModuleDisplayNames.DC);
        loggerParams.setFeatureName(FeatureNames.SYSTEM_PARAMETER);
        return loggerParams;
    }

    /**
     * Gets the sys param details.
     *
     * @param parameterId the parameter id
     * @return the sys param details
     */
    public NpsSystemParameterDto getSysParamDetails(final String parameterId) {
        final NpsSystemParameter paramList = this.npsSystemParameterDao.getSysParamDetails(parameterId);

        final NpsSystemParameterDto paramDto = new NpsSystemParameterDto();
        paramDto.setParameterId(paramList.getParameterId());
        paramDto.setParameterDesc(paramList.getParameterDesc());
        paramDto.setParameterValue(paramList.getParameterValue());
        paramDto.setParameterType(paramList.getParameterType());

        return paramDto;
    }

    /**
     * Gets the sys param details list.
     *
     * @return the sys param details list
     */
    public List<NpsSystemParameterDetailsDto> getSysParamDetailsList() {
        final List<NpsSystemParameterDetailsDto> paramDtoDetList = new ArrayList<>(17);
        final List<NpsSystemParameterDetails> paramDetList = this.npsSystemParameterDao.getSysParamDetailsList();
        for (final NpsSystemParameterDetails paranDet : paramDetList) {
            final NpsSystemParameterDetailsDto paramDetDto = new NpsSystemParameterDetailsDto();
            paramDetDto.setParameterId(paranDet.getParameterId());
            paramDetDto.setParameterValues(paranDet.getParameterValues());
            paramDetDto.setParameterValDesc(paranDet.getParameterValDesc());
            paramDtoDetList.add(paramDetDto);
        }
        return paramDtoDetList;
    }


    public List<NpsSystemParameterDetailsDto> getCommonDetailsList() {
        final List<NpsSystemParameterDetailsDto> paramDtoDetList = new ArrayList<>(17);
        final List<NpsSystemParameterDetails> paramDetList = this.npsSystemParameterDao.getCommonDetailsList();
        for (final NpsSystemParameterDetails paranDet : paramDetList) {
            final NpsSystemParameterDetailsDto paramDetDto = new NpsSystemParameterDetailsDto();
            paramDetDto.setParameterId(paranDet.getParameterId());
            paramDetDto.setParameterValues(paranDet.getParameterValues());
            paramDetDto.setParameterValDesc(paranDet.getParameterValDesc());
            paramDtoDetList.add(paramDetDto);
        }
        return paramDtoDetList;
    }

    /**
     * Gets the sys param list.
     *
     * @return the sys param list
     */
    public List<NpsSystemParameterAuditLogWrapper> getSysParamList() {
        final List<NpsSystemParameter> paramList = this.npsSystemParameterDao.getSysParamList();
        final List<NpsSystemParameterAuditLogWrapper> wrapperList = new ArrayList<>(17);
        for (final NpsSystemParameter sysParam : paramList) {
            final NpsSystemParameterAuditLogWrapper wrapper = new NpsSystemParameterAuditLogWrapper();
            final NpsSystemParameterDto paramDto = new NpsSystemParameterDto();
            paramDto.setParameterId(sysParam.getParameterId());
            paramDto.setParameterDesc(sysParam.getParameterDesc());
            paramDto.setParameterValue(sysParam.getParameterValue());
            paramDto.setParameterType(sysParam.getParameterType());
            paramDto.setSchCode(sysParam.getSchCode());
            wrapper.setDto(paramDto);
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(sysParam);
            wrapper.setAuditLogDto(auditLogDto);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }


    public List<NpsSystemParameterAuditLogWrapper> getCommonList() {
        final List<NpsSystemParameter> paramList = this.npsSystemParameterDao.getCommonList();
        final List<NpsSystemParameterAuditLogWrapper> wrapperList = new ArrayList<>(17);
        for (final NpsSystemParameter sysParam : paramList) {
            final NpsSystemParameterAuditLogWrapper wrapper = new NpsSystemParameterAuditLogWrapper();
            final NpsSystemParameterDto paramDto = new NpsSystemParameterDto();
            paramDto.setParameterId(sysParam.getParameterId());
            paramDto.setParameterDesc(sysParam.getParameterDesc());
            paramDto.setParameterValue(sysParam.getParameterValue());
            paramDto.setParameterType(sysParam.getParameterType());
            paramDto.setSchCode(sysParam.getSchCode());
            wrapper.setDto(paramDto);
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(sysParam);
            wrapper.setAuditLogDto(auditLogDto);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }


    public List<NpsSystemParameterAuditLogWrapper> getSysParamModuleList(String moduleId) {
        final List<NpsSystemParameter> npsSystemParameters = this.npsSystemParameterDao.getSysParamModuleList(moduleId);
        final List<NpsSystemParameterAuditLogWrapper> wrapperList = new ArrayList<>(17);

        for (final NpsSystemParameter systemParameter : npsSystemParameters) {
            final NpsSystemParameterAuditLogWrapper wrapper = new NpsSystemParameterAuditLogWrapper();
            final NpsSystemParameterDto parameterDto = new NpsSystemParameterDto();
            parameterDto.setParameterId(systemParameter.getParameterId());
            parameterDto.setParameterDesc(systemParameter.getParameterDesc());
            parameterDto.setParameterValue(systemParameter.getParameterValue());
            parameterDto.setParameterType(systemParameter.getParameterType());
            parameterDto.setSchCode(systemParameter.getSchCode());
            wrapper.setDto(parameterDto);
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(systemParameter);
            wrapper.setAuditLogDto(auditLogDto);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    /**
     * Map dto to model.
     *
     * @param sysParamDto the sys param dto
     * @return the nps system parameter
     */
    private NpsSystemParameter mapDtoToModel(final NpsSystemParameterDto sysParamDto) {
        final NpsSystemParameter parameter = new NpsSystemParameter();
        parameter.setParameterId(sysParamDto.getParameterId());
        parameter.setParameterDesc(sysParamDto.getParameterDesc());
        parameter.setParameterType(sysParamDto.getParameterType());
        parameter.setParameterValue(sysParamDto.getParameterValue());
        parameter.setSchCode(sysParamDto.getSchCode());
        return parameter;
    }

    /**
     * Map user details.
     *
     * @param savedData the saved data
     * @param model     the model
     */
    private void mapUserDetails(final NpsSystemParameter savedData, final NpsSystemParameter model) {
        model.setVerified(savedData.getVerified());
        model.setAuditable(savedData.getAuditable());
        model.setLastModifiedBy(savedData.getLastModifiedBy());
        model.setLastModifiedDate(savedData.getLastModifiedDate());
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
     * Sets the nps system parameter dao.
     *
     * @param npsSystemParameterDao the new nps system parameter dao
     */
    public void setNpsSystemParameterDao(final NpsSystemParameterDao npsSystemParameterDao) {
        this.npsSystemParameterDao = npsSystemParameterDao;
    }

    /**
     * Update sys param.
     *
     * @param wrapperList the wrapper list
     */
    public void updateSysParam(final List<NpsSystemParameterAuditLogWrapper> wrapperList) {
        final List<NpsSystemParameter> sysParamList = new ArrayList<>(17);
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (final NpsSystemParameterAuditLogWrapper wrapper : wrapperList) {
            final NpsSystemParameterDto sysParamDto = wrapper.getDto();
            final NpsSystemParameter savedData = this.npsSystemParameterDao
                    .getSysParamDetails(sysParamDto.getParameterId());
            final NpsSystemParameter model = this.mapDtoToModel(sysParamDto);

            this.mapUserDetails(savedData, model);

            final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, model,
                    model.getParameterId());
            final Boolean unVerifiedFlag = (null == savedData.getVerified()) || ('N' == savedData.getVerified());
            if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
                model.setLastModifiedBy(currentUser);
                model.setLastModifiedDate(currentDate);
                final AuditLoggerParams helperParam = this.createAuditLoggerParams(currentUser, currentDate, savedData,
                        model);
                if (unVerifiedFlag) {
                    // newly added record
                    helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                    this.auditLogger.saveOrUpdateAuditLog(helperParam);
                    sysParamList.add(model);
                } else {
                    // already verified record
                    helperParam.setAlreadyVerifiedRecord(Boolean.TRUE);
                    this.auditLogger.saveOrUpdateAuditLog(helperParam);
                    this.auditLogger.saveOrUpdateGenModRecords(helperParam);
                }
            } else {
                this.auditLogger.revertAuditLogRecords(model, currentUser, currentDate, unVerifiedFlag);
                this.auditLogger.revertGenModRecords(model, currentUser, currentDate, unVerifiedFlag);
            }
        }
        if (CollectionUtils.isNotEmpty(sysParamList)) {
            this.npsSystemParameterDao.updateSysParam(sysParamList);
        }
    }

    /**
     * Verify sys param.
     *
     * @param wrapperList the wrapper list
     */
    public void verifySysParam(final List<NpsSystemParameterAuditLogWrapper> wrapperList) {
        final List<NpsSystemParameter> modelList = new ArrayList<>(17);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (final NpsSystemParameterAuditLogWrapper wrapper : wrapperList) {
            final NpsSystemParameterDto dto = wrapper.getDto();
            final NpsSystemParameter savedData = this.npsSystemParameterDao.getSysParamDetails(dto.getParameterId());
            final NpsSystemParameter model = this.mapDtoToModel(dto);
            this.mapUserDetails(savedData, model);
            model.setVerified(CommonConstants.YES);
            model.setVerifiedBy(user);
            model.setVerifiedDate(currentDate);
            model.setAuditable(Boolean.TRUE);
            modelList.add(model);
        }
        this.npsSystemParameterDao.verifySysParam(modelList);
        this.auditLogger.verifyAuditLog(user, currentDate, modelList, Boolean.TRUE);
        this.auditLogger.verifyGenModRecords(user, currentDate, modelList, Boolean.TRUE);

    }

}
