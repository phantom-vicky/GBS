package com.isl.gbm.common.service;

import com.isl.gbm.common.dto.NpsGbmSubCodeMasterDto;
import com.isl.gbm.common.dto.wrapper.GbmSchemeParameterAuditLogWrapper;

import java.util.List;

public interface GeneralSchemeParameterMaintenanceService {

    /**
     * Gets the scheme details.
     *
     * @param schemeType scheme code
     * @return the scheme details
     */
    List<GbmSchemeParameterAuditLogWrapper> getGeneralSchemeParameterMaintenance(String schemeType);

    /**
     * Gets whether internet enabled or not.
     *
     * @param schemeType scheme code
     * @return String
     */
    Boolean getInternetEnabledForScheme(String schemeType);

    /**
     * Gets the scheme types.
     *
     * @return the gbm sub code details
     */
    List<NpsGbmSubCodeMasterDto> getSchemeTypes();

    /**
     * Updates the general scheme parameter details.
     *
     * @return void
     * @paramdtoWrapper the audit log wrapper
     */
    void updateGeneralSchemeParameterMaintenance(List<GbmSchemeParameterAuditLogWrapper> dtoList);

    /**
     * verifies the general scheme parameter details.
     *
     * @return void
     * @paramdtoWrapper the audit log wrapper
     */
    void verifyGeneralSchemeParameterMaintenance(List<GbmSchemeParameterAuditLogWrapper> wrapper);
}
