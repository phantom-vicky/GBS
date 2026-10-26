/*
 *
 */
package com.isl.gbm.cbs.integration.service.impl;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.cbs.integration.dto.NpsGbmPlaceholderDto;
import com.isl.gbm.cbs.integration.manager.AccountPlaceHolderIntegrationManager;
import com.isl.gbm.cbs.integration.service.AccountPlaceHolderIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountPlaceHolderIntegrationServiceImpl implements AccountPlaceHolderIntegrationService {
    @Autowired
    private AccountPlaceHolderIntegrationManager accountPlaceHolderIntegrationManager;

    @Override
    public List<NpsGbmPlaceholderDto> getClearingAccountDetails(final String accountNumber) {
        return this.accountPlaceHolderIntegrationManager.getClearingAccountDetails(accountNumber);
    }

    @Override
    public ValidatorWrapperDTO validateBacid(final String bacid) {
        return this.accountPlaceHolderIntegrationManager.validateBacid(bacid);
    }

    public void setAccountPlaceHolderIntegrationManager(
            final AccountPlaceHolderIntegrationManager accountPlaceHolderIntegrationManager) {

        this.accountPlaceHolderIntegrationManager = accountPlaceHolderIntegrationManager;
    }
}
