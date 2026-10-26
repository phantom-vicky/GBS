package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.cbs.integration.dto.CustomerDetails;
import com.isl.gbm.cbs.integration.service.CustomerDetailsIntegrationService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.HufTransferToSingleAndJointBo;
import com.isl.gbm.rbibond.dao.HufTransferToSingleAndJointDao;
import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import com.isl.gbm.rbibond.dto.HufTransferSingleAndJointDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanValResponseDTO;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.user.isac.common.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class HufTransferToSingleAndJointBoImpl implements HufTransferToSingleAndJointBo {

    private static final Logger LOGGER = LoggerFactory.getLogger(HufTransferToSingleAndJointBoImpl.class);

    @Autowired
    private HufTransferToSingleAndJointDao hufTransferToSingleAndJointDao;

    @Autowired
    private CustomerDetailsIntegrationService customerDetailsIntegrationService;

    @Autowired
    ExternalService externalService;
    @Override
    public ResponseDataDTO getBcifApi(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            CustomerDetailsValidatorDTO customerDetailsValidatorDTO =customerDetailsIntegrationService.getCustomerDetails(hufTransferSingleAndJointDTO.getCustId(),hufTransferSingleAndJointDTO.getOperativeAccNo());

            if (customerDetailsValidatorDTO.getStatus().equalsIgnoreCase(CommonConstants.SUCCESS)) {

                CustomerDetails customerDetails = customerDetailsValidatorDTO.getCustomerDetails();
                responseDataDTO.setMessage(CommonConstants.SUCCESS_CONST);
                Map<String, String> map = new HashMap<>();
                map.put("addressOne", customerDetails.getAddressLine1());
                map.put("addressTwo", customerDetails.getAddressLine2());
                map.put("addressThree", customerDetails.getAddressLine3());
                map.put("city", customerDetails.getCity());
                map.put("pinCode", customerDetails.getZipCode());
                map.put("phoneNumber", customerDetails.getPhoneNumber());
                map.put("mobileNumber", customerDetails.getMobileNumber());
                map.put("email", customerDetails.getEmail());
                map.put("operativeAccNo", hufTransferSingleAndJointDTO.getOperativeAccNo());
                map.put("operativeBranchId", hufTransferSingleAndJointDTO.getOperativeAccBranchId());

                responseDataDTO.setResult(map);
            } else {
                responseDataDTO.setMessage(customerDetailsValidatorDTO.getReason());
            }
        } catch (Exception e) {
            responseDataDTO.setMessage("Error occucured while Fetching the details");
            e.printStackTrace();
        }
        return responseDataDTO;
    }

    @Override
    public ResponseDataDTO getMinorDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getMinorDetails(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getWboBranchDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getWboBranchDetails(hufTransferSingleAndJointDTO);
    }


    @Override
    public ResponseDataDTO getModuleNameAdd(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getModuleNameAdd(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO add(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.add(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO validatePan(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        ResponseDataDTO responseDataDTO2=new ResponseDataDTO();
        ResponseDataDTO responseDataDTO = hufTransferToSingleAndJointDao.validatePanRequest(hufTransferSingleAndJointDTO);
        responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<PanValidationRetriggerResponseDTO> successList = new ArrayList<>();
        List<PanValidationRetriggerResponseDTO> failureList = new ArrayList<>();

        responseDataDTO2.setMessage(responseDataDTO.getMessage());
        responseDataDTO2.setResult(responseDataDTO.getResult());
        try{
            if (responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                for (int i = 0; i < hufTransferSingleAndJointDTO.getPanValidation().size(); i++) {
                    PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO(); // ✅ Create a new instance inside the loop

                    PanDetailsDTO panDetailsDTO = new PanDetailsDTO();

                    String dob = hufTransferSingleAndJointDTO.getPanValidation().get(i).getDob();

                    try {
                        if(null!=dob) {
                            dob = outputFormat.format(inputFormat.parse(dob)); // Convert and reassign
                            panDetailsDTO.setDob(dob);
                        }
                    } catch (Exception e) {
                        e.printStackTrace(); // Handle errors
                    }
                    panDetailsDTO.setPan(hufTransferSingleAndJointDTO.getPanValidation().get(i).getPan());
                    panDetailsDTO.setName(hufTransferSingleAndJointDTO.getPanValidation().get(i).getBondHolderName());
                    ResponseDataDTO panValidationResponse = new ResponseDataDTO();

                    if(hufTransferSingleAndJointDTO.getPanValidation().get(i).getPan().equalsIgnoreCase("KJDPS0987U")){
                        PanValResponseDTO failureResponse = new PanValResponseDTO();
                        failureResponse.setReturnCode("009");
                        failureResponse.setReturnDescription("Pan No. Validation failure");
                        failureResponse.setResponseTime("2024-01-30T13:23:11.991");
                        failureResponse.setReqNo("null");
                        failureResponse.setPan("null");
                        failureResponse.setPanStatus("null");
                        failureResponse.setPanStatusDescription("Unknown flag");
                        failureResponse.setName("null");
                        failureResponse.setPanNameDescription("null");
                        failureResponse.setFatherName("null");
                        failureResponse.setDob("null");
                        failureResponse.setPanDobDescription("null");
                        failureResponse.setSeedingStatus("null");
                        failureResponse.setSeedingStatusDescription("Unknown flag");

                        // ✅ Set the failure response in ResponseDataDTO
                        panValidationResponse.setMessage("SUCCESS");
                        panValidationResponse.setStatusCode("200");
                        panValidationResponse.setTotal(0.0);
                        panValidationResponse.setResult(failureResponse);
                        panValidationResponse.setResult1(null);
                    }else {
                        panValidationResponse = externalService.panValidation(panDetailsDTO);
                    }
                    if (panValidationResponse == null || panValidationResponse.getResult() == null) {
                        throw new IllegalStateException("PAN validation response or result cannot be null");
                    }

                    PanValResponseDTO panValResponseDTO = (PanValResponseDTO) panValidationResponse.getResult();
                    if (panValResponseDTO == null) {
                        throw new IllegalStateException("PAN validation response DTO cannot be null");
                    }

                    Map<String, String> panStatusMap = new HashMap<>(); // Map to store PAN and its status
                    panStatusMap.put("pan", panValResponseDTO.getPan()); // Store PAN number

                    if (null!=panValResponseDTO&&null!=panValResponseDTO.getReturnCode()&&panValResponseDTO.getReturnCode().equals("1") &&
                            null!=panValResponseDTO.getReturnDescription()&& panValResponseDTO.getReturnDescription().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                        panValidationRetriggerResponseDTO.setSchemeCode(hufTransferSingleAndJointDTO.getSchemeCode());
                        panValidationRetriggerResponseDTO.setPanNumber(panDetailsDTO.getPan());
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
                        if ("E".equalsIgnoreCase(panValResponseDTO.getPanStatus()) &&
                                "Y".equalsIgnoreCase(panValResponseDTO.getName()) &&
                                "Y".equalsIgnoreCase(panValResponseDTO.getDob())) {
                            panValidationRetriggerResponseDTO.setProcessStatus("Y");}
                        else{panValidationRetriggerResponseDTO.setProcessStatus("M");}
                        panValidationRetriggerResponseDTO.setItrFlag(panValResponseDTO.getSeedingStatus());
                        responseDataDTO2 = hufTransferToSingleAndJointDao.apiResponse(panValidationRetriggerResponseDTO);
                        if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                            LOGGER.info("{}: {}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                            successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                        } else {
                            LOGGER.info("{}: {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                            failureList.add(panValidationRetriggerResponseDTO); // ❌ Store failure PANs
                        }
                        responseDataDTO2.setMessage(CommonConstant.SUCCESS_DESC);
                    } else {
                        LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                        panValidationRetriggerResponseDTO.setSchemeCode(hufTransferSingleAndJointDTO.getSchemeCode());
                        panValidationRetriggerResponseDTO.setPanNumber(panDetailsDTO.getPan());
                        panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                        panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                        panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                        panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
                        panValidationRetriggerResponseDTO.setReturnCode(panValResponseDTO.getReturnCode());
                        panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
                        if ("E".equalsIgnoreCase(panValResponseDTO.getPanStatus()) &&
                                "Y".equalsIgnoreCase(panValResponseDTO.getName()) &&
                                "Y".equalsIgnoreCase(panValResponseDTO.getDob())) {
                            panValidationRetriggerResponseDTO.setProcessStatus("Y");
                        }
                        else{panValidationRetriggerResponseDTO.setProcessStatus("M");}
                        ResponseDataDTO res = hufTransferToSingleAndJointDao.apiResponse(panValidationRetriggerResponseDTO);
                        if (null!=res&&res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                            LOGGER.info("External DB update Status: {}", res.getMessage());
                            successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                        } else {
                            LOGGER.info("External DB update Failure: {}", res.getMessage());
                            failureList.add(panValidationRetriggerResponseDTO); // ❌ Store failure PANs
                        }
                        LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                        responseDataDTO2.setMessage(res.getMessage());
                    }
                }
                responseDataDTO2.setResult(successList);
                responseDataDTO2.setResult1(failureList);
            }
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            responseDataDTO2.setMessage("A null value was encountered: " + npe.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO2.setMessage("An unexpected error occurred: " + e.getMessage());
        }
        return responseDataDTO2;
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

    @Override
    public ResponseDataDTO addSubmit(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.addSubmit(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO inquiry(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.inquiry(hufTransferSingleAndJointDTO);
    }
    @Override
    public ResponseDataDTO getbranchName(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getbranchName(hufTransferSingleAndJointDTO);
    }
    @Override
    public ResponseDataDTO getbankName(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getbankName(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.verifySelect(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO verify(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.verify(hufTransferSingleAndJointDTO);
    }
    @Override
    public ResponseDataDTO delete(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.delete(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO blaNo(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.blaNo(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO blaTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.blaTorch(hufTransferSingleAndJointDTO);
    }
    @Override
    public ResponseDataDTO getblaNoInq(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getblaNoInq(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getsubscriptionIdInq(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getsubscriptionIdInq(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getBrokerTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getBrokerTorch(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getGuardianDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getGuardianDetails(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getNonHdfcTorch(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcBranchTorch(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getNonHdfcBranchTorch(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO fetchState(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.fetchState(hufTransferSingleAndJointDTO);
    }

    @Override
    public ResponseDataDTO getDetails(HufTransferSingleAndJointDTO hufTransferSingleAndJointDTO) {
        return hufTransferToSingleAndJointDao.getDetails(hufTransferSingleAndJointDTO);
    }

}
