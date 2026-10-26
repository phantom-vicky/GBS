package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;

public interface BlaOpeningCumSubscriptionFacade {

    ResponseDataDTO getBcifApi(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getMinorDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getWboBranchDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);
    ResponseDataDTO validatePan(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getAddDashBoard(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO addSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);
    ResponseDataDTO getbranchName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);
    ResponseDataDTO getbankName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);


    ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO blaNo(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getblaNoInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO blaTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getsubscriptionIdMod(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getsubscriptionIdInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO modifyPanTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getRbiBondModuleNameDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getBrokerTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getNonHdfcTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO getNonHdfcBranchTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO fetchState(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);
}
