/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.manager.SolDateIntegrationManager;
import com.isl.gbm.cbs.integration.service.SolDateIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * The Class SolDateIntegrationServiceImpl.
 */
@Service
@Transactional
public class SolDateIntegrationServiceImpl implements SolDateIntegrationService {

    /**
     * The manager.
     */
    @Autowired
    private SolDateIntegrationManager manager;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.SolDateIntegrationService#getSolDate()
     */
    @Override
    public SolDateDto getSolDate() {
        return this.manager.getSolDate();
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.SolDateIntegrationService#
     * isDocumentSubmittedRecently(java.util.Date)
     */
    @Override
    public boolean isDocumentSubmittedRecently(final Date date) {
        return this.manager.isDocumentSubmittedRecently(date);
    }

    /**
     * Sets the manager.
     *
     * @param manager the new manager
     */
    public void setManager(final SolDateIntegrationManager manager) {
        this.manager = manager;
    }

}
