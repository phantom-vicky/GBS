
package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.common.util.TransactionValidator;
import com.isl.gbm.rbibond.bo.RbiBondTransferInBo;
import com.isl.gbm.rbibond.dao.RbiBondTransferInDao;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.dto.RbiBondTransferInDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanValResponseDTO;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.user.isac.common.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class RbiBondTransferInBoImpl implements RbiBondTransferInBo {

    private static final Logger LOGGER = LoggerFactory.getLogger(RbiBondTransferInBoImpl.class);

    @Autowired
    RbiBondTransferInDao rbiBondTransferInDao;


    @Autowired
    private TransactionValidator transactionValidator;

    @Autowired
    ExternalService externalService;

    @Override
    public ResponseDataDTO getblaNoInq(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInDao.getblaNoInq(rbiBondTransferInDTO);
    }
    @Override
    public ResponseDataDTO getsubscriptionIdInq(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInDao.getsubscriptionIdInq(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO panTorch(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInDao.panTorch(rbiBondTransferInDTO);
    }
    @Override
    public ResponseDataDTO addTorch(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInDao.addTorch(rbiBondTransferInDTO);
    }


    public ResponseDataDTO otherBankDetails() throws SQLException {
        return rbiBondTransferInDao.otherBankDetails();

    }

    @Override
    public ResponseDataDTO bondTransferInAdd(RbiBondTransferInDTO rbiBondTransferInAddDto) {
        return rbiBondTransferInDao.bondTransferInAdd(rbiBondTransferInAddDto);
    }
    @Override
    public ResponseDataDTO verifySelect(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInDao.verifySelect(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInDao.modifySelect(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO modifyPAN(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInDao.modifyPAN(rbiBondTransferInDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInDao.inquiry(rbiBondTransferInDTO);
    }


    @Override
    public ResponseDataDTO delete(RbiBondTransferInDTO rbiBondTransferInDTO) {

            return rbiBondTransferInDao.delete(rbiBondTransferInDTO);


    }

    @Override
    public ResponseDataDTO modify(RbiBondTransferInDTO rbiBondTransferInDTO) {

            return rbiBondTransferInDao.modify(rbiBondTransferInDTO);

    }

    @Override
    public ResponseDataDTO getGuardianDetails(RbiBondTransferInDTO rbiBondTransferInDTO) {

        return rbiBondTransferInDao.getGuardianDetails(rbiBondTransferInDTO);

    }

    @Override
    public ResponseDataDTO verify(RbiBondTransferInDTO rbiBondTransferInDTO) {


        return rbiBondTransferInDao.verify(rbiBondTransferInDTO);


    }

    @Override
    public ResponseDataDTO validatePan(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO2=new ResponseDataDTO();
        ResponseDataDTO responseDataDTO = rbiBondTransferInDao.validatePanRequest(bondLedgerAccountMaintenanceDTO);
        responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<PanValidationRetriggerResponseDTO> successList = new ArrayList<>();
        List<PanValidationRetriggerResponseDTO> failureList = new ArrayList<>();

        responseDataDTO2.setMessage(responseDataDTO.getMessage());
        responseDataDTO2.setResult(responseDataDTO.getResult());
        try{
            if (responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                for (int i = 0; i < bondLedgerAccountMaintenanceDTO.getPanValidation().size(); i++) {
                    PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO(); // ✅ Create a new instance inside the loop

                    PanDetailsDTO panDetailsDTO = new PanDetailsDTO();

                    String dob = bondLedgerAccountMaintenanceDTO.getPanValidation().get(i).getDob();

                    try {
                        dob = outputFormat.format(inputFormat.parse(dob)); // Convert and reassign
                    } catch (Exception e) {
                        e.printStackTrace(); // Handle errors
                    }
                    panDetailsDTO.setDob(dob);
                    panDetailsDTO.setPan(bondLedgerAccountMaintenanceDTO.getPanValidation().get(i).getPan());
                    panDetailsDTO.setName(bondLedgerAccountMaintenanceDTO.getPanValidation().get(i).getBondHolderName());
                    ResponseDataDTO panValidationResponse = new ResponseDataDTO();

                    if(bondLedgerAccountMaintenanceDTO.getPanValidation().get(i).getPan().equalsIgnoreCase("KJDPS0987U")){
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
                        panValidationRetriggerResponseDTO.setSchemeCode(bondLedgerAccountMaintenanceDTO.getSchemeCode());
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
                        responseDataDTO2 = rbiBondTransferInDao.apiResponse(panValidationRetriggerResponseDTO);
                        if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                            LOGGER.info("{}: {}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                            successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                        } else {
                            LOGGER.info("{}: {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                            failureList.add(panValidationRetriggerResponseDTO); // ❌ Store failure PANs

                        }
                    } else {
                        LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                        panValidationRetriggerResponseDTO.setSchemeCode(bondLedgerAccountMaintenanceDTO.getSchemeCode());
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
                            successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                        }
                        else{panValidationRetriggerResponseDTO.setProcessStatus("M");
                            failureList.add(panValidationRetriggerResponseDTO); // ❌ Store failure PANs
                        }
                        ResponseDataDTO res = rbiBondTransferInDao.apiResponse(panValidationRetriggerResponseDTO);
                        if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                            LOGGER.info("External DB update Status: {}", res.getMessage());
                        } else {
                            LOGGER.error("External DB update Failure: {}", res.getMessage());
                        }
                        LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                        responseDataDTO2.setMessage(CommonConstant.SUCCESS_DESC);
                        responseDataDTO2.setResult(panValidationRetriggerResponseDTO);
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



}