package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.facade.CancellationSubscriptionCatCbtFacade;
import com.isl.gbm.rbibond.service.CancellationSubscriptionCatCbtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CancellationSubscriptionCatCbtFacadeImpl implements CancellationSubscriptionCatCbtFacade {

    @Autowired
    private CancellationSubscriptionCatCbtService cancellationSubscriptionCatCbtService;

    @Override
//    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUB_CANCEL_CAT_CBT:ADD")
    public ResponseDataDTO addCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtService.addCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUB_CANCEL_CAT_CBT:VERIFY")
    public ResponseDataDTO verifyRejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtService.verifyRejectCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUB_CANCEL_CAT_CBT:ADD")
    public ResponseDataDTO addSubCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtService.addSubCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    //    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUB_CANCEL_CAT_CBT:VERIFY")
    public ResponseDataDTO verifyCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtService.verifyCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    //    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUB_CANCEL_CAT_CBT:REJECT")
    public ResponseDataDTO rejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtService.rejectCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    //    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_SUB_CANCEL_CAT_CBT:INQUIRY")
    public ResponseDataDTO inquiryCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtService.inquiryCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }
}
