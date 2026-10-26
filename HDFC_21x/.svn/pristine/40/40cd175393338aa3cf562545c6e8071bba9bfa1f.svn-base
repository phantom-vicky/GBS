/**
 *
 */
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dao.OtherBankBranchesDao;
import com.isl.gbm.cbs.integration.dto.BankBranchesIntegrationDto;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.cbs.integration.model.OtherBankBranchesMaster;
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
public class BankBranchesIntegrationManager {

    @Autowired
    private GeneralSetupDao generalSetupDao;
    @Autowired
    private OtherBankBranchesDao otherBankBranchesDao;

    public List<BankBranchesIntegrationDto> getBankBranchesDetails(final String bankCode, final String branchCode) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_17.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_BANK_BRANCHES_DETAILS_SERVICE_URL) + "?bankCode="
                        + bankCode + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                if (StringUtils.isNotBlank(branchCode)) {
                    url = url + "&branchCode=" + branchCode;
                }
                return this.getDetailsFromWebservice(url);
            case MASTER_CREATION:
            case BULK_UPLOADED:
                return this.getDetailsFromMaster(bankCode, branchCode);
            default:
                return Collections.emptyList();

        }

    }

    private List<BankBranchesIntegrationDto> getDetailsFromMaster(final String bankCode, final String branchCode) {
        final List<OtherBankBranchesMaster> masterDetails = this.otherBankBranchesDao.getBankBranchesList(bankCode,
                branchCode);
        final List<BankBranchesIntegrationDto> dtoList = new ArrayList<>();
        for (final OtherBankBranchesMaster master : masterDetails) {
            final BankBranchesIntegrationDto dto = new BankBranchesIntegrationDto();
            dto.setBankCode(master.getBankCode());
            dto.setBranchCode(master.getBranchCode());
            dto.setBranchName(master.getBranchName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    private List<BankBranchesIntegrationDto> getDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<BankBranchesIntegrationDto>> myBean = new ParameterizedTypeReference<List<BankBranchesIntegrationDto>>() {
        };
        final ResponseEntity<List<BankBranchesIntegrationDto>> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final List<BankBranchesIntegrationDto> customerList = response.getBody();

        return customerList;
    }

    public void setGeneralSetupDao(final GeneralSetupDao generalSetupDao) {
        this.generalSetupDao = generalSetupDao;
    }

    public void setOtherBankBranchesDao(final OtherBankBranchesDao otherBankBranchesDao) {
        this.otherBankBranchesDao = otherBankBranchesDao;
    }

}
