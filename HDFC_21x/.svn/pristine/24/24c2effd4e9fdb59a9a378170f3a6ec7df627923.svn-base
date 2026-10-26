package com.isl.gbm.rbibond.facade.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondTdsParameterDTO;
import com.isl.gbm.rbibond.facade.TdsParameterForRbiBondFacade;
import com.isl.gbm.rbibond.service.TdsParameterForRbiBondService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TdsParameterForRbiBondFacadeimpl implements TdsParameterForRbiBondFacade {



    @Autowired
    private TdsParameterForRbiBondService tdsParameterForRbiBondService;



    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO add(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondService.add(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ADD")
    public ResponseDataDTO addValidation(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondService.addValidation(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:ENQUIRY")
    public ResponseDataDTO inquiryDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondService.inquiryDetails(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO verifyDeleteDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondService.verifyDeleteDetails(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondService.verify(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:VERIFY")
    public ResponseDataDTO delete(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondService.delete(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY")
    public ResponseDataDTO modifyDetails(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondService.modifyDetails(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TDS_PARAMETER_RBI_BOND:MODIFY")
    public ResponseDataDTO modifySubmit(RbiBondTdsParameterDTO rbiBondTdsParameterDTO) {
        return tdsParameterForRbiBondService.modifySubmit(rbiBondTdsParameterDTO);
    }
}
