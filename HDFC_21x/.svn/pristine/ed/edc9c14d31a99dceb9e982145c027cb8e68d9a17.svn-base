/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.BankBranchDto;
import com.isl.gbm.cbs.integration.dto.CbsCommonDTo;
import com.isl.gbm.cbs.integration.dto.wrapper.BankBranchDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

/**
 * The Interface BankBranchService.
 */
public interface BankBranchService {

    /**
     * Creates the.
     *
     * @param bankBranchDto the bank branch dto
     */
    void create(BankBranchDto bankBranchDto);

    /**
     * Delete.
     *
     * @param bankBranchDto the bank branch dto
     */
    void delete(BankBranchDto bankBranchDto);

    /**
     * Delete banks.
     *
     * @param fileName the file name
     */
    ResponseDataDTO deleteBanks(String fileName, String remarks);

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    boolean fileNameExists(String fileName);

    boolean unVerifiedDetails();

    /**
     * Gets the all bank details.
     *
     * @return the all bank details
     */
    List<BankBranchDto> getAllBankDetails();

    /**
     * Gets the all bank details for inquiry.
     *
     * @return the all bank details for inquiry
     */
    List<BankBranchDtoAuditLogWrapper> getAllBankDetailsForInquiry();

    /**
     * Gets the bank branch details.
     *
     * @param id the id
     * @return the bank branch details
     */
    BankBranchDtoAuditLogWrapper getBankBranchDetails(int id);

    /**
     * Gets the branch bank list.
     *
     * @return the branch bank list
     */
    public List<BankBranchDto> getBranchBankList();

    /**
     * Gets the sol idand description.
     *
     * @return the sol idand description
     */
    List<BankBranchDto> getSolIdandDescription();

    /**
     * Gets the unverified bank details.
     *
     * @param fileName the file name
     * @return the unverified bank details
     */
    List<BankBranchDto> getUnverifiedBankDetails(String fileName);

    List<BankBranchDto> getInquiryBankDetails(String fileName);

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    List<String> getUnverifiedFiles();

    List<String> getInquiryFiles();

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    List<BankBranchDtoAuditLogWrapper> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    List<BankBranchDto> getVerifiedRecords();

    /**
     * Checks if is sol id exists.
     *
     * @param solId the sol id
     * @return the boolean
     */
    Boolean isSolIdExists(String solId);

    /**
     * Save details.
     *
     * @param branchBankDtoList the branch bank dto list
     */
    ResponseDataDTO saveDetails(List<BankBranchDto> branchBankDtoList);

    /**
     * Update.
     *
     * @param bankBranchDto the bank branch dto
     */
    void update(BankBranchDto bankBranchDto);

    /**
     * Verify.
     *
     * @param bankBranchDto the bank branch dto
     */
    void verify(BankBranchDto bankBranchDto);

    /**
     * Verify banks.
     *
     * @param fileName the file name
     */
    ResponseDataDTO verifyBanks(String fileName);


    public ResponseDataDTO addDetails(BankBranchDto dto);

    public ResponseDataDTO inquiry(BankBranchDto dto);

    public ResponseDataDTO verDelDetails(BankBranchDto dto);

    public ResponseDataDTO modifyDetails(BankBranchDto dto);

    public ResponseDataDTO modify(BankBranchDto dto);

    public ResponseDataDTO verifySubmit(BankBranchDto dto);

    public ResponseDataDTO deleteSubmit(BankBranchDto dto);

    List<BankBranchMaster> getAllBranchId(BankBranchDto dto);


    public ResponseEntity<ResponseDataDTO> getUnverifiedBankDetailsDownload(CbsCommonDTo dto) throws IOException, InvalidFormatException;
}
