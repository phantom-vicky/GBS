package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondSchemeParameterDTO;
import com.isl.gbm.rbibond.facade.RbiBondSchemeParameterFacade;
import com.isl.gbm.rbibond.service.RbiBondSchemeParameterService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondSchemeParameterFacadeImpl implements RbiBondSchemeParameterFacade {

    @Autowired
    private RbiBondSchemeParameterService efreightSchemeParameterService;
    
    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:ADD")
    public ResponseDataDTO isRecordExist(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterService.isRecordExist(rbiBondSchemeParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:ADD")
    public ResponseDataDTO add(RbiBondSchemeParameterDTO EfreightSchemeParameterDTO) {
        return efreightSchemeParameterService.add(EfreightSchemeParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:MODIFY")
    public ResponseDataDTO modifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterService.modifySelect(rbiBondSchemeParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:MODIFY")
    public ResponseDataDTO modify(RbiBondSchemeParameterDTO EfreightSchemeParameterDTO) {
        return efreightSchemeParameterService.modify(EfreightSchemeParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:VERIFY")
    public ResponseDataDTO verify(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterService.verify(rbiBondSchemeParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:VERIFY")
    public ResponseDataDTO unVerifySelect(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterService.unVerifySelect(rbiBondSchemeParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:VERIFY")
    public ResponseDataDTO delete(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterService.delete(rbiBondSchemeParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBI:GBM_rbi_bond_scheme_parameter:ENQUIRY")
    public ResponseDataDTO inquiry(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterService.inquiry(rbiBondSchemeParameterDTO);
    }

    @Override
    public ResponseDataDTO interestRtCdTorch(RbiBondSchemeParameterDTO rbiBondSchemeParameterDTO) {
        return efreightSchemeParameterService.interestRtCdTorch(rbiBondSchemeParameterDTO);
    }
}
