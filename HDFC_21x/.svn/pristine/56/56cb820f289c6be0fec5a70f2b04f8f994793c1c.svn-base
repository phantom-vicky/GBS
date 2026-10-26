/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.dao;

import com.isl.gbm.cbs.integration.dto.InstrumentDetailsDto;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.model.SchemeCodes;
import com.isl.gbm.common.dto.ResponseDataDTO;

import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Interface SchemeCodeDao.
 */
public interface SchemeCodeDao {

    /**
     * Builds the details.
     *
     * @param schemeCodesList the scheme codes list
     */
    void buildDetails(List<SchemeCodes> schemeCodesList);

    /**
     * Delete.
     *
     * @param codes the codes
     */
    public void delete(SchemeCodes codes);

    /**
     * Delete scheme codes.
     *
     * @param fileName the file name
     */
    void deleteSchemeCodes(String fileName);

    void deleteSchemeCodesId(Integer id);

    /**
     * File name exists.
     *
     * @param schemeFileName the scheme file name
     * @return true, if successful
     */
    boolean fileNameExists(String schemeFileName);

    /**
     * Gets the all list.
     *
     * @return the all list
     */
    public List<SchemeCodes> getAllList();

    /**
     * Gets the gl sub head list.
     *
     * @param schemeType    the scheme type
     * @param schemeCode    the scheme code
     * @param glSubHeadCode the gl sub head code
     * @return the gl sub head list
     */
    public List<SchemeCodes> getGlSubHeadList(String schemeType, String schemeCode, String glSubHeadCode);

    /**
     * Gets the scheme codes.
     *
     * @param id the id
     * @return the scheme codes
     */
    public SchemeCodes getSchemeCodes(Integer id);

    /**
     * Gets the scheme codes.
     *
     * @param schemeType the scheme type
     * @param schemeCode the scheme code
     * @return the scheme codes
     */
    public List<SchemeCodes> getSchemeCodes(String schemeType, String schemeCode);

    /**
     * Gets the scheme codes list.
     *
     * @return the scheme codes list
     */
    List<SchemeCodes> getSchemeCodesList();

    /**
     * Gets the scheme codes list.
     *
     * @param schemeType the scheme type
     * @param schemeCode the scheme code
     * @return the scheme codes list
     */
    public List<SchemeCodes> getSchemeCodesList(String schemeType, String schemeCode);

    /**
     * Gets the scheme type list.
     *
     * @return the scheme type list
     */
    public List<String> getSchemeTypeList();

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
    public List<SchemeCodes> getUnverifiedRecords();

    /**
     * Gets the unverified scheme codes details.
     *
     * @param fileName the file name
     * @return the unverified scheme codes details
     */
    List<SchemeCodes> getUnverifiedSchemeCodesDetails(String fileName);

    /**
     * Gets the verified records.
     *
     * @return the verified records
     */
    public List<SchemeCodes> getVerifiedRecords();

    /**
     * Save or update.
     *
     * @param schemeCodes the scheme codes
     */
    public void saveOrUpdate(SchemeCodes schemeCodes);

    /**
     * Verify.
     *
     * @param schemeCodes the scheme codes
     */
    public void verify(SchemeCodes schemeCodes);

    /**
     * Verify scheme codes.
     *
     * @param fileName the file name
     */
    void verifySchemeCodes(String fileName);

    public ResponseDataDTO saveDetailsSchemeCodes(SchemeCodesDto dto);

    public ResponseDataDTO inquiry(SchemeCodesDto dto);

    public ResponseDataDTO verDelDetails(SchemeCodesDto dto);

    public ResponseDataDTO modifyDetails(SchemeCodesDto dto);

    public ResponseDataDTO modify(SchemeCodesDto dto);

    public ResponseDataDTO verifySubmit(SchemeCodesDto dto);

    public ResponseDataDTO deleteSubmit(SchemeCodesDto dto);


}
