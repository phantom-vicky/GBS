package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.common.dto.TocSharingMaintenanceDTO;
import com.isl.gbm.common.service.TocSharingMaintenanceService;
import com.isl.gbm.rbibond.dto.RbiBondTocSharingMaintenanceDTO;
import com.isl.gbm.rbibond.facade.RbiBondTocSharingMaintenanceFacade;
import com.isl.gbm.rbibond.service.RbiBondTocSharingMaintenanceService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondTocSharingMaintenanceFacadeImpl implements RbiBondTocSharingMaintenanceFacade {

    @Autowired
    private RbiBondTocSharingMaintenanceService rbiBondTocSharingMaintenanceService;

    @Override
   @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:ADD")
    public ResponseDataDTO add(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceService.add(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:ADD")
    public ResponseDataDTO addValidation(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceService.addValidation(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:MODIFY")
    public ResponseDataDTO modifySelect(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceService.modifySelect(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:MODIFY")
    public ResponseDataDTO modify(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceService.modify(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:VERIFY")
    public ResponseDataDTO verifyDeleteSelect(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceService.verifyDeleteSelect(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:VERIFY")
    public ResponseDataDTO verify(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceService.verify(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:DELETE")
    public ResponseDataDTO delete(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceService.delete(rbiBondTocSharingMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SHARING_MAINTENANCE:ENQUIRY")
    public ResponseDataDTO inquiry(RbiBondTocSharingMaintenanceDTO rbiBondTocSharingMaintenanceDTO) {
        return rbiBondTocSharingMaintenanceService.inquiry(rbiBondTocSharingMaintenanceDTO);
    }
}
