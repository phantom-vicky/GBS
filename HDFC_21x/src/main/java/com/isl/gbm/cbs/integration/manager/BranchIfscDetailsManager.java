/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.BranchIfscDetailsDao;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.CommonGenericTableDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BranchIfscDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.BranchIfscDetails;
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
 * The Class BranchIfscDetailsManager.
 */
@Component
public class BranchIfscDetailsManager {

    /**
     * The branch ifsc details dao.
     */
    @Autowired
    private BranchIfscDetailsDao branchIfscDetailsDao;
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
     * @return the branch ifsc details dto audit log wrapper
     */
    private BranchIfscDetailsDtoAuditLogWrapper buildAuditLogWrapper(final BranchIfscDetails master) {
        final BranchIfscDetailsDtoAuditLogWrapper wrapper = new BranchIfscDetailsDtoAuditLogWrapper();
        wrapper.setDto(this.buildBranchDetailsDto(master));
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(master));
        return wrapper;
    }

    /**
     * Builds the branch details dto.
     *
     * @param details the details
     * @return the branch ifsc details dto
     */
    private BranchIfscDetailsDto buildBranchDetailsDto(final BranchIfscDetails details) {
        final BranchIfscDetailsDto dto = new BranchIfscDetailsDto();
        dto.setAdditionalText1(details.getAdditionalText1());
        dto.setAdditionalText2(details.getAdditionalText2());
        dto.setAdditionalText3(details.getAdditionalText3());
        dto.setCreatedBy(details.getCreatedBy());
        dto.setCreatedDate(details.getCreatedDate());
        dto.setFileName(details.getFileName());
        dto.setModifiedBy(details.getModifiedBy());
        dto.setModifiedDate(details.getModifiedDate());
        dto.setVerified(details.isVerified());
        dto.setVerifiedBy(details.getVerifiedBy());
        dto.setVerifiedDate(details.getVerifiedDate());
        dto.setBankCode(details.getBankCode());
        dto.setBranchCode(details.getBranchCode());
        dto.setBankIdentifier(details.getBankIdentifier());
        dto.setPaysysId(details.getPaysysId());
        dto.setDeleted(details.isDeleted());
        dto.setBranchDetailsId(details.getId());
        return dto;
    }

    /**
     * Builds the branch ifsc details.
     *
     * @param branchIfscDetailsDto the branch ifsc details dto
     * @return the branch ifsc details
     */
    private BranchIfscDetails buildBranchIfscDetails(final BranchIfscDetailsDto branchIfscDetailsDto) {
        final BranchIfscDetails details = new BranchIfscDetails();
        details.setId(branchIfscDetailsDto.getBranchDetailsId());
        details.setAdditionalText1(branchIfscDetailsDto.getAdditionalText1());
        details.setAdditionalText2(branchIfscDetailsDto.getAdditionalText2());
        details.setAdditionalText3(branchIfscDetailsDto.getAdditionalText3());
        details.setCreatedBy(branchIfscDetailsDto.getCreatedBy());
        details.setCreatedDate(branchIfscDetailsDto.getCreatedDate());
        details.setFileName(branchIfscDetailsDto.getFileName()!=null?branchIfscDetailsDto.getFileName().toUpperCase():branchIfscDetailsDto.getFileName());
        details.setModifiedBy(branchIfscDetailsDto.getModifiedBy());
        details.setModifiedDate(branchIfscDetailsDto.getModifiedDate());
        details.setVerified(branchIfscDetailsDto.isVerified());
        details.setVerifiedBy(branchIfscDetailsDto.getVerifiedBy());
        details.setVerifiedDate(branchIfscDetailsDto.getVerifiedDate());
        details.setBankCode(branchIfscDetailsDto.getBankCode());
        details.setBranchCode(branchIfscDetailsDto.getBranchCode());
        details.setBankIdentifier(branchIfscDetailsDto.getBankIdentifier());
        details.setPaysysId(branchIfscDetailsDto.getPaysysId());
        details.setDeleted(branchIfscDetailsDto.isDeleted());
        return details;
    }

    /**
     * Builds the branch ifsc details dto list.
     *
     * @param detailsList the details list
     * @return the list
     */
    private List<BranchIfscDetailsDto> buildBranchIfscDetailsDtoList(final List<BranchIfscDetails> detailsList) {
        final List<BranchIfscDetailsDto> dtoList = new ArrayList<>(CollectionUtils.size(detailsList));
        for (final BranchIfscDetails details : detailsList) {
            dtoList.add(this.buildBranchDetailsDto(details));
        }
        return dtoList;
    }

    /**
     * Creates the.
     *
     * @param branchIfscDetailsDto the branch ifsc details dto
     */
    public void create(final BranchIfscDetailsDto branchIfscDetailsDto) {
        final BranchIfscDetails master = this.buildBranchIfscDetails(branchIfscDetailsDto);
        master.setCreatedBy(UserAccountUtils.getCurrentUsername());
        master.setCreatedDate(new Date());
        this.branchIfscDetailsDao.saveOrUpdate(master);
        this.auditLogger.createAuditLogEntries(master, master.getCreatedDate(), master.getCreatedBy(), null,
                ModuleDisplayNames.CBS, FeatureNames.BRANCH_IFSC_CODES);
    }

    public ResponseDataDTO add(BranchIfscDetailsDto branchIfscDetailsDto) {
        return this.branchIfscDetailsDao.add(branchIfscDetailsDto);
    }
    public ResponseDataDTO modify(BranchIfscDetailsDto branchIfscDetailsDto) {
        return this.branchIfscDetailsDao.modify(branchIfscDetailsDto);
    }

    public ResponseDataDTO modifySubmit(CommonGenericTableDto commonGenericTableDto) {
        return this.branchIfscDetailsDao.modifySubmit(commonGenericTableDto);
    }

    public ResponseDataDTO inquiry(BranchIfscDetailsDto branchIfscDetailsDto) {

        return this.branchIfscDetailsDao.inquiry(branchIfscDetailsDto);
    }

    public ResponseDataDTO verifyDeleteSelect(BranchIfscDetailsDto branchIfscDetailsDto) {
        return this.branchIfscDetailsDao.verifyDeleteSelect(branchIfscDetailsDto);
    }

    public ResponseDataDTO verifySubmit(CommonGenericTableDto commonGenericTableDto) {
        return this.branchIfscDetailsDao.verifySubmit(commonGenericTableDto);
    }

    public ResponseDataDTO deleteSubmit(CommonGenericTableDto commonGenericTableDto) {
        return this.branchIfscDetailsDao.deleteSubmit(commonGenericTableDto);
    }
    /**
     * Delete.
     *
     * @param branchIfscDetailsDto the branch ifsc details dto
     */
    public void delete(final BranchIfscDetailsDto branchIfscDetailsDto) {
        final BranchIfscDetails savedData = this.branchIfscDetailsDao
                .getBranchIfscDetails(branchIfscDetailsDto.getBranchDetailsId());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final BranchIfscDetails master = this.buildBranchIfscDetails(branchIfscDetailsDto);
        if (BooleanUtils.isNotTrue(savedData.isVerified())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.branchIfscDetailsDao.delete(master);
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
    public ResponseDataDTO delete(final String fileName, final String remarks) {
       /* final List<BranchIfscDetails> branchIfscList = this.branchIfscDetailsDao.getUnverifiedBranchIfscDetails(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (BranchIfscDetails branchIfsc : branchIfscList) {
            this.auditLogger.deleteAuditLogRecords(branchIfsc, user, currentDate);
        }*/
        return this.cbsIntegerationBulkUploadDao.deleteBanks(fileName,CbsBulkUploadEnum.BIFSCC.getCbsCode(), remarks);
    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {
        return this.branchIfscDetailsDao.fileNameExists(fileName);
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
    private AuditLoggerParams getAuditLoggerParams(final BranchIfscDetails oldEntity, final BranchIfscDetails newEntity,
                                                   final String currentUser, final Date currentDate) {
        final AuditLoggerParams helperParam = new AuditLoggerParams();
        helperParam.setCurrentUser(currentUser);
        helperParam.setLastModifiedUser(oldEntity.getModifiedBy());
        helperParam.setCurrentDate(currentDate);
        helperParam.setOldEntity(oldEntity);
        helperParam.setNewEntity(newEntity);
        helperParam.setColumnDisplayName(null);
        helperParam.setModuleDisplayName(ModuleDisplayNames.CBS);
        helperParam.setFeatureName(FeatureNames.BRANCH_IFSC_CODES);
        return helperParam;
    }

    /**
     * Gets the branch ifsc details.
     *
     * @param branchDetailsId the branch details id
     * @return the branch ifsc details
     */
    public BranchIfscDetailsDtoAuditLogWrapper getBranchIfscDetails(final Integer branchDetailsId) {
        return this.buildAuditLogWrapper(this.branchIfscDetailsDao.getBranchIfscDetails(branchDetailsId));
    }

    /**
     * Gets the branch ifsc details.
     *
     * @param bankCode   the bank code
     * @param branchCode the branch code
     * @return the branch ifsc details
     */
    public List<BranchIfscDetailsDto> getBranchIfscDetails(final String bankCode, final String branchCode) {
        return this.buildBranchIfscDetailsDtoList(this.branchIfscDetailsDao.getBranchIfscDetails(bankCode, branchCode));
    }

    /**
     * Gets the unverified branch ifsc details.
     *
     * @param fileName the file name
     * @return the unverified branch ifsc details
     */
    public List<BranchIfscDetailsDto> getUnverifiedBranchIfscDetails(final String fileName) {
     CbsIntegerationBulkUploadDto  cbsIntegerationBulkUploadDto= this.cbsIntegerationBulkUploadDao.getUnverifiedBankDetails(fileName,CbsBulkUploadEnum.BIFSCC.getCbsCode());
        return cbsIntegerationBulkUploadDto.getBranchIfscDetailsDtoList();
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {

        return this.branchIfscDetailsDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {

        return this.branchIfscDetailsDao.getInquiryFiles();
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<BranchIfscDetailsDtoAuditLogWrapper> getUnverifiedRecords() {
        final List<BranchIfscDetails> masterList = this.branchIfscDetailsDao.list();
        final List<BranchIfscDetailsDtoAuditLogWrapper> wrapperList = new ArrayList<>(masterList.size());
        for (final BranchIfscDetails master : masterList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(master);
            if (BooleanUtils.isFalse(master.isVerified()) || (null != auditLogDto)) {
                final BranchIfscDetailsDtoAuditLogWrapper wrapper = new BranchIfscDetailsDtoAuditLogWrapper();
                wrapper.setDto(this.buildBranchDetailsDto(master));
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
    public List<BranchIfscDetailsDto> getVerifiedRecords() {
        return this.buildBranchIfscDetailsDtoList(this.branchIfscDetailsDao.getVerifiedRecords());
    }

    /**
     * List.
     *
     * @return the list
     */
    public List<BranchIfscDetailsDtoAuditLogWrapper> list() {
        final List<BranchIfscDetails> detailsList = this.branchIfscDetailsDao.list();
        final List<BranchIfscDetailsDtoAuditLogWrapper> wrapperList = new ArrayList<>(detailsList.size());
        for (final BranchIfscDetails master : detailsList) {
            wrapperList.add(this.buildAuditLogWrapper(master));
        }
        return wrapperList;
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
     * Sets the branch ifsc details dao.
     *
     * @param branchIfscDetailsDao the new branch ifsc details dao
     */
    public void setBranchIfscDetailsDao(final BranchIfscDetailsDao branchIfscDetailsDao) {
        this.branchIfscDetailsDao = branchIfscDetailsDao;
    }

    /**
     * Update.
     *
     * @param branchIfscDetailsDto the branch ifsc details dto
     */
    public void update(final BranchIfscDetailsDto branchIfscDetailsDto) {
        final BranchIfscDetails savedData = this.branchIfscDetailsDao
                .getBranchIfscDetails(branchIfscDetailsDto.getBranchDetailsId());
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final BranchIfscDetails master = this.buildBranchIfscDetails(branchIfscDetailsDto);
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
                this.branchIfscDetailsDao.saveOrUpdate(master);
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
     * Upload.
     *
     * @param branchIfscDetailsDtoList the branch ifsc details dto list
     */
    public ResponseDataDTO upload(final List<BranchIfscDetailsDto> branchIfscDetailsDtoList) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto=new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setBranchIfscDetailsDtoList(branchIfscDetailsDtoList);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.BIFSCC.getCbsCode());
        cbsIntegerationBulkUploadDto.setFileName(branchIfscDetailsDtoList.size()>0?branchIfscDetailsDtoList.get(0).getFileName():null);
       return this.cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);
        /*for (BranchIfscDetails branchIfscdetails : branchIfscDetailsList) {
            //this.auditLogger.createAuditLogEntries(branchIfscdetails, currentDate, user, null, ModuleDisplayNames.CBS, FeatureNames.BRANCH_IFSC_CODES);
            this.auditLogger.createAuditLogEntries(branchIfscdetails, currentDate, user, null, ModuleDisplayNames.CBS, FeatureNames.BRANCH_IFSC_CODES);
        }*/
    }

    /**
     * Verify.
     *
     * @param branchIfscDetailsDto the branch ifsc details dto
     */
    public void verify(final BranchIfscDetailsDto branchIfscDetailsDto) {
        final BranchIfscDetails master = this.buildBranchIfscDetails(branchIfscDetailsDto);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        master.setId(branchIfscDetailsDto.getBranchDetailsId());
        master.setVerifiedDate(currentDate);
        master.setVerifiedBy(user);
        this.auditLogger.verifyAuditLog(user, currentDate, master);
        this.auditLogger.verifyGenModRecords(user, currentDate, master);
        this.branchIfscDetailsDao.verify(master);
    }


    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(final String fileName) {

     /*   final List<BranchIfscDetails> branchCodeList = this.branchIfscDetailsDao.getUnverifiedBranchIfscDetails(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (final BranchIfscDetails master : branchCodeList) {
            //this.auditLogger.verifyAuditLog(user, currentDate, master);
            this.auditLogger.verifyAuditLog(user, currentDate, master);
        }*/
       return this.cbsIntegerationBulkUploadDao.verifyBanks(fileName,CbsBulkUploadEnum.BIFSCC.getCbsCode());
    }



}
