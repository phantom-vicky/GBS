package com.isl.gbm.rbibond.bo.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.TdsParameterForRbiBondBo;
import com.isl.gbm.rbibond.dao.TdsParameterForRbiBondDao;
import com.isl.gbm.rbibond.dto.RbiBondTdsParameterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TdsParameterForRbiBondBoImpl implements TdsParameterForRbiBondBo {

    @Autowired
    private TdsParameterForRbiBondDao tdsParameterForRbiBondDao;


    @Override
    public ResponseDataDTO add(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondDao.add(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO addValidation(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondDao.addValidation(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO inquiryDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondDao.inquiryDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO verifyDeleteDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondDao.verifyDeleteDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondDao.verify(rbiBondTdsParameterDTO);

    }

    @Override
    public ResponseDataDTO delete(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondDao.delete(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO modifyDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondDao.modifyDetails(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondDao.modifySubmit(rbiBondTdsParameterDTO);
    }


}
