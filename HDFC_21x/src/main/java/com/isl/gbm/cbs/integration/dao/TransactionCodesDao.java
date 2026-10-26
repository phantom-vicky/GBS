/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.TransactionCodesDto;
import com.isl.gbm.cbs.integration.model.TransactionCodes;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface TransactionCodesDao.
 */
public interface TransactionCodesDao {

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(int id);

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public void delete(String fileName);

    /**
     * Delete.
     *
     * @param transactionCodes the transaction codes
     */
    public void delete(TransactionCodes transactionCodes);

    /**
     * File name exists.
     *
     * @param transactionFileName the transaction file name
     * @return true, if successful
     */
    public boolean fileNameExists(String transactionFileName);

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    public List<TransactionCodes> getAllRecords();

    /**
     * Gets the tran details list.
     *
     * @param tranCode the tran code
     * @return the tran details list
     */
    public List<TransactionCodes> getTranDetailsList(final String tranCode);

    /**
     * Gets the transaction details.
     *
     * @param id the id
     * @return the transaction details
     */
    public TransactionCodes getTransactionDetails(Integer id);

    /**
     * Gets the transaction details.
     *
     * @param clgTranCode    the clg tran code
     * @param instrumentType the instrument type
     * @return the transaction details
     */
    public List<TransactionCodes> getTransactionDetails(String clgTranCode, String instrumentType);

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
    public List<TransactionCodes> getUnverifiedRecords();

    /**
     * Gets the unverified transactions.
     *
     * @param fileName the file name
     * @return the unverified transactions
     */
    List<TransactionCodes> getUnverifiedTransactions(String fileName);

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<TransactionCodes> getVerifiedRecords();

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
    public List<TransactionCodes> list();

    /**
     * Save or update.
     *
     * @param transactionCodes the transaction codes
     */
    public void saveOrUpdate(TransactionCodes transactionCodes);

    /**
     * Upload.
     *
     * @param transactionList the transaction list
     */
    public void upload(List<TransactionCodes> transactionList);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public void verify(String fileName);

    /**
     * Verify.
     *
     * @param buildTransactionCodes the build transaction codes
     */
    public void verify(TransactionCodes buildTransactionCodes);

    ResponseDataDTO add(TransactionCodesDto dto);

    ResponseDataDTO modifySelect(TransactionCodesDto dto);

    ResponseDataDTO modifySubmit(TransactionCodesDto dto);

    ResponseDataDTO inquiry(TransactionCodesDto dto);

    ResponseDataDTO verifyDeleteSelect(TransactionCodesDto dto);

    ResponseDataDTO verifySubmit(TransactionCodesDto dto);

    ResponseDataDTO deleteSubmit(TransactionCodesDto dto);
}
