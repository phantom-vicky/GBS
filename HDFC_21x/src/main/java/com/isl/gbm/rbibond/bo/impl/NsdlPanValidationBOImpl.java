package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.NsdlPanValidationBO;
import com.isl.gbm.rbibond.dao.NsdlPanValidationDAO;
import com.isl.gbm.rbibond.dto.*;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanValResponseDTO;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.rbibond.util.IncomeTaxPanValidationUtlity;
import com.isl.gbm.user.isac.common.CommonConstant;
import com.isl.gbm.user.management.util.UserAccountUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Component
public class NsdlPanValidationBOImpl implements NsdlPanValidationBO {
    private static final Logger LOGGER = LoggerFactory.getLogger(NsdlPanValidationBOImpl.class);
    @Autowired
    private CommonConstants commonConstants;
    private final IncomeTaxPanValidationUtlity utility;
    private final NsdlPanValidationDAO nsdlPanValidationDAO;
    private static final Logger log = LoggerFactory.getLogger(NsdlPanValidationBOImpl.class);

    @Autowired
    public NsdlPanValidationBOImpl(NsdlPanValidationDAO
                                               nsdlPanValidationDAO, IncomeTaxPanValidationUtlity utility){
        this.nsdlPanValidationDAO=nsdlPanValidationDAO;
        this.utility = utility;
    }

    @Value("${pan.validation.stub}")
    private String subForPanValidation;
    /**
     * The validator.
     */
    @Autowired
    private Validator validator;

    @Autowired
    ExternalService externalService;

