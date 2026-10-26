/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dao.UserDetailsDao;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GbmUserDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.GbmUserDetails;
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
import java.util.stream.Collectors;

/**
 * The Class UserDetailsManager.
 */
@Component
public class UserDetailsManager {

    /**
     * The user details dao.
     */
    @Autowired
    private UserDetailsDao userDetailsDao;
    @Autowired
    private CbsIntegerationBulkUploadDao cbsIntegerationBulkUploadDao;
    @Autowired
    private AuditLogger auditLogger;

    public void setAuditLogger(AuditLogger auditLogger) {
        this.auditLogger = auditLogger;
    }




    public ResponseDataDTO add(final GbmUserDetailsDto dto)
    {
        return this.userDetailsDao.add(dto);
    }

    public ResponseDataDTO modifySelect(final GbmUserDetailsDto dto) {
        return this.userDetailsDao.modifySelect(dto);
    }

    public ResponseDataDTO modifySubmit(final GbmUserDetailsDto dto) {
        return this.userDetailsDao.modifySubmit(dto);
    }


    public ResponseDataDTO inquiry(final GbmUserDetailsDto dto) {

        return this.userDetailsDao.inquiry(dto);
    }


    public ResponseDataDTO verifyDeleteSelect(final GbmUserDetailsDto dto) {
        return this.userDetailsDao.verifyDeleteSelect(dto);
    }


    public ResponseDataDTO verifySubmit(final GbmUserDetailsDto dto) {
        return this.userDetailsDao.verifySubmit(dto);
    }

    public ResponseDataDTO deleteSubmit(final GbmUserDetailsDto dto) {
        return this.userDetailsDao.deleteSubmit(dto);
    }



