/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.model.OtherBankBranchesMaster;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface OtherBankBranchesDao.
 */
public interface OtherBankBranchesDao {

    /**
     * Creates the other bank branches.
     *
     * @param otherBankBranches the other bank branches
     */
    void createOtherBankBranches(List<OtherBankBranchesMaster> otherBankBranches);

    /**
     * Delete.
     *
     * @param branchId the branch id
     */
    public void delete(Integer branchId);

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public void delete(String fileName);

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(String fileName);

    public List<OtherBankBranchesMaster> getVerifiedBankBranchesList(final String bankCode, final String branchCode);

    /**
     * Gets the bank branches list.
     *
     * @param bankCode   the bank code
     * @param branchCode the branch code
     * @return the bank branches list
     */
    public List<OtherBankBranchesMaster> getBankBranchesList(final String bankCode, String branchCode);

    /**
     * Gets the other bank branches details.
     *
     * @param branchId the branch id
     * @return the other bank branches details
     */
    public OtherBankBranchesMaster getOtherBankBranchesDetails(Integer branchId);

    /**
     * Gets the other bank branches details.
     *
     * @param branchCode the branch code
     * @param bankCode   the bank code
     * @return the other bank branches details
     */
    public List<OtherBankBranchesMaster> getOtherBankBranchesDetails(String branchCode, String bankCode);

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
    public List<OtherBankBranchesMaster> getUnverifiedRecords();

    /**
     * Gets the unverified records.
     *
     * @param fileName the file name
     * @return the unverified records
     */
    public List<OtherBankBranchesMaster> getUnverifiedRecords(String fileName);

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<OtherBankBranchesMaster> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<OtherBankBranchesMaster> list();

    /**
     * Record list.
     *
     * @return the list
     */
    public List<OtherBankBranchesMaster> recordList();

    /**
     * Save or update.
     *
     * @param masterDetails the master details
     */
    public void saveOrUpdate(OtherBankBranchesMaster masterDetails);

    /**
     * Verify.
     *
     * @param details the details
     */
    public void verify(OtherBankBranchesMaster details);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public void verify(String fileName);

    ResponseDataDTO add(OtherBankBranchesDto dto);

    ResponseDataDTO inquiry(OtherBankBranchesDto dto);

    ResponseDataDTO verifyDeleteSelect(OtherBankBranchesDto dto);

    ResponseDataDTO verifySubmit(OtherBankBranchesDto dto);

    ResponseDataDTO deleteSubmit(OtherBankBranchesDto dto);

    ResponseDataDTO modifySubmit(OtherBankBranchesDto dto);

    ResponseDataDTO modifySelect(OtherBankBranchesDto dto);
}
