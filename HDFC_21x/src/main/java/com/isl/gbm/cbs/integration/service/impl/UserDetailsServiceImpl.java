/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.GbmUserDetailsDto;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GbmUserDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.UserDetailsManager;
import com.isl.gbm.cbs.integration.service.UserDetailsService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * The manager.
     */
    @Autowired
    private UserDetailsManager manager;

    @Override
    public ResponseDataDTO add(final GbmUserDetailsDto dto)
    {
        return this.manager.add(dto);
    }
    @Override
    public ResponseDataDTO modifySelect(final GbmUserDetailsDto dto) {
        return this.manager.modifySelect(dto);
    }
    @Override
    public ResponseDataDTO modifySubmit(final GbmUserDetailsDto dto) {
        return this.manager.modifySubmit(dto);
    }

    @Override
    public ResponseDataDTO inquiry(final GbmUserDetailsDto dto) {

        return this.manager.inquiry(dto);
    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(final GbmUserDetailsDto dto) {
        return this.manager.verifyDeleteSelect(dto);
    }

    @Override
    public ResponseDataDTO verifySubmit(final GbmUserDetailsDto dto) {
        return this.manager.verifySubmit(dto);
    }

    @Override
    public ResponseDataDTO deleteSubmit(final GbmUserDetailsDto dto) {
        return this.manager.deleteSubmit(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#create(com.isl.gbm.cbs
     * .integration.dto.GbmUserDetailsDto)
     */
    @Override
    public void create(final GbmUserDetailsDto dto) {
        this.manager.create(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#delete(com.isl.gbm.cbs
     * .integration.dto.GbmUserDetailsDto)
     */
    @Override
    public void delete(final GbmUserDetailsDto dto) {
        this.manager.delete(dto);
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.UserDetailsService#delete(java.lang.
     * String)
     */
    @Override
    public ResponseDataDTO delete(final String fileName,final String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
       return this.manager.delete(fileName,remarks);
    }

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#fileNameExists(java.
     * lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {
        return this.manager.fileNameExists(fileName);
    }

    @Override
    public List<GbmUserDetailsDto> getAllRecords() {
        return this.manager.getAllRecords();
    }

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        return this.manager.getUnverifiedFiles();
    }

    @Override
    public List<String> getInquiryFiles() {
        return this.manager.getInquiryFiles();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#getUnverifiedRecords()
     */
    @Override
    public List<GbmUserDetailsDtoAuditLogWrapper> getUnverifiedRecords() {
        return this.manager.getUnverifiedRecords();
    }

    /**
     * Gets the unverified user details.
     *
     * @param fileName the file name
     * @return the unverified user details
     */
    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.UserDetailsService#
     * getUnverifiedUserDetails(java.lang.String)
     */
    @Override
    public List<GbmUserDetailsDto> getUnverifiedUserDetails(final String fileName) {
        return this.manager.getUnverifiedUserDetails(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#getUser(java.lang.
     * String)
     */
    @Override
    public List<GbmUserDetailsDto> getUser(final String userId) {
        return this.manager.getUser(userId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#getUserWorkDetails(
     * java.lang.String)
     */
    @Override
    public GbmUserDetailsDto getUserWorkDetails(final Integer gbmUserDetailsId) {
        return this.manager.getUserWorkDetails(gbmUserDetailsId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#getVerifiedRecords()
     */
    @Override
    public List<GbmUserDetailsDtoAuditLogWrapper> getVerifiedRecords() {
        return this.manager.getUnverifiedRecords();
    }

    /**
     * List.
     *
     * @return the list
     */
    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.UserDetailsService#list()
     */
    @Override
    public List<GbmUserDetailsDto> list() {
        return this.manager.list();
    }

    /**
     * Save or update.
     *
     * @param userDetailsDto the user details dto
     */
    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#saveOrUpdate(com.isl.
     * gbm.cbs.integration.dto.GbmUserDetailsDto)
     */
    @Override
    public void saveOrUpdate(final GbmUserDetailsDto userDetailsDto) {
        this.manager.saveOrUpdate(userDetailsDto);
    }

    /**
     * Sets the manager.
     *
     * @param manager the new manager
     */
    public void setManager(final UserDetailsManager manager) {
        this.manager = manager;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#update(com.isl.gbm.cbs
     * .integration.dto.GbmUserDetailsDto)
     */
    @Override
    public void update(final GbmUserDetailsDto dto) {
        this.manager.update(dto);
    }

    /**
     * Upload.
     *
     * @param userDetailsDtoList the user details dto list
     */
    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#upload(java.util.List)
     */
    @Override
    public ResponseDataDTO upload(final List<GbmUserDetailsDto> userDetailsDtoList) {
        return  this.manager.upload(userDetailsDtoList);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.UserDetailsService#verify(com.isl.gbm.cbs
     * .integration.dto.GbmUserDetailsDto)
     */
    @Override
    public void verify(final GbmUserDetailsDto dto) {
        this.manager.verify(dto);
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.UserDetailsService#verify(java.lang.
     * String)
     */
    @Override
    public ResponseDataDTO verify(final String fileName) {
       return this.manager.verify(fileName);
    }

}
