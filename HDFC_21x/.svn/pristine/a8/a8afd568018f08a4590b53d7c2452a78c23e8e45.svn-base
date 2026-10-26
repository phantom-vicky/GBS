package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.BondLedgerAccountMaintenanceBo;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.service.BondLedgerAccountMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BondLedgerAccountMaintenanceServiceimpl implements BondLedgerAccountMaintenanceService {

    @Autowired
    private BondLedgerAccountMaintenanceBo bondLedgerAccountMaintenanceBo;


    @Override
    public ResponseDataDTO getBcifApi(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.getBcifApi(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.add(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getBlaNumDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.getBlaNumDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.getGuardianDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.inquiry(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getTdsRateCode(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.getTdsRateCode(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.modifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.modify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.verifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.verify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return bondLedgerAccountMaintenanceBo.delete(bondLedgerAccountMaintenanceDTO);
    }


}
