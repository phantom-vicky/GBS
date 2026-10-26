package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondCodeMappingBo;
import com.isl.gbm.rbibond.dto.RbiBondCodeMappingDTO;
import com.isl.gbm.rbibond.service.RbiBondCodeMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RbiBondCodeMappingServiceimpl implements RbiBondCodeMappingService {

    @Autowired
    private RbiBondCodeMappingBo rbiBondCodeMappingBo;


    @Override
    public ResponseDataDTO add(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingBo.add(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO addValidation() {
        return rbiBondCodeMappingBo.addValidation();
    }

    @Override
    public ResponseDataDTO inquiryDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingBo.inquiryDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO verifyDeleteDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingBo.verifyDeleteDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingBo.verify(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingBo.delete(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO modifyDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingBo.modifyDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingBo.modifySubmit(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO getBranchCode(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingBo.getBranchCode(rbiBondTdsParameterDTO);
    }


}
