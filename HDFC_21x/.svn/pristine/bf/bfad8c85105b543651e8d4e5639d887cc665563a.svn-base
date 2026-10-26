package com.isl.gbm.rbibond.facade.impl;


import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondCodeMappingDTO;
import com.isl.gbm.rbibond.facade.RbiBondCodeMappingFacade;
import com.isl.gbm.rbibond.facade.TdsParameterForRbiBondFacade;
import com.isl.gbm.rbibond.service.RbiBondCodeMappingService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondCodeMappingFacadeimpl implements RbiBondCodeMappingFacade {



    @Autowired
    private RbiBondCodeMappingService rbiBondCodeMappingService;



    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ADD")
    public ResponseDataDTO add(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingService.add(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ADD")
    public ResponseDataDTO addValidation() {
        return rbiBondCodeMappingService.addValidation();
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:ENQUIRY")
    public ResponseDataDTO inquiryDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingService.inquiryDetails(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:VERIFY")
    public ResponseDataDTO verifyDeleteDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingService.verifyDeleteDetails(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingService.verify(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:VERIFY")
    public ResponseDataDTO delete(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingService.delete(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:MODIFY")
    public ResponseDataDTO modifyDetails(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingService.modifyDetails(rbiBondTdsParameterDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CDE_MAPPING_RBI_BOND:MODIFY")
    public ResponseDataDTO modifySubmit(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingService.modifySubmit(rbiBondTdsParameterDTO);
    }

    @Override
    public ResponseDataDTO getBranchCode(RbiBondCodeMappingDTO rbiBondTdsParameterDTO) {
        return rbiBondCodeMappingService.getBranchCode(rbiBondTdsParameterDTO);
    }


}
