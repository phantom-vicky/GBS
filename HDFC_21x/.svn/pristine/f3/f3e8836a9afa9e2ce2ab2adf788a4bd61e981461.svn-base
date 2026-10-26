package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.SchemeMasterRequestResponseDTO;
import com.isl.gbm.rbibond.facade.RbiBondSchemeMasterFacade;
import com.isl.gbm.rbibond.service.RbiBondSchemeMasterService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondSchemeMasterFacadeImpl implements RbiBondSchemeMasterFacade {

    @Autowired
    RbiBondSchemeMasterService rbiBondSchemeMasterService;

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:MODIFY")
    public ResponseDataDTO modifySelect(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterService.modifySelect(schemeMasterRequestResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:VERIFY")
    public ResponseDataDTO verifySelect(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterService.verifySelect(schemeMasterRequestResponseDTO);
    }
    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:ADD")
    public ResponseDataDTO add(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterService.add(schemeMasterRequestResponseDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:ENQUIRY")
    public ResponseDataDTO inquiry(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterService.inquiry(schemeMasterRequestResponseDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:VERIFY")
    public ResponseDataDTO verify(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterService.verify(schemeMasterRequestResponseDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:MODIFY")
    public ResponseDataDTO modify(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterService.modify(schemeMasterRequestResponseDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_SCHEME_MASTER_RBI_BOND:VERIFY")
    public ResponseDataDTO delete(SchemeMasterRequestResponseDTO schemeMasterRequestResponseDTO) {
        return rbiBondSchemeMasterService.delete(schemeMasterRequestResponseDTO);
    }
}
