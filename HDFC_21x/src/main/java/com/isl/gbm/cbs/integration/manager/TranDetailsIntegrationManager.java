/**
 *
 */
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dao.TransactionCodesDao;
import com.isl.gbm.cbs.integration.dto.GbmDtdViewDto;
import com.isl.gbm.cbs.integration.dto.GbmOzhViewDto;
import com.isl.gbm.cbs.integration.dto.TranDetailsIntegrationDto;
import com.isl.gbm.cbs.integration.dto.wrapper.TellerDTO;
import com.isl.gbm.cbs.integration.dto.wrapper.TranInqDTO;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.cbs.integration.model.TransactionCodes;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Class TranDetailsIntegrationManager.
 *
 * @author Vasanthi
 */
@Component
public class TranDetailsIntegrationManager {

    /** The general setup dao. */
    @Autowired
    private GeneralSetupDao generalSetupDao;

    /** The transaction codes dao. */
    @Autowired
    private TransactionCodesDao transactionCodesDao;

    public GbmOzhViewDto getClearingTranDetails(final String solId, final String clearingZoneCode,
                                                final Date clearingZoneDate) {
        String zoneDate = null;
        if (ObjectUtils.notEqual(null, clearingZoneDate)) {
            zoneDate = new SimpleDateFormat(CommonConstants.DATE_FORMAT2).format(clearingZoneDate);
        }
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_14.getIntegrationTpId());

