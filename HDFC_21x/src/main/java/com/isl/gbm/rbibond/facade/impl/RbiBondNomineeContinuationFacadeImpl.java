package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathDTO;
import com.isl.gbm.rbibond.dto.RbiBondNomineeContinuationDTO;
import com.isl.gbm.rbibond.facade.RbiBondNomineeContinuationFacade;
import com.isl.gbm.rbibond.service.RbiBondNomineeContinuationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Facade for handling RBI Bond Nominee Continuation operations.
 *
 * @author Sneha Sampath
 * @date 2025-02-05
 */
@Component
public class RbiBondNomineeContinuationFacadeImpl implements RbiBondNomineeContinuationFacade {

    @Autowired
    private RbiBondNomineeContinuationService rbiBondNomineeContinuationService;



    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD")
    public ResponseDataDTO getAddDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationService.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD")
    public ResponseDataDTO getAddValidation(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationService.getAddValidation(rbiBondClosureBeforeMaturDTO);
    }
    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD")
    public ResponseDataDTO add(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationService.add(rbiBondClosureBeforeMaturDTO);
    }
    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:INQUIRY")
    public ResponseDataDTO getInqDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationService.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }
    @Override
//    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:VERIFY", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:DELETE"})
    public ResponseDataDTO getVerDelDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationService.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
//    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:VERIFY", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:DELETE"})
    public ResponseDataDTO getWboBranchDetails(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationService.getWboBranchDetails(rbiBondClosureBeforeMaturDTO);
    }




    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:VERIFY")
    public ResponseDataDTO verify(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationService.verify(rbiBondClosureBeforeMaturDTO);
    }
    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD")
    public ResponseDataDTO getPanValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationService.getPanValidation(rbiBondClosureBeforeMaturDTO);
    }
    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:DELETE")
    public ResponseDataDTO delete(RbiBondNomineeContinuationDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondNomineeContinuationService.delete(rbiBondClosureBeforeMaturDTO);
    }
}
