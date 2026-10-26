/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd.
 * All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import com.isl.gbm.cbs.integration.dto.CustomerDetailsIntegrationDto;

import java.util.List;

/**
 * The Interface CustomerDetailsIntegrationService.
 */
public interface CustomerDetailsIntegrationService {

    /**
     * Gets the customer account details.
     *
     * @param customerId the customer id
     * @param accountId  the account id
     * @return the customer account details
     */
    public List<CustomerDetailsIntegrationDto> getCustomerAccountDetails(final String customerId,
                                                                         final String accountId);

    /**
     * Gets the customer details.
     *
     * @param customerId the customer id
     * @param accountId  the account id
     * @return the customer details
     */
    public CustomerDetailsValidatorDTO getCustomerDetails(final String customerId, final String accountId);

    public CustomerDetailsValidatorDTO getCustomerDetail(final String customerId, final String accountId);

    public CustomerDetailsValidatorDTO validateCustomerAccountDetails(final String customerId, final String accountId);

    CustomerDetailsValidatorDTO validateAccountDetails(final String accountId);

    public CustomerDetailsValidatorDTO getPPCustomerDetails(final String customerId, final String accountId);


}
