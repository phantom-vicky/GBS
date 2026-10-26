package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.TdsParameterForRbiBondBo;
import com.isl.gbm.rbibond.dto.RbiBondTdsParameterDTO;
import com.isl.gbm.rbibond.service.TdsParameterForRbiBondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TdsParameterForRbiBondServiceimpl  implements TdsParameterForRbiBondService {

    @Autowired
    private TdsParameterForRbiBondBo tdsParameterForRbiBondBo;


    @Override
    public ResponseDataDTO add(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondBo.add(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO addValidation(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondBo.addValidation(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO inquiryDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondBo.inquiryDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO verifyDeleteDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondBo.verifyDeleteDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondBo.verify(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondBo.delete(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO modifyDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondBo.modifyDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondBo.modifySubmit(rbiBondTdsParameterDTO);
    }



}
