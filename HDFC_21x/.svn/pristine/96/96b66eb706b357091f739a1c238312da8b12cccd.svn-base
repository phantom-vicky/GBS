/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TransactionCodesDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface TransactionCodesService.
 */
public interface TransactionCodesService {

    /**
     * Creates the.
     *
     * @param transactionCodesDto the transaction codes dto
     */
    public void create(TransactionCodesDto transactionCodesDto);

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO delete(String fileName,String remarks );

    /**
     * Delete transaction codes.
     *
     * @param transactionCodesDto the transaction codes dto
     */
    public void deleteTransactionCodes(TransactionCodesDto transactionCodesDto);

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(String fileName);

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    public List<TransactionCodesDtoAuditLogWrapper> getAllRecords();

    /**
     * Gets the transaction details.
     *
     * @param id the id
     * @return the transaction details
     */
    public TransactionCodesDtoAuditLogWrapper getTransactionDetails(Integer id);

    /**
     * Gets the transaction details.
     *
     * @param clgTranCode    the clg tran code
     * @param instrumentType the instrument type
     * @return the transaction details
     */
    public List<TransactionCodesDto> getTransactionDetails(String clgTranCode, String instrumentType);

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles();

    public List<String> getInquiryFiles();

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<TransactionCodesDtoAuditLogWrapper> getUnverifiedRecords();

    /**
     * Gets the unverified transactions.
     *
     * @param fileName the file name
     * @return the unverified transactions
     */
    public List<TransactionCodesDto> getUnverifiedTransactions(String fileName);

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<TransactionCodesDto> getVerifiedRecords();

    /**
     * Checks if is tran code.
     *
     * @param clgTranCode the clg tran code
     * @return true, if is tran code
     */
    public boolean isTranCode(String clgTranCode);

    /**
     * List.
     *
     * @return the list
     */
    public List<TransactionCodesDto> list();

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(TransactionCodesDto dto);

    /**
     * Upload.
     *
     * @param transactionDtoList the transaction dto list
     */
    public ResponseDataDTO upload(List<TransactionCodesDto> transactionDtoList);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(String fileName);

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(TransactionCodesDto dto);

    ResponseDataDTO add(TransactionCodesDto dto);

    ResponseDataDTO modifySelect(TransactionCodesDto dto);

    ResponseDataDTO modifySubmit(TransactionCodesDto dto);

    ResponseDataDTO inquiry(TransactionCodesDto dto);

    ResponseDataDTO verifyDeleteSelect(TransactionCodesDto dto);

    ResponseDataDTO verifySubmit(TransactionCodesDto dto);

    ResponseDataDTO deleteSubmit(TransactionCodesDto dto);
}
