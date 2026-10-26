package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dto.wrapper.SignatureDTO;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ResourceBundle;
@Component
public class SignatureManager {
    private SignatureDTO tellerAmtAPIDetailsFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<SignatureDTO> myBean = new ParameterizedTypeReference<SignatureDTO>() {
        };
        final ResponseEntity<SignatureDTO> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        final SignatureDTO responseList = response.getBody();
        return responseList;

    }


    public SignatureDTO getSignature(String accountid, String accountType) {
        final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final String url = bundle.getString(IntegrationConstants.KEY_signature_API_DETAILS_SERVICE_URL) +  "?accountid=" + accountid  + "&accountType=" + accountType +
                "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
        return this.tellerAmtAPIDetailsFromWebService(url);
    }
}
