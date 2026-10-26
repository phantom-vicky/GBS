/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.OtherBankDetailsManager;
import com.isl.gbm.cbs.integration.service.OtherBankDetailsService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * The Class OtherBankDetailsServiceImpl.
 */
@Service
@Transactional
public class OtherBankDetailsServiceImpl implements OtherBankDetailsService {

    /**
     * The other bank details manager.
     */
    @Autowired
    private OtherBankDetailsManager otherBankDetailsManager;


    @Override
    public ResponseDataDTO add(final OtherBankDetailsDto dto) {
        return this.otherBankDetailsManager.add(dto);
    }
    @Override
    public ResponseDataDTO modifySelect(final OtherBankDetailsDto dto) {
        return this.otherBankDetailsManager.modifySelect(dto);
    }
    @Override
    public ResponseDataDTO modifySubmit(final OtherBankDetailsDto dto) {
        return this.otherBankDetailsManager.modifySubmit(dto);
    }

    @Override
    public ResponseDataDTO inquiry(final OtherBankDetailsDto dto) {
        return this.otherBankDetailsManager.inquiry(dto);
    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(final OtherBankDetailsDto dto) {
        return this.otherBankDetailsManager.verifyDeleteSelect(dto);
    }

    @Override
    public ResponseDataDTO verifySubmit(final OtherBankDetailsDto dto) {
        return this.otherBankDetailsManager.verifySubmit(dto);
    }

    @Override
    public ResponseDataDTO deleteSubmit(final OtherBankDetailsDto dto) {
        return this.otherBankDetailsManager.deleteSubmit(dto);
    }


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * deleteOtherBankDetails(java.lang.String)
     */
    @Override
    public ResponseDataDTO deleteOtherBankDetails(final String fileName,final String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
       return this.otherBankDetailsManager.deleteOtherBankDetails(fileName,remarks);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * deleteOtherBankRecords(com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto)
     */
    @Override
    public void deleteOtherBankRecords(final OtherBankDetailsDto otherBankDetailsDto) {
        this.otherBankDetailsManager.deleteOtherBankRecords(otherBankDetailsDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OtherBankDetailsService#fileNameExists(
     * java.lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {

        return this.otherBankDetailsManager.fileNameExists(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * getOtherBankDetails(java.lang.Integer)
     */
    @Override
    public OtherBankDetailsDtoAuditLogWrapper getOtherBankDetails(final Integer id) {
        return this.otherBankDetailsManager.getOtherBankDetails(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * getOtherBankDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<OtherBankDetailsDto> getOtherBankDetails(final String bankCode, final String bankName) {
        return this.otherBankDetailsManager.getOtherBankDetails(bankCode, bankName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * getOtherBankDetailsList()
     */
    @Override
    public List<OtherBankDetailsDto> getOtherBankDetailsList() {
        return this.otherBankDetailsManager.getOtherBankDetailsList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        return this.otherBankDetailsManager.getUnverifiedFiles();
    }

    @Override
    public List<String> getInquiryFiles() {
        return this.otherBankDetailsManager.getInquiryFiles();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * getUnverifiedOtherBankDetails(java.lang.String)
     */
    @Override
    public List<OtherBankDetailsDto> getUnverifiedOtherBankDetails(final String fileName) {
        return this.otherBankDetailsManager.getUnverifiedOtherBankDetails(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * getUnverifiedRecords()
     */
    @Override
    public List<OtherBankDetailsDtoAuditLogWrapper> getUnverifiedRecords() {
        return this.otherBankDetailsManager.getUnverifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * getVerifiedRecords()
     */
    @Override
    public List<OtherBankDetailsDto> getVerifiedRecords() {
        return this.otherBankDetailsManager.getVerifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#list()
     */
    @Override
    public List<OtherBankDetailsDtoAuditLogWrapper> list() {
        return this.otherBankDetailsManager.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OtherBankDetailsService#saveDetails(java.
     * util.List)
     */
    @Override
    public ResponseDataDTO saveDetails(final List<OtherBankDetailsDto> otherBankDetailsDtoList) {
      return otherBankDetailsManager.saveDetails(otherBankDetailsDtoList);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OtherBankDetailsService#saveEntryRecord(
     * com.isl.gbm.cbs.integration.dto.OtherBankDetailsDto)
     */
    @Override
    public void saveEntryRecord(final OtherBankDetailsDto otherBankDetailsDto) {
        throw new NullPointerException();
       // this.otherBankDetailsManager.saveOrUpdate(otherBankDetailsDto);
    }

    /**
     * Sets the other bank details manager.
     *
     * @param otherBankDetailsManager the new other bank details manager
     */
    public void setOtherBankDetailsManager(final OtherBankDetailsManager otherBankDetailsManager) {
        this.otherBankDetailsManager = otherBankDetailsManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OtherBankDetailsService#update(com.isl.
     * gbm.cbs.integration.dto.OtherBankDetailsDto)
     */
    @Override
    public void update(final OtherBankDetailsDto otherBankDetailsDto) {
        this.otherBankDetailsManager.update(otherBankDetailsDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * verifyOtherBankDetails(java.lang.String)
     */
    @Override
    public ResponseDataDTO verifyOtherBankDetails(final String fileName) {
      return  this.otherBankDetailsManager.verifyOtherBankDetails(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankDetailsService#
     * verifyOtherBankEntryRecord(com.isl.gbm.cbs.integration.dto.
     * OtherBankDetailsDto)
     */
    @Override
    public void verifyOtherBankEntryRecord(final OtherBankDetailsDto otherBankDetailsDto) {
        this.otherBankDetailsManager.verifyOtherBankEntryRecord(otherBankDetailsDto);
    }

    @Override
    public void updateUserLastSuccessLogin() {
        this.otherBankDetailsManager.updateUserLastSuccessLogin();
    }

}
