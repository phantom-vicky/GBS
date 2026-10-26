package com.isl.gbm.rbibond.controller;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.PanValidationRetriggerBO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanValResponseDTO;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.user.isac.common.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class PanValidationController {

    private static final Logger logger = LoggerFactory.getLogger(PanValidationController.class);

    @Value("${pan.validation.stub}")
    private String subForPanValidation;

    @Autowired
    ExternalService externalService;

    @Autowired
    PanValidationRetriggerBO panValidationRetriggerBO;

    private final ExecutorService executorService;
    private static final int THREAD_POOL_SIZE = 10;  // Thread pool size
    private static final int MAX_RETRIES = 3;  // Max retries for failed API calls
    private static final long RETRY_DELAY = 1000L;  // Retry delay in milliseconds

    public PanValidationController(PanValidationRetriggerBO panValidationRetriggerBO) {
        this.panValidationRetriggerBO = panValidationRetriggerBO;
        this.executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);  // Initialize thread pool
    }

    //@Scheduled(fixedDelay = 300000)//5 mini
    public void validatePanScheduler() {
        ResponseDataDTO data = panValidationRetriggerBO.validatePanSchedulerRequest();

        if (CommonConstants.SUCCESS_CONST.equalsIgnoreCase(data.getMessage())) {
            List<PanDetailsDTO> request = (List<PanDetailsDTO>) data.getResult();
            if (request != null && !request.isEmpty()) {
                logger.info("Request received with input data size: {}", request.size());

                for (PanDetailsDTO item : request) {
                    // Submit each validation task to the thread pool
                    executorService.submit(() -> processPanValidation(item));
                }
            }else {
                logger.info("no record found pan val: {}", request != null ? request.size() : "request is null");
            }
        } else {
            logger.error("Error fetching DB request message: {}", data.getMessage());
        }
    }

    private void processPanValidation(PanDetailsDTO item) {
        try {
            PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
            //logger.info("Processing Input Data Item: {}", item.getPan());

            if ("YES".equalsIgnoreCase(subForPanValidation)) {
                // Process stubbed PAN validation
                processStubPanValidation(item, panValidationRetriggerResponseDTO);
            } else {
                // Process real external API PAN validation
                processExternalPanValidation(item, panValidationRetriggerResponseDTO);
            }
        } catch (Exception e) {
            logger.error("Error processing PAN validation for: {}", e.getMessage());
        }
    }

    private void processStubPanValidation(PanDetailsDTO item, PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        panValidationRetriggerResponseDTO.setPanStatus("E");
        panValidationRetriggerResponseDTO.setPanNumber(item.getPan());
        panValidationRetriggerResponseDTO.setDobStatus("N");
        panValidationRetriggerResponseDTO.setNameStatus("Y");
        setProcessStatus(panValidationRetriggerResponseDTO);

        ResponseDataDTO res = panValidationRetriggerBO.validatePanSchedulerResponse(panValidationRetriggerResponseDTO);
        if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
            logger.info("Stub DB Status: {}", res.getMessage());
        } else {
            logger.error("Stub DB Failure: {}", res.getMessage());
        }
    }

    private void processExternalPanValidation(PanDetailsDTO item, PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        int attempts = 0;
        boolean success = false;

        while (attempts < MAX_RETRIES && !success) {
            try {
                attempts++;
                ResponseDataDTO data = externalService.panValidation(item);
                int responseCode= Integer.parseInt(data.getStatusCode());
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    logger.error("Failed with HTTP error code: {}", responseCode);
                    throw new IOException("Server returned non-OK status: " + responseCode);
                }
                PanValResponseDTO panValResponseDTO = (PanValResponseDTO) data.getResult();

                if (panValResponseDTO != null && "1".equals(panValResponseDTO.getReturnCode())
                        && "Success".equalsIgnoreCase(panValResponseDTO.getReturnDescription())) {
                    // success scenario
                    processSuccessResponse(panValResponseDTO, panValidationRetriggerResponseDTO);
                    success = true;
                } else if ((panValResponseDTO != null &&
                        panValResponseDTO.getReturnCode() != null &&
                        panValResponseDTO.getReturnDescription() != null &&
                        !panValResponseDTO.getReturnCode().equals("1") &&
                        !panValResponseDTO.getReturnDescription().equals("Success"))) {
                    processNegativeResponse(item.getPan(),panValResponseDTO,panValidationRetriggerResponseDTO);
                    success = true;
                    logger.info("Pan validation returned an undesired status. No further action needed.");
                } else {
                    // failure scenario, handle with retries if necessary
                    String returnDesc = (panValResponseDTO != null && panValResponseDTO.getReturnDescription() != null)
                            ? panValResponseDTO.getReturnDescription() : "null";

                    logger.error("Failed PAN validation for {}: {}", returnDesc);
                    if (attempts < MAX_RETRIES) {
                        Thread.sleep(RETRY_DELAY);  // Simple retry with delay
                    }
                }

            } catch (Exception e) {
                logger.error("Error during external PAN validation for {}: {}", e.getMessage());
                if (attempts < MAX_RETRIES) {
                    try {
                        Thread.sleep(RETRY_DELAY);  // Retry on failure
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }

    private void processSuccessResponse(PanValResponseDTO panValResponseDTO, PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        // Populate success data
        panValidationRetriggerResponseDTO.setPanNumber(panValResponseDTO.getPan());
        panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
        panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
        panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
        panValidationRetriggerResponseDTO.setPanStatusDescription(panValResponseDTO.getPanStatusDescription());
        panValidationRetriggerResponseDTO.setPanNameDescription(panValResponseDTO.getPanNameDescription());
        panValidationRetriggerResponseDTO.setFatherName(panValResponseDTO.getFatherName());
        panValidationRetriggerResponseDTO.setPanDobDescription(panValResponseDTO.getPanDobDescription());
        panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
        panValidationRetriggerResponseDTO.setReturnCode(panValResponseDTO.getReturnCode());
        panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
        panValidationRetriggerResponseDTO.setSeedingStatus(panValResponseDTO.getSeedingStatus());
        panValidationRetriggerResponseDTO.setSeedingStatusDescription(panValResponseDTO.getSeedingStatusDescription());
        setProcessStatus(panValidationRetriggerResponseDTO);

        ResponseDataDTO res = panValidationRetriggerBO.validatePanSchedulerResponse(panValidationRetriggerResponseDTO);
        if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
            logger.info("External DB Status: {}", res.getMessage());
        } else {
            logger.error("External DB Failure: {}", res.getMessage());
        }
    }

    private void processNegativeResponse(String panNumber,PanValResponseDTO panValResponseDTO, PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        // Populate success data
        panValidationRetriggerResponseDTO.setPanNumber(panNumber);
        panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
        panValidationRetriggerResponseDTO.setReturnCode(panValResponseDTO.getReturnCode());
        panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
        panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
        panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
        panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
        setProcessStatus(panValidationRetriggerResponseDTO);

        ResponseDataDTO res = panValidationRetriggerBO.validatePanSchedulerResponse(panValidationRetriggerResponseDTO);
        if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
            logger.info("External DB update Status: {}", res.getMessage());
        } else {
            logger.error("External DB update Failure: {}", res.getMessage());
        }
    }

    public void setProcessStatus(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        if ("E".equalsIgnoreCase(panValidationRetriggerResponseDTO.getPanStatus()) &&
                "Y".equalsIgnoreCase(panValidationRetriggerResponseDTO.getNameStatus()) &&
                "Y".equalsIgnoreCase(panValidationRetriggerResponseDTO.getDobStatus())) {
            panValidationRetriggerResponseDTO.setProcessStatus("Y");
        } else {
            panValidationRetriggerResponseDTO.setProcessStatus("M");
        }
    }
}

