/**
 *
 */
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.BankDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.manager.BankDetailsIntegrationManager;
import com.isl.gbm.cbs.integration.service.BankDetailsIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Vasanthi
 *
 */
@Service
@Transactional
public class BankDetailsIntegrationServiceImpl implements BankDetailsIntegrationService {

    @Autowired
    private BankDetailsIntegrationManager manager;

    @Override
    public List<BankDetailsIntegrationDto> getBankDetails(String bankCode) {
        return manager.getBankDetails(bankCode);
    }

    public void setManager(final BankDetailsIntegrationManager manager) {
        this.manager = manager;
    }

}
