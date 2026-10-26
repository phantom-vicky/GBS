/**
 *
 */
package com.isl.gbm.cbs.integration.manager;

import com.isl.gbm.cbs.integration.common.GeneralSetupParameterKeys;
import com.isl.gbm.cbs.integration.common.GeneralSetupParameterValues;
import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dao.GeneralSetupDao;
import com.isl.gbm.cbs.integration.dao.InstrumentDetailsDao;
import com.isl.gbm.cbs.integration.dto.InstrumentStatusDto;
import com.isl.gbm.cbs.integration.dto.InstrumentTypesIntegrationDto;
import com.isl.gbm.cbs.integration.model.GeneralSetupDetails;
import com.isl.gbm.cbs.integration.model.InstrumentDetails;
import com.isl.gbm.cbs.integration.dto.DdTransactionApiDto;
import com.isl.gbm.transaction.channel.CbsTransactionResponseDetails;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Msocadmin
 *
 */
@Component
public class InstrumentTypesIntegrationManager {

    @Autowired
    private GeneralSetupDao generalSetupDao;

    @Autowired
    private InstrumentDetailsDao instrumentDetailsDao;

    private static final Logger log = LoggerFactory.getLogger(InstrumentTypesIntegrationManager.class);

    private List<InstrumentTypesIntegrationDto> getDetailsFromWebservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();

        final ParameterizedTypeReference<List<InstrumentTypesIntegrationDto>> myBean = new ParameterizedTypeReference<List<InstrumentTypesIntegrationDto>>() {

        };
        final ResponseEntity<List<InstrumentTypesIntegrationDto>> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final List<InstrumentTypesIntegrationDto> schmCode = response.getBody();

