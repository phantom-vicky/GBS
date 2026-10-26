/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.manager.SolDetailsIntegrationManager;
import com.isl.gbm.cbs.integration.service.SolDetailsIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;

/**
 * The Class SolDetailsIntegrationServiceImpl.
 */
@Service
@Transactional
public class SolDetailsIntegrationServiceImpl implements SolDetailsIntegrationService {

    /**
     * The manager.
     */
    @Autowired
    private SolDetailsIntegrationManager manager;

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.SolDetailsIntegrationService#
     * getBankBranchesDetails(java.lang.String, java.lang.String,
     * java.lang.String)
     */
    @Override
    public List<SolDetailsIntegrationDto> getBankBranchesDetails(final String bankCode, final String branchCode,
                                                                 final String solId) {
        return this.manager.getBankBranchesDetails(bankCode, branchCode, solId);
    }

    /*
     * (non-Javadoc)
     * @see com.isl.gbm.cbs.integration.service.SolDetailsIntegrationService#
     * getSolDetails(java.lang.String)
     */
    @Override
    public List<SolDetailsIntegrationDto> getSolDetails(final String solId) {
        return this.manager.getSolDetails(solId);
    }

    /**
     * Sets the manager.
     *
     * @param manager the manager to set
     */
    public void setManager(final SolDetailsIntegrationManager manager) {
        this.manager = manager;
    }

}