   /* @Override
    public ResponseDataDTO api(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        ResponseDataDTO responseDataDTO2=new ResponseDataDTO();
        int count =0;
        List<String> failedRecords = new ArrayList<>();
        PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
        try {
            if (null == nsdlPanValidationRequestAndResponseDTO.getSchemeCode() ||
                    null == nsdlPanValidationRequestAndResponseDTO.getYear() ||
                    null == nsdlPanValidationRequestAndResponseDTO.getHalfYear()) {
                return nsdlPanValidationDAO.api(nsdlPanValidationRequestAndResponseDTO);
            } else {
                ResponseDataDTO responseDataDTO = nsdlPanValidationDAO.api(nsdlPanValidationRequestAndResponseDTO);
                responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
                responseDataDTO2.setMessage(responseDataDTO.getMessage());
                if (responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                    List<PanDetailsDTO> dtoList = (List<PanDetailsDTO>) responseDataDTO.getResult();
                    if (subForPanValidation.equalsIgnoreCase("YES")) {
        for (PanDetailsDTO dto : dtoList) {
            panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
            panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
            panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
            panValidationRetriggerResponseDTO.setNameStatus("Y");
            panValidationRetriggerResponseDTO.setPanStatus("Y");
            panValidationRetriggerResponseDTO.setDobStatus("Y");
            panValidationRetriggerResponseDTO.setProcessStatus("Y");
            panValidationRetriggerResponseDTO.setItrFlag("E");
            responseDataDTO2 = nsdlPanValidationDAO.apiResponse(panValidationRetriggerResponseDTO);
            if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                LOGGER.info("{} {}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
            } else {
                LOGGER.info("{} {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
            }
        }
    } else {
        for (PanDetailsDTO dto : dtoList) {
            ResponseDataDTO responseDataDTO1 = externalService.panValidation(dto);
            PanValResponseDTO panValResponseDTO = (PanValResponseDTO) responseDataDTO1.getResult();
            if(null!=panValResponseDTO&& null!=panValResponseDTO.getReturnCode()&&panValResponseDTO.getReturnCode().equals("1")&&
                    null!=panValResponseDTO.getReturnDescription()&& panValResponseDTO.getReturnDescription().equalsIgnoreCase("Success")) {
                panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                panValidationRetriggerResponseDTO.setFileMode(nsdlPanValidationRequestAndResponseDTO.getFileType());
                panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
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
                responseDataDTO2 = nsdlPanValidationDAO.apiResponse(panValidationRetriggerResponseDTO);
                if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                    LOGGER.info("{} {}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                } else {
                    LOGGER.info("{} {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                }
            } else {
                panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                panValidationRetriggerResponseDTO.setFileMode(nsdlPanValidationRequestAndResponseDTO.getFileType());
                panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
                panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                panValidationRetriggerResponseDTO.setReturnMessage((null!=panValResponseDTO&&null!=panValResponseDTO.getReturnMessage())?panValResponseDTO.getReturnMessage():"");
                panValidationRetriggerResponseDTO.setReturnCode((null!=panValResponseDTO&&null!=panValResponseDTO.getReturnCode())?panValResponseDTO.getReturnCode():"");
                panValidationRetriggerResponseDTO.setReturnDescription((null!=panValResponseDTO&&null!=panValResponseDTO.getReturnDescription())?panValResponseDTO.getReturnDescription():"");
                panValidationRetriggerResponseDTO.setProcessStatus("M");
                ResponseDataDTO res = nsdlPanValidationDAO.apiResponse(panValidationRetriggerResponseDTO);
                if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                    LOGGER.info("External DB update Status: {}", res.getMessage());
                } else {
                    LOGGER.error("External DB update Failure: {}", res.getMessage());
                }
                LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO != null ? panValResponseDTO : null);
                count++;
                failedRecords.add(dto.getPan());
            }
                        }
                    }
                }
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            responseDataDTO2.setMessage("A null value was encountered: " + npe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO2.setMessage("An unexpected error occurred: " + e.getMessage());
        }
        if(count>0){
            responseDataDTO2.setStatusCode("0");
            responseDataDTO2.setMessage("Pan validation partially Success");
            responseDataDTO2.setResult(failedRecords);
        }
        return responseDataDTO2;
    }*/
    @Override
    public ResponseDataDTO api(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        ResponseDataDTO responseDataDTO2 = new ResponseDataDTO();
        List<String> failedRecords = Collections.synchronizedList(new ArrayList<>()); // thread-safe
        int count = 0;
        long startTime = System.currentTimeMillis();
        LOGGER.info("PAN validation API started at: {}", new java.util.Date(startTime));
        try {
            // Shortcut: if no mandatory params, delegate directly
            if (nsdlPanValidationRequestAndResponseDTO.getSchemeCode() == null ||
                    nsdlPanValidationRequestAndResponseDTO.getYear() == null ||
                    nsdlPanValidationRequestAndResponseDTO.getHalfYear() == null) {
                return nsdlPanValidationDAO.api(nsdlPanValidationRequestAndResponseDTO);
            }
            ResponseDataDTO responseDataDTO = nsdlPanValidationDAO.api(nsdlPanValidationRequestAndResponseDTO);
            responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
            responseDataDTO2.setMessage(responseDataDTO.getMessage());
            if (CommonConstant.SUCCESS_DESC.equalsIgnoreCase(responseDataDTO.getMessage())) {
                List<PanDetailsDTO> dtoList = (List<PanDetailsDTO>) responseDataDTO.getResult();
                LOGGER.info("Total PAN records received: {}", dtoList.size());
                // Thread pool
                int threadPoolSize = 10; // tune this based on CPU cores / load
                String username = UserAccountUtils.getCurrentUsername();
                String solId = UserAccountUtils.getCurrentSolId();
                ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
                List<Future<Boolean>> futures = new ArrayList<>();
                for (PanDetailsDTO dto : dtoList) {
                    futures.add(executor.submit(() -> {
                        try {
                            if ("YES".equalsIgnoreCase(subForPanValidation)) {
                                // DB-only mode
                                PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                                panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                                panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
                                panValidationRetriggerResponseDTO.setNameStatus("Y");
                                panValidationRetriggerResponseDTO.setPanStatus("Y");
                                panValidationRetriggerResponseDTO.setDobStatus("Y");
                                panValidationRetriggerResponseDTO.setProcessStatus("Y");
                                panValidationRetriggerResponseDTO.setItrFlag("E");
                                ResponseDataDTO dbRes = nsdlPanValidationDAO.apiResponse(panValidationRetriggerResponseDTO,username,solId);
                                if (CommonConstant.SUCCESS_DESC.equalsIgnoreCase(dbRes.getMessage())) {
                                    LOGGER.info("{} {}", CommonConstants.DB_STATUS, dbRes.getMessage());
                                    return true;
                                } else {
                                    LOGGER.warn("{} {}", CommonConstants.DB_FAILURE, dbRes.getMessage());
                                    failedRecords.add(dto.getPan());
                                    return false;
                                }
                            } else {
                                // External service validation
                                ResponseDataDTO extRes = externalService.panValidation(dto);
                                PanValResponseDTO panValResponseDTO = (PanValResponseDTO) extRes.getResult();
                                if (panValResponseDTO != null &&
                                        "1".equals(panValResponseDTO.getReturnCode()) &&
                                        "Success".equalsIgnoreCase(panValResponseDTO.getReturnDescription())) {
                                    PanValidationRetriggerResponseDTO successDTO =mapSuccessResponse(nsdlPanValidationRequestAndResponseDTO, dto, panValResponseDTO);
                                    ResponseDataDTO dbRes = nsdlPanValidationDAO.apiResponse(successDTO,username,solId);
                                    if (CommonConstant.SUCCESS_DESC.equalsIgnoreCase(dbRes.getMessage())) {
                                        LOGGER.info("{} {}", CommonConstants.DB_STATUS, dbRes.getMessage());
                                        return true;
                                    } else {
                                        LOGGER.warn("{} {}", CommonConstants.DB_FAILURE, dbRes.getMessage());
                                        failedRecords.add(dto.getPan());
                                        return false;
                                    }
                                } else {
                                    PanValidationRetriggerResponseDTO failureDTO =mapFailureResponse(nsdlPanValidationRequestAndResponseDTO, dto, panValResponseDTO);
                                    ResponseDataDTO dbRes = nsdlPanValidationDAO.apiResponse(failureDTO,username,solId);
                                    if (CommonConstant.SUCCESS_DESC.equalsIgnoreCase(dbRes.getMessage())) {
                                        LOGGER.info("External DB update Status: {}", dbRes.getMessage());
                                    } else {
                                        LOGGER.error("External DB update Failure: {}", dbRes.getMessage());
                                    }
                                    LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE,panValResponseDTO != null ? panValResponseDTO : "null response");
                                    failedRecords.add(dto.getPan());
                                    return false;
                                }
                            }
                        } catch (Exception e) {
                            LOGGER.error("Error processing PAN validation: {}", e.getMessage(), e);
                            failedRecords.add(dto.getPan());
                            return false;
                        }
                    }));
                }
                executor.shutdown();
                executor.awaitTermination(30, TimeUnit.MINUTES); // wait for all tasks
                // Count failures
                count = (int) futures.stream().filter(f -> {
                    try { return !f.get(); } catch (Exception e) { return true; }
                }).count();
                LOGGER.info("PAN validation finished. Success: {}, Failures: {}", dtoList.size() - count, count);
            }
        } catch (NullPointerException npe) {
            LOGGER.error("Null value error: {}", npe.getMessage(), npe);
            responseDataDTO2.setMessage("A null value was encountered: " + npe.getMessage());
        } catch (Exception e) {
            LOGGER.error("Unexpected error in PAN validation: {}", e.getMessage(), e);
            responseDataDTO2.setMessage("An unexpected error occurred: " + e.getMessage());
        }
        if (count > 0) {
            responseDataDTO2.setStatusCode("0");
            responseDataDTO2.setMessage("Pan validation partially Success");
            responseDataDTO2.setResult(failedRecords);
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("PAN validation API ended at: {}", new java.util.Date(endTime));
        LOGGER.info("Total time taken: {} ms ({} seconds)", (endTime - startTime), (endTime - startTime) / 1000.0);
        return responseDataDTO2;
    }
    private PanValidationRetriggerResponseDTO mapSuccessResponse(
            NsdlPanValidationRequestAndResponseDTO requestDTO,
            PanDetailsDTO panDTO,
            PanValResponseDTO panValResponseDTO) {
        PanValidationRetriggerResponseDTO successDTO = new PanValidationRetriggerResponseDTO();
        successDTO.setSchemeCode(requestDTO.getSchemeCode());
        successDTO.setFileMode(requestDTO.getFileType());
        successDTO.setPanNumber(panDTO.getPan());
        successDTO.setNameStatus(null!=panValResponseDTO.getName()?panValResponseDTO.getName():"N");
        successDTO.setPanStatus(null!=panValResponseDTO.getPanStatus()?panValResponseDTO.getPanStatus():"N");
        successDTO.setDobStatus(null!=panValResponseDTO.getDob()?panValResponseDTO.getDob():"N");
        successDTO.setPanStatusDescription(panValResponseDTO.getPanStatusDescription());
        successDTO.setPanNameDescription(panValResponseDTO.getPanNameDescription());
        successDTO.setFatherName(panValResponseDTO.getFatherName());
        successDTO.setPanDobDescription(panValResponseDTO.getPanDobDescription());
        successDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
        successDTO.setReturnCode(panValResponseDTO.getReturnCode());
        successDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
        successDTO.setSeedingStatus(panValResponseDTO.getSeedingStatus());
        successDTO.setSeedingStatusDescription(panValResponseDTO.getSeedingStatusDescription());
        setProcessStatus(successDTO);
        return successDTO;
    }

