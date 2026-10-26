package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.bo.RbiBondNomineeContinuationBo;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathDTO;
import com.isl.gbm.rbibond.dto.RbiBondNomineeContinuationDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanDetailsDTO;
import com.isl.gbm.rbibond.externalservice.dto.PanValResponseDTO;
import com.isl.gbm.rbibond.externalservice.service.ExternalService;
import com.isl.gbm.rbibond.service.RbiBondNomineeContinuationService;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import com.isl.gbm.user.isac.common.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Service for handling RBI Bond Nominee Continuation operations.
 *
 * @author Sneha Sampath
 * @date 2025-02-05
 */
@Service
public class RbiBondNomineeContinuationServiceImpl implements RbiBondNomineeContinuationService {


    private static final Logger LOGGER = LoggerFactory.getLogger(RbiBondNomineeContinuationServiceImpl.class);

    @Value("${sftp.payment.file.bond.sub-acc.closure.on-maturity.in.path}")
    private String remoteDir;

    @Autowired
    RbiBondNomineeContinuationBo rbiBondNomineeContinuationBo;
    @Autowired
    ExternalService externalService;

    @Override
    public ResponseDataDTO getAddDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationBo.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getAddValidation(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            for (RbiBondNomineeContinuationDTO customer : rbiBondClosureBeforeMaturDTO.getNomineeDetList()) {
                // Add any customer-specific validations here
                if (null != customer.getPanNum() && !customer.getPanNum().equalsIgnoreCase("")) {
                    RbiBondServerSideRegexUtility.validatePAN(customer.getPanNum());
                }
            }
            if (null != rbiBondClosureBeforeMaturDTO && null != rbiBondClosureBeforeMaturDTO.getPanNum() && !rbiBondClosureBeforeMaturDTO.getPanNum().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondClosureBeforeMaturDTO.getPanNum());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            LOGGER.error("Error: {}",e.getMessage());
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        return rbiBondNomineeContinuationBo.getAddValidation(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields


            // Validate each customer in the list
            if (rbiBondClosureBeforeMaturDTO.getBlaDetailsList() != null) {
                for (RbiBondNomineeContinuationDTO customer : rbiBondClosureBeforeMaturDTO.getBlaDetailsList()) {
                    // Add any customer-specific validations here
                    if (null != customer.getPanNum() && !customer.getPanNum().equalsIgnoreCase("")) {
                        RbiBondServerSideRegexUtility.validatePAN(customer.getPanNum());
                    }
                    if (null != customer.getBondHldEmail() && !customer.getBondHldEmail().equalsIgnoreCase("")) {
                        RbiBondServerSideRegexUtility.validateEmail(customer.getBondHldEmail());
                    }
                    // if (null != customer.getIfscCode() && !customer.getIfscCode().equalsIgnoreCase("")) {
                    //     RbiBondServerSideRegexUtility.validateIFSC(customer.getIfscCode());
                    // } //IFSC code validation is not used in BLA screen so commenting
                    if (null != customer.getBondHolderPhoneNumber() && !customer.getBondHolderPhoneNumber().equalsIgnoreCase("")) {
                        RbiBondServerSideRegexUtility.validateMobileNumber(customer.getBondHolderPhoneNumber());
                    }
                }
            }

        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            LOGGER.error("Error {}",e.getMessage());
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondNomineeContinuationBo.add(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getInqDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields

            if (null != rbiBondClosureBeforeMaturDTO && null != rbiBondClosureBeforeMaturDTO.getPanNum() && !rbiBondClosureBeforeMaturDTO.getPanNum().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondClosureBeforeMaturDTO.getPanNum());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            LOGGER.error("Error {}",e.getMessage());
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        return rbiBondNomineeContinuationBo.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getVerDelDetails(RbiBondNomineeContinuationDTO rbiBondTransferInDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        try {
            // Validate all fields

            if (null != rbiBondTransferInDTO && null != rbiBondTransferInDTO.getPanNum() && !rbiBondTransferInDTO.getPanNum().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondTransferInDTO.getPanNum());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }

        return rbiBondNomineeContinuationBo.getVerDelDetails(rbiBondTransferInDTO);
    }
    @Override
    public ResponseDataDTO getWboBranchDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationBo.getWboBranchDetails(rbiBondClosureBeforeMaturDTO);
    }
 @Override
    public ResponseDataDTO verify(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationBo.verify(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationBo.delete(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO getPanValidation(RbiBondClosureOnMaturDeathDTO paymentRequestDTO) {
        ResponseDataDTO responseDataDTO2=new ResponseDataDTO();

        if (paymentRequestDTO == null) {
            throw new IllegalArgumentException("paymentRequestDTO cannot be null");
        }
        if (paymentRequestDTO.getPanNumList() == null) {
            throw new IllegalArgumentException("PanNumList cannot be null");
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<PanValidationRetriggerResponseDTO> successList = new ArrayList<>();
        List<PanValidationRetriggerResponseDTO> failureList = new ArrayList<>();
        responseDataDTO2 = rbiBondNomineeContinuationBo.getPanValidation(paymentRequestDTO);
        try{
            if (!ObjectUtils.isEmpty(responseDataDTO2) && responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO = new PanValidationRetriggerResponseDTO();

                for (int i = 0; i < paymentRequestDTO.getPanNumList().size(); i++) {
                    LOGGER.info("Pan Validation Request From DTO Pan No {}, Name {}, DOB {}",
                            paymentRequestDTO.getPanNumList().get(i).getPanNo(), paymentRequestDTO.getPanNumList().get(i).getNomineeName(), paymentRequestDTO.getPanNumList().get(i).getDateOfBirthOfMinor());

                    PanDetailsDTO panDetailsDTO = new PanDetailsDTO();

                    String dob = paymentRequestDTO.getPanNumList().get(i).getDateOfBirthOfMinor();

                    try {
                        dob = outputFormat.format(inputFormat.parse(dob)); // Convert and reassign
                    } catch (Exception e) {
                        e.printStackTrace(); // Handle errors
                    }
                    panDetailsDTO.setDob(dob);
                    panDetailsDTO.setPan(paymentRequestDTO.getPanNumList().get(i).getPanNo());
                    panDetailsDTO.setName(paymentRequestDTO.getPanNumList().get(i).getNomineeName());

                    ResponseDataDTO panValidationResponse = externalService.panValidation(panDetailsDTO);
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
                            null!=panValResponseDTO.getReturnDescription()&&panValResponseDTO.getReturnDescription().equalsIgnoreCase(CommonConstants.SUCCESS_CONST)) {
                        panValidationRetriggerResponseDTO.setSchemeCode(paymentRequestDTO.getSchemeCode());
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
                            panValidationRetriggerResponseDTO.setProcessStatus("Y");
                        } else {
                            panValidationRetriggerResponseDTO.setProcessStatus("M");
                        }
                        panValidationRetriggerResponseDTO.setItrFlag(panValResponseDTO.getSeedingStatus());

                        responseDataDTO2 = rbiBondNomineeContinuationBo.updatePANStatus(panValidationRetriggerResponseDTO);
                        if (responseDataDTO2.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                            LOGGER.info(CommonConstants.DB_STATUS + responseDataDTO2.getMessage());
                            successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                        } else {
                            LOGGER.info("{}: {}", CommonConstants.DB_FAILURE, responseDataDTO2.getMessage());
                            failureList.add(panValidationRetriggerResponseDTO);
                        }
                        responseDataDTO2.setMessage(CommonConstant.SUCCESS_DESC);
                    } else {
                        LOGGER.info(CommonConstants.PAN_VALID_FAILURE + ": " + (null != panValResponseDTO ? panValResponseDTO.toString() : ""));
                        panValidationRetriggerResponseDTO.setSchemeCode(paymentRequestDTO.getSchemeCode());
                        panValidationRetriggerResponseDTO.setPanNumber(panDetailsDTO.getPan());
                        panValidationRetriggerResponseDTO.setNameStatus(panValResponseDTO.getName());
                        panValidationRetriggerResponseDTO.setPanStatus(panValResponseDTO.getPanStatus());
                        panValidationRetriggerResponseDTO.setDobStatus(panValResponseDTO.getDob());
                        if ("E".equalsIgnoreCase(panValResponseDTO.getPanStatus()) &&
                                "Y".equalsIgnoreCase(panValResponseDTO.getName()) &&
                                "Y".equalsIgnoreCase(panValResponseDTO.getDob())) {
                            panValidationRetriggerResponseDTO.setProcessStatus("Y");
                        }
                        else{panValidationRetriggerResponseDTO.setProcessStatus("M");}
                        ResponseDataDTO res = rbiBondNomineeContinuationBo.updatePANStatus(panValidationRetriggerResponseDTO);
                        if (res.getMessage().equalsIgnoreCase(CommonConstant.SUCCESS_DESC)) {
                            LOGGER.info("External DB update Status: {}", res.getMessage());
                            successList.add(panValidationRetriggerResponseDTO); // ✅ Store success PANs
                        } else {
                            LOGGER.info("External DB update Failure: {}", res.getMessage());
                            failureList.add(panValidationRetriggerResponseDTO); // ❌ Store failure PANs
                        }
                        LOGGER.info(CommonConstants.PAN_VALID_FAILURE + ": " + (null != panValResponseDTO ? panValResponseDTO.toString() : ""));
                        responseDataDTO2.setStatusCode("0");
                        responseDataDTO2.setMessage(res.getMessage());
                        responseDataDTO2.setResult(panValResponseDTO);
                    }}
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
