package com.isl.gbm.rbibond.externalservice.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.externalservice.dto.FtsRequestDTO;
import com.isl.gbm.rbibond.externalservice.dto.FtsResponseDataDTO;
import com.isl.gbm.rbibond.externalservice.service.FtsService;
import com.isl.gbm.rbibond.externalservice.utility.IntegrationCommonConstants;
import com.isl.gbm.rbibond.externalservice.utility.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FtsServiceImpl implements FtsService {

    private static final Logger log = LoggerFactory.getLogger(FtsServiceImpl.class);

    @Value("${fts_service_url}")
    private String serviceURL;

    @Value("${transaction.branch}")
    private String transactionBranch;

    @Value("${fts.channel}")
    private String channel;

    @Value("${fts.token.value}")
    private String ftsTokenValue;

    private final RestTemplate restTemplate;
    private final Gson gson;
    private final ObjectMapper objectMapper;
    @Autowired
    public FtsServiceImpl(RestTemplate restTemplate,Gson gson,ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.gson=gson;
        this.objectMapper=objectMapper;
    }

    @Override
    public ResponseDataDTO getFileDetails(String barCode) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        ResponseEntity<String> responseEntity = null;
        try {
            FtsRequestDTO ftsRequestDTO = constructFtsRequest(barCode);
            String request = gson.toJson(ftsRequestDTO);
            log.info("Request sent : {}", request);
            HttpEntity<String> requestEntity = new HttpEntity<>(request, createHeaders());
            responseEntity = restTemplate.postForEntity(serviceURL, requestEntity, String.class);
            log.info("Response : {}", responseEntity.getBody());
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                log.info("Received successful HTTP status code: {}", responseEntity.getStatusCode());
                log.info("Response : {}", responseEntity.getBody());
                FtsResponseDataDTO ftsResponseDataDTO = objectMapper.readValue(responseEntity.getBody(), FtsResponseDataDTO.class);
                responseDataDTO.setResult(ftsResponseDataDTO);
                responseDataDTO.setMessage(IntegrationCommonConstants.FTS_RES_SUCCESS);
                responseDataDTO.setStatusCode(String.valueOf(responseEntity.getStatusCode().value()));
            } else {
                log.error("Received non-successful HTTP status code: {}", responseEntity.getStatusCode());
                responseDataDTO.setMessage(IntegrationCommonConstants.FTS_RES_FAILED);
                responseDataDTO.setStatusCode(String.valueOf(responseEntity.getStatusCode().value()));
            }
        } catch (HttpClientErrorException ex) {
            log.error("FTS Client error: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
            responseDataDTO.setMessage(IntegrationCommonConstants.FTS_RES_FAILED);
            responseDataDTO.setStatusCode(String.valueOf(Objects.requireNonNull(responseEntity).getStatusCode().value()));
        } catch (HttpServerErrorException ex) {
            log.error("FTS Server error: {} - {}", ex.getStatusCode(), ex.getResponseBodyAsString());
            responseDataDTO.setMessage(IntegrationCommonConstants.FTS_RES_FAILED);
            responseDataDTO.setStatusCode(String.valueOf(Objects.requireNonNull(responseEntity).getStatusCode().value()));
        } catch (Exception ex) {
            log.error("Unexpected error occurred: {}", ex.getMessage(), ex);
            responseDataDTO.setMessage(IntegrationCommonConstants.FTS_RES_FAILED);
            responseDataDTO.setStatusCode(String.valueOf(Objects.requireNonNull(responseEntity).getStatusCode().value()));
        }
        return responseDataDTO;
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private FtsRequestDTO constructFtsRequest(String barCode) {

        // Validate the input parameter
        validateBarCode(barCode);

        // Create FtsRequestDTO and its components
        FtsRequestDTO ftsRequestDTO = new FtsRequestDTO();
        FtsRequestDTO.SessionContextDTO sessionContext = new FtsRequestDTO.SessionContextDTO();
        FtsRequestDTO.FTSCustomerInstructionImageDetailsServiceRequestDTO serviceRequest = new FtsRequestDTO.FTSCustomerInstructionImageDetailsServiceRequestDTO();
        FtsRequestDTO.RequestStringDTO requestString = new FtsRequestDTO.RequestStringDTO();
        FtsRequestDTO.ObjRequestDTO objRequest = new FtsRequestDTO.ObjRequestDTO();

        // Set barcode and other fields in ObjRequestDTO
        setObjRequestFields(objRequest, barCode);

        // Set RequestStringDTO
        requestString.setObjRequest(objRequest);
        serviceRequest.setRequestString(requestString);

        // Set Header Params
        serviceRequest.setHeaderParams(createHeaderParam());

        // Set Session Context
        setSessionContext(sessionContext);

        // Associate the components
        ftsRequestDTO.setSessionContext(sessionContext);
        ftsRequestDTO.setFtsRequestDTO(serviceRequest);

        return ftsRequestDTO;
    }

    private void validateBarCode(String barCode) {
        if (barCode == null || barCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Bar code cannot be null or empty.");
        }

        // Example length check (adjust based on requirements)
        if (barCode.length() < 5 || barCode.length() > 20) {
            throw new IllegalArgumentException("Bar code must be between 5 and 20 characters.");
        }

        // Example format check (optional, adjust regex as needed)
        if (!barCode.matches("^[a-zA-Z0-9]*$")) { // Alphanumeric only
            throw new IllegalArgumentException("Bar code must be alphanumeric.");
        }
    }

    private void setObjRequestFields(FtsRequestDTO.ObjRequestDTO objRequest, String barCode) {
        objRequest.setAccount_Number(""); // Set appropriate values as needed
        if (barCode != null && barCode.matches("\\d+")) {
            objRequest.setCust_ID(barCode);
            objRequest.setBarcode("");
        } else {
            objRequest.setBarcode(barCode);
            objRequest.setCust_ID(IntegrationCommonConstants.FTS_CUST_ID);
        }
        objRequest.setForm_Status(IntegrationCommonConstants.FTS_FORM_STATUS);
        objRequest.setStatus(IntegrationCommonConstants.FTS_STATUS);
        objRequest.setIsData(IntegrationCommonConstants.FTS_IS_DATA);
        objRequest.setSystem_Type(IntegrationCommonConstants.FTS_SYSTEM_TYPE);
    }

    private void setSessionContext(FtsRequestDTO.SessionContextDTO sessionContext) {
        sessionContext.setBankCode(IntegrationCommonConstants.BANK_CODE);
        sessionContext.setChannel(channel);
        sessionContext.setExternalReferenceNo(RequestUtils.generateTransactionReference());
        sessionContext.setUserId(IntegrationCommonConstants.FTS_USER_ID);
        sessionContext.setTransactionBranch(transactionBranch);
    }


    private List<FtsRequestDTO.HeaderParamDTO> createHeaderParam() {
        List<FtsRequestDTO.HeaderParamDTO> headerParamList = new ArrayList<>();
        FtsRequestDTO.HeaderParamDTO headerParam = new FtsRequestDTO.HeaderParamDTO();
        headerParam.setKey(IntegrationCommonConstants.FTS_TOKEN_KEY);
//        headerParam.setValue(IntegrationCommonConstants.FTS_TOKEN_VALUE);
        headerParam.setValue(ftsTokenValue);
        headerParamList.add(headerParam);
        return headerParamList;
    }

    @Override
    public Map<String, String> fetchIndexingNamesByImageName(FtsResponseDataDTO responseData) {
        Map<String, String> imageToIndexingMap = new HashMap<>();

        // Ensure response data is not null and contains lstData
        if (responseData != null && responseData.getResponseString() != null && responseData.getResponseString().getListData() != null) {
            // Iterate through each LstData
            responseData.getResponseString().getListData().forEach(lstData -> {
                // For each LstData, iterate over its indexing details
                lstData.getListIndexingDetails().forEach(indexingDetails -> {
                    String imageName = indexingDetails.getImageName();
                    String indexingName = indexingDetails.getIndexingName();

                    // Add the imageName as key and indexingName as value to the map
                    imageToIndexingMap.put(imageName, indexingName);
                });
            });
        }
        return imageToIndexingMap;
    }
}
