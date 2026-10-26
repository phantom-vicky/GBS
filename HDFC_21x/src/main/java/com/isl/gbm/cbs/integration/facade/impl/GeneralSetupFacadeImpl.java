package com.isl.gbm.cbs.integration.facade.impl;

import com.isl.gbm.cbs.integration.dto.GeneralSetupDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GeneralSetupDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.facade.GeneralSetupFacade;
import com.isl.gbm.cbs.integration.service.GeneralSetupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneralSetupFacadeImpl implements GeneralSetupFacade {

    /**
     * The general setup manager.
     */
    @Autowired
    private GeneralSetupService generalSetupService;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.GeneralSetupService#
     * getGeneralSetupDetails()
     */
    @Override
    @RequiresPermissions("GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:ENQUIRY")
    public List<GeneralSetupDtoAuditLogWrapper> getGeneralSetupDetails() {
        return this.generalSetupService.getGeneralSetupDetails();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.GeneralSetupService#
     * getGeneralSetupDetails(java.lang.String)
     */
    @Override
    @RequiresPermissions("GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:ENQUIRY")
    public GeneralSetupDto getGeneralSetupDetails(final String paramName) {
        return this.generalSetupService.getGeneralSetupDetails(paramName);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.GeneralSetupService#update(com.isl.
     * gbm.cbs.integration.dto.GeneralSetupDto)
     */
    @Override
    @RequiresPermissions("GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:MODIFY")
    public void update(final List<GeneralSetupDtoAuditLogWrapper> wrapperList) {
        this.generalSetupService.update(wrapperList);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.GeneralSetupService#verify(java.util.
     * List)
     */
    @Override
    @RequiresPermissions("GBM_CBS_GENERAL_SETUP:CBS_INTEGRATION_GENERAL_SETUP:VERIFY")
    public void verify(final List<GeneralSetupDtoAuditLogWrapper> wrapperList) {
        this.generalSetupService.verify(wrapperList);
    }
}
