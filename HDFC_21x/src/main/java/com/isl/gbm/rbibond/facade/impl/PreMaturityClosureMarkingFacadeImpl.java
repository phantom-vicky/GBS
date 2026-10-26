package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.PreMaturityRequestDTO;
import com.isl.gbm.rbibond.facade.PreMaturityClosureMarkingFacade;
import com.isl.gbm.rbibond.service.PreMaturityClosureMarkingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreMaturityClosureMarkingFacadeImpl implements PreMaturityClosureMarkingFacade {
    @Autowired
    PreMaturityClosureMarkingService preMaturityClosureMarkingService;

    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:ADD")
    public ResponseDataDTO addDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.addDetails(preMaturityRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:ADD")
    public ResponseDataDTO addTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.addTorch(preMaturityRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:MODIFY")
    public ResponseDataDTO modifyDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.modifyDetails(preMaturityRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:MODIFY")
    public ResponseDataDTO modifyTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.modifyTorch(preMaturityRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:VERIFY")
    public ResponseDataDTO verifyDetails(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.verifyDetails(preMaturityRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:VERIFY")
    public ResponseDataDTO verifyTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.verifyTorch(preMaturityRequestDTO);
    }
@Override
@RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:INQUIRY")
public ResponseDataDTO inquiryDetails(PreMaturityRequestDTO preMaturityRequestDTO){
return preMaturityClosureMarkingService.inquiryDetails(preMaturityRequestDTO);
}
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:INQUIRY")
    public ResponseDataDTO inquiryTorch(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.inquiryTorch(preMaturityRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:ADD")
    public ResponseDataDTO add(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.add(preMaturityRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:MODIFY")
    public ResponseDataDTO modify(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.modify(preMaturityRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:VERIFY")
    public ResponseDataDTO verify(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.verify(preMaturityRequestDTO);
    }
    @Override
    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_PRE_MATURITY_CLOSURE_MARKING:VERIFY")
    public ResponseDataDTO delete(PreMaturityRequestDTO preMaturityRequestDTO) {
        return preMaturityClosureMarkingService.delete(preMaturityRequestDTO);
    }
}
