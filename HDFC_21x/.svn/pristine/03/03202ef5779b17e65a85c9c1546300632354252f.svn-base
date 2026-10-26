package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ColumnValueDTO;
import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondSchemeMasterBO;
import com.isl.gbm.rbibond.dto.SchemeMasterRequestResponseDTO;
import com.isl.gbm.rbibond.service.RbiBondSchemeMasterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RbiBondSchemeMasterServiceImpl implements RbiBondSchemeMasterService {

    @Autowired
    RbiBondSchemeMasterBO rbiBondSchemeMasterBO;

    ResponseDataDTO responseDataDTO  = new ResponseDataDTO();

    @Override
    public ResponseDataDTO modifySelect(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
//        if (!StringUtils.isEmpty(schemeMasterRequestResponseDTO.getSchemeName())) {


        if(schemeMasterRequestResponseDTO.getFlg().equalsIgnoreCase("D") && schemeMasterRequestResponseDTO.getSchemeName()==null){

            responseDataDTO.setMessage("Scheme Name should not be blank");
            return responseDataDTO;
        }


//                }

        return rbiBondSchemeMasterBO.modifySelect(schemeMasterRequestResponseDTO);
    }
    @Override
    public ResponseDataDTO verifySelect(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {


        if(schemeMasterRequestResponseDTO.getFlg().equalsIgnoreCase("D") && schemeMasterRequestResponseDTO.getSchemeName()==null){

            responseDataDTO.setMessage("Scheme Name should not be blank");
            return responseDataDTO;
        }
        return rbiBondSchemeMasterBO.verifySelect(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO add(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {

        if(schemeMasterRequestResponseDTO.getSchemeName() == null || schemeMasterRequestResponseDTO.getSchemeName().isEmpty()){

            responseDataDTO.setMessage("Scheme Name should not be blank");
            return responseDataDTO;
        }
        return rbiBondSchemeMasterBO.add(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO inquiry(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {


        if(schemeMasterRequestResponseDTO.getFlg().equalsIgnoreCase("D") && schemeMasterRequestResponseDTO.getSchemeName()==null){

            responseDataDTO.setMessage("Scheme Name should not be blank");
            return responseDataDTO;
        }
        return rbiBondSchemeMasterBO.inquiry(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO verify(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterBO.verify(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO modify(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        for (ColumnValueDTO column : schemeMasterRequestResponseDTO.getColumnValues()) {

            if ("C004".equalsIgnoreCase(column.getColName())) {

                String value = column.getValue();

                if (value == null ||
                        !(value.equalsIgnoreCase("Y") || value.equalsIgnoreCase("N"))) {
                    responseDataDTO.setMessage("Status is mandatory");
                    responseDataDTO.setStatusCode("0");
                    return responseDataDTO;
                }
            }
        }
        return rbiBondSchemeMasterBO.modify(schemeMasterRequestResponseDTO);
    }

    @Override
    public ResponseDataDTO delete(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {

        if(schemeMasterRequestResponseDTO.getRemarks()== null || schemeMasterRequestResponseDTO.getRemarks().isEmpty()){
            responseDataDTO.setMessage("Remarks should not be blank");
            return responseDataDTO;
        }
        return rbiBondSchemeMasterBO.delete(schemeMasterRequestResponseDTO);
    }
}
