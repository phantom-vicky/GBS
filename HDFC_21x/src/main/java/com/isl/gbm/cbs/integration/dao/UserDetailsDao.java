/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.model.GbmUserDetails;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

/**
 * The Interface UserDetailsDao.
 */
public interface UserDetailsDao {

    /**
     * Delete.
     *
     * @paramfileName the file name
     */
    public void delete(String userFileName);


    public void deleteUsingId(Integer id);

    /**
     * File name exists.
     *
     * @return true, if successful
     * @paramfileName the file name
     */
    public boolean fileNameExists(String userFileName);

    /**
     * Gets the all records.
     *
     * @return the all records
     */
    public List<GbmUserDetails> getAllRecords();

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
    public List<GbmUserDetails> getUnverifiedRecords();

    /**
     * Gets the unverified user details.
     *
     * @param fileName the file name
     * @return the unverified user details
     */
    public List<GbmUserDetails> getUnverifiedUserDetails(String fileName);

    /**
     * Gets the user.
     *
     * @param userId the user id
     * @return the user
     */
    public List<GbmUserDetails> getUser(String userId);

    /**
     * Gets the user work details.
     *
     * @param gbmUserDetailsId the gbm user details id
     * @return the user work details
     */
    public GbmUserDetails getUserWorkDetails(Integer gbmUserDetailsId);

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<GbmUserDetails> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<GbmUserDetails> list();

    /**
     * Save or update.
     *
     * @param userDetails the user details
     */
    public int saveOrUpdate(GbmUserDetails userDetails);


    public void update(final GbmUserDetails userDetails);

    /**
     * Upload.
     *
     * @param userDetailsList the user details list
     */
    public void upload(List<GbmUserDetails> userDetailsList);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public void verify(String fileName);


    ResponseDataDTO add(GbmUserDetailsDto dto);
    ResponseDataDTO modifySelect(GbmUserDetailsDto dto);
    ResponseDataDTO modifySubmit(GbmUserDetailsDto dto);
    ResponseDataDTO inquiry(GbmUserDetailsDto dto);
    ResponseDataDTO verifyDeleteSelect(GbmUserDetailsDto dto);
    ResponseDataDTO verifySubmit(GbmUserDetailsDto dto);
    ResponseDataDTO deleteSubmit(GbmUserDetailsDto dto);

}
