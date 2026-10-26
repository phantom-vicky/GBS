package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondHUFDeathMarkingDTO;
import com.isl.gbm.rbibond.facade.RbiBondHUFDeathMarkingFacade;
import com.isl.gbm.rbibond.service.RbiBondHUFDeathMarkingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondHUFDeathMarkingFacadeImpl  implements RbiBondHUFDeathMarkingFacade {
    @Autowired
    RbiBondHUFDeathMarkingService rbiBondHUFDeathMarkingService;

    @Override
   // @RequiresPermissions("GBM_GU_RBIBOND:RBIBOND_HUF_DEATH_MARKING_MENU:ADD")
    public ResponseDataDTO getAddPanNoDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.getAddPanNoDetails(rbiBondHUFDeathMarkingDTO);

    }

    @Override
    public ResponseDataDTO getCoParcenerDtls(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.getCoParcenerDtls(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO addCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.addCutomerDetails(rbiBondHUFDeathMarkingDTO);
    }

    public ResponseDataDTO getVerDelPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.getVerDelPanDetails(rbiBondHUFDeathMarkingDTO);
    }


    @Override
    public ResponseDataDTO getVerifyDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.getVerifyDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO verifyCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.verifyCutomerDetails(rbiBondHUFDeathMarkingDTO);
    }

    public ResponseDataDTO deleteCutomerDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.deleteCutomerDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getInquiryPanDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.getInquiryPanDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    public ResponseDataDTO getInquiryDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.getInquiryDetails(rbiBondHUFDeathMarkingDTO);
    }

    @Override
    // @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:ADD")
    public ResponseDataDTO getAddDetails(RbiBondHUFDeathMarkingDTO rbiBondHUFDeathMarkingDTO) {
        return rbiBondHUFDeathMarkingService.getAddDetails(rbiBondHUFDeathMarkingDTO);
    }
}
