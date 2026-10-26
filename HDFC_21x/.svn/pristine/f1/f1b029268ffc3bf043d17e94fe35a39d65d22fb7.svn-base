/**
 *
 */
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.BankBranchesIntegrationDto;
import com.isl.gbm.cbs.integration.manager.BankBranchesIntegrationManager;
import com.isl.gbm.cbs.integration.service.BankBranchesIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Vasanthi
 *
 */
@Service
public class BankBranchesIntegrationServiceImpl implements BankBranchesIntegrationService {
    @Autowired
    private BankBranchesIntegrationManager manager;

    @Override
    public List<BankBranchesIntegrationDto> getBankBranchesDetails(String bankCode, String branchCode) {
        return manager.getBankBranchesDetails(bankCode, branchCode);
    }

    public void setManager(final BankBranchesIntegrationManager manager) {
        this.manager = manager;
    }

}
