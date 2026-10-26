/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.dto.CommonGenericTableDto;
import com.isl.gbm.cbs.integration.model.BranchIfscDetails;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface BranchIfscDetailsDao.
 */
public interface BranchIfscDetailsDao {

    /**
     * Delete.
     *
     * @param branchIfscDetails the branch ifsc details
     */
    public void delete(BranchIfscDetails branchIfscDetails);

    /**
     * Delete.
     *
     * @param ifscFileName the ifsc file name
     */
    public void delete(String ifscFileName);

    /**
     * File name exists.
     *
     * @param ifscFileName the ifsc file name
     * @return true, if successful
     */
    public boolean fileNameExists(String ifscFileName);

    /**
     * Gets the branch ifsc details.
     *
     * @param branchIfscDetailsId the branch ifsc details id
     * @return the branch ifsc details
     */
    public BranchIfscDetails getBranchIfscDetails(Integer branchIfscDetailsId);

    /**
     * Gets the branch ifsc details.
     *
     * @param bankCode   the bank code
     * @param branchCode the branch code
     * @return the branch ifsc details
     */
    public List<BranchIfscDetails> getBranchIfscDetails(String bankCode, String branchCode);

    /**
     * Gets the unverified branch ifsc details.
     *
     * @param fileName the file name
     * @return the unverified branch ifsc details
     */
    public List<BranchIfscDetails> getUnverifiedBranchIfscDetails(String fileName);

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
    public List<BranchIfscDetails> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<BranchIfscDetails> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<BranchIfscDetails> list();

    /**
     * Save or update.
     *
     * @param branchIfscDetails the branch ifsc details
     */
    public void saveOrUpdate(BranchIfscDetails branchIfscDetails);

    /**
     * Upload.
     *
     * @param branchIfscDetailsList the branch ifsc details list
     */
    public void upload(List<BranchIfscDetails> branchIfscDetailsList);

    /**
     * Verify.
     *
     * @param branchIfscDetails the branch ifsc details
     */
    public void verify(BranchIfscDetails branchIfscDetails);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public void verify(String fileName);

    ResponseDataDTO add(BranchIfscDetailsDto branchIfscDetailsDto);

    ResponseDataDTO modify(BranchIfscDetailsDto branchIfscDetailsDto);

    ResponseDataDTO modifySubmit(CommonGenericTableDto commonGenericTableDto);

    ResponseDataDTO inquiry(BranchIfscDetailsDto branchIfscDetailsDto);

    ResponseDataDTO verifyDeleteSelect(BranchIfscDetailsDto branchIfscDetailsDto);

    ResponseDataDTO verifySubmit(CommonGenericTableDto commonGenericTableDto);

    ResponseDataDTO deleteSubmit(CommonGenericTableDto commonGenericTableDto);
}
