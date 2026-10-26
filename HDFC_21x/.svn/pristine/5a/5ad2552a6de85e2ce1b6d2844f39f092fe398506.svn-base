/**
 *
 */
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.GeneralSetupDto;
import com.isl.gbm.cbs.integration.dto.wrapper.GeneralSetupDtoAuditLogWrapper;
import com.isl.gbm.cbs.integration.manager.GeneralSetupManager;
import com.isl.gbm.cbs.integration.service.GeneralSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The Class GeneralSetupServiceImpl.
 *
 * @author harishbabu.t
 */
@Service
@Transactional
public class GeneralSetupServiceImpl implements GeneralSetupService {

    /** The general setup manager. */
    @Autowired
    private GeneralSetupManager generalSetupManager;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.GeneralSetupService#
     * getGeneralSetupDetails()
     */
    @Override
    public List<GeneralSetupDtoAuditLogWrapper> getGeneralSetupDetails() {
        return this.generalSetupManager.getGeneralSetupDetails();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.GeneralSetupService#
     * getGeneralSetupDetails(java.lang.String)
     */
    @Override
    public GeneralSetupDto getGeneralSetupDetails(final String paramName) {
        return this.generalSetupManager.getGeneralSetupDetails(paramName);
    }

    /**
     * Sets the general setup manager.
     *
     * @param generalSetupManager
     *            the generalSetupManager to set
     */
    public void setGeneralSetupManager(final GeneralSetupManager generalSetupManager) {
        this.generalSetupManager = generalSetupManager;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.GeneralSetupService#update(com.isl.
     * gbm.cbs.integration.dto.GeneralSetupDto)
     */
    @Override
    public void update(final List<GeneralSetupDtoAuditLogWrapper> wrapperList) {
        this.generalSetupManager.update(wrapperList);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.GeneralSetupService#verify(java.util.
     * List)
     */
    @Override
    public void verify(final List<GeneralSetupDtoAuditLogWrapper> wrapperList) {
        this.generalSetupManager.verify(wrapperList);
    }

}
