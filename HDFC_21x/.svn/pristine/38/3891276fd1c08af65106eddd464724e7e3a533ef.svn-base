package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondSchemeParameterBO;
import com.isl.gbm.rbibond.dao.RbiBondSchemeParameterDAO;
import com.isl.gbm.rbibond.dto.RbiBondSchemeParameterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondSchemeParameterBoImpl implements RbiBondSchemeParameterBO {

    @Autowired
    private RbiBondSchemeParameterDAO efreightSchemeParameterDAO;

    @Override
    public ResponseDataDTO isRecordExist(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterDAO.isRecordExist(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondSchemeParameterDTO efreightSchemeParameterDTO) {
        return efreightSchemeParameterDAO.add(efreightSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterDAO.modifySelect(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO modify(RbiBondSchemeParameterDTO efreightSchemeParameterDTO) {
        return efreightSchemeParameterDAO.modify(efreightSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterDAO.verify(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO unVerifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterDAO.unVerifySelect(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO delete(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterDAO.delete(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterDAO.inquiry(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO interestRtCdTorch(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterDAO.interestRtCdTorch(rbiBondSchemeParameterDTO);
    }
}
