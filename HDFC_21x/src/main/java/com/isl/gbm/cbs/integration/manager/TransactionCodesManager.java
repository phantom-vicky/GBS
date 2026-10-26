/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.CbsBulkUploadEnum;
import com.isl.gbm.cbs.integration.dao.CbsIntegerationBulkUploadDao;
import com.isl.gbm.cbs.integration.dao.TransactionCodesDao;
import com.isl.gbm.cbs.integration.dto.CbsIntegerationBulkUploadDto;
import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TransactionCodesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.TransactionCodes;
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
 * The Class TransactionCodesManager.
 */
@Component
public class TransactionCodesManager {

    /**
     * The transaction codes dao.
     */
    @Autowired
    private TransactionCodesDao transactionCodesDao;

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
     * @return the transaction codes dto audit log wrapper
     */
    private TransactionCodesDtoAuditLogWrapper buildAuditLogWrapper(final TransactionCodes master) {
        final TransactionCodesDtoAuditLogWrapper wrapper = new TransactionCodesDtoAuditLogWrapper();
        wrapper.setDto(this.buildTransactionsCodesDto(master));
        wrapper.setAuditLogDto(this.auditLogger.getActiveAuditLogDetails(master));
        return wrapper;
    }

    /**
     * Builds the transaction codes.
     *
     * @param transantionCodesDto the transantion codes dto
     * @return the transaction codes
     */
    private TransactionCodes buildTransactionCodes(final TransactionCodesDto transantionCodesDto) {
        TransactionCodes codes = null;
        if ((null == transantionCodesDto.getId()) || (transantionCodesDto.getId() <= 0)) {
            codes = new TransactionCodes();
        } else {
            codes = this.transactionCodesDao.getTransactionDetails(transantionCodesDto.getId());
        }
        codes.setId(transantionCodesDto.getId());
        codes.setClgTranCode(transantionCodesDto.getClgTranCode());
        codes.setClgTranCodeDesc(transantionCodesDto.getClgTranCodeDesc());
        codes.setInstrumentType(transantionCodesDto.getInstrumentType());
        codes.setSchemeCode(transantionCodesDto.getSchemeCode());
        codes.setCreatedBy(transantionCodesDto.getCreatedBy());
        codes.setCreatedDate(transantionCodesDto.getCreatedDate());
        codes.setVerified(transantionCodesDto.getIsVerified());
        codes.setModifiedBy(transantionCodesDto.getModifiedBy());
        codes.setModifiedDate(transantionCodesDto.getModifiedDate());
        codes.setVerifiedBy(transantionCodesDto.getVerifiedBy());
        codes.setVerifiedDate(transantionCodesDto.getVerifiedDate());
        codes.setFileName(transantionCodesDto.getFileName()!=null ? transantionCodesDto.getFileName().toUpperCase():transantionCodesDto.getFileName());
        codes.setAddText1(transantionCodesDto.getAddText1());
        codes.setAddText2(transantionCodesDto.getAddText2());
        codes.setAddText3(transantionCodesDto.getAddText3());
        codes.setDeleteFlag(transantionCodesDto.isDeleteFlag());
        return codes;
    }

    /**
     * Builds the transaction codes dto list.
     *
     * @param codesList the codes list
     * @return the list
     */
    private List<TransactionCodesDto> buildTransactionCodesDtoList(final List<TransactionCodes> codesList) {
        final List<TransactionCodesDto> dtoList = new ArrayList<>(CollectionUtils.size(codesList));
        for (final TransactionCodes codes : codesList) {
            dtoList.add(this.buildTransactionsCodesDto(codes));
        }
        return dtoList;
    }

    /**
     * Builds the transactions codes dto.
     *
     * @param transactionCodes the transaction codes
     * @return the transaction codes dto
     */
    private TransactionCodesDto buildTransactionsCodesDto(final TransactionCodes transactionCodes) {
        final TransactionCodesDto dto = new TransactionCodesDto();
        dto.setId(transactionCodes.getId());
        dto.setClgTranCode(transactionCodes.getClgTranCode());
        dto.setClgTranCodeDesc(transactionCodes.getClgTranCodeDesc());
        dto.setInstrumentType(transactionCodes.getInstrumentType());
        dto.setSchemeCode(transactionCodes.getSchemeCode());
        dto.setCreatedBy(transactionCodes.getCreatedBy());
        dto.setCreatedDate(transactionCodes.getCreatedDate());
        dto.setModifiedBy(transactionCodes.getModifiedBy());
        dto.setModifiedDate(transactionCodes.getModifiedDate());
        dto.setVerified(transactionCodes.isVerified());
        dto.setVerifiedBy(transactionCodes.getVerifiedBy());
        dto.setVerifiedDate(transactionCodes.getVerifiedDate());
        dto.setFileName(transactionCodes.getFileName());
        dto.setAddText1(transactionCodes.getAddText1());
        dto.setAddText2(transactionCodes.getAddText2());
        dto.setAddText3(transactionCodes.getAddText3());
        dto.setDeleteFlag(transactionCodes.isDeleteFlag());
        return dto;
    }

