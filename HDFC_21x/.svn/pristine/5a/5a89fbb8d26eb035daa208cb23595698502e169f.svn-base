package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.cbs.integration.common.DuplicateRecordException;
import com.isl.gbm.cbs.integration.common.InvalidDataException;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.NsdlPanValidationRedemptionOnMaturityBO;
import com.isl.gbm.rbibond.dao.NsdlPanValidationRedemptionOnMaturityDAO;
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
public class NsdlPanValidationRedemptionOnMaturityBOImpl implements NsdlPanValidationRedemptionOnMaturityBO {
    private static final Logger LOGGER = LoggerFactory.getLogger(NsdlPanValidationRedemptionOnMaturityBOImpl.class);
    private final NsdlPanValidationRedemptionOnMaturityDAO nsdlPanValidationRedemptionOnMaturityDAO;
    private final IncomeTaxPanValidationUtlity utlity;
    @Autowired
    public NsdlPanValidationRedemptionOnMaturityBOImpl(NsdlPanValidationRedemptionOnMaturityDAO
                                                                   nsdlPanValidationRedemptionOnMaturityDAO, IncomeTaxPanValidationUtlity utlity){
        this.nsdlPanValidationRedemptionOnMaturityDAO=nsdlPanValidationRedemptionOnMaturityDAO;
        this.utlity=utlity;
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
  public ResponseDataDTO api(NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO2 = new ResponseDataDTO();
        PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
        int count =0;
        List<String> failedRecords = new ArrayList<>();
        try {
            if (null == nsdlPanValidationRequestAndResponseDTO.getSchemeCode() ||
                    null == nsdlPanValidationRequestAndResponseDTO.getType()) {
                return nsdlPanValidationRedemptionOnMaturityDAO.api(nsdlPanValidationRequestAndResponseDTO);
            } else {
ResponseDataDTO responseDataDTO = nsdlPanValidationRedemptionOnMaturityDAO.api(nsdlPanValidationRequestAndResponseDTO);
                responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
                responseDataDTO2.setMessage(responseDataDTO.getMessage());
                if (responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                    List<PanDetailsDTO> dtoList = (List<PanDetailsDTO>) responseDataDTO.getResult();
                    if (subForPanValidation.equalsIgnoreCase("YES")) {
                        for (PanDetailsDTO dto : dtoList) {
                            panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
          panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
          panValidationRetriggerResponseDTO.setType(nsdlPanValidationRequestAndResponseDTO.getType());
                            panValidationRetriggerResponseDTO.setOldPan(dto.getPan());
                            panValidationRetriggerResponseDTO.setNameStatus("N");
                            panValidationRetriggerResponseDTO.setPanStatus("Y");
                            panValidationRetriggerResponseDTO.setDobStatus("Y");
                            panValidationRetriggerResponseDTO.setProcessStatus("M");
                            panValidationRetriggerResponseDTO.setItrFlag("E");
          responseDataDTO2 = nsdlPanValidationRedemptionOnMaturityDAO.apiResponse(panValidationRetriggerResponseDTO);
                            if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                LOGGER.info("{}: {}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                            } else {
                                LOGGER.info("{}: {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
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
                                panValidationRetriggerResponseDTO.setType(nsdlPanValidationRequestAndResponseDTO.getType());
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
                                responseDataDTO2 = nsdlPanValidationRedemptionOnMaturityDAO.apiResponse(panValidationRetriggerResponseDTO);
                                if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                    LOGGER.info("{}: {}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                                } else {
                                    LOGGER.info("{}: {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                                }
                            } else {
                                panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                                panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                                panValidationRetriggerResponseDTO.setType(nsdlPanValidationRequestAndResponseDTO.getType());
                                panValidationRetriggerResponseDTO.setFileMode(nsdlPanValidationRequestAndResponseDTO.getFileType());
                                panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
                                panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                                panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                                panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                                panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
                                panValidationRetriggerResponseDTO.setReturnCode(panValResponseDTO.getReturnCode());
                                panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
                                panValidationRetriggerResponseDTO.setProcessStatus("M");
                                LOGGER.info("Response from ValidatePan ", panValidationRetriggerResponseDTO.toString());
                                ResponseDataDTO res = nsdlPanValidationRedemptionOnMaturityDAO.apiResponse(panValidationRetriggerResponseDTO);
                                if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                    LOGGER.info("External DB update Status: {}", res.getMessage());
                                } else {
                                    LOGGER.error("External DB update Failure: {}", res.getMessage());
                                }
                                LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                                count++;
                                failedRecords.add(dto.getPan());
                            }
                        }
                    }
                }
            }
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
  public ResponseDataDTO api(NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO) {
      ResponseDataDTO responseDataDTO2 = new ResponseDataDTO();
      List<String> failedRecords = Collections.synchronizedList(new ArrayList<>());
      int threadPoolSize = 10; // tune as per server capacity
      try {
          if (null == nsdlPanValidationRequestAndResponseDTO.getSchemeCode() ||
                  null == nsdlPanValidationRequestAndResponseDTO.getType()) {
              return nsdlPanValidationRedemptionOnMaturityDAO.api(nsdlPanValidationRequestAndResponseDTO);
          } else {
              ResponseDataDTO responseDataDTO = nsdlPanValidationRedemptionOnMaturityDAO.api(nsdlPanValidationRequestAndResponseDTO);
              responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
              responseDataDTO2.setMessage(responseDataDTO.getMessage());
              if (CommonConstant.SUCCESS_DESC.equalsIgnoreCase(responseDataDTO.getMessage())) {
                  List<PanDetailsDTO> dtoList = (List<PanDetailsDTO>) responseDataDTO.getResult();
                  String username = UserAccountUtils.getCurrentUsername();
                  String solId = UserAccountUtils.getCurrentSolId();
                  ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
                  List<Future<Void>> futures = new ArrayList<>();
                  long startTime = System.currentTimeMillis();
                  LOGGER.info("PAN validation started at: {}", new Date(startTime));
                  for (PanDetailsDTO dto : dtoList) {
                      futures.add(executor.submit(() -> {
                          try {
                              processPanDetails(dto, nsdlPanValidationRequestAndResponseDTO, failedRecords,username,solId);
                          } catch (Exception e) {
                              LOGGER.error("Error processing PAN {}: {}", maskPan(dto.getPan()), e.getMessage(), e);
                              failedRecords.add(dto.getPan());
                          }
                          return null;
                      }));
                  }
                  // Wait for all tasks
                  for (Future<Void> future : futures) {
                      try {
                          future.get();
                      } catch (Exception e) {
                          LOGGER.error("Thread execution error: {}", e.getMessage(), e);
                      }
                  }
                  executor.shutdown();
                  long endTime = System.currentTimeMillis();
                  long durationMs = endTime - startTime;
                  LOGGER.info("Time taken for PAN validation: {} ms ({} seconds)", durationMs, durationMs / 1000.0);
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
          responseDataDTO2.setMessage("An unexpected error occurred: " + e.getMessage());
      }
      if (!failedRecords.isEmpty()) {
          responseDataDTO2.setStatusCode("0");
          responseDataDTO2.setMessage("Pan validation partially Success");
          responseDataDTO2.setResult(failedRecords);
      }
      return responseDataDTO2;
  }
    private String maskPan(String pan) {
        if (pan == null || pan.length() < 4) {
            return "XXXX";
        }
        return "XXXX" + pan.substring(pan.length() - 4);
    }
    /**
     * Handles validation for a single PAN (both subForPanValidation=YES and externalService call case).
     */
    private void processPanDetails(PanDetailsDTO dto,
                                   NsdlPanValidationRedemptionOnMaturityReqAndResDTO requestDto,
                                   List<String> failedRecords, String userName,String solId) {
        PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO;
        if ("YES".equalsIgnoreCase(subForPanValidation)) {
            panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
            panValidationRetriggerResponseDTO.setSchemeCode(requestDto.getSchemeCode());
            panValidationRetriggerResponseDTO.setType(requestDto.getType());
            panValidationRetriggerResponseDTO.setOldPan(dto.getPan());
            panValidationRetriggerResponseDTO.setNameStatus("Y");
            panValidationRetriggerResponseDTO.setPanStatus("Y");
            panValidationRetriggerResponseDTO.setDobStatus("Y");
            panValidationRetriggerResponseDTO.setProcessStatus("Y");
            panValidationRetriggerResponseDTO.setItrFlag("E");
            ResponseDataDTO daoRes = nsdlPanValidationRedemptionOnMaturityDAO.apiResponse(panValidationRetriggerResponseDTO,userName,solId);
            logDbStatus(daoRes);
        } else {
            ResponseDataDTO responseDataDTO1 = externalService.panValidation(dto);
            PanValResponseDTO panValResponseDTO = (PanValResponseDTO) responseDataDTO1.getResult();

            if (isValidPanResponse(panValResponseDTO)) {
                panValidationRetriggerResponseDTO = buildSuccessResponse(dto, requestDto, panValResponseDTO);
                ResponseDataDTO daoRes = nsdlPanValidationRedemptionOnMaturityDAO.apiResponse(panValidationRetriggerResponseDTO,userName,solId);
                logDbStatus(daoRes);
            } else {
                panValidationRetriggerResponseDTO = buildFailureResponse(dto, requestDto, panValResponseDTO);
                ResponseDataDTO daoRes = nsdlPanValidationRedemptionOnMaturityDAO.apiResponse(panValidationRetriggerResponseDTO,userName,solId);
                logDbStatus(daoRes);
                failedRecords.add(dto.getPan());
            }
        }
    }
    private PanValidationRetriggerResponseDTO buildSuccessResponse(PanDetailsDTO dto,
                                                                   NsdlPanValidationRedemptionOnMaturityReqAndResDTO requestDto,
                                                                   PanValResponseDTO panValResponseDTO) {
        PanValidationRetriggerResponseDTO response = new PanValidationRetriggerResponseDTO();
        response.setSchemeCode(requestDto.getSchemeCode());
        response.setType(requestDto.getType());
        response.setFileMode(requestDto.getFileType());
        response.setPanNumber(dto.getPan());
        response.setNameStatus(null!=panValResponseDTO.getName()?panValResponseDTO.getName():"N");
        response.setPanStatus(null!=panValResponseDTO.getPanStatus()?panValResponseDTO.getPanStatus():"N");
        response.setDobStatus(null!=panValResponseDTO.getDob()?panValResponseDTO.getDob():"N");
        response.setPanStatusDescription(panValResponseDTO.getPanStatusDescription());
        response.setPanNameDescription(panValResponseDTO.getPanNameDescription());
        response.setFatherName(panValResponseDTO.getFatherName());
        response.setPanDobDescription(panValResponseDTO.getPanDobDescription());
        response.setReturnMessage(panValResponseDTO.getReturnMessage());
        response.setReturnCode(panValResponseDTO.getReturnCode());
        response.setReturnDescription(panValResponseDTO.getReturnDescription());
        response.setSeedingStatus(panValResponseDTO.getSeedingStatus());
        response.setSeedingStatusDescription(panValResponseDTO.getSeedingStatusDescription());
        // Keep your existing method to decide process status
        setProcessStatus(response);
        return response;
    }
    private PanValidationRetriggerResponseDTO buildFailureResponse(PanDetailsDTO dto,
                                                                   NsdlPanValidationRedemptionOnMaturityReqAndResDTO requestDto,
                                                                   PanValResponseDTO panValResponseDTO) {
        PanValidationRetriggerResponseDTO response = new PanValidationRetriggerResponseDTO();
        response.setSchemeCode(requestDto.getSchemeCode());
        response.setType(requestDto.getType());
        response.setFileMode(requestDto.getFileType());
        response.setPanNumber(dto.getPan());
        // When failure, still set what you get from service (may be null/partial)
        if (panValResponseDTO != null) {
            response.setNameStatus(null!=panValResponseDTO.getName()?panValResponseDTO.getName():"N");
            response.setPanStatus(null!=panValResponseDTO.getPanStatus()?panValResponseDTO.getPanStatus():"N");
            response.setDobStatus(null!=panValResponseDTO.getDob()?panValResponseDTO.getDob():"N");
            response.setReturnMessage(panValResponseDTO.getReturnMessage());
            response.setReturnCode(panValResponseDTO.getReturnCode());
            response.setReturnDescription(panValResponseDTO.getReturnDescription());
        }
        // Mark process as "Manual"
        response.setProcessStatus("M");
        return response;
    }

    private boolean isValidPanResponse(PanValResponseDTO res) {
        return res != null &&
                "1".equals(res.getReturnCode()) &&
                "Success".equalsIgnoreCase(res.getReturnDescription());
    }

    private void logDbStatus(ResponseDataDTO res) {
        if (CommonConstant.SUCCESS_DESC.equalsIgnoreCase(res.getMessage())) {
            LOGGER.info("{}: {}", CommonConstants.DB_STATUS, res.getMessage());
        } else {
            LOGGER.error("{}: {}", CommonConstants.DB_FAILURE, res.getMessage());
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
 /* @Override
  public ResponseDataDTO verifyApi(NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO){
        ResponseDataDTO responseDataDTO2 = new ResponseDataDTO();
        PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
      int count =0;
      List<String> failedRecords = new ArrayList<>();
        try {
ResponseDataDTO responseDataDTO = nsdlPanValidationRedemptionOnMaturityDAO.verifyApiRequest(nsdlPanValidationRequestAndResponseDTO);
                responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
                responseDataDTO2.setMessage(responseDataDTO.getMessage());
                if (responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                    List<PanDetailsDTO> dtoList = (List<PanDetailsDTO>) responseDataDTO.getResult();
                    if (subForPanValidation.equalsIgnoreCase("YES")) {
                        for (PanDetailsDTO dto : dtoList) {
                            panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
          panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
          panValidationRetriggerResponseDTO.setType(nsdlPanValidationRequestAndResponseDTO.getType());
          panValidationRetriggerResponseDTO.setFileMode(nsdlPanValidationRequestAndResponseDTO.getFileType());
                            panValidationRetriggerResponseDTO.setOldPan(dto.getPan());
                            panValidationRetriggerResponseDTO.setNameStatus("Y");
                            panValidationRetriggerResponseDTO.setPanStatus("Y");
                            panValidationRetriggerResponseDTO.setDobStatus("Y");
                            panValidationRetriggerResponseDTO.setProcessStatus("Y");
                            panValidationRetriggerResponseDTO.setItrFlag("E");
          responseDataDTO2 = nsdlPanValidationRedemptionOnMaturityDAO.verifyApiResponse(panValidationRetriggerResponseDTO);
                            if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                LOGGER.info("DB Status: {}", responseDataDTO2.getMessage());
                            } else {
                                LOGGER.info("DB Failure: {}" , responseDataDTO2.getMessage());
                            }
                        }
                    } else {
                        for (PanDetailsDTO dto : dtoList) {
                            ResponseDataDTO responseDataDTO1 = externalService.panValidation(dto);
                            PanValResponseDTO panValResponseDTO = (PanValResponseDTO) responseDataDTO1.getResult();
                            if (null != panValResponseDTO && panValResponseDTO.getReturnCode().equals("1")
                                    && null!=panValResponseDTO.getReturnDescription()&&panValResponseDTO.getReturnDescription().equalsIgnoreCase("Success")){
                                panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                                panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                                panValidationRetriggerResponseDTO.setType(nsdlPanValidationRequestAndResponseDTO.getType());
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
                                responseDataDTO2 = nsdlPanValidationRedemptionOnMaturityDAO.verifyApiResponse(panValidationRetriggerResponseDTO);
                                if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                    LOGGER.info("{}: {}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                                } else {
                                    LOGGER.info("{}: {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                                }
                            } else {
                                panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
                                panValidationRetriggerResponseDTO.setSchemeCode(nsdlPanValidationRequestAndResponseDTO.getSchemeCode());
                                panValidationRetriggerResponseDTO.setType(nsdlPanValidationRequestAndResponseDTO.getType());
                                panValidationRetriggerResponseDTO.setFileMode(nsdlPanValidationRequestAndResponseDTO.getFileType());
                                panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
                                panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                                panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                                panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                                panValidationRetriggerResponseDTO.setReturnMessage((null!=panValResponseDTO&&null!=panValResponseDTO.getReturnMessage())?panValResponseDTO.getReturnMessage():"");
                                panValidationRetriggerResponseDTO.setReturnCode((null!=panValResponseDTO&&null!=panValResponseDTO.getReturnCode())?panValResponseDTO.getReturnCode():"");
                                panValidationRetriggerResponseDTO.setReturnDescription((null!=panValResponseDTO&&null!=panValResponseDTO.getReturnDescription())?panValResponseDTO.getReturnDescription():"");
                                panValidationRetriggerResponseDTO.setProcessStatus("M");
                                ResponseDataDTO res = nsdlPanValidationRedemptionOnMaturityDAO.apiResponse(panValidationRetriggerResponseDTO);
                                if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                    LOGGER.info("External DB update Status: {}", res.getMessage());
                                } else {
                                    LOGGER.error("External DB update Failure: {}", res.getMessage());
                                }
                                LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                                count++;
                                failedRecords.add(dto.getPan());
                            }
                        }
                    }
                }
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
    public ResponseDataDTO verifyApi(NsdlPanValidationRedemptionOnMaturityReqAndResDTO nsdlPanValidationRequestAndResponseDTO) {
        ResponseDataDTO responseDataDTO2 = new ResponseDataDTO();
        List<String> failedRecords = Collections.synchronizedList(new ArrayList<>()); // thread-safe list
        int count = 0;
        long startTime = System.currentTimeMillis();
        LOGGER.info("PAN verification started at: {}", new Date(startTime));
        try {
            ResponseDataDTO responseDataDTO = nsdlPanValidationRedemptionOnMaturityDAO.verifyApiRequest(nsdlPanValidationRequestAndResponseDTO);
            responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
            responseDataDTO2.setMessage(responseDataDTO.getMessage());
            if (CommonConstant.SUCCESS_DESC.equalsIgnoreCase(responseDataDTO.getMessage())) {
                List<PanDetailsDTO> dtoList = (List<PanDetailsDTO>) responseDataDTO.getResult();
                LOGGER.info("Total PAN records received for validation: {}", dtoList.size());
                // Thread pool - limit size based on CPU cores
                String username = UserAccountUtils.getCurrentUsername();
                String solId = UserAccountUtils.getCurrentSolId();
                ExecutorService executor = Executors.newFixedThreadPool(10);
                List<Future<Boolean>> futures = new ArrayList<>();
                for (PanDetailsDTO dto : dtoList) {
                    futures.add(executor.submit(() -> {
                        try {
                            if ("YES".equalsIgnoreCase(subForPanValidation)) {
                                // direct DB update case
                                PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = buildPanValidationResponseForYES(nsdlPanValidationRequestAndResponseDTO, dto);
                                ResponseDataDTO dbRes = nsdlPanValidationRedemptionOnMaturityDAO.verifyApiResponse(panValidationRetriggerResponseDTO);
                                if (!CommonConstant.SUCCESS_DESC.equalsIgnoreCase(dbRes.getMessage())) {
                                    failedRecords.add(dto.getPan());
                                    return false;
                                }
                            } else {
                                // External API call case
                                ResponseDataDTO extRes = externalService.panValidation(dto);
                                PanValResponseDTO panValResponseDTO = (PanValResponseDTO) extRes.getResult();
                                boolean success = processExternalApiResult(nsdlPanValidationRequestAndResponseDTO, dto, panValResponseDTO, failedRecords,username,solId);
                                return success;
                            }
                            return true;
                        } catch (Exception e) {
                            failedRecords.add(dto.getPan());
                            return false;
                        }
                    }));
                }
                executor.shutdown();
                executor.awaitTermination(30, TimeUnit.MINUTES); // wait until all tasks complete
                // Count failures
                count = (int) futures.stream().filter(f -> {
                    try { return !f.get(); } catch (Exception e) { return true; }
                }).count();
                LOGGER.info("PAN verification completed. Success: {}, Failures: {}",
                        dtoList.size() - count, count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO2.setMessage("An unexpected error occurred: " + e.getMessage());
        }
        if (count > 0) {
            responseDataDTO2.setStatusCode("0");
            responseDataDTO2.setMessage("Pan validation partially Success");
            responseDataDTO2.setResult(failedRecords);
        }
        return responseDataDTO2;
    }
    private PanValidationRetriggerResponseDTO buildPanValidationResponseForYES(
            NsdlPanValidationRedemptionOnMaturityReqAndResDTO req, PanDetailsDTO dto) {
        PanValidationRetriggerResponseDTO res = new PanValidationRetriggerResponseDTO();
        res.setSchemeCode(req.getSchemeCode());
        res.setType(req.getType());
        res.setFileMode(req.getFileType());
        res.setOldPan(dto.getPan());
        res.setNameStatus("Y");
        res.setPanStatus("Y");
        res.setDobStatus("Y");
        res.setProcessStatus("Y");
        res.setItrFlag("E");
        return res;
    }

    private boolean processExternalApiResult(
            NsdlPanValidationRedemptionOnMaturityReqAndResDTO req, PanDetailsDTO dto,
            PanValResponseDTO panValResponseDTO, List<String> failedRecords, String userName,String solId) {

        if (panValResponseDTO != null && "1".equals(panValResponseDTO.getReturnCode())
                && "Success".equalsIgnoreCase(panValResponseDTO.getReturnDescription())) {

            PanValidationRetriggerResponseDTO resDTO = mapSuccessResponse(req, dto, panValResponseDTO);
            ResponseDataDTO dbRes = nsdlPanValidationRedemptionOnMaturityDAO.verifyApiResponse(resDTO);
            return CommonConstant.SUCCESS_DESC.equalsIgnoreCase(dbRes.getMessage());
        } else {
            PanValidationRetriggerResponseDTO resDTO = mapFailureResponse(req, dto, panValResponseDTO);
            ResponseDataDTO dbRes = nsdlPanValidationRedemptionOnMaturityDAO.apiResponse(resDTO,userName,solId);
            failedRecords.add(dto.getPan());
            return false;
        }
    }
    private PanValidationRetriggerResponseDTO mapSuccessResponse(
            NsdlPanValidationRedemptionOnMaturityReqAndResDTO req,
            PanDetailsDTO dto,
            PanValResponseDTO panValResponseDTO) {
        PanValidationRetriggerResponseDTO resDTO = new PanValidationRetriggerResponseDTO();
        resDTO.setSchemeCode(req.getSchemeCode());
        resDTO.setType(req.getType());
        resDTO.setFileMode(req.getFileType());
        resDTO.setPanNumber(dto.getPan());
        // API returned values
        resDTO.setNameStatus(null!=panValResponseDTO.getName()?panValResponseDTO.getName():"N");
        resDTO.setPanStatus(null!=panValResponseDTO.getPanStatus()?panValResponseDTO.getPanStatus():"N");
        resDTO.setDobStatus(null!=panValResponseDTO.getDob()?panValResponseDTO.getDob():"N");
        resDTO.setPanStatusDescription(panValResponseDTO.getPanStatusDescription());
        resDTO.setPanNameDescription(panValResponseDTO.getPanNameDescription());
        resDTO.setFatherName(panValResponseDTO.getFatherName());
        resDTO.setPanDobDescription(panValResponseDTO.getPanDobDescription());
        resDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
        resDTO.setReturnCode(panValResponseDTO.getReturnCode());
        resDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
        resDTO.setSeedingStatus(panValResponseDTO.getSeedingStatus());
        resDTO.setSeedingStatusDescription(panValResponseDTO.getSeedingStatusDescription());
        // Process status for success
        setProcessStatus(resDTO);
        return resDTO;
    }

    private PanValidationRetriggerResponseDTO mapFailureResponse(
            NsdlPanValidationRedemptionOnMaturityReqAndResDTO req,
            PanDetailsDTO dto,
            PanValResponseDTO panValResponseDTO) {
        PanValidationRetriggerResponseDTO resDTO = new PanValidationRetriggerResponseDTO();
        resDTO.setSchemeCode(req.getSchemeCode());
        resDTO.setType(req.getType());
        resDTO.setFileMode(req.getFileType());
        resDTO.setPanNumber(dto.getPan());
        // Safely handle null panValResponseDTO
        if (panValResponseDTO != null) {
            resDTO.setNameStatus(null!=panValResponseDTO.getName()?panValResponseDTO.getName():"N");
            resDTO.setPanStatus(null!=panValResponseDTO.getPanStatus()?panValResponseDTO.getPanStatus():"N");
            resDTO.setDobStatus(null!=panValResponseDTO.getDob()?panValResponseDTO.getDob():"N");
            resDTO.setReturnMessage(panValResponseDTO.getReturnMessage() != null ? panValResponseDTO.getReturnMessage() : "");
            resDTO.setReturnCode(panValResponseDTO.getReturnCode() != null ? panValResponseDTO.getReturnCode() : "");
            resDTO.setReturnDescription(panValResponseDTO.getReturnDescription() != null ? panValResponseDTO.getReturnDescription() : "");
        } else {
            resDTO.setReturnMessage("Null response from external API");
            resDTO.setReturnCode("99"); // custom error code
            resDTO.setReturnDescription("Failure");
        }
        // Process status for failure
        resDTO.setProcessStatus("M");
        return resDTO;
    }

    @Override
    public ResponseDataDTO upload(MultipartFile multipartFile, NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        try {
            if (null != multipartFile) {
                String filename = multipartFile.getOriginalFilename();
                byte[] data = multipartFile.getBytes();
                if (null != dto && null != dto.getFileType() && dto.getFileType().equals("P002")) {
                    responseDataDTO = parseFile(multipartFile);
                    if(null!=responseDataDTO&&null!=responseDataDTO.getMessage()
                            &&(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.HEADER_VALIDATION_FAILURE) ||
                            String.valueOf(responseDataDTO.getResult()).equalsIgnoreCase("Validation Failed"))){
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }else{
                        if(null!=responseDataDTO&&null!=responseDataDTO.getResult()) {
                            dto.setIncomeTaxStatusList((List<IncomeTaxPanStatusDTO>) responseDataDTO.getResult());
                            dto.setFileName(filename);
                            dto.setFile(data);
                        }
                    }
                } else if (null != dto && null != dto.getFileType() && dto.getFileType().equals("P001")) {
                    responseDataDTO = parseFile1(multipartFile);
                    if(null!=responseDataDTO&&null!=responseDataDTO.getMessage()
                            &&(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstants.HEADER_VALIDATION_FAILURE) ||
                            String.valueOf(responseDataDTO.getResult()).equalsIgnoreCase("Validation Failed"))){
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }else {
                        if(null!=responseDataDTO&&null!=responseDataDTO.getResult()) {
                            dto.setNsdlPanStatusList((List<NsdlPanStatusDTO>) responseDataDTO.getResult());
                            dto.setFileName(filename);
                            dto.setFile(data);
                        }
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
            LOGGER.info("File not in expected format. Validation errors: {}", e.toString(), e);
            responseDataDTO.setMessage("File Validation Error ");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return nsdlPanValidationRedemptionOnMaturityDAO.upload(dto);
    }

    public ResponseDataDTO parseFile(MultipartFile file) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        List<IncomeTaxPanStatusDTO> panDetailsList = new ArrayList<>();
        try (InputStream inp = file.getInputStream();XSSFWorkbook workbook=new XSSFWorkbook(inp)) {
            String[] expectedHeaders = {"PAN_NO", "ITR_FLAG_STATUS", "INCOME_TAX_DATE"};
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final Sheet sheet = workbook.getSheetAt(sheetIndex);
                if (!validateHeaders(sheet, expectedHeaders)) {
                    LOGGER.error("Header validation failed for sheet: {}",sheet.getSheetName());
                    responseDataDTO.setMessage("Header validation failed");
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
                List<IncomeTaxPanStatusDTO> nonEmptyRows = entityList.stream()
                        .filter(row -> !isRowEmpty(row))
                        .collect(Collectors.toList());
                if (nonEmptyRows == null || nonEmptyRows.isEmpty()) {
                    LOGGER.info("No data available for the specified file");
                    responseDataDTO.setMessage("No data available for the specified file");
                    responseDataDTO.setResult("Validation Failed");
                    return responseDataDTO;
                }
                String errorMessage = utlity.processData(nonEmptyRows);
                if(!errorMessage.equals("NR")){
                    LOGGER.info("validation failed: {}",errorMessage);
                    responseDataDTO.setMessage(errorMessage);
                    responseDataDTO.setResult("Validation Failed");
                    return responseDataDTO;
                }
                if (CollectionUtils.isNotEmpty(nonEmptyRows))
                    panDetailsList.addAll(nonEmptyRows);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            responseDataDTO.setMessage(e.getMessage());
            return responseDataDTO;
        }
        responseDataDTO.setResult(this.buildResultList(panDetailsList));
        return responseDataDTO;
    }
    private byte[] bulkStatementNsdlExcel(ResponseDataDTO responseDataDTO) {
        List<NsdlPanStatusDTO> list = (List<NsdlPanStatusDTO>) responseDataDTO.getResult();
        ByteArrayOutputStream byteArrayOutputStream = null;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook(100)) {
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
                LOGGER.info("NSDL PAN REDEMPTION OBJECT HAVING EMPTY DATA ");
            }
            LOGGER.info("NSDL PAN REDEMPTION DATA GENERATION COMPLETED...");
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Exception while writing the Excel file: {}",e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
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
            LOGGER.info("INCOMETAX PAN OBJECT HAVING EMPTY DATA ");
            }
            LOGGER.info("INCOMETAX PAN DATA GENERATION COMPLETED...");
            // Set column widths
            for (int i = 0; i < maxColumnWidths.length; i++) {
                sheet.setColumnWidth(i, maxColumnWidths[i] * 256);
            }
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Exception while writing the Excel file: {}",e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
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
            LOGGER.error("Exception while writing the Excel file: {}",e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
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
        try (InputStream inp = file.getInputStream();
             XSSFWorkbook workbook=new XSSFWorkbook(inp)) {
            String[] expectedHeaders = {"PAN","PAN_STATUS","PAN_AS_PER_NSDL","NAME","NAME_STATUS",
                    "NAME_AS_PER_NSDL","DOB","DOB_STATUS","DOB_AS_PER_NSDL","REMARKS"};
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final Sheet sheet = workbook.getSheetAt(sheetIndex);
                if (!validateHeaders(sheet, expectedHeaders)) {
                    LOGGER.error("Header validation failed for sheet: {}" , sheet.getSheetName());
                    responseDataDTO.setMessage("Header validation failed");
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
                        .filter(row -> !isRowEmpty1(row))
                        .collect(Collectors.toList());
                if (nonEmptyRows == null || nonEmptyRows.isEmpty()) {
                    LOGGER.info("No data available for the specified file");
                    responseDataDTO.setMessage("No data available for the specified file");
                    responseDataDTO.setResult("Validation Failed");
                    return responseDataDTO;
                }
                String errorMessage = utlity.processDataNsdl(nonEmptyRows);
                if(!errorMessage.equals("NR")){
                    LOGGER.info("validation failed: {}",errorMessage);
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

    public List<NsdlPanStatusDTO> buildResultList1(
             final List<NsdlPanStatusDTO> nsdlPanStatusDTO) throws DuplicateRecordException {
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

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    private boolean isRowEmpty1(NsdlPanStatusDTO row) {
        return isNullOrEmpty(row.getPan()) &&
                isNullOrEmpty(row.getName()) &&
                isNullOrEmpty(row.getDob()) &&
                isNullOrEmpty(row.getNameStatus()) &&
                isNullOrEmpty(row.getPanStatus()) &&
                isNullOrEmpty(row.getDobStatus()) &&
                isNullOrEmpty(row.getDobAsPerNsdl()) &&
                isNullOrEmpty(row.getPanAsPerNsdl()) &&
                isNullOrEmpty(row.getRemarks()) &&
                isNullOrEmpty(row.getNameAsPerNsdl());
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
                if (serialNumber > 0) {
                    Date javaDate = DateUtil.getJavaDate(serialNumber);
                    formattedDate = inputFormat.format(javaDate);
                }
            } else {
                try {
                    Date date = inputFormat.parse(row.getIncomeTaxDate());
                    formattedDate = inputFormat.format(date);
                } catch (ParseException e) {
                    LOGGER.info("{}: {}", CommonConstants.INVALID_DATE_FORMAT, row.getIncomeTaxDate());
                }
            }
            panDetails.setIncomeTaxDate(formattedDate);
        }
        return panDetails;
    }

    private NsdlPanStatusDTO mapRowToPanDetails1(NsdlPanStatusDTO row) {
        NsdlPanStatusDTO panDetails = new NsdlPanStatusDTO();
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy");
        panDetails.setPan(row.getPan());
        panDetails.setPanStatus(row.getPanStatus());
        panDetails.setPanAsPerNsdl(row.getPanAsPerNsdl());
        panDetails.setName(row.getName());
        panDetails.setNameStatus(row.getNameStatus());
        panDetails.setNameAsPerNsdl(row.getNameAsPerNsdl());
        if (null != row.getDob()) {
            String formattedDate = null;
            if (isNumeric(row.getDob())) {
                double serialNumber = Double.parseDouble(row.getDob());
                if (serialNumber > 0) {
                    Date javaDate = DateUtil.getJavaDate(serialNumber);
                    formattedDate = inputFormat.format(javaDate);
                }
            } else {
                try {
                    Date date = inputFormat.parse(row.getDob());
                    formattedDate = inputFormat.format(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                    LOGGER.info("Invalid Date Format: {}" , row.getDob());
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
                if(serialNumber > 0) {
                    Date javaDate = DateUtil.getJavaDate(serialNumber);
                    nsdlDate = inputFormat.format(javaDate);
                }
            } else {
                try {
                    Date date = inputFormat.parse(row.getDobAsPerNsdl());
                    nsdlDate = inputFormat.format(date);
                }catch (ParseException e) {
                    LOGGER.info("Invalid Date Format: {}" , row.getDobAsPerNsdl());
                    e.printStackTrace();
                }
            }
            panDetails.setDobAsPerNsdl(nsdlDate);
        }else {
            panDetails.setDobAsPerNsdl(row.getDobAsPerNsdl());
        }
        panDetails.setRemarks(row.getRemarks());
        return panDetails;
    }
    @Override
    public ResponseDataDTO getUploadModuleName(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityDAO.upload(dto);
    }
    @Override
    public ResponseDataDTO delete(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityDAO.delete(dto);
    }
    @Override
    public ResponseDataDTO download(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        ResponseDataDTO responseDataDTO=nsdlPanValidationRedemptionOnMaturityDAO.download(dto);
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO=new CashFileUploadVerifySelectRequestDTO();
        try {
            if(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                if (null != dto &&null != dto.getFileType()&&dto.getFileType().equalsIgnoreCase("P001")) {
                    cashFileUploadVerifySelectRequestDTO.setFile(bulkStatementNsdlExcel(responseDataDTO));
                    cashFileUploadVerifySelectRequestDTO.setFileName("NSDL_PAN_STATUS_REDEMPTION.xlsx");
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO);
                } else if (null != dto &&null != dto.getFileType()&&dto.getFileType().equalsIgnoreCase("P002")) {
                    cashFileUploadVerifySelectRequestDTO.setFile(bulkStatementIncomeTaxExcel(responseDataDTO));
                    cashFileUploadVerifySelectRequestDTO.setFileName("INCOME_TAX_PAN_STATUS_REDEMPTION.xlsx");
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
    public ResponseDataDTO verify(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        return nsdlPanValidationRedemptionOnMaturityDAO.verify(dto);
    }

    @Override
    public ResponseDataDTO verifyDownload(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        ResponseDataDTO responseDataDTO = nsdlPanValidationRedemptionOnMaturityDAO.verifyDownload(dto);
        CashFileUploadVerifySelectRequestDTO cashFileUploadVerifySelectRequestDTO=new CashFileUploadVerifySelectRequestDTO();
        try {
            if(responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                if (null != dto &&null != dto.getFileType()&&dto.getFileType().equalsIgnoreCase("P001")) {
                    cashFileUploadVerifySelectRequestDTO.setFile(bulkStatementNsdlExcel(responseDataDTO));
                    cashFileUploadVerifySelectRequestDTO.setFileName("NSDL_PAN_STATUS_REDEMPTION_VERIFY.xlsx");
                    responseDataDTO.setResult(cashFileUploadVerifySelectRequestDTO);
                } else if (null != dto &&null != dto.getFileType()&&dto.getFileType().equalsIgnoreCase("P002")) {
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
    public ResponseDataDTO inquiry(NsdlPanValidationRedemptionOnMaturityReqAndResDTO dto) {
        if (null == dto.getSchemeCode() && null == dto.getFileStatus()) {
            return nsdlPanValidationRedemptionOnMaturityDAO.inquiryDetails(dto);
        }
        return nsdlPanValidationRedemptionOnMaturityDAO.inquiry(dto);
    }
}
