/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.OfficeAccountPlaceHolderDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.OfficeAccountPlaceHolderManager;
import com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class OfficeAccountPlaceHolderServiceImpl.
 */
@Service
@Transactional
public class OfficeAccountPlaceHolderServiceImpl implements OfficeAccountPlaceHolderService {

    /**
     * The office account place holder manager.
     */
    @Autowired
    private OfficeAccountPlaceHolderManager officeAccountPlaceHolderManager;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * create(com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto)
     */
  /*  @Override
    public void create(final OfficeAccountPlaceHolderDto dto) {
        this.officeAccountPlaceHolderManager.create(dto);
    }
*/
    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#delete(
     * com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto)
     */
    @Override
    public void delete(final OfficeAccountPlaceHolderDto dto) {
        this.officeAccountPlaceHolderManager.delete(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * delete(java.lang.String)
     */
    @Override
    public ResponseDataDTO delete(final String fileName,final String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return this.officeAccountPlaceHolderManager.delete(fileName,remarks);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * fileNameExists(java.lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {
        return this.officeAccountPlaceHolderManager.fileNameExists(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * getOfficeAccountPlaceHolderDetails(java.lang.Integer)
     */
    @Override
    public OfficeAccountPlaceHolderDtoAuditLogWrapper getOfficeAccountPlaceHolderDetails(final Integer officeId) {
        return this.officeAccountPlaceHolderManager.getOfficeAccountPlaceHolderDetails(officeId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * getOfficeAccountPlaceHolderDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<OfficeAccountPlaceHolderDto> getOfficeAccountPlaceHolderDetails(final String bacid) {
        return this.officeAccountPlaceHolderManager.getOfficeAccountPlaceHolderDetails(bacid);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * getUnverifiedFiles()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        return this.officeAccountPlaceHolderManager.getUnverifiedFiles();
    }

    @Override
    public List<String> getInquiryFiles() {
        return this.officeAccountPlaceHolderManager.getInquiryFiles();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * getUnverifiedPlaceHolders(java.lang.String)
     */
    @Override
    public List<OfficeAccountPlaceHolderDto> getUnverifiedPlaceHolders(final String fileName) {
        return this.officeAccountPlaceHolderManager.getUnverifiedPlaceHolders(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * getUnverifiedRecords()
     */
    @Override
    public List<OfficeAccountPlaceHolderDtoAuditLogWrapper> getUnverifiedRecords() {
        return this.officeAccountPlaceHolderManager.getUnverifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * getVerifiedRecords()
     */
    @Override
    public List<OfficeAccountPlaceHolderDto> getVerifiedRecords() {
        return this.officeAccountPlaceHolderManager.getVerifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#list()
     */
    @Override
    public List<OfficeAccountPlaceHolderDtoAuditLogWrapper> list() {
        return this.officeAccountPlaceHolderManager.list();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * officeAccountPlaceHolder(java.util.List)
     */
    @Override
    public ResponseDataDTO officeAccountPlaceHolder(final List<OfficeAccountPlaceHolderDto> bulkUpload) {
      return  this.officeAccountPlaceHolderManager.officeAccountPlaceHolderBulkUpload(bulkUpload);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * recordList()
     */
    @Override
    public List<OfficeAccountPlaceHolderDto> recordList() {
        return this.officeAccountPlaceHolderManager.recordList();
    }

    /**
     * Sets the office account place holder manager.
     *
     * @param officeAccountPlaceHolderManager the new office account place holder manager
     */
    public void setOfficeAccountPlaceHolderManager(
            final OfficeAccountPlaceHolderManager officeAccountPlaceHolderManager) {
        this.officeAccountPlaceHolderManager = officeAccountPlaceHolderManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#update(
     * com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto)
     */
    @Override
    public void update(final OfficeAccountPlaceHolderDto dto) {
        this.officeAccountPlaceHolderManager.update(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#verify(
     * com.isl.gbm.cbs.integration.dto.OfficeAccountPlaceHolderDto)
     */
    @Override
    public void verify(final OfficeAccountPlaceHolderDto dto) {
        this.officeAccountPlaceHolderManager.verify(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.OfficeAccountPlaceHolderService#
     * verify(java.lang.String)
     */
    @Override
    public ResponseDataDTO verify(final String fileName) {
       return this.officeAccountPlaceHolderManager.verify(fileName);
    }

    @Override
    public ResponseDataDTO create(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderManager.create(dto);
    }

    @Override
    public ResponseDataDTO inquiry(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderManager.inquiry(dto);
    }
    @Override
    public ResponseDataDTO verDelDetails(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderManager.verDelDetails(dto);
    }
    @Override
    public ResponseDataDTO modifyDetails(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderManager.modifyDetails(dto);
    }
    @Override
    public ResponseDataDTO modify(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderManager.modify(dto);
    }

    @Override
    public ResponseDataDTO verifySubmit(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderManager.verifySubmit(dto);
    }

    @Override
    public ResponseDataDTO deleteSubmit(OfficeAccountPlaceHolderDto dto) {
        return officeAccountPlaceHolderManager.deleteSubmit(dto);
    }

}
