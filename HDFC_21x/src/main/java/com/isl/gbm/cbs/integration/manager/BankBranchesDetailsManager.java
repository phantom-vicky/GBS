package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.dto.GbmBctView;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Component
public class BankBranchesDetailsManager {


    public List<GbmBctView> isValidSortCode(final String sortCode) {
        /*final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final String url = bundle.getString(IntegrationConstants.CHECK_BANK_BRANCHES_DETAILS_VALIDATION) + "?sortCode=" + sortCode + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();;

        final List<GbmBctView> brCode = this.getisValidSortCodeDetailsFromMockservice(url);

        return brCode;*/
        return null;
    }


    private List<GbmBctView> getisValidSortCodeDetailsFromMockservice(final String webServiceUrl) {
  /*      final RestTemplate restTemplate = new RestTemplate();

        final ParameterizedTypeReference<List<GbmBctView>> myBean = new ParameterizedTypeReference<List<GbmBctView>>() {
        };
        final ResponseEntity<List<GbmBctView>> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final List<GbmBctView> brCode = response.getBody();
*/
        return null;
    }


}
