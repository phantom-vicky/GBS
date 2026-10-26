/*
 *
 */
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dto.FinacleStatusValidationDTO;
import com.isl.gbm.cbs.integration.dto.McpStatusViewDto;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

@Component
public class McpStatusIntegrationManager {
    @Autowired
    private GeneralSetupDao generalSetupDao;

    Logger LOGGER = LoggerFactory.getLogger(McpStatusIntegrationManager.class);


    public FinacleStatusValidationDTO uploadMcpStatus(final McpStatusViewDto viewDto) {
        FinacleStatusValidationDTO responseDto = null;
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_26.getIntegrationTpId());

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.MCP_STATUS_UPDATE_SERVICE_URL);
                viewDto.setClientId(UserAccountUtils.getClientId());
                viewDto.setClientSecret(UserAccountUtils.getClientSecret());
                responseDto = this.uploadDetailsToWebservicePost(url, viewDto);
            default:

        }
        return responseDto;
    }

    public FinacleStatusValidationDTO uploadBulkMcpStatus(final McpStatusViewDto viewDto) {
        FinacleStatusValidationDTO responseDto = null;

                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.MCP_STATUS_BULK_UPDATE_SERVICE_URL);
                viewDto.setClientId(UserAccountUtils.getClientId());
                viewDto.setClientSecret(UserAccountUtils.getClientSecret());
                responseDto = this.uploadDetailsToWebservicePost(url, viewDto);


        return responseDto;
    }


    public FinacleStatusValidationDTO updateMcpStatus(final McpStatusViewDto viewDto) {
        FinacleStatusValidationDTO responseDto = null;
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_26.getIntegrationTpId());

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.MCP_STATUS_EOD_UPDATE_SERVICE_URL);
                viewDto.setClientId(UserAccountUtils.getClientId());
                viewDto.setClientSecret(UserAccountUtils.getClientSecret());
                responseDto = this.uploadDetailsToWebservicePost(url, viewDto);
            default:

        }

        return responseDto;
    }

    public FinacleStatusValidationDTO updateBulkMcpStatus(final McpStatusViewDto viewDto) {

        return null;
    }

    private FinacleStatusValidationDTO uploadDetailsToWebservicePost(final String webServiceUrl, final McpStatusViewDto npsGbmGamViewDto) {
        /*
        Note in use, so commented for APP-sec from KPMG
        final RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FinacleStatusValidationDTO> response = null;
        try {
            final RequestEntity<McpStatusViewDto> request = RequestEntity.post(new URI(webServiceUrl))
                    .contentType(MediaType.APPLICATION_JSON).body(npsGbmGamViewDto);
            final ParameterizedTypeReference<FinacleStatusValidationDTO> myBean = new ParameterizedTypeReference<FinacleStatusValidationDTO>() {
            };
            response = restTemplate.exchange(request, myBean);

        } catch (final URISyntaxException e) {
            this.LOGGER.error(e.getMessage());
        }

         */
        return null;
    }

    public FinacleStatusValidationDTO centralizedBod(McpStatusViewDto viewDto) {

        final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final String url = bundle.getString(IntegrationConstants.MCP_STATUS_CENTRALIZED_SERVICE_URL);
        viewDto.setClientId(UserAccountUtils.getClientId());
        viewDto.setClientSecret(UserAccountUtils.getClientSecret());
        FinacleStatusValidationDTO finacleStatusValidationDTO = this.uploadDetailsToWebservicePost(url, viewDto);
        return finacleStatusValidationDTO;

    }

    public FinacleStatusValidationDTO fetchCbsEodStatus(final McpStatusViewDto viewDto) {
        FinacleStatusValidationDTO responseDto = null;
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_26.getIntegrationTpId());

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.MCP_CBS_EOD_STATUS_SERVICE_URL);
                viewDto.setClientId(UserAccountUtils.getClientId());
                viewDto.setClientSecret(UserAccountUtils.getClientSecret());
                responseDto = this.uploadDetailsToWebservicePost(url, viewDto);
            default:

        }
        return responseDto;
    }

    public FinacleStatusValidationDTO pensionEodUpdate(final McpStatusViewDto viewDto) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_26.getIntegrationTpId());

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.PENSION_EOD_STATUS_UPDATE_URL);
                viewDto.setClientId(UserAccountUtils.getClientId());
                viewDto.setClientSecret(UserAccountUtils.getClientSecret());
                FinacleStatusValidationDTO finacleStatusValidationDTO = this.uploadDetailsToWebservice(url, viewDto);

                return finacleStatusValidationDTO;
            default:
                return null;

        }

    }

    private FinacleStatusValidationDTO uploadDetailsToWebservice(final String webServiceUrl, final McpStatusViewDto npsGbmGamViewDto) {
        final RestTemplate restTemplate = new RestTemplate();
        try {
            final RequestEntity<McpStatusViewDto> request = RequestEntity.post(new URI(webServiceUrl))
                    .contentType(MediaType.APPLICATION_JSON).body(npsGbmGamViewDto);
            final ParameterizedTypeReference<FinacleStatusValidationDTO> myBean = new ParameterizedTypeReference<FinacleStatusValidationDTO>() {
            };
            final ResponseEntity<FinacleStatusValidationDTO> response = restTemplate.exchange(request, myBean);
            FinacleStatusValidationDTO validationDTO = response.getBody();
            return validationDTO;
        } catch (final URISyntaxException e) {
            this.LOGGER.error(e.getMessage());
        }
        return null;

    }

}
