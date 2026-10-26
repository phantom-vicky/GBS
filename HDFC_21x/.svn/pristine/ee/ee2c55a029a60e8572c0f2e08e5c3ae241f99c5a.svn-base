/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dto.CustomerDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The Class CustomerDetailsIntegrationManager.
 */
@Component
public class CustomerDetailsIntegrationManager {

    /**
     * The general setup dao.
     */
    @Autowired
    private GeneralSetupDao generalSetupDao;

    private static final Logger log = LoggerFactory.getLogger(CustomerDetailsIntegrationManager.class);


    /**
     * Gets the customer account details.
     *
     * @param customerId the customer id
     * @param accountId  the account id
     * @return the customer account details
     */
    public List<CustomerDetailsIntegrationDto> getCustomerAccountDetails(final String customerId,
                                                                         final String accountId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_29.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_CUSTOMER_ACC_DETAILS_SERVICE_URL)
                        + "?customerId=" + customerId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                if (StringUtils.isNotBlank(accountId)) {
                    url = url + "&accountNumber=" + accountId;
                }
                return this.getDetailsFromWebservice(url);
            default:
                return Collections.emptyList();
        }

    }

    public String getCustomerAccountName(final String accountId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_29.getIntegrationTpId());

        if (null == details) {
            return "";
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_CUSTOMER_ACC_NAME_SERVICE_URL)
                        + "?accountNumber=" + accountId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                final RestTemplate restTemplate = new RestTemplate();
                final ParameterizedTypeReference<String> myBean = new ParameterizedTypeReference<String>() {
                };
                return restTemplate.exchange(url, HttpMethod.GET, null, myBean).getBody();
            default:
                return null;
        }

    }

    /**
     * Gets the customer details.
     *
     * @param customerId the customer id
     * @param accountId  the account id
     * @return the customer details
     */
    public CustomerDetailsValidatorDTO getCustomerDetails(final String customerId, final String accountId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_29.getIntegrationTpId());

        if (null == details) {
            return new CustomerDetailsValidatorDTO();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_CUSTOMER_DETAILS_SERVICE_URL)
                        + "?customerId=" + customerId + "&accountNumber=" + accountId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                return this.getCustomerDetailsFromWebservice(url);
            default:
                return new CustomerDetailsValidatorDTO();
        }

    }
    public CustomerDetailsValidatorDTO getCustomerDetail(final String customerId, final String accountId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_29.getIntegrationTpId());

        if (null == details) {
            return new CustomerDetailsValidatorDTO();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_CUSTOMER_DETAILS_SERVICE_URL1)
                        + "?customerId=" + customerId + "&accountNumber=" + accountId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                return this.getCustomerDetailsFromWebservice(url);
            default:
                return new CustomerDetailsValidatorDTO();
        }

    }

    private CustomerDetailsValidatorDTO getCustomerDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<CustomerDetailsValidatorDTO> myBean = new ParameterizedTypeReference<CustomerDetailsValidatorDTO>() {
        };
        final ResponseEntity<CustomerDetailsValidatorDTO> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final CustomerDetailsValidatorDTO customerDetails = response.getBody();
        return customerDetails;
    }

    /**
     * Gets the details from webservice.
     *
     * @param webServiceUrl the web service url
     * @return the details from webservice
     */
    private List<CustomerDetailsIntegrationDto> getDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<CustomerDetailsIntegrationDto>> myBean = new ParameterizedTypeReference<List<CustomerDetailsIntegrationDto>>() {
        };
        final ResponseEntity<List<CustomerDetailsIntegrationDto>> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final List<CustomerDetailsIntegrationDto> customerList = response.getBody();
        return customerList;
    }

    public CustomerDetailsValidatorDTO validateCustomerAccountDetails(final String customerId, final String accountId) {

        final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final String url = bundle.getString(IntegrationConstants.KEY_CUSTOMER_DETAILS_VALIDATE_SERVICE_URL)
                + "?customerId=" + customerId + "&accountNumber=" + accountId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

        return this.getCustomerDetailsFromWebservice(url);

    }

    public CustomerDetailsValidatorDTO validateAccountDetails(final String accountId) {

        final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_DETAILS_VALIDATE_SERVICE_URL)
                + "?accountNumber=" + accountId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

        return this.getCustomerDetailsFromWebservice(url);

    }

    /**
     * Sets the general setup dao.
     *
     * @param generalSetupDao the new general setup dao
     */
    public void setGeneralSetupDao(final GeneralSetupDao generalSetupDao) {
        this.generalSetupDao = generalSetupDao;
    }


    public CustomerDetailsValidatorDTO getPPCustomerDetails(final String customerId, final String accountId) {

        final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final String url = bundle.getString(IntegrationConstants.KEY_PP_CUSTOMER_DETAILS_VALIDATE_SERVICE_URL)
                + "?customerId=" + customerId + "&accountNumber=" + accountId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

        return this.getPPCustomerDetailsFromWebservice(url);
    }


    private CustomerDetailsValidatorDTO getPPCustomerDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<CustomerDetailsValidatorDTO> myBean = new ParameterizedTypeReference<CustomerDetailsValidatorDTO>() {
        };
        log.info("URL OF CBS JAR" + webServiceUrl);
        final ResponseEntity<CustomerDetailsValidatorDTO> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final CustomerDetailsValidatorDTO customerDetails = response.getBody();
        return customerDetails;
    }
}
