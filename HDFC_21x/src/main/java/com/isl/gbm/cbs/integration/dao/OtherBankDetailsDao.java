/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.model.OtherBankDetailsMaster;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * @author pavankumar g
 */

/**
 * The Interface OtherBankDetailsDao.
 */
public interface OtherBankDetailsDao {

    /**
     * Delete other bank details.
     *
     * @param fileName the file name
     */
    public void deleteOtherBankDetails(String fileName);


    public void deleteOtherBankDetailsId(Integer id);

    /**
     * Delete other bank records.
     *
     * @param id the id
     */
    public void deleteOtherBankRecords(Integer id);

    /**
     * File name exists.
     *
     * @param otherBankFileName the other bank file name
     * @return true, if successful
     */
    public boolean fileNameExists(String otherBankFileName);

    /**
     * Gets the bank details list.
     *
     * @param bankCode the bank code
     * @return the bank details list
     */
    public List<OtherBankDetailsMaster> getBankDetailsList(final String bankCode);

    /**
     * Gets the other bank details.
     *
     * @param id the id
     * @return the other bank details
     */
    public OtherBankDetailsMaster getOtherBankDetails(final Integer id);

    /**
     * Gets the other bank details.
     *
     * @param bankCode the bank code
     * @param bankName the bank name
     * @return the other bank details
     */
    public List<OtherBankDetailsMaster> getOtherBankDetails(final String bankCode, final String bankName);

    /**
     * Gets the other bank details list.
     *
     * @return the other bank details list
     */
    public List<OtherBankDetailsMaster> getOtherBankDetailsList();

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles();

    public List<String> getInquiryFiles();

    /**
     * Gets the unverified other bank details.
     *
     * @param fileName the file name
     * @return the unverified other bank details
     */
    public List<OtherBankDetailsMaster> getUnverifiedOtherBankDetails(String fileName);

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<OtherBankDetailsMaster> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<OtherBankDetailsMaster> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<OtherBankDetailsMaster> list();

    /**
     * Save details.
     *
     * @param otherbankdetailslist the otherbankdetailslist
     */
    public void saveDetails(List<OtherBankDetailsMaster> otherbankdetailslist);

    /**
     * Save or update.
     *
     * @param otherBankDetailsMaster the other bank details master
     */
    public void saveOrUpdate(final OtherBankDetailsMaster otherBankDetailsMaster);

    /**
     * Verify.
     *
     * @param otherBankDetailsMaster the other bank details master
     */
    public void verify(final OtherBankDetailsMaster otherBankDetailsMaster);

    /**
     * Verify other bank details.
     *
     * @param fileName the file name
     */
    public void verifyOtherBankDetails(String fileName);

    ResponseDataDTO add(OtherBankDetailsDto dto);

    ResponseDataDTO modifySelect(OtherBankDetailsDto dto);

    ResponseDataDTO modifySubmit(OtherBankDetailsDto dto);

    ResponseDataDTO inquiry(OtherBankDetailsDto dto);

    ResponseDataDTO verifyDeleteSelect(OtherBankDetailsDto dto);

    ResponseDataDTO verifySubmit(OtherBankDetailsDto dto);

    ResponseDataDTO deleteSubmit(OtherBankDetailsDto dto);
}
