/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service;

import com.isl.gbm.cbs.integration.dto.AccountExistsAmountDTO;
import com.isl.gbm.cbs.integration.dto.NpsGbmGamViewDto;
import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;

import java.util.List;


/**
 * The Interface AccountDetailsIntegrationService.
 */
public interface AccountDetailsIntegrationService {

    /**
     * Gets the acc dtls.
     *
     * @param npsGbmGamViewDto the nps gbm gam view dto
     * @return the acc dtls
     */
    public List<NpsGbmGamViewDto> getAccDtls(NpsGbmGamViewDto npsGbmGamViewDto);

    /**
     * Gets the account balance details.
     *
     * @param accountNumber the account number
     * @param solId         the sol id
     * @return the account balance details
     */
    public List<NpsGbmGamViewDto> getAccountBalanceDetails(String accountNumber, String solId);

    /**
     * Gets the account details.
     *
     * @param foracid the foracid
     * @return the account details
     */
    public List<NpsGbmGamViewDto> getAccountDetails(String foracid);

    /**
     * Gets the account details.
     *
     * @param accountNumber the account number
     * @param solId         the sol id
     * @return the account details
     */
    public List<NpsGbmGamViewDto> getAccountDetails(final String accountNumber, final String solId);

    /**
     * Gets the acid.
     *
     * @param forAcid the for acid
     * @return the acid
     */
    public String getAcid(final String forAcid);

    /**
     * Gets the act details.
     *
     * @param bacId the bac id
     * @param solId the sol id
     * @return the act details
     */
    public List<NpsGbmGamViewDto> getActDetails(final String bacId, final String solId);

    public List<NpsGbmGamViewDto> getFinacleAccountDetails();

    /**
     * Gets the finacle account details.
     *
     * @param accountNumber the account number
     * @return the finacle account details
     */
    public List<NpsGbmGamViewDto> getFinacleAccountDetails(final String accountNumber);

    /**
     * Gets the si effective balance.
     *
     * @param accountNumber the account number
     * @return the si effective balance
     */
    public Double getSiEffectiveBalance(final String accountNumber);

    public List<NpsGbmGamViewDto> accountNumberExistsSolid(String foracid, String solId);

    public CustomerDetailsValidatorDTO getEffectiveBalance(String accountNumber);

   public List<AccountExistsAmountDTO> getAccountNumberWithAmount(String accountNumber, String amount);

  public   List<AccountExistsAmountDTO> getCustomAcctInqApi(String acctNum);
}