        if (null == details) {
            return new GbmOzhViewDto();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String url = bundle.getString(IntegrationConstants.KEY_CLEARED_ZONE_TRAN_ID_DETAILS_SERVICE_URL)
                        + "?solId=" + solId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

                if (StringUtils.isNotBlank(clearingZoneCode)) {
                    url = url + "&clearingZoneCode=" + clearingZoneCode;
                }
                if (StringUtils.isNotBlank(zoneDate)) {
                    url = url + "&clearingZoneDate=" + zoneDate;
                }

                return this.getDetailsFromWebservice(url);
            default:
                return new GbmOzhViewDto();
        }
    }

    /**
     * Gets the daily tran details with matching tran id.
     *
     * @param gbmTranIdList
     *            the gbm tran id list
     * @param tranDateFrom
     *            the tran date from
     * @param tranDateTo
     *            the tran date to
     * @param solId
     *            the sol id
     * @return the daily tran details with matching tran id
     */
    public List<GbmDtdViewDto> getDailyTranDetailsWithMatchingTranId(final List<String> gbmTranIdList,
                                                                     final Date tranDateFrom, final Date tranDateTo, final String solId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_22.getIntegrationTpId());
        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle
                        .getString(IntegrationConstants.KEY_DTD_TRANSACTION_WITH_MATCHING_TRANID_SERVICE_URL)
                        + "?gbmTranIdList=" + gbmTranIdList + "&tranDateFrom="
                        + new SimpleDateFormat(CommonConstants.DATE_FORMAT).format(tranDateFrom) + "&tranDateTo="
                        + new SimpleDateFormat(CommonConstants.DATE_FORMAT).format(tranDateTo) + "&solId=" + solId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getDtdTransactionDetailsWithGamFromWebService(url);
            default:
                return null;
        }

    }

    public List<GbmDtdViewDto> getDailyTranDetailsWithMatchingTranIdPop(final List<String> gbmTranIdList,
                                                                        final Date tranDateFrom, final Date tranDateTo, final List<String> popSpList) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_22.getIntegrationTpId());
        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle
                        .getString(IntegrationConstants.KEY_DTD_TRANSACTION_WITH_MATCHING_TRANID_POP_SERVICE_URL)
                        + "?gbmTranIdList=" + gbmTranIdList + "&tranDateFrom="
                        + new SimpleDateFormat(CommonConstants.DATE_FORMAT).format(tranDateFrom) + "&tranDateTo="
                        + new SimpleDateFormat(CommonConstants.DATE_FORMAT).format(tranDateTo) + "&solIdList="
                        + popSpList + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getDtdTransactionDetailsWithGamFromWebService(url);
            default:
                return null;
        }

    }

    /**
     * Gets the daily transaction details.
     *
     * @param tranRefIdList
     *            the tran ref id list
     * @param solId
     *            the sol id
     * @param tranDate
     *            the tran date
     * @param entryUserId
     *            the entry user id
     * @return the daily transaction details
     */
    public GbmDtdViewDto getDailyTransactionDetails(final List<String> tranRefIdList, final String solId,
                                                    final Date tranDate, final String entryUserId) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_22.getIntegrationTpId());
        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                String tranDetails = null;
                for (String s : tranRefIdList) {
                    tranDetails = "'" + s + "'" + ",";
                }
                final String url = bundle.getString(IntegrationConstants.KEY_DAILY_TRANSACTION_DETAILS_SERVICE_URL)
                        + "?gbmTranRefId=" + tranDetails + "&tranDate="
                        + new SimpleDateFormat(CommonConstants.DATE_FORMAT2).format(tranDate) + "&solId=" + solId
                        + "&entryUserId=" + entryUserId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getTransactionDetailsFromWebService(url);
            default:
                return null;
        }

    }

    public List<GbmDtdViewDto> getDailyUnverifiedTransactionDetails(final String acid, final String entryUserId) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_22.getIntegrationTpId());
        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle
                        .getString(IntegrationConstants.KEY_DAILY_UNVERIFIED_TRANSACTION_DETAILS_SERVICE_URL) + "?acid="
                        + acid + "&entryUserId=" + entryUserId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getDtdTransactionDetailsFromWebService(url);
            default:
                return null;
        }
    }


    public List<GbmDtdViewDto> CheckTransactionDetailsForDAV(final String finacleTranId, final String reversalTranId, final Date tranDate) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_22.getIntegrationTpId());
        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle
                        .getString(IntegrationConstants.KEY_DAILY_UNVERIFIED_TRANSACTION_DETAILS_SERVICE_URL) + "?tranId="
                        + finacleTranId + "&reversalTranId=" + reversalTranId + "&tranDate=" + tranDate;
                return this.getDtdTransactionDetailsFromWebService(url);
            default:
                return null;
        }
    }

    /**
     * Gets the details from master.
     *
     * @param tranCode
     *            the tran code
     * @return the details from master
     */
    private List<TranDetailsIntegrationDto> getDetailsFromMaster(final String tranCode) {
        final List<TransactionCodes> masterDetails = this.transactionCodesDao.getTranDetailsList(tranCode);
        final List<TranDetailsIntegrationDto> dtoList = new ArrayList<>();
        for (final TransactionCodes master : masterDetails) {
            final TranDetailsIntegrationDto dto = new TranDetailsIntegrationDto();
            dto.setClgTranCode(master.getClgTranCode());
            dto.setClgTranCodeDesc(master.getClgTranCodeDesc());
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Gets the details from webservice.
     *
     * @param webServiceUrl
     *            the web service url
     * @return the details from webservice
     */
    private GbmOzhViewDto getDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<GbmOzhViewDto> myBean = new ParameterizedTypeReference<GbmOzhViewDto>() {
        };
        final ResponseEntity<GbmOzhViewDto> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        final GbmOzhViewDto ozhViewDto = response.getBody();
        return ozhViewDto;
    }

    /**
     * Gets the details from master.
     *
     * @param acid
     *            the acid
     * @param entryUserId
     *            the entry user id
     * @return the details from master
     */

    public List<GbmDtdViewDto> getDtdTransactionDetails(final String acid, final String entryUserId) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_22.getIntegrationTpId());
        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_DTD_TRANSACTION_DETAILS_SERVICE_URL)
                        + "?acid=" + acid + "&entryUserId=" + entryUserId + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getDtdTransactionDetailsFromWebService(url);
            default:
                return null;
        }
    }

    /**
     * Gets the dtd transaction details from web service.
     *
     * @param webServiceUrl
     *            the web service url
     * @return the dtd transaction details from web service
     */
    private List<GbmDtdViewDto> getDtdTransactionDetailsFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<GbmDtdViewDto>> myBean = new ParameterizedTypeReference<List<GbmDtdViewDto>>() {
        };
        final ResponseEntity<List<GbmDtdViewDto>> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        return response.getBody();
    }

    /**
     * Gets the details from master.
     *
     * @param solId
     *            the sol id
     * @param solDate
     *            the sol date
     * @return the details from master
     */

    public List<GbmDtdViewDto> getdtdTransactionDetailsPostedWithGAM(final String solId, final String solDate) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_22.getIntegrationTpId());
        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle
                        .getString(IntegrationConstants.KEY_DTD_TRANSACTION_POSTED_WITH_GAM_DETAILS_SERVICE_URL)
                        + "?solId=" + solId + "&solDate=" + solDate + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getDtdTransactionDetailsWithGamFromWebService(url);
            default:
                return null;
        }
    }

    /**
     * Gets the details from master.
     *
     * @param solId
     *            the tran code
     * @param solDate
     *            the tran date
     * @return the details from master
     */

    public List<GbmDtdViewDto> getdtdTransactionDetailsWithGAM(final String solId, final String solDate) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_22.getIntegrationTpId());
        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle
                        .getString(IntegrationConstants.KEY_DTD_TRANSACTION_WITH_GAM_DETAILS_SERVICE_URL) + "?solId="
                        + solId + "&solDate=" + solDate + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.getDtdTransactionDetailsWithGamFromWebService(url);
            default:
                return null;
        }
    }

    /**
     * Gets the details from webservice.
     *
     * @param webServiceUrl
     *            the web service url
     * @return the details from webservice
     */
    private List<GbmDtdViewDto> getDtdTransactionDetailsWithGamFromWebService(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<GbmDtdViewDto>> myBean = new ParameterizedTypeReference<List<GbmDtdViewDto>>() {
        };
        final ResponseEntity<List<GbmDtdViewDto>> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);

        return response.getBody();
    }

    /**
     * Gets the tran details.
     *
     * @param tranCode
     *            the tran code
     * @return the tran details
     */
    public List<TranDetailsIntegrationDto> getTranDetails(final String tranCode) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_15.getIntegrationTpId());
        if (null == details) {
            return Collections.emptyList();
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case MASTER_CREATION:
            case BULK_UPLOADED:
                return this.getDetailsFromMaster(tranCode);
            default:
                return Collections.emptyList();
        }
    }

    private GbmDtdViewDto getTransactionDetailsFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<GbmDtdViewDto> myBean = new ParameterizedTypeReference<GbmDtdViewDto>() {
        };
        final ResponseEntity<GbmDtdViewDto> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        return response.getBody();
    }

    /**
     * Sets the general setup dao.
     *
     * @param generalSetupDao
     *            the new general setup dao
     */
    public void setGeneralSetupDao(final GeneralSetupDao generalSetupDao) {
        this.generalSetupDao = generalSetupDao;
    }

    /**
     * Sets the transaction codes dao.
     *
     * @param transactionCodesDao
     *            the transactionCodesDao to set
     */
    public void setTransactionCodesDao(final TransactionCodesDao transactionCodesDao) {
        this.transactionCodesDao = transactionCodesDao;
    }

    /**
     * Tran id details.
     *
     * @param solId
     *            the sol id
     * @param solDate
     *            the sol date
     * @param tranId
     *            the tran id
     * @param acid
     *            the acid
     * @return the list
     */
    public List<GbmDtdViewDto> tranIdDetails(final String solId, final Date solDate, final String tranId,
                                             final String acid) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_22.getIntegrationTpId());
        if (null == details) {
            return null;
        }
        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());
        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_TRAN_ID_DETAILS_SERVICE_URL) + "?solId="
                        + solId + "&solDate=" + new SimpleDateFormat("dd/MM/yyyy").format(solDate) + "&tranId=" + tranId
                        + "&acid=" + acid + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.tranIdDetailsFromWebService(url);
            default:
                return null;
        }

    }

    /**
     * Tran id details from web service.
     *
     * @param webServiceUrl
     *            the web service url
     * @return the list
     */
    private List<GbmDtdViewDto> tranIdDetailsFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<List<GbmDtdViewDto>> myBean = new ParameterizedTypeReference<List<GbmDtdViewDto>>() {
        };
        final ResponseEntity<List<GbmDtdViewDto>> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        final List<GbmDtdViewDto> responseList = response.getBody();
        return (CollectionUtils.isEmpty(responseList) ? Collections.emptyList() : responseList);

    }

    private TranInqDTO tranIdApiDetailsFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<TranInqDTO> myBean = new ParameterizedTypeReference<TranInqDTO>() {
        };
        final ResponseEntity<TranInqDTO> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        final TranInqDTO responseList = response.getBody();
        return responseList;

    }
    private TellerDTO tellerAmtAPIDetailsFromWebService(final String webServiceUrl) {

        final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<TellerDTO> myBean = new ParameterizedTypeReference<TellerDTO>() {
        };
        final ResponseEntity<TellerDTO> response = restTemplate.exchange(webServiceUrl, HttpMethod.GET, null,
                myBean);
        final TellerDTO responseList = response.getBody();
        return responseList;

    }

    public TranInqDTO trandInqAPI(String tranDate, String tranId) {

                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_TRAN_INQ_API_DETAILS_SERVICE_URL) +  "?tranDate=" + tranDate  + "&tranId=" + tranId +
                        "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.tranIdApiDetailsFromWebService(url);

    }
    public TellerDTO tellerAmtAPI(String tranDate, String tranId) {
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_TELLER_AMT_API_DETAILS_SERVICE_URL) +  "?tranDate=" + tranDate  + "&tranId=" + tranId +
                        "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                return this.tellerAmtAPIDetailsFromWebService(url);

    }

}
