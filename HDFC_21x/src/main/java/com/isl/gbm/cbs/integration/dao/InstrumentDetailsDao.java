/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.CalendarEntriesDto;
import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.model.InstrumentDetails;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;
import java.util.Map;

/**
 * The Interface InstrumentDetailsDao.
 */
public interface InstrumentDetailsDao {

    /**
     * Delete.
     *
     * @param details the details
     */
    public void delete(InstrumentDetails details);


    public void deleteInstrument(Integer id);

    /**
     * Delete.
     *
     * @paramfileName the file name
     */
    public void delete(String instrumentFileName);

    /**
     * File name exists.
     *
     * @return true, if successful
     * @paramfileName the file name
     */
    public boolean fileNameExists(String instrumentFileName);

    /**
     * Gets the instrument details.
     *
     * @param instrumentId the instrument id
     * @return the instrument details
     */
    public InstrumentDetails getInstrumentDetails(Integer instrumentId);

    /**
     * Gets the instrument details.
     *
     * @param instrumentType the instrument type
     * @param schemeCode     the scheme code
     * @return the instrument details
     */
    public List<InstrumentDetails> getInstrumentDetails(String instrumentType, String schemeCode);

    /**
     * Gets the instrument types.
     *
     * @return the instrument types
     */
    public Map<String, String> getInstrumentTypes();

    public List<InstrumentDetails> getInstrumentTypesList(String schmCode);

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
    public List<InstrumentDetails> getUnverifiedInstruments(String fileName);

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<InstrumentDetails> getUnverifiedRecords();

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<InstrumentDetails> getVerifiedRecords();

    /**
     * List.
     *
     * @return the list
     */
    public List<InstrumentDetails> list();

    /**
     * Save or update.
     *
     * @param instrumentDetails the instrument details
     */
    public void saveOrUpdate(InstrumentDetails instrumentDetails);

    /**
     * Upload.
     *
     * @param instrumentList the instrument list
     */
    public void upload(List<InstrumentDetails> instrumentList);

    /**
     * Verify.
     *
     * @param details the details
     */
    public void verify(InstrumentDetails details);

    /**
     * Verify.
     *
     * @param fileName the file name
     */
    public void verify(String fileName);

    public ResponseDataDTO addDetails(InstrumentDetailsDto dto);

    public ResponseDataDTO inquiry(InstrumentDetailsDto dto);

    public ResponseDataDTO verDelDetails(InstrumentDetailsDto dto);

    public ResponseDataDTO modifyDetails(InstrumentDetailsDto dto);

    public ResponseDataDTO modify(InstrumentDetailsDto dto);

    public ResponseDataDTO verifySubmit(InstrumentDetailsDto dto);

    public ResponseDataDTO deleteSubmit(InstrumentDetailsDto dto);
}
