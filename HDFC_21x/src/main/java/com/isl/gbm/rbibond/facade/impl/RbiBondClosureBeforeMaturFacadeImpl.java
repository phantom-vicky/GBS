package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDTO;
import com.isl.gbm.rbibond.facade.RbiBondClosureBeforeMaturFacade;
import com.isl.gbm.rbibond.service.RbiBondClosureBeforeMaturService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondClosureBeforeMaturFacadeImpl implements RbiBondClosureBeforeMaturFacade {

    @Autowired
    RbiBondClosureBeforeMaturService rbiBondClosureBeforeMaturService;


    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:ADD")
    public ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturService.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:VERIFY", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:DELETE"})
    public ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturService.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:INQUIRY")
    public ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturService.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:ADD")
    public ResponseDataDTO add(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturService.add(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:VERIFY")
    public ResponseDataDTO verify(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturService.verify(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:DELETE")
    public ResponseDataDTO delete(RbiBondClosureBeforeMaturDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturService.delete(rbiBondClosureBeforeMaturDTO);
    }
}
