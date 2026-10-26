/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.model.OfficeAccountPlaceHolderMaster;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface OfficeAccountPlaceHolderDao.
 */
public interface OfficeAccountPlaceHolderDao {
    /**
     * Creates the office account place holder.
     *
     * @param officeAccountPlaceHolder the office account place holder
     */
    void createOfficeAccountPlaceHolder(List<OfficeAccountPlaceHolderMaster> officeAccountPlaceHolder);

    /**
     * Delete.
     *
     * @param id the id
     */
    public void delete(Integer id);

    /**
     * Delete.
     *
     * @paramfileName the file name
     */
    public void delete(String officeFileName);

    /**
     * File name exists.
     *
     * @return true, if successful
     * @paramfileName the file name
     */
    public boolean fileNameExists(String officeFileName);

    /**
     * Gets the office account place holder details.
     *
     * @param officeId the office id
     * @return the office account place holder details
     */
    public OfficeAccountPlaceHolderMaster getOfficeAccountPlaceHolderDetails(Integer officeId);

    /**
     * Gets the office account place holder details.
     *
     * @param bacid the bacid
     * @return the office account place holder details
     */
    public List<OfficeAccountPlaceHolderMaster> getOfficeAccountPlaceHolderDetails(String bacid);

    /**
     * Gets the office account place holder details.
     *
     * @return the office account place holder details
     * @parambacid list of bacid
     */
    public List<OfficeAccountPlaceHolderMaster> getOfficeAccountPlaceHolderDetailsForList(List<String> bacidList);

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles();

    public List<String> getInquiryFiles();

    /**
     * Gets the unverified place holders.
     *
     * @param fileName the file name
     * @return the unverified place holders
     */
    public List<OfficeAccountPlaceHolderMaster> getUnverifiedPlaceHolders(String fileName);

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<OfficeAccountPlaceHolderMaster> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<OfficeAccountPlaceHolderMaster> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<OfficeAccountPlaceHolderMaster> list();

    /**
     * Record list.
     *
     * @return the list
     */
    public List<OfficeAccountPlaceHolderMaster> recordList();

    /**
     * Save or update.
     *
     * @param masterDetails the master details
     */
    public void saveOrUpdate(OfficeAccountPlaceHolderMaster masterDetails);

    /**
     * Verify.
     *
     * @param details the details
     */
    public void verify(OfficeAccountPlaceHolderMaster details);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public void verify(String fileName);

    public ResponseDataDTO create(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO inquiry(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO verDelDetails(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO modifyDetails(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO modify(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO verifySubmit(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO deleteSubmit(OfficeAccountPlaceHolderDto dto);
}
