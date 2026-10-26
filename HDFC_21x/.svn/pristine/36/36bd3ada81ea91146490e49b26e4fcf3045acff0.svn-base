package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.BankBranchDao;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.model.BankBranchMaster;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
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

/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************

 import java.util.ArrayList;getGeneralSetupDetails
 import java.util.Collections;
 import java.util.List;
 import java.util.ResourceBundle;

 import org.apache.commons.lang3.StringUtils;
 import org.springframework.core.ParameterizedTypeReference;
 import org.springframework.http.HttpMethod;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.client.RestTemplate;

 import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
 import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
 import com.isl.gbm.cbs.integration.common.IntegrationConstants;
 import com.isl.gbm.cbs.integration.dao.BankBranchDao;
 import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
 import com.isl.gbm.cbs.integration.dto.SolDetailsIntegrationDto;
 import com.isl.gbm.cbs.integration.model.BankBranchMaster;
 import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;

 /**
 * The Class SolDetailsIntegrationManager.
 */
@Component
public class SolDetailsIntegrationManager {

    /**
     * The general setup dao.
     */
    @Autowired
    private GeneralSetupDao generalSetupDao;

    /**
     * The bank branch dao.
     */
    @Autowired
    private BankBranchDao bankBranchDao;

    public List<SolDetailsIntegrationDto> getBankBranchesDetails(final String bankCode, final String branchCode,
                                                                 final String solId) {
        final GeneralSetupDetails setupDetails = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_03.getIntegrationTpId());
        if (null == setupDetails) {
            return Collections.emptyList();
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(setupDetails.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle resourceBundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = resourceBundle.getString(IntegrationConstants.SOL_BANK_BRANCHES_SERVICE_URL) + "?clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                if (StringUtils.isNotBlank(bankCode)) {
                    url = url + "&bankCode=" + bankCode;
                }
                if (StringUtils.isNotBlank(branchCode)) {
                    url = url + "&branchCode=" + branchCode;
                }
                if (StringUtils.isNotBlank(solId)) {
                    url = url + "&solId=" + solId;
                }
                return this.getDetailsFromWebservice(url);
            case MASTER_CREATION:
            case BULK_UPLOADED:
                return this.getSolDetailsFromMaster(solId);
            default:
                return Collections.emptyList();
        }

    }

    /**
     * Gets the details from webservice.
     *
     * @param webServiceUrl the web service url
     * @return the details from webservice
     */
    private List<SolDetailsIntegrationDto> getDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<SolDetailsIntegrationDto>> myBean = new ParameterizedTypeReference<List<SolDetailsIntegrationDto>>() {
        };
        final ResponseEntity<List<SolDetailsIntegrationDto>> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final List<SolDetailsIntegrationDto> solList = response.getBody();
        return solList;
    }

    /**
     * Gets the sol details.
     *
     * @param solId the sol id
     * @return the sol details
     */
    public List<SolDetailsIntegrationDto> getSolDetails(final String solId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_03.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_SOL_DETAILS_SERVICE_URL) + "?clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                if (StringUtils.isNotBlank(solId)) {
                    url = url + "&solId=" + solId;
                }
                List<SolDetailsIntegrationDto> list = this.getDetailsFromWebservice(url);
                for (SolDetailsIntegrationDto dto : list)
                    dto.setSolId(solId);
                return list;
            default:
                return this.getSolDetailsFromMaster(solId);
        }

    }

    /**
     * Gets the sol details from master.
     *
     * @param solId the sol id
     * @return the sol details from master
     */
    private List<SolDetailsIntegrationDto> getSolDetailsFromMaster(final String solId) {
        final List<BankBranchMaster> solMasterDetails = this.bankBranchDao.getSolDetails(solId);
        final List<SolDetailsIntegrationDto> dtoList = new ArrayList<>();
        for (final BankBranchMaster master : solMasterDetails) {
            final SolDetailsIntegrationDto dto = new SolDetailsIntegrationDto();
            dto.setAddress1(master.getAddr1());
            dto.setAddress2(master.getAddr2());
            dto.setBankCode(master.getBankCode());
            dto.setBranchCode(master.getBranchCode());
            dto.setCirclOfficeName(master.getCirclOfficeName());
            dto.setCityCode(master.getCityCode());
            dto.setDivisionName(master.getDivisionName());
            dto.setMicrBankCode(master.getMicrBankCode());
            dto.setMicrBranchCode(master.getMicrBranchCode());
            dto.setMicrCertreCode(master.getMicrCertreCode());
            dto.setPanNumber(master.getPanGirNumber());
            dto.setPinCode(master.getPinCode());
            dto.setSolDescription(master.getSolDesc());
            dto.setSolId(master.getSolId());
            dto.setStateCode(master.getStateCode());
            dto.setTanNumber(master.getTaxDednDetails());
            dto.setTdsCircleName(master.getTdsCircleName());
            dto.setUniformBrCode(master.getUniformBrCode());
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Sets the bank branch dao.
     *
     * @param bankBranchDao the new bank branch dao
     */
    public void setBankBranchDao(final BankBranchDao bankBranchDao) {
        this.bankBranchDao = bankBranchDao;
    }

    /**
     * Sets the general setup dao.
     *
     * @param generalSetupDao the new general setup dao
     */
    public void setGeneralSetupDao(final GeneralSetupDao generalSetupDao) {
        this.generalSetupDao = generalSetupDao;
    }

}
