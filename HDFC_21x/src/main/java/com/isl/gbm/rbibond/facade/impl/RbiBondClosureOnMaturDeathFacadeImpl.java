package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.BondActualInterestPaymentRequestDTO;
import com.isl.gbm.rbibond.dto.RbiBondClosureOnMaturDeathDTO;
import com.isl.gbm.rbibond.facade.RbiBondClosureOnMaturDeathFacade;
import com.isl.gbm.rbibond.service.RbiBondClosureOnMaturDeathService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class RbiBondClosureOnMaturDeathFacadeImpl implements RbiBondClosureOnMaturDeathFacade {

    @Autowired
    RbiBondClosureOnMaturDeathService rbiBondClosureOnMaturDeathService;


    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD")
    public ResponseDataDTO getAddDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathService.getAddDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD")
    public ResponseDataDTO getAddValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathService.getAddValidation(rbiBondClosureBeforeMaturDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD")
    public ResponseDataDTO getPanValidation(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathService.getPanValidation(rbiBondClosureBeforeMaturDTO);
    }

    @Override
    public ResponseDataDTO uploadFile(MultipartFile textFile, String schemeCode, String paymentDate) {
        return rbiBondClosureOnMaturDeathService.uploadFile(textFile, schemeCode, paymentDate);

    }

    @Override
    public ResponseDataDTO downloadFileFromSFTP(BondActualInterestPaymentRequestDTO paymentRequestDTO) {
        return rbiBondClosureOnMaturDeathService.downloadFileFromSFTP(paymentRequestDTO);
    }

    @Override
//    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:VERIFY", "GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:DELETE"})
    public ResponseDataDTO getVerDelDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathService.getVerDelDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:INQUIRY")
    public ResponseDataDTO getInqDetails(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathService.getInqDetails(rbiBondClosureBeforeMaturDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:ADD")
    public ResponseDataDTO add(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathService.add(rbiBondClosureBeforeMaturDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:VERIFY")
    public ResponseDataDTO verify(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathService.verify(rbiBondClosureBeforeMaturDTO);
    }

    @Override
//    @RequiresPermissions("GBM_RBIBOND_GU_FUNCTIONALITY:RBIBOND_ACCOUNT_CLOSURE_ON_DEATH:DELETE")
    public ResponseDataDTO delete(RbiBondClosureOnMaturDeathDTO rbiBondClosureBeforeMaturDTO) {
        return rbiBondClosureOnMaturDeathService.delete(rbiBondClosureBeforeMaturDTO);
    }
}