    /**
     * Creates the.
     *
     * @param transactionCodesDto the transaction codes dto
     */
    public void create(final TransactionCodesDto transactionCodesDto) {
        final TransactionCodes master = this.buildTransactionCodes(transactionCodesDto);
        master.setCreatedBy(UserAccountUtils.getCurrentUsername());
        master.setCreatedDate(new Date());
        this.transactionCodesDao.saveOrUpdate(master);
        this.auditLogger.createAuditLogEntries(master, master.getCreatedDate(), master.getCreatedBy(), null,
                ModuleDisplayNames.CBS, FeatureNames.CBS_LIST_OF_TRANSACTION_CODES);
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO delete(final String fileName, final String remarks) {

       /* final List<TransactionCodes> transactionList = this.transactionCodesDao
                .getUnverifiedTransactions(fileName);

        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (TransactionCodes transactionmaster : transactionList) {
            this.auditLogger.deleteAuditLogRecords(transactionmaster, user, currentDate);
        }*/
      return  this.cbsIntegerationBulkUploadDao.deleteBanks(fileName, CbsBulkUploadEnum.TC.getCbsCode(),remarks);

    }

    /**
     * Delete transaction codes.
     *
     * @param transactionCodesDto the transaction codes dto
     */
    public void deleteTransactionCodes(final TransactionCodesDto transactionCodesDto) {
        final TransactionCodes savedData = this.transactionCodesDao.getTransactionDetails(transactionCodesDto.getId());
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final TransactionCodes master = this.buildTransactionCodes(transactionCodesDto);
        if (BooleanUtils.isNotTrue(savedData.isVerified())) {
            // newly added record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.transactionCodesDao.delete(transactionCodesDto.getId());
        } else {
            // already verified record
            this.auditLogger.deleteAuditLogRecords(master, user, currentDate);
            this.auditLogger.deleteGenModRecords(master, user, currentDate);
        }
    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(final String fileName) {
        return this.transactionCodesDao.fileNameExists(fileName);

    }

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    public List<TransactionCodesDtoAuditLogWrapper> getAllRecords() {
        final List<TransactionCodes> masterList = this.transactionCodesDao.getAllRecords();
        final List<TransactionCodesDtoAuditLogWrapper> wrapperList = new ArrayList<>(masterList.size());
        for (final TransactionCodes master : masterList) {
            wrapperList.add(this.buildAuditLogWrapper(master));
        }
        return wrapperList;
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
    private AuditLoggerParams getAuditLoggerParams(final TransactionCodes oldEntity, final TransactionCodes newEntity,
                                                   final String currentUser, final Date currentDate) {
        final AuditLoggerParams loggerParams = new AuditLoggerParams();
        loggerParams.setCurrentUser(currentUser);
        loggerParams.setLastModifiedUser(oldEntity.getModifiedBy());
        loggerParams.setCurrentDate(currentDate);
        loggerParams.setOldEntity(oldEntity);
        loggerParams.setNewEntity(newEntity);
        loggerParams.setColumnDisplayName(null);
        loggerParams.setModuleDisplayName(ModuleDisplayNames.CBS);
        loggerParams.setFeatureName(FeatureNames.CBS_LIST_OF_TRANSACTION_CODES);
        return loggerParams;
    }

    /**
     * Gets the transaction details.
     *
     * @param id the id
     * @return the transaction details
     */
    public TransactionCodesDtoAuditLogWrapper getTransactionDetails(final Integer id) {
        return this.buildAuditLogWrapper(this.transactionCodesDao.getTransactionDetails(id));
    }

    /**
     * Gets the transaction details.
     *
     * @param clgTranCode    the clg tran code
     * @param instrumentType the instrument type
     * @return the transaction details
     */
    public List<TransactionCodesDto> getTransactionDetails(final String clgTranCode, final String instrumentType) {
        return this.buildTransactionCodesDtoList(
                this.transactionCodesDao.getTransactionDetails(clgTranCode, instrumentType));
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles() {
        return this.transactionCodesDao.getUnverifiedFiles();
    }

    public List<String> getInquiryFiles() {
        return this.transactionCodesDao.getInquiryFiles();
    }

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<TransactionCodesDtoAuditLogWrapper> getUnverifiedRecords() {
        final List<TransactionCodes> masterList = this.transactionCodesDao.getAllRecords();
        final List<TransactionCodesDtoAuditLogWrapper> wrapperList = new ArrayList<>(masterList.size());
        for (final TransactionCodes master : masterList) {
            final AuditLogDto auditLogDto = this.auditLogger.getActiveAuditLogDetails(master);
            if (BooleanUtils.isFalse(master.isVerified()) || (null != auditLogDto)) {
                final TransactionCodesDtoAuditLogWrapper wrapper = new TransactionCodesDtoAuditLogWrapper();
                wrapper.setDto(this.buildTransactionsCodesDto(master));
                wrapper.setAuditLogDto(auditLogDto);
                wrapperList.add(wrapper);
            }
        }
        return wrapperList;
    }

    /**
     * Gets the unverified transaction.
     *
     * @param fileName the file name
     * @return the unverified transaction
     */
    public List<TransactionCodesDto> getUnverifiedTransaction(final String fileName) {

        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = this.cbsIntegerationBulkUploadDao.getUnverifiedBankDetails(fileName,CbsBulkUploadEnum.TC.getCbsCode());
        return cbsIntegerationBulkUploadDto.getTransactionCodesDtoList();
    }

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<TransactionCodesDto> getVerifiedRecords() {
        return this.buildTransactionCodesDtoList(this.transactionCodesDao.getVerifiedRecords());
    }

    /**
     * Checks if is tran code.
     *
     * @param clgTranCode the clg tran code
     * @return true, if is tran code
     */
    public boolean isTranCode(final String clgTranCode) {
        return this.transactionCodesDao.isTranCode(clgTranCode);
    }

    /**
     * List.
     *
     * @return the list
     */
    public List<TransactionCodesDto> list() {
        final List<TransactionCodes> codesList = this.transactionCodesDao.list();
        return this.buildTransactionCodesDtoList(codesList);
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
     * Sets the transaction codes dao.
     *
     * @param transactionCodesDao the new transaction codes dao
     */
    public void setTransactionCodesDao(final TransactionCodesDao transactionCodesDao) {
        this.transactionCodesDao = transactionCodesDao;
    }

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(final TransactionCodesDto dto) {
        final TransactionCodes savedData = this.transactionCodesDao.getTransactionDetails(dto.getId());
        final String currentUser = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        final TransactionCodes master = this.buildTransactionCodes(dto);
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
                this.transactionCodesDao.saveOrUpdate(master);
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
     * @param transactionDtoList the transaction dto list
     */
    public ResponseDataDTO upload(final List<TransactionCodesDto> transactionDtoList) {
        CbsIntegerationBulkUploadDto cbsIntegerationBulkUploadDto = new CbsIntegerationBulkUploadDto();
        cbsIntegerationBulkUploadDto.setTransactionCodesDtoList(transactionDtoList);
        cbsIntegerationBulkUploadDto.setFlag(CbsBulkUploadEnum.TC.getCbsCode());
        cbsIntegerationBulkUploadDto.setFileName(transactionDtoList.size() > 0 ? transactionDtoList.get(0).getFileName() : null);

       return this.cbsIntegerationBulkUploadDao.saveDetails(cbsIntegerationBulkUploadDto);
       /* for (TransactionCodes transactionCodes : transactionCodesList) {
            this.auditLogger.createAuditLogEntries(transactionCodes, currentDate, user, null,
                    ModuleDisplayNames.CBS, FeatureNames.CBS_LIST_OF_TRANSACTION_CODES);
        }*/
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(final String fileName) {

       /* final List<TransactionCodes> transactionList = this.transactionCodesDao
                .getUnverifiedTransactions(fileName);
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        for (TransactionCodes transactionMaster : transactionList) {
            this.auditLogger.verifyAuditLog(user, currentDate, transactionMaster);
        }*/
       return this.cbsIntegerationBulkUploadDao.verifyBanks(fileName,CbsBulkUploadEnum.TC.getCbsCode());

    }

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(final TransactionCodesDto dto) {
        final TransactionCodes master = new TransactionCodes();
        final String user = UserAccountUtils.getCurrentUsername();
        final Date currentDate = new Date();
        master.setId(dto.getId());
        master.setVerifiedDate(currentDate);
        master.setVerifiedBy(user);
        this.auditLogger.verifyAuditLog(user, currentDate, master);
        this.auditLogger.verifyGenModRecords(user, currentDate, master);
        this.transactionCodesDao.verify(master);
    }

    public ResponseDataDTO add(TransactionCodesDto dto) {
        return  this.transactionCodesDao.add(dto);
    }
    public ResponseDataDTO modifySelect(TransactionCodesDto dto) {
        return  this.transactionCodesDao.modifySelect(dto);
    }
    public ResponseDataDTO modifySubmit(TransactionCodesDto dto) {
        return  this.transactionCodesDao.modifySubmit(dto);
    }

    public ResponseDataDTO inquiry(TransactionCodesDto dto) {
        return  this.transactionCodesDao.inquiry(dto);
    }

    public ResponseDataDTO verifyDeleteSelect(TransactionCodesDto dto) {
        return  this.transactionCodesDao.verifyDeleteSelect(dto);
    }

    public ResponseDataDTO verifySubmit(TransactionCodesDto dto) {
        return  this.transactionCodesDao.verifySubmit(dto);
    }

    public ResponseDataDTO deleteSubmit(TransactionCodesDto dto) {
        return  this.transactionCodesDao.deleteSubmit(dto);
    }
}
