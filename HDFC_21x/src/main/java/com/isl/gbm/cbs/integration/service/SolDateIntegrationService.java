/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.SolDateDto;

import java.util.Date;

/**
 * The Interface SolDateIntegrationService.
 */
public interface SolDateIntegrationService {

    /**
     * Gets the sol date.
     *
     * @return the sol date
     */
    public SolDateDto getSolDate();

    /**
     * Checks if is document submitted recently (In last six months).
     *
     * @param date the date
     * @return true, if is document submitted recently
     */
    public boolean isDocumentSubmittedRecently(final Date date);

}
