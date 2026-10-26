package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondAccTransferOutBO;
import com.isl.gbm.rbibond.dao.RbiBondAccTransferOutDAO;
import com.isl.gbm.rbibond.dto.RbiBondAccTransferOutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RbiBondAccTransferOutBOimpl implements RbiBondAccTransferOutBO {

    @Autowired
    private RbiBondAccTransferOutDAO rbiBondAccTransferOutDAO;


    @Override
    public ResponseDataDTO getOtherBankDetails(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutDAO.getOtherBankDetails(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO getNomineeDetails(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutDAO.getNomineeDetails(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO add(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutDAO.add(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO addValidation(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutDAO.addValidation(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutDAO.inquiry(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutDAO.verifySelect(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO verify(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
      return rbiBondAccTransferOutDAO.verify(rbiBondAccTransferOutDTO);


    }

    @Override
    public ResponseDataDTO delete(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutDAO.delete(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutDAO.modifySelect(rbiBondAccTransferOutDTO);
    }

    @Override
    public ResponseDataDTO modify(RbiBondAccTransferOutDTO rbiBondAccTransferOutDTO) {
        return rbiBondAccTransferOutDAO.modify(rbiBondAccTransferOutDTO);
    }
}
