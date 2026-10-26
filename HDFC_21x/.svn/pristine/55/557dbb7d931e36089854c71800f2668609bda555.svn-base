package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondBlaDeathMarkingDTO;
import com.isl.gbm.rbibond.facade.RbiBondBlaDeathMarkingFacade;
import com.isl.gbm.rbibond.service.RbiBondBlaDeathMarkingService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondBlaDeathMarkingFacadeImpl implements RbiBondBlaDeathMarkingFacade{


    @Autowired
    RbiBondBlaDeathMarkingService rbiBondBlaDeathMarkingService;

    @Override
  //  @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:ADD")
    public ResponseDataDTO getAddPanNoDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.getAddPanNoDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
   // @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:ADD")
    public ResponseDataDTO getAddDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.getAddDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    //@RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:ADD")
    public ResponseDataDTO addCutomerDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.addCutomerDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
   // @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:VERIFY", "GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:DELETE"})
    public ResponseDataDTO getVerDelPanDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.getVerDelPanDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
   // @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:VERIFY")
    public ResponseDataDTO getVerifyDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.getVerifyDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    //@RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:VERIFY")
    public ResponseDataDTO verifyCutomerDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.verifyCutomerDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
   // @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:DELETE")
    public ResponseDataDTO getDeleteDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.getDeleteDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    //@RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:DELETE")
    public ResponseDataDTO deleteCutomerDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.deleteCutomerDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
    //@RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:INQUIRY")
    public ResponseDataDTO getInquiryPanDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.getInquiryPanDetails(rbiBondBlaDeathMarkingDTO);
    }

    @Override
   // @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:DEATH_MARKING_FOR_BLA:INQUIRY")
    public ResponseDataDTO getInquiryDetails(RbiBondBlaDeathMarkingDTO rbiBondBlaDeathMarkingDTO) {
        return rbiBondBlaDeathMarkingService.getInquiryDetails(rbiBondBlaDeathMarkingDTO);
    }



}
