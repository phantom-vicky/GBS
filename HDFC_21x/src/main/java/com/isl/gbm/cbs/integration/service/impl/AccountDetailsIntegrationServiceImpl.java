/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.service.impl;

import com.isl.gbm.cbs.integration.dto.AccountExistsAmountDTO;
import com.isl.gbm.cbs.integration.dto.NpsGbmGamViewDto;
import com.isl.gbm.cbs.integration.manager.AccountDetailsIntegrationManager;
import com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService;
import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class AccountDetailsIntegrationServiceImpl.
 */
@Service
public class AccountDetailsIntegrationServiceImpl implements AccountDetailsIntegrationService {

    /**
     * The manager.
     */
    @Autowired
    private AccountDetailsIntegrationManager manager;

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService#
     * getAccDtls(com.isl.gbm.nps.dto.NpsGbmGamViewDto)
     */
    @Override
    public List<NpsGbmGamViewDto> getAccDtls(final NpsGbmGamViewDto npsGbmGamViewDto) {
        return this.manager.getAccDtls(npsGbmGamViewDto);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService#
     * getAccountBalanceDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<NpsGbmGamViewDto> getAccountBalanceDetails(final String accountNumber, final String solId) {
        return this.manager.getAccountBalanceDetails(accountNumber, solId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService#
     * isAccountExists(java.lang.String)
     */
    @Override
    public List<NpsGbmGamViewDto> getAccountDetails(final String accountNumber) {
        return this.manager.getAccountDetails(accountNumber);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService#
     * getAccountDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<NpsGbmGamViewDto> getAccountDetails(final String accountNumber, final String solId) {
        return this.manager.getAccountDetails(accountNumber, solId);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService#
     * getAcid(java.lang.String)
     */
    @Override
    public String getAcid(final String forAcid) {
        return this.manager.getAcid(forAcid);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService#
     * getActDetails(java.lang.String, java.lang.String)
     */
    @Override
    public List<NpsGbmGamViewDto> getActDetails(final String bacId, final String solId) {
        return this.manager.getActDetails(bacId, solId);
    }

    @Override
    public List<NpsGbmGamViewDto> getFinacleAccountDetails() {
        return this.manager.getFinacleAccountDetails();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService#
     * getFinacleAccountDetails(java.lang.String)
     */
    @Override
    public List<NpsGbmGamViewDto> getFinacleAccountDetails(String accountNumber) {
        return this.manager.getFinacleAccountDetails(accountNumber);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService#
     * getSiEffectiveBalance(java.lang.String)
     */
    @Override
    public Double getSiEffectiveBalance(String accountNumber) {
        return this.manager.getSiEffectiveBalance(accountNumber);
    }

    /**
     * Sets the manager.
     *
     * @param manager the new manager
     */
    public void setManager(final AccountDetailsIntegrationManager manager) {
        this.manager = manager;
    }

    @Override
    public List<NpsGbmGamViewDto> accountNumberExistsSolid(String accountNumber, String solId) {
        return this.manager.accountNumberExistsSolid(accountNumber, solId);
    }

    @Override
    public CustomerDetailsValidatorDTO getEffectiveBalance(String accountNumber) {
        return this.manager.getEffectiveBalance(accountNumber);
    }

    @Override
    public List<AccountExistsAmountDTO> getAccountNumberWithAmount(String accountNumber, String amount) {
        return this.manager.getAccountNumberWithAmount(accountNumber, amount);
    }

    @Override
    public List<AccountExistsAmountDTO> getCustomAcctInqApi(String acctNum) {
        return this.manager.getCustomAcctInqApi(acctNum);
    }

}
