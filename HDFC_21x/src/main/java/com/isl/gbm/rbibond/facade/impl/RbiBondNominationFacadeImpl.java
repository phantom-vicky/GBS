package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondNominationDto;
import com.isl.gbm.rbibond.facade.RbiBondNominationFacade;
import com.isl.gbm.rbibond.service.RbiBondNominationService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondNominationFacadeImpl implements RbiBondNominationFacade {


    @Autowired
    private RbiBondNominationService rbiBondNominationService;

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:INQUIRY"})
    public ResponseDataDTO inquiry(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.inquiry(rbiBondNominationDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:VERIFY"})
    public ResponseDataDTO getVerifyDeleteDetails(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.getVerifyDeleteDetails(rbiBondNominationDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:VERIFY"})
    public ResponseDataDTO delete(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.delete(rbiBondNominationDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:VERIFY"})
    public ResponseDataDTO verify(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.verify(rbiBondNominationDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:MODIFY"})
    public ResponseDataDTO addModify(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.addModify(rbiBondNominationDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_GU_RBIBOND:RBIBOND_BOND_NOMINATIONS:MODIFY"})
    public ResponseDataDTO modifySelect(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.modifySelect(rbiBondNominationDto);
    }

    public ResponseDataDTO modifyPanTorch(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.modifyPanTorch(rbiBondNominationDto);
    }
    public ResponseDataDTO inquiryPanTorch(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.inquiryPanTorch(rbiBondNominationDto);
    }
    public ResponseDataDTO blaNo(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.blaNo(rbiBondNominationDto);
    }
    public ResponseDataDTO getInqBlaNo(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.getInqBlaNo(rbiBondNominationDto);
    }
    public ResponseDataDTO getsubscriptionIdMod(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.getsubscriptionIdMod(rbiBondNominationDto);
    }
    public ResponseDataDTO getsubscriptionIdInq(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.getsubscriptionIdInq(rbiBondNominationDto);
    }
    @Override
    public ResponseDataDTO nomineeValidation(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.nomineeValidation(rbiBondNominationDto);
    }

    @Override
    public ResponseDataDTO fetchState(RbiBondNominationDto rbiBondNominationDto) {
        return rbiBondNominationService.fetchState(rbiBondNominationDto);
    }
}
