/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.dto.wrapper.SchemeCodesDtoAuditLogWrapper;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;
import java.util.ResourceBundle;

/**
 * The Interface SchemeCodesService.
 */
public interface SchemeCodesService {

    /**
     * Builds the details.
     *
     * @param schemeCodesDtoList the scheme codes dto list
     */
    ResponseDataDTO buildDetails(List<SchemeCodesDto> schemeCodesDtoList);

    /**
     * Delete.
     *
     * @param dto the dto
     */
    public void delete(SchemeCodesDto dto);

    /**
     * Delete scheme codes.
     *
     * @param fileName the file name
     */
    ResponseDataDTO deleteSchemeCodes(String fileName,String remarks);

    /**
     * File name exists.
     *
     * @param fileName the file name
     * @return true, if successful
     */
    boolean fileNameExists(String fileName);

    /**
     * Gets the all list.
     *
     * @return the all list
     */
    public List<SchemeCodesDtoAuditLogWrapper> getAllList();

    /**
     * Gets the scheme codes.
     *
     * @param id the id
     * @return the scheme codes
     */
    public SchemeCodesDtoAuditLogWrapper getSchemeCodes(Integer id);

    /**
     * Gets the scheme codes.
     *
     * @param schemeType the scheme type
     * @param schemeCode the scheme code
     * @return the scheme codes
     */
    public List<SchemeCodesDto> getSchemeCodes(String schemeType, String schemeCode);

    /**
     * Gets the scheme codes list.
     *
     * @return the scheme codes list
     */
    public List<SchemeCodesDto> getSchemeCodesList();

    /**
     * Gets the unverified files.
     *
     * @return the unverified files
     */
    List<String> getUnverifiedFiles();

    List<String> getInquiryFiles();

    /**
     * Gets the unverified records.
     *
     * @return the unverified records
     */
    public List<SchemeCodesDtoAuditLogWrapper> getUnverifiedRecords();

    /**
     * Gets the unverified scheme codes details.
     *
     * @param fileName the file name
     * @return the unverified scheme codes details
     */
    List<SchemeCodesDto> getUnverifiedSchemeCodesDetails(String fileName);

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<SchemeCodesDto> getVerifiedRecords();

    /**
     * Save details scheme codes.
     *
     * @param dto the dto
     */
 //   public void saveDetailsSchemeCodes(SchemeCodesDto dto);

    /**
     * Update.
     *
     * @param dto the dto
     */
    public void update(SchemeCodesDto dto);

    /**
     * Verify.
     *
     * @param dto the dto
     */
    public void verify(SchemeCodesDto dto);

    /**
     * Verify scheme codes.
     *
     * @param fileName the file name
     */
    ResponseDataDTO verifySchemeCodes(String fileName);

    public ResponseDataDTO saveDetailsSchemeCodes(SchemeCodesDto dto);

    public ResponseDataDTO inquiry(SchemeCodesDto dto);

    public ResponseDataDTO verDelDetails(SchemeCodesDto dto);

    public ResponseDataDTO modifyDetails(SchemeCodesDto dto);

    public ResponseDataDTO modify(SchemeCodesDto dto);

    public ResponseDataDTO verifySubmit(SchemeCodesDto dto);

    public ResponseDataDTO deleteSubmit(SchemeCodesDto dto);

}
