package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.BondInwardOutwardBO;
import com.isl.gbm.rbibond.dao.BondInwardOutwardDAO;
import com.isl.gbm.rbibond.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BondInwardOutwardBOimpl implements BondInwardOutwardBO {




    @Autowired
    private BondInwardOutwardDAO bondInwardOutwardDAO;

    @Override
    public ResponseDataDTO getAddInRefNo(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardDAO.getAddInRefNo(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO addOutward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardDAO.addOutward(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO rejectOutward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardDAO.rejectOutward(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO addInward(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardDAO.addInward(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO getPanNoTor(BondInwardOutwardDTO bondInwardOutwardDTO) {

        return bondInwardOutwardDAO.getPanNoTor(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO inrRefNum(BondInwardOutwardDTO bondInwardOutwardDTO) {

        return bondInwardOutwardDAO.inrRefNum(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO upload(BondInwardOutwardDTO bondInwardOutwardDTO) {

        return null;
    }

    @Override
    public ResponseDataDTO download(BondInwardOutwardDTO bondInwardOutwardDTO) {

        return null;
//        return bondBrokeragePaymentDAO.download(bondInwardOutwardDTO);
    }
    @Override
    public ResponseDataDTO getBlaNoTor(BondInwardOutwardDTO bondInwardOutwardDTO) {

        return bondInwardOutwardDAO.getBlaNoTor(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO getBlaSubsIdTor(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardDAO.getBlaSubsIdTor(bondInwardOutwardDTO);
    }

    @Override
    public ResponseDataDTO delete(BondInwardOutwardDTO bondInwardOutwardDTO) {
        return bondInwardOutwardDAO.delete(bondInwardOutwardDTO);
    }

}
