package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.facade.RbiBondBodEodFacade;
import com.isl.gbm.rbibond.service.RbiBondBodEodService;
import com.isl.gbm.rbibond.dto.BodEodDto;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component

public class RbiBondBodEodFacadeImpl implements RbiBondBodEodFacade {

    @Autowired
    private RbiBondBodEodService rbiBondBodEodService;


    @Override
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_EOD:ENQUIRY")
    @RequiresPermissions("GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_LINK_CELL_BOD:ENQUIRY")
    public ResponseDataDTO startLinkCellBodProcess(BodEodDto bodEodDto) {
        return rbiBondBodEodService.startBodProcess(bodEodDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_CB_BOD:ENQUIRY")
//    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_EOD:ENQUIRY")
    public ResponseDataDTO startPayBranchBodProcess(BodEodDto bodEodDto) {
        return rbiBondBodEodService.startBodProcess(bodEodDto);
    }


    @Override
    @RequiresPermissions("GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_LINK_CELL_EOD:ENQUIRY")
    public ResponseDataDTO startLinkCellEodProcess(BodEodDto bodEodDto) {
        return rbiBondBodEodService.startLinkCellEodProcess(bodEodDto);
    }

    @Override
    @RequiresPermissions("GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_CB_EOD:ENQUIRY")
    public ResponseDataDTO startPayBranchEodProcess(BodEodDto bodEodDto) {
        return rbiBondBodEodService.startPayBranchEodProcess(bodEodDto);
    }

    @Override
    @RequiresPermissions(logical = Logical.OR, value = {"GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_CB_EOD:ENQUIRY", "GBM_RBIBOND_ADMIN_FUNCTIONALITY:RBIBOND_LINK_CELL_EOD:ENQUIRY"})
    public ResponseDataDTO getTimeValidation() {
        return rbiBondBodEodService.getTimeValidation();
    }
}
