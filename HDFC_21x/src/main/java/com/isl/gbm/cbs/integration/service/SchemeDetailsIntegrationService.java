/**
 *
 */
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;

import java.util.List;

/**
 * The Interface SchemeDetailsIntegrationService.
 *
 * @author Msocadmin
 */
public interface SchemeDetailsIntegrationService {

    /**
     * Gets the scheme type list.
     *
     * @return the scheme type list
     */
    public List<SchemeCodesDto> getSchemeTypeList();

    /**
     * Gets the scheme code list.
     *
     * @param schemeType the scheme type
     * @param schemeCode the scheme code
     * @return the scheme code list
     */
    public List<SchemeCodesDto> getSchemeCodeList(String schemeType, String schemeCode);

    /**
     * Gets the gl sub head list.
     *
     * @param schemeType the scheme type
     * @param schemeCode the scheme code
     * @param glSubHeadCode the gl sub head code
     * @return the gl sub head list
     */
    public List<SchemeCodesDto> getGlSubHeadList(String schemeType, String schemeCode, String glSubHeadCode);

}
