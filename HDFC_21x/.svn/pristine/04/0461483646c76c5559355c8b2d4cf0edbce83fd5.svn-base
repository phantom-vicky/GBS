package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.reports.CacAckNumMaintenanceDto;
import com.isl.gbm.rbibond.facade.CacAckNumMaintenanceFacade;
import com.isl.gbm.rbibond.service.CacAckNumMaintenanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacAckNumMaintenanceFacadeImpl implements CacAckNumMaintenanceFacade {

    @Autowired
    private CacAckNumMaintenanceService cacAckNumMaintenanceService;

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:ADD")
    public ResponseDataDTO add(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceService.add(cacAckNumMaintenanceDto);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:VERIFY")
    public ResponseDataDTO verifyDeleteSelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceService.verifyDeleteSelect(cacAckNumMaintenanceDto);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:ENQUIRY")
    public ResponseDataDTO inquiry(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceService.inquiry(cacAckNumMaintenanceDto);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:VERIFY")
    public ResponseDataDTO verify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceService.verify(cacAckNumMaintenanceDto);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:VERIFY")
    public ResponseDataDTO delete(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceService.delete(cacAckNumMaintenanceDto);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:MODIFY")
    public ResponseDataDTO modifySelect(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceService.modifySelect(cacAckNumMaintenanceDto);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:MODIFY")
    public ResponseDataDTO modify(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceService.modify(cacAckNumMaintenanceDto);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_CAS_ACK_NUM_MAINTENANCE:ADD")
    public ResponseDataDTO addDet(CacAckNumMaintenanceDto cacAckNumMaintenanceDto) {
        return cacAckNumMaintenanceService.addDet(cacAckNumMaintenanceDto);
    }
}
