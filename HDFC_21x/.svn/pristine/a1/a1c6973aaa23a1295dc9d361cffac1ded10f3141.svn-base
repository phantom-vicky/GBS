package com.isl.gbm.rbibond.service.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CancellationSubscriptionCatCbtBo;
import com.isl.gbm.rbibond.dao.GbmRbiBondRejResnMstDao;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.service.CancellationSubscriptionCatCbtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancellationSubscriptionCatCbtServiceImpl implements CancellationSubscriptionCatCbtService {

    @Autowired
    private CancellationSubscriptionCatCbtBo cancellationSubscriptionCatCbtBo;

    @Autowired
    private GbmRbiBondRejResnMstDao gbmRbiBondRejResnMstDao;

    @Override
    public ResponseDataDTO addCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtBo.addCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }


    @Override
    public ResponseDataDTO verifyRejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtBo.verifyRejectCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO addSubCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtBo.addSubCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifyCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtBo.verifyCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO rejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtBo.rejectCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO inquiryCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtBo.inquiryCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }
}
