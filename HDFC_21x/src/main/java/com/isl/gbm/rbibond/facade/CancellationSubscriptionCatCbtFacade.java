package com.isl.gbm.rbibond.facade;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;

public interface CancellationSubscriptionCatCbtFacade {


    ResponseDataDTO addCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO verifyRejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO addSubCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO verifyCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO rejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);

    ResponseDataDTO inquiryCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO);
}
