package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.facade.BlaOpeningCumSubscriptionFacade;

import com.isl.gbm.rbibond.service.BlaOpeningCumSubscriptionService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlaOpeningCumSubscriptionFacadeImpl implements BlaOpeningCumSubscriptionFacade {

    @Autowired
    private BlaOpeningCumSubscriptionService blaOpeningCumSubscriptionService;


    @Override
    public ResponseDataDTO getBcifApi(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getBcifApi(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getMinorDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getMinorDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO getWboBranchDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getWboBranchDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO add(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.add(bondLedgerAccountMaintenanceDTO);
    }
    public ResponseDataDTO validatePan(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.validatePan(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getAddDashBoard(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getAddDashBoard(bondLedgerAccountMaintenanceDTO);
    }
    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO addSubmit(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.addSubmit(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO inquiry(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.inquiry(bondLedgerAccountMaintenanceDTO);
    }
    public ResponseDataDTO getbranchName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getbranchName(bondLedgerAccountMaintenanceDTO);
    }
    public ResponseDataDTO getbankName(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getbankName(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY")
    public ResponseDataDTO modifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.modifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY")
    public ResponseDataDTO modify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.modify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO verifySelect(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.verifySelect(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.verify(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO delete(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.delete(bondLedgerAccountMaintenanceDTO);
    }
    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO blaNo(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.blaNo(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    //    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getblaNoInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getblaNoInq(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO blaTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.blaTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO modifyPanTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.modifyPanTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getsubscriptionIdMod(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getsubscriptionIdMod(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getsubscriptionIdInq(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getsubscriptionIdInq(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getRbiBondModuleNameDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getRbiBondModuleNameDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getBrokerTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getBrokerTorch(bondLedgerAccountMaintenanceDTO);
    }



    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getGuardianDetails(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getGuardianDetails(bondLedgerAccountMaintenanceDTO);
    }

    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getNonHdfcTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getNonHdfcTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    //    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO getNonHdfcBranchTorch(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.getNonHdfcBranchTorch(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    //    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO unlockBla(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.unlockBla(bondLedgerAccountMaintenanceDTO);
    }

    @Override
    public ResponseDataDTO fetchState(BondLedgerAccountMaintenanceDTO bondLedgerAccountMaintenanceDTO) {
        return blaOpeningCumSubscriptionService.fetchState(bondLedgerAccountMaintenanceDTO);
    }
}
