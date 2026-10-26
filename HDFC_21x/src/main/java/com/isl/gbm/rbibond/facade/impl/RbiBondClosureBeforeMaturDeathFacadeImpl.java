package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureBeforeMaturDeathDTO;
import com.isl.gbm.rbibond.facade.RbiBondClosureBeforeMaturDeathFacade;
import com.isl.gbm.rbibond.facade.RbiBondClosureBeforeMaturFacade;
import com.isl.gbm.rbibond.service.RbiBondClosureBeforeMaturDeathService;
import com.isl.gbm.rbibond.service.RbiBondClosureBeforeMaturService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondClosureBeforeMaturDeathFacadeImpl implements RbiBondClosureBeforeMaturDeathFacade {

    @Autowired
    RbiBondClosureBeforeMaturDeathService rbiBondClosureBeforeMaturDeathService;


    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_NOR:ADD")
    public ResponseDataDTO getAddDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathService.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:VERIFY", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:DELETE"})
    public ResponseDataDTO getVerDelDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathService.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:INQUIRY")
    public ResponseDataDTO getInqDetails(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathService.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:ADD")
    public ResponseDataDTO add(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathService.add(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:VERIFY")
    public ResponseDataDTO verify(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathService.verify(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_BM_DEATH:DELETE")
    public ResponseDataDTO delete(RbiBondClosureBeforeMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureBeforeMaturDeathService.delete(rbiBondClosureBeforeMaturDTO);
    }
}
