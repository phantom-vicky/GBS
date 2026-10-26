/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.dto.CommonGenericTableDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BranchIfscDetailsDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;
import java.util.List;

/**
 * The Interface BranchIfscDetailsService.
 */
public interface BranchIfscDetailsService {

    /**
     * Creates the.
     *
     * @param branchIfscDetailsDto the branch ifsc details dto
     */
    public void create(final BranchIfscDetailsDto branchIfscDetailsDto);

    /**
     * Delete.
     *
     * @param branchIfscDetailsDto the branch ifsc details dto
     */
    public void delete(final BranchIfscDetailsDto branchIfscDetailsDto);

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO delete(String fileName, String remarks);

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(String fileName);

    /**
     * Gets the branch ifsc details.
     *
     * @param branchIfscDetailsId the branch ifsc details id
     * @return the branch ifsc details
     */
    public BranchIfscDetailsDtoAuditLogWrapper getBranchIfscDetails(Integer branchIfscDetailsId);

    /**
     * Gets the branch ifsc details.
     *
     * @param bankCode   the bank code
     * @param branchCode the branch code
     * @return the branch ifsc details
     */
    public List<BranchIfscDetailsDto> getBranchIfscDetails(final String bankCode, final String branchCode);

    /**
     * Gets the unverified branch ifsc details.
     *
     * @param fileName the file name
     * @return the unverified branch ifsc details
     */
    public List<BranchIfscDetailsDto> getUnverifiedBranchIfscDetails(final String fileName);

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
    public List<BranchIfscDetailsDtoAuditLogWrapper> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<BranchIfscDetailsDto> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<BranchIfscDetailsDtoAuditLogWrapper> list();

    /**
     * Update.
     *
     * @param branchIfscDetailsDto the branch ifsc details dto
     */
    public void update(final BranchIfscDetailsDto branchIfscDetailsDto);

    /**
     * Upload.
     *
     * @param branchIfscDetailsDtoList the branch ifsc details dto list
     */
    public ResponseDataDTO upload(List<BranchIfscDetailsDto> branchIfscDetailsDtoList);

    /**
     * Verify.
     *
     * @param branchIfscDetailsDto the branch ifsc details dto
     */
    public void verify(final BranchIfscDetailsDto branchIfscDetailsDto);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(String fileName);


    ResponseDataDTO add(BranchIfscDetailsDto dto);

    ResponseDataDTO modify(BranchIfscDetailsDto dto);

    ResponseDataDTO modifySubmit(CommonGenericTableDto commonGenericTableDto);

    ResponseDataDTO inquiry(BranchIfscDetailsDto dto);

    ResponseDataDTO verifyDeleteSelect(BranchIfscDetailsDto dto);

    ResponseDataDTO verifySubmit(CommonGenericTableDto commonGenericTableDto);

    ResponseDataDTO deleteSubmit(CommonGenericTableDto commonGenericTableDto);
}
