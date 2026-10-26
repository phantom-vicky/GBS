package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondLedgerKartaDTO;
import com.isl.gbm.rbibond.facade.RbiBondLedgerKartaFacade;
import com.isl.gbm.rbibond.service.RbiBondLedgerKartaService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondLedgerKartaFacadeImpl implements RbiBondLedgerKartaFacade {


    @Autowired
    RbiBondLedgerKartaService rbiBondLedgerKartaService;

    @Override
    public ResponseDataDTO getTorchPan(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.getTorchPan(rbiBondLedgerKartaDTO);
    }



    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:MODIFY")
    public ResponseDataDTO getModifyTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.getModifyTorchBla(rbiBondLedgerKartaDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:INQUIRY")
    public ResponseDataDTO getInquiryTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.getInquiryTorchBla(rbiBondLedgerKartaDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:MODIFY")
    public ResponseDataDTO getModifyBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.getModifyBla(rbiBondLedgerKartaDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:VERIFY","GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:DELETE"})
    public ResponseDataDTO getVerDelTorchBla(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.getVerDelTorchBla(rbiBondLedgerKartaDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:MODIFY")
    public ResponseDataDTO modifySelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.modifySelect(rbiBondLedgerKartaDTO);
    }


    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:MODIFY")
    public ResponseDataDTO addModify(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.addModify(rbiBondLedgerKartaDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:INQUIRY")
    public ResponseDataDTO inquiry(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.inquiry(rbiBondLedgerKartaDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:INQUIRY")
    public ResponseDataDTO inquirySelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.inquirySelect(rbiBondLedgerKartaDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:VERIFY","GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:DELETE"})
    public ResponseDataDTO verDelSelect(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.verDelSelect(rbiBondLedgerKartaDTO);
    }
    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:VERIFY","GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:DELETE"})
    public ResponseDataDTO inquiryPan(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.inquiryPan(rbiBondLedgerKartaDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:VERIFY")
    public ResponseDataDTO verify(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.verify(rbiBondLedgerKartaDTO);
    }

    @Override
  //  @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:KARTA_COPARCENERS_DETAILS:DELETE")
    public ResponseDataDTO delete(RbiBondLedgerKartaDTO rbiBondLedgerKartaDTO) {
        return rbiBondLedgerKartaService.delete(rbiBondLedgerKartaDTO);
    }


}
