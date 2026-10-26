package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.PanValidationRetriggerBO;
import com.isl.gbm.rbibond.dao.PanValidationRetriggerDAO;
import com.isl.gbm.rbibond.dto.PanValidationRequest;
import com.isl.gbm.rbibond.dto.PanValidationResponse;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerDTO;
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

@Component
public class PanValidationRetriggerBOImpl implements PanValidationRetriggerBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PanValidationRetriggerBOImpl.class);

    @Autowired
    PanValidationRetriggerDAO panValidationRetriggerDAO;
    @Value("${profile.type}")
    private String stubProcess;
    @Autowired
    ExternalService externalService;
    @Override
    public ResponseDataDTO details(PanValidationRetriggerDTO panValidationRetriggerDTO) {
        return panValidationRetriggerDAO.details(panValidationRetriggerDTO);
    }

    @Override
    public ResponseDataDTO verify(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        return panValidationRetriggerDAO.verify(panValidationRetriggerResponseDTO);
    }
    @Override
    public ResponseDataDTO validate(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        PanValidationRequest.InputData panValidationRequest=new PanValidationRequest.InputData();
        PanValidationResponse panValidationResponse=new PanValidationResponse();
        PanDetailsDTO panDetailsDTO=new PanDetailsDTO();
        try {
            panValidationRequest.setName((panValidationRetriggerResponseDTO.getNewName() != null && !panValidationRetriggerResponseDTO.getNewName().trim().isEmpty())? panValidationRetriggerResponseDTO.getNewName() : panValidationRetriggerResponseDTO.getName());
            panValidationRequest.setPan((panValidationRetriggerResponseDTO.getNewPan() != null && !panValidationRetriggerResponseDTO.getNewPan().trim().isEmpty()) ? panValidationRetriggerResponseDTO.getNewPan() : panValidationRetriggerResponseDTO.getOldPan());
            panValidationRequest.setDob((panValidationRetriggerResponseDTO.getNewDob() != null && !panValidationRetriggerResponseDTO.getNewDob().trim().isEmpty()) ? panValidationRetriggerResponseDTO.getNewDob() : panValidationRetriggerResponseDTO.getDob());
            if ("TEST".equalsIgnoreCase(stubProcess)) {
                panValidationRetriggerResponseDTO.setPanStatus("E");
                panValidationRetriggerResponseDTO.setDobStatus("Y");
                panValidationRetriggerResponseDTO.setNameStatus("Y");
                panValidationRetriggerResponseDTO.setProcessStatus("Y");
                panValidationRetriggerResponseDTO.setItrFlag("Y");
                panValidationRetriggerResponseDTO.setPanNumber(panValidationRequest.getPan());
                panValidationRetriggerResponseDTO.setName(panValidationRequest.getName());
                panValidationRetriggerResponseDTO.setDob(panValidationRequest.getDob());
                panValidationRetriggerResponseDTO.setReturnMessage("Success");
                panValidationRetriggerResponseDTO.setReturnCode("1");
                panValidationRetriggerResponseDTO.setReturnDescription("Success");
                panValidationRetriggerResponseDTO.setSeedingStatusDescription("Operative PAN");
                panValidationRetriggerResponseDTO.setSeedingStatus("Y");
                panValidationRetriggerResponseDTO.setPanStatusDescription("Existing and Valid");
                panValidationRetriggerResponseDTO.setPanDobDescription("Matched with Database");
                panValidationRetriggerResponseDTO.setPanNameDescription("Matched with Database");
                panValidationResponse.setResponse_Code("200");
                if (panValidationResponse.getResponse_Code().equalsIgnoreCase("200")) {
                    responseDataDTO.setResult(panValidationRetriggerResponseDTO);
                    responseDataDTO.setMessage(CommonConstant.SUCCESS_DESC);
                }
            }else{
                panDetailsDTO.setPan(panValidationRequest.getPan());
                panDetailsDTO.setName(panValidationRequest.getName());
                panDetailsDTO.setDob(panValidationRequest.getDob());
                ResponseDataDTO responseDataDTO1 = externalService.panValidation(panDetailsDTO);
                PanValResponseDTO panValResponseDTO = (PanValResponseDTO) responseDataDTO1.getResult();
                if(null!=panValResponseDTO&& null!=panValResponseDTO.getReturnCode()&&panValResponseDTO.getReturnCode().equals("1")&&null!=panValResponseDTO.getReturnDescription()&&panValResponseDTO.getReturnDescription().equalsIgnoreCase("Success")) {
                    panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                    panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                    panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                    panValidationRetriggerResponseDTO.setPanNumber(panValidationRequest.getPan());
                    if ("E".equalsIgnoreCase(panValResponseDTO.getPanStatus()) &&
                            "Y".equalsIgnoreCase(panValResponseDTO.getName()) &&
                            "Y".equalsIgnoreCase(panValResponseDTO.getDob())) {
                        panValidationRetriggerResponseDTO.setProcessStatus("Y");
                    } else {
                        panValidationRetriggerResponseDTO.setProcessStatus("M");
                    }
                    panValidationRetriggerResponseDTO.setItrFlag(panValResponseDTO.getSeedingStatus());
                    panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage() != null ? panValResponseDTO.getReturnMessage() : null);
                    panValidationRetriggerResponseDTO.setReturnCode( panValResponseDTO.getReturnCode() != null ? panValResponseDTO.getReturnCode() : null);
                    panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription() != null ? panValResponseDTO.getReturnDescription() : null);
                    panValidationRetriggerResponseDTO.setSeedingStatusDescription(panValResponseDTO.getSeedingStatusDescription() != null ? panValResponseDTO.getSeedingStatusDescription() : null);
                    panValidationRetriggerResponseDTO.setSeedingStatus(panValResponseDTO.getSeedingStatus() != null ? panValResponseDTO.getSeedingStatus() : null);
                    panValidationRetriggerResponseDTO.setPanStatusDescription(panValResponseDTO.getPanStatusDescription() != null ? panValResponseDTO.getPanStatusDescription() : null);
                    panValidationRetriggerResponseDTO.setPanDobDescription(panValResponseDTO.getPanDobDescription() != null ? panValResponseDTO.getPanDobDescription() : null);
                    panValidationRetriggerResponseDTO.setPanNameDescription(panValResponseDTO.getPanNameDescription() != null ? panValResponseDTO.getPanNameDescription() : null);
                    responseDataDTO.setResult(panValidationRetriggerResponseDTO);
                    responseDataDTO.setMessage(CommonConstant.SUCCESS_DESC);
                }else{
                    panValidationRetriggerResponseDTO.setNameStatus((null!=panValResponseDTO&&panValResponseDTO.getName() != null) ? panValResponseDTO.getName() : null);
                    panValidationRetriggerResponseDTO.setPanStatus((null!=panValResponseDTO&&panValResponseDTO.getPanStatus() != null) ? panValResponseDTO.getPanStatus() : null);
                    panValidationRetriggerResponseDTO.setDobStatus((null!=panValResponseDTO&&panValResponseDTO.getDob() != null) ? panValResponseDTO.getDob() : null);
                    if ("E".equalsIgnoreCase(panValResponseDTO.getPanStatus()) &&
                            "Y".equalsIgnoreCase(panValResponseDTO.getName()) &&
                            "Y".equalsIgnoreCase(panValResponseDTO.getDob())) {
                        panValidationRetriggerResponseDTO.setProcessStatus("Y");
                    } else {
                        panValidationRetriggerResponseDTO.setProcessStatus("M");
                    }
                    panValidationRetriggerResponseDTO.setPanNumber(panValidationRequest.getPan());
                    panValidationRetriggerResponseDTO.setItrFlag(panValResponseDTO.getSeedingStatus());
                    panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage() != null ? panValResponseDTO.getReturnMessage() : null);
                    panValidationRetriggerResponseDTO.setReturnCode( panValResponseDTO.getReturnCode() != null ? panValResponseDTO.getReturnCode() : null);
                    panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription() != null ? panValResponseDTO.getReturnDescription() : null);
                    panValidationRetriggerResponseDTO.setSeedingStatusDescription(panValResponseDTO.getSeedingStatusDescription() != null ? panValResponseDTO.getSeedingStatusDescription() : null);
                    panValidationRetriggerResponseDTO.setSeedingStatus(panValResponseDTO.getSeedingStatus() != null ? panValResponseDTO.getSeedingStatus() : null);
                    panValidationRetriggerResponseDTO.setPanStatusDescription(panValResponseDTO.getPanStatusDescription() != null ? panValResponseDTO.getPanStatusDescription() : null);
                    panValidationRetriggerResponseDTO.setPanDobDescription(panValResponseDTO.getPanDobDescription() != null ? panValResponseDTO.getPanDobDescription() : null);
                    panValidationRetriggerResponseDTO.setPanNameDescription(panValResponseDTO.getPanNameDescription() != null ? panValResponseDTO.getPanNameDescription() : null);
                    responseDataDTO.setResult(panValidationRetriggerResponseDTO);
                    LOGGER.info("DATABASE STATUS: {}",responseDataDTO.getMessage());
                    responseDataDTO.setMessage(CommonConstant.SUCCESS_DESC);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseDataDTO;
    }
    @Override
    public ResponseDataDTO validatePanSchedulerRequest() {
        return panValidationRetriggerDAO.validatePanSchedulerRequest();
    }

    @Override
    public ResponseDataDTO validatePanSchedulerResponse(PanValidationRetriggerResponseDTO response) {
        return panValidationRetriggerDAO.validatePanSchedulerResponse(response);
    }
}
