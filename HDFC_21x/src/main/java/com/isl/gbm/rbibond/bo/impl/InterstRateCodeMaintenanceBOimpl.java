package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.InterstRateCodeMaintenanceBO;
import com.isl.gbm.rbibond.dao.InterstRateCodeMaintenanceDAO;
import com.isl.gbm.rbibond.dto.RateCodeMaintenanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterstRateCodeMaintenanceBOimpl implements InterstRateCodeMaintenanceBO {

    @Autowired
    private InterstRateCodeMaintenanceDAO interstRateCodeMaintenanceDAO;

    @Override
    public ResponseDataDTO add(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceDAO.add(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceDAO.modifySelect(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceDAO.modify(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceDAO.verify(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO unVerifySelect(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceDAO.unVerifySelect(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceDAO.delete(rateCodeMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(RateCodeMaintenanceDTO rateCodeMaintenanceDTO) {
        return interstRateCodeMaintenanceDAO.inquiry(rateCodeMaintenanceDTO);
    }

}
