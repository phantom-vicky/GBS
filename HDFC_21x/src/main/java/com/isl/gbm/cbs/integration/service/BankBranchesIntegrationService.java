/**
 *
 */
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.BankBranchesIntegrationDto;

import java.util.List;

/**
 * @author Vasanthi
 *
 */
public interface BankBranchesIntegrationService {

    public List<BankBranchesIntegrationDto> getBankBranchesDetails(String bankCode, String branchCode);

}
