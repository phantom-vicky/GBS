package com.isl.gbm.rbibond.bo.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.StatewiseSgstMasterForBrokerageBO;
import com.isl.gbm.rbibond.dao.StatewiseSgstMasterForBrokerageDAO;
import com.isl.gbm.rbibond.dto.StatewiseSgstMasterForBrokerageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class StatewiseSgstMasterForBrokerageBOImpl implements StatewiseSgstMasterForBrokerageBO {


    @Autowired
    private StatewiseSgstMasterForBrokerageDAO statewiseSgstMasterForBrokerageDAO;

//    @Override
//    public ResponseDataDTO getAddDetails(StatewiseSgstMasterForBrokerageDto StatewiseSgstMasterForBrokerageDto) {
//        return StatewiseSgstMasterForBrokerageBO.getAddDetails(StatewiseSgstMasterForBrokerageDto);
//    }

    @Override
    public ResponseDataDTO add(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageDAO.add(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO inquiry(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageDAO.inquiry(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO getModifyDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageDAO.getModifyDetails(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO getVerifyDeleteDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageDAO.getVerifyDeleteDetails(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO verify(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageDAO.verify(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO modify(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageDAO.modify(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO delete(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageDAO.delete(statewiseSgstMasterForBrokerageDto);
    }

    @Override
    public ResponseDataDTO getStateDetails(StatewiseSgstMasterForBrokerageDto statewiseSgstMasterForBrokerageDto) {
        return statewiseSgstMasterForBrokerageDAO.getStateDetails(statewiseSgstMasterForBrokerageDto);
    }




}
