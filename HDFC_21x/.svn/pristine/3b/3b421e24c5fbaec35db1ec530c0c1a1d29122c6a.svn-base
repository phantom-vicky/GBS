package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dto.wrapper.BCIFRequestDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.BCIFRequestDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.BCIFResponseDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.SignatureDTO;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ResourceBundle;

@Component
public class BCIFApiManager {

    private BCIFResponseDTO aPIDetailsFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<BCIFResponseDTO> myBean = new ParameterizedTypeReference<BCIFResponseDTO>() {
        };
        final ResponseEntity<BCIFResponseDTO> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        final BCIFResponseDTO responseList = response.getBody();
        return responseList;

    }


    public BCIFResponseDTO getBcifDetails(String id) {
        final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final String url = bundle.getString(IntegrationConstants.KEY_bcif_API_DETAILS_SERVICE_URL) +  "?id=" + id  +"&reqId=" + String.valueOf(System.currentTimeMillis())+
                "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
        return this.aPIDetailsFromWebService(url);
    }
}
