/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.BankBranchStatus;
import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.BankBranchDao;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BankBranchDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
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
 * The Class BankBranchManager.
 */
@Component
public class BankBranchManager {

    /**
     * The bank branch dao.
     */
    @Autowired
    private BankBranchDao bankBranchDao;

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
     * @param bankBranchMaster the bank branch master
     * @return the bank branch dto audit log wrapper
     */
    private BankBranchDtoAuditLogWrapper buildAuditLogWrapper(final BankBranchMaster bankBranchMaster) {
        final BankBranchDtoAuditLogWrapper wrapper = new BankBranchDtoAuditLogWrapper();
        wrapper.setDto(this.buildBankBranchDto(bankBranchMaster));
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(bankBranchMaster));
        return wrapper;
    }

    /**
     * Builds the bank branch dto.
     *
     * @param bankBranchMaster the bank branch master
     * @return the bank branch dto
     */
    private BankBranchDto buildBankBranchDto(final BankBranchMaster bankBranchMaster) {
        final BankBranchDto resultDto = new BankBranchDto();
        resultDto.setSolId(bankBranchMaster.getSolId());
        resultDto.setId(bankBranchMaster.getId());
        resultDto.setSolDesc(bankBranchMaster.getSolDesc());
        resultDto.setBankCode(bankBranchMaster.getBankCode());
        resultDto.setBranchCode(bankBranchMaster.getBranchCode());
        resultDto.setMicrCertreCode(bankBranchMaster.getMicrCertreCode());
        resultDto.setMicrBankCode(bankBranchMaster.getMicrBankCode());
        resultDto.setMicrBranchCode(bankBranchMaster.getMicrBranchCode());
        resultDto.setAddr1(bankBranchMaster.getAddr1());
        resultDto.setAddr2(bankBranchMaster.getAddr2());
        resultDto.setCityCode(bankBranchMaster.getCityCode());
        resultDto.setStateCode(bankBranchMaster.getStateCode());
        resultDto.setPinCode(bankBranchMaster.getPinCode());
        resultDto.setPanGirNumber(bankBranchMaster.getPanGirNumber());
        resultDto.setTaxDednDetails(bankBranchMaster.getTaxDednDetails());
        resultDto.setTdsCircleName(bankBranchMaster.getTdsCircleName());
        resultDto.setDivisionName(bankBranchMaster.getDivisionName());
        resultDto.setCirclOfficeName(bankBranchMaster.getCirclOfficeName());
        resultDto.setUniformBrCode(bankBranchMaster.getUniformBrCode());
        resultDto.setDeleteFlag(bankBranchMaster.isDeleteFlag());
        resultDto.setFileName(bankBranchMaster.getFileName());
        resultDto.setCreatedBy(bankBranchMaster.getCreatedBy());
        resultDto.setCreatedDate(bankBranchMaster.getCreatedDate());
        resultDto.setLastModifiedBy(bankBranchMaster.getLastModifiedBy());
        resultDto.setLastModifiedDate(bankBranchMaster.getLastModifiedDate());
        resultDto.setVerifiedFlag(bankBranchMaster.getVerifiedFlag());
        resultDto.setVerifiedBy(bankBranchMaster.getVerifiedBy());
        resultDto.setVerifiedDate(bankBranchMaster.getVerifiedDate());
        resultDto.setAdditionalText1(bankBranchMaster.getAdditionalText1());
        resultDto.setAdditionalText2(bankBranchMaster.getAdditionalText2());
        resultDto.setAdditionalText3(bankBranchMaster.getAdditionalText3());
        resultDto.setBranchLocation(bankBranchMaster.getBranchLocation());
        resultDto.setIfscCode(bankBranchMaster.getIfscCode());
        final BankBranchStatus status = BankBranchStatus.findByStatus(bankBranchMaster.getStatus());
        if (null != status) {
            resultDto.setBranchStatus(status.getStatus());
            resultDto.setBranchStatusDescription(status.getDescription());
        }

        return resultDto;
    }

    /**
     * Builds the bank model from dto.
     *
     * @param bankBranchDto the bank branch dto
     * @return the bank branch master
     */
    private BankBranchMaster buildBankModelFromDto(final BankBranchDto bankBranchDto) {
        final BankBranchMaster bankBranchMaster = new BankBranchMaster();
        bankBranchMaster.setId(bankBranchDto.getId());
        bankBranchMaster.setSolId(bankBranchDto.getSolId());
        bankBranchMaster.setSolDesc(bankBranchDto.getSolDesc());
        bankBranchMaster.setBankCode(bankBranchDto.getBankCode());
        bankBranchMaster.setBranchCode(bankBranchDto.getBranchCode());
        bankBranchMaster.setMicrCertreCode(bankBranchDto.getMicrCertreCode());
        bankBranchMaster.setMicrBankCode(bankBranchDto.getMicrBankCode());
        bankBranchMaster.setMicrBranchCode(bankBranchDto.getMicrBranchCode());
        bankBranchMaster.setAddr1(bankBranchDto.getAddr1());
        bankBranchMaster.setAddr2(bankBranchDto.getAddr2());
        bankBranchMaster.setCityCode(bankBranchDto.getCityCode());
        bankBranchMaster.setStateCode(bankBranchDto.getStateCode());
        bankBranchMaster.setPinCode(bankBranchDto.getPinCode());
        bankBranchMaster.setPanGirNumber(bankBranchDto.getPanGirNumber());
        bankBranchMaster.setTaxDednDetails(bankBranchDto.getTaxDednDetails());
        bankBranchMaster.setTdsCircleName(bankBranchDto.getTdsCircleName());
        bankBranchMaster.setDivisionName(bankBranchDto.getDivisionName());
        bankBranchMaster.setCirclOfficeName(bankBranchDto.getCirclOfficeName());
        bankBranchMaster.setUniformBrCode(bankBranchDto.getUniformBrCode());
        bankBranchMaster.setDeleteFlag(bankBranchDto.isDeleteFlag());
        bankBranchMaster.setVerifiedFlag(bankBranchDto.isVerifiedFlag());
        bankBranchMaster.setCreatedBy(bankBranchDto.getCreatedBy());
        bankBranchMaster.setCreatedDate(bankBranchDto.getCreatedDate());
        bankBranchMaster.setVerifiedBy(bankBranchDto.getVerifiedBy());
        bankBranchMaster.setVerifiedDate(bankBranchDto.getVerifiedDate());
        bankBranchMaster.setLastModifiedBy(bankBranchDto.getLastModifiedBy());
        bankBranchMaster.setLastModifiedDate(bankBranchDto.getLastModifiedDate());
        bankBranchMaster.setAdditionalText1(bankBranchDto.getAdditionalText1());
        bankBranchMaster.setAdditionalText2(bankBranchDto.getAdditionalText2());
        bankBranchMaster.setAdditionalText3(bankBranchDto.getAdditionalText3());
        bankBranchMaster.setFileName(bankBranchDto.getFileName() != null? bankBranchDto.getFileName():bankBranchDto.getFileName());
        bankBranchMaster.setStatus(bankBranchDto.getBranchStatus());
        bankBranchMaster.setBranchLocation(bankBranchDto.getBranchLocation());
        bankBranchMaster.setZoneCode(bankBranchDto.getZoneCode());
        bankBranchMaster.setZoneName(bankBranchDto.getZoneName());
        bankBranchMaster.setPdoCode(bankBranchDto.getPdoCode());
        bankBranchMaster.setShortName(bankBranchDto.getShortName());
        bankBranchMaster.setEmailID(bankBranchDto.getBranchEmailId());
        bankBranchMaster.setStatus(bankBranchDto.getBranchStatus());
        bankBranchMaster.setIfscCode(bankBranchDto.getIfscCode());
        return bankBranchMaster;
    }

    /**
     * Builds the dto list from model.
     *
     * @param branchBankList the branch bank list
     * @return the list
     */
    private List<BankBranchDto> buildDtoListFromModel(final List<BankBranchMaster> branchBankList) {
        final List<BankBranchDto> branchBankDtoList = new ArrayList<>();
        for (final BankBranchMaster branchBank : branchBankList) {
            branchBankDtoList.add(this.buildBankBranchDto(branchBank));
        }
        return branchBankDtoList;
    }

    /**
     * Builds the model from dto list.
     *
     * @param branchBankDtoList the branch bank dto list
     * @return the list
     */
    private List<BankBranchMaster> buildModelFromDtoList(final List<BankBranchDto> branchBankDtoList) {
        final List<BankBranchMaster> branchBankList = new ArrayList<>(branchBankDtoList.size());
        for (final BankBranchDto branchBankDto : branchBankDtoList) {
            branchBankList.add(this.buildBankModelFromDto(branchBankDto));
        }
        return branchBankList;
    }

    /**
     * Creates the.
     *
     * @param bankBranchDto the bank branch dto
     */
    public void create(final BankBranchDto bankBranchDto) {
        final BankBranchMaster master = this.buildBankModelFromDto(bankBranchDto);
        master.setCreatedBy(UserAccountUtils.getCurrentUsername());
        master.setCreatedDate(new Date());
        this.bankBranchDao.saveOrUpdate(master);
        this.auditLogger.createAuditLogEntries(master, master.getCreatedDate(), master.getCreatedBy(), null,
                ModuleDisplayNames.CBS, FeatureNames.CBS_BRANCHES_OF_THE_BANK);
    }

    /**
     * Delete.
     *
     * @param bankBranchDto the bank branch dto
     */
    public void delete(final BankBranchDto bankBranchDto) {
        final BankBranchMaster savedData = this.bankBranchDao.getBankBranchDetails(bankBranchDto.getId());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final BankBranchMaster master = this.buildBankModelFromDto(bankBranchDto);

        if (BooleanUtils.isNotTrue(savedData.getVerifiedFlag())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.bankBranchDao.deleteId(master.getId());
        } else {
            // already verified record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.auditLogger.deleteGenModRecords(master, user, currentDate);
            this.bankBranchDao.delete(master.getId());
        }
    }

    /**
     * Delete banks.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO deleteBanks(final String fileName, final String remarks) {
        /*final List<BankBranchMaster> masterList = this.bankBranchDao
                .getUnverifiedBankDetails(fileName);

        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (BankBranchMaster master : masterList) {
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
        }*/
      return  this.cbsIntegerationBulkUploadDao.deleteBanks(fileName,CbsBulkUploadEnum.BB.getCbsCode(),remarks);
    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {
        return this.bankBranchDao.fileNameExists(fileName);
    }

    public boolean unVerifiedDetails() {
        return this.bankBranchDao.unVerifiedDetails();
    }



    /**
     * Gets the all bank details.
     *
     * @return the all bank details
     */
    public List<BankBranchDto> getAllBankDetails() {
        final List<BankBranchMaster> branchBankList = this.bankBranchDao.getAllBankDetails();
        return this.buildDtoListFromModel(branchBankList);
    }

    /**
     * Gets the all bank details for inquiry.
     *
     * @return the all bank details for inquiry
     */
    public List<BankBranchDtoAuditLogWrapper> getAllBankDetailsForInquiry() {
		/*final List<BankBranchMaster> branchBankList = this.bankBranchDao.getAllBankDetailsForInquiry();
		final List<BankBranchDtoAuditLogWrapper> wrapperList = new ArrayList<>(branchBankList.size());
		for (final BankBranchMaster master : branchBankList) {
			wrapperList.add(this.buildAuditLogWrapper(master));
		}*/
        return this.bankBranchDao.getAllDetails();
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
    private AuditLoggerParams getAuditLoggerParams(final BankBranchMaster oldEntity, final BankBranchMaster newEntity,
                                                   final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(oldEntity.getLastModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setFeatureName(FeatureNames.CBS_BRANCHES_OF_THE_BANK);
        return loggerParams;
    }

    /**
     * Gets the bank branch details.
     *
     * @param id the id
     * @return the bank branch details
     */
    public BankBranchDtoAuditLogWrapper getBankBranchDetails(final int id) {
        return this.buildAuditLogWrapper(this.bankBranchDao.getBankBranchDetails(id));
    }

    /**
     * Gets the branch bank list.
     *
     * @return the branch bank list
     */
    public List<BankBranchDto> getBranchBankList() {
        final List<BankBranchMaster> branchBankList = this.bankBranchDao.getBranchBankList();
        return this.buildDtoListFromModel(branchBankList);
    }

    /**
     * Gets the sol idand description.
     *
     * @return the sol idand description
     */
    public List<BankBranchDto> getSolIdandDescription() {
        final List<BankBranchMaster> branchBankList = this.bankBranchDao.getSolIdandDescription();
        final List<BankBranchDto> bankBranchDtoList = new ArrayList<>();
        for (final BankBranchMaster bankBranchMaster : branchBankList) {
            final BankBranchDto bankBranchDto = new BankBranchDto();
            bankBranchDto.setSolId(bankBranchMaster.getSolId());
            bankBranchDto.setSolDesc(bankBranchMaster.getSolDesc());
            bankBranchDtoList.add(bankBranchDto);
        }
        return bankBranchDtoList;
    }

    /**
     * Gets the unverified bank details.
     *
     * @param fileName the file name
     * @return the unverified bank details
     */
    public List<BankBranchDto> getUnverifiedBankDetails(final String fileName) {
        CbsIntegerationBulkUploadDto branchBankList= this.cbsIntegerationBulkUploadDao.getUnverifiedBankDetails(fileName,CbsBulkUploadEnum.BB.getCbsCode());
        return branchBankList.getBankBranchDtoList();
    }

    public List<BankBranchDto> getInquiryBankDetails(final String fileName) {
        final List<BankBranchMaster> branchBankList = this.bankBranchDao.getInquiryBankDetails(fileName);
        return this.buildDtoListFromModel(branchBankList);
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {
        return this.bankBranchDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {
        return this.bankBranchDao.getInquiryFiles();
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<BankBranchDtoAuditLogWrapper> getUnverifiedRecords() {
        final List<BankBranchMaster> branchBankList = this.bankBranchDao.getAllBankDetailsForInquiry();
        final List<BankBranchDtoAuditLogWrapper> wrapperList = new ArrayList<>(branchBankList.size());
        for (final BankBranchMaster master : branchBankList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(master);
            if (BooleanUtils.isFalse(master.getVerifiedFlag()) || (null != auditLogDto)) {
                final BankBranchDtoAuditLogWrapper wrapper = new BankBranchDtoAuditLogWrapper();
                wrapper.setDto(this.buildBankBranchDto(master));
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
    public List<BankBranchDto> getVerifiedRecords() {
        final List<BankBranchMaster> branchBankList = this.bankBranchDao.getVerifiedRecords();
        return this.buildDtoListFromModel(branchBankList);
    }

    /**
     * Checks if is sol id exists.
     *
     * @param solId the sol id
     * @return the boolean
     */
    public Boolean isSolIdExists(final String solId) {
        return this.bankBranchDao.isSolIdExists(solId);
    }

    /**
     * Save details.
     *
     * @param branchBankDtoList the branch bank dto list
     */


    public ResponseDataDTO saveDetails(final List<BankBranchDto> branchBankDtoList) {

         ResponseDataDTO responseDataDTO=null;
        final List<BankBranchMaster> bankBranchList = new ArrayList<>(branchBankDtoList.size());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();

        branchBankDtoList.parallelStream().forEach((bankBranchDto) -> {
           bankBranchDto.setCreatedBy(user);
           bankBranchDto.setCreatedDate(currentDate);
        });
        for (final BankBranchDto bankBranchDto : branchBankDtoList) {
            BankBranchMaster master = this.buildBankModelFromDto(bankBranchDto);
            master.setCreatedBy(UserAccountUtils.getCurrentUsername());
            bankBranchList.add(master);
        }
        List<BankBranchMaster> bankList = this.buildModelFromDtoList(branchBankDtoList);
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto=new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setBranchBankList(bankList);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.BB.getCbsCode());
        cbsIntegerationBulkUploadDto.setFileName(bankList.size()>0?bankList.get(0).getFileName():null);
        responseDataDTO=this.cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);
        //this.bankBranchDao.saveDetails(bankList);
        /*for (final BankBranchMaster branchMaster : bankList) {
            this.auditLogger.createAuditLogEntries(branchMaster, currentDate, user, null,
                    ModuleDisplayNames.CBS, FeatureNames.CBS_BRANCHES_OF_THE_BANK);
        }*/
        return responseDataDTO;
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
     * Sets the bank branch dao.
     *
     * @param bankBranchDao the new bank branch dao
     */
    public void setBankBranchDao(final BankBranchDao bankBranchDao) {
        this.bankBranchDao = bankBranchDao;
    }

    /**
     * Update.
     *
     * @param bankBranchDto the bank branch dto
     */
    public void update(final BankBranchDto bankBranchDto) {
        final BankBranchMaster savedData = this.bankBranchDao.getBankBranchDetails(bankBranchDto.getId());
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final BankBranchMaster master = this.buildBankModelFromDto(bankBranchDto);
        final AuditLoggerParams helperParam = this.getAuditLoggerParams(savedData, master, currentUser, currentDate);
        master.setAuditable(savedData.getAuditable());
        final ModifiedDataDetails modifiedDataDetails = this.auditLogger.getModifiedDataDetails(savedData, master,
                null);
        if (CollectionUtils.isNotEmpty(modifiedDataDetails.getModifiedColumnDetailList())) {
            master.setLastModifiedBy(currentUser);
            master.setLastModifiedDate(currentDate);
            if (BooleanUtils.isNotTrue(savedData.getVerifiedFlag())) {
                // newly added record
                helperParam.setAlreadyVerifiedRecord(Boolean.FALSE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.bankBranchDao.saveOrUpdate(master);
            } else {
                // already verified record
                helperParam.setAlreadyVerifiedRecord(Boolean.TRUE);
                this.auditLogger.saveOrUpdateAuditLog(helperParam);
                this.auditLogger.saveOrUpdateGenModRecords(helperParam);
            }
        } else {
            this.auditLogger.revertAuditLogRecords(master, currentUser, currentDate, savedData.getVerifiedFlag());
            this.auditLogger.revertGenModRecords(master, currentUser, currentDate, savedData.getVerifiedFlag());
        }
    }

    /**
     * Verify.
     *
     * @param bankBranchDto the bank branch dto
     */
    public void verify(final BankBranchDto bankBranchDto) {
        final BankBranchMaster master = new BankBranchMaster();
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        master.setId(bankBranchDto.getId());
        master.setVerifiedFlag(Boolean.TRUE);
        master.setVerifiedDate(currentDate);
        master.setVerifiedBy(user);
        master.setAuditable(Boolean.TRUE);
        this.auditLogger.verifyAuditLog(user, currentDate, master);
        this.auditLogger.verifyGenModRecords(user, currentDate, master);
        this.bankBranchDao.verify(master);
    }

    /**
     * Verify banks.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verifyBanks(final String fileName) {
        return this.cbsIntegerationBulkUploadDao.verifyBanks(fileName, CbsBulkUploadEnum.BB.getCbsCode());
    }

    public ResponseDataDTO addDetails(BankBranchDto dto) {
        return bankBranchDao.addDetails(dto);
    }

    public ResponseDataDTO inquiry(BankBranchDto dto) {
        return bankBranchDao.inquiry(dto);
    }

    public ResponseDataDTO verDelDetails(BankBranchDto dto) {
        return bankBranchDao.verDelDetails(dto);
    }

    public ResponseDataDTO modifyDetails(BankBranchDto dto) {
        return bankBranchDao.modifyDetails(dto);
    }

    public ResponseDataDTO modify(BankBranchDto dto) {
        return bankBranchDao.modify(dto);
    }

    public List<BankBranchMaster> getAllBranchId(BankBranchDto dto) {
        return bankBranchDao.getAllBranchId(dto);
    }




    public ResponseDataDTO verifySubmit(BankBranchDto dto) {
        return bankBranchDao.verifySubmit(dto);
    }


    public ResponseDataDTO deleteSubmit(BankBranchDto dto) {
        return bankBranchDao.deleteSubmit(dto);
    }



}
