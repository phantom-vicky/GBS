/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GbmUserDetailsDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface UserDetailsService.
 */
public interface UserDetailsService {

    /**
     * Creates the.
     *
     * @param dto the dto
     */
    public void create(GbmUserDetailsDto dto);

    /**
     * Delete.
     *
     * @param dto the dto
     */
    public void delete(GbmUserDetailsDto dto);

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
     * Gets the all records.
     *
     * @return the all records
     */
    public List<GbmUserDetailsDto> getAllRecords();

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
    public List<GbmUserDetailsDtoAuditLogWrapper> getUnverifiedRecords();

    /**
     * Gets the unverified user details.
     *
     * @param fileName the file name
     * @return the unverified user details
     */
    public List<GbmUserDetailsDto> getUnverifiedUserDetails(final String fileName);

    /**
     * Gets the user.
     *
     * @param userId the user id
     * @return the user
     */
    public List<GbmUserDetailsDto> getUser(final String userId);

    /**
     * Gets the user work details.
     *
     * @param gbmUserDetailsId the gbm user details id
     * @return the user work details
     */
    public GbmUserDetailsDto getUserWorkDetails(Integer gbmUserDetailsId);

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<GbmUserDetailsDtoAuditLogWrapper> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<GbmUserDetailsDto> list();

    /**
     * Save or update.
     *
     * @param userDetailsDto the user details dto
     */
    public void saveOrUpdate(GbmUserDetailsDto userDetailsDto);

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(GbmUserDetailsDto dto);

    /**
     * Upload.
     *
     * @param userDetailsDtoList the user details dto list
     */
    public ResponseDataDTO upload(List<GbmUserDetailsDto> userDetailsDtoList);

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(GbmUserDetailsDto dto);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(String fileName);

    ResponseDataDTO add(GbmUserDetailsDto dto);
    ResponseDataDTO modifySelect(GbmUserDetailsDto dto);
    ResponseDataDTO modifySubmit(GbmUserDetailsDto dto);
    ResponseDataDTO inquiry(GbmUserDetailsDto dto);
    ResponseDataDTO verifyDeleteSelect(GbmUserDetailsDto dto);
    ResponseDataDTO verifySubmit(GbmUserDetailsDto dto);
    ResponseDataDTO deleteSubmit(GbmUserDetailsDto dto);
}
