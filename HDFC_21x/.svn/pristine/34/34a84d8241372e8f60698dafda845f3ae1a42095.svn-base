/**
 *
 */
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dao.SchemeCodeDao;
import com.isl.gbm.cbs.integration.dto.SchemeCodesDto;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.cbs.integration.model.SchemeCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Class SchemeDetailsIntegrationManager.
 *
 * @author Msocadmin
 */
@Component
public class SchemeDetailsIntegrationManager {
    /** The general setup dao. */
    @Autowired
    private GeneralSetupDao generalSetupDao;

    /** The scheme code dao. */
    @Autowired
    private SchemeCodeDao schemeCodeDao;

    /**
     * Gets the details from master gl sub head list.
     *
     * @param schemeType
     *            the scheme type
     * @param schemeCode
     *            the scheme code
     * @param glSubHeadCode
     *            the gl sub head code
     * @return the details from master gl sub head list
     */
    private List<SchemeCodesDto> getDetailsFromMasterGlSubHeadList(final String schemeType, final String schemeCode,
                                                                   final String glSubHeadCode) {
        final List<SchemeCodes> masterDetails = this.schemeCodeDao.getGlSubHeadList(schemeType, schemeCode,
                glSubHeadCode);
        final List<SchemeCodesDto> dtoList = new ArrayList<>();
        for (final SchemeCodes master : masterDetails) {
            final SchemeCodesDto dto = new SchemeCodesDto();
            dto.setSchemeType(master.getSchemeType());
            dto.setSchemeCode(master.getSchemeCode());
            dto.setGlSubHeadCode(master.getGlSubHeadCode());
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Gets the details from master scheme code.
     *
     * @param schemeType
     *            the scheme type
     * @param schemeCode
     *            the scheme code
     * @return the details from master scheme code
     */
    private List<SchemeCodesDto> getDetailsFromMasterSchemeCode(final String schemeType, final String schemeCode) {
        final List<SchemeCodes> masterDetails = this.schemeCodeDao.getSchemeCodesList(schemeType, schemeCode);
        final List<SchemeCodesDto> dtoList = new ArrayList<>();
        for (final SchemeCodes master : masterDetails) {
            final SchemeCodesDto dto = new SchemeCodesDto();
            dto.setSchemeType(master.getSchemeType());
            dto.setSchemeCode(master.getSchemeCode());
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Gets the details from master scheme type.
     *
     * @return the details from master scheme type
     */
    private List<SchemeCodesDto> getDetailsFromMasterSchemeType() {
        final List<String> masterDetails = this.schemeCodeDao.getSchemeTypeList();
        final List<SchemeCodesDto> dtoList = new ArrayList<>();
        for (final String master : masterDetails) {
            final SchemeCodesDto dto = new SchemeCodesDto();
            dto.setSchemeType(master);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Gets the gl sub head list.
     *
     * @param schemeType
     *            the scheme type
     * @param schemeCode
     *            the scheme code
     * @param glSubHeadCode
     *            the gl sub head code
     * @return the gl sub head list
     */
    public List<SchemeCodesDto> getGlSubHeadList(final String schemeType, final String schemeCode,
                                                 final String glSubHeadCode) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_21.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
            case MASTER_CREATION:
            case BULK_UPLOADED:
                return this.getDetailsFromMasterGlSubHeadList(schemeType, schemeCode, glSubHeadCode);
            default:
                return Collections.emptyList();
        }
    }

    /**
     * Gets the scheme code list.
     *
     * @param schemeType
     *            the scheme type
     * @param schemeCode
     *            the scheme code
     * @return the scheme code list
     */
    public List<SchemeCodesDto> getSchemeCodeList(final String schemeType, final String schemeCode) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_21.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
            case MASTER_CREATION:
            case BULK_UPLOADED:
                return this.getDetailsFromMasterSchemeCode(schemeType, schemeCode);
            default:
                return Collections.emptyList();
        }
    }

    /**
     * Gets the zone details.
     *
     * @return the zone details
     */
    public List<SchemeCodesDto> getSchemeTypeList() {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_21.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
            case MASTER_CREATION:
            case BULK_UPLOADED:
                return this.getDetailsFromMasterSchemeType();
            default:
                return Collections.emptyList();
        }
    }

    /**
     * Sets the general setup dao.
     *
     * @param generalSetupDao
     *            the new general setup dao
     */
    public void setGeneralSetupDao(final GeneralSetupDao generalSetupDao) {
        this.generalSetupDao = generalSetupDao;
    }

    /**
     * Sets the scheme code dao.
     *
     * @param schemeCodeDao
     *            the new scheme code dao
     */
    public void setSchemeCodeDao(final SchemeCodeDao schemeCodeDao) {
        this.schemeCodeDao = schemeCodeDao;
    }

}
