/**
 *
 */
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dao.OtherBankDetailsDao;
import com.isl.gbm.cbs.integration.dto.BankDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.cbs.integration.model.OtherBankDetailsMaster;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.StringUtils;
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

/**
 * @author Vasanthi
 *
 */
@Component
public class BankDetailsIntegrationManager {

    @Autowired
    private GeneralSetupDao generalSetupDao;
    @Autowired
    private OtherBankDetailsDao otherBankDetailsDao;

    public List<BankDetailsIntegrationDto> getBankDetails(final String bankCode) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_16.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_BANK_DETAILS_SERVICE_URL) + "?clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                if (StringUtils.isNotBlank(bankCode)) {
                    url = url + "&bankCode=" + bankCode;
                }
                return this.getDetailsFromWebservice(url);
            case MASTER_CREATION:
            case BULK_UPLOADED:

                return this.getDetailsFromMaster(bankCode);
            default:
                return Collections.emptyList();

        }

    }

    private List<BankDetailsIntegrationDto> getDetailsFromMaster(final String bankCode) {
        final List<OtherBankDetailsMaster> masterDetails = this.otherBankDetailsDao.getBankDetailsList(bankCode);
        final List<BankDetailsIntegrationDto> dtoList = new ArrayList<>();
        for (final OtherBankDetailsMaster master : masterDetails) {
            final BankDetailsIntegrationDto dto = new BankDetailsIntegrationDto();
            dto.setBankCode(master.getBankCode());
            dto.setBankName(master.getBankName());

            dtoList.add(dto);
        }
        return dtoList;
    }

    private List<BankDetailsIntegrationDto> getDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<BankDetailsIntegrationDto>> myBean = new ParameterizedTypeReference<List<BankDetailsIntegrationDto>>() {
        };
        final ResponseEntity<List<BankDetailsIntegrationDto>> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final List<BankDetailsIntegrationDto> customerList = response.getBody();

        return customerList;
    }

    public void setGeneralSetupDao(final GeneralSetupDao generalSetupDao) {
        this.generalSetupDao = generalSetupDao;
    }

    public void setOtherBankDetailsDao(final OtherBankDetailsDao otherBankDetailsDao) {
        this.otherBankDetailsDao = otherBankDetailsDao;
    }
}
