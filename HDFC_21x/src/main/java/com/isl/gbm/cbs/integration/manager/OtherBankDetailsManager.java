/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.OtherBankDetailsMaster;
import com.isl.gbm.common.audit.log.dto.AuditLogDto;
import com.isl.gbm.common.audit.log.logger.AuditLogger;
import com.isl.gbm.common.audit.log.logger.AuditLoggerParams;
import com.isl.gbm.common.audit.log.model.ModifiedDataDetails;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.management.constants.FeatureNames;
import com.isl.gbm.user.management.constants.ModuleDisplayNames;
import com.isl.gbm.user.management.dao.UserDao;
import com.isl.gbm.user.management.model.User;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Class OtherBankDetailsManager.
 */
@Component

public class OtherBankDetailsManager {



    /**
     * The other bank details dao.
     */
    @Autowired
    private OtherBankDetailsDao otherBankDetailsDao;

    @Autowired
    private CbsIntegerationBulkUploadDao cbsIntegerationBulkUploadDao;

    /**
     * The audit logger.
     */
    @Autowired
    private AuditLogger auditLogger;

    @Autowired
    private UserDao userDao;




    public ResponseDataDTO add(OtherBankDetailsDto dto) {
        return  this.otherBankDetailsDao.add(dto);
    }
    public ResponseDataDTO modifySelect(OtherBankDetailsDto dto) {
        return  this.otherBankDetailsDao.modifySelect(dto);
    }
    public ResponseDataDTO modifySubmit(OtherBankDetailsDto dto) {
        return  this.otherBankDetailsDao.modifySubmit(dto);
    }

    public ResponseDataDTO inquiry(OtherBankDetailsDto dto) {

        return  this.otherBankDetailsDao.inquiry(dto);
    }

    public ResponseDataDTO verifyDeleteSelect(OtherBankDetailsDto dto) {
        return  this.otherBankDetailsDao.verifyDeleteSelect(dto);
    }

    public ResponseDataDTO verifySubmit(OtherBankDetailsDto dto) {
        return  this.otherBankDetailsDao.verifySubmit(dto);
    }

    public ResponseDataDTO deleteSubmit(OtherBankDetailsDto dto) {
        return  this.otherBankDetailsDao.deleteSubmit(dto);
    }

