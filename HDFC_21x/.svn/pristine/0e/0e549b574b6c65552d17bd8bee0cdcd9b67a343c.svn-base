package com.isl.gbm.common.util;

import com.isl.gbm.cbs.integration.common.IntegrationConstants;
import com.isl.gbm.cbs.integration.dto.GbmTransactionMasterDto;
import com.isl.gbm.cbs.integration.dto.wrapper.ValidatorWrapperDTO;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

;


@Component
public class TransactionValidator {

  /*  @Value("${cbs.clientId}")
    public String clientId;

    @Value("${cbs.clientSecret}")
    public String clientSecret;*/


    public ValidatorWrapperDTO validateAccountDetails(List<GbmTransactionMasterDto> list) {

/*
        final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);

        String tranDetails = null;
        for (GbmTransactionMasterDto dto : list) {
            tranDetails = dto.getAcctNum() + "~" + dto.getTranLeg();
            if (StringUtils.equalsIgnoreCase(CommonConstants.DEBIT, dto.getTranLeg().toString())) {
                tranDetails = tranDetails + "~" + dto.getAmount();
            }
            tranDetails = tranDetails + "!";
        }

        String url = resourceBundle.getString(IntegrationConstants.TRANS_ACCT_DETAILS_VALIDATION_URL);
        url = url + "?tranDetails=" + tranDetails + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

        ValidatorWrapperDTO wrapperDTO = getDetailsFromWebservice(url);
        return wrapperDTO;*/
        return null;
    }

    public ValidatorWrapperDTO validateBacidFacidDetails(String tranDetails) {

/*
        final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);


        String url = resourceBundle.getString(IntegrationConstants.TRANS_BACID_FACID_DETAILS_VALIDATION_URL);
        url = url + "?tranDetails=" + tranDetails + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

        ValidatorWrapperDTO wrapperDTO = getDetailsFromWebservice(url);
        return wrapperDTO;*/
        return null;
    }

    public ValidatorWrapperDTO validateBacidFacidDetailsNoCliendIdclientSecret(String tranDetails) {


       /* final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);


        String url = resourceBundle.getString(IntegrationConstants.TRANS_BACID_FACID_DETAILS_VALIDATION_URL);
        url = url + "?tranDetails=" + tranDetails + "&clientId=" + this.clientId + "&clientSecret=" + this.clientSecret;

        ValidatorWrapperDTO wrapperDTO = getDetailsFromWebservice(url);
        return wrapperDTO;*/
        return null;
    }



    public ValidatorWrapperDTO validateAccountDetailsList(List<GbmTransactionMasterDto> list) {

       /* final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);

        List<String> tranDetails = new ArrayList<>();
        String tranDet = "";
        for (GbmTransactionMasterDto dto : list) {
            tranDet = dto.getAcctNum() + "~" + dto.getTranLeg();
            if (StringUtils.equalsIgnoreCase(CommonConstants.DEBIT, dto.getTranLeg().toString())) {
                tranDet = tranDet + "~" + Helper.correctDouble(dto.getAmount());
            }
            tranDetails.add(tranDet);
        }
        TransactionValidationRequestDto transactionValidationRequestDto = new TransactionValidationRequestDto();
        transactionValidationRequestDto.setTxnAccDetails(tranDetails);
        transactionValidationRequestDto.setClientId(UserAccountUtils.getClientId());
        transactionValidationRequestDto.setClientSecret(UserAccountUtils.getClientSecret());

        RestTemplate restTemplate = new RestTemplate();
        String url = resourceBundle.getString(IntegrationConstants.TRANS_ACCT_DETAILS_VALIDATION_URL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return restTemplate.postForObject(url, new HttpEntity<>(transactionValidationRequestDto, headers), ValidatorWrapperDTO.class);*/
   return null;
    }

    public ValidatorWrapperDTO validateBacidForAcidDetails(String tranDetails) {
/*

        final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);


        String url = resourceBundle.getString(IntegrationConstants.TRAN_BACID_FACID_DETAILS_VALIDATION_URL);
        url = url + "?tranDetails=" + tranDetails + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

        ValidatorWrapperDTO wrapperDTO = getDetailsFromWebservice(url);
        return wrapperDTO;*/
return null;
    }

    public ValidatorWrapperDTO validateBacidForAcidDetailsNoClientIdClientSecret(String tranDetails) {

/*
        final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);


        String url = resourceBundle.getString(IntegrationConstants.TRAN_BACID_FACID_DETAILS_VALIDATION_URL);
        url = url + "?tranDetails=" + tranDetails + "&clientId=" + this.clientId + "&clientSecret=" + this.clientSecret;

        ValidatorWrapperDTO wrapperDTO = getDetailsFromWebservice(url);
        return wrapperDTO;*/
return null;
    }


    public ValidatorWrapperDTO getForAcidDetails(String tranDetails) {

/*
        final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);


        String url = resourceBundle.getString(IntegrationConstants.FORACID_BACID_DETAILS_VALIDATION_URL);
        url = url + "?tranDetails=" + tranDetails + "&solId=" + UserAccountUtils.getCurrentSolId() + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

        ValidatorWrapperDTO wrapperDTO = getDetailsFromWebservice(url);
        return wrapperDTO;*/
     return null;
    }

