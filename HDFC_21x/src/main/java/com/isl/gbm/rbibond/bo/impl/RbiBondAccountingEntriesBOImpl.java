package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondAccountingEntriesBO;
import com.isl.gbm.rbibond.dao.RbiBondAccountingEntriesDAO;
import com.isl.gbm.rbibond.dto.RbiBondAccountingEntriesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondAccountingEntriesBOImpl implements RbiBondAccountingEntriesBO {
    @Autowired
    private RbiBondAccountingEntriesDAO rbiBondAccountingEntriesDAO;

    @Override
    public ResponseDataDTO getModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.getModuleName(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO getVersionNo(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.getVersionNo(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO getModifyModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.getModifyModuleName(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO getVerifyModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.getVerifyModuleName(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO addAccountEntriesValues(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.addAccountEntriesValues(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO inquiryDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.inquiryDetails(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO getModifyDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.getModifyDetails(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO getVerifyDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.getVerifyDetails(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO verifySubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.verifySubmit(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.modifySubmit(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO deleteSubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesDAO.deleteSubmit(rbiBondAccountingEntriesDTO);
    }
}