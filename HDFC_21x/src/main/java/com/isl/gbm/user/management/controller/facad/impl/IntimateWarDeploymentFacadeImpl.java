package com.isl.gbm.user.management.controller.facad.impl;

import com.isl.gbm.common.dto.ResponseDataDTO;
import com.isl.gbm.user.management.controller.facad.IntimateWarDeploymentFacade;
import com.isl.gbm.user.management.service.IntimateWarDeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntimateWarDeploymentFacadeImpl implements IntimateWarDeploymentFacade {

    @Autowired
    private IntimateWarDeploymentService intimateWarDeploymentService;

    @Override
    public ResponseDataDTO getWarDeploymentDetails() {
        return intimateWarDeploymentService.getWarDeploymentDetails();
    }
}
