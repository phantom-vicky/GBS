/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.InstrumentDetailsDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.InstrumentDetailsManager;
import com.isl.gbm.cbs.integration.service.InstrumentDetailsService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * The Class InstrumentDetailsServiceImpl.
 */
@Service
@Transactional
public class InstrumentDetailsServiceImpl implements InstrumentDetailsService {

    /**
     * The manager.
     */
    @Autowired
    private InstrumentDetailsManager manager;


    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.InstrumentDetailsService#create(com.isl.
     * gbm.cbs.integration.dto.InstrumentDetailsDto)
     */
    @Override
    public void create(final InstrumentDetailsDto dto) {
        this.manager.create(dto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.InstrumentDetailsService#delete(com.isl.
     * gbm.cbs.integration.dto.InstrumentDetailsDto)
     */
    @Override
    public void delete(final InstrumentDetailsDto dto) {
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
     * @see
     * com.isl.gbm.cbs.integration.service.InstrumentDetailsService#delete(java.lang
     * .String)
     */
    @Override
    public ResponseDataDTO delete(final String fileName, final String remarks) {
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
     * com.isl.gbm.cbs.integration.service.InstrumentDetailsService#fileNameExists(
     * java.lang.String)
     */
    @Override
    public boolean fileNameExists(final String fileName) {
        return this.manager.fileNameExists(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.InstrumentDetailsService#
     * getInstrumentDetails(java.lang.String)
     */
    @Override
    public InstrumentDetailsDtoAuditLogWrapper getInstrumentDetails(final Integer instrumentId) {
        return this.manager.getInstrumentDetails(instrumentId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.InstrumentDetailsService#
     * getInstrumentDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<InstrumentDetailsDto> getInstrumentDetails(final String instrumentType, final String schemeCode) {
        return this.manager.getInstrumentDetails(instrumentType, schemeCode);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.InstrumentDetailsService#
     * getInstrumentTypes()
     */
    @Override
    public Map<String, String> getInstrumentTypes() {
        return this.manager.getInstrumentTypes();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.InstrumentDetailsService#
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
     * @see com.isl.gbm.cbs.integration.service.InstrumentDetailsService#
     * getUnverifiedInstruments(java.lang.String)
     */
    @Override
    public List<InstrumentDetailsDto> getUnverifiedInstruments(final String fileName) {
        return this.manager.getUnverifiedInstruments(fileName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.InstrumentDetailsService#
     * getUnverifiedRecords()
     */
    @Override
    public List<InstrumentDetailsDtoAuditLogWrapper> getUnverifiedRecords() {
        return this.manager.getUnverifiedRecords();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.InstrumentDetailsService#
     * getVerifiedRecords()
     */
    @Override
    public List<InstrumentDetailsDto> getVerifiedRecords() {
        return this.manager.getVerifiedRecords();
    }

    /**
     * List.
     *
     * @return the list
     */
    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.InstrumentDetailsService#list()
     */
    @Override
    public List<InstrumentDetailsDtoAuditLogWrapper> list() {
        return this.manager.list();
    }

    /**
     * Sets the manager.
     *
     * @param manager the new manager
     */
    public void setManager(final InstrumentDetailsManager manager) {
        this.manager = manager;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.InstrumentDetailsService#update(com.isl.
     * gbm.cbs.integration.dto.InstrumentDetailsDto)
     */
    @Override
    public void update(final InstrumentDetailsDto dto) {
        this.manager.update(dto);
    }

    /**
     * Upload.
     *
     * @param instrumentDtoList the instrument dto list
     */
    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.InstrumentDetailsService#upload(java.util
     * .List)
     */
    @Override
    public ResponseDataDTO upload(final List<InstrumentDetailsDto> instrumentDtoList) {
       return this.manager.upload(instrumentDtoList);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.InstrumentDetailsService#verify(com.isl.
     * gbm.cbs.integration.dto.InstrumentDetailsDto)
     */
    @Override
    public void verify(final InstrumentDetailsDto dto) {
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
     * @see
     * com.isl.gbm.cbs.integration.service.InstrumentDetailsService#verify(java.lang
     * .String)
     */
    @Override
    public ResponseDataDTO verify(final String fileName) {
      return   this.manager.verify(fileName);
    }

    @Override
    public ResponseDataDTO addDetails(InstrumentDetailsDto dto) {
        return manager.addDetails(dto);
    }

    @Override
    public ResponseDataDTO inquiry(InstrumentDetailsDto dto) {
        return manager.inquiry(dto);
    }
    @Override
    public ResponseDataDTO verDelDetails(InstrumentDetailsDto dto) {
        return manager.verDelDetails(dto);
    }
    @Override
    public ResponseDataDTO modifyDetails(InstrumentDetailsDto dto) {
        return manager.modifyDetails(dto);
    }
    @Override
    public ResponseDataDTO modify(InstrumentDetailsDto dto) {
        return manager.modify(dto);
    }

    @Override
    public ResponseDataDTO verifySubmit(InstrumentDetailsDto dto) {
        return manager.verifySubmit(dto);
    }

    @Override
    public ResponseDataDTO deleteSubmit(InstrumentDetailsDto dto) {
        return manager.deleteSubmit(dto);
    }


}