    public ValidatorWrapperDTO validateAccountDetailsWithoutBalanceCheck(List<GbmTransactionMasterDto> list) {

/*
        final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);

        String tranDetails = null;
        for (GbmTransactionMasterDto dto : list) {
            tranDetails = dto.getAcctNum() + "~" + dto.getTranLeg();
            if (StringUtils.equalsIgnoreCase(CommonConstants.DEBIT, dto.getTranLeg().toString())) {
                tranDetails = tranDetails + "~" + dto.getAmount();
            }
            tranDetails = tranDetails + "!";
        }

        String url = resourceBundle.getString(IntegrationConstants.TRANS_ACCT_DETAILS_VALIDATION_URL_WITHOUT_BALANCE_CHECK);
        url = url + "?tranDetails=" + tranDetails + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();

        ValidatorWrapperDTO wrapperDTO = getDetailsFromWebserviceWithoutBalanceCheck(url);
        return wrapperDTO;*/
return null;
    }

    private ValidatorWrapperDTO getDetailsFromWebserviceWithoutBalanceCheck(final String webServiceUrl) {
        /*final RestTemplate restTemplate = new RestTemplate();
        final ParameterizedTypeReference<ValidatorWrapperDTO> myBean = new ParameterizedTypeReference<ValidatorWrapperDTO>() {
        };
        final ResponseEntity<ValidatorWrapperDTO> response = restTemplate.exchange(webServiceUrl,
                HttpMethod.GET, null, myBean);

        final ValidatorWrapperDTO wrapperDTO = response.getBody();
        return wrapperDTO;*/
        return null;
    }

    public ValidatorWrapperDTO validateAccountDetailslist(List<com.isl.gbm.transaction.channel.GbmTransactionMasterDto> accDetailsList) {
        /*final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);

        List<String> tranDetails = new ArrayList<>();
        String tranDet = "";
        for (com.isl.gbm.transaction.channel.GbmTransactionMasterDto dto : accDetailsList) {
            tranDet = dto.getAcctNum() + "~" + dto.getTranLeg();
            if (StringUtils.equalsIgnoreCase(CommonConstants.DEBIT, dto.getTranLeg().toString())) {
                tranDet = tranDet + "~" + Helper.correctDouble(dto.getAmount());
            }
            tranDetails.add(tranDet);
        }
        TransactionValidationRequestDto transactionValidationRequestDto = new TransactionValidationRequestDto();
        transactionValidationRequestDto.setTxnAccDetails(tranDetails);
        transactionValidationRequestDto.setClientId(UserAccountUtils.getClientId());
        transactionValidationRequestDto.setClientSecret(UserAccountUtils.getClientSecret());

        RestTemplate restTemplate = new RestTemplate();
        String url = resourceBundle.getString(IntegrationConstants.TRANS_ACCT_DETAILS_VALIDATION_URL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return restTemplate.postForObject(url, new HttpEntity<>(transactionValidationRequestDto, headers), ValidatorWrapperDTO.class);*/
   return null;
    }

    public ValidatorWrapperDTO valAccountDetailsWithoutBalanceCheck(List<OperativeAccountStatusDTO> list) {


//        final ResourceBundle resourceBundle = ResourceBundle
//                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);
//
//        String tranDetails = "";
//        for (OperativeAccountStatusDTO dto : list) {
//            tranDetails += dto.getOperativeAccountNum() + "~" + CommonConstants.TRAN_LEG_CREDIT;
//            tranDetails = tranDetails + "!";
//        }
//
//        String url = resourceBundle.getString(IntegrationConstants.TRANS_ACCT_DETAILS_VALIDATION_INQ_URL);
//        url = url + "?tranDetails=" + tranDetails + "&clientId=" + UserAccountUtils.getClientId() + "&clientSecret=" + UserAccountUtils.getClientSecret();
//
//        ValidatorWrapperDTO wrapperDTO = getDetailsFromWebserviceWithoutBalanceCheck(url);
//        return wrapperDTO;


        /*final ResourceBundle resourceBundle = ResourceBundle
                .getBundle(IntegrationConstants.CBS_WEB_SERVICE_CONFIG_FILE_NAME);

        List<String> tranDetails = new ArrayList<>();
        String tranDet = "";
        for (OperativeAccountStatusDTO dto : list) {
            tranDet = dto.getOperativeAccountNum() + "~" + CommonConstants.TRAN_LEG_CREDIT;

            tranDetails.add(tranDet);
        }
        TransactionValidationRequestDto transactionValidationRequestDto = new TransactionValidationRequestDto();
        transactionValidationRequestDto.setTxnAccDetails(tranDetails);
        transactionValidationRequestDto.setClientId(UserAccountUtils.getClientId());
        transactionValidationRequestDto.setClientSecret(UserAccountUtils.getClientSecret());

        RestTemplate restTemplate = new RestTemplate();
        String url = resourceBundle.getString(IntegrationConstants.TRANS_ACCT_DETAILS_VALIDATION_INQ_URL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return restTemplate.postForObject(url, new HttpEntity<>(transactionValidationRequestDto, headers), ValidatorWrapperDTO.class);*/
   return null;
    }
}
