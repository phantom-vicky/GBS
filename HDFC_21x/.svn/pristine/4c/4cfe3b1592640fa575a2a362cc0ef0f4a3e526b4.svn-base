/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OfficeAccountPlaceHolderDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface OfficeAccountPlaceHolderService.
 */
public interface OfficeAccountPlaceHolderService {

    /**
     * Creates the.
     *
     * @param dto the dto
     */
  //  public void create(OfficeAccountPlaceHolderDto dto);

    /**
     * Delete.
     *
     * @param dto the dto
     */
    public void delete(OfficeAccountPlaceHolderDto dto);

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
     * Gets the office account place holder details.
     *
     * @param officeId the office id
     * @return the office account place holder details
     */
    public OfficeAccountPlaceHolderDtoAuditLogWrapper getOfficeAccountPlaceHolderDetails(Integer officeId);

    /**
     * Gets the office account place holder details.
     *
     * @param bacid the bacid
     * @return the office account place holder details
     */
    public List<OfficeAccountPlaceHolderDto> getOfficeAccountPlaceHolderDetails(String bacid);

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
    public List<OfficeAccountPlaceHolderDto> getUnverifiedPlaceHolders(final String fileName);

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<OfficeAccountPlaceHolderDtoAuditLogWrapper> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<OfficeAccountPlaceHolderDto> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<OfficeAccountPlaceHolderDtoAuditLogWrapper> list();

    /**
     * Office account place holder.
     *
     * @param offAccPhDto the off acc ph dto
     */
    ResponseDataDTO officeAccountPlaceHolder(List<OfficeAccountPlaceHolderDto> offAccPhDto);

    /**
     * Record list.
     *
     * @return the list
     */
    public List<OfficeAccountPlaceHolderDto> recordList();

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(OfficeAccountPlaceHolderDto dto);

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(OfficeAccountPlaceHolderDto dto);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(String fileName);

    public ResponseDataDTO create(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO inquiry(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO verDelDetails(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO modifyDetails(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO modify(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO verifySubmit(OfficeAccountPlaceHolderDto dto);

    public ResponseDataDTO deleteSubmit(OfficeAccountPlaceHolderDto dto);

}
