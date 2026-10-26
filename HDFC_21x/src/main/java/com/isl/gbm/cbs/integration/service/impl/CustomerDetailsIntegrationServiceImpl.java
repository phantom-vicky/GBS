/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import com.isl.gbm.cbs.integration.dto.CustomerDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.manager.CustomerDetailsIntegrationManager;
import com.isl.gbm.cbs.integration.service.CustomerDetailsIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class CustomerDetailsIntegrationServiceImpl.
 */
@Service
public class CustomerDetailsIntegrationServiceImpl implements CustomerDetailsIntegrationService {

    /**
     * The manager.
     */
    @Autowired
    private CustomerDetailsIntegrationManager manager;

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.CustomerDetailsIntegrationService#
     * getCustomerAccountDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<CustomerDetailsIntegrationDto> getCustomerAccountDetails(final String customerId,
                                                                         final String accountId) {
        return this.manager.getCustomerAccountDetails(customerId, accountId);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.isl.gbm.cbs.integration.service.CustomerDetailsIntegrationService#
     * getCustomerDetails(java.lang.String, java.lang.String)
     */
    @Override
    public CustomerDetailsValidatorDTO getCustomerDetails(final String customerId, final String accountId) {
        return this.manager.getCustomerDetails(customerId, accountId);
    }

    public CustomerDetailsValidatorDTO getCustomerDetail(final String customerId, final String accountId) {
        return this.manager.getCustomerDetail(customerId, accountId);
    }

    @Override
    public CustomerDetailsValidatorDTO validateCustomerAccountDetails(final String customerId, final String accountId) {
        return this.manager.validateCustomerAccountDetails(customerId, accountId);
    }

    @Override
    public CustomerDetailsValidatorDTO validateAccountDetails(final String accountId) {
        return manager.validateAccountDetails(accountId);
    }

    @Override
    public CustomerDetailsValidatorDTO getPPCustomerDetails(final String customerId, final String accountId) {
        return this.manager.getPPCustomerDetails(customerId, accountId);
    }


    /**
     * Sets the manager.
     *
     * @param manager the new manager
     */
    public void setManager(final CustomerDetailsIntegrationManager manager) {
        this.manager = manager;
    }

//    @Override
//    public CustomerDetailsValidatorDTO getPPCustomerDetails(final String customerId, final String accountId) {
//        return this.manager.getPPCustomerDetails(customerId, accountId);
//    }

}
