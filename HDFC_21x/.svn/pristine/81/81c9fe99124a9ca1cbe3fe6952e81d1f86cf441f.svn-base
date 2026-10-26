package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.facade.BondLedgerAccountMaintenanceFacade;
import com.isl.gbm.rbibond.service.BondLedgerAccountMaintenanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BondLedgerAccountMaintenanceFacadeimpl implements BondLedgerAccountMaintenanceFacade {

    @Autowired
    private BondLedgerAccountMaintenanceService bondLedgerAccountMaintenanceService;

    @Override
    public ResponseDataDTO getBcifApi(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.getBcifApi(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.add(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getBlaNumDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.getBlaNumDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.getGuardianDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ENQUIRY")
    public ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.inquiry(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getTdsRateCode(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.getTdsRateCode(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY")
    public ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.modifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY")
    public ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.modify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.verifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.verify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:DELETE")
    public ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceService.delete(bondLedgerAccountMaintenanceDTO);
    }


    //@RequiresPermissions("GBM_DC_EFREIGHT:EFREIGHT_CENTRALIZED_BOD:ENQUIRY")

}
