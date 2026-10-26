/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TransactionCodesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.TransactionCodesManager;
import com.isl.gbm.cbs.integration.service.TransactionCodesService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class TransactionCodesServiceImpl.
 */
@Service
@Transactional
public class TransactionCodesServiceImpl implements TransactionCodesService {

    /**
     * The manager.
     */
    @Autowired
    private TransactionCodesManager manager;


    @Override
    public ResponseDataDTO add(final TransactionCodesDto dto) {
        return this.manager.add(dto);
    }
    @Override
    public ResponseDataDTO modifySelect(final TransactionCodesDto dto) {
        return this.manager.modifySelect(dto);
    }
    @Override
    public ResponseDataDTO modifySubmit(final TransactionCodesDto dto) {
        return this.manager.modifySubmit(dto);
    }

    @Override
    public ResponseDataDTO inquiry(final TransactionCodesDto dto) {
        return this.manager.inquiry(dto);
    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(final TransactionCodesDto dto) {
        return this.manager.verifyDeleteSelect(dto);
    }

    @Override
    public ResponseDataDTO verifySubmit(final TransactionCodesDto dto) {
        return this.manager.verifySubmit(dto);
    }

    @Override
    public ResponseDataDTO deleteSubmit(final TransactionCodesDto dto) {
        return this.manager.deleteSubmit(dto);
    }



    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.TransactionCodesService#create(com.isl.
     * gbm.cbs.integration.dto.TransactionCodesDto)
     */
    @Override
    public void create(final TransactionCodesDto transactionCodesDto) {
        this.manager.create(transactionCodesDto);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#delete(java.
     * lang.String)
     */
    @Override
    public ResponseDataDTO delete(final String fileName, final String remarks ) {
       return this.manager.delete(fileName,remarks);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#
     * deleteTransactionCodes(com.isl.gbm.cbs.integration.dto.TransactionCodesDto)
     */
    @Override
    public void deleteTransactionCodes(final TransactionCodesDto transactionCodesDto) {
        this.manager.deleteTransactionCodes(transactionCodesDto);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#
     * fileNameExists(java.lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {
        return this.manager.fileNameExists(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.TransactionCodesService#getAllRecords()
     */
    @Override
    public List<TransactionCodesDtoAuditLogWrapper> getAllRecords() {
        return this.manager.getAllRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#
     * getTransactionDetails(java.lang.Integer)
     */
    @Override
    public TransactionCodesDtoAuditLogWrapper getTransactionDetails(final Integer id) {
        return this.manager.getTransactionDetails(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#
     * getTransactionDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<TransactionCodesDto> getTransactionDetails(final String clgTranCode, final String instrumentType) {
        return this.manager.getTransactionDetails(clgTranCode, instrumentType);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#
     * getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        return this.manager.getUnverifiedFiles();
    }

    @Override
    public List<String> getInquiryFiles() {
        return this.manager.getInquiryFiles();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#
     * getUnverifiedRecords()
     */
    @Override
    public List<TransactionCodesDtoAuditLogWrapper> getUnverifiedRecords() {
        return this.manager.getUnverifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#
     * getUnverifiedTransactions(java.lang.String)
     */
    @Override
    public List<TransactionCodesDto> getUnverifiedTransactions(final String fileName) {
        return this.manager.getUnverifiedTransaction(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#
     * getVerifiedRecords()
     */
    @Override
    public List<TransactionCodesDto> getVerifiedRecords() {
        return this.manager.getVerifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.TransactionCodesService#isTranCode(java.
     * lang.String)
     */
    @Override
    public boolean isTranCode(final String clgTranCode) {
        return this.manager.isTranCode(clgTranCode);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#list()
     */
    @Override
    public List<TransactionCodesDto> list() {
        return this.manager.list();
    }

    /**
     * Sets the manager.
     *
     * @param manager the new manager
     */
    public void setManager(final TransactionCodesManager manager) {
        this.manager = manager;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.TransactionCodesService#update(com.isl.
     * gbm.cbs.integration.dto.TransactionCodesDto)
     */
    @Override
    public void update(final TransactionCodesDto dto) {
        this.manager.update(dto);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#upload(java.
     * util.List)
     */
    @Override
    public ResponseDataDTO upload(final List<TransactionCodesDto> transactionDtoList) {
      return  this.manager.upload(transactionDtoList);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.TransactionCodesService#verify(java.
     * lang.String)
     */
    @Override
    public ResponseDataDTO verify(final String fileName) {
      return   this.manager.verify(fileName);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.TransactionCodesService#verify(com.isl.
     * gbm.cbs.integration.dto.TransactionCodesDto)
     */
    @Override
    public void verify(final TransactionCodesDto dto) {
        this.manager.verify(dto);

    }
}
