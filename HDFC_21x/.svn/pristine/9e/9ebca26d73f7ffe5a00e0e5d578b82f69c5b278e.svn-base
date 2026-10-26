/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.BranchIfscDetailsDto;
import com.isl.gbm.cbs.integration.dto.CommonGenericTableDto;
import com.isl.gbm.cbs.integration.dto.wrapper.BranchIfscDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.BranchIfscDetailsManager;
import com.isl.gbm.cbs.integration.service.BranchIfscDetailsService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class BranchIfscDetailsServiceImpl.
 */
@Service
@Transactional
public class BranchIfscDetailsServiceImpl implements BranchIfscDetailsService {

    /**
     * The manager.
     */
    @Autowired
    private BranchIfscDetailsManager manager;


    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#create(com.afl.
     * gbm.cbs.integration.dto.BranchIfscDetailsDto)
     */
    @Override
    public void create(final BranchIfscDetailsDto branchIfscDetailsDto) {
        this.manager.create(branchIfscDetailsDto);
    }
    @Override
    public ResponseDataDTO add(final BranchIfscDetailsDto branchIfscDetailsDto) {
        return this.manager.add(branchIfscDetailsDto);
    }
    @Override
    public ResponseDataDTO modify(final BranchIfscDetailsDto branchIfscDetailsDto) {
        return this.manager.modify(branchIfscDetailsDto);
    }
    @Override
    public ResponseDataDTO modifySubmit(final CommonGenericTableDto commonGenericTableDto) {
        return this.manager.modifySubmit(commonGenericTableDto);
    }

    @Override
    public ResponseDataDTO inquiry(final BranchIfscDetailsDto branchIfscDetailsDto) {
        return this.manager.inquiry(branchIfscDetailsDto);
    }
    @Override
    public ResponseDataDTO verifyDeleteSelect(final BranchIfscDetailsDto branchIfscDetailsDto) {
        return this.manager.verifyDeleteSelect(branchIfscDetailsDto);
    }
    @Override
    public ResponseDataDTO verifySubmit(final CommonGenericTableDto commonGenericTableDto) {
        return this.manager.verifySubmit(commonGenericTableDto);
    }
    @Override
    public ResponseDataDTO deleteSubmit(final CommonGenericTableDto commonGenericTableDto) {
        return this.manager.deleteSubmit(commonGenericTableDto);
    }
    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#delete(com.afl.
     * gbm.cbs.integration.dto.BranchIfscDetailsDto)
     */
    @Override
    public void delete(final BranchIfscDetailsDto branchIfscDetailsDto) {
        this.manager.delete(branchIfscDetailsDto);
    }

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.service.InstrumentDetailsService#delete(java.lang
     * .String)
     */
    @Override
    public ResponseDataDTO delete(final String fileName,final String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
      return   this.manager.delete(fileName,remarks);
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
     * com.afl.gbm.cbs.integration.service.InstrumentDetailsService#fileNameExists(
     * java.lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {
        return this.manager.fileNameExists(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#
     * getBranchIfscDetails(java.lang.Integer)
     */
    @Override
    public BranchIfscDetailsDtoAuditLogWrapper getBranchIfscDetails(final Integer branchIfscDetailsId) {
        return this.manager.getBranchIfscDetails(branchIfscDetailsId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#
     * getBranchIfscDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<BranchIfscDetailsDto> getBranchIfscDetails(final String bankCode, final String branchCode) {
        return this.manager.getBranchIfscDetails(bankCode, branchCode);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#
     * getUnverifiedBranchIfscDetails(java.lang.String)
     */
    @Override
    public List<BranchIfscDetailsDto> getUnverifiedBranchIfscDetails(final String fileName) {
        return this.manager.getUnverifiedBranchIfscDetails(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.service.InstrumentDetailsService#
     * getUnverifiedFiles()
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
     * @see com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#
     * getUnverifiedRecords()
     */
    @Override
    public List<BranchIfscDetailsDtoAuditLogWrapper> getUnverifiedRecords() {
        return this.manager.getUnverifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#
     * getVerifiedRecords()
     */
    @Override
    public List<BranchIfscDetailsDto> getVerifiedRecords() {
        return this.manager.getVerifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#list()
     */
    @Override
    public List<BranchIfscDetailsDtoAuditLogWrapper> list() {
        return this.manager.list();
    }

    /**
     * Sets the manager.
     *
     * @param manager the new manager
     */
    public void setManager(final BranchIfscDetailsManager manager) {
        this.manager = manager;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#update(com.afl.
     * gbm.cbs.integration.dto.BranchIfscDetailsDto)
     */
    @Override
    public void update(final BranchIfscDetailsDto branchIfscDetailsDto) {
        this.manager.update(branchIfscDetailsDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#upload(java.util
     * .List)
     */
    @Override
    public ResponseDataDTO upload(final List<BranchIfscDetailsDto> branchIfscDetailsDtoList) {
       return this.manager.upload(branchIfscDetailsDtoList);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.service.BranchIfscDetailsService#verify(com.afl.
     * gbm.cbs.integration.dto.BranchIfscDetailsDto)
     */
    @Override
    public void verify(final BranchIfscDetailsDto branchIfscDetailsDto) {
        this.manager.verify(branchIfscDetailsDto);
    }

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    /*
     * (non-Javadoc)
     *
     * @see
     * com.afl.gbm.cbs.integration.service.InstrumentDetailsService#verify(java.lang
     * .String)
     */
    @Override
    public ResponseDataDTO verify(final String fileName) {
       return this.manager.verify(fileName);
    }

}
