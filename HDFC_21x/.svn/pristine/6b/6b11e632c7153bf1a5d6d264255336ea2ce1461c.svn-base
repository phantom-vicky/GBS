package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.BondInwardOutwardBO;
import com.isl.gbm.rbibond.dto.BondInwardOutwardDTO;
import com.isl.gbm.rbibond.service.BondInwardOutwardService;
import com.isl.gbm.rbibond.util.RbiBondServerSideRegexUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;


@Service
public class BondInwardOutwardServiceimpl implements BondInwardOutwardService {

    @Autowired
    private BondInwardOutwardBO bondInwardOutwardBO;

    @Override
    public ResponseDataDTO getAddInRefNo(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardBO.getAddInRefNo(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO addOutward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        ResponseDataDTO responseDataDTO=new ResponseDataDTO();
        if(bondInwardOutwardDTO.getOutwardRefNo() == null || bondInwardOutwardDTO.getOutwardRefNo().length() != 13){
            responseDataDTO.setMessage("Outward Number is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(bondInwardOutwardDTO.getTranStatus() ==null){
            responseDataDTO.setMessage("Status is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(bondInwardOutwardDTO.getInwDetailsList().isEmpty()){
            responseDataDTO.setMessage("Request Codes is mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        if(bondInwardOutwardDTO.getInwDate().isEmpty()){
            responseDataDTO.setMessage("Inward Date is Mandatory");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        String date = bondInwardOutwardDTO.getInwDate();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MMM-yyyy")
                .toFormatter(Locale.ENGLISH);
        try {
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            responseDataDTO.setMessage("Inward Date must be in dd-MMM-yyyy format");
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return bondInwardOutwardBO.addOutward(bondInwardOutwardDTO);
    }
    @Override
    public ResponseDataDTO rejectOutward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardBO.rejectOutward(bondInwardOutwardDTO);
    }
    @Override
    public ResponseDataDTO addInward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        ResponseDataDTO responseDataDTO= new ResponseDataDTO();
        try {
            if(bondInwardOutwardDTO!= null && bondInwardOutwardDTO.getPanNo() != null) {
                RbiBondServerSideRegexUtility.validatePAN(bondInwardOutwardDTO.getPanNo());
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDataDTO.setMessage(e.getMessage());
            responseDataDTO.setStatusCode("0");
            return responseDataDTO;
        }
        return bondInwardOutwardBO.addInward(bondInwardOutwardDTO);
    }
    @Override
    public ResponseDataDTO getPanNoTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardBO.getPanNoTor(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO inrRefNum(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardBO.inrRefNum(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO upload(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardBO.upload(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO download(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardBO.download(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO getBlaNoTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardBO.getBlaNoTor(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO getBlaSubsIdTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardBO.getBlaSubsIdTor(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO delete(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardBO.delete(bondInwardOutwardDTO);
    }

}
