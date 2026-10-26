/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BankBranchDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface BankBranchDao.
 */
public interface BankBranchDao {

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(int id);

    public void deleteId(int id);

    /**
     * Delete banks.
     *
     * @param bankFileName the bank file name
     */
    public void deleteBanks(String bankFileName);

    /**
     * File name exists.
     *
     * @param bankFileName the bank file name
     * @return true, if successful
     */
    public boolean fileNameExists(String bankFileName);

    public boolean unVerifiedDetails();



    /**
     * Gets the all bank details.
     *
     * @return the all bank details
     */
    public List<BankBranchMaster> getAllBankDetails();

    /**
     * Gets the all bank details for inquiry.
     *
     * @return the all bank details for inquiry
     */
    public List<BankBranchMaster> getAllBankDetailsForInquiry();

    /**
     * Gets the bank branch details.
     *
     * @param id the id
     * @return the bank branch details
     */
    public BankBranchMaster getBankBranchDetails(int id);

    /**
     * Gets the branch bank list.
     *
     * @return the branch bank list
     */
    public List<BankBranchMaster> getBranchBankList();

    /**
     * Gets the sol details.
     *
     * @param solId the sol id
     * @return the sol details
     */
    public List<BankBranchMaster> getSolDetails(String solId);

    /**
     * Gets the sol idand description.
     *
     * @return the sol idand description
     */
    public List<BankBranchMaster> getSolIdandDescription();

    /**
     * Gets the unverified bank details.
     *
     * @param fileName the file name
     * @return the unverified bank details
     */
    public List<BankBranchMaster> getUnverifiedBankDetails(String fileName);

    public List<BankBranchMaster> getInquiryBankDetails(String fileName);

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
    public List<BankBranchMaster> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<BankBranchMaster> getVerifiedRecords();

    /**
     * Checks if is sol id exists.
     *
     * @param solId the sol id
     * @return the boolean
     */
    public Boolean isSolIdExists(String solId);

    /**
     * Save details.
     *
     * @param branchBankList the branch bank list
     */
    public void saveDetails(List<BankBranchMaster> branchBankList);

    /**
     * Save or update.
     *
     * @param branchBankMaster the branch bank master
     */
    public void saveOrUpdate(BankBranchMaster branchBankMaster);

    /**
     * Verify.
     *
     * @param master the master
     */
    public void verify(BankBranchMaster master);

    /**
     * Verify banks.
     *
     * @param fileName the file name
     */
    public void verifyBanks(String fileName);

    List<BankBranchDtoAuditLogWrapper> getAllDetails();

    public ResponseDataDTO addDetails(BankBranchDto dto);

    public ResponseDataDTO inquiry(BankBranchDto dto);

    public ResponseDataDTO verDelDetails(BankBranchDto dto);

    public ResponseDataDTO modifyDetails(BankBranchDto dto);

    public ResponseDataDTO modify(BankBranchDto dto);


    List<BankBranchMaster> getAllBranchId(BankBranchDto dto);


    public ResponseDataDTO verifySubmit(BankBranchDto dto);

    public ResponseDataDTO deleteSubmit(BankBranchDto dto);
}
