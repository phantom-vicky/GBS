/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.SchemeCodesDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.SchemeCodesManager;
import com.isl.gbm.cbs.integration.service.SchemeCodesService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class SchemeCodesServiceImpl.
 */
@Service
@Transactional
public class SchemeCodesServiceImpl implements SchemeCodesService {

    /**
     * The scheme codes manager.
     */
    @Autowired
    private SchemeCodesManager schemeCodesManager;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.SchemeCodesService#buildDetails(java.
     * util.List)
     */
    @Override
    public ResponseDataDTO buildDetails(final List<SchemeCodesDto> schemeCodesDtoList) {
      return   this.schemeCodesManager.buildDetails(schemeCodesDtoList);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#delete(com.isl.
     * gbm.cbs.integration.dto.SchemeCodesDto)
     */
    @Override
    public void delete(final SchemeCodesDto dto) {
        this.schemeCodesManager.delete(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.SchemeCodesService#deleteSchemeCodes(
     * java.lang.String)
     */
    @Override
    public ResponseDataDTO deleteSchemeCodes(final String fileName,final String remarks) {
        if (remarks == null || remarks.trim().isEmpty()) {
            ResponseDataDTO responseDataDTO = new ResponseDataDTO();
            responseDataDTO.setMessage("RejectionRemarks is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return  this.schemeCodesManager.deleteSchemeCodes(fileName,remarks);

    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#fileNameExists(
     * java.lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {
        return this.schemeCodesManager.fileNameExists(fileName);
    }

    /**
     * getAll.
     *
     * @return the get all list
     */
    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#list()
     */
    @Override
    public List<SchemeCodesDtoAuditLogWrapper> getAllList() {
        return this.schemeCodesManager.getAllList();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#
     * getSchemeCodes(Integer)
     */
    @Override
    public SchemeCodesDtoAuditLogWrapper getSchemeCodes(final Integer id) {
        return this.schemeCodesManager.getSchemeCodes(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#
     * getSchemeCodes(java.lang.String, java.lang.String)
     */
    @Override
    public List<SchemeCodesDto> getSchemeCodes(final String schemeType, final String schemeCode) {
        return this.schemeCodesManager.getSchemeCodes(schemeType, schemeCode);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.SchemeCodesService#getSchemeCodesList ()
     */
    @Override
    public List<SchemeCodesDto> getSchemeCodesList() {
        return this.schemeCodesManager.getSchemeCodesList();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.SchemeCodesService#getUnverifiedFiles ()
     */
    @Override
    public List<String> getUnverifiedFiles() {
        return this.schemeCodesManager.getUnverifiedFiles();
    }

    @Override
    public List<String> getInquiryFiles() {
        return this.schemeCodesManager.getInquiryFiles();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#
     * getUnverifiedRecords()
     */
    @Override
    public List<SchemeCodesDtoAuditLogWrapper> getUnverifiedRecords() {
        return this.schemeCodesManager.getUnverifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#
     * getUnverifiedSchemeCodesDetails(java.lang.String)
     */
    @Override
    public List<SchemeCodesDto> getUnverifiedSchemeCodesDetails(final String fileName) {
        return this.schemeCodesManager.getUnverifiedSchemeCodesDetails(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#
     * getVerifiedRecords()
     */
    @Override
    public List<SchemeCodesDto> getVerifiedRecords() {
        return this.schemeCodesManager.getVerifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodeService#create(com.isl.
     * gbm.cbs.integration.dto.SchemeCodesDto)
     */
   /* @Override
    public void saveDetailsSchemeCodes(final SchemeCodesDto dto) {
        this.schemeCodesManager.saveDetailsSchemeCodes(dto);
    }*/

    /**
     * Sets the scheme codes manager.
     *
     * @param schemeCodesManager the new scheme codes manager
     */
    public void setSchemeCodesManager(final SchemeCodesManager schemeCodesManager) {
        this.schemeCodesManager = schemeCodesManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#update(com.isl.
     * gbm.cbs.integration.dto.SchemeCodesDto)
     */
    @Override
    public void update(final SchemeCodesDto dto) {
        this.schemeCodesManager.update(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SchemeCodesService#verify(com.isl.
     * gbm.cbs.integration.dto.SchemeCodesDto)
     */
    @Override
    public void verify(final SchemeCodesDto dto) {
        this.schemeCodesManager.verify(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.SchemeCodesService#verifySchemeCodes(
     * java.lang.String)
     */
    @Override
    public ResponseDataDTO verifySchemeCodes(final String fileName) {
      return this.schemeCodesManager.verifySchemeCodes(fileName);

    }

    @Override
    public ResponseDataDTO saveDetailsSchemeCodes(SchemeCodesDto dto) {
        return schemeCodesManager.saveDetailsSchemeCodes(dto);
    }
    @Override
    public ResponseDataDTO inquiry(SchemeCodesDto dto) {
        return schemeCodesManager.inquiry(dto);
    }
    @Override
    public ResponseDataDTO verDelDetails(SchemeCodesDto dto) {
        return schemeCodesManager.verDelDetails(dto);
    }
    @Override
    public ResponseDataDTO modifyDetails(SchemeCodesDto dto) {
        return schemeCodesManager.modifyDetails(dto);
    }
    @Override
    public ResponseDataDTO modify(SchemeCodesDto dto) {
        return schemeCodesManager.modify(dto);
    }

    @Override
    public ResponseDataDTO verifySubmit(SchemeCodesDto dto) {
        return schemeCodesManager.verifySubmit(dto);
    }

    @Override
    public ResponseDataDTO deleteSubmit(SchemeCodesDto dto) {
        return schemeCodesManager.deleteSubmit(dto);
    }


}
