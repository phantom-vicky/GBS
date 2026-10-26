package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CacAckNumMaintenanceBo;
import com.isl.gbm.rbibond.dto.reports.CacAckNumMaintenanceDto;
import com.isl.gbm.rbibond.service.CacAckNumMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacAckNumMaintenanceServiceImpl implements CacAckNumMaintenanceService {

    @Autowired
    private CacAckNumMaintenanceBo cacAckNumMaintenanceBo;

    @Override
    public ResponseDataDTO add(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceBo.add(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO verifyDeleteSelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceBo.verifyDeleteSelect(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO inquiry(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceBo.inquiry(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO verify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceBo.verify(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO delete(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceBo.delete(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO modifySelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceBo.modifySelect(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO modify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceBo.modify(cacAckNumMaintenanceDto);
    }

    @Override
    public ResponseDataDTO addDet(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceBo.addDet(cacAckNumMaintenanceDto);
    }
}