    /**
     * Builds the audit log wrapper.
     *
     * @param otherBankDetails the other bank details
     * @return the other bank details dto audit log wrapper
     */
    private OtherBankDetailsDtoAuditLogWrapper buildAuditLogWrapper(final OtherBankDetailsMaster otherBankDetails) {
        final OtherBankDetailsDtoAuditLogWrapper wrapper = new OtherBankDetailsDtoAuditLogWrapper();
        wrapper.setDto(this.buildOtherBankDetailsDto(otherBankDetails));
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(otherBankDetails));
        return wrapper;
    }

    /**
     * Builds the other bank details.
     *
     * @param bankDetailsDto the bank details dto
     * @return the other bank details master
     */
    private OtherBankDetailsMaster buildOtherBankDetails(final OtherBankDetailsDto bankDetailsDto) {
        final OtherBankDetailsMaster otherBankDetailsMaster = new OtherBankDetailsMaster();
        otherBankDetailsMaster.setId(bankDetailsDto.getId());
        otherBankDetailsMaster.setBankCode(bankDetailsDto.getBankCode());
        otherBankDetailsMaster.setBankName(bankDetailsDto.getBankName());
        otherBankDetailsMaster.setBankShortName(bankDetailsDto.getBankShortName());
        otherBankDetailsMaster.setDeleteFlag(bankDetailsDto.getDeleteFlag());
        otherBankDetailsMaster.setCreatedBy(bankDetailsDto.getCreatedBy());
        otherBankDetailsMaster.setCreatedDate(bankDetailsDto.getCreatedDate());
        otherBankDetailsMaster.setModifiedBy(bankDetailsDto.getModifiedBy());
        otherBankDetailsMaster.setModifiedDate(bankDetailsDto.getModifiedDate());
        otherBankDetailsMaster.setVerified(bankDetailsDto.isVerified());
        otherBankDetailsMaster.setVerifiedBy(bankDetailsDto.getVerifiedBy());
        otherBankDetailsMaster.setVerifiedDate(bankDetailsDto.getVerifiedDate());
        otherBankDetailsMaster.setFileName(bankDetailsDto.getFileName()!=null ? bankDetailsDto.getFileName().toUpperCase():bankDetailsDto.getFileName());
        otherBankDetailsMaster.setAdditionalText1(bankDetailsDto.getAdditionalText1());
        otherBankDetailsMaster.setAdditionalText2(bankDetailsDto.getAdditionalText2());
        otherBankDetailsMaster.setAdditionalText3(bankDetailsDto.getAdditionalText3());
        return otherBankDetailsMaster;
    }

    /**
     * Builds the other bank details dto.
     *
     * @param bankDetails the bank details
     * @return the other bank details dto
     */
    public OtherBankDetailsDto buildOtherBankDetailsDto(final OtherBankDetailsMaster bankDetails) {
        final OtherBankDetailsDto otherBankDetailsDto = new OtherBankDetailsDto();
        otherBankDetailsDto.setId(bankDetails.getId());
        otherBankDetailsDto.setBankCode(bankDetails.getBankCode());
        otherBankDetailsDto.setBankName(bankDetails.getBankName());
        otherBankDetailsDto.setBankShortName(bankDetails.getBankShortName());
        otherBankDetailsDto.setDeleteFlag(bankDetails.getDeleteFlag());
        otherBankDetailsDto.setCreatedBy(bankDetails.getCreatedBy());
        otherBankDetailsDto.setCreatedDate(bankDetails.getCreatedDate());
        otherBankDetailsDto.setModifiedBy(bankDetails.getModifiedBy());
        otherBankDetailsDto.setModifiedDate(bankDetails.getModifiedDate());
        otherBankDetailsDto.setVerified(bankDetails.isVerified());
        otherBankDetailsDto.setVerifiedBy(bankDetails.getVerifiedBy());
        otherBankDetailsDto.setVerifiedDate(bankDetails.getVerifiedDate());
        otherBankDetailsDto.setFileName(bankDetails.getFileName());
        otherBankDetailsDto.setAddOne(bankDetails.getAddOne());
        otherBankDetailsDto.setAddTwo(bankDetails.getAddTwo());
        otherBankDetailsDto.setCity(bankDetails.getCity());
        otherBankDetailsDto.setPinCode(bankDetails.getPinCode());
        return otherBankDetailsDto;
    }

    /**
     * Delete other bank details.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO deleteOtherBankDetails(final String fileName,final String remarks) {
    /*    final List<OtherBankDetailsMaster> otherBankDetailsList = this.otherBankDetailsDao
                .getUnverifiedOtherBankDetails(fileName);

        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (OtherBankDetailsMaster master : otherBankDetailsList) {
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
        }
*/
       return this.cbsIntegerationBulkUploadDao.deleteBanks(fileName,CbsBulkUploadEnum.OBD.getCbsCode(), remarks);


    }

    /**
     * Delete other bank records.
     *
     * @param otherBankDetailsDto the other bank details dto
     */
    public void deleteOtherBankRecords(final OtherBankDetailsDto otherBankDetailsDto) {
        final OtherBankDetailsMaster savedData = this.otherBankDetailsDao
                .getOtherBankDetails(otherBankDetailsDto.getId());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final OtherBankDetailsMaster master = this.buildOtherBankDetails(otherBankDetailsDto);
        if (BooleanUtils.isNotTrue(savedData.isVerified())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.otherBankDetailsDao.deleteOtherBankDetailsId(otherBankDetailsDto.getId());
        } else {
            // already verified record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.auditLogger.deleteGenModRecords(master, user, currentDate);
            this.otherBankDetailsDao.deleteOtherBankRecords(otherBankDetailsDto.getId());
        }
    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {

        return this.otherBankDetailsDao.fileNameExists(fileName);
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
    private AuditLoggerParams getAuditLoggerParams(final OtherBankDetailsMaster oldEntity,
                                                   final OtherBankDetailsMaster newEntity, final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(oldEntity.getModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setFeatureName(FeatureNames.CBS_OTHER_BANK_DETAILS);
        return loggerParams;
    }

    /**
     * Gets the other bank details.
     *
     * @param id the id
     * @return the other bank details
     */
    public OtherBankDetailsDtoAuditLogWrapper getOtherBankDetails(final Integer id) {
        return this.buildAuditLogWrapper(this.otherBankDetailsDao.getOtherBankDetails(id));
    }

    /**
     * Gets the other bank details.
     *
     * @param bankCode the bank code
     * @param bankName the bank name
     * @return the other bank details
     */
    public List<OtherBankDetailsDto> getOtherBankDetails(final String bankCode, final String bankName) {
        final List<OtherBankDetailsMaster> otherBankDetailsList = this.otherBankDetailsDao.getOtherBankDetails(bankCode,
                bankName);
        final List<OtherBankDetailsDto> otherBankDetailsDtoList = new ArrayList<>();
        for (final OtherBankDetailsMaster otherBankDetails : otherBankDetailsList) {

            otherBankDetailsDtoList.add(this.buildOtherBankDetailsDto(otherBankDetails));

        }
        return otherBankDetailsDtoList;
    }

    /**
     * Gets the other bank details list.
     *
     * @return the other bank details list
     */
    public List<OtherBankDetailsDto> getOtherBankDetailsList() {
        final List<OtherBankDetailsMaster> otherBankDetailsList = this.otherBankDetailsDao.getOtherBankDetailsList();
        final List<OtherBankDetailsDto> otherBankDetailsDtoList = new ArrayList<>();
        for (final OtherBankDetailsMaster otherBankDetails : otherBankDetailsList) {

            otherBankDetailsDtoList.add(this.buildOtherBankDetailsDto(otherBankDetails));

        }
        return otherBankDetailsDtoList;

    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {

        return this.otherBankDetailsDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {

        return this.otherBankDetailsDao.getInquiryFiles();
    }

    /**
     * Gets the unverified other bank details.
     *
     * @param fileName the file name
     * @return the unverified other bank details
     */
    public List<OtherBankDetailsDto> getUnverifiedOtherBankDetails(final String fileName) {

        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto  = this.cbsIntegerationBulkUploadDao
                .getUnverifiedBankDetails(fileName,CbsBulkUploadEnum.OBD.getCbsCode());
        return cbsIntegerationBulkUploadDto.getOtherBankDetailsDtoList();
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<OtherBankDetailsDtoAuditLogWrapper> getUnverifiedRecords() {
        final List<OtherBankDetailsMaster> masterList = this.otherBankDetailsDao.list();
        final List<OtherBankDetailsDtoAuditLogWrapper> wrapperList = new ArrayList<>(masterList.size());
        for (final OtherBankDetailsMaster master : masterList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(master);
            if (BooleanUtils.isFalse(master.isVerified()) || (null != auditLogDto)) {
                final OtherBankDetailsDtoAuditLogWrapper wrapper = new OtherBankDetailsDtoAuditLogWrapper();
                wrapper.setDto(this.buildOtherBankDetailsDto(master));
                wrapper.setAuditLogDto(auditLogDto);
                wrapperList.add(wrapper);
            }
        }
        return wrapperList;
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<OtherBankDetailsDto> getVerifiedRecords() {
        final List<OtherBankDetailsMaster> otherBankDetailsList = this.otherBankDetailsDao.getVerifiedRecords();
        final List<OtherBankDetailsDto> otherBankDetailsDtoList = new ArrayList<>();
        for (final OtherBankDetailsMaster otherBankDetails : otherBankDetailsList) {

            otherBankDetailsDtoList.add(this.buildOtherBankDetailsDto(otherBankDetails));

        }
        return otherBankDetailsDtoList;
    }

    /**
     * List.
     *
     * @return the list
     */
    public List<OtherBankDetailsDtoAuditLogWrapper> list() {
        final List<OtherBankDetailsMaster> otherBankDetailsList = this.otherBankDetailsDao.list();
        final List<OtherBankDetailsDtoAuditLogWrapper> wrapperList = new ArrayList<>(otherBankDetailsList.size());
        for (final OtherBankDetailsMaster otherBankDetails : otherBankDetailsList) {
            if (BooleanUtils.isTrue(otherBankDetails.isAuditable()))
                wrapperList.add(this.buildAuditLogWrapper(otherBankDetails));
            else if (StringUtils.isBlank(otherBankDetails.getFileName()))
                wrapperList.add(this.buildAuditLogWrapper(otherBankDetails));
        }
        return wrapperList;
    }

    /**
     * Save details.
     *
     * @param otherBankDetailsDtoList the other bank details dto list
     */
    public ResponseDataDTO saveDetails(final List<OtherBankDetailsDto> otherBankDetailsDtoList) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setOtherBankDetailsDtoList(otherBankDetailsDtoList);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.OBD.getCbsCode());
        cbsIntegerationBulkUploadDto.setFileName(otherBankDetailsDtoList.size() > 0 ? otherBankDetailsDtoList.get(0).getFileName() : null);
        return this.cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);
       /* for (final OtherBankDetailsMaster master : otherBankDetailsList) {
            this.auditLogger.createAuditLogEntries(master, createdDate, user, null,
                    ModuleDisplayNames.CBS, FeatureNames.CBS_OTHER_BANK_DETAILS);
        }
    }
        */
    }

    /**
     * Save or update.
     *
     * @param bankDetailsDto the bank details dto
     */
        public void saveOrUpdate( final OtherBankDetailsDto bankDetailsDto){
            final OtherBankDetailsMaster master = this.buildOtherBankDetails(bankDetailsDto);
            master.setCreatedBy(UserAccountUtils.getCurrentUsername());
            master.setCreatedDate(new Date());
            this.otherBankDetailsDao.saveOrUpdate(master);
            this.auditLogger.createAuditLogEntries(master, master.getCreatedDate(), master.getCreatedBy(), null,
                    ModuleDisplayNames.CBS, FeatureNames.CBS_OTHER_BANK_DETAILS);
        }

        /**
         * Sets the audit logger.
         *
         * @param auditLogger the new audit logger
         */
        public void setAuditLogger ( final AuditLogger auditLogger){
            this.auditLogger = auditLogger;
        }

        /**
         * Sets the other bank details dao.
         *
         * @param otherBankDetailsDao the new other bank details dao
         */
        public void setOtherBankDetailsDao ( final OtherBankDetailsDao otherBankDetailsDao){
            this.otherBankDetailsDao = otherBankDetailsDao;
        }

        /**
         * Update.
         *
         * @param dto the dto
         */
        public void update ( final OtherBankDetailsDto dto){
            final OtherBankDetailsMaster savedData = this.otherBankDetailsDao.getOtherBankDetails(dto.getId());
            final String currentUser = UserAccountUtils.getCurrentUsername();
            final Date currentDate = new Date();
            final OtherBankDetailsMaster master = this.buildOtherBankDetails(dto);
            final AuditLoggerParams helperParam = this.getAuditLoggerParams(savedData, master, currentUser, currentDate);
            master.setAuditable(savedData.isAuditable());
            final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, master,
                    null);
            if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
                master.setModifiedBy(currentUser);
                master.setModifiedDate(currentDate);
                if (BooleanUtils.isNotTrue(savedData.isVerified())) {
                    // newly added record
                    helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                    this.auditLogger.saveOrUpdateAuditLog(helperParam);
                    this.otherBankDetailsDao.saveOrUpdate(master);
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
         * Verify other bank details.
         *
         * @param fileName the file name
         */

        public ResponseDataDTO verifyOtherBankDetails ( final String fileName){

           /* final List<OtherBankDetailsMaster> otherBankDetailsList = this.otherBankDetailsDao
                    .getUnverifiedOtherBankDetails(fileName);
            final String user = UserAccountUtils.getCurrentUsername();
            final Date currentDate = new Date();
            for (OtherBankDetailsMaster master : otherBankDetailsList) {
                this.auditLogger.verifyAuditLog(user, currentDate, master);
            }*/
           return this.cbsIntegerationBulkUploadDao.verifyBanks(fileName,CbsBulkUploadEnum.OBD.getCbsCode());
        }

        /**
         * Verify other bank entry record.
         *
         * @param otherBankDetailsDto the other bank details dto
         */
        public void verifyOtherBankEntryRecord ( final OtherBankDetailsDto otherBankDetailsDto){
            final OtherBankDetailsMaster master = new OtherBankDetailsMaster();
            final String user = UserAccountUtils.getCurrentUsername();
            final Date currentDate = new Date();
            master.setId(otherBankDetailsDto.getId());
            master.setVerifiedDate(currentDate);
            master.setVerifiedBy(user);
            this.auditLogger.verifyAuditLog(user, currentDate, master);
            this.auditLogger.verifyGenModRecords(user, currentDate, master);
            this.otherBankDetailsDao.verify(master);
        }

        public void updateUserLastSuccessLogin () {
            User user = this.userDao.getUser(UserAccountUtils.getCurrentUserID());
            user.setLastSuccessLogin(new Date());
            user.setLoggedInFlag(CommonConstants.YES);
            this.userDao.save(user);
        }

}
