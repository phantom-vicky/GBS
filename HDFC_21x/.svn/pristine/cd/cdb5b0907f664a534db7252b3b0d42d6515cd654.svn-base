package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondAccountingEntriesBO;
import com.isl.gbm.rbibond.dto.RbiBondAccountingEntriesDTO;
import com.isl.gbm.rbibond.service.RbiBondAccountingEntriesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class RbiBondAccountingEntriesServiceImpl implements RbiBondAccountingEntriesService {
    @Autowired
    private RbiBondAccountingEntriesBO rbiBondAccountingEntriesBO;
    private static final Logger logger = LoggerFactory.getLogger(RbiBondAccountingEntriesServiceImpl.class);


    @Override
    public ResponseDataDTO getModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.getModuleName(rbiBondAccountingEntriesDTO) ;
    }

    @Override
    public ResponseDataDTO getVersionNo(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.getVersionNo(rbiBondAccountingEntriesDTO) ;
    }

    @Override
    public ResponseDataDTO addAccountEntriesValues(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {

        Map<String, String> entries = rbiBondAccountingEntriesDTO.getAccountingEntriesListModel();
        List<String> dateKeys = Arrays.asList("C006", "C123");

        for (String key : dateKeys) {
            entries.computeIfPresent(key, (k, v) -> formatDate(v));
        }
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            logger.error("Key: {}, Value: {}", entry.getKey(), entry.getValue());
        }

        return rbiBondAccountingEntriesBO.addAccountEntriesValues(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO getModifyModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.getModifyModuleName(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO getVerifyModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.getVerifyModuleName(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO inquiryDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.inquiryDetails(rbiBondAccountingEntriesDTO) ;
    }

    @Override
    public ResponseDataDTO getModifyDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.getModifyDetails(rbiBondAccountingEntriesDTO) ;
    }

    @Override
    public ResponseDataDTO getVerifyDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.getVerifyDetails(rbiBondAccountingEntriesDTO) ;
    }

    @Override
    public ResponseDataDTO verifySubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.verifySubmit(rbiBondAccountingEntriesDTO) ;
    }

    @Override
    public ResponseDataDTO modifySubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.modifySubmit(rbiBondAccountingEntriesDTO) ;
    }

    @Override
    public ResponseDataDTO deleteSubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesBO.deleteSubmit(rbiBondAccountingEntriesDTO) ;
    }

    private String formatDate(String inputDate) {
        try {
            logger.error("input date :{}",inputDate);
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = inputFormat.parse(inputDate);
            if (logger.isErrorEnabled()) {
                logger.error("output date: {}", outputFormat.format(date));
            }
            return outputFormat.format(date);
        } catch (Exception e) {
            logger.error("Date format error: {}", e.getMessage());
            return inputDate;
        }
    }
}