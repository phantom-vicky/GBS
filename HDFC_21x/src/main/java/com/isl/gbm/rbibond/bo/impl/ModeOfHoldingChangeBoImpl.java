package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.cbs.integration.dto.CustomerDetails;
import com.isl.gbm.cbs.integration.manager.BCIFApiManager;
import com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService;
import com.isl.gbm.cbs.integration.service.CustomerDetailsIntegrationService;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.ModeOfHoldingChangeBo;
import com.isl.gbm.rbibond.dao.ModeOfHoldingChangeDao;
import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import com.isl.gbm.rbibond.dto.ModeOfHoldingChangeDTO;
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

import java.util.HashMap;
import java.util.Map;


@Component
public class ModeOfHoldingChangeBoImpl implements ModeOfHoldingChangeBo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModeOfHoldingChangeBoImpl.class);

    @Autowired
    private ModeOfHoldingChangeDao modeOfHoldingChangeDao;

    @Autowired
    private CustomerDetailsIntegrationService customerDetailsIntegrationService;
    @Autowired
    private AccountDetailsIntegrationService accountDetailsIntegrationService;
    @Autowired
    ExternalService externalService;
    @Autowired
    private BCIFApiManager bcifApiManager;
    @Value("${profile.type}")
    private String stubProcess;

    @Autowired
    private CommonConstants commonConstants;

    @Override
    public ResponseDataDTO getBcifApi(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            CustomerDetailsValidatorDTO customerDetailsValidatorDTO =customerDetailsIntegrationService.getCustomerDetails(modeOfHoldingChangeDTO.getCustId(),modeOfHoldingChangeDTO.getOperativeAccNo());

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
                map.put("operativeAccNo", modeOfHoldingChangeDTO.getOperativeAccNo());
                map.put("operativeBranchId", modeOfHoldingChangeDTO.getOperativeAccBranchId());

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
    public ResponseDataDTO getMinorDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getMinorDetails(modeOfHoldingChangeDTO);
    }
   @Override
    public ResponseDataDTO validatePan(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
       ResponseDataDTO responseDataDTO2=new ResponseDataDTO();
       ResponseDataDTO responseDataDTO = modeOfHoldingChangeDao.validatePanRequest(modeOfHoldingChangeDTO);
       PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();
       responseDataDTO2.setStatusCode(responseDataDTO.getStatusCode());
       responseDataDTO2.setMessage(responseDataDTO.getMessage());
       responseDataDTO2.setResult(responseDataDTO.getResult());
        try{
            if (responseDataDTO.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                PanDetailsDTO dto = (PanDetailsDTO) responseDataDTO.getResult();
                if ("TEST".equalsIgnoreCase(stubProcess)) {
                        panValidationRetriggerResponseDTO.setSchemeCode(modeOfHoldingChangeDTO.getSchemeCode());
                        panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
                        panValidationRetriggerResponseDTO.setNameStatus("Y");
                        panValidationRetriggerResponseDTO.setPanStatus("E");
                        panValidationRetriggerResponseDTO.setDobStatus("Y");
                        panValidationRetriggerResponseDTO.setProcessStatus("Y");
                        panValidationRetriggerResponseDTO.setItrFlag("E");
                        responseDataDTO2 = modeOfHoldingChangeDao.apiResponse(panValidationRetriggerResponseDTO);
                        if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                            LOGGER.info("{}{}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                            responseDataDTO2.setResult(panValidationRetriggerResponseDTO);
                        } else {
                            LOGGER.info("{}{}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                            responseDataDTO2.setStatusCode("0");
                            responseDataDTO2.setResult(panValidationRetriggerResponseDTO);
                        }
                } else {
                        ResponseDataDTO responseDataDTO1 = externalService.panValidation(dto);
                        PanValResponseDTO panValResponseDTO = (PanValResponseDTO) responseDataDTO1.getResult();
                        if(null!=panValResponseDTO&& "1".equals(panValResponseDTO.getReturnCode())&&
                               null!=panValResponseDTO.getReturnDescription()&& "Success".equalsIgnoreCase(panValResponseDTO.getReturnDescription())) {
                            panValidationRetriggerResponseDTO.setSchemeCode(modeOfHoldingChangeDTO.getSchemeCode());
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
                            responseDataDTO2 = modeOfHoldingChangeDao.apiResponse(panValidationRetriggerResponseDTO);
                            if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                LOGGER.info("{}{}", CommonConstants.DB_STATUS, responseDataDTO2.getMessage());
                            } else {
                                LOGGER.info("{}: {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                            }
                        } else {
                            LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                            panValidationRetriggerResponseDTO.setSchemeCode(modeOfHoldingChangeDTO.getSchemeCode());
                            panValidationRetriggerResponseDTO.setPanNumber(dto.getPan());
                            panValidationRetriggerResponseDTO.setReturnMessage(panValResponseDTO.getReturnMessage());
                            panValidationRetriggerResponseDTO.setReturnCode(panValResponseDTO.getReturnCode());
                            panValidationRetriggerResponseDTO.setReturnDescription(panValResponseDTO.getReturnDescription());
                            panValidationRetriggerResponseDTO.setProcessStatus("M");
                            ResponseDataDTO res = modeOfHoldingChangeDao.apiResponse(panValidationRetriggerResponseDTO);
                            if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                                LOGGER.info("External DB update Status: {}", res.getMessage());
                            } else {
                                LOGGER.error("External DB update Failure: {}", res.getMessage());
                            }
                            LOGGER.info("{}: {}", CommonConstants.PAN_VALID_FAILURE, panValResponseDTO);
                            responseDataDTO2.setStatusCode("0");
                            responseDataDTO2.setMessage(commonConstants.PAN_VALID_FAILURE);
                            responseDataDTO2.setResult(panValResponseDTO);
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
    public ResponseDataDTO getWboBranchDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getWboBranchDetails(modeOfHoldingChangeDTO);
    }


    @Override
    public ResponseDataDTO getAddModuleDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getAddModuleDetails(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO add(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.add(modeOfHoldingChangeDTO);
    }
    @Override
    public ResponseDataDTO getDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getDetails(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO addSubmit(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.addSubmit(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO inquiry(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.inquiry(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.modifySelect(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO modify(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.modify(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.verifySelect(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO verify(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.verify(modeOfHoldingChangeDTO);
    }
    @Override
    public ResponseDataDTO delete(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.delete(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO blaNo(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.blaNo(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO blaTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.blaTorch(modeOfHoldingChangeDTO);
    }
    @Override
    public ResponseDataDTO getblaNoInq(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getblaNoInq(modeOfHoldingChangeDTO);
    }
    @Override
    public ResponseDataDTO getsubscriptionIdMod(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getsubscriptionIdMod(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getsubscriptionIdInq(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getsubscriptionIdInq(modeOfHoldingChangeDTO);
    }
    @Override
    public ResponseDataDTO modifyPanTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.modifyPanTorch(modeOfHoldingChangeDTO);
    }
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getRbiBondModuleNameDetails(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getBrokerTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getBrokerTorch(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getGuardianDetails(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getGuardianDetails(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getNonHdfcTorch(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcBranchTorch(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.getNonHdfcBranchTorch(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO unlockBla(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.unlockBla(modeOfHoldingChangeDTO);
    }

    @Override
    public ResponseDataDTO fetchState(ModeOfHoldingChangeDTO modeOfHoldingChangeDTO) {
        return modeOfHoldingChangeDao.fetchState(modeOfHoldingChangeDTO);
    }
}
