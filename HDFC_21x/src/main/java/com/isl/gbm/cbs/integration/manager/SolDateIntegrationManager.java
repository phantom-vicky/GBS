/********************************************************************************
 * Copyright (c) 2017 Accel Frontline Ltd. All rights reserved.
 *******************************************************************************/
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dao.SolDateDao;
import com.isl.gbm.cbs.integration.dto.BODDateDto;
import com.isl.gbm.cbs.integration.dto.SolDateDto;
import com.isl.gbm.cbs.integration.model.GbmSolDate;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * The Class SolDateIntegrationManager.
 */
@Component
public class SolDateIntegrationManager {

    /**
     * The general setup dao.
     */
    @Autowired
    private GeneralSetupDao generalSetupDao;

    /**
     * The sol date dao.
     */
    @Autowired
    private SolDateDao solDateDao;

    /**
     * Gets the sol date.
     *
     * @return the sol date
     */
    public SolDateDto getSolDate() {
                return this.getSolDateFromMaster();
    }

    /**
     * Gets the sol date from master.
     *
     * @return the sol date from master
     */
    private SolDateDto getSolDateFromMaster() {
        final GbmSolDate solDate = this.solDateDao.getSolDateRecord();
        if (null == solDate) {
            return null;
        }
        final SolDateDto dto = new SolDateDto();
        dto.setSolDate(solDate.getSolDate());
        return dto;
    }

    /**
     * Gets the sol date from webservice.
     *
     * @return the sol date from webservice
     */
    private SolDateDto getSolDateFromWebservice() {
        final ResourceBundle bundle = ResourceBundle.getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final RestTemplate restTemplate = new RestTemplate();
        final SolDateDto solDate = restTemplate
                .getForObject(bundle.getString(IntegrationConstants.KEY_SOL_DATE_SERVICE_URL) + "?clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret(), SolDateDto.class);
        return solDate;
    }


    public BODDateDto getHolidayFromFinacle(BODDateDto bODDateDto) {
        final ResourceBundle bundle = ResourceBundle.getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        final RestTemplate restTemplate = new RestTemplate();
        final BODDateDto solDate = restTemplate
                .getForObject(bundle.getString(IntegrationConstants.GET_HOLIDAY_BODDATE_URL) + "?solID="+bODDateDto.getSolID()+ "&boddate=" + bODDateDto.getSolDate() + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret(), BODDateDto.class);
        return solDate;
    }
    /**
     * Checks if is document submitted recently (In last six months).
     *
     * @param date the date
     * @return true, if is document submitted recently
     */
    public boolean isDocumentSubmittedRecently(final Date date) {
        final SolDateDto solDateDto = this.getSolDate();
        if ((null == solDateDto) || (null == solDateDto.getSolDate())) {
            return false;
        }
        final Date solDate = solDateDto.getSolDate();
        final LocalDate solLocalDate = solDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        final LocalDate inputDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        final Period interval = Period.between(inputDate, solLocalDate);

        if (interval.getMonths() > 6) {
            return false;
        }

        final boolean sixMonthLimit = (interval.getDays() > 0) || (interval.getYears() > 0) ? false : true;
        final boolean lessThanSixMonth = interval.getMonths() < 6 ? true : sixMonthLimit;

        return ((solLocalDate.isAfter(inputDate) || solLocalDate.isEqual(inputDate)) && lessThanSixMonth);
    }


}
