package com.isl.gbm.rbibond.facade.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondSubscripReversalAccNoDTO;
import com.isl.gbm.rbibond.facade.RbiBondSubscripReversalAccNoFacade;
import com.isl.gbm.rbibond.service.RbiBondSubscripReversalAccNoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondSubscripReversalAccNoFacadeImpl implements RbiBondSubscripReversalAccNoFacade {

    @Autowired
    RbiBondSubscripReversalAccNoService rbiBondSubscripReversalAccNoService;

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:ADD")
    public ResponseDataDTO add(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
       return rbiBondSubscripReversalAccNoService.add(bondSubscripReversalAccNoDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:ADD")
    public ResponseDataDTO addValidation(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoService.addValidation(bondSubscripReversalAccNoDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:VERIFY")
    public ResponseDataDTO verify(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoService.verify(bondSubscripReversalAccNoDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:DELETE")
    public ResponseDataDTO delete(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoService.delete(bondSubscripReversalAccNoDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:INQUIRY")
    public ResponseDataDTO inquiry(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoService.inquiry(bondSubscripReversalAccNoDTO);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_SUBS_REVERSAL_CHANGE_ACC_NO:VERIFY")
    public ResponseDataDTO verifySelect(BondSubscripReversalAccNoDTO bondSubscripReversalAccNoDTO) {
        return rbiBondSubscripReversalAccNoService.verifySelect(bondSubscripReversalAccNoDTO);
    }
}
