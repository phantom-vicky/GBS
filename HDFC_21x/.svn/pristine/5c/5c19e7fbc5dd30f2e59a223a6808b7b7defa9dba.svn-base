package com.isl.gbm.common.manager;

import com.isl.gbm.common.SchemeCodes;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.logger.AuditLogger;
import com.isl.gbm.common.audit.log.logger.AuditLoggerParams;
import com.isl.gbm.common.audit.log.model.ModifiedDataDetails;
import com.isl.gbm.common.dao.GbmSchemeParameterMasterDao;
import com.isl.gbm.common.dao.GbmSubCodeMasterDao;
import com.isl.gbm.common.dto.GbmSchemeParameterMasterDto;
import com.isl.gbm.common.dto.NpsGbmSubCodeMasterDto;
import com.isl.gbm.common.dto.wrapper.GbmSchemeParameterAuditLogWrapper;
import com.isl.gbm.common.model.GbmSchemeParameterMaster;
import com.isl.gbm.common.model.GbmSubCodeMaster;
import com.isl.gbm.common.util.CommonConstants;
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
import java.util.ResourceBundle;

@Component
public class GeneralSchemeParameterMaintenanceManager {

    /**
     * The gbm scheme paramter maintenance dao.
     */
    @Autowired
    private GbmSchemeParameterMasterDao gbmSchemeParameterMasterDao;

    /**
     * The sub code master dao.
     */
    @Autowired
    private GbmSubCodeMasterDao gbmSubCodeMasterDao;

    /**
     * The audit logger.
     */
    @Autowired
    private AuditLogger auditLogger;

    public List<GbmSchemeParameterAuditLogWrapper> getGeneralSchemeParameterMaintenance(final String schemeType) {
        final List<GbmSchemeParameterMaster> paramList = this.gbmSchemeParameterMasterDao
                .getGeneralSchemeParameterMaintenance(schemeType);
        final List<GbmSchemeParameterAuditLogWrapper> wrapperList = new ArrayList<>(paramList.size());
        for (final GbmSchemeParameterMaster param : paramList) {
            final GbmSchemeParameterAuditLogWrapper wrapper = new GbmSchemeParameterAuditLogWrapper();
            final GbmSchemeParameterMasterDto dto = this.mapModelToDto(param);
            wrapper.setDto(dto);
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(param);
            wrapper.setAuditLogDto(auditLogDto);
            wrapperList.add(wrapper);
        }
        return wrapperList;
    }

    /**
     * Creates the gbm scheme parameter maintenance master.
     *
     * @return the gbm scheme parameter maintenance master
     * @paramgbmDto the gbm dto
     */
    public Boolean getInternetEnabledForScheme(final String schemeType) {
        String scheme = null;
        final SchemeCodes schemeCode = SchemeCodes.findByKey(schemeType);
        final ResourceBundle bundle = ResourceBundle.getBundle(CommonConstants.GSPM_INTERNET_ENABLED_CONFIG_FILE_NAME);
        switch (schemeCode) {
            case CBDT:
                scheme = CommonConstants.KEY_IS_INTERNET_ENABLED_FOR_CBDT;
                break;
            case CBEC:
                scheme = CommonConstants.KEY_IS_INTERNET_ENABLED_FOR_CBEC;
                break;
            case SALESTAX:
                scheme = CommonConstants.KEY_IS_INTERNET_ENABLED_FOR_SALESTAX;
                break;
            case DOCA:
                scheme = CommonConstants.KEY_IS_INTERNET_ENABLED_FOR_DOCA;
                break;
            case RDCESS:
                scheme = CommonConstants.KEY_IS_INTERNET_ENABLED_FOR_RDCESS;
                break;
            case COMMON:
                scheme = CommonConstants.KEY_IS_INTERNET_ENABLED_FOR_COMMON;
                break;

            default:
        }

        return BooleanUtils.toBoolean(bundle.getString(scheme), "Y", "N");
    }

