/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao;
import com.isl.gbm.cbs.integration.dao.impl.CbsIntegerationBulkUploadDaoImpl;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OfficeAccountPlaceHolderDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.OfficeAccountPlaceHolderMaster;
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
 * The Class OfficeAccountPlaceHolderManager.
 */
@Component
public class OfficeAccountPlaceHolderManager {

    /**
     * The office account place holder dao.
     */
    @Autowired
    private OfficeAccountPlaceHolderDao officeAccountPlaceHolderDao;


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
     * @return the office account place holder dto audit log wrapper
     */
    private OfficeAccountPlaceHolderDtoAuditLogWrapper buildAuditLogWrapper(
            final OfficeAccountPlaceHolderMaster master) {
        final OfficeAccountPlaceHolderDtoAuditLogWrapper wrapper = new OfficeAccountPlaceHolderDtoAuditLogWrapper();
        wrapper.setDto(this.buildOfficeAccountPlaceHolderDto(master));
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(master));
        return wrapper;
    }

    /**
     * Builds the office account place holder details.
     *
     * @param officeAccountPlaceHolderDto the office account place holder dto
     * @return the office account place holder master
     */
    private OfficeAccountPlaceHolderMaster buildOfficeAccountPlaceHolderDetails(
            final OfficeAccountPlaceHolderDto officeAccountPlaceHolderDto) {
        final OfficeAccountPlaceHolderMaster details = new OfficeAccountPlaceHolderMaster();
        details.setId(officeAccountPlaceHolderDto.getOfficeId());
        details.setBacid(officeAccountPlaceHolderDto.getBacid());
        details.setBacidDesc(officeAccountPlaceHolderDto.getBacidDesc());
        details.setSchmType(officeAccountPlaceHolderDto.getSchmType());
        details.setAdditionalText1(officeAccountPlaceHolderDto.getAdditionalText1());
        details.setAdditionalText2(officeAccountPlaceHolderDto.getAdditionalText2());
        details.setAdditionalText3(officeAccountPlaceHolderDto.getAdditionalText3());
        details.setCreatedBy(officeAccountPlaceHolderDto.getCreatedBy());
        details.setCreatedDate(officeAccountPlaceHolderDto.getCreatedDate());
        details.setFilename(officeAccountPlaceHolderDto.getFilename());
        details.setLastModifiedBy(officeAccountPlaceHolderDto.getLastModifiedBy());
        details.setLastModifiedDate(officeAccountPlaceHolderDto.getLastModifiedDate());
        details.setVerified(officeAccountPlaceHolderDto.isVerified());
        details.setVerifiedBy(officeAccountPlaceHolderDto.getVerifiedBy());
        details.setVerifiedDate(officeAccountPlaceHolderDto.getVerifiedDate());
        return details;
    }

    /**
     * Builds the office account place holder dto.
     *
     * @param officeDetails the office details
     * @return the office account place holder dto
     */
    private OfficeAccountPlaceHolderDto buildOfficeAccountPlaceHolderDto(
            final OfficeAccountPlaceHolderMaster officeDetails) {
        final OfficeAccountPlaceHolderDto dto = new OfficeAccountPlaceHolderDto();
        dto.setOfficeId(officeDetails.getId());
        dto.setBacid(officeDetails.getBacid());
        dto.setBacidDesc(officeDetails.getBacidDesc());
        dto.setSchmType(officeDetails.getSchmType());
        dto.setAdditionalText1(officeDetails.getAdditionalText1());
        dto.setAdditionalText2(officeDetails.getAdditionalText2());
        dto.setAdditionalText3(officeDetails.getAdditionalText3());
        dto.setCreatedBy(officeDetails.getCreatedBy());
        dto.setCreatedDate(officeDetails.getCreatedDate());
        dto.setFilename(officeDetails.getFilename());
        dto.setLastModifiedBy(officeDetails.getLastModifiedBy());
        dto.setLastModifiedDate(officeDetails.getLastModifiedDate());
        dto.setVerified(officeDetails.isVerified());
        dto.setVerifiedBy(officeDetails.getVerifiedBy());
        dto.setVerifiedDate(officeDetails.getVerifiedDate());
        dto.setDelFlg(officeDetails.getDelFlg());
        return dto;
    }

    /**
     * Builds the office account place holder dto list.
     *
     * @param detailsList the details list
     * @return the list
     */
    private List<OfficeAccountPlaceHolderDto> buildOfficeAccountPlaceHolderDtoList(
            final List<OfficeAccountPlaceHolderMaster> detailsList) {
        final List<OfficeAccountPlaceHolderDto> dtoList = new ArrayList<>(CollectionUtils.size(detailsList));
        for (final OfficeAccountPlaceHolderMaster details : detailsList) {
            dtoList.add(this.buildOfficeAccountPlaceHolderDto(details));
        }
        return dtoList;
    }

    /**
     * Creates the.
     *
     * @param dto the dto
     */
   /* public void create(final OfficeAccountPlaceHolderDto dto) {
        final OfficeAccountPlaceHolderMaster master = this.officeAccountPlaceHolderMaster(dto);
        master.setCreatedBy(UserAccountUtils.getCurrentUsername());
        master.setCreatedDate(new Date());
        this.officeAccountPlaceHolderDao.saveOrUpdate(master);
        this.auditLogger.createAuditLogEntries(master, master.getCreatedDate(), master.getCreatedBy(), null,
                ModuleDisplayNames.CBS, FeatureNames.OFFICE_ACCOUNT_PLACE_HOLDER);
    }*/

    /**
     * Delete.
     *
     * @param dto the dto
     */
    public void delete(final OfficeAccountPlaceHolderDto dto) {
        final OfficeAccountPlaceHolderMaster savedData = this.officeAccountPlaceHolderDao
                .getOfficeAccountPlaceHolderDetails(dto.getOfficeId());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final OfficeAccountPlaceHolderMaster master = this.buildOfficeAccountPlaceHolderDetails(dto);
        if (BooleanUtils.isNotTrue(savedData.isVerified())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.officeAccountPlaceHolderDao.delete(dto.getOfficeId());
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

        /*final List<OfficeAccountPlaceHolderMaster> masterList = this.officeAccountPlaceHolderDao
                .getUnverifiedPlaceHolders(fileName);

        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (OfficeAccountPlaceHolderMaster master : masterList) {
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
        }*/
        return this.cbsIntegerationBulkUploadDao.deleteBanks(fileName, CbsBulkUploadEnum.OAHP.getCbsCode(),remarks);
    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {
        return this.officeAccountPlaceHolderDao.fileNameExists(fileName);
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
    private AuditLoggerParams getAuditLoggerParams(final OfficeAccountPlaceHolderMaster oldEntity,
                                                   final OfficeAccountPlaceHolderMaster newEntity, final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(oldEntity.getLastModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setFeatureName(FeatureNames.OFFICE_ACCOUNT_PLACE_HOLDER);
        return loggerParams;
    }

    /**
     * Gets the office account place holder details.
     *
     * @param officeId the office id
     * @return the office account place holder details
     */
    public OfficeAccountPlaceHolderDtoAuditLogWrapper getOfficeAccountPlaceHolderDetails(final Integer officeId) {
        return this.buildAuditLogWrapper(this.officeAccountPlaceHolderDao.getOfficeAccountPlaceHolderDetails(officeId));
    }

    /**
     * Gets the office account place holder details.
     *
     * @param bacid the bacid
     * @return the office account place holder details
     */
    public List<OfficeAccountPlaceHolderDto> getOfficeAccountPlaceHolderDetails(final String bacid) {
        return this.buildOfficeAccountPlaceHolderDtoList(
                this.officeAccountPlaceHolderDao.getOfficeAccountPlaceHolderDetails(bacid));
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {
        return this.officeAccountPlaceHolderDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {
        return this.officeAccountPlaceHolderDao.getInquiryFiles();
    }

    /**
     * Gets the unverified place holders.
     *
     * @param fileName the file name
     * @return the unverified place holders
     */
    public List<OfficeAccountPlaceHolderDto> getUnverifiedPlaceHolders(final String fileName) {
        final CbsIntegerationBulkUploadDto dtlsList = this.cbsIntegerationBulkUploadDao
                .getUnverifiedBankDetails(fileName, CbsBulkUploadEnum.OAHP.getCbsCode());
        return dtlsList.getPlaceHolderDtoList();
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<OfficeAccountPlaceHolderDtoAuditLogWrapper> getUnverifiedRecords() {
        final List<OfficeAccountPlaceHolderMaster> masterList = this.officeAccountPlaceHolderDao.list();
        final List<OfficeAccountPlaceHolderDtoAuditLogWrapper> wrapperList = new ArrayList<>(masterList.size());
        for (final OfficeAccountPlaceHolderMaster master : masterList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(master);
            if (BooleanUtils.isFalse(master.isVerified()) || (null != auditLogDto)) {
                final OfficeAccountPlaceHolderDtoAuditLogWrapper wrapper = new OfficeAccountPlaceHolderDtoAuditLogWrapper();
                wrapper.setDto(this.buildOfficeAccountPlaceHolderDto(master));
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
    public List<OfficeAccountPlaceHolderDto> getVerifiedRecords() {
        return this.buildOfficeAccountPlaceHolderDtoList(this.officeAccountPlaceHolderDao.getVerifiedRecords());
    }

    /**
     * List.
     *
     * @return the list
     */
    public List<OfficeAccountPlaceHolderDtoAuditLogWrapper> list() {
        final List<OfficeAccountPlaceHolderMaster> detailsList = this.officeAccountPlaceHolderDao.list();
        final List<OfficeAccountPlaceHolderDtoAuditLogWrapper> wrapperList = new ArrayList<>(detailsList.size());
        for (final OfficeAccountPlaceHolderMaster master : detailsList) {
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
    public List<OfficeAccountPlaceHolderDto> offAccPlaceHolderList(
            final List<OfficeAccountPlaceHolderMaster> detailsList) {
        final List<OfficeAccountPlaceHolderDto> dtoList = new ArrayList<>(CollectionUtils.size(detailsList));
        for (final OfficeAccountPlaceHolderMaster officeMasterDetails : detailsList) {
            final OfficeAccountPlaceHolderDto dto = new OfficeAccountPlaceHolderDto();
            dto.setOfficeId(officeMasterDetails.getId());
            dto.setBacid(officeMasterDetails.getBacid());
            dto.setBacidDesc(officeMasterDetails.getBacidDesc());
            dto.setSchmType(officeMasterDetails.getSchmType());
            dto.setAdditionalText1(officeMasterDetails.getAdditionalText1());
            dto.setAdditionalText2(officeMasterDetails.getAdditionalText2());
            dto.setAdditionalText3(officeMasterDetails.getAdditionalText3());
            dto.setCreatedBy(officeMasterDetails.getCreatedBy());
            dto.setCreatedDate(officeMasterDetails.getCreatedDate());
            dto.setFilename(officeMasterDetails.getFilename());
            dto.setLastModifiedBy(officeMasterDetails.getLastModifiedBy());
            dto.setLastModifiedDate(officeMasterDetails.getLastModifiedDate());
            dto.setVerified(officeMasterDetails.isVerified());
            dto.setVerifiedBy(officeMasterDetails.getVerifiedBy());
            dto.setVerifiedDate(officeMasterDetails.getVerifiedDate());
            dtoList.add(dto);

        }
        return dtoList;
    }

    /**
     * Office account place holder bulk upload.
     *
     * @param dtoList the dto list
     */
    public ResponseDataDTO officeAccountPlaceHolderBulkUpload(final List<OfficeAccountPlaceHolderDto> dtoList) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setPlaceHolderDtoList(dtoList);
        cbsIntegerationBulkUploadDto.setFileName(dtoList.size() > 0 ? dtoList.get(0).getFilename() : null);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.OAHP.getCbsCode());
        ResponseDataDTO responseDataDTO = cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);
        /*for (OfficeAccountPlaceHolderMaster officeAccountPlaceHolderMaster : officeAccountMaster) {
            this.auditLogger.createAuditLogEntries(officeAccountPlaceHolderMaster, currentDate, user, null,
                    ModuleDisplayNames.CBS, FeatureNames.OFFICE_ACCOUNT_PLACE_HOLDER);
        }
        */
        return responseDataDTO;
    }

    /**
     * Office account place holder master.
     *
     * @param accountDtoList the account dto list
     * @return the office account place holder master
     */
    public OfficeAccountPlaceHolderMaster officeAccountPlaceHolderMaster(
            final OfficeAccountPlaceHolderDto accountDtoList) {
        final OfficeAccountPlaceHolderMaster masterDtls = new OfficeAccountPlaceHolderMaster();
        masterDtls.setId(accountDtoList.getOfficeId());
        masterDtls.setBacid(accountDtoList.getBacid());
        masterDtls.setBacidDesc(accountDtoList.getBacidDesc());
        masterDtls.setSchmType(accountDtoList.getSchmType());
        masterDtls.setAdditionalText1(accountDtoList.getAdditionalText1());
        masterDtls.setAdditionalText2(accountDtoList.getAdditionalText2());
        masterDtls.setAdditionalText3(accountDtoList.getAdditionalText3());
        masterDtls.setCreatedBy(accountDtoList.getCreatedBy());
        masterDtls.setCreatedDate(new Date());
        masterDtls.setFilename(accountDtoList.getFilename() != null ? accountDtoList.getFilename().toUpperCase() : accountDtoList.getFilename());
        masterDtls.setLastModifiedBy(accountDtoList.getLastModifiedBy());
        masterDtls.setLastModifiedDate(accountDtoList.getLastModifiedDate());
        masterDtls.setVerified(accountDtoList.isVerified());
        masterDtls.setVerifiedBy(accountDtoList.getVerifiedBy());
        masterDtls.setVerifiedDate(accountDtoList.getVerifiedDate());
        return masterDtls;

    }

    /**
     * Record list.
     *
     * @return the list
     */
    public List<OfficeAccountPlaceHolderDto> recordList() {
        final List<OfficeAccountPlaceHolderMaster> detailsList = this.officeAccountPlaceHolderDao.recordList();
        final List<OfficeAccountPlaceHolderDto> dtoList = new ArrayList<>();
        for (final OfficeAccountPlaceHolderMaster officeMaster : detailsList) {
            final OfficeAccountPlaceHolderDto dto = new OfficeAccountPlaceHolderDto();
            dto.setOfficeId(officeMaster.getId());
            dto.setBacid(officeMaster.getBacid());
            dto.setBacidDesc(officeMaster.getBacidDesc());
            dto.setSchmType(officeMaster.getSchmType());
            dto.setAdditionalText1(officeMaster.getAdditionalText1());
            dto.setAdditionalText2(officeMaster.getAdditionalText2());
            dto.setAdditionalText3(officeMaster.getAdditionalText3());
            dto.setCreatedBy(officeMaster.getCreatedBy());
            dto.setCreatedDate(officeMaster.getCreatedDate());
            dto.setFilename(officeMaster.getFilename());
            dto.setLastModifiedBy(officeMaster.getLastModifiedBy());
            dto.setLastModifiedDate(officeMaster.getLastModifiedDate());
            dto.setVerified(officeMaster.isVerified());
            dto.setVerifiedBy(officeMaster.getVerifiedBy());
            dto.setVerifiedDate(officeMaster.getVerifiedDate());
            dtoList.add(dto);

        }
        return dtoList;

    }


    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(final OfficeAccountPlaceHolderDto dto) {
        final OfficeAccountPlaceHolderMaster savedData = this.officeAccountPlaceHolderDao
                .getOfficeAccountPlaceHolderDetails(dto.getOfficeId());
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final OfficeAccountPlaceHolderMaster master = this.buildOfficeAccountPlaceHolderDetails(dto);
        final AuditLoggerParams helperParam = this.getAuditLoggerParams(savedData, master, currentUser, currentDate);
        master.setAuditable(savedData.isAuditable());
        final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, master,
                null);
        if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
            master.setLastModifiedBy(currentUser);
            master.setLastModifiedDate(currentDate);
            if (BooleanUtils.isNotTrue(savedData.isVerified())) {
                // newly added record
                helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.officeAccountPlaceHolderDao.saveOrUpdate(master);
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
    public void verify(final OfficeAccountPlaceHolderDto dto) {
        final OfficeAccountPlaceHolderMaster master = this.buildOfficeAccountPlaceHolderDetails(dto);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        master.setId(dto.getOfficeId());
        master.setVerified(Boolean.TRUE);
        master.setVerifiedDate(currentDate);
        master.setVerifiedBy(user);
        master.setAuditable(Boolean.TRUE);
        this.auditLogger.verifyAuditLog(user, currentDate, master);
        this.auditLogger.verifyGenModRecords(user, currentDate, master);
        this.officeAccountPlaceHolderDao.verify(master);
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(final String fileName) {

       /* final List<OfficeAccountPlaceHolderMaster> bankBranchList = this.officeAccountPlaceHolderDao
                .getUnverifiedPlaceHolders(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (OfficeAccountPlaceHolderMaster master : bankBranchList) {
            this.auditLogger.verifyAuditLog(user, currentDate, master);
        }*/
        return this.cbsIntegerationBulkUploadDao.verifyBanks(fileName, CbsBulkUploadEnum.OAHP.getCbsCode())
                ;
    }

    public ResponseDataDTO create(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderDao.create(dto);
    }

    public ResponseDataDTO inquiry(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderDao.inquiry(dto);
    }

    public ResponseDataDTO verDelDetails(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderDao.verDelDetails(dto);
    }

    public ResponseDataDTO modifyDetails(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderDao.modifyDetails(dto);
    }

    public ResponseDataDTO modify(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderDao.modify(dto);
    }


    public ResponseDataDTO verifySubmit(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderDao.verifySubmit(dto);
    }


    public ResponseDataDTO deleteSubmit(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderDao.deleteSubmit(dto);
    }


}
