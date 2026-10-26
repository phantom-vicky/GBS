package com.isl.gbm.rbibond.bo.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.BrokerMasterMaintainanceBo;
import com.isl.gbm.rbibond.bo.RbiBondCodeMappingBo;
import com.isl.gbm.rbibond.dao.BrokerMasterMaintainanceDao;
import com.isl.gbm.rbibond.dao.RbiBondCodeMappingDao;
import com.isl.gbm.rbibond.dto.BrokerMasterMainDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrokerMasterMaintainanceBoImpl implements BrokerMasterMaintainanceBo {

    @Autowired
    private BrokerMasterMaintainanceDao brokerMasterMaintainanceDao;


    @Override
    public ResponseDataDTO add(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.add(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO addValidation(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.addValidation(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO inquiryDetails(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.inquiryDetails(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO verifyDeleteDetails(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.verifyDeleteDetails(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO verify(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.verify(brokerMasterMainDTO);

    }

    @Override
    public ResponseDataDTO delete(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.delete(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO modifyDetails(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.modifyDetails(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO modifySubmit(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.modifySubmit(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO getBranchCode(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.getBranchCode(brokerMasterMainDTO);
    }

    @Override
    public ResponseDataDTO getNonHdfcTorch(BrokerMasterMainDTO brokerMasterMainDTO) {
        return brokerMasterMaintainanceDao.getNonHdfcTorch(brokerMasterMainDTO);
    }
}
