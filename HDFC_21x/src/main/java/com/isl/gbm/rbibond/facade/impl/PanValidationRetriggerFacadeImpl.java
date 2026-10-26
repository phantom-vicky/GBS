package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerDTO;
import com.isl.gbm.rbibond.dto.PanValidationRetriggerResponseDTO;
import com.isl.gbm.rbibond.facade.PanValidationRetriggerFacade;
import com.isl.gbm.rbibond.service.PanValidationRetriggerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PanValidationRetriggerFacadeImpl implements PanValidationRetriggerFacade {
    @Autowired
    PanValidationRetriggerService panValidationRetriggerService;

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_VALIDATION_RETRIGGER:REPORT")
    public ResponseDataDTO details(PanValidationRetriggerDTO panValidationRetriggerDTO) {
        return panValidationRetriggerService.details(panValidationRetriggerDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_VALIDATION_RETRIGGER:REPORT")
    public ResponseDataDTO verify(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        return panValidationRetriggerService.verify(panValidationRetriggerResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PAN_VALIDATION_RETRIGGER:REPORT")
    public ResponseDataDTO validate(PanValidationRetriggerResponseDTO panValidationRetriggerResponseDTO) {
        return panValidationRetriggerService.validate(panValidationRetriggerResponseDTO);
    }

}