        return schmCode;
    }

    private InstrumentStatusDto getDetailsFromMockservice(final String webServiceUrl) {
        final RestTemplate restTemplate = new RestTemplate();

        final ParameterizedTypeReference<InstrumentStatusDto> myBean = new ParameterizedTypeReference<InstrumentStatusDto>() {
        };
        final ResponseEntity<InstrumentStatusDto> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final InstrumentStatusDto schmCode = response.getBody();

        return schmCode;
    }

    public List<InstrumentTypesIntegrationDto> getInstrumentTypes(final String schmCode) {

        final List<InstrumentDetails> masterDetails = this.instrumentDetailsDao.getInstrumentTypesList(schmCode);
        final List<InstrumentTypesIntegrationDto> dtoList = new ArrayList<>();
        for (final InstrumentDetails master : masterDetails) {
            final InstrumentTypesIntegrationDto dto = new InstrumentTypesIntegrationDto();
            dto.setInstType(master.getInstrumentType());
            dto.setDrCrInd(master.getDrCrIndicator());
            dto.setSchemeCode(master.getSchemeCode());
            dtoList.add(dto);
        }
        return dtoList;

    }


    public DdTransactionApiDto getDdTranFundTransfer(DdTransactionApiDto ddTransactionApiDto) {
        CbsTransactionResponseDetails cbsTransactionResponseDetails = null;
        DdTransactionApiDto ddTransactionApiDto1 = null;
//        final GeneralSetupDetails details = this.generalSetupDao
//                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_09.getIntegrationTpId());
//
//
//        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
//                .findByKey(details.getParameterValue());
//
//        switch (parameterValues) {
//            case BULK_UPLOADED:
//
//            case MASTER_CREATION:
//            case CBS_WEBSERVICE_CALLS:
        final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        String url = bundle.getString(IntegrationConstants.DD_TRANSACTION_SERVICE_URL);
        ddTransactionApiDto1 = this.getDdFundTransfer(url,ddTransactionApiDto);
        return ddTransactionApiDto1;

//            default:
//                return null;
//
//        }
    }


    private DdTransactionApiDto getDdFundTransfer(final String webServiceUrl,DdTransactionApiDto ddTransactionApiDto) {
        final RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DdTransactionApiDto> requestEntity = new HttpEntity<DdTransactionApiDto>(ddTransactionApiDto);
        final ParameterizedTypeReference<DdTransactionApiDto> myBean = new ParameterizedTypeReference<DdTransactionApiDto>() {};
        final ResponseEntity<DdTransactionApiDto> response = restTemplate.exchange(webServiceUrl, HttpMethod.POST, requestEntity, myBean);
        final DdTransactionApiDto schmCode = response.getBody();
        return schmCode;
    }

    private DdTransactionApiDto getDdBranchCodeApi(final String webServiceUrl,DdTransactionApiDto ddTransactionApiDto) {
        final RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DdTransactionApiDto> requestEntity = new HttpEntity<DdTransactionApiDto>(ddTransactionApiDto);
        final ParameterizedTypeReference<DdTransactionApiDto> myBean = new ParameterizedTypeReference<DdTransactionApiDto>() {};
        final ResponseEntity<DdTransactionApiDto> response = restTemplate.exchange(webServiceUrl, HttpMethod.POST, requestEntity, myBean);
        final DdTransactionApiDto schmCode = response.getBody();
        return schmCode;
    }
    public List<InstrumentTypesIntegrationDto> getInstrumentTypesBulkUpload(final String foracid) {
        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_09.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:

            case MASTER_CREATION:
            case BULK_UPLOADED:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_INST_TYPES_BULK_UPLOAD_SERVICE_URL)
                        + "?foracid=" + foracid;
                final List<InstrumentTypesIntegrationDto> schmCode = this.getDetailsFromWebservice(url);
                return schmCode;

            default:
                return Collections.emptyList();

        }
    }

    public DdTransactionApiDto getDdBranchCode(DdTransactionApiDto ddTransactionApiDto) {
        CbsTransactionResponseDetails cbsTransactionResponseDetails = null;
        DdTransactionApiDto ddTransactionApiDto1 = null;
//        final GeneralSetupDetails details = this.generalSetupDao
//                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_09.getIntegrationTpId());
//
//
//        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
//                .findByKey(details.getParameterValue());
//
//        switch (parameterValues) {
//            case BULK_UPLOADED:
//
//            case MASTER_CREATION:
//            case CBS_WEBSERVICE_CALLS:
        final ResourceBundle bundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
        String url = bundle.getString(IntegrationConstants.DD_BRANCH_CODE_SERVICE_URL);
        ddTransactionApiDto1 = this.getDdBranchCodeApi(url,ddTransactionApiDto);
        return ddTransactionApiDto1;

//            default:
//                return null;
//
//        }
    }


    public List<InstrumentTypesIntegrationDto> getSchmCode(final String solId, final String foracid) {

        final GeneralSetupDetails details = this.generalSetupDao
                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_09.getIntegrationTpId());

        if (null == details) {
            return Collections.emptyList();
        }

        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
                .findByKey(details.getParameterValue());

        switch (parameterValues) {
            case CBS_WEBSERVICE_CALLS:

            case MASTER_CREATION:
            case BULK_UPLOADED:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.KEY_INST_TYPES_SERVICE_URL) + "?solId=" + solId
                        + "&foracid=" + foracid + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                final List<InstrumentTypesIntegrationDto> schmCode = this.getDetailsFromWebservice(url);
                return schmCode;

            default:
                return Collections.emptyList();

        }
    }


    public InstrumentStatusDto getInstrumentCheck(final String instrumentNum, final String foracid) {
//
//        final GeneralSetupDetails details = this.generalSetupDao
//                .getGeneralSetupDetails(GeneralSetupParameterKeys.GBM_INT_09.getIntegrationTpId());

        log.info("The request For instrumentcheck " + instrumentNum,foracid);
//        final GeneralSetupParameterValues parameterValues = GeneralSetupParameterValues
//                .findByKey(details.getParameterValue());
//
//        switch (parameterValues) {
//            case BULK_UPLOADED:
//
//            case MASTER_CREATION:
//            case CBS_WEBSERVICE_CALLS:
                final ResourceBundle bundle = ResourceBundle
                        .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
                final String url = bundle.getString(IntegrationConstants.CHECK_INSTRUMENT_NUM_VALIDATION) + "?instrumeNum=" + instrumentNum
                        + "&foracid=" + foracid + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
                final InstrumentStatusDto schmCode = this.getDetailsFromMockservice(url);
                return schmCode;

//            default:
//                return null;
//
//        }
    }


}
