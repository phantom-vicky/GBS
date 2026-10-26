package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondAccTransferOutBO;
import com.isl.gbm.rbibond.dto.RbiBondAccTransferOutDTO;
import com.isl.gbm.rbibond.service.RbiBondAccTransferOutService;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RbiBondAccTransferOutServiceimpl implements RbiBondAccTransferOutService {


    @Autowired
    RbiBondAccTransferOutBO rbiBondAccTransferOutBO;

    @Override
    public ResponseDataDTO getOtherBankDetails(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutBO.getOtherBankDetails(rbiBondAccTransferOutDTO);
    }



    @Override
    public ResponseDataDTO getNomineeDetails(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutBO.getNomineeDetails(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutBO.add(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO addValidation(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {

        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        if(rbiBondAccTransferOutDTO.getSubscriptionDetailsList() !=null && !rbiBondAccTransferOutDTO.getSubscriptionDetailsList().isEmpty()) {
            for (RbiBondAccTransferOutDTO.SubscriptionDetails dto : rbiBondAccTransferOutDTO.getSubscriptionDetailsList()) {
                if (dto.getRateOfInterest() != null && !dto.getRateOfInterest().trim().isEmpty()) {
                    if (Double.parseDouble(dto.getRateOfInterest().trim()) < 0) {
                        responseDataDTO.setMessage("Rate of interest not be negative");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }
                if (dto.getSubscriptionAmount() != null && !dto.getSubscriptionAmount().trim().isEmpty()) {
                    if (Double.parseDouble(dto.getSubscriptionAmount().trim()) < 0) {
                        responseDataDTO.setMessage("Subscription Amount not be negative");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }
                if (dto.getTransferAmount() != null && !dto.getTransferAmount().trim().isEmpty()) {
                    if (Double.parseDouble(dto.getTransferAmount().trim()) < 0) {
                        responseDataDTO.setMessage("Transfer Amount not be negative");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }
            }
        }
        try {
            if (null != rbiBondAccTransferOutDTO && null != rbiBondAccTransferOutDTO.getPanNum() && !rbiBondAccTransferOutDTO.getPanNum().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondAccTransferOutDTO.getPanNum());
            }

        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondAccTransferOutBO.addValidation(rbiBondAccTransferOutDTO);

    }

    @Override
    public ResponseDataDTO inquiry(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();

        try {
            // Validate all fields
            if (null != rbiBondAccTransferOutDTO && null != rbiBondAccTransferOutDTO.getPanNum() && !rbiBondAccTransferOutDTO.getPanNum().equalsIgnoreCase("")) {
                RbiBondServerSideRegexUtility.validatePAN(rbiBondAccTransferOutDTO.getPanNum());
            }



        } catch (Exception e) {
            // Handle validation errors appropriately
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return rbiBondAccTransferOutBO.inquiry(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutBO.verifySelect(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        for(RbiBondAccTransferOutDTO.SubscriptionDetails dto:rbiBondAccTransferOutDTO.getSubscriptionDetailsList())
        {
            if(Double.parseDouble(dto.getRateOfInterest()) <0)   {
                responseDataDTO.setMessage("Rate of interest not be negative");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            if(Double.parseDouble(dto.getSubscriptionAmount()) <0)   {
                responseDataDTO.setMessage("Subscription Amount not be negative");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
            if(Double.parseDouble(dto.getTransferAmount()) <0)   {
                responseDataDTO.setMessage("Transfer Amount not be negative");
                responseDataDTO.setStatusCode("0");
                return responseDataDTO;
            }
        }
        return rbiBondAccTransferOutBO.verify(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutBO.delete(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutBO.modifySelect(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO modify(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutBO.modify(rbiBondAccTransferOutDTO);
    }


}
