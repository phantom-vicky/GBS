/**
 *
 */
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.manager.SchemeDetailsIntegrationManager;
import com.isl.gbm.cbs.integration.service.SchemeDetailsIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class SchemeDetailsIntegrationServiceImpl.
 *
 * @author Msocadmin
 */
@Service
public class SchemeDetailsIntegrationServiceImpl implements SchemeDetailsIntegrationService {

    /** The scheme details integration manager. */
    @Autowired
    private SchemeDetailsIntegrationManager schemeDetailsIntegrationManager;

    /**
     * Sets the scheme details integration manager.
     *
     * @param schemeDetailsIntegrationManager the new scheme details integration manager
     */
    public void setSchemeDetailsIntegrationManager(SchemeDetailsIntegrationManager schemeDetailsIntegrationManager) {
        this.schemeDetailsIntegrationManager = schemeDetailsIntegrationManager;
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.SchemeDetailsIntegrationService#getSchemeTypeList()
     */
    @Override
    public List<SchemeCodesDto> getSchemeTypeList() {
        return this.schemeDetailsIntegrationManager.getSchemeTypeList();
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.SchemeDetailsIntegrationService#getSchemeCodeList(java.lang.String, java.lang.String)
     */
    @Override
    public List<SchemeCodesDto> getSchemeCodeList(String schemeType, String schemeCode) {
        return this.schemeDetailsIntegrationManager.getSchemeCodeList(schemeType, schemeCode);
    }

    /* (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.SchemeDetailsIntegrationService#getGlSubHeadList(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public List<SchemeCodesDto> getGlSubHeadList(String schemeType, String schemeCode, String glSubHeadCode) {
        return this.schemeDetailsIntegrationManager.getGlSubHeadList(schemeType, schemeCode, glSubHeadCode);
    }

}
