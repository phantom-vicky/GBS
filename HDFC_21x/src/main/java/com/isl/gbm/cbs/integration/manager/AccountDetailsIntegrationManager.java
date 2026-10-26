/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dto.AccountExistsAmountDTO;
import com.isl.gbm.cbs.integration.dto.NpsGbmGamViewDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.common.exception.GenericBusinessException;
import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


// TODO: Auto-generated Javadoc

/**
 * The Class AccountDetailsIntegrationManager.
 */
@Component
public class AccountDetailsIntegrationManager {

    /**
     * The Constant SOL_ID.
     */
    private static final String SOL_ID = "&solId=";

    /**
     * The Constant ACCOUNT_NUMBER.
     */
    private static final String ACCOUNT_NUMBER = "?accountNumber=";

    /**
     * The logger.
     */
    Logger LOGGER = LoggerFactory.getLogger(AccountDetailsIntegrationManager.class);

    /**
     * The general setup dao.
     */
    @Autowired
    private GeneralSetupDao generalSetupDao;

    /**
     * Gets the acc dtls.
     *
     * @param npsGbmGamViewDto the nps gbm gam view dto
     * @return the acc dtls
     */
    public List<NpsGbmGamViewDto> getAccDtls(final NpsGbmGamViewDto npsGbmGamViewDto) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_DTLS_SERVICE_URL);
                npsGbmGamViewDto.setClientId(UserAccountUtils.getClientId());
                npsGbmGamViewDto.setClientSecret(UserAccountUtils.getClientSecret());
                return this.getDetailsFromWebservicePost(url, npsGbmGamViewDto);
            default:
                return Collections.emptyList();
        }

    }

    /**
     * Gets the account balance details.
     *
     * @param accountNumber the account number
     * @param solId         the sol id
     * @return the account balance details
     */

    public List<NpsGbmGamViewDto> getAccountBalanceDetails(final String accountNumber, final String solId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_BALANCE_DETAILS_SERVICE_URL)
                        + ACCOUNT_NUMBER + accountNumber + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                url = url + SOL_ID + solId;

                return this.getDetailsFromWebservice(url);
            default:
                return Collections.emptyList();
        }

    }



    public ValidatorWrapperDTO getForActDetail(String bacId, String solId) {
        AccountDetailsIntegrationManager manager= new AccountDetailsIntegrationManager();
        List<NpsGbmGamViewDto> list= manager.getActDetails(bacId,solId);
        ValidatorWrapperDTO validatorWrapperDTO = new ValidatorWrapperDTO();
        TranDetailsDTO tranDetailsDTO = new TranDetailsDTO();
        if(list.get(0).getForAccountId().length() >=8){
            String foracidAcc = list.get(0).getForAccountId();
            validatorWrapperDTO.setStatus("S");
            validatorWrapperDTO.setAccountNumber(foracidAcc);

        }else {
            throw new GenericBusinessException("BACID ACCOUNT VALIDATION FAILED");
        }


        return validatorWrapperDTO;
    }
    /**
     * Gets the account details.
     *
     * @param accountNumber the account number
     * @return the account details
     */
    public List<NpsGbmGamViewDto> getAccountDetails(final String accountNumber) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_EXIST_DETAILS_SERVICE_URL)
                        + ACCOUNT_NUMBER + accountNumber + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getDetailsFromWebservice(url);
            default:
                return Collections.emptyList();
        }

    }

    /**
     * Gets the account details.
     *
     * @param accountNumber the account number
     * @param solId         the sol id
     * @return the account details
     */
    public List<NpsGbmGamViewDto> getAccountDetails(final String accountNumber, final String solId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_DETAILS_SERVICE_URL) + ACCOUNT_NUMBER
                        + accountNumber + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                if (StringUtils.isNotBlank(solId)) {
                    url = url + SOL_ID + solId;
                }
                return this.getDetailsFromWebservice(url);
            default:
                return Collections.emptyList();
        }

    }

    /**
     * Gets the acid.
     *
     * @param forAcid the for acid
     * @return the acid
     */
    public String getAcid(final String forAcid) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_06.getIntegrationTpId());

        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_ACID_SERVICE_URL) + "?forAcid=" + forAcid + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getAcidFromWebService(url);
            default:
                return null;
        }

    }

    /**
     * Gets the acid from web service.
     *
     * @param webServiceUrl the web service url
     * @return the acid from web service
     */
    private String getAcidFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<String> myBean = new ParameterizedTypeReference<String>() {
        };
        final ResponseEntity<String> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null, myBean);
        final String acid = response.getBody();
        return acid;
    }

    /**
     * Gets the act details.
     *
     * @param bacId the bac id
     * @param solId the sol id
     * @return the act details
     */
    public List<NpsGbmGamViewDto> getActDetails(final String bacId, final String solId) {

//        final GeneralSetupDetails details = this.generalSetupDao
//                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());
//
//        if (null == details) {
//            return Collections.emptyList();
//        }
//
//        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
//                .findByKey(details.getParameterValue());
//
//        switch (parameterValues) {
//            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_BACID_DETAILS_SERVICE_URL) + "?bacId="
                        + bacId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                if (StringUtils.isNotBlank(solId)) {
                    url = url + SOL_ID + solId;
                }
                return this.getDetailsFromWebservice(url);
//            default:
//                return Collections.emptyList();
//        }

    }


    public List<NpsGbmGamViewDto> getActDetailsWithoutSolid(final String bacId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_BACID_DETAILS_WITHOUT_SOLID_SERVICE_URL) + "?bacId="
                        + bacId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getDetailsFromWebservice(url);
            default:
                return Collections.emptyList();
        }

    }
    /**
     * Gets the details from webservice.
     *
     * @param webServiceUrl the web service url
     * @return the details from webservice
     */
    private List<NpsGbmGamViewDto> getDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<NpsGbmGamViewDto>> myBean = new ParameterizedTypeReference<List<NpsGbmGamViewDto>>() {
        };
        final ResponseEntity<List<NpsGbmGamViewDto>> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET,
                null, myBean);

        final List<NpsGbmGamViewDto> customerList = response.getBody();
        return customerList;
    }

    private List<AccountExistsAmountDTO> getDetailsFromWebservices(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<AccountExistsAmountDTO>> myBean = new ParameterizedTypeReference<List<AccountExistsAmountDTO>>() {
        };
        final ResponseEntity<List<AccountExistsAmountDTO>> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET,
                null, myBean);


        final List<AccountExistsAmountDTO> customerList = response.getBody();
        if(webServiceUrl.contains("amount=")){
            customerList.forEach(e -> e.setAmount(webServiceUrl.substring(webServiceUrl.indexOf("amount=") + 7,webServiceUrl.indexOf("&clientId="))));
        }

        return customerList;
    }
    /**
     * Gets the details from webservice post.
     *
     * @param webServiceUrl    the web service url
     * @param npsGbmGamViewDto the nps gbm gam view dto
     * @return the details from webservice post
     */
    private List<NpsGbmGamViewDto> getDetailsFromWebservicePost(final String webServiceUrl,
                                                                final NpsGbmGamViewDto npsGbmGamViewDto) {
        List<NpsGbmGamViewDto> customerList = null;
        final RestTemplate restTemplate = new RestTemplate();
        try {
            final RequestEntity<NpsGbmGamViewDto> request = RequestEntity.post(new URI(webServiceUrl))
                    .contentType(MediaType.APPLICATION_JSON).body(npsGbmGamViewDto);
            final ParameterizedTypeReference<List<NpsGbmGamViewDto>> myBean = new ParameterizedTypeReference<List<NpsGbmGamViewDto>>() {
            };
            final ResponseEntity<List<NpsGbmGamViewDto>> response = restTemplate.exchange(request, myBean);
            customerList = response.getBody();
        } catch (final URISyntaxException e) {
            this.LOGGER.error(e.getMessage());
        }

        return customerList;
    }

    /**
     * Gets the eff balance from webservice.
     *
     * @param webServiceUrl the web service url
     * @return the eff balance from webservice
     */
    private Double getEffBalanceFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<Double> myBean = new ParameterizedTypeReference<Double>() {
        };
        final ResponseEntity<Double> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null, myBean);

        final Double customerList = response.getBody();
        return customerList;
    }

    public List<NpsGbmGamViewDto> getFinacleAccountDetails() {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_FINACLE_ACCOUNT_DETAILS_SERVICE_URL);
                return this.getDetailsFromWebservice(url);
            default:
                return Collections.emptyList();
        }

    }

    /**
     * Gets the finacle account details.
     *
     * @param accountNumber the account number
     * @return the finacle account details
     */
    public List<NpsGbmGamViewDto> getFinacleAccountDetails(String accountNumber) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_FINACLE_ACCOUNT_DETAILS_SERVICE_URL)
                        + ACCOUNT_NUMBER + accountNumber + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getDetailsFromWebservice(url);
            default:
                return Collections.emptyList();
        }

    }

    /**
     * Gets the si effective balance.
     *
     * @param accountNumber the account number
     * @return the si effective balance
     */
    public Double getSiEffectiveBalance(String accountNumber) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return 0.0;
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_SI_EFFECTIVE_BALANCE_SERVICE_URL)
                        + ACCOUNT_NUMBER + accountNumber + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getEffBalanceFromWebservice(url);
            default:
                return 0.0;
        }

    }

    public List<NpsGbmGamViewDto> accountNumberExistsSolid(String accountNumber, String solId) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_EXIST_SOLID_DETAILS_SERVICE_URL)
                        + ACCOUNT_NUMBER + accountNumber + SOL_ID + solId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
			/*	if (StringUtils.isNotBlank(solId)) {
					url = url + SOL_ID + solId;
				}*/
                return this.getDetailsFromWebservice(url);
            default:
                return Collections.emptyList();
        }
    }

    public CustomerDetailsValidatorDTO getEffectiveBalance(String accountNumber) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());

        if (null == details) {
            return null;
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_EFFECTIVE_BALANCE_SERVICE_URL)
                        + ACCOUNT_NUMBER + accountNumber + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getEffBalFromWebservice(url);
            default:
                return null;
        }

    }

    private CustomerDetailsValidatorDTO getEffBalFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<CustomerDetailsValidatorDTO> myBean = new ParameterizedTypeReference<CustomerDetailsValidatorDTO>() {
        };
        final ResponseEntity<CustomerDetailsValidatorDTO> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null, myBean);

        final CustomerDetailsValidatorDTO customerList = response.getBody();
        return customerList;
    }


    public List<AccountExistsAmountDTO> getAccountNumberWithAmount(String accountNumber, String amount) {
//        final GeneralSetupDetails details = this.generalSetupDao
//                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_08.getIntegrationTpId());
//
//        if (null == details) {
//            return Collections.emptyList();
//        }
//
//        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
//                .findByKey(details.getParameterValue());
//
//        switch (parameterValues) {
           // case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_EXIST_AMOUNT_DETAILS_SERVICE_URL)
                        + ACCOUNT_NUMBER + accountNumber + "&amount=" + amount + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                return this.getDetailsFromWebservices(url);
//            default:
//                return Collections.emptyList();
//        }
    }
    public List<AccountExistsAmountDTO> getCustomAcctInqApi(String accountNumber) {

                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_ACCOUNT_EXIST_CUSTOMACCTINQ_DETAILS_SERVICE_URL)
                        + ACCOUNT_NUMBER + accountNumber + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                return this.getDetailsFromWebservices(url);
    }
}
