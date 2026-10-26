package com.isl.gbm.rbibond.bo;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;

public interface BondLedgerAccountMaintenanceBo {

    ResponseDataDTO getBcifApi(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getBlaNumDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getTdsRateCode(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);
}
