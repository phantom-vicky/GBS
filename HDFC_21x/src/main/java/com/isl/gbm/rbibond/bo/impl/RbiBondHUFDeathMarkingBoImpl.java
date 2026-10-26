package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.RbiBondHUFDeathMarkingBo;
import com.isl.gbm.rbibond.dao.RbiBondBlaDeathMarkingDAO;
import com.isl.gbm.rbibond.dao.RbiBondHUFDeathMarkingDAO;
import com.isl.gbm.rbibond.dto.RbiBondHUFDeathMarkingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondHUFDeathMarkingBoImpl implements RbiBondHUFDeathMarkingBo {
    @Autowired
    RbiBondHUFDeathMarkingDAO rbiBondHUFDeathMarkingDAO;

    @Override
    public ResponseDataDTO getAddPanNoDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.getAddPanNoDetails(rbiBondHUFDeathMarkingDTO);

    }

    @Override
    public ResponseDataDTO getCoParcenerDtls(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.getCoParcenerDtls(rbiBondHUFDeathMarkingDTO);

    }

    @Override
    public ResponseDataDTO addCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.addCutomerDetails(rbiBondHUFDeathMarkingDTO);
    }


    @Override
    public ResponseDataDTO getVerDelPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.getVerDelPanDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getVerifyDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.getVerifyDetails(rbiBondHUFDeathMarkingDTO);
    }
    @Override
    public ResponseDataDTO verifyCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.verifyCutomerDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO deleteCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.deleteCutomerDetails(rbiBondHUFDeathMarkingDTO);
    }
    @Override
    public ResponseDataDTO getInquiryPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.getInquiryPanDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getInquiryDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.getInquiryDetails(rbiBondHUFDeathMarkingDTO);
    }
    @Override
    public ResponseDataDTO getAddDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingDAO.getAddDetails(rbiBondHUFDeathMarkingDTO);
    }

}
