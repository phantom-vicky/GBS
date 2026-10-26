package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.FreezUnfreezDTO;
import com.isl.gbm.rbibond.facade.FreezeUnfreezeFacade;
import com.isl.gbm.rbibond.service.FreezeUnfreezeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Component;

@Component
public class FreezeUnfreezeFacadeImpl implements FreezeUnfreezeFacade {


    private final FreezeUnfreezeService freezeUnfreezeService;

    public FreezeUnfreezeFacadeImpl(FreezeUnfreezeService freezeUnfreezeService) {
        this.freezeUnfreezeService = freezeUnfreezeService;
    }

    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:ADD")
    public ResponseDataDTO getAddDetails(FreezUnfreezDTO freezUnfreezDTO) {
        return freezeUnfreezeService.getAddDetails(freezUnfreezDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:INQUIRY")
    public ResponseDataDTO inquiry(FreezUnfreezDTO freezUnfreezDTO) {
        return freezeUnfreezeService.inquiry(freezUnfreezDTO);
    }

    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:VERIFY")
    public ResponseDataDTO verify(FreezUnfreezDTO freezUnfreezDTO) {
        return freezeUnfreezeService.verify(freezUnfreezDTO);
    }


    @Override
    @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_FREEZE_UNFREEZE:MODIFY")
    public ResponseDataDTO modifySelect(FreezUnfreezDTO freezUnfreezDTO) {
        return freezeUnfreezeService.modifySelect(freezUnfreezDTO);
    }

}
