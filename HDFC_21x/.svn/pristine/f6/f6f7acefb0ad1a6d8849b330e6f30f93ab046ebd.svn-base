package com.isl.gbm.rbibond.bo.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.bo.CancellationSubscriptionCatCbtBo;
import com.isl.gbm.rbibond.dao.CancellationSubscriptionCatCbtDao;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CancellationSubscriptionCatCbtBoImpl implements CancellationSubscriptionCatCbtBo {

    @Autowired
    private CancellationSubscriptionCatCbtDao cancellationSubscriptionCatCbtDao;


    @Override
    public ResponseDataDTO addCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtDao.addCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifyRejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtDao.verifyRejectCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO addSubCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtDao.addSubCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO verifyCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtDao.verifyCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO rejectCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtDao.rejectCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO inquiryCloserSubCatCbt(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return cancellationSubscriptionCatCbtDao.inquiryCloserSubCatCbt(bondLedgerAccountMaintenanceDTO);
    }
}
