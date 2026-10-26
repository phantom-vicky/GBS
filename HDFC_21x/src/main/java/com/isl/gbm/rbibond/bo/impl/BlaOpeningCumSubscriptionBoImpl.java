package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.rbibond.dto.CustomerDetailsValidatorDTO;
import com.isl.gbm.cbs.integration.dto.CustomerDetails;
import com.isl.gbm.cbs.integration.manager.BCIFApiManager;
import com.isl.gbm.cbs.integration.service.AccountDetailsIntegrationService;
import com.isl.gbm.cbs.integration.service.CustomerDetailsIntegrationService;
import com.isl.gbm.common.dto.ResponseDataDTO;

import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.BlaOpeningCumSubscriptionBo;

import com.isl.gbm.rbibond.dao.BlaOpeningCumSubscriptionDao;

import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;

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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class BlaOpeningCumSubscriptionBoImpl implements BlaOpeningCumSubscriptionBo {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlaOpeningCumSubscriptionBoImpl.class);

    @Autowired
    private BlaOpeningCumSubscriptionDao blaOpeningCumSubscriptionDao;

    @Autowired
    private CustomerDetailsIntegrationService customerDetailsIntegrationService;
    @Autowired
    private AccountDetailsIntegrationService accountDetailsIntegrationService;

    @Autowired
    private BCIFApiManager bcifApiManager;

    @Autowired
    private CommonConstants commonConstants;

    @Autowired
    ExternalService externalService;
    @Override
    public ResponseDataDTO getBcifApi(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();

        try {
            CustomerDetailsValidatorDTO customerDetailsValidatorDTO =customerDetailsIntegrationService.getCustomerDetails(bondLedgerAccountMaintenanceDTO.getCustId(),bondLedgerAccountMaintenanceDTO.getOperativeAccNo());

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
                map.put("operativeAccNo", bondLedgerAccountMaintenanceDTO.getOperativeAccNo());
                map.put("operativeBranchId", bondLedgerAccountMaintenanceDTO.getOperativeAccBranchId());

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
    public ResponseDataDTO getMinorDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getMinorDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getWboBranchDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getWboBranchDetails(bondLedgerAccountMaintenanceDTO);
    }


    @Override
    public ResponseDataDTO getAddDashBoard(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getAddDashBoard(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.add(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO validatePan(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO2=new ResponseDataDTO();
        ResponseDataDTO responseDataDTO = blaOpeningCumSubscriptionDao.validatePanRequest(bondLedgerAccountMaintenanceDTO);
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
                        if(null!=dob) {
                            dob = outputFormat.format(inputFormat.parse(dob)); // Convert and reassign
                            panDetailsDTO.setDob(dob);
                        }
                    } catch (Exception e) {
                        e.printStackTrace(); // Handle errors
                    }
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
                        responseDataDTO2 = blaOpeningCumSubscriptionDao.apiResponse(panValidationRetriggerResponseDTO);
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
                        }
                        else{panValidationRetriggerResponseDTO.setProcessStatus("M");}
                        ResponseDataDTO res = blaOpeningCumSubscriptionDao.apiResponse(panValidationRetriggerResponseDTO);
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
    public ResponseDataDTO addSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        ResponseDataDTO responseDataDTO = new ResponseDataDTO();
        if (bondLedgerAccountMaintenanceDTO.getInstrumentAmount() != null
                && !bondLedgerAccountMaintenanceDTO.getInstrumentAmount().trim().isEmpty()) {
            BigDecimal instrumentAmount = new BigDecimal(bondLedgerAccountMaintenanceDTO.getInstrumentAmount().trim());
            if (instrumentAmount.compareTo(BigDecimal.ZERO) < 0) {
                responseDataDTO.setMessage("Instrument amount of income Should Not Be Negative");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        if (bondLedgerAccountMaintenanceDTO.getSubscriptionAmount() != null
                && !bondLedgerAccountMaintenanceDTO.getSubscriptionAmount().trim().isEmpty()) {
            BigDecimal subscriptionAmount = new BigDecimal(bondLedgerAccountMaintenanceDTO.getSubscriptionAmount().trim());
            if (subscriptionAmount.compareTo(BigDecimal.ZERO) < 0) {
                responseDataDTO.setMessage("Subscription Amount Should Not Be Negative");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        if (bondLedgerAccountMaintenanceDTO.getRateOfInterest() != null
                && !bondLedgerAccountMaintenanceDTO.getRateOfInterest().trim().isEmpty()) {
            BigDecimal rateOfInterest = new BigDecimal(bondLedgerAccountMaintenanceDTO.getRateOfInterest().trim());
            if (rateOfInterest.compareTo(BigDecimal.ZERO) < 0) {
                responseDataDTO.setMessage("Rate of Interest Should Not Be Negative");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
/*        BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO1=(BondLedgerAccountMaintenanceDTO) blaOpeningCumSubscriptionDao.add(bondLedgerAccountMaintenanceDTO).getResult();
        BigDecimal subscriptionAmount = new BigDecimal(
                bondLedgerAccountMaintenanceDTO.getSubscriptionAmount().trim()
        );

        BigDecimal minCashLimit = new BigDecimal(
                bondLedgerAccountMaintenanceDTO1.getMinCashLimit().trim()
        );
        if (subscriptionAmount.compareTo(minCashLimit) < 0) {
            if(bondLedgerAccountMaintenanceDTO1.getTranMode().equalsIgnoreCase("C")) {
                responseDataDTO.setMessage("Subscription Amount must be between " + bondLedgerAccountMaintenanceDTO1.getMinCashLimit() + " and " + bondLedgerAccountMaintenanceDTO1.getCashLimitForCash());
            }else {
                responseDataDTO.setMessage("Subscription Amount must be between " + bondLedgerAccountMaintenanceDTO1.getMinCashLimit() + " and " + bondLedgerAccountMaintenanceDTO1.getCashLimitForClearingTransfer());
            }
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        BigDecimal depositMultiples = new BigDecimal(
                bondLedgerAccountMaintenanceDTO1.getDepositMultiples().trim()
        );
        if (subscriptionAmount.remainder(depositMultiples).compareTo(BigDecimal.ZERO) != 0) {
            responseDataDTO.setMessage("Subscription Amount must be multiples of " + bondLedgerAccountMaintenanceDTO1.getDepositMultiples());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }*/
        return blaOpeningCumSubscriptionDao.addSubmit(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.inquiry(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO getbranchName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getbranchName(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO getbankName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getbankName(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.modifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.modify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.verifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.verify(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.delete(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO blaNo(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.blaNo(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO blaTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.blaTorch(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO getblaNoInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getblaNoInq(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO getsubscriptionIdMod(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getsubscriptionIdMod(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getsubscriptionIdInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getsubscriptionIdInq(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO modifyPanTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.modifyPanTorch(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO getRbiBondModuleNameDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getRbiBondModuleNameDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getBrokerTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getBrokerTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getGuardianDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getNonHdfcTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcBranchTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.getNonHdfcBranchTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.unlockBla(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO fetchState(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionDao.fetchState(bondLedgerAccountMaintenanceDTO);
    }
}
