/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankBranchesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.OtherBankBranchesMaster;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.logger.AuditLogger;
import com.isl.gbm.common.audit.log.logger.AuditLoggerParams;
import com.isl.gbm.common.audit.log.model.ModifiedDataDetails;
import com.isl.gbm.common.dto.ResponseDataDTO;
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
 * The Class OtherBankBranchesManager.
 */
@Component
public class OtherBankBranchesManager {

    /**
     * The other bank branches dao.
     */
    @Autowired
    private OtherBankBranchesDao otherBankBranchesDao;

    @Autowired
    private CbsIntegerationBulkUploadDao cbsIntegerationBulkUploadDao;

    /**
     * The audit logger.
     */
    @Autowired
    private AuditLogger auditLogger;

    /**
     * Builds the audit log wrapper.
     *
     * @param master the master
     * @return the other bank branches dto audit log wrapper
     */
    private OtherBankBranchesDtoAuditLogWrapper buildAuditLogWrapper(final OtherBankBranchesMaster master) {
        final OtherBankBranchesDtoAuditLogWrapper wrapper = new OtherBankBranchesDtoAuditLogWrapper();
        wrapper.setDto(this.buildOtherBankBranchesDto(master));
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(master));
        return wrapper;
    }

    /**
     * Builds the other bank branches details.
     *
     * @param otherBankBranchesDto the other bank branches dto
     * @return the other bank branches master
     */
    private OtherBankBranchesMaster buildOtherBankBranchesDetails(final OtherBankBranchesDto otherBankBranchesDto) {
        final OtherBankBranchesMaster details = new OtherBankBranchesMaster();
        details.setId(otherBankBranchesDto.getBranchId());
        details.setBranchCode(otherBankBranchesDto.getBranchCode());
        details.setBankCode(otherBankBranchesDto.getBankCode());
        details.setMicrBankCode(otherBankBranchesDto.getMicrBankCode());
        details.setMicrBranchCode(otherBankBranchesDto.getMicrBranchCode());
        details.setMicrCentreCode(otherBankBranchesDto.getMicrCentreCode());
        details.setBranchAddr1(otherBankBranchesDto.getBranchAddr1());
        details.setBranchAddr2(otherBankBranchesDto.getBranchAddr2());
        details.setBranchAddr3(otherBankBranchesDto.getBranchAddr3());
        details.setBranchName(otherBankBranchesDto.getBranchName());
        details.setBranchCityCode(otherBankBranchesDto.getBranchCityCode());
        details.setBranchPinCode(otherBankBranchesDto.getBranchPinCode());
        details.setBranchStateCode(otherBankBranchesDto.getBranchStateCode());
        details.setAdditionalText1(otherBankBranchesDto.getAdditionalText1());
        details.setAdditionalText2(otherBankBranchesDto.getAdditionalText2());
        details.setAdditionalText3(otherBankBranchesDto.getAdditionalText3());
        details.setCreatedBy(otherBankBranchesDto.getCreatedBy());
        details.setCreatedDate(otherBankBranchesDto.getCreatedDate());
        details.setFilename(otherBankBranchesDto.getFilename());
        details.setLastModifiedBy(otherBankBranchesDto.getLastModifiedBy());
        details.setLastModifiedDate(otherBankBranchesDto.getLastModifiedDate());
        details.setVerified(otherBankBranchesDto.isVerified());
        details.setVerifiedBy(otherBankBranchesDto.getVerifiedBy());
        details.setVerifiedDate(otherBankBranchesDto.getVerifiedDate());
        details.setDelFlg(otherBankBranchesDto.getDelFlg());
        return details;
    }

    /**
     * Builds the other bank branches dto.
     *
     * @param details the details
     * @return the other bank branches dto
     */
    private OtherBankBranchesDto buildOtherBankBranchesDto(final OtherBankBranchesMaster details) {
        final OtherBankBranchesDto dto = new OtherBankBranchesDto();
        dto.setBranchId(details.getId());
        dto.setBranchCode(details.getBranchCode());
        dto.setBankCode(details.getBankCode());
        dto.setMicrBankCode(details.getMicrBankCode());
        dto.setMicrBranchCode(details.getMicrBranchCode());
        dto.setMicrCentreCode(details.getMicrCentreCode());
        dto.setBranchAddr1(details.getBranchAddr1());
        dto.setBranchAddr2(details.getBranchAddr2());
        dto.setBranchAddr3(details.getBranchAddr3());
        dto.setBranchName(details.getBranchName());
        dto.setBranchCityCode(details.getBranchCityCode());
        dto.setBranchPinCode(details.getBranchPinCode());
        dto.setBranchStateCode(details.getBranchStateCode());
        dto.setAdditionalText1(details.getAdditionalText1());
        dto.setAdditionalText2(details.getAdditionalText2());
        dto.setAdditionalText3(details.getAdditionalText3());
        dto.setCreatedBy(details.getCreatedBy());
        dto.setCreatedDate(details.getCreatedDate());
        dto.setFilename(details.getFilename());
        dto.setLastModifiedBy(details.getLastModifiedBy());
        dto.setLastModifiedDate(details.getLastModifiedDate());
        dto.setVerified(details.isVerified());
        dto.setVerifiedBy(details.getVerifiedBy());
        dto.setVerifiedDate(details.getVerifiedDate());
        dto.setDelFlg(details.getDelFlg());
        return dto;
    }

    /**
     * Builds the other bank branches dto list.
     *
     * @param detailsList the details list
     * @return the list
     */
    private List<OtherBankBranchesDto> buildOtherBankBranchesDtoList(final List<OtherBankBranchesMaster> detailsList) {
        final List<OtherBankBranchesDto> dtoList = new ArrayList<>(CollectionUtils.size(detailsList));
        for (final OtherBankBranchesMaster details : detailsList) {
            dtoList.add(this.buildOtherBankBranchesDto(details));
        }
        return dtoList;
    }

    /**
     * Creates the.
     *
     * @param dto the dto
     */
    public void create(final OtherBankBranchesDto dto) {
        final OtherBankBranchesMaster master = this.buildOtherBankBranchesDetails(dto);
        master.setCreatedBy(UserAccountUtils.getCurrentUsername());
        master.setCreatedDate(new Date());
        this.otherBankBranchesDao.saveOrUpdate(master);
        this.auditLogger.createAuditLogEntries(master, master.getCreatedDate(), master.getCreatedBy(), null,
                ModuleDisplayNames.CBS, FeatureNames.CBS_BRANCHES_OF_OTHER_BANK);
    }

    public ResponseDataDTO add(OtherBankBranchesDto dto) {
        return  this.otherBankBranchesDao.add(dto);
    }
    public ResponseDataDTO modifySelect(OtherBankBranchesDto dto) {
        return  this.otherBankBranchesDao.modifySelect(dto);
    }
    public ResponseDataDTO modifySubmit(OtherBankBranchesDto dto) {
        return  this.otherBankBranchesDao.modifySubmit(dto);
    }

    public ResponseDataDTO inquiry(OtherBankBranchesDto dto) {
        return  this.otherBankBranchesDao.inquiry(dto);
    }

    public ResponseDataDTO verifyDeleteSelect(OtherBankBranchesDto dto) {
        return  this.otherBankBranchesDao.verifyDeleteSelect(dto);
    }

    public ResponseDataDTO verifySubmit(OtherBankBranchesDto dto) {
        return  this.otherBankBranchesDao.verifySubmit(dto);
    }

    public ResponseDataDTO deleteSubmit(OtherBankBranchesDto dto) {
        return  this.otherBankBranchesDao.deleteSubmit(dto);
    }
    /**
     * Delete.
     *
     * @param dto the dto
     */
    public void delete(final OtherBankBranchesDto dto) {
        final OtherBankBranchesMaster savedData = this.otherBankBranchesDao
                .getOtherBankBranchesDetails(dto.getBranchId());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final OtherBankBranchesMaster master = this.buildOtherBankBranchesDetails(dto);
        if (BooleanUtils.isNotTrue(savedData.isVerified())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.otherBankBranchesDao.delete(dto.getBranchId());
        } else {
            // already verified record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.auditLogger.deleteGenModRecords(master, user, currentDate);
        }
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO delete(final String fileName,final String remarks) {

       /* final List<OtherBankBranchesMaster> otherBankBranchList = this.otherBankBranchesDao
                .getUnverifiedRecords(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (OtherBankBranchesMaster master : otherBankBranchList) {
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
        }*/
       return this.cbsIntegerationBulkUploadDao.deleteBanks(fileName,CbsBulkUploadEnum.BOB.getCbsCode(),remarks);
    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {
        return this.otherBankBranchesDao.fileNameExists(fileName);
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
    private AuditLoggerParams getAuditLoggerParams(final OtherBankBranchesMaster oldEntity,
                                                   final OtherBankBranchesMaster newEntity, final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(oldEntity.getLastModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setFeatureName(FeatureNames.CBS_BRANCHES_OF_OTHER_BANK);
        return loggerParams;
    }

    /**
     * Gets the other bank branches details.
     *
     * @param branchId the branch id
     * @return the other bank branches details
     */
    public OtherBankBranchesDtoAuditLogWrapper getOtherBankBranchesDetails(final Integer branchId) {
        return this.buildAuditLogWrapper(this.otherBankBranchesDao.getOtherBankBranchesDetails(branchId));
    }

    /**
     * Gets the other bank branches details.
     *
     * @param branchCode the branch code
     * @param bankCode   the bank code
     * @return the other bank branches details
     */
    public List<OtherBankBranchesDto> getOtherBankBranchesDetails(final String branchCode, final String bankCode) {
        return this.buildOtherBankBranchesDtoList(
                this.otherBankBranchesDao.getOtherBankBranchesDetails(branchCode, bankCode));
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {
        return this.otherBankBranchesDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {
        return this.otherBankBranchesDao.getInquiryFiles();
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<OtherBankBranchesDtoAuditLogWrapper> getUnverifiedRecords() {
        final List<OtherBankBranchesMaster> masterList = this.otherBankBranchesDao.list();
        final List<OtherBankBranchesDtoAuditLogWrapper> wrapperList = new ArrayList<>(masterList.size());
        for (final OtherBankBranchesMaster master : masterList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(master);
            if (BooleanUtils.isFalse(master.isVerified()) || (null != auditLogDto)) {
                final OtherBankBranchesDtoAuditLogWrapper wrapper = new OtherBankBranchesDtoAuditLogWrapper();
                wrapper.setDto(this.buildOtherBankBranchesDto(master));
                wrapper.setAuditLogDto(auditLogDto);
                wrapperList.add(wrapper);
            }
        }
        return wrapperList;
    }

    /**
     * Gets the unverified records.
     *
     * @param fileName the file name
     * @return the unverified records
     */
    public List<OtherBankBranchesDto> getUnverifiedRecords(final String fileName) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = this.cbsIntegerationBulkUploadDao.getUnverifiedBankDetails(fileName,CbsBulkUploadEnum.BOB.getCbsCode());

        return cbsIntegerationBulkUploadDto.getOtherBankBranchesDtoList();
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<OtherBankBranchesDto> getVerifiedRecords() {
        return this.buildOtherBankBranchesDtoList(this.otherBankBranchesDao.getVerifiedRecords());
    }

    public List<OtherBankBranchesDto> getVerifiedBankBranchesList(final String bankCode, final String branchCode) {
        return this.buildOtherBankBranchesDtoList(this.otherBankBranchesDao.getVerifiedBankBranchesList(bankCode, branchCode));
    }

    /**
     * List.
     *
     * @return the list
     */
    public List<OtherBankBranchesDtoAuditLogWrapper> list() {
        final List<OtherBankBranchesMaster> detailsList = this.otherBankBranchesDao.list();
        final List<OtherBankBranchesDtoAuditLogWrapper> wrapperList = new ArrayList<>(detailsList.size());
        for (final OtherBankBranchesMaster master : detailsList) {
            wrapperList.add(this.buildAuditLogWrapper(master));
        }
        return wrapperList;
    }

    /**
     * Off acc place holder list.
     *
     * @param detailsList the details list
     * @return the list
     */
    public List<OtherBankBranchesDto> offAccPlaceHolderList(final List<OtherBankBranchesMaster> detailsList) {
        final List<OtherBankBranchesDto> dtoList = new ArrayList<>(CollectionUtils.size(detailsList));
        for (final OtherBankBranchesMaster masterDetails : detailsList) {
            final OtherBankBranchesDto dto = new OtherBankBranchesDto();
            dto.setBranchId(masterDetails.getId());
            dto.setBranchCode(masterDetails.getBranchCode());
            dto.setBankCode(masterDetails.getBankCode());
            dto.setMicrBankCode(masterDetails.getMicrBankCode());
            dto.setMicrBranchCode(masterDetails.getMicrBranchCode());
            dto.setMicrCentreCode(masterDetails.getMicrCentreCode());
            dto.setBranchAddr1(masterDetails.getBranchAddr1());
            dto.setBranchAddr2(masterDetails.getBranchAddr2());
            dto.setBranchAddr3(masterDetails.getBranchAddr3());
            dto.setBranchName(masterDetails.getBranchName());
            dto.setBranchCityCode(masterDetails.getBranchCityCode());
            dto.setBranchPinCode(masterDetails.getBranchPinCode());
            dto.setBranchStateCode(masterDetails.getBranchStateCode());
            dto.setAdditionalText1(masterDetails.getAdditionalText1());
            dto.setAdditionalText2(masterDetails.getAdditionalText2());
            dto.setAdditionalText3(masterDetails.getAdditionalText3());
            dto.setCreatedBy(masterDetails.getCreatedBy());
            dto.setCreatedDate(masterDetails.getCreatedDate());
            dto.setFilename(masterDetails.getFilename());
            dto.setLastModifiedBy(masterDetails.getLastModifiedBy());
            dto.setLastModifiedDate(masterDetails.getLastModifiedDate());
            dto.setVerified(masterDetails.isVerified());
            dto.setVerifiedBy(masterDetails.getVerifiedBy());
            dto.setVerifiedDate(masterDetails.getVerifiedDate());
            dtoList.add(dto);

        }
        return dtoList;
    }

    /**
     * Other bank branches bulk upload.
     *
     * @param dtoList the dto list
     */
    public ResponseDataDTO otherBankBranchesBulkUpload(final List<OtherBankBranchesDto> dtoList) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setOtherBankBranchesDtoList(dtoList);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.BOB.getCbsCode());
        cbsIntegerationBulkUploadDto.setFileName(dtoList.size() > 0 ? dtoList.get(0).getFilename() : null);
        return this.cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);
       /* for (OtherBankBranchesMaster otherBankBranchesMaster : otherBankMaster) {
            this.auditLogger.createAuditLogEntries(otherBankBranchesMaster, currentDate, user, null,
                    ModuleDisplayNames.CBS, FeatureNames.CBS_BRANCHES_OF_OTHER_BANK);
        }*/
    }

    /**
     * Other bank branches master.
     *
     * @param dtoList the dto list
     * @return the other bank branches master
     */
    public OtherBankBranchesMaster otherBankBranchesMaster(final OtherBankBranchesDto dtoList) {
        final OtherBankBranchesMaster masterDtls = new OtherBankBranchesMaster();
        masterDtls.setId(dtoList.getBranchId());
        masterDtls.setBranchCode(dtoList.getBranchCode());
        masterDtls.setBankCode(dtoList.getBankCode());
        masterDtls.setMicrBankCode(dtoList.getMicrBankCode());
        masterDtls.setMicrBranchCode(dtoList.getMicrBranchCode());
        masterDtls.setMicrCentreCode(dtoList.getMicrCentreCode());
        masterDtls.setBranchAddr1(dtoList.getBranchAddr1());
        masterDtls.setBranchAddr2(dtoList.getBranchAddr2());
        masterDtls.setBranchAddr3(dtoList.getBranchAddr3());
        masterDtls.setBranchName(dtoList.getBranchName());
        masterDtls.setBranchCityCode(dtoList.getBranchCityCode());
        masterDtls.setBranchPinCode(dtoList.getBranchPinCode());
        masterDtls.setBranchStateCode(dtoList.getBranchStateCode());
        masterDtls.setAdditionalText1(dtoList.getAdditionalText1());
        masterDtls.setAdditionalText2(dtoList.getAdditionalText2());
        masterDtls.setAdditionalText3(dtoList.getAdditionalText3());
        masterDtls.setCreatedBy(dtoList.getCreatedBy());
        masterDtls.setCreatedDate(new Date());
        masterDtls.setFilename(dtoList.getFilename()!=null ? dtoList.getFilename().toUpperCase():dtoList.getFilename());
        masterDtls.setLastModifiedBy(dtoList.getLastModifiedBy());
        masterDtls.setLastModifiedDate(dtoList.getLastModifiedDate());
        masterDtls.setVerified(dtoList.isVerified());
        masterDtls.setVerifiedBy(dtoList.getVerifiedBy());
        masterDtls.setVerifiedDate(dtoList.getVerifiedDate());
        return masterDtls;

    }

    /**
     * Record list.
     *
     * @return the list
     */
    public List<OtherBankBranchesDto> recordList() {
        final List<OtherBankBranchesMaster> detailsList = this.otherBankBranchesDao.recordList();
        final List<OtherBankBranchesDto> dtoList = new ArrayList<>();
        for (final OtherBankBranchesMaster master : detailsList) {
            final OtherBankBranchesDto dto = new OtherBankBranchesDto();
            dto.setBranchId(master.getId());
            dto.setBranchCode(master.getBranchCode());
            dto.setBankCode(master.getBankCode());
            dto.setMicrBankCode(master.getMicrBankCode());
            dto.setMicrBranchCode(master.getMicrBranchCode());
            dto.setMicrCentreCode(master.getMicrCentreCode());
            dto.setBranchAddr1(master.getBranchAddr1());
            dto.setBranchAddr2(master.getBranchAddr2());
            dto.setBranchAddr3(master.getBranchAddr3());
            dto.setBranchName(master.getBranchName());
            dto.setBranchCityCode(master.getBranchCityCode());
            dto.setBranchPinCode(master.getBranchPinCode());
            dto.setBranchStateCode(master.getBranchStateCode());
            dto.setAdditionalText1(master.getAdditionalText1());
            dto.setAdditionalText2(master.getAdditionalText2());
            dto.setAdditionalText3(master.getAdditionalText3());
            dto.setCreatedBy(master.getCreatedBy());
            dto.setCreatedDate(master.getCreatedDate());
            dto.setFilename(master.getFilename());
            dto.setLastModifiedBy(master.getLastModifiedBy());
            dto.setLastModifiedDate(master.getLastModifiedDate());
            dto.setVerified(master.isVerified());
            dto.setVerifiedBy(master.getVerifiedBy());
            dto.setVerifiedDate(master.getVerifiedDate());
            dto.setBankIfscCode(master.getBankIfscCode());
            dtoList.add(dto);

        }
        return dtoList;

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
     * Sets the other bank branches dao.
     *
     * @param otherBankBranchesDao the new other bank branches dao
     */
    public void setOtherBankBranchesDao(final OtherBankBranchesDao otherBankBranchesDao) {
        this.otherBankBranchesDao = otherBankBranchesDao;
    }

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(final OtherBankBranchesDto dto) {
        final OtherBankBranchesMaster savedData = this.otherBankBranchesDao
                .getOtherBankBranchesDetails(dto.getBranchId());
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final OtherBankBranchesMaster master = this.buildOtherBankBranchesDetails(dto);
        final AuditLoggerParams helperParam = this.getAuditLoggerParams(savedData, master, currentUser, currentDate);
        master.setAuditable(savedData.getAuditable());
        final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, master,
                null);
        if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
            master.setLastModifiedBy(currentUser);
            master.setLastModifiedDate(currentDate);
            if (BooleanUtils.isNotTrue(savedData.isVerified())) {
                // newly added record
                helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.otherBankBranchesDao.saveOrUpdate(master);
            } else {
                // already verified record
                helperParam.setAlreadyVerifiedRecord(Boolean.TRUE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.auditLogger.saveOrUpdateGenModRecords(helperParam);
            }
        } else {
            this.auditLogger.revertAuditLogRecords(master, currentUser, currentDate, savedData.isVerified());
            this.auditLogger.revertGenModRecords(master, currentUser, currentDate, savedData.isVerified());
        }
    }

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(final OtherBankBranchesDto dto) {
        final OtherBankBranchesMaster master = this.buildOtherBankBranchesDetails(dto);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        master.setId(dto.getBranchId());
        master.setVerifiedDate(currentDate);
        master.setVerifiedBy(user);
        this.auditLogger.verifyAuditLog(user, currentDate, master);
        this.auditLogger.verifyGenModRecords(user, currentDate, master);
        this.otherBankBranchesDao.verify(master);
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(final String fileName) {
       /* final List<OtherBankBranchesMaster> otherBankBranchList = this.otherBankBranchesDao
                .getUnverifiedRecords(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (OtherBankBranchesMaster otherBankBranchMaster : otherBankBranchList) {
            this.auditLogger.verifyAuditLog(user, currentDate, otherBankBranchMaster);
        }*/
       return this.cbsIntegerationBulkUploadDao.verifyBanks(fileName,CbsBulkUploadEnum.BOB.getCbsCode());
    }



}
