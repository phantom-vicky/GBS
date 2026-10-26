/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankBranchesDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface OtherBankBranchesService.
 */
public interface OtherBankBranchesService {

    /**
     * Creates the.
     *
     * @param dto the dto
     */
    public void create(OtherBankBranchesDto dto);

    /**
     * Delete.
     *
     * @param dto the dto
     */
    public void delete(OtherBankBranchesDto dto);

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO delete(String fileName,String remarks);

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(String fileName);

    /**
     * Gets the other bank branches details.
     *
     * @param branchId the branch id
     * @return the other bank branches details
     */
    public OtherBankBranchesDtoAuditLogWrapper getOtherBankBranchesDetails(Integer branchId);

    /**
     * Gets the other bank branches details.
     *
     * @param branchCode the branch code
     * @param bankCode   the bank code
     * @return the other bank branches details
     */
    public List<OtherBankBranchesDto> getOtherBankBranchesDetails(String branchCode, String bankCode);

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
    public List<OtherBankBranchesDtoAuditLogWrapper> getUnverifiedRecords();

    /**
     * Gets the unverified records.
     *
     * @param fileName the file name
     * @return the unverified records
     */
    public List<OtherBankBranchesDto> getUnverifiedRecords(final String fileName);

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<OtherBankBranchesDto> getVerifiedRecords();


    public List<OtherBankBranchesDto> getVerifiedBankBranchesList(final String bankCode, final String branchCode);


    /**
     * List.
     *
     * @return the list
     */
    public List<OtherBankBranchesDtoAuditLogWrapper> list();

    /**
     * Other bank branches.
     *
     * @param otherBankBranches the other bank branches
     */
    ResponseDataDTO otherBankBranches(List<OtherBankBranchesDto> otherBankBranches);

    /**
     * Record list.
     *
     * @return the list
     */
    public List<OtherBankBranchesDto> recordList();

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(OtherBankBranchesDto dto);

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(OtherBankBranchesDto dto);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(String fileName);

    ResponseDataDTO add(OtherBankBranchesDto dto);

    ResponseDataDTO inquiry(OtherBankBranchesDto dto);

    ResponseDataDTO verifyDeleteSelect(OtherBankBranchesDto dto);

    ResponseDataDTO verifySubmit(OtherBankBranchesDto dto);

    ResponseDataDTO deleteSubmit(OtherBankBranchesDto dto);

    ResponseDataDTO modifySubmit(OtherBankBranchesDto dto);

    ResponseDataDTO modifySelect(OtherBankBranchesDto dto);
}
