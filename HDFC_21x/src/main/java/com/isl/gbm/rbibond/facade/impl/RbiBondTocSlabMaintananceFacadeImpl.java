package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondTocSlabMaintenanceDTO;
import com.isl.gbm.rbibond.facade.RbiBondTocSlabMaintananceFacade;
import com.isl.gbm.rbibond.service.RbiBondTocSlabMaintenanceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondTocSlabMaintananceFacadeImpl implements RbiBondTocSlabMaintananceFacade {
    @Autowired
    private RbiBondTocSlabMaintenanceService rbiBondTocSlabMaintenanceService;


    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:MODIFY")
    public ResponseDataDTO selectModifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceService.selectModifySlab(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:VERIFY")
    public ResponseDataDTO selectVerifySlab(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceService.selectVerifySlab(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:ADD")
    public ResponseDataDTO getAddDetails(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceService.getAddDetails(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:ADD")
    public ResponseDataDTO save(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceService.save(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:MODIFY")
    public ResponseDataDTO modify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceService.modify(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:VERIFY")
    public ResponseDataDTO verify(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceService.verify(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:VERIFY")
    public ResponseDataDTO delete(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceService.delete(rbiBondTocSlabMaintenanceDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_TOC_SLAB_MAINTENANCE:ENQUIRY")
    public ResponseDataDTO inquiry(RbiBondTocSlabMaintenanceDTO rbiBondTocSlabMaintenanceDTO) {
        return rbiBondTocSlabMaintenanceService.inquiry(rbiBondTocSlabMaintenanceDTO);
    }
}