    public List<NpsGbmSubCodeMasterDto> getSchemeTypes() {
        final List<GbmSubCodeMaster> paramList = this.gbmSubCodeMasterDao.getGspmSchemeDetails();
        final List<NpsGbmSubCodeMasterDto> dtoList = new ArrayList<>();
        for (final GbmSubCodeMaster param : paramList) {
            final NpsGbmSubCodeMasterDto dto = this.mapSubCodeModelToDto(param);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Creates the gbm scheme parameter maintenance master.
     *
     * @param gbmDto the gbm dto
     * @return the gbm scheme parameter maintenance master
     */
    private GbmSchemeParameterMaster mapDtoToModel(final GbmSchemeParameterMasterDto gbmDto) {
        final GbmSchemeParameterMaster gbmSchemeParameterMaster = new GbmSchemeParameterMaster();
        gbmSchemeParameterMaster.setId(gbmDto.getId());
        gbmSchemeParameterMaster.setSchemeCode(gbmDto.getSchmCode());
        gbmSchemeParameterMaster.setParameterName(gbmDto.getParamName());
        gbmSchemeParameterMaster.setParameterValue(gbmDto.getParamValue());
        gbmSchemeParameterMaster.setZeroBalance(gbmDto.getIsZeroBalance());
        gbmSchemeParameterMaster.setEffectiveFromDate(gbmDto.getEffFromDate());
        gbmSchemeParameterMaster.setEffectiveToDate(gbmDto.getEffToDate());
        gbmSchemeParameterMaster.setCreatedBy(gbmDto.getCreatedBy());
        gbmSchemeParameterMaster.setCreatedDate(gbmDto.getCreatedDate());
        gbmSchemeParameterMaster.setLastModifiedBy(gbmDto.getLastModifiedBy());
        gbmSchemeParameterMaster.setLastModifiedDate(gbmDto.getLastModifiedDate());
        gbmSchemeParameterMaster.setActive(CommonConstants.YES);
        gbmSchemeParameterMaster.setVerifiedBy(gbmDto.getVerifiedBy());
        gbmSchemeParameterMaster.setVerifiedDate(gbmDto.getVerifiedDate());
        gbmSchemeParameterMaster.setVerified(gbmDto.getVerified());
        return gbmSchemeParameterMaster;
    }

    /**
     * Map model to dto.
     *
     * @param param the param
     * @return the gbm scheme parameter maintenance dto
     */
    private GbmSchemeParameterMasterDto mapModelToDto(final GbmSchemeParameterMaster param) {
        final GbmSchemeParameterMasterDto schemeDto = new GbmSchemeParameterMasterDto();
        schemeDto.setId(param.getId());
        schemeDto.setSchmCode(param.getSchemeCode());
        schemeDto.setParamName(param.getParameterName());
        schemeDto.setParamValue(param.getParameterValue());
        schemeDto.setIsZeroBalance(param.getZeroBalance());
        schemeDto.setEffFromDate(param.getEffectiveFromDate());
        schemeDto.setEffToDate(param.getEffectiveToDate());
        schemeDto.setCreatedBy(param.getCreatedBy());
        schemeDto.setCreatedDate(param.getCreatedDate());
        schemeDto.setLastModifiedBy(param.getLastModifiedBy());
        schemeDto.setLastModifiedDate(param.getLastModifiedDate());
        schemeDto.setIsActive(param.getActive());
        schemeDto.setVerified(param.getVerified());
        schemeDto.setVerifiedBy(param.getVerifiedBy());
        schemeDto.setVerifiedDate(param.getVerifiedDate());
        return schemeDto;
    }

    /**
     * Map model to dto.
     *
     * @param param the param
     * @return the gbm scheme parameter maintenance dto
     */
    private NpsGbmSubCodeMasterDto mapSubCodeModelToDto(final GbmSubCodeMaster param) {
        final NpsGbmSubCodeMasterDto subCodeDto = new NpsGbmSubCodeMasterDto();
        subCodeDto.setSubCode(param.getSubCode());
        subCodeDto.setSubCodeDescription(param.getSubCodeDescription());
        return subCodeDto;
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
     * @param gbmSchemeParameterMasterDao the gbmSchemeParameterMasterDao to set
     */
    public void setGbmSchemeParameterMasterDao(final GbmSchemeParameterMasterDao gbmSchemeParameterMasterDao) {
        this.gbmSchemeParameterMasterDao = gbmSchemeParameterMasterDao;
    }

    /**
     * @param gbmSubCodeMasterDao the gbmSubCodeMasterDao to set
     */
    public void setGbmSubCodeMasterDao(final GbmSubCodeMasterDao gbmSubCodeMasterDao) {
        this.gbmSubCodeMasterDao = gbmSubCodeMasterDao;
    }

    public void updateGeneralSchemeParameterMaintenance(final List<GbmSchemeParameterAuditLogWrapper> wrapperList) {
        final List<GbmSchemeParameterMaster> modelList = new ArrayList<>();
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        /*
         * for (final GbmSchemeParameterMasterDto dto : dtoList) {
         *
         * final GbmSchemeParameterMaster model = this.mapDtoToModel(dto);
         * model.setLastModifiedBy(currentUser); model.setLastModifiedDate(currentDate);
         * model.setVerified(CommonConstants.NO); modelList.add(model); }
         */

        for (final GbmSchemeParameterAuditLogWrapper wrapper : wrapperList) {
            final GbmSchemeParameterMasterDto sysParamDto = wrapper.getDto();
            final GbmSchemeParameterMaster savedData = this.gbmSchemeParameterMasterDao.findById(sysParamDto.getId());
            final GbmSchemeParameterMaster model = this.mapDtoToModel(sysParamDto);

            this.mapUserDetails(savedData, model);

            final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, model,
                    model.getParameterName());
            System.out.println(model.getParameterName());
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
                    modelList.add(model);
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

        if (CollectionUtils.isNotEmpty(modelList)) {
            this.gbmSchemeParameterMasterDao.update(modelList);
        }
    }

    private AuditLoggerParams createAuditLoggerParams(final String currentUser, final Date currentDate,
                                                      final GbmSchemeParameterMaster savedData, final GbmSchemeParameterMaster model) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(savedData.getLastModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(savedData);
        loggerParams.setNewEntity(model);
        loggerParams.setColumnDisplayName(savedData.getParameterName());
        loggerParams.setModuleDisplayName(ModuleDisplayNames.DC);
        loggerParams.setFeatureName(FeatureNames.GENERAL_SCHEME_PARAMETER_MAINTENANCE);
        return loggerParams;
    }


    private void mapUserDetails(final GbmSchemeParameterMaster savedData, final GbmSchemeParameterMaster model) {
        model.setVerified(savedData.getVerified());
        model.setAuditable(savedData.getAuditable());
        model.setLastModifiedBy(savedData.getLastModifiedBy());
        model.setLastModifiedDate(savedData.getLastModifiedDate());
        model.setVerifiedBy(savedData.getVerifiedBy());
        model.setVerifiedDate(savedData.getVerifiedDate());
        model.setId(savedData.getId());
    }


    /**
     * Verify nps scheme parameter.
     *
     * @param wrapperList the wrapper list
     */
    public void verifyGeneralSchemeParameterMaintenance(final List<GbmSchemeParameterAuditLogWrapper> wrapperList) {
        final List<GbmSchemeParameterMaster> modelList = new ArrayList<>();
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        /*
         * for (final GbmSchemeParameterMasterDto dto : dtoList) {
         * dto.setVerified(CommonConstants.YES); dto.setVerifiedBy(user);
         * dto.setVerifiedDate(currentDate); modelList.add(this.mapDtoToModel(dto)); }
         */


        for (final GbmSchemeParameterAuditLogWrapper wrapper : wrapperList) {
            final GbmSchemeParameterMasterDto dto = wrapper.getDto();
            final GbmSchemeParameterMaster savedData = this.gbmSchemeParameterMasterDao.findById(dto.getId());
            final GbmSchemeParameterMaster model = this.mapDtoToModel(dto);
            this.mapUserDetails(savedData, model);
            model.setVerified(CommonConstants.YES);
            model.setVerifiedBy(user);
            model.setVerifiedDate(currentDate);
            model.setAuditable(Boolean.TRUE);
            modelList.add(model);
        }
        this.auditLogger.verifyAuditLog(user, currentDate, modelList, Boolean.TRUE);
        this.auditLogger.verifyGenModRecords(user, currentDate, modelList, Boolean.TRUE);

        this.gbmSchemeParameterMasterDao.verify(modelList);
    }

}