    private GbmUserDetailsDtoAuditLogWrapper buildAuditLogWrapper(final GbmUserDetails userDetails) {
        final GbmUserDetailsDtoAuditLogWrapper wrapper = new GbmUserDetailsDtoAuditLogWrapper();
        wrapper.setDto(this.buildUserDetailsDto(userDetails));
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(userDetails));
        return wrapper;
    }

    /**
     * Builds the gbm user details.
     *
     * @param gbmUserDetailsDto the gbm user details dto
     * @return the gbm user details
     */
    private GbmUserDetails buildGbmUserDetails(final GbmUserDetailsDto gbmUserDetailsDto) {
        final GbmUserDetails details = new GbmUserDetails();
        details.setId(gbmUserDetailsDto.getGbmUserDetailsId());
        details.setAdditionalText1(gbmUserDetailsDto.getAdditionalText1());
        details.setAdditionalText2(gbmUserDetailsDto.getAdditionalText2());
        details.setAdditionalText3(gbmUserDetailsDto.getAdditionalText3());
        details.setCreatedBy(gbmUserDetailsDto.getCreatedBy());
        details.setCreatedDate(gbmUserDetailsDto.getCreatedDate());
        details.setFileName(gbmUserDetailsDto.getFileName() !=null ? gbmUserDetailsDto.getFileName().toUpperCase():gbmUserDetailsDto.getFileName());
        details.setModifiedBy(gbmUserDetailsDto.getModifiedBy());
        details.setModifiedDate(gbmUserDetailsDto.getModifiedDate());
        details.setVerified(gbmUserDetailsDto.getVerified());
        details.setVerifiedBy(gbmUserDetailsDto.getVerifiedBy());
        details.setVerifiedDate(gbmUserDetailsDto.getVerifiedDate());
        details.setDeleted(gbmUserDetailsDto.getDeleted());
        details.setDefaultTermId(gbmUserDetailsDto.getDefaultTermId());
        details.setNewUserFlag(gbmUserDetailsDto.getNewUserFlag());
        details.setSolId(gbmUserDetailsDto.getSolId());
        details.setUserAccountExpiryDate(gbmUserDetailsDto.getUserAccountExpiryDate());
        details.setUserApplicationName(gbmUserDetailsDto.getUserApplicationName());
        details.setUserId(gbmUserDetailsDto.getUserId());
        details.setUserMaxInactiveTime(gbmUserDetailsDto.getUserMaxInactiveTime());
        details.setUserPassword(gbmUserDetailsDto.getUserPassword());
        details.setUserPasswordExpiryDate(gbmUserDetailsDto.getUserPasswordExpiryDate());
        details.setUserSolTenor(gbmUserDetailsDto.getUserSolTenor());
        details.setUserWorkClass(gbmUserDetailsDto.getUserWorkClass());
        details.setVirtualFlag(gbmUserDetailsDto.getVirtualFlag());

        return details;
    }

    /**
     * Creates the.
     *
     * @param dto the dto
     */
    public void create(final GbmUserDetailsDto dto) {
        dto.setCreatedBy(UserAccountUtils.getCurrentUsername());
        dto.setCreatedDate(new Date());
        dto.setDeleted(Boolean.FALSE);
        dto.setVerified(Boolean.FALSE);
        final GbmUserDetails master = this.buildGbmUserDetails(dto);
        int id = this.userDetailsDao.saveOrUpdate(master);
        master.setId(id);
        this.auditLogger.createAuditLogEntries(master, master.getCreatedDate(), master.getCreatedBy(), null,
                ModuleDisplayNames.CBS, FeatureNames.CBS_MAINTENANCE_OF_CALENDAR_ENTRIES);


    }

    /**
     * Delete.
     *
     * @param userDetailsDto the user details dto
     */
    public void delete(final GbmUserDetailsDto userDetailsDto) {

        final GbmUserDetails savedData = this.userDetailsDao.getUserWorkDetails(userDetailsDto.getGbmUserDetailsId());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final GbmUserDetails master = this.buildGbmUserDetails(userDetailsDto);
        if (BooleanUtils.isNotTrue(savedData.getVerified())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            master.setDeleted(Boolean.TRUE);
            this.userDetailsDao.deleteUsingId(master.getId());
        } else {
            // already verified record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.auditLogger.deleteGenModRecords(master, user, currentDate);
        }


        //userDetailsDto.setDeleted(Boolean.TRUE);
        //this.saveOrUpdate(userDetailsDto);
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO delete(final String fileName,final String remarks ) {

        /*final List<GbmUserDetails> detailsList = this.userDetailsDao.getUnverifiedUserDetails(fileName);

        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (GbmUserDetails master : detailsList) {
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
        }*/
       return this.cbsIntegerationBulkUploadDao.deleteBanks(fileName,CbsBulkUploadEnum.UWCI.getCbsCode(), remarks);
    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {
        return this.userDetailsDao.fileNameExists(fileName);
    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    public List<GbmUserDetailsDto> getAllRecords() {
        final List<GbmUserDetails> detailsList = this.userDetailsDao.getAllRecords();
        return this.getUserDetailsDtoList(detailsList);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {
        return this.userDetailsDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {
        return this.userDetailsDao.getInquiryFiles();
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<GbmUserDetailsDtoAuditLogWrapper> getUnverifiedRecords() {
        List<GbmUserDetails> masterList = this.userDetailsDao.list();
        List<GbmUserDetails> mastersListNonDeleted = masterList.stream().filter(a -> a.getDeleted().equals(Boolean.FALSE)).collect(Collectors.toList());
        final List<GbmUserDetailsDtoAuditLogWrapper> wrapperList = new ArrayList<>(masterList.size());
        for (final GbmUserDetails master : mastersListNonDeleted) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(master);
            if (BooleanUtils.isFalse(master.getVerified()) || (null != auditLogDto)) {
                final GbmUserDetailsDtoAuditLogWrapper wrapper = new GbmUserDetailsDtoAuditLogWrapper();
                wrapper.setDto(this.buildUserDetailsDto(master));
                wrapper.setAuditLogDto(auditLogDto);
                wrapperList.add(wrapper);
            }
        }

        return wrapperList;
    }

    /**
     * Gets the unverified user details.
     *
     * @param fileName the file name
     * @return the unverified user details
     */
    public List<GbmUserDetailsDto> getUnverifiedUserDetails(final String fileName) {
       CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = this.cbsIntegerationBulkUploadDao.getUnverifiedBankDetails(fileName,CbsBulkUploadEnum.UWCI.getCbsCode());
        return cbsIntegerationBulkUploadDto.getUserDetailsDtoList();
    }

    /**
     * Gets the user.
     *
     * @param userId the user id
     * @return the user
     */
    public List<GbmUserDetailsDto> getUser(final String userId) {
        return this.getUserDetailsDtoList(this.userDetailsDao.getUser(userId));
    }

    /**
     * Gets the user details dto list.
     *
     * @param detailsList the details list
     * @return the user details dto list
     */
    private List<GbmUserDetailsDto> getUserDetailsDtoList(final List<GbmUserDetails> detailsList) {
        final List<GbmUserDetailsDto> dtoList = new ArrayList<>(CollectionUtils.size(detailsList));
        for (final GbmUserDetails userDetails : detailsList) {
            final GbmUserDetailsDto dto = new GbmUserDetailsDto();
            dto.setGbmUserDetailsId(userDetails.getId());
            dto.setAdditionalText1(userDetails.getAdditionalText1());
            dto.setAdditionalText2(userDetails.getAdditionalText2());
            dto.setAdditionalText3(userDetails.getAdditionalText3());
            dto.setCreatedBy(userDetails.getCreatedBy());
            dto.setCreatedDate(userDetails.getCreatedDate());
            dto.setFileName(userDetails.getFileName());
            dto.setModifiedBy(userDetails.getModifiedBy());
            dto.setModifiedDate(userDetails.getModifiedDate());
            dto.setVerified(userDetails.getVerified());
            dto.setVerifiedBy(userDetails.getVerifiedBy());
            dto.setVerifiedDate(userDetails.getVerifiedDate());
            dto.setDeleted(userDetails.getDeleted());
            dto.setDefaultTermId(userDetails.getDefaultTermId());
            dto.setNewUserFlag(userDetails.getNewUserFlag());
            dto.setSolId(userDetails.getSolId());
            dto.setUserAccountExpiryDate(userDetails.getUserAccountExpiryDate());
            dto.setUserApplicationName(userDetails.getUserApplicationName());
            dto.setUserId(userDetails.getUserId());
            dto.setUserMaxInactiveTime(userDetails.getUserMaxInactiveTime());
            dto.setUserPassword(userDetails.getUserPassword());
            dto.setUserPasswordExpiryDate(userDetails.getUserPasswordExpiryDate());
            dto.setUserSolTenor(userDetails.getUserSolTenor());
            dto.setUserWorkClass(userDetails.getUserWorkClass());
            dto.setVirtualFlag(userDetails.getVirtualFlag());
            dtoList.add(dto);
        }

        return dtoList;
    }

    private GbmUserDetailsDto buildUserDetailsDto(final GbmUserDetails details) {

        final GbmUserDetailsDto dto = new GbmUserDetailsDto();
        dto.setGbmUserDetailsId(details.getId());
        dto.setAdditionalText1(details.getAdditionalText1());
        dto.setAdditionalText2(details.getAdditionalText2());
        dto.setAdditionalText3(details.getAdditionalText3());
        dto.setCreatedBy(details.getCreatedBy());
        dto.setCreatedDate(details.getCreatedDate());
        dto.setFileName(details.getFileName());
        dto.setModifiedBy(details.getModifiedBy());
        dto.setModifiedDate(details.getModifiedDate());
        dto.setVerified(details.getVerified());
        dto.setVerifiedBy(details.getVerifiedBy());
        dto.setVerifiedDate(details.getVerifiedDate());
        dto.setDeleted(details.getDeleted());
        dto.setDefaultTermId(details.getDefaultTermId());
        dto.setNewUserFlag(details.getNewUserFlag());
        dto.setSolId(details.getSolId());
        dto.setUserAccountExpiryDate(details.getUserAccountExpiryDate());
        dto.setUserApplicationName(details.getUserApplicationName());
        dto.setUserId(details.getUserId());
        dto.setUserMaxInactiveTime(details.getUserMaxInactiveTime());
        dto.setUserPassword(details.getUserPassword());
        dto.setUserPasswordExpiryDate(details.getUserPasswordExpiryDate());
        dto.setUserSolTenor(details.getUserSolTenor());
        dto.setUserWorkClass(details.getUserWorkClass());
        dto.setVirtualFlag(details.getVirtualFlag());
        return dto;
    }

    /**
     * Gets the user work details.
     *
     * @param gbmUserDetailsId the gbm user details id
     * @return the user work details
     */
    public GbmUserDetailsDto getUserWorkDetails(final Integer gbmUserDetailsId) {
        final GbmUserDetails details = this.userDetailsDao.getUserWorkDetails(gbmUserDetailsId);
        final GbmUserDetailsDto dto = new GbmUserDetailsDto();
        dto.setGbmUserDetailsId(details.getId());
        dto.setAdditionalText1(details.getAdditionalText1());
        dto.setAdditionalText2(details.getAdditionalText2());
        dto.setAdditionalText3(details.getAdditionalText3());
        dto.setCreatedBy(details.getCreatedBy());
        dto.setCreatedDate(details.getCreatedDate());
        dto.setFileName(details.getFileName());
        dto.setModifiedBy(details.getModifiedBy());
        dto.setModifiedDate(details.getModifiedDate());
        dto.setVerified(details.getVerified());
        dto.setVerifiedBy(details.getVerifiedBy());
        dto.setVerifiedDate(details.getVerifiedDate());
        dto.setDeleted(details.getDeleted());
        dto.setDefaultTermId(details.getDefaultTermId());
        dto.setNewUserFlag(details.getNewUserFlag());
        dto.setSolId(details.getSolId());
        dto.setUserAccountExpiryDate(details.getUserAccountExpiryDate());
        dto.setUserApplicationName(details.getUserApplicationName());
        dto.setUserId(details.getUserId());
        dto.setUserMaxInactiveTime(details.getUserMaxInactiveTime());
        dto.setUserPassword(details.getUserPassword());
        dto.setUserPasswordExpiryDate(details.getUserPasswordExpiryDate());
        dto.setUserSolTenor(details.getUserSolTenor());
        dto.setUserWorkClass(details.getUserWorkClass());
        dto.setVirtualFlag(details.getVirtualFlag());
        return dto;

    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<GbmUserDetailsDto> getVerifiedRecords() {
        return this.getUserDetailsDtoList(this.userDetailsDao.getVerifiedRecords());
    }

    /**
     * List.
     *
     * @return the list
     */
    public List<GbmUserDetailsDto> list() {
        final List<GbmUserDetails> detailsList = this.userDetailsDao.list();
        return this.getUserDetailsDtoList(detailsList);
    }

    /**
     * Save or update.
     *
     * @param userDetailsDto the user details dto
     */
    public void saveOrUpdate(final GbmUserDetailsDto userDetailsDto) {
        final GbmUserDetails details = this.buildGbmUserDetails(userDetailsDto);
        int id = this.userDetailsDao.saveOrUpdate(details);
    }

    /**
     * Sets the user details dao.
     *
     * @param userDetailsDao the new user details dao
     */
    public void setUserDetailsDao(final UserDetailsDao userDetailsDao) {
        this.userDetailsDao = userDetailsDao;
    }

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(final GbmUserDetailsDto dto) {
        //dto.setModifiedDate(new Date());
        //dto.setVerified(Boolean.FALSE);
        //this.saveOrUpdate(dto);


        final GbmUserDetails savedData = this.userDetailsDao.getUserWorkDetails(dto.getGbmUserDetailsId());
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final GbmUserDetails master = this.buildGbmUserDetails(dto);
        final AuditLoggerParams helperParam = this.getAuditLoggerParams(savedData, master, currentUser, currentDate);
        master.setAuditable(savedData.isAuditable());
        final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, master,
                null);
        if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
            master.setModifiedBy(currentUser);
            master.setModifiedDate(currentDate);
            if (BooleanUtils.isNotTrue(savedData.getVerified())) {
                // newly added record
                helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.userDetailsDao.update(master);
            } else {
                // already verified record
                helperParam.setAlreadyVerifiedRecord(Boolean.TRUE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.auditLogger.saveOrUpdateGenModRecords(helperParam);
            }
        } else {
            this.auditLogger.revertAuditLogRecords(master, currentUser, currentDate, savedData.getVerified());
            this.auditLogger.revertGenModRecords(master, currentUser, currentDate, savedData.getVerified());
        }
    }


    private AuditLoggerParams getAuditLoggerParams(final GbmUserDetails oldEntity,
                                                   final GbmUserDetails newEntity, final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(oldEntity.getModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setFeatureName(FeatureNames.CBS_MAINTENANCE_OF_CALENDAR_ENTRIES);
        return loggerParams;
    }

    /**
     * Upload.
     *
     * @param gbmUserDetailsDtoList the gbm user details dto list
     */
    public ResponseDataDTO upload(final List<GbmUserDetailsDto> gbmUserDetailsDtoList) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto=new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setUserDetailsDtoList(gbmUserDetailsDtoList);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.UWCI.getCbsCode());
        cbsIntegerationBulkUploadDto.setFileName(gbmUserDetailsDtoList.size()>0?gbmUserDetailsDtoList.get(0).getFileName():null);
       return this.cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);

       /* for (final GbmUserDetails master : userDetailsList) {
            this.auditLogger.createAuditLogEntries(master, createdDate, user, null,
                    ModuleDisplayNames.CBS, FeatureNames.CBS_USER_WORK_CLASS_INFORMATION);
        }*/
    }

    /**
     * Verify.
     *
     * @param userDetailsDto the user details dto
     */
    public void verify(final GbmUserDetailsDto userDetailsDto) {

        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();

        final GbmUserDetails userDetails = new GbmUserDetails();


        userDetails.setId(userDetailsDto.getGbmUserDetailsId());
        userDetails.setVerifiedBy(user);
        userDetails.setVerified(Boolean.TRUE);
        userDetails.setVerifiedDate(currentDate);
        userDetails.setAuditable(Boolean.TRUE);

        this.auditLogger.verifyAuditLog(user, currentDate, userDetails);
        this.auditLogger.verifyGenModRecords(user, currentDate, userDetails);

        userDetails.setCreatedBy(userDetailsDto.getCreatedBy());
        userDetails.setCreatedDate(userDetailsDto.getCreatedDate());
        userDetails.setDeleted(userDetailsDto.getDeleted());
        userDetails.setModifiedBy(userDetailsDto.getModifiedBy());
        userDetails.setModifiedDate(userDetailsDto.getModifiedDate());
        userDetails.setAdditionalText1(userDetailsDto.getAdditionalText1());
        userDetails.setAdditionalText2(userDetailsDto.getAdditionalText2());
        userDetails.setAdditionalText3(userDetailsDto.getAdditionalText3());
        userDetails.setDefaultTermId(userDetailsDto.getDefaultTermId());
        userDetails.setFileName(userDetailsDto.getFileName());
        userDetails.setNewUserFlag(userDetailsDto.getNewUserFlag());
        userDetails.setSolId(userDetailsDto.getSolId());
        userDetails.setUserAccountExpiryDate(userDetailsDto.getUserAccountExpiryDate());
        userDetails.setUserApplicationName(userDetailsDto.getUserApplicationName());
        userDetails.setUserId(userDetailsDto.getUserId());
        userDetails.setUserMaxInactiveTime(userDetailsDto.getUserMaxInactiveTime());
        userDetails.setUserPassword(userDetailsDto.getUserPassword());
        userDetails.setUserPasswordExpiryDate(userDetailsDto.getUserPasswordExpiryDate());
        userDetails.setUserSolTenor(userDetailsDto.getUserSolTenor());
        userDetails.setUserWorkClass(userDetailsDto.getUserWorkClass());
        userDetails.setVirtualFlag(userDetailsDto.getVirtualFlag());
        this.userDetailsDao.update(userDetails);
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(final String fileName) {
       /* final List<GbmUserDetails> detailsList = this.userDetailsDao.getUnverifiedUserDetails(fileName);

        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (GbmUserDetails master : detailsList) {
            this.auditLogger.verifyAuditLog(user, currentDate, master);
        }*/
        return this.cbsIntegerationBulkUploadDao.verifyBanks(fileName,CbsBulkUploadEnum.UWCI.getCbsCode());
    }

}
