package com.isl.gbm.rbibond.facade.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondLedgerAccountMaintenanceDTO;
import com.isl.gbm.rbibond.dto.RbiBondTransferInDTO;
import com.isl.gbm.rbibond.facade.RbiBondTransferInFacade;
import com.isl.gbm.rbibond.service.RbiBondTransferInService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class RbiBondTransferInFacadeImpl implements RbiBondTransferInFacade {


    @Autowired
    RbiBondTransferInService rbiBondTransferInService;

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:INQUIRY"})
    public ResponseDataDTO getblaNoInq(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.getblaNoInq(rbiBondTransferInDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:INQUIRY"})
    public ResponseDataDTO getsubscriptionIdInq(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.getsubscriptionIdInq(rbiBondTransferInDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:INQUIRY"})
    public ResponseDataDTO panTorch(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.panTorch(rbiBondTransferInDTO);
    }
    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:ADD"})
    public ResponseDataDTO addTorch(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.addTorch(rbiBondTransferInDTO);
    }



    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:VERIFY"})
    public ResponseDataDTO verifySelect(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.verifySelect(rbiBondTransferInDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:MODIFY"})
    public ResponseDataDTO modifySelect(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.modifySelect(rbiBondTransferInDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:MODIFY"})
    public ResponseDataDTO modifyPAN(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.modifyPAN(rbiBondTransferInDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:INQUIRY"})
    public ResponseDataDTO inquiry(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.inquiry(rbiBondTransferInDTO);
    }


    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:VERIFY"})
    public ResponseDataDTO verify(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.verify(rbiBondTransferInDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:VERIFY"})
    public ResponseDataDTO delete(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.delete(rbiBondTransferInDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:MODIFY"})
    public ResponseDataDTO modify(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.modify(rbiBondTransferInDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:ADD"})
    public ResponseDataDTO getGuardianDetails(RbiBondTransferInDTO rbiBondTransferInDTO) {
        return rbiBondTransferInService.getGuardianDetails(rbiBondTransferInDTO);
    }
    public ResponseDataDTO validatePan(RbiBondTransferInDTO bondLedgerAccountMaintenanceDTO) {
        return rbiBondTransferInService.validatePan(bondLedgerAccountMaintenanceDTO);
    }
    @Override
    public ResponseDataDTO otherBankDetails() throws SQLException {
        return rbiBondTransferInService.otherBankDetails();
    }

    @Override
    @RequiresPermissions(logical = Logical.OR ,value = {"GBM_GU_RBIBOND:RBIBOND_TF_OTHR_BNK:ADD"})
    public ResponseDataDTO bondTransferInAdd(RbiBondTransferInDTO rbiBondTransferInAddDto) {
        return rbiBondTransferInService.bondTransferInAdd(rbiBondTransferInAddDto);
    }
}
