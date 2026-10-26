/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dao.OfficeAccountPlaceHolderDao;
import com.isl.gbm.cbs.integration.dto.NpsGbmPlaceholderDto;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.cbs.integration.model.OfficeAccountPlaceHolderMaster;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


/**
 * The Class AccountPlaceHolderIntegrationManager.
 */
@Component
public class AccountPlaceHolderIntegrationManager {

    /**
     * The general setup dao.
     */
    @Autowired
    private GeneralSetupDao generalSetupDao;

    /**
     * The office account place holder dao.
     */
    @Autowired
    private OfficeAccountPlaceHolderDao officeAccountPlaceHolderDao;

    /**
     * Gets the clearing account details.
     *
     * @param accountNumber the account number
     * @return the clearing account details
     */
    public List<NpsGbmPlaceholderDto> getClearingAccountDetails(final String accountNumber) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_04.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_PLACE_HOLDER_SERVICE_URL)
                        + "?bankAccountId=" + accountNumber + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                return this.getDetailsFromWebservice(url);
            case MASTER_CREATION:
            case BULK_UPLOADED:
                return this.getDetailsFromMaster(accountNumber);
            default:
                return Collections.emptyList();

        }
    }

    public List<NpsGbmPlaceholderDto> getClearingAccountDetailsForList(final List<String> accountNumberList) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_04.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        String accountNumberLists = String.join(",", accountNumberList
                .stream()
                .map(name -> ("'" + name + "'"))
                .collect(Collectors.toList()));

        //String accountNumberLists = String.join(", ", accountNumberList);
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_PLACE_HOLDER_FOR_LIST_SERVICE_URL)
                        + "?bankAccountId=" + accountNumberLists + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                return this.getDetailsFromWebservice(url);
            case MASTER_CREATION:
            case BULK_UPLOADED:
                return this.getDetailsFromMasterForList(accountNumberList);
            default:
                return Collections.emptyList();

        }
    }

    public ValidatorWrapperDTO validateBacid(final String bacid) {


        final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_PLACE_HOLDER_SERVICE__VALIDATE_URL)
                + "?bankAccountId=" + bacid + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

        return this.getValidateDetailsFromWebservice(url);


    }

    /**
     * Gets the details from master.
     *
     * @param accountNumber the account number
     * @return the details from master
     */
    private List<NpsGbmPlaceholderDto> getDetailsFromMaster(final String accountNumber) {
        final List<OfficeAccountPlaceHolderMaster> list = this.officeAccountPlaceHolderDao
                .getOfficeAccountPlaceHolderDetails(accountNumber);
        final List<NpsGbmPlaceholderDto> dtoList = new ArrayList<>();
        for (final OfficeAccountPlaceHolderMaster master : list) {
            final NpsGbmPlaceholderDto dto = new NpsGbmPlaceholderDto();
            dto.setBankAccountId(master.getBacid());
            dto.setBankAccountIdDescription(master.getBacidDesc());
            dtoList.add(dto);
        }
        return dtoList;

    }

    /**
     * Gets the details from master.
     *
     * @return the details from master
     * @paramaccountNumber the account number
     */
    private List<NpsGbmPlaceholderDto> getDetailsFromMasterForList(final List<String> accountNumberList) {
        final List<OfficeAccountPlaceHolderMaster> list = this.officeAccountPlaceHolderDao
                .getOfficeAccountPlaceHolderDetailsForList(accountNumberList);
        final List<NpsGbmPlaceholderDto> dtoList = new ArrayList<>();
        for (final OfficeAccountPlaceHolderMaster master : list) {
            final NpsGbmPlaceholderDto dto = new NpsGbmPlaceholderDto();
            dto.setBankAccountId(master.getBacid());
            dto.setBankAccountIdDescription(master.getBacidDesc());
            dtoList.add(dto);
        }
        return dtoList;

    }

    /**
     * Gets the details from webservice.
     *
     * @param webServiceUrl the web service url
     * @return the details from webservice
     */
    private List<NpsGbmPlaceholderDto> getDetailsFromWebservice(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<NpsGbmPlaceholderDto>> myBean = new ParameterizedTypeReference<List<NpsGbmPlaceholderDto>>() {
        };
        final ResponseEntity<List<NpsGbmPlaceholderDto>> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET,
                null, myBean);

        final List<NpsGbmPlaceholderDto> placeHolderList = response.getBody();

        return placeHolderList;
    }


    private ValidatorWrapperDTO getValidateDetailsFromWebservice(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<ValidatorWrapperDTO> myBean = new ParameterizedTypeReference<ValidatorWrapperDTO>() {
        };
        final ResponseEntity<ValidatorWrapperDTO> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET,
                null, myBean);

        final ValidatorWrapperDTO placeHolderList = response.getBody();

        return placeHolderList;
    }

}
