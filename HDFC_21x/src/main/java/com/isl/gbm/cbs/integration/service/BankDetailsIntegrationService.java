/**
 *
 */
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.BankDetailsIntegrationDto;

import java.util.List;

/**
 * @author Vasanthi
 *
 */
public interface BankDetailsIntegrationService {

    public List<BankDetailsIntegrationDto> getBankDetails(String bankCode);

}
