/*
 *
 */
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dto.GbmOciViewDto;
import com.isl.gbm.cbs.integration.dto.GbmOzhViewDto;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * The Class ZoneDetailsIntegrationManager.
 */

@Component
public class ZoneDetailsIntegrationManager {

    /**
     * The general setup dao.
     */
    @Autowired
    private GeneralSetupDao generalSetupDao;

    public List<GbmOciViewDto> getCbsInstrumentDetails() {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_12.getIntegrationTpId());
        if (null == details) {
            return Collections.emptyList();
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_CBS_INSTRUMENT_DETAILS_SERVICE_URL) + "?clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getCbsInstrumentDetailsFromWebService(url);
            default:
                return Collections.emptyList();
        }
    }

    private List<GbmOciViewDto> getCbsInstrumentDetailsFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<GbmOciViewDto>> myBean = new ParameterizedTypeReference<List<GbmOciViewDto>>() {
        };
        final ResponseEntity<List<GbmOciViewDto>> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        final List<GbmOciViewDto> zoneDetailsList = response.getBody();
        return zoneDetailsList;
    }

    public List<GbmOciViewDto> getClearingSetNumDetails(final String instrumentNumber, final String solId,
                                                        final String zoneCode, final Date zoneDate) {
        String clearingZoneDate = "";
        if (ObjectUtils.notEqual(null, zoneDate)) {
            clearingZoneDate = new SimpleDateFormat("dd/MM/yyyy").format(zoneDate);
        }
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_12.getIntegrationTpId());
        if (null == details) {
            return Collections.emptyList();
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_CLEARING_SET_NUM_DETAILS_SERVICE_URL)
                        + "?instrumentNumber=" + instrumentNumber + "&solId=" + solId + "&zoneCode=" + zoneCode
                        + "&zoneDate=" + clearingZoneDate + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getClearingZoneDetailsFromWebService(url);
            default:
                return Collections.emptyList();
        }
    }

    public List<GbmOciViewDto> getClearingZoneDetails(final GbmOciViewDto ociViewDto) {
        String clearingZoneDate = "";
        if (ObjectUtils.notEqual(null, ociViewDto.getClearingZoneDate())) {
            clearingZoneDate = new SimpleDateFormat("dd/MM/yyyy").format(ociViewDto.getClearingZoneDate());
        }
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_12.getIntegrationTpId());
        if (null == details) {
            return Collections.emptyList();
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_CLEARING_ZONE_DETAILS_SERVICE_URL)
                        + "?clearingZoneCode=" + ociViewDto.getClearingZoneCode() + "&clearingZoneDate="
                        + clearingZoneDate + "&solId=" + ociViewDto.getSolId() + "&instrumentId="
                        + ociViewDto.getInstrumentId().trim() + "&tranCode=" + ociViewDto.getTranCode() + "&sortCode="
                        + ociViewDto.getSortCode() + "&bankCode=" + ociViewDto.getBankCode() + "&branchCode="
                        + ociViewDto.getBranchCode() + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getClearingZoneDetailsFromWebService(url);
            default:
                return Collections.emptyList();
        }
    }

    private List<GbmOciViewDto> getClearingZoneDetailsFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<GbmOciViewDto>> myBean = new ParameterizedTypeReference<List<GbmOciViewDto>>() {
        };
        final ResponseEntity<List<GbmOciViewDto>> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        final List<GbmOciViewDto> zoneDetailsList = response.getBody();
        return zoneDetailsList;
    }

    /**
     * Gets the details from webservice.
     *
     * @param webServiceUrl the web service url
     * @return the details from webservice
     */
    private List<GbmOzhViewDto> getDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<GbmOzhViewDto>> myBean = new ParameterizedTypeReference<List<GbmOzhViewDto>>() {
        };
        final ResponseEntity<List<GbmOzhViewDto>> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);

        final List<GbmOzhViewDto> customerList = response.getBody();
        return customerList;
    }

    /**
     * Gets the zone details.
     *
     * @param solId    the sol id
     * @param zoneCode the zone code
     * @return the zone details
     */
    public List<GbmOzhViewDto> getZoneDetails(final String solId, final String zoneCode) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_14.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_ZONE_DETAILS_SERVICE_URL) + "?solId=" + solId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                if (StringUtils.isNotBlank(zoneCode)) {
                    url = url + "&zoneCode=" + zoneCode;
                }

                return this.getDetailsFromWebservice(url);
            default:
                return Collections.emptyList();
        }
    }

    /**
     * Checks if is valid zone code.
     *
     * @param zoneCode the zone code
     * @param zoneDate the zone date
     * @param solId    the sol id
     * @return the string
     */
    public List<GbmOzhViewDto> isValidZoneCode(final String zoneCode, final Date zoneDate, final String solId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_14.getIntegrationTpId());
        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_VALID_ZONE_CODE_SERVICE_URL) + "?solId="
                        + solId + "&zoneCode=" + zoneCode + "&zoneDate="
                        + new SimpleDateFormat("dd/MM/yyyy").format(zoneDate) + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                final List<GbmOzhViewDto> gbmOzhViewDtoList = this.getDetailsFromWebservice(url);
                return gbmOzhViewDtoList;
            default:
                return Collections.emptyList();
        }
    }

    /**
     * Sets the general setup dao.
     *
     * @param generalSetupDao the new general setup dao
     */
    public void setGeneralSetupDao(final GeneralSetupDao generalSetupDao) {
        this.generalSetupDao = generalSetupDao;
    }

}
