package com.isl.gbm.cbs.integration.facade;

import com.isl.gbm.cbs.integration.dto.GeneralSetupDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GeneralSetupDtoAuditLogWrapper;

import java.util.List;

public interface GeneralSetupFacade {

    List<GeneralSetupDtoAuditLogWrapper> getGeneralSetupDetails();

    /**
     * Gets the general setup details.
     *
     * @param paramName the param name
     * @return the general setup details
     */
    GeneralSetupDto getGeneralSetupDetails(String paramName);

    /**
     * Update.
     *
     * @param wrapperList the wrapper list
     */
    void update(final List<GeneralSetupDtoAuditLogWrapper> wrapperList);

    /**
     * Verify.
     *
     * @param wrapperList the wrapper list
     */
    void verify(final List<GeneralSetupDtoAuditLogWrapper> wrapperList);

}
