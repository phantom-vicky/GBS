/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankDetailsDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface OtherBankDetailsService.
 */
public interface OtherBankDetailsService {

    /**
     * Delete other bank details.
     *
     * @param fileName the file name
     */
    ResponseDataDTO deleteOtherBankDetails(String fileName,String remarks);

    /**
     * Delete other bank records.
     *
     * @param otherBankDetailsDto the other bank details dto
     */
    void deleteOtherBankRecords(OtherBankDetailsDto otherBankDetailsDto);

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    boolean fileNameExists(String fileName);

    /**
     * Gets the other bank details.
     *
     * @param id the id
     * @return the other bank details
     */
    public OtherBankDetailsDtoAuditLogWrapper getOtherBankDetails(Integer id);

    /**
     * Gets the other bank details.
     *
     * @param bankCode the bank code
     * @param bankName the bank name
     * @return the other bank details
     */
    public List<OtherBankDetailsDto> getOtherBankDetails(String bankCode, String bankName);

    /**
     * Gets the other bank details list.
     *
     * @return the other bank details list
     */
    public List<OtherBankDetailsDto> getOtherBankDetailsList();

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    List<String> getUnverifiedFiles();

    List<String> getInquiryFiles();

    /**
     * Gets the unverified other bank details.
     *
     * @param fileName the file name
     * @return the unverified other bank details
     */
    List<OtherBankDetailsDto> getUnverifiedOtherBankDetails(String fileName);

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<OtherBankDetailsDtoAuditLogWrapper> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<OtherBankDetailsDto> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<OtherBankDetailsDtoAuditLogWrapper> list();

    /**
     * Save details.
     *
     * @param otherBankDetailsDtoList the other bank details dto list
     */
    ResponseDataDTO saveDetails(List<OtherBankDetailsDto> otherBankDetailsDtoList);

    /**
     * Save entry record.
     *
     * @param otherBankDetailsDto the other bank details dto
     */
    void saveEntryRecord(OtherBankDetailsDto otherBankDetailsDto);

    /**
     * Update.
     *
     * @param otherBankDetailsDto the other bank details dto
     */
    void update(OtherBankDetailsDto otherBankDetailsDto);

    /**
     * Verify other bank details.
     *
     * @param fileName the file name
     */
    ResponseDataDTO verifyOtherBankDetails(String fileName);

    /**
     * Verify other bank entry record.
     *
     * @param otherBankDetailsDto the other bank details dto
     */
    void verifyOtherBankEntryRecord(OtherBankDetailsDto otherBankDetailsDto);

    void updateUserLastSuccessLogin();

    ResponseDataDTO add(OtherBankDetailsDto dto);

    ResponseDataDTO modifySelect(OtherBankDetailsDto dto);

    ResponseDataDTO modifySubmit(OtherBankDetailsDto dto);

    ResponseDataDTO inquiry(OtherBankDetailsDto dto);

    ResponseDataDTO verifyDeleteSelect(OtherBankDetailsDto dto);

    ResponseDataDTO verifySubmit(OtherBankDetailsDto dto);

    ResponseDataDTO deleteSubmit(OtherBankDetailsDto dto);
}
