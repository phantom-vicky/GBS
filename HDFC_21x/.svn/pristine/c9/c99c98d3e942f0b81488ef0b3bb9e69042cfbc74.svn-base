/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.wrapper.InstrumentDetailsDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;
import java.util.Map;

/**
 * The Interface InstrumentDetailsService.
 */
public interface InstrumentDetailsService {

    /**
     * Creates the.
     *
     * @param dto the dto
     */
    public void create(InstrumentDetailsDto dto);

    /**
     * Delete.
     *
     * @param dto the dto
     */
    public void delete(InstrumentDetailsDto dto);

    /**
     * Delete.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO delete(String fileName, final String remarks);

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    public boolean fileNameExists(String fileName);

    /**
     * Gets the instrument details.
     *
     * @param instrumentId the instrument id
     * @return the instrument details
     */
    public InstrumentDetailsDtoAuditLogWrapper getInstrumentDetails(Integer instrumentId);

    /**
     * Gets the instrument details.
     *
     * @param instrumentType the instrument type
     * @param schemeCode     the scheme code
     * @return the instrument details
     */
    public List<InstrumentDetailsDto> getInstrumentDetails(String instrumentType, String schemeCode);

    /**
     * Gets the instrument types.
     *
     * @return the instrument types
     */
    public Map<String, String> getInstrumentTypes();

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    public List<String> getUnverifiedFiles();

    public List<String> getInquiryFiles();

    /**
     * Gets the unverified instruments.
     *
     * @param fileName the file name
     * @return the unverified instruments
     */
    public List<InstrumentDetailsDto> getUnverifiedInstruments(final String fileName);

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<InstrumentDetailsDtoAuditLogWrapper> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<InstrumentDetailsDto> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<InstrumentDetailsDtoAuditLogWrapper> list();

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(InstrumentDetailsDto dto);

    /**
     * Upload.
     *
     * @param instrumentDtoList the instrument dto list
     */
    public ResponseDataDTO upload(List<InstrumentDetailsDto> instrumentDtoList);

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(InstrumentDetailsDto dto);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public ResponseDataDTO verify(String fileName);

    public ResponseDataDTO addDetails(InstrumentDetailsDto dto);

    public ResponseDataDTO inquiry(InstrumentDetailsDto dto);

    public ResponseDataDTO verDelDetails(InstrumentDetailsDto dto);

    public ResponseDataDTO modifyDetails(InstrumentDetailsDto dto);

    public ResponseDataDTO modify(InstrumentDetailsDto dto);

    public ResponseDataDTO verifySubmit(InstrumentDetailsDto dto);

    public ResponseDataDTO deleteSubmit(InstrumentDetailsDto dto);
}
