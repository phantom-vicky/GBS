package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CacAckNumMaintenanceBo;
import com.isl.gbm.rbibond.dao.CacAckNumMaintenanceDao;
import com.isl.gbm.rbibond.dto.reports.CacAckNumMaintenanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacAckNumMaintenanceBoImpl implements CacAckNumMaintenanceBo {

    @Autowired
    private CacAckNumMaintenanceDao cacAckNumMaintenanceDao;
    @Override
    public ResponseDataDTO add(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceDao.add(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceDao.verifyDeleteSelect(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO inquiry(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceDao.inquiry(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO verify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceDao.verify(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO delete(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceDao.delete(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO modifySelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceDao.modifySelect(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO modify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceDao.modify(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO addDet(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceDao.addDet(cacAckNumMaintenanceDto);
    }
}
