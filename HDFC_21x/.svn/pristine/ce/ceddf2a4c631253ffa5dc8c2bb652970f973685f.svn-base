package com.isl.gbm.rbibond.facade.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.rbibond.dto.RbiBondEodBodDTO;
import com.isl.gbm.rbibond.facade.RbiBondCentalizedBodEodFacade;
import com.isl.gbm.rbibond.service.RbiBondCentalizedBodEodService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RbiBondCentalizedBodEodFacadeImpl implements RbiBondCentalizedBodEodFacade {

    @Autowired
    private RbiBondCentalizedBodEodService centalizedBODService;
    
    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_BOD:ENQUIRY")
    public ResponseDataDTO add(RbiBondEodBodDTO centalizedEodBodDTO){
        return centalizedBODService.add(centalizedEodBodDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_BOD:ENQUIRY")
    public ResponseDataDTO getDate(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODService.getDate(centalizedEodBodDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_BOD:ENQUIRY")
    public ResponseDataDTO getLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODService.getLevelBranch(centalizedEodBodDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_EOD:ENQUIRY")
    public ResponseDataDTO startEodProcess(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODService.startEodProcess(centalizedEodBodDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_EOD:ENQUIRY")
    public ResponseDataDTO getEodDate(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODService.getEodDate(centalizedEodBodDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_EOD:ENQUIRY")
    public ResponseDataDTO getEodLevelBranch(RbiBondEodBodDTO centalizedEodBodDTO) {
        return centalizedBODService.getEodLevelBranch(centalizedEodBodDTO);
    }

    @Override
    @RequiresPermissions("GBM_DC_RBIBOND:RBIBOND_CENTRALIZED_EOD:ENQUIRY")
    public ResponseDataDTO getTimeValidation() {
        return centalizedBODService.getTimeValidation();
    }

}
