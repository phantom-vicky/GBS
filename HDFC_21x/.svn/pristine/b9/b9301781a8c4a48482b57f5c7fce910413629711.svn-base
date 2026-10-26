/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.OtherBankBranchesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OtherBankBranchesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.OtherBankBranchesManager;
import com.isl.gbm.cbs.integration.service.OtherBankBranchesService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class OtherBankBranchesServiceImpl.
 */
@Service
@Transactional
public class OtherBankBranchesServiceImpl implements OtherBankBranchesService {

    /**
     * The other bank branches manager.
     */
    @Autowired
    private OtherBankBranchesManager otherBankBranchesManager;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#create(com.
     * isl.gbm.cbs.integration.dto.OtherBankBranchesDto)
     */
    @Override
    public void create(final OtherBankBranchesDto dto) {
        this.otherBankBranchesManager.create(dto);
    }

    @Override
    public ResponseDataDTO add(final OtherBankBranchesDto dto) {
        return this.otherBankBranchesManager.add(dto);
    }
    @Override
    public ResponseDataDTO modifySelect(final OtherBankBranchesDto dto) {

        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (!gbmRbiBondRejResnMstDao.isRejectionRemarkInMaster(dto.getRejectionRemarks())) {
            responseDataDTO.setMessage(
                    "Invalid Rejection Remarks");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        return this.otherBankBranchesManager.modifySelect(dto);
    }
    @Override
    public ResponseDataDTO modifySubmit(final OtherBankBranchesDto dto) {
        return this.otherBankBranchesManager.modifySubmit(dto);
    }

    @Override
    public ResponseDataDTO inquiry(final OtherBankBranchesDto dto) {
        return this.otherBankBranchesManager.inquiry(dto);
    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(final OtherBankBranchesDto dto) {
        return this.otherBankBranchesManager.verifyDeleteSelect(dto);
    }

    @Override
    public ResponseDataDTO verifySubmit(final OtherBankBranchesDto dto) {
        return this.otherBankBranchesManager.verifySubmit(dto);
    }

    @Override
    public ResponseDataDTO deleteSubmit(final OtherBankBranchesDto dto) {
        return this.otherBankBranchesManager.deleteSubmit(dto);
    }


    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#delete(com.
     * isl.gbm.cbs.integration.dto.OtherBankBranchesDto)
     */
    @Override
    public void delete(final OtherBankBranchesDto dto) {
        this.otherBankBranchesManager.delete(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OtherBankBranchesService#delete(java.
     * lang.String)
     */
    @Override
    public ResponseDataDTO delete(final String fileName,final String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
      return   this.otherBankBranchesManager.delete(fileName,remarks);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#
     * fileNameExists(java.lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {
        return this.otherBankBranchesManager.fileNameExists(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#
     * getOtherBankBranchesDetails(java.lang.Integer)
     */
    @Override
    public OtherBankBranchesDtoAuditLogWrapper getOtherBankBranchesDetails(final Integer branchId) {
        return this.otherBankBranchesManager.getOtherBankBranchesDetails(branchId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#
     * getOtherBankBranchesDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<OtherBankBranchesDto> getOtherBankBranchesDetails(final String branchCode, final String bankCode) {
        return this.otherBankBranchesManager.getOtherBankBranchesDetails(branchCode, bankCode);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#
     * getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        return this.otherBankBranchesManager.getUnverifiedFiles();
    }


    @Override
    public List<String> getInquiryFiles() {
        return this.otherBankBranchesManager.getInquiryFiles();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#
     * getUnverifiedRecords()
     */
    @Override
    public List<OtherBankBranchesDtoAuditLogWrapper> getUnverifiedRecords() {
        return this.otherBankBranchesManager.getUnverifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#
     * getUnverifiedRecords(java.lang.String)
     */
    @Override
    public List<OtherBankBranchesDto> getUnverifiedRecords(final String fileName) {
        return this.otherBankBranchesManager.getUnverifiedRecords(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#
     * getVerifiedRecords()
     */
    @Override
    public List<OtherBankBranchesDto> getVerifiedRecords() {
        return this.otherBankBranchesManager.getVerifiedRecords();
    }

    @Override
    public List<OtherBankBranchesDto> getVerifiedBankBranchesList(final String bankCode, final String branchCode) {
        return this.otherBankBranchesManager.getVerifiedBankBranchesList(bankCode, branchCode);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#list()
     */
    @Override
    public List<OtherBankBranchesDtoAuditLogWrapper> list() {
        return this.otherBankBranchesManager.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#
     * otherBankBranches(java.util.List)
     */
    @Override
    public ResponseDataDTO otherBankBranches(final List<OtherBankBranchesDto> bulkUpload) {
      return this.otherBankBranchesManager.otherBankBranchesBulkUpload(bulkUpload);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OtherBankBranchesService#recordList()
     */
    @Override
    public List<OtherBankBranchesDto> recordList() {
        return this.otherBankBranchesManager.recordList();
    }

    /**
     * Sets the other bank branches manager.
     *
     * @param otherBankBranchesManager the new other bank branches manager
     */
    public void setOtherBankBranchesManager(final OtherBankBranchesManager otherBankBranchesManager) {
        this.otherBankBranchesManager = otherBankBranchesManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#update(com.
     * isl.gbm.cbs.integration.dto.OtherBankBranchesDto)
     */
    @Override
    public void update(final OtherBankBranchesDto dto) {
        this.otherBankBranchesManager.update(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OtherBankBranchesService#verify(com.
     * isl.gbm.cbs.integration.dto.OtherBankBranchesDto)
     */
    @Override
    public void verify(final OtherBankBranchesDto dto) {
        this.otherBankBranchesManager.verify(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OtherBankBranchesService#verify(java.
     * lang.String)
     */
    @Override
    public ResponseDataDTO verify(final String fileName) {
       return this.otherBankBranchesManager.verify(fileName);
    }
}
