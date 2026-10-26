package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondAccountingEntriesDTO;
import com.isl.gbm.rbibond.facade.RbiBondAccountingEntriesFacade;
import com.isl.gbm.rbibond.service.RbiBondAccountingEntriesService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondAccountingEntriesFacadeImpl implements RbiBondAccountingEntriesFacade {
    @Autowired
    private RbiBondAccountingEntriesService rbiBondAccountingEntriesService;

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:ADD")
    public ResponseDataDTO getModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.getModuleName(rbiBondAccountingEntriesDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:ADD")
    public ResponseDataDTO addAccountEntriesValues(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.addAccountEntriesValues(rbiBondAccountingEntriesDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:INQUIRY")
    public ResponseDataDTO getVersionNo(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.getVersionNo(rbiBondAccountingEntriesDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:INQUIRY")
    public ResponseDataDTO inquiryDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.inquiryDetails(rbiBondAccountingEntriesDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:MODIFY")
    public ResponseDataDTO getModifyModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.getModifyModuleName(rbiBondAccountingEntriesDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:MODIFY")
    public ResponseDataDTO getModifyDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.getModifyDetails(rbiBondAccountingEntriesDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:MODIFY")
    public ResponseDataDTO modifySubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.modifySubmit(rbiBondAccountingEntriesDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:VERIFY")
    public ResponseDataDTO getVerifyModuleName(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.getVerifyModuleName(rbiBondAccountingEntriesDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:VERIFY")
    public ResponseDataDTO getVerifyDetails(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.getVerifyDetails(rbiBondAccountingEntriesDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_ACCOUNTING_ENTRIES:VERIFY")
    public ResponseDataDTO verifySubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.verifySubmit(rbiBondAccountingEntriesDTO);
    }

    @Override
    public ResponseDataDTO deleteSubmit(RbiBondAccountingEntriesDTO rbiBondAccountingEntriesDTO) {
        return rbiBondAccountingEntriesService.deleteSubmit(rbiBondAccountingEntriesDTO);
    }
}