    private PanValidationRetriggerResponseDTO mapFailureResponse(
            NsdlPanValidationRequestAndResponseDTO requestDTO,
            PanDetailsDTO panDTO,
            PanValResponseDTO panValResponseDTO) {
        PanValidationRetriggerResponseDTO failureDTO = new PanValidationRetriggerResponseDTO();
        failureDTO.setSchemeCode(requestDTO.getSchemeCode());
        failureDTO.setFileMode(requestDTO.getFileType());
        failureDTO.setPanNumber(panDTO.getPan());
        failureDTO.setNameStatus(panValResponseDTO.getName() != null ? panValResponseDTO.getName() : "N");
        failureDTO.setPanStatus(panValResponseDTO.getPanStatus() != null ? panValResponseDTO.getPanStatus() : "N");
        failureDTO.setDobStatus(panValResponseDTO.getDob() != null ? panValResponseDTO.getDob() : "N");
        failureDTO.setReturnMessage(panValResponseDTO != null ? panValResponseDTO.getReturnMessage() : "");
        failureDTO.setReturnCode(panValResponseDTO != null ? panValResponseDTO.getReturnCode() : "");
        failureDTO.setReturnDescription(panValResponseDTO != null ? panValResponseDTO.getReturnDescription() : "");
        failureDTO.setProcessStatus("M"); // mark as failed
        return failureDTO;
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
     /* @Override
    public ResponseDataDTO verifyApi(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        ResponseDataDTO responseDataDTO2 = new ResponseDataDTO();
        PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
        int count =0;
        List<String> failedRecords = new ArrayList<>();
     try {
       ResponseDataDTO responseDataDTO = nsdlPanValidationDAO.verifyApiRequest(nsdlPanValidationRequestAndResponseDTO);
                responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
                responseDataDTO2.setMessage(responseDataDTO.getMessage());
                if (responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                    List<PanDetailsDTO> dtoList = (List<PanDetailsDTO>) responseDataDTO.getResult();
        if (subForPanValidation.equalsIgnoreCase("YES")) {
        for (PanDetailsDTO dto : dtoList) {
            panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
            panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
            panValidationRetriggerResponseDTO.setOldPan(dto.getPan());
            panValidationRetriggerResponseDTO.setNameStatus("Y");
            panValidationRetriggerResponseDTO.setPanStatus("Y");
            panValidationRetriggerResponseDTO.setDobStatus("Y");
            panValidationRetriggerResponseDTO.setProcessStatus("Y");
            panValidationRetriggerResponseDTO.setItrFlag("E");
            responseDataDTO2 = nsdlPanValidationDAO.apiResponse(panValidationRetriggerResponseDTO);
            if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                LOGGER.info("DB Status: {}", responseDataDTO2.getMessage());
            } else {
                LOGGER.info("DB Failure: {}", responseDataDTO2.getMessage());
            }
        }
    } else {
        for (PanDetailsDTO dto : dtoList) {
            ResponseDataDTO responseDataDTO1 = externalService.panValidation(dto);
            PanValResponseDTO panValResponseDTO = (PanValResponseDTO) responseDataDTO1.getResult();
            if(null!=panValResponseDTO&& panValResponseDTO.getReturnCode().equals("1")&&
                    null!=panValResponseDTO.getReturnDescription()&&panValResponseDTO.getReturnDescription().equalsIgnoreCase("Success")) {
                panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                panValidationRetriggerResponseDTO.setFileMode(nsdlPanValidationRequestAndResponseDTO.getFileType());
                panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
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
                responseDataDTO2 = nsdlPanValidationDAO.apiResponse(panValidationRetriggerResponseDTO);
                if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                    LOGGER.info("{} {}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                } else {
                    LOGGER.info("{} {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                }
                    }
            else {
                panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                panValidationRetriggerResponseDTO.setFileMode(nsdlPanValidationRequestAndResponseDTO.getFileType());
                panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
                panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                panValidationRetriggerResponseDTO.setReturnMessage((null!=panValResponseDTO&&null!=panValResponseDTO.getReturnMessage())?panValResponseDTO.getReturnMessage():"");
                panValidationRetriggerResponseDTO.setReturnCode((null!=panValResponseDTO&&null!=panValResponseDTO.getReturnCode())?panValResponseDTO.getReturnCode():"");
                panValidationRetriggerResponseDTO.setReturnDescription((null!=panValResponseDTO&&null!=panValResponseDTO.getReturnDescription())?panValResponseDTO.getReturnDescription():"");
            panValidationRetriggerResponseDTO.setProcessStatus("M");
            ResponseDataDTO res = nsdlPanValidationDAO.apiResponse(panValidationRetriggerResponseDTO);
            if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                LOGGER.info("External DB update Status: {}", res.getMessage());
            } else {
                LOGGER.error("External DB update Failure: {}", res.getMessage());
            }
                LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO != null ? panValResponseDTO : null);
                count++;
            failedRecords.add(dto.getPan());
        }
        }
            }
        }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            responseDataDTO2.setMessage("A null value was encountered: " + npe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO2.setMessage("An unexpected error occurred: " + e.getMessage());
        }
        if(count>0){
            responseDataDTO2.setStatusCode("0");
            responseDataDTO2.setMessage("Pan validation partially Success");
            responseDataDTO2.setResult(failedRecords);
        }
        return responseDataDTO2;
    }*/
     @Override
     public ResponseDataDTO verifyApi(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
         long startTime = System.currentTimeMillis(); // Overall start time
         ResponseDataDTO responseDataDTO2 = new ResponseDataDTO();
         List<String> failedRecords = Collections.synchronizedList(new ArrayList<>());
         try {
             ResponseDataDTO responseDataDTO = nsdlPanValidationDAO.verifyApiRequest(nsdlPanValidationRequestAndResponseDTO);
             responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
             responseDataDTO2.setMessage(responseDataDTO.getMessage());
             if (CommonConstant.SUCCESS_DESC.equalsIgnoreCase(responseDataDTO.getMessage())) {
                 List<PanDetailsDTO> dtoList = (List<PanDetailsDTO>) responseDataDTO.getResult();
                 String username = UserAccountUtils.getCurrentUsername();
                 String solId = UserAccountUtils.getCurrentSolId();
                 if ("YES".equalsIgnoreCase(subForPanValidation)) {
                     // 🔹 DB updates in parallel
                     try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                         List<Future<?>> futures = new ArrayList<>();
                         for (PanDetailsDTO dto : dtoList) {
                             futures.add(executor.submit(() -> {
                                 long taskStart = System.currentTimeMillis();
                                 try {
                                     PanValidationRetriggerResponseDTO retriggerDTO = new PanValidationRetriggerResponseDTO();
                                     retriggerDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                                     retriggerDTO.setOldPan(dto.getPan());
                                     retriggerDTO.setNameStatus("Y");
                                     retriggerDTO.setPanStatus("Y");
                                     retriggerDTO.setDobStatus("Y");
                                     retriggerDTO.setProcessStatus("Y");
                                     retriggerDTO.setItrFlag("E");
                                     ResponseDataDTO resp = nsdlPanValidationDAO.apiResponse(retriggerDTO,username,solId);
                                     if (CommonConstant.SUCCESS_DESC.equalsIgnoreCase(resp.getMessage())) {
                                         LOGGER.info("DB Status: {}", resp.getMessage());
                                     } else {
                                         LOGGER.info("DB Failure: {}", resp.getMessage());
                                     }
                                 } finally {
                                     long taskEnd = System.currentTimeMillis();
                                     LOGGER.debug("DB Task for PAN {} executed in {} ms", dto.getPan(), (taskEnd - taskStart));
                                 }
                             }));
                         }
                         for (Future<?> f : futures) f.get(); // wait for all tasks
                     }
                 } else {
                     // 🔹 External service calls in parallel
                     try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
                         List<Future<?>> futures = new ArrayList<>();
                         for (PanDetailsDTO dto : dtoList) {
                             futures.add(executor.submit(() -> {
                                 long taskStart = System.currentTimeMillis();
                                 try {
                                     ResponseDataDTO responseDataDTO1 = externalService.panValidation(dto);
                                     PanValResponseDTO panValResponseDTO = (PanValResponseDTO) responseDataDTO1.getResult();
                                     PanValidationRetriggerResponseDTO retriggerDTO = new PanValidationRetriggerResponseDTO();
                                     retriggerDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                                     retriggerDTO.setFileMode(nsdlPanValidationRequestAndResponseDTO.getFileType());
                                     retriggerDTO.setPanNumber(dto.getPan());
                                     if (panValResponseDTO != null &&
                                             "1".equals(panValResponseDTO.getReturnCode()) &&
                                             "Success".equalsIgnoreCase(panValResponseDTO.getReturnDescription())) {
                                         retriggerDTO.setNameStatus(null!=panValResponseDTO.getName()?panValResponseDTO.getName():"N");
                                         retriggerDTO.setPanStatus(null!=panValResponseDTO.getPanStatus()?panValResponseDTO.getPanStatus():"N");
                                         retriggerDTO.setDobStatus(null!=panValResponseDTO.getDob()?panValResponseDTO.getDob():"N");
                                         retriggerDTO.setPanStatusDescription(panValResponseDTO.getPanStatusDescription());
                                         retriggerDTO.setPanNameDescription(panValResponseDTO.getPanNameDescription());
                                         retriggerDTO.setFatherName(panValResponseDTO.getFatherName());
                                         retriggerDTO.setPanDobDescription(panValResponseDTO.getPanDobDescription());
                                         retriggerDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
                                         retriggerDTO.setReturnCode(panValResponseDTO.getReturnCode());
                                         retriggerDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
                                         retriggerDTO.setSeedingStatus(panValResponseDTO.getSeedingStatus());
                                         retriggerDTO.setSeedingStatusDescription(panValResponseDTO.getSeedingStatusDescription());
                                         setProcessStatus(retriggerDTO);
                                         ResponseDataDTO resp = nsdlPanValidationDAO.apiResponse(retriggerDTO,username,solId);
                                         LOGGER.info("{} {}", CommonConstants.DB_STATUS, resp.getMessage());
                                     } else {
                                         retriggerDTO.setNameStatus(panValResponseDTO.getName() != null ? panValResponseDTO.getName() : "N");
                                         retriggerDTO.setPanStatus(panValResponseDTO.getPanStatus() != null ? panValResponseDTO.getPanStatus() : "N");
                                         retriggerDTO.setDobStatus(panValResponseDTO.getDob() != null ? panValResponseDTO.getDob() : "N");
                                         retriggerDTO.setReturnMessage(panValResponseDTO != null ? panValResponseDTO.getReturnMessage() : "");
                                         retriggerDTO.setReturnCode(panValResponseDTO != null ? panValResponseDTO.getReturnCode() : "");
                                         retriggerDTO.setReturnDescription(panValResponseDTO != null ? panValResponseDTO.getReturnDescription() : "");
                                         retriggerDTO.setProcessStatus("M");
                                         ResponseDataDTO res = nsdlPanValidationDAO.apiResponse(retriggerDTO,username,solId);
                                         LOGGER.info("External DB update Status: {}", res.getMessage());
                                         failedRecords.add(dto.getPan());
                                     }
                                 } finally {
                                     long taskEnd = System.currentTimeMillis();
                                     LOGGER.debug("External Task for executed in {} ms", (taskEnd - taskStart));
                                 }
                             }));
                         }
                         for (Future<?> f : futures) f.get();
                     }
                 }
             }
         } catch (NullPointerException npe) {
             LOGGER.error("Null value: {}", npe.getMessage(), npe);
             responseDataDTO2.setMessage("A null value was encountered: " + npe.getMessage());
         } catch (Exception e) {
             LOGGER.error("Unexpected error: {}", e.getMessage(), e);
             responseDataDTO2.setMessage("An unexpected error occurred: " + e.getMessage());
         }
         if (!failedRecords.isEmpty()) {
             responseDataDTO2.setStatusCode("0");
             responseDataDTO2.setMessage("Pan validation partially Success");
             responseDataDTO2.setResult(failedRecords);
         }
         long endTime = System.currentTimeMillis();
         LOGGER.info("Total execution time for verifyApi: {} ms", (endTime - startTime)); // Overall duration
         return responseDataDTO2;
     }

    @Override
    public ResponseDataDTO upload(MultipartFile multipartFile,
                                  NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            if (null != multipartFile) {
                String filename = multipartFile.getOriginalFilename();
                byte[] data = multipartFile.getBytes();
                if (null != nsdlPanValidationRequestAndResponseDTO &&
                        null != nsdlPanValidationRequestAndResponseDTO.getFileType() &&
                        nsdlPanValidationRequestAndResponseDTO.getFileType().equals("P002")) {
                    responseDataDTO = parseFile(multipartFile);
                    if(null!=responseDataDTO&&null!=responseDataDTO.getMessage()
                            &&(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.HEADER_VALIDATION_FAILURE) ||
                            String.valueOf(responseDataDTO.getResult()).equalsIgnoreCase("Validation Failed"))){
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }else{
                        nsdlPanValidationRequestAndResponseDTO.setIncomeTaxStatusList((responseDataDTO != null && responseDataDTO.getResult() instanceof List<?>)
                                        ? (List<IncomeTaxPanStatusDTO>) responseDataDTO.getResult(): null);
                        nsdlPanValidationRequestAndResponseDTO.setFileName(filename);
                        nsdlPanValidationRequestAndResponseDTO.setFile(data);
                    }
                } else if (null != nsdlPanValidationRequestAndResponseDTO &&
                        null != nsdlPanValidationRequestAndResponseDTO.getFileType() &&
                        nsdlPanValidationRequestAndResponseDTO.getFileType().equals("P001")) {
                    responseDataDTO = parseFile1(multipartFile);
                    if(null!=responseDataDTO&&null!=responseDataDTO.getMessage()
                            &&(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.HEADER_VALIDATION_FAILURE) ||
                            String.valueOf(responseDataDTO.getResult()).equalsIgnoreCase("Validation Failed"))){
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }else {
                        nsdlPanValidationRequestAndResponseDTO.setNsdlPanStatusList((responseDataDTO.getResult() instanceof List<?>)?(List<NsdlPanStatusDTO>)responseDataDTO.getResult(): null);
                        nsdlPanValidationRequestAndResponseDTO.setFileName(filename);
                        nsdlPanValidationRequestAndResponseDTO.setFile(data);
                    }
                } else {
                    responseDataDTO.setMessage("File Name Wrong");
                    responseDataDTO.setStatusCode("0");
                    return responseDataDTO;
                }
            } else {
                responseDataDTO.setMessage("File Data Empty");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        } catch (Exception e) {
            LOGGER.info("File not in expected format Validation errors: {}", e.toString(), e);
            responseDataDTO.setMessage("File Validation Error ");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return nsdlPanValidationDAO.upload(nsdlPanValidationRequestAndResponseDTO);
    }

    public ResponseDataDTO parseFile(MultipartFile file) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<IncomeTaxPanStatusDTO> panDetailsList = new ArrayList<>();
        try (InputStream inp = file.getInputStream(); XSSFWorkbook workbook=new XSSFWorkbook(inp)) {
            String[] expectedHeaders = {"PAN_NO", "ITR_FLAG_STATUS", "INCOME_TAX_DATE"};
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final Sheet sheet = workbook.getSheetAt(sheetIndex);
                if (!validateHeaders(sheet, expectedHeaders)) {
                    LOGGER.error("Header validation failed for sheet: {}", sheet.getSheetName());
                    responseDataDTO.setMessage(CommonConstants.HEADER_VALIDATION_FAILURE);
                    return responseDataDTO;
                }
                List<IncomeTaxPanStatusDTO> entityList = new ArrayList<>();
                DataFormatter formatter = new DataFormatter();
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row == null) continue;
                    IncomeTaxPanStatusDTO dto = new IncomeTaxPanStatusDTO();
                    dto.setPanNo(formatter.formatCellValue(row.getCell(0)));
                    dto.setItrFlagStatus(formatter.formatCellValue(row.getCell(1)));
                    dto.setIncomeTaxDate(formatter.formatCellValue(row.getCell(2)));
                    entityList.add(dto);
                }
                List<IncomeTaxPanStatusDTO> nonEmptyRows = entityList.stream() // Add check for empty rows
                        .filter(row -> !isRowEmpty(row))
                        .collect(Collectors.toList());
                if (nonEmptyRows == null || nonEmptyRows.isEmpty()) {
                    LOGGER.info("No data available for the specified file");
                    responseDataDTO.setMessage("No data available for the specified file");
                    responseDataDTO.setResult("Validation Failed");
                    return responseDataDTO;
                }
                String errorMessage = utility.processData(nonEmptyRows);
                if(!errorMessage.equals("NR")){
                    LOGGER.info("validation failed: {}",errorMessage);
                    responseDataDTO.setMessage(errorMessage);
                    responseDataDTO.setResult("Validation Failed");
                    return responseDataDTO;
                }
                if (CollectionUtils.isNotEmpty(nonEmptyRows)){
                    panDetailsList.addAll(nonEmptyRows);}
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        }
        responseDataDTO.setResult(this.buildResultList(panDetailsList));
        return responseDataDTO;
    }

    private boolean validateHeaders(Sheet sheet, String[] expectedHeaders) {
        Row headerRow = sheet.getRow(0); // Assuming the first row contains headers
        if (headerRow == null) {
            return false; // No header row found
        }
        for (int i = 0; i < expectedHeaders.length; i++) {
            Cell cell = headerRow.getCell(i);
            String headerValue = cell != null ? cell.getStringCellValue().trim() : "";
            if (!headerValue.equalsIgnoreCase(expectedHeaders[i])) {
                return false; // Header does not match
            }
        }
        return true; // All headers are valid
    }

    public List<IncomeTaxPanStatusDTO> buildResultList(
        final List<IncomeTaxPanStatusDTO> incomeTaxPanStatusDTO) throws DuplicateRecordException {

        final Set<IncomeTaxPanStatusDTO> rowSet = new HashSet<>(incomeTaxPanStatusDTO.size());
        final List<IncomeTaxPanStatusDTO> dtoList = new ArrayList<>(incomeTaxPanStatusDTO.size());

        for (final IncomeTaxPanStatusDTO row : incomeTaxPanStatusDTO) {
            if (!rowSet.add(row)) {
                throw new DuplicateRecordException("Duplicate Records Found In The Excel File");
            }

            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);

            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList())
                        .toString());
            }

            dtoList.add(this.mapRowToPanDetails(row));
        }

        return dtoList;
    }
    private boolean isRowEmpty(IncomeTaxPanStatusDTO row) {
        // Assuming the fields in the row are of type String; you can adjust the checks as needed.
        return (row.getPanNo() == null || row.getPanNo().trim().isEmpty()) &&
                (row.getItrFlagStatus() == null || row.getItrFlagStatus().trim().isEmpty()) &&
                (row.getIncomeTaxDate() == null || row.getIncomeTaxDate().trim().isEmpty());
    }

    public ResponseDataDTO parseFile1(MultipartFile file) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        List<NsdlPanStatusDTO> panDetailsList = new ArrayList<>();
        try (InputStream inp = file.getInputStream(); XSSFWorkbook workbook=new XSSFWorkbook(inp)) {
            String[] expectedHeaders = {"PAN","PAN_STATUS","PAN_AS_PER_NSDL","NAME",
                    "NAME_STATUS","NAME_AS_PER_NSDL","DOB","DOB_STATUS","DOB_AS_PER_NSDL","REMARKS"};
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final Sheet sheet = workbook.getSheetAt(sheetIndex);
                if (!validateHeaders(sheet, expectedHeaders)) {
                    LOGGER.error("Header validation failed for sheet: {}" ,sheet.getSheetName());
                    responseDataDTO.setMessage(CommonConstants.HEADER_VALIDATION_FAILURE);
                    return responseDataDTO;
                }
                List<NsdlPanStatusDTO> entityList = new ArrayList<>();
                DataFormatter formatter = new DataFormatter();
                    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                        Row row = sheet.getRow(i);
                        if (row == null) continue;
                        NsdlPanStatusDTO dto = new NsdlPanStatusDTO();
                        dto.setPan(formatter.formatCellValue(row.getCell(0)));
                        dto.setPanStatus(formatter.formatCellValue(row.getCell(1)));
                        dto.setPanAsPerNsdl(formatter.formatCellValue(row.getCell(2)));
                        dto.setName(formatter.formatCellValue(row.getCell(3)));
                        dto.setNameStatus(formatter.formatCellValue(row.getCell(4)));
                        dto.setNameAsPerNsdl(formatter.formatCellValue(row.getCell(5)));
                        dto.setDob(formatter.formatCellValue(row.getCell(6)));
                        dto.setDobStatus(formatter.formatCellValue(row.getCell(7)));
                        dto.setDobAsPerNsdl(formatter.formatCellValue(row.getCell(8)));
                        dto.setRemarks(formatter.formatCellValue(row.getCell(9)));
                        entityList.add(dto);
                    }
                    List<NsdlPanStatusDTO> nonEmptyRows = entityList.stream()
                        .filter(row -> !isRowEmpty1(row))  // Add check for empty rows
                        .collect(Collectors.toList());
                if (nonEmptyRows == null || nonEmptyRows.isEmpty()) {
                    LOGGER.info("No data available for the specified file");
                    responseDataDTO.setMessage("No data available for the specified file");
                    responseDataDTO.setResult("Validation Failed");
                    return responseDataDTO;
                }
                String errorMessage = utility.processDataNsdl(nonEmptyRows);
                if(!errorMessage.equals("NR")){
                    LOGGER.info("validation failed: {}", errorMessage);
                    responseDataDTO.setMessage(errorMessage);
                    responseDataDTO.setResult("Validation Failed");
                    return responseDataDTO;
                }
                if (CollectionUtils.isNotEmpty(nonEmptyRows)) {
                    panDetailsList.addAll(nonEmptyRows);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        }
        responseDataDTO.setResult(this.buildResultList1(panDetailsList));
        return responseDataDTO;
    }

    public List<NsdlPanStatusDTO> buildResultList1(final List<NsdlPanStatusDTO> nsdlPanStatusDTO) throws DuplicateRecordException {

        final Set<NsdlPanStatusDTO> rowSet = new HashSet<>(nsdlPanStatusDTO.size());
        final List<NsdlPanStatusDTO> dtoList = new ArrayList<>(nsdlPanStatusDTO.size());

        for (final NsdlPanStatusDTO row : nsdlPanStatusDTO) {
            if (!rowSet.add(row)) {
                throw new DuplicateRecordException("Duplicate Records Found In The Excel File");
            }
            final Errors errors = new BeanPropertyBindingResult(row, row.getClass().getName());
            this.validator.validate(row, errors);
            if (errors.hasErrors()) {
                throw new InvalidDataException(errors.getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList())
                        .toString());
            }
            dtoList.add(this.mapRowToPanDetails1(row));
        }
        return dtoList;
    }

    private boolean isRowEmpty1(NsdlPanStatusDTO row) {
        return isBlank(row.getPan()) &&
                isBlank(row.getName()) &&
                isBlank(row.getDob()) &&
                isBlank(row.getNameStatus()) &&
                isBlank(row.getPanStatus()) &&
                isBlank(row.getDobStatus()) &&
                isBlank(row.getDobAsPerNsdl()) &&
                isBlank(row.getPanAsPerNsdl()) &&
                isBlank(row.getRemarks()) &&
                isBlank(row.getNameAsPerNsdl());
    }
    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
    private IncomeTaxPanStatusDTO mapRowToPanDetails(IncomeTaxPanStatusDTO row) {
        IncomeTaxPanStatusDTO panDetails = new IncomeTaxPanStatusDTO();
        panDetails.setPanNo(row.getPanNo());
        panDetails.setItrFlagStatus(row.getItrFlagStatus());
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
        if (null != row.getIncomeTaxDate()) {
            String formattedDate = null;
            if (isNumeric(row.getIncomeTaxDate())) {
                double serialNumber = Double.parseDouble(row.getIncomeTaxDate());
                // Ensure serial number is within valid Excel date range
                if (serialNumber > 0) {
                    Date javaDate = DateUtil.getJavaDate(serialNumber);
                    formattedDate = inputFormat.format(javaDate);
                }
            } else {
                try {
                    Date date = inputFormat.parse(row.getIncomeTaxDate());
                    formattedDate = inputFormat.format(date);
                } catch (ParseException e) {
                    LOGGER.info("{} {}", CommonConstants.INVALID_DATE_FORMAT, row.getIncomeTaxDate());
                }
            }
            panDetails.setIncomeTaxDate(formattedDate);
        }
        return panDetails;
    }

    private NsdlPanStatusDTO mapRowToPanDetails1(NsdlPanStatusDTO row) {
        NsdlPanStatusDTO panDetails = new NsdlPanStatusDTO();
        panDetails.setPan(row.getPan());
        panDetails.setPanStatus(row.getPanStatus());
        panDetails.setPanAsPerNsdl(row.getPanAsPerNsdl());
        panDetails.setName(row.getName());
        panDetails.setNameStatus(row.getNameStatus());
        panDetails.setNameAsPerNsdl(row.getNameAsPerNsdl());
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
        if (null != row.getDob()) {
            String formattedDate = null;
            if (isNumeric(row.getDob())) {
                double serialNumber = Double.parseDouble(row.getDob());
                // Ensure serial number is within valid Excel date range
                if (serialNumber > 0) {
                    Date javaDate = DateUtil.getJavaDate(serialNumber);
                    formattedDate = inputFormat.format(javaDate);
                }
            } else {
                try {
                    Date date = inputFormat.parse(row.getDob());
                    formattedDate = inputFormat.format(date);
                } catch (ParseException e) {
                    LOGGER.info(CommonConstants.INVALID_DATE_FORMAT + " {}", row.getDob());
                }
            }
            panDetails.setDob(formattedDate);
        } else {
            panDetails.setDob(row.getDob());
        }
        panDetails.setDobStatus(row.getDobStatus());
        if (null != row.getDobAsPerNsdl()) {
            String nsdlDate = null;
            if (isNumeric(row.getDobAsPerNsdl())) {
                double serialNumber = Double.parseDouble(row.getDobAsPerNsdl());
                // Ensure serial number is within valid Excel date range
                if (serialNumber > 0) {
                    Date javaDate = DateUtil.getJavaDate(serialNumber);
                    nsdlDate = inputFormat.format(javaDate);
                }
            } else {
                try {
                    Date date = inputFormat.parse(row.getDobAsPerNsdl());
                    nsdlDate = inputFormat.format(date);
                } catch (ParseException e) {
                    LOGGER.info("Invalid Date Format: {}", row.getDobAsPerNsdl());
                }
            }
            panDetails.setDobAsPerNsdl(nsdlDate);
        } else {
            panDetails.setDobAsPerNsdl(row.getDobAsPerNsdl());
        }
        panDetails.setRemarks(row.getRemarks());
        return panDetails;
    }
    @Override
    public ResponseDataDTO getUploadModuleName(
            NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationDAO.upload(nsdlPanValidationRequestAndResponseDTO);
    }

    @Override
    public ResponseDataDTO delete(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationDAO.delete(nsdlPanValidationRequestAndResponseDTO);
    }

    @Override
    public ResponseDataDTO download(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        ResponseDataDTO responseDataDTO = nsdlPanValidationDAO.download(nsdlPanValidationRequestAndResponseDTO);
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO=new CashFileUploadVerifySelectRequestDTO();
        try {
    if(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
    if (null != nsdlPanValidationRequestAndResponseDTO &&
    null != nsdlPanValidationRequestAndResponseDTO.getFileType() &&
     nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P001")) {
        cashFileUploadVerifySelectRequestDTO.setFile(bulkStatementNsdlExcel(responseDataDTO));
        cashFileUploadVerifySelectRequestDTO.setFileName("NSDL_PAN_STATUS.xlsx");
        responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO);
    } else if (null != nsdlPanValidationRequestAndResponseDTO &&
    null != nsdlPanValidationRequestAndResponseDTO.getFileType() &&
    nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P002")) {
        cashFileUploadVerifySelectRequestDTO.setFile(bulkStatementIncomeTaxExcel(responseDataDTO));
        cashFileUploadVerifySelectRequestDTO.setFileName("INCOME_TAX_PAN_STATUS.xlsx");
        responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO);
    }
}
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage("Error: " + e.getMessage());
        }
        return responseDataDTO;
    }
    private byte[] bulkStatementIncomeTaxExcel(ResponseDataDTO responseDataDTO) {
        List<IncomeTaxDownloadDTO> list = (List<IncomeTaxDownloadDTO>) responseDataDTO.getResult();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            SXSSFRow headerRow = sheet.createRow(0);
            String[] headers = { "CUSTOMER_NAME","PAN_NO" };
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);
            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }
            list = (List<IncomeTaxDownloadDTO>) responseDataDTO.getResult();
            int rowNumber = 1;
            if (!list.isEmpty()) {
                for (IncomeTaxDownloadDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            data.getCustomerName() != null ? data.getCustomerName() : "",
                            data.getPanNo() != null ? data.getPanNo() : ""
                    };
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            }
            else{
            log.info("INCOMETAX PAN OBJECT HAVING EMPTY DATA ");
            }
            log.info("INCOMETAX PAN DATA GENERATION COMPLETED...");
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: {}", e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
    private byte[] bulkStatementNsdlExcel(ResponseDataDTO responseDataDTO) {
        List<NsdlPanStatusDTO> list = (List<NsdlPanStatusDTO>) responseDataDTO.getResult();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            workbook.setCompressTempFiles(true);
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            // Create header row with bold text
            SXSSFRow headerRow = sheet.createRow(0);
            String[] headers = {
                    "PAN", "PAN_STATUS", "PAN_AS_PER_NSDL", "NAME", "NAME_STATUS",
                    "NAME_AS_PER_NSDL", "DOB", "DOB_STATUS", "DOB_AS_PER_NSDL", "REMARKS"
            };
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);
            // Track maximum column widths
            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }
            list = (List<NsdlPanStatusDTO>) responseDataDTO.getResult();
            // Populate data rows
            int rowNumber = 1;
            if (!list.isEmpty()) {
                for (NsdlPanStatusDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            data.getPan() != null ? data.getPan() : "",
                            data.getPanStatus() != null ? data.getPanStatus() : "",
                            data.getPanAsPerNsdl() != null ? data.getPanAsPerNsdl() : "",
                            data.getName() != null ? data.getName() : "",
                            data.getNameStatus() != null ? data.getNameStatus() : "",
                            data.getNameAsPerNsdl() != null ? data.getNameAsPerNsdl() : "",
                            data.getDob() != null ? data.getDob() : "",
                            data.getDobStatus() != null ? data.getDobStatus() : "",
                            data.getDobAsPerNsdl() != null ? data.getDobAsPerNsdl() : "",
                            data.getRemarks() != null ? data.getRemarks() : "",
                    };
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            }
            else
            {
                log.info("NSDL PAN OBJECT HAVING EMPTY DATA ");
            }
            log.info("NSDL PAN DATA GENERATION COMPLETED...");
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Exception while writing the Excel file: {}", e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
    @Override
    public ResponseDataDTO verify(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        return nsdlPanValidationDAO.verify(nsdlPanValidationRequestAndResponseDTO);
    }

    @Override
    public ResponseDataDTO verifyDownload(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        ResponseDataDTO responseDataDTO= nsdlPanValidationDAO.verifyDownload(nsdlPanValidationRequestAndResponseDTO);
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO=new CashFileUploadVerifySelectRequestDTO();
        try {
            if(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                if (null != nsdlPanValidationRequestAndResponseDTO &&null != nsdlPanValidationRequestAndResponseDTO.getFileType()&&nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P001")) {
                    cashFileUploadVerifySelectRequestDTO.setFile(bulkStatementNsdlExcel(responseDataDTO));
                    cashFileUploadVerifySelectRequestDTO.setFileName("NSDL_PAN_STATUS_REDEMPTION_VERIFY.xlsx");
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO);
                } else if (null != nsdlPanValidationRequestAndResponseDTO &&null != nsdlPanValidationRequestAndResponseDTO.getFileType()&&nsdlPanValidationRequestAndResponseDTO.getFileType().equalsIgnoreCase("P002")) {
                    cashFileUploadVerifySelectRequestDTO.setFile(bulkStatementIncomeTaxExcelVerify(responseDataDTO));
                    cashFileUploadVerifySelectRequestDTO.setFileName("INCOME_TAX_PAN_STATUS_REDEMPTION_VERIFY.xlsx");
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage("Error: " + e.getMessage());
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO inquiry(NsdlPanValidationRequestAndResponseDTO nsdlPanValidationRequestAndResponseDTO) {
        if (null == nsdlPanValidationRequestAndResponseDTO.getSchemeCode()
                && null == nsdlPanValidationRequestAndResponseDTO.getFileStatus()) {
            return nsdlPanValidationDAO.inquiryDetails(nsdlPanValidationRequestAndResponseDTO);
        }
        return nsdlPanValidationDAO.inquiry(nsdlPanValidationRequestAndResponseDTO);
    }
    private byte[] bulkStatementIncomeTaxExcelVerify(ResponseDataDTO responseDataDTO) {
        List<IncomeTaxPanStatusDTO> list = (List<IncomeTaxPanStatusDTO>) responseDataDTO.getResult();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
            SXSSFSheet sheet = workbook.createSheet("sheet1");
            sheet.setRandomAccessWindowSize(100);
            byteArrayOutputStream = new ByteArrayOutputStream();
            SXSSFRow headerRow = sheet.createRow(0);
            String[] headers = {"PAN_NO","ITR_FLAG_STATUS","INCOME_TAX_DATE"};
            CellStyle headerCellStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerCellStyle.setFont(font);
            int[] maxColumnWidths = new int[headers.length];
            for (int i = 0; i < headers.length; i++) {
                SXSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerCellStyle);
                maxColumnWidths[i] = headers[i].length();
            }
            list = (List<IncomeTaxPanStatusDTO>) responseDataDTO.getResult();
            int rowNumber = 1;
            if (!list.isEmpty()) {
                for (IncomeTaxPanStatusDTO data : list) {
                    SXSSFRow row = sheet.createRow(rowNumber++);
                    int cellNumber = 0;
                    String[] cellValues = {
                            data.getPanNo() != null ? data.getPanNo() : "",
                            data.getItrFlagStatus() != null ? data.getItrFlagStatus() : "",
                            data.getIncomeTaxDate() != null ? data.getIncomeTaxDate() : ""
                    };
                    for (int i = 0; i < cellValues.length; i++) {
                        row.createCell(cellNumber++).setCellValue(cellValues[i]);
                        if (cellValues[i].length() > maxColumnWidths[i]) {
                            maxColumnWidths[i] = cellValues[i].length() + 5;
                        }
                    }
                }
            }
            else{
                LOGGER.info("INCOMETAX PAN VERIFY OBJECT HAVING EMPTY DATA ");
            }
            LOGGER.info("INCOMETAX PAN VERIFY DATA GENERATION COMPLETED...");
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Exception while writing the Excel file: {}", e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }
}
