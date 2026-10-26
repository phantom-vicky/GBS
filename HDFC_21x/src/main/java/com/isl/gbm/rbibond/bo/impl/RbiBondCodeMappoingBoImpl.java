package com.isl.gbm.rbibond.bo.impl;


import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondCodeMappingBo;
import com.isl.gbm.rbibond.dao.RbiBondCodeMappingDao;
import com.isl.gbm.rbibond.dto.RbiBondCodeMappingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

@Component
public class RbiBondCodeMappoingBoImpl implements RbiBondCodeMappingBo {

    @Autowired
    private RbiBondCodeMappingDao rbiBondCodeMappingDao;


    @Override
    public ResponseDataDTO add(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingDao.add(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO addValidation() {
        return rbiBondCodeMappingDao.addValidation();
    }

    @Override
    public ResponseDataDTO inquiryDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingDao.inquiryDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO verifyDeleteDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingDao.verifyDeleteDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingDao.verify(rbiBondTdsParameterDTO);

    }

    @Override
    public ResponseDataDTO delete(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingDao.delete(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO modifyDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingDao.modifyDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        ResponseDataDTO responseDataDTO =new ResponseDataDTO();
        if(rbiBondTdsParameterDTO.getColumnValues() != null){

            for (ColumnValueDTO col : rbiBondTdsParameterDTO.getColumnValues()){
                if ("C008".equalsIgnoreCase(col.getColName())) {
                    if(col.getValue().isEmpty()){
                        responseDataDTO.setMessage("Remarks is Mandatory");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }
                if ("C003".equalsIgnoreCase(col.getColName())) {
                    if(col.getValue().isEmpty() || col.getValue().length() > 10 || !col.getValue().matches("^[a-zA-Z0-9]+$")){
                        responseDataDTO.setMessage("PDO Code should is Mandatory & Alphanumeric");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }
                if ("C004".equalsIgnoreCase(col.getColName())) {
                    if(col.getValue().isEmpty() || col.getValue().length() > 10 || !col.getValue().matches("^[a-zA-Z0-9]+$")){
                        responseDataDTO.setMessage("RBI code should is Mandatory & Alphanumeric");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }
                if ("C005".equalsIgnoreCase(col.getColName())) {
                    if(col.getValue().isEmpty() || col.getValue().length() != 7 || !col.getValue().matches("^[A-Za-z]{3}[0-9]{4}$")){
                        responseDataDTO.setMessage("BLA Prefix should is Mandatory & Alphanumeric");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                }

                if ("C006".equalsIgnoreCase(col.getColName())) {
                    if(col.getValue().isEmpty()){
                        responseDataDTO.setMessage("Effective Date is Mandatory");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }
                    String date = col.getValue();
                    DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .appendPattern("dd-MMM-yyyy")
                            .toFormatter(Locale.ENGLISH);
                    try {
                        LocalDate.parse(date, formatter);
                    } catch (DateTimeParseException e) {
                        responseDataDTO.setMessage("Effective Date must be in dd-MMM-yyyy format");
                        responseDataDTO.setStatusCode("0");
                        return responseDataDTO;
                    }

                }

            }

        }
        return rbiBondCodeMappingDao.modifySubmit(rbiBondTdsParameterDTO);
    }

    private static String getPattern() {
        return "dd-MMM-yyyy";
    }

    @Override
    public ResponseDataDTO getBranchCode(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingDao.getBranchCode(rbiBondTdsParameterDTO);
    }


}
